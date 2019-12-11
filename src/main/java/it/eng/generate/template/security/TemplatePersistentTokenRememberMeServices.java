package it.eng.generate.template.security;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplatePersistentTokenRememberMeServices extends AbstractTemplate{

	public TemplatePersistentTokenRememberMeServices(DataBase database) {
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
				"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".PersistentToken;\r\n" +
				"import "+ conf.getPackageclass() + "." + conf.getSrcRepositoryFolder()+".PersistentTokenRepository;\r\n" +
				"import "+ conf.getPackageclass() + "." + conf.getSrcRepositoryFolder()+".UserRepository;\r\n" +
				"import "+ conf.getPackageclass() + "." + conf.getSrcServiceUtilFolder()+".RandomUtil;\r\n" +
				"import io.github.jhipster.config.JHipsterProperties;\r\n" +
				"import io.github.jhipster.security.PersistentTokenCache;\r\n" +
				"import org.slf4j.Logger;\r\n" +
				"import org.slf4j.LoggerFactory;\r\n" +
				"import org.springframework.dao.DataAccessException;\r\n" +
				"import org.springframework.security.core.Authentication;\r\n" +
				"import org.springframework.security.core.userdetails.UserDetails;\r\n" +
				"import org.springframework.security.core.userdetails.UsernameNotFoundException;\r\n" +
				"import org.springframework.security.web.authentication.rememberme.*;\r\n" +
				"import org.springframework.stereotype.Service;\r\n" +
				"import javax.servlet.http.HttpServletRequest;\r\n" +
				"import javax.servlet.http.HttpServletResponse;\r\n" +
				"import java.io.Serializable;\r\n" +
				"import java.time.LocalDate;\r\n" +
				"import java.util.*;\r\n\n" +
				"/**\r\n" +
				" * Custom implementation of Spring Security's RememberMeServices.\r\n" +
				" * <p>\r\n" +
				" * Persistent tokens are used by Spring Security to automatically log in users.\r\n" +
				" * <p>\r\n" +
				" * This is a specific implementation of Spring Security's remember-me authentication, but it is much\r\n" +
				" * more powerful than the standard implementations:\r\n" +
				" * <ul>\r\n" +
				" * <li>It allows a user to see the list of his currently opened sessions, and invalidate them</li>\r\n" +
				" * <li>It stores more information, such as the IP address and the user agent, for audit purposes<li>\r\n" +
				" * <li>When a user logs out, only his current session is invalidated, and not all of his sessions</li>\r\n" +
				" * </ul>\r\n" +
				" * <p>\r\n" +
				" * Please note that it allows the use of the same token for 5 seconds, and this value stored in a specific\r\n" +
				" * cache during that period. This is to allow concurrent requests from the same user: otherwise, two\r\n" +
				" * requests being sent at the same time could invalidate each other's token.\r\n" +
				" * <p>\r\n" +
				" * This is inspired by:\r\n" +
				" * <ul>\r\n" +
				" * <li><a href=\"http://jaspan.com/improved_persistent_login_cookie_best_practice\">Improved Persistent Login Cookie\r\n" +
				" * Best Practice</a></li>\r\n" +
				" * <li><a href=\"https://github.com/blog/1661-modeling-your-app-s-user-session\">GitHub's \"Modeling your App's User Session\"</a></li>\r\n" +
				" * </ul>\r\n" +
				" * <p>\r\n" +
				" * The main algorithm comes from Spring Security's PersistentTokenBasedRememberMeServices, but this class\r\n" +
				" * couldn't be cleanly extended.\r\n" +
				" */\r\n" +
				"@Service\r\n" +
				"public class "+getClassName()+" extends AbstractRememberMeServices {\r\n" +
				"    private final Logger log = LoggerFactory.getLogger("+getClassName()+".class);\r\n" +
				"    // Token is valid for one month\r\n" +
				"    private static final int TOKEN_VALIDITY_DAYS = 31;\r\n" +
				"    private static final int TOKEN_VALIDITY_SECONDS = 60 * 60 * 24 * TOKEN_VALIDITY_DAYS;\r\n" +
				"    private static final long UPGRADED_TOKEN_VALIDITY_MILLIS = 5000l;\r\n" +
				"    private final PersistentTokenCache<UpgradedRememberMeToken> upgradedTokenCache;\r\n" +
				"    private final PersistentTokenRepository persistentTokenRepository;\r\n" +
				"    private final UserRepository userRepository;\r\n" +
				"    public "+getClassName()+"(JHipsterProperties jHipsterProperties,\r\n" +
				"            org.springframework.security.core.userdetails.UserDetailsService userDetailsService,\r\n" +
				"            PersistentTokenRepository persistentTokenRepository, UserRepository userRepository) {\r\n" +
				"        super(jHipsterProperties.getSecurity().getRememberMe().getKey(), userDetailsService);\r\n" +
				"        this.persistentTokenRepository = persistentTokenRepository;\r\n" +
				"        this.userRepository = userRepository;\r\n" +
				"        upgradedTokenCache = new PersistentTokenCache<>(UPGRADED_TOKEN_VALIDITY_MILLIS);\r\n" +
				"    }\r\n" +
				"    @Override\r\n" +
				"    protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request,\r\n" +
				"        HttpServletResponse response) {\r\n" +
				"        synchronized (this) { // prevent 2 authentication requests from the same user in parallel\r\n" +
				"            String login = null;\r\n" +
				"            UpgradedRememberMeToken upgradedToken = upgradedTokenCache.get(cookieTokens[0]);\r\n" +
				"            if (upgradedToken != null) {\r\n" +
				"                login = upgradedToken.getUserLoginIfValid(cookieTokens);\r\n" +
				"                log.debug(\"Detected previously upgraded login token for user '{}'\", login);\r\n" +
				"            }\r\n" +
				"            if (login == null) {\r\n" +
				"                PersistentToken token = getPersistentToken(cookieTokens);\r\n" +
				"                login = token.getUser().getLogin();\r\n" +
				"                // Token also matches, so login is valid. Update the token value, keeping the *same* series number.\r\n" +
				"                log.debug(\"Refreshing persistent login token for user '{}', series '{}'\", login, token.getSeries());\r\n" +
				"                token.setTokenDate(LocalDate.now());\r\n" +
				"                token.setTokenValue(RandomUtil.generateTokenData());\r\n" +
				"                token.setIpAddress(request.getRemoteAddr());\r\n" +
				"                token.setUserAgent(request.getHeader(\"User-Agent\"));\r\n" +
				"                try {\r\n" +
				"                    persistentTokenRepository.saveAndFlush(token);\r\n" +
				"                } catch (DataAccessException e) {\r\n" +
				"                    log.error(\"Failed to update token: \", e);\r\n" +
				"                    throw new RememberMeAuthenticationException(\"Autologin failed due to data access problem\", e);\r\n" +
				"                }\r\n" +
				"                addCookie(token, request, response);\r\n" +
				"                upgradedTokenCache.put(cookieTokens[0], new UpgradedRememberMeToken(cookieTokens, login));\r\n" +
				"            }\r\n" +
				"            return getUserDetailsService().loadUserByUsername(login);\r\n" +
				"        }\r\n" +
				"    }\r\n" +
				"    @Override\r\n" +
				"    protected void onLoginSuccess(HttpServletRequest request, HttpServletResponse response, Authentication\r\n" +
				"        successfulAuthentication) {\r\n" +
				"        String login = successfulAuthentication.getName();\r\n" +
				"        log.debug(\"Creating new persistent login for user {}\", login);\r\n" +
				"        PersistentToken token = userRepository.findOneByLogin(login).map(u -> {\r\n" +
				"            PersistentToken t = new PersistentToken();\r\n" +
				"            t.setSeries(RandomUtil.generateSeriesData());\r\n" +
				"            t.setUser(u);\r\n" +
				"            t.setTokenValue(RandomUtil.generateTokenData());\r\n" +
				"            t.setTokenDate(LocalDate.now());\r\n" +
				"            t.setIpAddress(request.getRemoteAddr());\r\n" +
				"            t.setUserAgent(request.getHeader(\"User-Agent\"));\r\n" +
				"            return t;\r\n" +
				"        }).orElseThrow(() -> new UsernameNotFoundException(\"User \" + login + \" was not found in the database\"));\r\n" +
				"        try {\r\n" +
				"            persistentTokenRepository.saveAndFlush(token);\r\n" +
				"            addCookie(token, request, response);\r\n" +
				"        } catch (DataAccessException e) {\r\n" +
				"            log.error(\"Failed to save persistent token \", e);\r\n" +
				"        }\r\n" +
				"    }\r\n" +
				"    /**\r\n" +
				"     * When logout occurs, only invalidate the current token, and not all user sessions.\r\n" +
				"     * <p>\r\n" +
				"     * The standard Spring Security implementations are too basic: they invalidate all tokens for the\r\n" +
				"     * current user, so when he logs out from one browser, all his other sessions are destroyed.\r\n" +
				"     */\r\n" +
				"    @Override\r\n" +
				"    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {\r\n" +
				"        String rememberMeCookie = extractRememberMeCookie(request);\r\n" +
				"        if (rememberMeCookie != null && rememberMeCookie.length() != 0) {\r\n" +
				"            try {\r\n" +
				"                String[] cookieTokens = decodeCookie(rememberMeCookie);\r\n" +
				"                PersistentToken token = getPersistentToken(cookieTokens);\r\n" +
				"                persistentTokenRepository.deleteById(token.getSeries());\r\n" +
				"            } catch (InvalidCookieException ice) {\r\n" +
				"                log.info(\"Invalid cookie, no persistent token could be deleted\", ice);\r\n" +
				"            } catch (RememberMeAuthenticationException rmae) {\r\n" +
				"                log.debug(\"No persistent token found, so no token could be deleted\", rmae);\r\n" +
				"            }\r\n" +
				"        }\r\n" +
				"        super.logout(request, response, authentication);\r\n" +
				"    }\r\n" +
				"    /**\r\n" +
				"     * Validate the token and return it.\r\n" +
				"     */\r\n" +
				"    private PersistentToken getPersistentToken(String[] cookieTokens) {\r\n" +
				"        if (cookieTokens.length != 2) {\r\n" +
				"            throw new InvalidCookieException(\"Cookie token did not contain \" + 2 +\r\n" +
				"                \" tokens, but contained '\" + Arrays.asList(cookieTokens) + \"'\");\r\n" +
				"        }\r\n" +
				"        String presentedSeries = cookieTokens[0];\r\n" +
				"        String presentedToken = cookieTokens[1];\r\n" +
				"        Optional<PersistentToken> optionalToken = persistentTokenRepository.findById(presentedSeries);\r\n" +
				"        if (!optionalToken.isPresent()) {\r\n" +
				"            // No series match, so we can't authenticate using this cookie\r\n" +
				"            throw new RememberMeAuthenticationException(\"No persistent token found for series id: \" + presentedSeries);\r\n" +
				"        }\r\n" +
				"        PersistentToken token = optionalToken.get();\r\n" +
				"        // We have a match for this user/series combination\r\n" +
				"        log.info(\"presentedToken={} / tokenValue={}\", presentedToken, token.getTokenValue());\r\n" +
				"        if (!presentedToken.equals(token.getTokenValue())) {\r\n" +
				"            // Token doesn't match series value. Delete this session and throw an exception.\r\n" +
				"            persistentTokenRepository.deleteById(token.getSeries());\r\n" +
				"            throw new CookieTheftException(\"Invalid remember-me token (Series/token) mismatch. Implies previous \" +\r\n" +
				"                \"cookie theft attack.\");\r\n" +
				"        }\r\n" +
				"        if (token.getTokenDate().plusDays(TOKEN_VALIDITY_DAYS).isBefore(LocalDate.now())) {\r\n" +
				"            persistentTokenRepository.deleteById(token.getSeries());\r\n" +
				"            throw new RememberMeAuthenticationException(\"Remember-me login has expired\");\r\n" +
				"        }\r\n" +
				"        return token;\r\n" +
				"    }\r\n" +
				"    private void addCookie(PersistentToken token, HttpServletRequest request, HttpServletResponse response) {\r\n" +
				"        setCookie(\r\n" +
				"            new String[]{token.getSeries(), token.getTokenValue()},\r\n" +
				"            TOKEN_VALIDITY_SECONDS, request, response);\r\n" +
				"    }\r\n" +
				"    private static class UpgradedRememberMeToken implements Serializable {\r\n" +
				"        private static final long serialVersionUID = 1L;\r\n" +
				"        private final String[] upgradedToken;\r\n" +
				"        private final String userLogin;\r\n" +
				"        UpgradedRememberMeToken(String[] upgradedToken, String userLogin) {\r\n" +
				"            this.upgradedToken = upgradedToken;\r\n" +
				"            this.userLogin = userLogin;\r\n" +
				"        }\r\n" +
				"        String getUserLoginIfValid(String[] currentToken) {\r\n" +
				"            if (currentToken[0].equals(this.upgradedToken[0]) &&\r\n" +
				"                    currentToken[1].equals(this.upgradedToken[1])) {\r\n" +
				"                return this.userLogin;\r\n" +
				"            }\r\n" +
				"            return null;\r\n" +
				"        }\r\n" +
				"    }\r\n" +
				"}\r\n";
		return body;
	}

	public String getClassName() {
		return "PersistentTokenRememberMeServices";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
