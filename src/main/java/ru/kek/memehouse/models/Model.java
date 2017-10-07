package ru.kek.memehouse.models;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.kek.memehouse.MemeHouseApplication;

/**
 * gordeevnm@gmail.com
 * 07.10.17
 */
public class Model {
	private static JdbcTemplate jdbcTemplate;

	protected static JdbcTemplate getJdbcTemplate() {
		if (jdbcTemplate == null)
			jdbcTemplate = MemeHouseApplication.applicationContext.getBean(JdbcTemplate.class);

		return jdbcTemplate;
	}
}
