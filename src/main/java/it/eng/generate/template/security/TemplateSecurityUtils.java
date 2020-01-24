package it.eng.generate.template.security;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateSecurityUtils extends AbstractTemplate{

	public TemplateSecurityUtils(DataBase database) {
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
		"import org.springframework.security.core.context.SecurityContext;\r\n" +
		"import org.springframework.security.core.context.SecurityContextHolder;\r\n" +
		"import org.springframework.security.core.userdetails.UserDetails;\r\n" +
		"import java.util.Optional;\r\n\n" +
		"/**\r\n" +
		" * Utility class for Spring Security.\r\n" +
		" */\r\n" +
		"public final class "+getClassName() +" {\r\n\n" +
		"    private "+getClassName() +"() {\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Get the login of the current user.\r\n" +
		"     *\r\n" +
		"     * @return the login of the current user\r\n" +
		"     */\r\n" +
		"    public static Optional<String> getCurrentUserLogin() {\r\n" +
		"        SecurityContext securityContext = SecurityContextHolder.getContext();\r\n" +
		"        return Optional.ofNullable(securityContext.getAuthentication())\r\n" +
		"            .map(authentication -> {\r\n" +
		"                if (authentication.getPrincipal() instanceof UserDetails) {\r\n" +
		"                    UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();\r\n" +
		"                    return springSecurityUser.getUsername();\r\n" +
		"                } else if (authentication.getPrincipal() instanceof String) {\r\n" +
		"                    return (String) authentication.getPrincipal();\r\n" +
		"                }\r\n" +
		"                return null;\r\n" +
		"            });\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" + 
		"     * Get the JWT of the current user.\r\n" + 
		"     *\r\n" + 
		"     * @return the JWT of the current user.\r\n" + 
		"     */\r\n" + 
		"    public static Optional<String> getCurrentUserJWT() {\r\n" + 
		"        SecurityContext securityContext = SecurityContextHolder.getContext();\r\n" + 
		"        return Optional.ofNullable(securityContext.getAuthentication())\r\n" + 
		"            .filter(authentication -> authentication.getCredentials() instanceof String)\r\n" + 
		"            .map(authentication -> (String) authentication.getCredentials());\r\n" + 
		"    }\n\n" +
		"    /**\r\n" +
		"     * Check if a user is authenticated.\r\n" +
		"     *\r\n" +
		"     * @return true if the user is authenticated, false otherwise\r\n" +
		"     */\r\n" +
		"    public static boolean isAuthenticated() {\r\n" +
		"        SecurityContext securityContext = SecurityContextHolder.getContext();\r\n" +
		"        return Optional.ofNullable(securityContext.getAuthentication())\r\n" +
		"            .map(authentication -> authentication.getAuthorities().stream()\r\n" +
		"                .noneMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(AuthoritiesConstants.ANONYMOUS)))\r\n" +
		"            .orElse(false);\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * If the current user has a specific authority (security role).\r\n" +
		"     * <p>\r\n" +
		"     * The name of this method comes from the isUserInRole() method in the Servlet API\r\n" +
		"     *\r\n" +
		"     * @param authority the authority to check\r\n" +
		"     * @return true if the current user has the authority, false otherwise\r\n" +
		"     */\r\n" +
		"    public static boolean isCurrentUserInRole(String authority) {\r\n" +
		"        SecurityContext securityContext = SecurityContextHolder.getContext();\r\n" +
		"        return Optional.ofNullable(securityContext.getAuthentication())\r\n" +
		"            .map(authentication -> authentication.getAuthorities().stream()\r\n" +
		"                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(authority)))\r\n" +
		"            .orElse(false);\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "SecurityUtils";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
