CREATE TABLE public.user (
	id SERIAL NOT NULL,
	username varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	email varchar(255),
	registration_time timestamp without time zone NOT NULL,
	PRIMARY KEY (id)
);


COMMENT ON TABLE public.user
	IS 'Просто таблица пользователей';
COMMENT ON COLUMN public.user.email
	IS 'Привязка почты - не обязательное условие регистрации. Нужно будет, например, для выдачи прав модератора.';

CREATE TABLE public.ban (
	id SERIAL NOT NULL,
	user_id integer NOT NULL,
	moderator_id integer NOT NULL,
	start_time timestamp without time zone NOT NULL,
	end_time timestamp without time zone NOT NULL,
	reason varchar(1000) NOT NULL,
	PRIMARY KEY (id)
);

CREATE INDEX ON public.ban
	(user_id);
CREATE INDEX ON public.ban
	(moderator_id);


COMMENT ON TABLE public.ban
	IS 'Просто таблица для хранения информации о банах';

CREATE TABLE public.role (
	user_id integer NOT NULL,
	role varchar(255) NOT NULL
);

CREATE INDEX ON public.role
	(user_id);


COMMENT ON TABLE public.role
	IS 'Роли пользователя. Для выдачи прав на модерацию мемов/пользователей/тегов/...';

CREATE TABLE public.file (
	id SERIAL NOT NULL,
	uploaded_by integer NOT NULL,
	path varchar(500) NOT NULL,
	mime varchar(255) NOT NULL,
	size integer NOT NULL,
	upload_time timestamp without time zone NOT NULL,
	is_temp boolean NOT NULL,
	original_name varchar(255),
	PRIMARY KEY (id)
);

CREATE INDEX ON public.file
	(uploaded_by);


COMMENT ON TABLE public.file
	IS 'Хранение файлов. Могут храниться любые файлы. Скорее всего будет ограничение только на картинки, возможно надо будет подумать над загрузкой видео.';
COMMENT ON COLUMN public.file.is_temp
	IS 'Файл будет загружаться во время заполнения формы создания мема, по умолчанию поле равно true. После создания самого мема поле меняется на false.';
COMMENT ON COLUMN public.file.original_name
	IS 'Оригинальное название файла.';

CREATE TABLE public.meme (
	id SERIAL NOT NULL,
	uploaded_by integer NOT NULL,
	attachment_id integer NOT NULL,
	description varchar(1000),
	name varchar(255),
	upload_time timestamp without time zone NOT NULL,
	is_public boolean NOT NULL,
	lurkmore_link varchar(255),
	PRIMARY KEY (id)
);

CREATE INDEX ON public.meme
	(uploaded_by);
CREATE INDEX ON public.meme
	(attachment_id);


COMMENT ON TABLE public.meme
	IS 'Мем. Может содержать название, описание, картинку/видео, ссылку на лурк.';
COMMENT ON COLUMN public.meme.is_public
	IS 'Мем может быть доступным всем, или только владельцу. Личный мем можно сделать публичным. Публичный мем можно сделать личным только если с момента добавления прошло меньше N минут и другие пользователи не "сохранили" его и не добавили заметки(* возможно не стоит делать это условие обязательным)';
COMMENT ON COLUMN public.meme.lurkmore_link
	IS 'Ссылка на лурк, если там есть статья о данном меме, будет помогать при поиске похожих мемов.';

CREATE TABLE public.note (
	id SERIAL NOT NULL,
	user_id integer NOT NULL,
	meme_id bigint NOT NULL,
	note varchar(500) NOT NULL,
	PRIMARY KEY (id)
);

CREATE INDEX ON public.note
	(user_id);
CREATE INDEX ON public.note
	(meme_id);


COMMENT ON TABLE public.note
	IS 'Заметки пользователя о мемах. Видны только пользователю. На основной поиск не влияет, будет отдельный поиск по заметкам и список мемов с заметками.';

CREATE TABLE public.attachment (
	id SERIAL NOT NULL,
	thumb_file_id integer,
	original_file_id integer,
	attachment_type varchar(100) NOT NULL,
	video_url varchar(500),
	PRIMARY KEY (id)
);

CREATE INDEX ON public.attachment
	(thumb_file_id);
CREATE INDEX ON public.attachment
	(original_file_id);


COMMENT ON TABLE public.attachment
	IS 'Вложением может быть картинка или видео';
COMMENT ON COLUMN public.attachment.thumb_file_id
	IS 'Ссылается на таблицу file, если вложением является картинка. В файле уменьшенная версия оригинального изображения.';
COMMENT ON COLUMN public.attachment.video_url
	IS 'Заполняется ссылкой на видео, если вложение - видео(загружать видео на сервер - отдельная задача, возможно будет реализовано когда-нибудь)';

CREATE TABLE public.saved_meme (
	user_id integer NOT NULL,
	meme_id integer NOT NULL,
	saved_time timestamp NOT NULL
);

CREATE INDEX ON public.saved_meme
	(user_id);
CREATE INDEX ON public.saved_meme
	(meme_id);


COMMENT ON TABLE public.saved_meme
	IS 'Пользователь может сохранить какой-то мем. Что-то вроде "избранного"';

CREATE TABLE public.tag (
	id SERIAL NOT NULL,
	tag varchar(100) NOT NULL,
	PRIMARY KEY (id)
);


COMMENT ON TABLE public.tag
	IS 'У мемов есть теги для поиска по ним.';

CREATE TABLE public.meme_tag (
	meme_id integer NOT NULL,
	tag_id integer NOT NULL
);

CREATE INDEX ON public.meme_tag
	(meme_id);
CREATE INDEX ON public.meme_tag
	(tag_id);


CREATE TABLE public.synonymous_tag (
	general_tag integer NOT NULL,
	derivative_tag integer NOT NULL
);

CREATE INDEX ON public.synonymous_tag
	(general_tag);
CREATE INDEX ON public.synonymous_tag
	(derivative_tag);


COMMENT ON TABLE public.synonymous_tag
	IS 'Теги могут объединяться модераторами тегов для использования при поиске.';

CREATE TABLE public.personal_tag (
	user_id integer NOT NULL,
	tag_id integer NOT NULL,
	meme_id integer NOT NULL
);

CREATE INDEX ON public.personal_tag
	(user_id);
CREATE INDEX ON public.personal_tag
	(tag_id);
CREATE INDEX ON public.personal_tag
	(meme_id);


COMMENT ON TABLE public.personal_tag
	IS 'Таблица для собственных тегов пользователя, чтоб пользователь мог влиять на свою поисковую выдачу';

ALTER TABLE public.ban ADD CONSTRAINT FK_ban__user_id FOREIGN KEY (user_id) REFERENCES public.user(id);
ALTER TABLE public.ban ADD CONSTRAINT FK_ban__moderator_id FOREIGN KEY (moderator_id) REFERENCES public.user(id);
ALTER TABLE public.role ADD CONSTRAINT FK_role__user_id FOREIGN KEY (user_id) REFERENCES public.user(id);
ALTER TABLE public.file ADD CONSTRAINT FK_file__uploaded_by FOREIGN KEY (uploaded_by) REFERENCES public.user(id);
ALTER TABLE public.meme ADD CONSTRAINT FK_meme__uploaded_by FOREIGN KEY (uploaded_by) REFERENCES public.user(id);
ALTER TABLE public.meme ADD CONSTRAINT FK_meme__attachment_id FOREIGN KEY (attachment_id) REFERENCES public.attachment(id);
ALTER TABLE public.note ADD CONSTRAINT FK_note__user_id FOREIGN KEY (user_id) REFERENCES public.user(id);
ALTER TABLE public.note ADD CONSTRAINT FK_note__meme_id FOREIGN KEY (meme_id) REFERENCES public.meme(id);
ALTER TABLE public.attachment ADD CONSTRAINT FK_attachment__thumb_file_id FOREIGN KEY (thumb_file_id) REFERENCES public.file(id);
ALTER TABLE public.attachment ADD CONSTRAINT FK_attachment__original_file_id FOREIGN KEY (original_file_id) REFERENCES public.file(id);
ALTER TABLE public.saved_meme ADD CONSTRAINT FK_saved_meme__user_id FOREIGN KEY (user_id) REFERENCES public.user(id);
ALTER TABLE public.saved_meme ADD CONSTRAINT FK_saved_meme__meme_id FOREIGN KEY (meme_id) REFERENCES public.meme(id);
ALTER TABLE public.meme_tag ADD CONSTRAINT FK_meme_tag__meme_id FOREIGN KEY (meme_id) REFERENCES public.meme(id);
ALTER TABLE public.meme_tag ADD CONSTRAINT FK_meme_tag__tag_id FOREIGN KEY (tag_id) REFERENCES public.tag(id);
ALTER TABLE public.synonymous_tag ADD CONSTRAINT FK_synonymous_tag__general_tag FOREIGN KEY (general_tag) REFERENCES public.tag(id);
ALTER TABLE public.synonymous_tag ADD CONSTRAINT FK_synonymous_tag__derivative_tag FOREIGN KEY (derivative_tag) REFERENCES public.tag(id);
ALTER TABLE public.personal_tag ADD CONSTRAINT FK_personal_tag__user_id FOREIGN KEY (user_id) REFERENCES public.user(id);
ALTER TABLE public.personal_tag ADD CONSTRAINT FK_personal_tag__tag_id FOREIGN KEY (tag_id) REFERENCES public.tag(id);
ALTER TABLE public.personal_tag ADD CONSTRAINT FK_personal_tag__meme_id FOREIGN KEY (meme_id) REFERENCES public.meme(id);