package it.eng.generate.template.web.errors;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateLoginAlreadyUsedException extends AbstractTemplate{

	public TemplateLoginAlreadyUsedException(DataBase dataBase) {
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
		"public class LoginAlreadyUsedException extends BadRequestAlertException {\r\n\n" +
		"    private static final long serialVersionUID = 1L;\r\n\n" +
		"    public LoginAlreadyUsedException() {\r\n" +
		"        super(ErrorConstants.LOGIN_ALREADY_USED_TYPE, \"Login name already used!\", \"userManagement\", \"userexists\");\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "LoginAlreadyUsedException";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
