package it.eng.generate.template.ionic;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityTsIonic extends AbstractResourceTemplate {

	public TemplateEntityTsIonic(Table tabella) {
		super(tabella);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String nometabella = Utils.getClassNameLowerCase(tabella);
		String Nometabella = Utils.getEntityName(tabella);
		
		String body = 
		"import { Component } from '@angular/core';\r\n" +
		"import { NavController, ToastController, Platform, IonItemSliding } from '@ionic/angular';\r\n" +
		"import { filter, map } from 'rxjs/operators';\r\n" +
		"import { HttpResponse } from '@angular/common/http';\r\n" +
		"import { "+Nometabella+" } from './"+nometabella+".model';\r\n" +
		"import { JhiDataUtils } from 'ng-jhipster';\n" + 
		"import { "+Nometabella+"Service } from './"+nometabella+".service';\r\n\n" +
		"@Component({\r\n" +
		"    selector: 'page-"+nometabella+"',\r\n" +
		"    templateUrl: '"+nometabella+".html'\r\n" +
		"})\r\n" +
		"export class "+Nometabella+"Page {\r\n" +
		"    "+nometabella+"s: "+Nometabella+"[];\n" +
		"    inputSearch = '';\n\n"+
		"    // todo: add pagination\r\n\n" +
		"    constructor(\r\n" +
		"        private navController: NavController,\r\n" +
		"        private "+nometabella+"Service: "+Nometabella+"Service,\r\n" +
		"        private toastCtrl: ToastController,\r\n" +
		"        private dataUtils: JhiDataUtils,\n"+
		"        public plt: Platform\r\n" +
		"    ) {\r\n" +
		"        this."+nometabella+"s = [];\r\n" +
		"    }\r\n\n" +
		"    ionViewWillEnter() {\r\n" +
		"        this.loadAll();\r\n" +
		"    }\r\n\n" +
		"    async loadAll(refresher?) {\r\n" +
		"        this."+nometabella+"Service.query().pipe(\r\n" +
		"            filter((res: HttpResponse<"+Nometabella+"[]>) => res.ok),\r\n" +
		"            map((res: HttpResponse<"+Nometabella+"[]>) => res.body)\r\n" +
		"        )\r\n" +
		"        .subscribe(\r\n" +
		"            (response: "+Nometabella+"[]) => {\r\n" +
		"                this."+nometabella+"s = response;\r\n" +
		"                if (typeof(refresher) !== 'undefined') {\r\n" +
		"                    setTimeout(() => {\r\n" +
		"                        refresher.target.complete();\r\n" +
		"                    }, 750);\r\n" +
		"                }\r\n" +
		"            },\r\n" +
		"            async (error) => {\r\n" +
		"                console.error(error);\r\n" +
		"                const toast = await this.toastCtrl.create({message: 'Failed to load data', duration: 2000, position: 'middle'});\r\n" +
		"                toast.present();\r\n" +
		"            });\r\n" +
		"    }\r\n\n" +
		"    trackId(index: number, item: "+Nometabella+") {\r\n" +
		"        return item.id;\r\n" +
		"    }\r\n\n" +
		"    new() {\r\n" +
		"        this.navController.navigateForward('/tabs/entities/"+nometabella+"/new');\r\n" +
		"    }\r\n\n";
		
		body+=
		"    getFiltered"+Nometabella+"s() {\r\n" + 
		"       return this."+nometabella+"s.filter(item => {\r\n" + 
		"          const inputSearch = this.inputSearch.toLowerCase();\r\n";
		String definitionConst = "";
		String returnValue = "";
		int k = 1;
		for (Column column : tabella.getSortedColumns()) {
			String columnname = Utils.getFieldName(column);
			if( Utils.isTextField(column) ) {
				definitionConst 	+= "          const "+columnname+" = item."+columnname+" ? item."+columnname+".toLowerCase() : '';\n";
				returnValue 		+= (k>1?"\n                 || ":" ")+columnname+".indexOf(inputSearch) > -1";
				k++;
			}
		}
		body+= "" + definitionConst + "\n";
		body+= "          return"+ returnValue + " ;\n";
		body+=
		"         });\r\n" + 
		"     }\n\n" +
		
		
		
		
		"    edit(item: IonItemSliding, "+nometabella+": "+Nometabella+") {\r\n" +
		"        this.navController.navigateForward('/tabs/entities/"+nometabella+"/' + "+nometabella+".id + '/edit');\r\n" +
		"        item.close();\r\n" +
		"    }\r\n\n" +
		"    async delete("+nometabella+") {\r\n" +
		"        this."+nometabella+"Service.delete("+nometabella+".id).subscribe(async () => {\r\n" +
		"            const toast = await this.toastCtrl.create(\r\n" +
		"                {message: '"+Nometabella+" deleted successfully.', duration: 3000, position: 'middle'});\r\n" +
		"            toast.present();\r\n" +
		"            this.loadAll();\r\n" +
		"        }, (error) => console.error(error));\r\n" +
		"    }\r\n\n" +
		"    view("+nometabella+": "+Nometabella+") {\r\n" +
		"        this.navController.navigateForward('/tabs/entities/"+nometabella+"/' + "+nometabella+".id + '/view');\r\n" +
		"    }\r\n\n" +
		"    byteSize(field) {\r\n" + 
		"        return this.dataUtils.byteSize(field);\r\n" + 
		"    }\n\n"+
		
		
		"}\r\n";

		return body;
	}
	
	public String getClassName(){
		return Utils.getClassNameLowerCase(tabella);
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
