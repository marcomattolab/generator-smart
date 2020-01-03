package it.eng.generate.template.service;

import org.springframework.util.CollectionUtils;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.ProjectRelation;
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

	/**
	 * TODO MOVE into Utils
	 * @param conf ConfigCreateProject
	 * @return List of UsesMapperClass
	 */
	private String getUsesMapperClass(ConfigCreateProject conf) {
		String result = "";
		if(!CollectionUtils.isEmpty(conf.getProjectRelations())) {
			for(ProjectRelation rel: conf.getProjectRelations()) {
				String relationType = rel.getType();
				String nomeTabellaSx = rel.getSxTable();
				String nomeTabellaDx = rel.getDxTable();
				String nomeTabella = tabella.getNomeTabella().toLowerCase();
				
				if(nomeTabellaSx!=null && nomeTabellaDx != null) {
					if( relationType.equals(Utils.OneToOne) || relationType.equals(Utils.ManyToOne) ) {
						if ( nomeTabellaSx.toLowerCase().equals(nomeTabella) ) {
							result += Utils.getFirstUpperCase(nomeTabellaDx)+"Mapper.class" + ",";
						}
					} else if( relationType.equals(Utils.OneToMany) ) {
						//TODO DEVELOP THIS!! 
					} else if( relationType.equals(Utils.ManyToMany) ) {
						//TODO DEVELOP THIS!! 
					}
				}
				
			}
		}
		return result.length()>0 ? result.substring(0, result.length()-1) : "";
	}
	
	public String getBody() {
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcServiceMapperFolder()+";\r\n\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".*;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcServiceDtoFolder()+"."+Utils.getEntityName(tabella)+"DTO;\r\n\n" +
		"import org.mapstruct.*;\r\n\n" +
		"/**\r\n" +
		" * Mapper for the entity "+Utils.getEntityName(tabella)+" and its DTO "+Utils.getEntityName(tabella)+"DTO.\r\n" +
		" */\r\n" +
		
		"@Mapper(componentModel = \"spring\", uses = {"+getUsesMapperClass(conf)+"})\r\n" +
		"public interface "+getClassName()+" extends EntityMapper<"+Utils.getEntityName(tabella)+"DTO, "+Utils.getEntityName(tabella)+"> {\r\n\n";

		String toEntity = "";
		String toDTO = "";
		String toDTOS = "";
		
		//[Relation Management]
		if(!CollectionUtils.isEmpty(conf.getProjectRelations())) {
			for(ProjectRelation rel: conf.getProjectRelations()) {
				String relationType = rel.getType();
				String nomeTabellaSx = rel.getSxTable();
				String nomeRelazioneSx = rel.getSxName();
				String nomeSelectSx = rel.getSxSelect();
				String nomeTabellaDx = rel.getDxTable();
				String nomeRelazioneDx = rel.getDxName();
				String nomeTabella = tabella.getNomeTabella().toLowerCase();
				
				if(nomeTabellaSx!=null && nomeTabellaDx != null) {
					if ( relationType.equals(Utils.OneToOne) || relationType.equals(Utils.ManyToOne) ) {
						if (nomeTabellaSx.toLowerCase().equals(nomeTabella) ) {
							toDTO += "    @Mapping(source = \""+nomeRelazioneSx+".id\", target = \""+nomeRelazioneSx+"Id\")\n"+
								     "    @Mapping(source = \""+nomeRelazioneSx+"."+nomeSelectSx+"\", target = \""+nomeRelazioneSx+""+Utils.getFirstUpperCase(nomeSelectSx)+"\")\n";
							
							toDTOS+=	 "    "+Utils.getEntityName(tabella)+"DTO toDto("+Utils.getEntityName(tabella)+" "+Utils.getFirstLowerCase(nomeTabellaSx)+");\n\n";

							toEntity +="    @Mapping(source = \""+nomeRelazioneSx+"Id\", target = \""+nomeRelazioneSx+"\")\n";
							
						} else if (nomeTabellaDx.toLowerCase().equals(nomeTabella) && relationType.equals(Utils.OneToOne)) {
							toEntity += "    //@Mapping(target = \""+Utils.getFirstLowerCase(nomeRelazioneDx)+"\", ignore = true)\n"; 
						}
						
					} else if ( relationType.equals(Utils.OneToMany) ) {
						
						if (nomeTabellaSx.toLowerCase().equals(nomeTabella) ) {
							toDTO += "    @Mapping(target = \""+Utils.getFirstLowerCase(nomeRelazioneSx)+"s\", ignore = true)\n"; 
					
						} else if (nomeTabellaDx.toLowerCase().equals(nomeTabella)) {
							toDTO += "    @Mapping(source = \""+Utils.getFirstLowerCase(nomeTabellaSx)+".id\", target = \""+Utils.getFirstLowerCase(nomeTabellaSx)+"Id\")\n";
							toDTOS+= "    "+Utils.getEntityName(tabella)+"DTO toDto("+Utils.getEntityName(tabella)+" "+Utils.getFirstLowerCase(nomeTabella)+");\n\n";
									
							toEntity +="    //@Mapping(source = \""+Utils.getFirstLowerCase(nomeTabellaSx)+"Id\", target = \""+Utils.getFirstLowerCase(nomeTabellaSx)+"\")\n";
						}
						
					} else if ( relationType.equals(Utils.ManyToMany) ) {
						//TODO DEVELOP THIS!
					}
					
				}
				
			}
		}
	  	//[/Relation Management]

		//ToDTO
		body += toDTO + toDTOS;
	  	
		//ToEntity
	    body += toEntity + 	
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
