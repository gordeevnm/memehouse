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
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "\"user\"")
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
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Type(type = "string-array")
	@Column(name = "roles", columnDefinition = "text[]")
	private String[] roles;
	@Column(name = "username", nullable = false)
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "email")
	private String email;
	@Column(name = "registration_time")
	private Timestamp registrationTime;
	@Column(name = "is_deleted")
	private boolean isDeleted;
	@OneToMany
	@JoinColumn(name = "user_id")
	private Set<Ban> bans;
	@OneToMany
	@JoinColumn(name = "owner_id")
	private Set<BookmarkGroup> bookmarkGroups;
}
