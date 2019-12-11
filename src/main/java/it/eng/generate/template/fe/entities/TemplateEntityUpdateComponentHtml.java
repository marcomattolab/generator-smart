package it.eng.generate.template.fe.entities;

import java.util.Iterator;
import java.util.Set;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
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
		Set set = tabella.getColumnNames();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
			body += Utils.getTemplateHtmlByType(database, column, tabella, conf);
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
