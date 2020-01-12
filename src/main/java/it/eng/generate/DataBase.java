package it.eng.generate;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import it.eng.generate.project.ApplicationApp;
import it.eng.generate.project.TemplateAngular;
import it.eng.generate.project.TemplateApplicationWebXml;
import it.eng.generate.project.TemplateClassPath;
import it.eng.generate.project.TemplateFactoryPath;
import it.eng.generate.project.TemplateLoggingAspect;
import it.eng.generate.project.TemplatePackage;
import it.eng.generate.project.TemplatePackageLock;
import it.eng.generate.project.TemplatePom;
import it.eng.generate.template.conf.TemplateApplicationProperties;
import it.eng.generate.template.conf.TemplateAsyncConfiguration;
import it.eng.generate.template.conf.TemplateCacheConfiguration;
import it.eng.generate.template.conf.TemplateCloudDatabaseConfiguration;
import it.eng.generate.template.conf.TemplateCostants;
import it.eng.generate.template.conf.TemplateDatabaseConfiguration;
import it.eng.generate.template.conf.TemplateDateTimeFormatConfiguration;
import it.eng.generate.template.conf.TemplateDefaultProfileUtil;
import it.eng.generate.template.conf.TemplateJacksonConfiguration;
import it.eng.generate.template.conf.TemplateLiquidbaseConfiguration;
import it.eng.generate.template.conf.TemplateLocaleConfiguration;
import it.eng.generate.template.conf.TemplateLoggingAspectConfiguration;
import it.eng.generate.template.conf.TemplateLoggingConfiguration;
import it.eng.generate.template.conf.TemplateMetricsConfiguration;
import it.eng.generate.template.conf.TemplateSecurityConfiguration;
import it.eng.generate.template.conf.TemplateWebConfigurer;
import it.eng.generate.template.conf.audit.TemplateAsyncEntityAuditEventWriter;
import it.eng.generate.template.conf.audit.TemplateAuditEventConverter;
import it.eng.generate.template.conf.audit.TemplateEntityAuditAction;
import it.eng.generate.template.conf.audit.TemplateEntityAuditEventConfig;
import it.eng.generate.template.conf.audit.TemplateEntityAuditEventListener;
import it.eng.generate.template.domain.TemplateAbstractAuditingEntity;
import it.eng.generate.template.domain.TemplateAuthority;
import it.eng.generate.template.domain.TemplateDomain;
import it.eng.generate.template.domain.TemplateDomainEnumeration;
import it.eng.generate.template.domain.TemplateEntityAuditEvent;
import it.eng.generate.template.domain.TemplatePersistentAudit;
import it.eng.generate.template.domain.TemplateUser;
import it.eng.generate.template.fe.TemplateAppMain;
import it.eng.generate.template.fe.TemplateAppModule;
import it.eng.generate.template.fe.TemplateAppRoutingModule;
import it.eng.generate.template.fe.TemplateIndex;
import it.eng.generate.template.fe.TemplateManifest;
import it.eng.generate.template.fe.account.TemplateAccountModule;
import it.eng.generate.template.fe.admin.TemplateAdminEntityAuditModule;
import it.eng.generate.template.fe.admin.TemplateAdminModule;
import it.eng.generate.template.fe.admin.TemplateConfigurationService;
import it.eng.generate.template.fe.blocks.TemplateErrorehandlerInterceptor;
import it.eng.generate.template.fe.core.TemplateCoreModule;
import it.eng.generate.template.fe.core.TemplateLanguageHelper;
import it.eng.generate.template.fe.dashboard.TemplateDashboardBarchartModule;
import it.eng.generate.template.fe.dashboard.TemplateDashboardDoughnutchartModule;
import it.eng.generate.template.fe.dashboard.TemplateDashboardLinechartModule;
import it.eng.generate.template.fe.dashboard.TemplateDashboardModule;
import it.eng.generate.template.fe.dashboard.TemplateDashboardPiechartModule;
import it.eng.generate.template.fe.dashboard.TemplateDashboardPolarareachartModule;
import it.eng.generate.template.fe.dashboard.TemplateDashboardRadarchartModule;
import it.eng.generate.template.fe.entities.TemplateEntityComponentHtml;
import it.eng.generate.template.fe.entities.TemplateEntityComponentTs;
import it.eng.generate.template.fe.entities.TemplateEntityDeleteComponentHtml;
import it.eng.generate.template.fe.entities.TemplateEntityDeleteComponentTs;
import it.eng.generate.template.fe.entities.TemplateEntityDetailComponentHtml;
import it.eng.generate.template.fe.entities.TemplateEntityDetailComponentTs;
import it.eng.generate.template.fe.entities.TemplateEntityIndex;
import it.eng.generate.template.fe.entities.TemplateEntityModule;
import it.eng.generate.template.fe.entities.TemplateEntityRoute;
import it.eng.generate.template.fe.entities.TemplateEntityService;
import it.eng.generate.template.fe.entities.TemplateEntityUpdateComponentHtml;
import it.eng.generate.template.fe.entities.TemplateEntityUpdateComponentTs;
import it.eng.generate.template.fe.entities.TemplateModule;
import it.eng.generate.template.fe.home.TemplateHomeModule;
import it.eng.generate.template.fe.i18n.TemplateEntityI18N;
import it.eng.generate.template.fe.i18n.TemplateEnumerationsI18N;
import it.eng.generate.template.fe.i18n.TemplateGlobalI18N;
import it.eng.generate.template.fe.layouts.TemplateMainComponent;
import it.eng.generate.template.fe.layouts.TemplateNavbarComponent;
import it.eng.generate.template.fe.shared.TemplateAlertErrorComponent;
import it.eng.generate.template.fe.shared.TemplateEntitySharedModel;
import it.eng.generate.template.fe.shared.TemplateSharedCommonModule;
import it.eng.generate.template.fe.shared.TemplateSharedLibsModule;
import it.eng.generate.template.fe.shared.TemplateSharedModule;
import it.eng.generate.template.ionic.TemplateEntitiesModule;
import it.eng.generate.template.ionic.TemplateEntitiesPage;
import it.eng.generate.template.ionic.TemplateEntityDetailModuleIonic;
import it.eng.generate.template.ionic.TemplateEntityIndexIonic;
import it.eng.generate.template.ionic.TemplateEntityIonic;
import it.eng.generate.template.ionic.TemplateEntityModel;
import it.eng.generate.template.ionic.TemplateEntityModuleIonic;
import it.eng.generate.template.ionic.TemplateEntityServiceIonic;
import it.eng.generate.template.report.TemplateReportUtils;
import it.eng.generate.template.repository.TemplateAuthorityRepository;
import it.eng.generate.template.repository.TemplateCustomAuditEventRepository;
import it.eng.generate.template.repository.TemplateEntityAuditEventRepository;
import it.eng.generate.template.repository.TemplatePersistenceAuditEventRepository;
import it.eng.generate.template.repository.TemplateRepository;
import it.eng.generate.template.repository.TemplateUserRepository;
import it.eng.generate.template.resouces.TemplateApplication;
import it.eng.generate.template.resouces.TemplateApplicationDev;
import it.eng.generate.template.resouces.TemplateApplicationProd;
import it.eng.generate.template.resouces.TemplateBanner;
import it.eng.generate.template.resouces.TemplateI18N;
import it.eng.generate.template.resouces.TemplateLiquidbaseChangelog;
import it.eng.generate.template.resouces.TemplateLiquidbaseChangelogConstraint;
import it.eng.generate.template.resouces.TemplateLiquidbaseMaster;
import it.eng.generate.template.resouces.TemplateLiquidbaseMasterInitialSchema;
import it.eng.generate.template.resouces.TemplateMessage;
import it.eng.generate.template.security.TemplateAuthoritiesConstants;
import it.eng.generate.template.security.TemplateDomainUserDetailsService;
import it.eng.generate.template.security.TemplateSecurityJWTConfigurer;
import it.eng.generate.template.security.TemplateSecurityJWTFilter;
import it.eng.generate.template.security.TemplateSecurityTokenProvider;
import it.eng.generate.template.security.TemplateSecurityUtils;
import it.eng.generate.template.security.TemplateSpringSecurityAuditorAware;
import it.eng.generate.template.security.TemplateUserNotActivatedException;
import it.eng.generate.template.service.TemplateAbstractAuditingDTO;
import it.eng.generate.template.service.TemplateAuditEventService;
import it.eng.generate.template.service.TemplateEntityMapperService;
import it.eng.generate.template.service.TemplateMailService;
import it.eng.generate.template.service.TemplateMapperService;
import it.eng.generate.template.service.TemplatePasswordChangeDTO;
import it.eng.generate.template.service.TemplateQueryService;
import it.eng.generate.template.service.TemplateRandomUtil;
import it.eng.generate.template.service.TemplateService;
import it.eng.generate.template.service.TemplateServiceCriteria;
import it.eng.generate.template.service.TemplateServiceDTO;
import it.eng.generate.template.service.TemplateServiceImpl;
import it.eng.generate.template.service.TemplateUserDTO;
import it.eng.generate.template.service.TemplateUserMapperService;
import it.eng.generate.template.service.TemplateUserService;
import it.eng.generate.template.test.TemplateAccountResourceIntTest;
import it.eng.generate.template.test.TemplateAuditResourceIntTest;
import it.eng.generate.template.test.TemplateCustomAuditEventRepositoryIntTest;
import it.eng.generate.template.test.TemplateDateTimeWrapper;
import it.eng.generate.template.test.TemplateDateTimeWrapperRepository;
import it.eng.generate.template.test.TemplateDomainUserDetailsServiceIntTest;
import it.eng.generate.template.test.TemplateExceptionTranslatorIntTest;
import it.eng.generate.template.test.TemplateExceptionTranslatorTestController;
import it.eng.generate.template.test.TemplateHibernateTimeZoneTest;
import it.eng.generate.template.test.TemplateLogsResourceIntTest;
import it.eng.generate.template.test.TemplateMailServiceIntTest;
import it.eng.generate.template.test.TemplatePaginationUtilUnitTest;
import it.eng.generate.template.test.TemplateSecurityUtilsUnitTest;
import it.eng.generate.template.test.TemplateTestUtil;
import it.eng.generate.template.test.TemplateUserResourceIntTest;
import it.eng.generate.template.test.TemplateUserServiceIntTest;
import it.eng.generate.template.test.TemplateWebConfigurerTest;
import it.eng.generate.template.test.TemplateWebConfigurerTestController;
import it.eng.generate.template.util.TemplateCopyAll;
import it.eng.generate.template.util.TemplateDeleteTest;
import it.eng.generate.template.web.TemplateAccountResource;
import it.eng.generate.template.web.TemplateAuditResource;
import it.eng.generate.template.web.TemplateEntityAuditResource;
import it.eng.generate.template.web.TemplateLogsResource;
import it.eng.generate.template.web.TemplateReportBase;
import it.eng.generate.template.web.TemplateResource;
import it.eng.generate.template.web.TemplateUserJWTController;
import it.eng.generate.template.web.TemplateUserResource;
import it.eng.generate.template.web.errors.TemplateBadRequestAlertException;
import it.eng.generate.template.web.errors.TemplateCustomParameterizedException;
import it.eng.generate.template.web.errors.TemplateEmailAlreadyUsedException;
import it.eng.generate.template.web.errors.TemplateEmailNotFoundException;
import it.eng.generate.template.web.errors.TemplateErrorConstants;
import it.eng.generate.template.web.errors.TemplateExceptionTranslator;
import it.eng.generate.template.web.errors.TemplateFieldErrorVM;
import it.eng.generate.template.web.errors.TemplateInternalServerErrorException;
import it.eng.generate.template.web.errors.TemplateInvalidPasswordException;
import it.eng.generate.template.web.errors.TemplateLoginAlreadyUsedException;
import it.eng.generate.template.web.util.TemplateHeaderUtil;
import it.eng.generate.template.web.util.TemplatePaginationUtil;
import it.eng.generate.template.web.vm.TemplateKeyAndPasswordVM;
import it.eng.generate.template.web.vm.TemplateLoggerVM;
import it.eng.generate.template.web.vm.TemplateLoginVM;
import it.eng.generate.template.web.vm.TemplateManagedUserVM;

public class DataBase {
	public Map<String, Table> tabelle;
	public Map<String, List<String>> enumeration;
	private static DataBase conf;

	private DataBase(){
		tabelle = new HashMap<>();
		enumeration = new HashMap<>();
	}

	public static DataBase getInstance(){
		if(conf==null){
			conf = new DataBase();
			try {
				System.out.println("Loading database...");
				conf.init();
				System.out.println("\nDatabase has been succesfully loaded...\n\n");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conf; 
	}

	public Table getTables(String tabellaName) {
		return (Table) tabelle.get(tabellaName);
	}

	public Map<String, List<String>> getEnumeration() {
		return enumeration;
	}

	public Set<String> getTableName() {
		return tabelle.keySet();
	}

	public void init() throws ClassNotFoundException, SQLException {
		ConfigCreateProject ccp = ConfigCreateProject.getIstance();
		System.out.println("## ReverseEngineeringDB is "  + (ccp.isEnableReverseEngineeringDB() ? "Enabled" : "NOT Enabled") );
		
		//CASE A - Reverse Engineering for DataBase
		if (ccp.isEnableReverseEngineeringDB()) {
			
			//Inizializzazione Database
			Class.forName(ccp.getDriver());
			Connection con = DriverManager.getConnection(ccp.getUrlConnection(), ccp.getUsername(),ccp.getPassword());
			DatabaseMetaData dmd = con.getMetaData();
			String[] types = {"TABLE", "VIEW"};
			ResultSet rstabelle = dmd.getTables(ccp.getDataBaseName(),ccp.getOwner(), "%"+ccp.getTablePartName()+"%", types);
			System.out.println("DataBaseName:" + ccp.getDataBaseName() + " Owner:" + ccp.getOwner());

			//A - Generate Tables
			while(rstabelle.next()) {
				String tableName = rstabelle.getString("TABLE_NAME");
				Table table = new Table();
				table.setNomeTabellaCompleto(tableName);
				table.setNomeTabella(tableName.substring(ccp.getTablePartName().length()));
				this.addTable(tableName, table);
				ResultSet rs3 = dmd.getColumns(ccp.getDataBaseName(),ccp.getOwner(),tableName,"%%");

				System.out.println("# TableName: "+tableName);
				while(rs3.next()) {
					String columnName = (String) rs3.getObject("COLUMN_NAME");
					Integer typeColumn = (Integer) rs3.getObject("DATA_TYPE");
					Integer columnSize = (Integer) rs3.getObject("COLUMN_SIZE");
					String isNullable = (String) rs3.getObject("IS_NULLABLE");

					System.out.println(" - Column:"+columnName+" Type:"+typeColumn+" Size:"+columnSize+" Nullable:"+isNullable);

					Column column = new Column();
					column.setName(columnName);
					column.setTypeColumn(typeColumn.intValue());
					if(columnSize!=null) {
						column.setColumnSize(columnSize.intValue());
					}
					if((isNullable!=null) && isNullable.equals("YES")) {
						column.setNullable();
					}

					table.addColumn(column);
				}

				ResultSet rs4 = dmd.getPrimaryKeys(ccp.getDataBaseName(), ccp.getOwner(),tableName);
				while(rs4.next()) {
					String key = (String) rs4.getObject(4);
					table.getColumn(key).setKey();
				}

			}	

			//Build Enumerations
			buildEnumerations(ccp);
			
		} else {
			
			//CASE B - Generate from Project Entities
			List<ProjectEntity> prjEntities = ccp.getProjectEntities();
			
			if (!CollectionUtils.isEmpty(prjEntities)) {
				for(ProjectEntity entity : prjEntities) {
					Table table = new Table();
					String tableName = entity.getName();
					System.out.println("\n## TableName: "+tableName);
					
					table.setNomeTabellaCompleto(tableName);
					table.setNomeTabella(tableName);
					this.addTable(tableName, table);
					
					int sortColumn = 0;
					for(Field field : entity.getFields()) {
						String columnName = field.getFname();
						String mTypeColumn = field.getFtype();
						boolean isRequired = field.isFrequired();
						Integer columnSize = field.getFsize()!=null ? field.getFsize() : null;
						
						//MinLenght, MaxLenght, Pattern
						Integer columnMinSize = field.getFminlength()!=null ? field.getFminlength() : null;
						Integer columnMaxSize = field.getFmaxlength()!=null ? field.getFmaxlength() : null;
						String pattern = field.getFpattern();
						
						Column column = new Column();
						column.setName(columnName);
						column.setSortColumn(sortColumn++);
						
						int iTypeColmn = Column.corvertModelType(mTypeColumn);
						column.setTypeColumn(iTypeColmn);
						
						if (!isRequired) {
							column.setNullable();
						}
						if(columnSize!=null) {
							column.setColumnSize(columnSize.intValue());
						}
						if(columnMinSize!=null) {
							column.setColumnMinSize(columnMinSize.intValue());
						}
						if(columnMaxSize!=null) {
							column.setColumnMaxSize(columnMaxSize.intValue());
						}
						if(pattern!=null) {
							column.setPattern(pattern);
						}
						
						table.addColumn(column);
						
						System.out.println("  - Column: " + columnName + 
								"  => mType:" + mTypeColumn +
								"   iType:" + iTypeColmn + 
								"   Sort:" + sortColumn +
								"   Type:" + column.getTypeColumn() +
								"   Size:" + columnSize +
								"   Minsize:" + columnMinSize +
								"   Maxsize:" + columnMaxSize +
								"   Pattern:" + pattern +
								"   Required:" + isRequired);
						
						//Set Primary KEY - TODO DEVELOP THIS!
						String key = "id";
						if(table.getColumn(key)!=null) {
							table.getColumn(key).setKey();
						}
						
					}
					
				}
			}
			
			//Build Enumerations
			buildEnumerations(ccp);
			
		}
	}

	/**
	 * Build Enumerations Stuff from JSON
	 * @param ccp ConfigCreateProject
	 */
	private void buildEnumerations(ConfigCreateProject ccp) {
		//Generate Enumerations
		List<ProjectEnum> enums = ccp.getEnumerations();
		System.out.println("\n# Enumeration ");
		for (ProjectEnum projectEnum: enums) {
			String[] values = projectEnum.getValues().split("#");
			System.out.println("    - Define Enumeration: " + projectEnum.getName() + "  -  Values: " + Arrays.asList(values) + "");
			this.addEnumeration(projectEnum.getName(), Arrays.asList(values) );
		}
	}
	
	public void addTable(String tableName, Table table) {
		tabelle.put(tableName, table);
	}

	public void addEnumeration(String name, List<String> values) {
		enumeration.put(name, values);
	}

	public void generateFile() {
		System.out.println("-------------------------------------------------------");
		System.out.println("Generating Project and project Files for BE and FE ...");

		try {
			ConfigCreateProject config = ConfigCreateProject.getIstance();

			//Build Enumerations for Application
			buildEnumerationsNameInColumn(this); 
			
			//Project (statics)
			//new TemplateProject(this).generateTemplate();
			new TemplateClassPath(this).generateTemplate();
			new TemplateFactoryPath(this).generateTemplate();
			new TemplatePom(this).generateTemplate();
			new TemplateAngular(this).generateTemplate();
			new TemplatePackageLock(this).generateTemplate();
			new TemplatePackage(this).generateTemplate();

			//new TemplateJHipsterCostants(this).generateTemplate();
			new TemplateLoggingAspect(this).generateTemplate();
			new TemplateApplicationWebXml(this).generateTemplate();
			new ApplicationApp(this).generateTemplate();

			//Report Utils (statics)
			new TemplateReportUtils(this).generateTemplate();

			//Configuration (statics)
			new TemplateApplicationProperties(this).generateTemplate();
			new TemplateAsyncConfiguration(this).generateTemplate();
			new TemplateCacheConfiguration(this).generateTemplate(); 	
			new TemplateCloudDatabaseConfiguration(this).generateTemplate();
			new TemplateCostants(this).generateTemplate();
			new TemplateDatabaseConfiguration(this).generateTemplate();
			new TemplateDateTimeFormatConfiguration(this).generateTemplate();
			new TemplateDefaultProfileUtil(this).generateTemplate();
			new TemplateJacksonConfiguration(this).generateTemplate();
			new TemplateLiquidbaseConfiguration(this).generateTemplate();
			new TemplateLocaleConfiguration(this).generateTemplate();
			new TemplateLoggingAspectConfiguration(this).generateTemplate();
			new TemplateLoggingConfiguration(this).generateTemplate();
			new TemplateMetricsConfiguration(this).generateTemplate(); 	
			new TemplateSecurityConfiguration(this).generateTemplate();
			new TemplateWebConfigurer(this).generateTemplate();

			//Audit Config
			new TemplateAuditEventConverter(this).generateTemplate();
			new TemplateAsyncEntityAuditEventWriter(this).generateTemplate();
			new TemplateEntityAuditEventListener(this).generateTemplate();
			new TemplateEntityAuditAction(this).generateTemplate();
			new TemplateEntityAuditEventConfig(this).generateTemplate();

			//Domain (statics)
			new TemplateEntityAuditEvent(this).generateTemplate(); 	
			new TemplateAbstractAuditingEntity(this).generateTemplate();
			new TemplatePersistentAudit(this).generateTemplate();
			//new TemplatePersistentToken(this).generateTemplate(); //removed for JWT
			new TemplateAuthority(this).generateTemplate();
			new TemplateUser(this).generateTemplate();

			//Repository (statics)
			new TemplateEntityAuditEventRepository(this).generateTemplate();		
			new TemplateAuthorityRepository(this).generateTemplate();
			new TemplateCustomAuditEventRepository(this).generateTemplate();
			new TemplatePersistenceAuditEventRepository(this).generateTemplate();
			//new TemplatePersistenceTokenRepository(this).generateTemplate(); //removed for JWT
			new TemplateUserRepository(this).generateTemplate();

			//RETRIEVE BY JDL/DB/PROPERTY DYNAMIC
			List<Enumeration> enumerations = buildEnumerationsMap();
			for (Enumeration cEnum : enumerations) {
				new TemplateDomainEnumeration(cEnum).generateTemplate();
			}

			//Security (statics)
			new TemplateAuthoritiesConstants(this).generateTemplate();
			new TemplateDomainUserDetailsService(this).generateTemplate();
			new TemplateSecurityUtils(this).generateTemplate();
			//new TemplatePersistentTokenRememberMeServices(this).generateTemplate(); //removed for JWT
			new TemplateSpringSecurityAuditorAware(this).generateTemplate();
			new TemplateUserNotActivatedException(this).generateTemplate();
			//Add Security JWT
			new TemplateSecurityJWTConfigurer(this).generateTemplate();
			new TemplateSecurityJWTFilter(this).generateTemplate();
			new TemplateSecurityTokenProvider(this).generateTemplate();
			
			//Service (statics)
			new TemplateRandomUtil(this).generateTemplate();
			new TemplateMailService(this).generateTemplate();
			new TemplateUserService(this).generateTemplate();
			new TemplateAuditEventService(this).generateTemplate();
			new TemplateUserMapperService(this).generateTemplate();
			new TemplateEntityMapperService(this).generateTemplate();

			//DTO (statics)
			new TemplateUserDTO(this).generateTemplate();
			new TemplatePasswordChangeDTO(this).generateTemplate();
			new TemplateAbstractAuditingDTO(this).generateTemplate();  		

			//WEB.REST (statics)
			new TemplateEntityAuditResource(this).generateTemplate(); 	
			new TemplateAccountResource(this).generateTemplate();
			new TemplateAuditResource(this).generateTemplate();
			new TemplateUserResource(this).generateTemplate();
			new TemplateUserJWTController(this).generateTemplate();
			new TemplateLogsResource(this).generateTemplate();
			new TemplateReportBase(this).generateTemplate();				//Dynamic Jasper	

			//WEB.REST.UTILS (statics)
			new TemplateHeaderUtil(this).generateTemplate();
			new TemplatePaginationUtil(this).generateTemplate();

			//WEB.REST.VM (statics)
			new TemplateKeyAndPasswordVM(this).generateTemplate();
			new TemplateLoginVM(this).generateTemplate();
			new TemplateLoggerVM(this).generateTemplate();
			new TemplateManagedUserVM(this).generateTemplate();

			//WEB.REST.ERRORS (statics)
			new TemplateBadRequestAlertException(this).generateTemplate();
			new TemplateCustomParameterizedException(this).generateTemplate();
			new TemplateEmailAlreadyUsedException(this).generateTemplate();
			new TemplateEmailNotFoundException(this).generateTemplate();
			new TemplateErrorConstants(this).generateTemplate();
			new TemplateExceptionTranslator(this).generateTemplate();
			new TemplateFieldErrorVM(this).generateTemplate();
			new TemplateInternalServerErrorException(this).generateTemplate();
			new TemplateInvalidPasswordException(this).generateTemplate();
			new TemplateLoginAlreadyUsedException(this).generateTemplate();

			//RESOURCES START
			new TemplateBanner(this).generateTemplate();
			new TemplateApplication(this).generateTemplate();
			new TemplateApplicationProd(this).generateTemplate();
			new TemplateApplicationDev(this).generateTemplate();
			new TemplateLiquidbaseMasterInitialSchema(this).generateTemplate(); 
			new TemplateLiquidbaseMaster(this).generateTemplate(); 					
			new TemplateMessage(this).generateTemplate();

			//Copy All Template
			new TemplateCopyAll(this).generateTemplate();
			

			//Frontend
			new TemplateManifest(this).generateTemplate(); 
			new TemplateIndex(this).generateTemplate(); 
			new TemplateAppModule(this).generateTemplate(); 
			new TemplateAppMain(this).generateTemplate(); 
			new TemplateAppRoutingModule(this).generateTemplate(); 
			new TemplateAccountModule(this).generateTemplate(); 
			new TemplateAdminModule(this).generateTemplate(); 
			new TemplateAdminEntityAuditModule(this).generateTemplate(); 			//Audit Module TS
			new TemplateDashboardModule(this).generateTemplate();  					//Chart Dashboard
			new TemplateDashboardBarchartModule(this).generateTemplate();  			//Barchart Dashboard
			new TemplateDashboardDoughnutchartModule(this).generateTemplate();  	//Doughnutchart Dashboard
			new TemplateDashboardLinechartModule(this).generateTemplate();  		//Linechart Dashboard
			new TemplateDashboardPiechartModule(this).generateTemplate();  			//Piechart Dashboard
			new TemplateDashboardPolarareachartModule(this).generateTemplate(); 	//Polarareachart Dashboard
			new TemplateDashboardRadarchartModule(this).generateTemplate();  		//Radarchart Dashboard
			new TemplateConfigurationService(this).generateTemplate(); 
			new TemplateErrorehandlerInterceptor(this).generateTemplate(); 
			new TemplateCoreModule(this).generateTemplate(); 
			new TemplateLanguageHelper(this).generateTemplate(); 
			new TemplateHomeModule(this).generateTemplate(); 
			new TemplateNavbarComponent(this).generateTemplate(); 					//Cicle Entities Done
			new TemplateMainComponent(this).generateTemplate(); 
			new TemplateSharedModule(this).generateTemplate(); 
			new TemplateSharedLibsModule(this).generateTemplate(); 
			new TemplateSharedCommonModule(this).generateTemplate(); 
			new TemplateAlertErrorComponent(this).generateTemplate(); 
			new TemplateModule(this).generateTemplate(); 							//Cicle Entities Done

			
			//Mobile Ionic (Statics)
			new TemplateEntitiesModule(this).generateTemplate(); 			
			new TemplateEntitiesPage(this).generateTemplate(); 	
			
			
			
			
			
			//TEST Classes - TODO DEVELOP THIS!!
			if (config.isGenerateTest()) {
				new TemplateWebConfigurerTest(this).generateTemplate();
				new TemplateWebConfigurerTestController(this).generateTemplate();
				new TemplateHibernateTimeZoneTest(this).generateTemplate();
				new TemplateCustomAuditEventRepositoryIntTest(this).generateTemplate();
				new TemplateDateTimeWrapperRepository(this).generateTemplate();
				new TemplateDateTimeWrapper(this).generateTemplate();
				new TemplateSecurityUtilsUnitTest(this).generateTemplate();
				new TemplateDomainUserDetailsServiceIntTest(this).generateTemplate();
				new TemplateMailServiceIntTest(this).generateTemplate();
				new TemplateUserServiceIntTest(this).generateTemplate();
				new TemplatePaginationUtilUnitTest(this).generateTemplate();
				new TemplateExceptionTranslatorIntTest(this).generateTemplate();
				new TemplateExceptionTranslatorTestController(this).generateTemplate();
				new TemplateTestUtil(this).generateTemplate();
				new TemplateAccountResourceIntTest(this).generateTemplate();
				new TemplateAuditResourceIntTest(this).generateTemplate();
				new TemplateLogsResourceIntTest(this).generateTemplate();
				new TemplateUserResourceIntTest(this).generateTemplate();
			}

			//Building Data of All Enumerations - CHECK OR REMOVE THIS FIXME!
			List<Enumeration> enumList = new ArrayList<>();
			Map<String, List<String>> map = this.getEnumeration();
			for(String enumName: map.keySet()) {
				enumList.add( new Enumeration(enumName, map.get(enumName)) );
			}

			//All Other Level (dynamics) 
			System.out.println("Creating table dynamics in progress... ");
			for (Table tabella : Utils.getTables(this)) {
				System.out.println("\n# Generating table : "+tabella.getNomeTabella());
				new TemplateDomain(tabella).generateTemplate();
				new TemplateRepository(tabella).generateTemplate();
				new TemplateService(tabella).generateTemplate();   
				new TemplateServiceImpl(tabella).generateTemplate();
				new TemplateQueryService(tabella).generateTemplate();
				new TemplateMapperService(tabella).generateTemplate();
				new TemplateServiceDTO(tabella).generateTemplate();
				new TemplateServiceCriteria(this, tabella).generateTemplate(); 				//TODO Add enumeration management
				new TemplateResource(tabella).generateTemplate();
				new TemplateLiquidbaseChangelog(tabella).generateTemplate(); 	 			//TODO COMPLETE THIS  !!
				if (Utils.havingConstraints(config, tabella)) {
					new TemplateLiquidbaseChangelogConstraint(tabella).generateTemplate();	//TODO COMPLETE THIS  !!
				}
				//new TemplateIntTest(tabella).generateTemplate(); 							//TODO COMPLETE THIS  !!
				
				//MultiLanguages
				for(String languageCode: config.getLanguages()) {
					new TemplateEntityI18N(tabella, languageCode).generateTemplate();  
				}

				new TemplateEntityIndex(tabella).generateTemplate(); 
				new TemplateEntityService(tabella).generateTemplate();  					//DONE MANAGE DATES
				new TemplateEntityRoute(tabella).generateTemplate(); 
				new TemplateEntityModule(tabella).generateTemplate(); 
				new TemplateEntityComponentTs(this, tabella).generateTemplate(); 
				new TemplateEntityComponentHtml(this, tabella).generateTemplate(); 			
				new TemplateEntityUpdateComponentTs(tabella).generateTemplate(); 		
				new TemplateEntityUpdateComponentHtml(this, tabella).generateTemplate(); 	//DONE DEVELOP ENUMS AND DATES
				new TemplateEntityDetailComponentTs(tabella).generateTemplate(); 		
				new TemplateEntityDetailComponentHtml(tabella).generateTemplate(); 
				new TemplateEntityDeleteComponentTs(tabella).generateTemplate(); 		
				new TemplateEntityDeleteComponentHtml(tabella).generateTemplate(); 	
				new TemplateEntitySharedModel(this, tabella).generateTemplate(); 			//DONE COMPLETE ENUM
				
				
				//Mobile Ionic (Dynamics) - TODO DEVELOP 5/11 files
				new TemplateEntityIndexIonic(tabella).generateTemplate(); 
				new TemplateEntityModel(this, tabella).generateTemplate(); 	
				new TemplateEntityModuleIonic(tabella).generateTemplate(); 
				new TemplateEntityServiceIonic(tabella).generateTemplate();  			
				new TemplateEntityIonic(tabella).generateTemplate();  		
				new TemplateEntityDetailModuleIonic(tabella).generateTemplate();  		
			
			
			
			}

			//MultiLanguages 
			for(String languageCode: config.getLanguages()) {
				new TemplateGlobalI18N(this, languageCode).generateTemplate();  
				new TemplateI18N(this, languageCode).generateTemplate();  
				//Enumeration I18N
				for(Enumeration e : enumList) {
					new TemplateEnumerationsI18N(e, languageCode).generateTemplate();  
				}
			}
			
			
			//DELETE TEST FOLDER
			if ( !config.isGenerateTest() ) {
				new TemplateDeleteTest(this).generateTemplate();
			}

			System.out.println("\nGenerating Project Files Succesfully Completed. Try It!");
			System.out.println("--------------------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}catch(Throwable t){
			t.printStackTrace();
		}
	}

	/**
	 * Retrieve Enumerations from external configuration file.
	 * @return List<Enumeration>
	 */
	private List<Enumeration> buildEnumerationsMap() {
		System.out.println("Build enumerations map... ");
		List<Enumeration> enumerations = new ArrayList<>();
		for ( String enumName : this.enumeration.keySet() ) {
			List<String> enumValues = this.getEnumeration().get(enumName);
			enumerations.add(new Enumeration(enumName, enumValues));
		}
		return enumerations;
	}
	
	/**
	 * Build Enumerations Name for all columns about application
	 * @param db
	 * @return db
	 */
	private DataBase buildEnumerationsNameInColumn(DataBase db) {
		DataBase dataBase = db;
		Set<?> set = dataBase.getTableName();
		for (Iterator<?> iter = set.iterator(); iter.hasNext();) {
			String tabellaName = (String) iter.next();
			Table tabella = dataBase.getTables(tabellaName);

			Set<?> cset = tabella.getColumnNames();
			for (Iterator<?> citer = cset.iterator(); citer.hasNext();) {
				String columnName = (String) citer.next();
				Column column = tabella.getColumn(columnName);
				String enumeration = findEnumerationName(tabellaName, columnName);
				if(enumeration!=null && enumeration.length()>0) {
					System.out.println("@ Added Enum tabellaName.columnName.enum ===> "+tabellaName+"."+columnName+"."+enumeration);
					column.setEnumeration(enumeration);
				}
			}
		}
		return dataBase;
	}
	
	/**
	 * Retrieve Enumeration Name from configuration file.
	 * @param tabellaName
	 * @param columnName
	 * @return EnumerationName
	 */
	private String findEnumerationName(String tabellaName, String columnName) {
		String enumerationName = null;
		HashMap<String, List<String>> enums = Utils.filterEnumeration(tabellaName, columnName);
		if(enums!=null) {
	        for (String key : enums.keySet()) {
	            enumerationName = key;
	        }
		}
		return enumerationName;
	}
	
	public String toString() {
		String ret ="";
		Set<String> set = tabelle.keySet();
		for (Iterator<String> iter = set.iterator(); iter.hasNext();) {
			String tablename = (String) iter.next();
			Table table = (Table)tabelle.get(tablename);
			ret+="\n"+table;			
		}
		return ret;
	}

	/**
	 * Smart Generator Main Procedure.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		DataBase db = DataBase.getInstance();
		db.generateFile();
	}

}
