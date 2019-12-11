package it.eng.generate.template.domain;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Enumeration;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateDomainEnumeration extends AbstractTemplate{

	public TemplateDomainEnumeration(Enumeration enumeration) {
		super(enumeration);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcDomainEnumerationFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcDomainEnumerationFolder()+";\r\n\n" +
		"/**\r\n" +
		" * The "+enumeration.getNomeEnumeration()+" enumeration.\r\n" +
		" */\r\n" +
		"public enum "+enumeration.getNomeEnumeration()+" {";
		for(int i=0;i<enumeration.getValoriEnumeration().size(); i++) {
			body +="\r\n\t\t" + enumeration.getValoriEnumeration().get(i) 
					+ (i==enumeration.getValoriEnumeration().size()-1 ? "" : ",");
		}
		body +="\r\n\n";
		//TODO Add For block custom
		
		body +="}\r\n";
		return body;
	}

	public String getClassName() {
		return enumeration.getNomeEnumeration();
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
