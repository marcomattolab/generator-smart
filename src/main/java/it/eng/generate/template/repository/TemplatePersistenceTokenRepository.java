package it.eng.generate.template.repository;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplatePersistenceTokenRepository extends AbstractTemplate{
	private String PersistentToken = "PersistentToken";

	public TemplatePersistenceTokenRepository(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcRepositoryFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcRepositoryFolder()+";\r\n\n" +
		"import "+conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".PersistentToken;\r\n" +
		"import "+conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".User;\r\n" +
		"import java.time.LocalDate;\r\n" +
		"import org.springframework.data.jpa.repository.JpaRepository;\r\n" +
		"import java.util.List;\r\n\n" +
		"/**\r\n" +
		" * Spring Data JPA repository for the "+PersistentToken+" entity.\r\n" +
		" */\r\n" +
		"public interface "+getClassName() +" extends JpaRepository<"+PersistentToken+", String> {\r\n\n" +
		"    List<"+PersistentToken+"> findByUser(User user);\r\n\n" +
		"    List<"+PersistentToken+"> findByTokenDateBefore(LocalDate localDate);\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return Utils.getRepositoryClassName(PersistentToken);
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
