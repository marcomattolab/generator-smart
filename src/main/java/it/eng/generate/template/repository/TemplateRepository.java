package it.eng.generate.template.repository;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateRepository extends AbstractTemplate{

	public TemplateRepository(Table tabella) {
		super(tabella);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcRepositoryFolder(),".","/");
		return typeTemplate;
	}
	
	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcRepositoryFolder()+";\r\n\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder()+"."+Utils.getEntityName(tabella)+";\r\n" +
		"import org.springframework.data.jpa.repository.*;\r\n" +
		"import org.springframework.stereotype.Repository;\r\n\n" +
		"/**\r\n" +
		" * Spring Data repository for the " +Utils.getEntityName(tabella)+ " entity.\r\n" +
		" */\r\n" +
		"@SuppressWarnings(\"unused\")\r\n" +
		"@Repository\r\n" +
		"public interface "+getClassName() +" extends JpaRepository<"+Utils.getEntityName(tabella)+", Long>, JpaSpecificationExecutor<"+Utils.getEntityName(tabella)+"> {\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return Utils.getRepositoryClassName(tabella);
	}
	
	public String getSourceFolder() {
		return "src/main/java";
	}
	
}
