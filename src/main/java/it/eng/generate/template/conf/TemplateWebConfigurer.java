package it.eng.generate.template.conf;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateWebConfigurer extends AbstractTemplate {

	public TemplateWebConfigurer(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		//https://www.buildmystring.com/
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcConfigFolder() +";\r\n\n" +
		"import com.codahale.metrics.MetricRegistry;\r\n" +
		"import com.codahale.metrics.servlet.InstrumentedFilter;\r\n" +
		"import com.codahale.metrics.servlets.MetricsServlet;\r\n" +
		"import io.github.jhipster.config.JHipsterConstants;\r\n" +
		"import io.github.jhipster.config.JHipsterProperties;\r\n" +
		"import io.github.jhipster.web.filter.CachingHttpHeadersFilter;\r\n" +
		"import io.undertow.UndertowOptions;\r\n" +
		"import org.slf4j.Logger;\r\n" +
		"import org.slf4j.LoggerFactory;\r\n" +
		"import org.springframework.beans.factory.annotation.Autowired;\r\n" +
		"import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;\r\n" +
		"import org.springframework.boot.web.server.*;\r\n" +
		"import org.springframework.boot.web.servlet.ServletContextInitializer;\r\n" +
		"import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;\r\n" +
		"import org.springframework.context.annotation.Bean;\r\n" +
		"import org.springframework.context.annotation.Configuration;\r\n" +
		"import org.springframework.core.env.Environment;\r\n" +
		"import org.springframework.http.MediaType;\r\n" +
		"import org.springframework.web.cors.CorsConfiguration;\r\n" +
		"import org.springframework.web.cors.UrlBasedCorsConfigurationSource;\r\n" +
		"import org.springframework.web.filter.CorsFilter;\r\n" +
		"import javax.servlet.*;\r\n" +
		"import java.io.File;\r\n" +
		"import java.io.UnsupportedEncodingException;\r\n" +
		"import java.nio.charset.StandardCharsets;\r\n" +
		"import java.nio.file.Paths;\r\n" +
		"import java.util.*;\r\n" +
		"import static java.net.URLDecoder.decode;\r\n\n" +
		"/**\r\n" +
		" * Configuration of web application with Servlet 3.0 APIs.\r\n" +
		" */\r\n" +
		"@Configuration\r\n" +
		"public class "  + getClassName() + " implements ServletContextInitializer, WebServerFactoryCustomizer<WebServerFactory> {\r\n\n" +
		"    private final Logger log = LoggerFactory.getLogger(" + getClassName() + ".class);\r\n" +
		"    private final Environment env;\r\n" +
		"    private final JHipsterProperties jHipsterProperties;\r\n" +
		"    private MetricRegistry metricRegistry;\r\n\n" +
		"    public " + getClassName() + "(Environment env, JHipsterProperties jHipsterProperties) {\r\n" +
		"        this.env = env;\r\n" +
		"        this.jHipsterProperties = jHipsterProperties;\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    public void onStartup(ServletContext servletContext) throws ServletException {\r\n" +
		"        if (env.getActiveProfiles().length != 0) {\r\n" +
		"            log.info(\"Web application configuration, using profiles: {}\", (Object[]) env.getActiveProfiles());\r\n" +
		"        }\r\n" +
		"        EnumSet<DispatcherType> disps = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ASYNC);\r\n" +
		"        initMetrics(servletContext, disps);\r\n" +
		"        if (env.acceptsProfiles(JHipsterConstants.SPRING_PROFILE_PRODUCTION)) {\r\n" +
		"            initCachingHttpHeadersFilter(servletContext, disps);\r\n" +
		"        }\r\n" +
		"        log.info(\"Web application fully configured\");\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Customize the Servlet engine: Mime types, the document root, the cache.\r\n" +
		"     */\r\n" +
		"    @Override\r\n" +
		"    public void customize(WebServerFactory server) {\r\n" +
		"        setMimeMappings(server);\r\n" +
		"        // When running in an IDE or with ./mvnw spring-boot:run, set location of the static web assets.\r\n" +
		"        setLocationForStaticAssets(server);\r\n" +
		"        /*\r\n" +
		"         * Enable HTTP/2 for Undertow - https://twitter.com/ankinson/status/829256167700492288\r\n" +
		"         * HTTP/2 requires HTTPS, so HTTP requests will fallback to HTTP/1.1.\r\n" +
		"         * See the JHipsterProperties class and your application-*.yml configuration files\r\n" +
		"         * for more information.\r\n" +
		"         */\r\n" +
		"        if (jHipsterProperties.getHttp().getVersion().equals(JHipsterProperties.Http.Version.V_2_0) &&\r\n" +
		"            server instanceof UndertowServletWebServerFactory) {\r\n" +
		"            ((UndertowServletWebServerFactory) server)\r\n" +
		"                .addBuilderCustomizers(builder ->\r\n" +
		"                    builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true));\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"    private void setMimeMappings(WebServerFactory server) {\r\n" +
		"        if (server instanceof ConfigurableServletWebServerFactory) {\r\n" +
		"            MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);\r\n" +
		"            // IE issue, see https://github.com/jhipster/generator-jhipster/pull/711\r\n" +
		"            mappings.add(\"html\", MediaType.TEXT_HTML_VALUE + \";charset=\" + StandardCharsets.UTF_8.name().toLowerCase());\r\n" +
		"            // CloudFoundry issue, see https://github.com/cloudfoundry/gorouter/issues/64\r\n" +
		"            mappings.add(\"json\", MediaType.TEXT_HTML_VALUE + \";charset=\" + StandardCharsets.UTF_8.name().toLowerCase());\r\n" +
		"            ConfigurableServletWebServerFactory servletWebServer = (ConfigurableServletWebServerFactory) server;\r\n" +
		"            servletWebServer.setMimeMappings(mappings);\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"    private void setLocationForStaticAssets(WebServerFactory server) {\r\n" +
		"        if (server instanceof ConfigurableServletWebServerFactory) {\r\n" +
		"            ConfigurableServletWebServerFactory servletWebServer = (ConfigurableServletWebServerFactory) server;\r\n" +
		"            File root;\r\n" +
		"            String prefixPath = resolvePathPrefix();\r\n" +
		"            root = new File(prefixPath + \"target/www/\");\r\n" +
		"            if (root.exists() && root.isDirectory()) {\r\n" +
		"                servletWebServer.setDocumentRoot(root);\r\n" +
		"            }\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Resolve path prefix to static resources.\r\n" +
		"     */\r\n" +
		"    private String resolvePathPrefix() {\r\n" +
		"        String fullExecutablePath;\r\n" +
		"        try {\r\n" +
		"            fullExecutablePath = decode(this.getClass().getResource(\"\").getPath(), StandardCharsets.UTF_8.name());\r\n" +
		"        } catch (UnsupportedEncodingException e) {\r\n" +
		"            /* try without decoding if this ever happens */\r\n" +
		"            fullExecutablePath = this.getClass().getResource(\"\").getPath();\r\n" +
		"        }\r\n" +
		"        String rootPath = Paths.get(\".\").toUri().normalize().getPath();\r\n" +
		"        String extractedPath = fullExecutablePath.replace(rootPath, \"\");\r\n" +
		"        int extractionEndIndex = extractedPath.indexOf(\"target/\");\r\n" +
		"        if (extractionEndIndex <= 0) {\r\n" +
		"            return \"\";\r\n" +
		"        }\r\n" +
		"        return extractedPath.substring(0, extractionEndIndex);\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Initializes the caching HTTP Headers Filter.\r\n" +
		"     */\r\n" +
		"    private void initCachingHttpHeadersFilter(ServletContext servletContext,\r\n" +
		"                                              EnumSet<DispatcherType> disps) {\r\n" +
		"        log.debug(\"Registering Caching HTTP Headers Filter\");\r\n" +
		"        FilterRegistration.Dynamic cachingHttpHeadersFilter =\r\n" +
		"            servletContext.addFilter(\"cachingHttpHeadersFilter\",\r\n" +
		"                new CachingHttpHeadersFilter(jHipsterProperties));\r\n" +
		"        cachingHttpHeadersFilter.addMappingForUrlPatterns(disps, true, \"/i18n/*\");\r\n" +
		"        cachingHttpHeadersFilter.addMappingForUrlPatterns(disps, true, \"/content/*\");\r\n" +
		"        cachingHttpHeadersFilter.addMappingForUrlPatterns(disps, true, \"/app/*\");\r\n" +
		"        cachingHttpHeadersFilter.setAsyncSupported(true);\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Initializes Metrics.\r\n" +
		"     */\r\n" +
		"    private void initMetrics(ServletContext servletContext, EnumSet<DispatcherType> disps) {\r\n" +
		"        log.debug(\"Initializing Metrics registries\");\r\n" +
		"        servletContext.setAttribute(InstrumentedFilter.REGISTRY_ATTRIBUTE,\r\n" +
		"            metricRegistry);\r\n" +
		"        servletContext.setAttribute(MetricsServlet.METRICS_REGISTRY,\r\n" +
		"            metricRegistry);\r\n" +
		"        log.debug(\"Registering Metrics Filter\");\r\n" +
		"        FilterRegistration.Dynamic metricsFilter = servletContext.addFilter(\"webappMetricsFilter\",\r\n" +
		"            new InstrumentedFilter());\r\n" +
		"        metricsFilter.addMappingForUrlPatterns(disps, true, \"/*\");\r\n" +
		"        metricsFilter.setAsyncSupported(true);\r\n" +
		"        log.debug(\"Registering Metrics Servlet\");\r\n" +
		"        ServletRegistration.Dynamic metricsAdminServlet =\r\n" +
		"            servletContext.addServlet(\"metricsServlet\", new MetricsServlet());\r\n" +
		"        metricsAdminServlet.addMapping(\"/management/metrics/*\");\r\n" +
		"        metricsAdminServlet.setAsyncSupported(true);\r\n" +
		"        metricsAdminServlet.setLoadOnStartup(2);\r\n" +
		"    }\r\n\n" +
		"    @Bean\r\n" +
		"    public CorsFilter corsFilter() {\r\n" +
		"        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();\r\n" +
		"        CorsConfiguration config = jHipsterProperties.getCors();\r\n" +
		"        if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {\r\n" +
		"            log.debug(\"Registering CORS filter\");\r\n" +
		"            source.registerCorsConfiguration(\"/api/**\", config);\r\n" +
		"            source.registerCorsConfiguration(\"/management/**\", config);\r\n" +
		"            source.registerCorsConfiguration(\"/v2/api-docs\", config);\r\n" +
		"        }\r\n" +
		"        return new CorsFilter(source);\r\n" +
		"    }\r\n\n" +
		"    @Autowired(required = false)\r\n" +
		"    public void setMetricRegistry(MetricRegistry metricRegistry) {\r\n" +
		"        this.metricRegistry = metricRegistry;\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "WebConfigurer";
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
