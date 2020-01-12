package it.eng.generate.template.web.errors;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateCustomParameterizedException extends AbstractTemplate{

	public TemplateCustomParameterizedException(DataBase dataBase) {
		super(dataBase);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcWebRestErrorsFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcWebRestErrorsFolder()+";\r\n\n" +
		"import org.zalando.problem.AbstractThrowableProblem;\r\n" +
		"import java.util.HashMap;\r\n" +
		"import java.util.Map;\r\n" +
		"import static org.zalando.problem.Status.BAD_REQUEST;\r\n\n" +
		"/**\r\n" +
		" * Custom, parameterized exception, which can be translated on the client side.\r\n" +
		" * For example:\r\n" +
		" *\r\n" +
		" * <pre>\r\n" +
		" * throw new CustomParameterizedException(&quot;myCustomError&quot;, &quot;hello&quot;, &quot;world&quot;);\r\n" +
		" * </pre>\r\n" +
		" *\r\n" +
		" * Can be translated with:\r\n" +
		" *\r\n" +
		" * <pre>\r\n" +
		" * \"error.myCustomError\" :  \"The server says {{param0}} to {{param1}}\"\r\n" +
		" * </pre>\r\n" +
		" */\r\n" +
		"public class CustomParameterizedException extends AbstractThrowableProblem {\r\n\n" +
		"    private static final long serialVersionUID = 1L;\r\n" +
		"    private static final String PARAM = \"param\";\r\n\n" +
		"    public CustomParameterizedException(String message, String... params) {\r\n" +
		"        this(message, toParamMap(params));\r\n" +
		"    }\r\n\n" +
		"    public CustomParameterizedException(String message, Map<String, Object> paramMap) {\r\n" +
		"        super(ErrorConstants.PARAMETERIZED_TYPE, \"Parameterized Exception\", BAD_REQUEST, null, null, null, toProblemParameters(message, paramMap));\r\n" +
		"    }\r\n\n" +
		"    public static Map<String, Object> toParamMap(String... params) {\r\n" +
		"        Map<String, Object> paramMap = new HashMap<>();\r\n" +
		"        if (params != null && params.length > 0) {\r\n" +
		"            for (int i = 0; i < params.length; i++) {\r\n" +
		"                paramMap.put(PARAM + i, params[i]);\r\n" +
		"            }\r\n" +
		"        }\r\n" +
		"        return paramMap;\r\n" +
		"    }\r\n\n" +
		"    public static Map<String, Object> toProblemParameters(String message, Map<String, Object> paramMap) {\r\n" +
		"        Map<String, Object> parameters = new HashMap<>();\r\n" +
		"        parameters.put(\"message\", message);\r\n" +
		"        parameters.put(\"params\", paramMap);\r\n" +
		"        return parameters;\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "CustomParameterizedException";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
