package it.eng.generate.template.fe;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateAppMain extends AbstractResourceTemplate {

	public TemplateAppMain(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = "import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';\r\n" +
		"import { ProdConfig } from './blocks/config/prod.config';\r\n" +
		"import { "+Utils.getClassNameCamelCase(conf.getProjectName()) +"AppModule } from './app.module';\r\n" +
		"ProdConfig();\r\n" +
		"if (module['hot']) {\r\n" +
		"    module['hot'].accept();\r\n" +
		"}\r\n" +
		"platformBrowserDynamic()\r\n" +
		"    .bootstrapModule("+Utils.getClassNameCamelCase(conf.getProjectName()) +"AppModule, { preserveWhitespaces: true })\r\n" +
		"    .then(success => console.log(`Application started`))\r\n" +
		"    .catch(err => console.error(err));\r\n";
		return body;
	}

	public String getClassName(){
		return "app.main";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/webapp/app";
	}

}
