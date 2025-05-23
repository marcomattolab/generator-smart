package it.eng.generate.project;

import org.springframework.scheduling.annotation.EnableScheduling;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class ApplicationApp extends AbstractTemplate {

	public ApplicationApp(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "java";
	}

	/**
	 * This template has been generated by https://www.buildmystring.com
	 */
	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = 
		"package "+ conf.getPackageclass()   +";\r\n\n" +
		"import "+conf.getPackageclass() + "." + conf.getSrcConfigFolder() + ".ApplicationProperties;\r\n" +
		"import "+conf.getPackageclass() + "." + conf.getSrcConfigFolder() + ".DefaultProfileUtil;\r\n" +
		"import org.apache.commons.lang3.StringUtils;\r\n" +
		"import org.slf4j.Logger;\r\n" +
		"import org.slf4j.LoggerFactory;\r\n" +
		"import org.springframework.boot.SpringApplication;\r\n" +
		"import org.springframework.boot.autoconfigure.SpringBootApplication;\r\n" +
		"import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;\r\n" +
		"import org.springframework.boot.context.properties.EnableConfigurationProperties;\r\n" +
		"import org.springframework.core.env.Environment;\r\n" +
		"import io.github.jhipster.config.JHipsterConstants;\r\n"+
		"import org.springframework.scheduling.annotation.EnableScheduling;\r\n" + 
		"import javax.annotation.PostConstruct;\r\n" +
		"import java.net.InetAddress;\r\n" +
		"import java.net.UnknownHostException;\r\n" +
		"import java.util.Arrays;\r\n" +
		"import java.util.Collection;\r\n\n" +
		"@EnableScheduling\r\n" +
		"@SpringBootApplication\r\n" +
		"@EnableConfigurationProperties({LiquibaseProperties.class, ApplicationProperties.class})\r\n" +
		"public class "+ getClassName() +" {\r\n\n" +
		"    private static final Logger log = LoggerFactory.getLogger(" + getClassName() + ".class);\r\n" +
		"    private final Environment env;\r\n\n" +
		"    public  "+ getClassName() +"(Environment env) {\r\n" +
		"        this.env = env;\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Initializes "+conf.getProjectName()+".\r\n" +
		"     * <p>\r\n" +
		"     * Spring profiles can be configured with a program argument --spring.profiles.active=your-active-profile\r\n" +
		"     * <p>\r\n" +
		//"     * You can find more information on how profiles work with JHipster on <a href=\"https://www.jhipster.tech/profiles/\">https://www.jhipster.tech/profiles/</a>.\r\n" +
		"     */\r\n\n" +
		"    @PostConstruct\r\n" +
		"    public void initApplication() {\r\n\n" +
		"        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());\r\n" +
		"        if (activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_PRODUCTION)) {\r\n" +
		"            log.error(\"You have misconfigured your application! It should not run \" +\r\n" +
		"                \"with both the 'dev' and 'prod' profiles at the same time.\");\r\n" +
		"        }\r\n" +
		"        if (activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_CLOUD)) {\r\n" +
		"            log.error(\"You have misconfigured your application! It should not \" +\r\n" +
		"                \"run with both the 'dev' and 'cloud' profiles at the same time.\");\r\n" +
		"        }\r\n" +
		"    }\r\n\n\n" +
		"    /**\r\n" +
		"     * Main method, used to run the application.\r\n" +
		"     *\r\n" +
		"     * @param args the command line arguments\r\n" +
		"     */\r\n" +
		"    public static void main(String[] args) {\r\n" +
		"        SpringApplication app = new SpringApplication("+ getClassName()+".class);\r\n" +
		"        DefaultProfileUtil.addDefaultProfile(app);\r\n" +
		"        Environment env = app.run(args).getEnvironment();\r\n" +
		"        logApplicationStartup(env);\r\n" +
		"    }\r\n\n" +
		"    private static void logApplicationStartup(Environment env) {\r\n" +
		"        String protocol = \"http\";\r\n" +
		"        if (env.getProperty(\"server.ssl.key-store\") != null) {\r\n" +
		"            protocol = \"https\";\r\n" +
		"        }\r\n" +
		"        String serverPort = env.getProperty(\"server.port\");\r\n" +
		"        String contextPath = env.getProperty(\"server.servlet.context-path\");\r\n" +
		"        if (StringUtils.isBlank(contextPath)) {\r\n" +
		"            contextPath = \"/\";\r\n" +
		"        }\r\n" +
		"        String hostAddress = \"localhost\";\r\n" +
		"        try {\r\n" +
		"            hostAddress = InetAddress.getLocalHost().getHostAddress();\r\n" +
		"        } catch (UnknownHostException e) {\r\n" +
		"            log.warn(\"The host name could not be determined, using `localhost` as fallback\");\r\n" +
		"        }\r\n\n" +
		"        log.info(\"\\n----------------------------------------------------------\\n\\t\" +\r\n" +
		"                \"Application '{}' is running! Access URLs:\\n\\t\" +\r\n" +
		"                \"Local: \\t\\t{}://localhost:{}{}\\n\\t\" +\r\n" +
		"                \"External: \\t{}://{}:{}{}\\n\\t\" +\r\n" +
		"                \"Profile(s): \\t{}\\n----------------------------------------------------------\",\r\n" +
		"            env.getProperty(\"spring.application.name\"),\r\n" +
		"            protocol,\r\n" +
		"            serverPort,\r\n" +
		"            contextPath,\r\n" +
		"            protocol,\r\n" +
		"            hostAddress,\r\n" +
		"            serverPort,\r\n" +
		"            contextPath,\r\n" +
		"            env.getActiveProfiles());\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		return Utils.getClassNameCamelCase(conf.getProjectName()) + conf.getApp() ;
	}

	public String getTypeTemplate() {
		return "";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}
}
