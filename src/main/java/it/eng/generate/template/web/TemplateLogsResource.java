package it.eng.generate.template.web;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateLogsResource extends AbstractTemplate{

	public TemplateLogsResource(DataBase dataBase) {
		super(dataBase);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcWebRestFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcWebRestFolder()+";\r\n\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcWebRestVmFolder()+ ".LoggerVM;\r\n" +
		"import ch.qos.logback.classic.Level;\r\n" +
		"import ch.qos.logback.classic.LoggerContext;\r\n" +
		"import com.codahale.metrics.annotation.Timed;\r\n" +
		"import org.slf4j.LoggerFactory;\r\n" +
		"import org.springframework.http.HttpStatus;\r\n" +
		"import org.springframework.web.bind.annotation.*;\r\n" +
		"import java.util.List;\r\n" +
		"import java.util.stream.Collectors;\r\n\n" +
		"/**\r\n" +
		" * Controller for view and managing Log Level at runtime.\r\n" +
		" */\r\n" +
		"@RestController\r\n" +
		"@RequestMapping(\"/management\")\r\n" +
		"public class "+getClassName()+" {\r\n\n" +
		"    @GetMapping(\"/logs\")\r\n" +
		"    @Timed\r\n" +
		"    public List<LoggerVM> getList() {\r\n" +
		"        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();\r\n" +
		"        return context.getLoggerList()\r\n" +
		"            .stream()\r\n" +
		"            .map(LoggerVM::new)\r\n" +
		"            .collect(Collectors.toList());\r\n" +
		"    }\r\n\n" +
		"    @PutMapping(\"/logs\")\r\n" +
		"    @ResponseStatus(HttpStatus.NO_CONTENT)\r\n" +
		"    @Timed\r\n" +
		"    public void changeLevel(@RequestBody LoggerVM jsonLogger) {\r\n" +
		"        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();\r\n" +
		"        context.getLogger(jsonLogger.getName()).setLevel(Level.valueOf(jsonLogger.getLevel()));\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "LogsResource";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
