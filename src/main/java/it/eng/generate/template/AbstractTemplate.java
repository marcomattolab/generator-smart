package it.eng.generate.template;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Enumeration;
import it.eng.generate.Table;
import it.eng.generate.Utils;

public abstract class AbstractTemplate {
	protected Table tabella;
	protected DataBase database;
	protected Enumeration enumeration;
	
	public AbstractTemplate(DataBase database) {
		this.database = database;
	}

	public AbstractTemplate(Table tabella) {
		this.tabella = tabella;
	}
	
	public AbstractTemplate(Enumeration enumeration) {
		this.enumeration = enumeration;
	}
	
	public void generateTemplate() throws IOException{
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String relative = Utils.replace(conf.getPackageclass(),".","/");
		String root = conf.getPathname();
		String projectName = conf.getProjectName();
		String sourceFolder = getSourceFolder();
		File f = new File(root + "/"+projectName+"/"+sourceFolder+"/"+relative +"/"+getTypeTemplate().toLowerCase()+"/");
		if(!f.exists()) {
			f.mkdirs();
		}
		f = new File(f.getAbsolutePath()+"/"+getClassName()+"."+getTypeFile());
		FileWriter fw = new FileWriter(f);
		fw.write(getBody());
		fw.close();
	}
	
	public String getTypeFile() {
		return "java";
	}

	public abstract String getTypeTemplate() ;

	public abstract String getBody();
	
	public abstract String getClassName();

	public abstract String getSourceFolder();
	
}
