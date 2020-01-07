package it.eng.generate.template.fe.entities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.ProjectRelation;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityUpdateComponentTs extends AbstractResourceTemplate {
	private String IMPORT_SECTION = "IMPORT_SECTION";
	private String INIT_SECTION = "INIT_SECTION";
	private String CONSTRUCTOR_SECTION = "CONSTRUCTOR_SECTION";
	private String TRACKBY_SECTION = "TRACKBY_SECTION";
	private String NG_ONINIT_SECTION = "NG_ONINIT_SECTION";
	
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
		"import { "+Nometabella+"Service } from './"+nometabella+".service';\r\n";
		body += printRelations(conf, IMPORT_SECTION);
		
		body += "\n@Component({\r\n" +
		"    selector: 'jhi-"+nometabella+"-update',\r\n" +
		"    templateUrl: './"+nometabella+"-update.component.html'\r\n" +
		"})\r\n" +
		"export class "+Nometabella+"UpdateComponent implements OnInit {\r\n" +
		"    "+nometabella+": "+INometabella+";\r\n\n" +
		"    isSaving: boolean;\r\n";
		
		//MANAGE RELATIONS(DONE) AND DATES(TODO)
  		//"    incaricos: IIncarico[];\r\n" +
  		//"    dataNascitaDp: any;\r\n" +
		body += printRelations(conf, INIT_SECTION);

		Set<?> set = tabella.getColumnNames();
		for (Iterator<?> iter = set.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
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
		"        private jhiAlertService: JhiAlertService,\r\n";
	  
		body += printRelations(conf, CONSTRUCTOR_SECTION);
	  
		body +=
		"        private "+nometabella+"Service: "+Nometabella+"Service,\r\n" +
		"        private activatedRoute: ActivatedRoute\r\n" +
		"    ) {}\r\n\n" +
		"    ngOnInit() {\r\n" +
		"        this.isSaving = false;\r\n" +
		"        this.activatedRoute.data.subscribe(({ "+nometabella+" }) => {\r\n" +
		"            this."+nometabella+" = "+nometabella+";\r\n";
		
		Set<?> cset = tabella.getColumnNames();
		for (Iterator<?> iter = cset.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
			String nomeColonna = Utils.getFieldName(column);
			if ( Utils.isDateField(column) && !Utils.isLocalDate(column)) {
				body += "            this."+nomeColonna+" = this."+Utils.getClassNameLowerCase(tabella)+"."+nomeColonna+" != null ? this."+Utils.getClassNameLowerCase(tabella)+"."+nomeColonna+".format(DATE_TIME_FORMAT) : null;\r\n";
			}
		}
		body +=
		"        });\r\n";
		
		
		//Relations
		body += printRelations(conf, NG_ONINIT_SECTION);
		body +="    }\r\n\n" +
				
				
		
		"    byteSize(field) {\r\n" +
		"        return this.dataUtils.byteSize(field);\r\n" +
		"    }\r\n\n" +
		"    openFile(contentType, field) {\r\n" +
		"        return this.dataUtils.openFile(contentType, field);\r\n" +
		"    }\r\n\n" +
		"    setFileData(event, entity, field, isImage) {\r\n" +
		"        this.dataUtils.setFileData(event, entity, field, isImage);\r\n" +
		"    }\r\n\n" +
		"    previousState() {\r\n" +
		"        window.history.back();\r\n" +
		"    }\r\n\n" +
		"    save() {\r\n" +
		"        this.isSaving = true;\r\n" ;
		
		for (Iterator<?> iter = cset.iterator(); iter.hasNext();) {
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
		"    }\r\n\n";
		
		body += printRelations(conf, TRACKBY_SECTION);
		
		body += 
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

	private String printRelations(ConfigCreateProject conf, String section) {
		Map<String, String> relMap = new HashMap<>();
		String res = "";
		
		//Relations management
		if(!CollectionUtils.isEmpty(conf.getProjectRelations())) {
			for(ProjectRelation rel: conf.getProjectRelations()) {
				String relationType = rel.getType();
				String nomeTabellaSx = rel.getSxTable();
				String nomeRelazioneSx = rel.getSxName();
				String nomeTabellaDx = rel.getDxTable();
				String nomeRelazioneDx = rel.getDxName();
				String nomeSelectDx = rel.getDxSelect();
				String nomeTabella = tabella.getNomeTabella().toLowerCase();
				
				//Relations OneToOne / ManyToOne
				if(nomeTabellaSx!=null && nomeTabellaDx != null 
						&& (relationType.equals(Utils.OneToOne) || relationType.equals(Utils.ManyToOne))
						&& nomeTabellaSx.toLowerCase().equals(nomeTabella)) {
					if(IMPORT_SECTION.equals(section)) {
						relMap.put(relationType+nomeTabellaSx+IMPORT_SECTION+"Interface", 
								"import { I"+Utils.getFirstUpperCase(nomeTabellaDx)+" } from 'app/shared/model/"+Utils.getFirstLowerCase(nomeTabellaDx)+".model';\n");
						relMap.put(relationType+nomeTabellaSx+IMPORT_SECTION+"Model", 
								"import { "+Utils.getFirstUpperCase(nomeTabellaDx)+"Service } from 'app/entities/"+Utils.getFirstLowerCase(nomeTabellaDx)+"';\n");
						
					}else if(INIT_SECTION.equals(section)) {
						relMap.put(relationType+nomeTabellaSx+nomeRelazioneSx+INIT_SECTION, "    "+Utils.getFirstLowerCase(nomeRelazioneSx)+"s: I"+Utils.getFirstUpperCase(nomeTabellaDx)+"[];\n");
						if (relationType.equals(Utils.OneToOne)) {
							relMap.put(relationType+nomeTabellaSx+nomeRelazioneSx+INIT_SECTION+"ANY", "    id"+Utils.getFirstUpperCase(nomeRelazioneSx)+": any;\n");
						}
						
					}else if(CONSTRUCTOR_SECTION.equals(section)) {
						relMap.put(relationType+nomeTabellaSx+CONSTRUCTOR_SECTION, 
								 "        private "+Utils.getFirstLowerCase(nomeTabellaDx)+"Service: "+Utils.getFirstUpperCase(nomeTabellaDx)+"Service,\r\n");

					}else if(TRACKBY_SECTION.equals(section)) {
						if (relationType.equals(Utils.OneToOne)) {
							relMap.put(relationType+nomeTabellaSx+TRACKBY_SECTION, 
									"    track"+Utils.getFirstUpperCase(nomeTabellaDx)+"ById(index: number, item: I"+Utils.getFirstUpperCase(nomeTabellaDx)+") {\r\n" +
									"        return item.id;\r\n" +
									"    }\r\n\n");
						}
						
					}else if(NG_ONINIT_SECTION.equals(section)) {
						if (relationType.equals(Utils.OneToOne)) {
							
							//KEY => relMap.put(relationType+nomeTabellaSx/*+nomeRelazioneSx*/+NG_ONINIT_SECTION, 
							relMap.put(relationType+nomeTabellaSx+nomeRelazioneSx+NG_ONINIT_SECTION, 
									"\n       this."+Utils.getFirstLowerCase(nomeTabellaDx)+"Service.query({ filter: '"+Utils.getFirstLowerCase(nomeRelazioneDx)+"("+Utils.getFirstLowerCase(nomeSelectDx)+")-is-null' }).subscribe(\n"+
									"         (res: HttpResponse<I"+Utils.getFirstUpperCase(nomeTabellaDx)+"[]>) => {\n"+
						             "         if (!this."+Utils.getFirstLowerCase(nomeTabellaSx)+"."+Utils.getFirstLowerCase(nomeRelazioneSx)+"Id) {\n"+
						             "             this."+Utils.getFirstLowerCase(nomeRelazioneSx)+"s = res.body;\n"+
						             "         } else {\n"+
						             "                  this."+Utils.getFirstLowerCase(nomeTabellaDx)+"Service.find(this."+Utils.getFirstLowerCase(nomeTabellaSx)+"."+Utils.getFirstLowerCase(nomeRelazioneSx)+"Id).subscribe(\n"+
						             "                  (subRes: HttpResponse<I"+Utils.getFirstUpperCase(nomeTabellaDx)+">) => {\n"+
						             "                           this."+Utils.getFirstLowerCase(nomeRelazioneSx)+"s = [subRes.body].concat(res.body);\n"+
						             "                  },\n"+
						             "                  (subRes: HttpErrorResponse) => this.onError(subRes.message)\n"+
						             "                  );\n"+
						             "               }\n"+
						             "         },\n"+
						             "         (res: HttpErrorResponse) => this.onError(res.message)\n"+
						             "         );\n\n"+
						             "         console.log(this."+Utils.getFirstLowerCase(nomeTabellaSx)+"."+Utils.getFirstLowerCase(nomeRelazioneSx)+"Id);\n");
							
						} else if (relationType.equals(Utils.ManyToOne)) {
//							res +=  "\n        this."+Utils.getFirstLowerCase(nomeTabellaDx)+"Service.query().subscribe(\n"+
//						            "        (res: HttpResponse<I"+Utils.getFirstUpperCase(nomeTabellaDx)+"[]>) => {\n"+
//						            "            this."+Utils.getFirstLowerCase(nomeRelazioneSx)+"s = res.body;\n"+
//						            "        },\n"+
//						            "        (res: HttpErrorResponse) => this.onError(res.message)\n"+
//						            "        );\n\n";
							relMap.put(relationType+nomeTabellaSx+nomeRelazioneSx+NG_ONINIT_SECTION, 
									"\n        this."+Utils.getFirstLowerCase(nomeTabellaDx)+"Service.query().subscribe(\n"+
						            "        (res: HttpResponse<I"+Utils.getFirstUpperCase(nomeTabellaDx)+"[]>) => {\n"+
						            "            this."+Utils.getFirstLowerCase(nomeRelazioneSx)+"s = res.body;\n"+
						            "        },\n"+
						            "        (res: HttpErrorResponse) => this.onError(res.message)\n"+
						            "        );\n");
						}
					}
				}
				
				
				//Relations OneToMany
				if(nomeTabellaSx!=null && nomeTabellaDx != null  && relationType.equals(Utils.OneToMany)
						&& nomeTabellaDx.toLowerCase().equals(nomeTabella)) {
					
					if(IMPORT_SECTION.equals(section)) {
						relMap.put(relationType+nomeTabellaSx+IMPORT_SECTION+"Interface", 
								"import { I"+Utils.getFirstUpperCase(nomeTabellaSx)+" } from 'app/shared/model/"+Utils.getFirstLowerCase(nomeTabellaSx)+".model';\n");
						relMap.put(relationType+nomeTabellaSx+IMPORT_SECTION+"Model", 
								"import { "+Utils.getFirstUpperCase(nomeTabellaSx)+"Service } from 'app/entities/"+Utils.getFirstLowerCase(nomeTabellaSx)+"';\n");
						
					}else if(INIT_SECTION.equals(section)) {
						relMap.put(relationType+nomeTabellaSx+nomeRelazioneDx+INIT_SECTION, 
								// DONE settores ==> mysectors / nomeTabellaDx ==> nomeRelazioneSx
								"    "+Utils.getFirstLowerCase(nomeRelazioneDx)+"s: I"+Utils.getFirstUpperCase(nomeTabellaDx)+"[];\n");
						
					}else if(CONSTRUCTOR_SECTION.equals(section)) {
						relMap.put(relationType+nomeTabellaSx+CONSTRUCTOR_SECTION, 
								"        private "+Utils.getFirstLowerCase(nomeTabellaSx)+"Service: "+Utils.getFirstUpperCase(nomeTabellaSx)+"Service,\r\n");
						
					}else if(TRACKBY_SECTION.equals(section)) {
						relMap.put(relationType+nomeTabellaSx+TRACKBY_SECTION, 
								"    track"+Utils.getFirstUpperCase(nomeTabellaSx)+"ById(index: number, item: I"+Utils.getFirstUpperCase(nomeTabellaSx)+") {\r\n" +
								"        return item.id;\r\n" +
								"    }\r\n\n");
						
					}else if(NG_ONINIT_SECTION.equals(section)) {
						relMap.put(relationType+nomeTabellaSx+nomeRelazioneDx+INIT_SECTION, 
								"\n        this."+Utils.getFirstLowerCase(nomeTabellaSx)+"Service.query().subscribe(\n"+
					            "        (res: HttpResponse<I"+Utils.getFirstUpperCase(nomeTabellaSx)+"[]>) => {\n"+
					            "            this."+Utils.getFirstLowerCase(nomeRelazioneDx)+"s = res.body;\n"+
					            "        },\n"+
					            "        (res: HttpErrorResponse) => this.onError(res.message)\n"+
					            "        );\n");
						
						
					}
				}
				

				// DONE Relations ManyToMany ===> ex.  Company{myKeyword(keywordCode)} to CompanyKeyword{myCompany(companyName)}
				if(nomeTabellaSx!=null && nomeTabellaDx != null 
						&& relationType.equals(Utils.ManyToMany)
						&& nomeTabellaSx.toLowerCase().equals(nomeTabella)) {
					if(IMPORT_SECTION.equals(section)) {
						relMap.put(relationType+nomeTabellaSx+IMPORT_SECTION+"Interface", 
							"import { I"+Utils.getFirstUpperCase(nomeTabellaDx)+" } from 'app/shared/model/"+nomeTabellaDx.toLowerCase()+".model';\n");
						relMap.put(relationType+nomeTabellaSx+IMPORT_SECTION+"Model", 
							"import { "+Utils.getFirstUpperCase(nomeTabellaDx)+"Service } from 'app/entities/"+nomeTabellaDx.toLowerCase()+"';\n");
					
					}else if(INIT_SECTION.equals(section)) {
						relMap.put(relationType+nomeTabellaSx+nomeRelazioneSx+INIT_SECTION, 
									"    "+Utils.getFirstLowerCase(nomeRelazioneSx)+"s: I"+Utils.getFirstUpperCase(nomeTabellaDx)+"[];\n");
						// FIX settores ==> mysectors / nomeTabellaDx ==> nomeRelazioneSx
						
					}else if(CONSTRUCTOR_SECTION.equals(section)) {
						relMap.put(relationType+nomeTabellaSx+CONSTRUCTOR_SECTION, 
							"        private "+Utils.getFirstLowerCase(nomeTabellaDx)+"Service: "+Utils.getFirstUpperCase(nomeTabellaDx)+"Service,\r\n");
					
					}else if(TRACKBY_SECTION.equals(section)) {
						relMap.put(relationType+nomeTabellaSx+TRACKBY_SECTION, 
							"    track"+Utils.getFirstUpperCase(nomeTabellaDx)+"ById(index: number, item: I"+Utils.getFirstUpperCase(nomeTabellaDx)+") {\r\n" +
							"        return item.id;\r\n" +
							"    }\r\n\n");
					
					}else if(NG_ONINIT_SECTION.equals(section)) {
						relMap.put(relationType+nomeTabellaSx+nomeRelazioneSx+INIT_SECTION, 
							"\n        this."+Utils.getFirstLowerCase(nomeTabellaDx)+"Service.query().subscribe(\n"+
				            "        (res: HttpResponse<I"+Utils.getFirstUpperCase(nomeTabellaDx)+"[]>) => {\n"+
				            "            this."+Utils.getFirstLowerCase(nomeRelazioneSx)+"s = res.body;\n"+
				            "        },\n"+
				            "        (res: HttpErrorResponse) => this.onError(res.message)\n"+
				            "        );\n");
						
					}
				}
				//
				
				
			}
		}
		
		//Print Relation Map
		res += Utils.printRelationMap(res, relMap);
				
		return res;
	}
	
}
