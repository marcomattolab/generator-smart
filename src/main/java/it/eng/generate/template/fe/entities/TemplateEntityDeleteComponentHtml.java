package it.eng.generate.template.fe.entities;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityDeleteComponentHtml extends AbstractResourceTemplate {

	public TemplateEntityDeleteComponentHtml(Table tabella) {
		super(tabella);
	}

	public String getTypeFile() {
		return "html";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String nometabella = Utils.getClassNameLowerCase(tabella);
		String Nometabella = Utils.getEntityName(tabella);
		
		String body = 
		"<form name=\"deleteForm\" (ngSubmit)=\"confirmDelete("+nometabella+".id)\">\r\n" +
		"    <div class=\"modal-header\">\r\n" +
		"        <h4 class=\"modal-title\" jhiTranslate=\"entity.delete.title\">Confirm delete operation</h4>\r\n" +
		"        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\"\r\n" +
		"                (click)=\"clear()\">&times;</button>\r\n" +
		"    </div>\r\n" +
		"    <div class=\"modal-body\">\r\n" +
		"        <jhi-alert-error></jhi-alert-error>\r\n" +
		"        <p id=\"jhi-delete-"+nometabella+"-heading\" jhiTranslate=\""+conf.getProjectName()+"App."+nometabella+".delete.question\" translateValues=\"{id: '{{"+nometabella+".id}}'}\">Are you sure you want to delete this "+Nometabella+"?</p>\r\n" +
		"    </div>\r\n" +
		"    <div class=\"modal-footer\">\r\n" +
		"        <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\" (click)=\"clear()\">\r\n" +
		"            <fa-icon [icon]=\"'ban'\"></fa-icon>&nbsp;<span jhiTranslate=\"entity.action.cancel\">Cancel</span>\r\n" +
		"        </button>\r\n" +
		"        <button id=\"jhi-confirm-delete-"+nometabella+"\" type=\"submit\" class=\"btn btn-danger\">\r\n" +
		"            <fa-icon [icon]=\"'times'\"></fa-icon>&nbsp;<span jhiTranslate=\"entity.action.delete\">Delete</span>\r\n" +
		"        </button>\r\n" +
		"    </div>\r\n" +
		"</form>\r\n";
		return body;
	}
	
	public String getClassName(){
		return Utils.getClassNameLowerCase(tabella)+"-delete-dialog.component";
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
