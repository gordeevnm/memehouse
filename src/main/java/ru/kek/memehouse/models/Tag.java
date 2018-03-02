package ru.kek.memehouse.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

/**
 * gordeevnm@gmail.com
 * 07.10.17
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
	@Id
	private String tag;
	@Column(name = "memes_count")
	private int memesCount;
	@ManyToOne()
	@JoinColumn(name = "general_tag")
	private Tag generalTag;
	@OneToMany
	@JoinColumn(name = "general_tag")
	private Set<Tag> derivativeTags;
}
