package it.eng.generate.template.resouces;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateMessage extends AbstractResourceTemplate{
	
	public TemplateMessage(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getResi18nFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "properties";
	}

	public String getBody() {
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = "# Error page\r\n" +
		"error.title=Your request cannot be processed\r\n" +
		"error.subtitle=Sorry, an error has occurred.\r\n" +
		"error.status=Status:\r\n" +
		"error.message=Message:\r\n" +
		"# Activation email\r\n" +
		"email.activation.title="+conf.getProjectName()+" account activation\r\n" +
		"email.activation.greeting=Dear {0}\r\n" +
		"email.activation.text1=Your "+conf.getProjectName()+" account has been created, please click on the URL below to activate it:\r\n" +
		"email.activation.text2=Regards,\r\n" +
		"email.signature="+conf.getProjectName()+" Team.\r\n" +
		"# Creation email\r\n" +
		"email.creation.text1=Your "+conf.getProjectName()+" account has been created, please click on the URL below to access it:\r\n" +
		"# Reset email\r\n" +
		"email.reset.title="+conf.getProjectName()+" password reset\r\n" +
		"email.reset.greeting=Dear {0}\r\n" +
		"email.reset.text1=For your "+conf.getProjectName()+" account a password reset was requested, please click on the URL below to reset it:\r\n" +
		"email.reset.text2=Regards,\r\n";
		return body;
	}

	public String getClassName() {
		return "messages";
	}

	public String getSourceFolder() {
		return "src/main/resources";
	}

}
