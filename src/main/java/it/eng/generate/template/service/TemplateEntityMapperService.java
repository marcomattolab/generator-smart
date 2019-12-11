package it.eng.generate.template.service;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateEntityMapperService extends AbstractTemplate{

	public TemplateEntityMapperService(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcServiceMapperFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcServiceMapperFolder()+";\r\n\n" +
		"import java.util.List;\r\n\n" +
		"/**\r\n" +
		" * Contract for a generic dto to entity mapper.\r\n" +
		" *\r\n" +
		" * @param <D> - DTO type parameter.\r\n" +
		" * @param <E> - Entity type parameter.\r\n" +
		" */\r\n" +
		"public interface EntityMapper <D, E> {\r\n\n" +
		"    E toEntity(D dto);\r\n\n" +
		"    D toDto(E entity);\r\n\n" +
		"    List <E> toEntity(List<D> dtoList);\r\n\n" +
		"    List <D> toDto(List<E> entityList);\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "EntityMapper";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
