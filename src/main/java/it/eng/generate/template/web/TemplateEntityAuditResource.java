package it.eng.generate.template.web;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateEntityAuditResource extends AbstractTemplate{

	public TemplateEntityAuditResource(DataBase dataBase) {
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
		"import " + conf.getPackageclass() + "." + conf.getSrcDomainFolder() + ".EntityAuditEvent;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcRepositoryFolder() + ".EntityAuditEventRepository;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcSecurityFolder()+ ".AuthoritiesConstants;\r\n" +
		"import " + conf.getPackageclass() + "." + conf.getSrcWebRestUtilFolder()+ ".PaginationUtil;\r\n" +
		"import org.slf4j.Logger;\r\n" +
		"import org.slf4j.LoggerFactory;\r\n" +
		"import org.springframework.data.domain.Page;\r\n" +
		"import org.springframework.data.domain.PageRequest;\r\n" +
		"import org.springframework.data.domain.Pageable;\r\n" +
		"import org.springframework.http.HttpHeaders;\r\n" +
		"import org.springframework.http.HttpStatus;\r\n" +
		"import org.springframework.http.MediaType;\r\n" +
		"import org.springframework.http.ResponseEntity;\r\n" +
		"import org.springframework.web.bind.annotation.RequestMapping;\r\n" +
		"import org.springframework.web.bind.annotation.RequestMethod;\r\n" +
		"import org.springframework.web.bind.annotation.RequestParam;\r\n" +
		"import org.springframework.web.bind.annotation.RestController;\r\n" +
		"import org.springframework.security.access.annotation.Secured;\r\n" +
		"import com.codahale.metrics.annotation.Timed;\r\n" +
		"import java.net.URISyntaxException;\r\n" +
		"import java.util.List;\r\n" +
		"import javax.transaction.Transactional;\r\n\n" +
		"/**\r\n" +
		" * REST controller for getting the audit events for entity\r\n" +
		" */\r\n" +
		"@RestController\r\n" +
		"@RequestMapping(\"/api\")\r\n" +
		"@Transactional\r\n" +
		"public class "+getClassName()+" {\r\n\n" +
		"    private final Logger log = LoggerFactory.getLogger("+getClassName()+".class);\r\n" +
		"    private final EntityAuditEventRepository entityAuditEventRepository;\r\n\n" +
		"    public "+getClassName()+"(EntityAuditEventRepository entityAuditEventRepository) {\r\n" +
		"        this.entityAuditEventRepository = entityAuditEventRepository;\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * fetches all the audited entity types\r\n" +
		"     *\r\n" +
		"     * @return\r\n" +
		"     */\r\n" +
		"    @RequestMapping(value = \"/audits/entity/all\",\r\n" +
		"        method = RequestMethod.GET,\r\n" +
		"        produces = MediaType.APPLICATION_JSON_VALUE)\r\n" +
		"    @Timed\r\n" +
		"    @Secured(AuthoritiesConstants.ADMIN)\r\n" +
		"    public List<String> getAuditedEntities() {\r\n" +
		"        return entityAuditEventRepository.findAllEntityTypes();\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * fetches the last 100 change list for an entity class, if limit is passed fetches that many changes\r\n" +
		"     *\r\n" +
		"     * @return\r\n" +
		"     */\r\n" +
		"    @RequestMapping(value = \"/audits/entity/changes\",\r\n" +
		"        method = RequestMethod.GET,\r\n" +
		"        produces = MediaType.APPLICATION_JSON_VALUE)\r\n" +
		"    @Timed\r\n" +
		"    @Secured(AuthoritiesConstants.ADMIN)\r\n" +
		"    public ResponseEntity<List<EntityAuditEvent>> getChanges(@RequestParam(value = \"entityType\") String entityType,\r\n" +
		"                                                             @RequestParam(value = \"limit\") int limit)\r\n" +
		"        throws URISyntaxException {\r\n" +
		"        log.debug(\"REST request to get a page of EntityAuditEvents\");\r\n" +
		"        Pageable pageRequest = createPageRequest(limit);\r\n" +
		"        Page<EntityAuditEvent> page = entityAuditEventRepository.findAllByEntityType(entityType, pageRequest);\r\n" +
		"        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, \"/api/audits/entity/changes\");\r\n" +
		"        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * fetches a previous version for for an entity class and id\r\n" +
		"     *\r\n" +
		"     * @return\r\n" +
		"     */\r\n" +
		"    @RequestMapping(value = \"/audits/entity/changes/version/previous\",\r\n" +
		"        method = RequestMethod.GET,\r\n" +
		"        produces = MediaType.APPLICATION_JSON_VALUE)\r\n" +
		"    @Timed\r\n" +
		"    @Secured(AuthoritiesConstants.ADMIN)\r\n" +
		"    public ResponseEntity<EntityAuditEvent> getPrevVersion(@RequestParam(value = \"qualifiedName\") String qualifiedName,\r\n" +
		"                                                           @RequestParam(value = \"entityId\") Long entityId,\r\n" +
		"                                                           @RequestParam(value = \"commitVersion\") Integer commitVersion)\r\n" +
		"        throws URISyntaxException {\r\n" +
		"        EntityAuditEvent prev = entityAuditEventRepository.findOneByEntityTypeAndEntityIdAndCommitVersion(qualifiedName, entityId, commitVersion);\r\n" +
		"        return new ResponseEntity<>(prev, HttpStatus.OK);\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * creates a page request object for PaginationUti\r\n" +
		"     *\r\n" +
		"     * @return\r\n" +
		"     */\r\n" +
		"    private Pageable createPageRequest(int size) {\r\n" +
		"        return new PageRequest(0, size);\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "EntityAuditResource";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
