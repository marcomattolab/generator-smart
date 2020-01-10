package it.eng.generate.template.web.vm;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateLoginVM extends AbstractTemplate{

	public TemplateLoginVM(DataBase dataBase) {
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

		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcWebRestVmFolder()+";\r\n\n" +
		"import javax.validation.constraints.NotNull;\r\n" +
		"import javax.validation.constraints.Size;\r\n\n" +
		"/**\r\n" +
		" * View Model object for storing a user's credentials.\r\n" +
		" */\r\n" +
		"public class "+getClassName()+" {\r\n" +
		"    @NotNull\r\n" +
		"    @Size(min = 1, max = 50)\r\n" +
		"    private String username;\r\n" +
		"    @NotNull\r\n" +
		"    @Size(min = 4, max = 100)\r\n" +
		"    private String password;\r\n" +
		"    private Boolean rememberMe;\r\n" +
		"    public String getUsername() {\r\n" +
		"        return username;\r\n" +
		"    }\r\n" +
		"    public void setUsername(String username) {\r\n" +
		"        this.username = username;\r\n" +
		"    }\r\n" +
		"    public String getPassword() {\r\n" +
		"        return password;\r\n" +
		"    }\r\n" +
		"    public void setPassword(String password) {\r\n" +
		"        this.password = password;\r\n" +
		"    }\r\n" +
		"    public Boolean isRememberMe() {\r\n" +
		"        return rememberMe;\r\n" +
		"    }\r\n" +
		"    public void setRememberMe(Boolean rememberMe) {\r\n" +
		"        this.rememberMe = rememberMe;\r\n" +
		"    }\r\n" +
		"    @Override\r\n" +
		"    public String toString() {\r\n" +
		"        return \""+getClassName()+"{\" +\r\n" +
		"            \"username='\" + username + '\\'' +\r\n" +
		"            \", rememberMe=\" + rememberMe +\r\n" +
		"            '}';\r\n" +
		"    }\r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "LoginVM";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
