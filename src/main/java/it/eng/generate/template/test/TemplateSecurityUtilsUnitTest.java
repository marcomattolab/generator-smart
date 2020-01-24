package it.eng.generate.template.test;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateSecurityUtilsUnitTest extends AbstractTemplate{

	public TemplateSecurityUtilsUnitTest(DataBase database) {
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
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcSecurityFolder()+";\r\n\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcSecurityFolder()+".AuthoritiesConstants;\r\n"+
		"import org.junit.Test;\r\n" + 
		"import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;\r\n" + 
		"import org.springframework.security.core.GrantedAuthority;\r\n" + 
		"import org.springframework.security.core.authority.SimpleGrantedAuthority;\r\n" + 
		"import org.springframework.security.core.context.SecurityContext;\r\n" + 
		"import org.springframework.security.core.context.SecurityContextHolder;\r\n" + 
		"import java.util.ArrayList;\r\n" + 
		"import java.util.Collection;\r\n" + 
		"import java.util.Optional;\r\n" + 
		"import static org.assertj.core.api.Assertions.assertThat;\r\n\n" + 
		"/**\r\n" + 
		" * Test class for the SecurityUtils utility class.\r\n" + 
		" *\r\n" + 
		" * @see SecurityUtils\r\n" + 
		" */\r\n" + 
		"public class SecurityUtilsUnitTest {\r\n" + 
		"\r\n" + 
		"    @Test\r\n" + 
		"    public void testgetCurrentUserLogin() {\r\n" + 
		"        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();\r\n" + 
		"        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(\"admin\", \"admin\"));\r\n" + 
		"        SecurityContextHolder.setContext(securityContext);\r\n" + 
		"        Optional<String> login = SecurityUtils.getCurrentUserLogin();\r\n" + 
		"        assertThat(login).contains(\"admin\");\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"    @Test\r\n" + 
		"    public void testgetCurrentUserJWT() {\r\n" + 
		"        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();\r\n" + 
		"        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(\"admin\", \"token\"));\r\n" + 
		"        SecurityContextHolder.setContext(securityContext);\r\n" + 
		"        Optional<String> jwt = SecurityUtils.getCurrentUserJWT();\r\n" + 
		"        assertThat(jwt).contains(\"token\");\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"    @Test\r\n" + 
		"    public void testIsAuthenticated() {\r\n" + 
		"        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();\r\n" + 
		"        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(\"admin\", \"admin\"));\r\n" + 
		"        SecurityContextHolder.setContext(securityContext);\r\n" + 
		"        boolean isAuthenticated = SecurityUtils.isAuthenticated();\r\n" + 
		"        assertThat(isAuthenticated).isTrue();\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"    @Test\r\n" + 
		"    public void testAnonymousIsNotAuthenticated() {\r\n" + 
		"        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();\r\n" + 
		"        Collection<GrantedAuthority> authorities = new ArrayList<>();\r\n" + 
		"        authorities.add(new SimpleGrantedAuthority(AuthoritiesConstants.ANONYMOUS));\r\n" + 
		"        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(\"anonymous\", \"anonymous\", authorities));\r\n" + 
		"        SecurityContextHolder.setContext(securityContext);\r\n" + 
		"        boolean isAuthenticated = SecurityUtils.isAuthenticated();\r\n" + 
		"        assertThat(isAuthenticated).isFalse();\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"    @Test\r\n" + 
		"    public void testIsCurrentUserInRole() {\r\n" + 
		"        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();\r\n" + 
		"        Collection<GrantedAuthority> authorities = new ArrayList<>();\r\n" + 
		"        authorities.add(new SimpleGrantedAuthority(AuthoritiesConstants.USER));\r\n" + 
		"        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(\"user\", \"user\", authorities));\r\n" + 
		"        SecurityContextHolder.setContext(securityContext);\r\n" + 
		"\r\n" + 
		"        assertThat(SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.USER)).isTrue();\r\n" + 
		"        assertThat(SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)).isFalse();\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"}\r\n" + 
		"";
		return body;
	}

	public String getClassName() {
		return "SecurityUtilsUnitTest";
	}

	public String getSourceFolder() {
		return "src/test/java";
	}

}
