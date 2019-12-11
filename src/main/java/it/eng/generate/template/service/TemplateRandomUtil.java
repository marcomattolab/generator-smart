package it.eng.generate.template.service;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateRandomUtil extends AbstractTemplate{

	public TemplateRandomUtil(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcServiceUtilFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcServiceUtilFolder()+";\r\n\n" +
		"import org.apache.commons.lang3.RandomStringUtils;\r\n\n" +
		"/**\r\n" +
		" * Utility class for generating random Strings.\r\n" +
		" */\r\n" +
		"public final class "+getClassName()+" {\r\n" +
		"    private static final int DEF_COUNT = 20;\r\n" +
		"    private "+getClassName()+"() {\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Generate a password.\r\n" +
		"     *\r\n" +
		"     * @return the generated password\r\n" +
		"     */\r\n" +
		"    public static String generatePassword() {\r\n" +
		"        return RandomStringUtils.randomAlphanumeric(DEF_COUNT);\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Generate an activation key.\r\n" +
		"     *\r\n" +
		"     * @return the generated activation key\r\n" +
		"     */\r\n" +
		"    public static String generateActivationKey() {\r\n" +
		"        return RandomStringUtils.randomNumeric(DEF_COUNT);\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Generate a reset key.\r\n" +
		"     *\r\n" +
		"     * @return the generated reset key\r\n" +
		"     */\r\n" +
		"    public static String generateResetKey() {\r\n" +
		"        return RandomStringUtils.randomNumeric(DEF_COUNT);\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Generate a unique series to validate a persistent token, used in the\r\n" +
		"     * authentication remember-me mechanism.\r\n" +
		"     *\r\n" +
		"     * @return the generated series data\r\n" +
		"     */\r\n" +
		"    public static String generateSeriesData() {\r\n" +
		"        return RandomStringUtils.randomAlphanumeric(DEF_COUNT);\r\n" +
		"    }\r\n\n" +
		"    /**\r\n" +
		"     * Generate a persistent token, used in the authentication remember-me mechanism.\r\n" +
		"     *\r\n" +
		"     * @return the generated token data\r\n" +
		"     */\r\n" +
		"    public static String generateTokenData() {\r\n" +
		"        return RandomStringUtils.randomAlphanumeric(DEF_COUNT);\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "RandomUtil";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
