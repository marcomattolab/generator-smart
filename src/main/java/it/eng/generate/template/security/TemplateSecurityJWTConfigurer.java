package it.eng.generate.template.security;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateSecurityJWTConfigurer extends AbstractTemplate{

	public TemplateSecurityJWTConfigurer(DataBase database) {
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
		"import org.springframework.security.config.annotation.SecurityConfigurerAdapter;\r\n" +
		"import org.springframework.security.config.annotation.web.builders.HttpSecurity;\r\n" +
		"import org.springframework.security.web.DefaultSecurityFilterChain;\r\n" +
		"import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;\r\n\n" +
		"public class "+getClassName()+" extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {\r\n" +
		"    private TokenProvider tokenProvider;\r\n\n" +
		"    public "+getClassName()+"(TokenProvider tokenProvider) {\r\n" +
		"        this.tokenProvider = tokenProvider;\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    public void configure(HttpSecurity http) throws Exception {\r\n" +
		"        JWTFilter customFilter = new JWTFilter(tokenProvider);\r\n" +
		"        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "JWTConfigurer";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
