package it.eng.generate.template.fe.layouts;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateMainComponent extends AbstractResourceTemplate {

	public TemplateMainComponent(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = "import { Component, OnInit } from '@angular/core';\r\n" +
		"import { Router, ActivatedRouteSnapshot, NavigationEnd } from '@angular/router';\r\n" +
		"import { JhiLanguageHelper } from 'app/core';\r\n" +
		"@Component({\r\n" +
		"    selector: 'jhi-main',\r\n" +
		"    templateUrl: './main.component.html',\r\n" +
		"    styleUrls: ['./main.component.scss']\r\n" +
		"})\r\n" +
		"export class JhiMainComponent implements OnInit {\r\n" +
		"    constructor(private jhiLanguageHelper: JhiLanguageHelper, private router: Router) {}\r\n" +
		"    private getPageTitle(routeSnapshot: ActivatedRouteSnapshot) {\r\n" +
		"        let title: string = routeSnapshot.data && routeSnapshot.data['pageTitle'] ? routeSnapshot.data['pageTitle'] : '"+conf.getProjectName()+"App';\r\n" +
		"        if (routeSnapshot.firstChild) {\r\n" +
		"            title = this.getPageTitle(routeSnapshot.firstChild) || title;\r\n" +
		"        }\r\n" +
		"        return title;\r\n" +
		"    }\r\n" +
		"    ngOnInit() {\r\n" +
		"        this.router.events.subscribe(event => {\r\n" +
		"            if (event instanceof NavigationEnd) {\r\n" +
		"                this.jhiLanguageHelper.updateTitle(this.getPageTitle(this.router.routerState.snapshot.root));\r\n" +
		"            }\r\n" +
		"        });\r\n" +
		"    }\r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "main.component";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/webapp/app/layouts/main";
	}

}
