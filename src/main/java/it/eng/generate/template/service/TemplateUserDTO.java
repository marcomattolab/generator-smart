package it.eng.generate.template.service;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateUserDTO extends AbstractTemplate{

	public TemplateUserDTO(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcServiceDtoFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcServiceDtoFolder()+";\r\n\n" +
				"import "+ conf.getPackageclass() + "." + conf.getSrcConfigFolder()+".Constants;\r\n" +
				"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".User;\r\n" +
				"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".Authority;\r\n" +
				"import javax.validation.constraints.Email;\r\n" +
				"import javax.validation.constraints.NotBlank;\r\n" +
				"import javax.validation.constraints.*;\r\n" +
				"import java.time.Instant;\r\n" +
				"import java.util.Set;\r\n" +
				"import java.util.stream.Collectors;\r\n\n" +
				"/**\r\n" +
				" * A DTO representing a user, with his authorities.\r\n" +
				" */\r\n" +
				"public class "+getClassName()+" {\r\n\n" +
				"    private Long id;\r\n" +
				"    @NotBlank\r\n" +
				"    @Pattern(regexp = Constants.LOGIN_REGEX)\r\n" +
				"    @Size(min = 1, max = 50)\r\n" +
				"    private String login;\r\n" +
				"    @Size(max = 50)\r\n" +
				"    private String firstName;\r\n" +
				"    @Size(max = 50)\r\n" +
				"    private String lastName;\r\n" +
				"    @Email\r\n" +
				"    @Size(min = 5, max = 254)\r\n" +
				"    private String email;\r\n" +
				"    @Size(max = 256)\r\n" +
				"    private String imageUrl;\r\n" +
				"    private boolean activated = false;\r\n" +
				"    @Size(min = 2, max = 6)\r\n" +
				"    private String langKey;\r\n" +
				"    private String createdBy;\r\n" +
				"    private Instant createdDate;\r\n" +
				"    private String lastModifiedBy;\r\n" +
				"    private Instant lastModifiedDate;\r\n" +
				"    private Set<String> authorities;\r\n\n" +
				"    public "+getClassName()+"() {\r\n" +
				"        // Empty constructor needed for Jackson.\r\n" +
				"    }\r\n\n" +
				"    public "+getClassName()+"(User user) {\r\n" +
				"        this.id = user.getId();\r\n" +
				"        this.login = user.getLogin();\r\n" +
				"        this.firstName = user.getFirstName();\r\n" +
				"        this.lastName = user.getLastName();\r\n" +
				"        this.email = user.getEmail();\r\n" +
				"        this.activated = user.getActivated();\r\n" +
				"        this.imageUrl = user.getImageUrl();\r\n" +
				"        this.langKey = user.getLangKey();\r\n" +
				"        this.createdBy = user.getCreatedBy();\r\n" +
				"        this.createdDate = user.getCreatedDate();\r\n" +
				"        this.lastModifiedBy = user.getLastModifiedBy();\r\n" +
				"        this.lastModifiedDate = user.getLastModifiedDate();\r\n" +
				"        this.authorities = user.getAuthorities().stream()\r\n" +
				"            .map(Authority::getName)\r\n" +
				"            .collect(Collectors.toSet());\r\n" +
				"    }\r\n\n" +
				"    public Long getId() {\r\n" +
				"        return id;\r\n" +
				"    }\r\n\n" +
				"    public void setId(Long id) {\r\n" +
				"        this.id = id;\r\n" +
				"    }\r\n\n" +
				"    public String getLogin() {\r\n" +
				"        return login;\r\n" +
				"    }\r\n\n" +
				"    public void setLogin(String login) {\r\n" +
				"        this.login = login;\r\n" +
				"    }\r\n\n" +
				"    public String getFirstName() {\r\n" +
				"        return firstName;\r\n" +
				"    }\r\n\n" +
				"    public void setFirstName(String firstName) {\r\n" +
				"        this.firstName = firstName;\r\n" +
				"    }\r\n\n" +
				"    public String getLastName() {\r\n" +
				"        return lastName;\r\n" +
				"    }\r\n\n" +
				"    public void setLastName(String lastName) {\r\n" +
				"        this.lastName = lastName;\r\n" +
				"    }\r\n\n" +
				"    public String getEmail() {\r\n" +
				"        return email;\r\n" +
				"    }\r\n\n" +
				"    public void setEmail(String email) {\r\n" +
				"        this.email = email;\r\n" +
				"    }\r\n\n" +
				"    public String getImageUrl() {\r\n" +
				"        return imageUrl;\r\n" +
				"    }\r\n\n" +
				"    public void setImageUrl(String imageUrl) {\r\n" +
				"        this.imageUrl = imageUrl;\r\n" +
				"    }\r\n\n" +
				"    public boolean isActivated() {\r\n" +
				"        return activated;\r\n" +
				"    }\r\n\n" +
				"    public void setActivated(boolean activated) {\r\n" +
				"        this.activated = activated;\r\n" +
				"    }\r\n\n" +
				"    public String getLangKey() {\r\n" +
				"        return langKey;\r\n" +
				"    }\r\n\n" +
				"    public void setLangKey(String langKey) {\r\n" +
				"        this.langKey = langKey;\r\n" +
				"    }\r\n\n" +
				"    public String getCreatedBy() {\r\n" +
				"        return createdBy;\r\n" +
				"    }\r\n\n" +
				"    public void setCreatedBy(String createdBy) {\r\n" +
				"        this.createdBy = createdBy;\r\n" +
				"    }\r\n\n" +
				"    public Instant getCreatedDate() {\r\n" +
				"        return createdDate;\r\n" +
				"    }\r\n\n" +
				"    public void setCreatedDate(Instant createdDate) {\r\n" +
				"        this.createdDate = createdDate;\r\n" +
				"    }\r\n\n" +
				"    public String getLastModifiedBy() {\r\n" +
				"        return lastModifiedBy;\r\n" +
				"    }\r\n\n" +
				"    public void setLastModifiedBy(String lastModifiedBy) {\r\n" +
				"        this.lastModifiedBy = lastModifiedBy;\r\n" +
				"    }\r\n\n" +
				"    public Instant getLastModifiedDate() {\r\n" +
				"        return lastModifiedDate;\r\n" +
				"    }\r\n\n" +
				"    public void setLastModifiedDate(Instant lastModifiedDate) {\r\n" +
				"        this.lastModifiedDate = lastModifiedDate;\r\n" +
				"    }\r\n\n" +
				"    public Set<String> getAuthorities() {\r\n" +
				"        return authorities;\r\n" +
				"    }\r\n\n" +
				"    public void setAuthorities(Set<String> authorities) {\r\n" +
				"        this.authorities = authorities;\r\n" +
				"    }\r\n\n" +
				"    @Override\r\n" +
				"    public String toString() {\r\n" +
				"        return \""+getClassName()+"{\" +\r\n" +
				"            \"login='\" + login + '\\'' +\r\n" +
				"            \", firstName='\" + firstName + '\\'' +\r\n" +
				"            \", lastName='\" + lastName + '\\'' +\r\n" +
				"            \", email='\" + email + '\\'' +\r\n" +
				"            \", imageUrl='\" + imageUrl + '\\'' +\r\n" +
				"            \", activated=\" + activated +\r\n" +
				"            \", langKey='\" + langKey + '\\'' +\r\n" +
				"            \", createdBy=\" + createdBy +\r\n" +
				"            \", createdDate=\" + createdDate +\r\n" +
				"            \", lastModifiedBy='\" + lastModifiedBy + '\\'' +\r\n" +
				"            \", lastModifiedDate=\" + lastModifiedDate +\r\n" +
				"            \", authorities=\" + authorities +\r\n" +
				"            \"}\";\r\n" +
				"    }\r\n\n" +
				"}\r\n";
		return body;
	}

	public String getClassName() {
		return "UserDTO";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
