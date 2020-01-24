package it.eng.generate.template.ionic;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntitiesPageIonic extends AbstractResourceTemplate {

	public TemplateEntitiesPageIonic(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		
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
		"import {NavController, Platform} from '@ionic/angular';\n"+
		"import {Subscription} from 'rxjs';\n"+
		"import {UserRole} from '../../services/user/user.model';\r\n\n" +
		"interface EntityItem {\r\n" +
		"  labelKey: string;\r\n" +
		"  component: string;\r\n" +
		"  route: string;\r\n" +
		//"  authorizedRoles: Array<UserRole>;\r\n" +
		"  authorizedRoles: Array<String>;\r\n" +
		"  icon: string;\r\n" +
		"}\r\n\n" +
		"@Component({\r\n" +
		"  selector: 'app-entities',\r\n" +
		"  templateUrl: 'entities.page.html',\r\n" +
		"  styleUrls: ['entities.page.scss']\r\n" +
		"})\n\n" +
		"export class EntitiesPage {\r\n" +
		"  allEntities: Array<EntityItem> = [\r\n";
		
		int i=0;
		int lSize = Utils.getTables(database).size();
		for(Table table: Utils.getTables(database) ) {
			String Tablename = Utils.getEntityName(table);
			String tablename = Utils.getFieldName(table);
			
			//String authorities = "UserRole.ROLE_ADMIN, UserRole.ROLE_USER"; 
			//String authorities = "'ROLE_ADMIN', 'ROLE_USER'"; 
			String authorities = Utils.getAuthorities(table, Utils.APICE);
			
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
		
		"  private backButtonSubscription: Subscription;\n\n"+		
		//"  constructor(public navController: NavController) {\n" +
		//"  }\n" +
		"  constructor(\n" + 
		"    public navController: NavController,\n" + 
		"    private platform: Platform) {\n" + 
		"  }\n\n"+
		"  openPage(page) {\r\n" +
		"    this.navController.navigateForward('/tabs/entities/' + page.route);\n" +
		"  }\n\n" +
		"  ionViewDidEnter() {\n" + 
		"    this.backButtonSubscription = this.platform.backButton.subscribe(async () => {\n" + 
		"      this.navController.navigateRoot('/tabs/home');\n" + 
		"    });\n" + 
		"  }\n\n" + 
		"  ionViewWillLeave() {\n" + 
		"    this.backButtonSubscription.unsubscribe();\n" + 
		"  }\n"+
		"}\n";
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
