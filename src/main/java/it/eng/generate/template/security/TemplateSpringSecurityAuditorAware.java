package it.eng.generate.template.security;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateSpringSecurityAuditorAware extends AbstractTemplate{

	public TemplateSpringSecurityAuditorAware(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcSecurityFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcSecurityFolder() +";\r\n\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcConfigFolder() +".Constants;\r\n" +
		"import java.util.Optional;\r\n" +
		"import org.springframework.data.domain.AuditorAware;\r\n" +
		"import org.springframework.stereotype.Component;\r\n\n" +
		"/**\r\n" +
		" * Implementation of AuditorAware based on Spring Security.\r\n" +
		" */\r\n" +
		"@Component\r\n" +
		"public class "+getClassName()+" implements AuditorAware<String> {\r\n\n" +
		"    @Override\r\n" +
		"    public Optional<String> getCurrentAuditor() {\r\n" +
		"        return Optional.of(SecurityUtils.getCurrentUserLogin().orElse(Constants.SYSTEM_ACCOUNT));\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "SpringSecurityAuditorAware";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
