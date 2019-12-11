package it.eng.generate.template.test;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateExceptionTranslatorIntTest extends AbstractTemplate{

	public TemplateExceptionTranslatorIntTest(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcWebRestErrorsFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcWebRestErrorsFolder()+";\r\n\n" +
		"import "+ conf.getPackageclass() + "." + Utils.getClassNameCamelCase(conf.getProjectName()) + conf.getApp()+";\r\n" +
		"import org.junit.Before;\r\n" +
		"import org.junit.Test;\r\n" +
		"import org.junit.runner.RunWith;\r\n" +
		"import org.springframework.beans.factory.annotation.Autowired;\r\n" +
		"import org.springframework.boot.test.context.SpringBootTest;\r\n" +
		"import org.springframework.http.MediaType;\r\n" +
		"import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;\r\n" +
		"import org.springframework.test.context.junit4.SpringRunner;\r\n" +
		"import org.springframework.test.web.servlet.MockMvc;\r\n" +
		"import org.springframework.test.web.servlet.setup.MockMvcBuilders;\r\n" +
		"import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;\r\n" +
		"import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;\r\n" +
		"import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;\r\n" +
		"import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;\r\n" +
		"import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;\r\n\n" +
		"/**\r\n" +
		" * Test class for the ExceptionTranslator controller advice.\r\n" +
		" *\r\n" +
		" * @see ExceptionTranslator\r\n" +
		" */\r\n" +
		"@RunWith(SpringRunner.class)\r\n" +
		"@SpringBootTest(classes = "+Utils.getClassNameCamelCase(conf.getProjectName()) + conf.getApp()+".class)\r\n" +
		"public class ExceptionTranslatorIntTest {\r\n\n" +
		"    @Autowired\r\n" +
		"    private ExceptionTranslatorTestController controller;\r\n\n" +
		"    @Autowired\r\n" +
		"    private ExceptionTranslator exceptionTranslator;\r\n\n" +
		"    @Autowired\r\n" +
		"    private MappingJackson2HttpMessageConverter jacksonMessageConverter;\r\n\n" +
		"    private MockMvc mockMvc;\r\n\n" +
		"    @Before\r\n" +
		"    public void setup() {\r\n" +
		"        mockMvc = MockMvcBuilders.standaloneSetup(controller)\r\n" +
		"            .setControllerAdvice(exceptionTranslator)\r\n" +
		"            .setMessageConverters(jacksonMessageConverter)\r\n" +
		"            .build();\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testConcurrencyFailure() throws Exception {\r\n" +
		"        mockMvc.perform(get(\"/test/concurrency-failure\"))\r\n" +
		"            .andExpect(status().isConflict())\r\n" +
		"            .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))\r\n" +
		"            .andExpect(jsonPath(\"$.message\").value(ErrorConstants.ERR_CONCURRENCY_FAILURE));\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testMethodArgumentNotValid() throws Exception {\r\n" +
		"         mockMvc.perform(post(\"/test/method-argument\").content(\"{}\").contentType(MediaType.APPLICATION_JSON))\r\n" +
		"             .andExpect(status().isBadRequest())\r\n" +
		"             .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))\r\n" +
		"             .andExpect(jsonPath(\"$.message\").value(ErrorConstants.ERR_VALIDATION))\r\n" +
		"             .andExpect(jsonPath(\"$.fieldErrors.[0].objectName\").value(\"testDTO\"))\r\n" +
		"             .andExpect(jsonPath(\"$.fieldErrors.[0].field\").value(\"test\"))\r\n" +
		"             .andExpect(jsonPath(\"$.fieldErrors.[0].message\").value(\"NotNull\"));\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testParameterizedError() throws Exception {\r\n" +
		"        mockMvc.perform(get(\"/test/parameterized-error\"))\r\n" +
		"            .andExpect(status().isBadRequest())\r\n" +
		"            .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))\r\n" +
		"            .andExpect(jsonPath(\"$.message\").value(\"test parameterized error\"))\r\n" +
		"            .andExpect(jsonPath(\"$.params.param0\").value(\"param0_value\"))\r\n" +
		"            .andExpect(jsonPath(\"$.params.param1\").value(\"param1_value\"));\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testParameterizedError2() throws Exception {\r\n" +
		"        mockMvc.perform(get(\"/test/parameterized-error2\"))\r\n" +
		"            .andExpect(status().isBadRequest())\r\n" +
		"            .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))\r\n" +
		"            .andExpect(jsonPath(\"$.message\").value(\"test parameterized error\"))\r\n" +
		"            .andExpect(jsonPath(\"$.params.foo\").value(\"foo_value\"))\r\n" +
		"            .andExpect(jsonPath(\"$.params.bar\").value(\"bar_value\"));\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testMissingServletRequestPartException() throws Exception {\r\n" +
		"        mockMvc.perform(get(\"/test/missing-servlet-request-part\"))\r\n" +
		"            .andExpect(status().isBadRequest())\r\n" +
		"            .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))\r\n" +
		"            .andExpect(jsonPath(\"$.message\").value(\"error.http.400\"));\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testMissingServletRequestParameterException() throws Exception {\r\n" +
		"        mockMvc.perform(get(\"/test/missing-servlet-request-parameter\"))\r\n" +
		"            .andExpect(status().isBadRequest())\r\n" +
		"            .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))\r\n" +
		"            .andExpect(jsonPath(\"$.message\").value(\"error.http.400\"));\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testAccessDenied() throws Exception {\r\n" +
		"        mockMvc.perform(get(\"/test/access-denied\"))\r\n" +
		"            .andExpect(status().isForbidden())\r\n" +
		"            .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))\r\n" +
		"            .andExpect(jsonPath(\"$.message\").value(\"error.http.403\"))\r\n" +
		"            .andExpect(jsonPath(\"$.detail\").value(\"test access denied!\"));\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testUnauthorized() throws Exception {\r\n" +
		"        mockMvc.perform(get(\"/test/unauthorized\"))\r\n" +
		"            .andExpect(status().isUnauthorized())\r\n" +
		"            .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))\r\n" +
		"            .andExpect(jsonPath(\"$.message\").value(\"error.http.401\"))\r\n" +
		"            .andExpect(jsonPath(\"$.path\").value(\"/test/unauthorized\"))\r\n" +
		"            .andExpect(jsonPath(\"$.detail\").value(\"test authentication failed!\"));\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testMethodNotSupported() throws Exception {\r\n" +
		"        mockMvc.perform(post(\"/test/access-denied\"))\r\n" +
		"            .andExpect(status().isMethodNotAllowed())\r\n" +
		"            .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))\r\n" +
		"            .andExpect(jsonPath(\"$.message\").value(\"error.http.405\"))\r\n" +
		"            .andExpect(jsonPath(\"$.detail\").value(\"Request method 'POST' not supported\"));\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testExceptionWithResponseStatus() throws Exception {\r\n" +
		"        mockMvc.perform(get(\"/test/response-status\"))\r\n" +
		"            .andExpect(status().isBadRequest())\r\n" +
		"            .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))\r\n" +
		"            .andExpect(jsonPath(\"$.message\").value(\"error.http.400\"))\r\n" +
		"            .andExpect(jsonPath(\"$.title\").value(\"test response status\"));\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testInternalServerError() throws Exception {\r\n" +
		"        mockMvc.perform(get(\"/test/internal-server-error\"))\r\n" +
		"            .andExpect(status().isInternalServerError())\r\n" +
		"            .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))\r\n" +
		"            .andExpect(jsonPath(\"$.message\").value(\"error.http.500\"))\r\n" +
		"            .andExpect(jsonPath(\"$.title\").value(\"Internal Server Error\"));\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "ExceptionTranslatorIntTest";
	}

	public String getSourceFolder() {
		return "src/test/java";
	}

}
