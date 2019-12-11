package it.eng.generate.project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;

public class TemplateAngular {

	protected DataBase database;

	public TemplateAngular(DataBase database) {
		this.database = database;
	}

	public void generateTemplate() throws IOException{
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String root = conf.getPathname();
		String projectName = conf.getProjectName();
		File f = new File(root + "/"+projectName+"/");
		if(!f.exists()) {
			f.mkdirs();
		}
		f = new File(f.getAbsolutePath()+"/"+getClassName()+"."+getTypeFile());
		FileWriter fw = new FileWriter(f);
		fw.write(getBody());
		fw.close();
	}

	public String getTypeFile() {
		return "json";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = "{\r\n" +
		"  \"$schema\": \"./node_modules/@angular/cli/lib/config/schema.json\",\r\n" +
		"  \"version\": 1,\r\n" +
		"  \"newProjectRoot\": \"projects\",\r\n" +
		"  \"projects\": {\r\n" +
		"    \""+conf.getProjectName()+"\": {\r\n" +
		"      \"root\": \"\",\r\n" +
		"      \"sourceRoot\": \"src/main/webapp\",\r\n" +
		"      \"projectType\": \"application\",\r\n" +
		"      \"architect\": {}\r\n" +
		"    }\r\n" +
		"  },\r\n" +
		"  \"defaultProject\": \""+conf.getProjectName()+"\",\r\n" +
		"  \"cli\": {\r\n" +
		"    \"packageManager\": \"npm\"\r\n" +
		"  },\r\n" +
		"  \"schematics\": {\r\n" +
		"    \"@schematics/angular:component\": {\r\n" +
		"      \"inlineStyle\": true,\r\n" +
		"      \"inlineTemplate\": false,\r\n" +
		"      \"spec\": false,\r\n" +
		"      \"prefix\": \"jhi\",\r\n" +
		"      \"styleExt\": \"scss\"\r\n" +
		"    },\r\n" +
		"    \"@schematics/angular:directive\": {\r\n" +
		"      \"spec\": false,\r\n" +
		"      \"prefix\": \"jhi\"\r\n" +
		"    },\r\n" +
		"    \"@schematics/angular:guard\": {\r\n" +
		"      \"spec\": false\r\n" +
		"    },\r\n" +
		"    \"@schematics/angular:pipe\": {\r\n" +
		"      \"spec\": false\r\n" +
		"    },\r\n" +
		"    \"@schematics/angular:service\": {\r\n" +
		"      \"spec\": false\r\n" +
		"    }\r\n" +
		"  }\r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "angular";
	}

}
