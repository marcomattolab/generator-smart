package it.eng.generate.template.conf;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateLoggingAspectConfiguration extends AbstractTemplate {

	public TemplateLoggingAspectConfiguration(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcConfigFolder() +";\r\n\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcAopLoggingFolder()+".LoggingAspect;\r\n" +
		"import io.github.jhipster.config.JHipsterConstants;\r\n" +
		"import org.springframework.context.annotation.*;\r\n" +
		"import org.springframework.core.env.Environment;\r\n\n" +
		"@Configuration\r\n" +
		"@EnableAspectJAutoProxy\r\n" +
		"public class " + getClassName() + " {\r\n\n" +
		"    @Bean\r\n" +
		"    @Profile(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT)\r\n" +
		"    public LoggingAspect loggingAspect(Environment env) {\r\n" +
		"        return new LoggingAspect(env);\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "LoggingAspectConfiguration";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcConfigFolder(),".","/");
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/java";
	}

}
