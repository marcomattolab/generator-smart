package it.eng.generate.template.fe.entities;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityRoute extends AbstractResourceTemplate {

	public TemplateEntityRoute(Table tabella) {
		super(tabella);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String Nometabella = Utils.getEntityName(tabella);
		String nometabella = Utils.getClassNameLowerCase(tabella);
		String INometabella = Utils.getIName(tabella);
		
		//Authorities
		String authorities = Utils.getAuthorities(tabella, Utils.APICE);
		
		String body = 
		"import { Injectable } from '@angular/core';\r\n" +
		"import { HttpResponse } from '@angular/common/http';\r\n" +
		"import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';\r\n" +
		"import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';\r\n" +
		"import { UserRouteAccessService } from 'app/core';\r\n" +
		"import { Observable, of } from 'rxjs';\r\n" +
		"import { filter, map } from 'rxjs/operators';\r\n" +
		"import { "+Nometabella+" } from 'app/shared/model/"+nometabella+".model';\r\n" +
		"import { "+Nometabella+"Service } from './"+nometabella+".service';\r\n" +
		"import { "+Nometabella+"Component } from './"+nometabella+".component';\r\n" +
		"import { "+Nometabella+"DetailComponent } from './"+nometabella+"-detail.component';\r\n" +
		"import { "+Nometabella+"UpdateComponent } from './"+nometabella+"-update.component';\r\n" +
		"import { "+Nometabella+"DeletePopupComponent } from './"+nometabella+"-delete-dialog.component';\r\n" +
		"import { "+INometabella+" } from 'app/shared/model/"+nometabella+".model';\r\n\n" +
		"@Injectable({ providedIn: 'root' })\r\n" +
		"export class "+Nometabella+"Resolve implements Resolve<"+INometabella+"> {\r\n" +
		"    constructor(private service: "+Nometabella+"Service) {}\r\n" +
		"    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<"+Nometabella+"> {\r\n" +
		"        const id = route.params['id'] ? route.params['id'] : null;\r\n" +
		"        if (id) {\r\n" +
		"            return this.service.find(id).pipe(\r\n" +
		"                filter((response: HttpResponse<"+Nometabella+">) => response.ok),\r\n" +
		"                map(("+nometabella+": HttpResponse<"+Nometabella+">) => "+nometabella+".body)\r\n" +
		"            );\r\n" +
		"        }\r\n" +
		"        return of(new "+Nometabella+"());\r\n" +
		"    }\r\n" +
		"}\r\n\n" +
		"export const "+nometabella+"Route: Routes = [\r\n" +
		"    {\r\n" +
		"        path: '"+nometabella+"',\r\n" +
		"        component: "+Nometabella+"Component,\r\n" +
		"        resolve: {\r\n" +
		"            pagingParams: JhiResolvePagingParams\r\n" +
		"        },\r\n" +
		"        data: {\r\n" +
		"            authorities: ["+authorities+"],\r\n" +
		"            defaultSort: 'id,asc',\r\n" +
		"            pageTitle: '"+conf.getProjectName()+"App."+nometabella+".home.title'\r\n" +
		"        },\r\n" +
		"        canActivate: [UserRouteAccessService]\r\n" +
		"    },\r\n" +
		"    {\r\n" +
		"        path: '"+nometabella+"/:id/view',\r\n" +
		"        component: "+Nometabella+"DetailComponent,\r\n" +
		"        resolve: {\r\n" +
		"            "+nometabella+": "+Nometabella+"Resolve\r\n" +
		"        },\r\n" +
		"        data: {\r\n" +
		"            authorities: ["+authorities+"],\r\n" +
		"            pageTitle: '"+conf.getProjectName()+"App."+nometabella+".home.title'\r\n" +
		"        },\r\n" +
		"        canActivate: [UserRouteAccessService]\r\n" +
		"    },\r\n" +
		"    {\r\n" +
		"        path: '"+nometabella+"/new',\r\n" +
		"        component: "+Nometabella+"UpdateComponent,\r\n" +
		"        resolve: {\r\n" +
		"            "+nometabella+": "+Nometabella+"Resolve\r\n" +
		"        },\r\n" +
		"        data: {\r\n" +
		"            authorities: ["+authorities+"],\r\n" +
		"            pageTitle: '"+conf.getProjectName()+"App."+nometabella+".home.title'\r\n" +
		"        },\r\n" +
		"        canActivate: [UserRouteAccessService]\r\n" +
		"    },\r\n" +
		"    {\r\n" +
		"        path: '"+nometabella+"/:id/edit',\r\n" +
		"        component: "+Nometabella+"UpdateComponent,\r\n" +
		"        resolve: {\r\n" +
		"            "+nometabella+": "+Nometabella+"Resolve\r\n" +
		"        },\r\n" +
		"        data: {\r\n" +
		"            authorities: ["+authorities+"],\r\n" +
		"            pageTitle: '"+conf.getProjectName()+"App."+nometabella+".home.title'\r\n" +
		"        },\r\n" +
		"        canActivate: [UserRouteAccessService]\r\n" +
		"    }\r\n" +
		"];\r\n\n" +
		"export const "+nometabella+"PopupRoute: Routes = [\r\n" +
		"    {\r\n" +
		"        path: '"+nometabella+"/:id/delete',\r\n" +
		"        component: "+Nometabella+"DeletePopupComponent,\r\n" +
		"        resolve: {\r\n" +
		"            "+nometabella+": "+Nometabella+"Resolve\r\n" +
		"        },\r\n" +
		"        data: {\r\n" +
		"            authorities: ["+authorities+"],\r\n" +
		"            pageTitle: '"+conf.getProjectName()+"App."+nometabella+".home.title'\r\n" +
		"        },\r\n" +
		"        canActivate: [UserRouteAccessService],\r\n" +
		"        outlet: 'popup'\r\n" +
		"    }\r\n\n" +
		"];\r\n";
		return body;
	}
	
	public String getClassName(){
		return Utils.getClassNameLowerCase(tabella)+".route";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/webapp/app/entities/"+Utils.getClassNameLowerCase(tabella);
	}

}
