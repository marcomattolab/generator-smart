package it.eng.generate.project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;

public class TemplateProject {

	protected DataBase database;

	public TemplateProject(DataBase database) {
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
		"\n<projectDescription>" +
		"\n        <name>" + conf.getProjectName() + "</name>" +
		"\n        <comment></comment>" +
		"\n        <projects>" + conf.getProjectName() + "</projects>" +
		"\n        <buildSpec>" +
		"\n                <buildCommand>" +
		"\n                        <name>org.eclipse.jdt.core.javabuilder</name>" +
		"\n                        <arguments>" +
		"\n                        </arguments>" +
		"\n                </buildCommand>" +
		"\n                <buildCommand>" +
		"\n                        <name>org.eclipse.wst.common.project.facet.core.builder</name>" +
		"\n                        <arguments>" +
		"\n                        </arguments>" +
		"\n                </buildCommand>" +
		"\n                <buildCommand>" +
		"\n                        <name>org.eclipse.wst.validation.validationbuilder</name>" +
		"\n                        <arguments>" +
		"\n                        </arguments>" +
		"\n                </buildCommand>" +
		"\n                <buildCommand>" +
		"\n                        <name>org.springframework.ide.eclipse.core.springbuilder</name>" +
		"\n                        <arguments>" +
		"\n                        </arguments>" +
		"\n                </buildCommand>" +
		"\n                <buildCommand>" +
		"\n                        <name>org.springframework.ide.eclipse.boot.validation.springbootbuilder</name>" +
		"\n                        <arguments>" +
		"\n                        </arguments>" +
		"\n                </buildCommand>" +
		"\n                <buildCommand>" +
		"\n                        <name>com.genuitec.eclipse.typescript.typeScriptBuilder</name>" +
		"\n                        <arguments>" +
		"\n                        </arguments>" +
		"\n                </buildCommand>" +
		"\n                <buildCommand>" +
		"\n                        <name>org.eclipse.m2e.core.maven2Builder</name>" +
		"\n                        <arguments>" +
		"\n                        </arguments>" +
		"\n                </buildCommand>" +
		"\n        </buildSpec>" +
		"\n        <natures>" +
		"\n                <nature>org.springframework.ide.eclipse.core.springnature</nature>" +
		"\n                <nature>org.eclipse.jem.workbench.JavaEMFNature</nature>" +
		"\n                <nature>org.eclipse.wst.common.modulecore.ModuleCoreNature</nature>" +
		"\n                <nature>org.eclipse.jdt.core.javanature</nature>" +
		"\n                <nature>org.eclipse.m2e.core.maven2Nature</nature>" +
		"\n                <nature>org.eclipse.wst.common.project.facet.core.nature</nature>" +
		"\n                <nature>org.eclipse.wst.jsdt.core.jsNature</nature>" +
		"\n        </natures>" +
		"\n        <filteredResources>" +
		"\n                <filter>" +
		"\n                        <id>1541458018179</id>" +
		"\n                        <name></name>" +
		"\n                        <type>10</type>" +
		"\n                        <matcher>" +
		"\n                                <id>org.eclipse.ui.ide.multiFilter</id>" +
		"\n                                <arguments>1.0-name-matches-false-false-node_modules</arguments>" +
		"\n                        </matcher>" +
		"\n                </filter>" +
		"\n                <filter>" +
		"\n                        <id>1541458018182</id>" +
		"\n                        <name></name>" +
		"\n                        <type>10</type>" +
		"\n                        <matcher>" +
		"\n                                <id>org.eclipse.ui.ide.multiFilter</id>" +
		"\n                                <arguments>1.0-name-matches-false-false-node_modules</arguments>" +
		"\n                        </matcher>" +
		"\n                </filter>" +
		"\n                <filter>" +
		"\n                        <id>1541458018184</id>" +
		"\n                        <name></name>" +
		"\n                        <type>10</type>" +
		"\n                        <matcher>" +
		"\n                                <id>org.eclipse.ui.ide.multiFilter</id>" +
		"\n                                <arguments>1.0-name-matches-false-false-node_modules</arguments>" +
		"\n                        </matcher>" +
		"\n                </filter>" +
		"\n                <filter>" +
		"\n                        <id>1541458018190</id>" +
		"\n                        <name></name>" +
		"\n                        <type>10</type>" +
		"\n                        <matcher>" +
		"\n                                <id>org.eclipse.ui.ide.multiFilter</id>" +
		"\n                                <arguments>1.0-name-matches-false-false-node_modules</arguments>" +
		"\n                        </matcher>" +
		"\n                </filter>" +
		"\n                <filter>" +
		"\n                        <id>1541458018192</id>" +
		"\n                        <name></name>" +
		"\n                        <type>10</type>" +
		"\n                        <matcher>" +
		"\n                                <id>org.eclipse.ui.ide.multiFilter</id>" +
		"\n                                <arguments>1.0-name-matches-false-false-node_modules</arguments>" +
		"\n                        </matcher>" +
		"\n                </filter>" +
		"\n                <filter>" +
		"\n                        <id>1541458018195</id>" +
		"\n                        <name></name>" +
		"\n                        <type>10</type>" +
		"\n                        <matcher>" +
		"\n                                <id>org.eclipse.ui.ide.multiFilter</id>" +
		"\n                                <arguments>1.0-name-matches-false-false-node_modules</arguments>" +
		"\n                        </matcher>" +
		"\n                </filter>" +
		"\n        </filteredResources>" +
		"\n</projectDescription>";
		return body;
	}

	public String getClassName(){
		return ".project";
	}

}
