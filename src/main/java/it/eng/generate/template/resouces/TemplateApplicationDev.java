package it.eng.generate.template.resouces;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateApplicationDev extends AbstractResourceTemplate{
	
	public TemplateApplicationDev(DataBase database) {
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
		"# Spring Boot configuration for the \"dev\" profile.\r\n" +
		"#\r\n" +
		"# This configuration overrides the application.yml file.\r\n" +
		"#\r\n" +
		"# ===================================================================\r\n" +
		"# ===================================================================\r\n" +
		"# Standard Spring Boot properties.\r\n" +
		"# Full reference is available at:\r\n" +
		"# http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html\r\n" +
		"# ===================================================================\r\n" +
		"logging:\r\n" +
		"    level:\r\n" +
		"        ROOT: DEBUG\r\n" +
		"        io.github.jhipster: DEBUG\r\n" +
		"        it.eng: DEBUG\r\n" +
		"spring:\r\n" +
		"    profiles:\r\n" +
		"        active: dev\r\n" +
		"        include:\r\n" +
		"            - swagger\r\n" +
		"            # Uncomment to activate TLS for the dev profile\r\n" +
		"            #- tls\r\n" +
		"    devtools:\r\n" +
		"        restart:\r\n" +
		"            enabled: true\r\n" +
		"        livereload:\r\n" +
		"            enabled: false # we use Webpack dev server + BrowserSync for livereload\r\n" +
		"    jackson:\r\n" +
		"        serialization:\r\n" +
		"            indent-output: true\r\n" +
		"    datasource:\r\n" +
		"        type: com.zaxxer.hikari.HikariDataSource\r\n" +
		"        url: jdbc:mysql://localhost:3306/"+conf.getProjectName()+"?useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC\r\n" +
		"        username: "+conf.getUsername()+"\r\n" +
		"        password: "+conf.getPassword()+"\r\n" +
		"        hikari:\r\n" +
		"            auto-commit: false\r\n" +
		"            data-source-properties:\r\n" +
		"                cachePrepStmts: true\r\n" +
		"                prepStmtCacheSize: 250\r\n" +
		"                prepStmtCacheSqlLimit: 2048\r\n" +
		"                useServerPrepStmts: true\r\n" +
		"    jpa:\r\n" +
		"        database-platform: org.hibernate.dialect.MySQL5InnoDBDialect\r\n" +
		"        database: MYSQL\r\n" +
		"        show-sql: true\r\n" +
		"        properties:\r\n" +
		"            hibernate.id.new_generator_mappings: true\r\n" +
		"            hibernate.connection.provider_disables_autocommit: true\r\n" +
		"            hibernate.cache.use_second_level_cache: false\r\n" +
		"            hibernate.cache.use_query_cache: false\r\n" +
		"            hibernate.generate_statistics: true\r\n" +
		"    liquibase:\r\n" +
		"        contexts: dev\r\n" +
		"    mail:\r\n" +
		"        host: localhost\r\n" +
		"        port: 25\r\n" +
		"        username:\r\n" +
		"        password:\r\n" +
		"    messages:\r\n" +
		"        cache-duration: PT1S # 1 second, see the ISO 8601 standard\r\n" +
		"    thymeleaf:\r\n" +
		"        cache: false\r\n" +
		"server:\r\n" +
		"    port: 8080\r\n" +
		"# ===================================================================\r\n" +
		"# Specific properties\r\n" +
		"#\r\n" +
		"# Full reference is available at: Common-application-properties/\r\n" +
		"# ===================================================================\r\n" +
		"jhipster:\r\n" +
		"    http:\r\n" +
		"        version: V_1_1 # To use HTTP/2 you will need to activate TLS (see application-tls.yml)\r\n" +
		"    # CORS is only enabled by default with the \"dev\" profile, so BrowserSync can access the API\r\n" +
		"    cors:\r\n" +
		"        allowed-origins: \"*\"\r\n" +
		"        allowed-methods: \"*\"\r\n" +
		"        allowed-headers: \"*\"\r\n" +
		"        exposed-headers: \"Link,X-Total-Count\"\r\n" +
		"        allow-credentials: true\r\n" +
		"        max-age: 1800\r\n" +
		
//		"    security:\r\n" +
//		"        remember-me:\r\n" +
//		"            # security key (this key should be unique for your application, and kept secret)\r\n" +
//		"            key: b00e7c2b52803fb2a2cebcde07e02db45f1c50baf36a4f9d0847463554741a4f1f87bca18547760efff6d5061e3f79ce0886\r\n" +
		"    security:\r\n" +
		"       authentication:\r\n" +
		"         jwt:\r\n" +
		"            # This token must be encoded using Base64 and be at least 256 bits long (you can type `openssl rand -base64 64` on your command line to generate a 512 bits one)\r\n" +
		"            base64-secret: YmMwZjkwYjIxYWU0ZGUyZWJmOGVhOTk1NTI3MTJhNWNjZDk0NWFiODdlNzU3M2RhMWI2Nzk5Njg5ZWRlNjczOTg1ZWY5OTllMzU5YWRlNmQ5MjlmMzlkNzFhMDllY2QwMDI0ZTY0MzQyYzBhOGY4Mjc2MjMxY2Y5OTgyZGJjZTg=\r\n" +
		"            # Token is valid 24 hours\r\n" +
		"            token-validity-in-seconds: 86400\r\n" +
		"            token-validity-in-seconds-for-remember-me: 2592000\r\n"+


		"    mail: # specific mail property, for standard properties see MailProperties\r\n" +
		"        from: "+conf.getProjectName()+"@localhost\r\n" +
		"        base-url: http://127.0.0.1:8080\r\n" +
		"    metrics: # DropWizard Metrics configuration, used by MetricsConfiguration\r\n" +
		"        jmx:\r\n" +
		"            enabled: true\r\n" +
		"        prometheus:\r\n" +
		"            enabled: false #expose metrics via prometheus\r\n" +
		"        logs: # Reports Dropwizard metrics in the logs\r\n" +
		"            enabled: false\r\n" +
		"            report-frequency: 60 # in seconds\r\n" +
		"    logging:\r\n" +
		"        logstash: # Forward logs to logstash over a socket, used by LoggingConfiguration\r\n" +
		"            enabled: false\r\n" +
		"            host: localhost\r\n" +
		"            port: 5000\r\n" +
		"            queue-size: 512\r\n" +
		"# ===================================================================\r\n" +
		"# Application specific properties\r\n" +
		"# Add your own application properties here, see the ApplicationProperties class\r\n" +
		"# to have type-safe configuration \r\n" +
		"#\r\n" +
		"# ===================================================================\r\n" +
		"# application:\r\n";
		return body;
	}

	public String getClassName() {
		return "application-dev";
	}
	
	public String getSourceFolder() {
		return "src/main/resources";
	}

}
