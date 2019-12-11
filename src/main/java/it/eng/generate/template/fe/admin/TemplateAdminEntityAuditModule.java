package it.eng.generate.template.fe.admin;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateAdminEntityAuditModule extends AbstractResourceTemplate {

	public TemplateAdminEntityAuditModule(DataBase database) {
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
		"import { CommonModule } from '@angular/common';\r\n" +
		"import { DiffMatchPatchModule } from 'ng-diff-match-patch';\r\n" +
		"import { "+Utils.getClassNameCamelCase(conf.getProjectName()) +"SharedModule } from 'app/shared';\r\n" +
		"import { EntityAuditRoutingModule } from './entity-audit-routing.module';\r\n" +
		"import { EntityAuditComponent } from './entity-audit.component';\r\n" +
		"import { EntityAuditModalComponent } from './entity-audit-modal.component';\r\n" +
		"import { EntityAuditService } from './entity-audit.service';\r\n\n" +
		"@NgModule({\r\n" +
		"    imports: [\r\n" +
		"        CommonModule,\r\n" +
		"        "+Utils.getClassNameCamelCase(conf.getProjectName()) +"SharedModule,\r\n" +
		"        DiffMatchPatchModule,\r\n" +
		"        EntityAuditRoutingModule\r\n" +
		"    ],\r\n" +
		"    declarations: [\r\n" +
		"        EntityAuditComponent,\r\n" +
		"        EntityAuditModalComponent\r\n" +
		"    ],\r\n" +
		"    // https://ng-bootstrap.github.io/#/components/modal/examples\r\n" +
		"    entryComponents: [\r\n" +
		"        EntityAuditModalComponent\r\n" +
		"    ],\r\n" +
		"    providers: [\r\n" +
		"        EntityAuditService\r\n" +
		"    ],\r\n" +
		"    schemas: [CUSTOM_ELEMENTS_SCHEMA]\r\n" +
		"})\r\n" +
		"export class EntityAuditModule { }\r\n";
		return body;
	}

	public String getClassName(){
		return "entity-audit.module";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/webapp/app/admin/entity-audit";
	}

}
