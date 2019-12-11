package it.eng.generate.template.domain;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateEntityAuditEvent extends AbstractTemplate {

	public TemplateEntityAuditEvent(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/

		String body = 
		 "package "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder() +";\r\n\n" +
		"import org.hibernate.annotations.Cache;\r\n" +
		"import org.hibernate.annotations.CacheConcurrencyStrategy;\r\n" +
		"import java.time.Instant;\r\n" +
		"import javax.persistence.*;\r\n" +
		"import javax.validation.constraints.NotNull;\r\n" +
		"import javax.validation.constraints.Size;\r\n" +
		"import java.io.Serializable;\r\n" +
		"import java.util.Objects;\r\n\n" +
		"@Entity\r\n" +
		"@Table(name = \"jhi_entity_audit_event\")\r\n" +
		"@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)\r\n" +
		"public class "+getClassName()+" implements Serializable{\r\n\n" +
		"    private static final long serialVersionUID = 1L;\r\n" +
		"    \r\n" +
		"    @Id\r\n" +
		"    @GeneratedValue(strategy = GenerationType.IDENTITY)\r\n" +
		"    private Long id;\r\n" +
		"    @NotNull\r\n" +
		"    @Column(name = \"entity_id\", nullable = false)\r\n" +
		"    private Long entityId;\r\n" +
		"    @NotNull\r\n" +
		"    @Size(max = 255)\r\n" +
		"    @Column(name = \"entity_type\", length = 255, nullable = false)\r\n" +
		"    private String entityType;\r\n" +
		"    @NotNull\r\n" +
		"    @Size(max=20)\r\n" +
		"    @Column(name = \"action\", length = 20, nullable = false)\r\n" +
		"    private String action;\r\n" +
		"    @Lob\r\n" +
		"    @Column(name = \"entity_value\")\r\n" +
		"    private String entityValue;\r\n" +
		"    @Column(name = \"commit_version\")\r\n" +
		"    private Integer commitVersion;\r\n" +
		"    @Size(max = 100)\r\n" +
		"    @Column(name = \"modified_by\", length = 100)\r\n" +
		"    private String modifiedBy;\r\n" +
		"    @NotNull\r\n" +
		"    @Column(name = \"modified_date\", nullable = false)\r\n" +
		"    private Instant modifiedDate;\r\n" +
		"    \r\n" +
		"    \r\n" +
		"    public Long getId() {\r\n" +
		"        return id;\r\n" +
		"    }\r\n" +
		"    public void setId(Long id) {\r\n" +
		"        this.id = id;\r\n" +
		"    }\r\n" +
		"    public Long getEntityId() {\r\n" +
		"        return entityId;\r\n" +
		"    }\r\n" +
		"    public void setEntityId(Long entityId) {\r\n" +
		"        this.entityId = entityId;\r\n" +
		"    }\r\n" +
		"    public String getEntityType() {\r\n" +
		"        return entityType;\r\n" +
		"    }\r\n" +
		"    public void setEntityType(String entityType) {\r\n" +
		"        this.entityType = entityType;\r\n" +
		"    }\r\n" +
		"    public String getAction() {\r\n" +
		"        return action;\r\n" +
		"    }\r\n" +
		"    public void setAction(String action) {\r\n" +
		"        this.action = action;\r\n" +
		"    }\r\n" +
		"    public String getEntityValue() {\r\n" +
		"        return entityValue;\r\n" +
		"    }\r\n" +
		"    public void setEntityValue(String entityValue) {\r\n" +
		"        this.entityValue = entityValue;\r\n" +
		"    }\r\n" +
		"    public Integer getCommitVersion() {\r\n" +
		"        return commitVersion;\r\n" +
		"    }\r\n" +
		"    public void setCommitVersion(Integer commitVersion) {\r\n" +
		"        this.commitVersion = commitVersion;\r\n" +
		"    }\r\n" +
		"    public String getModifiedBy() {\r\n" +
		"        return modifiedBy;\r\n" +
		"    }\r\n" +
		"    public void setModifiedBy(String modifiedBy) {\r\n" +
		"        this.modifiedBy = modifiedBy;\r\n" +
		"    }\r\n" +
		"    public Instant getModifiedDate() {\r\n" +
		"        return modifiedDate;\r\n" +
		"    }\r\n" +
		"    public void setModifiedDate(Instant modifiedDate) {\r\n" +
		"        this.modifiedDate = modifiedDate;\r\n" +
		"    }\r\n" +
		"    @Override\r\n" +
		"    public boolean equals(Object o) {\r\n" +
		"        if (this == o) {\r\n" +
		"            return true;\r\n" +
		"        }\r\n" +
		"        if (o == null || getClass() != o.getClass()) {\r\n" +
		"            return false;\r\n" +
		"        }\r\n" +
		"        "+getClassName()+" entityAuditEvent = ("+getClassName()+") o;\r\n" +
		"        return Objects.equals(id, entityAuditEvent.id);\r\n" +
		"    }\r\n" +
		"    @Override\r\n" +
		"    public int hashCode() {\r\n" +
		"        return Objects.hashCode(id);\r\n" +
		"    }\r\n" +
		"    @Override\r\n" +
		"    public String toString() {\r\n" +
		"        return \""+getClassName()+"{\" +\r\n" +
		"            \"id=\" + id +\r\n" +
		"            \", entityId='\" + entityId + \"'\" +\r\n" +
		"            \", entityType='\" + entityType + \"'\" +\r\n" +
		"            \", action='\" + action + \"'\" +\r\n" +
		"            \", entityValue='\" + entityValue + \"'\" +\r\n" +
		"            \", commitVersion='\" + commitVersion + \"'\" +\r\n" +
		"            \", modifiedBy='\" + modifiedBy + \"'\" +\r\n" +
		"            \", modifiedDate='\" + modifiedDate + \"'\" +\r\n" +
		"            '}';\r\n" +
		"    }\r\n" +
		"    \r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "EntityAuditEvent";
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
