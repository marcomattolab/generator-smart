package it.eng.generate.template.conf.audit;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateAsyncEntityAuditEventWriter extends AbstractTemplate {

	public TemplateAsyncEntityAuditEventWriter(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcConfigAuditFolder() +";\r\n\n" +
		"import com.fasterxml.jackson.databind.ObjectMapper;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder() +".AbstractAuditingEntity;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder() +".EntityAuditEvent;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcRepositoryFolder() +".EntityAuditEventRepository;\r\n" +
		"import org.slf4j.Logger;\r\n" +
		"import org.slf4j.LoggerFactory;\r\n" +
		"import org.springframework.scheduling.annotation.Async;\r\n" +
		"import org.springframework.stereotype.Component;\r\n" +
		"import java.io.IOException;\r\n" +
		"import java.lang.reflect.Field;\r\n\n" +
		"/**\r\n" +
		" * Async Entity Audit Event writer\r\n" +
		" * This is invoked by Hibernate entity listeners to write audit event for entitities\r\n" +
		" */\r\n" +
		"@Component\r\n" +
		"public class "+getClassName() +" {\r\n\n" +
		"    private final Logger log = LoggerFactory.getLogger("+getClassName() +".class);\r\n" +
		"    private final EntityAuditEventRepository auditingEntityRepository;\r\n" +
		"    private final ObjectMapper objectMapper; //Jackson object mapper\r\n" +
		"    public AsyncEntityAuditEventWriter(EntityAuditEventRepository auditingEntityRepository, ObjectMapper objectMapper) {\r\n" +
		"        this.auditingEntityRepository = auditingEntityRepository;\r\n" +
		"        this.objectMapper = objectMapper;\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Writes audit events to DB asynchronously in a new thread\r\n" +
		"     */\r\n" +
		"    @Async\r\n" +
		"    public void writeAuditEvent(Object target, EntityAuditAction action) {\r\n" +
		"        log.debug(\"-------------- Post {} audit  --------------\", action.value());\r\n" +
		"        try {\r\n" +
		"            EntityAuditEvent auditedEntity = prepareAuditEntity(target, action);\r\n" +
		"            if (auditedEntity != null) {\r\n" +
		"                auditingEntityRepository.save(auditedEntity);\r\n" +
		"            }\r\n" +
		"        } catch (Exception e) {\r\n" +
		"            log.error(\"Exception while persisting audit entity for {} error: {}\", target, e);\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Method to prepare auditing entity\r\n" +
		"     *\r\n" +
		"     * @param entity\r\n" +
		"     * @param action\r\n" +
		"     * @return\r\n" +
		"     */\r\n" +
		"    private EntityAuditEvent prepareAuditEntity(final Object entity, EntityAuditAction action) {\r\n" +
		"        EntityAuditEvent auditedEntity = new EntityAuditEvent();\r\n" +
		"        Class<?> entityClass = entity.getClass(); // Retrieve entity class with reflection\r\n" +
		"        auditedEntity.setAction(action.value());\r\n" +
		"        auditedEntity.setEntityType(entityClass.getName());\r\n" +
		"        Long entityId;\r\n" +
		"        String entityData;\r\n" +
		"        log.trace(\"Getting Entity Id and Content\");\r\n" +
		"        try {\r\n" +
		"            Field privateLongField = entityClass.getDeclaredField(\"id\");\r\n" +
		"            privateLongField.setAccessible(true);\r\n" +
		"            entityId = (Long) privateLongField.get(entity);\r\n" +
		"            privateLongField.setAccessible(false);\r\n" +
		"            entityData = objectMapper.writeValueAsString(entity);\r\n" +
		"        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException |\r\n" +
		"            IOException e) {\r\n" +
		"            log.error(\"Exception while getting entity ID and content {}\", e);\r\n" +
		"            // returning null as we dont want to raise an application exception here\r\n" +
		"            return null;\r\n" +
		"        }\r\n\n" +
		"        auditedEntity.setEntityId(entityId);\r\n" +
		"        auditedEntity.setEntityValue(entityData);\r\n" +
		"        final AbstractAuditingEntity abstractAuditEntity = (AbstractAuditingEntity) entity;\r\n" +
		"        if (EntityAuditAction.CREATE.equals(action)) {\r\n" +
		"            auditedEntity.setModifiedBy(abstractAuditEntity.getCreatedBy());\r\n" +
		"            auditedEntity.setModifiedDate(abstractAuditEntity.getCreatedDate());\r\n" +
		"            auditedEntity.setCommitVersion(1);\r\n" +
		"        } else {\r\n" +
		"            auditedEntity.setModifiedBy(abstractAuditEntity.getLastModifiedBy());\r\n" +
		"            auditedEntity.setModifiedDate(abstractAuditEntity.getLastModifiedDate());\r\n" +
		"            calculateVersion(auditedEntity);\r\n" +
		"        }\r\n" +
		"        log.trace(\"Audit Entity --> {} \", auditedEntity.toString());\r\n" +
		"        return auditedEntity;\r\n" +
		"    }\r\n\n" +
		"    private void calculateVersion(EntityAuditEvent auditedEntity) {\r\n" +
		"        log.trace(\"Version calculation. for update/remove\");\r\n" +
		"        Integer lastCommitVersion = auditingEntityRepository.findMaxCommitVersion(auditedEntity\r\n" +
		"            .getEntityType(), auditedEntity.getEntityId());\r\n" +
		"        log.trace(\"Last commit version of entity => {}\", lastCommitVersion);\r\n" +
		"        if(lastCommitVersion!=null && lastCommitVersion != 0){\r\n" +
		"            log.trace(\"Present. Adding version..\");\r\n" +
		"            auditedEntity.setCommitVersion(lastCommitVersion + 1);\r\n" +
		"        } else {\r\n" +
		"            log.trace(\"No entities.. Adding new version 1\");\r\n" +
		"            auditedEntity.setCommitVersion(1);\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "AsyncEntityAuditEventWriter";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcConfigAuditFolder(),".","/");
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/java";
	}

}
