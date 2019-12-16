package it.eng.generate.template.web;

import java.util.List;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateDynamicReportResource extends AbstractTemplate{
	private static final String templateJRXML = "template-report.jrxml";
	
	public TemplateDynamicReportResource(Table tabella) {
		super(tabella);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcWebRestFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		
		//MANAGE THIS
		String entityName = Utils.getEntityName(tabella);
		String entita = Utils.getFirstLowerCase(entityName);
		String Entita = Utils.getFirstUpperCase(entityName);
		
		String body = 
		 "package "+ conf.getPackageclass() + "." + conf.getSrcWebRestFolder()+";\r\n\n" +
		 "import java.io.ByteArrayOutputStream;\r\n" +
		 "import java.util.Date;\r\n" +
		 "import org.slf4j.Logger;\r\n" +
		 "import org.slf4j.LoggerFactory;\r\n" +
		 "import org.springframework.http.HttpHeaders;\r\n" +
		 "import org.springframework.http.HttpStatus;\r\n" +
		 "import org.springframework.http.MediaType;\r\n" +
		 "import org.springframework.http.ResponseEntity;\r\n" +
		 "import org.springframework.web.bind.annotation.GetMapping;\r\n" +
		 "import org.springframework.web.bind.annotation.RequestMapping;\r\n" +
		 "import org.springframework.web.bind.annotation.RestController;\r\n" +
		 "import com.codahale.metrics.annotation.Timed;\r\n" +
		 "import ar.com.fdvs.dj.domain.AutoText;\r\n" +
		 "import ar.com.fdvs.dj.domain.DynamicReport;\r\n" +
		 "import ar.com.fdvs.dj.domain.builders.FastReportBuilder;\r\n" +
		 "import " + conf.getPackageclass() + "." + conf.getSrcServiceFolder()+ ".*;\r\n" +
		 "import " + conf.getPackageclass() + "." + conf.getSrcServiceDtoFolder()+ ".*;\r\n" +
		 "import net.sf.jasperreports.engine.JRDataSource;\r\n" +
		 "import net.sf.jasperreports.engine.JRException;\r\n" +
		 "import net.sf.jasperreports.engine.JasperPrint;\r\n" +
		 "import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;\r\n" +
		 "import net.sf.jasperreports.engine.export.JRPdfExporter;\r\n" +
		 "import net.sf.jasperreports.engine.export.JRXlsExporter;\r\n" +
		 "import net.sf.jasperreports.export.SimpleExporterInput;\r\n" +
		 "import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;\r\n\n" +
		 "@RestController\r\n" +
		 "@RequestMapping(\"/api\")\r\n" +
		 "public class "+getClassName()+" extends BaseReport {\r\n" +
		 "  private final Logger log = LoggerFactory.getLogger("+getClassName()+".class);\r\n" +
		 "	private final "+Entita+"Service "+entita+"Service;\r\n" +
		 "	private final "+Entita+"QueryService "+entita+"QueryService;\r\n" +
		 "	   \r\n" +
		 "	public "+getClassName()+"("+Entita+"Service "+entita+"Service, "+Entita+"QueryService "+entita+"QueryService) {\r\n" +
		 "        this."+entita+"Service = "+entita+"Service;\r\n" +
		 "        this."+entita+"QueryService = "+entita+"QueryService;\r\n" +
		 "    }\r\n" +
		 "	\r\n" +
		 "	@GetMapping(\"/export"+Entita+"\")\r\n" +
		 "	@Timed\r\n" +
		 "	public ResponseEntity<byte[]> export"+Entita+"("+Entita+"Criteria criteria, FileType fileType) throws Exception {\r\n" +
		 "		byte[] report = null;\r\n" +
		 "		HttpHeaders httpHeaders = new HttpHeaders();\r\n" +
		 "		try {\r\n" +
		 "			ByteArrayOutputStream baos = new ByteArrayOutputStream();\r\n" +
		 "			generateReport();\r\n" +
		 "			switch (fileType) {\r\n" +
		 "			case PDF:\r\n" +
		 "				exportPdf(getJasperPrint(), baos);\r\n" +
		 "				httpHeaders.setContentType(MediaType.valueOf(\"application/pdf\"));\r\n" +
		 "				httpHeaders.setContentDispositionFormData(\"inline\", \"report"+Entita+".pdf\");\r\n" +
		 "				break;\r\n" +
		 "			case XLS:\r\n" +
		 "				exportXls(getJasperPrint(), baos);\r\n" +
		 "				httpHeaders.setContentType(MediaType.valueOf(\"application/vnd.ms-excel\"));\r\n" +
		 "				httpHeaders.setContentDispositionFormData(\"inline\", \"report"+Entita+".xls\");\r\n" +
		 "				break;\r\n" +
		 "			default:\r\n" +
		 "				break;\r\n" +
		 "			}\r\n" +
		 "			\r\n" +
		 "			report = baos.toByteArray();\r\n" +
		 "			httpHeaders.setContentLength(report.length);\r\n" +
		 "			return new ResponseEntity<byte[]>(report, httpHeaders, HttpStatus.OK);\r\n" +
		 "		} catch (Exception ex) {\r\n" +
		 "			log.error(\"Errore in fase di generazione export "+entita+"\", ex);\r\n" +
		 "			throw ex;\r\n" +
		 "		}\r\n" +
		 "	}\r\n\n" +
		 "	@Override\r\n" +
		 "	public DynamicReport buildReport() throws Exception {\r\n" +
		 "		FastReportBuilder builder = new FastReportBuilder();\r\n" +
		 "		builder.setMargins(20, 40, 30, 30);\r\n" +
		 "		builder.setDetailHeight(10);\r\n" +
		 "		builder.setTitle(\"Report "+entita+"\");\r\n" +
		 "		builder.setSubtitle(\"Generato in data: \" + new Date());\r\n";
		 
		 //CICLE COLUMN AND TYPE
         List<Column> columns = tabella.getColumns();
         int cSize = 100 / columns.size();
		 for(Column c: columns) {
			 body += "		builder.addColumn(\""+c.getName()+"\", \""+c.getName()+"\", "+c.getTypeColumn().getName()+".class.getName(), "+cSize+");\r\n";
		 }
		 //CICLE COLUMN
		 
		 body +=
		 "		builder.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGMENT_RIGHT);\r\n" +
		 "		builder.setPrintBackgroundOnOddRows(true);\r\n" +
		 "		builder.setUseFullPageWidth(true);\r\n" +
		 "		getParams().put(\"paramAlgo\", \""+entita+":\");\r\n" +
		 "		builder.setTemplateFile(\""+templateJRXML+".jrxml\");\r\n" +
		 "		return builder.build();\r\n" +
		 "	}\r\n\n" +
		 "	@Override\r\n" +
		 "	public JRDataSource getDataSource() {\r\n" +
		 "		return new JRBeanCollectionDataSource( "+entita+"QueryService.findByCriteria(new "+Entita+"Criteria()) );\r\n" +
		 "	}\r\n" +
		 "	\r\n" +
		 "	private void exportPdf(JasperPrint jp, ByteArrayOutputStream baos) {\r\n" +
		 "		JRPdfExporter exporter = new JRPdfExporter();\r\n" +
		 "		exporter.setExporterInput(new SimpleExporterInput(jp));\r\n" +
		 "		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));\r\n" +
		 "		try {\r\n" +
		 "			exporter.exportReport();\r\n" +
		 "		} catch (JRException e) {\r\n" +
		 "			throw new RuntimeException(e);\r\n" +
		 "		}\r\n" +
		 "	}\r\n" +
		 "	\r\n" +
		 "	private static void exportXls(JasperPrint jp, ByteArrayOutputStream baos) {\r\n" +
		 "		JRXlsExporter exporter = new JRXlsExporter();\r\n" +
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
		return "DynamicReportResource";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
