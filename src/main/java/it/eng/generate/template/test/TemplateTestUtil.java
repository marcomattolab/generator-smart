package it.eng.generate.template.test;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateTestUtil extends AbstractTemplate{

	public TemplateTestUtil(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcWebRestFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcWebRestFolder()+";\r\n\n" +
		"import com.fasterxml.jackson.annotation.JsonInclude;\r\n" +
		"import com.fasterxml.jackson.databind.ObjectMapper;\r\n" +
		"import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;\r\n" +
		"import org.hamcrest.Description;\r\n" +
		"import org.hamcrest.TypeSafeDiagnosingMatcher;\r\n" +
		"import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;\r\n" +
		"import org.springframework.format.support.DefaultFormattingConversionService;\r\n" +
		"import org.springframework.format.support.FormattingConversionService;\r\n" +
		"import org.springframework.http.MediaType;\r\n" +
		"import java.io.IOException;\r\n" +
		"import java.nio.charset.StandardCharsets;\r\n" +
		"import java.time.ZonedDateTime;\r\n" +
		"import java.time.format.DateTimeParseException;\r\n" +
		"import static org.assertj.core.api.Assertions.assertThat;\r\n\n" +
		"/**\r\n" +
		" * Utility class for testing REST controllers.\r\n" +
		" */\r\n" +
		"public class TestUtil {\r\n" +
		"    /** MediaType for JSON UTF8 */\r\n" +
		"    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(\r\n" +
		"            MediaType.APPLICATION_JSON.getType(),\r\n" +
		"            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);\r\n\n" +
		"    /**\r\n" +
		"     * Convert an object to JSON byte array.\r\n" +
		"     *\r\n" +
		"     * @param object\r\n" +
		"     *            the object to convert\r\n" +
		"     * @return the JSON byte array\r\n" +
		"     * @throws IOException\r\n" +
		"     */\r\n" +
		"    public static byte[] convertObjectToJsonBytes(Object object)\r\n" +
		"            throws IOException {\r\n" +
		"        ObjectMapper mapper = new ObjectMapper();\r\n" +
		"        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);\r\n" +
		"        JavaTimeModule module = new JavaTimeModule();\r\n" +
		"        mapper.registerModule(module);\r\n" +
		"        return mapper.writeValueAsBytes(object);\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Create a byte array with a specific size filled with specified data.\r\n" +
		"     *\r\n" +
		"     * @param size the size of the byte array\r\n" +
		"     * @param data the data to put in the byte array\r\n" +
		"     * @return the JSON byte array\r\n" +
		"     */\r\n" +
		"    public static byte[] createByteArray(int size, String data) {\r\n" +
		"        byte[] byteArray = new byte[size];\r\n" +
		"        for (int i = 0; i < size; i++) {\r\n" +
		"            byteArray[i] = Byte.parseByte(data, 2);\r\n" +
		"        }\r\n" +
		"        return byteArray;\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * A matcher that tests that the examined string represents the same instant as the reference datetime.\r\n" +
		"     */\r\n" +
		"    public static class ZonedDateTimeMatcher extends TypeSafeDiagnosingMatcher<String> {\r\n" +
		"        private final ZonedDateTime date;\r\n" +
		"        public ZonedDateTimeMatcher(ZonedDateTime date) {\r\n" +
		"            this.date = date;\r\n" +
		"        }\r\n" +
		"        @Override\r\n" +
		"        protected boolean matchesSafely(String item, Description mismatchDescription) {\r\n" +
		"            try {\r\n" +
		"                if (!date.isEqual(ZonedDateTime.parse(item))) {\r\n" +
		"                    mismatchDescription.appendText(\"was \").appendValue(item);\r\n" +
		"                    return false;\r\n" +
		"                }\r\n" +
		"                return true;\r\n" +
		"            } catch (DateTimeParseException e) {\r\n" +
		"                mismatchDescription.appendText(\"was \").appendValue(item)\r\n" +
		"                    .appendText(\", which could not be parsed as a ZonedDateTime\");\r\n" +
		"                return false;\r\n" +
		"            }\r\n" +
		"        }\r\n\n" +
		"        @Override\r\n" +
		"        public void describeTo(Description description) {\r\n" +
		"            description.appendText(\"a String representing the same Instant as \").appendValue(date);\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Creates a matcher that matches when the examined string reprensents the same instant as the reference datetime\r\n" +
		"     * @param date the reference datetime against which the examined string is checked\r\n" +
		"     */\r\n" +
		"    public static ZonedDateTimeMatcher sameInstant(ZonedDateTime date) {\r\n" +
		"        return new ZonedDateTimeMatcher(date);\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Verifies the equals/hashcode contract on the domain object.\r\n" +
		"     */\r\n" +
		"    public static <T> void equalsVerifier(Class<T> clazz) throws Exception {\r\n" +
		"        T domainObject1 = clazz.getConstructor().newInstance();\r\n" +
		"        assertThat(domainObject1.toString()).isNotNull();\r\n" +
		"        assertThat(domainObject1).isEqualTo(domainObject1);\r\n" +
		"        assertThat(domainObject1.hashCode()).isEqualTo(domainObject1.hashCode());\r\n" +
		"        // Test with an instance of another class\r\n" +
		"        Object testOtherObject = new Object();\r\n" +
		"        assertThat(domainObject1).isNotEqualTo(testOtherObject);\r\n" +
		"        assertThat(domainObject1).isNotEqualTo(null);\r\n" +
		"        // Test with an instance of the same class\r\n" +
		"        T domainObject2 = clazz.getConstructor().newInstance();\r\n" +
		"        assertThat(domainObject1).isNotEqualTo(domainObject2);\r\n" +
		"        // HashCodes are equals because the objects are not persisted yet\r\n" +
		"        assertThat(domainObject1.hashCode()).isEqualTo(domainObject2.hashCode());\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Create a FormattingConversionService which use ISO date format, instead of the localized one.\r\n" +
		"     * @return the FormattingConversionService\r\n" +
		"     */\r\n" +
		"    public static FormattingConversionService createFormattingConversionService() {\r\n" +
		"        DefaultFormattingConversionService dfcs = new DefaultFormattingConversionService ();\r\n" +
		"        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();\r\n" +
		"        registrar.setUseIsoFormat(true);\r\n" +
		"        registrar.registerFormatters(dfcs);\r\n" +
		"        return dfcs;\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "TestUtil";
	}

	public String getSourceFolder() {
		return "src/test/java";
	}

}
