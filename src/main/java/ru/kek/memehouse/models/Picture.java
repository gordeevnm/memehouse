package ru.kek.memehouse.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Picture {
	private Long id;
	private String origFileName;
	private long size;
	private String mimeType;
	private String serverAddress;
	private String sizeOrig;
	private String size100;
	private String size200;
	private String size300;
	private String size400;
}