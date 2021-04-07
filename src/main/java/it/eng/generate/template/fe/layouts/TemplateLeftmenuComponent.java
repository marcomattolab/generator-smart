package it.eng.generate.template.fe.layouts;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateLeftmenuComponent extends AbstractResourceTemplate {

	public TemplateLeftmenuComponent(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "html";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		
		String gAuthorities = Utils.getGlobalAuthorities(conf, Utils.APICE);
		String jhiGAuthorities = "*jhiHasAnyAuthority= \"["+gAuthorities+"]\"";
		
		String body = 
		"<nav id=\"sidebar\" class=\"navbar-dark bg-dark\" [ngClass]=\"{'hidden': sideNavService.hideSideNav }\">\r\n" + 
		"  <ul class=\"navbar-nav\">\r\n" + 
		"    <li class=\"nav-item active\">\r\n" + 
		"      <a class=\"nav-link\" routerLink=\"/\" href=\"#\">Home</a>\r\n" + 
		"    </li>\r\n" + 
		
		//ENTITIES
		"    <li class=\"nav-item\" "  + jhiGAuthorities +" >\r\n" + 
		"      <a class=\"nav-link\">\r\n" + 
		"      	Tables\r\n" + 
		"      	<i class=\"fa fa-caret-down\"></i>\r\n" + 
		"      </a>\r\n\n" + 
		"	  <div class=\"dropdown-container\">\r\n";
		
		for(Table table: Utils.getTables(database)  ) {
			String Tablename = Utils.getEntityName(table);
			String tablename = Utils.getFieldName(table);
			String authorities = Utils.getAuthorities(table, Utils.APICE);
			String jhiAuthorities = "*jhiHasAnyAuthority= \"["+authorities+"]\"";
			
			body+="	    <a "+jhiAuthorities+" class=\"nav-link\" href=\"#\" routerLink=\""+tablename+"\">"+Tablename+"</a>\r\n";
		}		
		body+=
		"	  </div>\r\n" + 
		"    </li>\r\n"; 
		//	
			
		
		body+="  </ul>\r\n";
		body+="</nav>";
		return body;
	}

	public String getClassName(){
		return "leftmenu.component";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/webapp/app/layouts/leftmenu";
	}

}
