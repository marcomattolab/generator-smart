package it.eng.generate.template.domain;

import java.util.Iterator;
import java.util.Set;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateDomain extends AbstractTemplate{

	public TemplateDomain(Table tabella) {
		super(tabella);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcDomainFolder(),".","/");
		return typeTemplate;
	}
	
	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder()+";\r\n\n" +
		"import io.swagger.annotations.ApiModel;\r\n" +
		"import com.fasterxml.jackson.annotation.JsonIgnore;\r\n" +
		"import org.hibernate.annotations.Cache;\r\n" +
		"import org.hibernate.annotations.CacheConcurrencyStrategy;\r\n" +
		"import javax.persistence.*;\r\n" +
		"import javax.validation.constraints.*;\r\n" +
		"import java.io.Serializable;\r\n" +
		"import java.math.BigDecimal;\r\n" +
		"import java.time.Instant;\r\n" +
		"import java.time.LocalDate;\r\n" +
		"import java.time.ZonedDateTime;\r\n" +
		"import java.util.HashSet;\r\n" +
		"import java.util.Set;\r\n" +
		"import java.util.Objects;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder() + ".enumeration.*;\r\n\n" +
		"/**\r\n" +
		" * Entity "+getClassName()+"\r\n" +
		" */\r\n" +
		"@ApiModel(description = \"Entity "+getClassName()+"\")\r\n" +
		"@Entity\r\n" +
		"@Table(name = \""+getClassName()+"\")\r\n" +
	    "@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)\r\n" +  //TODO Added Cache
		"public class "+getClassName()+" extends AbstractAuditingEntity implements Serializable  {\r\n" +
		"\tprivate static final long serialVersionUID = 1L;\r\n";
		
		Set set = tabella.getColumnNames();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
			body += Utils.generaFieldExt(column)+"\n";
		}
		
		set = tabella.getColumnNames();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
			body += Utils.generaGetAndSetForBeanExt(column, getClassName());
		}
		
		//body += "\n";
		body += "\n\t@Override";
		body += "\n\tpublic int hashCode() {";
		body += "\n\t\treturn Objects.hashCode(getId());";
		body += "\n\t}";
		body += "\n";
		body += "\n\t@Override";
		body += "\n\tpublic boolean equals(Object o) {";
		body += "\n\t\tif (this == o) {";
		body += "\n\t\t\treturn true;";
		body += "\n\t\t}";
		body += "\n\t\tif (o == null || getClass() != o.getClass()) {";
		body += "\n\t\t\treturn false;";
		body += "\n\t\t}";
		body += "\n\t\t"+getClassName()+" object = ("+getClassName()+") o;";
		body += "\n\t\tif (object.getId() == null || getId() == null) {";
		body += "\n\t\t\treturn false;";
		body += "\n\t\t}";
		body += "\n\t\treturn Objects.equals(getId(), object.getId());";
		body += "\n\t}";
		body += "\n";
		body += "\n\t@Override";
		body += "\n\tpublic String toString(){";
		body += "\n\t\treturn this.getClass().getName()+\":{";
		boolean isFirst = true;
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
			if(isFirst){
				body += Utils.generaToString(column);
				isFirst = false;
			}else{
				body += ","+Utils.generaToString(column);
			}
		}
		body += "}\";";
		body += "\n\t}";
		body += "\n}";
		body += "\n";
		return body;
	}

	public String getClassName() {
		return Utils.getEntityName(tabella);
	}
	
	public String getSourceFolder() {
		return "src/main/java";
	}

}
