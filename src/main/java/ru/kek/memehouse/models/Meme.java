package ru.kek.memehouse.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.List;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class Meme {
	private int id;
	private int uploadedBy;
	private String description;
	private String name;
	private Timestamp uploadTime;
	private boolean isPublic;
	private String lurkmoreLink;
	private Attachment attachment;
	private List<String> tags;
	private boolean isDeleted;
	private String userNote;
}