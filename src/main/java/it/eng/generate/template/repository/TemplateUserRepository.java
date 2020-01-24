package it.eng.generate.template.repository;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateUserRepository extends AbstractTemplate{
	private String User = "User";

	public TemplateUserRepository(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcRepositoryFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcRepositoryFolder()+";\r\n\n" +
		"import "+conf.getPackageclass() + "." + conf.getSrcDomainFolder()+"."+User+";\r\n" +
		"import org.springframework.cache.annotation.Cacheable;\r\n" +
		"import org.springframework.data.domain.Page;\r\n" +
		"import org.springframework.data.domain.Pageable;\r\n" +
		"import org.springframework.data.jpa.repository.EntityGraph;\r\n" +
		"import org.springframework.data.jpa.repository.JpaRepository;\r\n" +
		"import org.springframework.stereotype.Repository;\r\n" +
		"import java.util.List;\r\n" +
		"import java.util.Optional;\r\n" +
		"import java.time.Instant;\r\n\n" +
		"/**\r\n" +
		" * Spring Data JPA repository for the User entity.\r\n" +
		" */\r\n" +
		"@Repository\r\n" +
		"public interface UserRepository extends JpaRepository<User, Long> {\r\n" +
		"    String USERS_BY_LOGIN_CACHE = \"usersByLogin\";\r\n" +
		"    String USERS_BY_EMAIL_CACHE = \"usersByEmail\";\r\n\n" +
		"    Optional<User> findOneByActivationKey(String activationKey);\r\n\n" +
		"    Optional<User> findOneByResetKey(String resetKey);\r\n\n" +
		"    Optional<User> findOneByEmailIgnoreCase(String email);\r\n\n" +
		"    Optional<User> findOneByLogin(String login);\r\n\n" +
		"    List<User> findAllByActivatedIsFalseAndCreatedDateBefore(Instant dateTime);\n\n"+
		"    List<User> findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant dateTime);\r\n\n" +
		"    @EntityGraph(attributePaths = \"authorities\")\r\n" +
		"    Optional<User> findOneWithAuthoritiesById(Long id);\r\n\n" +
		"    @EntityGraph(attributePaths = \"authorities\")\r\n" +
		"    @Cacheable(cacheNames = USERS_BY_LOGIN_CACHE)\r\n" +
		"    Optional<User> findOneWithAuthoritiesByLogin(String login);\r\n\n" +
		"    @EntityGraph(attributePaths = \"authorities\")\r\n" +
		"    @Cacheable(cacheNames = USERS_BY_EMAIL_CACHE)\r\n" +
		"    Optional<User> findOneWithAuthoritiesByEmail(String email);\r\n\n" +
		"    Page<User> findAllByLoginNot(Pageable pageable, String login);\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return Utils.getRepositoryClassName(User);
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
