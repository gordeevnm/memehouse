ROLLBACK;
START TRANSACTION;

INSERT INTO client (username, password)
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

INSERT INTO meme (description, name, uploaded_by)
VALUES ('Чувак с тыквой на голове танцует. 3 кадра с подписями, по 1 слогу в каждом кадре', 'Ни ху я', 1),
	('Грустная зеленая лягушка', 'Pepe Frog', 1),
	('Старик, скрывающий боль за фальшивой улыбкой', 'Harold hide the pain', 1),
	('Негр-актер тычет пальцем себе в висок', 'Негр умник', 1),
	('Кадр из порнухи. Негр на желтом фоне в фуражке', 'Темный властелин', 1),
	('Просто черно-белая фотография дедушки Фрейда', 'Фрейд', 1),
	('Кудрявый Шайа ЛаБаф показывает магию на каком-то телешоу', 'Magic', 1),
	('Несколько нарисованных воробьев с огроменными хуищами', 'Ебушки-воробушки', 1),
	(
		'Иконка zip-файла из винды кидает зигу и говорит "Zip File \o"; ' ||
		'Иконки rar-архива говорят "Винрары воевали! Нарцисты ебаные"; ' ||
		'Нарцисы говорят "сук пздц."',
		'Zip File', 1),
	(
		'Ублюдок, мать твою, а ну иди сюда, говно собачье! ' ||
		'Что, решил ко мне лезть?! Ты, засранец вонючий, мать твою, а? ' ||
		'Ну, иди сюда,﻿ попробуй меня трахнуть, я тебя сам трахну, ублюдок, онанист чертов, будь ты проклят! ' ||
		'Иди, идиот, трахать тебя и всю твою семью, говно собачье, жлоб вонючий, дерьмо, сука, падла! ' ||
		'Иди сюда, мерзавец, негодяй, гад, иди сюда, ты, говно, ЖОПА!',
		'Ублюдок, мать твою', 1);

INSERT INTO client_role (client_id, role)
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

INSERT INTO token (token, client_id)
VALUES ('TEST_TOKEN', 1),
	('o36cYZRaVcjmnwMVY5lkCAnWpF9Sao9geT4xJTRS2NOE337cNusjv4TCFOH8', 2),
	('qNqO3ZyUiviHh8f4EeFSXyGQ6xqBvSu89fNzbJClnjPRiJuJ1ClXC2ZbZSTp', 3),
	('7ISBfNFzdFu068D1Un007c6lTPz4yk6VDFMbxpu1cAKssCxCYMnPIMaMzCGM', 4),
	('eLY8CqWArvH6KurBDeO2bPeFtjB9nCkoVMhpEwOHNh4c0w94J4Gp08scj047', 5),
	('08usc3N2hakGxwXMf1zx7hoYlKjALd9Sk0USfxLBZPa8whRuARvaMH3gjrxK', 6),
	('1yrqKfod6KX9vvaXcGqll6b8Ny8JZWl7ZtNWAnbpc3UlkYZxDbiMchMoWwGm', 7),
	('VYNjC2LxpHgTvQUvlxXD0rmTjTGkA85wS0vcylyaOKPHTPbFMqeoPPrwhTFE', 8),
	('trEVjX19b095LrblUoxmgmcisJPIOwLbWvKLSGzGzuBeDkaQ7Jtt8qXVl32O', 9),
	('g7qbVYM518rPi85kFye7qaFT0Ao6B3hU4zOgEVzcbCErIiyM4akMrx46nLZr', 10);

INSERT INTO ban (client_id, end_time, reason, moderator)
VALUES (2, current_timestamp + INTERVAL '100 day', 'Я здесь закон', 1),
	(5, current_timestamp + INTERVAL '14 day', 'Потому что я могу', 4),
	(7, current_timestamp + INTERVAL '14 day', 'Был несогласен с властью', 4),
	(8, current_timestamp + INTERVAL '14 day', 'Надоел', 4);

INSERT INTO tag (tag, meme_count)
VALUES ('Pepe', 0),
	('Пепе', 0),
	('Фрейд', 0),
	('фотография', 0),
	('фото', 0),
	('рисунок', 0),
	('воробьи', 0),
	('воробей', 0),
	('член', 0),
	('ублюдок', 0),
	('цитата', 0),
	('боль', 0),
	('негр', 0),
	('картинка', 0),
	('гиф', 0),
	('видео', 0),
	('текст', 0),
	('лягушка', 0),
	('Harold', 0),
	('властелин', 0),
	('шакалы', 0),
	('Гарольд', 0);

COMMIT;