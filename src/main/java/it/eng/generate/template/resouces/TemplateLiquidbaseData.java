package it.eng.generate.template.resouces;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateLiquidbaseData extends AbstractResourceTemplate{
	private static final int ITERATION_COUNT = 10; 
	
	public TemplateLiquidbaseData(DataBase database, Table tabella) {
		super(database);
		this.tabella = tabella;
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getResConfigLiquibaseDataFolder(),".","/");
		return typeTemplate;
	}
	
	public String getTypeFile() {
		return "csv";
	}

	public String getBody() {
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "";
		
		//Header CSV
		int i = 0;
		int s = tabella.getColumns().size();
		for (Column column: tabella.getColumns()) {
			String nomeColonna = column.getName();
			body += nomeColonna + (i+1<s ? ";" : "");
			i++;
		}
		body += "\n";
		
		//Rows CSV
		for(int a=0; a<ITERATION_COUNT; a++) {
			int k=0;
			for (Column column: tabella.getColumns()) {
				String ctype = column.getLabelType().toLowerCase();
				String classes = column.getTypeColumn().getName();
				//TODO ADD PATTERN VALIDATION
				if ("date".equals(ctype)) {
			    		body += Utils.getRandomDate();
			    	} else if("string".equals(ctype)) {
					body +=  column.getEnumeration()!=null
						 ? Utils.getRandomEnumeration(column, tabella, database)
						 : Utils.getRandomString(column);
				} else if ("integer".equals(ctype)) {
					body += a+1;
				} else if ("numeric".equals(ctype)) {
					body += a+101;
				} else if ("boolean".equals(ctype)) {
					body += Utils.getRandomBoolean();
				}
				body += (k+1) < tabella.getColumns().size() ? ";" : "";
				k++;
			}
			body += "\n";
		}

		return body;
	}

	public String getClassName() {
		String entityname = Utils.getClassNameLowerCase(tabella);
		return entityname +"s";
	}
	
	public String getSourceFolder() {
		return "src/main/resources";
	}

}
