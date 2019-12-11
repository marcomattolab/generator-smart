package it.eng.generate.template.fe.entities;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityModule extends AbstractResourceTemplate {

	public TemplateEntityModule(Table tabella) {
		super(tabella);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String Nometabella = Utils.getEntityName(tabella);
		String nometabella = Utils.getClassNameLowerCase(tabella);
		
		String body = 
		"import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';\r\n" +
		"import { RouterModule } from '@angular/router';\r\n" +
		"import { FormsModule, ReactiveFormsModule } from '@angular/forms';\r\n" +
		"import { "+Utils.getClassNameCamelCase(conf.getProjectName()) +"SharedModule } from 'app/shared';\r\n" +
		"import {\r\n" +
		"    "+Nometabella+"Component,\r\n" +
		"    "+Nometabella+"DetailComponent,\r\n" +
		"    "+Nometabella+"UpdateComponent,\r\n" +
		"    "+Nometabella+"DeletePopupComponent,\r\n" +
		"    "+Nometabella+"DeleteDialogComponent,\r\n" +
		"    "+nometabella+"Route,\r\n" +
		"    "+nometabella+"PopupRoute\r\n" +
		"} from './';\r\n\n" +
		"const ENTITY_STATES = [..."+nometabella+"Route, ..."+nometabella+"PopupRoute];\r\n" +
		"@NgModule({\r\n" +
		"    imports: ["+Utils.getClassNameCamelCase(conf.getProjectName()) +"SharedModule, FormsModule, ReactiveFormsModule, RouterModule.forChild(ENTITY_STATES)],\r\n" +
		"    declarations: [\r\n" +
		"        "+Nometabella+"Component,\r\n" +
		"        "+Nometabella+"DetailComponent,\r\n" +
		"        "+Nometabella+"UpdateComponent,\r\n" +
		"        "+Nometabella+"DeleteDialogComponent,\r\n" +
		"        "+Nometabella+"DeletePopupComponent\r\n" +
		"    ],\r\n" +
		"    entryComponents: ["+Nometabella+"Component, "+Nometabella+"UpdateComponent, "+Nometabella+"DeleteDialogComponent, "+Nometabella+"DeletePopupComponent],\r\n" +
		"    schemas: [CUSTOM_ELEMENTS_SCHEMA]\r\n" +
		"})\r\n\n" +
		"export class "+Utils.getClassNameCamelCase(conf.getProjectName())+Nometabella+"Module {}\r\n";

		return body;
	}
	
	public String getClassName(){
		return Utils.getClassNameLowerCase(tabella)+".module";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/webapp/app/entities/"+Utils.getClassNameLowerCase(tabella);
	}

}
