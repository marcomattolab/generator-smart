package it.eng.generate.project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;

public class TemplateFactoryPath {
	protected DataBase database;

	public TemplateFactoryPath(DataBase database) {
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
		f = new File(f.getAbsolutePath()+"/"+getClassName());
		FileWriter fw = new FileWriter(f);
		fw.write(getBody());
		fw.close();
	}
	
	public String getTypeFile() {
		//return "properties";
		return "";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = 
		"<factorypath>\r\n" +
		"    <factorypathentry kind=\"PLUGIN\" id=\"org.eclipse.jst.ws.annotations.core\" enabled=\"false\" runInBatchMode=\"false\"/>\r\n" +
		"    <factorypathentry kind=\"VARJAR\" id=\"M2_REPO/org/hibernate/hibernate-jpamodelgen/5.2.17.Final/hibernate-jpamodelgen-5.2.17.Final.jar\" enabled=\"true\" runInBatchMode=\"false\"/>\r\n" +
		"</factorypath>\r\n";
		return body;
	}
	
	public String getClassName(){
		return ".factorypath";
	}
	
}
