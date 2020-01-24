package it.eng.generate.template.test;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateUserServiceIntTest extends AbstractTemplate{

	public TemplateUserServiceIntTest(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcServiceFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcServiceFolder()+";\r\n\n" +
		"import "+ conf.getPackageclass() + "." + Utils.getClassNameCamelCase(conf.getProjectName()) + conf.getApp() +";\r\n\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcConfigFolder()+".Constants;\r\n\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".User;\r\n\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcRepositoryFolder()+".UserRepository;\r\n\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcServiceDtoFolder()+".UserDTO;\r\n\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcServiceUtilFolder()+".RandomUtil;\r\n\n" +
		"import org.apache.commons.lang3.RandomStringUtils;\r\n" + 
		"import org.junit.Before;\r\n" + 
		"import org.junit.Test;\r\n" + 
		"import org.junit.runner.RunWith;\r\n" + 
		"import org.mockito.Mock;\r\n" + 
		"import org.springframework.beans.factory.annotation.Autowired;\r\n" + 
		"import org.springframework.boot.test.context.SpringBootTest;\r\n" + 
		"import org.springframework.data.auditing.AuditingHandler;\r\n" + 
		"import org.springframework.data.auditing.DateTimeProvider;\r\n" + 
		"import org.springframework.data.domain.Page;\r\n" + 
		"import org.springframework.data.domain.PageRequest;\r\n" + 
		"import org.springframework.test.context.junit4.SpringRunner;\r\n" + 
		"import org.springframework.transaction.annotation.Transactional;\r\n" + 
		"import java.time.Instant;\r\n" + 
		"import java.time.temporal.ChronoUnit;\r\n" + 
		"import java.time.LocalDateTime;\r\n" + 
		"import java.util.Optional;\r\n" + 
		"import java.util.List;\r\n" + 
		"import static org.assertj.core.api.Assertions.assertThat;\r\n" + 
		"import static org.mockito.Mockito.when;\r\n\n" + 
		"/**\r\n" + 
		" * Test class for the UserResource REST controller.\r\n" + 
		" *\r\n" + 
		" * @see UserService\r\n" + 
		" */\r\n" + 
		"@RunWith(SpringRunner.class)\r\n" + 
		"@SpringBootTest(classes = "+Utils.getClassNameCamelCase(conf.getProjectName()) + conf.getApp() +".class)\r\n" + 
		"@Transactional\r\n" + 
		"public class UserServiceIntTest {\r\n" + 
		"\r\n" + 
		"    @Autowired\r\n" + 
		"    private UserRepository userRepository;\r\n" + 
		"\r\n" + 
		"    @Autowired\r\n" + 
		"    private UserService userService;\r\n" + 
		"\r\n" + 
		"    @Autowired\r\n" + 
		"    private AuditingHandler auditingHandler;\r\n" + 
		"\r\n" + 
		"    @Mock\r\n" + 
		"    DateTimeProvider dateTimeProvider;\r\n" + 
		"\r\n" + 
		"    private User user;\r\n" + 
		"\r\n" + 
		"    @Before\r\n" + 
		"    public void init() {\r\n" + 
		"        user = new User();\r\n" + 
		"        user.setLogin(\"johndoe\");\r\n" + 
		"        user.setPassword(RandomStringUtils.random(60));\r\n" + 
		"        user.setActivated(true);\r\n" + 
		"        user.setEmail(\"johndoe@localhost\");\r\n" + 
		"        user.setFirstName(\"john\");\r\n" + 
		"        user.setLastName(\"doe\");\r\n" + 
		"        user.setImageUrl(\"http://placehold.it/50x50\");\r\n" + 
		"        user.setLangKey(\"en\");\r\n" + 
		"\r\n" + 
		"        when(dateTimeProvider.getNow()).thenReturn(Optional.of(LocalDateTime.now()));\r\n" + 
		"        auditingHandler.setDateTimeProvider(dateTimeProvider);\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"    @Test\r\n" + 
		"    @Transactional\r\n" + 
		"    public void assertThatUserMustExistToResetPassword() {\r\n" + 
		"        userRepository.saveAndFlush(user);\r\n" + 
		"        Optional<User> maybeUser = userService.requestPasswordReset(\"invalid.login@localhost\");\r\n" + 
		"        assertThat(maybeUser).isNotPresent();\r\n" + 
		"\r\n" + 
		"        maybeUser = userService.requestPasswordReset(user.getEmail());\r\n" + 
		"        assertThat(maybeUser).isPresent();\r\n" + 
		"        assertThat(maybeUser.orElse(null).getEmail()).isEqualTo(user.getEmail());\r\n" + 
		"        assertThat(maybeUser.orElse(null).getResetDate()).isNotNull();\r\n" + 
		"        assertThat(maybeUser.orElse(null).getResetKey()).isNotNull();\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"    @Test\r\n" + 
		"    @Transactional\r\n" + 
		"    public void assertThatOnlyActivatedUserCanRequestPasswordReset() {\r\n" + 
		"        user.setActivated(false);\r\n" + 
		"        userRepository.saveAndFlush(user);\r\n" + 
		"\r\n" + 
		"        Optional<User> maybeUser = userService.requestPasswordReset(user.getLogin());\r\n" + 
		"        assertThat(maybeUser).isNotPresent();\r\n" + 
		"        userRepository.delete(user);\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"    @Test\r\n" + 
		"    @Transactional\r\n" + 
		"    public void assertThatResetKeyMustNotBeOlderThan24Hours() {\r\n" + 
		"        Instant daysAgo = Instant.now().minus(25, ChronoUnit.HOURS);\r\n" + 
		"        String resetKey = RandomUtil.generateResetKey();\r\n" + 
		"        user.setActivated(true);\r\n" + 
		"        user.setResetDate(daysAgo);\r\n" + 
		"        user.setResetKey(resetKey);\r\n" + 
		"        userRepository.saveAndFlush(user);\r\n" + 
		"\r\n" + 
		"        Optional<User> maybeUser = userService.completePasswordReset(\"johndoe2\", user.getResetKey());\r\n" + 
		"        assertThat(maybeUser).isNotPresent();\r\n" + 
		"        userRepository.delete(user);\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"    @Test\r\n" + 
		"    @Transactional\r\n" + 
		"    public void assertThatResetKeyMustBeValid() {\r\n" + 
		"        Instant daysAgo = Instant.now().minus(25, ChronoUnit.HOURS);\r\n" + 
		"        user.setActivated(true);\r\n" + 
		"        user.setResetDate(daysAgo);\r\n" + 
		"        user.setResetKey(\"1234\");\r\n" + 
		"        userRepository.saveAndFlush(user);\r\n" + 
		"\r\n" + 
		"        Optional<User> maybeUser = userService.completePasswordReset(\"johndoe2\", user.getResetKey());\r\n" + 
		"        assertThat(maybeUser).isNotPresent();\r\n" + 
		"        userRepository.delete(user);\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"    @Test\r\n" + 
		"    @Transactional\r\n" + 
		"    public void assertThatUserCanResetPassword() {\r\n" + 
		"        String oldPassword = user.getPassword();\r\n" + 
		"        Instant daysAgo = Instant.now().minus(2, ChronoUnit.HOURS);\r\n" + 
		"        String resetKey = RandomUtil.generateResetKey();\r\n" + 
		"        user.setActivated(true);\r\n" + 
		"        user.setResetDate(daysAgo);\r\n" + 
		"        user.setResetKey(resetKey);\r\n" + 
		"        userRepository.saveAndFlush(user);\r\n" + 
		"\r\n" + 
		"        Optional<User> maybeUser = userService.completePasswordReset(\"johndoe2\", user.getResetKey());\r\n" + 
		"        assertThat(maybeUser).isPresent();\r\n" + 
		"        assertThat(maybeUser.orElse(null).getResetDate()).isNull();\r\n" + 
		"        assertThat(maybeUser.orElse(null).getResetKey()).isNull();\r\n" + 
		"        assertThat(maybeUser.orElse(null).getPassword()).isNotEqualTo(oldPassword);\r\n" + 
		"\r\n" + 
		"        userRepository.delete(user);\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"    @Test\r\n" + 
		"    @Transactional\r\n" + 
		"    public void testFindNotActivatedUsersByCreationDateBefore() {\r\n" + 
		"        Instant now = Instant.now();\r\n" + 
		"        when(dateTimeProvider.getNow()).thenReturn(Optional.of(now.minus(4, ChronoUnit.DAYS)));\r\n" + 
		"        user.setActivated(false);\r\n" + 
		"        User dbUser = userRepository.saveAndFlush(user);\r\n" + 
		"        dbUser.setCreatedDate(now.minus(4, ChronoUnit.DAYS));\r\n" + 
		"        userRepository.saveAndFlush(user);\r\n" + 
		"        List<User> users = userRepository.findAllByActivatedIsFalseAndCreatedDateBefore(now.minus(3, ChronoUnit.DAYS));\r\n" + 
		"        assertThat(users).isNotEmpty();\r\n" + 
		"        userService.removeNotActivatedUsers();\r\n" + 
		"        users = userRepository.findAllByActivatedIsFalseAndCreatedDateBefore(now.minus(3, ChronoUnit.DAYS));\r\n" + 
		"        //assertThat(users).isEmpty();\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"    @Test\r\n" + 
		"    @Transactional\r\n" + 
		"    public void assertThatAnonymousUserIsNotGet() {\r\n" + 
		"        user.setLogin(Constants.ANONYMOUS_USER);\r\n" + 
		"        if (!userRepository.findOneByLogin(Constants.ANONYMOUS_USER).isPresent()) {\r\n" + 
		"            userRepository.saveAndFlush(user);\r\n" + 
		"        }\r\n" + 
		"        final PageRequest pageable = PageRequest.of(0, (int) userRepository.count());\r\n" + 
		"        final Page<UserDTO> allManagedUsers = userService.getAllManagedUsers(pageable);\r\n" + 
		"        assertThat(allManagedUsers.getContent().stream()\r\n" + 
		"            .noneMatch(user -> Constants.ANONYMOUS_USER.equals(user.getLogin())))\r\n" + 
		"            .isTrue();\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"    @Test\r\n" + 
		"    @Transactional\r\n" + 
		"    public void testRemoveNotActivatedUsers() {\r\n" + 
		"        // custom \"now\" for audit to use as creation date\r\n" + 
		"        when(dateTimeProvider.getNow()).thenReturn(Optional.of(Instant.now().minus(30, ChronoUnit.DAYS)));\r\n" + 
		"\r\n" + 
		"        user.setActivated(false);\r\n" + 
		"        userRepository.saveAndFlush(user);\r\n" + 
		"\r\n" + 
		"        assertThat(userRepository.findOneByLogin(\"johndoe\")).isPresent();\r\n" + 
		"        userService.removeNotActivatedUsers();\r\n" + 
		"        //assertThat(userRepository.findOneByLogin(\"johndoe\")).isNotPresent();\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"}\r\n" + 
		"";
		return body;
	}

	public String getClassName() {
		return "UserServiceIntTest";
	}

	public String getSourceFolder() {
		return "src/test/java";
	}

}
