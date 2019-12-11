package it.eng.generate.template.domain;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplatePersistentToken extends AbstractTemplate {

	public TemplatePersistentToken(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder() +";\r\n\n" +
		"import com.fasterxml.jackson.annotation.JsonIgnore;\r\n" +
		"import org.hibernate.annotations.Cache;\r\n" +
		"import org.hibernate.annotations.CacheConcurrencyStrategy;\r\n" +
		"import java.time.LocalDate;\r\n" +
		"import javax.persistence.*;\r\n" +
		"import javax.validation.constraints.NotNull;\r\n" +
		"import javax.validation.constraints.Size;\r\n" +
		"import java.io.Serializable;\r\n\n" +
		"/**\r\n" +
		" * Persistent tokens are used by Spring Security to automatically log in users.\r\n" +
		" *\r\n" +
		" * @see it.eng.security.PersistentTokenRememberMeServices\r\n" +
		" */\r\n" +
		"@Entity\r\n" +
		"@Table(name = \"jhi_persistent_token\")\r\n" +
		"public class "+getClassName()+" implements Serializable {\r\n\n" +
		"    private static final long serialVersionUID = 1L;\r\n\n" +
		"    private static final int MAX_USER_AGENT_LEN = 255;\r\n\n" +
		"    @Id\r\n" +
		"    private String series;\r\n\n" +
		"    @JsonIgnore\r\n" +
		"    @NotNull\r\n" +
		"    @Column(name = \"token_value\", nullable = false)\r\n" +
		"    private String tokenValue;\r\n" +
		"    \r\n" +
		"    @Column(name = \"token_date\")\r\n" +
		"    private LocalDate tokenDate;\r\n\n" +
		"    //an IPV6 address max length is 39 characters\r\n" +
		"    @Size(min = 0, max = 39)\r\n" +
		"    @Column(name = \"ip_address\", length = 39)\r\n" +
		"    private String ipAddress;\r\n\n" +
		"    @Column(name = \"user_agent\")\r\n" +
		"    private String userAgent;\r\n" +
		"        \r\n" +
		"    @JsonIgnore\r\n" +
		"    @ManyToOne\r\n" +
		"    private User user;\r\n\n" +
		"    public String getSeries() {\r\n" +
		"        return series;\r\n" +
		"    }\r\n\n" +
		"    public void setSeries(String series) {\r\n" +
		"        this.series = series;\r\n" +
		"    }\r\n\n" +
		"    public String getTokenValue() {\r\n" +
		"        return tokenValue;\r\n" +
		"    }\r\n\n" +
		"    public void setTokenValue(String tokenValue) {\r\n" +
		"        this.tokenValue = tokenValue;\r\n" +
		"    }\r\n\n" +
		"    public LocalDate getTokenDate() {\r\n" +
		"        return tokenDate;\r\n" +
		"    }\r\n\n" +
		"    public void setTokenDate(LocalDate tokenDate) {\r\n" +
		"        this.tokenDate = tokenDate;\r\n" +
		"    }\r\n\n" +
		"    public String getIpAddress() {\r\n" +
		"        return ipAddress;\r\n" +
		"    }\r\n\n" +
		"    public void setIpAddress(String ipAddress) {\r\n" +
		"        this.ipAddress = ipAddress;\r\n" +
		"    }\r\n\n" +
		"    public String getUserAgent() {\r\n" +
		"        return userAgent;\r\n" +
		"    }\r\n\n" +
		"    public void setUserAgent(String userAgent) {\r\n" +
		"        if (userAgent.length() >= MAX_USER_AGENT_LEN) {\r\n" +
		"            this.userAgent = userAgent.substring(0, MAX_USER_AGENT_LEN - 1);\r\n" +
		"        } else {\r\n" +
		"            this.userAgent = userAgent;\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"    public User getUser() {\r\n" +
		"        return user;\r\n" +
		"    }\r\n\n" +
		"    public void setUser(User user) {\r\n" +
		"        this.user = user;\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    public boolean equals(Object o) {\r\n" +
		"        if (this == o) {\r\n" +
		"            return true;\r\n" +
		"        }\r\n" +
		"        if (o == null || getClass() != o.getClass()) {\r\n" +
		"            return false;\r\n" +
		"        }\r\n" +
		"        "+getClassName()+" that = ("+getClassName()+") o;\r\n" +
		"        if (!series.equals(that.series)) {\r\n" +
		"            return false;\r\n" +
		"        }\r\n" +
		"        return true;\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    public int hashCode() {\r\n" +
		"        return series.hashCode();\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    public String toString() {\r\n" +
		"        return \""+getClassName()+"{\" +\r\n" +
		"            \"series='\" + series + '\\'' +\r\n" +
		"            \", tokenValue='\" + tokenValue + '\\'' +\r\n" +
		"            \", tokenDate=\" + tokenDate +\r\n" +
		"            \", ipAddress='\" + ipAddress + '\\'' +\r\n" +
		"            \", userAgent='\" + userAgent + '\\'' +\r\n" +
		"            \"}\";\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "PersistentToken";
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
