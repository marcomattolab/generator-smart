package it.eng.generate.template.fe.home;

import java.util.List;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateHomeComponentHtml extends AbstractResourceTemplate {

	public TemplateHomeComponentHtml(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "html";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		List<String> authorities = Utils.getGlobalAuthoritiesCredential(conf);
		String body = 
		"<div class=\"row\">\r\n" + 
		"    <div class=\"col-md-3\">\r\n" + 
		"        <span class=\"hipster img-fluid rounded\"></span>\r\n" + 
		"    </div>\r\n" + 
		"    <div class=\"col-md-9\">\r\n" + 
		"        <h1 class=\"display-4\" jhiTranslate=\"home.title\">Welcome, Java Smart Generator!</h1>\r\n" + 
		"        <p class=\"lead\" jhiTranslate=\"home.subtitle\">This is your homepage</p>\r\n\n" + 
		"        <div [ngSwitch]=\"isAuthenticated()\">\r\n" + 
		"            <div class=\"alert alert-success\" *ngSwitchCase=\"true\">\r\n" + 
		"                <span id=\"home-logged-message\" *ngIf=\"account\" jhiTranslate=\"home.logged.message\"\r\n" + 
		"                    translateValues=\"{username: '{{account.login}}'}\"> You are logged in as user \"{{account.login}}\". </span>\r\n" + 
		"            </div>\r\n\n" + 
		"            <div class=\"alert alert-warning\" *ngSwitchCase=\"false\">\r\n" + 
		"                <span jhiTranslate=\"global.messages.info.authenticated.prefix\">If you want to </span>\r\n" + 
		"                <a class=\"alert-link\" (click)=\"login()\" jhiTranslate=\"global.messages.info.authenticated.link\">sign in</a>\r\n" + 
		"                <span jhiTranslate=\"global.messages.info.authenticated.suffix\">, you can try the default accounts:\r\n";
		
		for(String credential: authorities) {
			body+= "	                <br/>- "+Utils.getFirstUpperCase(credential)+" (login=\""+credential+"\" and password=\""+credential+"\") \n";
		}
		
		body+=
		"                </span>\r\n" + 
		"            </div>\r\n" + 
		"            <div class=\"alert alert-warning\" *ngSwitchCase=\"false\">\r\n" + 
		"                <span jhiTranslate=\"global.messages.info.register.noaccount\">You don't have an account yet?</span>&nbsp;\r\n" + 
		"                <a class=\"alert-link\" routerLink=\"register\" jhiTranslate=\"global.messages.info.register.link\">Register a new account</a>\r\n" + 
		"            </div>\r\n" + 
		"        </div>\r\n\n" + 
		"        <p jhiTranslate=\"home.question\">\r\n" + 
		"            If you have any question on Smart Generator:\r\n" + 
		"        </p>\r\n\n" + 
		"        <ul>\r\n" + 
		"            <li><a href=\"http://marcomartorana.it\" target=\"_blank\" rel=\"noopener\" jhiTranslate=\"home.link.homepage\">Smart Geneator Author Homepage</a></li>\r\n" + 
		"            <li><a href=\"https://github.com/marcomattolab/smart-generator/issues?state=open\" target=\"_blank\" rel=\"noopener\" jhiTranslate=\"home.link.bugtracker\">Smart Generator bug tracker</a></li>\r\n" + 
		"            <li><a href=\"https://twitter.com/marcomattox\" target=\"_blank\" rel=\"noopener\" jhiTranslate=\"home.link.follow\">follow @marcomattox on Twitter</a></li>\r\n" + 
		"        </ul>\r\n\n" + 
		"        <p>\r\n" + 
		"            <span jhiTranslate=\"home.like\">If you like Smart Generator, don't forget to give us a star on</span> <a href=\"https://github.com/marcomattolab/smart-generator\" target=\"_blank\" rel=\"noopener\" jhiTranslate=\"home.github\">GitHub</a>!\r\n" + 
		"        </p>\r\n" + 
		"    </div>\r\n" + 
		"</div>\r\n";
		return body;
	}

	public String getClassName(){
		return "home.component";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/webapp/app/home";
	}

}
