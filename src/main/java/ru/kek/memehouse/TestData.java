package ru.kek.memehouse;

import ru.kek.memehouse.models.BookmarkGroup;
import ru.kek.memehouse.models.Meme;
import ru.kek.memehouse.models.Tag;
import ru.kek.memehouse.models.User;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * gordeevnm@gmail.com
 * 22.04.18
 */
public class TestData {
	private static final List<User> users = Arrays.asList(
		  User.builder()
				 .id(1L)
				 .roles(new String[]{"REGISTERED", "ADMIN"})
				 .username("admin")
				 .password("qwerty123456")
				 .email(null)
				 .registrationTime(new Timestamp(System.currentTimeMillis()))
				 .isDeleted(false)
				 .deletionTime(null)
				 .bans(Collections.emptySet())
				 .build(),
		  User.builder()
				 .id(2L)
				 .roles(new String[]{"REGISTERED", "TAGS_MODERATOR", "USERS_MODERATOR", "CONTENT_MODERATOR"})
				 .username("moderator")
				 .password("qwerty123456")
				 .email(null)
				 .registrationTime(new Timestamp(System.currentTimeMillis()))
				 .isDeleted(false)
				 .deletionTime(null)
				 .bans(Collections.emptySet())
				 .build(),
		  User.builder()
				 .id(3L)
				 .roles(new String[]{"REGISTERED", "TAGS_MODERATOR"})
				 .username("RENTV")
				 .password("qwerty123456")
				 .email(null)
				 .registrationTime(new Timestamp(System.currentTimeMillis()))
				 .isDeleted(false)
				 .deletionTime(null)
				 .bans(Collections.emptySet())
				 .build(),
		  User.builder()
				 .id(4L)
				 .roles(new String[]{"REGISTERED"})
				 .username("Uspeli")
				 .password("qwerty123456")
				 .email(null)
				 .registrationTime(new Timestamp(System.currentTimeMillis()))
				 .isDeleted(false)
				 .deletionTime(null)
				 .bans(Collections.emptySet())
				 .build(),
		  User.builder()
				 .id(5L)
				 .roles(new String[]{"REGISTERED", "TAGS_MODERATOR", "USERS_MODERATOR"})
				 .username("0x00")
				 .password("qwerty123456")
				 .email(null)
				 .registrationTime(new Timestamp(System.currentTimeMillis()))
				 .isDeleted(false)
				 .deletionTime(null)
				 .bans(Collections.emptySet())
				 .build(),
		  User.builder()
				 .id(6L)
				 .roles(new String[]{"REGISTERED"})
				 .username("OVCEEB")
				 .password("qwerty123456")
				 .email(null)
				 .registrationTime(new Timestamp(System.currentTimeMillis()))
				 .isDeleted(false)
				 .deletionTime(null)
				 .bans(Collections.emptySet())
				 .build(),
		  User.builder()
				 .id(7L)
				 .roles(new String[]{"REGISTERED"})
				 .username("apres")
				 .password("qwerty123456")
				 .email(null)
				 .registrationTime(new Timestamp(System.currentTimeMillis()))
				 .isDeleted(false)
				 .deletionTime(null)
				 .bans(Collections.emptySet())
				 .build()
	);
	private static final List<Meme> memes = Arrays.asList(
		  Meme.builder()
				 .id(1L)
				 .createTime(new Timestamp(System.currentTimeMillis()))
				 .description("Чувак с тыквой на голове танцует.")
				 .name("Ни ху я")
				 .isPublic(true)
				 .pictureId("тут типа идентификатор картинки, по которому можно будет из отдельного сервиса достать дополнительную информацию")
				 .isDeleted(false)
				 .build(),
		  Meme.builder()
				 .id(2L)
				 .createTime(new Timestamp(System.currentTimeMillis()))
				 .description("Грустная зеленая лягушка")
				 .name("Pepe Frog")
				 .isPublic(true)
				 .pictureId("тут типа идентификатор картинки, по которому можно будет из отдельного сервиса достать дополнительную информацию")
				 .isDeleted(false)
				 .build(),
		  Meme.builder()
				 .id(3L)
				 .createTime(new Timestamp(System.currentTimeMillis()))
				 .description("Старик, скрывающий боль за фальшивой улыбкой")
				 .name("Harold hide the pain")
				 .isPublic(true)
				 .pictureId("тут типа идентификатор картинки, по которому можно будет из отдельного сервиса достать дополнительную информацию")
				 .isDeleted(false)
				 .build(),
		  Meme.builder()
				 .id(4L)
				 .createTime(new Timestamp(System.currentTimeMillis()))
				 .description("Негр-актер тычет пальцем себе в висок")
				 .name("Негр умник")
				 .isPublic(true)
				 .pictureId("тут типа идентификатор картинки, по которому можно будет из отдельного сервиса достать дополнительную информацию")
				 .isDeleted(false)
				 .build(),
		  Meme.builder()
				 .id(5L)
				 .createTime(new Timestamp(System.currentTimeMillis()))
				 .description("Кадр из порнухи. Негр на желтом фоне в фуражке")
				 .name("Темный властелин")
				 .isPublic(true)
				 .pictureId("тут типа идентификатор картинки, по которому можно будет из отдельного сервиса достать дополнительную информацию")
				 .isDeleted(false)
				 .build(),
		  Meme.builder()
				 .id(6L)
				 .createTime(new Timestamp(System.currentTimeMillis()))
				 .description("Просто черно-белая фотография дедушки Фрейда")
				 .name("Фрейд")
				 .isPublic(true)
				 .pictureId("тут типа идентификатор картинки, по которому можно будет из отдельного сервиса достать дополнительную информацию")
				 .isDeleted(false)
				 .build(),
		  Meme.builder()
				 .id(7L)
				 .createTime(new Timestamp(System.currentTimeMillis()))
				 .description("Кудрявый Шайа ЛаБаф показывает магию на каком-то телешоу")
				 .name("Magic")
				 .isPublic(true)
				 .pictureId("тут типа идентификатор картинки, по которому можно будет из отдельного сервиса достать дополнительную информацию")
				 .isDeleted(false)
				 .build(),
		  Meme.builder()
				 .id(8L)
				 .createTime(new Timestamp(System.currentTimeMillis()))
				 .description("Несколько нарисованных воробьев с огроменными хуищами")
				 .name("Ебушки-воробушки")
				 .isPublic(true)
				 .pictureId("тут типа идентификатор картинки, по которому можно будет из отдельного сервиса достать дополнительную информацию")
				 .isDeleted(false)
				 .build(),
		  Meme.builder()
				 .id(9L)
				 .createTime(new Timestamp(System.currentTimeMillis()))
				 .description("Иконка zip-файла из винды кидает зигу и говорит \"Zip File \\o\"; " +
						"Иконки rar-архива говорят \"Винрары воевали! Нарцисты ебаные\"; " +
						"Нарцисы говорят \"сук пздц.\"")
				 .name("Zip File")
				 .isPublic(true)
				 .pictureId("тут типа идентификатор картинки, по которому можно будет из отдельного сервиса достать дополнительную информацию")
				 .isDeleted(false)
				 .build(),
		  Meme.builder()
				 .id(10L)
				 .createTime(new Timestamp(System.currentTimeMillis()))
				 .description("Ублюдок, мать твою, а ну иди сюда, говно собачье! " +
						"Что, решил ко мне лезть?! Ты, засранец вонючий, мать твою, а? " +
						"Ну, иди сюда, попробуй меня трахнуть, я тебя сам трахну, ублюдок, онанист чертов, будь ты проклят! " +
						"Иди, идиот, трахать тебя и всю твою семью, говно собачье, жлоб вонючий, дерьмо, сука, падла! " +
						"Иди сюда, мерзавец, негодяй, гад, иди сюда, ты, говно, ЖОПА!")
				 .name("Ублюдок, мать твою")
				 .isPublic(true)
				 .pictureId("тут типа идентификатор картинки, по которому можно будет из отдельного сервиса достать дополнительную информацию")
				 .isDeleted(false)
				 .build()
	);
	
	private static final List<BookmarkGroup> bookmarkGroups = Arrays.asList(
		  BookmarkGroup.builder().id(1L).name("gif").build(),
		  BookmarkGroup.builder().id(2L).name("trash").build(),
		  BookmarkGroup.builder().id(3L).name("gif").build(),
		  BookmarkGroup.builder().id(4L).name("trash").build(),
		  BookmarkGroup.builder().id(5L).name("gif").build(),
		  BookmarkGroup.builder().id(6L).name("common").build(),
		  BookmarkGroup.builder().id(7L).name("incest").build(),
		  BookmarkGroup.builder().id(8L).name("pron").build(),
		  BookmarkGroup.builder().id(9L).name("gif").build(),
		  BookmarkGroup.builder().id(10L).name("common").build()
	);
	
	private static final List<Tag> tags = Arrays.asList(
		  Tag.builder().tag("Pepe").build(),
		  Tag.builder().tag("Пепе").build(),
		  Tag.builder().tag("тыква").build(),
		  Tag.builder().tag("Фрейд").build(),
		  Tag.builder().tag("фотография").build(),
		  Tag.builder().tag("фото").build(),
		  Tag.builder().tag("рисунок").build(),
		  Tag.builder().tag("картинка").build(),
		  Tag.builder().tag("воробьи").build(),
		  Tag.builder().tag("воробей").build(),
		  Tag.builder().tag("цитата").build(),
		  Tag.builder().tag("ублюдок").build(),
		  Tag.builder().tag("боль").build(),
		  Tag.builder().tag("негр").build(),
		  Tag.builder().tag("властелин").build(),
		  Tag.builder().tag("нигер").build(),
		  Tag.builder().tag("афроамериканец").build(),
		  Tag.builder().tag("гиф").build(),
		  Tag.builder().tag("gif").build(),
		  Tag.builder().tag("анимация").build(),
		  Tag.builder().tag("текст").build(),
		  Tag.builder().tag("лягушка").build(),
		  Tag.builder().tag("Harold").build(),
		  Tag.builder().tag("Гарольд").build(),
		  Tag.builder().tag("шакалы").build(),
		  Tag.builder().tag("ржака").build(),
		  Tag.builder().tag("файлообменник").build(),
		  Tag.builder().tag("скайп").build(),
		  Tag.builder().tag("skype").build(),
		  Tag.builder().tag("бред").build(),
		  Tag.builder().tag("реклама").build(),
		  Tag.builder().tag("танец").build(),
		  Tag.builder().tag("актер").build(),
		  Tag.builder().tag("интеллект").build(),
		  Tag.builder().tag("мотивация").build(),
		  Tag.builder().tag("член").build(),
		  Tag.builder().tag("zip").build(),
		  Tag.builder().tag("комикс").build(),
		  Tag.builder().tag("эмоции").build()
	);
	
	static {
		memes.get(0).setTags(new String[]{"тыква", "танец"});
		memes.get(1).setTags(new String[]{"лягушка", "Пепе", "рисунок", "эмоции"});
		memes.get(2).setTags(new String[]{"Гарольд", "боль", "эмоции", "фото"});
		memes.get(3).setTags(new String[]{"негр", "актер", "фото", "интеллект"});
		memes.get(4).setTags(new String[]{"властелин", "негр", "фото"});
		memes.get(5).setTags(new String[]{"фото", "фрейд"});
		memes.get(6).setTags(new String[]{"актер", "мотивация", "фото"});
		memes.get(7).setTags(new String[]{"воробьи", "член", "рисунок"});
		memes.get(8).setTags(new String[]{"комикс", "zip", "бред", "рисунок"});
		memes.get(9).setTags(new String[]{"ублюдок", "текст"});
		
		tags.get(1).setMergedWith(tags.get(0)).setMergedBy(users.get(4)).setMergeTime(new Timestamp(System.currentTimeMillis()));
		tags.get(5).setMergedWith(tags.get(4)).setMergedBy(users.get(4)).setMergeTime(new Timestamp(System.currentTimeMillis()));
		tags.get(9).setMergedWith(tags.get(8)).setMergedBy(users.get(4)).setMergeTime(new Timestamp(System.currentTimeMillis()));
		tags.get(15).setMergedWith(tags.get(13)).setMergedBy(users.get(4)).setMergeTime(new Timestamp(System.currentTimeMillis()));
		tags.get(16).setMergedWith(tags.get(13)).setMergedBy(users.get(4)).setMergeTime(new Timestamp(System.currentTimeMillis()));
		tags.get(17).setMergedWith(tags.get(18)).setMergedBy(users.get(4)).setMergeTime(new Timestamp(System.currentTimeMillis()));
		tags.get(19).setMergedWith(tags.get(18)).setMergedBy(users.get(4)).setMergeTime(new Timestamp(System.currentTimeMillis()));
		tags.get(22).setMergedWith(tags.get(22)).setMergedBy(users.get(4)).setMergeTime(new Timestamp(System.currentTimeMillis()));
		tags.get(28).setMergedWith(tags.get(27)).setMergedBy(users.get(4)).setMergeTime(new Timestamp(System.currentTimeMillis()));
		
		memes.get(0).setCreatedBy(users.get(2));
		memes.get(1).setCreatedBy(users.get(3));
		memes.get(2).setCreatedBy(users.get(5));
		memes.get(3).setCreatedBy(users.get(2));
		memes.get(4).setCreatedBy(users.get(2));
		memes.get(5).setCreatedBy(users.get(6));
		memes.get(6).setCreatedBy(users.get(5));
		memes.get(7).setCreatedBy(users.get(3));
		memes.get(8).setCreatedBy(users.get(2));
		memes.get(9).setCreatedBy(users.get(6));
		
		bookmarkGroups.get(0).setOwner(users.get(0));
		bookmarkGroups.get(1).setOwner(users.get(0));
		bookmarkGroups.get(2).setOwner(users.get(2));
		bookmarkGroups.get(3).setOwner(users.get(2));
		bookmarkGroups.get(4).setOwner(users.get(1));
		bookmarkGroups.get(5).setOwner(users.get(0));
		bookmarkGroups.get(6).setOwner(users.get(4));
		bookmarkGroups.get(7).setOwner(users.get(6));
		bookmarkGroups.get(8).setOwner(users.get(6));
		bookmarkGroups.get(9).setOwner(users.get(6));
	}
}
