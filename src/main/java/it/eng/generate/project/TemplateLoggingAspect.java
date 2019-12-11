package it.eng.generate.project;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateLoggingAspect extends AbstractTemplate {

	public TemplateLoggingAspect(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		 // https://www.buildmystring.com/
		String body = "\npackage "+ conf.getPackageclass() + "." + conf.getSrcAopLoggingFolder() +";\r\n\n"+
		//"import "+conf.getPackageclass()+".JHipsterConstants;\r\n" +
		"import io.github.jhipster.config.JHipsterConstants;\r\n" +
		"import org.aspectj.lang.JoinPoint;\r\n" +
		"import org.aspectj.lang.ProceedingJoinPoint;\r\n" +
		"import org.aspectj.lang.annotation.AfterThrowing;\r\n" +
		"import org.aspectj.lang.annotation.Around;\r\n" +
		"import org.aspectj.lang.annotation.Aspect;\r\n" +
		"import org.aspectj.lang.annotation.Pointcut;\r\n" +
		"import org.slf4j.Logger;\r\n" +
		"import org.slf4j.LoggerFactory;\r\n" +
		"import org.springframework.core.env.Environment;\r\n" +
		"import java.util.Arrays;\r\n\n" +
		"/**\r\n" +
		" * Aspect for logging execution of service and repository Spring components.\r\n" +
		" *\r\n" +
		" * By default, it only runs with the \"dev\" profile.\r\n" +
		" */\r\n" +
		"@Aspect\r\n" +
		"public class  " + getClassName() + " {\r\n" +
		"    private final Logger log = LoggerFactory.getLogger(this.getClass());\r\n" +
		"    private final Environment env;\r\n" +
		"    public  " + getClassName() + "(Environment env) {\r\n" +
		"        this.env = env;\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Pointcut that matches all repositories, services and Web REST endpoints.\r\n" +
		"     */\r\n" +
		"    @Pointcut(\"within(@org.springframework.stereotype.Repository *)\" +\r\n" +
		"        \" || within(@org.springframework.stereotype.Service *)\" +\r\n" +
		"        \" || within(@org.springframework.web.bind.annotation.RestController *)\")\r\n" +
		"    public void springBeanPointcut() {\r\n" +
		"        // Method is empty as this is just a Pointcut, the implementations are in the advices.\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Pointcut that matches all Spring beans in the application's main packages.\r\n" +
		"     */\r\n" +
		"    @Pointcut(\"within(it.eng.repository..*)\"+\r\n" +
		"        \" || within(it.eng.service..*)\"+\r\n" +
		"        \" || within(it.eng.web.rest..*)\")\r\n" +
		"    public void applicationPackagePointcut() {\r\n" +
		"        // Method is empty as this is just a Pointcut, the implementations are in the advices.\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Advice that logs methods throwing exceptions.\r\n" +
		"     *\r\n" +
		"     * @param joinPoint join point for advice\r\n" +
		"     * @param e exception\r\n" +
		"     */\r\n" +
		"    @AfterThrowing(pointcut = \"applicationPackagePointcut() && springBeanPointcut()\", throwing = \"e\")\r\n" +
		"    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {\r\n" +
		"        if (env.acceptsProfiles(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT)) {\r\n" +
		"            log.error(\"Exception in {}.{}() with cause = \\'{}\\' and exception = \\'{}\\'\", joinPoint.getSignature().getDeclaringTypeName(),\r\n" +
		"                joinPoint.getSignature().getName(), e.getCause() != null? e.getCause() : \"NULL\", e.getMessage(), e);\r\n" +
		"        } else {\r\n" +
		"            log.error(\"Exception in {}.{}() with cause = {}\", joinPoint.getSignature().getDeclaringTypeName(),\r\n" +
		"                joinPoint.getSignature().getName(), e.getCause() != null? e.getCause() : \"NULL\");\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Advice that logs when a method is entered and exited.\r\n" +
		"     *\r\n" +
		"     * @param joinPoint join point for advice\r\n" +
		"     * @return result\r\n" +
		"     * @throws Throwable throws IllegalArgumentException\r\n" +
		"     */\r\n" +
		"    @Around(\"applicationPackagePointcut() && springBeanPointcut()\")\r\n" +
		"    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {\r\n" +
		"        if (log.isDebugEnabled()) {\r\n" +
		"            log.debug(\"Enter: {}.{}() with argument[s] = {}\", joinPoint.getSignature().getDeclaringTypeName(),\r\n" +
		"                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));\r\n" +
		"        }\r\n" +
		"        try {\r\n" +
		"            Object result = joinPoint.proceed();\r\n" +
		"            if (log.isDebugEnabled()) {\r\n" +
		"                log.debug(\"Exit: {}.{}() with result = {}\", joinPoint.getSignature().getDeclaringTypeName(),\r\n" +
		"                    joinPoint.getSignature().getName(), result);\r\n" +
		"            }\r\n" +
		"            return result;\r\n" +
		"        } catch (IllegalArgumentException e) {\r\n" +
		"            log.error(\"Illegal argument: {} in {}.{}()\", Arrays.toString(joinPoint.getArgs()),\r\n" +
		"                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());\r\n" +
		"            throw e;\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "LoggingAspect";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcAopLoggingFolder(),".","/");
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/java";
	}

}
