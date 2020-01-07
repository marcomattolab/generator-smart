package it.eng.generate.template.fe.i18n;

import java.util.Iterator;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.ProjectRelation;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityI18N extends AbstractResourceTemplate{
	private String languageCode;
	
	public TemplateEntityI18N(Table tabella) {
		super(tabella);
	}
	
	public TemplateEntityI18N(Table tabella, String languageCode) {
		super(tabella);
		this.languageCode = languageCode;
	}

	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	public String getTypeFile() {
		return "json";
	}

	public String getBody() {
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		//https://www.buildmystring.com/
		String Tablename = Utils.getEntityName(tabella);
		String tablename = Utils.getFieldName(tabella);
		
		String body = "{\r\n" +
		"    \""+conf.getProjectName()+"App\": {\r\n" +
		"        \""+tablename+"\": {\r\n" +
		"            \"home\": {\r\n" +
		"                \"title\": \""+Tablename+"s\",\r\n" +
		"                \"createLabel\": \"Create a new "+Tablename+"\",\r\n" +
		"                \"createOrEditLabel\": \"Create or edit a "+Tablename+"\"\r\n" +
		"            },\r\n" +
		"            \"created\": \"A new "+Tablename+" is created with identifier {{ param }}\",\r\n" +
		"            \"updated\": \"A "+Tablename+" is updated with identifier {{ param }}\",\r\n" +
		"            \"deleted\": \"A "+Tablename+" is deleted with identifier {{ param }}\",\r\n" +
		"            \"delete\": {\r\n" +
		"                \"question\": \"Are you sure you want to delete "+Tablename+" {{ id }}?\"\r\n" +
		"            },\r\n" +
		"            \"detail\": {\r\n" +
		"                \"title\": \""+Tablename+"\"\r\n" +
		"            },\r\n" ;
		
		//[Manage Relations]
		if(!CollectionUtils.isEmpty(conf.getProjectRelations())) {
			for(ProjectRelation rel: conf.getProjectRelations()) {
				String relationType = rel.getType();
				String nomeTabellaSx = rel.getSxTable();
				String nomeRelazioneSx = rel.getSxName();
				String nomeRelazioneDx = rel.getDxName();
				String nomeTabellaDx = rel.getDxTable();
				String nomeTabella = tabella.getNomeTabella().toLowerCase();
				
				if(nomeTabellaSx!=null && nomeTabellaDx != null) {
					if (relationType.equals(Utils.OneToOne) || relationType.equals(Utils.ManyToOne)) {
						if (nomeTabellaSx.toLowerCase().equals(nomeTabella) ) {
							Column column = new Column();
							column.setName(nomeRelazioneSx);
							body += Utils.generateJson(column)+",\n";
						}
					
					} else if (relationType.equals(Utils.OneToMany) ) {
						if (nomeTabellaDx.toLowerCase().equals(nomeTabella.toLowerCase()) ) {
							Column column = new Column();
							//DONE    nomeTabellaSx ==> nomeRelazioneDx    /   autore ==> preferito2
							column.setName(nomeRelazioneDx);
							body += Utils.generateJson(column)+",\n";
						}
						
					} else if (relationType.equals(Utils.ManyToMany) ) {
						if (nomeTabellaSx.toLowerCase().equals(nomeTabella.toLowerCase()) ) {
							Column column = new Column();
							column.setName(nomeRelazioneSx);
							body +=  Utils.generateJson(column)+",\n";
						}
						
					}
				}
			}
		}
		//[/Manage Relations]
		
		Set<?> set = tabella.getColumnNames();
		for (Iterator<?> iter = set.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
			body += Utils.generateJson(column)+ (iter.hasNext()?",\n":"\n");
		}
		
		body += 
		"        }\r\n" +
		"    }\r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return Utils.getClassNameLowerCase(tabella);
	}

	public String getSourceFolder() {
		return "src/main/webapp/i18n/"+languageCode;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	
}
