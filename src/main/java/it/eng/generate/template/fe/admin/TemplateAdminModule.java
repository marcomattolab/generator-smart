package it.eng.generate.template.fe.admin;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateAdminModule extends AbstractResourceTemplate {

	public TemplateAdminModule(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = 
		"import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';\r\n" +
		"import { RouterModule } from '@angular/router';\r\n" +
		"import { JhiLanguageService } from 'ng-jhipster';\r\n" +
		"import { JhiLanguageHelper } from 'app/core';\r\n" +
		"import { "+Utils.getClassNameCamelCase(conf.getProjectName()) +"SharedModule } from 'app/shared';\r\n" +
		"import { EntityAuditModule } from './entity-audit/entity-audit.module';\r\n" + //Audit
		"/* jhipster-needle-add-admin-module-import - JHipster will add admin modules imports here */\r\n" +
		"import {\r\n" +
		"    adminState,\r\n" +
		"    AuditsComponent,\r\n" +
		"    UserMgmtComponent,\r\n" +
		"    UserMgmtDetailComponent,\r\n" +
		"    UserMgmtUpdateComponent,\r\n" +
		"    UserMgmtDeleteDialogComponent,\r\n" +
		"    LogsComponent,\r\n" +
		"    JhiMetricsMonitoringModalComponent,\r\n" +
		"    JhiMetricsMonitoringComponent,\r\n" +
		"    JhiHealthModalComponent,\r\n" +
		"    JhiHealthCheckComponent,\r\n" +
		"    JhiConfigurationComponent,\r\n" +
		"    JhiDocsComponent\r\n" +
		"} from './';\r\n" +
		"@NgModule({\r\n" +
		"    imports: [\r\n" +
		"        "+Utils.getClassNameCamelCase(conf.getProjectName()) +"SharedModule,\r\n" +
		"        RouterModule.forChild(adminState),\r\n" +
		"        EntityAuditModule\r\n" +
		"        /* jhipster-needle-add-admin-module - JHipster will add admin modules here */\r\n" +
		"    ],\r\n" +
		"    declarations: [\r\n" +
		"        AuditsComponent,\r\n" +
		"        UserMgmtComponent,\r\n" +
		"        UserMgmtDetailComponent,\r\n" +
		"        UserMgmtUpdateComponent,\r\n" +
		"        UserMgmtDeleteDialogComponent,\r\n" +
		"        LogsComponent,\r\n" +
		"        JhiConfigurationComponent,\r\n" +
		"        JhiHealthCheckComponent,\r\n" +
		"        JhiHealthModalComponent,\r\n" +
		"        JhiDocsComponent,\r\n" +
		"        JhiMetricsMonitoringComponent,\r\n" +
		"        JhiMetricsMonitoringModalComponent\r\n" +
		"    ],\r\n" +
		"    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],\r\n" +
		"    entryComponents: [UserMgmtDeleteDialogComponent, JhiHealthModalComponent, JhiMetricsMonitoringModalComponent],\r\n" +
		"    schemas: [CUSTOM_ELEMENTS_SCHEMA]\r\n" +
		"})\r\n" +
		"export class "+Utils.getClassNameCamelCase(conf.getProjectName()) +"AdminModule {\r\n" +
		"    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {\r\n" +
		"        this.languageHelper.language.subscribe((languageKey: string) => {\r\n" +
		"            if (languageKey !== undefined) {\r\n" +
		"                this.languageService.changeLanguage(languageKey);\r\n" +
		"            }\r\n" +
		"        });\r\n" +
		"    }\r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "admin.module";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/webapp/app/admin";
	}

}
