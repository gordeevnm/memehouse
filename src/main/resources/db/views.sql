CREATE VIEW meme_view AS
	SELECT
		m.id                    AS m_id,
		m.uploaded_by           AS m_uploaded_by,
		m.attachment_id         AS m_attachment_id,
		m.description           AS m_description,
		m.name                  AS m_name,
		m.upload_time           AS m_upload_time,
		m.is_public             AS m_is_public,
		m.lurkmore_link         AS m_lurkmore_link,
		owner.id                AS owner_id,
		owner.username          AS owner_username,
		owner.password          AS owner_password,
		owner.email             AS owner_email,
		owner.registration_time AS owner_registration_time,
		a.id                    AS a_id,
		a.thumb_file_id         AS a_thumb_file_id,
		a.original_file_id      AS a_original_file_id,
		a.attachment_type       AS a_attachment_type,
		a.video_url             AS a_video_url,
		orig.id                 AS orig_id,
		orig.uploaded_by        AS orig_uploaded_by,
		orig.path               AS orig_path,
		orig.mime               AS orig_mime,
		orig.size               AS orig_size,
		orig.upload_time        AS orig_upload_time,
		orig.is_temp            AS orig_is_temp,
		orig.original_name      AS orig_original_name,
		thumb.id                AS thumb_id,
		thumb.uploaded_by       AS thumb_uploaded_by,
		thumb.path              AS thumb_path,
		thumb.mime              AS thumb_mime,
		thumb.size              AS thumb_size,
		thumb.upload_time       AS thumb_upload_time,
		thumb.is_temp           AS thumb_is_temp,
		thumb.original_name     AS thumb_original_name,
		n.id                    AS n_id,
		n.user_id               AS n_user_id,
		n.meme_id               AS n_meme_id,
		n.note                  AS n_note
	FROM meme m
		JOIN "user" owner ON m.uploaded_by = owner.id
		LEFT JOIN
		(attachment a
			JOIN file orig ON a.original_file_id = orig.id
			JOIN file thumb ON a.thumb_file_id = thumb.id
			) ON m.attachment_id = a.id
		LEFT JOIN note n ON m.id = n.meme_id;