package ru.kek.memehouse.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "ban")

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ban implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	@ManyToOne
	@JoinColumn(name = "moderator_id", nullable = false)
	private User moderator;
	@Column(name = "start_time", nullable = false)
	private Timestamp startTime;
	@Column(name = "end_time", nullable = false)
	private Timestamp endTime;
	@Column(name = "reason", nullable = false)
	private String reason;
	@Column(name = "is_active")
	private boolean isActive;
}
