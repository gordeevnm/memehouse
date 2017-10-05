package ru.kek.memehouse.models.additional;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * gordeevnm@gmail.com
 * 06.10.17
 */
// TODO: 06.10.17 хз будет ли работать так
@JsonAutoDetect(
		fieldVisibility = JsonAutoDetect.Visibility.ANY,
		getterVisibility = JsonAutoDetect.Visibility.NONE,
		setterVisibility = JsonAutoDetect.Visibility.NONE)
public @interface JacksonDto {
}
