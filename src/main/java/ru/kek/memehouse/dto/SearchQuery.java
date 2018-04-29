package ru.kek.memehouse.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.kek.memehouse.models.Tag;

import java.sql.Timestamp;
import java.util.List;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@JsonAutoDetect(
	  fieldVisibility = JsonAutoDetect.Visibility.ANY,
	  getterVisibility = JsonAutoDetect.Visibility.NONE,
	  setterVisibility = JsonAutoDetect.Visibility.NONE,
	  creatorVisibility = JsonAutoDetect.Visibility.NONE
)
public class SearchQuery {
	private String queryString;
	@Builder.Default
	private String memeType = "any";
	@Builder.Default
	private String visibility = "all";
	private List<Tag> tags;
	private Timestamp periodStart;
	private Timestamp periodEnd;
	@Builder.Default
	private int count = 20;
	@Builder.Default
	private int offset = 0;
	@Builder.Default
	private boolean byOwner = false;
}
