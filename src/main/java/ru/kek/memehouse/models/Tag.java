package ru.kek.memehouse.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * gordeevnm@gmail.com
 * 07.10.17
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class Tag {
	private int id;
	private String tag;
}
