package it.eng.generate.template.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateResource extends AbstractTemplate{
	private static final String templateJRXML = "template-report.jrxml";
	
	public TemplateResource(Table tabella) {
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
		"import java.net.URI;\r\n" +
		"import java.net.URISyntaxException;\r\n" +
		"import java.util.Date;\r\n" +
		"import java.util.List;\r\n" +
		"import java.util.Optional;\r\n" +
		"import javax.validation.Valid;\r\n" +
		"import org.apache.commons.collections.CollectionUtils;\r\n" +
		"import org.slf4j.Logger;\r\n" +
		"import org.slf4j.LoggerFactory;\r\n" +
		"import org.springframework.data.domain.Page;\r\n" +
		"import org.springframework.data.domain.Pageable;\r\n" +
		"import org.springframework.http.HttpHeaders;\r\n" +
		"import org.springframework.http.HttpStatus;\r\n" +
		"import org.springframework.http.ResponseEntity;\r\n" +
		"import org.springframework.web.bind.annotation.DeleteMapping;\r\n" +
		"import org.springframework.web.bind.annotation.GetMapping;\r\n" +
		"import org.springframework.web.bind.annotation.PathVariable;\r\n" +
		"import org.springframework.web.bind.annotation.PostMapping;\r\n" +
		"import org.springframework.web.bind.annotation.PutMapping;\r\n" +
		"import org.springframework.web.bind.annotation.RequestBody;\r\n" +
		"import org.springframework.web.bind.annotation.RequestMapping;\r\n" +
		"import org.springframework.web.bind.annotation.RestController;\r\n" +
		"import com.codahale.metrics.annotation.Timed;\r\n" +
		"import ar.com.fdvs.dj.domain.AutoText;\r\n" +
		"import ar.com.fdvs.dj.domain.DynamicReport;\r\n" +
		"import ar.com.fdvs.dj.domain.builders.FastReportBuilder;\r\n" +
		"import io.github.jhipster.web.util.ResponseUtil;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcServiceFolder()+ ".*;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcServiceDtoFolder()+ ".*;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcWebRestErrorsFolder()+ ".*;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcWebRestUtilFolder()+ ".*;\r\n\n" +
		"/**\r\n" +
		" * REST controller for managing "+Utils.getEntityName(tabella)+".\r\n" +
		" */\r\n" +
		"@RestController\r\n" +
		"@RequestMapping(\"/api\")\r\n" +
		"public class "+getClassName() +" extends BaseReport {\r\n" +
		"    private final Logger log = LoggerFactory.getLogger("+getClassName() +".class);\r\n" +
		"    private static final String ENTITY_NAME = \""+Utils.getClassNameLowerCase(tabella)+"\";\r\n" +
		"    private final "+Utils.getEntityName(tabella)+"Service "+Utils.getClassNameLowerCase(tabella)+"Service;\r\n" +
		"    private final "+Utils.getEntityName(tabella)+"QueryService "+Utils.getClassNameLowerCase(tabella)+"QueryService;\r\n\n" +
		"    public "+getClassName()+"("+Utils.getEntityName(tabella)+"Service "+Utils.getClassNameLowerCase(tabella)+"Service, "+Utils.getEntityName(tabella)+"QueryService "+Utils.getClassNameLowerCase(tabella)+"QueryService) {\r\n" +
		"        this."+Utils.getClassNameLowerCase(tabella)+"Service = "+Utils.getClassNameLowerCase(tabella)+"Service;\r\n" +
		"        this."+Utils.getClassNameLowerCase(tabella)+"QueryService = "+Utils.getClassNameLowerCase(tabella)+"QueryService;\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * POST  /"+Utils.getClassNameLowerCase(tabella)+"s : Create a new "+Utils.getClassNameLowerCase(tabella)+".\r\n" +
		"     *\r\n" +
		"     * @param "+Utils.getClassNameLowerCase(tabella)+"DTO the "+Utils.getClassNameLowerCase(tabella)+"DTO to create\r\n" +
		"     * @return the ResponseEntity with status 201 (Created) and with body the new "+Utils.getClassNameLowerCase(tabella)+"DTO, or with status 400 (Bad Request) if the "+Utils.getClassNameLowerCase(tabella)+" has already an ID\r\n" +
		"     * @throws URISyntaxException if the Location URI syntax is incorrect\r\n" +
		"     */\r\n" +
		"    @PostMapping(\"/"+Utils.getClassNameLowerCase(tabella)+"s\")\r\n" +
		"    @Timed\r\n" +
		"    public ResponseEntity<"+Utils.getEntityName(tabella)+"DTO> create"+Utils.getEntityName(tabella)+"(@Valid @RequestBody "+Utils.getEntityName(tabella)+"DTO "+Utils.getClassNameLowerCase(tabella)+"DTO) throws URISyntaxException {\r\n" +
		"        log.debug(\"REST request to save "+Utils.getEntityName(tabella)+" : {}\", "+Utils.getClassNameLowerCase(tabella)+"DTO);\r\n" +
		"        if ("+Utils.getClassNameLowerCase(tabella)+"DTO.getId() != null) {\r\n" +
		"            throw new BadRequestAlertException(\"A new "+Utils.getClassNameLowerCase(tabella)+" cannot already have an ID\", ENTITY_NAME, \"idexists\");\r\n" +
		"        }\r\n" +
		"        "+Utils.getEntityName(tabella)+"DTO result = "+Utils.getClassNameLowerCase(tabella)+"Service.save("+Utils.getClassNameLowerCase(tabella)+"DTO);\r\n" +
		"        return ResponseEntity.created(new URI(\"/api/"+Utils.getClassNameLowerCase(tabella)+"s/\" + result.getId()))\r\n" +
		"            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))\r\n" +
		"            .body(result);\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * PUT  /"+Utils.getClassNameLowerCase(tabella)+"s : Updates an existing "+Utils.getClassNameLowerCase(tabella)+".\r\n" +
		"     *\r\n" +
		"     * @param "+Utils.getClassNameLowerCase(tabella)+"DTO the "+Utils.getClassNameLowerCase(tabella)+"DTO to update\r\n" +
		"     * @return the ResponseEntity with status 200 (OK) and with body the updated "+Utils.getClassNameLowerCase(tabella)+"DTO,\r\n" +
		"     * or with status 400 (Bad Request) if the "+Utils.getClassNameLowerCase(tabella)+"DTO is not valid,\r\n" +
		"     * or with status 500 (Internal Server Error) if the "+Utils.getClassNameLowerCase(tabella)+"DTO couldn't be updated\r\n" +
		"     * @throws URISyntaxException if the Location URI syntax is incorrect\r\n" +
		"     */\r\n" +
		"    @PutMapping(\"/"+Utils.getClassNameLowerCase(tabella)+"s\")\r\n" +
		"    @Timed\r\n" +
		"    public ResponseEntity<"+Utils.getEntityName(tabella)+"DTO> update"+Utils.getEntityName(tabella)+"(@Valid @RequestBody "+Utils.getEntityName(tabella)+"DTO "+Utils.getClassNameLowerCase(tabella)+"DTO) throws URISyntaxException {\r\n" +
		"        log.debug(\"REST request to update "+Utils.getEntityName(tabella)+" : {}\", "+Utils.getClassNameLowerCase(tabella)+"DTO);\r\n" +
		"        if ("+Utils.getClassNameLowerCase(tabella)+"DTO.getId() == null) {\r\n" +
		"            throw new BadRequestAlertException(\"Invalid id\", ENTITY_NAME, \"idnull\");\r\n" +
		"        }\r\n" +
		"        "+Utils.getEntityName(tabella)+"DTO result = "+Utils.getClassNameLowerCase(tabella)+"Service.save("+Utils.getClassNameLowerCase(tabella)+"DTO);\r\n" +
		"        return ResponseEntity.ok()\r\n" +
		"            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, "+Utils.getClassNameLowerCase(tabella)+"DTO.getId().toString()))\r\n" +
		"            .body(result);\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * GET  /"+Utils.getClassNameLowerCase(tabella)+"s : get all the "+Utils.getClassNameLowerCase(tabella)+"s.\r\n" +
		"     *\r\n" +
		"     * @param pageable the pagination information\r\n" +
		"     * @param criteria the criterias which the requested entities should match\r\n" +
		"     * @return the ResponseEntity with status 200 (OK) and the list of "+Utils.getClassNameLowerCase(tabella)+"s in body\r\n" +
		"     */\r\n" +
		"    @GetMapping(\"/"+Utils.getClassNameLowerCase(tabella)+"s\")\r\n" +
		"    @Timed\r\n" +
		"    public ResponseEntity<List<"+Utils.getEntityName(tabella)+"DTO>> getAll"+Utils.getEntityName(tabella)+"s("+Utils.getEntityName(tabella)+"Criteria criteria, Pageable pageable) {\r\n" +
		"        log.debug(\"REST request to get "+Utils.getEntityName(tabella)+"s by criteria: {}\", criteria);\r\n" +
		"        Page<"+Utils.getEntityName(tabella)+"DTO> page = "+Utils.getClassNameLowerCase(tabella)+"QueryService.findByCriteria(criteria, pageable);\r\n" +
		"        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, \"/api/"+Utils.getClassNameLowerCase(tabella)+"s\");\r\n" +
		"        return ResponseEntity.ok().headers(headers).body(page.getContent());\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"    * GET  /"+Utils.getClassNameLowerCase(tabella)+"s/count : count all the "+Utils.getClassNameLowerCase(tabella)+"s.\r\n" +
		"    *\r\n" +
		"    * @param criteria the criterias which the requested entities should match\r\n" +
		"    * @return the ResponseEntity with status 200 (OK) and the count in body\r\n" +
		"    */\r\n" +
		"    @GetMapping(\"/"+Utils.getClassNameLowerCase(tabella)+"s/count\")\r\n" +
		"    @Timed\r\n" +
		"    public ResponseEntity<Long> count"+Utils.getEntityName(tabella)+"s("+Utils.getEntityName(tabella)+"Criteria criteria) {\r\n" +
		"        log.debug(\"REST request to count "+Utils.getEntityName(tabella)+"s by criteria: {}\", criteria);\r\n" +
		"        return ResponseEntity.ok().body("+Utils.getClassNameLowerCase(tabella)+"QueryService.countByCriteria(criteria));\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * GET  /"+Utils.getClassNameLowerCase(tabella)+"s/:id : get the \"id\" "+Utils.getClassNameLowerCase(tabella)+".\r\n" +
		"     *\r\n" +
		"     * @param id the id of the "+Utils.getClassNameLowerCase(tabella)+"DTO to retrieve\r\n" +
		"     * @return the ResponseEntity with status 200 (OK) and with body the "+Utils.getClassNameLowerCase(tabella)+"DTO, or with status 404 (Not Found)\r\n" +
		"     */\r\n" +
		"    @GetMapping(\"/"+Utils.getClassNameLowerCase(tabella)+"s/{id}\")\r\n" +
		"    @Timed\r\n" +
		"    public ResponseEntity<"+Utils.getEntityName(tabella)+"DTO> get"+Utils.getEntityName(tabella)+"(@PathVariable Long id) {\r\n" +
		"        log.debug(\"REST request to get "+Utils.getEntityName(tabella)+" : {}\", id);\r\n" +
		"        Optional<"+Utils.getEntityName(tabella)+"DTO> "+Utils.getClassNameLowerCase(tabella)+"DTO = "+Utils.getClassNameLowerCase(tabella)+"Service.findOne(id);\r\n" +
		"        return ResponseUtil.wrapOrNotFound("+Utils.getClassNameLowerCase(tabella)+"DTO);\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * DELETE  /"+Utils.getClassNameLowerCase(tabella)+"s/:id : delete the \"id\" "+Utils.getClassNameLowerCase(tabella)+".\r\n" +
		"     *\r\n" +
		"     * @param id the id of the "+Utils.getClassNameLowerCase(tabella)+"DTO to delete\r\n" +
		"     * @return the ResponseEntity with status 200 (OK)\r\n" +
		"     */\r\n" +
		"    @DeleteMapping(\"/"+Utils.getClassNameLowerCase(tabella)+"s/{id}\")\r\n" +
		"    @Timed\r\n" +
		"    public ResponseEntity<Void> delete"+Utils.getEntityName(tabella)+"(@PathVariable Long id) {\r\n" +
		"        log.debug(\"REST request to delete "+Utils.getEntityName(tabella)+" : {}\", id);\r\n" +
		"        "+Utils.getClassNameLowerCase(tabella)+"Service.delete(id);\r\n" +
		"        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();\r\n" +
		"    }\r\n\n" +
	
		
		//REPORTISTICA
		 "	@GetMapping(\"/"+entita+"sExport\")\r\n" +
		"	@Timed\r\n" +
		 "	public ResponseEntity<byte[]> export"+Entita+"("+Entita+"Criteria criteria, FileType fileType) throws Exception {\r\n" +
		"		HttpHeaders httpHeaders = new HttpHeaders();\r\n" +
		"		try {\r\n" +
		"			//Find collection by criteria.\n"+
		"			List<"+Entita+"DTO> listToPrint = "+entita+"QueryService.findByCriteria(criteria);\r\n" +
		"			if (CollectionUtils.isEmpty(listToPrint)) {\n"+
		"				log.warn(\"Warning: "+Entita+" collection is Empty!!\");\n"+
		"			}\n"+
		"			//Generate report.\n"+
		"			generateReport(listToPrint);\n" +
		"			byte[] contentReport = buildContentReport(fileType, httpHeaders, \"report"+Entita+"\");\n" +
		"			return new ResponseEntity<byte[]>(contentReport, httpHeaders, HttpStatus.OK);\n" +
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
		 "		builder.setTemplateFile(\""+templateJRXML+"\");\r\n" +
		 "		return builder.build();\r\n" +
		 "	}\r\n\n" +
		//END REPORTISTICA
		
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return Utils.getResourceClassName(tabella);
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
