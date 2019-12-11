package it.eng.generate.template.test;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateDateTimeWrapper extends AbstractTemplate{

	public TemplateDateTimeWrapper(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcRepositoryTimezoneFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcRepositoryTimezoneFolder()+";\r\n\n" +
		"import javax.persistence.*;\r\n" +
		"import java.io.Serializable;\r\n" +
		"import java.time.*;\r\n" +
		"import java.util.Objects;\r\n\n" +
		"@Entity\r\n" +
		"@Table(name = \"jhi_date_time_wrapper\")\r\n" +
		"public class DateTimeWrapper implements Serializable {\r\n" +
		"    private static final long serialVersionUID = 1L;\r\n" +
		"    @Id\r\n" +
		"    @GeneratedValue(strategy = GenerationType.IDENTITY)\r\n" +
		"    private Long id;\r\n\n" +
		"    @Column(name = \"instant\")\r\n" +
		"    private Instant instant;\r\n\n" +
		"    @Column(name = \"local_date_time\")\r\n" +
		"    private LocalDateTime localDateTime;\r\n\n" +
		"    @Column(name = \"offset_date_time\")\r\n" +
		"    private OffsetDateTime offsetDateTime;\r\n\n" +
		"    @Column(name = \"zoned_date_time\")\r\n" +
		"    private ZonedDateTime zonedDateTime;\r\n\n" +
		"    @Column(name = \"local_time\")\r\n" +
		"    private LocalTime localTime;\r\n\n" +
		"    @Column(name = \"offset_time\")\r\n" +
		"    private OffsetTime offsetTime;\r\n\n" +
		"    @Column(name = \"local_date\")\r\n" +
		"    private LocalDate localDate;\r\n\n" +
		"    public Long getId() {\r\n" +
		"        return id;\r\n" +
		"    }\r\n\n" +
		"    public void setId(Long id) {\r\n" +
		"        this.id = id;\r\n" +
		"    }\r\n\n" +
		"    public Instant getInstant() {\r\n" +
		"        return instant;\r\n" +
		"    }\r\n\n" +
		"    public void setInstant(Instant instant) {\r\n" +
		"        this.instant = instant;\r\n" +
		"    }\r\n\n" +
		"    public LocalDateTime getLocalDateTime() {\r\n" +
		"        return localDateTime;\r\n" +
		"    }\r\n\n" +
		"    public void setLocalDateTime(LocalDateTime localDateTime) {\r\n" +
		"        this.localDateTime = localDateTime;\r\n" +
		"    }\r\n\n" +
		"    public OffsetDateTime getOffsetDateTime() {\r\n" +
		"        return offsetDateTime;\r\n" +
		"    }\r\n\n" +
		"    public void setOffsetDateTime(OffsetDateTime offsetDateTime) {\r\n" +
		"        this.offsetDateTime = offsetDateTime;\r\n" +
		"    }\r\n\n" +
		"    public ZonedDateTime getZonedDateTime() {\r\n" +
		"        return zonedDateTime;\r\n" +
		"    }\r\n\n" +
		"    public void setZonedDateTime(ZonedDateTime zonedDateTime) {\r\n" +
		"        this.zonedDateTime = zonedDateTime;\r\n" +
		"    }\r\n\n" +
		"    public LocalTime getLocalTime() {\r\n" +
		"        return localTime;\r\n" +
		"    }\r\n\n" +
		"    public void setLocalTime(LocalTime localTime) {\r\n" +
		"        this.localTime = localTime;\r\n" +
		"    }\r\n\n" +
		"    public OffsetTime getOffsetTime() {\r\n" +
		"        return offsetTime;\r\n" +
		"    }\r\n\n" +
		"    public void setOffsetTime(OffsetTime offsetTime) {\r\n" +
		"        this.offsetTime = offsetTime;\r\n" +
		"    }\r\n\n" +
		"    public LocalDate getLocalDate() {\r\n" +
		"        return localDate;\r\n" +
		"    }\r\n\n" +
		"    public void setLocalDate(LocalDate localDate) {\r\n" +
		"        this.localDate = localDate;\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    public boolean equals(Object o) {\r\n" +
		"        if (this == o) {\r\n" +
		"            return true;\r\n" +
		"        }\r\n" +
		"        if (o == null || getClass() != o.getClass()) {\r\n" +
		"            return false;\r\n" +
		"        }\r\n" +
		"        DateTimeWrapper dateTimeWrapper = (DateTimeWrapper) o;\r\n" +
		"        return !(dateTimeWrapper.getId() == null || getId() == null) && Objects.equals(getId(), dateTimeWrapper.getId());\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    public int hashCode() {\r\n" +
		"        return Objects.hashCode(getId());\r\n" +
		"    }\r\n\n" +
		"    @Override\r\n" +
		"    public String toString() {\r\n" +
		"        return \"TimeZoneTest{\" +\r\n" +
		"            \"id=\" + id +\r\n" +
		"            \", instant=\" + instant +\r\n" +
		"            \", localDateTime=\" + localDateTime +\r\n" +
		"            \", offsetDateTime=\" + offsetDateTime +\r\n" +
		"            \", zonedDateTime=\" + zonedDateTime +\r\n" +
		"            '}';\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "DateTimeWrapper";
	}

	public String getSourceFolder() {
		return "src/test/java";
	}

}
