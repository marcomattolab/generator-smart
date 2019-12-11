package it.eng.generate.template.fe.shared;

import java.util.Iterator;
import java.util.List;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Enumeration;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntitySharedModel extends AbstractResourceTemplate {

	public TemplateEntitySharedModel(Table tabella) {
		super(tabella);
	}
	
	public TemplateEntitySharedModel(DataBase database, Table tabella) {
		super(database);
		this.tabella = tabella;
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String Nometabella = Utils.getEntityName(tabella);
		String nometabella = Utils.getClassNameLowerCase(tabella);
		String INometabella = Utils.getIName(tabella);
		String NometabellaService = Utils.getEntityName(tabella);
		
		//Done MOVED THIS INTO UTILS
		List<Enumeration> enumList = Utils.getEnumerationsByDbAndTable(database, tabella);
		
		String body = 
		"import { Moment } from 'moment';\r\n\n";
		
		//TODO RELATION IMPORT
		//"import { IIncarico } from 'app/shared/model/incarico.model';\r\n" +

		//DONE Enumerations
		for(Enumeration enumeration : enumList) {
			body += "export const enum "+enumeration.getNomeEnumeration()+" {\r\n";
			List<String> values = enumeration.getValoriEnumeration();
			for (Iterator it = values.iterator(); it.hasNext();) {
				String value = (String) it.next();
				body += 	"    "+value+" = '"+value+"'" + (it.hasNext()?",\r\n":"\r\n");
			}
			body += "}\r\n\n";
		}
		
		//Generate IInetrface
		body += Utils.generateIInterface(tabella);
		
		//Generate Class
		body += Utils.generateIClass(tabella);
				
		body += "}\r\n";
		
		return body;
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
		return "src/main/webapp/app/shared/model";
	}

}
