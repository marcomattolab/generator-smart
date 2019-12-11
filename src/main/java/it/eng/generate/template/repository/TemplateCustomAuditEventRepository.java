package it.eng.generate.template.repository;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateCustomAuditEventRepository extends AbstractTemplate{
	private String CustomAuditEvent = "CustomAuditEvent";
	
	public TemplateCustomAuditEventRepository(DataBase database) {
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
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcRepositoryFolder()+";\r\n\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcConfigFolder() + ".Constants;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcConfigAuditFolder() + ".AuditEventConverter;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcDomainFolder() + ".PersistentAuditEvent;\r\n" +
		"import org.slf4j.Logger;\r\n" +
		"import org.slf4j.LoggerFactory;\r\n" +
		"import org.springframework.boot.actuate.audit.AuditEvent;\r\n" +
		"import org.springframework.boot.actuate.audit.AuditEventRepository;\r\n" +
		"import org.springframework.stereotype.Repository;\r\n" +
		"import org.springframework.transaction.annotation.Propagation;\r\n" +
		"import org.springframework.transaction.annotation.Transactional;\r\n" +
		"import java.time.Instant;\r\n" +
		"import java.util.*;\r\n\n"+
		"/**\r\n" +
		" * An implementation of Spring Boot's AuditEventRepository.\r\n" +
		" */\r\n" +
		"@Repository\r\n" +
		"public class "+getClassName()+" implements AuditEventRepository {\r\n\n" +
		"    private static final String AUTHORIZATION_FAILURE = \"AUTHORIZATION_FAILURE\";\r\n" +
		"    /**\r\n" +
		"     * Should be the same as in Liquibase migration.\r\n" +
		"     */\r\n" +
		"    protected static final int EVENT_DATA_COLUMN_MAX_LENGTH = 255;\r\n" +
		"    private final PersistenceAuditEventRepository persistenceAuditEventRepository;\r\n" +
		"    private final AuditEventConverter auditEventConverter;\r\n" +
		"    private final Logger log = LoggerFactory.getLogger(getClass());\r\n\n" +
		"    public "+getClassName()+"(PersistenceAuditEventRepository persistenceAuditEventRepository,\r\n" +
		"            AuditEventConverter auditEventConverter) {\r\n" +
		"        this.persistenceAuditEventRepository = persistenceAuditEventRepository;\r\n" +
		"        this.auditEventConverter = auditEventConverter;\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    public List<AuditEvent> find(String principal, Instant after, String type) {\r\n" +
		"        Iterable<PersistentAuditEvent> persistentAuditEvents =\r\n" +
		"            persistenceAuditEventRepository.findByPrincipalAndAuditEventDateAfterAndAuditEventType(principal, after, type);\r\n" +
		"        return auditEventConverter.convertToAuditEvent(persistentAuditEvents);\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    @Transactional(propagation = Propagation.REQUIRES_NEW)\r\n" +
		"    public void add(AuditEvent event) {\r\n" +
		"        if (!AUTHORIZATION_FAILURE.equals(event.getType()) &&\r\n" +
		"            !Constants.ANONYMOUS_USER.equals(event.getPrincipal())) {\r\n" +
		"            PersistentAuditEvent persistentAuditEvent = new PersistentAuditEvent();\r\n" +
		"            persistentAuditEvent.setPrincipal(event.getPrincipal());\r\n" +
		"            persistentAuditEvent.setAuditEventType(event.getType());\r\n" +
		"            persistentAuditEvent.setAuditEventDate(event.getTimestamp());\r\n" +
		"            Map<String, String> eventData = auditEventConverter.convertDataToStrings(event.getData());\r\n" +
		"            persistentAuditEvent.setData(truncate(eventData));\r\n" +
		"            persistenceAuditEventRepository.save(persistentAuditEvent);\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Truncate event data that might exceed column length.\r\n" +
		"     */\r\n" +
		"    private Map<String, String> truncate(Map<String, String> data) {\r\n" +
		"        Map<String, String> results = new HashMap<>();\r\n" +
		"        if (data != null) {\r\n" +
		"            for (Map.Entry<String, String> entry : data.entrySet()) {\r\n" +
		"                String value = entry.getValue();\r\n" +
		"                if (value != null) {\r\n" +
		"                    int length = value.length();\r\n" +
		"                    if (length > EVENT_DATA_COLUMN_MAX_LENGTH) {\r\n" +
		"                        value = value.substring(0, EVENT_DATA_COLUMN_MAX_LENGTH);\r\n" +
		"                        log.warn(\"Event data for {} too long ({}) has been truncated to {}. Consider increasing column width.\",\r\n" +
		"                                 entry.getKey(), length, EVENT_DATA_COLUMN_MAX_LENGTH);\r\n" +
		"                    }\r\n" +
		"                }\r\n" +
		"                results.put(entry.getKey(), value);\r\n" +
		"            }\r\n" +
		"        }\r\n" +
		"        return results;\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return Utils.getRepositoryClassName(CustomAuditEvent);
	}
	
	public String getSourceFolder() {
		return "src/main/java";
	}

}
