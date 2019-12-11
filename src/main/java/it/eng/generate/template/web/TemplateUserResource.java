package it.eng.generate.template.web;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateUserResource extends AbstractTemplate{

	public TemplateUserResource(DataBase dataBase) {
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
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcWebRestFolder()+";\r\n\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcServiceFolder()+ ".AuditEventService;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcWebRestUtilFolder()+ ".PaginationUtil;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcConfigFolder()+ ".Constants;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcDomainFolder()+ ".User;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcRepositoryFolder()+ ".UserRepository;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcSecurityFolder()+ ".AuthoritiesConstants;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcServiceFolder()+ ".MailService;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcServiceFolder()+ ".UserService;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcServiceDtoFolder()+ ".UserDTO;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcWebRestErrorsFolder()+ ".BadRequestAlertException;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcWebRestErrorsFolder()+ ".EmailAlreadyUsedException;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcWebRestErrorsFolder()+ ".LoginAlreadyUsedException;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcWebRestUtilFolder()+ ".HeaderUtil;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcWebRestUtilFolder()+ ".PaginationUtil;\r\n" +
		"import com.codahale.metrics.annotation.Timed;\r\n" +
		"import io.github.jhipster.web.util.ResponseUtil;\r\n" +
		"import org.slf4j.Logger;\r\n" +
		"import org.slf4j.LoggerFactory;\r\n" +
		"import org.springframework.data.domain.Page;\r\n" +
		"import org.springframework.data.domain.Pageable;\r\n" +
		"import org.springframework.http.HttpHeaders;\r\n" +
		"import org.springframework.http.HttpStatus;\r\n" +
		"import org.springframework.http.ResponseEntity;\r\n" +
		"import org.springframework.security.access.prepost.PreAuthorize;\r\n" +
		"import org.springframework.web.bind.annotation.*;\r\n" +
		"import javax.validation.Valid;\r\n" +
		"import java.net.URI;\r\n" +
		"import java.net.URISyntaxException;\r\n" +
		"import java.util.*;\r\n\n" +
		"/**\r\n" +
		" * REST controller for managing users.\r\n" +
		" * <p>\r\n" +
		" * This class accesses the User entity, and needs to fetch its collection of authorities.\r\n" +
		" * <p>\r\n" +
		" * For a normal use-case, it would be better to have an eager relationship between User and Authority,\r\n" +
		" * and send everything to the client side: there would be no View Model and DTO, a lot less code, and an outer-join\r\n" +
		" * which would be good for performance.\r\n" +
		" * <p>\r\n" +
		" * We use a View Model and a DTO for 3 reasons:\r\n" +
		" * <ul>\r\n" +
		" * <li>We want to keep a lazy association between the user and the authorities, because people will\r\n" +
		" * quite often do relationships with the user, and we don't want them to get the authorities all\r\n" +
		" * the time for nothing (for performance reasons). This is the #1 goal: we should not impact our users'\r\n" +
		" * application because of this use-case.</li>\r\n" +
		" * <li> Not having an outer join causes n+1 requests to the database. This is not a real issue as\r\n" +
		" * we have by default a second-level cache. This means on the first HTTP call we do the n+1 requests,\r\n" +
		" * but then all authorities come from the cache, so in fact it's much better than doing an outer join\r\n" +
		" * (which will get lots of data from the database, for each HTTP call).</li>\r\n" +
		" * <li> As this manages users, for security reasons, we'd rather have a DTO layer.</li>\r\n" +
		" * </ul>\r\n" +
		" * <p>\r\n" +
		" * Another option would be to have a specific JPA entity graph to handle this case.\r\n" +
		" */\r\n" +
		"@RestController\r\n" +
		"@RequestMapping(\"/api\")\r\n" +
		"public class UserResource {\r\n" +
		"    private final Logger log = LoggerFactory.getLogger(UserResource.class);\r\n" +
		"    private final UserService userService;\r\n" +
		"    private final UserRepository userRepository;\r\n" +
		"    private final MailService mailService;\r\n\n" +
		"    public UserResource(UserService userService, UserRepository userRepository, MailService mailService) {\r\n" +
		"        this.userService = userService;\r\n" +
		"        this.userRepository = userRepository;\r\n" +
		"        this.mailService = mailService;\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * POST  /users  : Creates a new user.\r\n" +
		"     * <p>\r\n" +
		"     * Creates a new user if the login and email are not already used, and sends an\r\n" +
		"     * mail with an activation link.\r\n" +
		"     * The user needs to be activated on creation.\r\n" +
		"     *\r\n" +
		"     * @param userDTO the user to create\r\n" +
		"     * @return the ResponseEntity with status 201 (Created) and with body the new user, or with status 400 (Bad Request) if the login or email is already in use\r\n" +
		"     * @throws URISyntaxException if the Location URI syntax is incorrect\r\n" +
		"     * @throws BadRequestAlertException 400 (Bad Request) if the login or email is already in use\r\n" +
		"     */\r\n\n" +
		"    @PostMapping(\"/users\")\r\n" +
		"    @Timed\r\n" +
		"    @PreAuthorize(\"hasRole(\\\"\" + AuthoritiesConstants.ADMIN + \"\\\")\")\r\n" +
		"    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) throws URISyntaxException {\r\n" +
		"        log.debug(\"REST request to save User : {}\", userDTO);\r\n" +
		"        if (userDTO.getId() != null) {\r\n" +
		"            throw new BadRequestAlertException(\"A new user cannot already have an ID\", \"userManagement\", \"idexists\");\r\n" +
		"            // Lowercase the user login before comparing with database\r\n" +
		"        } else if (userRepository.findOneByLogin(userDTO.getLogin().toLowerCase()).isPresent()) {\r\n" +
		"            throw new LoginAlreadyUsedException();\r\n" +
		"        } else if (userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).isPresent()) {\r\n" +
		"            throw new EmailAlreadyUsedException();\r\n" +
		"        } else {\r\n" +
		"            User newUser = userService.createUser(userDTO);\r\n" +
		"            mailService.sendCreationEmail(newUser);\r\n" +
		"            return ResponseEntity.created(new URI(\"/api/users/\" + newUser.getLogin()))\r\n" +
		"                .headers(HeaderUtil.createAlert( \"userManagement.created\", newUser.getLogin()))\r\n" +
		"                .body(newUser);\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * PUT /users : Updates an existing User.\r\n" +
		"     *\r\n" +
		"     * @param userDTO the user to update\r\n" +
		"     * @return the ResponseEntity with status 200 (OK) and with body the updated user\r\n" +
		"     * @throws EmailAlreadyUsedException 400 (Bad Request) if the email is already in use\r\n" +
		"     * @throws LoginAlreadyUsedException 400 (Bad Request) if the login is already in use\r\n" +
		"     */\r\n" +
		"    @PutMapping(\"/users\")\r\n" +
		"    @Timed\r\n" +
		"    @PreAuthorize(\"hasRole(\\\"\" + AuthoritiesConstants.ADMIN + \"\\\")\")\r\n" +
		"    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) {\r\n" +
		"        log.debug(\"REST request to update User : {}\", userDTO);\r\n" +
		"        Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());\r\n" +
		"        if (existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId()))) {\r\n" +
		"            throw new EmailAlreadyUsedException();\r\n" +
		"        }\r\n" +
		"        existingUser = userRepository.findOneByLogin(userDTO.getLogin().toLowerCase());\r\n" +
		"        if (existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId()))) {\r\n" +
		"            throw new LoginAlreadyUsedException();\r\n" +
		"        }\r\n" +
		"        Optional<UserDTO> updatedUser = userService.updateUser(userDTO);\r\n" +
		"        return ResponseUtil.wrapOrNotFound(updatedUser,\r\n" +
		"            HeaderUtil.createAlert(\"userManagement.updated\", userDTO.getLogin()));\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * GET /users : get all users.\r\n" +
		"     *\r\n" +
		"     * @param pageable the pagination information\r\n" +
		"     * @return the ResponseEntity with status 200 (OK) and with body all users\r\n" +
		"     */\r\n" +
		"    @GetMapping(\"/users\")\r\n" +
		"    @Timed\r\n" +
		"    public ResponseEntity<List<UserDTO>> getAllUsers(Pageable pageable) {\r\n" +
		"        final Page<UserDTO> page = userService.getAllManagedUsers(pageable);\r\n" +
		"        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, \"/api/users\");\r\n" +
		"        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * @return a string list of the all of the roles\r\n" +
		"     */\r\n" +
		"    @GetMapping(\"/users/authorities\")\r\n" +
		"    @Timed\r\n" +
		"    @PreAuthorize(\"hasRole(\\\"\" + AuthoritiesConstants.ADMIN + \"\\\")\")\r\n" +
		"    public List<String> getAuthorities() {\r\n" +
		"        return userService.getAuthorities();\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * GET /users/:login : get the \"login\" user.\r\n" +
		"     *\r\n" +
		"     * @param login the login of the user to find\r\n" +
		"     * @return the ResponseEntity with status 200 (OK) and with body the \"login\" user, or with status 404 (Not Found)\r\n" +
		"     */\r\n" +
		"    @GetMapping(\"/users/{login:\" + Constants.LOGIN_REGEX + \"}\")\r\n" +
		"    @Timed\r\n" +
		"    public ResponseEntity<UserDTO> getUser(@PathVariable String login) {\r\n" +
		"        log.debug(\"REST request to get User : {}\", login);\r\n" +
		"        return ResponseUtil.wrapOrNotFound(\r\n" +
		"            userService.getUserWithAuthoritiesByLogin(login)\r\n" +
		"                .map(UserDTO::new));\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * DELETE /users/:login : delete the \"login\" User.\r\n" +
		"     *\r\n" +
		"     * @param login the login of the user to delete\r\n" +
		"     * @return the ResponseEntity with status 200 (OK)\r\n" +
		"     */\r\n" +
		"    @DeleteMapping(\"/users/{login:\" + Constants.LOGIN_REGEX + \"}\")\r\n" +
		"    @Timed\r\n" +
		"    @PreAuthorize(\"hasRole(\\\"\" + AuthoritiesConstants.ADMIN + \"\\\")\")\r\n" +
		"    public ResponseEntity<Void> deleteUser(@PathVariable String login) {\r\n" +
		"        log.debug(\"REST request to delete User: {}\", login);\r\n" +
		"        userService.deleteUser(login);\r\n" +
		"        return ResponseEntity.ok().headers(HeaderUtil.createAlert( \"userManagement.deleted\", login)).build();\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "UserResource";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
