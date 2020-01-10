package it.eng.generate.template.conf;

import java.util.List;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateCacheConfiguration extends AbstractTemplate {

	public TemplateCacheConfiguration(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		
		// https://www.buildmystring.com/
		
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcConfigFolder() + ";\r\n\n" +
		"import java.time.Duration;\r\n" +
		"import org.ehcache.config.builders.*;\r\n" +
		"import org.ehcache.jsr107.Eh107Configuration;\r\n" +
		"import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;\r\n" +
		"import io.github.jhipster.config.JHipsterProperties;\r\n" +
		"import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;\r\n" +
		"import org.springframework.cache.annotation.EnableCaching;\r\n" +
		"import org.springframework.context.annotation.*;\r\n\n" +
		"@Configuration\r\n" +  
		"@EnableCaching\r\n" +
		"public class CacheConfiguration {\r\n" +
		"    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;\r\n" +
		"    public CacheConfiguration(JHipsterProperties jHipsterProperties) {\r\n" +
		"        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());\r\n" +
		"        JHipsterProperties.Cache.Ehcache ehcache =\r\n" +
		"            jHipsterProperties.getCache().getEhcache();\r\n\n" +
		"        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(\r\n" +
		"            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,\r\n" +
		"                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))\r\n" +
		"                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))\r\n" +
		"                .build());\r\n" +
		"    }\r\n\n" +
		"    @Bean\r\n" +
		"    public JCacheManagerCustomizer cacheManagerCustomizer() {\r\n" +
		"        return cm -> {\r\n";
		
		
		body += "\t\t//CACHE - CICLE ALL ENTITIES AND RELATIONS\n"+
				"\t\tcm.createCache("+conf.getPackageclass() + "." + conf.getSrcRepositoryFolder()+".UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);\r\n" +
				"\t\tcm.createCache("+conf.getPackageclass() + "." + conf.getSrcRepositoryFolder()+".UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);\r\n" +
				"\t\tcm.createCache("+conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".User.class.getName(), jcacheConfiguration);\r\n" +
				"\t\tcm.createCache("+conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".Authority.class.getName(), jcacheConfiguration);\r\n" +
				"\t\tcm.createCache("+conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".User.class.getName() + \".authorities\", jcacheConfiguration);\r\n";
				//"\t\tcm.createCache("+conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".PersistentToken.class.getName(), jcacheConfiguration);\r\n" +
				//"\t\tcm.createCache("+conf.getPackageclass() + "." + conf.getSrcDomainFolder()+".User.class.getName() + \".persistentTokens\", jcacheConfiguration);\r\n";
		
		//Cerco tutte le tabelle tranne quelle di sistema (JHI ed Liquibase)
		List<Table> tabelle = Utils.getTables(database);
		for (Table tabella: tabelle) {
			String NomeTabella = Utils.getEntityName(tabella); 
			String nometabella = Utils.getClassNameLowerCase(tabella); 
			System.out.println("CACHE nometabella : "+nometabella + "  NomeTabella: " + NomeTabella);
			body += "\t\tcm.createCache("+conf.getPackageclass() + "." + conf.getSrcDomainFolder()+"."+NomeTabella+".class.getName(), jcacheConfiguration);\r\n";
		}
		
//		TODO ADD RELATIONS !!
//		"            cm.createCache(it.arancia.domain.Cliente.class.getName(), jcacheConfiguration);\r\n" +
//		"            cm.createCache(it.arancia.domain.Cliente.class.getName() + \".listaContattis\", jcacheConfiguration);\r\n" +
//		"            cm.createCache(it.arancia.domain.Cliente.class.getName() + \".tags\", jcacheConfiguration);\r\n" +
//		"            cm.createCache(it.arancia.domain.Cliente.class.getName() + \".incaricoCommittentes\", jcacheConfiguration);\r\n" +
//		"            cm.createCache(it.arancia.domain.Cliente.class.getName() + \".incaricoProponentes\", jcacheConfiguration);\r\n" +
//		"            cm.createCache(it.arancia.domain.Cliente.class.getName() + \".incaricoAcquirenteLocatarios\", jcacheConfiguration);\r\n" +
//		"            cm.createCache(it.arancia.domain.Cliente.class.getName() + \".incaricoSegnalatores\", jcacheConfiguration);\r\n" +
//		"            cm.createCache(it.arancia.domain.Immobile.class.getName(), jcacheConfiguration);\r\n" +
//		"            cm.createCache(it.arancia.domain.Immobile.class.getName() + \".files\", jcacheConfiguration);\r\n" +
//		"            cm.createCache(it.arancia.domain.Immobile.class.getName() + \".incaricos\", jcacheConfiguration);\r\n" +
//		"            cm.createCache(it.arancia.domain.Files.class.getName(), jcacheConfiguration);\r\n" +
//		"            cm.createCache(it.arancia.domain.Partner.class.getName(), jcacheConfiguration);\r\n" +
//		"            cm.createCache(it.arancia.domain.Partner.class.getName() + \".incaricos\", jcacheConfiguration);\r\n" +
//		"            cm.createCache(it.arancia.domain.Incarico.class.getName(), jcacheConfiguration);\r\n" +
//		"            cm.createCache(it.arancia.domain.Incarico.class.getName() + \".listaContattis\", jcacheConfiguration);\r\n" +
//		"            cm.createCache(it.arancia.domain.Incarico.class.getName() + \".partners\", jcacheConfiguration);\r\n" +
//		"            cm.createCache(it.arancia.domain.Incarico.class.getName() + \".committentes\", jcacheConfiguration);\r\n" +
//		"            cm.createCache(it.arancia.domain.Incarico.class.getName() + \".proponentes\", jcacheConfiguration);\r\n" +
//		"            cm.createCache(it.arancia.domain.Incarico.class.getName() + \".acquirenteLocatarios\", jcacheConfiguration);\r\n" +
//		"            cm.createCache(it.arancia.domain.Incarico.class.getName() + \".segnalatores\", jcacheConfiguration);\r\n" +
//		"            cm.createCache(it.arancia.domain.Notifiche.class.getName(), jcacheConfiguration);\r\n" +
//		"            cm.createCache(it.arancia.domain.ListaContatti.class.getName(), jcacheConfiguration);\r\n" +
//		"            cm.createCache(it.arancia.domain.Tag.class.getName(), jcacheConfiguration);\r\n" +
//		"            cm.createCache(it.arancia.domain.Geolocalizzazione.class.getName(), jcacheConfiguration);\r\n" +
//		"            cm.createCache(it.arancia.domain.MailTemplate.class.getName(), jcacheConfiguration);\r\n" +
//		"            cm.createCache(it.arancia.domain.AppSettings.class.getName(), jcacheConfiguration);\r\n" +
//		"            cm.createCache(it.arancia.domain.Professione.class.getName(), jcacheConfiguration);\r\n" +
		
		body +=
		"        };\r\n" +
		"    }\r\n" +
		"}\r\n";
		
		return body;
	}

	public String getClassName(){
		return "CacheConfiguration";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcConfigFolder(),".","/");
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/java";
	}

}
