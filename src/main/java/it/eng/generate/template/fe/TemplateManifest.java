package it.eng.generate.template.fe;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateManifest extends AbstractResourceTemplate {

	public TemplateManifest(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "webapp";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		// Utils.getClassNameCamelCase(conf.getProjectName()) + conf.getApp() 
		String body = "{\r\n" +
		"  \"name\": \""+Utils.getClassNameCamelCase(conf.getProjectName())+"\",\r\n" +
		"  \"short_name\": \""+Utils.getClassNameCamelCase(conf.getProjectName())+"\",\r\n" +
		"  \"icons\": [\r\n" +
		"      {\r\n" +
		"          \"src\": \"./content/images/hipster192.png\",\r\n" +
		"          \"sizes\": \"192x192\",\r\n" +
		"          \"type\": \"image/png\"\r\n" +
		"      },\r\n" +
		"      {\r\n" +
		"          \"src\": \"./content/images/hipster256.png\",\r\n" +
		"          \"sizes\": \"256x256\",\r\n" +
		"          \"type\": \"image/png\"\r\n" +
		"      },\r\n" +
		"      {\r\n" +
		"          \"src\": \"./content/images/hipster384.png\",\r\n" +
		"          \"sizes\": \"384x384\",\r\n" +
		"          \"type\": \"image/png\"\r\n" +
		"      },\r\n" +
		"      {\r\n" +
		"          \"src\": \"./content/images/hipster512.png\",\r\n" +
		"          \"sizes\": \"512x512\",\r\n" +
		"          \"type\": \"image/png\"\r\n" +
		"      }\r\n" +
		"  ],\r\n" +
		"  \"theme_color\": \"#000000\",\r\n" +
		"  \"background_color\": \"#e0e0e0\",\r\n" +
		"  \"start_url\": \"/index.html\",\r\n" +
		"  \"display\": \"standalone\",\r\n" +
		"  \"orientation\": \"portrait\"\r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "manifest";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/webapp";
	}

}
