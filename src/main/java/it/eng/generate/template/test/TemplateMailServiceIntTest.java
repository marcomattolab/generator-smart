package it.eng.generate.template.test;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateMailServiceIntTest extends AbstractTemplate{

	public TemplateMailServiceIntTest(DataBase database) {
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
		String mailAddress = "test"; //FIXME => conf.getProjectName().toLowerCase();
		
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcServiceFolder()+";\r\n\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcConfigFolder()+".Constants;\r\n\n" +
		"import "+ conf.getPackageclass() + "." + Utils.getClassNameCamelCase(conf.getProjectName()) + conf.getApp() + ";\r\n\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".User;\r\n\n" +
		"import io.github.jhipster.config.JHipsterProperties;\r\n" +
		"import org.junit.Before;\r\n" +
		"import org.junit.Test;\r\n" +
		"import org.junit.runner.RunWith;\r\n" +
		"import org.mockito.ArgumentCaptor;\r\n" +
		"import org.mockito.Captor;\r\n" +
		"import org.mockito.MockitoAnnotations;\r\n" +
		"import org.mockito.Spy;\r\n" +
		"import org.springframework.beans.factory.annotation.Autowired;\r\n" +
		"import org.springframework.boot.test.context.SpringBootTest;\r\n" +
		"import org.springframework.context.MessageSource;\r\n" +
		"import org.springframework.mail.MailSendException;\r\n" +
		"import org.springframework.mail.javamail.JavaMailSenderImpl;\r\n" +
		"import org.springframework.test.context.junit4.SpringRunner;\r\n" +
		"import org.thymeleaf.spring5.SpringTemplateEngine;\r\n" +
		"import javax.mail.Multipart;\r\n" +
		"import javax.mail.internet.MimeBodyPart;\r\n" +
		"import javax.mail.internet.MimeMessage;\r\n" +
		"import javax.mail.internet.MimeMultipart;\r\n" +
		"import java.io.ByteArrayOutputStream;\r\n" +
		"import static org.assertj.core.api.Assertions.assertThat;\r\n" +
		"import static org.mockito.ArgumentMatchers.any;\r\n" +
		"import static org.mockito.Mockito.*;\r\n\n" +
		"@RunWith(SpringRunner.class)\r\n" +
		"@SpringBootTest(classes = "+Utils.getClassNameCamelCase(conf.getProjectName()) + conf.getApp()+".class)\r\n" +
		"public class MailServiceIntTest {\r\n\n" +
		"    @Autowired\r\n" +
		"    private JHipsterProperties jHipsterProperties;\r\n\n" +
		"    @Autowired\r\n" +
		"    private MessageSource messageSource;\r\n\n" +
		"    @Autowired\r\n" +
		"    private SpringTemplateEngine templateEngine;\r\n\n" +
		"    @Spy\r\n" +
		"    private JavaMailSenderImpl javaMailSender;\r\n\n" +
		"    @Captor\r\n" +
		"    private ArgumentCaptor<MimeMessage> messageCaptor;\r\n\n" +
		"    private MailService mailService;\r\n\n" +
		"    @Before\r\n" +
		"    public void setup() {\r\n" +
		"        MockitoAnnotations.initMocks(this);\r\n" +
		"        doNothing().when(javaMailSender).send(any(MimeMessage.class));\r\n" +
		"        mailService = new MailService(jHipsterProperties, javaMailSender, messageSource, templateEngine);\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testSendEmail() throws Exception {\r\n" +
		"        mailService.sendEmail(\"john.doe@example.com\", \"testSubject\", \"testContent\", false, false);\r\n" +
		"        verify(javaMailSender).send(messageCaptor.capture());\r\n" +
		"        MimeMessage message = messageCaptor.getValue();\r\n" +
		"        assertThat(message.getSubject()).isEqualTo(\"testSubject\");\r\n" +
		"        assertThat(message.getAllRecipients()[0].toString()).isEqualTo(\"john.doe@example.com\");\r\n" +
		"        assertThat(message.getFrom()[0].toString()).isEqualTo(\""+mailAddress+"@"+"localhost\");\r\n" +
		"        assertThat(message.getContent()).isInstanceOf(String.class);\r\n" +
		"        assertThat(message.getContent().toString()).isEqualTo(\"testContent\");\r\n" +
		"        assertThat(message.getDataHandler().getContentType()).isEqualTo(\"text/plain; charset=UTF-8\");\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testSendHtmlEmail() throws Exception {\r\n" +
		"        mailService.sendEmail(\"john.doe@example.com\", \"testSubject\", \"testContent\", false, true);\r\n" +
		"        verify(javaMailSender).send(messageCaptor.capture());\r\n" +
		"        MimeMessage message = messageCaptor.getValue();\r\n" +
		"        assertThat(message.getSubject()).isEqualTo(\"testSubject\");\r\n" +
		"        assertThat(message.getAllRecipients()[0].toString()).isEqualTo(\"john.doe@example.com\");\r\n" +
		"        assertThat(message.getFrom()[0].toString()).isEqualTo(\""+mailAddress+"@"+"localhost\");\r\n" +
		"        assertThat(message.getContent()).isInstanceOf(String.class);\r\n" +
		"        assertThat(message.getContent().toString()).isEqualTo(\"testContent\");\r\n" +
		"        assertThat(message.getDataHandler().getContentType()).isEqualTo(\"text/html;charset=UTF-8\");\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testSendMultipartEmail() throws Exception {\r\n" +
		"        mailService.sendEmail(\"john.doe@example.com\", \"testSubject\", \"testContent\", true, false);\r\n" +
		"        verify(javaMailSender).send(messageCaptor.capture());\r\n" +
		"        MimeMessage message = messageCaptor.getValue();\r\n" +
		"        MimeMultipart mp = (MimeMultipart) message.getContent();\r\n" +
		"        MimeBodyPart part = (MimeBodyPart) ((MimeMultipart) mp.getBodyPart(0).getContent()).getBodyPart(0);\r\n" +
		"        ByteArrayOutputStream aos = new ByteArrayOutputStream();\r\n" +
		"        part.writeTo(aos);\r\n" +
		"        assertThat(message.getSubject()).isEqualTo(\"testSubject\");\r\n" +
		"        assertThat(message.getAllRecipients()[0].toString()).isEqualTo(\"john.doe@example.com\");\r\n" +
		"        assertThat(message.getFrom()[0].toString()).isEqualTo(\""+mailAddress+"@"+"localhost\");\r\n" +
		"        assertThat(message.getContent()).isInstanceOf(Multipart.class);\r\n" +
		"        assertThat(aos.toString()).isEqualTo(\"\\r\\ntestContent\");\r\n" +
		"        assertThat(part.getDataHandler().getContentType()).isEqualTo(\"text/plain; charset=UTF-8\");\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testSendMultipartHtmlEmail() throws Exception {\r\n" +
		"        mailService.sendEmail(\"john.doe@example.com\", \"testSubject\", \"testContent\", true, true);\r\n" +
		"        verify(javaMailSender).send(messageCaptor.capture());\r\n" +
		"        MimeMessage message = messageCaptor.getValue();\r\n" +
		"        MimeMultipart mp = (MimeMultipart) message.getContent();\r\n" +
		"        MimeBodyPart part = (MimeBodyPart) ((MimeMultipart) mp.getBodyPart(0).getContent()).getBodyPart(0);\r\n" +
		"        ByteArrayOutputStream aos = new ByteArrayOutputStream();\r\n" +
		"        part.writeTo(aos);\r\n" +
		"        assertThat(message.getSubject()).isEqualTo(\"testSubject\");\r\n" +
		"        assertThat(message.getAllRecipients()[0].toString()).isEqualTo(\"john.doe@example.com\");\r\n" +
		"        assertThat(message.getFrom()[0].toString()).isEqualTo(\""+mailAddress+"@"+"localhost\");\r\n" +
		"        assertThat(message.getContent()).isInstanceOf(Multipart.class);\r\n" +
		"        assertThat(aos.toString()).isEqualTo(\"\\r\\ntestContent\");\r\n" +
		"        assertThat(part.getDataHandler().getContentType()).isEqualTo(\"text/html;charset=UTF-8\");\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testSendEmailFromTemplate() throws Exception {\r\n" +
		"        User user = new User();\r\n" +
		"        user.setLogin(\"john\");\r\n" +
		"        user.setEmail(\"john.doe@example.com\");\r\n" +
		"        user.setLangKey(\"en\");\r\n" +
		"        mailService.sendEmailFromTemplate(user, \"mail/testEmail\", \"email.test.title\");\r\n" +
		"        verify(javaMailSender).send(messageCaptor.capture());\r\n" +
		"        MimeMessage message = messageCaptor.getValue();\r\n" +
		"        assertThat(message.getSubject()).isEqualTo(\"test title\");\r\n" +
		"        assertThat(message.getAllRecipients()[0].toString()).isEqualTo(user.getEmail());\r\n" +
		"        assertThat(message.getFrom()[0].toString()).isEqualTo(\""+mailAddress+"@"+"localhost\");\r\n" +
		"        assertThat(message.getContent().toString()).isEqualToNormalizingNewlines(\"<html>test title, http://127.0.0.1:8080, john</html>\\n\");\r\n" +
		"        assertThat(message.getDataHandler().getContentType()).isEqualTo(\"text/html;charset=UTF-8\");\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testSendActivationEmail() throws Exception {\r\n" +
		"        User user = new User();\r\n" +
		"        user.setLangKey(Constants.DEFAULT_LANGUAGE);\r\n" +
		"        user.setLogin(\"john\");\r\n" +
		"        user.setEmail(\"john.doe@example.com\");\r\n" +
		"        mailService.sendActivationEmail(user);\r\n" +
		"        verify(javaMailSender).send(messageCaptor.capture());\r\n" +
		"        MimeMessage message = messageCaptor.getValue();\r\n" +
		"        assertThat(message.getAllRecipients()[0].toString()).isEqualTo(user.getEmail());\r\n" +
		"        assertThat(message.getFrom()[0].toString()).isEqualTo(\""+mailAddress+"@"+"localhost\");\r\n" +
		"        assertThat(message.getContent().toString()).isNotEmpty();\r\n" +
		"        assertThat(message.getDataHandler().getContentType()).isEqualTo(\"text/html;charset=UTF-8\");\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testCreationEmail() throws Exception {\r\n" +
		"        User user = new User();\r\n" +
		"        user.setLangKey(Constants.DEFAULT_LANGUAGE);\r\n" +
		"        user.setLogin(\"john\");\r\n" +
		"        user.setEmail(\"john.doe@example.com\");\r\n" +
		"        mailService.sendCreationEmail(user);\r\n" +
		"        verify(javaMailSender).send(messageCaptor.capture());\r\n" +
		"        MimeMessage message = messageCaptor.getValue();\r\n" +
		"        assertThat(message.getAllRecipients()[0].toString()).isEqualTo(user.getEmail());\r\n" +
		"        assertThat(message.getFrom()[0].toString()).isEqualTo(\""+mailAddress+"@"+"localhost\");\r\n" +
		"        assertThat(message.getContent().toString()).isNotEmpty();\r\n" +
		"        assertThat(message.getDataHandler().getContentType()).isEqualTo(\"text/html;charset=UTF-8\");\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testSendPasswordResetMail() throws Exception {\r\n" +
		"        User user = new User();\r\n" +
		"        user.setLangKey(Constants.DEFAULT_LANGUAGE);\r\n" +
		"        user.setLogin(\"john\");\r\n" +
		"        user.setEmail(\"john.doe@example.com\");\r\n" +
		"        mailService.sendPasswordResetMail(user);\r\n" +
		"        verify(javaMailSender).send(messageCaptor.capture());\r\n" +
		"        MimeMessage message = messageCaptor.getValue();\r\n" +
		"        assertThat(message.getAllRecipients()[0].toString()).isEqualTo(user.getEmail());\r\n" +
		"        assertThat(message.getFrom()[0].toString()).isEqualTo(\""+mailAddress+"@"+"localhost\");\r\n" +
		"        assertThat(message.getContent().toString()).isNotEmpty();\r\n" +
		"        assertThat(message.getDataHandler().getContentType()).isEqualTo(\"text/html;charset=UTF-8\");\r\n" +
		"    }\r\n\n" +
		"    @Test\r\n" +
		"    public void testSendEmailWithException() throws Exception {\r\n" +
		"        doThrow(MailSendException.class).when(javaMailSender).send(any(MimeMessage.class));\r\n" +
		"        mailService.sendEmail(\"john.doe@example.com\", \"testSubject\", \"testContent\", false, false);\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "MailServiceIntTest";
	}

	public String getSourceFolder() {
		return "src/test/java";
	}

}
