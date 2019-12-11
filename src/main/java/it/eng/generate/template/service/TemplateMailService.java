package it.eng.generate.template.service;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateMailService extends AbstractTemplate{

	public TemplateMailService(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcServiceFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcServiceFolder()+";\r\n\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".User;\r\n" +
		"import io.github.jhipster.config.JHipsterProperties;\r\n" +
		"import java.nio.charset.StandardCharsets;\r\n" +
		"import java.util.Locale;\r\n" +
		"import javax.mail.internet.MimeMessage;\r\n" +
		"import org.slf4j.Logger;\r\n" +
		"import org.slf4j.LoggerFactory;\r\n" +
		"import org.springframework.context.MessageSource;\r\n" +
		"import org.springframework.mail.javamail.JavaMailSender;\r\n" +
		"import org.springframework.mail.javamail.MimeMessageHelper;\r\n" +
		"import org.springframework.scheduling.annotation.Async;\r\n" +
		"import org.springframework.stereotype.Service;\r\n" +
		"import org.thymeleaf.context.Context;\r\n" +
		"import org.thymeleaf.spring5.SpringTemplateEngine;\r\n\n" +
		"/**\r\n" +
		" * Service for sending emails.\r\n" +
		" * <p>\r\n" +
		" * We use the @Async annotation to send emails asynchronously.\r\n" +
		" */\r\n" +
		"@Service\r\n" +
		"public class "+getClassName()+" {\r\n" +
		"    private final Logger log = LoggerFactory.getLogger("+getClassName()+".class);\r\n" +
		"    private static final String USER = \"user\";\r\n" +
		"    private static final String BASE_URL = \"baseUrl\";\r\n" +
		"    private final JHipsterProperties jHipsterProperties;\r\n" +
		"    private final JavaMailSender javaMailSender;\r\n" +
		"    private final MessageSource messageSource;\r\n" +
		"    private final SpringTemplateEngine templateEngine;\r\n\n" +
		"    public "+getClassName()+"(JHipsterProperties jHipsterProperties, JavaMailSender javaMailSender,\r\n" +
		"            MessageSource messageSource, SpringTemplateEngine templateEngine) {\r\n" +
		"        this.jHipsterProperties = jHipsterProperties;\r\n" +
		"        this.javaMailSender = javaMailSender;\r\n" +
		"        this.messageSource = messageSource;\r\n" +
		"        this.templateEngine = templateEngine;\r\n" +
		"    }\r\n\n" +
		"    @Async\r\n" +
		"    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {\r\n" +
		"        log.debug(\"Send email[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}\",\r\n" +
		"            isMultipart, isHtml, to, subject, content);\r\n" +
		"        // Prepare message using a Spring helper\r\n" +
		"        MimeMessage mimeMessage = javaMailSender.createMimeMessage();\r\n" +
		"        try {\r\n" +
		"            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());\r\n" +
		"            message.setTo(to);\r\n" +
		"            message.setFrom(jHipsterProperties.getMail().getFrom());\r\n" +
		"            message.setSubject(subject);\r\n" +
		"            message.setText(content, isHtml);\r\n" +
		"            javaMailSender.send(mimeMessage);\r\n" +
		"            log.debug(\"Sent email to User '{}'\", to);\r\n" +
		"        } catch (Exception e) {\r\n" +
		"            if (log.isDebugEnabled()) {\r\n" +
		"                log.warn(\"Email could not be sent to user '{}'\", to, e);\r\n" +
		"            } else {\r\n" +
		"                log.warn(\"Email could not be sent to user '{}': {}\", to, e.getMessage());\r\n" +
		"            }\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"    @Async\r\n" +
		"    public void sendEmailFromTemplate(User user, String templateName, String titleKey) {\r\n" +
		"        Locale locale = Locale.forLanguageTag(user.getLangKey());\r\n" +
		"        Context context = new Context(locale);\r\n" +
		"        context.setVariable(USER, user);\r\n" +
		"        context.setVariable(BASE_URL, jHipsterProperties.getMail().getBaseUrl());\r\n" +
		"        String content = templateEngine.process(templateName, context);\r\n" +
		"        String subject = messageSource.getMessage(titleKey, null, locale);\r\n" +
		"        sendEmail(user.getEmail(), subject, content, false, true);\r\n" +
		"    }\r\n\n" +
		"    @Async\r\n" +
		"    public void sendActivationEmail(User user) {\r\n" +
		"        log.debug(\"Sending activation email to '{}'\", user.getEmail());\r\n" +
		"        sendEmailFromTemplate(user, \"mail/activationEmail\", \"email.activation.title\");\r\n" +
		"    }\r\n\n" +
		"    @Async\r\n" +
		"    public void sendCreationEmail(User user) {\r\n" +
		"        log.debug(\"Sending creation email to '{}'\", user.getEmail());\r\n" +
		"        sendEmailFromTemplate(user, \"mail/creationEmail\", \"email.activation.title\");\r\n" +
		"    }\r\n\n" +
		"    @Async\r\n" +
		"    public void sendPasswordResetMail(User user) {\r\n" +
		"        log.debug(\"Sending password reset email to '{}'\", user.getEmail());\r\n" +
		"        sendEmailFromTemplate(user, \"mail/passwordResetEmail\", \"email.reset.title\");\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "MailService";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
