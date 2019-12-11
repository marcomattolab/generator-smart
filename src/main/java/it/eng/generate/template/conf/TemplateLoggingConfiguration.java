package it.eng.generate.template.conf;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateLoggingConfiguration extends AbstractTemplate {

	public TemplateLoggingConfiguration(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcConfigFolder() +";\r\n\n" +
		"import java.net.InetSocketAddress;\r\n" +
		"import java.util.Iterator;\r\n" +
		"import io.github.jhipster.config.JHipsterProperties;\r\n" +
		"import ch.qos.logback.classic.AsyncAppender;\r\n" +
		"import ch.qos.logback.classic.Level;\r\n" +
		"import ch.qos.logback.classic.LoggerContext;\r\n" +
		"import ch.qos.logback.classic.boolex.OnMarkerEvaluator;\r\n" +
		"import ch.qos.logback.classic.spi.ILoggingEvent;\r\n" +
		"import ch.qos.logback.classic.spi.LoggerContextListener;\r\n" +
		"import ch.qos.logback.core.Appender;\r\n" +
		"import ch.qos.logback.core.filter.EvaluatorFilter;\r\n" +
		"import ch.qos.logback.core.spi.ContextAwareBase;\r\n" +
		"import ch.qos.logback.core.spi.FilterReply;\r\n" +
		"import net.logstash.logback.appender.LogstashTcpSocketAppender;\r\n" +
		"import net.logstash.logback.encoder.LogstashEncoder;\r\n" +
		"import net.logstash.logback.stacktrace.ShortenedThrowableConverter;\r\n" +
		"import org.slf4j.Logger;\r\n" +
		"import org.slf4j.LoggerFactory;\r\n" +
		"import org.springframework.beans.factory.annotation.Value;\r\n" +
		"import org.springframework.context.annotation.Configuration;\r\n\n" +
		"@Configuration\r\n" +
		"public class " + getClassName() + " {\r\n\n" +
		"    private static final String LOGSTASH_APPENDER_NAME = \"LOGSTASH\";\r\n" +
		"    private static final String ASYNC_LOGSTASH_APPENDER_NAME = \"ASYNC_LOGSTASH\";\r\n" +
		"    private final Logger log = LoggerFactory.getLogger(" + getClassName() + " .class);\r\n" +
		"    private LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();\r\n" +
		"    private final String appName;\r\n" +
		"    private final String serverPort;\r\n" +
		"    private final JHipsterProperties jHipsterProperties;\r\n\n" +
		"    public " + getClassName() + " (@Value(\"${spring.application.name}\") String appName, @Value(\"${server.port}\") String serverPort,\r\n" +
		"         JHipsterProperties jHipsterProperties) {\r\n" +
		"        this.appName = appName;\r\n" +
		"        this.serverPort = serverPort;\r\n" +
		"        this.jHipsterProperties = jHipsterProperties;\r\n" +
		"        if (jHipsterProperties.getLogging().getLogstash().isEnabled()) {\r\n" +
		"            addLogstashAppender(context);\r\n" +
		"            addContextListener(context);\r\n" +
		"        }\r\n" +
		"        if (jHipsterProperties.getMetrics().getLogs().isEnabled()) {\r\n" +
		"            setMetricsMarkerLogbackFilter(context);\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"    private void addContextListener(LoggerContext context) {\r\n" +
		"        LogbackLoggerContextListener loggerContextListener = new LogbackLoggerContextListener();\r\n" +
		"        loggerContextListener.setContext(context);\r\n" +
		"        context.addListener(loggerContextListener);\r\n" +
		"    }\r\n\n" +
		"    private void addLogstashAppender(LoggerContext context) {\r\n" +
		"        log.info(\"Initializing Logstash logging\");\r\n" +
		"        LogstashTcpSocketAppender logstashAppender = new LogstashTcpSocketAppender();\r\n" +
		"        logstashAppender.setName(LOGSTASH_APPENDER_NAME);\r\n" +
		"        logstashAppender.setContext(context);\r\n" +
		"        String customFields = \"{\\\"app_name\\\":\\\"\" + appName + \"\\\",\\\"app_port\\\":\\\"\" + serverPort + \"\\\"}\";\r\n" +
		"        // More documentation is available at: https://github.com/logstash/logstash-logback-encoder\r\n" +
		"        LogstashEncoder logstashEncoder = new LogstashEncoder();\r\n" +
		"        // Set the Logstash appender config from JHipster properties\r\n" +
		"        logstashEncoder.setCustomFields(customFields);\r\n" +
		"        // Set the Logstash appender config from JHipster properties\r\n" +
		"        logstashAppender.addDestinations(new InetSocketAddress(jHipsterProperties.getLogging().getLogstash().getHost(), jHipsterProperties.getLogging().getLogstash().getPort()));\r\n" +
		"        ShortenedThrowableConverter throwableConverter = new ShortenedThrowableConverter();\r\n" +
		"        throwableConverter.setRootCauseFirst(true);\r\n" +
		"        logstashEncoder.setThrowableConverter(throwableConverter);\r\n" +
		"        logstashEncoder.setCustomFields(customFields);\r\n" +
		"        logstashAppender.setEncoder(logstashEncoder);\r\n" +
		"        logstashAppender.start();\r\n" +
		"        // Wrap the appender in an Async appender for performance\r\n" +
		"        AsyncAppender asyncLogstashAppender = new AsyncAppender();\r\n" +
		"        asyncLogstashAppender.setContext(context);\r\n" +
		"        asyncLogstashAppender.setName(ASYNC_LOGSTASH_APPENDER_NAME);\r\n" +
		"        asyncLogstashAppender.setQueueSize(jHipsterProperties.getLogging().getLogstash().getQueueSize());\r\n" +
		"        asyncLogstashAppender.addAppender(logstashAppender);\r\n" +
		"        asyncLogstashAppender.start();\r\n" +
		"        context.getLogger(\"ROOT\").addAppender(asyncLogstashAppender);\r\n" +
		"    }\r\n\n" +
		"    // Configure a log filter to remove \"metrics\" logs from all appenders except the \"LOGSTASH\" appender\r\n" +
		"    private void setMetricsMarkerLogbackFilter(LoggerContext context) {\r\n" +
		"        log.info(\"Filtering metrics logs from all appenders except the {} appender\", LOGSTASH_APPENDER_NAME);\r\n" +
		"        OnMarkerEvaluator onMarkerMetricsEvaluator = new OnMarkerEvaluator();\r\n" +
		"        onMarkerMetricsEvaluator.setContext(context);\r\n" +
		"        onMarkerMetricsEvaluator.addMarker(\"metrics\");\r\n" +
		"        onMarkerMetricsEvaluator.start();\r\n" +
		"        EvaluatorFilter<ILoggingEvent> metricsFilter = new EvaluatorFilter<>();\r\n" +
		"        metricsFilter.setContext(context);\r\n" +
		"        metricsFilter.setEvaluator(onMarkerMetricsEvaluator);\r\n" +
		"        metricsFilter.setOnMatch(FilterReply.DENY);\r\n" +
		"        metricsFilter.start();\r\n" +
		"        for (ch.qos.logback.classic.Logger logger : context.getLoggerList()) {\r\n" +
		"            for (Iterator<Appender<ILoggingEvent>> it = logger.iteratorForAppenders(); it.hasNext();) {\r\n" +
		"                Appender<ILoggingEvent> appender = it.next();\r\n" +
		"                if (!appender.getName().equals(ASYNC_LOGSTASH_APPENDER_NAME)) {\r\n" +
		"                    log.debug(\"Filter metrics logs from the {} appender\", appender.getName());\r\n" +
		"                    appender.setContext(context);\r\n" +
		"                    appender.addFilter(metricsFilter);\r\n" +
		"                    appender.start();\r\n" +
		"                }\r\n" +
		"            }\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Logback configuration is achieved by configuration file and API.\r\n" +
		"     * When configuration file change is detected, the configuration is reset.\r\n" +
		"     * This listener ensures that the programmatic configuration is also re-applied after reset.\r\n" +
		"     */\r\n" +
		"    class LogbackLoggerContextListener extends ContextAwareBase implements LoggerContextListener {\r\n" +
		"        @Override\r\n" +
		"        public boolean isResetResistant() {\r\n" +
		"            return true;\r\n" +
		"        }\r\n" +
		"        @Override\r\n" +
		"        public void onStart(LoggerContext context) {\r\n" +
		"            addLogstashAppender(context);\r\n" +
		"        }\r\n" +
		"        @Override\r\n" +
		"        public void onReset(LoggerContext context) {\r\n" +
		"            addLogstashAppender(context);\r\n" +
		"        }\r\n" +
		"        @Override\r\n" +
		"        public void onStop(LoggerContext context) {\r\n" +
		"            // Nothing to do.\r\n" +
		"        }\r\n" +
		"        @Override\r\n" +
		"        public void onLevelChange(ch.qos.logback.classic.Logger logger, Level level) {\r\n" +
		"            // Nothing to do.\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"}\r\n";

		
		return body;
	}

	public String getClassName(){
		return "LoggingConfiguration";
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
