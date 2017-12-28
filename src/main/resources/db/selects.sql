-- 1 Получить пользователя по id
SELECT *
FROM public.user
WHERE id = 1;

-- 2 Получить мемы по автору
SELECT *
FROM meme_view
WHERE owner_id = 1;

-- 3 Получить список блокировок пользователя
SELECT *
FROM ban
WHERE user_id = 1 AND end_time > current_time;

-- 4 Получить список пользовательских тегов к мему
SELECT *
FROM personal_tag
WHERE user_id = 1 AND meme_id = 1;

-- 5 Получить мемы по тегу(не учитываются синонимичные теги) с сортировкой по времени добавления и пагинацией(2 страница)
SELECT *
FROM meme_view
WHERE m_id IN
      (
	      SELECT m2.id
	      FROM meme_tag mt
		      JOIN meme m2 ON mt.meme_id = m2.id
		      JOIN tag t ON mt.tag_id = t.id
	      WHERE t = 1
      )
ORDER BY meme_view.m_upload_time
LIMIT 10
OFFSET 10;

-- 6 Получить мемы, к которым пользователь оставил заметку с сортировкой по времени добавления заметки и пагинацией(1 страница)
SELECT *
FROM meme_view
WHERE meme_view.m_id IN
      (
	      SELECT note.meme_id
	      FROM note
	      WHERE note.user_id = 1
      )
ORDER BY meme_view.n_id
LIMIT 10
OFFSET 0;

-- 7 Получить сохраненные мемы пользователя с сортировкой по времени сохранения и пагинацией(1 страница)
SELECT meme_view.*
FROM meme_view
	JOIN saved_meme sm ON sm.meme_id = meme_view.m_id
WHERE sm.user_id = 1
ORDER BY sm.saved_time
LIMIT 10
OFFSET 0;