package it.eng.generate.template.web.vm;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateLoggerVM extends AbstractTemplate{

	public TemplateLoggerVM(DataBase dataBase) {
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
		"import ch.qos.logback.classic.Logger;\r\n\n" +
		"/**\r\n" +
		" * View Model object for storing a Logback logger.\r\n" +
		" */\r\n" +
		"public class "+getClassName()+" {\r\n\n" +
		"    private String name;\r\n" +
		"    private String level;\r\n" +
		"    public "+getClassName()+"(Logger logger) {\r\n\n" +
		"        this.name = logger.getName();\r\n" +
		"        this.level = logger.getEffectiveLevel().toString();\r\n" +
		"    }\r\n\n" +
		"    public "+getClassName()+"() {\r\n" +
		"        // Empty public constructor used by Jackson.\r\n" +
		"    }\r\n\n" +
		"    public String getName() {\r\n" +
		"        return name;\r\n" +
		"    }\r\n\n" +
		"    public void setName(String name) {\r\n" +
		"        this.name = name;\r\n" +
		"    }\r\n\n" +
		"    public String getLevel() {\r\n" +
		"        return level;\r\n" +
		"    }\r\n\n" +
		"    public void setLevel(String level) {\r\n" +
		"        this.level = level;\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    public String toString() {\r\n" +
		"        return \""+getClassName()+"{\" +\r\n" +
		"            \"name='\" + name + '\\'' +\r\n" +
		"            \", level='\" + level + '\\'' +\r\n" +
		"            '}';\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "LoggerVM";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
