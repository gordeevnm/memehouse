package ru.kek.memehouse.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

/**
 * gordeevnm@gmail.com
 * 09.01.18
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class Bookmark {
	private Meme meme;
	private BookmarkGroup group;
	private Timestamp addingTime;
}
