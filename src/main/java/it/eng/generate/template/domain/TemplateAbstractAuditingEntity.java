package it.eng.generate.template.domain;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateAbstractAuditingEntity extends AbstractTemplate {

	public TemplateAbstractAuditingEntity(DataBase database) {
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
		//"import com.fasterxml.jackson.annotation.JsonIgnore;\r\n\n" +
		"import org.hibernate.envers.Audited;\r\n" +
		"import org.springframework.data.annotation.CreatedBy;\r\n" +
		"import org.springframework.data.annotation.CreatedDate;\r\n" +
		"import org.springframework.data.annotation.LastModifiedBy;\r\n" +
		"import org.springframework.data.annotation.LastModifiedDate;\r\n" +
		"import org.springframework.data.jpa.domain.support.AuditingEntityListener;\r\n\n" +
		"import java.io.Serializable;\r\n" +
		"import java.time.Instant;\r\n" +
		"import javax.persistence.Column;\r\n" +
		"import javax.persistence.EntityListeners;\r\n" +
		"import javax.persistence.MappedSuperclass;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcConfigAuditFolder() + ".EntityAuditEventListener;\r\n\n"+  //Audit
		"/**\r\n" +
		" * Base abstract class for entities which will hold definitions for created, last modified by and created,\r\n" +
		" * last modified by date.\r\n" +
		" */\r\n" +
		"@MappedSuperclass\r\n" +
		"@Audited\r\n" +
		//"@EntityListeners(AuditingEntityListener.class)\r\n" +
		"@EntityListeners({AuditingEntityListener.class, EntityAuditEventListener.class})\r\n"+ //Audit
		"public abstract class  "+ getClassName() + " implements Serializable {\r\n\n" +
		"    private static final long serialVersionUID = 1L;\r\n\n" +
		"    @CreatedBy\r\n" +
		"    @Column(name = \"created_by\", nullable = false, length = 50, updatable = false)\r\n" +
		//"    @JsonIgnore\r\n" +
		"    private String createdBy;\r\n\n" +
		"    @CreatedDate\r\n" +
		"    @Column(name = \"created_date\", updatable = false)\r\n" +
		//"    @JsonIgnore\r\n" +
		"    private Instant createdDate = Instant.now();\r\n\n" +
		"    @LastModifiedBy\r\n" +
		"    @Column(name = \"last_modified_by\", length = 50)\r\n" +
		//"    @JsonIgnore\r\n" +
		"    private String lastModifiedBy;\r\n\n" +
		"    @LastModifiedDate\r\n" +
		"    @Column(name = \"last_modified_date\")\r\n" +
		//"    @JsonIgnore\r\n" +
		"    private Instant lastModifiedDate = Instant.now();\r\n\n" +
		"    public String getCreatedBy() {\r\n" +
		"        return createdBy;\r\n" +
		"    }\r\n\n" +
		"    public void setCreatedBy(String createdBy) {\r\n" +
		"        this.createdBy = createdBy;\r\n" +
		"    }\r\n\n" +
		"    public Instant getCreatedDate() {\r\n" +
		"        return createdDate;\r\n" +
		"    }\r\n\n" +
		"    public void setCreatedDate(Instant createdDate) {\r\n" +
		"        this.createdDate = createdDate;\r\n" +
		"    }\r\n\n" +
		"    public String getLastModifiedBy() {\r\n" +
		"        return lastModifiedBy;\r\n" +
		"    }\r\n\n" +
		"    public void setLastModifiedBy(String lastModifiedBy) {\r\n" +
		"        this.lastModifiedBy = lastModifiedBy;\r\n" +
		"    }\r\n\n" +
		"    public Instant getLastModifiedDate() {\r\n" +
		"        return lastModifiedDate;\r\n" +
		"    }\r\n\n" +
		"    public void setLastModifiedDate(Instant lastModifiedDate) {\r\n" +
		"        this.lastModifiedDate = lastModifiedDate;\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "AbstractAuditingEntity";
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
