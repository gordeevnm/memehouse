package ru.kek.memehouse.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.kek.memehouse.models.Meme;

import java.sql.Timestamp;
import java.util.Arrays;
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
	private Long id;
	private Long createdBy;
	private String description;
	private String name;
	private Timestamp createTime;
	private Boolean isPublic;
	private String lurkmoreLink;
	private String pictureId;
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
			  .pictureId(this.pictureId)
			  .tags(((String[]) this.tags.toArray()))
			  .build();
	}
	
	public static MemeDto from(Meme meme) {
		return MemeDto.builder()
			  .id(meme.getId())
			  .createdBy(meme.getCreatedBy().getId())
			  .description(meme.getDescription())
			  .name(meme.getName())
			  .createTime(meme.getCreateTime())
			  .isPublic(meme.isPublic())
			  .lurkmoreLink(meme.getLurkmoreLink())
			  .pictureId(meme.getPictureId())
			  .tags(Arrays.asList(meme.getTags()))
			  .isDeleted(meme.isDeleted())
			  .userNote(meme.getUserNote())
			  .build();
	}
}