package it.eng.generate.template.resouces;

import org.springframework.util.CollectionUtils;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.ProjectRelation;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateLiquidbaseChangelogConstraint extends AbstractResourceTemplate{
	
	public TemplateLiquidbaseChangelogConstraint(Table tabella) {
		super(tabella);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getResConfigLiquidbaseChangelogFolder(),".","/");
		return typeTemplate;
	}
	
	public String getTypeFile() {
		return "xml";
	}

	public String getBody() {
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		//String entityname = Utils.getClassNameLowerCase(tabella);
		
		String body = 
				"<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" +
				"<databaseChangeLog\r\n" +
				"    xmlns=\"http://www.liquibase.org/xml/ns/dbchangelog\"\r\n" +
				"    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n" +
				"    xsi:schemaLocation=\"http://www.liquibase.org/xml/ns/dbchangelog http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.5.xsd\">\r\n" +
				"    <!--\r\n" +
				"        Added the constraints for entity "+Utils.getEntityName(tabella)+".\r\n" +
				"    -->\r\n" +
				"    <changeSet id=\""+Utils.getCurrentDate(tabella.getSort())+"-01"+"\" author=\"smart\">\r\n\n";
		
				//Relations management
				if(!CollectionUtils.isEmpty(conf.getProjectRelations())) {
					for(ProjectRelation rel: conf.getProjectRelations()) {
						String relationType = rel.getType();
						String nomeTabellaSx = rel.getSxTable();
						String nomeRelazioneSx = rel.getSxName();
						//String nomeRelazioneDx = rel.getDxName();
						String nomeTabellaDx = rel.getDxTable();
						String nomeTabella = tabella.getNomeTabella().toLowerCase();
						
						if(nomeTabellaSx!=null && nomeTabellaDx != null) {
							if ( relationType.equals(Utils.OneToOne) ) {
								//TODO DEVELOP THIS
							} else if (relationType.equals(Utils.ManyToMany)) {
								// Company{myKeyword(keywordCode)} to CompanyKeyword{myCompany(companyName)}
								if (nomeTabellaSx.toLowerCase().equals(nomeTabella)) {
									body +=  
									"        <addForeignKeyConstraint baseColumnNames=\""+Utils.getFirstLowerCase(nomeTabellaSx)+"s_id\"\r\n" +
									"                                 baseTableName=\""+Utils.getFirstLowerCase(nomeTabellaSx)+"_"+Utils.getFirstLowerCase(nomeRelazioneSx)+"\"\r\n" +
									"                                 constraintName=\"fk_"+Utils.getFirstLowerCase(nomeTabellaSx)+"_"+Utils.getFirstLowerCase(nomeRelazioneSx)+"_"+Utils.getFirstLowerCase(nomeTabellaSx)+"s_id\"\r\n" +
									"                                 referencedColumnNames=\"id\"\r\n" +
									"                                 referencedTableName=\""+Utils.getFirstLowerCase(nomeTabellaSx)+"\"/>\r\n" +
									"        <addForeignKeyConstraint baseColumnNames=\""+Utils.getFirstLowerCase(nomeRelazioneSx)+"s_id\"\r\n" +
									"                                 baseTableName=\""+Utils.getFirstLowerCase(nomeTabellaSx)+"_"+Utils.getFirstLowerCase(nomeRelazioneSx)+"\"\r\n" +
									"                                 constraintName=\"fk_"+Utils.getFirstLowerCase(nomeTabellaSx)+"_"+Utils.getFirstLowerCase(nomeRelazioneSx)+"_"+Utils.getFirstLowerCase(nomeRelazioneSx)+"s_id\"\r\n" +
									"                                 referencedColumnNames=\"id\"\r\n" +
									"                                 referencedTableName=\""+Utils.getFirstLowerCase(nomeTabellaDx)+"\"/>\r\n";
								}
								
							} else if (relationType.equals(Utils.OneToMany)) {
								if (nomeTabellaDx.toLowerCase().equals(nomeTabella)) {
					                body += 
					                	"        <addForeignKeyConstraint baseColumnNames=\""+Utils.getFirstLowerCase(nomeTabellaSx)+"_id\"\r\n" +
									"                                 baseTableName=\""+Utils.getFirstLowerCase(nomeTabellaDx)+"\"\r\n" +
									"                                 constraintName=\"fk_"+Utils.getFirstLowerCase(nomeTabellaDx)+"_"+Utils.getFirstLowerCase(nomeTabellaSx)+"_id\"\r\n" +
									"                                 referencedColumnNames=\"id\"\r\n" +
									"                                 referencedTableName=\""+Utils.getFirstLowerCase(nomeTabellaSx)+"\"/>\r\n";
								}
							} else if (relationType.equals(Utils.ManyToOne)) {
								//TODO DEVELOP THIS
							}
						}
		            
					}
				}

				body +=
				"    </changeSet>\r\n" +
				"</databaseChangeLog>\r\n";

		return body;
	}

	public String getClassName() {
		return Utils.getCurrentDate(tabella.getSort())+"_added_entity_constraints_"+Utils.getEntityName(tabella);
	}
	
	public String getSourceFolder() {
		return "src/main/resources";
	}

}
