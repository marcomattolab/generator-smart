package it.eng.generate.template.fe.entities;

import java.util.Iterator;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.ProjectRelation;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityUpdateComponentHtml extends AbstractResourceTemplate {

	public TemplateEntityUpdateComponentHtml(Table tabella) {
		super(tabella);
	}

	public TemplateEntityUpdateComponentHtml(DataBase database, Table tabella) {
		super(database);
		this.tabella = tabella;
	}

	public String getTypeFile() {
		return "html";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String Nometabella = Utils.getEntityName(tabella);
		String nometabella = Utils.getClassNameLowerCase(tabella);

		String body = 
		"<div class=\"row justify-content-center\">\r\n" +
		"    <div class=\"col-8\">\r\n" +
		"        <form name=\"editForm\" role=\"form\" novalidate (ngSubmit)=\"save()\" #editForm=\"ngForm\">\r\n" +
		"            <h2 id=\"jhi-"+nometabella+"-heading\" jhiTranslate=\""+conf.getProjectName()+"App."+nometabella+".home.createOrEditLabel\">Create or edit a "+Nometabella+"</h2>\r\n" +

		// MAIN CICLE DL - START
		"            <div>\r\n" ;
		Set<?> set = tabella.getColumnNames();
		for (Iterator<?> iter = set.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
			body += Utils.getTemplateHtmlByType(database, column, tabella, conf);
		}
		
		
		//Relations management
		if(!CollectionUtils.isEmpty(conf.getProjectRelations())) {
			for(ProjectRelation rel: conf.getProjectRelations()) {
				String relationType = rel.getType();
				String nomeTabellaSx = rel.getSxTable();
				String nomeRelazioneSx = rel.getSxName();
				String nomeTabellaDx = rel.getDxTable();
				String nomeSelectSx = rel.getSxSelect();
				String nomeSelectDx = rel.getDxSelect();
				String nomeTabella = tabella.getNomeTabella().toLowerCase();
				
				if(nomeTabellaSx!=null && nomeTabellaDx != null) {
					if(relationType.equals(Utils.OneToOne) || relationType.equals(Utils.ManyToOne)) {
						if ( nomeTabellaSx.toLowerCase().equals(nomeTabella) ) {
						String track = (relationType.equals(Utils.OneToOne)) ? "; trackBy: track"+Utils.getFirstUpperCase(nomeRelazioneSx)+"ById" : "";
						body += "\n               <!-- Add Relation: OneToOne / ManyToOne -->";
						body += "\n                <div class=\"form-group\">\r\n" +
								"             		<label class=\"form-control-label\" jhiTranslate=\""+conf.getProjectName()+"App."+Utils.getFirstLowerCase(nomeTabellaSx)+"."+nomeRelazioneSx+"\" for=\"field_"+nomeRelazioneSx+"\">"+Utils.getFirstUpperCase(nomeRelazioneSx)+"</label>\r\n" +
								"             		<select class=\"form-control\" id=\"field_"+nomeRelazioneSx+"\" name=\""+nomeRelazioneSx+"\" [(ngModel)]=\""+Utils.getFirstLowerCase(nomeTabellaSx)+"."+nomeRelazioneSx+"Id\">\r\n" +
								"                 		<option [ngValue]=\"null\"></option>\r\n" +
								"                 		<option [ngValue]=\""+nomeRelazioneSx+"Option.id\" *ngFor=\"let "+nomeRelazioneSx+"Option of "+nomeRelazioneSx+"s"+track+"\">{{"+nomeRelazioneSx+"Option."+nomeSelectSx+"}}</option>\r\n" +
								"             		</select>\r\n" +
								"                </div>\r\n\n";
						}
					} else if(relationType.equals(Utils.OneToMany)) {
						if ( nomeTabellaDx.toLowerCase().equals(nomeTabella) ) {
							body += "\n               <!-- Add Relation: OneToMany -->";
							String track = "; trackBy: track"+Utils.getFirstUpperCase(nomeTabellaSx)+"ById";
							body += "\n                <div class=\"form-group\">\r\n" +
									"             		<label class=\"form-control-label\" jhiTranslate=\""+conf.getProjectName()+"App."+Utils.getFirstLowerCase(nomeTabellaDx)+"."+Utils.getFirstLowerCase(nomeTabellaSx)+"\" for=\"field_"+Utils.getFirstLowerCase(nomeTabellaSx)+"\">"+Utils.getFirstUpperCase(nomeTabellaSx)+"</label>\r\n" +
									"             		<select class=\"form-control\" id=\"field_"+Utils.getFirstLowerCase(nomeTabellaSx)+"\" name=\""+Utils.getFirstLowerCase(nomeTabellaSx)+"\" [(ngModel)]=\""+Utils.getFirstLowerCase(nomeTabellaDx)+"."+Utils.getFirstLowerCase(nomeTabellaSx)+"Id\">\r\n" +
									"                 		<option [ngValue]=\"null\"></option>\r\n" +
									"                 		<option [ngValue]=\""+Utils.getFirstLowerCase(nomeTabellaSx)+"Option.id\" *ngFor=\"let "+Utils.getFirstLowerCase(nomeTabellaSx)+"Option of "+Utils.getFirstLowerCase(nomeTabellaSx)+"s"+track+"\">{{"+Utils.getFirstLowerCase(nomeTabellaSx)+"Option."+nomeSelectDx+"}}</option>\r\n" +
									"             		</select>\r\n" +
									"                </div>\r\n\n";
							}
					} else if(relationType.equals(Utils.ManyToMany)) {
						//TODO DEVELOP THIS!! 
					}
					
				}
				
			}
		}
		
		
         
		body +=
		"            </div>\r\n" ;
		// MAIN CICLE DL - END
		
		body +=
		"            <div>\r\n" +
		"                <button type=\"button\" id=\"cancel-save\" class=\"btn btn-secondary\"  (click)=\"previousState()\">\r\n" +
		"                    <fa-icon [icon]=\"'ban'\"></fa-icon>&nbsp;<span jhiTranslate=\"entity.action.cancel\">Cancel</span>\r\n" +
		"                </button>\r\n" +
		"                <button type=\"submit\" id=\"save-entity\" [disabled]=\"editForm.form.invalid || isSaving\" class=\"btn btn-primary\">\r\n" +
		"                    <fa-icon [icon]=\"'save'\"></fa-icon>&nbsp;<span jhiTranslate=\"entity.action.save\">Save</span>\r\n" +
		"                </button>\r\n" +
		"            </div>\r\n" +
		"        </form>\r\n" +
		"    </div>\r\n" +
		"</div>\r\n";
		return body;
	}
	
	public String getClassName(){
		return Utils.getClassNameLowerCase(tabella)+"-update.component";
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
