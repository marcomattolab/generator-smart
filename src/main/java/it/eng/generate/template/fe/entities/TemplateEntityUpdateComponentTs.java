package it.eng.generate.template.fe.entities;

import java.util.Iterator;
import java.util.Set;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityUpdateComponentTs extends AbstractResourceTemplate {

	public TemplateEntityUpdateComponentTs(Table tabella) {
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
		"import { HttpResponse, HttpErrorResponse } from '@angular/common/http';\r\n" +
		"import { Observable } from 'rxjs';\r\n" +
		"import * as moment from 'moment';\r\n" +
		"import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';\r\n" +
		"import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';\r\n" +
		"import { "+INometabella+" } from 'app/shared/model/"+nometabella+".model';\r\n" +
		"import { "+Nometabella+"Service } from './"+nometabella+".service';\r\n\n" +
		"@Component({\r\n" +
		"    selector: 'jhi-"+nometabella+"-update',\r\n" +
		"    templateUrl: './"+nometabella+"-update.component.html'\r\n" +
		"})\r\n\n" +
		"export class "+Nometabella+"UpdateComponent implements OnInit {\r\n" +
		"    "+nometabella+": "+INometabella+";\r\n\n" +
		"    isSaving: boolean;\r\n";
		
		//TODO MANAGE RELATIONS AND DATES
  		//"    incaricos: IIncarico[];\r\n" +
  		//"    dataNascitaDp: any;\r\n" +
		Set set = tabella.getColumnNames();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
			Class filterType = column.getTypeColumn();
			String nomeColonna = Utils.getFieldName(column);
			if ( Utils.isDateField(column) ) {
				String dateType = Utils.isLocalDate(column) ? "any" : "string" ;
				body += "    "+nomeColonna+": "+dateType+";\r\n";
			}
		}
		
		body +=
		"\n" +
		"    constructor(\r\n" +
		"        private dataUtils: JhiDataUtils,\r\n" +
		"        private jhiAlertService: JhiAlertService,\r\n" +
	  //"        private incaricoService: IncaricoService,\r\n" +
		"        private "+nometabella+"Service: "+Nometabella+"Service,\r\n" +
		"        private activatedRoute: ActivatedRoute\r\n" +
		"    ) {}\r\n\n" +
		"    ngOnInit() {\r\n" +
		"        this.isSaving = false;\r\n" +
		"        this.activatedRoute.data.subscribe(({ "+nometabella+" }) => {\r\n" +
		"            this."+nometabella+" = "+nometabella+";\r\n";
		
		Set cset = tabella.getColumnNames();
		for (Iterator iter = cset.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
			Class filterType = column.getTypeColumn();
			String nomeColonna = Utils.getFieldName(column);
			if ( Utils.isDateField(column) && !Utils.isLocalDate(column)) {
				body += "            this."+nomeColonna+" = this."+Utils.getClassNameLowerCase(tabella)+"."+nomeColonna+" != null ? this."+Utils.getClassNameLowerCase(tabella)+"."+nomeColonna+".format(DATE_TIME_FORMAT) : null;\r\n";
			}
		}

		body +=
		"       });\r\n" +
		"    }\r\n\n" +
		"    byteSize(field) {\r\n" +
		"        return this.dataUtils.byteSize(field);\r\n" +
		"    }\r\n\n" +
		"    openFile(contentType, field) {\r\n" +
		"        return this.dataUtils.openFile(contentType, field);\r\n" +
		"    }\r\n" +
		"    setFileData(event, entity, field, isImage) {\r\n" +
		"        this.dataUtils.setFileData(event, entity, field, isImage);\r\n" +
		"    }\r\n\n" +
		"    previousState() {\r\n" +
		"        window.history.back();\r\n" +
		"    }\r\n\n" +
		"    save() {\r\n" +
		"        this.isSaving = true;\r\n" ;
		
		Set fset = tabella.getColumnNames();
		for (Iterator iter = cset.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
			String nomeColonna = Utils.getFieldName(column);
			if ( Utils.isDateField(column) && !Utils.isLocalDate(column)) {
				body += "        this."+Utils.getClassNameLowerCase(tabella)+"."+nomeColonna+" = this."+nomeColonna+" != null ? moment(this."+nomeColonna+", DATE_TIME_FORMAT) : null;\r\n";
			}
		}
		
		body +=
		"        if (this."+nometabella+".id !== undefined) {\r\n" +
		"            this.subscribeToSaveResponse(this."+nometabella+"Service.update(this."+nometabella+"));\r\n" +
		"        } else {\r\n" +
		"            this.subscribeToSaveResponse(this."+nometabella+"Service.create(this."+nometabella+"));\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"    private subscribeToSaveResponse(result: Observable<HttpResponse<"+INometabella+">>) {\r\n" +
		"        result.subscribe((res: HttpResponse<"+INometabella+">) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());\r\n" +
		"    }\r\n\n" +
		"    private onSaveSuccess() {\r\n" +
		"        this.isSaving = false;\r\n" +
		"        this.previousState();\r\n" +
		"    }\r\n\n" +
		"    private onSaveError() {\r\n" +
		"        this.isSaving = false;\r\n" +
		"    }\r\n\n" +
		"    private onError(errorMessage: string) {\r\n" +
		"        this.jhiAlertService.error(errorMessage, null, null);\r\n" +
		"    }\r\n\n" +
		//"    trackIncaricoById(index: number, item: IIncarico) {\r\n" +
		//"        return item.id;\r\n" +
		//"    }\r\n" +
		"    getSelected(selectedVals: Array<any>, option: any) {\r\n" +
		"        if (selectedVals) {\r\n" +
		"            for (let i = 0; i < selectedVals.length; i++) {\r\n" +
		"                if (option.id === selectedVals[i].id) {\r\n" +
		"                    return selectedVals[i];\r\n" +
		"                }\r\n" +
		"            }\r\n" +
		"        }\r\n" +
		"        return option;\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}
	
	public String getClassName(){
		return Utils.getClassNameLowerCase(tabella)+"-update.component";
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
