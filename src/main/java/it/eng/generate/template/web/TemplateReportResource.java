package it.eng.generate.template.web;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateReportResource extends AbstractTemplate{

	public TemplateReportResource(DataBase dataBase) {
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
		"import java.util.List;\r\n" +
		"import org.apache.commons.collections.CollectionUtils;\r\n" +
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
		"import " + conf.getPackageclass() + "." + conf.getSrcServiceFolder()+ ".*;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcServiceFolder()+ ".ReportService;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcServiceDtoFolder()+ ".*;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcServiceImplFolder()+ ".ReportService.ReportOutput;\r\n\n" +
		"@RestController\r\n" +
		"@RequestMapping(\"/api\")\r\n" +
		"public class "+getClassName()+" {\r\n" +
		"	private static final Logger LOGGER = LoggerFactory.getLogger("+getClassName()+".class);\r\n" +
		"	private final ReportService reportService;\r\n" +
		"	private final AutoreQueryService queryService;\r\n\n" + //TODO DEVELOP THIS! AutoreDTO
		"	public "+getClassName()+"(ReportService reportService, AutoreQueryService queryService) {\r\n" +
		"		this.reportService = reportService;\r\n" +
		"		this.queryService = queryService;\r\n" +
		"	}\r\n\n" +
		"	@GetMapping(\"/exportAutore\")\r\n" +
		"	@Timed\r\n" +
		"	public ResponseEntity<byte[]> exportInterventi(AutoreCriteria criteria, ReportOutput output) throws Exception {\r\n" +
		"		byte[] report = null;\r\n" +
		"		HttpHeaders httpHeaders = new HttpHeaders();\r\n" +
		"		try {\r\n" +
		//"			List<InterventoReportDTO> items = queryService.findInterventiReportByCriteria(criteria);\r\n" + 
		"			List<AutoreDTO> items = queryService.findByCriteria(criteria);\r\n" + //TODO COMPLETE THIS
		"			if (CollectionUtils.isEmpty(items)) {\r\n" +
		"				return new ResponseEntity<byte[]>(report, httpHeaders, HttpStatus.NO_CONTENT);\r\n" +
		"			}\r\n" +
		"			// Generazione Report\r\n" +
		"			report = reportService.generateRisorsaFromList(items, output);\r\n" +
		"			httpHeaders.setContentLength(report.length);\r\n" +
		"			switch (output) {\r\n" +
		"			case PDF:\r\n" +
		"				httpHeaders.setContentType(MediaType.valueOf(\"application/pdf\"));\r\n" +
		"				httpHeaders.setContentDispositionFormData(\"inline\", \"report-autori.pdf\");\r\n" +
		"				break;\r\n" +
		"			case XLS:\r\n" +
		"				httpHeaders.setContentType(MediaType.valueOf(\"application/vnd.ms-excel\"));\r\n" +
		"				httpHeaders.setContentDispositionFormData(\"inline\", \"report-autori.xls\");\r\n" +
		"				break;\r\n" +
		"			default:\r\n" +
		"				break;\r\n" +
		"			}\r\n" +
		"			// Output\r\n" +
		"			return new ResponseEntity<byte[]>(report, httpHeaders, HttpStatus.OK);\r\n" +
		"		} catch (Exception ex) {\r\n" +
		"			LOGGER.error(\"errore in fase di generazione export\", ex);\r\n" +
		"			throw ex;\r\n" +
		"		}\r\n" +
		"	}\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "ReportResource";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
