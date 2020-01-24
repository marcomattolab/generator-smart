package it.eng.generate.template.test;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateSecurityTokenProviderTest extends AbstractTemplate{

	public TemplateSecurityTokenProviderTest(DataBase database) {
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
		"import java.security.Key;\r\n" + 
		"import java.util.*;\r\n" + 
		"import org.junit.Before;\r\n" + 
		"import org.junit.Test;\r\n" + 
		"import org.mockito.Mockito;\r\n" + 
		"import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;\r\n" + 
		"import org.springframework.security.core.Authentication;\r\n" + 
		"import org.springframework.security.core.GrantedAuthority;\r\n" + 
		"import org.springframework.security.core.authority.SimpleGrantedAuthority;\r\n" + 
		"import org.springframework.test.util.ReflectionTestUtils;\r\n" + 
		"import io.github.jhipster.config.JHipsterProperties;\r\n" + 
		"import io.jsonwebtoken.Jwts;\r\n" + 
		"import io.jsonwebtoken.SignatureAlgorithm;\r\n" + 
		"import io.jsonwebtoken.io.Decoders;\r\n" + 
		"import io.jsonwebtoken.security.Keys;\r\n" + 
		"import static org.assertj.core.api.Assertions.assertThat;\r\n" + 

		"public class TokenProviderTest {\r\n" + 
		"\r\n" + 
		"    private final long ONE_MINUTE = 60000;\r\n" + 
		"    private Key key;\r\n" + 
		"    private JHipsterProperties jHipsterProperties;\r\n" + 
		"    private TokenProvider tokenProvider;\r\n" + 
		"\r\n" + 
		"    @Before\r\n" + 
		"    public void setup() {\r\n" + 
		"        jHipsterProperties = Mockito.mock(JHipsterProperties.class);\r\n" + 
		"        tokenProvider = new TokenProvider(jHipsterProperties);\r\n" + 
		"        key = Keys.hmacShaKeyFor(Decoders.BASE64\r\n" + 
		"            .decode(\"fd54a45s65fds737b9aafcb3412e07ed99b267f33413274720ddbb7f6c5e64e9f14075f2d7ed041592f0b7657baf8\"));\r\n" + 
		"\r\n" + 
		"        ReflectionTestUtils.setField(tokenProvider, \"key\", key);\r\n" + 
		"        ReflectionTestUtils.setField(tokenProvider, \"tokenValidityInMilliseconds\", ONE_MINUTE);\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"    @Test\r\n" + 
		"    public void testReturnFalseWhenJWThasInvalidSignature() {\r\n" + 
		"        boolean isTokenValid = tokenProvider.validateToken(createTokenWithDifferentSignature());\r\n" + 
		"\r\n" + 
		"        assertThat(isTokenValid).isEqualTo(false);\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"    @Test\r\n" + 
		"    public void testReturnFalseWhenJWTisMalformed() {\r\n" + 
		"        Authentication authentication = createAuthentication();\r\n" + 
		"        String token = tokenProvider.createToken(authentication, false);\r\n" + 
		"        String invalidToken = token.substring(1);\r\n" + 
		"        boolean isTokenValid = tokenProvider.validateToken(invalidToken);\r\n" + 
		"\r\n" + 
		"        assertThat(isTokenValid).isEqualTo(false);\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"    @Test\r\n" + 
		"    public void testReturnFalseWhenJWTisExpired() {\r\n" + 
		"        ReflectionTestUtils.setField(tokenProvider, \"tokenValidityInMilliseconds\", -ONE_MINUTE);\r\n" + 
		"\r\n" + 
		"        Authentication authentication = createAuthentication();\r\n" + 
		"        String token = tokenProvider.createToken(authentication, false);\r\n" + 
		"\r\n" + 
		"        boolean isTokenValid = tokenProvider.validateToken(token);\r\n" + 
		"\r\n" + 
		"        assertThat(isTokenValid).isEqualTo(false);\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"    @Test\r\n" + 
		"    public void testReturnFalseWhenJWTisUnsupported() {\r\n" + 
		"        String unsupportedToken = createUnsupportedToken();\r\n" + 
		"\r\n" + 
		"        boolean isTokenValid = tokenProvider.validateToken(unsupportedToken);\r\n" + 
		"\r\n" + 
		"        assertThat(isTokenValid).isEqualTo(false);\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"    @Test\r\n" + 
		"    public void testReturnFalseWhenJWTisInvalid() {\r\n" + 
		"        boolean isTokenValid = tokenProvider.validateToken(\"\");\r\n" + 
		"\r\n" + 
		"        assertThat(isTokenValid).isEqualTo(false);\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"    private Authentication createAuthentication() {\r\n" + 
		"        Collection<GrantedAuthority> authorities = new ArrayList<>();\r\n" + 
		"        authorities.add(new SimpleGrantedAuthority(AuthoritiesConstants.ANONYMOUS));\r\n" + 
		"        return new UsernamePasswordAuthenticationToken(\"anonymous\", \"anonymous\", authorities);\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"    private String createUnsupportedToken() {\r\n" + 
		"        return Jwts.builder()\r\n" + 
		"            .setPayload(\"payload\")\r\n" + 
		"            .signWith(key, SignatureAlgorithm.HS512)\r\n" + 
		"            .compact();\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"    private String createTokenWithDifferentSignature() {\r\n" + 
		"        Key otherKey = Keys.hmacShaKeyFor(Decoders.BASE64\r\n" + 
		"            .decode(\"Xfd54a45s65fds737b9aafcb3412e07ed99b267f33413274720ddbb7f6c5e64e9f14075f2d7ed041592f0b7657baf8\"));\r\n" + 
		"\r\n" + 
		"        return Jwts.builder()\r\n" + 
		"            .setSubject(\"anonymous\")\r\n" + 
		"            .signWith(otherKey, SignatureAlgorithm.HS512)\r\n" + 
		"            .setExpiration(new Date(new Date().getTime() + ONE_MINUTE))\r\n" + 
		"            .compact();\r\n" + 
		"    }\r\n" + 
		"}\r\n" + 
		"";
		return body;
	}

	public String getClassName() {
		return "TokenProviderTest";
	}

	public String getSourceFolder() {
		return "src/test/java";
	}

}
