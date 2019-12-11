package it.eng.generate.template.web.errors;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateEmailAlreadyUsedException extends AbstractTemplate{

	public TemplateEmailAlreadyUsedException(DataBase dataBase) {
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
		"public class EmailAlreadyUsedException extends BadRequestAlertException {\r\n\n" +
		"    private static final long serialVersionUID = 1L;\r\n\n" +
		"    public EmailAlreadyUsedException() {\r\n" +
		"        super(ErrorConstants.EMAIL_ALREADY_USED_TYPE, \"Email is already in use!\", \"userManagement\", \"emailexists\");\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "EmailAlreadyUsedException";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
