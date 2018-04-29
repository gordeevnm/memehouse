package ru.kek.memehouse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * gordeevnm@gmail.com
 * 15.01.18
 */
@Configuration
public class DBInit {
	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	private void initDb() throws SQLException, IOException {
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			stmt.execute("DROP SCHEMA public CASCADE;\n" +
				  "CREATE SCHEMA public;" +
				  "GRANT ALL ON SCHEMA public TO postgres;\n" +
				  "GRANT ALL ON SCHEMA public TO public;");
			
			String createSql = IOUtils.toString(this.getClass().getResourceAsStream("/db/create.sql"), "UTF-8");
			
			stmt.execute(createSql);
		}
	}
}
