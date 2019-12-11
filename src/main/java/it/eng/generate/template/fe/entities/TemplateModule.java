package it.eng.generate.template.fe.entities;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateModule extends AbstractResourceTemplate {

	public TemplateModule(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		
		String body = 
		"import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';\r\n";
		
		for(Table table: Utils.getTables(database) ) {
			String Tablename = Utils.getEntityName(table);
			String tablename = Utils.getFieldName(table);
			body += "import { "+Utils.getClassNameCamelCase(conf.getProjectName()) + Tablename+"Module } from './"+tablename+"/"+tablename+".module';\r\n" ;
		}
		body += "/* jh-needle-add-entity-module-import - JH will add entity modules imports here */\r\n" +
		"@NgModule({\r\n" +
		"    // prettier-ignore\r\n" +
		"    imports: [\r\n" ;
		for(Table table: Utils.getTables(database) ) {
			String Tablename = Utils.getEntityName(table);
			body += "        "+Utils.getClassNameCamelCase(conf.getProjectName()) +Tablename+"Module,\r\n" ;
		}
		body +=
		"        /* jh-needle-add-entity-module - JH will add entity modules here */\r\n" +
		"    ],\r\n" +
		"    declarations: [],\r\n" +
		"    entryComponents: [],\r\n" +
		"    providers: [],\r\n" +
		"    schemas: [CUSTOM_ELEMENTS_SCHEMA]\r\n" +
		"})\r\n" +
		"export class "+Utils.getClassNameCamelCase(conf.getProjectName()) +"EntityModule {}\r\n";
		return body;
	}
	
	public String getClassName(){
		return "entity.module";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/webapp/app/entities";
	}

}
