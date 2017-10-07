CREATE TABLE meme (
	id          BIGINT PRIMARY KEY,
	description TEXT,
	name        VARCHAR(512),
	loadedBy    BIGINT REFERENCES "user" (id),
	isDeleted   BOOLEAN DEFAULT FALSE
);
CREATE TABLE "user" (
	id        BIGSERIAL PRIMARY KEY,
	username  VARCHAR(255) NOT NULL UNIQUE,
	password  VARCHAR(255) NOT NULL,
	isBanned  BOOLEAN DEFAULT FALSE,
	isDeleted BOOLEAN DEFAULT FALSE
);
CREATE TABLE token (
	id      BIGSERIAL PRIMARY KEY,
	token   VARCHAR(255) NOT NULL,
	user_id BIGINT REFERENCES "user" (id)
);
CREATE TABLE attachment (
	id BIGSERIAL PRIMARY KEY
);
CREATE TABLE meme_attachment (
	meme_id       BIGINT REFERENCES meme (id),
	attachment_id BIGINT REFERENCES attachment (id)
);
CREATE TABLE saved_meme (
	meme_id BIGINT REFERENCES meme (id),
	user_id BIGINT REFERENCES "user" (id)
);
CREATE TABLE meme_tag (
	meme_id BIGINT REFERENCES meme (id),
	tag     VARCHAR(255)
);
CREATE TABLE user_role (
	user_id BIGINT REFERENCES "user" (id),
	role    VARCHAR(255)
);