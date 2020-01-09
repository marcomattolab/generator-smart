package it.eng.generate.template.fe.entities;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.ProjectRelation;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityComponentTs extends AbstractResourceTemplate {
	private static final String A = "A";
	private static final String DA = "Da";
	
	private String IMPORT_SECTION = "IMPORT_SECTION";
	private String INIT_SECTION = "INIT_SECTION";
	private String SEARCH = "SEARCH";
	private String SEARCH_FILTER = "SEARCH_FILTER";
	private String CONSTRUCTOR_SECTION = "CONSTRUCTOR_SECTION";
	private String TRACKBY_SECTION = "TRACKBY_SECTION";
	private String NG_ONINIT_SECTION = "NG_ONINIT_SECTION";
	
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
		"import { FormGroup, ReactiveFormsModule, FormControl } from '@angular/forms';\r\n" +
		"import { Observable } from 'rxjs';\r\n" +
		"import * as moment from 'moment';\r\n" +
		"import { toTimestampInizio, toTimestampFine } from 'app/shared/util/date-util';\r\n" +
		"import { "+INometabella+" } from 'app/shared/model/"+nometabella+".model';\r\n" +
		"import { "+Nometabella+"Service } from './"+nometabella+".service';\r\n"+
		"import { checkAndCompileSearchFilterContains, checkAndCompileSearchFilterEquals, checkAndCompileSearchBetween, ITEMS_PER_PAGE } from 'app/shared';\n";

		body += printRelations(conf, IMPORT_SECTION);
		
		body += 
		"\n@Component({\r\n" +
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
		"    myGroup: FormGroup;\r\n";
		body += printRelations(conf, INIT_SECTION);
		
		body += 
		"\n    constructor(\r\n" +
		"        private "+nometabella+"Service: "+Nometabella+"Service,\r\n" +
		"        private parseLinks: JhiParseLinks,\r\n" +
		"        private jhiAlertService: JhiAlertService,\r\n" +
		"        private principal: Principal,\r\n" +
		"        private activatedRoute: ActivatedRoute,\r\n" +
		"        private dataUtils: JhiDataUtils,\r\n" +
		"        private router: Router,\r\n";
		body += printRelations(conf, CONSTRUCTOR_SECTION);
		body += 
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
		
		body += printRelations(conf, SEARCH);
		
		int cSize =  1;
		for(Column column: tabella.getSortedColumns()) {
			boolean hasNext = cSize < tabella.getSortedColumns().size();
			if(Utils.isDateField(column)) {
				//DATES
				body += "            "+Utils.getFieldName(column)+DA+": new FormControl(''),\r\n";
				body += "            "+Utils.getFieldName(column)+A+": new FormControl('')"+(hasNext ? ",\r\n" : "\r\n");
				
			} else if(Utils.isNumericField(column)) {
				//NUMERICS
				body += "            "+Utils.getFieldName(column)+DA+": new FormControl(''),\r\n";
				body += "            "+Utils.getFieldName(column)+A+": new FormControl('')"+(hasNext ? ",\r\n" : "\r\n");

			} else if(Utils.isTextField(column)) {
				//STRING
				body += "            "+Utils.getFieldName(column)+": new FormControl('')"+(hasNext ? ",\r\n" : "\r\n");
			
			} else if(Utils.isBlob(column) || Utils.isClob(column)) {
				// TODO DEVELOP BLOB/CLOB
				
			}
			
			cSize++;
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
		"        this.loadAll();\r\n\n" +
		"        this.principal.identity().then(account => {\r\n" +
		"            this.currentAccount = account;\r\n" +
		"        });\r\n" +
		"        this.registerChangeIn"+Nometabella+"s();\r\n";
		body += printRelations(conf, NG_ONINIT_SECTION);
		body += 
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
		
		
		// Export File (PDF, XLS, DOC etc)
		"    exportFile(fileType) {\r\n" +
		"        console.log('Export file with type: ' + fileType);\n\n" +
		"        const myGroupControls = this.myGroup.controls;\n" +
		"        let searchFilter = {\n" +
		"            'fileType': fileType\n" +
        "        };\n\n";
		
		//EXP_FILE.1 Print FilterSearch of Relations / SEARCH_FILTER
		body += printFilterRelations(conf);
		
		//EXP_FILE.2 Print FilterSearch of Columns  / Column
		for(Column column: tabella.getSortedColumns()) {
				boolean isEnumeration = column.getEnumeration()!=null;
				
				if(Utils.isDateField(column)) {
					//DATES
					String param = Utils.getFieldName(column);
					String paramDa = param + DA;
					String paramA =param + A;
					body += "        searchFilter = checkAndCompileSearchBetween(myGroupControls, searchFilter, '"+paramDa+"', '"+paramA+"', '"+param+"');\n";
				
				} else if(Utils.isNumericField(column)) {
					//NUMERICS
					String param = Utils.getFieldName(column);
					String paramDa = param + DA;
					String paramA =param + A;
					body += "        searchFilter = checkAndCompileSearchBetween(myGroupControls, searchFilter, '"+paramDa+"', '"+paramA+"', '"+param+"');\n";
				
				} else if(Utils.isTextField(column) && isEnumeration){
					//STRING EQUALS
				    body += "        searchFilter = checkAndCompileSearchFilterEquals(myGroupControls, searchFilter, '"+Utils.getFieldName(column)+"');\n";
				
				} else if(Utils.isTextField(column) && !isEnumeration){
					//STRING CONTAINS
					body += "        searchFilter = checkAndCompileSearchFilterContains(myGroupControls, searchFilter, '"+Utils.getFieldName(column)+"');\n";
				
				} else if(Utils.isBlob(column) || Utils.isClob(column)) {
					// TODO DEVELOP BLOB/CLOB
					
				}
			}
		
		
	body += 
		"\n        return window.location.href = this." + nometabella + "Service.resourceExportUrl + this.buildFilterParams(searchFilter);\r\n" +
		"    }\r\n\n"+
		
		// Build filter Params for Print
		"    buildFilterParams(searchFilter) {\n"+
		"       let res = '';\n"+
		"       let isFirst = true;\n"+
		"       for (const filter in searchFilter) {\n"+
		"          if (searchFilter.hasOwnProperty(filter)) {\n"+
		"             res += (isFirst ? '?' : '&') + filter + '=' + searchFilter[filter];\n"+
		"             isFirst = false;\n"+
		"          }\n"+
		"       }\n"+
		"       return res;\n"+
		"    }\n\n"+
		
		// Reset Filtri di Ricerca
		"    resetFiltri() {\r\n" +
		"        this.initFormRicerca();\r\n" +
		"    }\r\n\n"+
		
		
		// Implementazione Logica di RICERCA
		"    cerca() {\r\n" +
		"        console.log('Cerca "+Nometabella+" ');\r\n"+
		"        const myGroupControls = this.myGroup.controls;\n" +
		"        let searchFilter = {\n" +
		"            'page': this.page - 1,\n"+
		"            'size': this.itemsPerPage,\n"+
		"            'sort': this.sort()\n"+
	    "        };\n\n";
	
		//RICERCA.1 - Print FilterSearch of Relations / SEARCH_FILTER
		body += printFilterRelations(conf);
		
		//RICERCA.2 - Print FilterSearch of Columns  / Column
		for(Column column: tabella.getSortedColumns()) {
			boolean isEnumeration = column.getEnumeration()!=null;
			if(Utils.isDateField(column)) {
				//DATES
				String param = Utils.getFieldName(column);
				String paramDa = param + DA;
				String paramA =param + A;
				body += "        searchFilter = checkAndCompileSearchBetween(myGroupControls, searchFilter, '"+paramDa+"', '"+paramA+"', '"+param+"');\n";
			
			} else if(Utils.isNumericField(column)) {
				//NUMERICS
				String param = Utils.getFieldName(column);
				String paramDa = param + DA;
				String paramA =param + A;
				body += "        searchFilter = checkAndCompileSearchBetween(myGroupControls, searchFilter, '"+paramDa+"', '"+paramA+"', '"+param+"');\n";
			
			} else if(Utils.isTextField(column) && isEnumeration){
				//STRING EQUALS
			    body += "        searchFilter = checkAndCompileSearchFilterEquals(myGroupControls, searchFilter, '"+Utils.getFieldName(column)+"');\n";
			
			} else if(Utils.isTextField(column) && !isEnumeration){
				//STRING CONTAINS
				body += "        searchFilter = checkAndCompileSearchFilterContains(myGroupControls, searchFilter, '"+Utils.getFieldName(column)+"');\n";
			
			} else if(Utils.isBlob(column) || Utils.isClob(column)) {
				// TODO DEVELOP BLOB/CLOB
				
			}
		}
		body +=
		"\n        this."+nometabella+"Service.query(searchFilter).subscribe(\r\n" +
		"             (res: HttpResponse<"+INometabella+"[]>) => this.paginate"+Nometabella+"s(res.body, res.headers),\r\n" +
		"             (res: HttpErrorResponse) => this.onError(res.message)\r\n" +
		"        );\r\n" +
		"    }\r\n\n"+
		//FINE Servizio di RICERCA
		
		
		
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
	
	
	private String printFilterRelations(ConfigCreateProject conf) {
		Map<String, String> relMap = new HashMap<>();
		String res = "";
		//Relations management
		if(!CollectionUtils.isEmpty(conf.getProjectRelations())) {
			for(ProjectRelation rel: conf.getProjectRelations()) {
				String relationType = rel.getType();
				String nomeTabellaSx = rel.getSxTable();
				String nomeRelazioneSx = rel.getSxName();
				String nomeRelazioneDx = rel.getDxName();
				String nomeTabellaDx = rel.getDxTable();
				String nomeTabella = tabella.getNomeTabella().toLowerCase();

				if(nomeTabellaSx!=null && nomeTabellaDx != null) {
					if(relationType.equals(Utils.OneToOne) || relationType.equals(Utils.ManyToOne)) {
						if (nomeTabellaSx.toLowerCase().equals(nomeTabella)) {
							relMap.put(relationType+nomeTabellaSx+nomeRelazioneSx+SEARCH_FILTER, 
							"        searchFilter = checkAndCompileSearchFilterEquals(myGroupControls, searchFilter, '"+Utils.getFirstLowerCase(nomeRelazioneSx)+"Id');\n");
						}
					}
					if(relationType.equals(Utils.OneToMany)) {
						if (nomeTabellaDx.toLowerCase().equals(nomeTabella)) {
							relMap.put(relationType+nomeTabellaDx+nomeRelazioneDx+SEARCH_FILTER, 
							"        searchFilter = checkAndCompileSearchFilterEquals(myGroupControls, searchFilter, '"+Utils.getFirstLowerCase(nomeRelazioneDx)+"Id');\n");
						}
					}

				}
			}
		}
		
		//Print Relation Map
		res += Utils.printRelationMap(res, relMap);
		return res;
	}
	
	private String printRelations(ConfigCreateProject conf, String section) {
		Map<String, String> relMap = new HashMap<>();
		String res = "";
		
		//Relations management
		if(!CollectionUtils.isEmpty(conf.getProjectRelations())) {
			for(ProjectRelation rel: conf.getProjectRelations()) {
				String relationType = rel.getType();
				String nomeTabellaSx = rel.getSxTable();
				String nomeRelazioneSx = rel.getSxName();
				String nomeRelazioneDx = rel.getDxName();
				String nomeTabellaDx = rel.getDxTable();
				String nomeTabella = tabella.getNomeTabella().toLowerCase();
				
				if(nomeTabellaSx!=null && nomeTabellaDx != null ) {
					boolean isOne2OneOrMany2One = relationType.equals(Utils.OneToOne) || relationType.equals(Utils.ManyToOne);
					boolean isOneToMany = relationType.equals(Utils.OneToMany);
					
					if(IMPORT_SECTION.equals(section)) {
						if(nomeTabellaSx.toLowerCase().equals(nomeTabella) && isOne2OneOrMany2One) {
							relMap.put(relationType+nomeTabellaSx+IMPORT_SECTION+"Interface", 
									"import { I"+Utils.getFirstUpperCase(nomeTabellaDx)+" } from 'app/shared/model/"+Utils.getFirstLowerCase(nomeTabellaDx)+".model';\n");
							relMap.put(relationType+nomeTabellaSx+IMPORT_SECTION+"Model", 
									"import { "+Utils.getFirstUpperCase(nomeTabellaDx)+"Service } from 'app/entities/"+Utils.getFirstLowerCase(nomeTabellaDx)+"';\n");
						}
						if(nomeTabellaDx.toLowerCase().equals(nomeTabella) && isOneToMany) {
							relMap.put(relationType+nomeTabellaDx+IMPORT_SECTION+"Interface", 
									"import { I"+Utils.getFirstUpperCase(nomeTabellaSx)+" } from 'app/shared/model/"+Utils.getFirstLowerCase(nomeTabellaSx)+".model';\n");
							relMap.put(relationType+nomeTabellaDx+IMPORT_SECTION+"Model", 
									"import { "+Utils.getFirstUpperCase(nomeTabellaSx)+"Service } from 'app/entities/"+Utils.getFirstLowerCase(nomeTabellaSx)+"';\n");
						}
						
					}else if(SEARCH.equals(section)) {
						if(nomeTabellaSx.toLowerCase().equals(nomeTabella) && isOne2OneOrMany2One) {
							relMap.put(relationType+nomeTabellaSx+nomeRelazioneSx+SEARCH, 
									"            "+Utils.getFirstLowerCase(nomeRelazioneSx)+"Id: new FormControl(''),\n");
						}
						if(nomeTabellaDx.toLowerCase().equals(nomeTabella) && isOneToMany) {
							relMap.put(relationType+nomeTabellaDx+nomeRelazioneSx+SEARCH, 
									"            "+Utils.getFirstLowerCase(nomeRelazioneDx)+"Id: new FormControl(''),\n");
						}
						
					}else if(INIT_SECTION.equals(section)) {
						if(nomeTabellaSx.toLowerCase().equals(nomeTabella) && isOne2OneOrMany2One) {
							relMap.put(relationType+nomeTabellaSx+nomeRelazioneSx+INIT_SECTION, 
									"    "+Utils.getFirstLowerCase(nomeRelazioneSx)+"s: I"+Utils.getFirstUpperCase(nomeTabellaDx)+"[];\n");
						}
						if(nomeTabellaDx.toLowerCase().equals(nomeTabella) && isOneToMany) {
							relMap.put(relationType+nomeTabellaDx+nomeRelazioneDx+INIT_SECTION, 
									"    "+Utils.getFirstLowerCase(nomeRelazioneDx)+"s: I"+Utils.getFirstUpperCase(nomeTabellaSx)+"[];\n");
						}
					}else if(CONSTRUCTOR_SECTION.equals(section)) {
						if(nomeTabellaSx.toLowerCase().equals(nomeTabella) && isOne2OneOrMany2One) {
							relMap.put(relationType+nomeTabellaSx+CONSTRUCTOR_SECTION, 
									"        private "+Utils.getFirstLowerCase(nomeTabellaDx)+"Service: "+Utils.getFirstUpperCase(nomeTabellaDx)+"Service,\r\n");
						}
						if(nomeTabellaDx.toLowerCase().equals(nomeTabella) && isOneToMany) {
							relMap.put(relationType+nomeTabellaDx+CONSTRUCTOR_SECTION, 
									"        private "+Utils.getFirstLowerCase(nomeTabellaSx)+"Service: "+Utils.getFirstUpperCase(nomeTabellaSx)+"Service,\r\n");
						}
					}else if(TRACKBY_SECTION.equals(section)) {
						if(nomeTabellaSx.toLowerCase().equals(nomeTabella) && relationType.equals(Utils.OneToOne)) {
							relMap.put(relationType+nomeTabellaSx+TRACKBY_SECTION, 
								"    track"+Utils.getFirstUpperCase(nomeTabellaDx)+"ById(index: number, item: I"+Utils.getFirstUpperCase(nomeTabellaDx)+") {\r\n" +
								"        return item.id;\r\n" +
								"    }\r\n");
						}
					}else if(NG_ONINIT_SECTION.equals(section)) {
						if (relationType.equals(Utils.ManyToOne) || relationType.equals(Utils.OneToOne)) {
							if(nomeTabellaSx.toLowerCase().equals(nomeTabella)) {
								relMap.put(relationType+nomeTabellaSx+nomeRelazioneSx+NG_ONINIT_SECTION, 
									"\n        // Relation Type: "+relationType+"\n"+
									"        this."+Utils.getFirstLowerCase(nomeTabellaDx)+"Service.query().subscribe(\n"+
						            "        (res: HttpResponse<I"+Utils.getFirstUpperCase(nomeTabellaDx)+"[]>) => {\n"+
						            "            this."+Utils.getFirstLowerCase(nomeRelazioneSx)+"s = res.body;\n"+
						            "        },\n"+
						            "        (res: HttpErrorResponse) => this.onError(res.message)\n"+
						            "        );\n");
							}
						} else if(isOneToMany) {
							if(nomeTabellaDx.toLowerCase().equals(nomeTabella)) {
								relMap.put(relationType+nomeTabella+nomeRelazioneDx+NG_ONINIT_SECTION, 
										"\n        // Relation Type: "+relationType+"\n"+
										"        this."+Utils.getFirstLowerCase(nomeTabellaSx)+"Service.query().subscribe(\n"+
							            "        (res: HttpResponse<I"+Utils.getFirstUpperCase(nomeTabellaSx)+"[]>) => {\n"+
							            "            this."+Utils.getFirstLowerCase(nomeRelazioneDx)+"s = res.body;\n"+
							            "        },\n"+
							            "        (res: HttpErrorResponse) => this.onError(res.message)\n"+
							            "        );\n");
							}
						}
					}
				
				}
				
			}
		}
		
		//Print Relation Map
		res += Utils.printRelationMap(res, relMap);
				
		return res;
	}

}
