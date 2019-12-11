package it.eng.generate.template.conf.audit;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateEntityAuditEventConfig extends AbstractTemplate {

	public TemplateEntityAuditEventConfig(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcConfigAuditFolder() +";\r\n\n" +
		"import org.springframework.beans.factory.BeanFactory;\r\n" +
		"import org.springframework.beans.factory.BeanFactoryAware;\r\n" +
		"import org.springframework.context.annotation.Configuration;\r\n\n" +
		"@Configuration\r\n" +
		"public class EntityAuditEventConfig implements BeanFactoryAware {\r\n\n" +
		"    @Override\r\n" +
		"    public void setBeanFactory(BeanFactory beanFactory) {\r\n" +
		"        EntityAuditEventListener.setBeanFactory(beanFactory);\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "EntityAuditEventConfig";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcConfigAuditFolder(),".","/");
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/java";
	}

}
