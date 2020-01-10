package it.eng.generate.template.resouces;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateApplicationProd extends AbstractResourceTemplate{

	public TemplateApplicationProd(DataBase database) {
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
				"# Spring Boot configuration for the \"prod\" profile.\r\n" +
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
				"        ROOT: INFO\r\n" +
				"        it.eng: INFO\r\n" +
				"        io.github.jhipster: INFO\r\n" +
				"spring:\r\n" +
				"    devtools:\r\n" +
				"        restart:\r\n" +
				"            enabled: false\r\n" +
				"        livereload:\r\n" +
				"            enabled: false\r\n" +
				"    datasource:\r\n" +
				"        type: com.zaxxer.hikari.HikariDataSource\r\n" +
				"        url: jdbc:mysql://localhost:3306/"+conf.getProjectName()+"?useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false\r\n" +
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
				"        show-sql: false\r\n" +
				"        properties:\r\n" +
				"            hibernate.id.new_generator_mappings: true\r\n" +
				"            hibernate.connection.provider_disables_autocommit: true\r\n" +
				"            hibernate.cache.use_second_level_cache: false\r\n" +
				"            hibernate.cache.use_query_cache: false\r\n" +
				"            hibernate.generate_statistics: false\r\n" +
				"    liquibase:\r\n" +
				"        contexts: prod\r\n" +
				"    mail:\r\n" +
				"        host: localhost\r\n" +
				"        port: 25\r\n" +
				"        username:\r\n" +
				"        password:\r\n" +
				"    thymeleaf:\r\n" +
				"        cache: true\r\n" +
				"# ===================================================================\r\n" +
				"# To enable TLS in production, generate a certificate using:\r\n" +
				"# keytool -genkey -alias "+conf.getProjectName()+" -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650\r\n" +
				"#\r\n" +
				"# You can also use Let's Encrypt:\r\n" +
				"# https://maximilian-boehm.com/hp2121/Create-a-Java-Keystore-JKS-from-Let-s-Encrypt-Certificates.htm\r\n" +
				"#\r\n" +
				"# Then, modify the server.ssl properties so your \"server\" configuration looks like:\r\n" +
				"#\r\n" +
				"# server:\r\n" +
				"#    port: 443\r\n" +
				"#    ssl:\r\n" +
				"#        key-store: classpath:config/tls/keystore.p12\r\n" +
				"#        key-store-password: password\r\n" +
				"#        key-store-type: PKCS12\r\n" +
				"#        key-alias: "+conf.getProjectName()+"\r\n" +
				"#        # The ciphers suite enforce the security by deactivating some old and deprecated SSL cipher, this list was tested against SSL Labs (https://www.ssllabs.com/ssltest/)\r\n" +
				"#        ciphers: TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 ,TLS_DHE_RSA_WITH_AES_128_GCM_SHA256 ,TLS_DHE_RSA_WITH_AES_256_GCM_SHA384 ,TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256,TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384,TLS_DHE_RSA_WITH_AES_128_CBC_SHA256,TLS_DHE_RSA_WITH_AES_128_CBC_SHA,TLS_DHE_RSA_WITH_AES_256_CBC_SHA256,TLS_DHE_RSA_WITH_AES_256_CBC_SHA,TLS_RSA_WITH_AES_128_GCM_SHA256,TLS_RSA_WITH_AES_256_GCM_SHA384,TLS_RSA_WITH_AES_128_CBC_SHA256,TLS_RSA_WITH_AES_256_CBC_SHA256,TLS_RSA_WITH_AES_128_CBC_SHA,TLS_RSA_WITH_AES_256_CBC_SHA,TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA,TLS_RSA_WITH_CAMELLIA_256_CBC_SHA,TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA,TLS_RSA_WITH_CAMELLIA_128_CBC_SHA\r\n" +
				"# ===================================================================\r\n" +
				"server:\r\n" +
				"    port: 8080\r\n" +
				"    compression:\r\n" +
				"        enabled: true\r\n" +
				"        mime-types: text/html,text/xml,text/plain,text/css, application/javascript, application/json\r\n" +
				"        min-response-size: 1024\r\n" +
				"# ===================================================================\r\n" +
				"# Specific properties\r\n" +
				"#\r\n" +
				"# Full reference is available at: Common-application-properties/\r\n" +
				"# ===================================================================\r\n" +
				"jhipster:\r\n" +
				"    http:\r\n" +
				"        version: V_1_1 # To use HTTP/2 you will need SSL support (see above the \"server.ssl\" configuration)\r\n" +
				"        cache: # Used by the CachingHttpHeadersFilter\r\n" +
				"            timeToLiveInDays: 1461\r\n" +
				
//				"    security:\r\n" +
//				"        remember-me:\r\n" +
//				"            # security key (this key should be unique for your application, and kept secret)\r\n" +
//				"            key: b00e7c2b52803fb2a2cebcde07e02db45f1c50baf36a4f9d0847463554741a4f1f87bca18547760efff6d5061e3f79ce0886\r\n" +
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
				"        base-url: http://my-server-url-to-change # Modify according to your server's URL\r\n" +
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
				"# to have type-safe configuration\r\n" +
				"#\r\n" +
				"# ===================================================================\r\n" +
				"# application:\r\n";

		return body;
	}

	public String getClassName() {
		return "application-prod";
	}

	public String getSourceFolder() {
		return "src/main/resources";
	}

}
