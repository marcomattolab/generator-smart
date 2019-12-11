package it.eng.generate.template.fe.dashboard;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateDashboardBarchartModule extends AbstractResourceTemplate {

	public TemplateDashboardBarchartModule(DataBase database) {
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
		"import { "+Utils.getClassNameCamelCase(conf.getProjectName())+"SharedModule } from '../../shared';\r\n" +
		"import { ChartModule } from 'primeng/primeng';\r\n" +
		"import {\r\n" +
		"    BarchartComponent,\r\n" +
		"    barchartRoute\r\n" +
		"} from './';\r\n" +
		"const DASHBOARD_STATES = [\r\n" +
		"    barchartRoute\r\n" +
		"];\r\n" +
		"@NgModule({\r\n" +
		"    imports: [\r\n" +
		"        "+Utils.getClassNameCamelCase(conf.getProjectName())+"SharedModule,\r\n" +
		"        ChartModule,\r\n" +
		"        RouterModule.forRoot(DASHBOARD_STATES, { useHash: true })\r\n" +
		"    ],\r\n" +
		"    declarations: [\r\n" +
		"        BarchartComponent\r\n" +
		"    ],\r\n" +
		"    schemas: [CUSTOM_ELEMENTS_SCHEMA]\r\n" +
		"})\r\n" +
		"export class "+Utils.getClassNameCamelCase(conf.getProjectName())+"BarchartModule {}\r\n";
		return body;
	}

	public String getClassName(){
		return "barchart.module";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/webapp/app/dashboard/barchart";
	}

}
