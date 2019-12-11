package it.eng.generate.template.conf;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateCostants extends AbstractTemplate {

	public TemplateCostants(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcConfigFolder() +";\r\n\n" +
		"/**\r\n" +
		" * Application constants.\r\n" +
		" */\r\n" +
		"public final class "+getClassName()+" {\r\n\n" +
		"    // Regex for acceptable logins\r\n" +
		"    public static final String LOGIN_REGEX = \"^[_.@A-Za-z0-9-]*$\";\r\n" +
		"    public static final String SYSTEM_ACCOUNT = \"system\";\r\n" +
		"    public static final String ANONYMOUS_USER = \"anonymoususer\";\r\n" +
		"    public static final String DEFAULT_LANGUAGE = \"it\";\r\n" +
		"    \r\n" +
		"    private "+getClassName()+"() {\r\n\n" +
		"    }\r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "Constants";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcConfigFolder(),".","/");
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/java";
	}

}
