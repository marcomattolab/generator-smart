package it.eng.generate.template.fe.account;

import org.springframework.orm.jpa.vendor.Database;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateAccountSettingsModule extends AbstractResourceTemplate {

	public TemplateAccountSettingsModule(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		
		//Authorities
		String authorities = Utils.getGlobalAuthorities(conf, Utils.APICE);
		
		String body = 
		"import { Route } from '@angular/router';\n" + 
		"import { UserRouteAccessService } from 'app/core';\n" + 
		"import { SettingsComponent } from './settings.component';\n\n" + 
		"export const settingsRoute: Route = {\n" + 
		"    path: 'settings',\n" + 
		"    component: SettingsComponent,\n" + 
		"    data: {\n" + 
		"        authorities: ["+authorities+"],\n" + 
		"        pageTitle: 'global.menu.account.settings'\n" + 
		"    },\n" + 
		"    canActivate: [UserRouteAccessService]\n" + 
		"};\n" + 
		"";
		return body;
	}

	public String getClassName(){
		return "settings.route";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/webapp/app/account/settings";
	}

}
