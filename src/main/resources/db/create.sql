CREATE TABLE "user"
(
	id                SERIAL                NOT NULL
		CONSTRAINT user_pkey
		PRIMARY KEY,
	username          VARCHAR(255)          NOT NULL,
	password          VARCHAR(255)          NOT NULL,
	email             VARCHAR(255),
	registration_time TIMESTAMP             NOT NULL,
	roles             VARCHAR(255) [],
	is_deleted        BOOLEAN DEFAULT FALSE NOT NULL
);

COMMENT ON TABLE "user" IS 'Просто таблица пользователей';

COMMENT ON COLUMN "user".email IS 'Привязка почты - не обязательное условие регистрации. Нужно будет, например, для выдачи прав модератора.';

CREATE TABLE ban
(
	id           SERIAL               NOT NULL
		CONSTRAINT ban_pkey
		PRIMARY KEY,
	user_id      INTEGER              NOT NULL
		CONSTRAINT fk_ban__user_id
		REFERENCES "user",
	moderator_id INTEGER              NOT NULL
		CONSTRAINT fk_ban__moderator_id
		REFERENCES "user",
	start_time   TIMESTAMP            NOT NULL,
	end_time     TIMESTAMP            NOT NULL,
	reason       VARCHAR(1000)        NOT NULL,
	is_active    BOOLEAN DEFAULT TRUE NOT NULL
);

CREATE INDEX ban_user_id_idx
	ON ban (user_id);

CREATE INDEX ban_moderator_id_idx
	ON ban (moderator_id);

COMMENT ON TABLE ban IS 'Просто таблица для хранения информации о банах';

CREATE TABLE meme
(
	id            SERIAL                NOT NULL
		CONSTRAINT meme_pkey
		PRIMARY KEY,
	uploaded_by   INTEGER               NOT NULL
		CONSTRAINT fk_meme__uploaded_by
		REFERENCES "user",
	description   VARCHAR(1000),
	name          VARCHAR(255),
	upload_time   TIMESTAMP             NOT NULL,
	is_public     BOOLEAN               NOT NULL,
	lurkmore_link VARCHAR(255),
	attachment    JSON,
	tags_array    VARCHAR(50) [],
	is_deleted    BOOLEAN DEFAULT FALSE NOT NULL
);

CREATE INDEX meme_uploaded_by_idx
	ON meme (uploaded_by);

COMMENT ON TABLE meme IS 'Мем. Может содержать название, описание, картинку/видео, ссылку на лурк.';

COMMENT ON COLUMN meme.is_public IS 'Мем может быть доступным всем, или только владельцу. Личный мем можно сделать публичным. Публичный мем можно сделать личным только если с момента добавления прошло меньше N минут и другие пользователи не "сохранили" его и не добавили заметки(* возможно не стоит делать это условие обязательным)';

COMMENT ON COLUMN meme.lurkmore_link IS 'Ссылка на лурк, если там есть статья о данном меме, будет помогать при поиске похожих мемов.';

CREATE TABLE note
(
	id      SERIAL       NOT NULL
		CONSTRAINT note_pkey
		PRIMARY KEY,
	user_id INTEGER      NOT NULL
		CONSTRAINT fk_note__user_id
		REFERENCES "user",
	meme_id BIGINT       NOT NULL
		CONSTRAINT fk_note__meme_id
		REFERENCES meme,
	note    VARCHAR(500) NOT NULL
);

CREATE UNIQUE INDEX note_user_id_meme_id_uindex
	ON note (user_id, meme_id);

CREATE INDEX note_user_id_idx
	ON note (user_id);

CREATE INDEX note_meme_id_idx
	ON note (meme_id);

COMMENT ON TABLE note IS 'Заметки пользователя о мемах. Видны только пользователю. На основной поиск не влияет, будет отдельный поиск по заметкам и список мемов с заметками.';

CREATE TABLE tag
(
	id          SERIAL            NOT NULL
		CONSTRAINT tag_pkey
		PRIMARY KEY,
	tag         VARCHAR(50)       NOT NULL,
	memes_count INTEGER DEFAULT 0 NOT NULL,
	merged_with INTEGER
		CONSTRAINT tag_merged_with_fkey
		REFERENCES tag
);

COMMENT ON TABLE tag IS 'У мемов есть теги для поиска по ним.';

CREATE TABLE meme_tag
(
	meme_id INTEGER NOT NULL
		CONSTRAINT fk_meme_tag__meme_id
		REFERENCES meme,
	tag_id  INTEGER NOT NULL
		CONSTRAINT fk_meme_tag__tag_id
		REFERENCES tag
);

CREATE UNIQUE INDEX meme_tag_meme_id_tag_id_uindex
	ON meme_tag (meme_id, tag_id);

CREATE INDEX meme_tag_meme_id_idx
	ON meme_tag (meme_id);

CREATE INDEX meme_tag_tag_id_idx
	ON meme_tag (tag_id);

CREATE TABLE bookmark_group
(
	id          SERIAL            NOT NULL
		CONSTRAINT bookmark_group_pkey
		PRIMARY KEY,
	name        VARCHAR(80)       NOT NULL,
	owner_id    INTEGER           NOT NULL
		CONSTRAINT bookmark_group_owner_fkey
		REFERENCES "user",
	memes_count INTEGER DEFAULT 0 NOT NULL
);

CREATE TABLE bookmark
(
	meme_id     INTEGER                 NOT NULL
		CONSTRAINT bookmark_meme_id_fkey
		REFERENCES meme,
	group_id    INTEGER                 NOT NULL
		CONSTRAINT bookmark_group_id_fkey
		REFERENCES bookmark_group,
	adding_time TIMESTAMP DEFAULT now() NOT NULL
);

CREATE UNIQUE INDEX bookmark_meme_id_group_id_uindex
	ON bookmark (meme_id, group_id);

CREATE VIEW user_with_bans_view AS
	SELECT
		u.id                AS u_id,
		u.username          AS u_username,
		u.password          AS u_password,
		u.email             AS u_email,
		u.registration_time AS u_registration_time,
		u.roles             AS u_roles,
		u.is_deleted        AS u_is_deleted,
		b.id                AS b_id,
		b.user_id           AS b_user_id,
		b.moderator_id      AS b_moderator_id,
		b.start_time        AS b_start_time,
		b.end_time          AS b_end_time,
		b.reason            AS b_reason,
		b.is_active         AS b_is_active
	FROM ("user" u
		LEFT JOIN ban b ON ((u.id = b.user_id)));

