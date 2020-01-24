package it.eng.generate.template.test;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateSecurityJWTFilterTest extends AbstractTemplate{

	public TemplateSecurityJWTFilterTest(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcSecurityJWTFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcSecurityJWTFolder()+";\r\n\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcSecurityFolder()+".AuthoritiesConstants;\r\n"+
		"import io.github.jhipster.config.JHipsterProperties;\r\n" + 
		"import io.jsonwebtoken.io.Decoders;\r\n" + 
		"import io.jsonwebtoken.security.Keys;\r\n" + 
		"import org.junit.Before;\r\n" + 
		"import org.junit.Test;\r\n" + 
		"import org.springframework.http.HttpStatus;\r\n" + 
		"import org.springframework.mock.web.MockFilterChain;\r\n" + 
		"import org.springframework.mock.web.MockHttpServletRequest;\r\n" + 
		"import org.springframework.mock.web.MockHttpServletResponse;\r\n" + 
		"import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;\r\n" + 
		"import org.springframework.security.core.authority.SimpleGrantedAuthority;\r\n" + 
		"import org.springframework.security.core.context.SecurityContextHolder;\r\n" + 
		"import org.springframework.test.util.ReflectionTestUtils;\r\n" + 
		"import java.util.Collections;\r\n" + 
		"import static org.assertj.core.api.Assertions.assertThat;\r\n\n" + 
		"\r\n" + 
		"public class JWTFilterTest {\r\n" + 
		"\r\n" + 
		"    private TokenProvider tokenProvider;\r\n" + 
		"\r\n" + 
		"    private JWTFilter jwtFilter;\r\n" + 
		"\r\n" + 
		"    @Before\r\n" + 
		"    public void setup() {\r\n" + 
		"        JHipsterProperties jHipsterProperties = new JHipsterProperties();\r\n" + 
		"        tokenProvider = new TokenProvider(jHipsterProperties);\r\n" + 
		"        ReflectionTestUtils.setField(tokenProvider, \"key\",\r\n" + 
		"            Keys.hmacShaKeyFor(Decoders.BASE64\r\n" + 
		"                .decode(\"fd54a45s65fds737b9aafcb3412e07ed99b267f33413274720ddbb7f6c5e64e9f14075f2d7ed041592f0b7657baf8\")));\r\n" + 
		"\r\n" + 
		"        ReflectionTestUtils.setField(tokenProvider, \"tokenValidityInMilliseconds\", 60000);\r\n" + 
		"        jwtFilter = new JWTFilter(tokenProvider);\r\n" + 
		"        SecurityContextHolder.getContext().setAuthentication(null);\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"    @Test\r\n" + 
		"    public void testJWTFilter() throws Exception {\r\n" + 
		"        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(\r\n" + 
		"            \"test-user\",\r\n" + 
		"            \"test-password\",\r\n" + 
		"            Collections.singletonList(new SimpleGrantedAuthority(AuthoritiesConstants.USER))\r\n" + 
		"        );\r\n" + 
		"        String jwt = tokenProvider.createToken(authentication, false);\r\n" + 
		"        MockHttpServletRequest request = new MockHttpServletRequest();\r\n" + 
		"        request.addHeader(JWTFilter.AUTHORIZATION_HEADER, \"Bearer \" + jwt);\r\n" + 
		"        request.setRequestURI(\"/api/test\");\r\n" + 
		"        MockHttpServletResponse response = new MockHttpServletResponse();\r\n" + 
		"        MockFilterChain filterChain = new MockFilterChain();\r\n" + 
		"        jwtFilter.doFilter(request, response, filterChain);\r\n" + 
		"        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());\r\n" + 
		"        assertThat(SecurityContextHolder.getContext().getAuthentication().getName()).isEqualTo(\"test-user\");\r\n" + 
		"        assertThat(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString()).isEqualTo(jwt);\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"    @Test\r\n" + 
		"    public void testJWTFilterInvalidToken() throws Exception {\r\n" + 
		"        String jwt = \"wrong_jwt\";\r\n" + 
		"        MockHttpServletRequest request = new MockHttpServletRequest();\r\n" + 
		"        request.addHeader(JWTFilter.AUTHORIZATION_HEADER, \"Bearer \" + jwt);\r\n" + 
		"        request.setRequestURI(\"/api/test\");\r\n" + 
		"        MockHttpServletResponse response = new MockHttpServletResponse();\r\n" + 
		"        MockFilterChain filterChain = new MockFilterChain();\r\n" + 
		"        jwtFilter.doFilter(request, response, filterChain);\r\n" + 
		"        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());\r\n" + 
		"        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"    @Test\r\n" + 
		"    public void testJWTFilterMissingAuthorization() throws Exception {\r\n" + 
		"        MockHttpServletRequest request = new MockHttpServletRequest();\r\n" + 
		"        request.setRequestURI(\"/api/test\");\r\n" + 
		"        MockHttpServletResponse response = new MockHttpServletResponse();\r\n" + 
		"        MockFilterChain filterChain = new MockFilterChain();\r\n" + 
		"        jwtFilter.doFilter(request, response, filterChain);\r\n" + 
		"        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());\r\n" + 
		"        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"    @Test\r\n" + 
		"    public void testJWTFilterMissingToken() throws Exception {\r\n" + 
		"        MockHttpServletRequest request = new MockHttpServletRequest();\r\n" + 
		"        request.addHeader(JWTFilter.AUTHORIZATION_HEADER, \"Bearer \");\r\n" + 
		"        request.setRequestURI(\"/api/test\");\r\n" + 
		"        MockHttpServletResponse response = new MockHttpServletResponse();\r\n" + 
		"        MockFilterChain filterChain = new MockFilterChain();\r\n" + 
		"        jwtFilter.doFilter(request, response, filterChain);\r\n" + 
		"        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());\r\n" + 
		"        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"    @Test\r\n" + 
		"    public void testJWTFilterWrongScheme() throws Exception {\r\n" + 
		"        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(\r\n" + 
		"            \"test-user\",\r\n" + 
		"            \"test-password\",\r\n" + 
		"            Collections.singletonList(new SimpleGrantedAuthority(AuthoritiesConstants.USER))\r\n" + 
		"        );\r\n" + 
		"        String jwt = tokenProvider.createToken(authentication, false);\r\n" + 
		"        MockHttpServletRequest request = new MockHttpServletRequest();\r\n" + 
		"        request.addHeader(JWTFilter.AUTHORIZATION_HEADER, \"Basic \" + jwt);\r\n" + 
		"        request.setRequestURI(\"/api/test\");\r\n" + 
		"        MockHttpServletResponse response = new MockHttpServletResponse();\r\n" + 
		"        MockFilterChain filterChain = new MockFilterChain();\r\n" + 
		"        jwtFilter.doFilter(request, response, filterChain);\r\n" + 
		"        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());\r\n" + 
		"        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"}\r\n" + 
		"";
		return body;
	}

	public String getClassName() {
		return "JWTFilterTest";
	}

	public String getSourceFolder() {
		return "src/test/java";
	}

}
