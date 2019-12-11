package it.eng.generate.template.security;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateDomainUserDetailsService extends AbstractTemplate{

	public TemplateDomainUserDetailsService(DataBase database) {
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
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".User;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcRepositoryFolder()+".UserRepository;\r\n" +
		"import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;\r\n" +
		"import org.slf4j.Logger;\r\n" +
		"import org.slf4j.LoggerFactory;\r\n" +
		"import org.springframework.security.core.GrantedAuthority;\r\n" +
		"import org.springframework.security.core.authority.SimpleGrantedAuthority;\r\n" +
		"import org.springframework.security.core.userdetails.UserDetails;\r\n" +
		"import org.springframework.security.core.userdetails.UserDetailsService;\r\n" +
		"import org.springframework.security.core.userdetails.UsernameNotFoundException;\r\n" +
		"import org.springframework.stereotype.Component;\r\n" +
		"import org.springframework.transaction.annotation.Transactional;\r\n" +
		"import java.util.*;\r\n" +
		"import java.util.stream.Collectors;\r\n\n" +
		"/**\r\n" +
		" * Authenticate a user from the database.\r\n" +
		" */\r\n" +
		"@Component(\"userDetailsService\")\r\n" +
		"public class "+getClassName()+" implements UserDetailsService {\r\n" +
		"    private final Logger log = LoggerFactory.getLogger("+getClassName()+".class);\r\n" +
		"    private final UserRepository userRepository;\r\n\n" +
		"    public "+getClassName()+"(UserRepository userRepository) {\r\n" +
		"        this.userRepository = userRepository;\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    @Transactional\r\n" +
		"    public UserDetails loadUserByUsername(final String login) {\r\n" +
		"        log.debug(\"Authenticating {}\", login);\r\n" +
		"        if (new EmailValidator().isValid(login, null)) {\r\n" +
		"            return userRepository.findOneWithAuthoritiesByEmail(login)\r\n" +
		"                .map(user -> createSpringSecurityUser(login, user))\r\n" +
		"                .orElseThrow(() -> new UsernameNotFoundException(\"User with email \" + login + \" was not found in the database\"));\r\n" +
		"        }\r\n" +
		"        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);\r\n" +
		"        return userRepository.findOneWithAuthoritiesByLogin(lowercaseLogin)\r\n" +
		"            .map(user -> createSpringSecurityUser(lowercaseLogin, user))\r\n" +
		"            .orElseThrow(() -> new UsernameNotFoundException(\"User \" + lowercaseLogin + \" was not found in the database\"));\r\n" +
		"    }\r\n\n" +
		"    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, User user) {\r\n" +
		"        if (!user.getActivated()) {\r\n" +
		"            throw new UserNotActivatedException(\"User \" + lowercaseLogin + \" was not activated\");\r\n" +
		"        }\r\n" +
		"        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()\r\n" +
		"            .map(authority -> new SimpleGrantedAuthority(authority.getName()))\r\n" +
		"            .collect(Collectors.toList());\r\n" +
		"        return new org.springframework.security.core.userdetails.User(user.getLogin(),\r\n" +
		"            user.getPassword(),\r\n" +
		"            grantedAuthorities);\r\n" +
		"    }\r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "DomainUserDetailsService";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
