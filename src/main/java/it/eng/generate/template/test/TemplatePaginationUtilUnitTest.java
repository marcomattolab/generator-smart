package it.eng.generate.template.test;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplatePaginationUtilUnitTest extends AbstractTemplate{

	public TemplatePaginationUtilUnitTest(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcWebRestUtilFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcWebRestUtilFolder()+";\r\n\n" +
		"import static org.junit.Assert.assertEquals;\r\n" +
		"import static org.junit.Assert.assertNotNull;\r\n" +
		"import static org.junit.Assert.assertTrue;\r\n" +
		"import java.util.ArrayList;\r\n" +
		"import java.util.List;\r\n" +
		"import org.junit.Test;\r\n" +
		"import org.springframework.data.domain.Page;\r\n" +
		"import org.springframework.data.domain.PageImpl;\r\n" +
		"import org.springframework.data.domain.PageRequest;\r\n" +
		"import org.springframework.http.HttpHeaders;\r\n\n" +
		"/**\r\n" +
		" * Tests based on parsing algorithm in app/components/util/pagination-util.service.js\r\n" +
		" *\r\n" +
		" * @see PaginationUtil\r\n" +
		" */\r\n" +
		"public class PaginationUtilUnitTest {\r\n\n" +
		"    @Test\r\n" +
		"    public void generatePaginationHttpHeadersTest() {\r\n" +
		"        String baseUrl = \"/api/_search/example\";\r\n" +
		"        List<String> content = new ArrayList<>();\r\n" +
		"        Page<String> page = new PageImpl<>(content, PageRequest.of(6, 50), 400L);\r\n" +
		"        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, baseUrl);\r\n" +
		"        List<String> strHeaders = headers.get(HttpHeaders.LINK);\r\n" +
		"        assertNotNull(strHeaders);\r\n" +
		"        assertTrue(strHeaders.size() == 1);\r\n" +
		"        String headerData = strHeaders.get(0);\r\n" +
		"        assertTrue(headerData.split(\",\").length == 4);\r\n" +
		"        String expectedData = \"</api/_search/example?page=7&size=50>; rel=\\\"next\\\",\"\r\n" +
		"                + \"</api/_search/example?page=5&size=50>; rel=\\\"prev\\\",\"\r\n" +
		"                + \"</api/_search/example?page=7&size=50>; rel=\\\"last\\\",\"\r\n" +
		"                + \"</api/_search/example?page=0&size=50>; rel=\\\"first\\\"\";\r\n" +
		"        assertEquals(expectedData, headerData);\r\n" +
		"        List<String> xTotalCountHeaders = headers.get(\"X-Total-Count\");\r\n" +
		"        assertTrue(xTotalCountHeaders.size() == 1);\r\n" +
		"        assertTrue(Long.valueOf(xTotalCountHeaders.get(0)).equals(400L));\r\n" +
		"    }\r\n\n" +
		"}\r\n";

		return body;
	}

	public String getClassName() {
		return "PaginationUtilUnitTest";
	}

	public String getSourceFolder() {
		return "src/test/java";
	}

}
