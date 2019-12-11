package it.eng.generate.template.web;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateAuditResource extends AbstractTemplate{

	public TemplateAuditResource(DataBase dataBase) {
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
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcWebRestFolder()+";\r\n\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcServiceFolder()+ ".AuditEventService;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcWebRestUtilFolder()+ ".PaginationUtil;\r\n" +
		"import io.github.jhipster.web.util.ResponseUtil;\r\n" +
		"import org.springframework.boot.actuate.audit.AuditEvent;\r\n" +
		"import org.springframework.data.domain.Page;\r\n" +
		"import org.springframework.data.domain.Pageable;\r\n" +
		"import org.springframework.http.HttpHeaders;\r\n" +
		"import org.springframework.http.HttpStatus;\r\n" +
		"import org.springframework.http.ResponseEntity;\r\n" +
		"import org.springframework.web.bind.annotation.*;\r\n" +
		"import java.time.LocalDate;\r\n" +
		"import java.time.ZoneId;\r\n" +
		"import java.util.List;\r\n\n" +
		"/**\r\n" +
		" * REST controller for getting the audit events.\r\n" +
		" */\r\n" +
		"@RestController\r\n" +
		"@RequestMapping(\"/management/audits\")\r\n" +
		"public class "+getClassName()+" {\r\n" +
		"    private final AuditEventService auditEventService;\r\n" +
		"    public "+getClassName()+"(AuditEventService auditEventService) {\r\n" +
		"        this.auditEventService = auditEventService;\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * GET /audits : get a page of AuditEvents.\r\n" +
		"     *\r\n" +
		"     * @param pageable the pagination information\r\n" +
		"     * @return the ResponseEntity with status 200 (OK) and the list of AuditEvents in body\r\n" +
		"     */\r\n" +
		"    @GetMapping\r\n" +
		"    public ResponseEntity<List<AuditEvent>> getAll(Pageable pageable) {\r\n" +
		"        Page<AuditEvent> page = auditEventService.findAll(pageable);\r\n" +
		"        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, \"/management/audits\");\r\n" +
		"        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * GET  /audits : get a page of AuditEvents between the fromDate and toDate.\r\n" +
		"     *\r\n" +
		"     * @param fromDate the start of the time period of AuditEvents to get\r\n" +
		"     * @param toDate the end of the time period of AuditEvents to get\r\n" +
		"     * @param pageable the pagination information\r\n" +
		"     * @return the ResponseEntity with status 200 (OK) and the list of AuditEvents in body\r\n" +
		"     */\r\n" +
		"    @GetMapping(params = {\"fromDate\", \"toDate\"})\r\n" +
		"    public ResponseEntity<List<AuditEvent>> getByDates(\r\n" +
		"        @RequestParam(value = \"fromDate\") LocalDate fromDate,\r\n" +
		"        @RequestParam(value = \"toDate\") LocalDate toDate,\r\n" +
		"        Pageable pageable) {\r\n" +
		"        Page<AuditEvent> page = auditEventService.findByDates(\r\n" +
		"            fromDate.atStartOfDay(ZoneId.systemDefault()).toInstant(),\r\n" +
		"            toDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1).toInstant(),\r\n" +
		"            pageable);\r\n" +
		"        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, \"/management/audits\");\r\n" +
		"        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * GET  /audits/:id : get an AuditEvent by id.\r\n" +
		"     *\r\n" +
		"     * @param id the id of the entity to get\r\n" +
		"     * @return the ResponseEntity with status 200 (OK) and the AuditEvent in body, or status 404 (Not Found)\r\n" +
		"     */\r\n" +
		"    @GetMapping(\"/{id:.+}\")\r\n" +
		"    public ResponseEntity<AuditEvent> get(@PathVariable Long id) {\r\n" +
		"        return ResponseUtil.wrapOrNotFound(auditEventService.find(id));\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "AuditResource";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
