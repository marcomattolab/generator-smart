package it.eng.generate.template.domain;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateUser extends AbstractTemplate {

	public TemplateUser(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder() +";\r\n\n" +
		"import "+conf.getPackageclass() + "." + conf.getSrcConfigFolder()+".Constants;\r\n" +
		"import com.fasterxml.jackson.annotation.JsonIgnore;\r\n" +
		"import org.apache.commons.lang3.StringUtils;\r\n" +
		"import org.hibernate.annotations.BatchSize;\r\n" +
		"import org.hibernate.annotations.Cache;\r\n" +
		"import org.hibernate.annotations.CacheConcurrencyStrategy;\r\n" +
		"import javax.validation.constraints.Email;\r\n" +
		"import javax.persistence.*;\r\n" +
		"import javax.validation.constraints.NotNull;\r\n" +
		"import javax.validation.constraints.Pattern;\r\n" +
		"import javax.validation.constraints.Size;\r\n" +
		"import java.io.Serializable;\r\n" +
		"import java.util.HashSet;\r\n" +
		"import java.util.Locale;\r\n" +
		"import java.util.Objects;\r\n" +
		"import java.util.Set;\r\n" +
		"import java.time.Instant;\r\n\n" +
		"/**\r\n" +
		" * A user.\r\n" +
		" */\r\n" +
		"@Entity\r\n" +
		"@Table(name = \"jhi_user\")\r\n" +
		"@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)\r\n" +
		"public class "+getClassName()+" extends AbstractAuditingEntity implements Serializable {\r\n\n" +
		"    private static final long serialVersionUID = 1L;\r\n\n" +
		"    @Id\r\n" +
		"    @GeneratedValue(strategy = GenerationType.IDENTITY)\r\n" +
		"    private Long id;\r\n\n" +
		"    @NotNull\r\n" +
		"    @Pattern(regexp = Constants.LOGIN_REGEX)\r\n" +
		"    @Size(min = 1, max = 50)\r\n" +
		"    @Column(length = 50, unique = true, nullable = false)\r\n" +
		"    private String login;\r\n\n" +
		"    @JsonIgnore\r\n" +
		"    @NotNull\r\n" +
		"    @Size(min = 60, max = 60)\r\n" +
		"    @Column(name = \"password_hash\", length = 60, nullable = false)\r\n" +
		"    private String password;\r\n\n" +
		"    @Size(max = 50)\r\n" +
		"    @Column(name = \"first_name\", length = 50)\r\n" +
		"    private String firstName;\r\n\n" +
		"    @Size(max = 50)\r\n" +
		"    @Column(name = \"last_name\", length = 50)\r\n" +
		"    private String lastName;\r\n\n" +
		"    @Email\r\n" +
		"    @Size(min = 5, max = 254)\r\n" +
		"    @Column(length = 254, unique = true)\r\n" +
		"    private String email;\r\n\n" +
		"    @NotNull\r\n" +
		"    @Column(nullable = false)\r\n" +
		"    private boolean activated = false;\r\n\n" +
		"    @Size(min = 2, max = 6)\r\n" +
		"    @Column(name = \"lang_key\", length = 6)\r\n" +
		"    private String langKey;\r\n\n" +
		"    @Size(max = 256)\r\n" +
		"    @Column(name = \"image_url\", length = 256)\r\n" +
		"    private String imageUrl;\r\n\n" +
		"    @Size(max = 20)\r\n" +
		"    @Column(name = \"activation_key\", length = 20)\r\n" +
		"    @JsonIgnore\r\n" +
		"    private String activationKey;\r\n\n" +
		"    @Size(max = 20)\r\n" +
		"    @Column(name = \"reset_key\", length = 20)\r\n" +
		"    @JsonIgnore\r\n" +
		"    private String resetKey;\r\n\n" +
		"    @Column(name = \"reset_date\")\r\n" +
		"    private Instant resetDate = null;\r\n\n" +
		"    @JsonIgnore\r\n" +
		"    @ManyToMany\r\n" +
		"    @JoinTable(\r\n" +
		"        name = \"jhi_user_authority\",\r\n" +
		"        joinColumns = {@JoinColumn(name = \"user_id\", referencedColumnName = \"id\")},\r\n" +
		"        inverseJoinColumns = {@JoinColumn(name = \"authority_name\", referencedColumnName = \"name\")})\r\n" +
		"    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)\r\n" +
		"    @BatchSize(size = 20)\r\n" +
		"    private Set<Authority> authorities = new HashSet<>();\r\n\n" +
//		"    @JsonIgnore\r\n" +
//		"    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = \"user\")\r\n" +
//		"    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)\r\n" +
//		"    private Set<PersistentToken> persistentTokens = new HashSet<>();\r\n\n" +
		"    public Long getId() {\r\n\n" +
		"        return id;\r\n" +
		"    }\r\n\n" +
		"    public void setId(Long id) {\r\n" +
		"        this.id = id;\r\n" +
		"    }\r\n\n" +
		"    public String getLogin() {\r\n" +
		"        return login;\r\n" +
		"    }\r\n\n" +
		"    // Lowercase the login before saving it in database\r\n" +
		"    public void setLogin(String login) {\r\n" +
		"        this.login = StringUtils.lowerCase(login, Locale.ENGLISH);\r\n" +
		"    }\r\n\n" +
		"    public String getPassword() {\r\n" +
		"        return password;\r\n" +
		"    }\r\n\n" +
		"    public void setPassword(String password) {\r\n" +
		"        this.password = password;\r\n" +
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
		"    public boolean getActivated() {\r\n" +
		"        return activated;\r\n" +
		"    }\r\n\n" +
		"    public void setActivated(boolean activated) {\r\n" +
		"        this.activated = activated;\r\n" +
		"    }\r\n\n" +
		"    public String getActivationKey() {\r\n" +
		"        return activationKey;\r\n" +
		"    }\r\n\n" +
		"    public void setActivationKey(String activationKey) {\r\n" +
		"        this.activationKey = activationKey;\r\n" +
		"    }\r\n\n" +
		"    public String getResetKey() {\r\n" +
		"        return resetKey;\r\n" +
		"    }\r\n\n" +
		"    public void setResetKey(String resetKey) {\r\n" +
		"        this.resetKey = resetKey;\r\n" +
		"    }\r\n\n" +
		"    public Instant getResetDate() {\r\n" +
		"        return resetDate;\r\n" +
		"    }\r\n\n" +
		"    public void setResetDate(Instant resetDate) {\r\n" +
		"        this.resetDate = resetDate;\r\n" +
		"    }\r\n\n" +
		"    public String getLangKey() {\r\n" +
		"        return langKey;\r\n" +
		"    }\r\n\n" +
		"    public void setLangKey(String langKey) {\r\n" +
		"        this.langKey = langKey;\r\n" +
		"    }\r\n\n" +
		"    public Set<Authority> getAuthorities() {\r\n" +
		"        return authorities;\r\n" +
		"    }\r\n\n" +
		"    public void setAuthorities(Set<Authority> authorities) {\r\n" +
		"        this.authorities = authorities;\r\n" +
		"    }\r\n\n" +
//		"    public Set<PersistentToken> getPersistentTokens() {\r\n" +
//		"        return persistentTokens;\r\n" +
//		"    }\r\n\n" +
//		"    public void setPersistentTokens(Set<PersistentToken> persistentTokens) {\r\n" +
//		"        this.persistentTokens = persistentTokens;\r\n" +
//		"    }\r\n\n" +
		"    @Override\r\n" +
		"    public boolean equals(Object o) {\r\n" +
		"        if (this == o) {\r\n" +
		"            return true;\r\n" +
		"        }\r\n" +
		"        if (o == null || getClass() != o.getClass()) {\r\n" +
		"            return false;\r\n" +
		"        }\r\n" +
		"        "+getClassName()+" user = ("+getClassName()+") o;\r\n" +
		"        return !(user.getId() == null || getId() == null) && Objects.equals(getId(), user.getId());\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    public int hashCode() {\r\n" +
		"        return Objects.hashCode(getId());\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    public String toString() {\r\n" +
		"        return \""+getClassName()+"{\" +\r\n" +
		"            \"login='\" + login + '\\'' +\r\n" +
		"            \", firstName='\" + firstName + '\\'' +\r\n" +
		"            \", lastName='\" + lastName + '\\'' +\r\n" +
		"            \", email='\" + email + '\\'' +\r\n" +
		"            \", imageUrl='\" + imageUrl + '\\'' +\r\n" +
		"            \", activated='\" + activated + '\\'' +\r\n" +
		"            \", langKey='\" + langKey + '\\'' +\r\n" +
		"            \", activationKey='\" + activationKey + '\\'' +\r\n" +
		"            \"}\";\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "User";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcDomainFolder(),".","/");
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/java";
	}

}
