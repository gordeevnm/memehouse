package ru.kek.memehouse.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.kek.memehouse.models.Meme;

import java.sql.Timestamp;
import java.util.List;

/**
 * gordeevnm@gmail.com
 * 14.01.18
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@JsonAutoDetect(
		fieldVisibility = JsonAutoDetect.Visibility.ANY,
		getterVisibility = JsonAutoDetect.Visibility.NONE,
		setterVisibility = JsonAutoDetect.Visibility.NONE,
		creatorVisibility = JsonAutoDetect.Visibility.NONE
)
public class MemeDto {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer id;
	private Integer uploadedBy;
	private String description;
	private String name;
	private Timestamp uploadTime;
	private Boolean isPublic;
	private String lurkmoreLink;
	private AttachmentDto attachment;
	private List<String> tags;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean isDeleted;
	private String userNote;
	
	public Meme toModel() {
		return Meme.builder()
				.description(this.description)
				.name(this.name)
				.isPublic(this.isPublic)
				.lurkmoreLink(this.lurkmoreLink)
				.attachment(this.attachment.toModel())
				.tags(this.tags)
				.build();
		
	}
	
	public static MemeDto from(Meme meme) {
		return MemeDto.builder()
				.id(meme.getId())
				.uploadedBy(meme.getUploadedBy())
				.description(meme.getDescription())
				.name(meme.getName())
				.uploadTime(meme.getUploadTime())
				.isPublic(meme.isPublic())
				.lurkmoreLink(meme.getLurkmoreLink())
				.attachment(AttachmentDto.from(meme.getAttachment()))
				.tags(meme.getTags())
				.isDeleted(meme.isDeleted())
				.userNote(meme.getUserNote())
				.build();
	}
}