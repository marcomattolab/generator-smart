package it.eng.generate.template.fe.account;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateAccountModule extends AbstractResourceTemplate {

	public TemplateAccountModule(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		
		String body = 
		"import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';\r\n" +
		"import { RouterModule } from '@angular/router';\r\n" +
		"import { "+Utils.getClassNameCamelCase(conf.getProjectName()) +"SharedModule } from 'app/shared';\r\n" +
		"import {\r\n" +
		//"    SessionsComponent,\r\n" +
		"    PasswordStrengthBarComponent,\r\n" +
		"    RegisterComponent,\r\n" +
		"    ActivateComponent,\r\n" +
		"    PasswordComponent,\r\n" +
		"    PasswordResetInitComponent,\r\n" +
		"    PasswordResetFinishComponent,\r\n" +
		"    SettingsComponent,\r\n" +
		"    accountState\r\n" +
		"} from './';\r\n" +
		"@NgModule({\r\n" +
		"    imports: ["+Utils.getClassNameCamelCase(conf.getProjectName()) +"SharedModule, RouterModule.forChild(accountState)],\r\n" +
		"    declarations: [\r\n" +
		"        ActivateComponent,\r\n" +
		"        RegisterComponent,\r\n" +
		"        PasswordComponent,\r\n" +
		"        PasswordStrengthBarComponent,\r\n" +
		"        PasswordResetInitComponent,\r\n" +
		"        PasswordResetFinishComponent,\r\n" +
		//"        SessionsComponent,\r\n" +
		"        SettingsComponent\r\n" +
		"    ],\r\n" +
		"    schemas: [CUSTOM_ELEMENTS_SCHEMA]\r\n" +
		"})\r\n" +
		"export class "+Utils.getClassNameCamelCase(conf.getProjectName()) +"AccountModule {}\r\n";
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
		return "src/main/webapp/app/account";
	}

}
