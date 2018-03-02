package ru.kek.memehouse.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "bookmark")

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bookmark {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "group_id", nullable = false)
	private BookmarkGroup group;
	@Column(name = "adding_time", nullable = false)
	private Timestamp addingTime;
	@ManyToOne
	@JoinColumn(name = "meme_id", nullable = false)
	private Meme meme;
}
