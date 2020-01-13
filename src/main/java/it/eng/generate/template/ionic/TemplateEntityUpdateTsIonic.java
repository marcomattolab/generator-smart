package it.eng.generate.template.ionic;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityUpdateTsIonic extends AbstractResourceTemplate {
	
	public TemplateEntityUpdateTsIonic(Table tabella) {
		super(tabella);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String Nometabella = Utils.getEntityName(tabella);
		String nometabella = Utils.getClassNameLowerCase(tabella);
		
		boolean printRelation = conf.isPrintRelation();

		String body = 
		"import { Component, OnInit } from '@angular/core';\r\n" +
		"import { FormBuilder, Validators } from '@angular/forms';\r\n" +
		"import { NavController, Platform, ToastController } from '@ionic/angular';\r\n" +
		"import { HttpResponse, HttpErrorResponse } from '@angular/common/http';\r\n" +
		"import { ActivatedRoute } from '@angular/router';\r\n";
		if(Utils.hasColumnAttachment( tabella.getSortedColumns())) { //Type: Allegato - Clob/Blob
			body+= "import {Camera, CameraOptions} from '@ionic-native/camera/ngx';\r\n";
		}
		body+=
		"import { Observable } from 'rxjs';\r\n" +
		"import { getMomentDateNoTZ } from '../../../shared/util/moment-util';\r\n" +
		"import { "+Nometabella+" } from './"+nometabella+".model';\r\n" +
		"import { "+Nometabella+"Service } from './"+nometabella+".service';\r\n\n" +
		"@Component({\r\n" +
		"    selector: 'page-"+nometabella+"-update',\r\n" +
		"    templateUrl: '"+nometabella+"-update.html'\r\n" +
		"})\r\n" +
		
		
		"export class "+Nometabella+"UpdatePage implements OnInit {\r\n\n" +
		"    "+nometabella+": "+Nometabella+";\r\n";
		//RELATIONS - TODO DEVELOP
		//		trasfertas: Trasferta[];
		//		strutturas: Struttura[];
		//COLUMNS
		for (Column column : tabella.getSortedColumns()) {
			String columnname = Utils.getFieldName(column);
			if(Utils.isDateField(column) ) {
				body +="    "+columnname+"Dp: any;\r\n";
			}
		}
		
		// Type: Allegato - Clob/Blob
		if(Utils.hasColumnAttachment( tabella.getSortedColumns())) {
			body+=  "    @ViewChild('fileInput', {static: true}) fileInput;\n"+ 
					"    cameraOptions: CameraOptions;\n";
		}
				
		body+=
		"    isSaving = false;\r\n" +
		"    isNew = true;\r\n" +
		"    isReadyToSave: boolean;\r\n\n" +
		
		
		"    form = this.formBuilder.group({\r\n";
		//RELATIONS - TODO DEVELOP
		//		body +=
		//		"        trasferta: [null, []],\n"+
		//		"        structure: [null, []],\n";
		
		//COLUMNS
		for (Column column : tabella.getSortedColumns()) {
			String columnname = Utils.getFieldName(column);
			boolean isNullable = column.isNullable();
			
			if(Utils.isPrimaryKeyID(column) ) {
				body +="        id: [],\r\n";
			
			} else if( Utils.isBlob(column) || Utils.isClob(column) ) {
				body +="        "+columnname+": [null, ["+(column.isNullable() ? "" : "Validators.required")+"]],\n";
				body +="        "+columnname+"ContentType: [null, []],\n";
				
			} else {
				body +="        "+columnname+": [null, ["+( isNullable?"":"Validators.required")+"]],\r\n";
			}
			
		}
		
		body +=	
		"    });\r\n\n"+
		
		
		"    constructor(\r\n" +
		//RELATIONS - TODO DEVELOP
		//"        private trasfertaService: TrasfertaService,\n"+
		//"        private strutturaService: StrutturaService,\n"+
		
		"        protected activatedRoute: ActivatedRoute,\r\n" +
		"        protected navController: NavController,\r\n" +
		"        protected formBuilder: FormBuilder,\r\n" +
		"        protected platform: Platform,\r\n" +
		"        protected toastCtrl: ToastController,\r\n" +
		"        private "+nometabella+"Service: "+Nometabella+"Service\r\n"+
		"    ) {\r\n\n" +
		"        // Watch the form for changes\r\n" +
		"        this.form.valueChanges.subscribe((v) => {\r\n" +
		"            this.isReadyToSave = this.form.valid;\r\n" +
		"        });\r\n\n" ;
	
		
		//IF BLOB / CLOB 
		if(Utils.hasColumnAttachment( tabella.getSortedColumns() )) {
			body +=	"//Set the Camera options\n"+
					"this.cameraOptions = {\n"+
					"  quality: 100,\n"+
					"  targetWidth: 900,\n"+
					"  targetHeight: 600,\n"+
					"  destinationType: this.camera.DestinationType.DATA_URL,\n"+
					"  encodingType: this.camera.EncodingType.JPEG,\n"+
					"  mediaType: this.camera.MediaType.PICTURE,\n"+
					"  saveToPhotoAlbum: false,\n"+
					"  allowEdit: true,\n"+
					"  sourceType: 1\n"+
					"};\n\n";
		}
		
		body +=	
		"    }\r\n\n" +
		"    ngOnInit() {\r\n" +
		"        this.activatedRoute.data.subscribe((response) => {\r\n" +
		"            this.updateForm(response.data);\r\n" +
		"            this."+nometabella+" = response.data;\r\n" +
		"            this.isNew = this."+nometabella+".id === null || this."+nometabella+".id === undefined;\r\n" +
		"        });\r\n" +
		"    }\r\n\n" +
		"    updateForm("+nometabella+": "+Nometabella+") {\r\n" +
		"        this.form.patchValue({\r\n";
		//COLUMNS
		for (Column column : tabella.getSortedColumns()) {
			String columnname = Utils.getFieldName(column);
			if(Utils.isPrimaryKeyID(column) ) {
				body +="            "+columnname+": "+nometabella+"."+columnname+",\r\n";
			} else {
				body +="            "+columnname+": "+nometabella+"."+columnname+",\r\n";
			}
		}
		//RELATIONS - TODO DEVELOP
		//body +="            trasferta: spesa.trasfertaId,\n";
		//body +="            structure: spesa.structureId,\n";
	      
		body +=
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
		
		
		"    private createFromForm(): "+Nometabella+" {\r\n";
		//TYPE DATE
		for (Column column : tabella.getSortedColumns()) {
			String columnname = Utils.getFieldName(column);
			if( Utils.isDateField(column) ) { //DATE FIELDS
				body +="          const "+columnname+" = this.form.get(['"+columnname+"']).value;\n";
				body +="          const "+columnname+"Value = "+columnname+" ? getMomentDateNoTZ("+columnname+") : null;\n\n";
			}
		}
		
		body +=
		"        return {\r\n" +
		"            ...new "+Nometabella+"(),\r\n";
		
		//COLUMNS
		for (Column column : tabella.getSortedColumns()) {
			String columnname = Utils.getFieldName(column);
			if(Utils.isPrimaryKeyID(column) ) {
				body +="            "+columnname+": this.form.get(['"+columnname+"']).value,\r\n";
			} else if( Utils.isDateField(column) ) { //DATE FIELDS
				body +="            "+columnname+": "+columnname+"Value,\r\n";
			} else {
				body +="            "+columnname+": this.form.get(['"+columnname+"']).value,\r\n";
			}
		}
		
		body +=
		"        };\r\n" +
		"    }\r\n\n";
		
		
		//IF BLOB / CLOB SECTION
		if(Utils.hasColumnAttachment( tabella.getSortedColumns())) {
			body += "  getPicture(fieldName) {\r\n" +
					"    if (Camera.installed()) {\r\n" +
					"      this.camera.getPicture(this.cameraOptions).then((data) => {\r\n" +
					"        this.giustificativo[fieldName] = data;\r\n" +
					"        this.giustificativo[fieldName + 'ContentType'] = 'image/jpeg';\r\n" +
					"        this.form.patchValue({[fieldName]: data});\r\n" +
					"        this.form.patchValue({[fieldName + 'ContentType']: 'image/jpeg'});\r\n" +
					"      }, (err) => {\r\n" +
					"        alert('Unable to take photo');\r\n" +
					"      });\r\n" +
					"    } else {\r\n" +
					"      this.fileInput.nativeElement.click();\r\n" +
					"    }\r\n" +
					"  }\r\n\n" +
					"  processWebImage(event, fieldName) {\r\n" +
					"    const reader = new FileReader();\r\n" +
					"    reader.onload = (readerEvent) => {\r\n" +
					"      let imageData = (readerEvent.target as any).result;\r\n" +
					"      const imageType = event.target.files[0].type;\r\n" +
					"      imageData = imageData.substring(imageData.indexOf(',') + 1);\r\n" +
					"      this.form.patchValue({[fieldName]: imageData});\r\n" +
					"      this.form.patchValue({[fieldName + 'ContentType']: imageType});\r\n" +
					"    };\r\n" +
					"    reader.readAsDataURL(event.target.files[0]);\r\n" +
					"  }\r\n\n" +
					"  clearInputImage(field: string, fieldContentType: string, idInput: string) {\r\n" +
					"    this.dataUtils.clearInputImage(this.giustificativo, this.elementRef, field, fieldContentType, idInput);\r\n" +
					"    this.form.patchValue({[field]: ''});\r\n" +
					"  }\r\n\n";
		}
		
		
		//RELATIONS - TODO DEVELOP  
		//		body +=
		//		"    compareTrasferta(first: Trasferta, second: Trasferta): boolean {\n"+
		//		"       return first && second ? first.id === second.id : first === second;\n"+
		//		"    }\n\n"+
		//		"    compareStruttura(first: Struttura, second: Struttura): boolean {\n"+
		//		"       return first && second ? first.id === second.id : first === second;\n"+
		//		"    }\n\n"+
		
		
		
		body +=
		"}\r\n";

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
