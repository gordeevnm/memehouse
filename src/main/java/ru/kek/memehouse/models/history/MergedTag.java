package ru.kek.memehouse.models.history;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.kek.memehouse.models.User;

import java.sql.Timestamp;

/**
 * gordeevnm@gmail.com
 * 18.04.18
 */

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MergedTag {
	private User mergedBy;
	private Timestamp mergeTime;
}
