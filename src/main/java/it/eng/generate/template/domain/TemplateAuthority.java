package it.eng.generate.template.domain;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateAuthority extends AbstractTemplate {

	public TemplateAuthority(DataBase database) {
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
		"import javax.persistence.Entity;\r\n" +
		"import javax.persistence.Id;\r\n" +
		"import javax.persistence.Table;\r\n" +
		"import javax.persistence.Column;\r\n" +
		"import javax.validation.constraints.NotNull;\r\n" +
		"import javax.validation.constraints.Size;\r\n" +
		"import java.io.Serializable;\r\n\n" +
		"/**\r\n" +
		" * An authority (a security role) used by Spring Security.\r\n" +
		" */\r\n" +
		"@Entity\r\n" +
		"@Table(name = \"jhi_authority\")\r\n" +
		"@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)\r\n" +
		"public class "+getClassName()+" implements Serializable {\r\n" +
		"    private static final long serialVersionUID = 1L;\r\n\n" +
		"    @NotNull\r\n" +
		"    @Size(max = 50)\r\n" +
		"    @Id\r\n" +
		"    @Column(length = 50)\r\n" +
		"    private String name;\r\n\n" +
		"    public String getName() {\r\n" +
		"        return name;\r\n" +
		"    }\r\n\n" +
		"    public void setName(String name) {\r\n" +
		"        this.name = name;\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    public boolean equals(Object o) {\r\n" +
		"        if (this == o) {\r\n" +
		"            return true;\r\n" +
		"        }\r\n" +
		"        if (o == null || getClass() != o.getClass()) {\r\n" +
		"            return false;\r\n" +
		"        }\r\n" +
		"        "+getClassName()+" authority = ("+getClassName()+") o;\r\n" +
		"        return !(name != null ? !name.equals(authority.name) : authority.name != null);\r\n" +
		"    }\r\n" +
		"    @Override\r\n\n" +
		"    public int hashCode() {\r\n" +
		"        return name != null ? name.hashCode() : 0;\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    public String toString() {\r\n" +
		"        return \""+getClassName()+"{\" +\r\n" +
		"            \"name='\" + name + '\\'' +\r\n" +
		"            \"}\";\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "Authority";
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
