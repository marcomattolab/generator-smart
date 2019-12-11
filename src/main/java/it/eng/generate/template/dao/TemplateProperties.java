package it.eng.generate.template.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

@Deprecated
public class TemplateProperties extends AbstractTemplate {

	public TemplateProperties(DataBase database) {
		super(database);
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
		return "properties";
	}

	public String getSourceFolder() {
		//return ConfigCreateProject.getIstance().getSourceDAOImplFolder();
		return "";
	}

	public String getBody(){
		ConfigCreateProject cc = ConfigCreateProject.getIstance();
		String body = "";
		body += "\npassword="+cc.getPassword();
		body += "\nuser="+cc.getUsername();
		body += "\njdbcUrl="+cc.getUrlConnection();
		body += "\ndriverDB="+cc.getDriver();
		body += "\nowner="+((cc.getOwner()==null||cc.getOwner().trim().equals(""))?"":cc.getOwner()+".");
		body += "\n#jndi_owner=";
		body += "\nDEBUG=TRUE";
		body += "\nCacheRealise=12*ORA";
		body += "\nNumConnection=200";
		body += "\n#jndiName=java:comp/env/ds/"+cc.getDataBaseName();
		Set setTableNames = database.getTableName();
		int curr = 0;
		for (Iterator iter = setTableNames.iterator(); iter.hasNext();) {
			String tabName = (String) iter.next();
			Table tabella =  database.getTables(tabName);
			body += "\ngeneratepersistent_"+curr+"="+cc.getPackageclass()+".generatepersistent."+Utils.getGPClassName(tabella);
			curr++;
		}
		body += "";
		return body;
	}

	public String getClassName(){
		return "db";
	}

	public String getTypeTemplate() {
		return "conf";
	}	

}
