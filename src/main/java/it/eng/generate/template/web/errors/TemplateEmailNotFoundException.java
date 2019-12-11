package it.eng.generate.template.web.errors;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateEmailNotFoundException extends AbstractTemplate{

	public TemplateEmailNotFoundException(DataBase dataBase) {
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
		"public class EmailNotFoundException extends AbstractThrowableProblem {\r\n\n" +
		"    private static final long serialVersionUID = 1L;\r\n\n" +
		"    public EmailNotFoundException() {\r\n" +
		"        super(ErrorConstants.EMAIL_NOT_FOUND_TYPE, \"Email address not registered\", Status.BAD_REQUEST);\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "EmailNotFoundException";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
