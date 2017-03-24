package com.jitin.springembeddeddatabase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SqlGroup({
		@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts = "classpath:beforeTestRun.sql"),
		@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,scripts = "classpath:afterTestRun.sql")
})
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void contextLoads() {

		String selectQuery = "SELECT * from PERSON WHERE PERSONID = 1";

		List<Map<String, Object>> resultSet = jdbcTemplate
				.queryForList(selectQuery);

		System.out.println(resultSet);
	}

}
