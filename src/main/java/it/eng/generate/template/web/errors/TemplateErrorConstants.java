package it.eng.generate.template.web.errors;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateErrorConstants extends AbstractTemplate{

	public TemplateErrorConstants(DataBase dataBase) {
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
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcWebRestErrorsFolder()+";\r\n\n\n" +
		"import java.net.URI;\r\n\n" +
		"public final class ErrorConstants {\r\n\n" +
		"    public static final String ERR_CONCURRENCY_FAILURE = \"error.concurrencyFailure\";\r\n" +
		"    public static final String ERR_VALIDATION = \"error.validation\";\r\n" +
		"    public static final String PROBLEM_BASE_URL = \"https://www.jhipster.tech/problem\";\r\n" +
		"    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + \"/problem-with-message\");\r\n" +
		"    public static final URI CONSTRAINT_VIOLATION_TYPE = URI.create(PROBLEM_BASE_URL + \"/constraint-violation\");\r\n" +
		"    public static final URI PARAMETERIZED_TYPE = URI.create(PROBLEM_BASE_URL + \"/parameterized\");\r\n" +
		"    public static final URI ENTITY_NOT_FOUND_TYPE = URI.create(PROBLEM_BASE_URL + \"/entity-not-found\");\r\n" +
		"    public static final URI INVALID_PASSWORD_TYPE = URI.create(PROBLEM_BASE_URL + \"/invalid-password\");\r\n" +
		"    public static final URI EMAIL_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + \"/email-already-used\");\r\n" +
		"    public static final URI LOGIN_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + \"/login-already-used\");\r\n" +
		"    public static final URI EMAIL_NOT_FOUND_TYPE = URI.create(PROBLEM_BASE_URL + \"/email-not-found\");\r\n\n" +
		"    private ErrorConstants() {\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "ErrorConstants";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
