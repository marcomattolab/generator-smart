package it.eng.generate.template.test;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateHibernateTimeZoneTest extends AbstractTemplate{

	public TemplateHibernateTimeZoneTest(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcConfigTimezoneFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcConfigTimezoneFolder()+";\r\n\n" +
		"import "+ conf.getPackageclass() + "." + Utils.getClassNameCamelCase(conf.getProjectName()) + conf.getApp() +";\r\n"+
		"import "+ conf.getPackageclass() + "." + conf.getSrcRepositoryTimezoneFolder()+".DateTimeWrapper;\r\n"+
		"import "+ conf.getPackageclass() + "." + conf.getSrcRepositoryTimezoneFolder()+".DateTimeWrapperRepository;\r\n"+
		"import org.junit.Before;\r\n" +
		"import org.junit.Test;\r\n" +
		"import org.junit.runner.RunWith;\r\n" +
		"import org.springframework.beans.factory.annotation.Autowired;\r\n" +
		"import org.springframework.boot.test.context.SpringBootTest;\r\n" +
		"import org.springframework.jdbc.core.JdbcTemplate;\r\n" +
		"import org.springframework.jdbc.support.rowset.SqlRowSet;\r\n" +
		"import org.springframework.test.context.junit4.SpringRunner;\r\n" +
		"import org.springframework.transaction.annotation.Transactional;\r\n" +
		"import java.time.*;\r\n" +
		"import java.time.format.DateTimeFormatter;\r\n" +
		"import static java.lang.String.format;\r\n" +
		"import static org.assertj.core.api.Assertions.assertThat;\r\n\n" +
		"/**\r\n" +
		" * Unit tests for the UTC Hibernate configuration.\r\n" +
		" */\r\n" +
		"@RunWith(SpringRunner.class)\r\n" +
		"@SpringBootTest(classes = "+Utils.getClassNameCamelCase(conf.getProjectName()) + conf.getApp()+".class)\r\n" +
		"public class HibernateTimeZoneTest {\r\n\n" +
		"    @Autowired\r\n" +
		"    private DateTimeWrapperRepository dateTimeWrapperRepository;\r\n\n" +
		"    @Autowired\r\n" +
		"    private JdbcTemplate jdbcTemplate;\r\n" +
		"    private DateTimeWrapper dateTimeWrapper;\r\n" +
		"    private DateTimeFormatter dateTimeFormatter;\r\n" +
		"    private DateTimeFormatter timeFormatter;\r\n" +
		"    private DateTimeFormatter dateFormatter;\r\n\n" +
		"    @Before\r\n" +
		"    public void setup() {\r\n" +
		"        dateTimeWrapper = new DateTimeWrapper();\r\n" +
		"        dateTimeWrapper.setInstant(Instant.parse(\"2014-11-12T05:50:00.0Z\"));\r\n" +
		"        dateTimeWrapper.setLocalDateTime(LocalDateTime.parse(\"2014-11-12T07:50:00.0\"));\r\n" +
		"        dateTimeWrapper.setOffsetDateTime(OffsetDateTime.parse(\"2011-12-14T08:30:00.0Z\"));\r\n" +
		"        dateTimeWrapper.setZonedDateTime(ZonedDateTime.parse(\"2011-12-14T08:30:00.0Z\"));\r\n" +
		"        dateTimeWrapper.setLocalTime(LocalTime.parse(\"14:30:00\"));\r\n" +
		"        dateTimeWrapper.setOffsetTime(OffsetTime.parse(\"14:30:00+02:00\"));\r\n" +
		"        dateTimeWrapper.setLocalDate(LocalDate.parse(\"2016-09-10\"));\r\n" +
		"        dateTimeFormatter = DateTimeFormatter\r\n" +
		"            .ofPattern(\"yyyy-MM-dd HH:mm:ss.S\")\r\n" +
		"            .withZone(ZoneId.of(\"UTC\"));\r\n" +
		"        timeFormatter = DateTimeFormatter\r\n" +
		"            .ofPattern(\"HH:mm:ss\")\r\n" +
		"            .withZone(ZoneId.of(\"UTC\"));\r\n" +
		"        dateFormatter = DateTimeFormatter\r\n" +
		"            .ofPattern(\"yyyy-MM-dd\");\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void storeInstantWithUtcConfigShouldBeStoredOnGMTTimeZone() {\r\n" +
		"        dateTimeWrapperRepository.saveAndFlush(dateTimeWrapper);\r\n" +
		"        String request = generateSqlRequest(\"instant\", dateTimeWrapper.getId());\r\n" +
		"        SqlRowSet resultSet = jdbcTemplate.queryForRowSet(request);\r\n" +
		"        String expectedValue = dateTimeFormatter.format(dateTimeWrapper.getInstant());\r\n" +
		"        assertThatDateStoredValueIsEqualToInsertDateValueOnGMTTimeZone(resultSet, expectedValue);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void storeLocalDateTimeWithUtcConfigShouldBeStoredOnGMTTimeZone() {\r\n" +
		"        dateTimeWrapperRepository.saveAndFlush(dateTimeWrapper);\r\n" +
		"        String request = generateSqlRequest(\"local_date_time\", dateTimeWrapper.getId());\r\n" +
		"        SqlRowSet resultSet = jdbcTemplate.queryForRowSet(request);\r\n" +
		"        String expectedValue = dateTimeWrapper\r\n" +
		"            .getLocalDateTime()\r\n" +
		"            .atZone(ZoneId.systemDefault())\r\n" +
		"            .format(dateTimeFormatter);\r\n" +
		"        assertThatDateStoredValueIsEqualToInsertDateValueOnGMTTimeZone(resultSet, expectedValue);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void storeOffsetDateTimeWithUtcConfigShouldBeStoredOnGMTTimeZone() {\r\n" +
		"        dateTimeWrapperRepository.saveAndFlush(dateTimeWrapper);\r\n" +
		"        String request = generateSqlRequest(\"offset_date_time\", dateTimeWrapper.getId());\r\n" +
		"        SqlRowSet resultSet = jdbcTemplate.queryForRowSet(request);\r\n" +
		"        String expectedValue = dateTimeWrapper\r\n" +
		"            .getOffsetDateTime()\r\n" +
		"            .format(dateTimeFormatter);\r\n" +
		"        assertThatDateStoredValueIsEqualToInsertDateValueOnGMTTimeZone(resultSet, expectedValue);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void storeZoneDateTimeWithUtcConfigShouldBeStoredOnGMTTimeZone() {\r\n" +
		"        dateTimeWrapperRepository.saveAndFlush(dateTimeWrapper);\r\n" +
		"        String request = generateSqlRequest(\"zoned_date_time\", dateTimeWrapper.getId());\r\n" +
		"        SqlRowSet resultSet = jdbcTemplate.queryForRowSet(request);\r\n" +
		"        String expectedValue = dateTimeWrapper\r\n" +
		"            .getZonedDateTime()\r\n" +
		"            .format(dateTimeFormatter);\r\n" +
		"        assertThatDateStoredValueIsEqualToInsertDateValueOnGMTTimeZone(resultSet, expectedValue);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void storeLocalTimeWithUtcConfigShouldBeStoredOnGMTTimeZoneAccordingToHis1stJan1970Value() {\r\n" +
		"        dateTimeWrapperRepository.saveAndFlush(dateTimeWrapper);\r\n" +
		"        String request = generateSqlRequest(\"local_time\", dateTimeWrapper.getId());\r\n" +
		"        SqlRowSet resultSet = jdbcTemplate.queryForRowSet(request);\r\n" +
		"        String expectedValue = dateTimeWrapper\r\n" +
		"            .getLocalTime()\r\n" +
		"            .atDate(LocalDate.of(1970, Month.JANUARY, 1))\r\n" +
		"            .atZone(ZoneId.systemDefault())\r\n" +
		"            .format(timeFormatter);\r\n" +
		"        assertThatDateStoredValueIsEqualToInsertDateValueOnGMTTimeZone(resultSet, expectedValue);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void storeOffsetTimeWithUtcConfigShouldBeStoredOnGMTTimeZoneAccordingToHis1stJan1970Value() {\r\n" +
		"        dateTimeWrapperRepository.saveAndFlush(dateTimeWrapper);\r\n" +
		"        String request = generateSqlRequest(\"offset_time\", dateTimeWrapper.getId());\r\n" +
		"        SqlRowSet resultSet = jdbcTemplate.queryForRowSet(request);\r\n" +
		"        String expectedValue = dateTimeWrapper\r\n" +
		"            .getOffsetTime()\r\n" +
		"            .toLocalTime()\r\n" +
		"            .atDate(LocalDate.of(1970, Month.JANUARY, 1))\r\n" +
		"            .atZone(ZoneId.systemDefault())\r\n" +
		"            .format(timeFormatter);\r\n" +
		"        assertThatDateStoredValueIsEqualToInsertDateValueOnGMTTimeZone(resultSet, expectedValue);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void storeLocalDateWithUtcConfigShouldBeStoredWithoutTransformation() {\r\n" +
		"        dateTimeWrapperRepository.saveAndFlush(dateTimeWrapper);\r\n" +
		"        String request = generateSqlRequest(\"local_date\", dateTimeWrapper.getId());\r\n" +
		"        SqlRowSet resultSet = jdbcTemplate.queryForRowSet(request);\r\n" +
		"        String expectedValue = dateTimeWrapper\r\n" +
		"            .getLocalDate()\r\n" +
		"            .format(dateFormatter);\r\n" +
		"        assertThatDateStoredValueIsEqualToInsertDateValueOnGMTTimeZone(resultSet, expectedValue);\r\n" +
		"    }\r\n\n" +
		"    private String generateSqlRequest(String fieldName, long id) {\r\n" +
		"        return format(\"SELECT %s FROM jhi_date_time_wrapper where id=%d\", fieldName, id);\r\n" +
		"    }\r\n\n" +
		"    private void assertThatDateStoredValueIsEqualToInsertDateValueOnGMTTimeZone(SqlRowSet sqlRowSet, String expectedValue) {\r\n" +
		"        while (sqlRowSet.next()) {\r\n" +
		"            String dbValue = sqlRowSet.getString(1);\r\n" +
		"            assertThat(dbValue).isNotNull();\r\n" +
		"            assertThat(dbValue).isEqualTo(expectedValue);\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "HibernateTimeZoneTest";
	}

	public String getSourceFolder() {
		return "src/test/java";
	}

}
