package it.eng.generate.template.security;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateAuthoritiesConstants extends AbstractTemplate{
	private static final String ROLE = "ROLE_";

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
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcSecurityFolder()+";\n\n" +
		"/**\n" +
		" * Constants for Spring Security authorities.\n" +
		" */\n" +
		"public final class "+getClassName()+" {\n" +
		buildAuthorities(conf) +
//		"    public static final String SUPERADMIN = \"ROLE_SUPERADMIN\";\r\n\n" +
//		"    public static final String ADMIN = \"ROLE_ADMIN\";\r\n\n" +
//		"    public static final String USER = \"ROLE_USER\";\r\n\n" +
//		"    public static final String OPERATOR = \"ROLE_OPERATOR\";\r\n\n" +
//		"    public static final String ANONYMOUS = \"ROLE_ANONYMOUS\";\r\n\n" +
		"\n" +
		"    private "+getClassName()+"() {\n" +
		"    }\n" +
		"}\n";
		return body;
	}

	private String buildAuthorities(ConfigCreateProject conf) {
		String res = "";
		String[] profiles = conf.getProfiles();
		for (int i = 0; i < profiles.length; i++) {
			String profileValue = profiles[i];
			String profileKey = profiles[i].replace(ROLE, "");
			res += "    public static final String "+profileKey+" = \""+profileValue+"\";\n";
		}
		return res;
	}

	public String getClassName() {
		return "AuthoritiesConstants";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
