package it.eng.generate.template.test;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateAuditResourceIntTest extends AbstractTemplate{

	public TemplateAuditResourceIntTest(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcWebRestFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcWebRestFolder()+";\r\n\n" +
		"import " + conf.getPackageclass() + "." + Utils.getClassNameCamelCase(conf.getProjectName()) + conf.getApp()+";\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcConfigAuditFolder()+".AuditEventConverter;\r\n"+
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".PersistentAuditEvent;\r\n"+
		"import "+ conf.getPackageclass() + "." + conf.getSrcRepositoryFolder()+".PersistenceAuditEventRepository;\r\n"+
		"import "+ conf.getPackageclass() + "." + conf.getSrcServiceFolder()+".AuditEventService;\r\n"+
		"import org.junit.Before;\r\n" +
		"import org.junit.Test;\r\n" +
		"import org.junit.runner.RunWith;\r\n" +
		"import org.mockito.MockitoAnnotations;\r\n" +
		"import org.springframework.beans.factory.annotation.Autowired;\r\n" +
		"import org.springframework.boot.test.context.SpringBootTest;\r\n" +
		"import org.springframework.data.web.PageableHandlerMethodArgumentResolver;\r\n" +
		"import org.springframework.format.support.FormattingConversionService;\r\n" +
		"import org.springframework.http.MediaType;\r\n" +
		"import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;\r\n" +
		"import org.springframework.test.context.junit4.SpringRunner;\r\n" +
		"import org.springframework.test.web.servlet.MockMvc;\r\n" +
		"import org.springframework.test.web.servlet.setup.MockMvcBuilders;\r\n" +
		"import org.springframework.transaction.annotation.Transactional;\r\n" +
		"import java.time.Instant;\r\n" +
		"import static org.assertj.core.api.AssertionsForClassTypes.assertThat;\r\n" +
		"import static org.hamcrest.Matchers.hasItem;\r\n" +
		"import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;\r\n" +
		"import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;\r\n" +
		"/**\r\n" +
		" * Test class for the AuditResource REST controller.\r\n" +
		" *\r\n" +
		" * @see AuditResource\r\n" +
		" */\r\n" +
		"@RunWith(SpringRunner.class)\r\n" +
		"@SpringBootTest(classes = "+Utils.getClassNameCamelCase(conf.getProjectName()) + conf.getApp()+".class)\r\n" +
		"@Transactional\r\n" +
		"public class AuditResourceIntTest {\r\n" +
		"    private static final String SAMPLE_PRINCIPAL = \"SAMPLE_PRINCIPAL\";\r\n" +
		"    private static final String SAMPLE_TYPE = \"SAMPLE_TYPE\";\r\n" +
		"    private static final Instant SAMPLE_TIMESTAMP = Instant.parse(\"2015-08-04T10:11:30Z\");\r\n" +
		"    private static final long SECONDS_PER_DAY = 60 * 60 * 24;\r\n" +
		"    @Autowired\r\n" +
		"    private PersistenceAuditEventRepository auditEventRepository;\r\n" +
		"    @Autowired\r\n" +
		"    private AuditEventConverter auditEventConverter;\r\n" +
		"    @Autowired\r\n" +
		"    private MappingJackson2HttpMessageConverter jacksonMessageConverter;\r\n" +
		"    @Autowired\r\n" +
		"    private FormattingConversionService formattingConversionService;\r\n" +
		"    @Autowired\r\n" +
		"    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;\r\n" +
		"    private PersistentAuditEvent auditEvent;\r\n" +
		"    private MockMvc restAuditMockMvc;\r\n" +
		"    @Before\r\n" +
		"    public void setup() {\r\n" +
		"        MockitoAnnotations.initMocks(this);\r\n" +
		"        AuditEventService auditEventService =\r\n" +
		"            new AuditEventService(auditEventRepository, auditEventConverter);\r\n" +
		"        AuditResource auditResource = new AuditResource(auditEventService);\r\n" +
		"        this.restAuditMockMvc = MockMvcBuilders.standaloneSetup(auditResource)\r\n" +
		"            .setCustomArgumentResolvers(pageableArgumentResolver)\r\n" +
		"            .setConversionService(formattingConversionService)\r\n" +
		"            .setMessageConverters(jacksonMessageConverter).build();\r\n" +
		"    }\r\n" +
		"    @Before\r\n" +
		"    public void initTest() {\r\n" +
		"        auditEventRepository.deleteAll();\r\n" +
		"        auditEvent = new PersistentAuditEvent();\r\n" +
		"        auditEvent.setAuditEventType(SAMPLE_TYPE);\r\n" +
		"        auditEvent.setPrincipal(SAMPLE_PRINCIPAL);\r\n" +
		"        auditEvent.setAuditEventDate(SAMPLE_TIMESTAMP);\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    public void getAllAudits() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        auditEventRepository.save(auditEvent);\r\n" +
		"        // Get all the audits\r\n" +
		"        restAuditMockMvc.perform(get(\"/management/audits\"))\r\n" +
		"            .andExpect(status().isOk())\r\n" +
		"            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))\r\n" +
		"            .andExpect(jsonPath(\"$.[*].principal\").value(hasItem(SAMPLE_PRINCIPAL)));\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    public void getAudit() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        auditEventRepository.save(auditEvent);\r\n" +
		"        // Get the audit\r\n" +
		"        restAuditMockMvc.perform(get(\"/management/audits/{id}\", auditEvent.getId()))\r\n" +
		"            .andExpect(status().isOk())\r\n" +
		"            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))\r\n" +
		"            .andExpect(jsonPath(\"$.principal\").value(SAMPLE_PRINCIPAL));\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    public void getAuditsByDate() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        auditEventRepository.save(auditEvent);\r\n" +
		"        // Generate dates for selecting audits by date, making sure the period will contain the audit\r\n" +
		"        String fromDate  = SAMPLE_TIMESTAMP.minusSeconds(SECONDS_PER_DAY).toString().substring(0, 10);\r\n" +
		"        String toDate = SAMPLE_TIMESTAMP.plusSeconds(SECONDS_PER_DAY).toString().substring(0, 10);\r\n" +
		"        // Get the audit\r\n" +
		"        restAuditMockMvc.perform(get(\"/management/audits?fromDate=\"+fromDate+\"&toDate=\"+toDate))\r\n" +
		"            .andExpect(status().isOk())\r\n" +
		"            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))\r\n" +
		"            .andExpect(jsonPath(\"$.[*].principal\").value(hasItem(SAMPLE_PRINCIPAL)));\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    public void getNonExistingAuditsByDate() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        auditEventRepository.save(auditEvent);\r\n" +
		"        // Generate dates for selecting audits by date, making sure the period will not contain the sample audit\r\n" +
		"        String fromDate  = SAMPLE_TIMESTAMP.minusSeconds(2*SECONDS_PER_DAY).toString().substring(0, 10);\r\n" +
		"        String toDate = SAMPLE_TIMESTAMP.minusSeconds(SECONDS_PER_DAY).toString().substring(0, 10);\r\n" +
		"        // Query audits but expect no results\r\n" +
		"        restAuditMockMvc.perform(get(\"/management/audits?fromDate=\" + fromDate + \"&toDate=\" + toDate))\r\n" +
		"            .andExpect(status().isOk())\r\n" +
		"            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))\r\n" +
		"            .andExpect(header().string(\"X-Total-Count\", \"0\"));\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    public void getNonExistingAudit() throws Exception {\r\n" +
		"        // Get the audit\r\n" +
		"        restAuditMockMvc.perform(get(\"/management/audits/{id}\", Long.MAX_VALUE))\r\n" +
		"            .andExpect(status().isNotFound());\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void testPersistentAuditEventEquals() throws Exception {\r\n" +
		"        TestUtil.equalsVerifier(PersistentAuditEvent.class);\r\n" +
		"        PersistentAuditEvent auditEvent1 = new PersistentAuditEvent();\r\n" +
		"        auditEvent1.setId(1L);\r\n" +
		"        PersistentAuditEvent auditEvent2 = new PersistentAuditEvent();\r\n" +
		"        auditEvent2.setId(auditEvent1.getId());\r\n" +
		"        assertThat(auditEvent1).isEqualTo(auditEvent2);\r\n" +
		"        auditEvent2.setId(2L);\r\n" +
		"        assertThat(auditEvent1).isNotEqualTo(auditEvent2);\r\n" +
		"        auditEvent1.setId(null);\r\n" +
		"        assertThat(auditEvent1).isNotEqualTo(auditEvent2);\r\n" +
		"    }\r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "AuditResourceIntTest";
	}

	public String getSourceFolder() {
		return "src/test/java";
	}

}
