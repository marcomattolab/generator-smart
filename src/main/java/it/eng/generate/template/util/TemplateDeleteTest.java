package it.eng.generate.template.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;

public class TemplateDeleteTest {
	protected DataBase database;

	public TemplateDeleteTest(DataBase database) {
		this.database = database;
	}
	
	public void generateTemplate() throws IOException{
		ConfigCreateProject cc = ConfigCreateProject.getIstance();
		String pathOut = cc.getPathname() + cc.getProjectName();
		
		String PATH_FOLDER_TEST = pathOut + File.separator + "src" + File.separator + "test";
		System.out.println("# Deleting   the   PATH_FOLDER_TEST     ====> " + PATH_FOLDER_TEST );
		deleteDirectoryStream( Paths.get(PATH_FOLDER_TEST) );
	}
	
	static void deleteDirectoryStream(Path path) throws IOException {
		System.out.println("Deleting ...");
		Files.walk(path).sorted(Comparator.reverseOrder())
						.map(Path::toFile)
						.forEach(File::delete);
		System.out.println("Delete done!");
	}
	
	public String getTypeFile() {
		return "properties";
	}

	public String getBody(){
		return "";
	}
	
	public String getClassName(){
		return ".classpath";
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println("Start Copy and Delete...");
		deleteDirectoryStream( Paths.get("C:\\eclipse-workspace\\demogenerated\\src\\test") );
		System.out.println("End Copy and Delete!!!");
	}
	
}
