create or replace function prepare_tags(
	tags_array varchar(50) []
)
	returns varchar(50) [] as $$
with tag_rows as (insert into tag (name)
values (unnest(prepare_tags.tags_array))
on conflict (name)
	do update SET name = EXCLUDED.name
returning coalesce(merged_with, name))
select array(select distinct *
             from tag_rows);
$$
language sql;

comment on function prepare_tags(varchar(50) [])
IS $$
Записывает входной массив тегов в бд, возвращает массив тегов с удаленными дубликатами и связанными тегами.
$$;

create or replace function insert_meme(
	created_by    meme.created_by%TYPE,
	description   meme.description%TYPE,
	name          meme.name%TYPE,
	create_time   meme.create_time%TYPE,
	is_public     meme.is_public%TYPE,
	tags_array    meme.tags_array%TYPE,
	lurkmore_link meme.lurkmore_link%TYPE default null,
	picture_id    meme.picture_id%TYPE default null
)
	returns meme as $$
declare
	prepared_tags varchar(50) [];
	saved_meme    meme;
begin
	select prepare_tags(insert_meme.tags_array)
	into prepared_tags;
	
	insert into meme (
		created_by,
		description,
		name,
		create_time,
		is_public,
		lurkmore_link,
		picture_id,
		tags_array)
	values
		(insert_meme.created_by,
		 insert_meme.description,
		 insert_meme.name,
		 insert_meme.create_time,
		 insert_meme.is_public,
		 insert_meme.lurkmore_link,
		 insert_meme.picture_id,
		 prepared_tags)
	returning *
		into saved_meme;
	
	insert into meme_tag (meme_id, tag_name)
		select
			saved_meme.id,
			unnest(prepared_tags);
	
	return saved_meme;
end;
$$
language plpgsql;

comment on function insert_meme(created_by    bigint,
                                description   varchar(1000),
                                name          varchar(255),
                                create_time   timestamp,
                                is_public     boolean,
                                tags_array    varchar(50) [],
                                lurkmore_link varchar(255),
                                picture_id    varchar(255),
                                is_deleted    boolean)
IS $$
Записыват мем в базу, вызывает функцию insert_tags() для полученного массива тегов.
Будет возвращена созданная запись, массив тегов будет отредактирован в соответствии с объединенными тегами.
Пример использования:
select *
from insert_meme(created_by := :created_by :: bigint,
                 description := :description :: varchar,
                 name := :name :: varchar,
                 create_time := :create_time :: timestamp,
                 is_public := :is_public :: boolean,
                 tags_array := :tags_array :: varchar [])
$$;

create or replace function merge_tag(
	derivative_tag varchar(50),
	general_tag    varchar(50),
	merged_by      bigint,
	merge_time     timestamp
)
	returns setof bigint as $$
declare
	derivative_tag_row tag;
	general_tag_row    tag;
begin
	if exists(select name
	          from tag
	          where name = merge_tag.derivative_tag)
	then
		select tag.*
		into derivative_tag_row
		from tag
		where name = merge_tag.derivative_tag;
	else
		insert into tag (name)
		values (merge_tag.derivative_tag)
		on conflict (name)
			do update set name = EXCLUDED.name
		returning tag.*
			into derivative_tag_row;
	end if;
	
	if exists(select name
	          from tag
	          where name = merge_tag.general_tag)
	then
		select tag.*
		into general_tag_row
		from tag
		where name = merge_tag.general_tag;
	else
		insert into tag (name)
		values (merge_tag.general_tag)
		on conflict (name)
			do update set name = EXCLUDED.name
		returning tag.*
			into general_tag_row;
	end if;
	
	if derivative_tag_row.merged_with isnull and general_tag_row.merged_with isnull
	then
		
		update tag
		set
			merged_with = general_tag,
			merged_by   = merge_tag.merged_by,
			merge_time  = merge_tag.merge_time
		where
			name = merge_tag.derivative_tag;
		
		
		return query
		update meme
		set
			tags_array = prepare_tags(tags_array)
		where
			id in (select distinct meme_id
			       from meme_tag
			       where tag_name = merge_tag.derivative_tag)
		returning meme.id;
	
	else
		
		if derivative_tag_row.merged_with notnull
		then
			-- 			todo: implement
			raise exception 'not implemented'
			using hint = 'you cant merge already merged tag';
		end if;
		
		if general_tag_row.merged_with notnull
		then
			-- 			todo: implement
			raise exception 'not implemented'
			using hint = 'you cant merge tag with already merged tag';
		end if;
	
	end if;
end;
$$
language plpgsql;

create or replace function update_meme(
	id              meme.id%TYPE,
	last_updated_by meme.last_updated_by%TYPE,
	description     meme.description%TYPE,
	name            meme.name%TYPE,
	is_public       meme.is_public%TYPE,
	tags_array      meme.tags_array%TYPE,
	lurkmore_link   meme.lurkmore_link%TYPE default null,
	picture_id      meme.picture_id%TYPE default null,
)
	returns meme as $$
declare
	prepared_tags varchar(50) [];
	saved_meme    meme;
begin
	select prepare_tags(update_meme.tags_array)
	into prepared_tags;
	
	update meme
	set
		description     = update_meme.description,
		name            = update_meme.name,
		last_updated_by = update_meme.last_updated_by,
		is_public       = update_meme.is_public,
		lurkmore_link   = update_meme.lurkmore_link,
		picture_id      = update_meme.picture_id,
		tags_array      = prepared_tags
	where
		id = update_meme.id
	returning *
		into saved_meme;
	
	delete from meme_tag m2
	where meme_id = update_meme.id;
	
	insert into meme_tag (meme_id, tag_name)
		select
			saved_meme.id,
			unnest(prepared_tags);
	
	return saved_meme;
end;
$$
language plpgsql;