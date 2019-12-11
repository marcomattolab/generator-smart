package it.eng.generate.template.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

@Deprecated
public class TemplateCostantName extends AbstractTemplate {

	public TemplateCostantName(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody(){
		List campi = generaEntityCostant();
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "\npackage "+ conf.getPackageclass()+".utils;";
		body += "\n";
		body += "\n";
		body += "\npublic class "+getClassName()+" {";
		body += "\npublic static final String COL_DATAFINEVALIDITA = \"datafinevalidita\";";
		body += "\npublic static final String COL_DATAINIZIOVALIDITA = \"datainiziovalidita\";";
		for (Iterator iter = campi.iterator(); iter.hasNext();) {
			body += iter.next().toString();			
		}
		body += "\n}";
		return body;
	}

	private List generaEntityCostant() {
		List campi = new ArrayList();
		Set setTabNames = database.getTableName();
		for (Iterator iter = setTabNames.iterator(); iter.hasNext();) {
			String keyTab = (String) iter.next();
			Table tabella =  database.getTables(keyTab);
			String constTabName = "TAB_"+Utils.getCostantName(tabella);
			EntityCostant ec = new EntityCostant(constTabName,tabella.getNomeTabellaCompleto());
			if(!campi.contains(ec)) campi.add(ec);
			Set setColNames = tabella.getColumnNames();
			for (Iterator iter2 = setColNames.iterator(); iter2.hasNext();) {
				String keyCol = (String) iter2.next();
				Column col = tabella.getColumn(keyCol);
				String constColName = "COL_"+Utils.getCostantName(col);
				ec = new EntityCostant(constColName,col.getName());
				if(!campi.contains(ec)) campi.add(ec);
			}
		}
		return campi;
	}

	public String getClassName(){
		return "ConstantName";
	}

	public String getTypeTemplate() {
		return "Utils";
	}

	public String getSourceFolder() {
		//return ConfigCreateProject.getIstance().getSourceDAOImplFolder();
		return "";
	}

	private class EntityCostant{
		private String costant;
		private String value;

		public EntityCostant(String costant,String value){
			this.costant = costant;
			this.value = value;
		}

		public String toString() {
			return "\n\tpublic static final String "+costant+" = \""+value+"\";\n";
		}

		public int hashCode() {
			return costant.hashCode();
		}

		public boolean equals(Object obj) {
			if(!(obj instanceof EntityCostant))
				return false;
			else
				return this.hashCode() == obj.hashCode();
		}
	}


}
