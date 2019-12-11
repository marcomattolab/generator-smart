package it.eng.generate.template.fe.blocks;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateErrorehandlerInterceptor extends AbstractResourceTemplate {

	public TemplateErrorehandlerInterceptor(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = "import { Injectable } from '@angular/core';\r\n" +
		"import { JhiEventManager } from 'ng-jhipster';\r\n" +
		"import { HttpInterceptor, HttpRequest, HttpErrorResponse, HttpHandler, HttpEvent } from '@angular/common/http';\r\n" +
		"import { Observable } from 'rxjs';\r\n" +
		"import { tap } from 'rxjs/operators';\r\n" +
		"@Injectable()\r\n" +
		"export class ErrorHandlerInterceptor implements HttpInterceptor {\r\n" +
		"    constructor(private eventManager: JhiEventManager) {}\r\n" +
		"    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {\r\n" +
		"        return next.handle(request).pipe(\r\n" +
		"            tap(\r\n" +
		"                (event: HttpEvent<any>) => {},\r\n" +
		"                (err: any) => {\r\n" +
		"                    if (err instanceof HttpErrorResponse) {\r\n" +
		"                        if (!(err.status === 401 && (err.message === '' || (err.url && err.url.includes('/api/account'))))) {\r\n" +
		"                            this.eventManager.broadcast({ name: '"+conf.getProjectName()+"App.httpError', content: err });\r\n" +
		"                        }\r\n" +
		"                    }\r\n" +
		"                }\r\n" +
		"            )\r\n" +
		"        );\r\n" +
		"    }\r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "errorhandler.interceptor";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/webapp/app/blocks/interceptor";
	}

}
