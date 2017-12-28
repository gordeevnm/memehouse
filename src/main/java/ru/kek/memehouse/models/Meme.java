package ru.kek.memehouse.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

/**
 * gordeevnm@gmail.com
 * 06.10.17
 */
@Document(collection = "memes")
public class Meme extends MongoDoc {
	private Attachment attachment;
	private String description;
	private String name;
	private Set<Tag> tags;
	private User loadedBy;
	private boolean isDeleted;
	// TODO: 06.10.17 добавить "родительские" и "дочерние" мемы
	
	@Data
	@Builder
	@AllArgsConstructor
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class MemeDto {
		private Attachment attachment;
		private String description;
		private String name;
		private Set<Tag.TagDto> tags;
		private User.UserDto loadedBy;
		private Boolean isDeleted;
	}
}
