package it.eng.generate.template.resouces;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateLiquidbaseMasterInitialSchema extends AbstractResourceTemplate{

	public TemplateLiquidbaseMasterInitialSchema(DataBase database) {
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
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		
		
		String body = 
		"<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" +
		"<databaseChangeLog\r\n" +
		"    xmlns=\"http://www.liquibase.org/xml/ns/dbchangelog\"\r\n" +
		"    xmlns:ext=\"http://www.liquibase.org/xml/ns/dbchangelog-ext\"\r\n" +
		"    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n" +
		"    xsi:schemaLocation=\"http://www.liquibase.org/xml/ns/dbchangelog http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.5.xsd\r\n" +
		"                        http://www.liquibase.org/xml/ns/dbchangelog-ext http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-ext.xsd\">\r\n" +
		"    <property name=\"autoIncrement\" value=\"true\"/>\r\n" +
		"    <!--\r\n" +
		"        Core tables.\r\n" +
		"        The initial schema has the '00000000000001' id, so that it is over-written if we re-generate it.\r\n" +
		"    -->\r\n" +
		"    <changeSet id=\"00000000000001\" author=\"jhipster\">\r\n" +
		"        <createTable tableName=\"jhi_user\">\r\n" +
		"            <column name=\"id\" type=\"bigint\" autoIncrement=\"${autoIncrement}\">\r\n" +
		"                <constraints primaryKey=\"true\" nullable=\"false\"/>\r\n" +
		"            </column>\r\n" +
		"            <column name=\"login\" type=\"varchar(50)\">\r\n" +
		"                <constraints unique=\"true\" nullable=\"false\" uniqueConstraintName=\"ux_user_login\"/>\r\n" +
		"            </column>\r\n" +
		"            <column name=\"password_hash\" type=\"varchar(60)\"/>\r\n" +
		"            <column name=\"first_name\" type=\"varchar(50)\"/>\r\n" +
		"            <column name=\"last_name\" type=\"varchar(50)\"/>\r\n" +
		"            <column name=\"email\" type=\"varchar(254)\">\r\n" +
		"                <constraints unique=\"true\" nullable=\"true\" uniqueConstraintName=\"ux_user_email\"/>\r\n" +
		"            </column>\r\n" +
		"            <column name=\"image_url\" type=\"varchar(256)\"/>\r\n" +
		"            <column name=\"activated\" type=\"boolean\" valueBoolean=\"false\">\r\n" +
		"                <constraints nullable=\"false\" />\r\n" +
		"            </column>\r\n" +
		"            <column name=\"lang_key\" type=\"varchar(6)\"/>\r\n" +
		"            <column name=\"activation_key\" type=\"varchar(20)\"/>\r\n" +
		"            <column name=\"reset_key\" type=\"varchar(20)\"/>\r\n" +
		"            <column name=\"created_by\" type=\"varchar(50)\">\r\n" +
		"                <constraints nullable=\"false\"/>\r\n" +
		"            </column>\r\n" +
		"            <column name=\"created_date\" type=\"timestamp\"/>\r\n" +
		"            <column name=\"reset_date\" type=\"timestamp\">\r\n" +
		"                <constraints nullable=\"true\"/>\r\n" +
		"            </column>\r\n" +
		"            <column name=\"last_modified_by\" type=\"varchar(50)\"/>\r\n" +
		"            <column name=\"last_modified_date\" type=\"timestamp\"/>\r\n" +
		"        </createTable>\r\n" +
		"        <createTable tableName=\"jhi_authority\">\r\n" +
		"            <column name=\"name\" type=\"varchar(50)\">\r\n" +
		"                <constraints primaryKey=\"true\" nullable=\"false\"/>\r\n" +
		"            </column>\r\n" +
		"        </createTable>\r\n" +
		"        <createTable tableName=\"jhi_user_authority\">\r\n" +
		"            <column name=\"user_id\" type=\"bigint\">\r\n" +
		"                <constraints nullable=\"false\"/>\r\n" +
		"            </column>\r\n" +
		"            <column name=\"authority_name\" type=\"varchar(50)\">\r\n" +
		"                <constraints nullable=\"false\"/>\r\n" +
		"            </column>\r\n" +
		"        </createTable>\r\n" +
		"        <addPrimaryKey columnNames=\"user_id, authority_name\" tableName=\"jhi_user_authority\"/>\r\n" +
//		"        <createTable tableName=\"jhi_persistent_token\">\r\n" +
//		"            <column name=\"series\" type=\"varchar(20)\">\r\n" +
//		"                <constraints primaryKey=\"true\" nullable=\"false\"/>\r\n" +
//		"            </column>\r\n" +
//		"            <column name=\"user_id\" type=\"bigint\"/>\r\n" +
//		"            <column name=\"token_value\" type=\"varchar(20)\">\r\n" +
//		"                <constraints nullable=\"false\" />\r\n" +
//		"            </column>\r\n" +
//		"            <column name=\"token_date\" type=\"date\"/>\r\n" +
//		"            <column name=\"ip_address\" type=\"varchar(39)\"/>\r\n" +
//		"            <column name=\"user_agent\" type=\"varchar(255)\"/>\r\n" +
//		"        </createTable>\r\n" +
		"        <addForeignKeyConstraint baseColumnNames=\"authority_name\"\r\n" +
		"                                 baseTableName=\"jhi_user_authority\"\r\n" +
		"                                 constraintName=\"fk_authority_name\"\r\n" +
		"                                 referencedColumnNames=\"name\"\r\n" +
		"                                 referencedTableName=\"jhi_authority\"/>\r\n" +
		"        <addForeignKeyConstraint baseColumnNames=\"user_id\"\r\n" +
		"                                 baseTableName=\"jhi_user_authority\"\r\n" +
		"                                 constraintName=\"fk_user_id\"\r\n" +
		"                                 referencedColumnNames=\"id\"\r\n" +
		"                                 referencedTableName=\"jhi_user\"/>\r\n" +
		"        <addNotNullConstraint   columnName=\"password_hash\"\r\n" +
		"                                columnDataType=\"varchar(60)\"\r\n" +
		"                                tableName=\"jhi_user\"/>\r\n" +
//		"        <addForeignKeyConstraint baseColumnNames=\"user_id\"\r\n" +
//		"                                 baseTableName=\"jhi_persistent_token\"\r\n" +
//		"                                 constraintName=\"fk_user_persistent_token\"\r\n" +
//		"                                 referencedColumnNames=\"id\"\r\n" +
//		"                                 referencedTableName=\"jhi_user\"/>\r\n" +
		"        <loadData encoding=\"UTF-8\"\r\n" +
		"                  file=\"config/liquibase/users.csv\"\r\n" +
		"                  separator=\";\"\r\n" +
		"                  tableName=\"jhi_user\">\r\n" +
		"            <column name=\"activated\" type=\"boolean\"/>\r\n" +
		"            <column name=\"created_date\" type=\"timestamp\"/>\r\n" +
		"        </loadData>\r\n" +
		"        <dropDefaultValue tableName=\"jhi_user\" columnName=\"created_date\" columnDataType=\"datetime\"/>\r\n" +
		"        <loadData encoding=\"UTF-8\"\r\n" +
		"                  file=\"config/liquibase/authorities.csv\"\r\n" +
		"                  separator=\";\"\r\n" +
		"                  tableName=\"jhi_authority\"/>\r\n" +
		"        <loadData encoding=\"UTF-8\"\r\n" +
		"                  file=\"config/liquibase/users_authorities.csv\"\r\n" +
		"                  separator=\";\"\r\n" +
		"                  tableName=\"jhi_user_authority\"/>\r\n" +
		"        <createTable tableName=\"jhi_persistent_audit_event\">\r\n" +
		"            <column name=\"event_id\" type=\"bigint\" autoIncrement=\"${autoIncrement}\">\r\n" +
		"                <constraints primaryKey=\"true\" nullable=\"false\"/>\r\n" +
		"            </column>\r\n" +
		"            <column name=\"principal\" type=\"varchar(50)\">\r\n" +
		"                <constraints nullable=\"false\" />\r\n" +
		"            </column>\r\n" +
		"            <column name=\"event_date\" type=\"timestamp\"/>\r\n" +
		"            <column name=\"event_type\" type=\"varchar(255)\"/>\r\n" +
		"        </createTable>\r\n" +
		"        <createTable tableName=\"jhi_persistent_audit_evt_data\">\r\n" +
		"            <column name=\"event_id\" type=\"bigint\">\r\n" +
		"                <constraints nullable=\"false\"/>\r\n" +
		"            </column>\r\n" +
		"            <column name=\"name\" type=\"varchar(150)\">\r\n" +
		"                <constraints nullable=\"false\"/>\r\n" +
		"            </column>\r\n" +
		"            <column name=\"value\" type=\"varchar(255)\"/>\r\n" +
		"        </createTable>\r\n" +
		"        <addPrimaryKey columnNames=\"event_id, name\" tableName=\"jhi_persistent_audit_evt_data\"/>\r\n" +
		"        <createIndex indexName=\"idx_persistent_audit_event\"\r\n" +
		"                     tableName=\"jhi_persistent_audit_event\"\r\n" +
		"                     unique=\"false\">\r\n" +
		"            <column name=\"principal\" type=\"varchar(50)\"/>\r\n" +
		"            <column name=\"event_date\" type=\"timestamp\"/>\r\n" +
		"        </createIndex>\r\n" +
		"        <createIndex indexName=\"idx_persistent_audit_evt_data\"\r\n" +
		"                     tableName=\"jhi_persistent_audit_evt_data\"\r\n" +
		"                     unique=\"false\">\r\n" +
		"            <column name=\"event_id\" type=\"bigint\"/>\r\n" +
		"        </createIndex>\r\n" +
		"        <addForeignKeyConstraint baseColumnNames=\"event_id\"\r\n" +
		"                                 baseTableName=\"jhi_persistent_audit_evt_data\"\r\n" +
		"                                 constraintName=\"fk_evt_pers_audit_evt_data\"\r\n" +
		"                                 referencedColumnNames=\"event_id\"\r\n" +
		"                                 referencedTableName=\"jhi_persistent_audit_event\"/>\r\n" +
		"    </changeSet>\r\n" +
		"    <changeSet author=\"jhipster\" id=\"00000000000002\" context=\"test\">\r\n" +
		"        <createTable tableName=\"jhi_date_time_wrapper\">\r\n" +
		"            <column autoIncrement=\"${autoIncrement}\" name=\"id\" type=\"BIGINT\">\r\n" +
		"                <constraints primaryKey=\"true\" primaryKeyName=\"jhi_date_time_wrapperPK\"/>\r\n" +
		"            </column>\r\n" +
		"            <column name=\"instant\" type=\"timestamp\"/>\r\n" +
		"            <column name=\"local_date_time\" type=\"timestamp\"/>\r\n" +
		"            <column name=\"offset_date_time\" type=\"timestamp\"/>\r\n" +
		"            <column name=\"zoned_date_time\" type=\"timestamp\"/>\r\n" +
		"            <column name=\"local_time\" type=\"time\"/>\r\n" +
		"            <column name=\"offset_time\" type=\"time\"/>\r\n" +
		"            <column name=\"local_date\" type=\"date\"/>\r\n" +
		"        </createTable>\r\n" +
		"    </changeSet>\r\n" +
		"</databaseChangeLog>\r\n";
		return body;
	}

	public String getClassName() {
		return "00000000000000_initial_schema";
	}

	public String getSourceFolder() {
		return "src/main/resources";
	}

}
