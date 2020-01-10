package it.eng.generate.template.security;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateSecurityTokenProvider extends AbstractTemplate{

	public TemplateSecurityTokenProvider(DataBase database) {
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
		"import java.nio.charset.StandardCharsets;\r\n" +
		"import java.security.Key;\r\n" +
		"import java.util.Arrays;\r\n" +
		"import java.util.Collection;\r\n" +
		"import java.util.Date;\r\n" +
		"import java.util.stream.Collectors;\r\n" +
		"import org.slf4j.Logger;\r\n" +
		"import org.slf4j.LoggerFactory;\r\n" +
		"import org.springframework.beans.factory.InitializingBean;\r\n" +
		"import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;\r\n" +
		"import org.springframework.security.core.Authentication;\r\n" +
		"import org.springframework.security.core.GrantedAuthority;\r\n" +
		"import org.springframework.security.core.authority.SimpleGrantedAuthority;\r\n" +
		"import org.springframework.security.core.userdetails.User;\r\n" +
		"import org.springframework.stereotype.Component;\r\n" +
		"import org.springframework.util.StringUtils;\r\n" +
		"import io.github.jhipster.config.JHipsterProperties;\r\n" +
		"import io.jsonwebtoken.*;\r\n" +
		"import io.jsonwebtoken.io.Decoders;\r\n" +
		"import io.jsonwebtoken.security.Keys;\r\n\n" +
		"@Component\r\n" +
		"public class TokenProvider implements InitializingBean {\r\n" +
		"    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);\r\n" +
		"    private static final String AUTHORITIES_KEY = \"auth\";\r\n" +
		"    private Key key;\r\n" +
		"    private long tokenValidityInMilliseconds;\r\n" +
		"    private long tokenValidityInMillisecondsForRememberMe;\r\n" +
		"    private final JHipsterProperties jHipsterProperties;\r\n\n" +
		"    public TokenProvider(JHipsterProperties jHipsterProperties) {\r\n" +
		"        this.jHipsterProperties = jHipsterProperties;\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    public void afterPropertiesSet() throws Exception {\r\n" +
		"        byte[] keyBytes;\r\n" +
		"        String secret = jHipsterProperties.getSecurity().getAuthentication().getJwt().getSecret();\r\n" +
		"        if (!StringUtils.isEmpty(secret)) {\r\n" +
		"            log.warn(\"Warning: the JWT key used is not Base64-encoded. \" +\r\n" +
		"                \"We recommend using the `jhipster.security.authentication.jwt.base64-secret` key for optimum security.\");\r\n" +
		"            keyBytes = secret.getBytes(StandardCharsets.UTF_8);\r\n" +
		"        } else {\r\n" +
		"            log.debug(\"Using a Base64-encoded JWT secret key\");\r\n" +
		"            keyBytes = Decoders.BASE64.decode(jHipsterProperties.getSecurity().getAuthentication().getJwt().getBase64Secret());\r\n" +
		"        }\r\n" +
		"        this.key = Keys.hmacShaKeyFor(keyBytes);\r\n" +
		"        this.tokenValidityInMilliseconds =\r\n" +
		"            1000 * jHipsterProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSeconds();\r\n" +
		"        this.tokenValidityInMillisecondsForRememberMe =\r\n" +
		"            1000 * jHipsterProperties.getSecurity().getAuthentication().getJwt()\r\n" +
		"                .getTokenValidityInSecondsForRememberMe();\r\n" +
		"    }\r\n\n" +
		"    public String createToken(Authentication authentication, boolean rememberMe) {\r\n" +
		"        String authorities = authentication.getAuthorities().stream()\r\n" +
		"            .map(GrantedAuthority::getAuthority)\r\n" +
		"            .collect(Collectors.joining(\",\"));\r\n" +
		"        long now = (new Date()).getTime();\r\n" +
		"        Date validity;\r\n" +
		"        if (rememberMe) {\r\n" +
		"            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);\r\n" +
		"        } else {\r\n" +
		"            validity = new Date(now + this.tokenValidityInMilliseconds);\r\n" +
		"        }\r\n" +
		"        return Jwts.builder()\r\n" +
		"            .setSubject(authentication.getName())\r\n" +
		"            .claim(AUTHORITIES_KEY, authorities)\r\n" +
		"            .signWith(key, SignatureAlgorithm.HS512)\r\n" +
		"            .setExpiration(validity)\r\n" +
		"            .compact();\r\n" +
		"    }\r\n\n" +
		"    public Authentication getAuthentication(String token) {\r\n" +
		"        Claims claims = Jwts.parser()\r\n" +
		"            .setSigningKey(key)\r\n" +
		"            .parseClaimsJws(token)\r\n" +
		"            .getBody();\r\n" +
		"        Collection<? extends GrantedAuthority> authorities =\r\n" +
		"            Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(\",\"))\r\n" +
		"                .map(SimpleGrantedAuthority::new)\r\n" +
		"                .collect(Collectors.toList());\r\n" +
		"        User principal = new User(claims.getSubject(), \"\", authorities);\r\n" +
		"        return new UsernamePasswordAuthenticationToken(principal, token, authorities);\r\n" +
		"    }\r\n\n" +
		"    public boolean validateToken(String authToken) {\r\n" +
		"        try {\r\n" +
		"            Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);\r\n" +
		"            return true;\r\n" +
		"        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {\r\n" +
		"            log.info(\"Invalid JWT signature.\");\r\n" +
		"            log.trace(\"Invalid JWT signature trace: {}\", e);\r\n" +
		"        } catch (ExpiredJwtException e) {\r\n" +
		"            log.info(\"Expired JWT token.\");\r\n" +
		"            log.trace(\"Expired JWT token trace: {}\", e);\r\n" +
		"        } catch (UnsupportedJwtException e) {\r\n" +
		"            log.info(\"Unsupported JWT token.\");\r\n" +
		"            log.trace(\"Unsupported JWT token trace: {}\", e);\r\n" +
		"        } catch (IllegalArgumentException e) {\r\n" +
		"            log.info(\"JWT token compact of handler are invalid.\");\r\n" +
		"            log.trace(\"JWT token compact of handler are invalid trace: {}\", e);\r\n" +
		"        }\r\n" +
		"        return false;\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		
		
		return body;
	}

	public String getClassName() {
		return "TokenProvider";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
