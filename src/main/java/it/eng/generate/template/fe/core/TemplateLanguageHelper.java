package it.eng.generate.template.fe.core;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateLanguageHelper extends AbstractResourceTemplate {

	public TemplateLanguageHelper(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = "import { Injectable, RendererFactory2, Renderer2 } from '@angular/core';\r\n" +
		"import { Title } from '@angular/platform-browser';\r\n" +
		"import { Router, ActivatedRouteSnapshot } from '@angular/router';\r\n" +
		"import { TranslateService, LangChangeEvent } from '@ngx-translate/core';\r\n" +
		"import { BehaviorSubject, Observable } from 'rxjs';\r\n" +
		"import { LANGUAGES } from 'app/core/language/language.constants';\r\n" +
		"@Injectable({ providedIn: 'root' })\r\n" +
		"export class JhiLanguageHelper {\r\n" +
		"    renderer: Renderer2 = null;\r\n" +
		"    private _language: BehaviorSubject<string>;\r\n" +
		"    constructor(\r\n" +
		"        private translateService: TranslateService,\r\n" +
		"        // tslint:disable-next-line: no-unused-variable\r\n" +
		"        private rootRenderer: RendererFactory2,\r\n" +
		"        private titleService: Title,\r\n" +
		"        private router: Router\r\n" +
		"    ) {\r\n" +
		"        this._language = new BehaviorSubject<string>(this.translateService.currentLang);\r\n" +
		"        this.renderer = rootRenderer.createRenderer(document.querySelector('html'), null);\r\n" +
		"        this.init();\r\n" +
		"    }\r\n" +
		"    getAll(): Promise<any> {\r\n" +
		"        return Promise.resolve(LANGUAGES);\r\n" +
		"    }\r\n" +
		"    get language(): Observable<string> {\r\n" +
		"        return this._language.asObservable();\r\n" +
		"    }\r\n" +
		"    /**\r\n" +
		"     * Update the window title using params in the following\r\n" +
		"     * order:\r\n" +
		"     * 1. titleKey parameter\r\n" +
		"     * 2. $state.$current.data.pageTitle (current state page title)\r\n" +
		"     * 3. 'global.title'\r\n" +
		"     */\r\n" +
		"    updateTitle(titleKey?: string) {\r\n" +
		"        if (!titleKey) {\r\n" +
		"            titleKey = this.getPageTitle(this.router.routerState.snapshot.root);\r\n" +
		"        }\r\n" +
		"        this.translateService.get(titleKey).subscribe(title => {\r\n" +
		"            this.titleService.setTitle(title);\r\n" +
		"        });\r\n" +
		"    }\r\n" +
		"    private init() {\r\n" +
		"        this.translateService.onLangChange.subscribe((event: LangChangeEvent) => {\r\n" +
		"            this._language.next(this.translateService.currentLang);\r\n" +
		"            this.renderer.setAttribute(document.querySelector('html'), 'lang', this.translateService.currentLang);\r\n" +
		"            this.updateTitle();\r\n" +
		"        });\r\n" +
		"    }\r\n" +
		"    private getPageTitle(routeSnapshot: ActivatedRouteSnapshot) {\r\n" +
		"        let title: string = routeSnapshot.data && routeSnapshot.data['pageTitle'] ? routeSnapshot.data['pageTitle'] : '"+conf.getProjectName()+"App';\r\n" +
		"        if (routeSnapshot.firstChild) {\r\n" +
		"            title = this.getPageTitle(routeSnapshot.firstChild) || title;\r\n" +
		"        }\r\n" +
		"        return title;\r\n" +
		"    }\r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "language.helper";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/webapp/app/core/language";
	}

}
