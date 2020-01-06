package it.eng.generate.template.fe.entities;

import org.springframework.util.CollectionUtils;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.ProjectRelation;
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
		
		for (Column column : tabella.getSortedColumns()) {
			String ColumnName = Utils.getFieldNameForMethod(column);
			String columnname = Utils.getFieldName(column);
			String splitted = Utils.splitCamelCase(ColumnName);
			
			if(Utils.isPrimaryKeyID(column) ) {
				//System.out.println("#Skip generation for Primary Key ID..");
			} else {
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
			
		}
		
		
		//[Relations]
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
							body += "\n                <!-- Add Relation: OneToOne / ManyToOne -->\n";
							body += "                <dt><span jhiTranslate=\""+conf.getProjectName()+"App."+Utils.getFirstLowerCase(nomeTabellaSx)+"."+Utils.getFirstLowerCase(nomeRelazioneSx)+"\">"+Utils.getFirstUpperCase(nomeRelazioneSx)+"</span></dt>\r\n" +
									"                <dd>\r\n" + 
									"                    <div *ngIf=\""+Utils.getFirstLowerCase(nomeTabellaSx)+"."+Utils.getFirstLowerCase(nomeRelazioneSx)+"Id\">\r\n"+
									"                        <a [routerLink]=\"['/"+Utils.getFirstLowerCase(nomeTabellaDx)+"', "+Utils.getFirstLowerCase(nomeTabellaSx)+"."+Utils.getFirstLowerCase(nomeRelazioneSx)+"Id, 'view']\">{{"+Utils.getFirstLowerCase(nomeTabellaSx)+"."+Utils.getFirstLowerCase(nomeRelazioneSx)+""+Utils.getFirstUpperCase(nomeSelectSx)+"}}</a>\r\n"+
									"                    </div>\r\n"+
									"                </dd>\r\n";
							
						}
					} else if(relationType.equals(Utils.OneToMany)) {
						if ( nomeTabellaDx.toLowerCase().equals(nomeTabella) ) {
							body += "\n                <!-- Add Relation: OneToMany -->\n";
							body += "                <dt><span jhiTranslate=\""+conf.getProjectName()+"App."+Utils.getFirstLowerCase(nomeTabellaDx)+"."+Utils.getFirstLowerCase(nomeTabellaSx)+"\">"+Utils.getFirstUpperCase(nomeTabellaSx)+"</span></dt>\r\n" +
									"                <dd>\r\n" + 
									"                    <div *ngIf=\""+Utils.getFirstLowerCase(nomeTabellaDx)+"."+Utils.getFirstLowerCase(nomeTabellaSx)+"Id\">\r\n"+
									"                        <a [routerLink]=\"['/"+Utils.getFirstLowerCase(nomeTabellaSx)+"', "+Utils.getFirstLowerCase(nomeTabellaDx)+"."+Utils.getFirstLowerCase(nomeTabellaSx)+"Id, 'view']\">{{"+Utils.getFirstLowerCase(nomeTabellaDx)+"."+Utils.getFirstLowerCase(nomeTabellaSx)+""+Utils.getFirstUpperCase(nomeSelectDx)+"}}</a>\r\n"+
									"                    </div>\r\n"+
									"                </dd>\r\n";
						}
					} else if(relationType.equals(Utils.ManyToMany)) {
						if ( nomeTabellaSx.toLowerCase().equals(nomeTabella) ) {
							body += "\n                <!-- Add Relation: ManyToMany -->\n";
							body += "                <dt><span jhiTranslate=\""+conf.getProjectName()+"App."+Utils.getFirstLowerCase(nomeTabellaSx)+"."+Utils.getFirstLowerCase(nomeRelazioneSx)+"\">"+Utils.getFirstUpperCase(nomeRelazioneSx)+"</span></dt>\r\n" +
									"                <dd>\r\n" + 
									"                    <span *ngFor=\"let "+Utils.getFirstLowerCase(nomeRelazioneSx)+" of "+Utils.getFirstLowerCase(nomeTabellaSx)+"."+Utils.getFirstLowerCase(nomeRelazioneSx)+"s; let last = last\">\n"+
									"                        <a [routerLink]=\"['/"+Utils.getFirstLowerCase(nomeTabellaDx)+"', "+Utils.getFirstLowerCase(nomeRelazioneSx)+"?.id, 'view']\">{{"+Utils.getFirstLowerCase(nomeRelazioneSx)+"."+Utils.getFirstLowerCase(nomeSelectSx)+"}}</a>{{last ? '' : ', '}}\n"+
									"                    </span>\r\n"+
									"                </dd>\r\n";
						}
					}
				}
			}
		}
		//[/Relations]
		
		
		
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
