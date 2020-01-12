package it.eng.generate.template.web.util;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateHeaderUtil extends AbstractTemplate{

	public TemplateHeaderUtil(DataBase dataBase) {
		super(dataBase);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcWebRestUtilFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcWebRestUtilFolder()+";\r\n\n" +
		"import org.slf4j.Logger;\r\n" +
		"import org.slf4j.LoggerFactory;\r\n" +
		"import org.springframework.http.HttpHeaders;\r\n\n" +
		"/**\r\n" +
		" * Utility class for HTTP headers creation.\r\n" +
		" */\r\n" +
		"public final class "+getClassName()+" {\r\n" +
		"    private static final Logger log = LoggerFactory.getLogger("+getClassName()+".class);\r\n" +
		"    private static final String APPLICATION_NAME = \""+ conf.getProjectName() + conf.getApp()  +"\";\r\n\n" +
		"    private "+getClassName()+"() {\r\n" +
		"    }\r\n\n" +
		"    public static HttpHeaders createAlert(String message, String param) {\r\n" +
		"        HttpHeaders headers = new HttpHeaders();\r\n" +
		"        headers.add(\"X-\" + APPLICATION_NAME + \"-alert\", message);\r\n" +
		"        headers.add(\"X-\" + APPLICATION_NAME + \"-params\", param);\r\n" +
		"        return headers;\r\n" +
		"    }\r\n\n" +
		"    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {\r\n" +
		"        return createAlert(APPLICATION_NAME + \".\" + entityName + \".created\", param);\r\n" +
		"    }\r\n\n" +
		"    public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {\r\n" +
		"        return createAlert(APPLICATION_NAME + \".\" + entityName + \".updated\", param);\r\n" +
		"    }\r\n\n" +
		"    public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {\r\n" +
		"        return createAlert(APPLICATION_NAME + \".\" + entityName + \".deleted\", param);\r\n" +
		"    }\r\n\n" +
		"    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {\r\n" +
		"        log.error(\"Entity processing failed, {}\", defaultMessage);\r\n" +
		"        HttpHeaders headers = new HttpHeaders();\r\n" +
		"        headers.add(\"X-\" + APPLICATION_NAME + \"-error\", \"error.\" + errorKey);\r\n" +
		"        headers.add(\"X-\" + APPLICATION_NAME + \"-params\", entityName);\r\n" +
		"        return headers;\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "HeaderUtil";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
