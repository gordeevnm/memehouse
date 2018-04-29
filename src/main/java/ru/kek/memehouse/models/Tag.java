package ru.kek.memehouse.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

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
	@JoinColumn(name = "merged_with")
	private Tag mergedWith;
	@Column(name = "merge_time")
	private Timestamp mergeTime;
	@Column(name = "merged_by")
	private User mergedBy;
}
