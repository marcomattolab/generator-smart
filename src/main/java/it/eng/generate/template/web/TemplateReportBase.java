package it.eng.generate.template.web;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateReportBase extends AbstractTemplate{

	public TemplateReportBase(DataBase dataBase) {
		super(dataBase);
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

		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcWebRestFolder()+";\r\n\n" +
		"import java.io.ByteArrayOutputStream;\r\n" +
		"import java.util.HashMap;\r\n" +
		"import java.util.Map;\r\n" +
		"import ar.com.fdvs.dj.core.DynamicJasperHelper;\r\n" +
		"import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;\r\n" +
		"import ar.com.fdvs.dj.core.layout.LayoutManager;\r\n" +
		"import ar.com.fdvs.dj.domain.DynamicReport;\r\n" +
		"import net.sf.jasperreports.engine.JRDataSource;\r\n" +
		"import net.sf.jasperreports.engine.JRException;\r\n" +
		"import net.sf.jasperreports.engine.JasperFillManager;\r\n" +
		"import net.sf.jasperreports.engine.JasperPrint;\r\n" +
		"import net.sf.jasperreports.engine.JasperReport;\r\n" +
		"import net.sf.jasperreports.engine.export.JRCsvExporter;\r\n" +
		"import net.sf.jasperreports.engine.export.JRPdfExporter;\r\n" +
		"import net.sf.jasperreports.engine.export.JRRtfExporter;\r\n" +
		"import net.sf.jasperreports.engine.export.JRXlsExporter;\r\n" +
		"import net.sf.jasperreports.export.SimpleExporterInput;\r\n" +
		"import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;\r\n" +
		"import net.sf.jasperreports.export.SimpleWriterExporterOutput;\r\n\n" +
		"public abstract class "+getClassName()+" {\r\n\n" +
		"	protected JasperPrint jasperPrint;\r\n" +
		"	protected JasperReport jasperReport;\r\n" +
		"	protected Map<String, Object> params = new HashMap<>();\r\n" +
		"	protected DynamicReport dynamicReport;\r\n" +
		"	public abstract DynamicReport buildReport() throws Exception;\r\n" +
		"	public abstract JRDataSource getDataSource();\r\n" +
		"	public enum FileType {\r\n" +
		"		PDF,\r\n" +
		"		XLS,\r\n" +
		"		CSV,\r\n" +
		"		DOC\r\n" +
		"	}\r\n" +
		"	protected void generateReport() throws Exception {\r\n" +
		"		dynamicReport = buildReport();\r\n" +
		"		JRDataSource dataSource = getDataSource();\r\n" +
		"		jasperReport = DynamicJasperHelper.generateJasperReport(dynamicReport, getLayoutManager(), params);\r\n" +
		"		if (dataSource != null) {\r\n" +
		"			jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);\r\n" +
		"		} else {\r\n" +
		"			jasperPrint = JasperFillManager.fillReport(jasperReport, params);\r\n" +
		"		}\r\n" +
		"	}\r\n" +
		"	protected void exportPdf(JasperPrint jp, ByteArrayOutputStream baos) {\r\n" +
		"		JRPdfExporter exporter = new JRPdfExporter();\r\n" +
		"		exporter.setExporterInput(new SimpleExporterInput(jp));\r\n" +
		"		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));\r\n" +
		"		try {\r\n" +
		"			exporter.exportReport();\r\n" +
		"		} catch (JRException e) {\r\n" +
		"			throw new RuntimeException(e);\r\n" +
		"		}\r\n" +
		"	}\r\n" +
		"	protected void exportXls(JasperPrint jp, ByteArrayOutputStream baos) {\r\n" +
		"		JRXlsExporter exporter = new JRXlsExporter();\r\n" +
		"		exporter.setExporterInput(new SimpleExporterInput(jp));\r\n" +
		"		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));\r\n" +
		"		try {\r\n" +
		"			exporter.exportReport();\r\n" +
		"		} catch (JRException e) {\r\n" +
		"			throw new RuntimeException(e);\r\n" +
		"		}\r\n" +
		"	}\r\n" +
		"	protected void exportRtf(JasperPrint jp, ByteArrayOutputStream baos) {\r\n" +
		"		JRRtfExporter exporter = new JRRtfExporter();\r\n" +
		"		exporter.setExporterInput(new SimpleExporterInput(jp));\r\n" +
		"		exporter.setExporterOutput(new SimpleWriterExporterOutput(baos));\r\n" +
		"		try {\r\n" +
		"			exporter.exportReport();\r\n" +
		"		} catch (JRException e) {\r\n" +
		"			throw new RuntimeException(e);\r\n" +
		"		}\r\n" +
		"	}\r\n" +
		"	protected void exportCsv(JasperPrint jp, ByteArrayOutputStream baos) {\r\n" +
		"		JRCsvExporter exporter = new JRCsvExporter();\r\n" +
		"		exporter.setExporterInput(new SimpleExporterInput(jp));\r\n" +
		"		exporter.setExporterOutput(new SimpleWriterExporterOutput(baos));\r\n" +
		"		try {\r\n" +
		"			exporter.exportReport();\r\n" +
		"		} catch (JRException e) {\r\n" +
		"			throw new RuntimeException(e);\r\n" +
		"		}\r\n" +
		"	}\r\n" +
		"	protected LayoutManager getLayoutManager() {\r\n" +
		"		return new ClassicLayoutManager();\r\n" +
		"	}\r\n" +
		"	public JasperPrint getJasperPrint() {\r\n" +
		"		return jasperPrint;\r\n" +
		"	}\r\n" +
		"	public JasperReport getJasperReport() {\r\n" +
		"		return jasperReport;\r\n" +
		"	}\r\n" +
		"	public Map<String, Object> getParams() {\r\n" +
		"		return params;\r\n" +
		"	}\r\n" +
		"	\r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "BaseReport";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
