package it.eng.generate.template.fe.entities;

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

public class TemplateEntityComponentHtml extends AbstractResourceTemplate {
	private static final String TH = "TH";
	private static final String TD = "TD";
	private static final String printButtonSizeMedium = "16px";
	// FILTER_COLUMN_SIZE (12/4)   ==>   3 filtri di ricerca per riga 
	private static int FILTER_COLUMN_SIZE = 4; 
	
	public TemplateEntityComponentHtml(DataBase database, Table tabella) {
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
		"<div>\r\n" +
		"    <h2 id=\"page-heading\">\r\n" +
		"        <span jhiTranslate=\""+conf.getProjectName()+"App."+nometabella+".home.title\">"+Nometabella+"s</span>\r\n" +
		"        <button id=\"jh-create-entity\" class=\"btn btn-primary float-right jh-create-entity create-"+nometabella+"\" [routerLink]=\"['/"+nometabella+"/new']\">\r\n" +
		"            <fa-icon [icon]=\"'plus'\"></fa-icon>\r\n" +
		"            <span  jhiTranslate=\""+conf.getProjectName()+"App."+nometabella+".home.createLabel\">\r\n" +
		"            Create new "+Nometabella+"\r\n" +
		"            </span>\r\n" +
		"        </button>\r\n" +
		"    </h2>\r\n" +
		"    <jhi-alert></jhi-alert>\r\n" +
		"    <br/>\r\n"+
		
		
		//[FILTRI_RICERCA]
		"     <ngb-accordion #acc=\"ngbAccordion\" activeIds=\"filtriPanel\">\r\n" +
		"        <ngb-panel id=\"filtriPanel\">\r\n" +
		"            <ng-template ngbPanelTitle>\r\n" +
		"                <span jhiTranslate=\"global.search.filtersLabel\">Filtri</span>\r\n" +
		"            </ng-template>\r\n" +
		"            <ng-template ngbPanelContent>\r\n" +
		"                <form (ngSubmit)=\"cerca()\">\r\n" +
		"                    <!-- [filtri di ricerca] -->\r\n" +
		"                    <div class=\"row\" [formGroup]=\"myGroup\">\r\n";
		
		
		for (Column column : tabella.getSortedColumns()) {
			String ColumnName = Utils.getFieldNameForMethod(column);
			String columnname = Utils.getFieldName(column);
			String Splitted = Utils.splitCamelCase(ColumnName);
			Class<?> filterType = column.getTypeColumn();
			boolean isEnumeration = column.getEnumeration()!=null ? true : false;
			
			if (filterType.getName().equals("java.lang.String") && !isEnumeration) {
				body += //TEXT
				"                        <div class=\"col-md-"+FILTER_COLUMN_SIZE+"\">\r\n" +
				"                            <div class=\"form-group\">\r\n" +
				"                                <label jhiTranslate=\""+conf.getProjectName()+"App."+nometabella+"."+columnname+"\">"+Splitted+"</label>\r\n" +
				"                                <input formControlName=\""+columnname+"\" type=\"text\" class=\"form-control\" />\r\n" +
				"                            </div>\r\n" +
				"                        </div>\r\n";	
				
			} else if(filterType.getName().equals("java.lang.String") && isEnumeration) {
				body += //ENUMERATION
				"                        <div class=\"col-md-"+FILTER_COLUMN_SIZE+"\">\r\n" +
				"                              <div class=\"form-group\">\r\n" +
				"                                   <label jhiTranslate=\""+conf.getProjectName()+"App."+nometabella+"."+columnname+"\">"+ColumnName+"</label>\r\n" +
				"                                   <select class=\"form-control\" formControlName=\""+columnname+"\" >\r\n";
				List<Enumeration> enumList = Utils.getEnumerationsByDbAndTable(database, tabella);
				body += "                                 <option></option>\r\n";
				for(Enumeration e : enumList) {
					if ( column.getEnumeration()!=null 
							&& column.getEnumeration().equals(e.getNomeEnumeration()) ) { 
						for(String vEnum : e.getValoriEnumeration()) {
				body +="                                  <option value=\""+vEnum+"\">{{'"+conf.getProjectName()+"App."+e.getNomeEnumeration()+"."+vEnum+"' | translate}}</option>\r\n" ;
						}
					}
				}
				body +=
				"                                    </select>\r\n" +
				"                               </div>\r\n\n" +
				"			               </div>\r\n" ;
				
			} else if(Utils.isDateField(column)) { 
				body +=
				"                        <div class=\"col-md-"+FILTER_COLUMN_SIZE+"\">\r\n" +
				"                            <div class=\"form-group\">\r\n" +
				"                                <label jhiTranslate=\""+conf.getProjectName()+"App."+nometabella+"."+columnname+"\">"+Splitted+"(Da / A)</label>\r\n" +
				"                                <div class=\"row\">\r\n" +
				"                                    <div class=\"col-md-6 col-12\">\r\n" +
				"                                        <div class=\"input-group form-group\">\r\n" +
				"                                            <input formControlName=\""+columnname+"Da\" type=\"date\" class=\"form-control\" />\r\n" +
				"                                        </div>\r\n" +
				"                                    </div>\r\n" +
				"                                    <div class=\"col-md-6 col-12\">\r\n" +
				"                                        <div class=\"input-group form-group\">\r\n" +
				"                                            <input formControlName=\""+columnname+"A\" type=\"date\" class=\"form-control\" />\r\n" +
				"                                        </div>\r\n" +
				"                                    </div>\r\n" +
				"                                </div>\r\n" +
				"                            </div>\r\n" +
				"                        </div>\r\n";
				
			} else if(Utils.isNumericField(column)) { 
				//NUMERICS
				body +=
				"                        <div class=\"col-md-"+FILTER_COLUMN_SIZE+"\">\r\n" +
				"                            <div class=\"form-group\">\r\n" +
				"                                <label jhiTranslate=\""+conf.getProjectName()+"App."+nometabella+"."+columnname+"\">"+Splitted+" (Da / A)</label>\r\n" +
				"                                <div class=\"row\">\r\n" +
				"                                    <div class=\"col-md-6 col-12\">\r\n" +
				"                                        <div class=\"input-group form-group\">\r\n" +
				"                                            <input formControlName=\""+columnname+"Da\" type=\"number\" class=\"form-control\" />\r\n" +
				"                                        </div>\r\n" +
				"                                    </div>\r\n" +
				"                                    <div class=\"col-md-6 col-12\">\r\n" +
				"                                        <div class=\"input-group form-group\">\r\n" +
				"                                            <input formControlName=\""+columnname+"A\" type=\"number\" class=\"form-control\" />\r\n" +
				"                                        </div>\r\n" +
				"                                    </div>\r\n" +
				"                                </div>\r\n" +
				"                            </div>\r\n" +
				"                        </div>\r\n";
			
			} else {
				// TODO DEVELOP THESE CLOB/BLOBS ...
			
			}
		}
		//TODO DEVELOP RELATIONS (SEARCH FILTERS)

		
		
		
		//Campi di ricerca
		body +=
		"                    </div>\r\n" +
		"				   <!-- [/filtri di ricerca] -->\r\n\n" +
		
		
		//Bottoni Download PDF/XLS and so on
		"                    <div class=\"form-group float-left\">\r\n" +
		"						<button style=\"font-size:"+printButtonSizeMedium+";\" type=\"button\" (click)=\"exportFile('CSV')\">CSV <i class=\"fa fa-file-o\"></i></button>\r\n" +
		"						<button style=\"font-size:"+printButtonSizeMedium+";\" type=\"button\" (click)=\"exportFile('PDF')\">PDF <i class=\"fa fa-file-pdf-o\"></i></button>\r\n" +
		"						<button style=\"font-size:"+printButtonSizeMedium+";\" type=\"button\" (click)=\"exportFile('XLS')\">XLS <i class=\"fa fa-file-excel-o\"></i></button>\r\n" +
		"						<button style=\"font-size:"+printButtonSizeMedium+";\" type=\"button\" (click)=\"exportFile('DOC')\">DOC <i class=\"fa fa-file-word-o\"></i></button>\r\n" +
		"                    </div>\r\n\n" +
		

		//Bottoni Cerca e Cancella Filtri 
		"                    <div class=\"form-group float-right\">\r\n" +
		"                        <button class=\"btn btn-link\" type=\"button\" (click)=\"resetFiltri()\">\r\n" +
		"                            <span jhiTranslate=\"global.search.clearfiltersLabel\">Pulisci Filtri</span>\r\n" +
		"                        </button>\r\n" +
		"                        <button class=\"btn btn-primary\" type=\"submit\">\r\n" +
		"                            <fa-icon [icon]=\"'search'\"></fa-icon>\r\n" +
		"                            <span jhiTranslate=\"global.search.buttonLabel\">Cerca</span>\r\n" +
		"                        </button>\r\n" +
		"                    </div>\r\n" +
		"                </form>\r\n" +
		"            </ng-template>\r\n" +
		"        </ngb-panel>\r\n" +
		"    </ngb-accordion>\r\n";

		//[/FILTRI_RICERCA]
		
		
		
		
		//TABELLA
		body +=
		"    <div class=\"table-responsive\" *ngIf=\""+nometabella+"s\">\r\n" +
		"        <table class=\"table table-striped\">\r\n";
		
		
		//MAIN CICLE ONE
		body +=
		"            <thead>\r\n"+
		"            <tr jhiSort [(predicate)]=\"predicate\" [(ascending)]=\"reverse\" [callback]=\"transition.bind(this)\">\r\n"+
	    "            <th jhiSortBy=\"id\"><span jhiTranslate=\"global.field.id\">ID</span> <fa-icon [icon]=\"'sort'\"></fa-icon></th>\r\n";
		for (Column column : tabella.getSortedColumns()) {
			String ColumnName = Utils.getFieldNameForMethod(column);
			String columnname = Utils.getFieldName(column);
			String Splitted = Utils.splitCamelCase(ColumnName);
			if(Utils.isPrimaryKeyID(column) ) {
				//System.out.println("#Skip generation for Primary Key ID..");
			} else {
				body += "\t\t\t<th jhiSortBy=\""+columnname+"\"><span jhiTranslate=\""+conf.getProjectName()+"App."+nometabella+"."+columnname+"\">"+Splitted+"</span><fa-icon [icon]=\"'sort'\"></fa-icon></th>\r\n";
			}
		}
		
		//Relations  TH
		body += buildRelations(conf, TH);
		
		body +=
		"            <th></th>\r\n" +
		"            </tr>\r\n" +
		"            </thead>\r\n";
		//MAIN CICLE ONE
		
		
		body +=
		"            <tbody>\r\n" +
		"            <tr *ngFor=\"let "+nometabella+" of "+nometabella+"s ;trackBy: trackId\">\r\n" +
		"                <td><a [routerLink]=\"['/"+nometabella+"', "+nometabella+".id, 'view' ]\">{{"+nometabella+".id}}</a></td>\r\n";
		
		
		for (Column column : tabella.getSortedColumns()) {
			String columnname = Utils.getFieldName(column);
			if(Utils.isPrimaryKeyID(column) ) {
				//System.out.println("#Skip generation for Primary Key ID..");
				
			} else if(Utils.isDateField(column)) {
				/**
				 * LocalDate ==> | date:'mediumDate'"
				 * Instant   ==> | date:'medium'
				 */
				String filterDate = Utils.isLocalDate(column) ? " date:'mediumDate'" : " date:'medium'";
				body += "\t\t\t<td>{{"+nometabella+"." + columnname + " | " + filterDate + "}}</td>\r\n";
			} else if(Utils.isBlob(column)) {
				/**
				 * Blob management
				 */
				body += "t\t\t<td>\r\n";
				body += "\t\t\t    <a *ngIf=\""+nometabella+"."+ columnname +"\" (click)=\"openFile("+nometabella+"."+ columnname +"ContentType, "+nometabella+"."+ columnname +")\" jhiTranslate=\"entity.action.open\">open</a>\r\n" ;
				body += "\t\t\t    <span *ngIf=\""+nometabella+"."+ columnname +"\">{{"+nometabella+"."+ columnname +"ContentType}}, {{byteSize("+nometabella+"."+ columnname +")}}</span>\r\n" ;
				body += "t\t\t</td>\r\n";

			} else {
				body += "\t\t\t<td>{{"+nometabella+"."+columnname+"}}</td>\r\n";
			}
		}
		
		//Relations fields
		body += buildRelations(conf, TD);
		
		body +=
		"                <td class=\"text-right\">\r\n" +
		"                    <div class=\"btn-group flex-btn-group-container\">\r\n" +
		"                        <button type=\"submit\"\r\n" +
		"                                [routerLink]=\"['/"+nometabella+"', "+nometabella+".id, 'view' ]\"\r\n" +
		"                                class=\"btn btn-info btn-sm\">\r\n" +
		"                            <fa-icon [icon]=\"'eye'\"></fa-icon>\r\n" +
		"                            <span class=\"d-none d-md-inline\" jhiTranslate=\"entity.action.view\">View</span>\r\n" +
		"                        </button>\r\n" +
		"                        <button type=\"submit\"\r\n" +
		"                                [routerLink]=\"['/"+nometabella+"', "+nometabella+".id, 'edit']\"\r\n" +
		"                                class=\"btn btn-primary btn-sm\">\r\n" +
		"                            <fa-icon [icon]=\"'pencil-alt'\"></fa-icon>\r\n" +
		"                            <span class=\"d-none d-md-inline\" jhiTranslate=\"entity.action.edit\">Edit</span>\r\n" +
		"                        </button>\r\n" +
		"                        <button type=\"submit\"\r\n" +
		"                                [routerLink]=\"['/', { outlets: { popup: '"+nometabella+"/'+ "+nometabella+".id + '/delete'} }]\"\r\n" +
		"                                replaceUrl=\"true\"\r\n" +
		"                                queryParamsHandling=\"merge\"\r\n" +
		"                                class=\"btn btn-danger btn-sm\">\r\n" +
		"                            <fa-icon [icon]=\"'times'\"></fa-icon>\r\n" +
		"                            <span class=\"d-none d-md-inline\" jhiTranslate=\"entity.action.delete\">Delete</span>\r\n" +
		"                        </button>\r\n" +
		"                    </div>\r\n" +
		"                </td>\r\n" +
		"            </tr>\r\n" +
		"            </tbody>\r\n" +
		"        </table>\r\n" +
		"    </div>\r\n" +
		"    <div *ngIf=\""+nometabella+"s && "+nometabella+"s.length\">\r\n" +
		"        <div class=\"row justify-content-center\">\r\n" +
		"            <jhi-item-count [page]=\"page\" [total]=\"queryCount\" [maxSize]=\"5\" [itemsPerPage]=\"itemsPerPage\"></jhi-item-count>\r\n" +
		"        </div>\r\n" +
		"        <div class=\"row justify-content-center\">\r\n" +
		"            <ngb-pagination [collectionSize]=\"totalItems\" [(page)]=\"page\" [pageSize]=\"itemsPerPage\" [maxSize]=\"5\" [rotate]=\"true\" [boundaryLinks]=\"true\" (pageChange)=\"loadPage(page)\"></ngb-pagination>\r\n" +
		"        </div>\r\n" +
		"    </div>\r\n" +
		"</div>\r\n";
		return body;
	}

	private String buildRelations(ConfigCreateProject conf, String type) {
		String result = "";
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
					
					//Relations OneToOne / ManyToOne
					if (relationType.equals(Utils.OneToOne) || relationType.equals(Utils.ManyToOne)) {
						if (nomeTabellaSx.toLowerCase().equals(nomeTabella)) {
							if(TH.equals(type)){
								result += "			<!-- TH - OneToOne / ManyToOne -->\n";
								result += 
									  "			<th jhiSortBy=\""+Utils.getFirstLowerCase(nomeRelazioneSx)+""+Utils.getFirstUpperCase(nomeSelectSx)+"\">"
									+ "			  <span jhiTranslate=\""+conf.getProjectName()+"App."+Utils.getFirstLowerCase(nomeTabellaSx)+"."+Utils.getFirstLowerCase(nomeRelazioneSx)+"\">"+Utils.getFirstUpperCase(nomeRelazioneSx)+"</span>\n"
									+ "			  <fa-icon [icon]=\"'sort'\"></fa-icon>\n"
									+ "			</th>\r\n";
							}
							if(TD.equals(type)) {
								result += "			<!-- TD - OneToOne / ManyToOne -->\n";
								result += 
										"			<td>\r\n" +
										"			   <div *ngIf=\""+Utils.getFirstLowerCase(nomeTabellaSx)+"."+Utils.getFirstLowerCase(nomeRelazioneSx)+"Id\">\r\n" +
										"                  <a [routerLink]=\"['../"+Utils.getFirstLowerCase(nomeTabellaDx)+"', "+Utils.getFirstLowerCase(nomeTabellaSx)+"."+Utils.getFirstLowerCase(nomeRelazioneSx)+"Id , 'view' ]\" >{{"+Utils.getFirstLowerCase(nomeTabellaSx)+"."+Utils.getFirstLowerCase(nomeRelazioneSx)+""+Utils.getFirstUpperCase(nomeSelectSx)+"}}</a>\r\n" +
										"			   </div>\r\n" +
										"			</td>\r\n";
							}
						}
						
					} else if (relationType.equals(Utils.OneToMany)) {
						//Relations OneToMany	
						if (nomeTabellaDx.toLowerCase().equals(nomeTabella)) {
							if(TH.equals(type)){
								// DONE TEST   "relations"   autore  ==> preferito2   /      nomeTabellaSx ==> nomeRelazioneDx
								result += "			<!-- TH    Name :  "+nomeRelazioneDx+"  - Type: OneToMany -->\n";
								result += 
									  "			<th jhiSortBy=\""+Utils.getFirstLowerCase(nomeRelazioneDx)+""+Utils.getFirstUpperCase(nomeSelectDx)+"\">\n"
									+ "			  <span jhiTranslate=\""+conf.getProjectName()+"App."+Utils.getFirstLowerCase(nomeTabellaDx)+"."+Utils.getFirstLowerCase(nomeRelazioneDx)+"\">"+Utils.getFirstUpperCase(nomeRelazioneDx)+"</span>\n"
									+ "			  <fa-icon [icon]=\"'sort'\"></fa-icon>\n"
									+ "			</th>\r\n";
								
								
							}
							if(TD.equals(type)) {
								result += "			<!-- TD    Name :  "+nomeRelazioneDx+"  - Type: OneToMany -->\n";
								result += 
										"			<td>\r\n" +
										"			   <div *ngIf=\""+Utils.getFirstLowerCase(nomeTabellaDx)+"."+Utils.getFirstLowerCase(nomeRelazioneDx)+"Id\">\r\n" +
										"                  <a [routerLink]=\"['../"+Utils.getFirstLowerCase(nomeTabellaSx)+"', "+Utils.getFirstLowerCase(nomeTabellaDx)+"."+Utils.getFirstLowerCase(nomeRelazioneDx)+"Id , 'view' ]\" >{{"+Utils.getFirstLowerCase(nomeTabellaDx)+"."+Utils.getFirstLowerCase(nomeRelazioneDx)+""+Utils.getFirstUpperCase(nomeSelectDx)+"}}</a>\r\n" +
										"			   </div>\r\n" +
										"			</td>\r\n";
							}
						}
						
						
					} else if (relationType.equals(Utils.ManyToMany)) {
						//Relations ManyToMany - DEVELOP THIS!
					}
				}
				
				
			}
		}
		return result;
	}
	
	public String getClassName(){
		return Utils.getClassNameLowerCase(tabella)+".component";
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
