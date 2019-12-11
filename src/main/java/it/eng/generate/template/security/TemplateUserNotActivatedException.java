package it.eng.generate.template.security;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateUserNotActivatedException extends AbstractTemplate{

	public TemplateUserNotActivatedException(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcSecurityFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcSecurityFolder()+";\r\n\n" +
		"import org.springframework.security.core.AuthenticationException;\r\n\n" +
		"/**\r\n" +
		" * This exception is thrown in case of a not activated user trying to authenticate.\r\n" +
		" */\r\n" +
		"public class "+getClassName()+" extends AuthenticationException {\r\n\n" +
		"    private static final long serialVersionUID = 1L;\r\n\n" +
		"    public "+getClassName()+"(String message) {\r\n" +
		"        super(message);\r\n" +
		"    }\r\n\n" +
		"    public "+getClassName()+"(String message, Throwable t) {\r\n" +
		"        super(message, t);\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "UserNotActivatedException";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
