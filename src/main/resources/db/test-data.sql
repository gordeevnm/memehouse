ROLLBACK;
START TRANSACTION;

INSERT INTO public.user (username, password)
VALUES ('test', '$2a$10$hlso88ZWok8odMLC/QcYj.rgOPXFtu.fpYIE9RTCc/FzFSnehGqtC'), -- test, test
	('user_2', 'e9uZUMW8a3hyWFTGioxGbnkJ0BlLFy'),
	('user_3', 'Po7D6D9dhi8ovOyFBD7qOBcLaW842X'),
	('user_4', '2zlxdnvg4lcAOdk5TljNJI7Uxn5v3X'),
	('user_5', 'oBnptyUM88kCf3cP2VxY5tlPOAwVT7'),
	('user_6', 'RaETEkpWiCTFBR8biziKMtcB7kdVdP'),
	('user_7', 'St5UayDRQUBkN7NxH5QVLLdRvWt9nA'),
	('user_8', 'DhEvqeepTzRvzL7NARBqSr6ru4HRkk'),
	('user_9', 'uTWUxpqMpvrI5t9ZSF2UdxZHmr76jD'),
	('user_10', 'cVMqUSwu7iH0f1VmtvW4h4AwqUcLZh');

INSERT INTO role (user_id, role)
VALUES (1, 'ADMIN'),
	(1, 'MODERATOR'),
	(1, 'USER'),
	(2, 'MODERATOR'),
	(2, 'USER'),
	(3, 'USER'),
	(4, 'MODERATOR'),
	(4, 'USER'),
	(5, 'USER'),
	(6, 'USER'),
	(7, 'USER'),
	(8, 'USER');

INSERT INTO ban (user_id, end_time, reason, moderator_id)
VALUES (2, current_timestamp + INTERVAL '100 day', 'Я здесь закон', 1),
	(5, current_timestamp + INTERVAL '14 day', 'Потому что я могу', 4),
	(7, current_timestamp + INTERVAL '14 day', 'Был несогласен с властью', 4),
	(8, current_timestamp + INTERVAL '14 day', 'Надоел', 4);

INSERT INTO file (id, uploaded_by, path, mime, size, upload_time, is_temp, original_name)
VALUES (1, 1, '/files/attachments/1', 'image/jpeg', 222222222, current_timestamp, FALSE, 'asdasd.jpg'),
	(2, 1, '/files/attachments/2', 'image/jpeg', 222222222, current_timestamp, FALSE, 'asdasd1.jpg'),
	(3, 1, '/files/attachments/3', 'image/jpeg', 222222222, current_timestamp, FALSE, 'asdasd2.jpg'),
	(4, 1, '/files/attachments/4', 'image/jpeg', 222222222, current_timestamp, FALSE, 'asdasd3.jpg'),
	(5, 1, '/files/attachments/5', 'image/jpeg', 222222222, current_timestamp, FALSE, 'asdasd4.jpg'),
	(6, 1, '/files/attachments/6', 'image/jpeg', 222222222, current_timestamp, FALSE, 'asdasd5.jpg'),
	(7, 1, '/files/attachments/7', 'image/jpeg', 222222222, current_timestamp, FALSE, 'asdasd6.jpg'),
	(8, 1, '/files/attachments/8', 'image/jpeg', 222222222, current_timestamp, FALSE, 'asdasd7.jpg'),
	(9, 1, '/files/attachments/9', 'image/jpeg', 222222222, current_timestamp, FALSE, 'asdasd8.jpg'),
	(10, 1, '/files/attachments/10', 'image/jpeg', 222222222, current_timestamp, FALSE, 'asdasd9.jpg');

INSERT INTO file (id, uploaded_by, path, mime, size, upload_time, is_temp, original_name)
VALUES (11, 1, '/files/thumbs/1', 'image/jpeg', 11111111, current_timestamp, FALSE, 'asdasd.jpg'),
	(12, 1, '/files/thumbs/2', 'image/jpeg', 11111111, current_timestamp, FALSE, 'asdasd1.jpg'),
	(13, 1, '/files/thumbs/3', 'image/jpeg', 11111111, current_timestamp, FALSE, 'asdasd2.jpg'),
	(14, 1, '/files/thumbs/4', 'image/jpeg', 11111111, current_timestamp, FALSE, 'asdasd3.jpg'),
	(15, 1, '/files/thumbs/5', 'image/jpeg', 11111111, current_timestamp, FALSE, 'asdasd4.jpg'),
	(16, 1, '/files/thumbs/6', 'image/jpeg', 11111111, current_timestamp, FALSE, 'asdasd5.jpg'),
	(17, 1, '/files/thumbs/7', 'image/jpeg', 11111111, current_timestamp, FALSE, 'asdasd6.jpg'),
	(18, 1, '/files/thumbs/8', 'image/jpeg', 11111111, current_timestamp, FALSE, 'asdasd7.jpg'),
	(19, 1, '/files/thumbs/9', 'image/jpeg', 11111111, current_timestamp, FALSE, 'asdasd8.jpg'),
	(20, 1, '/files/thumbs/10', 'image/jpeg', 11111111, current_timestamp, FALSE, 'asdasd9.jpg');

INSERT INTO attachment (id, thumb_file_id, original_file_id, attachment_type, video_url)
VALUES (1, 11, 1, 'image', NULL),
	(2, 12, 2, 'image', NULL),
	(3, 13, 3, 'image', NULL),
	(4, 14, 4, 'image', NULL),
	(5, 15, 5, 'image', NULL),
	(6, 16, 6, 'image', NULL),
	(7, 17, 7, 'image', NULL),
	(8, 18, 8, 'image', NULL),
	(9, 19, 9, 'image', NULL),
	(10, 20, 10, 'image', NULL);

INSERT INTO meme (id, upload_time, attachment_id, description, name, uploaded_by, is_public)
VALUES
	(1, current_timestamp, 1, 'Чувак с тыквой на голове танцует. 3 кадра с подписями, по 1 слогу в каждом кадре', 'Ни ху я', 1, TRUE),
	(2, current_timestamp, 2, 'Грустная зеленая лягушка', 'Pepe Frog', 1, TRUE),
	(3, current_timestamp, 3, 'Старик, скрывающий боль за фальшивой улыбкой', 'Harold hide the pain', 1, TRUE),
	(4, current_timestamp, 4, 'Негр-актер тычет пальцем себе в висок', 'Негр умник', 1, TRUE),
	(5, current_timestamp, 5, 'Кадр из порнухи. Негр на желтом фоне в фуражке', 'Темный властелин', 1, TRUE),
	(6, current_timestamp, 6, 'Просто черно-белая фотография дедушки Фрейда', 'Фрейд', 1, TRUE),
	(7, current_timestamp, 7, 'Кудрявый Шайа ЛаБаф показывает магию на каком-то телешоу', 'Magic', 1, TRUE),
	(8, current_timestamp, 8, 'Несколько нарисованных воробьев с огроменными хуищами', 'Ебушки-воробушки', TRUE, 1),
	(9, current_timestamp, 9,
	 'Иконка zip-файла из винды кидает зигу и говорит "Zip File \o"; ' ||
	 'Иконки rar-архива говорят "Винрары воевали! Нарцисты ебаные"; ' ||
	 'Нарцисы говорят "сук пздц."',
	 'Zip File', 1, TRUE),
	(10, current_timestamp, 10,
	 'Ублюдок, мать твою, а ну иди сюда, говно собачье! ' ||
	 'Что, решил ко мне лезть?! Ты, засранец вонючий, мать твою, а? ' ||
	 'Ну, иди сюда,﻿ попробуй меня трахнуть, я тебя сам трахну, ублюдок, онанист чертов, будь ты проклят! ' ||
	 'Иди, идиот, трахать тебя и всю твою семью, говно собачье, жлоб вонючий, дерьмо, сука, падла! ' ||
	 'Иди сюда, мерзавец, негодяй, гад, иди сюда, ты, говно, ЖОПА!',
	 'Ублюдок, мать твою', 1, TRUE);

INSERT INTO tag (id, tag)
VALUES (1, 'Pepe'),
	(2, 'Пепе'),
	(3, 'тыква'),
	(4, 'Фрейд'),
	(5, 'фотография'),
	(6, 'фото'),
	(7, 'рисунок'),
	(8, 'воробьи'),
	(9, 'воробей'),
	(10, 'негр'),
	(12, 'ублюдок'),
	(13, 'цитата'),
	(14, 'боль'),
	(15, 'негр'),
	(16, 'картинка'),
	(17, 'гиф'),
	(18, 'видео'),
	(19, 'текст'),
	(20, 'лягушка'),
	(21, 'Harold'),
	(22, 'властелин'),
	(23, 'шакалы'),
	(24, 'Гарольд'),
	(25, 'ржака');

INSERT INTO synonymous_tag (general_tag, derivative_tag)
VALUES (1, 2),
	(16, 5),
	(16, 5),
	(16, 6),
	(16, 7),
	(8, 9),
	(21, 24),
	(16, 5);

INSERT INTO meme_tag (meme_id, tag_id)
VALUES (1, 3), (1, 16),
	(2, 1), (2, 20), (2, 16),
	(3, 21), (3, 5), (3, 23),
	(4, 10), (4, 5),
	(5, 10), (5, 5), (5, 22),
	(6, 4), (6, 5),
	(7, 5),
	(8, 8), (8, 16),
	(9, 16),
	(10, 12), (10, 19), (10, 13);

INSERT INTO note (id, user_id, meme_id, note)
VALUES (1, 1, 1, 'лол'),
	(2, 1, 2, 'кек'),
	(3, 1, 3, 'чебурек'),
	(4, 2, 4, 'схоронил'),
	(5, 2, 2, 'лол'),
	(6, 2, 3, 'кек'),
	(7, 3, 1, 'лол'),
	(8, 4, 1, 'хахахах'),
	(9, 5, 1, 'гыгыгы'),
	(10, 6, 1, 'лол');

INSERT INTO personal_tag (user_id, tag_id, meme_id)
VALUES (1, 25, 1),
	(1, 25, 2),
	(1, 25, 3),
	(1, 25, 4),
	(1, 25, 5),
	(2, 25, 6),
	(2, 25, 7),
	(2, 25, 8),
	(3, 25, 9),
	(3, 25, 1),
	(4, 25, 2),
	(5, 25, 3),
	(6, 25, 4),
	(7, 25, 5),
	(8, 25, 6);

INSERT INTO saved_meme (user_id, meme_id, saved_time)
VALUES (1, 1, current_timestamp),
	(1, 2, current_timestamp),
	(1, 3, current_timestamp),
	(1, 5, current_timestamp),
	(1, 8, current_timestamp),
	(1, 9, current_timestamp),
	(2, 5, current_timestamp),
	(2, 6, current_timestamp),
	(2, 7, current_timestamp),
	(2, 8, current_timestamp),
	(2, 9, current_timestamp),
	(3, 1, current_timestamp),
	(3, 6, current_timestamp),
	(3, 7, current_timestamp),
	(3, 9, current_timestamp),
	(4, 10, current_timestamp),
	(5, 10, current_timestamp),
	(6, 1, current_timestamp);

COMMIT;