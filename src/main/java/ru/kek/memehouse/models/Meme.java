package ru.kek.memehouse.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;
import ru.kek.memehouse.services.ObjectMapperBean;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class Meme {
	private int id;
	private int uploadedBy;
	private String description;
	private String name;
	private Timestamp uploadTime;
	private boolean isPublic;
	private String lurkmoreLink;
	private Attachment attachment;
	private List<String> tags;
	private boolean isDeleted;
	private String userNote;
	
	public static final RowMapper<Meme> DEF_ROW_MAPPER = new DefaultRowMapper();
	
	private static class DefaultRowMapper implements RowMapper<Meme> {
		@NotNull
		@SneakyThrows
		public Meme mapRow(@NotNull ResultSet rs, int rowNum) {
			return Meme.builder()
					.uploadedBy(rs.getInt("uploadedBy"))
					.description(rs.getString("description"))
					.name(rs.getString("name"))
					.uploadTime(rs.getTimestamp("uploadTime"))
					.isPublic(rs.getBoolean("isPublic"))
					.lurkmoreLink(rs.getString("lurkmoreLink"))
					.attachment(ObjectMapperBean.get().readValue(rs.getString("attachment"), Attachment.class))
					.tags(Arrays.asList((String[]) rs.getArray("tags").getArray()))
					.isDeleted(rs.getBoolean("isDeleted"))
					.build();
		}
	}
}