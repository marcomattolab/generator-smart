package it.eng.generate.template.ionic;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntitiesModuleIonic extends AbstractResourceTemplate {

	public TemplateEntitiesModuleIonic(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		
		String authorities = "'ROLE_USER'"; //TODO ADD IN PROPERTIES

		String body = 
		"import {CommonModule} from '@angular/common';\r\n" +
		"import {NgModule} from '@angular/core';\r\n" +
		"import {FormsModule} from '@angular/forms';\r\n" +
		"import {RouterModule, Routes} from '@angular/router';\r\n" +
		"import {IonicModule} from '@ionic/angular';\r\n" +
		"import {TranslateModule} from '@ngx-translate/core';\r\n" +
		"import {UserRouteAccessService} from 'src/app/services/auth/user-route-access.service';\r\n" +
		"import {EntitiesPage} from './entities.page';\r\n" +
		"import {SharedModule} from '../../shared/shared.module';\r\n\n" +
		"const routes: Routes = [\r\n" +
		"  {\r\n" +
		"    path: '',\r\n" +
		"    component: EntitiesPage,\r\n" +
		"    data: {\r\n" +
		"      authorities: ["+authorities+"]\r\n" +
		"    },\r\n" +
		"    canActivate: [UserRouteAccessService]\r\n" +
		"  }\n" ;
		for(Table table: Utils.getTables(database) ) {
			String Tablename = Utils.getEntityName(table);
			String tablename = Utils.getFieldName(table);
			body += 
			"  , {\r\n" +
			"    path: '"+tablename+"',\r\n" +
			"    loadChildren: './"+tablename+"/"+tablename+".module#"+Tablename+"PageModule'\r\n" +
			"  }\r\n";
		}
		body +=
		"];\r\n" +
		"@NgModule({\r\n" +
		"  imports: [\r\n" +
		"    IonicModule,\r\n" +
		"    CommonModule,\r\n" +
		"    FormsModule,\r\n" +
		"    RouterModule.forChild(routes),\r\n" +
		"    TranslateModule,\r\n" +
		"    SharedModule\r\n" +
		"  ],\r\n" +
		"  declarations: [EntitiesPage]\r\n" +
		"})\r\n" +
		"export class EntitiesPageModule {\r\n" +
		"}\r\n";
		return body;
	}
	
	public String getClassName(){
		return "entities.module";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "mobile/src/app/pages/entities";
	}

}
