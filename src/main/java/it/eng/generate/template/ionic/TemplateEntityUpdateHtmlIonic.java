package it.eng.generate.template.ionic;

import java.util.List;

import org.springframework.util.CollectionUtils;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Enumeration;
import it.eng.generate.ProjectRelation;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityUpdateHtmlIonic extends AbstractResourceTemplate {
	private static final boolean PRINT_RELATIONS = false; //TODO TEST IT!
	private static final String DATE_PATTERN = "DD/MM/YYYY"; //TODO MOVE INTO PROPERTY

	public TemplateEntityUpdateHtmlIonic(Table tabella) {
		super(tabella);
	}

	public String getTypeFile() {
		return "html";
	}

	public String getBody(){
		// https://www.buildmystring.com/
		// TODO DEVELOP JHI TRANSLATE
		// TODO MANAGE CLOB BLOB BOOLEAN AND DATES
		// TODO MANAGE PATTERN AND REQUIRED
		
		// String jhiTranslate = "<span jhiTranslate=\""+conf.getProjectName()+"App."+nometabella+".detail.title\">"+Nometabella+"</span>\r\n";
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
						"        <ion-buttons slot=\"end\">\r\n" +
						"            <ion-button [disabled]=\"!isReadyToSave\" (click)=\"save()\" color=\"primary\">\r\n" +
						"              <span *ngIf=\"platform.is('ios')\">{{'DONE_BUTTON' | translate}}</span>\r\n" +
						"              <ion-icon name=\"md-checkmark\" *ngIf=\"!platform.is('ios')\"></ion-icon>\r\n" +
						"            </ion-button>\r\n" +
						"        </ion-buttons>\r\n" +
						"    </ion-toolbar>\r\n" +
						"</ion-header>\r\n\n" +
						"<ion-content padding>\r\n" +
						"    <form *ngIf=\"form\" name=\"form\" [formGroup]=\"form\" (ngSubmit)=\"save()\">\r\n" +
						"        <ion-list>\r\n";


		//Columns
		for (Column column : tabella.getSortedColumns()) {
			String ColumnName = Utils.getFieldNameForMethod(column);
			String columnname = Utils.getFieldName(column);
			Class<?> filterType = column.getTypeColumn();
			boolean isEnumeration = column.getEnumeration()!=null;
			
			if(Utils.isPrimaryKeyID(column) ) {
				body += "            <ion-item [hidden]=\"!form.id\">\r\n" +
						"                <ion-label>ID</ion-label>\r\n" +
						"                <ion-input type=\"hidden\" id=\"id\" formControlName=\"id\" readonly></ion-input>\r\n" +
						"            </ion-item>\r\n";
			
			} else if (filterType.getName().equals("java.lang.String") && !isEnumeration) {
				body += "            <ion-item>\r\n" +
						"                <ion-label position=\"floating\">"+ColumnName+"</ion-label>\r\n" +
						"                <ion-input type=\"text\" name=\""+columnname+"\" formControlName=\""+columnname+"\"></ion-input>\r\n" +
						"            </ion-item>\r\n";
		
			} else if(filterType.getName().equals("java.lang.String") && isEnumeration) {
				//ENUMERATION
				body += "            <ion-item>\r\n" +
						"                <ion-label>"+ColumnName+"</ion-label>\r\n" +
						"                <ion-select formControlName=\""+columnname+"\" id=\"field_"+columnname+"\">\r\n";
				List<Enumeration> enumList = Utils.getEnumerationsByDbAndTable(database, tabella);
				for(Enumeration e : enumList) {
					if ( column.getEnumeration()!=null && column.getEnumeration().equals(e.getNomeEnumeration()) ) { 
						for(String vEnum : e.getValoriEnumeration()) {
							body += "                <ion-select-option value=\""+vEnum+"\">"+vEnum+"</ion-select-option>\r\n";
						}
					}
				}
				body += "                </ion-select>\r\n" +
						"            </ion-item>\r\n";

			} else if(filterType.getName().equals("java.lang.Long") || filterType.getName().equals("java.lang.Integer") || filterType.getName().equals("java.lang.Float") || filterType.getName().equals("java.math.BigDecimal")) {
				body += "            <ion-item>\n"+
						"                <ion-label position=\"floating\">Importo Spesa</ion-label>\n"+
						"                <ion-input type=\"number\" name=\"importoSpesa\" formControlName=\"importoSpesa\"></ion-input>\n"+
						"            </ion-item>\n";
			
			} else if(filterType.getName().equals("java.lang.Boolean")) {
				//TODO DEVELOP THIS!

			} else if( filterType.getName().equals("java.sql.Blob") ) {
				//TODO DEVELOP THIS!
				
			} else if( filterType.getName().equals("java.sql.Clob") ) {
				//TODO DEVELOP THIS!
				
			} else if( Utils.isDateField(column) && !Utils.isLocalDate(column)) {
				body += "            <ion-item>\n"+
						"                <ion-label>"+ColumnName+"</ion-label>\n"+
						"                <ion-datetime displayFormat=\""+DATE_PATTERN+"\" formControlName=\""+columnname+"\" id=\"field_"+columnname+"\"></ion-datetime>\n"+
						"            </ion-item>\n";
				
			}
			
		}
		//
		
		//Enumerations - TODO DEVELOP THIS
		//	    <ion-item>
		//      <ion-label>Stato</ion-label>
		//      <ion-select formControlName="stato" id="field_stato">
		//        <ion-select-option value="BOZZA">BOZZA</ion-select-option>
		//        <ion-select-option value="SOTTOPOSTA">SOTTOPOSTA</ion-select-option>
		//        <ion-select-option value="VALIDATA">VALIDATA</ion-select-option>
		//        <ion-select-option value="RIFIUTATA">RIFIUTATA</ion-select-option>
		//      </ion-select>
		//    </ion-item>

		//Relations - TODO DEVELOP THIS!
		if(!CollectionUtils.isEmpty(conf.getProjectRelations()) && PRINT_RELATIONS) {
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
							//String jhiTR = "<span jhiTranslate=\""+conf.getProjectName()+"App."+Utils.getFirstLowerCase(nomeTabellaSx)+"."+Utils.getFirstLowerCase(nomeRelazioneSx)+"\">"+Utils.getFirstUpperCase(nomeRelazioneSx)+"</span>\n";
							String label = Utils.getFirstUpperCase(nomeRelazioneSx);
							body += "        <ion-item>\r\n" +
									"            <ion-label position=\"fixed\">"+label+"</ion-label>\r\n" +
									"            <div item-content>\r\n" +
									"                <span>{{"+Utils.getFirstLowerCase(nomeTabellaSx)+"."+Utils.getFirstLowerCase(nomeRelazioneSx)+""+Utils.getFirstUpperCase(nomeSelectSx)+"}}</span>\r\n" +
									"            </div>\r\n" +
									"        </ion-item>\r\n";
							//	<ion-item>
							//      <ion-label>Trasferta</ion-label>
							//      <ion-select id="field_trasferta" formControlName="trasferta" [compareWith]="compareTrasferta">
							//        <ion-select-option [value]="null"></ion-select-option>
							//        <ion-select-option [value]="trasfertaOption.id" *ngFor="let trasfertaOption of trasfertas;">
							//          {{trasfertaOption.descrizione}}</ion-select-option>
							//      </ion-select>
							//    </ion-item>
						}

					} else if(relationType.equals(Utils.OneToMany)) {
						if ( nomeTabellaDx.toLowerCase().equals(nomeTabella) ) {
							body += "\n        <!-- Add Relation    Name: "+nomeRelazioneDx+"     Type: OneToMany -->\n";
							//String jhiTR = "<span jhiTranslate=\""+conf.getProjectName()+"App."+Utils.getFirstLowerCase(nomeTabellaDx)+"."+Utils.getFirstLowerCase(nomeRelazioneDx)+"\">"+Utils.getFirstUpperCase(nomeRelazioneDx)+"</span>\n";
							String label = Utils.getFirstUpperCase(nomeRelazioneDx);	
							body += "        <ion-item>\r\n" +
									"            <ion-label position=\"fixed\">"+label+"</ion-label>\r\n" +
									"            <div item-content>\r\n" +
									"                <span>{{"+Utils.getFirstLowerCase(nomeTabellaDx)+"."+Utils.getFirstLowerCase(nomeRelazioneDx)+""+Utils.getFirstUpperCase(nomeSelectDx)+"}}</span>\r\n" +
									"            </div>\r\n" +
									"        </ion-item>\r\n";
						}

					} else if(relationType.equals(Utils.ManyToMany)) {
						if ( nomeTabellaSx.toLowerCase().equals(nomeTabella) ) {
							body += "\n        <!-- Add Relation: ManyToMany -->\n";
							//String jhiTR = body += "<span jhiTranslate=\""+conf.getProjectName()+"App."+Utils.getFirstLowerCase(nomeTabellaSx)+"."+Utils.getFirstLowerCase(nomeRelazioneSx)+"\">"+Utils.getFirstUpperCase(nomeRelazioneSx)+"</span>\n";
							String label = 	Utils.getFirstUpperCase(nomeRelazioneSx);
							body += "        <ion-item>\r\n" +
									"            <ion-label position=\"fixed\">"+label+"</ion-label>\r\n" +
									"            <div item-content>\r\n" +
									"                <span>{{"+Utils.getFirstLowerCase(nomeRelazioneSx)+"."+Utils.getFirstLowerCase(nomeSelectSx)+"}}</span>\r\n" +
									"            </div>\r\n" +
									"        </ion-item>\r\n";
						}

					}
				}
			}
		}
		//



		body += "        </ion-list>\r\n" +
				"    </form>\r\n" +
				"</ion-content>\r\n";


		return body;
	}

	public String getClassName(){
		return Utils.getClassNameLowerCase(tabella)+"-update";
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
