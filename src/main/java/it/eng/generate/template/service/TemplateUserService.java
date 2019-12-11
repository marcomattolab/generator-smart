package it.eng.generate.template.service;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateUserService extends AbstractTemplate{

	public TemplateUserService(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcServiceFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcServiceFolder()+";\r\n\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcConfigFolder()+".Constants;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".Authority;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".User;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcRepositoryFolder()+".AuthorityRepository;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcRepositoryFolder()+".PersistentTokenRepository;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcRepositoryFolder()+".UserRepository;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcSecurityFolder()+".AuthoritiesConstants;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcSecurityFolder()+".SecurityUtils;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcServiceDtoFolder()+".UserDTO;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcServiceUtilFolder()+".RandomUtil;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcWebRestErrorsFolder()+".*;\r\n" +
		"import org.slf4j.Logger;\r\n" +
		"import org.slf4j.LoggerFactory;\r\n" +
		"import org.springframework.data.domain.Page;\r\n" +
		"import org.springframework.data.domain.Pageable;\r\n" +
		"import org.springframework.scheduling.annotation.Scheduled;\r\n" +
		"import org.springframework.security.crypto.password.PasswordEncoder;\r\n" +
		"import org.springframework.stereotype.Service;\r\n" +
		"import org.springframework.transaction.annotation.Transactional;\r\n" +
		"import java.time.LocalDate;\r\n" +
		"import java.time.Instant;\r\n" +
		"import java.time.temporal.ChronoUnit;\r\n" +
		"import java.util.*;\r\n" +
		"import java.util.stream.Collectors;\r\n\n" +
		"/**\r\n" +
		" * Service class for managing users.\r\n" +
		" */\r\n" +
		"@Service\r\n" +
		"@Transactional\r\n" +
		"public class "+getClassName()+" {\r\n" +
		"    private final Logger log = LoggerFactory.getLogger("+getClassName()+".class);\r\n\n" +
		"    private final UserRepository userRepository;\r\n" +
		"    private final PasswordEncoder passwordEncoder;\r\n" +
		"    private final PersistentTokenRepository persistentTokenRepository;\r\n" +
		"    private final AuthorityRepository authorityRepository;\r\n\n" +
		"    public "+getClassName()+"(UserRepository userRepository, PasswordEncoder passwordEncoder, PersistentTokenRepository persistentTokenRepository, AuthorityRepository authorityRepository) {\r\n" +
		"        this.userRepository = userRepository;\r\n" +
		"        this.passwordEncoder = passwordEncoder;\r\n" +
		"        this.persistentTokenRepository = persistentTokenRepository;\r\n" +
		"        this.authorityRepository = authorityRepository;\r\n" +
		"    }\r\n\n" +
		"    public Optional<User> activateRegistration(String key) {\r\n" +
		"        log.debug(\"Activating user for activation key {}\", key);\r\n" +
		"        return userRepository.findOneByActivationKey(key)\r\n" +
		"            .map(user -> {\r\n" +
		"                // activate given user for the registration key.\r\n" +
		"                user.setActivated(true);\r\n" +
		"                user.setActivationKey(null);\r\n" +
		"                log.debug(\"Activated user: {}\", user);\r\n" +
		"                return user;\r\n" +
		"            });\r\n" +
		"    }\r\n\n" +
		"    public Optional<User> completePasswordReset(String newPassword, String key) {\r\n" +
		"        log.debug(\"Reset user password for reset key {}\", key);\r\n" +
		"        return userRepository.findOneByResetKey(key)\r\n" +
		"            .filter(user -> user.getResetDate().isAfter(Instant.now().minusSeconds(86400)))\r\n" +
		"            .map(user -> {\r\n" +
		"                user.setPassword(passwordEncoder.encode(newPassword));\r\n" +
		"                user.setResetKey(null);\r\n" +
		"                user.setResetDate(null);\r\n" +
		"                return user;\r\n" +
		"            });\r\n" +
		"    }\r\n\n" +
		"    public Optional<User> requestPasswordReset(String mail) {\r\n" +
		"        return userRepository.findOneByEmailIgnoreCase(mail)\r\n" +
		"            .filter(User::getActivated)\r\n" +
		"            .map(user -> {\r\n" +
		"                user.setResetKey(RandomUtil.generateResetKey());\r\n" +
		"                user.setResetDate(Instant.now());\r\n" +
		"                return user;\r\n" +
		"            });\r\n" +
		"    }\r\n\n" +
		"    public User registerUser(UserDTO userDTO, String password) {\r\n" +
		"        userRepository.findOneByLogin(userDTO.getLogin().toLowerCase()).ifPresent(existingUser -> {\r\n" +
		"            boolean removed = removeNonActivatedUser(existingUser);\r\n" +
		"            if (!removed) {\r\n" +
		"                throw new LoginAlreadyUsedException();\r\n" +
		"            }\r\n" +
		"        });\r\n" +
		"        userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).ifPresent(existingUser -> {\r\n" +
		"            boolean removed = removeNonActivatedUser(existingUser);\r\n" +
		"            if (!removed) {\r\n" +
		"                throw new EmailAlreadyUsedException();\r\n" +
		"            }\r\n" +
		"        });\r\n" +
		"        User newUser = new User();\r\n" +
		"        String encryptedPassword = passwordEncoder.encode(password);\r\n" +
		"        newUser.setLogin(userDTO.getLogin().toLowerCase());\r\n" +
		"        // new user gets initially a generated password\r\n" +
		"        newUser.setPassword(encryptedPassword);\r\n" +
		"        newUser.setFirstName(userDTO.getFirstName());\r\n" +
		"        newUser.setLastName(userDTO.getLastName());\r\n" +
		"        newUser.setEmail(userDTO.getEmail().toLowerCase());\r\n" +
		"        newUser.setImageUrl(userDTO.getImageUrl());\r\n" +
		"        newUser.setLangKey(userDTO.getLangKey());\r\n" +
		"        // new user is not active\r\n" +
		"        newUser.setActivated(false);\r\n" +
		"        // new user gets registration key\r\n" +
		"        newUser.setActivationKey(RandomUtil.generateActivationKey());\r\n" +
		"        Set<Authority> authorities = new HashSet<>();\r\n" +
		"        authorityRepository.findById(AuthoritiesConstants.USER).ifPresent(authorities::add);\r\n" +
		"        newUser.setAuthorities(authorities);\r\n" +
		"        userRepository.save(newUser);\r\n" +
		"        log.debug(\"Created Information for User: {}\", newUser);\r\n" +
		"        return newUser;\r\n" +
		"    }\r\n\n" +
		"    private boolean removeNonActivatedUser(User existingUser){\r\n" +
		"        if (existingUser.getActivated()) {\r\n" +
		"             return false;\r\n" +
		"        }\r\n" +
		"        userRepository.delete(existingUser);\r\n" +
		"        userRepository.flush();\r\n" +
		"        return true;\r\n" +
		"    }\r\n\n" +
		"    public User createUser(UserDTO userDTO) {\r\n" +
		"        User user = new User();\r\n" +
		"        user.setLogin(userDTO.getLogin().toLowerCase());\r\n" +
		"        user.setFirstName(userDTO.getFirstName());\r\n" +
		"        user.setLastName(userDTO.getLastName());\r\n" +
		"        user.setEmail(userDTO.getEmail().toLowerCase());\r\n" +
		"        user.setImageUrl(userDTO.getImageUrl());\r\n" +
		"        if (userDTO.getLangKey() == null) {\r\n" +
		"            user.setLangKey(Constants.DEFAULT_LANGUAGE); // default language\r\n" +
		"        } else {\r\n" +
		"            user.setLangKey(userDTO.getLangKey());\r\n" +
		"        }\r\n" +
		"        String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());\r\n" +
		"        user.setPassword(encryptedPassword);\r\n" +
		"        user.setResetKey(RandomUtil.generateResetKey());\r\n" +
		"        user.setResetDate(Instant.now());\r\n" +
		"        user.setActivated(true);\r\n" +
		"        if (userDTO.getAuthorities() != null) {\r\n" +
		"            Set<Authority> authorities = userDTO.getAuthorities().stream()\r\n" +
		"                .map(authorityRepository::findById)\r\n" +
		"                .filter(Optional::isPresent)\r\n" +
		"                .map(Optional::get)\r\n" +
		"                .collect(Collectors.toSet());\r\n" +
		"            user.setAuthorities(authorities);\r\n" +
		"        }\r\n" +
		"        userRepository.save(user);\r\n" +
		"        log.debug(\"Created Information for User: {}\", user);\r\n" +
		"        return user;\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Update basic information (first name, last name, email, language) for the current user.\r\n" +
		"     *\r\n" +
		"     * @param firstName first name of user\r\n" +
		"     * @param lastName last name of user\r\n" +
		"     * @param email email id of user\r\n" +
		"     * @param langKey language key\r\n" +
		"     * @param imageUrl image URL of user\r\n" +
		"     */\r\n" +
		"    public void updateUser(String firstName, String lastName, String email, String langKey, String imageUrl) {\r\n" +
		"        SecurityUtils.getCurrentUserLogin()\r\n" +
		"            .flatMap(userRepository::findOneByLogin)\r\n" +
		"            .ifPresent(user -> {\r\n" +
		"                user.setFirstName(firstName);\r\n" +
		"                user.setLastName(lastName);\r\n" +
		"                user.setEmail(email.toLowerCase());\r\n" +
		"                user.setLangKey(langKey);\r\n" +
		"                user.setImageUrl(imageUrl);\r\n" +
		"                log.debug(\"Changed Information for User: {}\", user);\r\n" +
		"            });\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Update all information for a specific user, and return the modified user.\r\n" +
		"     *\r\n" +
		"     * @param userDTO user to update\r\n" +
		"     * @return updated user\r\n" +
		"     */\r\n" +
		"    public Optional<UserDTO> updateUser(UserDTO userDTO) {\r\n" +
		"        return Optional.of(userRepository\r\n" +
		"            .findById(userDTO.getId()))\r\n" +
		"            .filter(Optional::isPresent)\r\n" +
		"            .map(Optional::get)\r\n" +
		"            .map(user -> {\r\n" +
		"                user.setLogin(userDTO.getLogin().toLowerCase());\r\n" +
		"                user.setFirstName(userDTO.getFirstName());\r\n" +
		"                user.setLastName(userDTO.getLastName());\r\n" +
		"                user.setEmail(userDTO.getEmail().toLowerCase());\r\n" +
		"                user.setImageUrl(userDTO.getImageUrl());\r\n" +
		"                user.setActivated(userDTO.isActivated());\r\n" +
		"                user.setLangKey(userDTO.getLangKey());\r\n" +
		"                Set<Authority> managedAuthorities = user.getAuthorities();\r\n" +
		"                managedAuthorities.clear();\r\n" +
		"                userDTO.getAuthorities().stream()\r\n" +
		"                    .map(authorityRepository::findById)\r\n" +
		"                    .filter(Optional::isPresent)\r\n" +
		"                    .map(Optional::get)\r\n" +
		"                    .forEach(managedAuthorities::add);\r\n" +
		"                log.debug(\"Changed Information for User: {}\", user);\r\n" +
		"                return user;\r\n" +
		"            })\r\n" +
		"            .map(UserDTO::new);\r\n" +
		"    }\r\n\n" +
		"    public void deleteUser(String login) {\r\n" +
		"        userRepository.findOneByLogin(login).ifPresent(user -> {\r\n" +
		"            userRepository.delete(user);\r\n" +
		"            log.debug(\"Deleted User: {}\", user);\r\n" +
		"        });\r\n" +
		"    }\r\n\n" +
		"    public void changePassword(String currentClearTextPassword, String newPassword) {\r\n" +
		"        SecurityUtils.getCurrentUserLogin()\r\n" +
		"            .flatMap(userRepository::findOneByLogin)\r\n" +
		"            .ifPresent(user -> {\r\n" +
		"                String currentEncryptedPassword = user.getPassword();\r\n" +
		"                if (!passwordEncoder.matches(currentClearTextPassword, currentEncryptedPassword)) {\r\n" +
		"                    throw new InvalidPasswordException();\r\n" +
		"                }\r\n" +
		"                String encryptedPassword = passwordEncoder.encode(newPassword);\r\n" +
		"                user.setPassword(encryptedPassword);\r\n" +
		"                log.debug(\"Changed password for User: {}\", user);\r\n" +
		"            });\r\n" +
		"    }\r\n\n" +
		"    @Transactional(readOnly = true)\r\n" +
		"    public Page<UserDTO> getAllManagedUsers(Pageable pageable) {\r\n" +
		"        return userRepository.findAllByLoginNot(pageable, Constants.ANONYMOUS_USER).map(UserDTO::new);\r\n" +
		"    }\r\n\n" +
		"    @Transactional(readOnly = true)\r\n" +
		"    public Optional<User> getUserWithAuthoritiesByLogin(String login) {\r\n" +
		"        return userRepository.findOneWithAuthoritiesByLogin(login);\r\n" +
		"    }\r\n\n" +
		"    @Transactional(readOnly = true)\r\n" +
		"    public Optional<User> getUserWithAuthorities(Long id) {\r\n" +
		"        return userRepository.findOneWithAuthoritiesById(id);\r\n" +
		"    }\r\n\n" +
		"    @Transactional(readOnly = true)\r\n" +
		"    public Optional<User> getUserWithAuthorities() {\r\n" +
		"        return SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneWithAuthoritiesByLogin);\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Persistent Token are used for providing automatic authentication, they should be automatically deleted after\r\n" +
		"     * 30 days.\r\n" +
		"     * <p>\r\n" +
		"     * This is scheduled to get fired everyday, at midnight.\r\n" +
		"     */\r\n" +
		"    @Scheduled(cron = \"0 0 0 * * ?\")\r\n" +
		"    public void removeOldPersistentTokens() {\r\n" +
		"        LocalDate now = LocalDate.now();\r\n" +
		"        persistentTokenRepository.findByTokenDateBefore(now.minusMonths(1)).forEach(token -> {\r\n" +
		"            log.debug(\"Deleting token {}\", token.getSeries());\r\n" +
		"            User user = token.getUser();\r\n" +
		"            user.getPersistentTokens().remove(token);\r\n" +
		"            persistentTokenRepository.delete(token);\r\n" +
		"        });\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Not activated users should be automatically deleted after 3 days.\r\n" +
		"     * <p>\r\n" +
		"     * This is scheduled to get fired everyday, at 01:00 (am).\r\n" +
		"     */\r\n" +
		"    @Scheduled(cron = \"0 0 1 * * ?\")\r\n" +
		"    public void removeNotActivatedUsers() {\r\n" +
		"        userRepository\r\n" +
		"            .findAllByActivatedIsFalseAndCreatedDateBefore(Instant.now().minus(3, ChronoUnit.DAYS))\r\n" +
		"            .forEach(user -> {\r\n" +
		"                log.debug(\"Deleting not activated user {}\", user.getLogin());\r\n" +
		"                userRepository.delete(user);\r\n" +
		"            });\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * @return a list of all the authorities\r\n" +
		"     */\r\n" +
		"    public List<String> getAuthorities() {\r\n" +
		"        return authorityRepository.findAll().stream().map(Authority::getName).collect(Collectors.toList());\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "UserService";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
