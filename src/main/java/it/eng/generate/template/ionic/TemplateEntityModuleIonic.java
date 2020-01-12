package it.eng.generate.template.ionic;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityModuleIonic extends AbstractResourceTemplate {

	public TemplateEntityModuleIonic(Table tabella) {
		super(tabella);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String Nometabella = Utils.getEntityName(tabella);
		String nometabella = Utils.getClassNameLowerCase(tabella);

		//TODO MOVE IN PROPERTY
		//String authorities = "UserRole.ROLE_ADMIN, UserRole.ROLE_USER"; 
		String authorities = "'ROLE_ADMIN', 'ROLE_USER'"; 
		
		String body = 
		"import { NgModule, Injectable } from '@angular/core';\r\n" +
		"import { TranslateModule } from '@ngx-translate/core';\r\n" +
		"import { IonicModule } from '@ionic/angular';\r\n" +
		"import { CommonModule } from '@angular/common';\r\n" +
		"import { Routes, RouterModule, Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';\r\n" +
		"import { UserRouteAccessService } from '../../../services/auth/user-route-access.service';\r\n" +
		"import { FormsModule, ReactiveFormsModule } from '@angular/forms';\r\n" +
		"import { Observable, of } from 'rxjs';\r\n" +
		"import { HttpResponse } from '@angular/common/http';\r\n" +
		"import { filter, map } from 'rxjs/operators';\r\n" +
		"import { "+Nometabella+"Page } from './"+nometabella+"';\r\n" +
		"import { "+Nometabella+"UpdatePage } from './"+nometabella+"-update';\r\n" +
		"import { "+Nometabella+", "+Nometabella+"Service, "+Nometabella+"DetailPage } from '.';\r\n\n" +
		"@Injectable({ providedIn: 'root' })\r\n" +
		"export class "+Nometabella+"Resolve implements Resolve<"+Nometabella+"> {\r\n" +
		"  constructor(private service: "+Nometabella+"Service) {}\r\n\n" +
		"  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<"+Nometabella+"> {\r\n" +
		"    const id = route.params.id ? route.params.id : null;\r\n" +
		"    if (id) {\r\n" +
		"      return this.service.find(id).pipe(\r\n" +
		"        filter((response: HttpResponse<"+Nometabella+">) => response.ok),\r\n" +
		"        map(("+nometabella+": HttpResponse<"+Nometabella+">) => "+nometabella+".body)\r\n" +
		"      );\r\n" +
		"    }\r\n" +
		"    return of(new "+Nometabella+"());\r\n" +
		"  }\r\n" +
		"}\r\n\n" +
		"const routes: Routes = [\r\n" +
		"    {\r\n" +
		"      path: '',\r\n" +
		"      component: "+Nometabella+"Page,\r\n" +
		"      data: {\r\n" +
		"        authorities: ["+authorities+"]\r\n" +
		"      },\r\n" +
		"      canActivate: [UserRouteAccessService]\r\n" +
		"    },\r\n" +
		"    {\r\n" +
		"      path: 'new',\r\n" +
		"      component: "+Nometabella+"UpdatePage,\r\n" +
		"      resolve: {\r\n" +
		"        data: "+Nometabella+"Resolve\r\n" +
		"      },\r\n" +
		"      data: {\r\n" +
		"        authorities: ["+authorities+"]\r\n" +
		"      },\r\n" +
		"      canActivate: [UserRouteAccessService]\r\n" +
		"    },\r\n" +
		"    {\r\n" +
		"      path: ':id/view',\r\n" +
		"      component: "+Nometabella+"DetailPage,\r\n" +
		"      resolve: {\r\n" +
		"        data: "+Nometabella+"Resolve\r\n" +
		"      },\r\n" +
		"      data: {\r\n" +
		"        authorities: ["+authorities+"]\r\n" +
		"      },\r\n" +
		"      canActivate: [UserRouteAccessService]\r\n" +
		"    },\r\n" +
		"    {\r\n" +
		"      path: ':id/edit',\r\n" +
		"      component: "+Nometabella+"UpdatePage,\r\n" +
		"      resolve: {\r\n" +
		"        data: "+Nometabella+"Resolve\r\n" +
		"      },\r\n" +
		"      data: {\r\n" +
		"        authorities: ["+authorities+"]\r\n" +
		"      },\r\n" +
		"      canActivate: [UserRouteAccessService]\r\n" +
		"    }\r\n" +
		"  ];\r\n\n" +
		"@NgModule({\r\n" +
		"    declarations: [\r\n" +
		"        "+Nometabella+"Page,\r\n" +
		"        "+Nometabella+"UpdatePage,\r\n" +
		"        "+Nometabella+"DetailPage\r\n" +
		"    ],\r\n" +
		"    imports: [\r\n" +
		"        IonicModule,\r\n" +
		"        FormsModule,\r\n" +
		"        ReactiveFormsModule,\r\n" +
		"        CommonModule,\r\n" +
		"        TranslateModule,\r\n" +
		"        RouterModule.forChild(routes)\r\n" +
		"    ]\r\n" +
		"})\r\n" +
		"export class "+Nometabella+"PageModule {\r\n" +
		"}\r\n";

		return body;
	}
	
	public String getClassName(){
		return Utils.getClassNameLowerCase(tabella)+".module";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "mobile/src/app/pages/entities/"+Utils.getClassNameLowerCase(tabella);
	}

}
