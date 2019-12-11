package it.eng.generate.template.test;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateUserResourceIntTest extends AbstractTemplate{

	public TemplateUserResourceIntTest(DataBase database) {
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
		"import "+conf.getPackageclass()+"."+Utils.getClassNameCamelCase(conf.getProjectName()) + conf.getApp()+";\r\n" +
		"import "+conf.getPackageclass()+".domain.Authority;\r\n" +
		"import "+conf.getPackageclass()+".domain.User;\r\n" +
		"import "+conf.getPackageclass()+".repository.UserRepository;\r\n" +
		"import "+conf.getPackageclass()+".security.AuthoritiesConstants;\r\n" +
		"import "+conf.getPackageclass()+".service.MailService;\r\n" +
		"import "+conf.getPackageclass()+".service.UserService;\r\n" +
		"import "+conf.getPackageclass()+".service.dto.UserDTO;\r\n" +
		"import "+conf.getPackageclass()+".service.mapper.UserMapper;\r\n" +
		"import "+conf.getPackageclass()+".web.rest.errors.ExceptionTranslator;\r\n" +
		"import "+conf.getPackageclass()+".web.rest.vm.ManagedUserVM;\r\n" +
		"import org.apache.commons.lang3.RandomStringUtils;\r\n" +
		"import org.junit.Before;\r\n" +
		"import org.junit.Test;\r\n" +
		"import org.junit.runner.RunWith;\r\n" +
		"import org.springframework.beans.factory.annotation.Autowired;\r\n" +
		"import org.springframework.boot.test.context.SpringBootTest;\r\n" +
		"import org.springframework.data.web.PageableHandlerMethodArgumentResolver;\r\n" +
		"import org.springframework.http.MediaType;\r\n" +
		"import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;\r\n" +
		"import org.springframework.test.context.junit4.SpringRunner;\r\n" +
		"import org.springframework.test.web.servlet.MockMvc;\r\n" +
		"import org.springframework.test.web.servlet.setup.MockMvcBuilders;\r\n" +
		"import org.springframework.transaction.annotation.Transactional;\r\n" +
		"import javax.persistence.EntityManager;\r\n" +
		"import java.time.Instant;\r\n" +
		"import java.util.*;\r\n" +
		"import static org.assertj.core.api.Assertions.assertThat;\r\n" +
		"import static org.hamcrest.Matchers.hasItems;\r\n" +
		"import static org.hamcrest.Matchers.hasItem;\r\n" +
		"import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;\r\n" +
		"import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;\r\n\n" +
		"/**\r\n" +
		" * Test class for the UserResource REST controller.\r\n" +
		" *\r\n" +
		" * @see UserResource\r\n" +
		" */\r\n" +
		"@RunWith(SpringRunner.class)\r\n" +
		"@SpringBootTest(classes = "+Utils.getClassNameCamelCase(conf.getProjectName()) + conf.getApp()+".class)\r\n" +
		"public class UserResourceIntTest {\r\n\n" +
		"    private static final String DEFAULT_LOGIN = \"johndoe\";\r\n" +
		"    private static final String UPDATED_LOGIN = \"jhipster\";\r\n" +
		"    private static final Long DEFAULT_ID = 1L;\r\n" +
		"    private static final String DEFAULT_PASSWORD = \"passjohndoe\";\r\n" +
		"    private static final String UPDATED_PASSWORD = \"passjhipster\";\r\n" +
		"    private static final String DEFAULT_EMAIL = \"johndoe@localhost\";\r\n" +
		"    private static final String UPDATED_EMAIL = \"jhipster@localhost\";\r\n" +
		"    private static final String DEFAULT_FIRSTNAME = \"john\";\r\n" +
		"    private static final String UPDATED_FIRSTNAME = \"jhipsterFirstName\";\r\n" +
		"    private static final String DEFAULT_LASTNAME = \"doe\";\r\n" +
		"    private static final String UPDATED_LASTNAME = \"jhipsterLastName\";\r\n" +
		"    private static final String DEFAULT_IMAGEURL = \"http://placehold.it/50x50\";\r\n" +
		"    private static final String UPDATED_IMAGEURL = \"http://placehold.it/40x40\";\r\n" +
		"    private static final String DEFAULT_LANGKEY = \"en\";\r\n" +
		"    private static final String UPDATED_LANGKEY = \"fr\";\r\n\n" +
		"    @Autowired\r\n" +
		"    private UserRepository userRepository;\r\n" +
		"    @Autowired\r\n" +
		"    private MailService mailService;\r\n" +
		"    @Autowired\r\n" +
		"    private UserService userService;\r\n" +
		"    @Autowired\r\n" +
		"    private UserMapper userMapper;\r\n" +
		"    @Autowired\r\n" +
		"    private MappingJackson2HttpMessageConverter jacksonMessageConverter;\r\n" +
		"    @Autowired\r\n" +
		"    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;\r\n" +
		"    @Autowired\r\n" +
		"    private ExceptionTranslator exceptionTranslator;\r\n" +
		"    @Autowired\r\n" +
		"    private EntityManager em;\r\n" +
		"    private MockMvc restUserMockMvc;\r\n" +
		"    private User user;\r\n" +
		"    @Before\r\n" +
		"    public void setup() {\r\n" +
		"        UserResource userResource = new UserResource(userService, userRepository, mailService);\r\n" +
		"        this.restUserMockMvc = MockMvcBuilders.standaloneSetup(userResource)\r\n" +
		"            .setCustomArgumentResolvers(pageableArgumentResolver)\r\n" +
		"            .setControllerAdvice(exceptionTranslator)\r\n" +
		"            .setMessageConverters(jacksonMessageConverter)\r\n" +
		"            .build();\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Create a User.\r\n" +
		"     *\r\n" +
		"     * This is a static method, as tests for other entities might also need it,\r\n" +
		"     * if they test an entity which has a required relationship to the User entity.\r\n" +
		"     */\r\n" +
		"    public static User createEntity(EntityManager em) {\r\n" +
		"        User user = new User();\r\n" +
		"        user.setLogin(DEFAULT_LOGIN + RandomStringUtils.randomAlphabetic(5));\r\n" +
		"        user.setPassword(RandomStringUtils.random(60));\r\n" +
		"        user.setActivated(true);\r\n" +
		"        user.setEmail(RandomStringUtils.randomAlphabetic(5) + DEFAULT_EMAIL);\r\n" +
		"        user.setFirstName(DEFAULT_FIRSTNAME);\r\n" +
		"        user.setLastName(DEFAULT_LASTNAME);\r\n" +
		"        user.setImageUrl(DEFAULT_IMAGEURL);\r\n" +
		"        user.setLangKey(DEFAULT_LANGKEY);\r\n" +
		"        return user;\r\n" +
		"    }\r\n\n" +
		"    @Before\r\n" +
		"    public void initTest() {\r\n" +
		"        user = createEntity(em);\r\n" +
		"        user.setLogin(DEFAULT_LOGIN);\r\n" +
		"        user.setEmail(DEFAULT_EMAIL);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void createUser() throws Exception {\r\n" +
		"        int databaseSizeBeforeCreate = userRepository.findAll().size();\r\n" +
		"        // Create the User\r\n" +
		"        ManagedUserVM managedUserVM = new ManagedUserVM();\r\n" +
		"        managedUserVM.setLogin(DEFAULT_LOGIN);\r\n" +
		"        managedUserVM.setPassword(DEFAULT_PASSWORD);\r\n" +
		"        managedUserVM.setFirstName(DEFAULT_FIRSTNAME);\r\n" +
		"        managedUserVM.setLastName(DEFAULT_LASTNAME);\r\n" +
		"        managedUserVM.setEmail(DEFAULT_EMAIL);\r\n" +
		"        managedUserVM.setActivated(true);\r\n" +
		"        managedUserVM.setImageUrl(DEFAULT_IMAGEURL);\r\n" +
		"        managedUserVM.setLangKey(DEFAULT_LANGKEY);\r\n" +
		"        managedUserVM.setAuthorities(Collections.singleton(AuthoritiesConstants.USER));\r\n" +
		"        restUserMockMvc.perform(post(\"/api/users\")\r\n" +
		"            .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"            .content(TestUtil.convertObjectToJsonBytes(managedUserVM)))\r\n" +
		"            .andExpect(status().isCreated());\r\n" +
		"        // Validate the User in the database\r\n" +
		"        List<User> userList = userRepository.findAll();\r\n" +
		"        assertThat(userList).hasSize(databaseSizeBeforeCreate + 1);\r\n" +
		"        User testUser = userList.get(userList.size() - 1);\r\n" +
		"        assertThat(testUser.getLogin()).isEqualTo(DEFAULT_LOGIN);\r\n" +
		"        assertThat(testUser.getFirstName()).isEqualTo(DEFAULT_FIRSTNAME);\r\n" +
		"        assertThat(testUser.getLastName()).isEqualTo(DEFAULT_LASTNAME);\r\n" +
		"        assertThat(testUser.getEmail()).isEqualTo(DEFAULT_EMAIL);\r\n" +
		"        assertThat(testUser.getImageUrl()).isEqualTo(DEFAULT_IMAGEURL);\r\n" +
		"        assertThat(testUser.getLangKey()).isEqualTo(DEFAULT_LANGKEY);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void createUserWithExistingId() throws Exception {\r\n" +
		"        int databaseSizeBeforeCreate = userRepository.findAll().size();\r\n" +
		"        ManagedUserVM managedUserVM = new ManagedUserVM();\r\n" +
		"        managedUserVM.setId(1L);\r\n" +
		"        managedUserVM.setLogin(DEFAULT_LOGIN);\r\n" +
		"        managedUserVM.setPassword(DEFAULT_PASSWORD);\r\n" +
		"        managedUserVM.setFirstName(DEFAULT_FIRSTNAME);\r\n" +
		"        managedUserVM.setLastName(DEFAULT_LASTNAME);\r\n" +
		"        managedUserVM.setEmail(DEFAULT_EMAIL);\r\n" +
		"        managedUserVM.setActivated(true);\r\n" +
		"        managedUserVM.setImageUrl(DEFAULT_IMAGEURL);\r\n" +
		"        managedUserVM.setLangKey(DEFAULT_LANGKEY);\r\n" +
		"        managedUserVM.setAuthorities(Collections.singleton(AuthoritiesConstants.USER));\r\n" +
		"        // An entity with an existing ID cannot be created, so this API call must fail\r\n" +
		"        restUserMockMvc.perform(post(\"/api/users\")\r\n" +
		"            .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"            .content(TestUtil.convertObjectToJsonBytes(managedUserVM)))\r\n" +
		"            .andExpect(status().isBadRequest());\r\n" +
		"        // Validate the User in the database\r\n" +
		"        List<User> userList = userRepository.findAll();\r\n" +
		"        assertThat(userList).hasSize(databaseSizeBeforeCreate);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void createUserWithExistingLogin() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        userRepository.saveAndFlush(user);\r\n" +
		"        int databaseSizeBeforeCreate = userRepository.findAll().size();\r\n" +
		"        ManagedUserVM managedUserVM = new ManagedUserVM();\r\n" +
		"        managedUserVM.setLogin(DEFAULT_LOGIN);// this login should already be used\r\n" +
		"        managedUserVM.setPassword(DEFAULT_PASSWORD);\r\n" +
		"        managedUserVM.setFirstName(DEFAULT_FIRSTNAME);\r\n" +
		"        managedUserVM.setLastName(DEFAULT_LASTNAME);\r\n" +
		"        managedUserVM.setEmail(\"anothermail@localhost\");\r\n" +
		"        managedUserVM.setActivated(true);\r\n" +
		"        managedUserVM.setImageUrl(DEFAULT_IMAGEURL);\r\n" +
		"        managedUserVM.setLangKey(DEFAULT_LANGKEY);\r\n" +
		"        managedUserVM.setAuthorities(Collections.singleton(AuthoritiesConstants.USER));\r\n" +
		"        // Create the User\r\n" +
		"        restUserMockMvc.perform(post(\"/api/users\")\r\n" +
		"            .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"            .content(TestUtil.convertObjectToJsonBytes(managedUserVM)))\r\n" +
		"            .andExpect(status().isBadRequest());\r\n" +
		"        // Validate the User in the database\r\n" +
		"        List<User> userList = userRepository.findAll();\r\n" +
		"        assertThat(userList).hasSize(databaseSizeBeforeCreate);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void createUserWithExistingEmail() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        userRepository.saveAndFlush(user);\r\n" +
		"        int databaseSizeBeforeCreate = userRepository.findAll().size();\r\n" +
		"        ManagedUserVM managedUserVM = new ManagedUserVM();\r\n" +
		"        managedUserVM.setLogin(\"anotherlogin\");\r\n" +
		"        managedUserVM.setPassword(DEFAULT_PASSWORD);\r\n" +
		"        managedUserVM.setFirstName(DEFAULT_FIRSTNAME);\r\n" +
		"        managedUserVM.setLastName(DEFAULT_LASTNAME);\r\n" +
		"        managedUserVM.setEmail(DEFAULT_EMAIL);// this email should already be used\r\n" +
		"        managedUserVM.setActivated(true);\r\n" +
		"        managedUserVM.setImageUrl(DEFAULT_IMAGEURL);\r\n" +
		"        managedUserVM.setLangKey(DEFAULT_LANGKEY);\r\n" +
		"        managedUserVM.setAuthorities(Collections.singleton(AuthoritiesConstants.USER));\r\n" +
		"        // Create the User\r\n" +
		"        restUserMockMvc.perform(post(\"/api/users\")\r\n" +
		"            .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"            .content(TestUtil.convertObjectToJsonBytes(managedUserVM)))\r\n" +
		"            .andExpect(status().isBadRequest());\r\n" +
		"        // Validate the User in the database\r\n" +
		"        List<User> userList = userRepository.findAll();\r\n" +
		"        assertThat(userList).hasSize(databaseSizeBeforeCreate);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAllUsers() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        userRepository.saveAndFlush(user);\r\n" +
		"        // Get all the users\r\n" +
		"        restUserMockMvc.perform(get(\"/api/users?sort=id,desc\")\r\n" +
		"            .accept(MediaType.APPLICATION_JSON))\r\n" +
		"            .andExpect(status().isOk())\r\n" +
		"            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))\r\n" +
		"            .andExpect(jsonPath(\"$.[*].login\").value(hasItem(DEFAULT_LOGIN)))\r\n" +
		"            .andExpect(jsonPath(\"$.[*].firstName\").value(hasItem(DEFAULT_FIRSTNAME)))\r\n" +
		"            .andExpect(jsonPath(\"$.[*].lastName\").value(hasItem(DEFAULT_LASTNAME)))\r\n" +
		"            .andExpect(jsonPath(\"$.[*].email\").value(hasItem(DEFAULT_EMAIL)))\r\n" +
		"            .andExpect(jsonPath(\"$.[*].imageUrl\").value(hasItem(DEFAULT_IMAGEURL)))\r\n" +
		"            .andExpect(jsonPath(\"$.[*].langKey\").value(hasItem(DEFAULT_LANGKEY)));\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getUser() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        userRepository.saveAndFlush(user);\r\n" +
		"        // Get the user\r\n" +
		"        restUserMockMvc.perform(get(\"/api/users/{login}\", user.getLogin()))\r\n" +
		"            .andExpect(status().isOk())\r\n" +
		"            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))\r\n" +
		"            .andExpect(jsonPath(\"$.login\").value(user.getLogin()))\r\n" +
		"            .andExpect(jsonPath(\"$.firstName\").value(DEFAULT_FIRSTNAME))\r\n" +
		"            .andExpect(jsonPath(\"$.lastName\").value(DEFAULT_LASTNAME))\r\n" +
		"            .andExpect(jsonPath(\"$.email\").value(DEFAULT_EMAIL))\r\n" +
		"            .andExpect(jsonPath(\"$.imageUrl\").value(DEFAULT_IMAGEURL))\r\n" +
		"            .andExpect(jsonPath(\"$.langKey\").value(DEFAULT_LANGKEY));\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getNonExistingUser() throws Exception {\r\n" +
		"        restUserMockMvc.perform(get(\"/api/users/unknown\"))\r\n" +
		"            .andExpect(status().isNotFound());\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void updateUser() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        userRepository.saveAndFlush(user);\r\n" +
		"        int databaseSizeBeforeUpdate = userRepository.findAll().size();\r\n" +
		"        // Update the user\r\n" +
		"        User updatedUser = userRepository.findById(user.getId()).get();\r\n" +
		"        ManagedUserVM managedUserVM = new ManagedUserVM();\r\n" +
		"        managedUserVM.setId(updatedUser.getId());\r\n" +
		"        managedUserVM.setLogin(updatedUser.getLogin());\r\n" +
		"        managedUserVM.setPassword(UPDATED_PASSWORD);\r\n" +
		"        managedUserVM.setFirstName(UPDATED_FIRSTNAME);\r\n" +
		"        managedUserVM.setLastName(UPDATED_LASTNAME);\r\n" +
		"        managedUserVM.setEmail(UPDATED_EMAIL);\r\n" +
		"        managedUserVM.setActivated(updatedUser.getActivated());\r\n" +
		"        managedUserVM.setImageUrl(UPDATED_IMAGEURL);\r\n" +
		"        managedUserVM.setLangKey(UPDATED_LANGKEY);\r\n" +
		"        managedUserVM.setCreatedBy(updatedUser.getCreatedBy());\r\n" +
		"        managedUserVM.setCreatedDate(updatedUser.getCreatedDate());\r\n" +
		"        managedUserVM.setLastModifiedBy(updatedUser.getLastModifiedBy());\r\n" +
		"        managedUserVM.setLastModifiedDate(updatedUser.getLastModifiedDate());\r\n" +
		"        managedUserVM.setAuthorities(Collections.singleton(AuthoritiesConstants.USER));\r\n" +
		"        restUserMockMvc.perform(put(\"/api/users\")\r\n" +
		"            .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"            .content(TestUtil.convertObjectToJsonBytes(managedUserVM)))\r\n" +
		"            .andExpect(status().isOk());\r\n" +
		"        // Validate the User in the database\r\n" +
		"        List<User> userList = userRepository.findAll();\r\n" +
		"        assertThat(userList).hasSize(databaseSizeBeforeUpdate);\r\n" +
		"        User testUser = userList.get(userList.size() - 1);\r\n" +
		"        assertThat(testUser.getFirstName()).isEqualTo(UPDATED_FIRSTNAME);\r\n" +
		"        assertThat(testUser.getLastName()).isEqualTo(UPDATED_LASTNAME);\r\n" +
		"        assertThat(testUser.getEmail()).isEqualTo(UPDATED_EMAIL);\r\n" +
		"        assertThat(testUser.getImageUrl()).isEqualTo(UPDATED_IMAGEURL);\r\n" +
		"        assertThat(testUser.getLangKey()).isEqualTo(UPDATED_LANGKEY);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void updateUserLogin() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        userRepository.saveAndFlush(user);\r\n" +
		"        int databaseSizeBeforeUpdate = userRepository.findAll().size();\r\n" +
		"        // Update the user\r\n" +
		"        User updatedUser = userRepository.findById(user.getId()).get();\r\n" +
		"        ManagedUserVM managedUserVM = new ManagedUserVM();\r\n" +
		"        managedUserVM.setId(updatedUser.getId());\r\n" +
		"        managedUserVM.setLogin(UPDATED_LOGIN);\r\n" +
		"        managedUserVM.setPassword(UPDATED_PASSWORD);\r\n" +
		"        managedUserVM.setFirstName(UPDATED_FIRSTNAME);\r\n" +
		"        managedUserVM.setLastName(UPDATED_LASTNAME);\r\n" +
		"        managedUserVM.setEmail(UPDATED_EMAIL);\r\n" +
		"        managedUserVM.setActivated(updatedUser.getActivated());\r\n" +
		"        managedUserVM.setImageUrl(UPDATED_IMAGEURL);\r\n" +
		"        managedUserVM.setLangKey(UPDATED_LANGKEY);\r\n" +
		"        managedUserVM.setCreatedBy(updatedUser.getCreatedBy());\r\n" +
		"        managedUserVM.setCreatedDate(updatedUser.getCreatedDate());\r\n" +
		"        managedUserVM.setLastModifiedBy(updatedUser.getLastModifiedBy());\r\n" +
		"        managedUserVM.setLastModifiedDate(updatedUser.getLastModifiedDate());\r\n" +
		"        managedUserVM.setAuthorities(Collections.singleton(AuthoritiesConstants.USER));\r\n" +
		"        restUserMockMvc.perform(put(\"/api/users\")\r\n" +
		"            .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"            .content(TestUtil.convertObjectToJsonBytes(managedUserVM)))\r\n" +
		"            .andExpect(status().isOk());\r\n" +
		"        // Validate the User in the database\r\n" +
		"        List<User> userList = userRepository.findAll();\r\n" +
		"        assertThat(userList).hasSize(databaseSizeBeforeUpdate);\r\n" +
		"        User testUser = userList.get(userList.size() - 1);\r\n" +
		"        assertThat(testUser.getLogin()).isEqualTo(UPDATED_LOGIN);\r\n" +
		"        assertThat(testUser.getFirstName()).isEqualTo(UPDATED_FIRSTNAME);\r\n" +
		"        assertThat(testUser.getLastName()).isEqualTo(UPDATED_LASTNAME);\r\n" +
		"        assertThat(testUser.getEmail()).isEqualTo(UPDATED_EMAIL);\r\n" +
		"        assertThat(testUser.getImageUrl()).isEqualTo(UPDATED_IMAGEURL);\r\n" +
		"        assertThat(testUser.getLangKey()).isEqualTo(UPDATED_LANGKEY);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void updateUserExistingEmail() throws Exception {\r\n" +
		"        // Initialize the database with 2 users\r\n" +
		"        userRepository.saveAndFlush(user);\r\n" +
		"        User anotherUser = new User();\r\n" +
		"        anotherUser.setLogin(\"jhipster\");\r\n" +
		"        anotherUser.setPassword(RandomStringUtils.random(60));\r\n" +
		"        anotherUser.setActivated(true);\r\n" +
		"        anotherUser.setEmail(\"jhipster@localhost\");\r\n" +
		"        anotherUser.setFirstName(\"java\");\r\n" +
		"        anotherUser.setLastName(\"hipster\");\r\n" +
		"        anotherUser.setImageUrl(\"\");\r\n" +
		"        anotherUser.setLangKey(\"en\");\r\n" +
		"        userRepository.saveAndFlush(anotherUser);\r\n" +
		"        // Update the user\r\n" +
		"        User updatedUser = userRepository.findById(user.getId()).get();\r\n" +
		"        ManagedUserVM managedUserVM = new ManagedUserVM();\r\n" +
		"        managedUserVM.setId(updatedUser.getId());\r\n" +
		"        managedUserVM.setLogin(updatedUser.getLogin());\r\n" +
		"        managedUserVM.setPassword(updatedUser.getPassword());\r\n" +
		"        managedUserVM.setFirstName(updatedUser.getFirstName());\r\n" +
		"        managedUserVM.setLastName(updatedUser.getLastName());\r\n" +
		"        managedUserVM.setEmail(\"jhipster@localhost\");// this email should already be used by anotherUser\r\n" +
		"        managedUserVM.setActivated(updatedUser.getActivated());\r\n" +
		"        managedUserVM.setImageUrl(updatedUser.getImageUrl());\r\n" +
		"        managedUserVM.setLangKey(updatedUser.getLangKey());\r\n" +
		"        managedUserVM.setCreatedBy(updatedUser.getCreatedBy());\r\n" +
		"        managedUserVM.setCreatedDate(updatedUser.getCreatedDate());\r\n" +
		"        managedUserVM.setLastModifiedBy(updatedUser.getLastModifiedBy());\r\n" +
		"        managedUserVM.setLastModifiedDate(updatedUser.getLastModifiedDate());\r\n" +
		"        managedUserVM.setAuthorities(Collections.singleton(AuthoritiesConstants.USER));\r\n" +
		"        restUserMockMvc.perform(put(\"/api/users\")\r\n" +
		"            .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"            .content(TestUtil.convertObjectToJsonBytes(managedUserVM)))\r\n" +
		"            .andExpect(status().isBadRequest());\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void updateUserExistingLogin() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        userRepository.saveAndFlush(user);\r\n" +
		"        User anotherUser = new User();\r\n" +
		"        anotherUser.setLogin(\"jhipster\");\r\n" +
		"        anotherUser.setPassword(RandomStringUtils.random(60));\r\n" +
		"        anotherUser.setActivated(true);\r\n" +
		"        anotherUser.setEmail(\"jhipster@localhost\");\r\n" +
		"        anotherUser.setFirstName(\"java\");\r\n" +
		"        anotherUser.setLastName(\"hipster\");\r\n" +
		"        anotherUser.setImageUrl(\"\");\r\n" +
		"        anotherUser.setLangKey(\"en\");\r\n" +
		"        userRepository.saveAndFlush(anotherUser);\r\n" +
		"        // Update the user\r\n" +
		"        User updatedUser = userRepository.findById(user.getId()).get();\r\n" +
		"        ManagedUserVM managedUserVM = new ManagedUserVM();\r\n" +
		"        managedUserVM.setId(updatedUser.getId());\r\n" +
		"        managedUserVM.setLogin(\"jhipster\");// this login should already be used by anotherUser\r\n" +
		"        managedUserVM.setPassword(updatedUser.getPassword());\r\n" +
		"        managedUserVM.setFirstName(updatedUser.getFirstName());\r\n" +
		"        managedUserVM.setLastName(updatedUser.getLastName());\r\n" +
		"        managedUserVM.setEmail(updatedUser.getEmail());\r\n" +
		"        managedUserVM.setActivated(updatedUser.getActivated());\r\n" +
		"        managedUserVM.setImageUrl(updatedUser.getImageUrl());\r\n" +
		"        managedUserVM.setLangKey(updatedUser.getLangKey());\r\n" +
		"        managedUserVM.setCreatedBy(updatedUser.getCreatedBy());\r\n" +
		"        managedUserVM.setCreatedDate(updatedUser.getCreatedDate());\r\n" +
		"        managedUserVM.setLastModifiedBy(updatedUser.getLastModifiedBy());\r\n" +
		"        managedUserVM.setLastModifiedDate(updatedUser.getLastModifiedDate());\r\n" +
		"        managedUserVM.setAuthorities(Collections.singleton(AuthoritiesConstants.USER));\r\n" +
		"        restUserMockMvc.perform(put(\"/api/users\")\r\n" +
		"            .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"            .content(TestUtil.convertObjectToJsonBytes(managedUserVM)))\r\n" +
		"            .andExpect(status().isBadRequest());\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void deleteUser() throws Exception {\r\n" +
		"        // Initialize the database\r\n" +
		"        userRepository.saveAndFlush(user);\r\n" +
		"        int databaseSizeBeforeDelete = userRepository.findAll().size();\r\n" +
		"        // Delete the user\r\n" +
		"        restUserMockMvc.perform(delete(\"/api/users/{login}\", user.getLogin())\r\n" +
		"            .accept(TestUtil.APPLICATION_JSON_UTF8))\r\n" +
		"            .andExpect(status().isOk());\r\n" +
		"        // Validate the database is empty\r\n" +
		"        List<User> userList = userRepository.findAll();\r\n" +
		"        assertThat(userList).hasSize(databaseSizeBeforeDelete - 1);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void getAllAuthorities() throws Exception {\r\n" +
		"        restUserMockMvc.perform(get(\"/api/users/authorities\")\r\n" +
		"            .accept(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"            .contentType(TestUtil.APPLICATION_JSON_UTF8))\r\n" +
		"            .andExpect(status().isOk())\r\n" +
		"            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))\r\n" +
		"            .andExpect(jsonPath(\"$\").isArray())\r\n" +
		"            .andExpect(jsonPath(\"$\").value(hasItems(AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN)));\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void testUserEquals() throws Exception {\r\n" +
		"        TestUtil.equalsVerifier(User.class);\r\n" +
		"        User user1 = new User();\r\n" +
		"        user1.setId(1L);\r\n" +
		"        User user2 = new User();\r\n" +
		"        user2.setId(user1.getId());\r\n" +
		"        assertThat(user1).isEqualTo(user2);\r\n" +
		"        user2.setId(2L);\r\n" +
		"        assertThat(user1).isNotEqualTo(user2);\r\n" +
		"        user1.setId(null);\r\n" +
		"        assertThat(user1).isNotEqualTo(user2);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testUserFromId() {\r\n" +
		"        assertThat(userMapper.userFromId(DEFAULT_ID).getId()).isEqualTo(DEFAULT_ID);\r\n" +
		"        assertThat(userMapper.userFromId(null)).isNull();\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testUserDTOtoUser() {\r\n" +
		"        UserDTO userDTO = new UserDTO();\r\n" +
		"        userDTO.setId(DEFAULT_ID);\r\n" +
		"        userDTO.setLogin(DEFAULT_LOGIN);\r\n" +
		"        userDTO.setFirstName(DEFAULT_FIRSTNAME);\r\n" +
		"        userDTO.setLastName(DEFAULT_LASTNAME);\r\n" +
		"        userDTO.setEmail(DEFAULT_EMAIL);\r\n" +
		"        userDTO.setActivated(true);\r\n" +
		"        userDTO.setImageUrl(DEFAULT_IMAGEURL);\r\n" +
		"        userDTO.setLangKey(DEFAULT_LANGKEY);\r\n" +
		"        userDTO.setCreatedBy(DEFAULT_LOGIN);\r\n" +
		"        userDTO.setLastModifiedBy(DEFAULT_LOGIN);\r\n" +
		"        userDTO.setAuthorities(Collections.singleton(AuthoritiesConstants.USER));\r\n" +
		"        User user = userMapper.userDTOToUser(userDTO);\r\n" +
		"        assertThat(user.getId()).isEqualTo(DEFAULT_ID);\r\n" +
		"        assertThat(user.getLogin()).isEqualTo(DEFAULT_LOGIN);\r\n" +
		"        assertThat(user.getFirstName()).isEqualTo(DEFAULT_FIRSTNAME);\r\n" +
		"        assertThat(user.getLastName()).isEqualTo(DEFAULT_LASTNAME);\r\n" +
		"        assertThat(user.getEmail()).isEqualTo(DEFAULT_EMAIL);\r\n" +
		"        assertThat(user.getActivated()).isEqualTo(true);\r\n" +
		"        assertThat(user.getImageUrl()).isEqualTo(DEFAULT_IMAGEURL);\r\n" +
		"        assertThat(user.getLangKey()).isEqualTo(DEFAULT_LANGKEY);\r\n" +
		"        assertThat(user.getCreatedBy()).isNull();\r\n" +
		"        assertThat(user.getCreatedDate()).isNotNull();\r\n" +
		"        assertThat(user.getLastModifiedBy()).isNull();\r\n" +
		"        assertThat(user.getLastModifiedDate()).isNotNull();\r\n" +
		"        assertThat(user.getAuthorities()).extracting(\"name\").containsExactly(AuthoritiesConstants.USER);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testUserToUserDTO() {\r\n" +
		"        user.setId(DEFAULT_ID);\r\n" +
		"        user.setCreatedBy(DEFAULT_LOGIN);\r\n" +
		"        user.setCreatedDate(Instant.now());\r\n" +
		"        user.setLastModifiedBy(DEFAULT_LOGIN);\r\n" +
		"        user.setLastModifiedDate(Instant.now());\r\n" +
		"        Set<Authority> authorities = new HashSet<>();\r\n" +
		"        Authority authority = new Authority();\r\n" +
		"        authority.setName(AuthoritiesConstants.USER);\r\n" +
		"        authorities.add(authority);\r\n" +
		"        user.setAuthorities(authorities);\r\n" +
		"        UserDTO userDTO = userMapper.userToUserDTO(user);\r\n" +
		"        assertThat(userDTO.getId()).isEqualTo(DEFAULT_ID);\r\n" +
		"        assertThat(userDTO.getLogin()).isEqualTo(DEFAULT_LOGIN);\r\n" +
		"        assertThat(userDTO.getFirstName()).isEqualTo(DEFAULT_FIRSTNAME);\r\n" +
		"        assertThat(userDTO.getLastName()).isEqualTo(DEFAULT_LASTNAME);\r\n" +
		"        assertThat(userDTO.getEmail()).isEqualTo(DEFAULT_EMAIL);\r\n" +
		"        assertThat(userDTO.isActivated()).isEqualTo(true);\r\n" +
		"        assertThat(userDTO.getImageUrl()).isEqualTo(DEFAULT_IMAGEURL);\r\n" +
		"        assertThat(userDTO.getLangKey()).isEqualTo(DEFAULT_LANGKEY);\r\n" +
		"        assertThat(userDTO.getCreatedBy()).isEqualTo(DEFAULT_LOGIN);\r\n" +
		"        assertThat(userDTO.getCreatedDate()).isEqualTo(user.getCreatedDate());\r\n" +
		"        assertThat(userDTO.getLastModifiedBy()).isEqualTo(DEFAULT_LOGIN);\r\n" +
		"        assertThat(userDTO.getLastModifiedDate()).isEqualTo(user.getLastModifiedDate());\r\n" +
		"        assertThat(userDTO.getAuthorities()).containsExactly(AuthoritiesConstants.USER);\r\n" +
		"        assertThat(userDTO.toString()).isNotNull();\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testAuthorityEquals() {\r\n" +
		"        Authority authorityA = new Authority();\r\n" +
		"        assertThat(authorityA).isEqualTo(authorityA);\r\n" +
		"        assertThat(authorityA).isNotEqualTo(null);\r\n" +
		"        assertThat(authorityA).isNotEqualTo(new Object());\r\n" +
		"        assertThat(authorityA.hashCode()).isEqualTo(0);\r\n" +
		"        assertThat(authorityA.toString()).isNotNull();\r\n" +
		"        Authority authorityB = new Authority();\r\n" +
		"        assertThat(authorityA).isEqualTo(authorityB);\r\n" +
		"        authorityB.setName(AuthoritiesConstants.ADMIN);\r\n" +
		"        assertThat(authorityA).isNotEqualTo(authorityB);\r\n" +
		"        authorityA.setName(AuthoritiesConstants.USER);\r\n" +
		"        assertThat(authorityA).isNotEqualTo(authorityB);\r\n" +
		"        authorityB.setName(AuthoritiesConstants.USER);\r\n" +
		"        assertThat(authorityA).isEqualTo(authorityB);\r\n" +
		"        assertThat(authorityA.hashCode()).isEqualTo(authorityB.hashCode());\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "UserResourceIntTest";
	}

	public String getSourceFolder() {
		return "src/test/java";
	}

}
