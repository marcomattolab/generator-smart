package it.eng.generate.template.ionic;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateAccountModuleIonic extends AbstractResourceTemplate {

	public TemplateAccountModuleIonic(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		
		//String authorities = "'ROLE_ADMIN', 'ROLE_USER'"; 
		String authorities = Utils.getGlobalAuthorities(conf, Utils.APICE);
		
		String body = 
		"import { CommonModule } from '@angular/common';\n" + 
		"import { NgModule } from '@angular/core';\n" + 
		"import { FormsModule } from '@angular/forms';\n" + 
		"import { RouterModule, Routes } from '@angular/router';\n" + 
		"import { IonicModule } from '@ionic/angular';\n" + 
		"import { TranslateModule } from '@ngx-translate/core';\n" + 
		"import { UserRouteAccessService } from 'src/app/services/auth/user-route-access.service';\n" + 
		"import { AccountPage } from './account.page';\n\n" + 
		"const routes: Routes = [\n" + 
		"  {\n" + 
		"    path: '',\n" + 
		"    component: AccountPage,\n" + 
		"    data: {\n" + 
		"      authorities: ["+authorities+"]\n" + 
		"    },\n" + 
		"    canActivate: [UserRouteAccessService]\n" + 
		"  }\n" + 
		"];\n\n" + 
		"@NgModule({\n" + 
		"  imports: [IonicModule, CommonModule, FormsModule, RouterModule.forChild(routes), TranslateModule],\n" + 
		"  declarations: [AccountPage]\n" + 
		"})\n" + 
		"export class AccountPageModule {}\n" + 
		"";
		return body;
	}
	
	public String getClassName(){
		return "account.module";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "mobile/src/app/pages/account";
	}

}
