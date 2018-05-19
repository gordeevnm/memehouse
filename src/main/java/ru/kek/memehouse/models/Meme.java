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
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.jdbc.core.RowMapper;

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
@Document(indexName = "memes_index", type = "meme", createIndex = false, useServerConfiguration = true)
public class Meme {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "created_by", nullable = false)
	private Long createdById;
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
	
	@Transient
	private transient String userNote;
	@Transient
	private transient User createdByUser;
	
	public static RowMapper<Meme> DEF_ROW_MAPPER =
		  (rs, rowNum) ->
				 Meme.builder()
						.id(rs.getLong("id"))
						.createdById(rs.getLong("created_by"))
						.createTime(rs.getTimestamp("create_time"))
						.description(rs.getString("description"))
						.name(rs.getString("name"))
						.isPublic(rs.getBoolean("is_public"))
						.lurkmoreLink(rs.getString("lurkmore_link"))
						.pictureId(rs.getString("picture_id"))
						.tags(((String[]) rs.getArray("tags_array").getArray()))
						.isDeleted(rs.getBoolean("is_deleted"))
						.build();
}