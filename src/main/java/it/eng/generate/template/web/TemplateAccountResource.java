package it.eng.generate.template.web;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateAccountResource extends AbstractTemplate{

	public TemplateAccountResource(DataBase dataBase) {
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
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcWebRestFolder()+";\r\n\n" +
		"import com.codahale.metrics.annotation.Timed;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcDomainFolder() + ".PersistentToken;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcDomainFolder() + ".User;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcRepositoryFolder() + ".PersistentTokenRepository;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcRepositoryFolder() + ".UserRepository;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcSecurityFolder()+ ".SecurityUtils;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcServiceFolder()+ ".MailService;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcServiceFolder()+ ".UserService;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcServiceDtoFolder()+ ".PasswordChangeDTO;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcServiceDtoFolder()+ ".UserDTO;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcWebRestErrorsFolder()+ ".*;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcWebRestVmFolder()+ ".KeyAndPasswordVM;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcWebRestVmFolder()+ ".ManagedUserVM;\r\n" +
		"import org.apache.commons.lang3.StringUtils;\r\n" +
		"import org.slf4j.Logger;\r\n" +
		"import org.slf4j.LoggerFactory;\r\n" +
		"import org.springframework.http.HttpStatus;\r\n" +
		"import org.springframework.web.bind.annotation.*;\r\n" +
		"import javax.servlet.http.HttpServletRequest;\r\n" +
		"import javax.validation.Valid;\r\n" +
		"import java.io.UnsupportedEncodingException;\r\n" +
		"import java.net.URLDecoder;\r\n" +
		"import java.util.*;\r\n\n" +
		"/**\r\n" +
		" * REST controller for managing the current user's account.\r\n" +
		" */\r\n" +
		"@RestController\r\n" +
		"@RequestMapping(\"/api\")\r\n" +
		"public class "+getClassName()+" {\r\n" +
		"    private final Logger log = LoggerFactory.getLogger("+getClassName()+".class);\r\n" +
		"    private final UserRepository userRepository;\r\n" +
		"    private final UserService userService;\r\n" +
		"    private final MailService mailService;\r\n" +
		"    private final PersistentTokenRepository persistentTokenRepository;\r\n\n" +
		"    public "+getClassName()+"(UserRepository userRepository, UserService userService, MailService mailService, PersistentTokenRepository persistentTokenRepository) {\r\n" +
		"        this.userRepository = userRepository;\r\n" +
		"        this.userService = userService;\r\n" +
		"        this.mailService = mailService;\r\n" +
		"        this.persistentTokenRepository = persistentTokenRepository;\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * POST  /register : register the user.\r\n" +
		"     *\r\n" +
		"     * @param managedUserVM the managed user View Model\r\n" +
		"     * @throws InvalidPasswordException 400 (Bad Request) if the password is incorrect\r\n" +
		"     * @throws EmailAlreadyUsedException 400 (Bad Request) if the email is already used\r\n" +
		"     * @throws LoginAlreadyUsedException 400 (Bad Request) if the login is already used\r\n" +
		"     */\r\n" +
		"    @PostMapping(\"/register\")\r\n" +
		"    @Timed\r\n" +
		"    @ResponseStatus(HttpStatus.CREATED)\r\n" +
		"    public void registerAccount(@Valid @RequestBody ManagedUserVM managedUserVM) {\r\n" +
		"        if (!checkPasswordLength(managedUserVM.getPassword())) {\r\n" +
		"            throw new InvalidPasswordException();\r\n" +
		"        }\r\n" +
		"        User user = userService.registerUser(managedUserVM, managedUserVM.getPassword());\r\n" +
		"        mailService.sendActivationEmail(user);\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * GET  /activate : activate the registered user.\r\n" +
		"     *\r\n" +
		"     * @param key the activation key\r\n" +
		"     * @throws RuntimeException 500 (Internal Server Error) if the user couldn't be activated\r\n" +
		"     */\r\n" +
		"    @GetMapping(\"/activate\")\r\n" +
		"    @Timed\r\n" +
		"    public void activateAccount(@RequestParam(value = \"key\") String key) {\r\n" +
		"        Optional<User> user = userService.activateRegistration(key);\r\n" +
		"        if (!user.isPresent()) {\r\n" +
		"            throw new InternalServerErrorException(\"No user was found for this activation key\");\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * GET  /authenticate : check if the user is authenticated, and return its login.\r\n" +
		"     *\r\n" +
		"     * @param request the HTTP request\r\n" +
		"     * @return the login if the user is authenticated\r\n" +
		"     */\r\n" +
		"    @GetMapping(\"/authenticate\")\r\n" +
		"    @Timed\r\n" +
		"    public String isAuthenticated(HttpServletRequest request) {\r\n" +
		"        log.debug(\"REST request to check if the current user is authenticated\");\r\n" +
		"        return request.getRemoteUser();\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * GET  /account : get the current user.\r\n" +
		"     *\r\n" +
		"     * @return the current user\r\n" +
		"     * @throws RuntimeException 500 (Internal Server Error) if the user couldn't be returned\r\n" +
		"     */\r\n" +
		"    @GetMapping(\"/account\")\r\n" +
		"    @Timed\r\n" +
		"    public UserDTO getAccount() {\r\n" +
		"        return userService.getUserWithAuthorities()\r\n" +
		"            .map(UserDTO::new)\r\n" +
		"            .orElseThrow(() -> new InternalServerErrorException(\"User could not be found\"));\r\n" +
		"    }\r\n" +
		"    /**\r\n" +
		"     * POST  /account : update the current user information.\r\n" +
		"     *\r\n" +
		"     * @param userDTO the current user information\r\n" +
		"     * @throws EmailAlreadyUsedException 400 (Bad Request) if the email is already used\r\n" +
		"     * @throws RuntimeException 500 (Internal Server Error) if the user login wasn't found\r\n" +
		"     */\r\n" +
		"    @PostMapping(\"/account\")\r\n" +
		"    @Timed\r\n" +
		"    public void saveAccount(@Valid @RequestBody UserDTO userDTO) {\r\n" +
		"        final String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new InternalServerErrorException(\"Current user login not found\"));\r\n" +
		"        Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());\r\n" +
		"        if (existingUser.isPresent() && (!existingUser.get().getLogin().equalsIgnoreCase(userLogin))) {\r\n" +
		"            throw new EmailAlreadyUsedException();\r\n" +
		"        }\r\n" +
		"        Optional<User> user = userRepository.findOneByLogin(userLogin);\r\n" +
		"        if (!user.isPresent()) {\r\n" +
		"            throw new InternalServerErrorException(\"User could not be found\");\r\n" +
		"        }\r\n" +
		"        userService.updateUser(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(),\r\n" +
		"            userDTO.getLangKey(), userDTO.getImageUrl());\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * POST  /account/change-password : changes the current user's password\r\n" +
		"     *\r\n" +
		"     * @param passwordChangeDto current and new password\r\n" +
		"     * @throws InvalidPasswordException 400 (Bad Request) if the new password is incorrect\r\n" +
		"     */\r\n" +
		"    @PostMapping(path = \"/account/change-password\")\r\n" +
		"    @Timed\r\n" +
		"    public void changePassword(@RequestBody PasswordChangeDTO passwordChangeDto) {\r\n" +
		"        if (!checkPasswordLength(passwordChangeDto.getNewPassword())) {\r\n" +
		"            throw new InvalidPasswordException();\r\n" +
		"        }\r\n" +
		"        userService.changePassword(passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword());\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * GET  /account/sessions : get the current open sessions.\r\n" +
		"     *\r\n" +
		"     * @return the current open sessions\r\n" +
		"     * @throws RuntimeException 500 (Internal Server Error) if the current open sessions couldn't be retrieved\r\n" +
		"     */\r\n" +
		"    @GetMapping(\"/account/sessions\")\r\n" +
		"    @Timed\r\n" +
		"    public List<PersistentToken> getCurrentSessions() {\r\n" +
		"        return persistentTokenRepository.findByUser(\r\n" +
		"            userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()\r\n" +
		"                .orElseThrow(() -> new InternalServerErrorException(\"Current user login not found\")))\r\n" +
		"                    .orElseThrow(() -> new InternalServerErrorException(\"User could not be found\"))\r\n" +
		"        );\r\n" +
		"    }\r\n" +
		"    /**\r\n" +
		"     * DELETE  /account/sessions?series={series} : invalidate an existing session.\r\n" +
		"     *\r\n" +
		"     * - You can only delete your own sessions, not any other user's session\r\n" +
		"     * - If you delete one of your existing sessions, and that you are currently logged in on that session, you will\r\n" +
		"     *   still be able to use that session, until you quit your browser: it does not work in real time (there is\r\n" +
		"     *   no API for that), it only removes the \"remember me\" cookie\r\n" +
		"     * - This is also true if you invalidate your current session: you will still be able to use it until you close\r\n" +
		"     *   your browser or that the session times out. But automatic login (the \"remember me\" cookie) will not work\r\n" +
		"     *   anymore.\r\n" +
		"     *   There is an API to invalidate the current session, but there is no API to check which session uses which\r\n" +
		"     *   cookie.\r\n" +
		"     *\r\n" +
		"     * @param series the series of an existing session\r\n" +
		"     * @throws UnsupportedEncodingException if the series couldnt be URL decoded\r\n" +
		"     */\r\n\n" +
		"    @DeleteMapping(\"/account/sessions/{series}\")\r\n" +
		"    @Timed\r\n" +
		"    public void invalidateSession(@PathVariable String series) throws UnsupportedEncodingException {\r\n" +
		"        String decodedSeries = URLDecoder.decode(series, \"UTF-8\");\r\n" +
		"        SecurityUtils.getCurrentUserLogin()\r\n" +
		"            .flatMap(userRepository::findOneByLogin)\r\n" +
		"            .ifPresent(u ->\r\n" +
		"                persistentTokenRepository.findByUser(u).stream()\r\n" +
		"                    .filter(persistentToken -> StringUtils.equals(persistentToken.getSeries(), decodedSeries))\r\n" +
		"                    .findAny().ifPresent(t -> persistentTokenRepository.deleteById(decodedSeries)));\r\n" +
		"    }\r\n" +
		"    /**\r\n" +
		"     * POST   /account/reset-password/init : Send an email to reset the password of the user\r\n" +
		"     *\r\n" +
		"     * @param mail the mail of the user\r\n" +
		"     * @throws EmailNotFoundException 400 (Bad Request) if the email address is not registered\r\n" +
		"     */\r\n" +
		"    @PostMapping(path = \"/account/reset-password/init\")\r\n" +
		"    @Timed\r\n" +
		"    public void requestPasswordReset(@RequestBody String mail) {\r\n" +
		"       mailService.sendPasswordResetMail(\r\n" +
		"           userService.requestPasswordReset(mail)\r\n" +
		"               .orElseThrow(EmailNotFoundException::new)\r\n" +
		"       );\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * POST   /account/reset-password/finish : Finish to reset the password of the user\r\n" +
		"     *\r\n" +
		"     * @param keyAndPassword the generated key and the new password\r\n" +
		"     * @throws InvalidPasswordException 400 (Bad Request) if the password is incorrect\r\n" +
		"     * @throws RuntimeException 500 (Internal Server Error) if the password could not be reset\r\n" +
		"     */\r\n" +
		"    @PostMapping(path = \"/account/reset-password/finish\")\r\n" +
		"    @Timed\r\n" +
		"    public void finishPasswordReset(@RequestBody KeyAndPasswordVM keyAndPassword) {\r\n" +
		"        if (!checkPasswordLength(keyAndPassword.getNewPassword())) {\r\n" +
		"            throw new InvalidPasswordException();\r\n" +
		"        }\r\n" +
		"        Optional<User> user =\r\n" +
		"            userService.completePasswordReset(keyAndPassword.getNewPassword(), keyAndPassword.getKey());\r\n" +
		"        if (!user.isPresent()) {\r\n" +
		"            throw new InternalServerErrorException(\"No user was found for this reset key\");\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"    private static boolean checkPasswordLength(String password) {\r\n" +
		"        return !StringUtils.isEmpty(password) &&\r\n" +
		"            password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH &&\r\n" +
		"            password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH;\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "AccountResource";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
