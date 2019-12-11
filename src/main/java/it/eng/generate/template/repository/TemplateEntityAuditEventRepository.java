package it.eng.generate.template.repository;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateEntityAuditEventRepository extends AbstractTemplate{
	
	public TemplateEntityAuditEventRepository(DataBase database) {
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
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		

		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcRepositoryFolder()+";\r\n\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder() + ".EntityAuditEvent;\r\n" +
		"import org.springframework.data.domain.Page;\r\n" +
		"import org.springframework.data.domain.Pageable;\r\n" +
		"import org.springframework.data.jpa.repository.JpaRepository;\r\n" +
		"import org.springframework.data.jpa.repository.Query;\r\n" +
		"import org.springframework.data.repository.query.Param;\r\n" +
		"import java.util.List;\r\n\n" +
		"/**\r\n" +
		" * Spring Data JPA repository for the EntityAuditEvent entity.\r\n" +
		" */\r\n" +
		"public interface EntityAuditEventRepository extends JpaRepository<EntityAuditEvent, Long> {\r\n\n" +
		"    List<EntityAuditEvent> findAllByEntityTypeAndEntityId(String entityType, Long entityId);\r\n\n" +
		"    @Query(\"SELECT max(a.commitVersion) FROM EntityAuditEvent a where a.entityType = :type and a.entityId = :entityId\")\r\n" +
		"    Integer findMaxCommitVersion(@Param(\"type\") String type, @Param(\"entityId\") Long entityId);\r\n\n" +
		"    @Query(\"SELECT DISTINCT (a.entityType) from EntityAuditEvent a\")\r\n" +
		"    List<String> findAllEntityTypes();\r\n\n" +
		"    Page<EntityAuditEvent> findAllByEntityType(String entityType, Pageable pageRequest);\r\n\n" +
		"    @Query(\"SELECT ae FROM EntityAuditEvent ae where ae.entityType = :type and ae.entityId = :entityId and \" +\r\n" +
		"        \"ae.commitVersion =(SELECT max(a.commitVersion) FROM EntityAuditEvent a where a.entityType = :type and \" +\r\n" +
		"        \"a.entityId = :entityId and a.commitVersion < :commitVersion)\")\r\n" +
		"    EntityAuditEvent findOneByEntityTypeAndEntityIdAndCommitVersion(@Param(\"type\") String type, @Param(\"entityId\")\r\n\n" +
		"    Long entityId, @Param(\"commitVersion\") Integer commitVersion);\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "EntityAuditEventRepository";
	}
	
	public String getSourceFolder() {
		return "src/main/java";
	}

}
