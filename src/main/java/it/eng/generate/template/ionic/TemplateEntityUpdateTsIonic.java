package it.eng.generate.template.ionic;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityUpdateTsIonic extends AbstractResourceTemplate {
	private static final boolean PRINT_RELATIONS = false; //TODO TEST IT!

	public TemplateEntityUpdateTsIonic(Table tabella) {
		super(tabella);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		// https://www.buildmystring.com/
		//TODO DEVELOP JHI TRANSLATE
		// String jhiTranslate = "<span jhiTranslate=\""+conf.getProjectName()+"App."+nometabella+".detail.title\">"+Nometabella+"</span>\r\n";
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String Nometabella = Utils.getEntityName(tabella);
		String nometabella = Utils.getClassNameLowerCase(tabella);
		
		

		String body = 
		"import { Component, OnInit } from '@angular/core';\r\n" +
		"import { FormBuilder, Validators } from '@angular/forms';\r\n" +
		"import { NavController, Platform, ToastController } from '@ionic/angular';\r\n" +
		"import { HttpResponse, HttpErrorResponse } from '@angular/common/http';\r\n" +
		"import { ActivatedRoute } from '@angular/router';\r\n" +
		"import { Observable } from 'rxjs';\r\n" +
		"import { "+Nometabella+" } from './"+nometabella+".model';\r\n" +
		"import { "+Nometabella+"Service } from './"+nometabella+".service';\r\n\n" +
		"@Component({\r\n" +
		"    selector: 'page-"+nometabella+"-update',\r\n" +
		"    templateUrl: '"+nometabella+"-update.html'\r\n" +
		"})\r\n" +
		"export class "+Nometabella+"UpdatePage implements OnInit {\r\n\n" +
		"    "+nometabella+": "+Nometabella+";\r\n" +
		"    isSaving = false;\r\n" +
		"    isNew = true;\r\n" +
		"    isReadyToSave: boolean;\r\n\n" +
		"    form = this.formBuilder.group({\r\n" +
		"        id: [],\r\n" +
		"        nome: [null, [Validators.required]],\r\n" +
		"        cognome: [null, [Validators.required]],\r\n" +
		"        dataNascita: [null, [Validators.required]],\r\n" +
		"        codiceFiscale: [null, []],\r\n" +
		"        cid: [null, []],\r\n" +
		"        sede: [null, []],\r\n" +
		"        profilo: [null, []],\r\n" +
		"        email: [null, []],\r\n" +
		"        telefono: [null, []],\r\n" +
		"    });\r\n\n" +
		"    constructor(\r\n" +
		"        protected activatedRoute: ActivatedRoute,\r\n" +
		"        protected navController: NavController,\r\n" +
		"        protected formBuilder: FormBuilder,\r\n" +
		"        protected platform: Platform,\r\n" +
		"        protected toastCtrl: ToastController,\r\n" +
		"        private "+nometabella+"Service: "+Nometabella+"Service\r\n" +
		"    ) {\r\n\n" +
		"        // Watch the form for changes, and\r\n" +
		"        this.form.valueChanges.subscribe((v) => {\r\n" +
		"            this.isReadyToSave = this.form.valid;\r\n" +
		"        });\r\n" +
		"    }\r\n\n" +
		"    ngOnInit() {\r\n" +
		"        this.activatedRoute.data.subscribe((response) => {\r\n" +
		"            this.updateForm(response.data);\r\n" +
		"            this."+nometabella+" = response.data;\r\n" +
		"            this.isNew = this."+nometabella+".id === null || this."+nometabella+".id === undefined;\r\n" +
		"        });\r\n" +
		"    }\r\n\n" +
		"    updateForm("+nometabella+": "+Nometabella+") {\r\n" +
		"        this.form.patchValue({\r\n" +
		"            id: "+nometabella+".id,\r\n" +
		"            nome: "+nometabella+".nome,\r\n" +
//		"            cognome: persona.cognome,\r\n" +
//		"            dataNascita: persona.dataNascita,\r\n" +
//		"            codiceFiscale: persona.codiceFiscale,\r\n" +
//		"            cid: persona.cid,\r\n" +
//		"            sede: persona.sede,\r\n" +
//		"            profilo: persona.profilo,\r\n" +
//		"            email: persona.email,\r\n" +
//		"            telefono: persona.telefono,\r\n" +
		"        });\r\n" +
		"    }\r\n\n" +
		"    save() {\r\n" +
		"        this.isSaving = true;\r\n" +
		"        const "+nometabella+" = this.createFromForm();\r\n" +
		"        if (!this.isNew) {\r\n" +
		"            this.subscribeToSaveResponse(this."+nometabella+"Service.update("+nometabella+"));\r\n" +
		"        } else {\r\n" +
		"            this.subscribeToSaveResponse(this."+nometabella+"Service.create("+nometabella+"));\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"    protected subscribeToSaveResponse(result: Observable<HttpResponse<"+Nometabella+">>) {\r\n" +
		"        result.subscribe((res: HttpResponse<"+Nometabella+">) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onError(res.error));\r\n" +
		"    }\r\n\n" +
		"    async onSaveSuccess(response) {\r\n" +
		"        let action = 'updated';\r\n" +
		"        if (response.status === 201) {\r\n" +
		"          action = 'created';\r\n" +
		"        }\r\n" +
		"        this.isSaving = false;\r\n" +
		"        const toast = await this.toastCtrl.create({message: `"+Nometabella+" ${action} successfully.`, duration: 2000, position: 'middle'});\r\n" +
		"        toast.present();\r\n" +
		"        this.navController.navigateBack('/tabs/entities/"+nometabella+"');\r\n" +
		"    }\r\n\n" +
		"    previousState() {\r\n" +
		"        window.history.back();\r\n" +
		"    }\r\n\n" +
		"    async onError(error) {\r\n" +
		"        this.isSaving = false;\r\n" +
		"        console.error(error);\r\n" +
		"        const toast = await this.toastCtrl.create({message: 'Failed to load data', duration: 2000, position: 'middle'});\r\n" +
		"        toast.present();\r\n" +
		"    }\r\n\n" +
		"    private createFromForm(): "+Nometabella+" {\r\n" +
		"        return {\r\n" +
		"            ...new "+Nometabella+"(),\r\n" +
		"            id: this.form.get(['id']).value,\r\n" +
		"            nome: this.form.get(['nome']).value,\r\n" +
		"            cognome: this.form.get(['cognome']).value,\r\n" +
		"            dataNascita: this.form.get(['dataNascita']).value,\r\n" +
		"            codiceFiscale: this.form.get(['codiceFiscale']).value,\r\n" +
		"            cid: this.form.get(['cid']).value,\r\n" +
		"            sede: this.form.get(['sede']).value,\r\n" +
		"            profilo: this.form.get(['profilo']).value,\r\n" +
		"            email: this.form.get(['email']).value,\r\n" +
		"            telefono: this.form.get(['telefono']).value,\r\n" +
		"        };\r\n" +
		"    }\r\n\n" +
		"}\r\n";

		
		
		
		
		
//		
//		
//		String body = 
//		"<ion-header>\r\n" +
//		"    <ion-toolbar>\r\n" +
//		"        <ion-buttons slot=\"start\">\r\n" +
//		"            <ion-back-button></ion-back-button>\r\n" +
//		"        </ion-buttons>\r\n" +
//		"        <ion-title>\r\n" +
//		"            "+Nometabella+"\r\n" +
//		"        </ion-title>\r\n" +
//		"    </ion-toolbar>\r\n" +
//		"</ion-header>\r\n\n" +
//		"<ion-content padding>\r\n" +
//		"    <ion-list>\r\n";
//	
//		// Columns
//		for (Column column : tabella.getSortedColumns()) {
//			String ColumnName = Utils.getFieldNameForMethod(column);
//			String columnname = Utils.getFieldName(column);
//			if(Utils.isPrimaryKeyID(column) ) {
//				//System.out.println("#Skip generation for Primary Key ID..");
//				body +=
//				"        <ion-item>\r\n" +
//				"            <ion-label position=\"fixed\">"+ColumnName+"</ion-label>\r\n" +
//				"            <div item-content>\r\n" +
//				"                <span>{{"+nometabella+"."+columnname+"}}</span>\r\n" +
//				"            </div>\r\n" +
//				"        </ion-item>\r\n";
//			} else {
//				body +=
//				"        <ion-item>\r\n" +
//				"            <ion-label position=\"fixed\">"+ColumnName+"</ion-label>\r\n" +
//				"            <div item-content>\r\n" +
//				"                <span>{{"+nometabella+"."+columnname+"}}</span>\r\n" +
//				"            </div>\r\n" +
//				"        </ion-item>\r\n";
//			}
//		}
//		
//		//Relations
//		if(!CollectionUtils.isEmpty(conf.getProjectRelations()) && PRINT_RELATIONS) {
//			for(ProjectRelation rel: conf.getProjectRelations()) {
//				String relationType = rel.getType();
//				String nomeTabellaSx = rel.getSxTable();
//				String nomeRelazioneSx = rel.getSxName();
//				String nomeRelazioneDx = rel.getDxName();
//				String nomeTabellaDx = rel.getDxTable();
//				String nomeSelectSx = rel.getSxSelect();
//				String nomeSelectDx = rel.getDxSelect();
//				String nomeTabella = tabella.getNomeTabella().toLowerCase();
//				
//				if(nomeTabellaSx!=null && nomeTabellaDx != null) {
//					if(relationType.equals(Utils.OneToOne) || relationType.equals(Utils.ManyToOne)) {
//						if ( nomeTabellaSx.toLowerCase().equals(nomeTabella) ) {
//							body += "\n         <!-- Add Relation: OneToOne / ManyToOne -->\n";
//							//String jhiTR = "<span jhiTranslate=\""+conf.getProjectName()+"App."+Utils.getFirstLowerCase(nomeTabellaSx)+"."+Utils.getFirstLowerCase(nomeRelazioneSx)+"\">"+Utils.getFirstUpperCase(nomeRelazioneSx)+"</span>\n";
//							String label = Utils.getFirstUpperCase(nomeRelazioneSx);
//							body += "        <ion-item>\r\n" +
//									"            <ion-label position=\"fixed\">"+label+"</ion-label>\r\n" +
//									"            <div item-content>\r\n" +
//									"                <span>{{"+Utils.getFirstLowerCase(nomeTabellaSx)+"."+Utils.getFirstLowerCase(nomeRelazioneSx)+""+Utils.getFirstUpperCase(nomeSelectSx)+"}}</span>\r\n" +
//									"            </div>\r\n" +
//									"        </ion-item>\r\n";
//						}
//						
//					} else if(relationType.equals(Utils.OneToMany)) {
//						if ( nomeTabellaDx.toLowerCase().equals(nomeTabella) ) {
//							body += "\n        <!-- Add Relation    Name: "+nomeRelazioneDx+"     Type: OneToMany -->\n";
//							//String jhiTR = "<span jhiTranslate=\""+conf.getProjectName()+"App."+Utils.getFirstLowerCase(nomeTabellaDx)+"."+Utils.getFirstLowerCase(nomeRelazioneDx)+"\">"+Utils.getFirstUpperCase(nomeRelazioneDx)+"</span>\n";
//							String label = Utils.getFirstUpperCase(nomeRelazioneDx);	
//							body += "        <ion-item>\r\n" +
//									"            <ion-label position=\"fixed\">"+label+"</ion-label>\r\n" +
//									"            <div item-content>\r\n" +
//									"                <span>{{"+Utils.getFirstLowerCase(nomeTabellaDx)+"."+Utils.getFirstLowerCase(nomeRelazioneDx)+""+Utils.getFirstUpperCase(nomeSelectDx)+"}}</span>\r\n" +
//									"            </div>\r\n" +
//									"        </ion-item>\r\n";
//						}
//						
//					} else if(relationType.equals(Utils.ManyToMany)) {
//						if ( nomeTabellaSx.toLowerCase().equals(nomeTabella) ) {
//							body += "\n        <!-- Add Relation: ManyToMany -->\n";
//							//String jhiTR = body += "<span jhiTranslate=\""+conf.getProjectName()+"App."+Utils.getFirstLowerCase(nomeTabellaSx)+"."+Utils.getFirstLowerCase(nomeRelazioneSx)+"\">"+Utils.getFirstUpperCase(nomeRelazioneSx)+"</span>\n";
//							String label = 	Utils.getFirstUpperCase(nomeRelazioneSx);
//							body += "        <ion-item>\r\n" +
//									"            <ion-label position=\"fixed\">"+label+"</ion-label>\r\n" +
//									"            <div item-content>\r\n" +
//									"                <span>{{"+Utils.getFirstLowerCase(nomeRelazioneSx)+"."+Utils.getFirstLowerCase(nomeSelectSx)+"}}</span>\r\n" +
//									"            </div>\r\n" +
//									"        </ion-item>\r\n";
//						}
//						
//					}
//				}
//			}
//		}
//		//Relations
//		
//		
//		body +=
//		"    </ion-list>\r\n\n" +
//		"    <ion-button expand=\"block\" color=\"primary\" (click)=\"open("+nometabella+")\">{{ 'EDIT_BUTTON' | translate }}</ion-button>\r\n" +
//		"    <ion-button expand=\"block\" color=\"danger\" (click)=\"deleteModal("+nometabella+")\">{{ 'DELETE_BUTTON' | translate }}</ion-button>\r\n\n" +
//		"</ion-content>\r\n";
//		
		return body;
	}
	
	public String getClassName(){
		return Utils.getClassNameLowerCase(tabella)+"-update";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "mobile/src/app/pages/entities/"+Utils.getClassNameLowerCase(tabella);
	}

}
