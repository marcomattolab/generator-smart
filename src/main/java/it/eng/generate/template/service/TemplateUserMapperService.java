package it.eng.generate.template.service;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateUserMapperService extends AbstractTemplate{

	public TemplateUserMapperService(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcServiceMapperFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = "package "+ conf.getPackageclass() + "." + conf.getSrcServiceMapperFolder()+";\r\n\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".Authority;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".User;\r\n" +
		"import "+ conf.getPackageclass() + "." + conf.getSrcServiceDtoFolder()+".UserDTO;\r\n" +
		"import org.springframework.stereotype.Service;\r\n" +
		"import java.util.*;\r\n" +
		"import java.util.stream.Collectors;\r\n\n" +
		"/**\r\n" +
		" * Mapper for the entity User and its DTO called UserDTO.\r\n" +
		" *\r\n" +
		" * Normal mappers are generated using MapStruct, this one is hand-coded as MapStruct\r\n" +
		" * support is still in beta, and requires a manual step with an IDE.\r\n" +
		" */\r\n" +
		"@Service\r\n" +
		"public class "+getClassName()+" {\r\n\n" +
		"    public UserDTO userToUserDTO(User user) {\r\n" +
		"        return new UserDTO(user);\r\n" +
		"    }\r\n\n" +
		"    public List<UserDTO> usersToUserDTOs(List<User> users) {\r\n" +
		"        return users.stream()\r\n" +
		"            .filter(Objects::nonNull)\r\n" +
		"            .map(this::userToUserDTO)\r\n" +
		"            .collect(Collectors.toList());\r\n" +
		"    }\r\n\n" +
		"    public User userDTOToUser(UserDTO userDTO) {\r\n" +
		"        if (userDTO == null) {\r\n" +
		"            return null;\r\n" +
		"        } else {\r\n" +
		"            User user = new User();\r\n" +
		"            user.setId(userDTO.getId());\r\n" +
		"            user.setLogin(userDTO.getLogin());\r\n" +
		"            user.setFirstName(userDTO.getFirstName());\r\n" +
		"            user.setLastName(userDTO.getLastName());\r\n" +
		"            user.setEmail(userDTO.getEmail());\r\n" +
		"            user.setImageUrl(userDTO.getImageUrl());\r\n" +
		"            user.setActivated(userDTO.isActivated());\r\n" +
		"            user.setLangKey(userDTO.getLangKey());\r\n" +
		"            Set<Authority> authorities = this.authoritiesFromStrings(userDTO.getAuthorities());\r\n" +
		"            if (authorities != null) {\r\n" +
		"                user.setAuthorities(authorities);\r\n" +
		"            }\r\n" +
		"            return user;\r\n" +
		"        }\r\n" +
		"    }\r\n\n" +
		"    public List<User> userDTOsToUsers(List<UserDTO> userDTOs) {\r\n" +
		"        return userDTOs.stream()\r\n" +
		"            .filter(Objects::nonNull)\r\n" +
		"            .map(this::userDTOToUser)\r\n" +
		"            .collect(Collectors.toList());\r\n" +
		"    }\r\n\n" +
		"    public User userFromId(Long id) {\r\n" +
		"        if (id == null) {\r\n" +
		"            return null;\r\n" +
		"        }\r\n" +
		"        User user = new User();\r\n" +
		"        user.setId(id);\r\n" +
		"        return user;\r\n" +
		"    }\r\n\n" +
		"    public Set<Authority> authoritiesFromStrings(Set<String> strings) {\r\n" +
		"        return strings.stream().map(string -> {\r\n" +
		"            Authority auth = new Authority();\r\n" +
		"            auth.setName(string);\r\n" +
		"            return auth;\r\n" +
		"        }).collect(Collectors.toSet());\r\n" +
		"    }\r\n\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return "UserMapper";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
