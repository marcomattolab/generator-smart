package it.eng.generate.template.conf;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateDateTimeFormatConfiguration extends AbstractTemplate {

	public TemplateDateTimeFormatConfiguration(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcConfigFolder() +";\r\n\n" +
		"import org.springframework.context.annotation.Configuration;\r\n" +
		"import org.springframework.format.FormatterRegistry;\r\n" +
		"import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;\r\n" +
		"import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;\r\n\n" +
		"/**\r\n" +
		" * Configure the converters to use the ISO format for dates by default.\r\n" +
		" */\r\n" +
		"@Configuration\r\n" +
		"public class "+getClassName()+" implements WebMvcConfigurer {\r\n\n" +
		"    @Override\r\n" +
		"    public void addFormatters(FormatterRegistry registry) {\r\n" +
		"        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();\r\n" +
		"        registrar.setUseIsoFormat(true);\r\n" +
		"        registrar.registerFormatters(registry);\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "DateTimeFormatConfiguration";
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
