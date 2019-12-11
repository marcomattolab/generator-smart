package it.eng.generate.template.conf;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateAsyncConfiguration extends AbstractTemplate {

	public TemplateAsyncConfiguration(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcConfigFolder() +";\r\n\n" +
		"import io.github.jhipster.async.ExceptionHandlingAsyncTaskExecutor;\r\n" +
		"import io.github.jhipster.config.JHipsterProperties;\r\n" +
		"import org.slf4j.Logger;\r\n" +
		"import org.slf4j.LoggerFactory;\r\n" +
		"import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;\r\n" +
		"import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;\r\n" +
		"import org.springframework.context.annotation.Bean;\r\n" +
		"import org.springframework.context.annotation.Configuration;\r\n" +
		"import org.springframework.scheduling.annotation.*;\r\n" +
		"import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;\r\n" +
		"import org.springframework.scheduling.annotation.SchedulingConfigurer;\r\n" +
		"import org.springframework.scheduling.config.ScheduledTaskRegistrar;\r\n" +
		"import java.util.concurrent.Executor;\r\n" +
		"import java.util.concurrent.Executors;\r\n\n" +
		"@Configuration\r\n" +
		"@EnableAsync\r\n" +
		"@EnableScheduling\r\n" +
		"public class " + getClassName() + "  implements AsyncConfigurer, SchedulingConfigurer {\r\n\n" +
		"    private final Logger log = LoggerFactory.getLogger(" + getClassName() + " .class);\r\n" +
		"    private final JHipsterProperties jHipsterProperties;\r\n\n" +
		"    public " + getClassName() + " (JHipsterProperties jHipsterProperties) {\r\n" +
		"        this.jHipsterProperties = jHipsterProperties;\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    @Bean(name = \"taskExecutor\")\r\n" +
		"    public Executor getAsyncExecutor() {\r\n" +
		"        log.debug(\"Creating Async Task Executor\");\r\n" +
		"        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();\r\n" +
		"        executor.setCorePoolSize(jHipsterProperties.getAsync().getCorePoolSize());\r\n" +
		"        executor.setMaxPoolSize(jHipsterProperties.getAsync().getMaxPoolSize());\r\n" +
		"        executor.setQueueCapacity(jHipsterProperties.getAsync().getQueueCapacity());\r\n" +
		"        executor.setThreadNamePrefix(\""+conf.getProjectName()+"-Executor-\");\r\n" +
		"        return new ExceptionHandlingAsyncTaskExecutor(executor);\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {\r\n" +
		"        return new SimpleAsyncUncaughtExceptionHandler();\r\n" +
		"    }\r\n" +
		"    \r\n\n" +
		"    @Override\r\n" +
		"    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {\r\n" +
		"        taskRegistrar.setScheduler(scheduledTaskExecutor());\r\n" +
		"    }\r\n\n" +
		"    @Bean\r\n" +
		"    public Executor scheduledTaskExecutor() {\r\n" +
		"        return Executors.newScheduledThreadPool(jHipsterProperties.getAsync().getCorePoolSize());\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "AsyncConfiguration";
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
