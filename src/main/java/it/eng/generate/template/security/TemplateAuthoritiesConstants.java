package it.eng.generate.template.security;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateAuthoritiesConstants extends AbstractTemplate{

	public TemplateAuthoritiesConstants(DataBase database) {
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
		"/**\r\n" +
		" * Constants for Spring Security authorities.\r\n" +
		" */\r\n" +
		"public final class "+getClassName()+" {\r\n\n" +
		"    public static final String SUPERADMIN = \"ROLE_SUPERADMIN\";\r\n\n" +
		"    public static final String ADMIN = \"ROLE_ADMIN\";\r\n\n" +
		"    public static final String USER = \"ROLE_USER\";\r\n\n" +
		"    public static final String OPERATOR = \"ROLE_OPERATOR\";\r\n\n" +
		"    public static final String ANONYMOUS = \"ROLE_ANONYMOUS\";\r\n\n" +
		"    private "+getClassName()+"() {\r\n\n" +
		"    }\r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "AuthoritiesConstants";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
