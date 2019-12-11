package it.eng.generate.template.web.errors;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateFieldErrorVM extends AbstractTemplate{

	public TemplateFieldErrorVM(DataBase dataBase) {
		super(dataBase);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcWebRestErrorsFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcWebRestErrorsFolder()+";\r\n\n" +
		"import java.io.Serializable;\r\n\n" +
		"public class FieldErrorVM implements Serializable {\r\n\n" +
		"    private static final long serialVersionUID = 1L;\r\n" +
		"    private final String objectName;\r\n" +
		"    private final String field;\r\n" +
		"    private final String message;\r\n\n" +
		"    public FieldErrorVM(String dto, String field, String message) {\r\n" +
		"        this.objectName = dto;\r\n" +
		"        this.field = field;\r\n" +
		"        this.message = message;\r\n" +
		"    }\r\n\n" +
		"    public String getObjectName() {\r\n" +
		"        return objectName;\r\n" +
		"    }\r\n\n" +
		"    public String getField() {\r\n" +
		"        return field;\r\n" +
		"    }\r\n\n" +
		"    public String getMessage() {\r\n" +
		"        return message;\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "FieldErrorVM";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
