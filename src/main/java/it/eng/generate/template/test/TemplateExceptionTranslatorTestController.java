package it.eng.generate.template.test;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateExceptionTranslatorTestController extends AbstractTemplate{

	public TemplateExceptionTranslatorTestController(DataBase database) {
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
		"import org.springframework.dao.ConcurrencyFailureException;\r\n" +
		"import org.springframework.http.HttpStatus;\r\n" +
		"import org.springframework.security.access.AccessDeniedException;\r\n" +
		"import org.springframework.security.authentication.BadCredentialsException;\r\n" +
		"import org.springframework.web.bind.annotation.*;\r\n" +
		"import javax.validation.Valid;\r\n" +
		"import javax.validation.constraints.NotNull;\r\n" +
		"import java.util.HashMap;\r\n" +
		"import java.util.Map;\r\n\n" +
		"@RestController\r\n" +
		"public class ExceptionTranslatorTestController {\r\n" +
		"    @GetMapping(\"/test/concurrency-failure\")\r\n" +
		"    public void concurrencyFailure() {\r\n" +
		"        throw new ConcurrencyFailureException(\"test concurrency failure\");\r\n" +
		"    }\r\n\n" +
		"    @PostMapping(\"/test/method-argument\")\r\n" +
		"    public void methodArgument(@Valid @RequestBody TestDTO testDTO) {\r\n" +
		"    }\r\n\n" +
		"    @GetMapping(\"/test/parameterized-error\")\r\n" +
		"    public void parameterizedError() {\r\n" +
		"        throw new CustomParameterizedException(\"test parameterized error\", \"param0_value\", \"param1_value\");\r\n" +
		"    }\r\n\n" +
		"    @GetMapping(\"/test/parameterized-error2\")\r\n" +
		"    public void parameterizedError2() {\r\n" +
		"        Map<String, Object> params = new HashMap<>();\r\n" +
		"        params.put(\"foo\", \"foo_value\");\r\n" +
		"        params.put(\"bar\", \"bar_value\");\r\n" +
		"        throw new CustomParameterizedException(\"test parameterized error\", params);\r\n" +
		"    }\r\n\n" +
		"    @GetMapping(\"/test/missing-servlet-request-part\")\r\n" +
		"    public void missingServletRequestPartException(@RequestPart String part) {\r\n" +
		"    }\r\n\n" +
		"    @GetMapping(\"/test/missing-servlet-request-parameter\")\r\n" +
		"    public void missingServletRequestParameterException(@RequestParam String param) {\r\n" +
		"    }\r\n" +
		"    @GetMapping(\"/test/access-denied\")\r\n" +
		"    public void accessdenied() {\r\n" +
		"        throw new AccessDeniedException(\"test access denied!\");\r\n" +
		"    }\r\n\n" +
		"    @GetMapping(\"/test/unauthorized\")\r\n" +
		"    public void unauthorized() {\r\n" +
		"        throw new BadCredentialsException(\"test authentication failed!\");\r\n" +
		"    }\r\n\n" +
		"    @GetMapping(\"/test/response-status\")\r\n" +
		"    public void exceptionWithReponseStatus() {\r\n" +
		"        throw new TestResponseStatusException();\r\n" +
		"    }\r\n\n" +
		"    @GetMapping(\"/test/internal-server-error\")\r\n" +
		"    public void internalServerError() {\r\n" +
		"        throw new RuntimeException();\r\n" +
		"    }\r\n\n" +
		"    public static class TestDTO {\r\n" +
		"        @NotNull\r\n" +
		"        private String test;\r\n" +
		"        public String getTest() {\r\n" +
		"            return test;\r\n" +
		"        }\r\n" +
		"        public void setTest(String test) {\r\n" +
		"            this.test = test;\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = \"test response status\")\r\n" +
		"    @SuppressWarnings(\"serial\")\r\n" +
		"    public static class TestResponseStatusException extends RuntimeException {\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "ExceptionTranslatorTestController";
	}

	public String getSourceFolder() {
		return "src/test/java";
	}

}
