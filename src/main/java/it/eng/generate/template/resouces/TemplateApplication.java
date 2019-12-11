package it.eng.generate.template.resouces;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateApplication extends AbstractResourceTemplate{
	
	public TemplateApplication(DataBase database) {
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
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = "# ===================================================================\r\n" +
		"# Spring Boot configuration.\r\n" +
		"#\r\n" +
		"# This configuration will be overridden by the Spring profile you use,\r\n" +
		"# for example application-dev.yml if you use the \"dev\" profile.\r\n" +
		"#\r\n" +
		"# ===================================================================\r\n" +
		"# ===================================================================\r\n" +
		"# Standard Spring Boot properties.\r\n" +
		"# Full reference is available at:\r\n" +
		"# http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html\r\n" +
		"# ===================================================================\r\n" +
		"management:\r\n" +
		"    endpoints:\r\n" +
		"        web:\r\n" +
		"            base-path: /management\r\n" +
		"            exposure:\r\n" +
		"                include: [\"configprops\", \"env\", \"health\", \"info\", \"threaddump\", \"logfile\" ]\r\n" +
		"    endpoint:\r\n" +
		"        health:\r\n" +
		"            show-details: when-authorized\r\n" +
		"    info:\r\n" +
		"        git:\r\n" +
		"            mode: full\r\n" +
		"    health:\r\n" +
		"        mail:\r\n" +
		"            enabled: false # When using the MailService, configure an SMTP server and set this to true\r\n" +
		"    metrics:\r\n" +
		"        enabled: false # http://micrometer.io/ is disabled by default, as we use http://metrics.dropwizard.io/ instead\r\n" +
		"spring:\r\n" +
		"    application:\r\n" +
		"        name: "+conf.getProjectName()+"\r\n" +
		"    profiles:\r\n" +
		"        # The commented value for `active` can be replaced with valid Spring profiles to load.\r\n" +
		"        # Otherwise, it will be filled in by maven when building the WAR file\r\n" +
		"        # Either way, it can be overridden by `--spring.profiles.active` value passed in the commandline or `-Dspring.profiles.active` set in `JAVA_OPTS`\r\n" +
		"        active: #spring.profiles.active#\r\n" +
		"    jpa:\r\n" +
		"        open-in-view: false\r\n" +
		"        properties:\r\n" +
		"            hibernate.jdbc.time_zone: UTC\r\n" +
		"        hibernate:\r\n" +
		"            ddl-auto: none\r\n" +
		"            naming:\r\n" +
		"                physical-strategy: org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy\r\n" +
		"                implicit-strategy: org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy\r\n" +
		"    messages:\r\n" +
		"        basename: i18n/messages\r\n" +
		"    mvc:\r\n" +
		"        favicon:\r\n" +
		"            enabled: false\r\n" +
		"    thymeleaf:\r\n" +
		"        mode: HTML\r\n" +
		"server:\r\n" +
		"    servlet:\r\n" +
		"        session:\r\n" +
		"            cookie:\r\n" +
		"                http-only: true\r\n" +
		"# Properties to be exposed on the /info management endpoint\r\n" +
		"info:\r\n" +
		"    # Comma separated list of profiles that will trigger the ribbon to show\r\n" +
		"    display-ribbon-on-profiles: \"dev\"\r\n" +
		"# ===================================================================\r\n" +
		"# Specific properties\r\n" +
		"#\r\n" +
		"# Full reference is available at: Common-application-properties/\r\n" +
		"# ===================================================================\r\n" +
		"jhipster:\r\n" +
		"    async:\r\n" +
		"        core-pool-size: 2\r\n" +
		"        max-pool-size: 50\r\n" +
		"        queue-capacity: 10000\r\n" +
		"    # By default CORS is disabled. Uncomment to enable.\r\n" +
		"    #cors:\r\n" +
		"        #allowed-origins: \"*\"\r\n" +
		"        #allowed-methods: \"*\"\r\n" +
		"        #allowed-headers: \"*\"\r\n" +
		"        #exposed-headers: \"Link,X-Total-Count\"\r\n" +
		"        #allow-credentials: true\r\n" +
		"        #max-age: 1800\r\n" +
		"    mail:\r\n" +
		"        from: "+conf.getProjectName()+"@localhost\r\n" +
		"    swagger:\r\n" +
		"        default-include-pattern: /api/.*\r\n" +
		"        title: "+conf.getProjectName()+" API\r\n" +
		"        description: "+conf.getProjectName()+" API documentation\r\n" +
		"        version: 0.0.1\r\n" +
		"        terms-of-service-url:\r\n" +
		"        contact-name:\r\n" +
		"        contact-url:\r\n" +
		"        contact-email:\r\n" +
		"        license:\r\n" +
		"        license-url:\r\n" +
		"# ===================================================================\r\n" +
		"# Application specific properties\r\n" +
		"# Add your own application properties here, see the ApplicationProperties class\r\n" +
		"# to have type-safe configuration\r\n" +
		"#\r\n" +
		"# ===================================================================\r\n" +
		"# application:\r\n";
		return body;
	}

	public String getClassName() {
		return "application";
	}
	
	public String getSourceFolder() {
		return "src/main/resources";
	}

}
