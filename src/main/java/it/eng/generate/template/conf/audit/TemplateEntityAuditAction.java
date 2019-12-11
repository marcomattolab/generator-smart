package it.eng.generate.template.conf.audit;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateEntityAuditAction extends AbstractTemplate {

	public TemplateEntityAuditAction(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcConfigAuditFolder() +";\r\n\n" +
		"/**\r\n" +
		" * Enum for the different audit actions\r\n" +
		" */\r\n" +
		"public enum EntityAuditAction {\r\n\n" +
		"    CREATE(\"CREATE\"),\r\n" +
		"    UPDATE(\"UPDATE\"),\r\n" +
		"    DELETE(\"DELETE\");\r\n" +
		"    private String value;\r\n\n" +
		"    EntityAuditAction(final String value) {\r\n" +
		"        this.value = value;\r\n" +
		"    }\r\n\n" +
		"    public String value() {\r\n" +
		"        return value;\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    public String toString() {\r\n" +
		"        return this.value();\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "EntityAuditAction";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcConfigAuditFolder(),".","/");
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/java";
	}

}
