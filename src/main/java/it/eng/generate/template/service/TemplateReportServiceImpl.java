package it.eng.generate.template.service;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateReportServiceImpl extends AbstractTemplate{

	public TemplateReportServiceImpl(DataBase database) {
		super(database);
	}
	
	public TemplateReportServiceImpl(DataBase database, Table tabella) {
		super(database);
		this.tabella = tabella;
	}
	
	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcServiceImplFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcServiceImplFolder()+";\r\n\n" +
		"import java.util.List;\r\n" +
		"import org.springframework.stereotype.Service;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcServiceFolder()+".ReportService;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcServiceDtoFolder()+".*;\r\n\n" +
		"@Service\r\n" +
		"public class "+getClassName()+" extends ReportService<"+Utils.getEntityName(tabella)+"DTO> {\r\n\n" +
		"	@Override\r\n" +
		"	public byte[] generateRisorsaFromList(List<"+Utils.getEntityName(tabella)+"DTO> items, ReportOutput output) {\r\n" +
		"		return generateReportFromList(items, \"report"+Utils.getEntityName(tabella)+".jrxml\", composeParamsReportRisorsa(items), output);\r\n" +
		"	}\r\n\n" +
		"}\r\n";
		
		//TODO DEVELOP THIS!
		// 					https://blog.icterra.com/java-reporting-made-easy-with-dynamicjasper/
		// 					http://dynamicjasper.com/docs/current/xref-test/ar/com/fdvs/dj/test/ImageBannerReportTest.html
		String body2 = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcServiceImplFolder()+";\r\n\n" +
		"import java.io.FileOutputStream;\r\n" +
		"import java.util.ArrayList;\r\n" +
		"import java.util.List;\r\n" +
		"import org.springframework.stereotype.Service;\r\n" +
								"import it.exprivia.service.dto.AutoreDTO;\r\n" + //TODO FIXME
		"import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;\r\n" +
		"import net.sf.dynamicreports.report.builder.DynamicReports;\r\n" +
		"import net.sf.dynamicreports.report.builder.column.Columns;\r\n" +
		"import net.sf.dynamicreports.report.builder.component.Components;\r\n" +
		"import net.sf.dynamicreports.report.builder.datatype.DataTypes;\r\n" +
		"import net.sf.dynamicreports.report.constant.HorizontalAlignment;\r\n" +
		"@Service\r\n" +
		"public class ReportAutoreDynamicServiceImpl {\r\n" +
		"		public static void main(String[] args) {\r\n" +
		"			\r\n" +
		"			List<AutoreDTO> autori = new ArrayList<AutoreDTO>();\r\n" +
		"			AutoreDTO autore = new AutoreDTO();\r\n" +
		"			autore.setId(1L);\r\n" +
		"			autore.setCognome(\"Baudo\");\r\n" +
		"			autore.setNome(\"Pippo\");\r\n" +
		"			autore.setSesso(\"Maschio\");\r\n" +
		"			autori.add(autore);\r\n" +
		"			autori.add(autore);\r\n" +
		"			autori.add(autore);\r\n" +
		"			autori.add(autore);\r\n" +
		"			autori.add(autore);\r\n" +
		"			\r\n" +
		"			JasperReportBuilder report = DynamicReports.report();//a new report\r\n" +
		"			report\r\n" +
		"			  .columns(\r\n" +
		"			      Columns.column(\"Id\", \"id\", DataTypes.longType()),\r\n" +
		"			      Columns.column(\"Cognome\", \"cognome\", DataTypes.stringType()),\r\n" +
		"			      Columns.column(\"Nome\", \"nome\", DataTypes.stringType()),\r\n" +
		"			  	  Columns.column(\"Sesso\", \"sesso\", DataTypes.stringType()))\r\n" +
		"			  .title(//title of the report\r\n" +
		"			      Components.text(\"SimpleReportExample\")\r\n" +
		"				  .setHorizontalAlignment(HorizontalAlignment.CENTER))\r\n" +
		"				  .pageFooter(Components.pageXofY())//show page number on the page footer\r\n" +
		"				  .setDataSource(autori);\r\n" +
		"			try {\r\n" +
		"		        //show the report\r\n" +
		"				report.show();\r\n1n" +
        "				//export the report to a pdf file																	\r\n" +
		"				report.toPdf(new FileOutputStream(\"C:/Users/Martorana/Downloads/demoDynamicJasper.pdf\"));			\r\n\n" +	
		"				//export the report to a xls file																	\r\n" +
		"				report.toXls(new FileOutputStream(\"C:/Users/Martorana/Downloads/demoDynamicJasper.xls\"));			\r\n\n" +
		"				//export the report to a CSV file																	\r\n" +
		"				report.toCsv(new FileOutputStream(\"C:/Users/Martorana/Downloads/demoDynamicJasper.csv\"));			\r\n\n" +
		"			} catch (Exception e) {\r\n" +
		"				e.printStackTrace();\r\n" +
		"			}\r\n" +
		"		}\r\n" +
		"	\r\n" +
		"}\r\n";

		return body;
	}

	public String getClassName() {
		//return "ReportServiceImpl";
		return "Report"+Utils.getServiceImplClassName(tabella);
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
