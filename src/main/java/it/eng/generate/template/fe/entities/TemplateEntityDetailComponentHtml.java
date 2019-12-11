package it.eng.generate.template.fe.entities;

import java.util.Iterator;
import java.util.Set;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityDetailComponentHtml extends AbstractResourceTemplate {

	public TemplateEntityDetailComponentHtml(Table tabella) {
		super(tabella);
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
		"        <div *ngIf=\""+nometabella+"\">\r\n" +
		"            <h2><span jhiTranslate=\""+conf.getProjectName()+"App."+nometabella+".detail.title\">"+Nometabella+"</span> {{"+nometabella+".id}}</h2>\r\n" +
		"            <hr>\r\n" +
		"            <jhi-alert-error></jhi-alert-error>\r\n" +
		
		// MAIN CICLE DL - START
		"            <dl class=\"row-md jh-entity-details\">\r\n";
		Set set = tabella.getColumnNames();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
			String ColumnName = Utils.getFieldNameForMethod(column);
			String columnname = Utils.getFieldName(column);
			String splitted = Utils.splitCamelCase(ColumnName);
			
			String spanField = Utils.isBlob(column) 
								? 	"					<div *ngIf=\""+nometabella+"."+columnname+"\">\r\n" +
									"                        <a (click)=\"openFile("+nometabella+"."+columnname+"ContentType, "+nometabella+"."+columnname+")\" jhiTranslate=\"entity.action.open\">open</a>\r\n" +
									"                        {{"+nometabella+"."+columnname+"ContentType}}, {{byteSize("+nometabella+"."+columnname+")}}\r\n" +
									"                    </div>\r\n"
								: 	"                    <span>{{"+nometabella+"."+columnname+"}}</span>\r\n";
			
			body += 
			"                <dt><span jhiTranslate=\""+conf.getProjectName()+"App."+nometabella+"."+columnname+"\">"+splitted+"</span></dt>\r\n" +
			"                <dd>\r\n" + 
								spanField + 
			"                </dd>\r\n";
		}
		body +=
		"            </dl>\r\n";
		// MAIN CICLE DL - END
		
		body +=
		"            <button type=\"submit\"\r\n" +
		"                    (click)=\"previousState()\"\r\n" +
		"                    class=\"btn btn-info\">\r\n" +
		"                <fa-icon [icon]=\"'arrow-left'\"></fa-icon>&nbsp;<span jhiTranslate=\"entity.action.back\"> Back</span>\r\n" +
		"            </button>\r\n" +
		"            <button type=\"button\"\r\n" +
		"                    [routerLink]=\"['/"+nometabella+"', "+nometabella+".id, 'edit']\"\r\n" +
		"                    class=\"btn btn-primary\">\r\n" +
		"                <fa-icon [icon]=\"'pencil-alt'\"></fa-icon>&nbsp;<span jhiTranslate=\"entity.action.edit\"> Edit</span>\r\n" +
		"            </button>\r\n" +
		"        </div>\r\n" +
		"    </div>\r\n" +
		"</div>\r\n";
		return body;
	}
	
	public String getClassName(){
		return Utils.getClassNameLowerCase(tabella)+"-detail.component";
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
