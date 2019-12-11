package it.eng.generate.template.fe.core;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateCoreModule extends AbstractResourceTemplate {

	public TemplateCoreModule(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = "import { NgModule, LOCALE_ID } from '@angular/core';\r\n" +
		"import { DatePipe, registerLocaleData } from '@angular/common';\r\n" +
		"import { HttpClientModule } from '@angular/common/http';\r\n" +
		"import { Title } from '@angular/platform-browser';\r\n" +
		"import locale from '@angular/common/locales/it';\r\n" +
		"@NgModule({\r\n" +
		"    imports: [HttpClientModule],\r\n" +
		"    exports: [],\r\n" +
		"    declarations: [],\r\n" +
		"    providers: [\r\n" +
		"        Title,\r\n" +
		"        {\r\n" +
		"            provide: LOCALE_ID,\r\n" +
		"            useValue: 'it'\r\n" +
		"        },\r\n" +
		"        DatePipe\r\n" +
		"    ]\r\n" +
		"})\r\n" +
		"export class "+Utils.getClassNameCamelCase(conf.getProjectName()) +"CoreModule {\r\n" +
		"    constructor() {\r\n" +
		"        registerLocaleData(locale);\r\n" +
		"    }\r\n" +
		"}\r\n";

		return body;
	}

	public String getClassName(){
		return "core.module";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/webapp/app/core";
	}

}
