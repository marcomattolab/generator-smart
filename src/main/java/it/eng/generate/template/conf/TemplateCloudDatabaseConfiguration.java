package it.eng.generate.template.conf;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateCloudDatabaseConfiguration extends AbstractTemplate {

	public TemplateCloudDatabaseConfiguration(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcConfigFolder() +";\r\n\n" +
		"import io.github.jhipster.config.JHipsterConstants;\r\n" +
		"import org.slf4j.Logger;\r\n" +
		"import org.slf4j.LoggerFactory;\r\n" +
		"import org.springframework.cloud.config.java.AbstractCloudConfig;\r\n" +
		"import org.springframework.context.annotation.*;\r\n" +
		"import javax.sql.DataSource;\r\n" +
		"import org.springframework.boot.context.properties.ConfigurationProperties;\r\n\n" +
		"@Configuration\r\n" +
		"@Profile(JHipsterConstants.SPRING_PROFILE_CLOUD)\r\n" +
		"public class " + getClassName() + " extends AbstractCloudConfig {\r\n\n" +
		"    private final Logger log = LoggerFactory.getLogger("+ getClassName() + " .class);\r\n" +
		"    \r\n" +
		"    private final String CLOUD_CONFIGURATION_HIKARI_PREFIX = \"spring.datasource.hikari\";\r\n\n" +
		"    @Bean\r\n" +
		"    @ConfigurationProperties(CLOUD_CONFIGURATION_HIKARI_PREFIX)\r\n" +
		"    public DataSource dataSource() {\r\n" +
		"        log.info(\"Configuring JDBC datasource from a cloud provider\");\r\n" +
		"        return connectionFactory().dataSource();\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "CloudDatabaseConfiguration";
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
