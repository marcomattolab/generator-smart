package it.eng.generate.template.test;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateDomainUserDetailsServiceIntTest extends AbstractTemplate{

	public TemplateDomainUserDetailsServiceIntTest(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcSecurityFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcSecurityFolder()+";\r\n\n" +
		"import "+ conf.getPackageclass() + "." + Utils.getClassNameCamelCase(conf.getProjectName()) + conf.getApp() +";\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".User;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcRepositoryFolder()+".UserRepository;\r\n" +
		"import org.apache.commons.lang3.RandomStringUtils;\r\n" +
		"import org.junit.Before;\r\n" +
		"import org.junit.Test;\r\n" +
		"import org.junit.runner.RunWith;\r\n" +
		"import org.springframework.beans.factory.annotation.Autowired;\r\n" +
		"import org.springframework.boot.test.context.SpringBootTest;\r\n" +
		"import org.springframework.security.core.userdetails.UserDetails;\r\n" +
		"import org.springframework.security.core.userdetails.UserDetailsService;\r\n" +
		"import org.springframework.security.core.userdetails.UsernameNotFoundException;\r\n" +
		"import org.springframework.test.context.junit4.SpringRunner;\r\n" +
		"import org.springframework.transaction.annotation.Transactional;\r\n" +
		"import java.util.Locale;\r\n" +
		"import static org.assertj.core.api.Assertions.assertThat;\r\n\n" +
		"/**\r\n" +
		" * Test class for DomainUserDetailsService.\r\n" +
		" *\r\n" +
		" * @see DomainUserDetailsService\r\n" +
		" */\r\n" +
		"@RunWith(SpringRunner.class)\r\n" +
		"@SpringBootTest(classes = "+Utils.getClassNameCamelCase(conf.getProjectName()) + conf.getApp() +".class)\r\n" +
		"@Transactional\r\n" +
		"public class DomainUserDetailsServiceIntTest {\r\n" +
		"    private static final String USER_ONE_LOGIN = \"test-user-one\";\r\n" +
		"    private static final String USER_ONE_EMAIL = \"test-user-one@localhost\";\r\n" +
		"    private static final String USER_TWO_LOGIN = \"test-user-two\";\r\n" +
		"    private static final String USER_TWO_EMAIL = \"test-user-two@localhost\";\r\n" +
		"    private static final String USER_THREE_LOGIN = \"test-user-three\";\r\n" +
		"    private static final String USER_THREE_EMAIL = \"test-user-three@localhost\";\r\n\n" +
		"    @Autowired\r\n" +
		"    private UserRepository userRepository;\r\n" +
		"    @Autowired\r\n" +
		"    private UserDetailsService domainUserDetailsService;\r\n" +
		"    private User userOne;\r\n" +
		"    private User userTwo;\r\n" +
		"    private User userThree;\r\n\n" +
		"    @Before\r\n" +
		"    public void init() {\r\n" +
		"        userOne = new User();\r\n" +
		"        userOne.setLogin(USER_ONE_LOGIN);\r\n" +
		"        userOne.setPassword(RandomStringUtils.random(60));\r\n" +
		"        userOne.setActivated(true);\r\n" +
		"        userOne.setEmail(USER_ONE_EMAIL);\r\n" +
		"        userOne.setFirstName(\"userOne\");\r\n" +
		"        userOne.setLastName(\"doe\");\r\n" +
		"        userOne.setLangKey(\"en\");\r\n" +
		"        userRepository.save(userOne);\r\n" +
		"        userTwo = new User();\r\n" +
		"        userTwo.setLogin(USER_TWO_LOGIN);\r\n" +
		"        userTwo.setPassword(RandomStringUtils.random(60));\r\n" +
		"        userTwo.setActivated(true);\r\n" +
		"        userTwo.setEmail(USER_TWO_EMAIL);\r\n" +
		"        userTwo.setFirstName(\"userTwo\");\r\n" +
		"        userTwo.setLastName(\"doe\");\r\n" +
		"        userTwo.setLangKey(\"en\");\r\n" +
		"        userRepository.save(userTwo);\r\n" +
		"        userThree = new User();\r\n" +
		"        userThree.setLogin(USER_THREE_LOGIN);\r\n" +
		"        userThree.setPassword(RandomStringUtils.random(60));\r\n" +
		"        userThree.setActivated(false);\r\n" +
		"        userThree.setEmail(USER_THREE_EMAIL);\r\n" +
		"        userThree.setFirstName(\"userThree\");\r\n" +
		"        userThree.setLastName(\"doe\");\r\n" +
		"        userThree.setLangKey(\"en\");\r\n" +
		"        userRepository.save(userThree);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void assertThatUserCanBeFoundByLogin() {\r\n" +
		"        UserDetails userDetails = domainUserDetailsService.loadUserByUsername(USER_ONE_LOGIN);\r\n" +
		"        assertThat(userDetails).isNotNull();\r\n" +
		"        assertThat(userDetails.getUsername()).isEqualTo(USER_ONE_LOGIN);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void assertThatUserCanBeFoundByLoginIgnoreCase() {\r\n" +
		"        UserDetails userDetails = domainUserDetailsService.loadUserByUsername(USER_ONE_LOGIN.toUpperCase(Locale.ENGLISH));\r\n" +
		"        assertThat(userDetails).isNotNull();\r\n" +
		"        assertThat(userDetails.getUsername()).isEqualTo(USER_ONE_LOGIN);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void assertThatUserCanBeFoundByEmail() {\r\n" +
		"        UserDetails userDetails = domainUserDetailsService.loadUserByUsername(USER_TWO_EMAIL);\r\n" +
		"        assertThat(userDetails).isNotNull();\r\n" +
		"        assertThat(userDetails.getUsername()).isEqualTo(USER_TWO_LOGIN);\r\n" +
		"    }\r\n\n" +
		"    @Test(expected = UsernameNotFoundException.class)\r\n" +
		"    @Transactional\r\n" +
		"    public void assertThatUserCanNotBeFoundByEmailIgnoreCase() {\r\n" +
		"    domainUserDetailsService.loadUserByUsername(USER_TWO_EMAIL.toUpperCase(Locale.ENGLISH));\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    @Transactional\r\n" +
		"    public void assertThatEmailIsPrioritizedOverLogin() {\r\n" +
		"        UserDetails userDetails = domainUserDetailsService.loadUserByUsername(USER_ONE_EMAIL);\r\n" +
		"        assertThat(userDetails).isNotNull();\r\n" +
		"        assertThat(userDetails.getUsername()).isEqualTo(USER_ONE_LOGIN);\r\n" +
		"    }\r\n\n" +
		"    @Test(expected = UserNotActivatedException.class)\r\n" +
		"    @Transactional\r\n" +
		"    public void assertThatUserNotActivatedExceptionIsThrownForNotActivatedUsers() {\r\n" +
		"        domainUserDetailsService.loadUserByUsername(USER_THREE_LOGIN);\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "DomainUserDetailsServiceIntTest";
	}

	public String getSourceFolder() {
		return "src/test/java";
	}

}
