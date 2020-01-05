package it.eng.generate.template.domain;

import java.util.Iterator;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.ProjectRelation;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateDomain extends AbstractTemplate{

	public TemplateDomain(Table tabella) {
		super(tabella);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcDomainFolder(),".","/");
		return typeTemplate;
	}
	
	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder()+";\r\n\n" +
		"import io.swagger.annotations.ApiModel;\r\n" +
		"import com.fasterxml.jackson.annotation.JsonIgnore;\r\n" +
		"import org.hibernate.annotations.Cache;\r\n" +
		"import org.hibernate.annotations.CacheConcurrencyStrategy;\r\n" +
		"import javax.persistence.*;\r\n" +
		"import javax.validation.constraints.*;\r\n" +
		"import java.io.Serializable;\r\n" +
		"import java.math.BigDecimal;\r\n" +
		"import java.time.Instant;\r\n" +
		"import java.time.LocalDate;\r\n" +
		"import java.time.ZonedDateTime;\r\n" +
		"import java.util.HashSet;\r\n" +
		"import java.util.Set;\r\n" +
		"import java.util.Objects;\r\n" +
		"import com.fasterxml.jackson.annotation.JsonIgnore;\n" +
		"import com.fasterxml.jackson.annotation.JsonIgnoreProperties;\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder() + ".enumeration.*;\r\n\n" +

		"/**\r\n" +
		" * Entity "+getClassName()+"\r\n" +
		" */\r\n" +
		"@ApiModel(description = \"Entity "+getClassName()+"\")\r\n" +
		"@Entity\r\n" +
		"@Table(name = \""+getClassName()+"\")\r\n" +
	    "@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)\r\n" +  //TODO Added Cache
		"public class "+getClassName()+" extends AbstractAuditingEntity implements Serializable  {\r\n" +
		"\tprivate static final long serialVersionUID = 1L;\r\n";
		
		Set<?> set = tabella.getColumnNames();
		for (Iterator<?> iter = set.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
			body += Utils.generaFieldExt(column)+"\n";
		}
		
		set = tabella.getColumnNames();
		for (Iterator<?> iter = set.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
			body += Utils.generaGetAndSetForBeanExt(column, getClassName());
		}
		
		//Relations management
		if(!CollectionUtils.isEmpty(conf.getProjectRelations())) {
			for(ProjectRelation rel: conf.getProjectRelations()) {
				String relationType = rel.getType();
				String nomeTabellaSx = rel.getSxTable();
				String nomeRelazioneSx = rel.getSxName();
				String nomeTabellaDx = rel.getDxTable();
				String nomeRelazioneDx = rel.getDxName();
				String nomeTabella = tabella.getNomeTabella().toLowerCase();

				if(nomeTabellaSx!=null && nomeTabellaDx != null 
						&& (nomeTabellaSx.toLowerCase().equals(nomeTabella) || nomeTabellaDx.toLowerCase().equals(nomeTabella)) ) {

					System.out.println("## relationType: "+relationType+ "  nomeTabellaSx: "+ nomeTabellaSx+ "  nomeTabellaDx: " + nomeTabellaDx  +"  nomeTabella: "+ nomeTabella);
				
					//Relation Types
					if (Utils.OneToOne.equals(relationType) ) {
						
						if(nomeTabellaSx.toLowerCase().equals(nomeTabella)) {
							body += "\n	@OneToOne(cascade = CascadeType.REMOVE)\n";
							body += "	@JoinColumn(unique = true)\n";
							body += "	private "+Utils.getClassNameCamelCase(nomeTabellaDx)+" "+nomeRelazioneSx+";\n\n";
							body += "	public "+Utils.getClassNameCamelCase(nomeTabellaDx)+" get"+Utils.getClassNameCamelCase(nomeRelazioneSx)+"() {\n";
							body += "	    return "+nomeRelazioneSx+";\n";
							body += "	}\n\n";
							body += "	public "+Utils.getClassNameCamelCase(nomeTabellaSx)+" "+nomeRelazioneSx+"("+Utils.getClassNameCamelCase(nomeTabellaDx)+" "+nomeRelazioneSx+") {\n";
							body += "	    this."+nomeRelazioneSx+" = "+nomeRelazioneSx+";\n";
							body += "	    return this;\n";
							body += "	}\n\n";
							body += "	public void set"+Utils.getClassNameCamelCase(nomeRelazioneSx)+"("+Utils.getClassNameCamelCase(nomeTabellaDx)+" "+nomeRelazioneSx+") {\n";
							body += "	    this."+nomeRelazioneSx+" = "+nomeRelazioneSx+";\n";
							body += "	}\n";
						
						} else if(nomeTabellaDx.toLowerCase().equals(nomeTabella)) {
							body += "\n	@OneToOne(mappedBy = \""+nomeRelazioneSx+"\")\r\n";
							body += "	@JsonIgnore\r\n";
							body += "	private "+Utils.getClassNameCamelCase(nomeTabellaSx)+" "+nomeRelazioneDx+";\n\n";
							body += "	public "+Utils.getClassNameCamelCase(nomeTabellaSx)+" get"+Utils.getClassNameCamelCase(nomeRelazioneDx)+"() {\r\n";
							body += "	    return "+nomeRelazioneDx+";\r\n";
							body += "	}\r\n\n";
							body += "	public "+Utils.getClassNameCamelCase(nomeTabellaDx)+" "+nomeRelazioneDx+"("+Utils.getClassNameCamelCase(nomeTabellaSx)+" "+nomeTabellaSx.toLowerCase()+") {\r\n";
							body += "	    this."+nomeRelazioneDx+" = "+nomeTabellaSx.toLowerCase()+";\r\n";
							body += " 	    return this;\r\n";
							body += "	}\r\n\n";
							body += "	public void set"+Utils.getClassNameCamelCase(nomeRelazioneDx)+"("+Utils.getClassNameCamelCase(nomeTabellaSx)+" "+nomeTabellaSx.toLowerCase()+") {\r\n";
							body += "	    this."+nomeRelazioneDx+" = "+nomeTabellaSx.toLowerCase()+";\r\n";
							body += "	}\n";
						}
						
					} else if (Utils.ManyToMany.equals(relationType) ) {
//						body += "\n	@OneToOne(mappedBy = \""+nomeRelazioneSx+"\")\r\n";
//						body += "	@JsonIgnore\r\n";
//						body += "	private "+Utils.getClassNameCamelCase(nomeTabellaSx)+" "+nomeRelazioneDx+";\n\n";
//						body += "	public "+Utils.getClassNameCamelCase(nomeTabellaSx)+" get"+Utils.getClassNameCamelCase(nomeRelazioneDx)+"() {\r\n";
//						body += "	    return "+nomeRelazioneDx+";\r\n";
//						body += "	}\r\n\n";
//						body += "	public "+Utils.getClassNameCamelCase(nomeTabellaDx)+" "+nomeRelazioneDx+"("+Utils.getClassNameCamelCase(nomeTabellaSx)+" "+nomeTabellaSx.toLowerCase()+") {\r\n";
//						body += "	    this."+nomeRelazioneDx+" = "+nomeTabellaSx.toLowerCase()+";\r\n";
//						body += " 	    return this;\r\n";
//						body += "	}\r\n\n";
//						body += "	public void set"+Utils.getClassNameCamelCase(nomeRelazioneDx)+"("+Utils.getClassNameCamelCase(nomeTabellaSx)+" "+nomeTabellaSx.toLowerCase()+") {\r\n";
//						body += "	    this."+nomeRelazioneDx+" = "+nomeTabellaSx.toLowerCase()+";\r\n";
//						body += "	}\n";

					} else if (Utils.OneToMany.equals(relationType) ) {
						if(nomeTabellaSx.toLowerCase().equals(nomeTabella)) {
							String relationSX =  Utils.getFirstLowerCase( Utils.getClassNameCamelCase(nomeRelazioneSx) );
							body += "\n	@OneToMany(mappedBy = \""+Utils.getFirstLowerCase(nomeTabellaSx)+"\")\n";
							body += "	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)\n";
							body += "	private Set<"+Utils.getClassNameCamelCase(nomeTabellaDx)+"> "+Utils.getFirstLowerCase(nomeRelazioneSx)+"s = new HashSet<>();\n\n";
							body += "	public Set<"+Utils.getClassNameCamelCase(nomeTabellaDx)+"> get"+Utils.getClassNameCamelCase(nomeRelazioneSx)+"s() {\n";
							body += "	   return "+Utils.getFirstLowerCase(nomeRelazioneSx)+"s;\n";
							body += "	}\n\n";
							body += "	public "+Utils.getFirstUpperCase(nomeTabella)+ " "+Utils.getFirstLowerCase(nomeRelazioneSx)+"s(Set<"+Utils.getClassNameCamelCase(nomeTabellaDx)+"> "+Utils.getFirstLowerCase(nomeRelazioneSx)+"s) {\n";
							body += "	    this."+Utils.getFirstLowerCase(nomeRelazioneSx)+"s = "+Utils.getFirstLowerCase(nomeRelazioneSx)+"s;\n";
							body += "	    return this;\n";
							body += "	}\n\n";
							body += "	public "+Utils.getFirstUpperCase(nomeTabella)+" add"+Utils.getClassNameCamelCase(nomeRelazioneSx)+"("+Utils.getClassNameCamelCase(nomeTabellaDx)+" "+relationSX+") {\n";
							body += "	   this."+Utils.getFirstLowerCase(nomeRelazioneSx)+"s.add("+relationSX+");\n";
							body += "	   "+relationSX+".set"+Utils.getFirstUpperCase(nomeTabella)+"(this);\n";
							body += "	   return this;\n";
							body += "	}\n\n";
							body += "	public "+Utils.getFirstUpperCase(nomeTabella)+" remove"+Utils.getClassNameCamelCase(nomeRelazioneSx)+"("+Utils.getClassNameCamelCase(nomeTabellaDx)+" "+relationSX+") {\n";
							body += "	    this."+Utils.getFirstLowerCase(nomeRelazioneSx)+"s.remove("+relationSX+");\n";
							body += "	    "+relationSX+".set"+Utils.getFirstUpperCase(nomeTabella)+"(null);\n";
							body += "	    return this;\n";
							body += "	}\n\n";
							body += "	public void set"+Utils.getClassNameCamelCase(nomeRelazioneSx)+"s(Set<"+Utils.getClassNameCamelCase(nomeTabellaDx)+"> "+Utils.getFirstLowerCase(nomeRelazioneSx)+"s) {\n";
							body += "	    this."+Utils.getFirstLowerCase(nomeRelazioneSx)+"s = "+Utils.getFirstLowerCase(nomeRelazioneSx)+"s;\n";
							body += "	}\n\n";
						}
						else if(nomeTabellaDx.toLowerCase().equals(nomeTabella)) {
							String TblSxUp = Utils.getFirstUpperCase(nomeTabellaSx);
							String TblDxUp = Utils.getFirstUpperCase(nomeTabellaDx);
							String tblSxLw = Utils.getFirstLowerCase(nomeTabellaSx);
							
							body +="\n	@ManyToOne\n";
							body +="	@JsonIgnoreProperties(\""+Utils.getFirstLowerCase(nomeTabellaDx)+"s\")\n";
							body +="	private "+TblSxUp+" "+tblSxLw+";\n\n";
							
							body +="	public "+TblSxUp+" get"+TblSxUp+"() {\n";
							body +="	    return "+tblSxLw+";\n";
							body +="	}\n\n";
							
							body +="	public "+TblDxUp+" "+tblSxLw+"("+TblSxUp+" "+tblSxLw+") {\n";
							body +="	    this."+tblSxLw+" = "+tblSxLw+";\n";
							body +="	    return this;\n";
							body +="	}\n\n";
							
							body +="	public void set"+TblSxUp+"("+TblSxUp+" "+tblSxLw+") {\n";
							body +="	    this."+tblSxLw+" = "+tblSxLw+";\n";
							body +="	}\n\n";
						}
					    
					} else if (Utils.ManyToOne.equals(relationType) && nomeTabellaSx.toLowerCase().equals(nomeTabella) ) {
						body += "\n	@ManyToOne\n";
						body += "	@JsonIgnoreProperties(\"\")\n";
						body += "	private "+Utils.getClassNameCamelCase(nomeTabellaDx)+" "+nomeRelazioneSx+";\n\n";
						body += "	public "+Utils.getClassNameCamelCase(nomeTabellaDx)+" get"+Utils.getClassNameCamelCase(nomeRelazioneSx)+"() {\n";
						body += "	    return "+nomeRelazioneSx+";\n";
						body += "	}\n\n";
						body += "	public "+Utils.getClassNameCamelCase(nomeTabellaSx)+" "+nomeRelazioneSx+"("+Utils.getClassNameCamelCase(nomeTabellaDx)+" "+nomeRelazioneSx+") {\n";
						body += "	    this."+nomeRelazioneSx+" = "+nomeRelazioneSx+";\n";
						body += "	    return this;\n";
						body += "	}\n\n";
						body += "	public void set"+Utils.getClassNameCamelCase(nomeRelazioneSx)+"("+Utils.getClassNameCamelCase(nomeTabellaDx)+" "+nomeRelazioneSx+") {\n";
						body += "	    this."+nomeRelazioneSx+" = "+nomeRelazioneSx+";\n";
						body += "	}\n";
   					}
					
				}
				
			}
		}
		
		//body += "\n";
		body += "\n\t@Override";
		body += "\n\tpublic int hashCode() {";
		body += "\n\t\treturn Objects.hashCode(getId());";
		body += "\n\t}";
		body += "\n";
		body += "\n\t@Override";
		body += "\n\tpublic boolean equals(Object o) {";
		body += "\n\t\tif (this == o) {";
		body += "\n\t\t\treturn true;";
		body += "\n\t\t}";
		body += "\n\t\tif (o == null || getClass() != o.getClass()) {";
		body += "\n\t\t\treturn false;";
		body += "\n\t\t}";
		body += "\n\t\t"+getClassName()+" object = ("+getClassName()+") o;";
		body += "\n\t\tif (object.getId() == null || getId() == null) {";
		body += "\n\t\t\treturn false;";
		body += "\n\t\t}";
		body += "\n\t\treturn Objects.equals(getId(), object.getId());";
		body += "\n\t}";
		body += "\n";
		body += "\n\t@Override";
		body += "\n\tpublic String toString(){";
		body += "\n\t\treturn this.getClass().getName()+\":{";
		boolean isFirst = true;
		for (Iterator<?> iter = set.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
			if(isFirst){
				body += Utils.generaToString(column);
				isFirst = false;
			}else{
				body += ","+Utils.generaToString(column);
			}
		}
		body += "}\";";
		body += "\n\t}";
		body += "\n}";
		body += "\n";
		return body;
	}

	public String getClassName() {
		return Utils.getEntityName(tabella);
	}
	
	public String getSourceFolder() {
		return "src/main/java";
	}

	/**
  		JDL - Defining multiple oneToOne relationships
		
		relationship OneToOne {
			Immobile{geolocalizzazione(immobile)} to Geolocalizzazione{posizione(codice)}
		}
                    
		relationship ManyToOne {
			Partner{professione(denominazione)} to Professione
		}
		
		relationship OneToMany {
			Incarico{listaContatti(esito)} to ListaContatti{incarico(riferimento)}
		}
		
		relationship ManyToMany {
			Candidate{language(languageCode)} to LanguageSkill{candidateName(lastName)},
			Company{myKeyword(keywordCode)} to CompanyKeyword{myCompany(companyName)}
		}

	 **/
	
}
