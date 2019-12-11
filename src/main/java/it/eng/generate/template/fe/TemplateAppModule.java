package it.eng.generate.template.fe;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateAppModule extends AbstractResourceTemplate {

	public TemplateAppModule(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = 
		"import './vendor.ts';\r\n" +
		"import { NgModule } from '@angular/core';\r\n" +
		"import { BrowserModule } from '@angular/platform-browser';\r\n" +
		"import { HTTP_INTERCEPTORS } from '@angular/common/http';\r\n" +
		"import { NgbDatepickerConfig } from '@ng-bootstrap/ng-bootstrap';\r\n" +
		"import { Ng2Webstorage } from 'ngx-webstorage';\r\n" +
		"import { NgJhipsterModule } from 'ng-jhipster';\r\n" +
		"import { AuthExpiredInterceptor } from './blocks/interceptor/auth-expired.interceptor';\r\n" +
		"import { ErrorHandlerInterceptor } from './blocks/interceptor/errorhandler.interceptor';\r\n" +
		"import { NotificationInterceptor } from './blocks/interceptor/notification.interceptor';\r\n" +
		"import { "+Utils.getClassNameCamelCase(conf.getProjectName()) +"SharedModule } from 'app/shared';\r\n" +
		"import { "+Utils.getClassNameCamelCase(conf.getProjectName()) +"CoreModule } from 'app/core';\r\n" +
		"import { "+Utils.getClassNameCamelCase(conf.getProjectName()) +"AppRoutingModule } from './app-routing.module';\r\n" +
		"import { "+Utils.getClassNameCamelCase(conf.getProjectName()) +"HomeModule } from './home/home.module';\r\n" +
		"import { "+Utils.getClassNameCamelCase(conf.getProjectName()) +"AccountModule } from './account/account.module';\r\n" +
		"import { "+Utils.getClassNameCamelCase(conf.getProjectName()) +"EntityModule } from './entities/entity.module';\r\n" +
		"import { "+Utils.getClassNameCamelCase(conf.getProjectName()) +"DashboardModule } from './dashboard/dashboard.module';\r\n" +
		"import * as moment from 'moment';\r\n" +
		"// jhipster-needle-angular-add-module-import JH will add new module here\r\n" +
		"import { JhiMainComponent, NavbarComponent, FooterComponent, PageRibbonComponent, ActiveMenuDirective, ErrorComponent } from './layouts';\r\n" +
		"@NgModule({\r\n" +
		"    imports: [\r\n" +
		"        BrowserModule,\r\n" +
		"        "+Utils.getClassNameCamelCase(conf.getProjectName()) +"AppRoutingModule,\r\n" +
		"        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-' }),\r\n" +
		"        NgJhipsterModule.forRoot({\r\n" +
		"            // set below to true to make alerts look like toast\r\n" +
		"            alertAsToast: false,\r\n" +
		"            alertTimeout: 5000,\r\n" +
		"            i18nEnabled: true,\r\n" +
		"            defaultI18nLang: 'it'\r\n" +
		"        }),\r\n" +
		"        "+Utils.getClassNameCamelCase(conf.getProjectName()) +"SharedModule.forRoot(),\r\n" +
		"        "+Utils.getClassNameCamelCase(conf.getProjectName()) +"CoreModule,\r\n" +
		"        "+Utils.getClassNameCamelCase(conf.getProjectName()) +"HomeModule,\r\n" +
		"        "+Utils.getClassNameCamelCase(conf.getProjectName()) +"AccountModule,\r\n" +
		"        "+Utils.getClassNameCamelCase(conf.getProjectName()) +"DashboardModule,\r\n" +
		"        // jhipster-needle-angular-add-module JHipster will add new module here\r\n" +
		"        "+Utils.getClassNameCamelCase(conf.getProjectName()) +"EntityModule\r\n" +
		"    ],\r\n" +
		"    declarations: [JhiMainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],\r\n" +
		"    providers: [\r\n" +
		"        {\r\n" +
		"            provide: HTTP_INTERCEPTORS,\r\n" +
		"            useClass: AuthExpiredInterceptor,\r\n" +
		"            multi: true\r\n" +
		"        },\r\n" +
		"        {\r\n" +
		"            provide: HTTP_INTERCEPTORS,\r\n" +
		"            useClass: ErrorHandlerInterceptor,\r\n" +
		"            multi: true\r\n" +
		"        },\r\n" +
		"        {\r\n" +
		"            provide: HTTP_INTERCEPTORS,\r\n" +
		"            useClass: NotificationInterceptor,\r\n" +
		"            multi: true\r\n" +
		"        }\r\n" +
		"    ],\r\n" +
		"    bootstrap: [JhiMainComponent]\r\n" +
		"})\r\n" +
		"export class "+Utils.getClassNameCamelCase(conf.getProjectName()) +"AppModule {\r\n" +
		"    constructor(private dpConfig: NgbDatepickerConfig) {\r\n" +
		"        this.dpConfig.minDate = { year: moment().year() - 100, month: 1, day: 1 };\r\n" +
		"    }\r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "app.module";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/webapp/app";
	}

}
