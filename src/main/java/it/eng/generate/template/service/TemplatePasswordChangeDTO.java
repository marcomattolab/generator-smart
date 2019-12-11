package it.eng.generate.template.service;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplatePasswordChangeDTO extends AbstractTemplate{

	public TemplatePasswordChangeDTO(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcServiceDtoFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcServiceDtoFolder()+";\r\n\n" +
		"/**\r\n" +
		" * A DTO representing a password change required data - current and new password.\r\n" +
		" */\r\n" +
		"public class "+getClassName()+" {\r\n" +
		"    private String currentPassword;\r\n" +
		"    private String newPassword;\r\n\n" +
		"    public "+getClassName()+"() {\r\n" +
		"        // Empty constructor needed for Jackson.\r\n" +
		"    }\r\n\n" +
		"    public "+getClassName()+"(String currentPassword, String newPassword) {\r\n" +
		"        this.currentPassword = currentPassword;\r\n" +
		"        this.newPassword = newPassword;\r\n" +
		"    }\r\n\n" +
		"    public String getCurrentPassword() {\r\n" +
		"        return currentPassword;\r\n" +
		"    }\r\n\n" +
		"    public void setCurrentPassword(String currentPassword) {\r\n" +
		"        this.currentPassword = currentPassword;\r\n" +
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
		return "PasswordChangeDTO";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
