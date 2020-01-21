package it.eng.generate.template.resouces;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateLiquidbaseEntityAudit extends AbstractResourceTemplate{

	public TemplateLiquidbaseEntityAudit(DataBase database) {
		super(database);
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
		
		String body = 
		"<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" + 
		"<databaseChangeLog\r\n" + 
		"    xmlns=\"http://www.liquibase.org/xml/ns/dbchangelog\"\r\n" + 
		"    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n" + 
		"    xsi:schemaLocation=\"http://www.liquibase.org/xml/ns/dbchangelog http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.4.xsd\">\r\n" + 
		"\r\n" + 
		"    <property name=\"now\" value=\"now()\" dbms=\"mysql,h2\"/>\r\n" + 
		"    <property name=\"now\" value=\"current_timestamp\" dbms=\"postgresql\"/>\r\n" + 
		"    <property name=\"now\" value=\"sysdate\" dbms=\"oracle\"/>\r\n" + 
		"    <property name=\"now\" value=\"GETDATE()\" dbms=\"mssql\"/>\r\n" + 
		"    <property name=\"autoIncrement\" value=\"true\" dbms=\"mysql,h2,postgresql,oracle,mssql\"/>\r\n" + 
		"    <property name=\"floatType\" value=\"float4\" dbms=\"postgresql, h2\"/>\r\n" + 
		"    <property name=\"floatType\" value=\"float\" dbms=\"mysql, oracle, mssql\"/>\r\n" + 
		"\r\n" + 
		"    <!--\r\n" + 
		"        Added the entity EntityAuditEvent.\r\n" + 
		"    -->\r\n" + 
		"    <changeSet id=\"20190105231246\" author=\"jhipster\">\r\n" + 
		"        <createTable tableName=\"jhi_entity_audit_event\">\r\n" + 
		"            <column name=\"id\" type=\"bigint\" autoIncrement=\"${autoIncrement}\">\r\n" + 
		"                <constraints primaryKey=\"true\" nullable=\"false\"/>\r\n" + 
		"            </column>\r\n" + 
		"            <column name=\"entity_id\" type=\"bigint\">\r\n" + 
		"                <constraints nullable=\"false\" />\r\n" + 
		"            </column>\r\n" + 
		"            <column name=\"entity_type\" type=\"varchar(255)\">\r\n" + 
		"                <constraints nullable=\"false\" />\r\n" + 
		"            </column>\r\n" + 
		"            <column name=\"action\" type=\"varchar(20)\">\r\n" + 
		"                <constraints nullable=\"false\" />\r\n" + 
		"            </column>\r\n" + 
		"            <column name=\"entity_value\" type=\"clob\"/>\r\n" + 
		"            <column name=\"commit_version\" type=\"integer\"/>\r\n" + 
		"            <column name=\"modified_by\" type=\"varchar(100)\"/>\r\n" + 
		"            <column name=\"modified_date\" type=\"timestamp\">\r\n" + 
		"                <constraints nullable=\"false\" />\r\n" + 
		"            </column>\r\n" + 
		"        </createTable>\r\n" + 
		"        <createIndex indexName=\"idx_entity_audit_event_entity_id\"\r\n" + 
		"            tableName=\"jhi_entity_audit_event\">\r\n" + 
		"            <column name=\"entity_id\" type=\"bigint\"/>\r\n" + 
		"        </createIndex>\r\n" + 
		"        <createIndex indexName=\"idx_entity_audit_event_entity_type\"\r\n" + 
		"            tableName=\"jhi_entity_audit_event\">\r\n" + 
		"            <column name=\"entity_type\" type=\"varchar(255)\"/>\r\n" + 
		"        </createIndex>\r\n" + 
		"        <dropDefaultValue tableName=\"jhi_entity_audit_event\" columnName=\"modified_date\" columnDataType=\"datetime\"/>\r\n" + 
		"    </changeSet>\r\n\n" + 
		"</databaseChangeLog>\r\n";
		return body;
	}

	public String getClassName() {
		return "20190105231246_added_entity_EntityAuditEvent";
	}

	public String getSourceFolder() {
		return "src/main/resources";
	}

}
