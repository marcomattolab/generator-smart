package it.eng.generate;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigCreateProject {
	//TODO CHANGE_ME To Modify Destination Path about generated code!!!
	//private static final boolean USE_PATHNAME_ENV_B = true; //APPLE
	private static final boolean USE_PATHNAME_ENV_B = false; //WINDOWS
	
	//FIXME ENABLE PRINT RELATION ON MOBILE
	private static final boolean printRelation = true; 
	
	private boolean isOracle;
	private boolean isMysql;
	private boolean generateTest;
	private boolean enableReverseEngineeringDB;
	private String urlConnection;
	private String username;
	private String password;
	private String owner;
	private String pathname;
	private String packageclass;
	private String app;
	private String driver;
	private String dataBaseName;
	private String tablePartName;
	private List<ProjectEnum> enumerations;
	private List<ProjectEntity> projectEntities;
	private List<ProjectRelation> projectRelations;
	
	private String srcAopLoggingFolder = "aop.logging";
	private String srcConfigFolder = "config";
	private String srcConfigTimezoneFolder = "config.timezone";
	private String srcConfigAuditFolder = "config.audit";
	private String srcDomainFolder = "domain";
	private String srcDomainEnumerationFolder = "domain.enumeration";
	private String srcRepositoryFolder = "repository";
	private String srcRepositoryTimezoneFolder = "repository.timezone";
	private String srcSecurityFolder = "security";
	private String srcSecurityJWTFolder = "security.jwt";
	private String srcServiceFolder = "service";
	private String srcReportUtilFolder = "report.util";
	private String srcServiceDtoFolder = "service.dto";
	private String srcServiceImplFolder = "service.impl";
	private String srcServiceMapperFolder = "service.mapper";
	private String srcServiceUtilFolder = "service.util";
	private String srcWebFolder = "web";
	private String srcWebRestFolder = "web.rest";
	private String srcWebRestErrorsFolder = "web.rest.errors";
	private String srcWebRestUtilFolder = "web.rest.util";
	private String srcWebRestVmFolder = "web.rest.vm";
	
	private String resi18nFolder = "i18n";
	private String resTemplatesFolder = "templates";
	private String resConfigFolder = "config";
	private String resConfigTksFolder = "config.tls";
	private String resConfigLiquibaseFolder = "config.liquibase";
	private String resConfigLiquibaseChangelogFolder = "config.liquibase.changelog";
	
	private String projectName;
	
	private String[] languages;
	
	private static ConfigCreateProject ccp;
	
	private ConfigCreateProject(){
		init();
	}
	
	public static ConfigCreateProject getIstance(){
		if(ccp==null) {
			ccp = new ConfigCreateProject();
		}
		return ccp;
	}


	private void init() {
		ProjectConfig jsonConf = readProjectJson();

		this.setGenerateTest(jsonConf.isGenerateTest());
		this.setEnableReverseEngineeringDB(jsonConf.isEnableReverseEngineeringDB());
		this.setApp(jsonConf.getApp());
		this.setDriver(jsonConf.getDriver());
		this.setOwner(jsonConf.getOwner());
		this.setDataBaseName(jsonConf.getDataBaseName());
		this.setPackageclass(jsonConf.getPackageclass());
		this.setPassword(jsonConf.getPassword());
		this.setUsername(jsonConf.getUsername());
		this.setTablePartName(jsonConf.getTablePartName());
		this.setPathname(USE_PATHNAME_ENV_B ? jsonConf.getPathnameEnvB() : jsonConf.getPathname());
		this.setProjectName(jsonConf.getProjectName());
		this.setUrlConnection(jsonConf.getUrlConnection());
		this.setEnumerations(jsonConf.getEnumerations());
		this.setLanguages( jsonConf.getLanguages().toArray(new String[jsonConf.getLanguages().size()]) );
		this.setProjectEntities(jsonConf.getEntities());
		this.setProjectRelations(jsonConf.getRelations());
		this.setMysql(jsonConf.getDriver().contains("mysql") ? true : false);
		this.setOracle(jsonConf.getDriver().contains("oracle") ? true : false);
	}
	
	/**
	 * Read Project configuration JSON
	 */
	public ProjectConfig readProjectJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
	        String projectParentPath = System.getProperty("user.dir");
	        System.out.println("# Parent Directory Path : "+ projectParentPath);
	        String PATH = projectParentPath + File.separator + "project.json";
	        System.out.println("## Project File Path : "+ PATH);
	        		
	        ProjectConfig jsonConf = mapper.readValue(new File(PATH), ProjectConfig.class);
	        String prettyJsonConf = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonConf);
	        System.out.println("### Test - Project Configuration JSON => " + prettyJsonConf);
            	
            return jsonConf;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public String getProjectName() {
		return this.projectName;
	}
	
	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}

	public String getDataBaseName() {
		return dataBaseName;
	}

	public void setTablePartName(String tablePartName) {
		this.tablePartName =tablePartName;
	}

	public String getTablePartName() {
		return tablePartName;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getPathname() {
		return pathname;
	}

	public void setPathname(String pathname) {
		this.pathname = pathname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrlConnection() {
		return urlConnection;
	}

	public void setUrlConnection(String urlConnection) {
		this.urlConnection = urlConnection;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPackageclass() {
		return packageclass;
	}

	public void setPackageclass(String packageclass) {
		this.packageclass = packageclass;
	}

	public String getDriver() {
		return driver;
	}
	
	public void setDriver(String driver) {
		this.driver = driver; 
	}
	
	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getSrcAopLoggingFolder() {
		return srcAopLoggingFolder;
	}

	public void setSrcAopLoggingFolder(String srcAopLoggingFolder) {
		this.srcAopLoggingFolder = srcAopLoggingFolder;
	}

	public String getSrcConfigFolder() {
		return srcConfigFolder;
	}

	public void setSrcConfigFolder(String srcConfigFolder) {
		this.srcConfigFolder = srcConfigFolder;
	}

	public String getSrcConfigAuditFolder() {
		return srcConfigAuditFolder;
	}

	public void setSrcConfigAuditFolder(String srcConfigAuditFolder) {
		this.srcConfigAuditFolder = srcConfigAuditFolder;
	}

	public String getSrcDomainFolder() {
		return srcDomainFolder;
	}

	public void setSrcDomainFolder(String srcDomainFolder) {
		this.srcDomainFolder = srcDomainFolder;
	}

	public String getSrcDomainEnumerationFolder() {
		return srcDomainEnumerationFolder;
	}

	public void setSrcDomainEnumerationFolder(String srcDomainEnumerationFolder) {
		this.srcDomainEnumerationFolder = srcDomainEnumerationFolder;
	}

	public String getSrcRepositoryFolder() {
		return srcRepositoryFolder;
	}

	public void setSrcRepositoryFolder(String srcRepositoryFolder) {
		this.srcRepositoryFolder = srcRepositoryFolder;
	}

	public String getSrcSecurityFolder() {
		return srcSecurityFolder;
	}

	public void setSrcSecurityFolder(String srcSecurityFolder) {
		this.srcSecurityFolder = srcSecurityFolder;
	}

	public String getSrcServiceFolder() {
		return srcServiceFolder;
	}

	public void setSrcServiceFolder(String srcServiceFolder) {
		this.srcServiceFolder = srcServiceFolder;
	}

	public String getSrcServiceDtoFolder() {
		return srcServiceDtoFolder;
	}

	public void setSrcServiceDtoFolder(String srcServiceDtoFolder) {
		this.srcServiceDtoFolder = srcServiceDtoFolder;
	}

	public String getSrcServiceImplFolder() {
		return srcServiceImplFolder;
	}

	public void setSrcServiceImplFolder(String srcServiceImplFolder) {
		this.srcServiceImplFolder = srcServiceImplFolder;
	}

	public String getSrcServiceMapperFolder() {
		return srcServiceMapperFolder;
	}

	public void setSrcServiceMapperFolder(String srcServiceMapperFolder) {
		this.srcServiceMapperFolder = srcServiceMapperFolder;
	}

	public String getSrcServiceUtilFolder() {
		return srcServiceUtilFolder;
	}

	public void setSrcServiceUtilFolder(String srcServiceUtilFolder) {
		this.srcServiceUtilFolder = srcServiceUtilFolder;
	}

	public String getSrcWebFolder() {
		return srcWebFolder;
	}

	public void setSrcWebFolder(String srcWebFolder) {
		this.srcWebFolder = srcWebFolder;
	}

	public String getSrcWebRestFolder() {
		return srcWebRestFolder;
	}

	public void setSrcWebRestFolder(String srcWebRestFolder) {
		this.srcWebRestFolder = srcWebRestFolder;
	}

	public String getSrcWebRestErrorsFolder() {
		return srcWebRestErrorsFolder;
	}

	public void setSrcWebRestErrorsFolder(String srcWebRestErrorsFolder) {
		this.srcWebRestErrorsFolder = srcWebRestErrorsFolder;
	}

	public String getSrcWebRestUtilFolder() {
		return srcWebRestUtilFolder;
	}

	public void setSrcWebRestUtilFolder(String srcWebRestUtilFolder) {
		this.srcWebRestUtilFolder = srcWebRestUtilFolder;
	}

	public String getSrcWebRestVmFolder() {
		return srcWebRestVmFolder;
	}

	public void setSrcWebRestVmFolder(String srcWebRestVmFolder) {
		this.srcWebRestVmFolder = srcWebRestVmFolder;
	}

	public String getResi18nFolder() {
		return resi18nFolder;
	}

	public void setResi18nFolder(String resi18nFolder) {
		this.resi18nFolder = resi18nFolder;
	}

	public String getResTemplatesFolder() {
		return resTemplatesFolder;
	}

	public void setResTemplatesFolder(String resTemplatesFolder) {
		this.resTemplatesFolder = resTemplatesFolder;
	}

	public String getResConfigFolder() {
		return resConfigFolder;
	}

	public void setResConfigFolder(String resConfigFolder) {
		this.resConfigFolder = resConfigFolder;
	}

	public String getResConfigTksFolder() {
		return resConfigTksFolder;
	}

	public void setResConfigTksFolder(String resConfigTksFolder) {
		this.resConfigTksFolder = resConfigTksFolder;
	}

	public String getResConfigLiquidbaseFolder() {
		return resConfigLiquibaseFolder;
	}

	public void setResConfigLiquidbaseFolder(String resConfigLiquidbaseFolder) {
		this.resConfigLiquibaseFolder = resConfigLiquidbaseFolder;
	}

	public String getResConfigLiquidbaseChangelogFolder() {
		return resConfigLiquibaseChangelogFolder;
	}

	public void setResConfigLiquidbaseChangelogFolder(String resConfigLiquidbaseChangelogFolder) {
		this.resConfigLiquibaseChangelogFolder = resConfigLiquidbaseChangelogFolder;
	}

	public String getSrcConfigTimezoneFolder() {
		return srcConfigTimezoneFolder;
	}

	public void setSrcConfigTimezoneFolder(String srcConfigTimezoneFolder) {
		this.srcConfigTimezoneFolder = srcConfigTimezoneFolder;
	}

	public String getResConfigLiquibaseFolder() {
		return resConfigLiquibaseFolder;
	}

	public void setResConfigLiquibaseFolder(String resConfigLiquibaseFolder) {
		this.resConfigLiquibaseFolder = resConfigLiquibaseFolder;
	}

	public String getResConfigLiquibaseChangelogFolder() {
		return resConfigLiquibaseChangelogFolder;
	}

	public void setResConfigLiquibaseChangelogFolder(String resConfigLiquibaseChangelogFolder) {
		this.resConfigLiquibaseChangelogFolder = resConfigLiquibaseChangelogFolder;
	}

	public boolean isGenerateTest() {
		return generateTest;
	}

	public void setGenerateTest(boolean generateTest) {
		this.generateTest = generateTest;
	}

	public String getSrcRepositoryTimezoneFolder() {
		return srcRepositoryTimezoneFolder;
	}

	public void setSrcRepositoryTimezoneFolder(String srcRepositoryTimezoneFolder) {
		this.srcRepositoryTimezoneFolder = srcRepositoryTimezoneFolder;
	}

	public String[] getLanguages() {
		return languages;
	}

	public void setLanguages(String[] languages) {
		this.languages = languages;
	}

	public String getSrcReportUtilFolder() {
		return srcReportUtilFolder;
	}

	public void setSrcReportUtilFolder(String srcReportUtilFolder) {
		this.srcReportUtilFolder = srcReportUtilFolder;
	}

	public List<ProjectEnum> getEnumerations() {
		return enumerations;
	}

	public void setEnumerations(List<ProjectEnum> enumerations) {
		this.enumerations = enumerations;
	}

	public boolean isEnableReverseEngineeringDB() {
		return enableReverseEngineeringDB;
	}

	public void setEnableReverseEngineeringDB(boolean enableReverseEngineeringDB) {
		this.enableReverseEngineeringDB = enableReverseEngineeringDB;
	}

	public List<ProjectEntity> getProjectEntities() {
		return projectEntities;
	}

	public void setProjectEntities(List<ProjectEntity> projectEntities) {
		this.projectEntities = projectEntities;
	}
	
	public List<ProjectRelation> getProjectRelations() {
		return projectRelations;
	}
	
	public void setProjectRelations(List<ProjectRelation> projectRelations) {
		this.projectRelations = projectRelations;
	}

	public String getSrcSecurityJWTFolder() {
		return srcSecurityJWTFolder;
	}

	public void setSrcSecurityJWTFolder(String srcSecurityJWTFolder) {
		this.srcSecurityJWTFolder = srcSecurityJWTFolder;
	}

	public boolean isOracle() {
		return isOracle;
	}

	public void setOracle(boolean isOracle) {
		this.isOracle = isOracle;
	}

	public boolean isMysql() {
		return isMysql;
	}

	public void setMysql(boolean isMysql) {
		this.isMysql = isMysql;
	}

	public boolean isPrintRelation() {
		return printRelation;
	}


	public String toString() {
		String ret = "";
		ret+="\nURL CONNECTION:"+urlConnection;
		ret+="\nUSERNAME:"+username;
		ret+="\nPASSWORD:"+password;
		ret+="\nOWNER:"+owner;
		ret+="\nPATHNAME:"+pathname;
		ret+="\nPACKAGE CLASS:"+packageclass;
		ret+="\nDRIVER:"+driver;
		ret+="\nDATABASE NAME"+dataBaseName;
		ret+="\nTABLE PART NAME"+tablePartName;
		return ret;
	}
}
