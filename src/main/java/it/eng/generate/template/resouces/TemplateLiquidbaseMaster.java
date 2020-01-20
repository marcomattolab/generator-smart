package it.eng.generate.template.resouces;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateLiquidbaseMaster extends AbstractResourceTemplate{
	
	public TemplateLiquidbaseMaster(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getResConfigLiquidbaseFolder(),".","/");
		return typeTemplate;
	}
	
	public String getTypeFile() {
		return "xml";
	}

	public String getBody() {
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = 
		"<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" +
		"<databaseChangeLog\r\n" +
		"    xmlns=\"http://www.liquibase.org/xml/ns/dbchangelog\"\r\n" +
		"    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n" +
		"    xsi:schemaLocation=\"http://www.liquibase.org/xml/ns/dbchangelog http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.5.xsd\">\r\n" +
		"    <include file=\"config/liquibase/changelog/00000000000000_initial_schema.xml\" relativeToChangelogFile=\"false\"/>\r\n\n" ;
		
		//Entity
		body+="    <!-- liquibase-add-entity-changelog -->\r\n" ;
		for(Table table: Utils.getTables(database)  ) {
			String Tablename = Utils.getEntityName(table);
			body+="    <include file=\"config/liquibase/changelog/"+Utils.getCurrentDate(table.getSort())+"_added_entity_"+Tablename+".xml\" relativeToChangelogFile=\"false\"/>\r\n" ;
		}
		
		//Constraints
		body+="\n" ;
		body+="    <!-- liquibase-add-constraints-changelog -->\r\n" ;
		for(Table table: Utils.getTables(database)  ) {
			String Tablename = Utils.getEntityName(table);
			if (Utils.havingConstraints(conf, table)) {
				body+="    <include file=\"config/liquibase/changelog/"+Utils.getCurrentDate(table.getSort())+"_added_entity_constraints_"+Tablename+".xml\" relativeToChangelogFile=\"false\"/>\r\n" ;
			} else {
				//System.out.println("## Skip constraints.xml for entity " + Tablename + " ..");
			}
		}
		
		//Audit
		body+="\n" ;
		body+="    <!-- liquibase-add-audit-changelog -->\r\n" ;
		body+="    <include file=\"config/liquibase/changelog/20190105231246_added_entity_EntityAuditEvent.xml\" relativeToChangelogFile=\"false\"/>\r\n" ;
		body+="\n" ;
		
		body+=
		"</databaseChangeLog>\r\n";
		return body;
	}


	public String getClassName() {
		return "master";
	}
	
	public String getSourceFolder() {
		return "src/main/resources";
	}

}
