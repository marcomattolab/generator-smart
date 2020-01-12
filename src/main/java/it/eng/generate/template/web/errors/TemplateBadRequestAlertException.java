package it.eng.generate.template.web.errors;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateBadRequestAlertException extends AbstractTemplate{

	public TemplateBadRequestAlertException(DataBase dataBase) {
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
		
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcWebRestErrorsFolder()+";\r\n\n" +
		"import org.zalando.problem.AbstractThrowableProblem;\r\n" +
		"import org.zalando.problem.Status;\r\n" +
		"import java.net.URI;\r\n" +
		"import java.util.HashMap;\r\n" +
		"import java.util.Map;\r\n\n" +
		"public class BadRequestAlertException extends AbstractThrowableProblem {\r\n\n" +
		"    private static final long serialVersionUID = 1L;\r\n" +
		"    private final String entityName;\r\n" +
		"    private final String errorKey;\r\n\n" +
		"    public BadRequestAlertException(String defaultMessage, String entityName, String errorKey) {\r\n" +
		"        this(ErrorConstants.DEFAULT_TYPE, defaultMessage, entityName, errorKey);\r\n" +
		"    }\r\n\n" +
		"    public BadRequestAlertException(URI type, String defaultMessage, String entityName, String errorKey) {\r\n" +
		"        super(type, defaultMessage, Status.BAD_REQUEST, null, null, null, getAlertParameters(entityName, errorKey));\r\n" +
		"        this.entityName = entityName;\r\n" +
		"        this.errorKey = errorKey;\r\n" +
		"    }\r\n\n" +
		"    public String getEntityName() {\r\n" +
		"        return entityName;\r\n" +
		"    }\r\n\n" +
		"    public String getErrorKey() {\r\n" +
		"        return errorKey;\r\n" +
		"    }\r\n\n" +
		"    private static Map<String, Object> getAlertParameters(String entityName, String errorKey) {\r\n" +
		"        Map<String, Object> parameters = new HashMap<>();\r\n" +
		"        parameters.put(\"message\", \"error.\" + errorKey);\r\n" +
		"        parameters.put(\"params\", entityName);\r\n" +
		"        return parameters;\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "BadRequestAlertException";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
