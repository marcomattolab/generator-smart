package it.eng.generate.template.fe.entities;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityDetailComponentTs extends AbstractResourceTemplate {

	public TemplateEntityDetailComponentTs(Table tabella) {
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
		String INometabella = Utils.getIName(tabella);

		String body = 
		"import { Component, OnInit } from '@angular/core';\r\n" +
		"import { ActivatedRoute } from '@angular/router';\r\n" +
		"import { JhiDataUtils } from 'ng-jhipster';\r\n" +
		"import { "+INometabella+" } from 'app/shared/model/"+nometabella+".model';\r\n\n" +
		"@Component({\r\n" +
		"    selector: 'jhi-"+nometabella+"-detail',\r\n" +
		"    templateUrl: './"+nometabella+"-detail.component.html'\r\n" +
		"})\r\n\n" +
		"export class  "+Nometabella+"DetailComponent implements OnInit {\r\n" +
		"    "+nometabella+": "+INometabella+";\r\n\n" +
		"    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}\r\n\n" +
		"    ngOnInit() {\r\n" +
		"        this.activatedRoute.data.subscribe(({ "+nometabella+" }) => {\r\n" +
		"            this."+nometabella+" = "+nometabella+";\r\n" +
		"        });\r\n" +
		"    }\r\n\n" +
		"    byteSize(field) {\r\n" +
		"        return this.dataUtils.byteSize(field);\r\n" +
		"    }\r\n\n" +
		"    openFile(contentType, field) {\r\n" +
		"        return this.dataUtils.openFile(contentType, field);\r\n" +
		"    }\r\n\n" +
		"    previousState() {\r\n" +
		"        window.history.back();\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}
	
	public String getClassName(){
		return Utils.getClassNameLowerCase(tabella)+"-detail.component";
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
