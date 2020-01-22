package it.eng.generate.template.resouces;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateLiquidbaseUsersAuthoritiesCSV extends AbstractResourceTemplate{
	
	public TemplateLiquidbaseUsersAuthoritiesCSV(DataBase database) {
		super(database);
	}
	
	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getResConfigLiquibaseDataFolder(),".","/");
		return typeTemplate;
	}
	
	public String getTypeFile() {
		return "csv";
	}

	public String getBody() {
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		
		String[] profiles = conf.getProfiles();
		String userProfileList  = profiles!=null && profiles.length>0 
								? buildProfiles(profiles) 
								: buildDefaultProfiles();
							
		String body = "user_id;authority_name\n" + userProfileList; 					
		return body;
	}


	private String buildDefaultProfiles() {
		String res= "1;ROLE_ADMIN\n" + 
					"1;ROLE_USER\n" + 
					"3;ROLE_ADMIN\n" + 
					"3;ROLE_USER\n" + 
					"4;ROLE_USER\n";
		return res;
	}

	private String buildProfiles(String[] profiles) {
		String res = "";
		for (int id = 0; id < profiles.length; id++) {
			String profileRole = profiles[id];
			//"1;ROLE_ADMIN\n" +
			//"2;ROLE_USER\n" + 
			res += (id+1)+";"+profileRole+"\n";
		}
		return res;
	}

	public String getClassName() {
		return "users_authorities";
	}
	
	public String getSourceFolder() {
		return "src/main/resources/";
	}

}
