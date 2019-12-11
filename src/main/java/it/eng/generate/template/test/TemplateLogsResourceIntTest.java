package it.eng.generate.template.test;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateLogsResourceIntTest extends AbstractTemplate{

	public TemplateLogsResourceIntTest(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcWebRestFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcWebRestFolder()+";\r\n\n" +
		"import " + conf.getPackageclass() + "." + Utils.getClassNameCamelCase(conf.getProjectName()) + conf.getApp() + ";\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcWebRestVmFolder()+".LoggerVM;\r\n"+
		"import ch.qos.logback.classic.AsyncAppender;\r\n" +
		"import ch.qos.logback.classic.LoggerContext;\r\n" +
		"import org.junit.Before;\r\n" +
		"import org.junit.Test;\r\n" +
		"import org.junit.runner.RunWith;\r\n" +
		"import org.slf4j.LoggerFactory;\r\n" +
		"import org.springframework.boot.test.context.SpringBootTest;\r\n" +
		"import org.springframework.http.MediaType;\r\n" +
		"import org.springframework.test.context.junit4.SpringRunner;\r\n" +
		"import org.springframework.test.web.servlet.MockMvc;\r\n" +
		"import org.springframework.test.web.servlet.setup.MockMvcBuilders;\r\n" +
		"import static org.assertj.core.api.Assertions.assertThat;\r\n" +
		"import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;\r\n" +
		"import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;\r\n" +
		"import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;\r\n" +
		"import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;\r\n\n" +
		"/**\r\n" +
		" * Test class for the LogsResource REST controller.\r\n" +
		" *\r\n" +
		" * @see LogsResource\r\n" +
		" */\r\n" +
		"@RunWith(SpringRunner.class)\r\n" +
		"@SpringBootTest(classes = "+Utils.getClassNameCamelCase(conf.getProjectName()) + conf.getApp()+".class)\r\n" +
		"public class LogsResourceIntTest {\r\n\n" +
		"    private MockMvc restLogsMockMvc;\r\n" +
		"    @Before\r\n" +
		"    public void setup() {\r\n" +
		"        LogsResource logsResource = new LogsResource();\r\n" +
		"        this.restLogsMockMvc = MockMvcBuilders\r\n" +
		"            .standaloneSetup(logsResource)\r\n" +
		"            .build();\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void getAllLogs() throws Exception {\r\n" +
		"        restLogsMockMvc.perform(get(\"/management/logs\"))\r\n" +
		"            .andExpect(status().isOk())\r\n" +
		"            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void changeLogs() throws Exception {\r\n" +
		"        LoggerVM logger = new LoggerVM();\r\n" +
		"        logger.setLevel(\"INFO\");\r\n" +
		"        logger.setName(\"ROOT\");\r\n" +
		"        restLogsMockMvc.perform(put(\"/management/logs\")\r\n" +
		"            .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"            .content(TestUtil.convertObjectToJsonBytes(logger)))\r\n" +
		"            .andExpect(status().isNoContent());\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testLogstashAppender() {\r\n" +
		"        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();\r\n" +
		"        assertThat(context.getLogger(\"ROOT\").getAppender(\"ASYNC_LOGSTASH\")).isInstanceOf(AsyncAppender.class);\r\n" +
		"    }\r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "LogsResourceIntTest";
	}

	public String getSourceFolder() {
		return "src/test/java";
	}

}
