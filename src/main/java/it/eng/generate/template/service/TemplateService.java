package it.eng.generate.template.service;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateService extends AbstractTemplate{

	public TemplateService(Table tabella) {
		super(tabella);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcServiceFolder(),".","/");
		return typeTemplate;
	}
	
	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcServiceFolder()+";\r\n\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcServiceDtoFolder()+"."+Utils.getEntityName(tabella)+"DTO;\r\n" +
		"import org.springframework.data.domain.Page;\r\n" +
		"import org.springframework.data.domain.Pageable;\r\n" +
		"import java.util.Optional;\r\n\n" +
		"/**\r\n" +
		" * Service Interface for managing "+Utils.getEntityName(tabella)+".\r\n" +
		" */\r\n" +
		"public interface "+getClassName()+" {\r\n\n" +
		"    /**\r\n" +
		"     * Save "+Utils.getEntityName(tabella)+".\r\n" +
		"     *\r\n" +
		"     * @param "+Utils.getClassNameLowerCase(tabella)+"DTO the entity to save\r\n" +
		"     * @return the persisted entity\r\n" +
		"     */\r\n" +
		"    "+Utils.getDTOClassName(tabella)+" save("+Utils.getDTOClassName(tabella)+" "+Utils.getClassNameLowerCase(tabella)+"DTO);\r\n\n" +
		"    /**\r\n" +
		"     * Get all the "+Utils.getClassNameLowerCase(tabella)+"s.\r\n" +
		"     *\r\n" +
		"     * @param pageable the pagination information\r\n" +
		"     * @return the list of entities\r\n" +
		"     */\r\n" +
		"    Page<"+Utils.getDTOClassName(tabella)+"> findAll(Pageable pageable);\r\n\n" +
		"    /**\r\n" +
		"     * Get the \"id\" "+Utils.getClassNameLowerCase(tabella)+".\r\n" +
		"     *\r\n" +
		"     * @param id the id of the entity\r\n" +
		"     * @return the entity\r\n" +
		"     */\r\n" +
		"    Optional<"+Utils.getDTOClassName(tabella)+"> findOne(Long id);\r\n\n" +
		"    /**\r\n" +
		"     * Delete the \"id\" "+Utils.getEntityName(tabella)+".\r\n" +
		"     *\r\n" +
		"     * @param id the id of the entity\r\n" +
		"     */\r\n" +
		"    void delete(Long id);\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return Utils.getServiceClassName(tabella);
	}
	
	public String getSourceFolder() {
		return "src/main/java";
	}
	
}
