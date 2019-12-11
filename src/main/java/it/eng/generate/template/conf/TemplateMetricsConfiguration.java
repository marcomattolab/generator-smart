package it.eng.generate.template.conf;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateMetricsConfiguration extends AbstractTemplate {

	public TemplateMetricsConfiguration(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcConfigFolder() +";\r\n\n" +
		"import io.github.jhipster.config.JHipsterProperties;\r\n" +
		"import com.codahale.metrics.JmxReporter;\r\n" +
		"import com.codahale.metrics.JvmAttributeGaugeSet;\r\n" +
		"import com.codahale.metrics.MetricRegistry;\r\n" +
		"import com.codahale.metrics.Slf4jReporter;\r\n" +
		"import com.codahale.metrics.health.HealthCheckRegistry;\r\n" +
		"import com.codahale.metrics.jcache.JCacheGaugeSet;\r\n" + //Done Add Cache
		"import com.codahale.metrics.jvm.*;\r\n" +
		"import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;\r\n" +
		"import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;\r\n" +
		"import com.zaxxer.hikari.HikariDataSource;\r\n" +
		"import io.prometheus.client.CollectorRegistry;\r\n" +
		"import io.prometheus.client.dropwizard.DropwizardExports;\r\n" +
		"import io.prometheus.client.exporter.MetricsServlet;\r\n" +
		"import org.slf4j.Logger;\r\n" +
		"import org.slf4j.LoggerFactory;\r\n" +
		"import org.slf4j.Marker;\r\n" +
		"import org.slf4j.MarkerFactory;\r\n" +
		"import org.springframework.beans.factory.annotation.Autowired;\r\n" +
		"import org.springframework.boot.web.servlet.ServletContextInitializer;\r\n" +
		"import org.springframework.cache.CacheManager;\r\n" +
		"import org.springframework.context.annotation.*;\r\n" +
		"import javax.annotation.PostConstruct;\r\n" +
		"import javax.servlet.ServletContext;\r\n" +
		"import java.lang.management.ManagementFactory;\r\n" +
		"import java.util.concurrent.TimeUnit;\r\n\n" +
		"@Configuration\r\n" +
		"@EnableMetrics(proxyTargetClass = true)\r\n" +
		"public class "  + getClassName() + " extends MetricsConfigurerAdapter implements ServletContextInitializer {\r\n\n" +
		"    private static final String PROP_METRIC_REG_JVM_MEMORY = \"jvm.memory\";\r\n" +
		"    private static final String PROP_METRIC_REG_JVM_GARBAGE = \"jvm.garbage\";\r\n" +
		"    private static final String PROP_METRIC_REG_JVM_THREADS = \"jvm.threads\";\r\n" +
		"    private static final String PROP_METRIC_REG_JVM_FILES = \"jvm.files\";\r\n" +
		"    private static final String PROP_METRIC_REG_JVM_BUFFERS = \"jvm.buffers\";\r\n" +
		"    private static final String PROP_METRIC_REG_JVM_ATTRIBUTE_SET = \"jvm.attributes\";\r\n\n" +
		"    private static final String PROP_METRIC_REG_JCACHE_STATISTICS = \"jcache.statistics\";\r\n\n" +
		"    private final Logger log = LoggerFactory.getLogger("  + getClassName() + ".class);\r\n\n" +
		"    private MetricRegistry metricRegistry = new MetricRegistry();\r\n\n" +
		"    private HealthCheckRegistry healthCheckRegistry = new HealthCheckRegistry();\r\n\n" +
		"    private final JHipsterProperties jHipsterProperties;\r\n\n" +
		"    private HikariDataSource hikariDataSource;\r\n\n" +
		"    // The cacheManager is injected here to force its initialization, so the JCacheGaugeSet will be correctly created below.\r\n" +
		"    public "  + getClassName() + "(JHipsterProperties jHipsterProperties, CacheManager cacheManager) {\r\n" + //Done Add Cache
		"        this.jHipsterProperties = jHipsterProperties;\r\n" +
		"    }\r\n\n" +
//		"    public MetricsConfiguration(JHipsterProperties jHipsterProperties) {\r\n" +
//		"    	  this.jHipsterProperties = jHipsterProperties;\r\n" +
//		"    }\r\n\n" +
		"    @Autowired(required = false)\r\n" +
		"    public void setHikariDataSource(HikariDataSource hikariDataSource) {\r\n" +
		"        this.hikariDataSource = hikariDataSource;\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    @Bean\r\n" +
		"    public MetricRegistry getMetricRegistry() {\r\n" +
		"        return metricRegistry;\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    @Bean\r\n" +
		"    public HealthCheckRegistry getHealthCheckRegistry() {\r\n" +
		"        return healthCheckRegistry;\r\n" +
		"    }\r\n\n" +
		"    @PostConstruct\r\n" +
		"    public void init() {\r\n" +
		"        log.debug(\"Registering JVM gauges\");\r\n" +
		"        metricRegistry.register(PROP_METRIC_REG_JVM_MEMORY, new MemoryUsageGaugeSet());\r\n" +
		"        metricRegistry.register(PROP_METRIC_REG_JVM_GARBAGE, new GarbageCollectorMetricSet());\r\n" +
		"        metricRegistry.register(PROP_METRIC_REG_JVM_THREADS, new ThreadStatesGaugeSet());\r\n" +
		"        metricRegistry.register(PROP_METRIC_REG_JVM_FILES, new FileDescriptorRatioGauge());\r\n" +
		"        metricRegistry.register(PROP_METRIC_REG_JVM_BUFFERS, new BufferPoolMetricSet(ManagementFactory.getPlatformMBeanServer()));\r\n" +
		"        metricRegistry.register(PROP_METRIC_REG_JVM_ATTRIBUTE_SET, new JvmAttributeGaugeSet());\r\n\n" +
		"        metricRegistry.register(PROP_METRIC_REG_JCACHE_STATISTICS, new JCacheGaugeSet());\r\n\n" +  //Done ADD CACHE
		"        if (hikariDataSource != null) {\r\n" +
		"            log.debug(\"Monitoring the datasource\");\r\n" +
		"            // remove the factory created by HikariDataSourceMetricsPostProcessor until JHipster migrate to Micrometer\r\n" +
		"            hikariDataSource.setMetricsTrackerFactory(null);\r\n" +
		"            hikariDataSource.setMetricRegistry(metricRegistry);\r\n" +
		"        }\r\n\n" +
		"        if (jHipsterProperties.getMetrics().getJmx().isEnabled()) {\r\n" +
		"            log.debug(\"Initializing Metrics JMX reporting\");\r\n" +
		"            JmxReporter jmxReporter = JmxReporter.forRegistry(metricRegistry).build();\r\n" +
		"            jmxReporter.start();\r\n" +
		"        }\r\n\n" +
		"        if (jHipsterProperties.getMetrics().getLogs().isEnabled()) {\r\n" +
		"            log.info(\"Initializing Metrics Log reporting\");\r\n" +
		"            Marker metricsMarker = MarkerFactory.getMarker(\"metrics\");\r\n" +
		"            final Slf4jReporter reporter = Slf4jReporter.forRegistry(metricRegistry)\r\n" +
		"                .outputTo(LoggerFactory.getLogger(\"metrics\"))\r\n" +
		"                .markWith(metricsMarker)\r\n" +
		"                .convertRatesTo(TimeUnit.SECONDS)\r\n" +
		"                .convertDurationsTo(TimeUnit.MILLISECONDS)\r\n" +
		"                .build();\r\n" +
		"            reporter.start(jHipsterProperties.getMetrics().getLogs().getReportFrequency(), TimeUnit.SECONDS);\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    public void onStartup(ServletContext servletContext) {\r\n\n" +
		"        if (jHipsterProperties.getMetrics().getPrometheus().isEnabled()) {\r\n" +
		"            String endpoint = jHipsterProperties.getMetrics().getPrometheus().getEndpoint();\r\n\n" +
		"            log.debug(\"Initializing prometheus metrics exporting via {}\", endpoint);\r\n\n" +
		"            CollectorRegistry.defaultRegistry.register(new DropwizardExports(metricRegistry));\r\n" +
		"            servletContext\r\n" +
		"                .addServlet(\"prometheusMetrics\", new MetricsServlet(CollectorRegistry.defaultRegistry))\r\n" +
		"                .addMapping(endpoint);\r\n" +
		"        }\r\n" +
		"    }\r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "MetricsConfiguration";
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
