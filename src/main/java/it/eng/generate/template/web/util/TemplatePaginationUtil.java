package it.eng.generate.template.web.util;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplatePaginationUtil extends AbstractTemplate{

	public TemplatePaginationUtil(DataBase dataBase) {
		super(dataBase);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcWebRestUtilFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcWebRestUtilFolder()+";\r\n\n" +
		"import org.springframework.data.domain.Page;\r\n" +
		"import org.springframework.http.HttpHeaders;\r\n" +
		"import org.springframework.web.util.UriComponentsBuilder;\r\n\n" +
		"/**\r\n" +
		" * Utility class for handling pagination.\r\n" +
		" *\r\n" +
		" * <p>\r\n" +
		" * Pagination uses the same principles as the <a href=\"https://developer.github.com/v3/#pagination\">GitHub API</a>,\r\n" +
		" * and follow <a href=\"http://tools.ietf.org/html/rfc5988\">RFC 5988 (Link header)</a>.\r\n" +
		" */\r\n" +
		"public final class "+getClassName()+" {\r\n\n" +
		"    private "+getClassName()+"() {\r\n" +
		"    }\r\n\n" +
		"    public static <T> HttpHeaders generatePaginationHttpHeaders(Page<T> page, String baseUrl) {\r\n" +
		"        HttpHeaders headers = new HttpHeaders();\r\n" +
		"        headers.add(\"X-Total-Count\", Long.toString(page.getTotalElements()));\r\n" +
		"        String link = \"\";\r\n" +
		"        if ((page.getNumber() + 1) < page.getTotalPages()) {\r\n" +
		"            link = \"<\" + generateUri(baseUrl, page.getNumber() + 1, page.getSize()) + \">; rel=\\\"next\\\",\";\r\n" +
		"        }\r\n" +
		"        // prev link\r\n" +
		"        if ((page.getNumber()) > 0) {\r\n" +
		"            link += \"<\" + generateUri(baseUrl, page.getNumber() - 1, page.getSize()) + \">; rel=\\\"prev\\\",\";\r\n" +
		"        }\r\n" +
		"        // last and first link\r\n" +
		"        int lastPage = 0;\r\n" +
		"        if (page.getTotalPages() > 0) {\r\n" +
		"            lastPage = page.getTotalPages() - 1;\r\n" +
		"        }\r\n" +
		"        link += \"<\" + generateUri(baseUrl, lastPage, page.getSize()) + \">; rel=\\\"last\\\",\";\r\n" +
		"        link += \"<\" + generateUri(baseUrl, 0, page.getSize()) + \">; rel=\\\"first\\\"\";\r\n" +
		"        headers.add(HttpHeaders.LINK, link);\r\n" +
		"        return headers;\r\n" +
		"    }\r\n\n" +
		"    private static String generateUri(String baseUrl, int page, int size) {\r\n" +
		"        return UriComponentsBuilder.fromUriString(baseUrl).queryParam(\"page\", page).queryParam(\"size\", size).toUriString();\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "PaginationUtil";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
