package it.eng.generate;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectConfig {
	private String owner;
	private Integer defaultColumnSize;
	private String tablePartName;
    private boolean generateTest;
    private boolean enableReverseEngineeringDB;
    private String app;
    private String driver;
    private String dataBaseName;
    private String packageclass;
    private String username;
    private String password;
    private String pathname;
    private String projectName;
    private String urlConnection;
    private List<String> languages;
    
    @JsonProperty("entities")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<ProjectEntity> entities;
    
    @JsonProperty("enumerations")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<ProjectEnum> enumerations;
    
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getDataBaseName() {
		return dataBaseName;
	}
	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}
	public String getPackageclass() {
		return packageclass;
	}
	public void setPackageclass(String packageclass) {
		this.packageclass = packageclass;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPathname() {
		return pathname;
	}
	public void setPathname(String pathname) {
		this.pathname = pathname;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getUrlConnection() {
		return urlConnection;
	}
	public void setUrlConnection(String urlConnection) {
		this.urlConnection = urlConnection;
	}
	public List<String> getLanguages() {
		return languages;
	}
	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}
	public boolean isGenerateTest() {
		return generateTest;
	}
	public void setGenerateTest(boolean generateTest) {
		this.generateTest = generateTest;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getTablePartName() {
		return tablePartName;
	}
	public void setTablePartName(String tablePartName) {
		this.tablePartName = tablePartName;
	}
	public List<ProjectEnum> getEnumerations() {
		return enumerations;
	}
	public void setEnumerations(List<ProjectEnum> enumerations) {
		this.enumerations = enumerations;
	}
	public List<ProjectEntity> getEntities() {
		return entities;
	}
	public void setEntities(List<ProjectEntity> entities) {
		this.entities = entities;
	}
	public boolean isEnableReverseEngineeringDB() {
		return enableReverseEngineeringDB;
	}
	public void setEnableReverseEngineeringDB(boolean enableReverseEngineeringDB) {
		this.enableReverseEngineeringDB = enableReverseEngineeringDB;
	}
	public Integer getDefaultColumnSize() {
		return defaultColumnSize;
	}
	public void setDefaultColumnSize(Integer defaultColumnSize) {
		this.defaultColumnSize = defaultColumnSize;
	}
	
}
