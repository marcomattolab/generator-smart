package it.eng.generate.template.repository;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplatePersistenceAuditEventRepository extends AbstractTemplate{
	private String PersistenceAuditEvent = "PersistenceAuditEvent";

	public TemplatePersistenceAuditEventRepository(DataBase database) {
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
		"import "+conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".PersistentAuditEvent;\r\n" +
		"import org.springframework.data.domain.Page;\r\n" +
		"import org.springframework.data.domain.Pageable;\r\n" +
		"import org.springframework.data.jpa.repository.JpaRepository;\r\n" +
		"import java.time.Instant;\r\n" +
		"import java.util.List;\r\n\n" +
		"/**\r\n" +
		" * Spring Data JPA repository for the "+PersistenceAuditEvent+" entity.\r\n" +
		" */\r\n" +
		"public interface "+getClassName()+" extends JpaRepository<PersistentAuditEvent, Long> {\r\n\n" +
		"    List<PersistentAuditEvent> findByPrincipal(String principal);\r\n\n" +
		"    List<PersistentAuditEvent> findByAuditEventDateAfter(Instant after);\r\n\n" +
		"    List<PersistentAuditEvent> findByPrincipalAndAuditEventDateAfter(String principal, Instant after);\r\n\n" +
		"    List<PersistentAuditEvent> findByPrincipalAndAuditEventDateAfterAndAuditEventType(String principal, Instant after, String type);\r\n\n" +
		"    Page<PersistentAuditEvent> findAllByAuditEventDateBetween(Instant fromDate, Instant toDate, Pageable pageable);\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return Utils.getRepositoryClassName(PersistenceAuditEvent);
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
