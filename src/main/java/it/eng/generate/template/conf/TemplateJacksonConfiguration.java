package it.eng.generate.template.conf;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateJacksonConfiguration extends AbstractTemplate {

	public TemplateJacksonConfiguration(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcConfigFolder() +";\r\n\n" +
		"import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;\r\n" +
		"import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;\r\n" +
		"import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;\r\n" +
		"import com.fasterxml.jackson.module.afterburner.AfterburnerModule;\r\n" +
		"import org.springframework.context.annotation.Bean;\r\n" +
		"import org.springframework.context.annotation.Configuration;\r\n" +
		"import org.zalando.problem.ProblemModule;\r\n" +
		"import org.zalando.problem.violations.ConstraintViolationProblemModule;\r\n\n" +
		"@Configuration\r\n" +
		"public class "+getClassName()+" {\r\n\n" +
		"    /**\r\n" +
		"     * Support for Java date and time API.\r\n" +
		"     * @return the corresponding Jackson module.\r\n" +
		"     */\r\n" +
		"    @Bean\r\n" +
		"    public JavaTimeModule javaTimeModule() {\r\n" +
		"        return new JavaTimeModule();\r\n" +
		"    }\r\n\n" +
		"    @Bean\r\n" +
		"    public Jdk8Module jdk8TimeModule() {\r\n" +
		"        return new Jdk8Module();\r\n" +
		"    }\r\n\n" +
		"    /*\r\n" +
		"     * Support for Hibernate types in Jackson.\r\n" +
		"     */\r\n" +
		"    @Bean\r\n" +
		"    public Hibernate5Module hibernate5Module() {\r\n" +
		"        return new Hibernate5Module();\r\n" +
		"    }\r\n\n" +
		"    /*\r\n" +
		"     * Jackson Afterburner module to speed up serialization/deserialization.\r\n" +
		"     */\r\n" +
		"    @Bean\r\n" +
		"    public AfterburnerModule afterburnerModule() {\r\n" +
		"        return new AfterburnerModule();\r\n" +
		"    }\r\n\n" +
		"    /*\r\n" +
		"     * Module for serialization/deserialization of RFC7807 Problem.\r\n" +
		"     */\r\n" +
		"    @Bean\r\n" +
		"    ProblemModule problemModule() {\r\n" +
		"        return new ProblemModule();\r\n" +
		"    }\r\n\n" +
		"    /*\r\n" +
		"     * Module for serialization/deserialization of ConstraintViolationProblem.\r\n" +
		"     */\r\n" +
		"    @Bean\r\n" +
		"    ConstraintViolationProblemModule constraintViolationProblemModule() {\r\n" +
		"        return new ConstraintViolationProblemModule();\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "JacksonConfiguration";
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
