package ru.kek.memehouse.models;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "meme")
@TypeDefs({
		@TypeDef(
				name = "string-array",
				typeClass = StringArrayType.class
		)
})

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Meme {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable = false)
	private User createdBy;
	@Column(name = "create_time", nullable = false)
	private Timestamp createTime;
	@Column(name = "description")
	private String description;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "is_public")
	private boolean isPublic;
	@Column(name = "lurkmore_link")
	private String lurkmoreLink;
	@Column(name = "picture_id")
	private String pictureId;
	@Type(type = "string-array")
	@Column(name = "tags_array", columnDefinition = "text[]")
	private String[] tags;
	@Column(name = "is_deleted")
	private boolean isDeleted;
	// TODO: 22.04.18 переделать
	@Transient
	private String userNote;
}