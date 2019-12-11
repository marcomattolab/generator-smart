package it.eng.generate.template.fe;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateAppRoutingModule extends AbstractResourceTemplate {

	public TemplateAppRoutingModule(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = "import { NgModule } from '@angular/core';\r\n" +
		"import { RouterModule } from '@angular/router';\r\n" +
		"import { errorRoute, navbarRoute } from './layouts';\r\n" +
		"import { DEBUG_INFO_ENABLED } from 'app/app.constants';\r\n" +
		"const LAYOUT_ROUTES = [navbarRoute, ...errorRoute];\r\n" +
		"@NgModule({\r\n" +
		"    imports: [\r\n" +
		"        RouterModule.forRoot(\r\n" +
		"            [\r\n" +
		"                ...LAYOUT_ROUTES,\r\n" +
		"                {\r\n" +
		"                    path: 'admin',\r\n" +
		"                    loadChildren: './admin/admin.module#"+Utils.getClassNameCamelCase(conf.getProjectName()) +"AdminModule'\r\n" +
		"                }\r\n" +
		"            ],\r\n" +
		"            { useHash: true, enableTracing: DEBUG_INFO_ENABLED }\r\n" +
		"        )\r\n" +
		"    ],\r\n" +
		"    exports: [RouterModule]\r\n" +
		"})\r\n" +
		"export class "+Utils.getClassNameCamelCase(conf.getProjectName()) +"AppRoutingModule {}\r\n";
		return body;
	}

	public String getClassName(){
		return "app-routing.module";
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
