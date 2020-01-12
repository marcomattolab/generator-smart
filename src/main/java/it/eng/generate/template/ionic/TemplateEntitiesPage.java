package it.eng.generate.template.ionic;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntitiesPage extends AbstractResourceTemplate {

	public TemplateEntitiesPage(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		
		//TODO ADD IN PROPERTIES
		String authorities = "UserRole.ROLE_ADMIN, UserRole.ROLE_USER"; 
		
		//TODO ADD IN PROPERTIES
        String[] icons = {
        		"'airplane'", "'document'", "'card'", 
        		"'thumbs-up'", "'business'", "'settings'", 
        		"'paper'", "'pin'", "'analytics'",
        		"'alert'", "'albums'", "'apps'",
        		"'funnel'", "'folder'", "'heart'", 
        		"'appstore'", "'archive'", "'camera'", 
        		"'construct'", "'logo-euro'", "'contacts'", 
        		"'call'", "'card'", "'cloud'", "'cog'"};
        
		String body = 
		"import {Component} from '@angular/core';\r\n" +
		"import {NavController} from '@ionic/angular';\r\n" +
		"import {UserRole} from '../../services/user/user.model';\r\n\n" +
		"interface EntityItem {\r\n" +
		"  labelKey: string;\r\n" +
		"  component: string;\r\n" +
		"  route: string;\r\n" +
		"  authorizedRoles: Array<UserRole>;\r\n" +
		"  icon: string;\r\n" +
		"}\r\n\n" +
		"@Component({\r\n" +
		"  selector: 'app-entities',\r\n" +
		"  templateUrl: 'entities.page.html',\r\n" +
		"  styleUrls: ['entities.page.scss']\r\n" +
		"})\r\n" +
		"export class EntitiesPage {\r\n" +
		"  allEntities: Array<EntityItem> = [\r\n";
		
		int i=0;
		int lSize = Utils.getTables(database).size();
		for(Table table: Utils.getTables(database) ) {
			String Tablename = Utils.getEntityName(table);
			String tablename = Utils.getFieldName(table);
			boolean isLast = i+1 == lSize;
			body += 
			"    {\r\n" +
		  //"      labelKey: '"+Tablename+".TITLE',\r\n" +
			"      labelKey: '"+Tablename+"',\r\n" +
			"      component: '"+Tablename+"Page',\r\n" +
			"      route: '"+tablename+"',\r\n" +
			"      authorizedRoles: ["+authorities+"],\r\n" +
			"      icon: "+icons[i++]+"\r\n" +
			"    }"+(isLast?"":",")+"\r\n";
		}
		body +=
		"  ];\r\n\n" +
		"  constructor(public navController: NavController) {\r\n" +
		"  }\r\n" +
		"  openPage(page) {\r\n" +
		"    this.navController.navigateForward('/tabs/entities/' + page.route);\r\n" +
		"  }\r\n" +
		"}\r\n";
		return body;
	}
	
	public String getClassName(){
		return "entities.page";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "mobile/src/app/pages/entities";
	}

}
