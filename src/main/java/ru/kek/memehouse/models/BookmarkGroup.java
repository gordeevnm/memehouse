package ru.kek.memehouse.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "bookmark_group")

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = false)
	private User owner;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "memes_count")
	private int memesCount;
	@OneToMany
	@JoinColumn(name = "group_id")
	private Set<Bookmark> bookmarks;
}