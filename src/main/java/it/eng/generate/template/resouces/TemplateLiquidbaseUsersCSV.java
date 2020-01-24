package it.eng.generate.template.resouces;

import at.favre.lib.crypto.bcrypt.BCrypt;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateLiquidbaseUsersCSV extends AbstractResourceTemplate{
	
	public TemplateLiquidbaseUsersCSV(DataBase database) {
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
		String profilesList = profiles!=null && profiles.length>0 
							? buildProfiles(profiles) 
							: buildDefaultProfiles();
							
		String body = "id;login;password_hash;first_name;last_name;email;image_url;activated;lang_key;created_by;last_modified_by\n" + profilesList; 					
		return body;
	}


	private String buildProfiles(String[] profiles) {
		String res = "";
		System.out.println("\n\n----- Credential Section Start --------");
		for (int id = 0; id < profiles.length; id++) {
			String profileRole = profiles[id];
			String profile = profileRole.replace("ROLE_", "").toLowerCase();
			String Profile = Utils.getFirstUpperCase(profile);
			String passwordHash = BCrypt.withDefaults().hashToString(12, profile.toCharArray());
			System.out.println(" - Role: "+profileRole+"    Credential(user/pwd): "+profile+"/"+profile+" Password(Cripted): "+passwordHash);
					
			res+= (id+1)+";"+profile+";"+passwordHash+";"+Profile+";"+Profile+";"+profile+"@localhost;;true;it;system;system\n";
		}
		System.out.println("\n");
		return res;
	}

	private String buildDefaultProfiles() {
		String res = 
		"1;system;$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG;System;System;system@localhost;;true;it;system;system\n" + 
		"2;anonymoususer;$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO;Anonymous;User;anonymous@localhost;;true;it;system;system\n" + 
		"3;admin;$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC;Administrator;Administrator;admin@localhost;;true;it;system;system\n" + 
		"4;user;$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K;User;User;user@localhost;;true;it;system;system\n"; 
		return res;
	}

	public String getClassName() {
		return "users";
	}
	
	public String getSourceFolder() {
		return "src/main/resources/";
	}

}
