package it.eng.generate.template.report;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateReportUtils extends AbstractTemplate {

	public TemplateReportUtils(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "java";
	}
	
	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();

		// https://www.buildmystring.com/
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcReportUtilFolder() +";\n\n" +
		"import java.awt.image.BufferedImage;\r\n" +
		"import java.io.ByteArrayInputStream;\r\n" +
		"import java.io.ByteArrayOutputStream;\r\n" +
		"import java.io.InputStream;\r\n" +
		"import java.time.ZoneId;\r\n" +
		"import java.time.format.DateTimeFormatter;\r\n" +
		"import java.time.format.FormatStyle;\r\n" +
		"import java.time.temporal.TemporalAccessor;\r\n" +
		"import java.util.Locale;\r\n" +
		"import javax.imageio.ImageIO;\r\n" +
		"import org.slf4j.Logger;\r\n" +
		"import org.slf4j.LoggerFactory;\r\n\n" +
		"public class "+getClassName()+" {\r\n" +
		"	private static final Logger LOGGER = LoggerFactory.getLogger("+getClassName()+".class);\r\n\n" +
		"	public static final String PATH_TEMPLATES = \"/templates/reports/\";\r\n" +
		"	public static final String PATH_IMAGES = \"/templates/images/\";\r\n" +
		"	private static final Locale LOCALE = Locale.ITALY;\r\n\n\n" +
		"	public static BufferedImage getImage(String name) {\r\n" +
		"		BufferedImage bImageFromConvert = null;\r\n" +
		"		try (InputStream inputStream = ReportUtils.class.getResourceAsStream(PATH_IMAGES + name)) {\r\n" +
		"			// Read the image ...\r\n" +
		"			ByteArrayOutputStream baos = new ByteArrayOutputStream();\r\n" +
		"			byte[] buffer = new byte[1024];\r\n" +
		"			int n = 0;\r\n" +
		"			while (-1 != (n = inputStream.read(buffer))) {\r\n" +
		"				baos.write(buffer, 0, n);\r\n" +
		"			}\r\n\n" +
		"			baos.flush();\r\n" +
		"			byte[] imageInByte = baos.toByteArray();\r\n" +
		"			baos.close();\r\n" +
		"			// convert byte array back to BufferedImage\r\n" +
		"			InputStream in = new ByteArrayInputStream(imageInByte);\r\n" +
		"			bImageFromConvert = ImageIO.read(in);\r\n" +
		"		} catch (Exception e) {\r\n" +
		"			LOGGER.error(\"errore in fase di caricamebnto immagine\" + PATH_IMAGES + name, e);\r\n" +
		"		}\r\n" +
		"		return bImageFromConvert;\r\n" +
		"	}\r\n\n\n" +
		"	public static String parseToShortDate(TemporalAccessor date) {\r\n" +
		"		DateTimeFormatter toShortDateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(LOCALE).withZone(ZoneId.systemDefault());\r\n" +
		"		return toShortDateFormatter.format(date);\r\n" +
		"	}\r\n\n\n" +
		"	public static String parseToLongDate(TemporalAccessor date) {\r\n" +
		"		DateTimeFormatter toShortDateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(LOCALE).withZone(ZoneId.systemDefault());\r\n" +
		"		return toShortDateFormatter.format(date);\r\n" +
		"	}\r\n\n\n" +
		"	public static String parseToShortTime(TemporalAccessor date) {\r\n" +
		"		DateTimeFormatter toShortTimeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(LOCALE).withZone(ZoneId.systemDefault());\r\n" +
		"		return toShortTimeFormatter.format(date);\r\n" +
		"	}\r\n\n\n" +
		"	public static String parseToFullDate(TemporalAccessor date) {\r\n" +
		"		DateTimeFormatter toFullDateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)\r\n" +
		"				.withLocale(LOCALE).withZone(ZoneId.systemDefault());\r\n" +
		"		return toFullDateFormatter.format(date);\r\n" +
		"	}\r\n\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "ReportUtils";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcReportUtilFolder(),".","/");
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/java";
	}

}
