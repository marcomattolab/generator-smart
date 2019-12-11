package it.eng.generate.template.test;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateWebConfigurerTest extends AbstractTemplate{

	public TemplateWebConfigurerTest(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcConfigFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcConfigFolder()+";\r\n\n" +
		"import com.codahale.metrics.MetricRegistry;\r\n" +
		"import com.codahale.metrics.servlet.InstrumentedFilter;\r\n" +
		"import com.codahale.metrics.servlets.MetricsServlet;\r\n" +
		"import io.github.jhipster.config.JHipsterConstants;\r\n" +
		"import io.github.jhipster.config.JHipsterProperties;\r\n" +
		"import io.github.jhipster.web.filter.CachingHttpHeadersFilter;\r\n" +
		"import io.undertow.Undertow;\r\n" +
		"import io.undertow.Undertow.Builder;\r\n" +
		"import io.undertow.UndertowOptions;\r\n" +
		"import org.apache.commons.io.FilenameUtils;\r\n" +
		"import org.junit.Before;\r\n" +
		"import org.junit.Test;\r\n" +
		"import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;\r\n" +
		"import org.springframework.http.HttpHeaders;\r\n" +
		"import org.springframework.mock.env.MockEnvironment;\r\n" +
		"import org.springframework.mock.web.MockServletContext;\r\n" +
		"import org.springframework.test.util.ReflectionTestUtils;\r\n" +
		"import org.springframework.test.web.servlet.MockMvc;\r\n" +
		"import org.springframework.test.web.servlet.setup.MockMvcBuilders;\r\n" +
		"import org.xnio.OptionMap;\r\n" +
		"import javax.servlet.*;\r\n" +
		"import java.util.*;\r\n" +
		"import static org.assertj.core.api.Assertions.assertThat;\r\n" +
		"import static org.mockito.ArgumentMatchers.any;\r\n" +
		"import static org.mockito.ArgumentMatchers.anyString;\r\n" +
		"import static org.mockito.Mockito.*;\r\n" +
		"import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;\r\n" +
		"import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;\r\n" +
		"import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;\r\n" +
		"import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;\r\n\n" +
		"/**\r\n" +
		" * Unit tests for the WebConfigurer class.\r\n" +
		" *\r\n" +
		" * @see WebConfigurer\r\n" +
		" */\r\n" +
		"public class WebConfigurerTest {\r\n" +
		"    private WebConfigurer webConfigurer;\r\n" +
		"    private MockServletContext servletContext;\r\n" +
		"    private MockEnvironment env;\r\n" +
		"    private JHipsterProperties props;\r\n" +
		"    private MetricRegistry metricRegistry;\r\n\n" +
		"    @Before\r\n" +
		"    public void setup() {\r\n" +
		"        servletContext = spy(new MockServletContext());\r\n" +
		"        doReturn(mock(FilterRegistration.Dynamic.class))\r\n" +
		"            .when(servletContext).addFilter(anyString(), any(Filter.class));\r\n" +
		"        doReturn(mock(ServletRegistration.Dynamic.class))\r\n" +
		"            .when(servletContext).addServlet(anyString(), any(Servlet.class));\r\n" +
		"        env = new MockEnvironment();\r\n" +
		"        props = new JHipsterProperties();\r\n" +
		"        webConfigurer = new WebConfigurer(env, props);\r\n" +
		"        metricRegistry = new MetricRegistry();\r\n" +
		"        webConfigurer.setMetricRegistry(metricRegistry);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testStartUpProdServletContext() throws ServletException {\r\n" +
		"        env.setActiveProfiles(JHipsterConstants.SPRING_PROFILE_PRODUCTION);\r\n" +
		"        webConfigurer.onStartup(servletContext);\r\n" +
		"        assertThat(servletContext.getAttribute(InstrumentedFilter.REGISTRY_ATTRIBUTE)).isEqualTo(metricRegistry);\r\n" +
		"        assertThat(servletContext.getAttribute(MetricsServlet.METRICS_REGISTRY)).isEqualTo(metricRegistry);\r\n" +
		"        verify(servletContext).addFilter(eq(\"webappMetricsFilter\"), any(InstrumentedFilter.class));\r\n" +
		"        verify(servletContext).addServlet(eq(\"metricsServlet\"), any(MetricsServlet.class));\r\n" +
		"        verify(servletContext).addFilter(eq(\"cachingHttpHeadersFilter\"), any(CachingHttpHeadersFilter.class));\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testStartUpDevServletContext() throws ServletException {\r\n" +
		"        env.setActiveProfiles(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT);\r\n" +
		"        webConfigurer.onStartup(servletContext);\r\n" +
		"        assertThat(servletContext.getAttribute(InstrumentedFilter.REGISTRY_ATTRIBUTE)).isEqualTo(metricRegistry);\r\n" +
		"        assertThat(servletContext.getAttribute(MetricsServlet.METRICS_REGISTRY)).isEqualTo(metricRegistry);\r\n" +
		"        verify(servletContext).addFilter(eq(\"webappMetricsFilter\"), any(InstrumentedFilter.class));\r\n" +
		"        verify(servletContext).addServlet(eq(\"metricsServlet\"), any(MetricsServlet.class));\r\n" +
		"        verify(servletContext, never()).addFilter(eq(\"cachingHttpHeadersFilter\"), any(CachingHttpHeadersFilter.class));\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testCustomizeServletContainer() {\r\n" +
		"        env.setActiveProfiles(JHipsterConstants.SPRING_PROFILE_PRODUCTION);\r\n" +
		"        UndertowServletWebServerFactory container = new UndertowServletWebServerFactory();\r\n" +
		"        webConfigurer.customize(container);\r\n" +
		"        assertThat(container.getMimeMappings().get(\"abs\")).isEqualTo(\"audio/x-mpeg\");\r\n" +
		"        assertThat(container.getMimeMappings().get(\"html\")).isEqualTo(\"text/html;charset=utf-8\");\r\n" +
		"        assertThat(container.getMimeMappings().get(\"json\")).isEqualTo(\"text/html;charset=utf-8\");\r\n" +
		"        if (container.getDocumentRoot() != null) {\r\n" +
		"            assertThat(container.getDocumentRoot().getPath()).isEqualTo(FilenameUtils.separatorsToSystem(\"target/www\"));\r\n" +
		"        }\r\n" +
		"        Builder builder = Undertow.builder();\r\n" +
		"        container.getBuilderCustomizers().forEach(c -> c.customize(builder));\r\n" +
		"        OptionMap.Builder serverOptions = (OptionMap.Builder) ReflectionTestUtils.getField(builder, \"serverOptions\");\r\n" +
		"        assertThat(serverOptions.getMap().get(UndertowOptions.ENABLE_HTTP2)).isNull();\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testUndertowHttp2Enabled() {\r\n" +
		"        props.getHttp().setVersion(JHipsterProperties.Http.Version.V_2_0);\r\n" +
		"        UndertowServletWebServerFactory container = new UndertowServletWebServerFactory();\r\n" +
		"        webConfigurer.customize(container);\r\n" +
		"        Builder builder = Undertow.builder();\r\n" +
		"        container.getBuilderCustomizers().forEach(c -> c.customize(builder));\r\n" +
		"        OptionMap.Builder serverOptions = (OptionMap.Builder) ReflectionTestUtils.getField(builder, \"serverOptions\");\r\n" +
		"        assertThat(serverOptions.getMap().get(UndertowOptions.ENABLE_HTTP2)).isTrue();\r\n" +
		"    }\r\n" +
		"    @Test\r\n\n" +
		"    public void testCorsFilterOnApiPath() throws Exception {\r\n" +
		"        props.getCors().setAllowedOrigins(Collections.singletonList(\"*\"));\r\n" +
		"        props.getCors().setAllowedMethods(Arrays.asList(\"GET\", \"POST\", \"PUT\", \"DELETE\"));\r\n" +
		"        props.getCors().setAllowedHeaders(Collections.singletonList(\"*\"));\r\n" +
		"        props.getCors().setMaxAge(1800L);\r\n" +
		"        props.getCors().setAllowCredentials(true);\r\n" +
		"        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new WebConfigurerTestController())\r\n" +
		"            .addFilters(webConfigurer.corsFilter())\r\n" +
		"            .build();\r\n" +
		"        mockMvc.perform(\r\n" +
		"            options(\"/api/test-cors\")\r\n" +
		"                .header(HttpHeaders.ORIGIN, \"other.domain.com\")\r\n" +
		"                .header(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, \"POST\"))\r\n" +
		"            .andExpect(status().isOk())\r\n" +
		"            .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, \"other.domain.com\"))\r\n" +
		"            .andExpect(header().string(HttpHeaders.VARY, \"Origin\"))\r\n" +
		"            .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, \"GET,POST,PUT,DELETE\"))\r\n" +
		"            .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, \"true\"))\r\n" +
		"            .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_MAX_AGE, \"1800\"));\r\n" +
		"        mockMvc.perform(\r\n" +
		"            get(\"/api/test-cors\")\r\n" +
		"                .header(HttpHeaders.ORIGIN, \"other.domain.com\"))\r\n" +
		"            .andExpect(status().isOk())\r\n" +
		"            .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, \"other.domain.com\"));\r\n" +
		"    }\r\n" +
		"    @Test\r\n\n" +
		"    public void testCorsFilterOnOtherPath() throws Exception {\r\n" +
		"        props.getCors().setAllowedOrigins(Collections.singletonList(\"*\"));\r\n" +
		"        props.getCors().setAllowedMethods(Arrays.asList(\"GET\", \"POST\", \"PUT\", \"DELETE\"));\r\n" +
		"        props.getCors().setAllowedHeaders(Collections.singletonList(\"*\"));\r\n" +
		"        props.getCors().setMaxAge(1800L);\r\n" +
		"        props.getCors().setAllowCredentials(true);\r\n" +
		"        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new WebConfigurerTestController())\r\n" +
		"            .addFilters(webConfigurer.corsFilter())\r\n" +
		"            .build();\r\n" +
		"        mockMvc.perform(\r\n" +
		"            get(\"/test/test-cors\")\r\n" +
		"                .header(HttpHeaders.ORIGIN, \"other.domain.com\"))\r\n" +
		"            .andExpect(status().isOk())\r\n" +
		"            .andExpect(header().doesNotExist(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));\r\n" +
		"    }\r\n" +
		"    @Test\r\n\n" +
		"    public void testCorsFilterDeactivated() throws Exception {\r\n" +
		"        props.getCors().setAllowedOrigins(null);\r\n" +
		"        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new WebConfigurerTestController())\r\n" +
		"            .addFilters(webConfigurer.corsFilter())\r\n" +
		"            .build();\r\n" +
		"        mockMvc.perform(\r\n" +
		"            get(\"/api/test-cors\")\r\n" +
		"                .header(HttpHeaders.ORIGIN, \"other.domain.com\"))\r\n" +
		"            .andExpect(status().isOk())\r\n" +
		"            .andExpect(header().doesNotExist(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testCorsFilterDeactivated2() throws Exception {\r\n" +
		"        props.getCors().setAllowedOrigins(new ArrayList<>());\r\n" +
		"        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new WebConfigurerTestController())\r\n" +
		"            .addFilters(webConfigurer.corsFilter())\r\n" +
		"            .build();\r\n" +
		"        mockMvc.perform(\r\n" +
		"            get(\"/api/test-cors\")\r\n" +
		"                .header(HttpHeaders.ORIGIN, \"other.domain.com\"))\r\n" +
		"            .andExpect(status().isOk())\r\n" +
		"            .andExpect(header().doesNotExist(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));\r\n" +
		"    }\r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "WebConfigurerTest";
	}

	public String getSourceFolder() {
		return "src/test/java";
	}

}
