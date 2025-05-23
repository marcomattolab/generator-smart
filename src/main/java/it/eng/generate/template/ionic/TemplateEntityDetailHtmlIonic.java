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
		String TABELLA = Nometabella.toUpperCase();
		
		String body = 
		"<ion-header>\r\n" +
		"    <ion-toolbar>\r\n" +
		"        <ion-buttons slot=\"start\">\r\n" +
		"            <ion-back-button></ion-back-button>\r\n" +
		"        </ion-buttons>\r\n" +
		"        <ion-title>{{'"+TABELLA+".TITLE' | translate}}</ion-title>\r\n" +
		"    </ion-toolbar>\r\n" +
		"</ion-header>\r\n\n" +
		"<ion-content padding>\r\n" +
		"    <ion-list>\r\n";
	
		// Columns
		for (Column column : tabella.getSortedColumns()) {
			String ColumnName = Utils.getFieldNameForMethod(column);
			String columnname = Utils.getFieldName(column);
			String COLONNA = ColumnName.toUpperCase();
			
			if(Utils.isPrimaryKeyID(column) ) {
				//System.out.println("#Skip generation for Primary Key ID..");
				body +=
				"        <ion-item>\r\n" +
				"            <ion-label position=\"fixed\">{{'"+TABELLA+"."+COLONNA+"' | translate}}</ion-label>\r\n" +
				"            <div item-content>\r\n" +
				"                <span>{{"+nometabella+"."+columnname+"}}</span>\r\n" +
				"            </div>\r\n" +
				"        </ion-item>\r\n";
			} else if (Utils.isBlob(column) || Utils.isClob(column)) {
				body +=
				"        <ion-item>\r\n" + 
				"          <ion-label position=\"fixed\" color=\"disabled\">{{'ATTACHMENT' | translate}}</ion-label>\r\n" + 
				"          <div item-content>\r\n" + 
				"             <div *ngIf=\""+nometabella+"."+columnname+"\">\r\n" + 
				"               <a (click)=\"openFile("+nometabella+"."+columnname+"ContentType, "+nometabella+"."+columnname+")\">\r\n" + 
				"                <img [src]=\"'data:' + "+nometabella+"."+columnname+"ContentType + ';base64,' + "+nometabella+"."+columnname+"\"\r\n" + 
				"                 style=\"max-width: 100%;\" alt=\""+nometabella+" image\"/>\r\n" + 
				"               </a>\r\n" + 
				"               {{"+nometabella+"."+columnname+"ContentType}}, {{byteSize("+nometabella+"."+columnname+")}}\r\n" + 
				"             </div>\r\n" + 
				"          </div>\r\n" + 
				"        </ion-item>";
			} else {
				body +=
				"        <ion-item>\r\n" +
				"            <ion-label position=\"fixed\">{{'"+TABELLA+"."+COLONNA+"' | translate}}</ion-label>\r\n" +
				"            <div item-content>\r\n" +
				"                <span>{{"+nometabella+"."+columnname+"}}</span>\r\n" +
				"            </div>\r\n" +
				"        </ion-item>\r\n";
			}
		}
		
		//Relations
		if(!CollectionUtils.isEmpty(conf.getProjectRelations())) {
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
							body += "        <!-- Add Relation:   Name: "+nomeRelazioneSx+"    Type: "+relationType+"   Alfa -->\n";
							String LABEL = Utils.getFirstUpperCase(nomeRelazioneSx).toUpperCase();
							body += "        <ion-item>\r\n" +
									"            <ion-label position=\"fixed\">{{'"+nomeTabella.toUpperCase()+"."+LABEL+"' | translate}}</ion-label>\r\n" +
									"            <div item-content>\r\n" +
									"                <span>{{"+Utils.getFirstLowerCase(nomeTabellaSx)+"."+Utils.getFirstLowerCase(nomeRelazioneSx)+""+Utils.getFirstUpperCase(nomeSelectSx)+"}}</span>\r\n" +
									"            </div>\r\n" +
									"        </ion-item>\r\n";
						}
						
					} else if(relationType.equals(Utils.OneToMany)) {
						if ( nomeTabellaDx.toLowerCase().equals(nomeTabella) ) {
							body += "        <!-- Add Relation    Name: "+nomeRelazioneDx+"     Type: OneToMany    Gamma -->\n";
							String LABEL = Utils.getFirstUpperCase(nomeRelazioneDx).toUpperCase();	
							body += "        <ion-item>\r\n" +
									"            <ion-label position=\"fixed\">{{'"+nomeTabella.toUpperCase()+"."+LABEL+"' | translate}}</ion-label>\r\n" +
									"            <div item-content>\r\n" +
									"                <span>{{"+Utils.getFirstLowerCase(nomeTabellaDx)+"."+Utils.getFirstLowerCase(nomeRelazioneDx)+""+Utils.getFirstUpperCase(nomeSelectDx)+"}}</span>\r\n" +
									"            </div>\r\n" +
									"        </ion-item>\r\n";
						}
						
					} else if(relationType.equals(Utils.ManyToMany)) {
						if ( nomeTabellaSx.toLowerCase().equals(nomeTabella) ) {
							String LABEL = 	Utils.getFirstUpperCase(nomeRelazioneSx).toUpperCase();
							body += "        <!-- Add Relation:  Name: "+nomeRelazioneSx+"   Type: ManyToMany    Yota -->\n";
							body += "        <ion-item>\r\n" +
									"            <ion-label position=\"fixed\">{{'"+nomeTabella.toUpperCase()+"."+LABEL+"' | translate}}</ion-label>\n" +
									"            <div item-content>\n" +
									"                <span *ngFor=\"let "+nomeRelazioneSx+" of "+nomeTabella+"."+nomeRelazioneSx+"s; let last = last\">"+
									"                   {{"+Utils.getFirstLowerCase(nomeRelazioneSx)+"."+Utils.getFirstLowerCase(nomeSelectSx)+"}} {{last ? '' : ', '}}"+
									"                </span>\n" +
									"            </div>\n" +
									"        </ion-item>\n";
						}
		                
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
