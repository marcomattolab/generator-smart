package it.eng.generate.template.fe.dashboard;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateDashboardModule extends AbstractResourceTemplate {

	public TemplateDashboardModule(DataBase database) {
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
		"import { "+Utils.getClassNameCamelCase(conf.getProjectName()) +"BarchartModule } from './barchart/barchart.module';\r\n" +
		"import { "+Utils.getClassNameCamelCase(conf.getProjectName()) +"DoughnutchartModule } from './doughnutchart/doughnutchart.module';\r\n" +
		"import { "+Utils.getClassNameCamelCase(conf.getProjectName()) +"LinechartModule } from './linechart/linechart.module';\r\n" +
		"import { "+Utils.getClassNameCamelCase(conf.getProjectName()) +"PiechartModule } from './piechart/piechart.module';\r\n" +
		"import { "+Utils.getClassNameCamelCase(conf.getProjectName()) +"PolarareachartModule } from './polarareachart/polarareachart.module';\r\n" +
		"import { "+Utils.getClassNameCamelCase(conf.getProjectName()) +"RadarchartModule } from './radarchart/radarchart.module';\r\n\n" +
		"@NgModule({\r\n" +
		"    imports: [\r\n" +
		"        "+Utils.getClassNameCamelCase(conf.getProjectName()) +"BarchartModule,\r\n" +
		"        "+Utils.getClassNameCamelCase(conf.getProjectName()) +"DoughnutchartModule,\r\n" +
		"        "+Utils.getClassNameCamelCase(conf.getProjectName()) +"LinechartModule,\r\n" +
		"        "+Utils.getClassNameCamelCase(conf.getProjectName()) +"PiechartModule,\r\n" +
		"        "+Utils.getClassNameCamelCase(conf.getProjectName()) +"PolarareachartModule,\r\n" +
		"        "+Utils.getClassNameCamelCase(conf.getProjectName()) +"RadarchartModule,\r\n" +
		"    ],\r\n" +
		"    declarations: [],\r\n" +
		"    entryComponents: [],\r\n" +
		"    providers: [],\r\n" +
		"    schemas: [CUSTOM_ELEMENTS_SCHEMA]\r\n" +
		"})\r\n" +
		"export class "+Utils.getClassNameCamelCase(conf.getProjectName()) +"DashboardModule {}\r\n";

		return body;
	}

	public String getClassName(){
		return "dashboard.module";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/webapp/app/dashboard";
	}

}
