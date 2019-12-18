package it.eng.generate.template.fe.entities;

import java.util.Iterator;
import java.util.Set;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityComponentTs extends AbstractResourceTemplate {

	public TemplateEntityComponentTs(DataBase database, Table tabella) {
		super(database);
		this.tabella = tabella;
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
		"import { Component, OnInit, OnDestroy, NgModule, ViewChild } from '@angular/core';\r\n"	+	
		"import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';\r\n" +
		"import { ActivatedRoute, Router } from '@angular/router';\r\n" +
		"import { Subscription } from 'rxjs';\r\n" +
		"import { JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils } from 'ng-jhipster';\r\n" +
		"import { Principal } from 'app/core';\r\n" +
		"import { ITEMS_PER_PAGE } from 'app/shared';\r\n" +
		"import { FormGroup, ReactiveFormsModule, FormControl } from '@angular/forms';\r\n" +
		"import { Observable } from 'rxjs';\r\n" +
		"import * as moment from 'moment';\r\n" +
		"import { toTimestampInizio, toTimestampFine } from 'app/shared/util/date-util';\r\n" +
		"import { "+INometabella+" } from 'app/shared/model/"+nometabella+".model';\r\n" +
		"import { "+Nometabella+"Service } from './"+nometabella+".service';\r\n\n" +
		"@Component({\r\n" +
		"    selector: 'jhi-"+nometabella+"',\r\n" +
		"    templateUrl: './"+nometabella+".component.html'\r\n" +
		"})\r\n" +
		"export class "+Nometabella+"Component implements OnInit, OnDestroy {\r\n" +
		"    currentAccount: any;\r\n" +
		"    "+nometabella+"s: "+INometabella+"[];\r\n" +
		"    error: any;\r\n" +
		"    success: any;\r\n" +
		"    eventSubscriber: Subscription;\r\n" +
		"    routeData: any;\r\n" +
		"    links: any;\r\n" +
		"    totalItems: any;\r\n" +
		"    queryCount: any;\r\n" +
		"    itemsPerPage: any;\r\n" +
		"    page: any;\r\n" +
		"    predicate: any;\r\n" +
		"    previousPage: any;\r\n" +
		"    reverse: any;\r\n" +
		"    myGroup: FormGroup;\r\n\n" +
		"    constructor(\r\n" +
		"        private "+nometabella+"Service: "+Nometabella+"Service,\r\n" +
		"        private parseLinks: JhiParseLinks,\r\n" +
		"        private jhiAlertService: JhiAlertService,\r\n" +
		"        private principal: Principal,\r\n" +
		"        private activatedRoute: ActivatedRoute,\r\n" +
		"        private dataUtils: JhiDataUtils,\r\n" +
		"        private router: Router,\r\n" +
		"        private eventManager: JhiEventManager\r\n" +
		"    ) {\r\n" +
		"        this.itemsPerPage = ITEMS_PER_PAGE;\r\n" +
		"        this.routeData = this.activatedRoute.data.subscribe(data => {\r\n" +
		"            this.page = data.pagingParams.page;\r\n" +
		"            this.previousPage = data.pagingParams.page;\r\n" +
		"            this.reverse = data.pagingParams.ascending;\r\n" +
		"            this.predicate = data.pagingParams.predicate;\r\n" +
		"        });\r\n\n" +
		"        this.initFormRicerca();\r\n" +
		"    }\r\n\n" +
		
		 // Search Filters
		"    initFormRicerca() {\r\n" +
		"        this.myGroup = new FormGroup({\r\n";
		
		Set set = tabella.getColumnNames();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
			boolean hasNext = iter != null ? iter.hasNext() : false;
			
			if(Utils.isDateField(column)) {
				//DATES
				body += "            "+Utils.getFieldName(column)+"Da: new FormControl(''),\r\n";
				body += "            "+Utils.getFieldName(column)+"A: new FormControl('')"+(hasNext ? ",\r\n" : "\r\n");
				
			} else if(Utils.isNumericField(column)) {
				//NUMERICS
				body += "            "+Utils.getFieldName(column)+"Da: new FormControl(''),\r\n";
				body += "            "+Utils.getFieldName(column)+"A: new FormControl('')"+(hasNext ? ",\r\n" : "\r\n");

			} else {
				//STRING
				body += "            "+Utils.getFieldName(column)+": new FormControl('')"+(hasNext ? ",\r\n" : "\r\n");
			}
			// TODO DEVELOP BLOB/CLOB
		}
		
		body +=
		"        });\r\n" +
		"        this.myGroup.updateValueAndValidity();\r\n" +
		"    }\r\n\n"+

		
		"    loadAll() {\r\n" +
		"        this."+nometabella+"Service\r\n" +
		"            .query({\r\n" +
		"                page: this.page - 1,\r\n" +
		"                size: this.itemsPerPage,\r\n" +
		"                sort: this.sort()\r\n" +
		"            })\r\n" +
		"            .subscribe(\r\n" +
		"                (res: HttpResponse<"+INometabella+"[]>) => this.paginate"+Nometabella+"s(res.body, res.headers),\r\n" +
		"                (res: HttpErrorResponse) => this.onError(res.message)\r\n" +
		"            );\r\n" +
		"    }\r\n\n" +
		"    loadPage(page: number) {\r\n" +
		"        if (page !== this.previousPage) {\r\n" +
		"            this.previousPage = page;\r\n" +
		"            this.transition();\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"    transition() {\r\n" +
		"        this.router.navigate(['/"+nometabella+"'], {\r\n" +
		"            queryParams: {\r\n" +
		"                page: this.page,\r\n" +
		"                size: this.itemsPerPage,\r\n" +
		"                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')\r\n" +
		"            }\r\n" +
		"        });\r\n" +
		"        this.loadAll();\r\n" +
		"    }\r\n\n" +
		"    clear() {\r\n" +
		"        this.page = 0;\r\n" +
		"        this.router.navigate([\r\n" +
		"            '/"+nometabella+"',\r\n" +
		"            {\r\n" +
		"                page: this.page,\r\n" +
		"                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')\r\n" +
		"            }\r\n" +
		"        ]);\r\n" +
		"        this.loadAll();\r\n" +
		"    }\r\n\n" +
		"    ngOnInit() {\r\n" +
		"        this.loadAll();\r\n" +
		"        this.principal.identity().then(account => {\r\n" +
		"            this.currentAccount = account;\r\n" +
		"        });\r\n" +
		"        this.registerChangeIn"+Nometabella+"s();\r\n" +
		"    }\r\n\n" +
		"    ngOnDestroy() {\r\n" +
		"        this.eventManager.destroy(this.eventSubscriber);\r\n" +
		"    }\r\n\n" +
		"    trackId(index: number, item: "+INometabella+") {\r\n" +
		"        return item.id;\r\n" +
		"    }\r\n\n" +
		"    byteSize(field) {\r\n" +
		"        return this.dataUtils.byteSize(field);\r\n" +
		"    }\r\n\n" +
		"    openFile(contentType, field) {\r\n" +
		"        return this.dataUtils.openFile(contentType, field);\r\n" +
		"    }\r\n\n" +
		"    registerChangeIn"+Nometabella+"s() {\r\n" +
		"        this.eventSubscriber = this.eventManager.subscribe('"+nometabella+"ListModification', response => this.loadAll());\r\n" +
		"    }\r\n\n" +
		"    sort() {\r\n" +
		"        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];\r\n" +
		"        if (this.predicate !== 'id') {\r\n" +
		"            result.push('id');\r\n" +
		"        }\r\n" +
		"        return result;\r\n" +
		"    }\r\n\n" +
		"    private paginate"+Nometabella+"s(data: "+INometabella+"[], headers: HttpHeaders) {\r\n" +
		"        this.links = this.parseLinks.parse(headers.get('link'));\r\n" +
		"        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);\r\n" +
		"        this.queryCount = this.totalItems;\r\n" +
		"        this."+nometabella+"s = data;\r\n" +
		"    }\r\n\n" +
		
		
		// Export File (PDF XLS, TXT etc)
		"    exportFile(fileType) {\r\n" +
		"        console.log('Export file with type: ' + fileType);\r\n" +
		"    }\r\n\n"+
		
		
		// Reset Filtri di RICERCA
		"    resetFiltri() {\r\n" +
		"        this.initFormRicerca();\r\n" +
		"    }\r\n\n"+
		
		
		// Implementazione Logica di RICERCA
		"    cerca() {\r\n" +

		// [PARAMETRY_QUERY]
		"        console.log('Cerca "+Nometabella+" ');\r\n\n";
		Set qset = tabella.getColumnNames();
		for (Iterator iter = qset.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
			String columnname = Utils.getFieldName(column);
			
			if ( Utils.isDateField(column) ) {
				body += 
				"        // Filtro "+columnname+" Inizio\r\n" +
				"        let "+columnname+"Inizio: moment.Moment;\r\n" +
				"        const "+columnname+"Da = this.myGroup.controls['"+columnname+"Da'].value;\r\n" +
				"        if ("+columnname+"Da) {\r\n" +
				"            "+columnname+"Inizio = toTimestampInizio(moment("+columnname+"Da));\r\n" +
				"        }\r\n\n" +
				"        // Filtro "+columnname+" Fine\r\n" +
				"        let "+columnname+"Fine: moment.Moment;\r\n" +
				"        const "+columnname+"A = this.myGroup.controls['"+columnname+"A'].value;\r\n\n" +
				"        if ("+columnname+"A) {\r\n" +
				"            "+columnname+"Fine = toTimestampFine(moment("+columnname+"A));\r\n" +
				"        }\r\n\n";
			
			} else if ( Utils.isTextField(column) ) {
				body +=
				"        // Filtro "+columnname+"\r\n" +
				"        const "+columnname+": String = this.myGroup.controls['"+columnname+"'].value;\r\n\n";
			
			} else if ( Utils.isNumericField(column) ) {
				//TODO TEST / COMPLETE !!!
				body += 
				"        // Filtro "+columnname+" Inizio\r\n" +
				"        let "+columnname+"Inizio: String;\r\n" +
				"        const "+columnname+"Da = this.myGroup.controls['"+columnname+"Da'].value;\r\n" +
				"        if ("+columnname+"Da) {\r\n" +
				"            "+columnname+"Inizio = "+columnname+"Da;\r\n" +
				"        }\r\n\n" +
				"        // Filtro "+columnname+" Fine\r\n" +
				"        let "+columnname+"Fine: String;\r\n" +
				"        const "+columnname+"A = this.myGroup.controls['"+columnname+"A'].value;\r\n\n" +
				"        if ("+columnname+"A) {\r\n" +
				"            "+columnname+"Fine = "+columnname+"A;\r\n" +
				"        }\r\n\n";
				
			} else {
				//TODO DEVELOP THIS!!   CLOB / BLOB |  SELECT with Id(s)
//				"        // filtro autista\r\n" +
//				"        let idAutista = '';\r\n" +
//				"        const autista = this.myGroup.controls['autista'].value;\r\n" +
//				"        if (autista) {\r\n" +
//				"            idAutista = autista.id.toString();\r\n" +
//				"        }\r\n" +
			}
		}
		// [/PARAMETRY_QUERY]
		
		//Servizio di Ricerca TS
		body += 
		"        this."+nometabella+"Service\r\n" +
		"            .query({\r\n" +
		"                page: this.page - 1,\r\n" +
		"                size: this.itemsPerPage,\r\n" +
		"                sort: this.sort(),\r\n";
		
		//[DINAMIC_QUERY]
		Set cset = tabella.getColumnNames();
		for (Iterator iter = cset.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
			String columnname = Utils.getFieldName(column);
			boolean isEnumeration = column.getEnumeration()!=null ? true : false;
			boolean hasNext = iter != null ? iter.hasNext() : false;
			
			if ( Utils.isDateField(column) ) {
				body += "                '"+columnname+".greaterOrEqualThan': "+columnname+"Inizio && "+columnname+"Inizio.isValid() ? "+columnname+"Inizio.toJSON() : '',\r\n";
				body += "                '"+columnname+".lessOrEqualThan': "+columnname+"Fine && "+columnname+"Fine.isValid() ? "+columnname+"Fine.toJSON() : ''"  + (hasNext ? ",\r\n" : "\r\n");
			
			} else if ( Utils.isNumericField(column) ) {
				body += "                '"+columnname+".greaterOrEqualThan': "+columnname+"Inizio ? "+columnname+"Inizio : '',\r\n";
				body += "                '"+columnname+".lessOrEqualThan': "+columnname+"Fine ? "+columnname+"Fine : ''"  + (hasNext ? ",\r\n" : "\r\n");
					
			} else if ( Utils.isTextField(column) && !isEnumeration) {
				body += "                '"+columnname+".contains': "+columnname+" ? "+columnname+" : ''" + (hasNext ? ",\r\n" : "\r\n");
			
			} else if ( Utils.isTextField(column) && isEnumeration) {
				body += "                '"+columnname+".equals': "+columnname+" ? "+columnname+" : ''" + (hasNext ? ",\r\n" : "\r\n");
				
			} else {
				//TODO DEVELOP THIS!!  CLOB/BLOB |  SELECT with id(s)
				//"                'autistaId.equals': idAutista,\r\n" +
			}
		}
		//[/DINAMIC_QUERY]
		
		body += 
		"            })\r\n" +
		"            .subscribe(\r\n" +
		"                (res: HttpResponse<"+INometabella+"[]>) => this.paginate"+Nometabella+"s(res.body, res.headers),\r\n" +
		"                (res: HttpErrorResponse) => this.onError(res.message)\r\n" +
		"            );\r\n" +
		"    }\r\n\n"+
		//FINE servizio di RICERCA
		
		
		
		"    private onError(errorMessage: string) {\r\n" +
		"        this.jhiAlertService.error(errorMessage, null, null);\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}
	
	public String getClassName(){
		return Utils.getClassNameLowerCase(tabella)+".component";
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
