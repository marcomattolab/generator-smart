package it.eng.generate.template.service;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateAbstractAuditingDTO extends AbstractTemplate{

	public TemplateAbstractAuditingDTO(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcServiceDtoFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcServiceDtoFolder()+";\r\n\n" +
		"import java.io.Serializable;\r\n" +
		"import java.time.Instant;\r\n" +
		"import org.springframework.data.annotation.ReadOnlyProperty;\r\n\n" +
		"/**\r\n" +
		" * Base abstract class for DTO which will hold definitions for created, last modified by and created,\r\n" +
		" * last modified by date.\r\n" +
		" */\r\n" +
		"public abstract class AbstractAuditingDTO implements Serializable {\r\n\n" +
		"    private static final long serialVersionUID = 1L;\r\n" +
		"    @ReadOnlyProperty\r\n" +
		"    private String createdBy;\r\n" +
		"    @ReadOnlyProperty\r\n" +
		"    private Instant createdDate = Instant.now();\r\n" +
		"    private String lastModifiedBy;\r\n" +
		"    private Instant lastModifiedDate = Instant.now();\r\n" +
		"    public String getCreatedBy() {\r\n" +
		"        return createdBy;\r\n" +
		"    }\r\n" +
		"    public void setCreatedBy(String createdBy) {\r\n" +
		"        this.createdBy = createdBy;\r\n" +
		"    }\r\n" +
		"    public Instant getCreatedDate() {\r\n" +
		"        return createdDate;\r\n" +
		"    }\r\n" +
		"    public void setCreatedDate(Instant createdDate) {\r\n" +
		"        this.createdDate = createdDate;\r\n" +
		"    }\r\n" +
		"    public String getLastModifiedBy() {\r\n" +
		"        return lastModifiedBy;\r\n" +
		"    }\r\n" +
		"    public void setLastModifiedBy(String lastModifiedBy) {\r\n" +
		"        this.lastModifiedBy = lastModifiedBy;\r\n" +
		"    }\r\n" +
		"    public Instant getLastModifiedDate() {\r\n" +
		"        return lastModifiedDate;\r\n" +
		"    }\r\n" +
		"    public void setLastModifiedDate(Instant lastModifiedDate) {\r\n" +
		"        this.lastModifiedDate = lastModifiedDate;\r\n" +
		"    }\r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "AbstractAuditingDTO";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
