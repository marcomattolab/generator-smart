package it.eng.generate.template.web;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateUserJWTController extends AbstractTemplate{

	public TemplateUserJWTController(DataBase dataBase) {
		super(dataBase);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcWebRestFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String  body = "package "+ conf.getPackageclass() + "." + conf.getSrcWebRestFolder()+";\r\n\n" +
		"import javax.validation.Valid;\r\n" +
		"import org.springframework.http.HttpHeaders;\r\n" +
		"import org.springframework.http.HttpStatus;\r\n" +
		"import org.springframework.http.ResponseEntity;\r\n" +
		"import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;\r\n" +
		"import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;\r\n" +
		"import org.springframework.security.core.Authentication;\r\n" +
		"import org.springframework.security.core.context.SecurityContextHolder;\r\n" +
		"import org.springframework.web.bind.annotation.PostMapping;\r\n" +
		"import org.springframework.web.bind.annotation.RequestBody;\r\n" +
		"import org.springframework.web.bind.annotation.RequestMapping;\r\n" +
		"import org.springframework.web.bind.annotation.RestController;\r\n" +
		"import com.fasterxml.jackson.annotation.JsonProperty;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcSecurityJWTFolder()+ ".JWTFilter;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcSecurityJWTFolder()+ ".TokenProvider;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcWebRestVmFolder()+ ".LoginVM;\r\n\n" +
		"/**\r\n" +
		" * Controller to authenticate users.\r\n" +
		" */\r\n" +
		"@RestController\r\n" +
		"@RequestMapping(\"/api\")\r\n" +
		"public class UserJWTController {\r\n" +
		"    private final TokenProvider tokenProvider;\r\n" +
		"    private final AuthenticationManagerBuilder authenticationManagerBuilder;\r\n\n" +
		"    public UserJWTController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {\r\n" +
		"        this.tokenProvider = tokenProvider;\r\n" +
		"        this.authenticationManagerBuilder = authenticationManagerBuilder;\r\n" +
		"    }\r\n\n" +
		"    @PostMapping(\"/authenticate\")\r\n" +
		"    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginVM loginVM) {\r\n" +
		"        UsernamePasswordAuthenticationToken authenticationToken =\r\n" +
		"            new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());\r\n" +
		"        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);\r\n" +
		"        SecurityContextHolder.getContext().setAuthentication(authentication);\r\n" +
		"        boolean rememberMe = (loginVM.isRememberMe() == null) ? false : loginVM.isRememberMe();\r\n" +
		"        String jwt = tokenProvider.createToken(authentication, rememberMe);\r\n" +
		"        HttpHeaders httpHeaders = new HttpHeaders();\r\n" +
		"        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, \"Bearer \" + jwt);\r\n" +
		"        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Object to return as body in JWT Authentication.\r\n" +
		"     */\r\n" +
		"    static class JWTToken {\r\n" +
		"        private String idToken;\r\n" +
		"        JWTToken(String idToken) {\r\n" +
		"            this.idToken = idToken;\r\n" +
		"        }\r\n" +
		"        @JsonProperty(\"id_token\")\r\n" +
		"        String getIdToken() {\r\n" +
		"            return idToken;\r\n" +
		"        }\r\n" +
		"        void setIdToken(String idToken) {\r\n" +
		"            this.idToken = idToken;\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"}\r\n";

		return body;
	}

	public String getClassName() {
		return "UserJWTController";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
