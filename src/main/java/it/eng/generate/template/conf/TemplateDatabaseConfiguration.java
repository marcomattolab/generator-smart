package it.eng.generate.template.conf;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateDatabaseConfiguration extends AbstractTemplate {

	public TemplateDatabaseConfiguration(DataBase database) {
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
				"import org.slf4j.Logger;\r\n" +
				"import org.slf4j.LoggerFactory;\r\n" +
				"import io.github.jhipster.config.JHipsterConstants;\r\n" +
				"import org.springframework.context.annotation.Configuration;\r\n" +
				"import org.springframework.data.jpa.repository.config.EnableJpaAuditing;\r\n" +
				"import org.springframework.data.jpa.repository.config.EnableJpaRepositories;\r\n" +
				"import org.springframework.transaction.annotation.EnableTransactionManagement;\r\n\n" +
				"@Configuration\r\n" +
				"@EnableJpaRepositories(\""+conf.getPackageclass()+"."+conf.getSrcRepositoryFolder()+"\")\r\n" +
				"@EnableJpaAuditing(auditorAwareRef = \"springSecurityAuditorAware\")\r\n" +
				"@EnableTransactionManagement\r\n" +
				"public class "+getClassName()+" {\r\n\n" +
				"    private final Logger log = LoggerFactory.getLogger("+getClassName()+".class);\r\n" +
				"}\r\n";
		return body;
	}

	public String getClassName(){
		return "DatabaseConfiguration";
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
