package it.eng.generate.template.ionic;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateHomeModuleIonic extends AbstractResourceTemplate {

	public TemplateHomeModuleIonic(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		
		//TODO ADD IN PROPERTIES
		//String authorities = "'ROLE_ADMIN', 'ROLE_USER'"; 
		String authorities = Utils.getGlobalAuthorities(conf, Utils.APICE);

		String body = 
		"import { CommonModule } from '@angular/common';\r\n" + 
		"import { NgModule } from '@angular/core';\r\n" + 
		"import { FormsModule } from '@angular/forms';\r\n" + 
		"import { RouterModule, Routes } from '@angular/router';\r\n" + 
		"import { IonicModule } from '@ionic/angular';\r\n" + 
		"import { TranslateModule } from '@ngx-translate/core';\r\n" + 
		"import { UserRouteAccessService } from 'src/app/services/auth/user-route-access.service';\r\n" + 
		"import { HomePage } from './home.page';\r\n\n" + 
		"const routes: Routes = [\r\n" + 
		"  {\r\n" + 
		"    path: '',\r\n" + 
		"    component: HomePage,\r\n" + 
		"    data: {\r\n" + 
		"      authorities: ["+authorities+"]\r\n" + 
		"    },\r\n" + 
		"    canActivate: [UserRouteAccessService]\r\n" + 
		"  }\r\n" + 
		"];\r\n\n" + 
		"@NgModule({\r\n" + 
		"  imports: [IonicModule, CommonModule, FormsModule, TranslateModule, RouterModule.forChild(routes)],\r\n" + 
		"  declarations: [HomePage]\r\n" + 
		"})\r\n" + 
		"export class HomePageModule {}\r\n" + 
		"";
		return body;
	}
	
	public String getClassName(){
		return "home.module";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "mobile/src/app/pages/home";
	}

}
