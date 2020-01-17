package it.eng.generate.template.ionic;

import org.springframework.util.CollectionUtils;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.ProjectRelation;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityHtmlIonic extends AbstractResourceTemplate {

	public TemplateEntityHtmlIonic(Table tabella) {
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
		"<!-- todo: add elasticsearch support -->\r\n\n" +
		"<ion-content padding>\r\n" +
		"    <ion-refresher [disabled]=\"plt.is('desktop')\" slot=\"fixed\" (ionRefresh)=\"loadAll($event)\">\r\n" +
		"        <ion-refresher-content></ion-refresher-content>\r\n" +
		"    </ion-refresher>\r\n" +
		"    <ion-list>\r\n" +
		"        <ion-item-sliding *ngFor=\"let "+nometabella+" of "+nometabella+"s; trackBy: trackId\" #slidingItem>\r\n" +
		"            <ion-item (click)=\"view("+nometabella+")\">\r\n" +
		"              <ion-label text-wrap>\r\n";
		
		// CICLE PROPERTIES & RELATIONS START
		
		// Columns
		for (Column column : tabella.getSortedColumns()) {
			String ColumnName = Utils.getFieldNameForMethod(column);
			String columnname = Utils.getFieldName(column);
			if(Utils.isPrimaryKeyID(column) ) {
				//System.out.println("#Skip generation for Primary Key ID..");
				body += "                <p>{{"+nometabella+"."+columnname+"}}</p>\r\n";
			} else {
				body += "                <p>{{"+nometabella+"."+columnname+"}}</p>\r\n";
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
							body += "                <!-- Add Relation:  Name: "+nomeRelazioneSx+"      Type: "+relationType+" -->\n";
							body += "                <p>{{"+Utils.getFirstLowerCase(nomeTabellaSx)+"."+Utils.getFirstLowerCase(nomeRelazioneSx)+""+Utils.getFirstUpperCase(nomeSelectSx)+"}}</p>\n";
						}
						
					} else if(relationType.equals(Utils.OneToMany)) {
						if ( nomeTabellaDx.toLowerCase().equals(nomeTabella) ) {
							body += "                <!-- Add Relation    Name: "+nomeRelazioneDx+"     Type: OneToMany -->\n";
							body += "                <p>{{"+Utils.getFirstLowerCase(nomeTabellaDx)+"."+Utils.getFirstLowerCase(nomeRelazioneDx)+""+Utils.getFirstUpperCase(nomeSelectDx)+"}}</p>\n";
						}
						
					} else if(relationType.equals(Utils.ManyToMany)) {
						//TODO DEVELOP NEXT
//						if ( nomeTabellaSx.toLowerCase().equals(nomeTabella) ) {
//							body += "                <!-- Add Relation:   Name: "+nomeRelazioneSx+"   Type: "+relationType+" -->\n";
//							body += "                <p>{{"+Utils.getFirstLowerCase(nomeRelazioneSx)+"."+Utils.getFirstLowerCase(nomeSelectSx)+"}}</p>\n";
//						}
					}
				}
			}
		}
		//Relations
		
		// CICLE PROPERTIES & RELATIONS END
		
		body +=
		"              </ion-label>\r\n" +
		"            </ion-item>\r\n";
				
		
		body +=
		"            <ion-item-options side=\"end\">\r\n" +
		"                <ion-item-option color=\"primary\" (click)=\"edit(slidingItem, "+nometabella+")\">\r\n" +
		"                    {{ 'EDIT_BUTTON' | translate }}\r\n" +
		"                </ion-item-option>\r\n" +
		"                <ion-item-option color=\"danger\" (click)=\"delete("+nometabella+")\">\r\n" +
		"                    {{ 'DELETE_BUTTON' | translate }}\r\n" +
		"                </ion-item-option>\r\n" +
		"            </ion-item-options>\r\n" +
		"        </ion-item-sliding>\r\n" +
		"    </ion-list>\r\n\n" +
		"    <ion-item *ngIf=\"!"+nometabella+"s?.length\">\r\n" +
		"        <ion-label>{{ 'NO"+TABELLA+"FOUND' | translate}}</ion-label>" +
		"    </ion-item>\r\n\n" +
		"    <ion-fab vertical=\"bottom\" horizontal=\"end\" slot=\"fixed\">\r\n" +
		"        <ion-fab-button (click)=\"new()\">\r\n" +
		"            <ion-icon name=\"add\"></ion-icon>\r\n" +
		"        </ion-fab-button>\r\n" +
		"    </ion-fab>\r\n\n" +
		"</ion-content>\r\n";
		
		return body;
	}
	
	public String getClassName(){
		return Utils.getClassNameLowerCase(tabella)+"";
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
