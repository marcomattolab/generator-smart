package it.eng.generate.template.ionic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Enumeration;
import it.eng.generate.ProjectRelation;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityModelIonic extends AbstractResourceTemplate {

	public TemplateEntityModelIonic(Table tabella) {
		super(tabella);
	}
	
	public TemplateEntityModelIonic(DataBase database, Table tabella) {
		super(database);
		this.tabella = tabella;
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();

		List<Enumeration> enumList = Utils.getEnumerationsByDbAndTable(database, tabella);
		
		String body = 
		"import { BaseEntity } from 'src/model/base-entity';\n"+
		"import { Moment } from 'moment';\n";

		
		//RELATION IMPORT
		//"import { Giustificativo } from '../giustificativo/giustificativo.model';\n"+
		body += writeImportRelations(conf); 

		
		//DONE Enumerations
		for(Enumeration enumeration : enumList) {
			body += "export const enum "+enumeration.getNomeEnumeration()+" {\r\n";
			List<String> values = enumeration.getValoriEnumeration();
			for (Iterator<String> it = values.iterator(); it.hasNext();) {
				String value = (String) it.next();
				body += 	"    "+value+" = '"+value+"'" + (it.hasNext()?",\r\n":"\r\n");
			}
			body += "}\r\n\n";
		}
		
		//Before RelationsStore Original List
		List<Column> extendedList = new ArrayList<>(tabella.getColumns());
		
		//[Manage Relations]
		if(!CollectionUtils.isEmpty(conf.getProjectRelations())) {
			for(ProjectRelation rel: conf.getProjectRelations()) {
				String relationType = rel.getType();
				String nomeTabellaSx = rel.getSxTable();
				String nomeRelazioneDx = rel.getDxName();
				String nomeRelazioneSx = rel.getSxName();
				String nomeTabellaDx = rel.getDxTable();
				String nomeTabella = tabella.getNomeTabella().toLowerCase();
				
				if(nomeTabellaSx!=null && nomeTabellaDx != null  ) {
					
					if (relationType.equals(Utils.OneToOne) || relationType.equals(Utils.ManyToOne)) {
						if(nomeTabellaSx.toLowerCase().equals(nomeTabella)) {
							Column columnId = new Column();
							columnId.setName(nomeRelazioneSx+"Id");
							columnId.setTypeColumn(Column.corvertModelType("Long"));
							extendedList.add(columnId);
		
							if (relationType.equals(Utils.ManyToOne)) {
								Column columnSelect = new Column();
								columnSelect.setName(nomeRelazioneSx + Utils.getFirstUpperCase(rel.getSxSelect())); 
								columnSelect.setTypeColumn(Utils.getTypeColumnFromRelation(conf, rel.getSxSelect(), nomeTabellaDx));
								extendedList.add(columnSelect);
							}
						}
						
					} else if (relationType.equals(Utils.ManyToMany)) {
						if(nomeTabellaSx.toLowerCase().equals(nomeTabella)) {
							Column columnRel = new Column();
							columnRel.setName(nomeRelazioneSx+"s");
							columnRel.setTypeColumnRelation(""+Utils.getFirstUpperCase(nomeTabellaDx));
							extendedList.add(columnRel);
						}
						if(nomeTabellaDx.toLowerCase().equals(nomeTabella)) {
							Column columnRel = new Column();
							columnRel.setName(nomeRelazioneDx+"s");
							columnRel.setTypeColumnRelation(""+Utils.getFirstUpperCase(nomeTabellaSx));
							extendedList.add(columnRel);
						}
						
					} else if (relationType.equals(Utils.OneToMany)) {
						//TODO DEVELOP THIS!
					}
				} 
			}
		}
		//[/Manage Relations]
      
		//Generate Class
		body += Utils.generateIClass(tabella, extendedList, "BaseEntity");
				
		body += "}\r\n";
		
		return body;
	}
	
	/**
	 * @param conf
	 * @return body with import 
	 */
	private String writeImportRelations(ConfigCreateProject conf) {
		//"import { Giustificativo } from '../giustificativo/giustificativo.model';\n"+
		String res = "";
		Map<String, String> resMap = new HashMap<>();
		if(!CollectionUtils.isEmpty(conf.getProjectRelations())) {
			for(ProjectRelation rel: conf.getProjectRelations()) {
				String relationType = rel.getType();
				String nomeTabellaSx = rel.getSxTable();
				String nomeTabellaDx = rel.getDxTable();
				String nomeTabella = tabella.getNomeTabella().toLowerCase();
				
				if(nomeTabellaSx!=null && nomeTabellaDx != null  ) {
					if (relationType.equals(Utils.ManyToMany)) {
						if(nomeTabellaSx.toLowerCase().equals(nomeTabella)) {
							String impStr = "import { "+Utils.getFirstUpperCase(nomeTabellaDx)+" } from '../"+nomeTabellaDx.toLowerCase()+"/"+nomeTabellaDx.toLowerCase()+".model';\n"; 
							resMap.put(nomeTabellaSx, impStr);
						}
						if(nomeTabellaDx.toLowerCase().equals(nomeTabella)) {
							String impStr = "import { "+Utils.getFirstUpperCase(nomeTabellaSx)+" } from '../"+nomeTabellaSx.toLowerCase()+"/"+nomeTabellaSx.toLowerCase()+".model';\n";
							resMap.put(nomeTabellaDx, impStr);
						}
					} 
				} 
			}
		}
		//Print Relation Map
		res += Utils.printRelationMap(res, resMap);
		res+="\n";
		return res;
	}


	public String getClassName(){
		return Utils.getClassNameLowerCase(tabella)+".model";
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
