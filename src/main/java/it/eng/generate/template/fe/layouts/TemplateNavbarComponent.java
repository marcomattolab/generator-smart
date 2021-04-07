package it.eng.generate.template.fe.layouts;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateNavbarComponent extends AbstractResourceTemplate {

	public TemplateNavbarComponent(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "html";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		
		String body = 
		"<nav class=\"navbar navbar-dark navbar-expand-md jh-navbar\">\r\n" +
		"   <!--\r\n" + 
		"	<button type=\"button\" class=\"navbar-toggler\" (click)=\"sideNavService.toggleSideNav()\">\r\n" + 
		"	  <span class=\"navbar-toggler-icon\"></span>\r\n" + 
		"	</button>\r\n" + 
		"	-->\r\n" + 
		"	<button type=\"button\" (click)=\"sideNavService.toggleSideNav()\">\r\n" + 
		"	  <span class=\"navbar-toggler-icon\"></span>\r\n" + 
		"	</button>"+
				
		"    <div class=\"jh-logo-container float-left\">\r\n" +
		"        <a class=\"jh-navbar-toggler d-lg-none float-right\" href=\"javascript:void(0);\" data-toggle=\"collapse\" data-target=\"#navbarResponsive\" aria-controls=\"navbarResponsive\" aria-expanded=\"false\" aria-label=\"Toggle navigation\" (click)=\"toggleNavbar()\">\r\n" +
		"            <fa-icon icon=\"bars\"></fa-icon>\r\n" +
		"        </a>\r\n" +
		"        <a class=\"navbar-brand logo float-left\" routerLink=\"/\" (click)=\"collapseNavbar()\">\r\n" +
		"            <span class=\"logo-img\"></span>\r\n" +
		"            <span jhiTranslate=\"global.title\" class=\"navbar-title\">"+Utils.getClassNameCamelCase(conf.getProjectName()) +"</span> <span class=\"navbar-version\">{{version}}</span>\r\n" +
		"        </a>\r\n" +
		"    </div>\r\n" +
		"    <div class=\"navbar-collapse collapse\" id=\"navbarResponsive\" [ngbCollapse]=\"isNavbarCollapsed\" [ngSwitch]=\"isAuthenticated()\">\r\n" +
		"        <ul class=\"navbar-nav ml-auto\">\r\n" +
		"            <li class=\"nav-item\" routerLinkActive=\"active\" [routerLinkActiveOptions]=\"{exact: true}\">\r\n" +
		"                <a class=\"nav-link\" routerLink=\"/\" (click)=\"collapseNavbar()\">\r\n" +
		"                    <span>\r\n" +
		"                        <fa-icon icon=\"home\"></fa-icon>\r\n" +
		"                        <span jhiTranslate=\"global.menu.home\">Home</span>\r\n" +
		"                    </span>\r\n" +
		"                </a>\r\n" +
		"            </li>\r\n" +
		
		//Dashboard Demo
		"            <li *ngSwitchCase=\"true\" ngbDropdown class=\"nav-item dropdown pointer\" routerLinkActive=\"active\" [routerLinkActiveOptions]=\"{exact: true}\">\r\n" +
		"                <a class=\"nav-link dropdown-toggle\" ngbDropdownToggle href=\"javascript:void(0);\" id=\"dashboard-menu\">\r\n" +
		"                    <span>\r\n" +
		"                        <i class=\"fa fa-area-chart\" aria-hidden=\"true\"></i>\r\n" +
		"                        <span jhiTranslate=\"global.menu.dashboard.main\">Dashboard</span>\r\n" +
		"                        <b class=\"caret\"></b>\r\n" +
		"                    </span>\r\n" +
		"                </a>\r\n" +
		"                <ul class=\"dropdown-menu\" ngbDropdownMenu>\r\n" +
		"                    <li uiSrefActive=\"active\">\r\n" +
		"                        <a class=\"dropdown-item\" routerLink=\"barchart\" routerLinkActive=\"active\" (click)=\"collapseNavbar()\">\r\n" +
		"                            <i class=\"fa fa-fw fa-bar-chart\" aria-hidden=\"true\"></i>\r\n" +
		"                            <span jhiTranslate=\"global.menu.dashboard.barchart\">BarChart</span>\r\n" +
		"                        </a>\r\n" +
		"                    </li>\r\n" +
		"                    <li uiSrefActive=\"active\">\r\n" +
		"                        <a class=\"dropdown-item\" routerLink=\"doughnutchart\" routerLinkActive=\"active\" (click)=\"collapseNavbar()\">\r\n" +
		"                            <i class=\"fa fa-fw fa-circle-o-notch\" aria-hidden=\"true\"></i>\r\n" +
		"                            <span jhiTranslate=\"global.menu.dashboard.doughnutchart\">BarChart</span>\r\n" +
		"                        </a>\r\n" +
		"                    </li>\r\n" +
		"                    <li uiSrefActive=\"active\">\r\n" +
		"                        <a class=\"dropdown-item\" routerLink=\"linechart\" routerLinkActive=\"active\" (click)=\"collapseNavbar()\">\r\n" +
		"                            <i class=\"fa fa-fw fa-line-chart\" aria-hidden=\"true\"></i>\r\n" +
		"                            <span jhiTranslate=\"global.menu.dashboard.linechart\">LineChart</span>\r\n" +
		"                        </a>\r\n" +
		"                    </li>\r\n" +
		"                    <li uiSrefActive=\"active\">\r\n" +
		"                        <a class=\"dropdown-item\" routerLink=\"piechart\" routerLinkActive=\"active\" (click)=\"collapseNavbar()\">\r\n" +
		"                            <i class=\"fa fa-fw fa-pie-chart\" aria-hidden=\"true\"></i>\r\n" +
		"                            <span jhiTranslate=\"global.menu.dashboard.piechart\">PieChart</span>\r\n" +
		"                        </a>\r\n" +
		"                    </li>\r\n" +
		"                    <li uiSrefActive=\"active\">\r\n" +
		"                        <a class=\"dropdown-item\" routerLink=\"polarareachart\" routerLinkActive=\"active\" (click)=\"collapseNavbar()\">\r\n" +
		"                            <i class=\"fa fa-fw fa-bullseye\" aria-hidden=\"true\"></i>\r\n" +
		"                            <span jhiTranslate=\"global.menu.dashboard.polarareachart\">PolarAreaChart</span>\r\n" +
		"                        </a>\r\n" +
		"                    </li>\r\n" +
		"                    <li uiSrefActive=\"active\">\r\n" +
		"                        <a class=\"dropdown-item\" routerLink=\"radarchart\" routerLinkActive=\"active\" (click)=\"collapseNavbar()\">\r\n" +
		"                            <i class=\"fa fa-fw fa-star-o\" aria-hidden=\"true\"></i>\r\n" +
		"                            <span jhiTranslate=\"global.menu.dashboard.radarchart\">RadarChart</span>\r\n" +
		"                        </a>\r\n" +
		"                    </li>\r\n" +
		"                </ul>\r\n" +
		"            </li>\r\n" +
		//
		
		"            <li *ngSwitchCase=\"true\" ngbDropdown class=\"nav-item dropdown pointer\" routerLinkActive=\"active\" [routerLinkActiveOptions]=\"{exact: true}\">\r\n" +
		"                <a class=\"nav-link dropdown-toggle\" ngbDropdownToggle href=\"javascript:void(0);\" id=\"entity-menu\">\r\n" +
		"                    <span>\r\n" +
		"                        <fa-icon icon=\"th-list\"></fa-icon>\r\n" +
		"                        <span jhiTranslate=\"global.menu.entities.main\">\r\n" +
		"                            Entities\r\n" +
		"                        </span>\r\n" +
		"                    </span>\r\n" +
		"                </a>\r\n" +
		
		// MENU ENTITIES START
		"                <ul class=\"dropdown-menu\" ngbDropdownMenu>\r\n" ;
		for(Table table: Utils.getTables(database)  ) {
			String Tablename = Utils.getEntityName(table);
			String tablename = Utils.getFieldName(table);
			String authorities = Utils.getAuthorities(table, Utils.APICE);
			
			String jhiAuthorities = "*jhiHasAnyAuthority= \"["+authorities+"]\"";
			body+=
			"                    <li "+ jhiAuthorities+" >\r\n" +
			"                        <a class=\"dropdown-item\" routerLink=\""+tablename+"\" routerLinkActive=\"active\" [routerLinkActiveOptions]=\"{ exact: true }\" (click)=\"collapseNavbar()\">\r\n" +
			"                            <fa-icon icon=\"asterisk\" fixedWidth=\"true\"></fa-icon>\r\n" +
			"                            <span jhiTranslate=\"global.menu.entities."+tablename+"\">"+Tablename+"</span>\r\n" +
			"                        </a>\r\n" +
			"                    </li>\r\n" ;
		}
				
		body+=
		"                </ul>\r\n" +
		// MENU ENTITIES END
		
		"            </li>\r\n" +
		"            <li *jhiHasAnyAuthority=\"'ROLE_ADMIN'\" ngbDropdown class=\"nav-item dropdown pointer\" routerLinkActive=\"active\" [routerLinkActiveOptions]=\"{exact: true}\">\r\n" +
		"                <a class=\"nav-link dropdown-toggle\" ngbDropdownToggle href=\"javascript:void(0);\" id=\"admin-menu\">\r\n" +
		"                    <span>\r\n" +
		"                        <fa-icon icon=\"user-plus\"></fa-icon>\r\n" +
		"                        <span jhiTranslate=\"global.menu.admin.main\">Administration</span>\r\n" +
		"                    </span>\r\n" +
		"                </a>\r\n" +
		"                <ul class=\"dropdown-menu\" ngbDropdownMenu>\r\n" +
		"                    <li>\r\n" +
		"                        <a class=\"dropdown-item\" routerLink=\"admin/user-management\" routerLinkActive=\"active\" (click)=\"collapseNavbar()\">\r\n" +
		"                            <fa-icon icon=\"user\" fixedWidth=\"true\"></fa-icon>\r\n" +
		"                            <span jhiTranslate=\"global.menu.admin.userManagement\">User management</span>\r\n" +
		"                        </a>\r\n" +
		"                    </li>\r\n" +
		"                    <li>\r\n" +
		"                        <a class=\"dropdown-item\" routerLink=\"admin/jhi-metrics\" routerLinkActive=\"active\" (click)=\"collapseNavbar()\">\r\n" +
		"                            <fa-icon icon=\"tachometer-alt\" fixedWidth=\"true\"></fa-icon>\r\n" +
		"                            <span jhiTranslate=\"global.menu.admin.metrics\">Metrics</span>\r\n" +
		"                        </a>\r\n" +
		"                    </li>\r\n" +
		"                    <li>\r\n" +
		"                        <a class=\"dropdown-item\" routerLink=\"admin/jhi-health\" routerLinkActive=\"active\" (click)=\"collapseNavbar()\">\r\n" +
		"                            <fa-icon icon=\"heart\" fixedWidth=\"true\"></fa-icon>\r\n" +
		"                            <span jhiTranslate=\"global.menu.admin.health\">Health</span>\r\n" +
		"                        </a>\r\n" +
		"                    </li>\r\n" +
		"                    <li>\r\n" +
		"                        <a class=\"dropdown-item\" routerLink=\"admin/jhi-configuration\" routerLinkActive=\"active\" (click)=\"collapseNavbar()\">\r\n" +
		"                            <fa-icon icon=\"list\" fixedWidth=\"true\"></fa-icon>\r\n" +
		"                            <span jhiTranslate=\"global.menu.admin.configuration\">Configuration</span>\r\n" +
		"                        </a>\r\n" +
		"                    </li>\r\n" +
		"                    <li>\r\n" +
		"                        <a class=\"dropdown-item\" routerLink=\"admin/audits\" routerLinkActive=\"active\" (click)=\"collapseNavbar()\">\r\n" +
		"                            <fa-icon icon=\"bell\" fixedWidth=\"true\"></fa-icon>\r\n" +
		"                            <span jhiTranslate=\"global.menu.admin.audits\">Audits</span>\r\n" +
		"                        </a>\r\n" +
		"                    </li>\r\n" +
		"                    <li>\r\n" +
		"                        <a class=\"dropdown-item\" routerLink=\"admin/logs\" routerLinkActive=\"active\" (click)=\"collapseNavbar()\">\r\n" +
		"                            <fa-icon icon=\"tasks\" fixedWidth=\"true\"></fa-icon>\r\n" +
		"                            <span jhiTranslate=\"global.menu.admin.logs\">Logs</span>\r\n" +
		"                        </a>\r\n" +
		"                    </li>\r\n" +
		"                    <li *ngIf=\"swaggerEnabled\">\r\n" +
		"                        <a class=\"dropdown-item\" routerLink=\"admin/docs\" routerLinkActive=\"active\" (click)=\"collapseNavbar()\">\r\n" +
		"                            <fa-icon icon=\"book\" fixedWidth=\"true\"></fa-icon>\r\n" +
		"                            <span jhiTranslate=\"global.menu.admin.apidocs\">API</span>\r\n" +
		"                        </a>\r\n" +
		"                    </li>\r\n" +

		//Audit
		"                    <li>\r\n" +
		"                        <a class=\"dropdown-item\" routerLink=\"admin/entity-audit\" routerLinkActive=\"active\" (click)=\"collapseNavbar()\">\r\n" +
		"                            <fa-icon icon=\"tasks\" fixedWidth=\"true\"></fa-icon>\n"+
		"                            <span jhiTranslate=\"global.menu.admin.entity-audit\">Entity Audit</span>\r\n" +
		"                        </a>\r\n" +
		"                    </li>\r\n"+
		
		"                </ul>\r\n" +
		"            </li>\r\n" +

		
		
		//Tools
		"            <li ngbDropdown class=\"nav-item dropdown pointer\" *ngIf=\"languages && languages.length > 1\">\r\n" +
		"                <a class=\"nav-link dropdown-toggle\" ngbDropdownToggle href=\"javascript:void(0);\" id=\"languagesnavBarDropdown\">\r\n" +
		"                    <span>\r\n" +
		"                        <fa-icon icon=\"flag\"></fa-icon>\r\n" +
		"                        <span jhiTranslate=\"global.menu.language\">Language</span>\r\n" +
		"                    </span>\r\n" +
		"                </a>\r\n" +
		"                <ul class=\"dropdown-menu\" ngbDropdownMenu>\r\n" +
		"                    <li *ngFor=\"let language of languages\">\r\n" +
		"                        <a class=\"dropdown-item\" [jhiActiveMenu]=\"language\" href=\"javascript:void(0);\" (click)=\"changeLanguage(language);collapseNavbar();\">{{language | findLanguageFromKey}}</a>\r\n" +
		"                    </li>\r\n" +
		"                </ul>\r\n" +
		"            </li>\r\n" +
		"            <li ngbDropdown class=\"nav-item dropdown pointer\" placement=\"bottom-right\" routerLinkActive=\"active\" [routerLinkActiveOptions]=\"{exact: true}\">\r\n" +
		"                <a class=\"nav-link dropdown-toggle\" ngbDropdownToggle href=\"javascript:void(0);\" id=\"account-menu\">\r\n" +
		"                  <span *ngIf=\"!getImageUrl()\">\r\n" +
		"                    <fa-icon icon=\"user\"></fa-icon>\r\n" +
		"                    <span jhiTranslate=\"global.menu.account.main\">\r\n" +
		"                      Account\r\n" +
		"                    </span>\r\n" +
		"                  </span>\r\n" +
		"                  <span *ngIf=\"getImageUrl()\">\r\n" +
		"                      <img [src]=\"getImageUrl()\" class=\"profile-image img-circle\" alt=\"Avatar\">\r\n" +
		"                  </span>\r\n" +
		"                </a>\r\n" +
		"                <ul class=\"dropdown-menu\" ngbDropdownMenu>\r\n" +
		"                    <li *ngSwitchCase=\"true\">\r\n" +
		"                        <a class=\"dropdown-item\" routerLink=\"settings\" routerLinkActive=\"active\" (click)=\"collapseNavbar()\">\r\n" +
		"                            <fa-icon icon=\"wrench\" fixedWidth=\"true\"></fa-icon>\r\n" +
		"                            <span jhiTranslate=\"global.menu.account.settings\">Settings</span>\r\n" +
		"                        </a>\r\n" +
		"                    </li>\r\n" +
		"                    <li *ngSwitchCase=\"true\">\r\n" +
		"                        <a class=\"dropdown-item\" routerLink=\"password\" routerLinkActive=\"active\" (click)=\"collapseNavbar()\">\r\n" +
		"                            <fa-icon icon=\"clock\" fixedWidth=\"true\"></fa-icon>\r\n" +
		"                            <span jhiTranslate=\"global.menu.account.password\">Password</span>\r\n" +
		"                        </a>\r\n" +
		"                    </li>\r\n" +
		"                    <li *ngSwitchCase=\"true\">\r\n" +
		"                        <a class=\"dropdown-item\" (click)=\"logout()\" id=\"logout\">\r\n" +
		"                            <fa-icon icon=\"sign-out-alt\" fixedWidth=\"true\"></fa-icon>\r\n" +
		"                            <span jhiTranslate=\"global.menu.account.logout\">Sign out</span>\r\n" +
		"                        </a>\r\n" +
		"                    </li>\r\n" +
		"                    <li *ngSwitchCase=\"false\">\r\n" +
		"                        <a class=\"dropdown-item\" (click)=\"login()\" id=\"login\">\r\n" +
		"                            <fa-icon icon=\"sign-in-alt\" fixedWidth=\"true\"></fa-icon>\r\n" +
		"                            <span jhiTranslate=\"global.menu.account.login\">Sign in</span>\r\n" +
		"                        </a>\r\n" +
		"                    </li>\r\n" +
		"                    <li *ngSwitchCase=\"false\">\r\n" +
		"                        <a class=\"dropdown-item\" routerLink=\"register\" routerLinkActive=\"active\" (click)=\"collapseNavbar()\">\r\n" +
		"                            <fa-icon icon=\"user-plus\" fixedWidth=\"true\"></fa-icon>\r\n" +
		"                            <span jhiTranslate=\"global.menu.account.register\">Register</span>\r\n" +
		"                        </a>\r\n" +
		"                    </li>\r\n" +
		"                </ul>\r\n" +
		"            </li>\r\n" +
		"        </ul>\r\n" +
		"    </div>\r\n" +
		"</nav>\r\n";
		return body;
	}

	public String getClassName(){
		return "navbar.component";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/webapp/app/layouts/navbar";
	}

}
