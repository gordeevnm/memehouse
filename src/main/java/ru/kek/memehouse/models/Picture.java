package ru.kek.memehouse.models;

/**
 * gordeevnm@gmail.com
 * 06.10.17
 */
public class Picture extends Model implements Attachment {
	private FileModel originalFile;
	private FileModel thumbFile;
	private String originalResolution;
	private String thumbResolution;
}
