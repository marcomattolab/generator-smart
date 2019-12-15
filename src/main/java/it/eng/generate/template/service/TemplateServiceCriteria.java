package it.eng.generate.template.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.ProjectRelation;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateServiceCriteria extends AbstractTemplate{

	public TemplateServiceCriteria(Table tabella) {
		super(tabella);
	}
	
	public TemplateServiceCriteria(DataBase database, Table tabella) {
		super(database);
		this.tabella = tabella;
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
		"import java.io.Serializable;\r\n" +
		"import java.util.Objects;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainEnumerationFolder()+".*;\r\n\n" +
		"import io.github.jhipster.service.filter.BooleanFilter;\r\n" +
		"import io.github.jhipster.service.filter.DoubleFilter;\r\n" +
		"import io.github.jhipster.service.filter.Filter;\r\n" +
		"import io.github.jhipster.service.filter.FloatFilter;\r\n" +
		"import io.github.jhipster.service.filter.IntegerFilter;\r\n" +
		"import io.github.jhipster.service.filter.LongFilter;\r\n" +
		"import io.github.jhipster.service.filter.BigDecimalFilter;\r\n" +
		"import io.github.jhipster.service.filter.StringFilter;\r\n" +
		"import io.github.jhipster.service.filter.InstantFilter;\r\n" +
		"import io.github.jhipster.service.filter.LocalDateFilter;\r\n\n" +
		"import io.github.jhipster.service.filter.ZonedDateTimeFilter;\r\n\n" +
		
		"/**\r\n" +
		" * "+Utils.getEntityName(tabella)+" class for the "+Utils.getEntityName(tabella)+" entity. This class is used in "+Utils.getEntityName(tabella)+"Resource to\r\n" +
		" * receive all the possible filtering options from the Http GET request parameters.\r\n" +
		" * For example the following could be a valid requests:\r\n" +
		" * <code> /"+Utils.getEntityName(tabella)+"s?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>\r\n" +
		" * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use\r\n" +
		" * fix type specific filters.\r\n" +
		" */\r\n" +
		"public class "+getClassName()+" implements Serializable {\r\n\n";

		//Add Enumeration management
	    //HashMap<String, List<String>> enums = database.getEnumeration();
		//TODO DEVELOP ENUMERATIONS GET!!!!
		System.out.println("## Enumeration for table: " + tabella.getNomeTabella() + " .. developing ");
		HashMap<String, List<String>> enums = Utils.filterEnumeration(tabella.getNomeTabella(), database.getEnumerationRelation());
		Set<String> enumNames = enums.keySet();
		for (String enumName : enumNames) {
			body+= "    /**\r\n";
			body+= "     * Class for filtering "+enumName+"\r\n";
			body+= "     */\r\n";
			body+= "    public static class "+enumName+"Filter extends Filter<"+enumName+"> {\r\n" ;
			body+= "    }\r\n\n";
		}
		
		//[Manage Relations]
		if(!CollectionUtils.isEmpty(conf.getProjectRelations())) {
			for(ProjectRelation rel: conf.getProjectRelations()) {
				String relationType = rel.getType();
				String nomeTabellaSx = rel.getSxTable();
				String nomeRelazioneSx = rel.getSxName();
				String nomeTabellaDx = rel.getDxTable();
				String nomeTabella = tabella.getNomeTabella().toLowerCase();
				
				if(nomeTabellaSx!=null && nomeTabellaDx != null 
						&& relationType.equals(Utils.OneToOne) 
						&& nomeTabellaSx.toLowerCase().equals(nomeTabella) ) {
					
					Column columnId = new Column();
					columnId.setName(nomeRelazioneSx+"Id");
					columnId.setTypeColumn(Column.corvertModelType("Long"));
					tabella.addColumn(columnId);
					//body += Utils.generaField(columnId, false)+"\n";
					//body += Utils.generaAddForBeanSimple(columnId, getClassName(), false);
				} else {
					//TODO DEVELOP THIS!
				}
			}
		}
		
		//[/Manage Relations]

		body+=
		"    private static final long serialVersionUID = 1L;\r\n";
		Set set = tabella.getColumnNames();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
			//body += Utils.generaFieldFilter(column)+"\n";
			body += Utils.generaFieldFilter(column, false)+"\n";
		}	
		
		body+=
		"	 \n"	+
		"    public "+getClassName()+"() {\r\n" +
		"    }\r\n\n";
		
		set = tabella.getColumnNames();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
			
			String filterTypology = Utils.getFilterTypology(column);
			body += 
			"    public "+filterTypology+" get"+Utils.getFieldNameForMethod(column,false)+"() {\r\n" +
			"        return "+Utils.getFieldName(column, false)+";\r\n" +
			"    }\r\n\n" +
			"    public void set"+Utils.getFieldNameForMethod(column,false)+"("+filterTypology+" "+Utils.getFieldName(column, false)+") {\r\n" +
			"        this."+Utils.getFieldName(column, false)+" = "+Utils.getFieldName(column, false)+";\r\n" +
			"    }\r\n\n";
		}
		
		body += 
		"    @Override\r\n" +
		"    public boolean equals(Object o) {\r\n" +
		"        if (this == o) {\r\n" +
		"            return true;\r\n" +
		"        }\r\n" +
		"        if (o == null || getClass() != o.getClass()) {\r\n" +
		"            return false;\r\n" +
		"        }\r\n" +
		"        final "+getClassName()+" that = ("+getClassName()+") o;\r\n" +
		"        return\r\n";
		set = tabella.getColumnNames();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			boolean isLatest = !iter.hasNext();
			Column column = tabella.getColumn(key);
			body += "\t\t\tObjects.equals("+Utils.getFieldName(column,false)+", that."+Utils.getFieldName(column,false)+") ";
			body += (!isLatest ? "&&" : ";") + "\r\n";
		}
		body +=
		"    }\r\n\n";
		
		body +=
		"    @Override\r\n" +
		"    public int hashCode() {\r\n" +
		"        return Objects.hash(\r\n";
		set = tabella.getColumnNames();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			boolean isLatest = !iter.hasNext();
			Column column = tabella.getColumn(key);
			body += "\t\t\t"+Utils.getFieldName(column,false)+"";
			body += (!isLatest ? "," : "") + "\r\n";
		}
		body +=
		"        );\r\n" +
		"    }\r\n\n";
		
		body +=
		"    @Override\r\n" +
		"    public String toString() {\r\n" +
		"        return \""+getClassName()+"{\" +\r\n";
		
		set = tabella.getColumnNames();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			boolean isLatest = !iter.hasNext();
			Column column = tabella.getColumn(key);
			body += "\t\t\t("+Utils.getFieldName(column,false)+" != null ? \""+Utils.getFieldName(column,false)+"=\" + "+Utils.getFieldName(column,false)+" + \", \" : \"\") +";
			body += (!isLatest ? "" : "") + "\r\n";
		}
		body +=
		"            \"}\";\r\n" +
		"    }\r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return Utils.getCriteriaClassName(tabella);
	}
	
	public String getSourceFolder() {
		return "src/main/java";
	}
	
}
