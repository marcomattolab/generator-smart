package it.eng.generate.template.service;

import java.util.Iterator;

import org.springframework.util.CollectionUtils;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.ProjectRelation;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateQueryService extends AbstractTemplate{

	public TemplateQueryService(Table tabella) {
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
		"import java.util.List;\r\n" +
		"import javax.persistence.criteria.JoinType;\r\n" +
		"import org.slf4j.Logger;\r\n" +
		"import org.slf4j.LoggerFactory;\r\n" +
		"import org.springframework.data.domain.Page;\r\n" +
		"import org.springframework.data.domain.Pageable;\r\n" +
		"import org.springframework.data.jpa.domain.Specification;\r\n" +
		"import org.springframework.stereotype.Service;\r\n" +
		"import org.springframework.transaction.annotation.Transactional;\r\n" +
		"import io.github.jhipster.service.QueryService;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder()+"."+Utils.getEntityName(tabella) +";\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".*; // for static metamodels\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcRepositoryFolder()+"."+Utils.getEntityName(tabella)+"Repository;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcServiceDtoFolder()+"."+Utils.getEntityName(tabella)+"Criteria;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcServiceDtoFolder()+"."+Utils.getEntityName(tabella)+"DTO;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcServiceMapperFolder()+"."+Utils.getEntityName(tabella)+"Mapper;\r\n\n" +
		"/**\r\n" +
		" * Service for executing complex queries for "+Utils.getEntityName(tabella)+" entities in the database.\r\n" +
		" * The main input is a {@link "+Utils.getEntityName(tabella)+"Criteria} which gets converted to {@link Specification},\r\n" +
		" * in a way that all the filters must apply.\r\n" +
		" * It returns a {@link List} of {@link "+Utils.getEntityName(tabella)+"DTO} or a {@link Page} of {@link "+Utils.getEntityName(tabella)+"DTO} which fulfills the criteria.\r\n" +
		" */\r\n" +
		"@Service\r\n" +
		"@Transactional(readOnly = true)\r\n" +
		"public class "+getClassName()+" extends QueryService<"+Utils.getEntityName(tabella)+"> {\r\n" +
		"    private final Logger log = LoggerFactory.getLogger("+getClassName()+".class);\r\n" +
		"    private final "+Utils.getEntityName(tabella)+"Repository repository;\r\n" +
		"    private final "+Utils.getEntityName(tabella)+"Mapper mapper;\r\n\n" +
		"    public "+getClassName()+"("+Utils.getEntityName(tabella)+"Repository repository, "+Utils.getEntityName(tabella)+"Mapper mapper) {\r\n" +
		"        this.repository = repository;\r\n" +
		"        this.mapper = mapper;\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Return a {@link List} of {@link "+Utils.getEntityName(tabella)+"DTO} which matches the criteria from the database\r\n" +
		"     * @param criteria The object which holds all the filters, which the entities should match.\r\n" +
		"     * @return the matching entities.\r\n" +
		"     */\r\n" +
		"    @Transactional(readOnly = true)\r\n" +
		"    public List<"+Utils.getEntityName(tabella)+"DTO> findByCriteria("+Utils.getEntityName(tabella)+"Criteria criteria) {\r\n" +
		"        log.debug(\"find by criteria : {}\", criteria);\r\n" +
		"        final Specification<"+Utils.getEntityName(tabella)+"> specification = createSpecification(criteria);\r\n" +
		"        return mapper.toDto(repository.findAll(specification));\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Return a {@link Page} of {@link "+Utils.getEntityName(tabella)+"DTO} which matches the criteria from the database\r\n" +
		"     * @param criteria The object which holds all the filters, which the entities should match.\r\n" +
		"     * @param page The page, which should be returned.\r\n" +
		"     * @return the matching entities.\r\n" +
		"     */\r\n" +
		"    @Transactional(readOnly = true)\r\n" +
		"    public Page<"+Utils.getEntityName(tabella)+"DTO> findByCriteria("+Utils.getEntityName(tabella)+"Criteria criteria, Pageable page) {\r\n" +
		"        log.debug(\"find by criteria : {}, page: {}\", criteria, page);\r\n" +
		"        final Specification<"+Utils.getEntityName(tabella)+"> specification = createSpecification(criteria);\r\n" +
		"        return repository.findAll(specification, page)\r\n" +
		"            .map(mapper::toDto);\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Return the number of matching entities in the database\r\n" +
		"     * @param criteria The object which holds all the filters, which the entities should match.\r\n" +
		"     * @return the number of matching entities.\r\n" +
		"     */\r\n" +
		"    @Transactional(readOnly = true)\r\n" +
		"    public long countByCriteria("+Utils.getEntityName(tabella)+"Criteria criteria) {\r\n" +
		"        log.debug(\"count by criteria : {}\", criteria);\r\n" +
		"        final Specification<"+Utils.getEntityName(tabella)+"> specification = createSpecification(criteria);\r\n" +
		"        return repository.count(specification);\r\n" +
		"    }\r\n\n";
		body +=
		"    /**\r\n" +
		"     * Function to convert "+Utils.getEntityName(tabella)+"Criteria to a {@link Specification}\r\n" +
		"     */\r\n" +
		"    private Specification<"+Utils.getEntityName(tabella)+"> createSpecification("+Utils.getEntityName(tabella)+"Criteria criteria) {\r\n" +
		"        Specification<"+Utils.getEntityName(tabella)+"> specification = Specification.where(null);\r\n"+
		"        if (criteria != null) {\r\n";
		
		for (Iterator<?> iter = tabella.getColumnNames().iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
			Class<?> filterType = column.getTypeColumn();
			boolean isEnumeration = column.getEnumeration()!=null ? true : false;
			
			if ( Utils.isDateField(column) || (Utils.isNumericField(column) && !Utils.isPrimaryKeyID(column) ) ) {
			body +=	"            if (criteria.get"+Utils.getFieldNameForMethod(column)+"() != null) {\r\n"+
					"                specification = specification.and(buildRangeSpecification(criteria.get"+Utils.getFieldNameForMethod(column)+"(), "+Utils.getEntityName(tabella)+"_."+ Utils.getFieldNameForMethodReplace(column.getName(), true)  +"));\r\n" +
					"            }\r\n";
			} else if ( filterType.getName().equals("java.lang.String") && !isEnumeration ) {
			body +=	"            if (criteria.get"+Utils.getFieldNameForMethod(column)+"() != null) {\r\n"+
					"                specification = specification.and(buildStringSpecification(criteria.get"+Utils.getFieldNameForMethod(column)+"(), "+Utils.getEntityName(tabella)+"_."+ Utils.getFieldNameForMethodReplace(column.getName(), true)  +"));\r\n" +
					"            }\r\n";
			} else {
			body +=	"            if (criteria.get"+Utils.getFieldNameForMethod(column)+"() != null) {\r\n"+
					"                specification = specification.and(buildSpecification(criteria.get"+Utils.getFieldNameForMethod(column)+"(), "+Utils.getEntityName(tabella)+"_."+ Utils.getFieldNameForMethodReplace(column.getName(), true)  +"));\r\n" +
					"            }\r\n";
			}
		}
		
		//BUILD RELATIONS
		body += buildRelations(conf);
		
		body+=
		"        }\r\n" +
		"        return specification;\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	/**
	 * Build Relation Criteria
	 * @param conf
	 * @return body
	 */
	private String buildRelations(ConfigCreateProject conf) {
		String result = "";
		if(!CollectionUtils.isEmpty(conf.getProjectRelations())) {
			for(ProjectRelation rel: conf.getProjectRelations()) {
				String relationType = rel.getType();
				String nomeTabellaSx = rel.getSxTable();
				String nomeRelazioneSx = rel.getSxName();
				String nomeRelazioneDx = rel.getDxName();
				String nomeTabellaDx = rel.getDxTable();
				String nomeTabella = tabella.getNomeTabella().toLowerCase();
				
				if(nomeTabellaSx!=null && nomeTabellaDx != null) {
					if(relationType.equals(Utils.OneToOne)) {
						if(nomeTabellaSx.toLowerCase().equals(nomeTabella)) {
							result+=
							"		    if (criteria.get"+Utils.getFirstUpperCase(nomeRelazioneSx)+"Id() != null) {\r\n" +
							"                specification = specification.and(buildSpecification(criteria.get"+Utils.getFirstUpperCase(nomeRelazioneSx)+"Id(),\r\n" +
							"                    root -> root.join("+Utils.getFirstUpperCase(nomeTabellaSx)+"_."+Utils.getFirstLowerCase(nomeRelazioneSx)+", JoinType.LEFT).get("+Utils.getFirstUpperCase(nomeTabellaDx)+"_.id)));\r\n" +
							"            }\r\n";
						}
						
					}else if(relationType.equals(Utils.ManyToMany)) {
						// Company{myKeyword(keywordCode)} to CompanyKeyword{myCompany(companyName)}
						if(nomeTabellaSx.toLowerCase().equals(nomeTabella)) {
							result+=
							"		    if (criteria.get"+Utils.getFirstUpperCase(nomeRelazioneSx)+"Id() != null) {\n"+
							"		        specification = specification.and(buildSpecification(criteria.get"+Utils.getFirstUpperCase(nomeRelazioneSx)+"Id(),\n"+
							"		            root -> root.join("+Utils.getFirstUpperCase(nomeTabella)+"_."+Utils.getFirstLowerCase(nomeRelazioneSx)+"s, JoinType.LEFT).get("+Utils.getFirstUpperCase(nomeTabellaDx)+"_.id)));\n"+
							"		    }\n";
						}
						if(nomeTabellaDx.toLowerCase().equals(nomeTabella)) {
							result+=
							"		    if (criteria.get"+Utils.getFirstUpperCase(nomeRelazioneDx)+"Id() != null) {\n"+
							"		        specification = specification.and(buildSpecification(criteria.get"+Utils.getFirstUpperCase(nomeRelazioneDx)+"Id(),\n"+
							"		            root -> root.join("+Utils.getFirstUpperCase(nomeTabella)+"_."+Utils.getFirstLowerCase(nomeRelazioneDx)+"s, JoinType.LEFT).get("+Utils.getFirstUpperCase(nomeTabellaSx)+"_.id)));\n"+
							"		    }\n";
						}
						
					}else if(relationType.equals(Utils.OneToMany)) {
						// OneToMany  ==> Project{currentjobOffer(name)} to JobOffer{projectName(name)}
						if(nomeTabellaSx.toLowerCase().equals(nomeTabella)) {
							result+=
							"		    if (criteria.get"+Utils.getFirstUpperCase(nomeRelazioneSx)+"Id() != null) {\n"+
							"		        specification = specification.and(buildSpecification(criteria.get"+Utils.getFirstUpperCase(nomeRelazioneSx)+"Id(),\n"+
							"		            root -> root.join("+Utils.getFirstUpperCase(nomeTabella)+"_."+Utils.getFirstLowerCase(nomeRelazioneSx)+"s, JoinType.LEFT).get("+Utils.getFirstUpperCase(nomeTabellaDx)+"_.id)));\n"+
							"		    }\n";
						}
						if(nomeTabellaDx.toLowerCase().equals(nomeTabella)) {
							result+=
							"		    if (criteria.get"+Utils.getFirstUpperCase(nomeRelazioneDx)+"Id() != null) {\n"+
							"		        specification = specification.and(buildSpecification(criteria.get"+Utils.getFirstUpperCase(nomeRelazioneDx)+"Id(),\n"+
							"		            root -> root.join("+Utils.getFirstUpperCase(nomeTabella)+"_."+Utils.getFirstLowerCase(nomeRelazioneDx)+"s, JoinType.LEFT).get("+Utils.getFirstUpperCase(nomeTabellaSx)+"_.id)));\n"+
							"		    }\n";
						}
						
					}else if(relationType.equals(Utils.ManyToOne)) {
						// ManyToOne  =>  Partner{professione(denominazione)} to Professione 
						if(nomeTabellaSx.toLowerCase().equals(nomeTabella)) {
							result+=
							"		    if (criteria.get"+Utils.getFirstUpperCase(nomeRelazioneSx)+"Id() != null) {\n"+
							"		        specification = specification.and(buildSpecification(criteria.get"+Utils.getFirstUpperCase(nomeRelazioneSx)+"Id(),\n"+
							"		            root -> root.join("+Utils.getFirstUpperCase(nomeTabella)+"_."+Utils.getFirstLowerCase(nomeRelazioneSx)+"s, JoinType.LEFT).get("+Utils.getFirstUpperCase(nomeTabellaDx)+"_.id)));\n"+
							"		    }\n";
						}
						
					}
				}
			}
		}
		return result;
	}
	
	public String getClassName() {
		return Utils.getQueryServiceClassName(tabella);
	}
	
	public String getSourceFolder() {
		return "src/main/java";
	}
	
}
