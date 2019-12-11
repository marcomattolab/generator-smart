package it.eng.generate.template.conf;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateDefaultProfileUtil extends AbstractTemplate {

	public TemplateDefaultProfileUtil(DataBase database) {
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
		"import org.springframework.boot.SpringApplication;\r\n" +
		"import org.springframework.core.env.Environment;\r\n" +
		"import java.util.*;\r\n\n" +
		"/**\r\n" +
		" * Utility class to load a Spring profile to be used as default\r\n" +
		" * when there is no <code>spring.profiles.active</code> set in the environment or as command line argument.\r\n" +
		" * If the value is not available in <code>application.yml</code> then <code>dev</code> profile will be used as default.\r\n" +
		" */\r\n" +
		"public final class "+getClassName()+" {\r\n\n" +
		"    private static final String SPRING_PROFILE_DEFAULT = \"spring.profiles.default\";\r\n" +
		"    private "+getClassName()+"() {\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Set a default to use when no profile is configured.\r\n" +
		"     *\r\n" +
		"     * @param app the Spring application\r\n" +
		"     */\r\n" +
		"    public static void addDefaultProfile(SpringApplication app) {\r\n" +
		"        Map<String, Object> defProperties = new HashMap<>();\r\n" +
		"        /*\r\n" +
		"        * The default profile to use when no other profiles are defined\r\n" +
		"        * This cannot be set in the <code>application.yml</code> file.\r\n" +
		"        * See https://github.com/spring-projects/spring-boot/issues/1219\r\n" +
		"        */\r\n" +
		"        defProperties.put(SPRING_PROFILE_DEFAULT, JHipsterConstants.SPRING_PROFILE_DEVELOPMENT);\r\n" +
		"        app.setDefaultProperties(defProperties);\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Get the profiles that are applied else get default profiles.\r\n" +
		"     *\r\n" +
		"     * @param env spring environment\r\n" +
		"     * @return profiles\r\n" +
		"     */\r\n" +
		"    public static String[] getActiveProfiles(Environment env) {\r\n" +
		"        String[] profiles = env.getActiveProfiles();\r\n" +
		"        if (profiles.length == 0) {\r\n" +
		"            return env.getDefaultProfiles();\r\n" +
		"        }\r\n" +
		"        return profiles;\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "DefaultProfileUtil";
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
