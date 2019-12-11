package it.eng.generate.template.fe.shared;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateSharedLibsModule extends AbstractResourceTemplate {

	public TemplateSharedLibsModule(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = "import { NgModule } from '@angular/core';\r\n" +
		"import { FormsModule } from '@angular/forms';\r\n" +
		"import { CommonModule } from '@angular/common';\r\n" +
		"import { NgbModule } from '@ng-bootstrap/ng-bootstrap';\r\n" +
		"import { NgJhipsterModule } from 'ng-jhipster';\r\n" +
		"import { InfiniteScrollModule } from 'ngx-infinite-scroll';\r\n" +
		"import { CookieModule } from 'ngx-cookie';\r\n" +
		"import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';\r\n" +
		"@NgModule({\r\n" +
		"    imports: [NgbModule.forRoot(), InfiniteScrollModule, CookieModule.forRoot(), FontAwesomeModule],\r\n" +
		"    exports: [FormsModule, CommonModule, NgbModule, NgJhipsterModule, InfiniteScrollModule, FontAwesomeModule]\r\n" +
		"})\r\n" +
		"export class "+Utils.getClassNameCamelCase(conf.getProjectName()) +"SharedLibsModule {\r\n" +
		"    static forRoot() {\r\n" +
		"        return {\r\n" +
		"            ngModule: "+Utils.getClassNameCamelCase(conf.getProjectName()) +"SharedLibsModule\r\n" +
		"        };\r\n" +
		"    }\r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "shared-libs.module";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/webapp/app/shared";
	}

}
