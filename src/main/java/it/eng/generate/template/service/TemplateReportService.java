package it.eng.generate.template.service;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateReportService extends AbstractTemplate{

	public TemplateReportService(DataBase database) {
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
		String body =
		"package " + conf.getPackageclass() + "." + conf.getSrcServiceFolder()+";\r\n\n" +
		"import java.io.ByteArrayOutputStream;\r\n" +
		"import java.io.IOException;\r\n" +
		"import java.io.InputStream;\r\n" +
		"import java.time.Instant;\r\n" +
		"import java.util.HashMap;\r\n" +
		"import java.util.List;\r\n" +
		"import org.apache.commons.collections.CollectionUtils;\r\n" +
		"import org.slf4j.Logger;\r\n" +
		"import org.slf4j.LoggerFactory;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcReportUtilFolder()+".ReportUtils;\r\n" +
		"import net.sf.jasperreports.engine.JRException;\r\n" +
		"import net.sf.jasperreports.engine.JRParameter;\r\n" +
		"import net.sf.jasperreports.engine.JasperCompileManager;\r\n" +
		"import net.sf.jasperreports.engine.JasperFillManager;\r\n" +
		"import net.sf.jasperreports.engine.JasperPrint;\r\n" +
		"import net.sf.jasperreports.engine.JasperReport;\r\n" +
		"import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;\r\n" +
		"import net.sf.jasperreports.engine.design.JasperDesign;\r\n" +
		"import net.sf.jasperreports.engine.export.JRPdfExporter;\r\n" +
		"import net.sf.jasperreports.engine.export.JRXlsExporter;\r\n" +
		"import net.sf.jasperreports.engine.xml.JRXmlLoader;\r\n" +
		"import net.sf.jasperreports.export.SimpleExporterInput;\r\n" +
		"import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;\r\n\n" +
		"public abstract class "+getClassName() +"<T> {\r\n" +
		"	private final Logger LOGGER = LoggerFactory.getLogger("+getClassName() +".class);\r\n" +
		"	\r\n" +
		"	public abstract byte[] generateRisorsaFromList(List<T> items, ReportOutput output);\r\n\n" +
		"	public enum ReportOutput {\r\n" +
		"		PDF,\r\n" +
		"		XLS\r\n" +
		"	}\r\n\n" +
		"	protected HashMap<String, Object> composeParamsReportRisorsa(List<T> items) {\r\n" +
		"		HashMap<String, Object> params = new HashMap<String, Object>();\r\n" +
		"		int size = CollectionUtils.size(items);\r\n" +
		"		params.put(\"COUNT_NOTTURNO\", size);\r\n" +
		"		params.put(\"DATA_ESTRAZIONE_REPORT\", ReportUtils.parseToFullDate(Instant.now()));\r\n" +
		"		// LOGO IMAGE\r\n" +
		"		params.put(\"LOGO\", ReportUtils.getImage(\"app-logo.png\"));\r\n" +
		"		return params;\r\n" +
		"	}\r\n\n" +
		"	/**\r\n" +
		"	 * Metodo per la generazione di report a fronte di costruzione di template e jrdatasource\r\n" +
		"	 * \r\n" +
		"	 * @param format Formato export\r\n" +
		"	 * @return byte[]\r\n" +
		"	 */\r\n" +
		"	protected byte[] generateReportFromList(List<T> list, String nameTemplate, HashMap<String, Object> params, ReportOutput format) {\r\n" +
		"		// Create InputStream\r\n" +
		"		try (InputStream reportStream = ReportUtils.class.getResourceAsStream(ReportUtils.PATH_TEMPLATES + nameTemplate)){\r\n" +
		"			// Report parameters, Retrieve Template, Convert template to JasperDesign\r\n" +
		"			JasperDesign jd = JRXmlLoader.load(reportStream);\r\n" +
		"			// Compile design to JasperReport\r\n" +
		"			JasperReport jr = JasperCompileManager.compileReport(jd);\r\n" +
		"			// Create the JasperPrint object\r\n" +
		"			JasperPrint jp = null;\r\n" +
		"			// Export report (PDF) to byte stream\r\n" +
		"			ByteArrayOutputStream baos = new ByteArrayOutputStream();\r\n" +
		"			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);\r\n" +
		"			switch(format) {\r\n" +
		"				case PDF:\r\n" +
		"					jp = JasperFillManager.fillReport(jr, params, dataSource);\r\n" +
		"					exportPdf(jp, baos);\r\n" +
		"					break;\r\n" +
		"				case XLS:\r\n" +
		"					params.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);\r\n" +
		"					jp = JasperFillManager.fillReport(jr, params, dataSource);\r\n" +
		"					exportXls(jp, baos);\r\n" +
		"					break;\r\n" +
		"				default:\r\n" +
		"					LOGGER.error(\"Output Type not supported: \"+format);\r\n" +
		"			}\r\n" +
		"			// Return byte Array\r\n" +
		"			return baos.toByteArray();\r\n" +
		"		} catch (JRException | IOException jre) {\r\n" +
		"			throw new RuntimeException(jre);\r\n" +
		"		}		\r\n" +
		"	}\r\n" +
		"	/**\r\n" +
		"	 * Metodo di creazione report pdf\r\n" +
		"	 * \r\n" +
		"	 * @param jp printer\r\n" +
		"	 * @param baos stream report\r\n" +
		"	 */\r\n" +
		"	private static void exportPdf(JasperPrint jp, ByteArrayOutputStream baos) {\r\n" +
		"		// Create a JRPdfExporter instance\r\n" +
		"		JRPdfExporter exporter = new JRPdfExporter();\r\n" +
		"		// Here we assign the parameters jp and baos to the exporter\r\n" +
		"		exporter.setExporterInput(new SimpleExporterInput(jp));\r\n" +
		"		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));\r\n" +
		"		try {\r\n" +
		"			exporter.exportReport();\r\n" +
		"		} catch (JRException e) {\r\n" +
		"			throw new RuntimeException(e);\r\n" +
		"		}\r\n" +
		"	}	\r\n" +
		"	\r\n" +
		"	/**\r\n" +
		"	 * Metodo di creazione report XLS\r\n" +
		"	 * \r\n" +
		"	 * @param jp printer\r\n" +
		"	 * @param baos stream report\r\n" +
		"	 */\r\n" +
		"	private static void exportXls(JasperPrint jp, ByteArrayOutputStream baos) {\r\n" +
		"		// Create a JRPdfExporter instance\r\n" +
		"		JRXlsExporter exporter = new JRXlsExporter();\r\n" +
		"		// Here we assign the parameters jp and baos to the exporter\r\n" +
		"		exporter.setExporterInput(new SimpleExporterInput(jp));\r\n" +
		"		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));\r\n" +
		"		try {\r\n" +
		"			exporter.exportReport();\r\n" +
		"		} catch (JRException e) {\r\n" +
		"			throw new RuntimeException(e);\r\n" +
		"		}\r\n" +
		"	}\r\n" +
		"	\r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "ReportService";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
