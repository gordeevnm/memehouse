package ru.kek.memehouse.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * gordeevnm@gmail.com
 * 07.10.17
 */

@Entity
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
	@Id
	private String name;
	@Column(name = "memes_count")
	private int memesCount;
	@ManyToOne()
	@JoinColumn(name = "merged_with")
	private Tag mergedWith;
	@Column(name = "merge_time")
	private Timestamp mergeTime;
	@ManyToOne
	@JoinColumn(name = "merged_by")
	private User mergedBy;
}
