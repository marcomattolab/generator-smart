package it.eng.generate.template.service;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateServiceImpl extends AbstractTemplate{

	public TemplateServiceImpl(Table tabella) {
		super(tabella);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcServiceImplFolder(),".","/");
		return typeTemplate;
	}
	
	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcServiceImplFolder()+";\r\n\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcServiceFolder()+"."+Utils.getEntityName(tabella)+"Service;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder()+"."+Utils.getEntityName(tabella)+";\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcRepositoryFolder()+"."+Utils.getEntityName(tabella)+"Repository;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcServiceDtoFolder()+"."+Utils.getEntityName(tabella)+"DTO;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcServiceMapperFolder()+"."+Utils.getEntityName(tabella)+"Mapper;\r\n" +
		"import org.slf4j.Logger;\r\n" +
		"import org.slf4j.LoggerFactory;\r\n" +
		"import org.springframework.data.domain.Page;\r\n" +
		"import org.springframework.data.domain.Pageable;\r\n" +
		"import org.springframework.stereotype.Service;\r\n" +
		"import org.springframework.transaction.annotation.Transactional;\r\n" +
		"import java.util.Optional;\r\n\n" +
		"/**\r\n" +
		" * Service Implementation for managing "+Utils.getEntityName(tabella)+".\r\n" +
		" */\r\n" +
		"@Service\r\n" +
		"@Transactional\r\n" +
		"public class "+getClassName()+" implements "+Utils.getEntityName(tabella)+"Service {\r\n" +
		"    private final Logger log = LoggerFactory.getLogger("+getClassName()+".class);\r\n" +
		"    private final "+Utils.getEntityName(tabella)+"Repository "+Utils.getClassNameLowerCase(tabella)+"Repository;\r\n" +
		"    private final "+Utils.getEntityName(tabella)+"Mapper "+Utils.getClassNameLowerCase(tabella)+"Mapper;\r\n\n" +
		"    public "+getClassName()+"("+Utils.getEntityName(tabella)+"Repository "+Utils.getClassNameLowerCase(tabella)+"Repository, "+Utils.getEntityName(tabella)+"Mapper "+Utils.getClassNameLowerCase(tabella)+"Mapper) {\r\n" +
		"        this."+Utils.getClassNameLowerCase(tabella)+"Repository = "+Utils.getClassNameLowerCase(tabella)+"Repository;\r\n" +
		"        this."+Utils.getClassNameLowerCase(tabella)+"Mapper = "+Utils.getClassNameLowerCase(tabella)+"Mapper;\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Save a "+Utils.getClassNameLowerCase(tabella)+".\r\n" +
		"     *\r\n" +
		"     * @param "+Utils.getClassNameLowerCase(tabella)+"DTO the entity to save\r\n" +
		"     * @return the persisted entity\r\n" +
		"     */\r\n" +
		"    @Override\r\n" +
		"    public "+Utils.getEntityName(tabella)+"DTO save("+Utils.getEntityName(tabella)+"DTO "+Utils.getClassNameLowerCase(tabella)+"DTO) {\r\n" +
		"        log.debug(\"Request to save "+Utils.getEntityName(tabella)+" : {}\", "+Utils.getClassNameLowerCase(tabella)+"DTO);\r\n" +
		"        "+Utils.getEntityName(tabella)+" "+Utils.getClassNameLowerCase(tabella)+" = "+Utils.getClassNameLowerCase(tabella)+"Mapper.toEntity("+Utils.getClassNameLowerCase(tabella)+"DTO);\r\n" +
		"        "+Utils.getClassNameLowerCase(tabella)+" = "+Utils.getClassNameLowerCase(tabella)+"Repository.save("+Utils.getClassNameLowerCase(tabella)+ ");\r\n" +
		"        return "+Utils.getClassNameLowerCase(tabella)+"Mapper.toDto("+Utils.getClassNameLowerCase(tabella)+");\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Get all the "+Utils.getClassNameLowerCase(tabella)+"s.\r\n" +
		"     *\r\n" +
		"     * @param pageable the pagination information\r\n" +
		"     * @return the list of entities\r\n" +
		"     */\r\n" +
		"    @Override\r\n" +
		"    @Transactional(readOnly = true)\r\n" +
		"    public Page<"+Utils.getEntityName(tabella)+"DTO> findAll(Pageable pageable) {\r\n" +
		"        log.debug(\"Request to get all "+Utils.getEntityName(tabella)+"s\");\r\n" +
		"        return "+Utils.getClassNameLowerCase(tabella)+"Repository.findAll(pageable)\r\n" +
		"            .map("+Utils.getClassNameLowerCase(tabella)+"Mapper::toDto);\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Get one "+Utils.getClassNameLowerCase(tabella)+" by id.\r\n" +
		"     *\r\n" +
		"     * @param id the id of the entity\r\n" +
		"     * @return the entity\r\n" +
		"     */\r\n" +
		"    @Override\r\n" +
		"    @Transactional(readOnly = true)\r\n" +
		"    public Optional<"+Utils.getEntityName(tabella)+"DTO> findOne(Long id) {\r\n" +
		"        log.debug(\"Request to get "+Utils.getEntityName(tabella)+" : {}\", id);\r\n" +
		"        return "+Utils.getClassNameLowerCase(tabella)+"Repository.findById(id)\r\n" +
		"            .map("+Utils.getClassNameLowerCase(tabella)+"Mapper::toDto);\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Delete the "+Utils.getClassNameLowerCase(tabella)+" by id.\r\n" +
		"     *\r\n" +
		"     * @param id the id of the entity\r\n" +
		"     */\r\n" +
		"    @Override\r\n" +
		"    public void delete(Long id) {\r\n" +
		"        log.debug(\"Request to delete "+Utils.getEntityName(tabella)+" : {}\", id);\r\n" +
		"        "+Utils.getClassNameLowerCase(tabella)+"Repository.deleteById(id);\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return Utils.getServiceImplClassName(tabella);
	}
	
	public String getSourceFolder() {
		return "src/main/java";
	}
	
}
