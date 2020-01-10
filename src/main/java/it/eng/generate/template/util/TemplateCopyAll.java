package it.eng.generate.template.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;

public class TemplateCopyAll {
	private static final String FOLDER_SRC = "folder";
	protected DataBase database;

	public TemplateCopyAll(DataBase database) {
		this.database = database;
	}
	
	public void generateTemplate() throws IOException{
		ConfigCreateProject cc = ConfigCreateProject.getIstance();
		String pathOut = cc.getPathname() + cc.getProjectName();
		copyDir(new File(FOLDER_SRC), new File(pathOut));
	}
	
	static void copyDir(File baseIn, File baseOut) throws IOException{
		if(!baseOut.exists()) {
			baseOut.mkdirs();
		}
		File[] files = baseIn.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			String filename = baseOut.getAbsolutePath()+""+file.getAbsolutePath().substring(baseIn.getAbsolutePath().length());
			if(file.isDirectory()){
				copyDir(file,new File(filename));
			}else{
				copy(file,new File(filename));
			}
		}
	}

	static void copy(File src, File dst) throws IOException {
		InputStream in = new FileInputStream(src);
		OutputStream out = new FileOutputStream(dst);
		
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
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
		copyDir(new File("C:\\test\\ResumeCV"), new File("C:\\test\\ResumeCVcloned"));
	}
	
}
