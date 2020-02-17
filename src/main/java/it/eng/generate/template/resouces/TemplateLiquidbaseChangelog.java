package it.eng.generate.template.resouces;

import java.util.Iterator;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.ProjectRelation;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateLiquidbaseChangelog extends AbstractResourceTemplate{
	private static final String BIG_DECIMAL_PRECISION = "decimal(10,2)"; //FIXME Retrieve from DB
	
	
	public TemplateLiquidbaseChangelog(Table tabella) {
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
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String entityname = Utils.getClassNameLowerCase(tabella);
		
		String body = 
		"<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" +
		"<databaseChangeLog\r\n" +
		"    xmlns=\"http://www.liquibase.org/xml/ns/dbchangelog\"\r\n" +
		"    xmlns:ext=\"http://www.liquibase.org/xml/ns/dbchangelog-ext\"\r\n" +
		"    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n" +
		"    xsi:schemaLocation=\"http://www.liquibase.org/xml/ns/dbchangelog http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.5.xsd\r\n" +
		"                        http://www.liquibase.org/xml/ns/dbchangelog-ext http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-ext.xsd\">\r\n\n" +
		"    <property name=\"now\" value=\"now()\" dbms=\"h2\"/>\r\n" +
		"    <property name=\"now\" value=\"now()\" dbms=\"mysql\"/>\r\n\n" +
		"    <property name=\"autoIncrement\" value=\"true\"/>\r\n\n" +
		"    <property name=\"floatType\" value=\"float4\" dbms=\"postgresql, h2\"/>\r\n" +
		"    <property name=\"floatType\" value=\"float\" dbms=\"mysql, oracle, mssql\"/>\r\n\n" +
		"    <!--\r\n" +
		"        Added the entity "+Utils.getEntityName(tabella)+".\r\n" +
		"    -->\r\n" +
		"    <changeSet id=\""+Utils.getCurrentDate(tabella.getSort())+"\" author=\"smart\">\r\n" +
		"        <createTable tableName=\""+entityname+"\">\r\n\n";
		
		
		//MAIN CICLE DL - START
		Set<?> set = tabella.getColumnNames();
		for (Iterator<?> iter = set.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
			Class<?> filterType = column.getTypeColumn();
			//boolean isPrimaryKey = column.isKey();
			String nomeColonna = column.getName();
			int sizeColumn = column.getColumnSize();
			
			if( Utils.isPrimaryKeyID(column) ) {
				//Primary Key - FIXME retrieve from db
				body += "            <column name=\"id\" type=\"bigint\" autoIncrement=\"${autoIncrement}\">\r\n" +
						"                <constraints primaryKey=\"true\" nullable=\"false\"/>\r\n" +
						"            </column>\r\n\n";
				
			} else if (filterType.getName().equals("java.lang.String")) {
				body += "            <column name=\""+nomeColonna+"\" type=\"varchar("+sizeColumn+")\">\r\n" +
						"                <constraints nullable=\"true\" />\r\n" +
						"            </column>\r\n\n";
			
			} else if(filterType.getName().equals("java.math.BigDecimal")) {
				body += "            <column name=\""+nomeColonna+"\" type=\""+BIG_DECIMAL_PRECISION+"\">\r\n" +
						"                <constraints nullable=\"true\" />\r\n" +
						"            </column>\r\n\n";
			
			} else if(filterType.getName().equals("java.lang.Long")) {
				body += "            <column name=\""+nomeColonna+"\" type=\"bigint\">\r\n" +
						"                <constraints nullable=\"true\" />\r\n" +
						"            </column>\r\n\n";
			
			} else if(filterType.getName().equals("java.lang.Float")) {
				body += "            <column name=\""+nomeColonna+"\" type=\"${floatType}\">\r\n" +
						"                <constraints nullable=\"true\" />\r\n" +
						"            </column>\r\n\n";
			
			} else if(filterType.getName().equals("java.lang.Integer")) {
				body += "            <column name=\""+nomeColonna+"\" type=\"integer\">\r\n" +
						"                <constraints nullable=\"true\" />\r\n" +
						"            </column>\r\n";
			
			} else if(filterType.getName().equals("java.lang.Boolean")) {
				body += "            <column name=\""+nomeColonna+"\" type=\"bit\">\r\n" +
						"                <constraints nullable=\"true\" />\r\n" +
						"            </column>\r\n\n" ;
			
			} else if(filterType.getName().equals("java.sql.Date") 
					  || filterType.getName().equals("java.time.LocalDate") ) {
				body += "            <column name=\""+nomeColonna+"\" type=\"date\">\r\n" +
						"                <constraints nullable=\"true\" />\r\n" +
						"            </column>\r\n\n";
			
			} else if(filterType.getName().equals("java.sql.Timestamp") 
					  || filterType.getName().equals("java.time.Instant") 
					  || filterType.getName().equals("java.time.ZonedDateTime") ) {
				body += "            <column name=\""+nomeColonna+"\" type=\"datetime\">\r\n" +
						"                <constraints nullable=\"false\" />\r\n" +
						"            </column>\r\n\n";
				
			} else if(filterType.getName().equals("java.sql.Clob")) {
				body += "            <column name=\""+nomeColonna+"\" type=\"clob\">\r\n" +
						"                <constraints nullable=\"true\" />\r\n" +
						"            </column>\r\n\n";

			} else if(filterType.getName().equals("java.sql.Blob") ) {
				body += "            <column name=\""+nomeColonna+"\" type=\"longblob\">\r\n" +
						"                <constraints nullable=\"true\" />\r\n" +
						"            </column>\r\n\n";
				
				String fieldNameContentType = Utils.getFieldNameContentType(column);
				body += "            <column name=\""+fieldNameContentType +"\" type=\"varchar(255)\">\r\n" +
						"                <constraints nullable=\"true\" />\r\n" +
						"            </column>\r\n\n";
			}				

		}
		
		//Relations management
		if(!CollectionUtils.isEmpty(conf.getProjectRelations())) {
			for(ProjectRelation rel: conf.getProjectRelations()) {
				String relationType = rel.getType();
				String nomeTabellaSx = rel.getSxTable();
				String nomeRelazioneSx = rel.getSxName();
				String nomeRelazioneDx = rel.getDxName();
				String nomeTabellaDx = rel.getDxTable();
				String nomeTabella = tabella.getNomeTabella().toLowerCase();
				
				if(nomeTabellaSx!=null && nomeTabellaDx != null) {
					if ( relationType.equals(Utils.OneToOne) ) {
						if (nomeTabellaSx.toLowerCase().equals(nomeTabella)) {
							body += "            <column name=\""+nomeRelazioneSx+"_id\" type=\"bigint\">\n" +
									"                <constraints unique=\"true\" nullable=\"true\" uniqueConstraintName=\"ux_"+nomeTabellaDx.toLowerCase()+"_"+nomeRelazioneSx+"_id\" />\n"+
									"            </column>\n";
						}
						
					} else if (relationType.equals(Utils.OneToMany)) {
						if (nomeTabellaDx.toLowerCase().equals(nomeTabella)) {
							//DONE    nomeTabellaSx ==> nomeRelazioneDx    /   autore_id ==> preferito2_id
			                body += "            <column name=\""+Utils.getFirstLowerCase(nomeRelazioneDx)+"_id\" type=\"bigint\">\n" +
									"                <constraints nullable=\"true\" />\n"+
									"            </column>\n";
						}
						
					} else if (relationType.equals(Utils.ManyToOne)) {
						if (nomeTabellaSx.toLowerCase().equals(nomeTabella)) {
							body += "            <column name=\""+nomeRelazioneSx+"_id\" type=\"bigint\">\n" +
									"                <constraints nullable=\"true\" />\n"+
									"            </column>\n";
						}
					}
				}
            
			}
		}
		
		// MAIN CICLE DL - END
		body += "        </createTable>\r\n";
		
		
		//Relations ManyToMany
		if(!CollectionUtils.isEmpty(conf.getProjectRelations())) {
			for(ProjectRelation rel: conf.getProjectRelations()) {
				String relationType = rel.getType();
				String nomeTabellaSx = rel.getSxTable();
				String nomeRelazioneSx = rel.getSxName();
				String nomeTabellaDx = rel.getDxTable();
				String nomeTabella = tabella.getNomeTabella().toLowerCase();
				
				if(nomeTabellaSx!=null && nomeTabellaDx != null) {
					if ( relationType.equals(Utils.ManyToMany) ) {
						if (nomeTabellaSx.toLowerCase().equals(nomeTabella)) {
						body += "\n		<createTable tableName=\""+ Utils.getFirstLowerCase(nomeTabellaSx) + "_"+ Utils.getFirstLowerCase(nomeRelazioneSx) +"\">\r\n" +
								"          <column name=\""+ Utils.getFirstLowerCase(nomeRelazioneSx) +"s_id\" type=\"bigint\">\r\n" +
								"              <constraints nullable=\"false\"/>\r\n" +
								"          </column>\r\n" +
								"          <column name=\""+ Utils.getFirstLowerCase(nomeTabellaSx) + "s_id\" type=\"bigint\">\r\n" +
								"              <constraints nullable=\"false\"/>\r\n" +
								"          </column>\r\n" +
								"	    </createTable>\r\n\n" +
								"	    <addPrimaryKey columnNames=\""+ Utils.getFirstLowerCase(nomeTabellaSx) + "s_id, "+ Utils.getFirstLowerCase(nomeRelazioneSx) +"s_id\" tableName=\""+ Utils.getFirstLowerCase(nomeTabellaSx) + "_"+ Utils.getFirstLowerCase(nomeRelazioneSx) +"\"/>\r\n\n";
						}
					}
				}
			}
		}
		//
		
		
		//CICLE DATES - START
		Set<?> cset = tabella.getColumnNames();
		for (Iterator<?> iter = cset.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
			Class<?> filterType = column.getTypeColumn();
			String nomeColonna = column.getName();				// data_nascita
			
			if(filterType.getName().equals("java.sql.Timestamp") 
				|| filterType.getName().equals("java.time.Instant") 
				|| filterType.getName().equals("java.time.ZonedDateTime") ) {
				
				body += "        <dropDefaultValue tableName=\""+entityname+"\" columnName=\""+nomeColonna+"\" columnDataType=\"datetime\"/>\r\n";
			}
			
		}
		//CICLE DATES - END

		
		body += 
		"\n" +
		"    </changeSet>\r\n" +
				
		
		//Audit START
		"    <!-- Added the entity audit columns -->\r\n" +
		"    <changeSet id=\""+Utils.getCurrentDate(tabella.getSort())+"-audit-1\" author=\"jhipster-entity-audit\">\r\n" +
		"        <addColumn tableName=\""+entityname+"\">\r\n" +
		"            <column name=\"created_by\" type=\"varchar(50)\">\r\n" +
		"                <constraints nullable=\"false\"/>\r\n" +
		"            </column>\r\n" +
		"            <column name=\"created_date\" type=\"timestamp\" defaultValueDate=\"${now}\">\r\n" +
		"                <constraints nullable=\"false\"/>\r\n" +
		"            </column>\r\n" +
		"            <column name=\"last_modified_by\" type=\"varchar(50)\"/>\r\n" +
		"            <column name=\"last_modified_date\" type=\"timestamp\"/>\r\n" +
		"        </addColumn>\r\n" +
		"    </changeSet>\r\n\n" + 
		//Audit
		
		
		//Riempimento Dati tramite file CSV
		"   <!--\r\n" + 
		"        Load sample data generated with Faker.js\r\n" + 
		"        - This data can be easily edited using a CSV editor (or even MS Excel) and\r\n" + 
		"          is located in the 'src/main/resources/config/liquibase/data' directory\r\n" + 
		"        - By default this data is applied when running with 'dev' profile.\r\n" + 
		"          This can be customized by adding or removing 'faker' in the 'spring.liquibase.contexts'\r\n" + 
		"          Spring Boot configuration key.\r\n" + 
		"    -->\r\n" + 
		"    <changeSet id=\""+Utils.getCurrentDate(tabella.getSort())+"-audit-91\" author=\"smart\" context=\"faker\">\n" +
		"     	<loadData encoding=\"UTF-8\"\n" + 
		"                  file=\"config/liquibase/data/"+entityname+"s.csv\"\n" +
		"                  separator=\";\"\n" +
		"                  tableName=\""+entityname+"\">\n";
		for (Column column : tabella.getColumns()) {
			String nomeColonna = column.getName();
			String ctype = column.getLabelType().toLowerCase();
			if( Utils.isPrimaryKeyID(column) ) {
				body += "            <column name=\""+nomeColonna+"\" type=\"numeric\"/>\n";
			} else if( Utils.isBlob(column) ) {
	            body += "            <column name=\""+nomeColonna+"\" type=\""+"blob"+"\"/>\n";
	            body += "            <column name=\""+nomeColonna+"_content_type"+"\" type=\""+"string"+"\"/>\n";
			} else if( Utils.isClob(column) ) {
				//TODO TEST
				body += "            <column name=\""+nomeColonna+"\" type=\""+"clob"+"\"/>\n";
			} else if( Utils.isNumericField(column) ) {
				body += "            <column name=\""+nomeColonna+"\" type=\"numeric\"/>\n";
			} else {
				body += "            <column name=\""+nomeColonna+"\" type=\""+ctype+"\"/>\n";
			}
		}
		body +=
		"        </loadData>\n" +
		"    </changeSet>\n\n" +
		
		"</databaseChangeLog>\r\n";
		return body;
	}

	public String getClassName() {
		return Utils.getCurrentDate(tabella.getSort())+"_added_entity_"+Utils.getEntityName(tabella);
	}
	
	public String getSourceFolder() {
		return "src/main/resources";
	}

}
