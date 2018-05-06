package ru.kek.memehouse.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.kek.memehouse.models.Meme;

/**
 * gordeevnm@gmail.com
 * 23.04.18
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(
	  fieldVisibility = JsonAutoDetect.Visibility.ANY,
	  getterVisibility = JsonAutoDetect.Visibility.NONE,
	  setterVisibility = JsonAutoDetect.Visibility.NONE,
	  creatorVisibility = JsonAutoDetect.Visibility.NONE
)
public class MemeModifyDto {
	private String name;
	private String description;
	private boolean isPublic;
	private String lurkmoreLink;
	private String pictureId;
	private String[] tags;
	
	public Meme toModel() {
		return Meme.builder()
			  .description(this.description)
			  .name(this.name)
			  .isPublic(this.isPublic)
			  .lurkmoreLink(this.lurkmoreLink)
			  .pictureId(this.pictureId)
			  .tags(this.tags)
			  .build();
	}
}
