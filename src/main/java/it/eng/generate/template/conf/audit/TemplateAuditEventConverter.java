package it.eng.generate.template.conf.audit;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateAuditEventConverter extends AbstractTemplate {

	public TemplateAuditEventConverter(DataBase database) {
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
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder() +".PersistentAuditEvent;\r\n" +
		"import org.springframework.boot.actuate.audit.AuditEvent;\r\n" +
		"import org.springframework.security.web.authentication.WebAuthenticationDetails;\r\n" +
		"import org.springframework.stereotype.Component;\r\n" +
		"import java.util.*;\r\n\n" +
		"@Component\r\n" +
		"public class " + getClassName() + " {\r\n\n" +
		"    /**\r\n" +
		"     * Convert a list of PersistentAuditEvent to a list of AuditEvent\r\n" +
		"     *\r\n" +
		"     * @param persistentAuditEvents the list to convert\r\n" +
		"     * @return the converted list.\r\n" +
		"     */\r\n" +
		"    public List<AuditEvent> convertToAuditEvent(Iterable<PersistentAuditEvent> persistentAuditEvents) {\r\n" +
		"        if (persistentAuditEvents == null) {\r\n" +
		"            return Collections.emptyList();\r\n" +
		"        }\r\n" +
		"        List<AuditEvent> auditEvents = new ArrayList<>();\r\n" +
		"        for (PersistentAuditEvent persistentAuditEvent : persistentAuditEvents) {\r\n" +
		"            auditEvents.add(convertToAuditEvent(persistentAuditEvent));\r\n" +
		"        }\r\n" +
		"        return auditEvents;\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Convert a PersistentAuditEvent to an AuditEvent\r\n" +
		"     *\r\n" +
		"     * @param persistentAuditEvent the event to convert\r\n" +
		"     * @return the converted list.\r\n" +
		"     */\r\n" +
		"    public AuditEvent convertToAuditEvent(PersistentAuditEvent persistentAuditEvent) {\r\n" +
		"        if (persistentAuditEvent == null) {\r\n" +
		"            return null;\r\n" +
		"        }\r\n" +
		"        return new AuditEvent(persistentAuditEvent.getAuditEventDate(), persistentAuditEvent.getPrincipal(),\r\n" +
		"            persistentAuditEvent.getAuditEventType(), convertDataToObjects(persistentAuditEvent.getData()));\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Internal conversion. This is needed to support the current SpringBoot actuator AuditEventRepository interface\r\n" +
		"     *\r\n" +
		"     * @param data the data to convert\r\n" +
		"     * @return a map of String, Object\r\n" +
		"     */\r\n" +
		"    public Map<String, Object> convertDataToObjects(Map<String, String> data) {\r\n" +
		"        Map<String, Object> results = new HashMap<>();\r\n" +
		"        if (data != null) {\r\n" +
		"            for (Map.Entry<String, String> entry : data.entrySet()) {\r\n" +
		"                results.put(entry.getKey(), entry.getValue());\r\n" +
		"            }\r\n" +
		"        }\r\n" +
		"        return results;\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Internal conversion. This method will allow to save additional data.\r\n" +
		"     * By default, it will save the object as string\r\n" +
		"     *\r\n" +
		"     * @param data the data to convert\r\n" +
		"     * @return a map of String, String\r\n" +
		"     */\r\n" +
		"    public Map<String, String> convertDataToStrings(Map<String, Object> data) {\r\n" +
		"        Map<String, String> results = new HashMap<>();\r\n" +
		"        if (data != null) {\r\n" +
		"            for (Map.Entry<String, Object> entry : data.entrySet()) {\r\n" +
		"                // Extract the data that will be saved.\r\n" +
		"                if (entry.getValue() instanceof WebAuthenticationDetails) {\r\n" +
		"                    WebAuthenticationDetails authenticationDetails = (WebAuthenticationDetails) entry.getValue();\r\n" +
		"                    results.put(\"remoteAddress\", authenticationDetails.getRemoteAddress());\r\n" +
		"                    results.put(\"sessionId\", authenticationDetails.getSessionId());\r\n" +
		"                } else {\r\n" +
		"                    results.put(entry.getKey(), Objects.toString(entry.getValue()));\r\n" +
		"                }\r\n" +
		"            }\r\n" +
		"        }\r\n" +
		"        return results;\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "AuditEventConverter";
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
