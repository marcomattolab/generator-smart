package it.eng.generate.template.service;

import java.util.Iterator;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.ProjectRelation;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateServiceDTO extends AbstractTemplate{

	public TemplateServiceDTO(Table tabella) {
		super(tabella);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcServiceDtoFolder(),".","/");
		return typeTemplate;
	}
	
	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcServiceDtoFolder()+";\r\n\n" +
		"import java.time.Instant;\r\n" +
		"import java.time.LocalDate;\r\n" +
		"import javax.validation.constraints.*;\r\n" +
		"import java.io.Serializable;\r\n" +
		"import java.util.Objects;\r\n" +
		"import javax.persistence.Lob;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainEnumerationFolder()+".*;\r\n\n" +
		"/**\r\n" +
		" * A DTO for the "+Utils.getEntityName(tabella)+" entity.\r\n" +
		" */\r\n" +
		"public class "+getClassName()+" extends AbstractAuditingDTO implements Serializable {\r\n";

		
		Set set = tabella.getColumnNames();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
			body += Utils.generaField(column)+"\n";
		}
		
		//[Relations]
		if(!CollectionUtils.isEmpty(conf.getProjectRelations())) {
			for(ProjectRelation rel: conf.getProjectRelations()) {
				String relationType = rel.getType();
				String nomeTabellaSx = rel.getSxTable();
				String nomeRelazioneSx = rel.getSxName();
				String nomeSelectSx = rel.getSxSelect();
				String nomeTabellaDx = rel.getDxTable();
				String nomeTabella = tabella.getNomeTabella().toLowerCase();
				
				if(nomeTabellaSx!=null && nomeTabellaDx != null 
						&& relationType.equals(Utils.OneToOne) 
						&& nomeTabellaSx.toLowerCase().equals(nomeTabella) ) {
					
					Column columnId = new Column();
					columnId.setName(nomeRelazioneSx+"Id");
					columnId.setTypeColumn(Column.corvertModelType("Long"));
					
					Column columnSelect = new Column();
					columnSelect.setName(nomeRelazioneSx+Utils.getFirstUpperCase(nomeSelectSx));
					columnSelect.setTypeColumn(Utils.getTypeColumnFromRelation(conf, nomeSelectSx, nomeTabellaDx));

					body += Utils.generaField(columnId, false)+"\n";
					body += Utils.generaField(columnSelect, false)+"\n";

					body += Utils.generaAddForBeanSimple(columnId, getClassName(), false);
					body += Utils.generaAddForBeanSimple(columnSelect, getClassName(), false);
				} else {
					//TODO DEVELOP THIS!
				}
			}
		}
		//[/Relations]
		
		set = tabella.getColumnNames();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
			body += Utils.generaAddForBeanSimple(column, getClassName());
		}
		
		body += "\n\n";
		
		body += 
		"    @Override\r\n" +
		"    public boolean equals(Object o) {\r\n" +
		"        if (this == o) {\r\n" +
		"            return true;\r\n" +
		"        }\r\n" +
		"        if (o == null || getClass() != o.getClass()) {\r\n" +
		"            return false;\r\n" +
		"        }\r\n" +
		"        "+Utils.getDTOClassName(tabella)+" "+Utils.getClassNameLowerCase(tabella)+"DTO = ("+Utils.getDTOClassName(tabella)+") o;\r\n" +
		"        if ("+Utils.getClassNameLowerCase(tabella)+"DTO.getId() == null || getId() == null) {\r\n" +
		"            return false;\r\n" +
		"        }\r\n" +
		"        return Objects.equals(getId(), "+Utils.getClassNameLowerCase(tabella)+"DTO.getId());\r\n" +
		"    }\r\n\n";
		
		body += Utils.generateHashCode(tabella);
		
		body += Utils.generateToString(tabella, Utils.getDTOClassName(tabella));
		
		body += "}\r\n";
		
		return body;
	}

	public String getClassName() {
		return Utils.getDTOClassName(tabella);
	}
	
	public String getSourceFolder() {
		return "src/main/java";
	}
	
}
