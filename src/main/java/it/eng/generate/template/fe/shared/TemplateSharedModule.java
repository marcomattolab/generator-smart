package it.eng.generate.template.fe.shared;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateSharedModule extends AbstractResourceTemplate {

	public TemplateSharedModule(DataBase database) {
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
		"import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';\r\n" +
		"import { NgbDateMomentAdapter } from './util/datepicker-adapter';\r\n" +
		"import { "+Utils.getClassNameCamelCase(conf.getProjectName()) +"SharedLibsModule, "+Utils.getClassNameCamelCase(conf.getProjectName()) +"SharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';\r\n" +
		"@NgModule({\r\n" +
		"    imports: ["+Utils.getClassNameCamelCase(conf.getProjectName()) +"SharedLibsModule, "+Utils.getClassNameCamelCase(conf.getProjectName()) +"SharedCommonModule],\r\n" +
		"    declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],\r\n" +
		"    providers: [{ provide: NgbDateAdapter, useClass: NgbDateMomentAdapter }],\r\n" +
		"    entryComponents: [JhiLoginModalComponent],\r\n" +
		"    exports: ["+Utils.getClassNameCamelCase(conf.getProjectName()) +"SharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],\r\n" +
		"    schemas: [CUSTOM_ELEMENTS_SCHEMA]\r\n" +
		"})\r\n" +
		"export class "+Utils.getClassNameCamelCase(conf.getProjectName()) +"SharedModule {\r\n" +
		"    static forRoot() {\r\n" +
		"        return {\r\n" +
		"            ngModule: "+Utils.getClassNameCamelCase(conf.getProjectName()) +"SharedModule\r\n" +
		"        };\r\n" +
		"    }\r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "shared.module";
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
