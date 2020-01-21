package it.eng.generate.template.ionic;

import java.util.List;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateLoginPageIonic extends AbstractResourceTemplate {

	public TemplateLoginPageIonic(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "html";
	}

	public String getBody(){
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		
		//String authorities = Utils.getGlobalAuthorities(conf, Utils.APICE);
		List<String> authorities = Utils.getGlobalAuthoritiesCredential(conf);
		
		String body = 
		"<ion-header>\r\n" + 
		"  <ion-toolbar>\r\n" + 
		"    <ion-buttons slot=\"start\">\r\n" + 
		"      <ion-back-button></ion-back-button>\r\n" + 
		"    </ion-buttons>\r\n" + 
		"    <ion-title>{{ 'LOGIN_TITLE' | translate }}</ion-title>\r\n" + 
		"  </ion-toolbar>\r\n" + 
		"</ion-header>\r\n" + 
		"<ion-content class=\"ion-padding\">\r\n" + 
		"  <form (submit)=\"doLogin()\">\r\n" + 
		"    <ion-list>\r\n" + 
		"      <ion-item>\r\n" + 
		"        <ion-label position=\"floating\">{{ 'USERNAME' | translate }}</ion-label>\r\n" + 
		"        <ion-input type=\"string\" [(ngModel)]=\"account.username\" name=\"username\"></ion-input>\r\n" + 
		"      </ion-item>\r\n" + 
		"      <ion-item>\r\n" + 
		"        <ion-label position=\"floating\">{{ 'PASSWORD' | translate }}</ion-label>\r\n" + 
		"        <ion-input type=\"password\" [(ngModel)]=\"account.password\" name=\"password\"></ion-input>\r\n" + 
		"      </ion-item>\r\n" + 
		"      <ion-item>\r\n" + 
		"        <ion-label>{{ 'REMEMBER_ME' | translate }}</ion-label>\r\n" + 
		"        <ion-toggle [(ngModel)]=\"account.rememberMe\" name=\"rememberMe\"></ion-toggle>\r\n" + 
		"      </ion-item>\r\n" + 
		"      <ion-row class=\"ion-padding\">\r\n" + 
		"        <ion-col>\r\n" + 
		"          <ion-button expand=\"block\" type=\"submit\" color=\"primary\" id=\"login\">{{ 'LOGIN_BUTTON' | translate }}</ion-button>\r\n" + 
		"        </ion-col>\r\n" + 
		"      </ion-row>\r\n\n";

		body+=
		"      <!-- Direct Accesses START: Remove before production  -->\r\n"+
		"      <!-- \n"+
		"      <ion-row class=\"ion-padding\">\n";
		for(String credential: authorities) {
			body+=
			"        <ion-col>\n" + 
			"           <ion-chip (click)=\"account.username = '"+credential+"'; account.password = '"+credential+"'; doLogin()\" >\n" + 
			"			  <ion-icon name=\"person\" color=\"primary\"></ion-icon>\n" + 
			//"			  <ion-label>"+Utils.getFirstUpperCase(credential)+" ( "+credential+" / "+credential+" )</ion-label>\n" + 
			"			  <ion-label>"+Utils.getFirstUpperCase(credential)+"</ion-label>\n" + 
			"		   </ion-chip>\n" + 
			"        </ion-col>\n";
		}
		body+="      </ion-row>\n";
		body+="      -->\n\n\n";
		
		
		body+=
		"    </ion-list>\r\n" + 
		"  </form>\r\n" + 
		"</ion-content>\r\n" + 
		"";
		return body;
	}
	
	public String getClassName(){
		return "login.page";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "mobile/src/app/pages/login";
	}

}
