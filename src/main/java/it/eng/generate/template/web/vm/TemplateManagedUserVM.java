package it.eng.generate.template.web.vm;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateManagedUserVM extends AbstractTemplate{

	public TemplateManagedUserVM(DataBase dataBase) {
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
		"import " + conf.getPackageclass() + "." + conf.getSrcServiceDtoFolder() + ".UserDTO;\r\n" +
		"import javax.validation.constraints.Size;\r\n\n" +
		"/**\r\n" +
		" * View Model extending the UserDTO, which is meant to be used in the user management UI.\r\n" +
		" */\r\n" +
		"public class "+getClassName()+" extends UserDTO {\r\n\n" +
		"    public static final int PASSWORD_MIN_LENGTH = 4;\r\n" +
		"    public static final int PASSWORD_MAX_LENGTH = 100;\r\n\n" +
		"    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)\r\n" +
		"    private String password;\r\n\n" +
		"    public "+getClassName()+"() {\r\n" +
		"        // Empty constructor needed for Jackson.\r\n" +
		"    }\r\n\n" +
		"    public String getPassword() {\r\n" +
		"        return password;\r\n" +
		"    }\r\n\n" +
		"    public void setPassword(String password) {\r\n" +
		"        this.password = password;\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    public String toString() {\r\n" +
		"        return \""+getClassName()+"{\" +\r\n" +
		"            \"} \" + super.toString();\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "ManagedUserVM";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
