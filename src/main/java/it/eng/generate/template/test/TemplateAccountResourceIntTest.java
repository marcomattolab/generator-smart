package it.eng.generate.template.test;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateAccountResourceIntTest extends AbstractTemplate{

	public TemplateAccountResourceIntTest(DataBase database) {
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
		"import "+ conf.getPackageclass() + "."+Utils.getClassNameCamelCase(conf.getProjectName()) + conf.getApp() +";\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcConfigFolder()+".Constants;\r\n"+
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".Authority;\r\n"+
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".PersistentToken;\r\n"+
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".User;\r\n"+
		"import "+ conf.getPackageclass() + "." + conf.getSrcRepositoryFolder()+".AuthorityRepository;\r\n"+
		"import "+ conf.getPackageclass() + "." + conf.getSrcRepositoryFolder()+".PersistentTokenRepository;\r\n"+
		"import "+ conf.getPackageclass() + "." + conf.getSrcRepositoryFolder()+".UserRepository;\r\n"+
		"import "+ conf.getPackageclass() + "." + conf.getSrcSecurityFolder()+".AuthoritiesConstants;\r\n"+
		"import "+ conf.getPackageclass() + "." + conf.getSrcServiceFolder()+".MailService;\r\n"+
		"import "+ conf.getPackageclass() + "." + conf.getSrcServiceFolder()+".UserService;\r\n"+
		"import "+ conf.getPackageclass() + "." + conf.getSrcServiceDtoFolder()+".PasswordChangeDTO;\r\n"+
		"import "+ conf.getPackageclass() + "." + conf.getSrcServiceDtoFolder()+".UserDTO;\r\n"+
		"import "+ conf.getPackageclass() + "." + conf.getSrcWebRestErrorsFolder()+".ExceptionTranslator;\r\n"+
		"import "+ conf.getPackageclass() + "." + conf.getSrcWebRestVmFolder()+".KeyAndPasswordVM;\r\n"+
		"import "+ conf.getPackageclass() + "." + conf.getSrcWebRestVmFolder()+".ManagedUserVM;\r\n"+
		"import org.apache.commons.lang3.RandomStringUtils;\r\n" +
		"import org.junit.Before;\r\n" +
		"import org.junit.Test;\r\n" +
		"import org.junit.runner.RunWith;\r\n" +
		"import org.mockito.Mock;\r\n" +
		"import org.mockito.MockitoAnnotations;\r\n" +
		"import org.springframework.beans.factory.annotation.Autowired;\r\n" +
		"import org.springframework.boot.test.context.SpringBootTest;\r\n" +
		"import org.springframework.http.MediaType;\r\n" +
		"import org.springframework.http.converter.HttpMessageConverter;\r\n" +
		"import org.springframework.security.crypto.password.PasswordEncoder;\r\n" +
		"import org.springframework.security.test.context.support.WithMockUser;\r\n" +
		"import org.springframework.test.context.junit4.SpringRunner;\r\n" +
		"import org.springframework.test.web.servlet.MockMvc;\r\n" +
		"import org.springframework.test.web.servlet.setup.MockMvcBuilders;\r\n" +
		"import org.springframework.transaction.annotation.Transactional;\r\n" +
		"import java.time.Instant;\r\n" +
		"import java.time.LocalDate;\r\n" +
		"import java.util.*;\r\n" +
		"import static org.assertj.core.api.Assertions.assertThat;\r\n" +
		"import static org.hamcrest.Matchers.hasItem;\r\n" +
		"import static org.mockito.ArgumentMatchers.any;\r\n" +
		"import static org.mockito.Mockito.doNothing;\r\n" +
		"import static org.mockito.Mockito.when;\r\n" +
		"import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;\r\n" +
		"import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;\r\n\n" +
		"/**\r\n" +
		" * Test class for the AccountResource REST controller.\r\n" +
		" * @see AccountResource\r\n" +
		" */\r\n" +
		"@RunWith(SpringRunner.class)\r\n" +
		"@SpringBootTest(classes = "+Utils.getClassNameCamelCase(conf.getProjectName()) + conf.getApp() +".class)\r\n" +
		"public class AccountResourceIntTest {\r\n" +
		"    @Autowired private UserRepository userRepository;\r\n" +
		"    @Autowired private AuthorityRepository authorityRepository;\r\n" +
		"    @Autowired private UserService userService;\r\n" +
		"    @Autowired private PersistentTokenRepository persistentTokenRepository;\r\n" +
		"    @Autowired private PasswordEncoder passwordEncoder;\r\n" +
		"    @Autowired private HttpMessageConverter<?>[] httpMessageConverters;\r\n" +
		"    @Autowired private ExceptionTranslator exceptionTranslator;\r\n" +
		"    @Mock private UserService mockUserService;\r\n" +
		"    @Mock private MailService mockMailService;\r\n" +
		"    private MockMvc restMvc;\r\n" +
		"    private MockMvc restUserMockMvc;\r\n" +
		"    @Before\r\n" +
		"    public void setup() {\r\n" +
		"        MockitoAnnotations.initMocks(this);\r\n" +
		"        doNothing().when(mockMailService).sendActivationEmail(any());\r\n" +
		"        AccountResource accountResource =\r\n" +
		"            new AccountResource(userRepository, userService, mockMailService, persistentTokenRepository);\r\n" +
		"        AccountResource accountUserMockResource =\r\n" +
		"            new AccountResource(userRepository, mockUserService, mockMailService, persistentTokenRepository);\r\n" +
		"        this.restMvc = MockMvcBuilders.standaloneSetup(accountResource)\r\n" +
		"            .setMessageConverters(httpMessageConverters)\r\n" +
		"            .setControllerAdvice(exceptionTranslator)\r\n" +
		"            .build();\r\n" +
		"        this.restUserMockMvc = MockMvcBuilders.standaloneSetup(accountUserMockResource)\r\n" +
		"            .setControllerAdvice(exceptionTranslator)\r\n" +
		"            .build();\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    public void testNonAuthenticatedUser() throws Exception {\r\n" +
		"        restUserMockMvc.perform(get(\"/api/authenticate\")\r\n" +
		"            .accept(MediaType.APPLICATION_JSON))\r\n" +
		"            .andExpect(status().isOk())\r\n" +
		"            .andExpect(content().string(\"\"));\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    public void testAuthenticatedUser() throws Exception {\r\n" +
		"        restUserMockMvc.perform(get(\"/api/authenticate\")\r\n" +
		"            .with(request -> {\r\n" +
		"                request.setRemoteUser(\"test\");\r\n" +
		"                return request;\r\n" +
		"            })\r\n" +
		"            .accept(MediaType.APPLICATION_JSON))\r\n" +
		"            .andExpect(status().isOk())\r\n" +
		"            .andExpect(content().string(\"test\"));\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    public void testGetExistingAccount() throws Exception {\r\n" +
		"        Set<Authority> authorities = new HashSet<>();\r\n" +
		"        Authority authority = new Authority();\r\n" +
		"        authority.setName(AuthoritiesConstants.ADMIN);\r\n" +
		"        authorities.add(authority);\r\n" +
		"        User user = new User();\r\n" +
		"        user.setLogin(\"test\");\r\n" +
		"        user.setFirstName(\"john\");\r\n" +
		"        user.setLastName(\"doe\");\r\n" +
		"        user.setEmail(\"john.doe@jhipster.com\");\r\n" +
		"        user.setImageUrl(\"http://placehold.it/50x50\");\r\n" +
		"        user.setLangKey(\"en\");\r\n" +
		"        user.setAuthorities(authorities);\r\n" +
		"        when(mockUserService.getUserWithAuthorities()).thenReturn(Optional.of(user));\r\n" +
		"        restUserMockMvc.perform(get(\"/api/account\")\r\n" +
		"            .accept(MediaType.APPLICATION_JSON))\r\n" +
		"            .andExpect(status().isOk())\r\n" +
		"            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))\r\n" +
		"            .andExpect(jsonPath(\"$.login\").value(\"test\"))\r\n" +
		"            .andExpect(jsonPath(\"$.firstName\").value(\"john\"))\r\n" +
		"            .andExpect(jsonPath(\"$.lastName\").value(\"doe\"))\r\n" +
		"            .andExpect(jsonPath(\"$.email\").value(\"john.doe@jhipster.com\"))\r\n" +
		"            .andExpect(jsonPath(\"$.imageUrl\").value(\"http://placehold.it/50x50\"))\r\n" +
		"            .andExpect(jsonPath(\"$.langKey\").value(\"en\"))\r\n" +
		"            .andExpect(jsonPath(\"$.authorities\").value(AuthoritiesConstants.ADMIN));\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    public void testGetUnknownAccount() throws Exception {\r\n" +
		"        when(mockUserService.getUserWithAuthorities()).thenReturn(Optional.empty());\r\n" +
		"        restUserMockMvc.perform(get(\"/api/account\")\r\n" +
		"            .accept(MediaType.APPLICATION_PROBLEM_JSON))\r\n" +
		"            .andExpect(status().isInternalServerError());\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void testRegisterValid() throws Exception {\r\n" +
		"        ManagedUserVM validUser = new ManagedUserVM();\r\n" +
		"        validUser.setLogin(\"test-register-valid\");\r\n" +
		"        validUser.setPassword(\"password\");\r\n" +
		"        validUser.setFirstName(\"Alice\");\r\n" +
		"        validUser.setLastName(\"Test\");\r\n" +
		"        validUser.setEmail(\"test-register-valid@example.com\");\r\n" +
		"        validUser.setImageUrl(\"http://placehold.it/50x50\");\r\n" +
		"        validUser.setLangKey(Constants.DEFAULT_LANGUAGE);\r\n" +
		"        validUser.setAuthorities(Collections.singleton(AuthoritiesConstants.USER));\r\n" +
		"        assertThat(userRepository.findOneByLogin(\"test-register-valid\").isPresent()).isFalse();\r\n" +
		"        restMvc.perform(\r\n" +
		"            post(\"/api/register\")\r\n" +
		"                .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"                .content(TestUtil.convertObjectToJsonBytes(validUser)))\r\n" +
		"            .andExpect(status().isCreated());\r\n" +
		"        assertThat(userRepository.findOneByLogin(\"test-register-valid\").isPresent()).isTrue();\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void testRegisterInvalidLogin() throws Exception {\r\n" +
		"        ManagedUserVM invalidUser = new ManagedUserVM();\r\n" +
		"        invalidUser.setLogin(\"funky-log!n\");// <-- invalid\r\n" +
		"        invalidUser.setPassword(\"password\");\r\n" +
		"        invalidUser.setFirstName(\"Funky\");\r\n" +
		"        invalidUser.setLastName(\"One\");\r\n" +
		"        invalidUser.setEmail(\"funky@example.com\");\r\n" +
		"        invalidUser.setActivated(true);\r\n" +
		"        invalidUser.setImageUrl(\"http://placehold.it/50x50\");\r\n" +
		"        invalidUser.setLangKey(Constants.DEFAULT_LANGUAGE);\r\n" +
		"        invalidUser.setAuthorities(Collections.singleton(AuthoritiesConstants.USER));\r\n" +
		"        restUserMockMvc.perform(\r\n" +
		"            post(\"/api/register\")\r\n" +
		"                .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"                .content(TestUtil.convertObjectToJsonBytes(invalidUser)))\r\n" +
		"            .andExpect(status().isBadRequest());\r\n" +
		"        Optional<User> user = userRepository.findOneByEmailIgnoreCase(\"funky@example.com\");\r\n" +
		"        assertThat(user.isPresent()).isFalse();\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void testRegisterInvalidEmail() throws Exception {\r\n" +
		"        ManagedUserVM invalidUser = new ManagedUserVM();\r\n" +
		"        invalidUser.setLogin(\"bob\");\r\n" +
		"        invalidUser.setPassword(\"password\");\r\n" +
		"        invalidUser.setFirstName(\"Bob\");\r\n" +
		"        invalidUser.setLastName(\"Green\");\r\n" +
		"        invalidUser.setEmail(\"invalid\");// <-- invalid\r\n" +
		"        invalidUser.setActivated(true);\r\n" +
		"        invalidUser.setImageUrl(\"http://placehold.it/50x50\");\r\n" +
		"        invalidUser.setLangKey(Constants.DEFAULT_LANGUAGE);\r\n" +
		"        invalidUser.setAuthorities(Collections.singleton(AuthoritiesConstants.USER));\r\n" +
		"        restUserMockMvc.perform(\r\n" +
		"            post(\"/api/register\")\r\n" +
		"                .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"                .content(TestUtil.convertObjectToJsonBytes(invalidUser)))\r\n" +
		"            .andExpect(status().isBadRequest());\r\n" +
		"        Optional<User> user = userRepository.findOneByLogin(\"bob\");\r\n" +
		"        assertThat(user.isPresent()).isFalse();\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void testRegisterInvalidPassword() throws Exception {\r\n" +
		"        ManagedUserVM invalidUser = new ManagedUserVM();\r\n" +
		"        invalidUser.setLogin(\"bob\");\r\n" +
		"        invalidUser.setPassword(\"123\");// password with only 3 digits\r\n" +
		"        invalidUser.setFirstName(\"Bob\");\r\n" +
		"        invalidUser.setLastName(\"Green\");\r\n" +
		"        invalidUser.setEmail(\"bob@example.com\");\r\n" +
		"        invalidUser.setActivated(true);\r\n" +
		"        invalidUser.setImageUrl(\"http://placehold.it/50x50\");\r\n" +
		"        invalidUser.setLangKey(Constants.DEFAULT_LANGUAGE);\r\n" +
		"        invalidUser.setAuthorities(Collections.singleton(AuthoritiesConstants.USER));\r\n" +
		"        restUserMockMvc.perform(\r\n" +
		"            post(\"/api/register\")\r\n" +
		"                .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"                .content(TestUtil.convertObjectToJsonBytes(invalidUser)))\r\n" +
		"            .andExpect(status().isBadRequest());\r\n" +
		"        Optional<User> user = userRepository.findOneByLogin(\"bob\");\r\n" +
		"        assertThat(user.isPresent()).isFalse();\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void testRegisterNullPassword() throws Exception {\r\n" +
		"        ManagedUserVM invalidUser = new ManagedUserVM();\r\n" +
		"        invalidUser.setLogin(\"bob\");\r\n" +
		"        invalidUser.setPassword(null);// invalid null password\r\n" +
		"        invalidUser.setFirstName(\"Bob\");\r\n" +
		"        invalidUser.setLastName(\"Green\");\r\n" +
		"        invalidUser.setEmail(\"bob@example.com\");\r\n" +
		"        invalidUser.setActivated(true);\r\n" +
		"        invalidUser.setImageUrl(\"http://placehold.it/50x50\");\r\n" +
		"        invalidUser.setLangKey(Constants.DEFAULT_LANGUAGE);\r\n" +
		"        invalidUser.setAuthorities(Collections.singleton(AuthoritiesConstants.USER));\r\n" +
		"        restUserMockMvc.perform(\r\n" +
		"            post(\"/api/register\")\r\n" +
		"                .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"                .content(TestUtil.convertObjectToJsonBytes(invalidUser)))\r\n" +
		"            .andExpect(status().isBadRequest());\r\n" +
		"        Optional<User> user = userRepository.findOneByLogin(\"bob\");\r\n" +
		"        assertThat(user.isPresent()).isFalse();\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void testRegisterDuplicateLogin() throws Exception {\r\n" +
		"        // First registration\r\n" +
		"        ManagedUserVM firstUser = new ManagedUserVM();\r\n" +
		"        firstUser.setLogin(\"alice\");\r\n" +
		"        firstUser.setPassword(\"password\");\r\n" +
		"        firstUser.setFirstName(\"Alice\");\r\n" +
		"        firstUser.setLastName(\"Something\");\r\n" +
		"        firstUser.setEmail(\"alice@example.com\");\r\n" +
		"        firstUser.setImageUrl(\"http://placehold.it/50x50\");\r\n" +
		"        firstUser.setLangKey(Constants.DEFAULT_LANGUAGE);\r\n" +
		"        firstUser.setAuthorities(Collections.singleton(AuthoritiesConstants.USER));\r\n" +
		"        // Duplicate login, different email\r\n" +
		"        ManagedUserVM secondUser = new ManagedUserVM();\r\n" +
		"        secondUser.setLogin(firstUser.getLogin());\r\n" +
		"        secondUser.setPassword(firstUser.getPassword());\r\n" +
		"        secondUser.setFirstName(firstUser.getFirstName());\r\n" +
		"        secondUser.setLastName(firstUser.getLastName());\r\n" +
		"        secondUser.setEmail(\"alice2@example.com\");\r\n" +
		"        secondUser.setImageUrl(firstUser.getImageUrl());\r\n" +
		"        secondUser.setLangKey(firstUser.getLangKey());\r\n" +
		"        secondUser.setCreatedBy(firstUser.getCreatedBy());\r\n" +
		"        secondUser.setCreatedDate(firstUser.getCreatedDate());\r\n" +
		"        secondUser.setLastModifiedBy(firstUser.getLastModifiedBy());\r\n" +
		"        secondUser.setLastModifiedDate(firstUser.getLastModifiedDate());\r\n" +
		"        secondUser.setAuthorities(new HashSet<>(firstUser.getAuthorities()));\r\n" +
		"        // First user\r\n" +
		"        restMvc.perform(\r\n" +
		"            post(\"/api/register\")\r\n" +
		"                .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"                .content(TestUtil.convertObjectToJsonBytes(firstUser)))\r\n" +
		"            .andExpect(status().isCreated());\r\n" +
		"        // Second (non activated) user\r\n" +
		"        restMvc.perform(\r\n" +
		"            post(\"/api/register\")\r\n" +
		"                .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"                .content(TestUtil.convertObjectToJsonBytes(secondUser)))\r\n" +
		"            .andExpect(status().isCreated());\r\n" +
		"        Optional<User> testUser = userRepository.findOneByEmailIgnoreCase(\"alice2@example.com\");\r\n" +
		"        assertThat(testUser.isPresent()).isTrue();\r\n" +
		"        testUser.get().setActivated(true);\r\n" +
		"        userRepository.save(testUser.get());\r\n" +
		"        // Second (already activated) user\r\n" +
		"        restMvc.perform(\r\n" +
		"            post(\"/api/register\")\r\n" +
		"                .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"                .content(TestUtil.convertObjectToJsonBytes(secondUser)))\r\n" +
		"            .andExpect(status().is4xxClientError());\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void testRegisterDuplicateEmail() throws Exception {\r\n" +
		"        // First user\r\n" +
		"        ManagedUserVM firstUser = new ManagedUserVM();\r\n" +
		"        firstUser.setLogin(\"test-register-duplicate-email\");\r\n" +
		"        firstUser.setPassword(\"password\");\r\n" +
		"        firstUser.setFirstName(\"Alice\");\r\n" +
		"        firstUser.setLastName(\"Test\");\r\n" +
		"        firstUser.setEmail(\"test-register-duplicate-email@example.com\");\r\n" +
		"        firstUser.setImageUrl(\"http://placehold.it/50x50\");\r\n" +
		"        firstUser.setLangKey(Constants.DEFAULT_LANGUAGE);\r\n" +
		"        firstUser.setAuthorities(Collections.singleton(AuthoritiesConstants.USER));\r\n" +
		"        // Register first user\r\n" +
		"        restMvc.perform(\r\n" +
		"            post(\"/api/register\")\r\n" +
		"                .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"                .content(TestUtil.convertObjectToJsonBytes(firstUser)))\r\n" +
		"            .andExpect(status().isCreated());\r\n" +
		"        Optional<User> testUser1 = userRepository.findOneByLogin(\"test-register-duplicate-email\");\r\n" +
		"        assertThat(testUser1.isPresent()).isTrue();\r\n" +
		"        // Duplicate email, different login\r\n" +
		"        ManagedUserVM secondUser = new ManagedUserVM();\r\n" +
		"        secondUser.setLogin(\"test-register-duplicate-email-2\");\r\n" +
		"        secondUser.setPassword(firstUser.getPassword());\r\n" +
		"        secondUser.setFirstName(firstUser.getFirstName());\r\n" +
		"        secondUser.setLastName(firstUser.getLastName());\r\n" +
		"        secondUser.setEmail(firstUser.getEmail());\r\n" +
		"        secondUser.setImageUrl(firstUser.getImageUrl());\r\n" +
		"        secondUser.setLangKey(firstUser.getLangKey());\r\n" +
		"        secondUser.setAuthorities(new HashSet<>(firstUser.getAuthorities()));\r\n" +
		"        // Register second (non activated) user\r\n" +
		"        restMvc.perform(\r\n" +
		"            post(\"/api/register\")\r\n" +
		"                .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"                .content(TestUtil.convertObjectToJsonBytes(secondUser)))\r\n" +
		"            .andExpect(status().isCreated());\r\n" +
		"        Optional<User> testUser2 = userRepository.findOneByLogin(\"test-register-duplicate-email\");\r\n" +
		"        assertThat(testUser2.isPresent()).isFalse();\r\n" +
		"        Optional<User> testUser3 = userRepository.findOneByLogin(\"test-register-duplicate-email-2\");\r\n" +
		"        assertThat(testUser3.isPresent()).isTrue();\r\n" +
		"        // Duplicate email - with uppercase email address\r\n" +
		"        ManagedUserVM userWithUpperCaseEmail = new ManagedUserVM();\r\n" +
		"        userWithUpperCaseEmail.setId(firstUser.getId());\r\n" +
		"        userWithUpperCaseEmail.setLogin(\"test-register-duplicate-email-3\");\r\n" +
		"        userWithUpperCaseEmail.setPassword(firstUser.getPassword());\r\n" +
		"        userWithUpperCaseEmail.setFirstName(firstUser.getFirstName());\r\n" +
		"        userWithUpperCaseEmail.setLastName(firstUser.getLastName());\r\n" +
		"        userWithUpperCaseEmail.setEmail(\"TEST-register-duplicate-email@example.com\");\r\n" +
		"        userWithUpperCaseEmail.setImageUrl(firstUser.getImageUrl());\r\n" +
		"        userWithUpperCaseEmail.setLangKey(firstUser.getLangKey());\r\n" +
		"        userWithUpperCaseEmail.setAuthorities(new HashSet<>(firstUser.getAuthorities()));\r\n" +
		"        // Register third (not activated) user\r\n" +
		"        restMvc.perform(\r\n" +
		"            post(\"/api/register\")\r\n" +
		"                .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"                .content(TestUtil.convertObjectToJsonBytes(userWithUpperCaseEmail)))\r\n" +
		"            .andExpect(status().isCreated());\r\n" +
		"        Optional<User> testUser4 = userRepository.findOneByLogin(\"test-register-duplicate-email-3\");\r\n" +
		"        assertThat(testUser4.isPresent()).isTrue();\r\n" +
		"        assertThat(testUser4.get().getEmail()).isEqualTo(\"test-register-duplicate-email@example.com\");\r\n" +
		"        testUser4.get().setActivated(true);\r\n" +
		"        userService.updateUser((new UserDTO(testUser4.get())));\r\n" +
		"        // Register 4th (already activated) user\r\n" +
		"        restMvc.perform(\r\n" +
		"            post(\"/api/register\")\r\n" +
		"                .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"                .content(TestUtil.convertObjectToJsonBytes(secondUser)))\r\n" +
		"            .andExpect(status().is4xxClientError());\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void testRegisterAdminIsIgnored() throws Exception {\r\n" +
		"        ManagedUserVM validUser = new ManagedUserVM();\r\n" +
		"        validUser.setLogin(\"badguy\");\r\n" +
		"        validUser.setPassword(\"password\");\r\n" +
		"        validUser.setFirstName(\"Bad\");\r\n" +
		"        validUser.setLastName(\"Guy\");\r\n" +
		"        validUser.setEmail(\"badguy@example.com\");\r\n" +
		"        validUser.setActivated(true);\r\n" +
		"        validUser.setImageUrl(\"http://placehold.it/50x50\");\r\n" +
		"        validUser.setLangKey(Constants.DEFAULT_LANGUAGE);\r\n" +
		"        validUser.setAuthorities(Collections.singleton(AuthoritiesConstants.ADMIN));\r\n" +
		"        restMvc.perform(\r\n" +
		"            post(\"/api/register\")\r\n" +
		"                .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"                .content(TestUtil.convertObjectToJsonBytes(validUser)))\r\n" +
		"            .andExpect(status().isCreated());\r\n" +
		"        Optional<User> userDup = userRepository.findOneByLogin(\"badguy\");\r\n" +
		"        assertThat(userDup.isPresent()).isTrue();\r\n" +
		"        assertThat(userDup.get().getAuthorities()).hasSize(1)\r\n" +
		"            .containsExactly(authorityRepository.findById(AuthoritiesConstants.USER).get());\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void testActivateAccount() throws Exception {\r\n" +
		"        final String activationKey = \"some activation key\";\r\n" +
		"        User user = new User();\r\n" +
		"        user.setLogin(\"activate-account\");\r\n" +
		"        user.setEmail(\"activate-account@example.com\");\r\n" +
		"        user.setPassword(RandomStringUtils.random(60));\r\n" +
		"        user.setActivated(false);\r\n" +
		"        user.setActivationKey(activationKey);\r\n" +
		"        userRepository.saveAndFlush(user);\r\n" +
		"        restMvc.perform(get(\"/api/activate?key={activationKey}\", activationKey))\r\n" +
		"            .andExpect(status().isOk());\r\n" +
		"        user = userRepository.findOneByLogin(user.getLogin()).orElse(null);\r\n" +
		"        assertThat(user.getActivated()).isTrue();\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void testActivateAccountWithWrongKey() throws Exception {\r\n" +
		"        restMvc.perform(get(\"/api/activate?key=wrongActivationKey\"))\r\n" +
		"            .andExpect(status().isInternalServerError());\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    @WithMockUser(\"save-account\")\r\n" +
		"    public void testSaveAccount() throws Exception {\r\n" +
		"        User user = new User();\r\n" +
		"        user.setLogin(\"save-account\");\r\n" +
		"        user.setEmail(\"save-account@example.com\");\r\n" +
		"        user.setPassword(RandomStringUtils.random(60));\r\n" +
		"        user.setActivated(true);\r\n" +
		"        userRepository.saveAndFlush(user);\r\n" +
		"        UserDTO userDTO = new UserDTO();\r\n" +
		"        userDTO.setLogin(\"not-used\");\r\n" +
		"        userDTO.setFirstName(\"firstname\");\r\n" +
		"        userDTO.setLastName(\"lastname\");\r\n" +
		"        userDTO.setEmail(\"save-account@example.com\");\r\n" +
		"        userDTO.setActivated(false);\r\n" +
		"        userDTO.setImageUrl(\"http://placehold.it/50x50\");\r\n" +
		"        userDTO.setLangKey(Constants.DEFAULT_LANGUAGE);\r\n" +
		"        userDTO.setAuthorities(Collections.singleton(AuthoritiesConstants.ADMIN));\r\n" +
		"        restMvc.perform(\r\n" +
		"            post(\"/api/account\")\r\n" +
		"                .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"                .content(TestUtil.convertObjectToJsonBytes(userDTO)))\r\n" +
		"            .andExpect(status().isOk());\r\n" +
		"        User updatedUser = userRepository.findOneByLogin(user.getLogin()).orElse(null);\r\n" +
		"        assertThat(updatedUser.getFirstName()).isEqualTo(userDTO.getFirstName());\r\n" +
		"        assertThat(updatedUser.getLastName()).isEqualTo(userDTO.getLastName());\r\n" +
		"        assertThat(updatedUser.getEmail()).isEqualTo(userDTO.getEmail());\r\n" +
		"        assertThat(updatedUser.getLangKey()).isEqualTo(userDTO.getLangKey());\r\n" +
		"        assertThat(updatedUser.getPassword()).isEqualTo(user.getPassword());\r\n" +
		"        assertThat(updatedUser.getImageUrl()).isEqualTo(userDTO.getImageUrl());\r\n" +
		"        assertThat(updatedUser.getActivated()).isEqualTo(true);\r\n" +
		"        assertThat(updatedUser.getAuthorities()).isEmpty();\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    @WithMockUser(\"save-invalid-email\")\r\n" +
		"    public void testSaveInvalidEmail() throws Exception {\r\n" +
		"        User user = new User();\r\n" +
		"        user.setLogin(\"save-invalid-email\");\r\n" +
		"        user.setEmail(\"save-invalid-email@example.com\");\r\n" +
		"        user.setPassword(RandomStringUtils.random(60));\r\n" +
		"        user.setActivated(true);\r\n" +
		"        userRepository.saveAndFlush(user);\r\n" +
		"        UserDTO userDTO = new UserDTO();\r\n" +
		"        userDTO.setLogin(\"not-used\");\r\n" +
		"        userDTO.setFirstName(\"firstname\");\r\n" +
		"        userDTO.setLastName(\"lastname\");\r\n" +
		"        userDTO.setEmail(\"invalid email\");\r\n" +
		"        userDTO.setActivated(false);\r\n" +
		"        userDTO.setImageUrl(\"http://placehold.it/50x50\");\r\n" +
		"        userDTO.setLangKey(Constants.DEFAULT_LANGUAGE);\r\n" +
		"        userDTO.setAuthorities(Collections.singleton(AuthoritiesConstants.ADMIN));\r\n" +
		"        restMvc.perform(\r\n" +
		"            post(\"/api/account\")\r\n" +
		"                .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"                .content(TestUtil.convertObjectToJsonBytes(userDTO)))\r\n" +
		"            .andExpect(status().isBadRequest());\r\n" +
		"        assertThat(userRepository.findOneByEmailIgnoreCase(\"invalid email\")).isNotPresent();\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    @WithMockUser(\"save-existing-email\")\r\n" +
		"    public void testSaveExistingEmail() throws Exception {\r\n" +
		"        User user = new User();\r\n" +
		"        user.setLogin(\"save-existing-email\");\r\n" +
		"        user.setEmail(\"save-existing-email@example.com\");\r\n" +
		"        user.setPassword(RandomStringUtils.random(60));\r\n" +
		"        user.setActivated(true);\r\n" +
		"        userRepository.saveAndFlush(user);\r\n" +
		"        User anotherUser = new User();\r\n" +
		"        anotherUser.setLogin(\"save-existing-email2\");\r\n" +
		"        anotherUser.setEmail(\"save-existing-email2@example.com\");\r\n" +
		"        anotherUser.setPassword(RandomStringUtils.random(60));\r\n" +
		"        anotherUser.setActivated(true);\r\n" +
		"        userRepository.saveAndFlush(anotherUser);\r\n" +
		"        UserDTO userDTO = new UserDTO();\r\n" +
		"        userDTO.setLogin(\"not-used\");\r\n" +
		"        userDTO.setFirstName(\"firstname\");\r\n" +
		"        userDTO.setLastName(\"lastname\");\r\n" +
		"        userDTO.setEmail(\"save-existing-email2@example.com\");\r\n" +
		"        userDTO.setActivated(false);\r\n" +
		"        userDTO.setImageUrl(\"http://placehold.it/50x50\");\r\n" +
		"        userDTO.setLangKey(Constants.DEFAULT_LANGUAGE);\r\n" +
		"        userDTO.setAuthorities(Collections.singleton(AuthoritiesConstants.ADMIN));\r\n" +
		"        restMvc.perform(\r\n" +
		"            post(\"/api/account\")\r\n" +
		"                .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"                .content(TestUtil.convertObjectToJsonBytes(userDTO)))\r\n" +
		"            .andExpect(status().isBadRequest());\r\n" +
		"        User updatedUser = userRepository.findOneByLogin(\"save-existing-email\").orElse(null);\r\n" +
		"        assertThat(updatedUser.getEmail()).isEqualTo(\"save-existing-email@example.com\");\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    @WithMockUser(\"save-existing-email-and-login\")\r\n" +
		"    public void testSaveExistingEmailAndLogin() throws Exception {\r\n" +
		"        User user = new User();\r\n" +
		"        user.setLogin(\"save-existing-email-and-login\");\r\n" +
		"        user.setEmail(\"save-existing-email-and-login@example.com\");\r\n" +
		"        user.setPassword(RandomStringUtils.random(60));\r\n" +
		"        user.setActivated(true);\r\n" +
		"        userRepository.saveAndFlush(user);\r\n" +
		"        UserDTO userDTO = new UserDTO();\r\n" +
		"        userDTO.setLogin(\"not-used\");\r\n" +
		"        userDTO.setFirstName(\"firstname\");\r\n" +
		"        userDTO.setLastName(\"lastname\");\r\n" +
		"        userDTO.setEmail(\"save-existing-email-and-login@example.com\");\r\n" +
		"        userDTO.setActivated(false);\r\n" +
		"        userDTO.setImageUrl(\"http://placehold.it/50x50\");\r\n" +
		"        userDTO.setLangKey(Constants.DEFAULT_LANGUAGE);\r\n" +
		"        userDTO.setAuthorities(Collections.singleton(AuthoritiesConstants.ADMIN));\r\n" +
		"        restMvc.perform(\r\n" +
		"            post(\"/api/account\")\r\n" +
		"                .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"                .content(TestUtil.convertObjectToJsonBytes(userDTO)))\r\n" +
		"            .andExpect(status().isOk());\r\n" +
		"        User updatedUser = userRepository.findOneByLogin(\"save-existing-email-and-login\").orElse(null);\r\n" +
		"        assertThat(updatedUser.getEmail()).isEqualTo(\"save-existing-email-and-login@example.com\");\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    @WithMockUser(\"change-password-wrong-existing-password\")\r\n" +
		"    public void testChangePasswordWrongExistingPassword() throws Exception {\r\n" +
		"        User user = new User();\r\n" +
		"        String currentPassword = RandomStringUtils.random(60);\r\n" +
		"        user.setPassword(passwordEncoder.encode(currentPassword));\r\n" +
		"        user.setLogin(\"change-password-wrong-existing-password\");\r\n" +
		"        user.setEmail(\"change-password-wrong-existing-password@example.com\");\r\n" +
		"        userRepository.saveAndFlush(user);\r\n" +
		"        restMvc.perform(post(\"/api/account/change-password\")\r\n" +
		"            .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"            .content(TestUtil.convertObjectToJsonBytes(new PasswordChangeDTO(\"1\"+currentPassword, \"new password\"))))\r\n" +
		"            .andExpect(status().isBadRequest());\r\n" +
		"        User updatedUser = userRepository.findOneByLogin(\"change-password-wrong-existing-password\").orElse(null);\r\n" +
		"        assertThat(passwordEncoder.matches(\"new password\", updatedUser.getPassword())).isFalse();\r\n" +
		"        assertThat(passwordEncoder.matches(currentPassword, updatedUser.getPassword())).isTrue();\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    @WithMockUser(\"change-password\")\r\n" +
		"    public void testChangePassword() throws Exception {\r\n" +
		"        User user = new User();\r\n" +
		"        String currentPassword = RandomStringUtils.random(60);\r\n" +
		"        user.setPassword(passwordEncoder.encode(currentPassword));\r\n" +
		"        user.setLogin(\"change-password\");\r\n" +
		"        user.setEmail(\"change-password@example.com\");\r\n" +
		"        userRepository.saveAndFlush(user);\r\n" +
		"        restMvc.perform(post(\"/api/account/change-password\")\r\n" +
		"            .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"            .content(TestUtil.convertObjectToJsonBytes(new PasswordChangeDTO(currentPassword, \"new password\"))))\r\n" +
		"            .andExpect(status().isOk());\r\n" +
		"        User updatedUser = userRepository.findOneByLogin(\"change-password\").orElse(null);\r\n" +
		"        assertThat(passwordEncoder.matches(\"new password\", updatedUser.getPassword())).isTrue();\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    @WithMockUser(\"change-password-too-small\")\r\n" +
		"    public void testChangePasswordTooSmall() throws Exception {\r\n" +
		"        User user = new User();\r\n" +
		"        String currentPassword = RandomStringUtils.random(60);\r\n" +
		"        user.setPassword(passwordEncoder.encode(currentPassword));\r\n" +
		"        user.setLogin(\"change-password-too-small\");\r\n" +
		"        user.setEmail(\"change-password-too-small@example.com\");\r\n" +
		"        userRepository.saveAndFlush(user);\r\n" +
		"        restMvc.perform(post(\"/api/account/change-password\")\r\n" +
		"            .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"            .content(TestUtil.convertObjectToJsonBytes(new PasswordChangeDTO(currentPassword, \"new\"))))\r\n" +
		"            .andExpect(status().isBadRequest());\r\n" +
		"        User updatedUser = userRepository.findOneByLogin(\"change-password-too-small\").orElse(null);\r\n" +
		"        assertThat(updatedUser.getPassword()).isEqualTo(user.getPassword());\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    @WithMockUser(\"change-password-too-long\")\r\n" +
		"    public void testChangePasswordTooLong() throws Exception {\r\n" +
		"        User user = new User();\r\n" +
		"        String currentPassword = RandomStringUtils.random(60);\r\n" +
		"        user.setPassword(passwordEncoder.encode(currentPassword));\r\n" +
		"        user.setLogin(\"change-password-too-long\");\r\n" +
		"        user.setEmail(\"change-password-too-long@example.com\");\r\n" +
		"        userRepository.saveAndFlush(user);\r\n" +
		"        restMvc.perform(post(\"/api/account/change-password\")\r\n" +
		"            .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"            .content(TestUtil.convertObjectToJsonBytes(new PasswordChangeDTO(currentPassword, RandomStringUtils.random(101)))))\r\n" +
		"            .andExpect(status().isBadRequest());\r\n" +
		"        User updatedUser = userRepository.findOneByLogin(\"change-password-too-long\").orElse(null);\r\n" +
		"        assertThat(updatedUser.getPassword()).isEqualTo(user.getPassword());\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    @WithMockUser(\"change-password-empty\")\r\n" +
		"    public void testChangePasswordEmpty() throws Exception {\r\n" +
		"        User user = new User();\r\n" +
		"        user.setPassword(RandomStringUtils.random(60));\r\n" +
		"        user.setLogin(\"change-password-empty\");\r\n" +
		"        user.setEmail(\"change-password-empty@example.com\");\r\n" +
		"        userRepository.saveAndFlush(user);\r\n" +
		"        restMvc.perform(post(\"/api/account/change-password\").content(RandomStringUtils.random(0)))\r\n" +
		"            .andExpect(status().isBadRequest());\r\n" +
		"        User updatedUser = userRepository.findOneByLogin(\"change-password-empty\").orElse(null);\r\n" +
		"        assertThat(updatedUser.getPassword()).isEqualTo(user.getPassword());\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    @WithMockUser(\"current-sessions\")\r\n" +
		"    public void testGetCurrentSessions() throws Exception {\r\n" +
		"        User user = new User();\r\n" +
		"        user.setPassword(RandomStringUtils.random(60));\r\n" +
		"        user.setLogin(\"current-sessions\");\r\n" +
		"        user.setEmail(\"current-sessions@example.com\");\r\n" +
		"        userRepository.saveAndFlush(user);\r\n" +
		"        PersistentToken token = new PersistentToken();\r\n" +
		"        token.setSeries(\"current-sessions\");\r\n" +
		"        token.setUser(user);\r\n" +
		"        token.setTokenValue(\"current-session-data\");\r\n" +
		"        token.setTokenDate(LocalDate.of(2017, 3, 23));\r\n" +
		"        token.setIpAddress(\"127.0.0.1\");\r\n" +
		"        token.setUserAgent(\"Test agent\");\r\n" +
		"        persistentTokenRepository.saveAndFlush(token);\r\n" +
		"        restMvc.perform(get(\"/api/account/sessions\"))\r\n" +
		"            .andExpect(status().isOk())\r\n" +
		"            .andExpect(jsonPath(\"$.[*].series\").value(hasItem(token.getSeries())))\r\n" +
		"            .andExpect(jsonPath(\"$.[*].ipAddress\").value(hasItem(token.getIpAddress())))\r\n" +
		"            .andExpect(jsonPath(\"$.[*].userAgent\").value(hasItem(token.getUserAgent())))\r\n" +
		"            .andExpect(jsonPath(\"$.[*].tokenDate\").value(hasItem(token.getTokenDate().toString())));\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    @WithMockUser(\"invalidate-session\")\r\n" +
		"    public void testInvalidateSession() throws Exception {\r\n" +
		"        User user = new User();\r\n" +
		"        user.setPassword(RandomStringUtils.random(60));\r\n" +
		"        user.setLogin(\"invalidate-session\");\r\n" +
		"        user.setEmail(\"invalidate-session@example.com\");\r\n" +
		"        userRepository.saveAndFlush(user);\r\n" +
		"        PersistentToken token = new PersistentToken();\r\n" +
		"        token.setSeries(\"invalidate-session\");\r\n" +
		"        token.setUser(user);\r\n" +
		"        token.setTokenValue(\"invalidate-data\");\r\n" +
		"        token.setTokenDate(LocalDate.of(2017, 3, 23));\r\n" +
		"        token.setIpAddress(\"127.0.0.1\");\r\n" +
		"        token.setUserAgent(\"Test agent\");\r\n" +
		"        persistentTokenRepository.saveAndFlush(token);\r\n" +
		"        assertThat(persistentTokenRepository.findByUser(user)).hasSize(1);\r\n" +
		"        restMvc.perform(delete(\"/api/account/sessions/invalidate-session\"))\r\n" +
		"            .andExpect(status().isOk());\r\n" +
		"        assertThat(persistentTokenRepository.findByUser(user)).isEmpty();\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void testRequestPasswordReset() throws Exception {\r\n" +
		"        User user = new User();\r\n" +
		"        user.setPassword(RandomStringUtils.random(60));\r\n" +
		"        user.setActivated(true);\r\n" +
		"        user.setLogin(\"password-reset\");\r\n" +
		"        user.setEmail(\"password-reset@example.com\");\r\n" +
		"        userRepository.saveAndFlush(user);\r\n" +
		"        restMvc.perform(post(\"/api/account/reset-password/init\")\r\n" +
		"            .content(\"password-reset@example.com\"))\r\n" +
		"            .andExpect(status().isOk());\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void testRequestPasswordResetUpperCaseEmail() throws Exception {\r\n" +
		"        User user = new User();\r\n" +
		"        user.setPassword(RandomStringUtils.random(60));\r\n" +
		"        user.setActivated(true);\r\n" +
		"        user.setLogin(\"password-reset\");\r\n" +
		"        user.setEmail(\"password-reset@example.com\");\r\n" +
		"        userRepository.saveAndFlush(user);\r\n" +
		"        restMvc.perform(post(\"/api/account/reset-password/init\")\r\n" +
		"            .content(\"password-reset@EXAMPLE.COM\"))\r\n" +
		"            .andExpect(status().isOk());\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    public void testRequestPasswordResetWrongEmail() throws Exception {\r\n" +
		"        restMvc.perform(\r\n" +
		"            post(\"/api/account/reset-password/init\")\r\n" +
		"                .content(\"password-reset-wrong-email@example.com\"))\r\n" +
		"            .andExpect(status().isBadRequest());\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void testFinishPasswordReset() throws Exception {\r\n" +
		"        User user = new User();\r\n" +
		"        user.setPassword(RandomStringUtils.random(60));\r\n" +
		"        user.setLogin(\"finish-password-reset\");\r\n" +
		"        user.setEmail(\"finish-password-reset@example.com\");\r\n" +
		"        user.setResetDate(Instant.now().plusSeconds(60));\r\n" +
		"        user.setResetKey(\"reset key\");\r\n" +
		"        userRepository.saveAndFlush(user);\r\n" +
		"        KeyAndPasswordVM keyAndPassword = new KeyAndPasswordVM();\r\n" +
		"        keyAndPassword.setKey(user.getResetKey());\r\n" +
		"        keyAndPassword.setNewPassword(\"new password\");\r\n" +
		"        restMvc.perform(\r\n" +
		"            post(\"/api/account/reset-password/finish\")\r\n" +
		"                .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"                .content(TestUtil.convertObjectToJsonBytes(keyAndPassword)))\r\n" +
		"            .andExpect(status().isOk());\r\n" +
		"        User updatedUser = userRepository.findOneByLogin(user.getLogin()).orElse(null);\r\n" +
		"        assertThat(passwordEncoder.matches(keyAndPassword.getNewPassword(), updatedUser.getPassword())).isTrue();\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void testFinishPasswordResetTooSmall() throws Exception {\r\n" +
		"        User user = new User();\r\n" +
		"        user.setPassword(RandomStringUtils.random(60));\r\n" +
		"        user.setLogin(\"finish-password-reset-too-small\");\r\n" +
		"        user.setEmail(\"finish-password-reset-too-small@example.com\");\r\n" +
		"        user.setResetDate(Instant.now().plusSeconds(60));\r\n" +
		"        user.setResetKey(\"reset key too small\");\r\n" +
		"        userRepository.saveAndFlush(user);\r\n" +
		"        KeyAndPasswordVM keyAndPassword = new KeyAndPasswordVM();\r\n" +
		"        keyAndPassword.setKey(user.getResetKey());\r\n" +
		"        keyAndPassword.setNewPassword(\"foo\");\r\n" +
		"        restMvc.perform(\r\n" +
		"            post(\"/api/account/reset-password/finish\")\r\n" +
		"                .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"                .content(TestUtil.convertObjectToJsonBytes(keyAndPassword)))\r\n" +
		"            .andExpect(status().isBadRequest());\r\n" +
		"        User updatedUser = userRepository.findOneByLogin(user.getLogin()).orElse(null);\r\n" +
		"        assertThat(passwordEncoder.matches(keyAndPassword.getNewPassword(), updatedUser.getPassword())).isFalse();\r\n" +
		"    }\r\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void testFinishPasswordResetWrongKey() throws Exception {\r\n" +
		"        KeyAndPasswordVM keyAndPassword = new KeyAndPasswordVM();\r\n" +
		"        keyAndPassword.setKey(\"wrong reset key\");\r\n" +
		"        keyAndPassword.setNewPassword(\"new password\");\r\n" +
		"        restMvc.perform(\r\n" +
		"            post(\"/api/account/reset-password/finish\")\r\n" +
		"                .contentType(TestUtil.APPLICATION_JSON_UTF8)\r\n" +
		"                .content(TestUtil.convertObjectToJsonBytes(keyAndPassword)))\r\n" +
		"            .andExpect(status().isInternalServerError());\r\n" +
		"    }\r\n"+
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "AccountResourceIntTest";
	}

	public String getSourceFolder() {
		return "src/test/java";
	}

}
