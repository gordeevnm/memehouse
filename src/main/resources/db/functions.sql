create or replace function insert_tags(
	tags_array varchar(50) []
)
	returns varchar(50) [] as $$
with tag_rows as (insert into tag (tag)
values (unnest(insert_tags.tags_array))
on conflict (tag)
	do update SET tag = EXCLUDED.tag
returning coalesce(tag.merged_with, tag.tag))
select array(select distinct *
             from tag_rows);
$$
language sql;

comment on function insert_meme(created_by    bigint,
                                description   varchar(1000),
                                name          varchar(255),
                                create_time   timestamp,
                                is_public     boolean,
                                tags_array    varchar(50) [],
                                lurkmore_link varchar(255),
                                picture_id    varchar(255),
                                is_deleted    boolean)
IS 'Записыват мем в базу, вызывает функцию insert_tags() для полученного массива тегов.
Будет возвращена созданная запись, массив тегов будет отредактирован в соответствии с объединенными тегами.
Пример использования:
select *
from insert_meme(created_by := :created_by :: bigint,
                 description := :description :: varchar,
                 name := :name :: varchar,
                 create_time := :create_time :: timestamp,
                 is_public := :is_public :: boolean,
                 tags_array := :tags_array :: varchar [])';

create or replace function insert_meme(
	created_by    bigint,
	description   varchar(1000),
	name          varchar(255),
	create_time   timestamp,
	is_public     boolean,
	tags_array    varchar(50) [],
	lurkmore_link varchar(255) default null,
	picture_id    varchar(255) default null,
	is_deleted    boolean default false
)
	returns meme as $$
insert into meme (created_by,
                  description,
                  name,
                  create_time,
                  is_public,
                  lurkmore_link,
                  picture_id,
                  tags_array,
                  is_deleted)
values
	(insert_meme.created_by,
	 insert_meme.description,
	 insert_meme.name,
	 insert_meme.create_time,
	 insert_meme.is_public,
	 insert_meme.lurkmore_link,
	 insert_meme.picture_id,
	 insert_tags(insert_meme.tags_array),
	 insert_meme.is_deleted)
returning meme;
$$
language sql;
