package it.eng.generate.template.resouces;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateBanner extends AbstractResourceTemplate{
	
	public TemplateBanner(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = ""; 
		return typeTemplate;
	}
	
	public String getTypeFile() {
		return "txt";
	}

	public String getBody() {
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = "\r\n" + 
				"  ${AnsiColor.GREEN}      ██╗${AnsiColor.RED}   ██████╗ ████████╗ ████████╗ ███████╗\r\n" + 
				"  ${AnsiColor.GREEN}      ██║${AnsiColor.RED}  ██╔════╝ ╚══██╔══╝ ██╔═════╝ ██╔═══██╗\r\n" + 
				"  ${AnsiColor.GREEN}      ██║${AnsiColor.RED}  ╚█████╗     ██║    ██████╗   ███████╔╝\r\n" + 
				"  ${AnsiColor.GREEN}██╗   ██║${AnsiColor.RED}   ╚═══██╗    ██║    ██╔═══╝   ██╔══██║\r\n" + 
				"  ${AnsiColor.GREEN}╚██████╔╝${AnsiColor.RED}  ██████╔╝    ██║    ████████╗ ██║  ╚██╗\r\n" + 
				"  ${AnsiColor.GREEN} ╚═════╝ ${AnsiColor.RED}  ╚═════╝     ╚═╝    ╚═══════╝ ╚═╝   ╚═╝\r\n" + 
				"\r\n" + 
				"${AnsiColor.BRIGHT_BLUE}:: JSter 🤓  :: Running Spring Boot ${spring-boot.version} ::\r\n" + 
				":: https://github.com/marcomattolab/smart ::${AnsiColor.DEFAULT}\r\n";
		return body;
	}

	public String getClassName() {
		return "banner";
	}
	
	public String getSourceFolder() {
		return "src/main/resources";
	}

}
