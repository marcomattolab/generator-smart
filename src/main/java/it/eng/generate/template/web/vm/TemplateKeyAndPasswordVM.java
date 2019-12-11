package it.eng.generate.template.web.vm;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateKeyAndPasswordVM extends AbstractTemplate{

	public TemplateKeyAndPasswordVM(DataBase dataBase) {
		super(dataBase);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcWebRestVmFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcWebRestVmFolder()+";\r\n\n" +
		"/**\r\n" +
		" * View Model object for storing the user's key and password.\r\n" +
		" */\r\n" +
		"public class KeyAndPasswordVM {\r\n\n" +
		"    private String key;\r\n" +
		"    private String newPassword;\r\n" +
		"    public String getKey() {\r\n" +
		"        return key;\r\n" +
		"    }\r\n\n" +
		"    public void setKey(String key) {\r\n" +
		"        this.key = key;\r\n" +
		"    }\r\n\n" +
		"    public String getNewPassword() {\r\n" +
		"        return newPassword;\r\n" +
		"    }\r\n\n" +
		"    public void setNewPassword(String newPassword) {\r\n" +
		"        this.newPassword = newPassword;\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "KeyAndPasswordVM";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
