package it.eng.generate.template.security;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateSecurityJWTFilter extends AbstractTemplate{

	public TemplateSecurityJWTFilter(DataBase database) {
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
		"import org.springframework.security.core.Authentication;\r\n" +
		"import org.springframework.security.core.context.SecurityContextHolder;\r\n" +
		"import org.springframework.util.StringUtils;\r\n" +
		"import org.springframework.web.filter.GenericFilterBean;\r\n" +
		"import javax.servlet.FilterChain;\r\n" +
		"import javax.servlet.ServletException;\r\n" +
		"import javax.servlet.ServletRequest;\r\n" +
		"import javax.servlet.ServletResponse;\r\n" +
		"import javax.servlet.http.HttpServletRequest;\r\n" +
		"import java.io.IOException;\r\n\n" +
		"/**\r\n" +
		" * Filters incoming requests and installs a Spring Security principal if a header corresponding to a valid user is found.\r\n" +
		" */\r\n" +
		"public class "+getClassName()+" extends GenericFilterBean {\r\n" +
		"    public static final String AUTHORIZATION_HEADER = \"Authorization\";\r\n\n" +
		"    private TokenProvider tokenProvider;\r\n\n" +
		"    public "+getClassName()+"(TokenProvider tokenProvider) {\r\n" +
		"        this.tokenProvider = tokenProvider;\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)\r\n" +
		"        throws IOException, ServletException {\r\n" +
		"        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;\r\n" +
		"        String jwt = resolveToken(httpServletRequest);\r\n" +
		"        if (StringUtils.hasText(jwt) && this.tokenProvider.validateToken(jwt)) {\r\n" +
		"            Authentication authentication = this.tokenProvider.getAuthentication(jwt);\r\n" +
		"            SecurityContextHolder.getContext().setAuthentication(authentication);\r\n" +
		"        }\r\n" +
		"        filterChain.doFilter(servletRequest, servletResponse);\r\n" +
		"    }\r\n\n" +
		"    private String resolveToken(HttpServletRequest request){\r\n" +
		"        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);\r\n" +
		"        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(\"Bearer \")) {\r\n" +
		"            return bearerToken.substring(7);\r\n" +
		"        }\r\n" +
		"        return null;\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "JWTFilter";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
