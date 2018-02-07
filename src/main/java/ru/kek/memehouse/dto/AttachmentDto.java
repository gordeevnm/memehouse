package ru.kek.memehouse.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.kek.memehouse.models.Attachment;

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
public class AttachmentDto {
	public Attachment toModel() {
		// TODO: 14.01.18 реализовать
		return new Attachment();
	}
	
	public static AttachmentDto from(Attachment attachment) {
		// TODO: 14.01.18 реализовать
		return new AttachmentDto();
	}
}
