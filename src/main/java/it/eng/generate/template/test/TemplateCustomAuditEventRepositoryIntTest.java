package it.eng.generate.template.test;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateCustomAuditEventRepositoryIntTest extends AbstractTemplate{

	public TemplateCustomAuditEventRepositoryIntTest(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcRepositoryFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcRepositoryFolder()+";\r\n\n" +
		"import "+ conf.getPackageclass() + "." + Utils.getClassNameCamelCase(conf.getProjectName()) + conf.getApp() +";\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcConfigFolder()+".Constants;\r\n"+
		"import "+ conf.getPackageclass() + "." + conf.getSrcConfigAuditFolder()+".AuditEventConverter;\r\n"+
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".PersistentAuditEvent;\r\n"+
		"import org.junit.Before;\r\n" +
		"import org.junit.Test;\r\n" +
		"import org.junit.runner.RunWith;\r\n" +
		"import org.springframework.beans.factory.annotation.Autowired;\r\n" +
		"import org.springframework.boot.actuate.audit.AuditEvent;\r\n" +
		"import org.springframework.boot.test.context.SpringBootTest;\r\n" +
		"import org.springframework.mock.web.MockHttpServletRequest;\r\n" +
		"import org.springframework.mock.web.MockHttpSession;\r\n" +
		"import org.springframework.security.web.authentication.WebAuthenticationDetails;\r\n" +
		"import org.springframework.test.context.junit4.SpringRunner;\r\n" +
		"import org.springframework.transaction.annotation.Transactional;\r\n" +
		"import javax.servlet.http.HttpSession;\r\n" +
		"import java.time.Instant;\r\n" +
		"import java.util.HashMap;\r\n" +
		"import java.util.List;\r\n" +
		"import java.util.Map;\r\n" +
		"import static org.assertj.core.api.Assertions.assertThat;\r\n" +
		"import static "+ conf.getPackageclass() + "." + conf.getSrcRepositoryFolder()+".CustomAuditEventRepository.EVENT_DATA_COLUMN_MAX_LENGTH;\r\n\n"+
		"/**\r\n" +
		" * Test class for the CustomAuditEventRepository class.\r\n" +
		" *\r\n" +
		" * @see CustomAuditEventRepository\r\n" +
		" */\r\n" +
		"@RunWith(SpringRunner.class)\r\n" +
		"@SpringBootTest(classes = "+Utils.getClassNameCamelCase(conf.getProjectName()) + conf.getApp() +".class)\r\n" +
		"@Transactional\r\n" +
		"public class CustomAuditEventRepositoryIntTest {\r\n\n" +
		"    @Autowired\r\n" +
		"    private PersistenceAuditEventRepository persistenceAuditEventRepository;\r\n\n" +
		"    @Autowired\r\n" +
		"    private AuditEventConverter auditEventConverter;\r\n\n" +
		"    private CustomAuditEventRepository customAuditEventRepository;\r\n" +
		"    private PersistentAuditEvent testUserEvent;\r\n" +
		"    private PersistentAuditEvent testOtherUserEvent;\r\n" +
		"    private PersistentAuditEvent testOldUserEvent;\r\n\n" +
		"    @Before\r\n" +
		"    public void setup() {\r\n" +
		"        customAuditEventRepository = new CustomAuditEventRepository(persistenceAuditEventRepository, auditEventConverter);\r\n" +
		"        persistenceAuditEventRepository.deleteAll();\r\n" +
		"        Instant oneHourAgo = Instant.now().minusSeconds(3600);\r\n" +
		"        testUserEvent = new PersistentAuditEvent();\r\n" +
		"        testUserEvent.setPrincipal(\"test-user\");\r\n" +
		"        testUserEvent.setAuditEventType(\"test-type\");\r\n" +
		"        testUserEvent.setAuditEventDate(oneHourAgo);\r\n" +
		"        Map<String, String> data = new HashMap<>();\r\n" +
		"        data.put(\"test-key\", \"test-value\");\r\n" +
		"        testUserEvent.setData(data);\r\n" +
		"        testOldUserEvent = new PersistentAuditEvent();\r\n" +
		"        testOldUserEvent.setPrincipal(\"test-user\");\r\n" +
		"        testOldUserEvent.setAuditEventType(\"test-type\");\r\n" +
		"        testOldUserEvent.setAuditEventDate(oneHourAgo.minusSeconds(10000));\r\n" +
		"        testOtherUserEvent = new PersistentAuditEvent();\r\n" +
		"        testOtherUserEvent.setPrincipal(\"other-test-user\");\r\n" +
		"        testOtherUserEvent.setAuditEventType(\"test-type\");\r\n" +
		"        testOtherUserEvent.setAuditEventDate(oneHourAgo);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void addAuditEvent() {\r\n" +
		"        Map<String, Object> data = new HashMap<>();\r\n" +
		"        data.put(\"test-key\", \"test-value\");\r\n" +
		"        AuditEvent event = new AuditEvent(\"test-user\", \"test-type\", data);\r\n" +
		"        customAuditEventRepository.add(event);\r\n" +
		"        List<PersistentAuditEvent> persistentAuditEvents = persistenceAuditEventRepository.findAll();\r\n" +
		"        assertThat(persistentAuditEvents).hasSize(1);\r\n" +
		"        PersistentAuditEvent persistentAuditEvent = persistentAuditEvents.get(0);\r\n" +
		"        assertThat(persistentAuditEvent.getPrincipal()).isEqualTo(event.getPrincipal());\r\n" +
		"        assertThat(persistentAuditEvent.getAuditEventType()).isEqualTo(event.getType());\r\n" +
		"        assertThat(persistentAuditEvent.getData()).containsKey(\"test-key\");\r\n" +
		"        assertThat(persistentAuditEvent.getData().get(\"test-key\")).isEqualTo(\"test-value\");\r\n" +
		"        assertThat(persistentAuditEvent.getAuditEventDate()).isEqualTo(event.getTimestamp());\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void addAuditEventTruncateLargeData() {\r\n" +
		"        Map<String, Object> data = new HashMap<>();\r\n" +
		"        StringBuilder largeData = new StringBuilder();\r\n" +
		"        for (int i = 0; i < EVENT_DATA_COLUMN_MAX_LENGTH + 10; i++) {\r\n" +
		"            largeData.append(\"a\");\r\n" +
		"        }\r\n" +
		"        data.put(\"test-key\", largeData);\r\n" +
		"        AuditEvent event = new AuditEvent(\"test-user\", \"test-type\", data);\r\n" +
		"        customAuditEventRepository.add(event);\r\n" +
		"        List<PersistentAuditEvent> persistentAuditEvents = persistenceAuditEventRepository.findAll();\r\n" +
		"        assertThat(persistentAuditEvents).hasSize(1);\r\n" +
		"        PersistentAuditEvent persistentAuditEvent = persistentAuditEvents.get(0);\r\n" +
		"        assertThat(persistentAuditEvent.getPrincipal()).isEqualTo(event.getPrincipal());\r\n" +
		"        assertThat(persistentAuditEvent.getAuditEventType()).isEqualTo(event.getType());\r\n" +
		"        assertThat(persistentAuditEvent.getData()).containsKey(\"test-key\");\r\n" +
		"        String actualData = persistentAuditEvent.getData().get(\"test-key\");\r\n" +
		"        assertThat(actualData.length()).isEqualTo(EVENT_DATA_COLUMN_MAX_LENGTH);\r\n" +
		"        assertThat(actualData).isSubstringOf(largeData);\r\n" +
		"        assertThat(persistentAuditEvent.getAuditEventDate()).isEqualTo(event.getTimestamp());\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testAddEventWithWebAuthenticationDetails() {\r\n" +
		"        HttpSession session = new MockHttpSession(null, \"test-session-id\");\r\n" +
		"        MockHttpServletRequest request = new MockHttpServletRequest();\r\n" +
		"        request.setSession(session);\r\n" +
		"        request.setRemoteAddr(\"1.2.3.4\");\r\n" +
		"        WebAuthenticationDetails details = new WebAuthenticationDetails(request);\r\n" +
		"        Map<String, Object> data = new HashMap<>();\r\n" +
		"        data.put(\"test-key\", details);\r\n" +
		"        AuditEvent event = new AuditEvent(\"test-user\", \"test-type\", data);\r\n" +
		"        customAuditEventRepository.add(event);\r\n" +
		"        List<PersistentAuditEvent> persistentAuditEvents = persistenceAuditEventRepository.findAll();\r\n" +
		"        assertThat(persistentAuditEvents).hasSize(1);\r\n" +
		"        PersistentAuditEvent persistentAuditEvent = persistentAuditEvents.get(0);\r\n" +
		"        assertThat(persistentAuditEvent.getData().get(\"remoteAddress\")).isEqualTo(\"1.2.3.4\");\r\n" +
		"        assertThat(persistentAuditEvent.getData().get(\"sessionId\")).isEqualTo(\"test-session-id\");\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testAddEventWithNullData() {\r\n" +
		"        Map<String, Object> data = new HashMap<>();\r\n" +
		"        data.put(\"test-key\", null);\r\n" +
		"        AuditEvent event = new AuditEvent(\"test-user\", \"test-type\", data);\r\n" +
		"        customAuditEventRepository.add(event);\r\n" +
		"        List<PersistentAuditEvent> persistentAuditEvents = persistenceAuditEventRepository.findAll();\r\n" +
		"        assertThat(persistentAuditEvents).hasSize(1);\r\n" +
		"        PersistentAuditEvent persistentAuditEvent = persistentAuditEvents.get(0);\r\n" +
		"        assertThat(persistentAuditEvent.getData().get(\"test-key\")).isEqualTo(\"null\");\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void addAuditEventWithAnonymousUser() {\r\n" +
		"        Map<String, Object> data = new HashMap<>();\r\n" +
		"        data.put(\"test-key\", \"test-value\");\r\n" +
		"        AuditEvent event = new AuditEvent(Constants.ANONYMOUS_USER, \"test-type\", data);\r\n" +
		"        customAuditEventRepository.add(event);\r\n" +
		"        List<PersistentAuditEvent> persistentAuditEvents = persistenceAuditEventRepository.findAll();\r\n" +
		"        assertThat(persistentAuditEvents).hasSize(0);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void addAuditEventWithAuthorizationFailureType() {\r\n" +
		"        Map<String, Object> data = new HashMap<>();\r\n" +
		"        data.put(\"test-key\", \"test-value\");\r\n" +
		"        AuditEvent event = new AuditEvent(\"test-user\", \"AUTHORIZATION_FAILURE\", data);\r\n" +
		"        customAuditEventRepository.add(event);\r\n" +
		"        List<PersistentAuditEvent> persistentAuditEvents = persistenceAuditEventRepository.findAll();\r\n" +
		"        assertThat(persistentAuditEvents).hasSize(0);\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "CustomAuditEventRepositoryIntTest";
	}

	public String getSourceFolder() {
		return "src/test/java";
	}

}
