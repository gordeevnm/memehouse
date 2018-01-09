package ru.kek.memehouse.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

/**
 * gordeevnm@gmail.com
 * 09.01.18
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class Ban {
	private int id;
	private int userId;
	private int moderatorId;
	private Timestamp startTime;
	private Timestamp endTime;
	private String reason;
	private boolean isActive;
}
