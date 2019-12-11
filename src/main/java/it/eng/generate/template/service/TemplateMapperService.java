package it.eng.generate.template.service;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateMapperService extends AbstractTemplate{

	public TemplateMapperService(Table tabella) {
		super(tabella);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcServiceMapperFolder(),".","/");
		return typeTemplate;
	}
	
	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcServiceMapperFolder()+";\r\n\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".*;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcServiceDtoFolder()+"."+Utils.getEntityName(tabella)+"DTO;\r\n" +
		"import org.mapstruct.*;\r\n\n" +
		"/**\r\n" +
		" * Mapper for the entity "+Utils.getEntityName(tabella)+" and its DTO "+Utils.getEntityName(tabella)+"DTO.\r\n" +
		" */\r\n" +
		"@Mapper(componentModel = \"spring\", uses = {})\r\n" +
		"public interface "+getClassName()+" extends EntityMapper<"+Utils.getEntityName(tabella)+"DTO, "+Utils.getEntityName(tabella)+"> {\r\n\n" +
	  //"    @Mapping(target = \"incaricos\", ignore = true)\r\n" +
		"    "+Utils.getEntityName(tabella)+" toEntity("+Utils.getEntityName(tabella)+"DTO "+Utils.getClassNameLowerCase(tabella)+"DTO);\r\n\n" +
		"    default "+Utils.getEntityName(tabella)+" fromId(Long id) {\r\n" +
		"        if (id == null) {\r\n" +
		"            return null;\r\n" +
		"        }\r\n\n" +
		"        "+Utils.getEntityName(tabella)+" "+Utils.getClassNameLowerCase(tabella)+" = new "+Utils.getEntityName(tabella)+"();\r\n" +
		"        "+Utils.getClassNameLowerCase(tabella)+".setId(id);\r\n" +
		"        return "+Utils.getClassNameLowerCase(tabella)+";\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return Utils.getMapperClassName(tabella);
	}
	
	public String getSourceFolder() {
		return "src/main/java";
	}
	
}
