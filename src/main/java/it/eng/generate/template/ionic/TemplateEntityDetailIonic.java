package it.eng.generate.template.ionic;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityDetailIonic extends AbstractResourceTemplate {

	public TemplateEntityDetailIonic(Table tabella) {
		super(tabella);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String nometabella = Utils.getClassNameLowerCase(tabella);
		String Nometabella = Utils.getEntityName(tabella);
		
		String body = 
		"import { Component, OnInit } from '@angular/core';\r\n" +
		"import { "+Nometabella+" } from './"+nometabella+".model';\r\n" +
		"import { "+Nometabella+"Service } from './"+nometabella+".service';\r\n" +
		"import { NavController, AlertController } from '@ionic/angular';\r\n" +
		"import { JhiDataUtils } from 'ng-jhipster';\n"+
		"import { ActivatedRoute } from '@angular/router';\r\n\n" +
		"@Component({\r\n" +
		"    selector: 'page-"+nometabella+"-detail',\r\n" +
		"    templateUrl: '"+nometabella+"-detail.html'\r\n" +
		"})\r\n" +
		"export class "+Nometabella+"DetailPage implements OnInit {\r\n" +
		"    "+nometabella+": "+Nometabella+";\r\n\n" +
		"    constructor(\r\n" +
		"        private dataUtils: JhiDataUtils,\n"+
		"        private navController: NavController,\r\n" +
		"        private "+nometabella+"Service: "+Nometabella+"Service,\r\n" +
		"        private activatedRoute: ActivatedRoute,\r\n" +
		"        private alertController: AlertController\r\n" +
		"    ) { }\r\n\n" +
		"    ngOnInit(): void {\r\n" +
		"        this.activatedRoute.data.subscribe((response) => {\r\n" +
		"            this."+nometabella+" = response.data;\r\n" +
		"        });\r\n" +
		"    }\r\n\n" +
		"    open(item: "+Nometabella+") {\r\n" +
		"        this.navController.navigateForward('/tabs/entities/"+nometabella+"/' + item.id + '/edit');\r\n" +
		"    }\r\n\n" +
		"    async deleteModal(item: "+Nometabella+") {\r\n" +
		"        const alert = await this.alertController.create({\r\n" +
		"            header: 'Confirm the deletion?',\r\n" +
		"            buttons: [\r\n" +
		"                {\r\n" +
		"                    text: 'Cancel',\r\n" +
		"                    role: 'cancel',\r\n" +
		"                    cssClass: 'secondary'\r\n" +
		"                }, {\r\n" +
		"                    text: 'Delete',\r\n" +
		"                    handler: () => {\r\n" +
		"                        this."+nometabella+"Service.delete(item.id).subscribe(() => {\r\n" +
		"                            this.navController.navigateForward('/tabs/entities/"+nometabella+"');\r\n" +
		"                        });\r\n" +
		"                    }\r\n" +
		"                }\r\n" +
		"            ]\r\n" +
		"        });\r\n" +
		"        await alert.present();\r\n" +
		"    }\r\n\n" +
		"    byteSize(field) {\r\n" + 
		"        return this.dataUtils.byteSize(field);\r\n" + 
		"    }\n\n"+
		"    openFile(contentType, field) {\r\n" + 
		"        return this.dataUtils.openFile(contentType, field);\r\n" + 
		"    }\n\n"+
		"}\r\n";
		return body;
	}
	
	public String getClassName(){
		return Utils.getClassNameLowerCase(tabella)+"-detail";
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
