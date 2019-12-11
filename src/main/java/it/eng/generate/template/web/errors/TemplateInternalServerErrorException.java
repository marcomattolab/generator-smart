package it.eng.generate.template.web.errors;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateInternalServerErrorException extends AbstractTemplate{

	public TemplateInternalServerErrorException(DataBase dataBase) {
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
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcWebRestErrorsFolder()+";\r\n\n" +
		"import org.zalando.problem.AbstractThrowableProblem;\r\n" +
		"import org.zalando.problem.Status;\r\n\n" +
		"/**\r\n" +
		" * Simple exception with a message, that returns an Internal Server Error code.\r\n" +
		" */\r\n" +
		"public class InternalServerErrorException extends AbstractThrowableProblem {\r\n\n" +
		"    private static final long serialVersionUID = 1L;\r\n\n" +
		"    public InternalServerErrorException(String message) {\r\n" +
		"        super(ErrorConstants.DEFAULT_TYPE, message, Status.INTERNAL_SERVER_ERROR);\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "InternalServerErrorException";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
