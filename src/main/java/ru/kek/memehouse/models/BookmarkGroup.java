package ru.kek.memehouse.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * gordeevnm@gmail.com
 * 09.01.18
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class BookmarkGroup {
	private int id;
	private int ownerId;
	private String name;
	private int memesCount;
}
