package it.eng.generate.template.test;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateWebConfigurerTestController extends AbstractTemplate{

	public TemplateWebConfigurerTestController(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcConfigFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcConfigFolder()+";\r\n\n" +
		"import org.springframework.web.bind.annotation.GetMapping;\r\n" +
		"import org.springframework.web.bind.annotation.RestController;\r\n\n" +
		"@RestController\r\n" +
		"public class WebConfigurerTestController {\r\n" +
		"    @GetMapping(\"/api/test-cors\")\r\n" +
		"    public void testCorsOnApiPath() {\r\n" +
		"    }\r\n\n" +
		"    @GetMapping(\"/test/test-cors\")\r\n" +
		"    public void testCorsOnOtherPath() {\r\n" +
		"    }\r\n\n" +
		"}\r\n";

		return body;
	}

	public String getClassName() {
		return "WebConfigurerTestController";
	}

	public String getSourceFolder() {
		return "src/test/java";
	}

}
