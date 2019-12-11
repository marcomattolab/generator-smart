package it.eng.generate.template.fe.home;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateHomeModule extends AbstractResourceTemplate {

	public TemplateHomeModule(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = "import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';\r\n" +
		"import { RouterModule } from '@angular/router';\r\n" +
		"import { "+Utils.getClassNameCamelCase(conf.getProjectName()) +"SharedModule } from 'app/shared';\r\n" +
		"import { HOME_ROUTE, HomeComponent } from './';\r\n" +
		"@NgModule({\r\n" +
		"    imports: ["+Utils.getClassNameCamelCase(conf.getProjectName()) +"SharedModule, RouterModule.forChild([HOME_ROUTE])],\r\n" +
		"    declarations: [HomeComponent],\r\n" +
		"    schemas: [CUSTOM_ELEMENTS_SCHEMA]\r\n" +
		"})\r\n" +
		"export class "+Utils.getClassNameCamelCase(conf.getProjectName()) +"HomeModule {}\r\n";
		return body;
	}

	public String getClassName(){
		return "home.module";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/webapp/app/home";
	}

}
