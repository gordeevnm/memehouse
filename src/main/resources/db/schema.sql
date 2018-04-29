CREATE TABLE "user"
(
	id                BIGSERIAL             NOT NULL PRIMARY KEY,
	username          VARCHAR(255)          NOT NULL,
	password          VARCHAR(255)          NOT NULL,
	email             VARCHAR(255),
	roles             VARCHAR(255) [],
	registration_time TIMESTAMP             NOT NULL,
	deletion_time     TIMESTAMP             NULL,
	is_deleted        BOOLEAN DEFAULT FALSE NOT NULL
);

CREATE TABLE ban
(
	id           BIGSERIAL     NOT NULL PRIMARY KEY,
	user_id      BIGINT        NOT NULL REFERENCES "user" (id),
	moderator_id BIGINT        NOT NULL REFERENCES "user" (id),
	start_time   TIMESTAMP     NOT NULL,
	end_time     TIMESTAMP     NOT NULL,
	reason       VARCHAR(1000) NOT NULL,
	is_active    BOOLEAN       NOT NULL DEFAULT TRUE
);

CREATE TABLE meme
(
	id            BIGSERIAL      NOT NULL PRIMARY KEY,
	created_by    BIGINT         NOT NULL REFERENCES "user" (id),
	description   VARCHAR(1000)  NOT NULL,
	name          VARCHAR(255)   NOT NULL,
	create_time   TIMESTAMP      NOT NULL,
	is_public     BOOLEAN        NOT NULL,
	lurkmore_link VARCHAR(255),
	picture_id    VARCHAR(255),
	tags_array    VARCHAR(50) [] NOT NULL,
	is_deleted    BOOLEAN        NOT NULL DEFAULT FALSE
);

CREATE TABLE note
(
	id      BIGSERIAL    NOT NULL PRIMARY KEY,
	user_id BIGINT       NOT NULL REFERENCES "user" (id),
	meme_id BIGINT       NOT NULL REFERENCES meme (id),
	note    VARCHAR(500) NOT NULL
);

CREATE TABLE tag
(
	tag         VARCHAR(50) NOT NULL PRIMARY KEY,
	memes_count INTEGER     NOT NULL DEFAULT 0,
	merged_with VARCHAR(50) REFERENCES tag (tag),
	merge_time  TIMESTAMP,
	merged_by   BIGINT REFERENCES "user" (id)
);

CREATE TABLE meme_tag
(
	meme_id BIGINT      NOT NULL REFERENCES meme (id),
	tag     VARCHAR(50) NOT NULL REFERENCES tag (tag)
);

CREATE TABLE bookmark_group
(
	id          BIGSERIAL   NOT NULL PRIMARY KEY,
	name        VARCHAR(80) NOT NULL,
	owner_id    BIGINT      NOT NULL REFERENCES "user" (id),
	memes_count INTEGER     NOT NULL DEFAULT 0
);

CREATE TABLE bookmark
(
	id       BIGSERIAL NOT NULL PRIMARY KEY,
	meme_id  BIGINT    NOT NULL REFERENCES meme (id),
	group_id BIGINT REFERENCES bookmark_group (id)
);
