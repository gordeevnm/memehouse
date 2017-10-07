package ru.kek.memehouse.models;

import java.util.Set;

/**
 * gordeevnm@gmail.com
 * 06.10.17
 */
public class Meme extends Model {
	private Attachment attachment;
	private String description;
	private String name;
	private Set<Tag> tags;
	private User loadedBy;
	private boolean isDeleted;
	// TODO: 06.10.17 добавить "родительские" и "дочерние" мемы
}
