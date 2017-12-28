import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * gordeevnm@gmail.com
 * 27.12.17
 */
public class BullshitGenerator {
	public static void main(String[] args) throws InterruptedException {
		int usersCount = 10000;
		int videosCount = 100000;
		int memesCount = 1000000;
		int notesCount = 30000;
		int tagsCount = 10000000;
		int memeTagRelCount = 10000000;
		int personalTagsCount = 2000000;
		int savedMemesCount = 100000;
		int synonymousTagRelCount = 1000000;
		
		ExecutorService taskExecutor = Executors.newCachedThreadPool();
		
		taskExecutor.submit(() -> {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(new File("users")));
				for (int i = 1; i <= usersCount; i++) {
					String sql = "INSERT INTO public.user(username, password, email, registration_time) " +
							"VALUES ('" + getRandomUsername(16) + "', '" + getRandomString(30) + "', '" + getRandomUsername(30) + "', '" + getRandomDate() + "');";
					writer.write(sql);
					writer.newLine();
				}
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}
			System.out.println("users");
		});
		
		taskExecutor.submit(() -> {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(new File("videos")));
				for (int i = 1; i <= videosCount; i++) {
					String sql = "INSERT INTO public.attachment(attachment_type, video_url) " +
							"VALUES ('video', '" + getRandomString(30) + "');";
					writer.write(sql);
					writer.newLine();
				}
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}
			System.out.println("videos");
		});
		
		taskExecutor.submit(() -> {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(new File("memes")));
				for (int i = 1; i <= memesCount; i++) {
					String sql = "INSERT INTO public.meme(uploaded_by, attachment_id, description, name, upload_time, is_public, lurkmore_link) " +
							"VALUES (" + random.nextInt(usersCount) + ", " + random.nextInt(videosCount) + ", '" + getRandomString(30) + "', '" + getRandomString(30) + "', '" + getRandomDate() + "', TRUE , '" + getRandomUsername(30) + "');";
					writer.write(sql);
					writer.newLine();
				}
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}
			System.out.println("memes");
		});
		
		taskExecutor.submit(() -> {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(new File("notes")));
				for (int i = 1; i <= notesCount; i++) {
					String sql = "INSERT INTO public.note(user_id, meme_id, note) " +
							"VALUES (" + random.nextInt(usersCount) + ", " + random.nextInt(memesCount) + ", '" + getRandomString(30) + "');";
					writer.write(sql);
					writer.newLine();
				}
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}
			System.out.println("notes");
		});
		
		taskExecutor.submit(() -> {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(new File("tags")));
				for (int i = 1; i <= tagsCount; i++) {
					String sql = "INSERT INTO public.tag(tag) " +
							"VALUES ('" + getRandomString(30) + "');";
					writer.write(sql);
					writer.newLine();
				}
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}
			System.out.println("tags");
		});
		
		taskExecutor.submit(() -> {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(new File("memeTagRel")));
				for (int i = 1; i <= memeTagRelCount; i++) {
					String sql = "INSERT INTO public.meme_tag(meme_id, tag_id) " +
							"VALUES (" + random.nextInt(memesCount) + ", " + random.nextInt(tagsCount) + ");";
					writer.write(sql);
					writer.newLine();
				}
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}
			System.out.println("memeTagRel");
		});
		
		taskExecutor.submit(() -> {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(new File("personalTags")));
				for (int i = 1; i <= personalTagsCount; i++) {
					String sql = "INSERT INTO public.personal_tag(user_id, tag_id, meme_id) " +
							"VALUES (" + random.nextInt(usersCount) + ", " + random.nextInt(tagsCount) + ", " + random.nextInt(memesCount) + ");";
					writer.write(sql);
					writer.newLine();
				}
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}
			System.out.println("personalTags");
		});
		
		taskExecutor.submit(() -> {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(new File("savedMemes")));
				for (int i = 1; i <= savedMemesCount; i++) {
					String sql = "INSERT INTO public.saved_meme(user_id, meme_id) " +
							"VALUES (" + random.nextInt(usersCount) + ", " + random.nextInt(memesCount) + ");";
					writer.write(sql);
					writer.newLine();
				}
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}
			System.out.println("savedMemes");
		});
		
		taskExecutor.submit(() -> {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(new File("synonymousTagRel")));
				for (int i = 1; i <= synonymousTagRelCount; i++) {
					String sql = "INSERT INTO public.synonymous_tag(general_tag, derivative_tag) " +
							"VALUES (" + random.nextInt(tagsCount) + ", " + random.nextInt(tagsCount) + ");";
					writer.write(sql);
					writer.newLine();
				}
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}
			System.out.println("synonymousTagRel");
		});
		
		taskExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
	}
	
	private static Random random = new Random();
	
	private static String getRandomDate() {
		Date date = new Date(Math.abs(random.nextLong()) % System.currentTimeMillis());
		int day = date.getDay();
		int month = date.getMonth();
		int year = date.getYear();
		
		return day + "." + month + "." + year;
	}
	
	private static final String CHARS = "qwertyuiopasdfghjklzxcvbnmйцукенгшщзфывапролдячсмитьбюёQWERTYUIOPASDFGHJKLZXCVBNMЁЙЦУКЕНГШЩЗФЫВАПРОЛДЯЧСМИТЬБЮЭЖХЪжэхъ ";
	
	private static String getRandomString(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
		}
		
		return sb.toString();
	}
	
	private static final String ALPHANUM = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
	
	private static String getRandomUsername(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(ALPHANUM.charAt(random.nextInt(ALPHANUM.length())));
		}
		
		return sb.toString();
	}
}
