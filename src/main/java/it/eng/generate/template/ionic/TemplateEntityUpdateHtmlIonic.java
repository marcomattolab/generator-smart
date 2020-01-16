package it.eng.generate.template.ionic;

import java.util.List;

import org.springframework.util.CollectionUtils;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Enumeration;
import it.eng.generate.ProjectRelation;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityUpdateHtmlIonic extends AbstractResourceTemplate {

	public TemplateEntityUpdateHtmlIonic(DataBase database, Table tabella) {
		super(database);
		this.tabella = tabella;
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

		// TODO MANAGE PATTERN AND REQUIRED

		String body = 
				"<ion-header>\r\n" +
						"    <ion-toolbar>\r\n" +
						"        <ion-buttons slot=\"start\">\r\n" +
						"            <ion-back-button></ion-back-button>\r\n" +
						"        </ion-buttons>\r\n" +
						"        <ion-title>{{"+TABELLA+" | translate}}</ion-title>\r\n" +
						"        <ion-buttons slot=\"end\">\r\n" +
						"            <ion-button [disabled]=\"!isReadyToSave\" (click)=\"save()\" color=\"white\">\r\n" +
						"              <span *ngIf=\"isIos()\">{{'DONE_BUTTON' | translate}}</span>\r\n" + 
						"              <ion-icon name=\"md-checkmark\" *ngIf=\"!isIos()\"></ion-icon>\r\n" + 
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
			String COLONNA = ColumnName.toUpperCase();
			Class<?> filterType = column.getTypeColumn();
			boolean isEnumeration = column.getEnumeration()!=null;
			boolean isNullable = column.isNullable();
			String mandatory = isNullable ? "" : "*";
			
			if(Utils.isPrimaryKeyID(column) ) {
				body += "            <ion-item [hidden]=\"!form.id\">\r\n" +
						"                <ion-label>{{'"+TABELLA+"."+COLONNA+"' | translate}}</ion-label>\r\n" +
						"                <ion-input type=\"hidden\" id=\"id\" formControlName=\"id\" readonly></ion-input>\r\n" +
						"            </ion-item>\r\n";
			
			} else if (filterType.getName().equals("java.lang.String") && !isEnumeration) {
				//STRING
				body += "            <ion-item>\r\n" +
						"                <ion-label position=\"floating\">{{"+TABELLA+"."+COLONNA+" | translate}}</ion-label>\r\n" +
						"                <ion-input type=\"text\" name=\""+columnname+"\" formControlName=\""+columnname+"\"></ion-input>\r\n" +
						"            </ion-item>\r\n";
		
			} else if(filterType.getName().equals("java.lang.String") && isEnumeration) {
				//ENUMERATION
				body += "            <ion-item>\r\n" +
						"                <ion-label>{{"+TABELLA+"."+COLONNA+" | translate}}</ion-label>\r\n" +
						"                <ion-select formControlName=\""+columnname+"\" id=\"field_"+columnname+"\">\r\n";
				List<Enumeration> enumList = Utils.getEnumerationsByDbAndTable(database, tabella);
				for(Enumeration e : enumList) {
					if ( column.getEnumeration()!=null && column.getEnumeration().equals(e.getNomeEnumeration()) ) { 
						for(String vEnum : e.getValoriEnumeration()) {
							body += "                   <ion-select-option value=\""+vEnum+"\">"+vEnum+"</ion-select-option>\r\n";
						}
					}
				}
				body += "                </ion-select>\r\n" +
						"            </ion-item>\r\n";

			} else if(filterType.getName().equals("java.lang.Long") || filterType.getName().equals("java.lang.Integer") || filterType.getName().equals("java.lang.Float") || filterType.getName().equals("java.math.BigDecimal")) {
				body += "            <ion-item>\n"+
						"                <ion-label>{{"+TABELLA+"."+COLONNA+" | translate}}</ion-label>\n"+
						"                <ion-input type=\"number\" name=\""+columnname+"\" formControlName=\""+columnname+"\"></ion-input>\n"+
						"            </ion-item>\n";
				
			} else if( Utils.isDateField(column) && Utils.isLocalDate(column) ) {
				body += "            <ion-item>\n"+
						"                <ion-label>{{"+TABELLA+"."+COLONNA+" | translate}}</ion-label>\n"+
						"                <ion-datetime displayFormat=\""+Utils.DATE_PATTERN+"\" formControlName=\""+columnname+"\" id=\"field_"+columnname+"\"></ion-datetime>\n"+
						"            </ion-item>\n";
		
			} else if( Utils.isDateField(column) && !Utils.isLocalDate(column)) {
				//TODO DEVELOP THIS AND TEST!
				body += "            <ion-item>\n"+
						"                <ion-label>{{"+TABELLA+"."+COLONNA+" | translate}}</ion-label>\n"+
						"                <ion-datetime displayFormat=\""+Utils.DATE_PATTERN+"\" formControlName=\""+columnname+"\" id=\"field_"+columnname+"\"></ion-datetime>\n"+
						"            </ion-item>\n";
				
			} else if(filterType.getName().equals("java.lang.Boolean")) {
				body += "            <ion-item>\n"+
						"                <ion-label>{{"+TABELLA+"."+COLONNA+" | translate}}</ion-label>\n"+
						"                <ion-checkbox formControlName=\""+columnname+"\"></ion-checkbox>\n"+
						"            </ion-item>\n";
		
			} else if( filterType.getName().equals("java.sql.Blob") ||  filterType.getName().equals("java.sql.Clob") ) {
				body += "		      <ion-item>\r\n" + 
						"		        <div>\r\n" + 
						"		          <img [src]=\"'data:' + "+nometabella+"."+columnname+"ContentType + ';base64,' + "+nometabella+"."+columnname+"\"\r\n" + 
						"		               style=\"max-height: 100px;\" *ngIf=\""+nometabella+"?."+columnname+"\" alt=\""+nometabella+" image\"/>\r\n" + 
						"		          <div *ngIf=\""+nometabella+"."+columnname+"\">\r\n" + 
						"		            <p>{{"+nometabella+"."+columnname+"ContentType}}, {{byteSize("+nometabella+"."+columnname+")}}</p>\r\n" + 
						"		            <ion-button color=\"danger\" (click)=\"clearInputImage('"+columnname+"', '"+columnname+"ContentType', 'fileImage')\">\r\n" + 
						"		              <ion-icon name=\"trash\" slot=\"icon-only\"></ion-icon>\r\n" + 
						"		            </ion-button>\r\n" + 
						"		          </div>\r\n" + 
						"		          <input type=\"file\" #fileInput style=\"display: none\"\r\n" + 
						"		                 (change)=\"setFileData($event, "+nometabella+", '"+columnname+"', true)\" accept=\"image/*\"/>\r\n" + 
						"		          <ion-button *ngIf=\"!"+nometabella+"?."+columnname+"\" (click)=\"getPicture('"+columnname+"')\">\r\n" + 
						"		            <ion-icon name=\"add-circle\" slot=\"start\"></ion-icon>\r\n" + 
						"		            {{ 'ITEM_CREATE_CHOOSE_IMAGE' | translate }}\r\n" + 
						"		          </ion-button>\r\n" + 
						"		        </div>\r\n" + 
						"		      </ion-item>\r\n"; 
			}
		}
		
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
							body += "         	<!-- Add Relation:    Name: "+nomeRelazioneSx+"    Type: "+relationType+"  beta -->\n";
							body += 
							"        		<ion-item>\n"+
							"            		<ion-label>{{"+nomeTabellaSx.toUpperCase()+"."+Utils.getFirstUpperCase(nomeRelazioneSx).toUpperCase()+" | translate}}</ion-label>\n"+
							"            		<ion-select id=\"field_"+Utils.getFirstLowerCase(nomeRelazioneSx)+"\" formControlName=\""+Utils.getFirstLowerCase(nomeRelazioneSx)+"\">\n"+
							"                		<ion-select-option [value]=\"null\"></ion-select-option>\n"+
							"                		<ion-select-option [value]=\""+Utils.getFirstLowerCase(nomeRelazioneSx)+"Option.id\" *ngFor=\"let "+Utils.getFirstLowerCase(nomeRelazioneSx)+"Option of "+Utils.getFirstLowerCase(nomeRelazioneSx)+"s;\">{{"+Utils.getFirstLowerCase(nomeRelazioneSx)+"Option."+Utils.getFirstLowerCase(nomeSelectSx)+"}}</ion-select-option>\n"+
							"            		</ion-select>\n"+
							"        		</ion-item>\n";
						}

					} else if(relationType.equals(Utils.OneToMany)) {
						if ( nomeTabellaDx.toLowerCase().equals(nomeTabella) ) {
							body += "         	<!-- Add Relation    Name: "+nomeRelazioneDx+"     Type: OneToMany   gamma -->\n";
							body+=	
							"        		<ion-item>\n" + 
							"            		<ion-label>{{"+nometabella.toUpperCase()+"."+Utils.getFirstUpperCase(nomeRelazioneDx).toUpperCase()+" | translate}}</ion-label>\r\n" + 
							"            		<ion-select id=\"field_"+Utils.getFirstLowerCase(nomeRelazioneDx)+"\" formControlName=\""+Utils.getFirstLowerCase(nomeRelazioneDx)+"\">\r\n" + 
							"                		<ion-select-option [value]=\"null\"></ion-select-option>\r\n" + 
							"                		<ion-select-option *ngFor=\"let "+Utils.getFirstLowerCase(nomeRelazioneDx)+"Option of "+Utils.getFirstLowerCase(nomeRelazioneDx)+"s\" [value]=\""+Utils.getFirstLowerCase(nomeRelazioneDx)+"Option.id\">{{"+Utils.getFirstLowerCase(nomeRelazioneDx)+"Option."+Utils.getFirstLowerCase(nomeSelectDx)+"}}</ion-select-option>\r\n" + 
							"            		</ion-select>\r\n" + 
							"        		</ion-item>\n";
							
						}

					} else if(relationType.equals(Utils.ManyToMany)) {
						//TODO DEVELOP/TEST THIS FEATURE!!
						if ( nomeTabellaSx.toLowerCase().equals(nomeTabella) ) {
							body += "		    <!-- Add Relation:  Name:  "+nomeRelazioneSx+"   Type: ManyToMany   delta -->\n";
							body += 
							"        		<ion-item>\n"+
							"            		<ion-label>{{"+nomeTabella.toUpperCase()+"."+Utils.getFirstUpperCase(nomeRelazioneSx).toUpperCase()+" | translate}}</ion-label>\n"+
							"            		<ion-select id=\"field_"+Utils.getFirstLowerCase(nomeRelazioneSx)+"\" formControlName=\""+Utils.getFirstLowerCase(nomeRelazioneSx)+"\" [compareWith]=\"compare"+Utils.getFirstUpperCase(nomeTabellaDx)+"\">\n"+
							"                		<ion-select-option [value]=\"null\"></ion-select-option>\n"+
							"                		<ion-select-option [value]=\""+Utils.getFirstLowerCase(nomeRelazioneSx)+"Option.id\" *ngFor=\"let "+Utils.getFirstLowerCase(nomeRelazioneSx)+"Option of "+Utils.getFirstLowerCase(nomeRelazioneSx)+"s;\">{{"+Utils.getFirstLowerCase(nomeRelazioneSx)+"Option."+Utils.getFirstLowerCase(nomeSelectSx)+"}}</ion-select-option>\n"+
							"            		</ion-select>\n"+
							"        		</ion-item>\n";
							
						}

					}
				}
			}
		}

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
