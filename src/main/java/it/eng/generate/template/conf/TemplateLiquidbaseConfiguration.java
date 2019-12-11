package it.eng.generate.template.conf;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateLiquidbaseConfiguration extends AbstractTemplate {

	public TemplateLiquidbaseConfiguration(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcConfigFolder() +";\r\n\n" +
		"import javax.sql.DataSource;\r\n" +
		"import org.slf4j.Logger;\r\n" +
		"import org.slf4j.LoggerFactory;\r\n" +
		"import org.springframework.beans.factory.annotation.Qualifier;\r\n" +
		"import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;\r\n" +
		"import org.springframework.cache.CacheManager;\r\n" +
		"import org.springframework.context.annotation.Bean;\r\n" +
		"import org.springframework.context.annotation.Configuration;\r\n" +
		"import org.springframework.core.env.Environment;\r\n" +
		"import org.springframework.core.task.TaskExecutor;\r\n" +
		"import io.github.jhipster.config.JHipsterConstants;\r\n" +
		"import io.github.jhipster.config.liquibase.AsyncSpringLiquibase;\r\n" +
		"import liquibase.integration.spring.SpringLiquibase;\r\n\n" +
		"@Configuration\r\n" +
		"public class "+getClassName()+"  {\r\n" +
		"    private final Logger log = LoggerFactory.getLogger(" + getClassName() + ".class);\r\n" +
		"    private final Environment env;\r\n" +
		"    //private final CacheManager cacheManager;\r\n\n" +
		"    //public " + getClassName() + " (Environment env, CacheManager cacheManager) {\r\n" +
		"    //    this.env = env;\r\n" +
		"    //    this.cacheManager = cacheManager;\r\n" +
		"    //}\r\n\n" +
		"    public " + getClassName() + " (Environment env) {\r\n" +
		"        this.env = env;\r\n" +
		"    }\r\n\n" +
		"    @Bean\r\n" +
		"    public SpringLiquibase liquibase(@Qualifier(\"taskExecutor\") TaskExecutor taskExecutor,\r\n" +
		"            DataSource dataSource, LiquibaseProperties liquibaseProperties) {\r\n" +
		"        // Use liquibase.integration.spring.SpringLiquibase if you don't want Liquibase to start asynchronously\r\n" +
		"        SpringLiquibase liquibase = new AsyncSpringLiquibase(taskExecutor, env);\r\n" +
		"        liquibase.setDataSource(dataSource);\r\n" +
		"        liquibase.setChangeLog(\"classpath:config/liquibase/master.xml\");\r\n" +
		"        liquibase.setContexts(liquibaseProperties.getContexts());\r\n" +
		"        liquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());\r\n" +
		"        liquibase.setDropFirst(liquibaseProperties.isDropFirst());\r\n" +
		"        liquibase.setChangeLogParameters(liquibaseProperties.getParameters());\r\n" +
		"        if (env.acceptsProfiles(JHipsterConstants.SPRING_PROFILE_NO_LIQUIBASE)) {\r\n" +
		"            liquibase.setShouldRun(false);\r\n" +
		"        } else {\r\n" +
		"            liquibase.setShouldRun(liquibaseProperties.isEnabled());\r\n" +
		"            log.debug(\"Configuring Liquibase\");\r\n" +
		"        }\r\n" +
		"        return liquibase;\r\n" +
		"    }\r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "LiquibaseConfiguration";
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
