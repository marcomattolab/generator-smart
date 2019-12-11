package it.eng.generate.template.domain;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplatePersistentAudit extends AbstractTemplate {

	public TemplatePersistentAudit(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody(){
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder()+";\r\n\n" +
		"import javax.persistence.*;\r\n" +
		"import javax.validation.constraints.NotNull;\r\n" +
		"import java.io.Serializable;\r\n" +
		"import java.time.Instant;\r\n" +
		"import java.util.HashMap;\r\n" +
		"import java.util.Objects;\r\n" +
		"import java.util.Map;\r\n\n" +
		"/**\r\n" +
		" * Persist AuditEvent managed by the Spring Boot actuator.\r\n" +
		" *\r\n" +
		" * @see org.springframework.boot.actuate.audit.AuditEvent\r\n" +
		" */\r\n" +
		"@Entity\r\n" +
		"@Table(name = \"jhi_persistent_audit_event\")\r\n" +
		"public class "+getClassName()+" implements Serializable {\r\n\n" +
		"    private static final long serialVersionUID = 1L;\r\n\n" +
		"    @Id\r\n" +
		"    @GeneratedValue(strategy = GenerationType.IDENTITY)\r\n" +
		"    @Column(name = \"event_id\")\r\n" +
		"    private Long id;\r\n\n" +
		"    @NotNull\r\n" +
		"    @Column(nullable = false)\r\n" +
		"    private String principal;\r\n\n" +
		"    @Column(name = \"event_date\")\r\n" +
		"    private Instant auditEventDate;\r\n\n" +
		"    @Column(name = \"event_type\")\r\n" +
		"    private String auditEventType;\r\n\n" +
		"    @ElementCollection\r\n" +
		"    @MapKeyColumn(name = \"name\")\r\n" +
		"    @Column(name = \"value\")\r\n" +
		"    @CollectionTable(name = \"jhi_persistent_audit_evt_data\", joinColumns=@JoinColumn(name=\"event_id\"))\r\n" +
		"    private Map<String, String> data = new HashMap<>();\r\n\n" +
		"    public Long getId() {\r\n" +
		"        return id;\r\n" +
		"    }\r\n\n" +
		"    public void setId(Long id) {\r\n" +
		"        this.id = id;\r\n" +
		"    }\r\n\n" +
		"    public String getPrincipal() {\r\n" +
		"        return principal;\r\n" +
		"    }\r\n\n" +
		"    public void setPrincipal(String principal) {\r\n" +
		"        this.principal = principal;\r\n" +
		"    }\r\n\n" +
		"    public Instant getAuditEventDate() {\r\n" +
		"        return auditEventDate;\r\n" +
		"    }\r\n\n" +
		"    public void setAuditEventDate(Instant auditEventDate) {\r\n" +
		"        this.auditEventDate = auditEventDate;\r\n" +
		"    }\r\n\n" +
		"    public String getAuditEventType() {\r\n" +
		"        return auditEventType;\r\n" +
		"    }\r\n\n" +
		"    public void setAuditEventType(String auditEventType) {\r\n" +
		"        this.auditEventType = auditEventType;\r\n" +
		"    }\r\n\n" +
		"    public Map<String, String> getData() {\r\n" +
		"        return data;\r\n" +
		"    }\r\n\n" +
		"    public void setData(Map<String, String> data) {\r\n" +
		"        this.data = data;\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    public boolean equals(Object o) {\r\n" +
		"        if (this == o) {\r\n" +
		"            return true;\r\n" +
		"        }\r\n" +
		"        if (o == null || getClass() != o.getClass()) {\r\n" +
		"            return false;\r\n" +
		"        }\r\n" +
		"        "+getClassName()+" persistentAuditEvent = ("+getClassName()+") o;\r\n" +
		"        return !(persistentAuditEvent.getId() == null || getId() == null) && Objects.equals(getId(), persistentAuditEvent.getId());\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    public int hashCode() {\r\n" +
		"        return Objects.hashCode(getId());\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    public String toString() {\r\n" +
		"        return \""+getClassName()+"{\" +\r\n" +
		"            \"principal='\" + principal + '\\'' +\r\n" +
		"            \", auditEventDate=\" + auditEventDate +\r\n" +
		"            \", auditEventType='\" + auditEventType + '\\'' +\r\n" +
		"            '}';\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "PersistentAuditEvent";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcDomainFolder(),".","/");
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/java";
	}

}
