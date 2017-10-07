package ru.kek.memehouse.models;

import java.sql.Date;

/**
 * gordeevnm@gmail.com
 * 07.10.17
 */
public class FileModel {
	private String url;
	private long size;
	private String mime;
	private Date loadedDate;
	private User loadedBy;
	private boolean isTemp;
	private boolean isDeleted;
}
