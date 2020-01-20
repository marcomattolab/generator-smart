package it.eng.generate.template.ionic;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityDetailModuleIonic extends AbstractResourceTemplate {

	public TemplateEntityDetailModuleIonic(Table tabella) {
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
		
		//String authorities = "UserRole.ROLE_ADMIN, UserRole.ROLE_USER"; 
		//String authorities = "'ROLE_ADMIN', 'ROLE_USER'"; 
		String authorities = Utils.getAuthorities(tabella,"'");
		
		String body = 
		"import { NgModule } from '@angular/core';\r\n" +
		"import { TranslateModule } from '@ngx-translate/core';\r\n" +
		"import { "+Nometabella+"DetailPage } from './"+nometabella+"-detail';\r\n" +
		"import { IonicModule } from '@ionic/angular';\r\n" +
		"import { CommonModule } from '@angular/common';\r\n" +
		"import { FormsModule } from '@angular/forms';\r\n" +
		"import { Routes, RouterModule } from '@angular/router';\r\n" +
		"import { UserRouteAccessService } from '../../../services/auth/user-route-access.service';\r\n\n" +
		"const routes: Routes = [\r\n" +
		"    {\r\n" +
		"      path: '',\r\n" +
		"      component: "+Nometabella+"DetailPage,\r\n" +
		"      data: {\r\n" +
		"        authorities: ["+authorities+"]\r\n" +
		"      },\r\n" +
		"      canActivate: [UserRouteAccessService]\r\n" +
		"    }\r\n" +
		"  ];\r\n\n" +
		"@NgModule({\r\n" +
		"    declarations: [\r\n" +
		"        "+Nometabella+"DetailPage\r\n" +
		"    ],\r\n" +
		"    imports: [\r\n" +
		"        IonicModule,\r\n" +
		"        CommonModule,\r\n" +
		"        FormsModule,\r\n" +
		"        RouterModule.forChild(routes),\r\n" +
		"        TranslateModule\r\n" +
		"    ]\r\n" +
		"})\r\n" +
		"export class "+Nometabella+"DetailPageModule {\r\n" +
		"}\r\n";

		return body;
	}
	
	public String getClassName(){
		return Utils.getClassNameLowerCase(tabella)+"-detail.module";
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
