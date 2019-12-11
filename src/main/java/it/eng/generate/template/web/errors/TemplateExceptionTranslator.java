package it.eng.generate.template.web.errors;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateExceptionTranslator extends AbstractTemplate{

	public TemplateExceptionTranslator(DataBase dataBase) {
		super(dataBase);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcWebRestErrorsFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcWebRestErrorsFolder()+";\r\n\n\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcWebRestUtilFolder()+".HeaderUtil;\r\n" +
		"import org.springframework.dao.ConcurrencyFailureException;\r\n" +
		"import org.springframework.http.ResponseEntity;\r\n" +
		"import org.springframework.validation.BindingResult;\r\n" +
		"import org.springframework.web.bind.MethodArgumentNotValidException;\r\n" +
		"import org.springframework.web.bind.annotation.ControllerAdvice;\r\n" +
		"import org.springframework.web.bind.annotation.ExceptionHandler;\r\n" +
		"import org.springframework.web.context.request.NativeWebRequest;\r\n" +
		"import org.zalando.problem.DefaultProblem;\r\n" +
		"import org.zalando.problem.Problem;\r\n" +
		"import org.zalando.problem.ProblemBuilder;\r\n" +
		"import org.zalando.problem.Status;\r\n" +
		"import org.zalando.problem.spring.web.advice.ProblemHandling;\r\n" +
		"import org.zalando.problem.violations.ConstraintViolationProblem;\r\n" +
		"import javax.annotation.Nonnull;\r\n" +
		"import javax.annotation.Nullable;\r\n" +
		"import javax.servlet.http.HttpServletRequest;\r\n" +
		"import java.util.List;\r\n" +
		"import java.util.NoSuchElementException;\r\n" +
		"import java.util.stream.Collectors;\r\n\n" +
		"/**\r\n" +
		" * Controller advice to translate the server side exceptions to client-friendly json structures.\r\n" +
		" * The error response follows RFC7807 - Problem Details for HTTP APIs (https://tools.ietf.org/html/rfc7807)\r\n" +
		" */\r\n" +
		"@ControllerAdvice\r\n" +
		"public class ExceptionTranslator implements ProblemHandling {\r\n\n" +
		"    /**\r\n" +
		"     * Post-process the Problem payload to add the message key for the front-end if needed\r\n" +
		"     */\r\n" +
		"    @Override\r\n" +
		"    public ResponseEntity<Problem> process(@Nullable ResponseEntity<Problem> entity, NativeWebRequest request) {\r\n" +
		"        if (entity == null) {\r\n" +
		"            return entity;\r\n" +
		"        }\r\n" +
		"        Problem problem = entity.getBody();\r\n" +
		"        if (!(problem instanceof ConstraintViolationProblem || problem instanceof DefaultProblem)) {\r\n" +
		"            return entity;\r\n" +
		"        }\r\n" +
		"        ProblemBuilder builder = Problem.builder()\r\n" +
		"            .withType(Problem.DEFAULT_TYPE.equals(problem.getType()) ? ErrorConstants.DEFAULT_TYPE : problem.getType())\r\n" +
		"            .withStatus(problem.getStatus())\r\n" +
		"            .withTitle(problem.getTitle())\r\n" +
		"            .with(\"path\", request.getNativeRequest(HttpServletRequest.class).getRequestURI());\r\n" +
		"        if (problem instanceof ConstraintViolationProblem) {\r\n" +
		"            builder\r\n" +
		"                .with(\"violations\", ((ConstraintViolationProblem) problem).getViolations())\r\n" +
		"                .with(\"message\", ErrorConstants.ERR_VALIDATION);\r\n" +
		"        } else {\r\n" +
		"            builder\r\n" +
		"                .withCause(((DefaultProblem) problem).getCause())\r\n" +
		"                .withDetail(problem.getDetail())\r\n" +
		"                .withInstance(problem.getInstance());\r\n" +
		"            problem.getParameters().forEach(builder::with);\r\n" +
		"            if (!problem.getParameters().containsKey(\"message\") && problem.getStatus() != null) {\r\n" +
		"                builder.with(\"message\", \"error.http.\" + problem.getStatus().getStatusCode());\r\n" +
		"            }\r\n" +
		"        }\r\n" +
		"        return new ResponseEntity<>(builder.build(), entity.getHeaders(), entity.getStatusCode());\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    public ResponseEntity<Problem> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @Nonnull NativeWebRequest request) {\r\n" +
		"        BindingResult result = ex.getBindingResult();\r\n" +
		"        List<FieldErrorVM> fieldErrors = result.getFieldErrors().stream()\r\n" +
		"            .map(f -> new FieldErrorVM(f.getObjectName(), f.getField(), f.getCode()))\r\n" +
		"            .collect(Collectors.toList());\r\n" +
		"        Problem problem = Problem.builder()\r\n" +
		"            .withType(ErrorConstants.CONSTRAINT_VIOLATION_TYPE)\r\n" +
		"            .withTitle(\"Method argument not valid\")\r\n" +
		"            .withStatus(defaultConstraintViolationStatus())\r\n" +
		"            .with(\"message\", ErrorConstants.ERR_VALIDATION)\r\n" +
		"            .with(\"fieldErrors\", fieldErrors)\r\n" +
		"            .build();\r\n" +
		"        return create(ex, problem, request);\r\n" +
		"    }\r\n\n" +
		"    @ExceptionHandler\r\n" +
		"    public ResponseEntity<Problem> handleNoSuchElementException(NoSuchElementException ex, NativeWebRequest request) {\r\n" +
		"        Problem problem = Problem.builder()\r\n" +
		"            .withStatus(Status.NOT_FOUND)\r\n" +
		"            .with(\"message\", ErrorConstants.ENTITY_NOT_FOUND_TYPE)\r\n" +
		"            .build();\r\n" +
		"        return create(ex, problem, request);\r\n" +
		"    }\r\n\n" +
		"    @ExceptionHandler\r\n" +
		"    public ResponseEntity<Problem> handleBadRequestAlertException(BadRequestAlertException ex, NativeWebRequest request) {\r\n" +
		"        return create(ex, request, HeaderUtil.createFailureAlert(ex.getEntityName(), ex.getErrorKey(), ex.getMessage()));\r\n" +
		"    }\r\n\n" +
		"    @ExceptionHandler\r\n" +
		"    public ResponseEntity<Problem> handleConcurrencyFailure(ConcurrencyFailureException ex, NativeWebRequest request) {\r\n" +
		"        Problem problem = Problem.builder()\r\n" +
		"            .withStatus(Status.CONFLICT)\r\n" +
		"            .with(\"message\", ErrorConstants.ERR_CONCURRENCY_FAILURE)\r\n" +
		"            .build();\r\n" +
		"        return create(ex, problem, request);\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "ExceptionTranslator";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
