package it.eng.generate.template.conf;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateSecurityConfiguration extends AbstractTemplate {

	public TemplateSecurityConfiguration(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		//https://www.buildmystring.com/
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcConfigFolder() +";\r\n\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcSecurityFolder() +".*;\r\n" +
		"import io.github.jhipster.config.JHipsterProperties;\r\n" +
		"import io.github.jhipster.security.*;\r\n" +
		"import org.springframework.beans.factory.BeanInitializationException;\r\n" +
		"import org.springframework.context.annotation.Bean;\r\n" +
		"import org.springframework.context.annotation.Configuration;\r\n" +
		"import org.springframework.context.annotation.Import;\r\n" +
		"import org.springframework.http.HttpMethod;\r\n" +
		"import org.springframework.security.authentication.AuthenticationManager;\r\n" +
		"import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;\r\n" +
		"import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;\r\n" +
		"import org.springframework.security.config.annotation.web.builders.HttpSecurity;\r\n" +
		"import org.springframework.security.config.annotation.web.builders.WebSecurity;\r\n" +
		"import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;\r\n" +
		"import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;\r\n" +
		"import org.springframework.security.core.userdetails.UserDetailsService;\r\n" +
		"import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;\r\n" +
		"import org.springframework.security.crypto.password.PasswordEncoder;\r\n" +
		"import org.springframework.security.web.authentication.RememberMeServices;\r\n" +
		"import org.springframework.security.web.csrf.CookieCsrfTokenRepository;\r\n" +
		"import org.springframework.security.web.csrf.CsrfFilter;\r\n" +
		"import org.springframework.web.filter.CorsFilter;\r\n" +
		"import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;\r\n" +
		"import javax.annotation.PostConstruct;\r\n\n" +
		"@Configuration\r\n" +
		"@EnableWebSecurity\r\n" +
		"@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)\r\n" +
		"@Import(SecurityProblemSupport.class)\r\n" +
		"public class "  + getClassName() + " extends WebSecurityConfigurerAdapter {\r\n\n" +
		"    private final AuthenticationManagerBuilder authenticationManagerBuilder;\r\n" +
		"    private final UserDetailsService userDetailsService;\r\n" +
		"    private final JHipsterProperties jHipsterProperties;\r\n" +
		"    private final RememberMeServices rememberMeServices;\r\n" +
		"    private final CorsFilter corsFilter;\r\n" +
		"    private final SecurityProblemSupport problemSupport;\r\n\n" +
		"    public " + getClassName() + "(AuthenticationManagerBuilder authenticationManagerBuilder, UserDetailsService userDetailsService, \r\n" +
		"        JHipsterProperties jHipsterProperties, RememberMeServices rememberMeServices, CorsFilter corsFilter, SecurityProblemSupport problemSupport) {\r\n" +
		"        this.authenticationManagerBuilder = authenticationManagerBuilder;\r\n" +
		"        this.userDetailsService = userDetailsService;\r\n" +
		"        this.jHipsterProperties = jHipsterProperties;\r\n" +
		"        this.rememberMeServices = rememberMeServices;\r\n" +
		"        this.corsFilter = corsFilter;\r\n" +
		"        this.problemSupport = problemSupport;\r\n" +
		"    }\r\n\n" +
		"    @PostConstruct\r\n" +
		"    public void init() {\r\n" +
		"        try {\r\n" +
		"            authenticationManagerBuilder\r\n" +
		"                .userDetailsService(userDetailsService)\r\n" +
		"                .passwordEncoder(passwordEncoder());\r\n" +
		"        } catch (Exception e) {\r\n" +
		"            throw new BeanInitializationException(\"Security configuration failed\", e);\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    @Bean\r\n" +
		"    public AuthenticationManager authenticationManagerBean() throws Exception {\r\n" +
		"        return super.authenticationManagerBean();\r\n" +
		"    }\r\n\n" +
		"    @Bean\r\n" +
		"    public AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler() {\r\n" +
		"        return new AjaxAuthenticationSuccessHandler();\r\n" +
		"    }\r\n\n" +
		"    @Bean\r\n" +
		"    public AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler() {\r\n" +
		"        return new AjaxAuthenticationFailureHandler();\r\n" +
		"    }\r\n\n" +
		"    @Bean\r\n" +
		"    public AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler() {\r\n" +
		"        return new AjaxLogoutSuccessHandler();\r\n" +
		"    }\r\n\n" +
		"    @Bean\r\n" +
		"    public PasswordEncoder passwordEncoder() {\r\n" +
		"        return new BCryptPasswordEncoder();\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    public void configure(WebSecurity web) throws Exception {\r\n" +
		"        web.ignoring()\r\n" +
		"            .antMatchers(HttpMethod.OPTIONS, \"/**\")\r\n" +
		"            .antMatchers(\"/app/**/*.{js,html}\")\r\n" +
		"            .antMatchers(\"/i18n/**\")\r\n" +
		"            .antMatchers(\"/content/**\")\r\n" +
		"            .antMatchers(\"/swagger-ui/index.html\")\r\n" +
		"            .antMatchers(\"/test/**\");\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    public void configure(HttpSecurity http) throws Exception {\r\n" +
		"        http\r\n" +
		"            .csrf()\r\n" +
		"            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())\r\n" +
		"        .and()\r\n" +
		"            .addFilterBefore(corsFilter, CsrfFilter.class)\r\n" +
		"            .exceptionHandling()\r\n" +
		"            .authenticationEntryPoint(problemSupport)\r\n" +
		"            .accessDeniedHandler(problemSupport)\r\n" +
		"        .and()\r\n" +
		"            .rememberMe()\r\n" +
		"            .rememberMeServices(rememberMeServices)\r\n" +
		"            .rememberMeParameter(\"remember-me\")\r\n" +
		"            .key(jHipsterProperties.getSecurity().getRememberMe().getKey())\r\n" +
		"        .and()\r\n" +
		"            .formLogin()\r\n" +
		"            .loginProcessingUrl(\"/api/authentication\")\r\n" +
		"            .successHandler(ajaxAuthenticationSuccessHandler())\r\n" +
		"            .failureHandler(ajaxAuthenticationFailureHandler())\r\n" +
		"            .usernameParameter(\"j_username\")\r\n" +
		"            .passwordParameter(\"j_password\")\r\n" +
		"            .permitAll()\r\n" +
		"        .and()\r\n" +
		"            .logout()\r\n" +
		"            .logoutUrl(\"/api/logout\")\r\n" +
		"            .logoutSuccessHandler(ajaxLogoutSuccessHandler())\r\n" +
		"            .permitAll()\r\n" +
		"        .and()\r\n" +
		"            .headers()\r\n" +
		"            .frameOptions()\r\n" +
		"            .disable()\r\n" +
		"        .and()\r\n" +
		"            .authorizeRequests()\r\n" +
		"            .antMatchers(\"/api/register\").permitAll()\r\n" +
		"            .antMatchers(\"/api/activate\").permitAll()\r\n" +
		"            .antMatchers(\"/api/authenticate\").permitAll()\r\n" +
		"            .antMatchers(\"/api/account/reset-password/init\").permitAll()\r\n" +
		"            .antMatchers(\"/api/account/reset-password/finish\").permitAll()\r\n" +
		"            .antMatchers(\"/api/**\").authenticated()\r\n" +
		"            .antMatchers(\"/management/health\").permitAll()\r\n" +
		"            .antMatchers(\"/management/info\").permitAll()\r\n" +
		"            .antMatchers(\"/management/**\").hasAuthority(AuthoritiesConstants.ADMIN);\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "SecurityConfiguration";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcConfigFolder(),".","/");
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/java";
	}

}
