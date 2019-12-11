package it.eng.generate.template.test;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateIntTest extends AbstractTemplate{
	
	public TemplateIntTest(Table tabella) {
		super(tabella);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcWebRestFolder(),".","/");
		return typeTemplate;
	}
	
	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcWebRestFolder()+";\r\n\n" +
		"import "+conf.getPackageclass()+"."+Utils.getClassNameCamelCase(conf.getProjectName()) + conf.getApp()+";\r\n" +
		"import "+conf.getPackageclass()+".domain."+Utils.getEntityName(tabella)+";\r\n" +
		"import "+conf.getPackageclass()+".repository."+Utils.getEntityName(tabella)+"Repository;\r\n" +
		"import "+conf.getPackageclass()+".service."+Utils.getEntityName(tabella)+"Service;\r\n" +
		"import "+conf.getPackageclass()+".service.dto."+Utils.getEntityName(tabella)+"DTO;\r\n" +
		"import "+conf.getPackageclass()+".service.mapper."+Utils.getEntityName(tabella)+"Mapper;\r\n" +
		"import "+conf.getPackageclass()+".web.rest.errors.ExceptionTranslator;\r\n" +
		"import "+conf.getPackageclass()+".service.dto."+Utils.getEntityName(tabella)+"Criteria;\r\n" +
		"import "+conf.getPackageclass()+".service."+Utils.getEntityName(tabella)+"QueryService;\r\n" +
		"import org.junit.Before;\r\n" +
		"import org.junit.Test;\r\n" +
		"import org.junit.runner.RunWith;\r\n" +
		"import org.mockito.MockitoAnnotations;\r\n" +
		"import org.springframework.beans.factory.annotation.Autowired;\r\n" +
		"import org.springframework.boot.test.context.SpringBootTest;\r\n" +
		"import org.springframework.data.web.PageableHandlerMethodArgumentResolver;\r\n" +
		"import org.springframework.http.MediaType;\r\n" +
		"import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;\r\n" +
		"import org.springframework.test.context.junit4.SpringRunner;\r\n" +
		"import org.springframework.test.web.servlet.MockMvc;\r\n" +
		"import org.springframework.test.web.servlet.setup.MockMvcBuilders;\r\n" +
		"import org.springframework.transaction.annotation.Transactional;\r\n" +
		"import org.springframework.util.Base64Utils;\r\n" +
		"import javax.persistence.EntityManager;\r\n" +
		"import java.time.Instant;\r\n" +
		"import java.time.temporal.ChronoUnit;\r\n" +
		"import java.util.List;\r\n" +
		"import static "+conf.getPackageclass()+".web.rest.TestUtil.createFormattingConversionService;\r\n" +
		"import static org.assertj.core.api.Assertions.assertThat;\r\n" +
		"import static org.hamcrest.Matchers.hasItem;\r\n" +
		"import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;\r\n" +
		"import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;\r\n" +
		"import "+conf.getPackageclass()+"."+conf.getSrcDomainEnumerationFolder()+".*;\r\n\n" +
		"/**\r\n" +
		" * Test class for the "+Utils.getEntityName(tabella)+"Resource REST controller.\r\n" +
		" *\r\n" +
		" * @see "+Utils.getEntityName(tabella)+"Resource\r\n" +
		" */\r\n" +
		"@RunWith(SpringRunner.class)\r\n" +
		"@SpringBootTest(classes = "+Utils.getClassNameCamelCase(conf.getProjectName()) + conf.getApp()+".class)\r\n" +
		"public class "+Utils.getEntityName(tabella)+"ResourceIntTest {\r\n" +
		"    //private static final CanaleNotifica DEFAULT_CANALE_NOTIFICA = CanaleNotifica.MAIL;\r\n" +
		"    //private static final CanaleNotifica UPDATED_CANALE_NOTIFICA = CanaleNotifica.SMS;\r\n" +
		"    //private static final TipoNotifica DEFAULT_TIPO_NOTIFICA = TipoNotifica.MANUALE;\r\n" +
		"    //private static final TipoNotifica UPDATED_TIPO_NOTIFICA = TipoNotifica.AUTOMATICO;\r\n" +
		"    //private static final CategoriaNotifica DEFAULT_CATEGORIA_NOTIFICA = CategoriaNotifica.COMPLEANNO;\r\n" +
		"    //private static final CategoriaNotifica UPDATED_CATEGORIA_NOTIFICA = CategoriaNotifica.FATTURA;\r\n" +
		"    private static final String DEFAULT_OGGETTO_NOTIFICA = \"AAAAAAAAAA\";\r\n" +
		"    private static final String UPDATED_OGGETTO_NOTIFICA = \"BBBBBBBBBB\";\r\n" +
		"    private static final String DEFAULT_TESTO_NOTIFICA = \"AAAAAAAAAA\";\r\n" +
		"    private static final String UPDATED_TESTO_NOTIFICA = \"BBBBBBBBBB\";\r\n" +
		"    private static final String DEFAULT_DESTINATARI_NORIFICA = \"AAAAAAAAAA\";\r\n" +
		"    private static final String UPDATED_DESTINATARI_NORIFICA = \"BBBBBBBBBB\";\r\n" +
		"    //private static final TipoEsito DEFAULT_ESITO_NOTIFICA = TipoEsito.DA_INVIARE;\r\n" +
		"    //private static final TipoEsito UPDATED_ESITO_NOTIFICA = TipoEsito.INVIATA;\r\n" +
		"    private static final Instant DEFAULT_DATA_CREAZIONE = Instant.ofEpochMilli(0L);\r\n" +
		"    private static final Instant UPDATED_DATA_CREAZIONE = Instant.now().truncatedTo(ChronoUnit.MILLIS);\r\n" +
		"    private static final Instant DEFAULT_DATA_MODIFICA = Instant.ofEpochMilli(0L);\r\n" +
		"    private static final Instant UPDATED_DATA_MODIFICA = Instant.now().truncatedTo(ChronoUnit.MILLIS);\r\n" +
		"    private static final String DEFAULT_UTENTE_CREAZIONE = \"AAAAAAAAAA\";\r\n" +
		"    private static final String UPDATED_UTENTE_CREAZIONE = \"BBBBBBBBBB\";\r\n" +
		"    private static final String DEFAULT_UTENTE_MODIFICA = \"AAAAAAAAAA\";\r\n" +
		"    private static final String UPDATED_UTENTE_MODIFICA = \"BBBBBBBBBB\";\r\n\n" +
		"    @Autowired private "+Utils.getEntityName(tabella)+"Repository notificheRepository;\r\n" +
		"    @Autowired private "+Utils.getEntityName(tabella)+"Mapper notificheMapper;\r\n" +
		"    @Autowired private "+Utils.getEntityName(tabella)+"Service notificheService;\r\n" +
		"    @Autowired private "+Utils.getEntityName(tabella)+"QueryService notificheQueryService;\r\n" +
		"    @Autowired private MappingJackson2HttpMessageConverter jacksonMessageConverter;\r\n" +
		"    @Autowired private PageableHandlerMethodArgumentResolver pageableArgumentResolver;\r\n" +
		"    @Autowired private ExceptionTranslator exceptionTranslator;\r\n" +
		"    @Autowired private EntityManager em;\r\n" +
		"    private MockMvc restNotificheMockMvc;\r\n" +
		"    private "+Utils.getEntityName(tabella)+" notifiche;\r\n\n" +
		"    @Before\r\n" +
		"    public void setup() {\r\n\n" +
		"        MockitoAnnotations.initMocks(this);\r\n" +
		"        final "+Utils.getEntityName(tabella)+"Resource notificheResource = new "+Utils.getEntityName(tabella)+"Resource(notificheService, notificheQueryService);\r\n" +
		"        this.restNotificheMockMvc = MockMvcBuilders.standaloneSetup(notificheResource)\r\n" +
		"            .setCustomArgumentResolvers(pageableArgumentResolver)\r\n" +
		"            .setControllerAdvice(exceptionTranslator)\r\n" +
		"            .setConversionService(createFormattingConversionService())\r\n" +
		"            .setMessageConverters(jacksonMessageConverter).build();\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Create an entity for this test.\r\n" +
		"     *\r\n" +
		"     * This is a static method, as tests for other entities might also need it,\r\n" +
		"     * if they test an entity which requires the current entity.\r\n" +
		"     */\r\n" +
		"    public static "+Utils.getEntityName(tabella)+" createEntity(EntityManager em) {\r\n" +
		"        //TODO DEVELOP THIS\r\n" +
		"        "+Utils.getEntityName(tabella)+" notifiche = new "+Utils.getEntityName(tabella)+"()\r\n" +
		"            //.canaleNotifica(DEFAULT_CANALE_NOTIFICA)\r\n" +
		"            //.tipoNotifica(DEFAULT_TIPO_NOTIFICA)\r\n" +
		"            //.categoriaNotifica(DEFAULT_CATEGORIA_NOTIFICA)\r\n" +
		"            //.oggettoNotifica(DEFAULT_OGGETTO_NOTIFICA)\r\n" +
		"            //.testoNotifica(DEFAULT_TESTO_NOTIFICA)\r\n" +
		"            //.destinatariNorifica(DEFAULT_DESTINATARI_NORIFICA)\r\n" +
		"            //.esitoNotifica(DEFAULT_ESITO_NOTIFICA)\r\n" +
		"            //.dataCreazione(DEFAULT_DATA_CREAZIONE)\r\n" +
		"            //.dataModifica(DEFAULT_DATA_MODIFICA)\r\n" +
		"            //.utenteCreazione(DEFAULT_UTENTE_CREAZIONE)\r\n" +
		"            //.utenteModifica(DEFAULT_UTENTE_MODIFICA);\r\n" +
		"            ;\r\n" +
		"        return notifiche;\r\n" +
		"    }\r\n\n" +
		"    @Before\r\n" +
		"    public void initTest() {\r\n" +
		"        notifiche = createEntity(em);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void create"+Utils.getEntityName(tabella)+"() throws Exception {\r\n" +
		"        //TODO DEVELOP THIS\r\n" +
		"        int databaseSizeBeforeCreate = notificheRepository.findAll().size();\r\n" +
		"        // Create the "+Utils.getEntityName(tabella)+"\r\n" +
		"        "+Utils.getEntityName(tabella)+"DTO notificheDTO = notificheMapper.toDto(notifiche);\r\n" +
		"        restNotificheMockMvc.perform(post(\"/api/"+Utils.getEntityName(tabella)+"s\")\r\n" +
		"            .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"            .content(TestUtil.convertObjectToJsonBytes(notificheDTO)))\r\n" +
		"            .andExpect(status().isCreated());\r\n" +
		"        // Validate the Notifiche in the database\r\n" +
		"        List<"+Utils.getEntityName(tabella)+"> notificheList = notificheRepository.findAll();\r\n" +
		"        assertThat(notificheList).hasSize(databaseSizeBeforeCreate + 1);\r\n" +
		"        "+Utils.getEntityName(tabella)+" testNotifiche = notificheList.get(notificheList.size() - 1);\r\n" +
		"        //assertThat(testNotifiche.getCanaleNotifica()).isEqualTo(DEFAULT_CANALE_NOTIFICA);\r\n" +
		"        //assertThat(testNotifiche.getTipoNotifica()).isEqualTo(DEFAULT_TIPO_NOTIFICA);\r\n" +
		"        //assertThat(testNotifiche.getCategoriaNotifica()).isEqualTo(DEFAULT_CATEGORIA_NOTIFICA);\r\n" +
		"        //assertThat(testNotifiche.getOggettoNotifica()).isEqualTo(DEFAULT_OGGETTO_NOTIFICA);\r\n" +
		"        //assertThat(testNotifiche.getTestoNotifica()).isEqualTo(DEFAULT_TESTO_NOTIFICA);\r\n" +
		"        //assertThat(testNotifiche.getDestinatariNorifica()).isEqualTo(DEFAULT_DESTINATARI_NORIFICA);\r\n" +
		"        //assertThat(testNotifiche.getEsitoNotifica()).isEqualTo(DEFAULT_ESITO_NOTIFICA);\r\n" +
		"        //assertThat(testNotifiche.getDataCreazione()).isEqualTo(DEFAULT_DATA_CREAZIONE);\r\n" +
		"        //assertThat(testNotifiche.getDataModifica()).isEqualTo(DEFAULT_DATA_MODIFICA);\r\n" +
		"        //assertThat(testNotifiche.getUtenteCreazione()).isEqualTo(DEFAULT_UTENTE_CREAZIONE);\r\n" +
		"        //assertThat(testNotifiche.getUtenteModifica()).isEqualTo(DEFAULT_UTENTE_MODIFICA);\r\n" +
		"        ;\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void create"+Utils.getEntityName(tabella)+"WithExistingId() throws Exception {\r\n" +
		"        //TODO DEVELOP THIS\r\n" +
		"        int databaseSizeBeforeCreate = notificheRepository.findAll().size();\r\n" +
		"        // Create the "+Utils.getEntityName(tabella)+" with an existing ID\r\n" +
		"        notifiche.setId(1L);\r\n" +
		"        "+Utils.getEntityName(tabella)+"DTO notificheDTO = notificheMapper.toDto(notifiche);\r\n" +
		"        // An entity with an existing ID cannot be created, so this API call must fail\r\n" +
		"        restNotificheMockMvc.perform(post(\"/api/"+Utils.getEntityName(tabella)+"s\")\r\n" +
		"            .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"            .content(TestUtil.convertObjectToJsonBytes(notificheDTO)))\r\n" +
		"            .andExpect(status().isBadRequest());\r\n" +
		"        // Validate the Notifiche in the database\r\n" +
		"        List<"+Utils.getEntityName(tabella)+"> notificheList = notificheRepository.findAll();\r\n" +
		"        assertThat(notificheList).hasSize(databaseSizeBeforeCreate);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void checkDataCreazioneIsRequired() throws Exception {\r\n" +
		"        int databaseSizeBeforeTest = notificheRepository.findAll().size();\r\n" +
		"        // set the field null\r\n" +
		"        //notifiche.setDataCreazione(null);\r\n" +
		"        // Create the "+Utils.getEntityName(tabella)+", which fails.\r\n" +
		"        "+Utils.getEntityName(tabella)+"DTO notificheDTO = notificheMapper.toDto(notifiche);\r\n" +
		"        restNotificheMockMvc.perform(post(\"/api/"+Utils.getEntityName(tabella)+"s\")\r\n" +
		"            .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"            .content(TestUtil.convertObjectToJsonBytes(notificheDTO)))\r\n" +
		"            .andExpect(status().isBadRequest());\r\n" +
		"        List<"+Utils.getEntityName(tabella)+"> notificheList = notificheRepository.findAll();\r\n" +
		"        assertThat(notificheList).hasSize(databaseSizeBeforeTest);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"s() throws Exception {\r\n" +
		"        //TODO DEVELOP THIS\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList\r\n" +
		"        restNotificheMockMvc.perform(get(\"/api/"+Utils.getEntityName(tabella)+"s?sort=id,desc\"))\r\n" +
		"            .andExpect(status().isOk())\r\n" +
		"            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))\r\n" +
		"            .andExpect(jsonPath(\"$.[*].id\").value(hasItem(notifiche.getId().intValue())))\r\n" +
		"            //.andExpect(jsonPath(\"$.[*].canaleNotifica\").value(hasItem(DEFAULT_CANALE_NOTIFICA.toString())))\r\n" +
		"            //.andExpect(jsonPath(\"$.[*].tipoNotifica\").value(hasItem(DEFAULT_TIPO_NOTIFICA.toString())))\r\n" +
		"            //.andExpect(jsonPath(\"$.[*].categoriaNotifica\").value(hasItem(DEFAULT_CATEGORIA_NOTIFICA.toString())))\r\n" +
		"            //.andExpect(jsonPath(\"$.[*].oggettoNotifica\").value(hasItem(DEFAULT_OGGETTO_NOTIFICA.toString())))\r\n" +
		"            //.andExpect(jsonPath(\"$.[*].testoNotifica\").value(hasItem(DEFAULT_TESTO_NOTIFICA.toString())))\r\n" +
		"            //.andExpect(jsonPath(\"$.[*].destinatariNorifica\").value(hasItem(DEFAULT_DESTINATARI_NORIFICA.toString())))\r\n" +
		"            //.andExpect(jsonPath(\"$.[*].esitoNotifica\").value(hasItem(DEFAULT_ESITO_NOTIFICA.toString())))\r\n" +
		"            //.andExpect(jsonPath(\"$.[*].dataCreazione\").value(hasItem(DEFAULT_DATA_CREAZIONE.toString())))\r\n" +
		"            //.andExpect(jsonPath(\"$.[*].dataModifica\").value(hasItem(DEFAULT_DATA_MODIFICA.toString())))\r\n" +
		"            //.andExpect(jsonPath(\"$.[*].utenteCreazione\").value(hasItem(DEFAULT_UTENTE_CREAZIONE.toString())))\r\n" +
		"            //.andExpect(jsonPath(\"$.[*].utenteModifica\").value(hasItem(DEFAULT_UTENTE_MODIFICA.toString())));\r\n" +
		"        ;\r\n" +
		"    }\r\n\n" +
		"    \r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void get"+Utils.getEntityName(tabella)+"() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get the notifiche\r\n" +
		"        restNotificheMockMvc.perform(get(\"/api/"+Utils.getEntityName(tabella)+"s/{id}\", notifiche.getId()))\r\n" +
		"            .andExpect(status().isOk())\r\n" +
		"            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))\r\n" +
		"            //.andExpect(jsonPath(\"$.id\").value(notifiche.getId().intValue()))\r\n" +
		"            //.andExpect(jsonPath(\"$.canaleNotifica\").value(DEFAULT_CANALE_NOTIFICA.toString()))\r\n" +
		"            //.andExpect(jsonPath(\"$.tipoNotifica\").value(DEFAULT_TIPO_NOTIFICA.toString()))\r\n" +
		"            //.andExpect(jsonPath(\"$.categoriaNotifica\").value(DEFAULT_CATEGORIA_NOTIFICA.toString()))\r\n" +
		"            //.andExpect(jsonPath(\"$.oggettoNotifica\").value(DEFAULT_OGGETTO_NOTIFICA.toString()))\r\n" +
		"            //.andExpect(jsonPath(\"$.testoNotifica\").value(DEFAULT_TESTO_NOTIFICA.toString()))\r\n" +
		"            //.andExpect(jsonPath(\"$.destinatariNorifica\").value(DEFAULT_DESTINATARI_NORIFICA.toString()))\r\n" +
		"            //.andExpect(jsonPath(\"$.esitoNotifica\").value(DEFAULT_ESITO_NOTIFICA.toString()))\r\n" +
		"            //.andExpect(jsonPath(\"$.dataCreazione\").value(DEFAULT_DATA_CREAZIONE.toString()))\r\n" +
		"            //.andExpect(jsonPath(\"$.dataModifica\").value(DEFAULT_DATA_MODIFICA.toString()))\r\n" +
		"            //.andExpect(jsonPath(\"$.utenteCreazione\").value(DEFAULT_UTENTE_CREAZIONE.toString()))\r\n" +
		"            //.andExpect(jsonPath(\"$.utenteModifica\").value(DEFAULT_UTENTE_MODIFICA.toString()));\r\n" +
		"        ;\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByCanaleNotificaIsEqualToSomething() throws Exception {\r\n" +
		"        //TODO DEVELOP THIS\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where canaleNotifica equals to DEFAULT_CANALE_NOTIFICA\r\n" +
		"        //default"+Utils.getEntityName(tabella)+"ShouldBeFound(\"canaleNotifica.equals=\" + DEFAULT_CANALE_NOTIFICA);\r\n" +
		"        // Get all the notificheList where canaleNotifica equals to UPDATED_CANALE_NOTIFICA\r\n" +
		"        //default"+Utils.getEntityName(tabella)+"ShouldNotBeFound(\"canaleNotifica.equals=\" + UPDATED_CANALE_NOTIFICA);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByCanaleNotificaIsInShouldWork() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where canaleNotifica in DEFAULT_CANALE_NOTIFICA or UPDATED_CANALE_NOTIFICA\r\n" +
		"        //default"+Utils.getEntityName(tabella)+"ShouldBeFound(\"canaleNotifica.in=\" + DEFAULT_CANALE_NOTIFICA + \",\" + UPDATED_CANALE_NOTIFICA);\r\n" +
		"        // Get all the notificheList where canaleNotifica equals to UPDATED_CANALE_NOTIFICA\r\n" +
		"        //default"+Utils.getEntityName(tabella)+"ShouldNotBeFound(\"canaleNotifica.in=\" + UPDATED_CANALE_NOTIFICA);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByCanaleNotificaIsNullOrNotNull() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where canaleNotifica is not null\r\n" +
		"        //defaultNotificheShouldBeFound(\"canaleNotifica.specified=true\");\r\n" +
		"        // Get all the notificheList where canaleNotifica is null\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"canaleNotifica.specified=false\");\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByTipoNotificaIsEqualToSomething() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where tipoNotifica equals to DEFAULT_TIPO_NOTIFICA\r\n" +
		"        //defaultNotificheShouldBeFound(\"tipoNotifica.equals=\" + DEFAULT_TIPO_NOTIFICA);\r\n" +
		"        // Get all the notificheList where tipoNotifica equals to UPDATED_TIPO_NOTIFICA\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"tipoNotifica.equals=\" + UPDATED_TIPO_NOTIFICA);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByTipoNotificaIsInShouldWork() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where tipoNotifica in DEFAULT_TIPO_NOTIFICA or UPDATED_TIPO_NOTIFICA\r\n" +
		"        //defaultNotificheShouldBeFound(\"tipoNotifica.in=\" + DEFAULT_TIPO_NOTIFICA + \",\" + UPDATED_TIPO_NOTIFICA);\r\n" +
		"        // Get all the notificheList where tipoNotifica equals to UPDATED_TIPO_NOTIFICA\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"tipoNotifica.in=\" + UPDATED_TIPO_NOTIFICA);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByTipoNotificaIsNullOrNotNull() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where tipoNotifica is not null\r\n" +
		"        //defaultNotificheShouldBeFound(\"tipoNotifica.specified=true\");\r\n" +
		"        // Get all the notificheList where tipoNotifica is null\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"tipoNotifica.specified=false\");\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByCategoriaNotificaIsEqualToSomething() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where categoriaNotifica equals to DEFAULT_CATEGORIA_NOTIFICA\r\n" +
		"        //defaultNotificheShouldBeFound(\"categoriaNotifica.equals=\" + DEFAULT_CATEGORIA_NOTIFICA);\r\n" +
		"        // Get all the notificheList where categoriaNotifica equals to UPDATED_CATEGORIA_NOTIFICA\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"categoriaNotifica.equals=\" + UPDATED_CATEGORIA_NOTIFICA);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByCategoriaNotificaIsInShouldWork() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where categoriaNotifica in DEFAULT_CATEGORIA_NOTIFICA or UPDATED_CATEGORIA_NOTIFICA\r\n" +
		"        //defaultNotificheShouldBeFound(\"categoriaNotifica.in=\" + DEFAULT_CATEGORIA_NOTIFICA + \",\" + UPDATED_CATEGORIA_NOTIFICA);\r\n" +
		"        // Get all the notificheList where categoriaNotifica equals to UPDATED_CATEGORIA_NOTIFICA\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"categoriaNotifica.in=\" + UPDATED_CATEGORIA_NOTIFICA);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByCategoriaNotificaIsNullOrNotNull() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where categoriaNotifica is not null\r\n" +
		"        //defaultNotificheShouldBeFound(\"categoriaNotifica.specified=true\");\r\n" +
		"        // Get all the notificheList where categoriaNotifica is null\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"categoriaNotifica.specified=false\");\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByOggettoNotificaIsEqualToSomething() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where oggettoNotifica equals to DEFAULT_OGGETTO_NOTIFICA\r\n" +
		"        //defaultNotificheShouldBeFound(\"oggettoNotifica.equals=\" + DEFAULT_OGGETTO_NOTIFICA);\r\n" +
		"        // Get all the notificheList where oggettoNotifica equals to UPDATED_OGGETTO_NOTIFICA\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"oggettoNotifica.equals=\" + UPDATED_OGGETTO_NOTIFICA);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByOggettoNotificaIsInShouldWork() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where oggettoNotifica in DEFAULT_OGGETTO_NOTIFICA or UPDATED_OGGETTO_NOTIFICA\r\n" +
		"        //defaultNotificheShouldBeFound(\"oggettoNotifica.in=\" + DEFAULT_OGGETTO_NOTIFICA + \",\" + UPDATED_OGGETTO_NOTIFICA);\r\n" +
		"        // Get all the notificheList where oggettoNotifica equals to UPDATED_OGGETTO_NOTIFICA\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"oggettoNotifica.in=\" + UPDATED_OGGETTO_NOTIFICA);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByOggettoNotificaIsNullOrNotNull() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where oggettoNotifica is not null\r\n" +
		"        //defaultNotificheShouldBeFound(\"oggettoNotifica.specified=true\");\r\n" +
		"        // Get all the notificheList where oggettoNotifica is null\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"oggettoNotifica.specified=false\");\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByDestinatariNorificaIsEqualToSomething() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where destinatariNorifica equals to DEFAULT_DESTINATARI_NORIFICA\r\n" +
		"        //defaultNotificheShouldBeFound(\"destinatariNorifica.equals=\" + DEFAULT_DESTINATARI_NORIFICA);\r\n" +
		"        // Get all the notificheList where destinatariNorifica equals to UPDATED_DESTINATARI_NORIFICA\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"destinatariNorifica.equals=\" + UPDATED_DESTINATARI_NORIFICA);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByDestinatariNorificaIsInShouldWork() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where destinatariNorifica in DEFAULT_DESTINATARI_NORIFICA or UPDATED_DESTINATARI_NORIFICA\r\n" +
		"        //defaultNotificheShouldBeFound(\"destinatariNorifica.in=\" + DEFAULT_DESTINATARI_NORIFICA + \",\" + UPDATED_DESTINATARI_NORIFICA);\r\n" +
		"        // Get all the notificheList where destinatariNorifica equals to UPDATED_DESTINATARI_NORIFICA\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"destinatariNorifica.in=\" + UPDATED_DESTINATARI_NORIFICA);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByDestinatariNorificaIsNullOrNotNull() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where destinatariNorifica is not null\r\n" +
		"        //defaultNotificheShouldBeFound(\"destinatariNorifica.specified=true\");\r\n" +
		"        // Get all the notificheList where destinatariNorifica is null\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"destinatariNorifica.specified=false\");\r\n" +
		"    }\r\n" +
		"    @Test\r\n\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByEsitoNotificaIsEqualToSomething() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where esitoNotifica equals to DEFAULT_ESITO_NOTIFICA\r\n" +
		"        //defaultNotificheShouldBeFound(\"esitoNotifica.equals=\" + DEFAULT_ESITO_NOTIFICA);\r\n" +
		"        // Get all the notificheList where esitoNotifica equals to UPDATED_ESITO_NOTIFICA\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"esitoNotifica.equals=\" + UPDATED_ESITO_NOTIFICA);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByEsitoNotificaIsInShouldWork() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where esitoNotifica in DEFAULT_ESITO_NOTIFICA or UPDATED_ESITO_NOTIFICA\r\n" +
		"        //defaultNotificheShouldBeFound(\"esitoNotifica.in=\" + DEFAULT_ESITO_NOTIFICA + \",\" + UPDATED_ESITO_NOTIFICA);\r\n" +
		"        // Get all the notificheList where esitoNotifica equals to UPDATED_ESITO_NOTIFICA\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"esitoNotifica.in=\" + UPDATED_ESITO_NOTIFICA);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByEsitoNotificaIsNullOrNotNull() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where esitoNotifica is not null\r\n" +
		"        //defaultNotificheShouldBeFound(\"esitoNotifica.specified=true\");\r\n" +
		"        // Get all the notificheList where esitoNotifica is null\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"esitoNotifica.specified=false\");\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByDataCreazioneIsEqualToSomething() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where dataCreazione equals to DEFAULT_DATA_CREAZIONE\r\n" +
		"        //defaultNotificheShouldBeFound(\"dataCreazione.equals=\" + DEFAULT_DATA_CREAZIONE);\r\n" +
		"        // Get all the notificheList where dataCreazione equals to UPDATED_DATA_CREAZIONE\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"dataCreazione.equals=\" + UPDATED_DATA_CREAZIONE);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByDataCreazioneIsInShouldWork() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where dataCreazione in DEFAULT_DATA_CREAZIONE or UPDATED_DATA_CREAZIONE\r\n" +
		"        //defaultNotificheShouldBeFound(\"dataCreazione.in=\" + DEFAULT_DATA_CREAZIONE + \",\" + UPDATED_DATA_CREAZIONE);\r\n" +
		"        // Get all the notificheList where dataCreazione equals to UPDATED_DATA_CREAZIONE\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"dataCreazione.in=\" + UPDATED_DATA_CREAZIONE);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByDataCreazioneIsNullOrNotNull() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where dataCreazione is not null\r\n" +
		"        //defaultNotificheShouldBeFound(\"dataCreazione.specified=true\");\r\n" +
		"        // Get all the notificheList where dataCreazione is null\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"dataCreazione.specified=false\");\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByDataModificaIsEqualToSomething() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where dataModifica equals to DEFAULT_DATA_MODIFICA\r\n" +
		"        //defaultNotificheShouldBeFound(\"dataModifica.equals=\" + DEFAULT_DATA_MODIFICA);\r\n" +
		"        // Get all the notificheList where dataModifica equals to UPDATED_DATA_MODIFICA\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"dataModifica.equals=\" + UPDATED_DATA_MODIFICA);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByDataModificaIsInShouldWork() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where dataModifica in DEFAULT_DATA_MODIFICA or UPDATED_DATA_MODIFICA\r\n" +
		"        //defaultNotificheShouldBeFound(\"dataModifica.in=\" + DEFAULT_DATA_MODIFICA + \",\" + UPDATED_DATA_MODIFICA);\r\n" +
		"        // Get all the notificheList where dataModifica equals to UPDATED_DATA_MODIFICA\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"dataModifica.in=\" + UPDATED_DATA_MODIFICA);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByDataModificaIsNullOrNotNull() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where dataModifica is not null\r\n" +
		"        //defaultNotificheShouldBeFound(\"dataModifica.specified=true\");\r\n" +
		"        // Get all the notificheList where dataModifica is null\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"dataModifica.specified=false\");\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByUtenteCreazioneIsEqualToSomething() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where utenteCreazione equals to DEFAULT_UTENTE_CREAZIONE\r\n" +
		"        //defaultNotificheShouldBeFound(\"utenteCreazione.equals=\" + DEFAULT_UTENTE_CREAZIONE);\r\n" +
		"        // Get all the notificheList where utenteCreazione equals to UPDATED_UTENTE_CREAZIONE\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"utenteCreazione.equals=\" + UPDATED_UTENTE_CREAZIONE);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByUtenteCreazioneIsInShouldWork() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where utenteCreazione in DEFAULT_UTENTE_CREAZIONE or UPDATED_UTENTE_CREAZIONE\r\n" +
		"        //defaultNotificheShouldBeFound(\"utenteCreazione.in=\" + DEFAULT_UTENTE_CREAZIONE + \",\" + UPDATED_UTENTE_CREAZIONE);\r\n" +
		"        // Get all the notificheList where utenteCreazione equals to UPDATED_UTENTE_CREAZIONE\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"utenteCreazione.in=\" + UPDATED_UTENTE_CREAZIONE);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByUtenteCreazioneIsNullOrNotNull() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where utenteCreazione is not null\r\n" +
		"        //defaultNotificheShouldBeFound(\"utenteCreazione.specified=true\");\r\n" +
		"        // Get all the notificheList where utenteCreazione is null\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"utenteCreazione.specified=false\");\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByUtenteModificaIsEqualToSomething() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where utenteModifica equals to DEFAULT_UTENTE_MODIFICA\r\n" +
		"        //defaultNotificheShouldBeFound(\"utenteModifica.equals=\" + DEFAULT_UTENTE_MODIFICA);\r\n" +
		"        // Get all the notificheList where utenteModifica equals to UPDATED_UTENTE_MODIFICA\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"utenteModifica.equals=\" + UPDATED_UTENTE_MODIFICA);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByUtenteModificaIsInShouldWork() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where utenteModifica in DEFAULT_UTENTE_MODIFICA or UPDATED_UTENTE_MODIFICA\r\n" +
		"        //defaultNotificheShouldBeFound(\"utenteModifica.in=\" + DEFAULT_UTENTE_MODIFICA + \",\" + UPDATED_UTENTE_MODIFICA);\r\n" +
		"        // Get all the notificheList where utenteModifica equals to UPDATED_UTENTE_MODIFICA\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"utenteModifica.in=\" + UPDATED_UTENTE_MODIFICA);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAll"+Utils.getEntityName(tabella)+"sByUtenteModificaIsNullOrNotNull() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        // Get all the notificheList where utenteModifica is not null\r\n" +
		"        //defaultNotificheShouldBeFound(\"utenteModifica.specified=true\");\r\n" +
		"        // Get all the notificheList where utenteModifica is null\r\n" +
		"        //defaultNotificheShouldNotBeFound(\"utenteModifica.specified=false\");\r\n" +
		"    }\r\n\n" +
		"/**\r\n" +
		"     * Executes the search, and checks that the default entity is returned\r\n" +
		"     */\r\n" +
		"    private void default"+Utils.getEntityName(tabella)+"ShouldBeFound(String filter) throws Exception {\r\n" +
		"        restNotificheMockMvc.perform(get(\"/api/"+Utils.getEntityName(tabella)+"s?sort=id,desc&\" + filter))\r\n" +
		"            .andExpect(status().isOk())\r\n" +
		"            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))\r\n" +
		"            .andExpect(jsonPath(\"$.[*].id\").value(hasItem(notifiche.getId().intValue())))\r\n" +
		"            //.andExpect(jsonPath(\"$.[*].canaleNotifica\").value(hasItem(DEFAULT_CANALE_NOTIFICA.toString())))\r\n" +
		"            //.andExpect(jsonPath(\"$.[*].tipoNotifica\").value(hasItem(DEFAULT_TIPO_NOTIFICA.toString())))\r\n" +
		"            //.andExpect(jsonPath(\"$.[*].categoriaNotifica\").value(hasItem(DEFAULT_CATEGORIA_NOTIFICA.toString())))\r\n" +
		"            //.andExpect(jsonPath(\"$.[*].oggettoNotifica\").value(hasItem(DEFAULT_OGGETTO_NOTIFICA.toString())))\r\n" +
		"            //.andExpect(jsonPath(\"$.[*].testoNotifica\").value(hasItem(DEFAULT_TESTO_NOTIFICA.toString())))\r\n" +
		"            //.andExpect(jsonPath(\"$.[*].destinatariNorifica\").value(hasItem(DEFAULT_DESTINATARI_NORIFICA.toString())))\r\n" +
		"            //.andExpect(jsonPath(\"$.[*].esitoNotifica\").value(hasItem(DEFAULT_ESITO_NOTIFICA.toString())))\r\n" +
		"            //.andExpect(jsonPath(\"$.[*].dataCreazione\").value(hasItem(DEFAULT_DATA_CREAZIONE.toString())))\r\n" +
		"            //.andExpect(jsonPath(\"$.[*].dataModifica\").value(hasItem(DEFAULT_DATA_MODIFICA.toString())))\r\n" +
		"            //.andExpect(jsonPath(\"$.[*].utenteCreazione\").value(hasItem(DEFAULT_UTENTE_CREAZIONE.toString())))\r\n" +
		"            //.andExpect(jsonPath(\"$.[*].utenteModifica\").value(hasItem(DEFAULT_UTENTE_MODIFICA.toString())));\r\n" +
		"        	 ;\r\n" +
		"        // Check, that the count call also returns 1\r\n" +
		"        restNotificheMockMvc.perform(get(\"/api/"+Utils.getEntityName(tabella)+"s/count?sort=id,desc&\" + filter))\r\n" +
		"            .andExpect(status().isOk())\r\n" +
		"            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))\r\n" +
		"            .andExpect(content().string(\"1\"));\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Executes the search, and checks that the default entity is not returned\r\n" +
		"     */\r\n" +
		"    private void default"+Utils.getEntityName(tabella)+"ShouldNotBeFound(String filter) throws Exception {\r\n" +
		"        restNotificheMockMvc.perform(get(\"/api/"+Utils.getEntityName(tabella)+"s?sort=id,desc&\" + filter))\r\n" +
		"            .andExpect(status().isOk())\r\n" +
		"            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))\r\n" +
		"            .andExpect(jsonPath(\"$\").isArray())\r\n" +
		"            .andExpect(jsonPath(\"$\").isEmpty());\r\n" +
		"        // Check, that the count call also returns 0\r\n" +
		"        restNotificheMockMvc.perform(get(\"/api/"+Utils.getEntityName(tabella)+"s/count?sort=id,desc&\" + filter))\r\n" +
		"            .andExpect(status().isOk())\r\n" +
		"            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))\r\n" +
		"            .andExpect(content().string(\"0\"));\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getNonExisting"+Utils.getEntityName(tabella)+"() throws Exception {\r\n" +
		"        // Get the notifiche\r\n" +
		"        restNotificheMockMvc.perform(get(\"/api/"+Utils.getEntityName(tabella)+"s/{id}\", Long.MAX_VALUE))\r\n" +
		"            .andExpect(status().isNotFound());\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void update"+Utils.getEntityName(tabella)+"() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        int databaseSizeBeforeUpdate = notificheRepository.findAll().size();\r\n" +
		"        // Update the notifiche\r\n" +
		"        "+Utils.getEntityName(tabella)+" updatedNotifiche = notificheRepository.findById(notifiche.getId()).get();\r\n" +
		"        // Disconnect from session so that the updates on updatedNotifiche are not directly saved in db\r\n" +
		"        em.detach(updatedNotifiche);\r\n" +
		"        //updatedNotifiche\r\n" +
		"            //.canaleNotifica(UPDATED_CANALE_NOTIFICA)\r\n" +
		"            //.tipoNotifica(UPDATED_TIPO_NOTIFICA)\r\n" +
		"            //.categoriaNotifica(UPDATED_CATEGORIA_NOTIFICA)\r\n" +
		"            //.oggettoNotifica(UPDATED_OGGETTO_NOTIFICA)\r\n" +
		"            //.testoNotifica(UPDATED_TESTO_NOTIFICA)\r\n" +
		"            //.destinatariNorifica(UPDATED_DESTINATARI_NORIFICA)\r\n" +
		"            //.esitoNotifica(UPDATED_ESITO_NOTIFICA)\r\n" +
		"            //.dataCreazione(UPDATED_DATA_CREAZIONE)\r\n" +
		"            //.dataModifica(UPDATED_DATA_MODIFICA)\r\n" +
		"            //.utenteCreazione(UPDATED_UTENTE_CREAZIONE)\r\n" +
		"            //.utenteModifica(UPDATED_UTENTE_MODIFICA);\r\n" +
		"        "+Utils.getEntityName(tabella)+"DTO notificheDTO = notificheMapper.toDto(updatedNotifiche);\r\n" +
		"        restNotificheMockMvc.perform(put(\"/api/"+Utils.getEntityName(tabella)+"s\")\r\n" +
		"            .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"            .content(TestUtil.convertObjectToJsonBytes(notificheDTO)))\r\n" +
		"            .andExpect(status().isOk());\r\n" +
		"        // Validate the "+Utils.getEntityName(tabella)+" in the database\r\n" +
		"        List<"+Utils.getEntityName(tabella)+"> notificheList = notificheRepository.findAll();\r\n" +
		"        assertThat(notificheList).hasSize(databaseSizeBeforeUpdate);\r\n" +
		"        "+Utils.getEntityName(tabella)+" testNotifiche = notificheList.get(notificheList.size() - 1);\r\n" +
		"        //assertThat(testNotifiche.getCanaleNotifica()).isEqualTo(UPDATED_CANALE_NOTIFICA);\r\n" +
		"        //assertThat(testNotifiche.getTipoNotifica()).isEqualTo(UPDATED_TIPO_NOTIFICA);\r\n" +
		"        //assertThat(testNotifiche.getCategoriaNotifica()).isEqualTo(UPDATED_CATEGORIA_NOTIFICA);\r\n" +
		"        //assertThat(testNotifiche.getOggettoNotifica()).isEqualTo(UPDATED_OGGETTO_NOTIFICA);\r\n" +
		"        //assertThat(testNotifiche.getTestoNotifica()).isEqualTo(UPDATED_TESTO_NOTIFICA);\r\n" +
		"        //assertThat(testNotifiche.getDestinatariNorifica()).isEqualTo(UPDATED_DESTINATARI_NORIFICA);\r\n" +
		"        //assertThat(testNotifiche.getEsitoNotifica()).isEqualTo(UPDATED_ESITO_NOTIFICA);\r\n" +
		"        //assertThat(testNotifiche.getDataCreazione()).isEqualTo(UPDATED_DATA_CREAZIONE);\r\n" +
		"        //assertThat(testNotifiche.getDataModifica()).isEqualTo(UPDATED_DATA_MODIFICA);\r\n" +
		"        //assertThat(testNotifiche.getUtenteCreazione()).isEqualTo(UPDATED_UTENTE_CREAZIONE);\r\n" +
		"        //assertThat(testNotifiche.getUtenteModifica()).isEqualTo(UPDATED_UTENTE_MODIFICA);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void updateNonExisting"+Utils.getEntityName(tabella)+"() throws Exception {\r\n" +
		"        int databaseSizeBeforeUpdate = notificheRepository.findAll().size();\r\n" +
		"        // Create the "+Utils.getEntityName(tabella)+"\r\n" +
		"        "+Utils.getEntityName(tabella)+"DTO notificheDTO = notificheMapper.toDto(notifiche);\r\n" +
		"        // If the entity doesn't have an ID, it will throw BadRequestAlertException\r\n" +
		"        restNotificheMockMvc.perform(put(\"/api/"+Utils.getEntityName(tabella)+"s\")\r\n" +
		"            .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"            .content(TestUtil.convertObjectToJsonBytes(notificheDTO)))\r\n" +
		"            .andExpect(status().isBadRequest());\r\n" +
		"        // Validate the Notifiche in the database\r\n" +
		"        List<"+Utils.getEntityName(tabella)+"> notificheList = notificheRepository.findAll();\r\n" +
		"        assertThat(notificheList).hasSize(databaseSizeBeforeUpdate);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void delete"+Utils.getEntityName(tabella)+"() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        notificheRepository.saveAndFlush(notifiche);\r\n" +
		"        int databaseSizeBeforeDelete = notificheRepository.findAll().size();\r\n" +
		"        // Get the "+Utils.getEntityName(tabella)+"\r\n" +
		"        restNotificheMockMvc.perform(delete(\"/api/"+Utils.getEntityName(tabella)+"s/{id}\", notifiche.getId())\r\n" +
		"            .accept(TestUtil.APPLICATION_JSON_UTF8))\r\n" +
		"            .andExpect(status().isOk());\r\n" +
		"        // Validate the database is empty\r\n" +
		"        List<"+Utils.getEntityName(tabella)+"> notificheList = notificheRepository.findAll();\r\n" +
		"        assertThat(notificheList).hasSize(databaseSizeBeforeDelete - 1);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void equalsVerifier() throws Exception {\r\n" +
		"        TestUtil.equalsVerifier("+Utils.getEntityName(tabella)+".class);\r\n" +
		"        "+Utils.getEntityName(tabella)+" notifiche1 = new "+Utils.getEntityName(tabella)+"();\r\n" +
		"        notifiche1.setId(1L);\r\n" +
		"        "+Utils.getEntityName(tabella)+" notifiche2 = new "+Utils.getEntityName(tabella)+"();\r\n" +
		"        notifiche2.setId(notifiche1.getId());\r\n" +
		"        assertThat(notifiche1).isEqualTo(notifiche2);\r\n" +
		"        notifiche2.setId(2L);\r\n" +
		"        assertThat(notifiche1).isNotEqualTo(notifiche2);\r\n" +
		"        notifiche1.setId(null);\r\n" +
		"        assertThat(notifiche1).isNotEqualTo(notifiche2);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void dtoEqualsVerifier() throws Exception {\r\n" +
		"        TestUtil.equalsVerifier("+Utils.getEntityName(tabella)+"DTO.class);\r\n" +
		"        "+Utils.getEntityName(tabella)+"DTO notificheDTO1 = new "+Utils.getEntityName(tabella)+"DTO();\r\n" +
		"        notificheDTO1.setId(1L);\r\n" +
		"        "+Utils.getEntityName(tabella)+"DTO notificheDTO2 = new "+Utils.getEntityName(tabella)+"DTO();\r\n" +
		"        assertThat(notificheDTO1).isNotEqualTo(notificheDTO2);\r\n" +
		"        notificheDTO2.setId(notificheDTO1.getId());\r\n" +
		"        assertThat(notificheDTO1).isEqualTo(notificheDTO2);\r\n" +
		"        notificheDTO2.setId(2L);\r\n" +
		"        assertThat(notificheDTO1).isNotEqualTo(notificheDTO2);\r\n" +
		"        notificheDTO1.setId(null);\r\n" +
		"        assertThat(notificheDTO1).isNotEqualTo(notificheDTO2);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void testEntityFromId() {\r\n" +
		"        assertThat(notificheMapper.fromId(42L).getId()).isEqualTo(42);\r\n" +
		"        assertThat(notificheMapper.fromId(null)).isNull();\r\n" +
		"    }\r\n\n"+
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return Utils.getEntityName(tabella)+"ResourceIntTest";
	}
	
	public String getSourceFolder() {
		return "src/test/java";
	}

}
