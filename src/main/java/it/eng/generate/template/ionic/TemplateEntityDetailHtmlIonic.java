package it.eng.generate.template.ionic;

import org.springframework.util.CollectionUtils;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.ProjectRelation;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityDetailHtmlIonic extends AbstractResourceTemplate {

	public TemplateEntityDetailHtmlIonic(Table tabella) {
		super(tabella);
	}

	public String getTypeFile() {
		return "html";
	}

	public String getBody(){
		// https://www.buildmystring.com/
		
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String Nometabella = Utils.getEntityName(tabella);
		String nometabella = Utils.getClassNameLowerCase(tabella);
		
		String body = 
		"<ion-header>\r\n" +
		"    <ion-toolbar>\r\n" +
		"        <ion-buttons slot=\"start\">\r\n" +
		"            <ion-back-button></ion-back-button>\r\n" +
		"        </ion-buttons>\r\n" +
		"        <ion-title>\r\n" +
		"            "+Nometabella+"\r\n" +
		"        </ion-title>\r\n" +
		"    </ion-toolbar>\r\n" +
		"</ion-header>\r\n\n" +
		"<ion-content padding>\r\n" +
		"    <ion-list>\r\n";
	
		// Columns
		for (Column column : tabella.getSortedColumns()) {
			String ColumnName = Utils.getFieldNameForMethod(column);
			String columnname = Utils.getFieldName(column);
			if(Utils.isPrimaryKeyID(column) ) {
				//System.out.println("#Skip generation for Primary Key ID..");
				body +=
				"        <ion-item>\r\n" +
				"            <ion-label position=\"fixed\">"+ColumnName+"</ion-label>\r\n" +
				"            <div item-content>\r\n" +
				"                <span>{{"+nometabella+"."+columnname+"}}</span>\r\n" +
				"            </div>\r\n" +
				"        </ion-item>\r\n";
			} else {
				body +=
				"        <ion-item>\r\n" +
				"            <ion-label position=\"fixed\">"+ColumnName+"</ion-label>\r\n" +
				"            <div item-content>\r\n" +
				"                <span>{{"+nometabella+"."+columnname+"}}</span>\r\n" +
				"            </div>\r\n" +
				"        </ion-item>\r\n";
			}
		}
		
		//Relations
		if(!CollectionUtils.isEmpty(conf.getProjectRelations()) && conf.isPrintRelation()) {
			for(ProjectRelation rel: conf.getProjectRelations()) {
				String relationType = rel.getType();
				String nomeTabellaSx = rel.getSxTable();
				String nomeRelazioneSx = rel.getSxName();
				String nomeRelazioneDx = rel.getDxName();
				String nomeTabellaDx = rel.getDxTable();
				String nomeSelectSx = rel.getSxSelect();
				String nomeSelectDx = rel.getDxSelect();
				String nomeTabella = tabella.getNomeTabella().toLowerCase();
				
				if(nomeTabellaSx!=null && nomeTabellaDx != null) {
					if(relationType.equals(Utils.OneToOne) || relationType.equals(Utils.ManyToOne)) {
						if ( nomeTabellaSx.toLowerCase().equals(nomeTabella) ) {
							body += "\n         <!-- Add Relation: OneToOne / ManyToOne -->\n";
							String label = Utils.getFirstUpperCase(nomeRelazioneSx);
							body += "        <ion-item>\r\n" +
									"            <ion-label position=\"fixed\">"+label+"</ion-label>\r\n" +
									"            <div item-content>\r\n" +
									"                <span>{{"+Utils.getFirstLowerCase(nomeTabellaSx)+"."+Utils.getFirstLowerCase(nomeRelazioneSx)+""+Utils.getFirstUpperCase(nomeSelectSx)+"}}</span>\r\n" +
									"            </div>\r\n" +
									"        </ion-item>\r\n";
						}
						
					} else if(relationType.equals(Utils.OneToMany)) {
						if ( nomeTabellaDx.toLowerCase().equals(nomeTabella) ) {
							body += "\n        <!-- Add Relation    Name: "+nomeRelazioneDx+"     Type: OneToMany  bis -->\n";
							String label = Utils.getFirstUpperCase(nomeRelazioneDx);	
							body += "        <ion-item>\r\n" +
									"            <ion-label position=\"fixed\">"+label+"</ion-label>\r\n" +
									"            <div item-content>\r\n" +
									"                <span>{{"+Utils.getFirstLowerCase(nomeTabellaDx)+"."+Utils.getFirstLowerCase(nomeRelazioneDx)+""+Utils.getFirstUpperCase(nomeSelectDx)+"}}</span>\r\n" +
									"            </div>\r\n" +
									"        </ion-item>\r\n";
						}
						
					} else if(relationType.equals(Utils.ManyToMany)) {
						//TODO DEVELOP THIS FEATURE!!
//						if ( nomeTabellaSx.toLowerCase().equals(nomeTabella) ) {
//							body += "\n        <!-- Add Relation: ManyToMany -->\n";
//							String label = 	Utils.getFirstUpperCase(nomeRelazioneSx);
//							body += "        <ion-item>\r\n" +
//									"            <ion-label position=\"floating\">"+label+"</ion-label>\r\n" +
//									"            <div item-content>\r\n" +
//									"                <span>{{"+Utils.getFirstLowerCase(nomeRelazioneSx)+"."+Utils.getFirstLowerCase(nomeSelectSx)+"}}</span>\r\n" +
//									"            </div>\r\n" +
//									"        </ion-item>\r\n";
//						}
						
					}
				}
			}
		}
		//Relations
		
		
		body +=
		"    </ion-list>\r\n\n" +
		"    <ion-button expand=\"block\" color=\"primary\" (click)=\"open("+nometabella+")\">{{ 'EDIT_BUTTON' | translate }}</ion-button>\r\n" +
		"    <ion-button expand=\"block\" color=\"danger\" (click)=\"deleteModal("+nometabella+")\">{{ 'DELETE_BUTTON' | translate }}</ion-button>\r\n\n" +
		"</ion-content>\r\n";
		
		return body;
	}
	
	public String getClassName(){
		return Utils.getClassNameLowerCase(tabella)+"-detail";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "mobile/src/app/pages/entities/"+Utils.getClassNameLowerCase(tabella);
	}

}
