package it.eng.generate.template.conf.audit;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateEntityAuditEventListener extends AbstractTemplate {

	public TemplateEntityAuditEventListener(DataBase database) {
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
		"import org.slf4j.Logger;\r\n" +
		"import org.slf4j.LoggerFactory;\r\n" +
		"import org.springframework.beans.factory.annotation.Autowired;\r\n" +
		"import org.springframework.beans.factory.BeanFactory;\r\n" +
		"import org.springframework.beans.factory.NoSuchBeanDefinitionException;\r\n" +
		"import org.springframework.data.jpa.domain.support.AuditingEntityListener;\r\n" +
		"import javax.persistence.PostPersist;\r\n" +
		"import javax.persistence.PostRemove;\r\n" +
		"import javax.persistence.PostUpdate;\r\n\n" +
		"public class "+getClassName()+" extends AuditingEntityListener {\r\n\n" +
		"    private final Logger log = LoggerFactory.getLogger("+getClassName()+".class);\r\n" +
		"    private static BeanFactory beanFactory;\r\n\n" +
		"    @PostPersist\r\n" +
		"    public void onPostCreate(Object target) {\r\n" +
		"        try {\r\n" +
		"            AsyncEntityAuditEventWriter asyncEntityAuditEventWriter = beanFactory.getBean(AsyncEntityAuditEventWriter.class);\r\n" +
		"            asyncEntityAuditEventWriter.writeAuditEvent(target, EntityAuditAction.CREATE);\r\n" +
		"        } catch (NoSuchBeanDefinitionException e) {\r\n" +
		"            log.error(\"No bean found for AsyncEntityAuditEventWriter\");\r\n" +
		"        } catch (Exception e) {\r\n" +
		"            log.error(\"Exception while persisting create audit entity {}\", e);\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"    @PostUpdate\r\n" +
		"    public void onPostUpdate(Object target) {\r\n" +
		"        try {\r\n" +
		"            AsyncEntityAuditEventWriter asyncEntityAuditEventWriter = beanFactory.getBean(AsyncEntityAuditEventWriter.class);\r\n" +
		"            asyncEntityAuditEventWriter.writeAuditEvent(target, EntityAuditAction.UPDATE);\r\n" +
		"        } catch (NoSuchBeanDefinitionException e) {\r\n" +
		"            log.error(\"No bean found for AsyncEntityAuditEventWriter\");\r\n" +
		"        } catch (Exception e) {\r\n" +
		"            log.error(\"Exception while persisting update audit entity {}\", e);\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"    @PostRemove\r\n" +
		"    public void onPostRemove(Object target) {\r\n" +
		"        try {\r\n" +
		"            AsyncEntityAuditEventWriter asyncEntityAuditEventWriter = beanFactory.getBean(AsyncEntityAuditEventWriter.class);\r\n" +
		"            asyncEntityAuditEventWriter.writeAuditEvent(target, EntityAuditAction.DELETE);\r\n" +
		"        } catch (NoSuchBeanDefinitionException e) {\r\n" +
		"            log.error(\"No bean found for AsyncEntityAuditEventWriter\");\r\n" +
		"        } catch (Exception e) {\r\n" +
		"            log.error(\"Exception while persisting delete audit entity {}\", e);\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"    static void setBeanFactory(BeanFactory beanFactory) {\r\n" +
		"        "+getClassName()+".beanFactory = beanFactory;\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "EntityAuditEventListener";
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
