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
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
public class User implements Serializable, UserDetails, CredentialsContainer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Type(type = "string-array")
	@Column(name = "roles", columnDefinition = "text[]")
	private String[] roles;
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "email")
	private String email;
	@Column(name = "registration_time")
	private Timestamp registrationTime;
	@Column(name = "is_deleted")
	private boolean isDeleted;
	@Column(name = "deletion_time")
	private Timestamp deletionTime;
	@OneToMany
	@JoinColumn(name = "user_id")
	private Set<Ban> bans;
	@OneToMany
	@JoinColumn(name = "owner_id")
	private Set<BookmarkGroup> bookmarkGroups;
	
	@Override
	public void eraseCredentials() {
		this.password = "";
	}
	
	@Override
	public List<GrantedAuthority> getAuthorities() {
		return Arrays.stream(roles)
			  .map(SimpleGrantedAuthority::new)
			  .collect(Collectors.toList());
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		return bans.stream()
			  .anyMatch(ban -> ban.isActive()
					 && ban.getEndTime().after(currentTimestamp)
					 && ban.getStartTime().before(currentTimestamp));
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return !isDeleted;
	}
	
	public List<Ban> getFutureBansList() {
		return bans.stream()
			  .filter(ban -> ban.isActive() && ban.getEndTime().after(new Timestamp(System.currentTimeMillis())))
			  .collect(Collectors.toList());
	}
	
	public void addRole(String role) {
		this.roles = append(roles, role);
	}
	
	public void addRole(GrantedAuthority role) {
		addRole(role.getAuthority());
	}
	
	private String[] append(String[] roles, String role) {
		String[] newArray = Arrays.copyOf(roles, roles.length + 1);
		newArray[roles.length] = role;
		
		return newArray;
	}
}
