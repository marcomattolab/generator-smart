package it.eng.generate.template.test;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateApplicationYamlTest extends AbstractResourceTemplate{
	
	public TemplateApplicationYamlTest(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getResConfigFolder(),".","/");
		return typeTemplate;
	}
	
	public String getTypeFile() {
		return "yml";
	}

	public String getBody() {
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();

		String appname = conf.getProjectName().toLowerCase();

		String body = 
		"# ===================================================================\r\n" + 
		"# Spring Boot configuration.\r\n" + 
		"#\r\n" + 
		"# This configuration is used for unit/integration tests.\r\n" + 
		"#\r\n" + 
		"# More information on profiles: https://www.jhipster.tech/profiles/\r\n" + 
		"# More information on configuration properties: https://www.jhipster.tech/common-application-properties/\r\n" + 
		"# ===================================================================\r\n" + 
		"\r\n" + 
		"# ===================================================================\r\n" + 
		"# Standard Spring Boot properties.\r\n" + 
		"# Full reference is available at:\r\n" + 
		"# http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html\r\n" + 
		"# ===================================================================\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"spring:\r\n" + 
		"    application:\r\n" + 
		"        name: "+appname+"\r\n" + 
		"    datasource:\r\n" + 
		"        type: com.zaxxer.hikari.HikariDataSource\r\n" + 
		"        url: jdbc:h2:mem:"+appname+";DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE\r\n" + 
		"        name:\r\n" + 
		"        username:\r\n" + 
		"        password:\r\n" + 
		"        hikari:\r\n" + 
		"            auto-commit: false\r\n" + 
		"    jpa:\r\n" + 
		"        database-platform: io.github.jhipster.domain.util.FixedH2Dialect\r\n" + 
		"        database: H2\r\n" + 
		"        open-in-view: false\r\n" + 
		"        show-sql: false\r\n" + 
		"        hibernate:\r\n" + 
		"            ddl-auto: none\r\n" + 
		"            naming:\r\n" + 
		"                physical-strategy: org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy\r\n" + 
		"                implicit-strategy: org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy\r\n" + 
		"        properties:\r\n" + 
		"            hibernate.id.new_generator_mappings: true\r\n" + 
		"            hibernate.connection.provider_disables_autocommit: true\r\n" + 
		"            hibernate.cache.use_second_level_cache: false\r\n" + 
		"            hibernate.cache.use_query_cache: false\r\n" + 
		"            hibernate.generate_statistics: true\r\n" + 
		"            hibernate.hbm2ddl.auto: validate\r\n" + 
		"            hibernate.jdbc.time_zone: UTC\r\n" + 
		"    liquibase:\r\n" + 
		"        contexts: test\r\n" + 
		"    mail:\r\n" + 
		"        host: localhost\r\n" + 
		"    messages:\r\n" + 
		"        basename: i18n/messages\r\n" + 
		"    mvc:\r\n" + 
		"        favicon:\r\n" + 
		"            enabled: false\r\n" + 
		"    thymeleaf:\r\n" + 
		"        mode: HTML\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"server:\r\n" + 
		"    port: 10344\r\n" + 
		"    address: localhost\r\n" + 
		"\r\n" + 
		"# ===================================================================\r\n" + 
		"# JH specific properties\r\n" + 
		"#\r\n" + 
		"# Full reference is available at: https://www.jhipster.tech/common-application-properties/\r\n" + 
		"# ===================================================================\r\n" + 
		"\r\n" + 
		"jhipster:\r\n" + 
		"    async:\r\n" + 
		"        core-pool-size: 1\r\n" + 
		"        max-pool-size: 50\r\n" + 
		"        queue-capacity: 10000\r\n" + 
		"    # To test logstash appender\r\n" + 
		"    logging:\r\n" + 
		"        logstash:\r\n" + 
		"            enabled: true\r\n" + 
		"            host: localhost\r\n" + 
		"            port: 5000\r\n" + 
		"            queue-size: 512\r\n" + 
		"    mail:\r\n" + 
		"        from: test@localhost\r\n" + 
		"        base-url: http://127.0.0.1:8080\r\n" + 
		"    security:\r\n" + 
		"        authentication:\r\n" + 
		"            jwt:\r\n" + 
		"                # This token must be encoded using Base64 (you can type `echo 'secret-key'|base64` on your command line)\r\n" + 
		"                base64-secret: NmQzZTkyYjllZmMzM2Y2ZGFmZWJjNGE3ZGE0ZGNjZWJhZTE0ODNiODQ3ZGZlZDQ3MWUxNThhNjFhZTQ3NWEwMDdiODI2ZTdhOTNkYmNiMzIzOGQyZTUzNDY5ZTgzNWJjOWMzZDhkN2Y5Y2Q1MmU2ZDMzNTRkZjZhN2EyNzNkMzI=\r\n" + 
		"                # Token is valid 24 hours\r\n" + 
		"                token-validity-in-seconds: 86400\r\n" + 
		"    metrics: # DropWizard Metrics configuration, used by MetricsConfiguration\r\n" + 
		"        jmx.enabled: true\r\n" + 
		"        logs: # Reports Dropwizard metrics in the logs\r\n" + 
		"            enabled: true\r\n" + 
		"            report-frequency: 60 # in seconds\r\n" + 
		"\r\n" + 
		"# ===================================================================\r\n" + 
		"# Application specific properties\r\n" + 
		"# Add your own application properties here, see the ApplicationProperties class\r\n" + 
		"# to have type-safe configuration, like in the JHipsterProperties above\r\n" + 
		"#\r\n" + 
		"# More documentation is available at:\r\n" + 
		"# https://www.jhipster.tech/common-application-properties/\r\n" + 
		"# ===================================================================\r\n" + 
		"\r\n" + 
		"# application:\r\n" + 
		"";
		return body;
	}

	public String getClassName() {
		return "application";
	}
	
	public String getSourceFolder() {
		return "src/test/resources";
	}

}
