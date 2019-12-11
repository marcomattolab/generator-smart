package it.eng.generate.project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;

public class TemplateClassPath {
	protected DataBase database;

	public TemplateClassPath(DataBase database) {
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
		return "properties";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = 
		"\n<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
		"\n<classpath>" +
		"\n        <classpathentry kind=\"src\" output=\"target/classes\" path=\"src/main/java\">" +
		"\n                <attributes>" +
		"\n                        <attribute name=\"optional\" value=\"true\"/>" +
		"\n                        <attribute name=\"maven.pomderived\" value=\"true\"/>" +
		"\n                </attributes>" +
		"\n        </classpathentry>" +
		"\n        <classpathentry excluding=\"**\" kind=\"src\" output=\"target/classes\" path=\"src/main/resources\">" +
		"\n                <attributes>" +
		"\n                        <attribute name=\"maven.pomderived\" value=\"true\"/>" +
		"\n                </attributes>" +
		"\n        </classpathentry>" +
		"\n        <classpathentry kind=\"src\" output=\"target/test-classes\" path=\"src/test/java\">" +
		"\n                <attributes>" +
		"\n                        <attribute name=\"optional\" value=\"true\"/>" +
		"\n                        <attribute name=\"maven.pomderived\" value=\"true\"/>" +
		"\n                        <attribute name=\"test\" value=\"true\"/>" +
		"\n                </attributes>" +
		"\n        </classpathentry>" +
		"\n        <classpathentry excluding=\"**\" kind=\"src\" output=\"target/test-classes\" path=\"src/test/resources\">" +
		"\n                <attributes>" +
		"\n                        <attribute name=\"maven.pomderived\" value=\"true\"/>" +
		"\n                        <attribute name=\"test\" value=\"true\"/>" +
		"\n                </attributes>" +
		"\n        </classpathentry>" +
		"\n        <classpathentry kind=\"con\" path=\"org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-1.8\">" +
		"\n                <attributes>" +
		"\n                        <attribute name=\"maven.pomderived\" value=\"true\"/>" +
		"\n                </attributes>" +
		"\n        </classpathentry>" +
		"\n        <classpathentry kind=\"con\" path=\"org.eclipse.m2e.MAVEN2_CLASSPATH_CONTAINER\">" +
		"\n                <attributes>" +
		"\n                        <attribute name=\"maven.pomderived\" value=\"true\"/>" +
		"\n                        <attribute name=\"org.eclipse.jst.component.dependency\" value=\"/WEB-INF/lib\"/>" +
		"\n                </attributes>" +
		"\n        </classpathentry>" +
		"\n        <classpathentry kind=\"src\" path=\".apt_generated\">" +
		"\n        		<attributes>" +
		"\n        			<attribute name=\"optional\" value=\"true\"/>" +
		"\n        		</attributes>" +
		"\n        	</classpathentry>" +
		"\n        <classpathentry kind=\"output\" path=\"target/classes\"/>" +
		"\n</classpath>";
		return body;
	}

	
	public String getClassName(){
		return ".classpath";
	}
	
}
