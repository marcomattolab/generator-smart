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
	    "@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)\r\n" +
		"public class "+getClassName()+" extends AbstractAuditingEntity implements Serializable  {\r\n" +
		"\tprivate static final long serialVersionUID = 1L;\r\n";
		
		for (Column column: tabella.getSortedColumns()) {
			body += Utils.generaFieldExt(column)+"\n";
		}
		
		for (Column column: tabella.getSortedColumns()) {
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
						//Company{myKeyword(keywordCode)} to CompanyKeyword{myCompany(companyName)}

						if(nomeTabellaSx.toLowerCase().equals(nomeTabella)) {
							body += "\n	@ManyToMany\r\n";
							body += "	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)\n";
							body += "	@JoinTable(	name = \""+ Utils.getFirstLowerCase(nomeTabellaSx) +"_"+ Utils.getFirstLowerCase(nomeRelazioneSx) +"\",\n";
							body += "				joinColumns = @JoinColumn(name = \""+ Utils.getFirstLowerCase(nomeTabellaSx) +"s_id\", referencedColumnName = \"id\"),\n";
							body += "				inverseJoinColumns = @JoinColumn(name = \""+ Utils.getFirstLowerCase(nomeRelazioneSx) +"s_id\", referencedColumnName = \"id\"))\n";
							body += "	private Set<"+Utils.getClassNameCamelCase(nomeTabellaDx)+"> "+nomeRelazioneSx+"s = new HashSet<>();\n\n";
							//
							body += "	public Set<"+Utils.getClassNameCamelCase(nomeTabellaDx)+"> get"+ Utils.getFirstUpperCase(nomeRelazioneSx) +"s() {\r\n" +
									"		return "+Utils.getFirstLowerCase(nomeRelazioneSx)+"s;\r\n" +
									"	}\r\n\n" +
									"	public "+Utils.getFirstUpperCase(nomeTabellaSx)+" "+Utils.getFirstLowerCase(nomeRelazioneSx)+"s(Set<"+Utils.getClassNameCamelCase(nomeTabellaDx)+"> "+Utils.getFirstLowerCase(nomeTabellaDx)+"s) {\r\n" +
									"		this."+Utils.getFirstLowerCase(nomeRelazioneSx)+"s = "+Utils.getFirstLowerCase(nomeTabellaDx)+"s;\r\n" +
									"		return this;\r\n" +
									"	}\r\n\n" +
									"	public "+Utils.getFirstUpperCase(nomeTabellaSx)+" add"+ Utils.getFirstUpperCase(nomeRelazioneSx) +"("+Utils.getClassNameCamelCase(nomeTabellaDx)+" "+Utils.getFirstLowerCase(nomeTabellaDx)+") {\r\n" +
									"		this."+Utils.getFirstLowerCase(nomeRelazioneSx)+"s.add("+Utils.getFirstLowerCase(nomeTabellaDx)+");\r\n" +
									"		"+Utils.getFirstLowerCase(nomeTabellaDx)+".get"+Utils.getFirstUpperCase(nomeRelazioneDx)+"s().add(this);\r\n" +
									"		return this;\r\n" +
									"	}\r\n\n" +
									"	public "+Utils.getFirstUpperCase(nomeTabellaSx)+" remove"+ Utils.getFirstUpperCase(nomeRelazioneSx) +"("+Utils.getClassNameCamelCase(nomeTabellaDx)+" "+Utils.getFirstLowerCase(nomeTabellaDx)+") {\r\n" +
									"		this."+Utils.getFirstLowerCase(nomeRelazioneSx)+"s.remove("+Utils.getFirstLowerCase(nomeTabellaDx)+");\r\n" +
									"		"+Utils.getFirstLowerCase(nomeTabellaDx)+".get"+Utils.getFirstUpperCase(nomeRelazioneDx)+"s().remove(this);\r\n" +
									"		return this;\r\n" +
									"	}\r\n\n" +
									"	public void set"+ Utils.getFirstUpperCase(nomeRelazioneSx) +"s(Set<"+Utils.getClassNameCamelCase(nomeTabellaDx)+"> "+Utils.getFirstLowerCase(nomeTabellaDx)+"s) {\r\n" +
									"		this."+Utils.getFirstLowerCase(nomeRelazioneSx)+"s = "+Utils.getFirstLowerCase(nomeTabellaDx)+"s;\r\n" +
									"	}\r\n\n";
						} else if(nomeTabellaDx.toLowerCase().equals(nomeTabella)) {
							//Company{myKeyword(keywordCode)} to CompanyKeyword{myCompany(companyName)}
							body += "\n	@ManyToMany(mappedBy = \""+Utils.getFirstLowerCase(nomeRelazioneSx)+"s\")\r\n";
							body += "	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)\n";
							body += "	@JsonIgnore\n";
							body += "	private Set<"+Utils.getClassNameCamelCase(nomeTabellaSx)+"> "+nomeRelazioneDx+"s = new HashSet<>();\n\n";
							//
							body += "	public Set<"+Utils.getClassNameCamelCase(nomeTabellaSx)+"> get"+ Utils.getFirstUpperCase(nomeRelazioneDx) +"s() {\r\n" +
									"		return "+Utils.getFirstLowerCase(nomeRelazioneDx)+"s;\r\n" +
									"	}\r\n\n" +
									"	public "+Utils.getFirstUpperCase(nomeTabellaDx)+" "+Utils.getFirstLowerCase(nomeRelazioneDx)+"s(Set<"+Utils.getClassNameCamelCase(nomeTabellaSx)+"> "+Utils.getFirstLowerCase(nomeTabellaSx)+"s) {\r\n" +
									"		this."+Utils.getFirstLowerCase(nomeRelazioneDx)+"s = "+Utils.getFirstLowerCase(nomeTabellaSx)+"s;\r\n" +
									"		return this;\r\n" +
									"	}\r\n\n" +
									"	public "+Utils.getFirstUpperCase(nomeTabellaDx)+" add"+ Utils.getFirstUpperCase(nomeRelazioneDx) +"("+Utils.getClassNameCamelCase(nomeTabellaSx)+" "+Utils.getFirstLowerCase(nomeTabellaSx)+") {\r\n" +
									"		this."+Utils.getFirstLowerCase(nomeRelazioneDx)+"s.add("+Utils.getFirstLowerCase(nomeTabellaSx)+");\r\n" +
									"		"+Utils.getFirstLowerCase(nomeTabellaSx)+".get"+Utils.getFirstUpperCase(nomeRelazioneSx)+"s().add(this);\r\n" +
									"		return this;\r\n" +
									"	}\r\n\n" +
									
									"	public "+Utils.getFirstUpperCase(nomeTabellaDx)+" remove"+ Utils.getFirstUpperCase(nomeRelazioneDx) +"("+Utils.getClassNameCamelCase(nomeTabellaSx)+" "+Utils.getFirstLowerCase(nomeTabellaSx)+") {\r\n" +
									"		this."+Utils.getFirstLowerCase(nomeRelazioneDx)+"s.remove("+Utils.getFirstLowerCase(nomeTabellaSx)+");\r\n" +
									"		"+Utils.getFirstLowerCase(nomeTabellaSx)+".get"+Utils.getFirstUpperCase(nomeRelazioneSx)+"s().remove(this);\r\n" +
									"		return this;\r\n" +
									"	}\r\n\n" +
									"	public void set"+ Utils.getFirstUpperCase(nomeRelazioneDx) +"s(Set<"+Utils.getClassNameCamelCase(nomeTabellaSx)+"> "+Utils.getFirstLowerCase(nomeTabellaSx)+"s) {\r\n" +
									"		this."+Utils.getFirstLowerCase(nomeRelazioneDx)+"s = "+Utils.getFirstLowerCase(nomeTabellaSx)+"s;\r\n" +
									"	}\r\n\n";
						}
						
					    

					} else if (Utils.OneToMany.equals(relationType) ) {
						if(nomeTabellaSx.toLowerCase().equals(nomeTabella)) {
							// TODO TEST:  autore ==> preferito   /   nomeTabellaSx  ==> nomeRelazioneDx
							String relationSX =  Utils.getFirstLowerCase( Utils.getClassNameCamelCase(nomeRelazioneSx) );
							String RelDxUp = Utils.getFirstUpperCase(nomeRelazioneDx);
							String relDxUp = Utils.getFirstLowerCase(nomeRelazioneDx);
							
							body += "\n	@OneToMany(mappedBy = \""+relDxUp+"\")\n";
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
							body += "	   "+relationSX+".set"+RelDxUp+"(this);\n";
							body += "	   return this;\n";
							body += "	}\n\n";
							body += "	public "+Utils.getFirstUpperCase(nomeTabella)+" remove"+Utils.getClassNameCamelCase(nomeRelazioneSx)+"("+Utils.getClassNameCamelCase(nomeTabellaDx)+" "+relationSX+") {\n";
							body += "	    this."+Utils.getFirstLowerCase(nomeRelazioneSx)+"s.remove("+relationSX+");\n";
							body += "	    "+relationSX+".set"+RelDxUp+"(null);\n";
							body += "	    return this;\n";
							body += "	}\n\n";
							body += "	public void set"+Utils.getClassNameCamelCase(nomeRelazioneSx)+"s(Set<"+Utils.getClassNameCamelCase(nomeTabellaDx)+"> "+Utils.getFirstLowerCase(nomeRelazioneSx)+"s) {\n";
							body += "	    this."+Utils.getFirstLowerCase(nomeRelazioneSx)+"s = "+Utils.getFirstLowerCase(nomeRelazioneSx)+"s;\n";
							body += "	}\n\n";
						
						} else if(nomeTabellaDx.toLowerCase().equals(nomeTabella)) {
							// TODO TEST:  autore ==> preferito2   /   nomeTabellaSx  ==> nomeRelazioneDx
							String TblSxUp = Utils.getFirstUpperCase(nomeTabellaSx);
							String RelDxUp = Utils.getFirstUpperCase(nomeRelazioneDx);
							String relDxLw = Utils.getFirstLowerCase(nomeRelazioneDx);
							String TblDxUp = Utils.getFirstUpperCase(nomeTabellaDx);
							
							body +="\n	@ManyToOne\n";
							//TODO TEST:   clientes  ==> clienti2s   /    nomeTabellaDx ==> nomeRelazioneSx 
							body +="	@JsonIgnoreProperties(\""+Utils.getFirstLowerCase(nomeRelazioneSx)+"s\")\n";
							body +="	private "+TblSxUp+" "+relDxLw+";\n\n";
							
							body +="	public "+TblSxUp+" get"+RelDxUp+"() {\n";
							body +="	    return "+relDxLw+";\n";
							body +="	}\n\n";
							
							body +="	public "+TblDxUp+" "+relDxLw+"("+TblSxUp+" "+relDxLw+") {\n";
							body +="	    this."+relDxLw+" = "+relDxLw+";\n";
							body +="	    return this;\n";
							body +="	}\n\n";
							
							body +="	public void set"+RelDxUp+"("+TblSxUp+" "+relDxLw+") {\n";
							body +="	    this."+relDxLw+" = "+relDxLw+";\n";
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
		for (Column column: tabella.getSortedColumns()) {
			if(isFirst){
				body += Utils.generaToString(column);
				isFirst = false;
			}else{
				body += ", "+Utils.generaToString(column);
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

}
