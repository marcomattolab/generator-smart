package it.eng.generate.template.service;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateAuditEventService extends AbstractTemplate{

	public TemplateAuditEventService(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcServiceFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcServiceFolder()+";\r\n\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcConfigAuditFolder()+".AuditEventConverter;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcRepositoryFolder()+".PersistenceAuditEventRepository;\r\n" +
		"import org.springframework.boot.actuate.audit.AuditEvent;\r\n" +
		"import org.springframework.data.domain.Page;\r\n" +
		"import org.springframework.data.domain.Pageable;\r\n" +
		"import org.springframework.stereotype.Service;\r\n" +
		"import org.springframework.transaction.annotation.Transactional;\r\n" +
		"import java.time.Instant;\r\n" +
		"import java.util.Optional;\r\n\n" +
		"/**\r\n" +
		" * Service for managing audit events.\r\n" +
		" * <p>\r\n" +
		" * This is the default implementation to support SpringBoot Actuator "+getClassName()+"Repository\r\n" +
		" */\r\n" +
		"@Service\r\n" +
		"@Transactional\r\n" +
		"public class "+getClassName()+" {\r\n" +
		"    private final PersistenceAuditEventRepository persistenceAuditEventRepository;\r\n" +
		"    private final AuditEventConverter auditEventConverter;\r\n\n" +
		"    public "+getClassName()+"(\r\n" +
		"        PersistenceAuditEventRepository persistenceAuditEventRepository,\r\n" +
		"        AuditEventConverter auditEventConverter) {\r\n" +
		"        this.persistenceAuditEventRepository = persistenceAuditEventRepository;\r\n" +
		"        this.auditEventConverter = auditEventConverter;\r\n" +
		"    }\r\n\n" +
		"    public Page<AuditEvent> findAll(Pageable pageable) {\r\n" +
		"        return persistenceAuditEventRepository.findAll(pageable)\r\n" +
		"            .map(auditEventConverter::convertToAuditEvent);\r\n" +
		"    }\r\n\n" +
		"    public Page<AuditEvent> findByDates(Instant fromDate, Instant toDate, Pageable pageable) {\r\n" +
		"        return persistenceAuditEventRepository.findAllByAuditEventDateBetween(fromDate, toDate, pageable)\r\n" +
		"            .map(auditEventConverter::convertToAuditEvent);\r\n" +
		"    }\r\n\n" +
		"    public Optional<AuditEvent> find(Long id) {\r\n" +
		"        return Optional.ofNullable(persistenceAuditEventRepository.findById(id))\r\n" +
		"            .filter(Optional::isPresent)\r\n" +
		"            .map(Optional::get)\r\n" +
		"            .map(auditEventConverter::convertToAuditEvent);\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "AuditEventService";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
