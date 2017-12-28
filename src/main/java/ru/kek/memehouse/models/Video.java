package ru.kek.memehouse.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * gordeevnm@gmail.com
 * 06.10.17
 */
@JsonAutoDetect(
		fieldVisibility = JsonAutoDetect.Visibility.ANY,
		getterVisibility = JsonAutoDetect.Visibility.NONE,
		setterVisibility = JsonAutoDetect.Visibility.NONE)
public class Video extends Attachment {
	private String url;
}
