package it.eng.generate.project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;

public class TemplatePom {
	protected DataBase database;

	public TemplatePom(DataBase database) {
		this.database = database;
	}

	public void generateTemplate() throws IOException{
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String root = conf.getPathname();
		String projectName = conf.getProjectName();
		File f = new File(root + "/"+projectName+"/");
		if(!f.exists()) {
			f.mkdirs();
		}
		f = new File(f.getAbsolutePath()+"/"+getClassName()+"."+getTypeFile());
		FileWriter fw = new FileWriter(f);
		fw.write(getBody());
		fw.close();
	}

	public String getTypeFile() {
		return "xml";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		boolean IS_ORACLE = conf.isOracle();
		String body = 
		"<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd\">" +
		"\n    <modelVersion>4.0.0</modelVersion>" +
		"\n    <groupId>"+conf.getPackageclass()+"</groupId>" +
		"\n    <artifactId>"+conf.getProjectName()+"</artifactId>" +
		"\n    <version>0.0.1-SNAPSHOT</version>" +
		"\n    <packaging>war</packaging>" +
		"\n    <name>"+conf.getProjectName()+"</name>" +
		
		
		"\n    <repositories>" ;
		if (IS_ORACLE) {
		body +=
		"\n        <!-- More information at https://blogs.oracle.com/dev2dev/entry/how_to_get_oracle_jdbc -->" +
		"\n        <repository>" +
		"\n            <id>maven.oracle.com</id>" +
		"\n            <name>oracle-maven-repo</name>" +
		"\n            <url>https://maven.oracle.com</url>" +
		"\n            <layout>default</layout>" +
		"\n            <releases>" +
		"\n                <enabled>true</enabled>" +
		"\n                <updatePolicy>always</updatePolicy>" +
		"\n            </releases>" +
		"\n        </repository>";
		}
		body +=
		//"\n    <!-- jh-needle-maven-repository -->" +
		"\n    </repositories>" ;
		
		body +=
		//"\n    <!-- jh-needle-distribution-management -->" +
		"\n    <properties>" +
		"\n        <!-- Build properties -->" +
		"\n        <maven.version>3.0.0</maven.version>" +
		"\n        <java.version>1.8</java.version>" +
		"\n        <scala.version>2.12.6</scala.version>" +
		"\n        <node.version>v10.13.0</node.version>" +
		"\n        <npm.version>6.4.1</npm.version>" +
		"\n        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>" +
		"\n        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>" +
		"\n        <project.testresult.directory>${project.build.directory}/test-results</project.testresult.directory>" +
		"\n        <maven.build.timestamp.format>yyyyMMddHHmmss</maven.build.timestamp.format>" +
		"\n        <maven.compiler.source>${java.version}</maven.compiler.source>" +
		"\n        <maven.compiler.target>${java.version}</maven.compiler.target>" +
		"\n        <argLine>-Djava.security.egd=file:/dev/./urandom -Xmx256m</argLine>" +
		"\n        <m2e.apt.activation>jdt_apt</m2e.apt.activation>" +
		"\n        <run.addResources>false</run.addResources>" +
		"\n        <!-- These remain empty unless the corresponding profile is active -->" +
		"\n        <profile.no-liquibase />" +
		"\n        <profile.swagger />" +
		"\n        <profile.tls />" +
		"\n        <!-- Dependency versions -->" +
		"\n        <jhipster-dependencies.version>2.0.28</jhipster-dependencies.version>" +
		"\n        <!-- The spring-boot version should match the one managed by" +
		"\n        https://mvnrepository.com/artifact/io.github.jhipster/jhipster-dependencies/${jhipster-dependencies.version} -->" +
		"\n        <spring-boot.version>2.0.6.RELEASE</spring-boot.version>" +
		"\n        <!-- The hibernate version should match the one managed by" +
		"\n        https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-dependencies/${spring-boot.version} -->" +
		"\n        <hibernate.version>5.2.17.Final</hibernate.version>" +
		"\n        <!-- The javassist version should match the one managed by" +
		"\n        https://mvnrepository.com/artifact/org.hibernate/hibernate-core/${hibernate.version} -->" +
		"\n        <javassist.version>3.22.0-GA</javassist.version>" +
		"\n        <!-- The liquibase version should match the one managed by" +
		"\n        https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-dependencies/${spring-boot.version} -->" +
		"\n        <liquibase.version>3.5.5</liquibase.version>" +
		"\n        <liquibase-hibernate5.version>3.6</liquibase-hibernate5.version>" +
		"\n        <validation-api.version>2.0.1.Final</validation-api.version>" +
		"\n        <mapstruct.version>1.2.0.Final</mapstruct.version>" +
		"\n        <!-- Plugin versions -->" +
		"\n        <maven-clean-plugin.version>3.1.0</maven-clean-plugin.version>" +
		"\n        <maven-compiler-plugin.version>3.8.0</maven-compiler-plugin.version>" +
		"\n        <maven-eclipse-plugin.version>2.10</maven-eclipse-plugin.version>" +
		"\n        <maven-enforcer-plugin.version>3.0.0-M2</maven-enforcer-plugin.version>" +
		"\n        <maven-resources-plugin.version>3.1.0</maven-resources-plugin.version>" +
		"\n        <maven-surefire-plugin.version>2.22.1</maven-surefire-plugin.version>" +
		"\n        <maven-war-plugin.version>3.2.2</maven-war-plugin.version>" +
		"\n        <jib-maven-plugin.version>0.9.11</jib-maven-plugin.version>" +
		"\n        <frontend-maven-plugin.version>1.6</frontend-maven-plugin.version>" +
		"\n        <jacoco-maven-plugin.version>0.8.2</jacoco-maven-plugin.version>" +
		"\n        <lifecycle-mapping.version>1.0.0</lifecycle-mapping.version>" +
		"\n        <scala-maven-plugin.version>3.4.2</scala-maven-plugin.version>" +
		"\n        <sonar-maven-plugin.version>3.5.0.1254</sonar-maven-plugin.version>" +
		"\n        <git-commit-id-plugin.version>2.2.5</git-commit-id-plugin.version>" +
		"\n        <!-- Sonar properties -->" +
		"\n        <sonar.host.url>http://localhost:9001</sonar.host.url>" +
		"\n        <sonar.exclusions>src/main/webapp/content/**/*.*, src/main/webapp/i18n/*.js, target/www/**/*.*</sonar.exclusions>" +
		"\n        <sonar.issue.ignore.multicriteria>S3437,S4684,UndocumentedApi,BoldAndItalicTagsCheck</sonar.issue.ignore.multicriteria>" +
		"\n        <!-- Rule https://sonarcloud.io/coding_rules?open=Web%3ABoldAndItalicTagsCheck&rule_key=Web%3ABoldAndItalicTagsCheck is ignored. Even if we agree that using the \"i\" tag is an awful practice, this is what is recommended by http://fontawesome.io/examples/ -->" +
		"\n        <sonar.issue.ignore.multicriteria.BoldAndItalicTagsCheck.resourceKey>src/main/webapp/app/**/*.*</sonar.issue.ignore.multicriteria.BoldAndItalicTagsCheck.resourceKey>" +
		"\n        <sonar.issue.ignore.multicriteria.BoldAndItalicTagsCheck.ruleKey>Web:BoldAndItalicTagsCheck</sonar.issue.ignore.multicriteria.BoldAndItalicTagsCheck.ruleKey>" +
		"\n        <!-- Rule https://sonarcloud.io/coding_rules?open=squid%3AS3437&rule_key=squid%3AS3437 is ignored, as a JPA-managed field cannot be transient -->" +
		"\n        <sonar.issue.ignore.multicriteria.S3437.resourceKey>src/main/java/**/*</sonar.issue.ignore.multicriteria.S3437.resourceKey>" +
		"\n        <sonar.issue.ignore.multicriteria.S3437.ruleKey>squid:S3437</sonar.issue.ignore.multicriteria.S3437.ruleKey>" +
		"\n        <!-- Rule https://sonarcloud.io/coding_rules?open=squid%3AUndocumentedApi&rule_key=squid%3AUndocumentedApi is ignored, as we want to follow \"clean code\" guidelines and classes, methods and arguments names should be self-explanatory -->" +
		"\n        <sonar.issue.ignore.multicriteria.UndocumentedApi.resourceKey>src/main/java/**/*</sonar.issue.ignore.multicriteria.UndocumentedApi.resourceKey>" +
		"\n        <sonar.issue.ignore.multicriteria.UndocumentedApi.ruleKey>squid:UndocumentedApi</sonar.issue.ignore.multicriteria.UndocumentedApi.ruleKey>" +
		"\n        <!-- Rule https://sonarcloud.io/coding_rules?open=squid%3AS4684&rule_key=squid%3AS4684 -->" +
		"\n        <sonar.issue.ignore.multicriteria.S4684.resourceKey>src/main/java/**/*</sonar.issue.ignore.multicriteria.S4684.resourceKey>" +
		"\n        <sonar.issue.ignore.multicriteria.S4684.ruleKey>squid:S4684</sonar.issue.ignore.multicriteria.S4684.ruleKey>" +
		"\n        <sonar.jacoco.reportPaths>${project.testresult.directory}/coverage/jacoco/jacoco.exec</sonar.jacoco.reportPaths>" +
		"\n        <sonar.java.codeCoveragePlugin>jacoco</sonar.java.codeCoveragePlugin>" +
		"\n        <sonar.testExecutionReportPaths>${project.testresult.directory}/jest/TESTS-results-sonar.xml</sonar.testExecutionReportPaths>" +
		"\n        <sonar.typescript.lcov.reportPaths>${project.testresult.directory}/lcov.info</sonar.typescript.lcov.reportPaths>" +
		"\n        <sonar.sources>${project.basedir}/src/main/</sonar.sources>" +
		"\n        <sonar.surefire.reportsPath>${project.testresult.directory}/surefire-reports</sonar.surefire.reportsPath>" +
		"\n        <sonar.tests>${project.basedir}/src/test/</sonar.tests>" +
		"\n        <!-- jh-needle-maven-property -->" +
		

		//AZURE PLUGIN
		"\n" + 
		"\n        <!-- Properties di configurazione per il plugin azure-webapp -->" + 
		"\n		<!-- " + 
		"\n        <azure-webapp-maven-plugin.version>1.8.0</azure-webapp-maven-plugin.version>" + 
		"\n        <AZURE_AUTH>azure-auth</AZURE_AUTH>" + 
		"\n        <RESOURCEGROUP_NAME>Exprivia-DFAI-DM-GestioneTrasferte</RESOURCEGROUP_NAME>" + 
		"\n        <WEBAPP_NAME>gestione-trasferte</WEBAPP_NAME>" + 
		"\n        <start-class>it.exprivia.trasferte.TrasferteappApp</start-class>" + 
		"\n		-->" + 
		//
		
		
		"\n\n    </properties>" +
		"\n    <dependencyManagement>" +
		"\n        <dependencies>" +
		"\n            <dependency>" +
		"\n                <groupId>io.github.jhipster</groupId>" +
		"\n                <artifactId>jhipster-dependencies</artifactId>" +
		"\n                <version>${jhipster-dependencies.version}</version>" +
		"\n                <type>pom</type>" +
		"\n                <scope>import</scope>" +
		"\n            </dependency>" +
		"\n          <!-- jh-needle-maven-add-dependency-management -->" +
		"\n        </dependencies>" +
		"\n    </dependencyManagement>" +
		"\n    <dependencies>" +
		"\n        <dependency>" +
		"\n            <groupId>io.github.jhipster</groupId>" +
		"\n            <artifactId>jhipster-framework</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>org.springframework.boot</groupId>" +
		"\n            <artifactId>spring-boot-starter-cache</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>io.dropwizard.metrics</groupId>" +
		"\n            <artifactId>metrics-core</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>io.dropwizard.metrics</groupId>" +
		"\n            <artifactId>metrics-annotation</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>io.dropwizard.metrics</groupId>" +
		"\n            <artifactId>metrics-json</artifactId>" +
		"\n        </dependency>" +
		"\n		  <!-- https://mvnrepository.com/artifact/net.sf.jasperreports/jasperreports -->" +
		"\n        <dependency>" +
		"\n		      <groupId>net.sf.jasperreports</groupId>" +
		"\n		      <artifactId>jasperreports</artifactId>" +
		"\n		      <version>6.7.0</version>" +
		"\n		      <exclusions>" +
		"\n                <exclusion>" +
		"\n                    <groupId>com.lowagie</groupId>" +
		"\n                    <artifactId>itext</artifactId>" +
		"\n                </exclusion>" +
		"\n	          </exclusions>" +
		"\n		   </dependency>" +
		"\n		   <!-- https://mvnrepository.com/artifact/com.lowagie/itext -->" +
		"\n		   <dependency>" +
		"\n		       <groupId>com.lowagie</groupId>" +
		"\n		       <artifactId>itext</artifactId>" +
		"\n		       <version>2.1.7</version>" +
		"\n		   </dependency>"+
		"\n        <dependency>" +
		"\n            <groupId>io.prometheus</groupId>" +
		"\n            <artifactId>simpleclient</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>io.prometheus</groupId>" +
		"\n            <artifactId>simpleclient_dropwizard</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>io.prometheus</groupId>" +
		"\n            <artifactId>simpleclient_servlet</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>io.dropwizard.metrics</groupId>" +
		"\n            <artifactId>metrics-jcache</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>io.dropwizard.metrics</groupId>" +
		"\n            <artifactId>metrics-jvm</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>io.dropwizard.metrics</groupId>" +
		"\n            <artifactId>metrics-servlet</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>io.dropwizard.metrics</groupId>" +
		"\n            <artifactId>metrics-servlets</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>com.fasterxml.jackson.datatype</groupId>" +
		"\n            <artifactId>jackson-datatype-hibernate5</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>com.fasterxml.jackson.datatype</groupId>" +
		"\n            <artifactId>jackson-datatype-hppc</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>com.fasterxml.jackson.datatype</groupId>" +
		"\n            <artifactId>jackson-datatype-jsr310</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>com.fasterxml.jackson.module</groupId>" +
		"\n            <artifactId>jackson-module-afterburner</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>com.h2database</groupId>" +
		"\n            <artifactId>h2</artifactId>" +
		"\n            <scope>test</scope>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>com.jayway.jsonpath</groupId>" +
		"\n            <artifactId>json-path</artifactId>" +
		"\n            <scope>test</scope>" +
		"\n            <!-- parent POM declares this dependency in default (compile) scope -->" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>io.springfox</groupId>" +
		"\n            <artifactId>springfox-swagger2</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>io.springfox</groupId>" +
		"\n            <artifactId>springfox-bean-validators</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>com.mattbertolini</groupId>" +
		"\n            <artifactId>liquibase-slf4j</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>com.ryantenney.metrics</groupId>" +
		"\n            <artifactId>metrics-spring</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>com.zaxxer</groupId>" +
		"\n            <artifactId>HikariCP</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>commons-io</groupId>" +
		"\n            <artifactId>commons-io</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>org.apache.commons</groupId>" +
		"\n            <artifactId>commons-lang3</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>javax.cache</groupId>" +
		"\n            <artifactId>cache-api</artifactId>" +
		"\n        </dependency>";
		
		if (IS_ORACLE) {
		body +=
		"\n        <!-- More information at https://blogs.oracle.com/dev2dev/entry/how_to_get_oracle_jdbc -->" +
		"\n		   <!-- https://mvnrepository.com/artifact/com.oracle/ojdbc6 -->" +
		"\n		   <dependency>" +
		"\n		       <groupId>com.oracle</groupId>" +
		"\n		       <artifactId>ojdbc6</artifactId>" +
		"\n		       <version>11.2.0.4.0-atlassian-hosted</version>" +
		"\n		   </dependency>";
		}
		
		body +=
		"\n        <dependency>" +
		"\n            <groupId>mysql</groupId>" +
		"\n            <artifactId>mysql-connector-java</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>org.assertj</groupId>" +
		"\n            <artifactId>assertj-core</artifactId>" +
		"\n            <scope>test</scope>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>org.ehcache</groupId>" +
		"\n            <artifactId>ehcache</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>org.hibernate</groupId>" +
		"\n            <artifactId>hibernate-jcache</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>org.hibernate</groupId>" +
		"\n            <artifactId>hibernate-jpamodelgen</artifactId>" +
		"\n            <scope>provided</scope>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>org.hibernate</groupId>" +
		"\n            <artifactId>hibernate-envers</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>org.hibernate.validator</groupId>" +
		"\n            <artifactId>hibernate-validator</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>org.liquibase</groupId>" +
		"\n            <artifactId>liquibase-core</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>net.logstash.logback</groupId>" +
		"\n            <artifactId>logstash-logback-encoder</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>org.mapstruct</groupId>" +
		"\n            <artifactId>mapstruct-jdk8</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>org.mapstruct</groupId>" +
		"\n            <artifactId>mapstruct-processor</artifactId>" +
		"\n            <scope>provided</scope>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>org.springframework.boot</groupId>" +
		"\n            <artifactId>spring-boot-configuration-processor</artifactId>" +
		"\n            <scope>provided</scope>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>org.springframework.boot</groupId>" +
		"\n            <artifactId>spring-boot-loader-tools</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>org.springframework.boot</groupId>" +
		"\n            <artifactId>spring-boot-starter-actuator</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>org.springframework.boot</groupId>" +
		"\n            <artifactId>spring-boot-starter-aop</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>org.springframework.boot</groupId>" +
		"\n            <artifactId>spring-boot-starter-data-jpa</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>org.springframework.boot</groupId>" +
		"\n            <artifactId>spring-boot-starter-logging</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>org.springframework.boot</groupId>" +
		"\n            <artifactId>spring-boot-starter-mail</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>org.springframework.boot</groupId>" +
		"\n            <artifactId>spring-boot-starter-security</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>org.springframework.boot</groupId>" +
		"\n            <artifactId>spring-boot-starter-thymeleaf</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>org.springframework.boot</groupId>" +
		"\n            <artifactId>spring-boot-starter-web</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>org.springframework.boot</groupId>" +
		"\n            <artifactId>spring-boot-starter-test</artifactId>" +
		"\n            <scope>test</scope>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>org.springframework.boot</groupId>" +
		"\n            <artifactId>spring-boot-test</artifactId>" +
		"\n            <scope>test</scope>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>org.springframework.security</groupId>" +
		"\n            <artifactId>spring-security-test</artifactId>" +
		"\n            <scope>test</scope>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>org.zalando</groupId>" +
		"\n            <artifactId>problem-spring-web</artifactId>" +
		"\n        </dependency>" +
		
		// Security JWT 
		"\n" +
		"\n        <!-- Spring Security JWT -->" +
		"\n        <dependency>" +
		"\n            <groupId>io.jsonwebtoken</groupId>" +
		"\n            <artifactId>jjwt-api</artifactId>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>io.jsonwebtoken</groupId>" +
		"\n            <artifactId>jjwt-impl</artifactId>" +
		"\n            <scope>runtime</scope>" +
		"\n        </dependency>" +
		"\n        <dependency>" +
		"\n            <groupId>io.jsonwebtoken</groupId>" +
		"\n            <artifactId>jjwt-jackson</artifactId>" +
		"\n            <scope>runtime</scope>" +
		"\n        </dependency>"+
		"\n" +
		
		"\n        <!-- Spring Cloud -->" +
		"\n        <dependency>" +
		"\n            <groupId>org.springframework.boot</groupId>" +
		"\n            <artifactId>spring-boot-starter-cloud-connectors</artifactId>" +
		"\n        </dependency>" +
		"\n        <!-- Security -->" +
		"\n        <dependency>" +
		"\n            <groupId>org.springframework.security</groupId>" +
		"\n            <artifactId>spring-security-data</artifactId>" +
		"\n        </dependency>" +

		//Dynamic Jasper 1 
		"\n 		<!-- Dynamic Jasper 1-->" +
		"\n			<dependency>" +
		"\n		  		<groupId>ar.com.fdvs</groupId>" +
		"\n		  		<artifactId>DynamicJasper</artifactId>" +
		"\n		  		<version>5.1.2</version>" +
		"\n			</dependency>" +
		
		//Dynamic Jasper 2 START
		"\n 		<!-- Dynamic Jasper 2-->" +
		"\n			<dependency>" +
		"\n		  		<groupId>net.sourceforge.dynamicreports</groupId>" +
		"\n		  		<artifactId>dynamicreports-core</artifactId>" +
		"\n		  		<version>6.1.0</version>" +
		"\n			</dependency>" +
		"\n 		<!-- https://mvnrepository.com/artifact/net.sf.jasperreports/jasperreports -->" +
		"\n			<dependency>" +
		"\n		  		<groupId>net.sf.jasperreports</groupId>" +
		"\n		  		<artifactId>jasperreports</artifactId>" +
		"\n		  		<version>6.9.0</version>" +
		"\n			</dependency>" +
		"\n 		<!-- https://mvnrepository.com/artifact/org.apache.poi/poi -->" +
		"\n			<dependency>" +
		"\n		  		<groupId>org.apache.poi</groupId>" +
		"\n		  		<artifactId>poi</artifactId>" +
		"\n		  		<version>3.17</version>" +
		"\n			</dependency>" +
		//Dynamic Jasper 2 END
		
		
		
		"\n        <!-- jh-needle-maven-add-dependency -->" +
		"\n    </dependencies>" +
		"\n    <build>" +
		"\n        <defaultGoal>spring-boot:run</defaultGoal>" +
		"\n        <plugins>" +
		"\n            <plugin>" +
		"\n                <groupId>org.apache.maven.plugins</groupId>" +
		"\n                <artifactId>maven-compiler-plugin</artifactId>" +
		"\n                <configuration>" +
		"\n                    <annotationProcessorPaths>" +
		"\n                        <path>" +
		"\n                            <groupId>org.mapstruct</groupId>" +
		"\n                            <artifactId>mapstruct-processor</artifactId>" +
		"\n                            <version>${mapstruct.version}</version>" +
		"\n                        </path>" +
		"\n                        <!-- For JPA static metamodel generation -->" +
		"\n                        <path>" +
		"\n                            <groupId>org.hibernate</groupId>" +
		"\n                            <artifactId>hibernate-jpamodelgen</artifactId>" +
		"\n                            <version>${hibernate.version}</version>" +
		"\n                        </path>" +
		"\n                    </annotationProcessorPaths>" +
		"\n                </configuration>" +
		"\n            </plugin>" +
		"\n            <plugin>" +
		"\n                <groupId>org.apache.maven.plugins</groupId>" +
		"\n                <artifactId>maven-eclipse-plugin</artifactId>" +
		"\n            </plugin>" +
		"\n            <plugin>" +
		"\n                <groupId>org.apache.maven.plugins</groupId>" +
		"\n                <artifactId>maven-enforcer-plugin</artifactId>" +
		"\n            </plugin>" +
		"\n            <plugin>" +
		"\n                <groupId>org.apache.maven.plugins</groupId>" +
		"\n                <artifactId>maven-resources-plugin</artifactId>" +
		"\n            </plugin>" +
		"\n            <plugin>" +
		"\n                <groupId>org.apache.maven.plugins</groupId>" +
		"\n                <artifactId>maven-surefire-plugin</artifactId>" +
		"\n            </plugin>" +
		"\n            <plugin>" +
		"\n                <groupId>org.jacoco</groupId>" +
		"\n                <artifactId>jacoco-maven-plugin</artifactId>" +
		"\n            </plugin>" +
		"\n            <plugin>" +
		"\n                <groupId>org.sonarsource.scanner.maven</groupId>" +
		"\n                <artifactId>sonar-maven-plugin</artifactId>" +
		"\n            </plugin>" +
		"\n            <plugin>" +
		"\n                <groupId>org.liquibase</groupId>" +
		"\n                <artifactId>liquibase-maven-plugin</artifactId>" +
		"\n            </plugin>" +
		"\n            <plugin>" +
		"\n                <groupId>org.springframework.boot</groupId>" +
		"\n                <artifactId>spring-boot-maven-plugin</artifactId>" +
		"\n                <executions>" +
		"\n                    <execution>" +
		"\n                        <goals>" +
		"\n                            <goal>repackage</goal>" +
		"\n                        </goals>" +
		"\n                    </execution>" +
		"\n                </executions>" +
		"\n                <configuration>" +
		"\n                    <mainClass>${start-class}</mainClass>" +
		
		//AZURE
		"\n                    <!-- COMMENT THIS FOR AZURE - START-->" +
		"\n                    <executable>true</executable>" +
		"\n                    <!-- COMMENT THIS FOR AZURE - END -->" +
		//
		
		"\n                    <fork>true</fork>" +
		"\n                    <!--" +
		"\n                    Enable the line below to have remote debugging of your application on port 5005" +
		"\n                    <jvmArguments>-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005</jvmArguments>" +
		"\n                    -->" +
		"\n                </configuration>" +
		"\n            </plugin>" +
		"\n            <plugin>" +
		"\n                <groupId>com.google.cloud.tools</groupId>" +
		"\n                <artifactId>jib-maven-plugin</artifactId>" +
		"\n            </plugin>" +
		"\n            <!-- jh-needle-maven-add-plugin -->" +
		"\n        </plugins>" +
		"\n        <pluginManagement>" +
		"\n            <plugins>" +
		"\n                <plugin>" +
		"\n                    <groupId>org.apache.maven.plugins</groupId>" +
		"\n                    <artifactId>maven-compiler-plugin</artifactId>" +
		"\n                    <version>${maven-compiler-plugin.version}</version>" +
		"\n                </plugin>" +
		"\n                <plugin>" +
		"\n                    <groupId>com.github.eirslett</groupId>" +
		"\n                    <artifactId>frontend-maven-plugin</artifactId>" +
		"\n                    <version>${frontend-maven-plugin.version}</version>" +
		"\n                </plugin>" +
		"\n                <plugin>" +
		"\n                    <groupId>pl.project13.maven</groupId>" +
		"\n                    <artifactId>git-commit-id-plugin</artifactId>" +
		"\n                    <version>${git-commit-id-plugin.version}</version>" +
		"\n                        <executions>" +
		"\n                            <execution>" +
		"\n                                <goals>" +
		"\n                                    <goal>revision</goal>" +
		"\n                                </goals>" +
		"\n                            </execution>" +
		"\n                        </executions>" +
		"\n                        <configuration>" +
		"\n                            <failOnNoGitDirectory>false</failOnNoGitDirectory>" +
		"\n                            <generateGitPropertiesFile>true</generateGitPropertiesFile>" +
		"\n                            <includeOnlyProperties>" +
		"\n                                <includeOnlyProperty>^git.commit.id.abbrev$</includeOnlyProperty>" +
		"\n                                <includeOnlyProperty>^git.commit.id.describe$</includeOnlyProperty>" +
		"\n                                <includeOnlyProperty>^git.branch$</includeOnlyProperty>" +
		"\n                            </includeOnlyProperties>" +
		"\n                    </configuration>" +
		"\n                </plugin>" +
		"\n                <plugin>" +
		"\n                    <groupId>org.jacoco</groupId>" +
		"\n                    <artifactId>jacoco-maven-plugin</artifactId>" +
		"\n                    <version>${jacoco-maven-plugin.version}</version>" +
		"\n                    <executions>" +
		"\n                        <execution>" +
		"\n                            <id>pre-unit-tests</id>" +
		"\n                            <goals>" +
		"\n                                <goal>prepare-agent</goal>" +
		"\n                            </goals>" +
		"\n                            <configuration>" +
		"\n                                <!-- Sets the path to the file which contains the execution data. -->" +
		"\n                                <destFile>${project.testresult.directory}/coverage/jacoco/jacoco.exec</destFile>" +
		"\n                            </configuration>" +
		"\n                        </execution>" +
		"\n                        <!-- Ensures that the code coverage report for unit tests is created after unit tests have been run -->" +
		"\n                        <execution>" +
		"\n                            <id>post-unit-test</id>" +
		"\n                            <phase>test</phase>" +
		"\n                            <goals>" +
		"\n                                <goal>report</goal>" +
		"\n                            </goals>" +
		"\n                            <configuration>" +
		"\n                                <dataFile>${project.testresult.directory}/coverage/jacoco/jacoco.exec</dataFile>" +
		"\n                                <outputDirectory>${project.testresult.directory}/coverage/jacoco</outputDirectory>" +
		"\n                            </configuration>" +
		"\n                        </execution>" +
		"\n                    </executions>" +
		"\n                </plugin>" +
		"\n                <plugin>" +
		"\n                    <groupId>com.google.cloud.tools</groupId>" +
		"\n                    <artifactId>jib-maven-plugin</artifactId>" +
		"\n                    <version>${jib-maven-plugin.version}</version>" +
		"\n                    <configuration>" +
		"\n                      <from>" +
		"\n                          <image>openjdk:8-jre-alpine</image>" +
		"\n                      </from>" +
		"\n                      <to>" +
		"\n                          <image>"+conf.getProjectName()+":latest</image>" +
		"\n                      </to>" +
		"\n                      <container>" +
		"\n                          <entrypoint>" +
		"\n                              <shell>sh</shell>" +
		"\n                              <option>-c</option>" +
		"\n                              <arg>chmod +x /entrypoint.sh &amp;&amp; sync &amp;&amp; /entrypoint.sh</arg>" +
		"\n                          </entrypoint>" +
		"\n                          <ports>" +
		"\n                              <port>8080</port>" +
		"\n                          </ports>" +
		"\n                          <environment>" +
		"\n                              <SPRING_OUTPUT_ANSI_ENABLED>ALWAYS</SPRING_OUTPUT_ANSI_ENABLED>" +
		"\n                              <JHIPSTER_SLEEP>0</JHIPSTER_SLEEP>" +
		"\n                          </environment>" +
		"\n                          <useCurrentTimestamp>true</useCurrentTimestamp>" +
		"\n                      </container>" +
		"\n                  </configuration>" +
		"\n                </plugin>" +
		"\n                <!--" +
		"\n                    This plugin's configuration is used to store Eclipse m2e settings only." +
		"\n                    It has no influence on the Maven build itself." +
		"\n                    Remove when the m2e plugin can correctly bind to Maven lifecycle" +
		"\n                -->" +
		"\n                <plugin>" +
		"\n                    <groupId>org.eclipse.m2e</groupId>" +
		"\n                    <artifactId>lifecycle-mapping</artifactId>" +
		"\n                    <version>${lifecycle-mapping.version}</version>" +
		"\n                    <configuration>" +
		"\n                        <lifecycleMappingMetadata>" +
		"\n                            <pluginExecutions>" +
		"\n                                <pluginExecution>" +
		"\n                                    <pluginExecutionFilter>" +
		"\n                                        <groupId>org.jacoco</groupId>" +
		"\n                                        <artifactId>" +
		"\n                                            jacoco-maven-plugin" +
		"\n                                        </artifactId>" +
		"\n                                        <versionRange>" +
		"\n                                            ${jacoco-maven-plugin.version}" +
		"\n                                        </versionRange>" +
		"\n                                        <goals>" +
		"\n                                            <goal>prepare-agent</goal>" +
		"\n                                        </goals>" +
		"\n                                    </pluginExecutionFilter>" +
		"\n                                    <action>" +
		"\n                                        <ignore/>" +
		"\n                                    </action>" +
		"\n                                </pluginExecution>" +
		"\n                                <pluginExecution>" +
		"\n                                    <pluginExecutionFilter>" +
		"\n                                        <groupId>com.github.eirslett</groupId>" +
		"\n                                        <artifactId>frontend-maven-plugin</artifactId>" +
		"\n                                        <versionRange>${frontend-maven-plugin.version}</versionRange>" +
		"\n                                        <goals>" +
		"\n                                            <goal>install-node-and-npm</goal>" +
		"\n                                            <goal>npm</goal>" +
		"\n                                        </goals>" +
		"\n                                    </pluginExecutionFilter>" +
		"\n                                    <action>" +
		"\n                                        <ignore/>" +
		"\n                                    </action>" +
		"\n                                </pluginExecution>" +
		"\n                            </pluginExecutions>" +
		"\n                        </lifecycleMappingMetadata>" +
		"\n                    </configuration>" +
		"\n                </plugin>" +
		"\n                <plugin>" +
		"\n                    <groupId>org.liquibase</groupId>" +
		"\n                    <artifactId>liquibase-maven-plugin</artifactId>" +
		"\n                    <version>${liquibase.version}</version>";
		
		if ( IS_ORACLE  ) {
			//Oracle
			body +=
			"\n 					  <configuration>" +
			"\n                        <changeLogFile>src/main/resources/config/liquibase/master.xml</changeLogFile>" +
			"\n                        <diffChangeLogFile>src/main/resources/config/liquibase/changelog/${maven.build.timestamp}_changelog.xml</diffChangeLogFile>" +
			"\n                        <driver>oracle.jdbc.OracleDriver</driver>" +
			"\n                        <url>jdbc:oracle:thin:@localhost:1521:xe</url>" +
			"\n                        <defaultSchemaName>"+conf.getProjectName()+"</defaultSchemaName>" +
			"\n 					       <username>"+conf.getUsername()+"</username>" +
			"\n					       <password>"+conf.getPassword()+"</password>" +
			"\n                        <referenceUrl>hibernate:spring:"+conf.getPackageclass()+"."+conf.getSrcDomainFolder()+"?dialect=org.hibernate.dialect.Oracle10gDialect&amp;hibernate.physical_naming_strategy=org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy&amp;hibernate.implicit_naming_strategy=org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy</referenceUrl>" +
			"\n                        <verbose>true</verbose>" +
			"\n                        <logging>debug</logging>" +
			"\n                    </configuration>";
		} else {
			//MySQL
			body +=
			"\n                    <configuration>" +
			"\n                        <changeLogFile>src/main/resources/config/liquibase/master.xml</changeLogFile>" +
			"\n                        <diffChangeLogFile>src/main/resources/config/liquibase/changelog/${maven.build.timestamp}_changelog.xml</diffChangeLogFile>" +
			"\n                        <driver>com.mysql.jdbc.Driver</driver>" +
			"\n                        <url>jdbc:mysql://localhost:3306/"+conf.getProjectName()+"</url>" +
			"\n                        <defaultSchemaName>"+conf.getProjectName()+"</defaultSchemaName>" +
			"\n                        <username>"+conf.getUsername()+"</username>" +
			"\n                        <password>"+conf.getPassword()+"</password>" +
			"\n                        <referenceUrl>hibernate:spring:"+conf.getPackageclass()+"."+conf.getSrcDomainFolder()+"?dialect=org.hibernate.dialect.MySQL5InnoDBDialect&amp;hibernate.physical_naming_strategy=org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy&amp;hibernate.implicit_naming_strategy=org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy</referenceUrl>" +
			"\n                        <verbose>true</verbose>" +
			"\n                        <logging>debug</logging>" +
			"\n                    </configuration>" ;
		}
		
		body +=
		"\n                    <dependencies>" +
		"\n                        <dependency>" +
		"\n                            <groupId>org.javassist</groupId>" +
		"\n                            <artifactId>javassist</artifactId>" +
		"\n                            <version>${javassist.version}</version>" +
		"\n                        </dependency>" +
		"\n                        <dependency>" +
		"\n                            <groupId>org.liquibase.ext</groupId>" +
		"\n                            <artifactId>liquibase-hibernate5</artifactId>" +
		"\n                            <version>${liquibase-hibernate5.version}</version>" +
		"\n                        </dependency>" +
		"\n                        <dependency>" +
		"\n                            <groupId>org.springframework.boot</groupId>" +
		"\n                            <artifactId>spring-boot-starter-data-jpa</artifactId>" +
		"\n                            <version>${spring-boot.version}</version>" +
		"\n                        </dependency>" +
		"\n                        <dependency>" +
		"\n                            <groupId>javax.validation</groupId>" +
		"\n                            <artifactId>validation-api</artifactId>" +
		"\n                            <version>${validation-api.version}</version>" +
		"\n                        </dependency>" +
		"\n                    </dependencies>" +
		"\n                </plugin>" +
		"\n                <plugin>" +
		"\n                    <artifactId>maven-clean-plugin</artifactId>" +
		"\n                    <version>${maven-clean-plugin.version}</version>" +
		"\n                </plugin>" +
		"\n                <plugin>" +
		"\n                    <groupId>org.apache.maven.plugins</groupId>" +
		"\n                    <artifactId>maven-eclipse-plugin</artifactId>" +
		"\n                    <version>${maven-eclipse-plugin.version}</version>" +
		"\n                    <configuration>" +
		"\n                        <downloadSources>true</downloadSources>" +
		"\n                        <downloadJavadocs>true</downloadJavadocs>" +
		"\n                    </configuration>" +
		"\n                </plugin>" +
		"\n                <plugin>" +
		"\n                    <groupId>org.apache.maven.plugins</groupId>" +
		"\n                    <artifactId>maven-enforcer-plugin</artifactId>" +
		"\n                    <version>${maven-enforcer-plugin.version}</version>" +
		"\n                    <executions>" +
		"\n                        <execution>" +
		"\n                            <id>enforce-versions</id>" +
		"\n                            <goals>" +
		"\n                                <goal>enforce</goal>" +
		"\n                            </goals>" +
		"\n                        </execution>" +
		"\n                    </executions>" +
		"\n                    <configuration>" +
		"\n                        <rules>" +
		"\n                            <requireMavenVersion>" +
		"\n                                <message>You are running an older version of Maven. JH requires at least Maven ${maven.version}</message>" +
		"\n                                <version>[${maven.version},)</version>" +
		"\n                            </requireMavenVersion>" +
		"\n                            <requireJavaVersion>" +
		"\n                                <!-- Until JH supports JDK 9 -->" +
		"\n                                <message>You are running an incompatible version of Java. JH requires JDK ${java.version}</message>" +
		"\n                                <version>[1.8,1.9)</version>" +
		"\n                            </requireJavaVersion>" +
		"\n                        </rules>" +
		"\n                    </configuration>" +
		"\n                </plugin>" +
		"\n				   <plugin> " +
		"\n                    <groupId>org.apache.maven.plugins</groupId>" +
		"\n                    <artifactId>maven-resources-plugin</artifactId>" +
		"\n                    <version>${maven-resources-plugin.version}</version>" +
		"\n                    <executions>" +
		"\n                        <execution>" +
		"\n                            <id>default-resources</id>" +
		"\n                            <phase>validate</phase>" +
		"\n                            <goals>" +
		"\n                                <goal>copy-resources</goal>" +
		"\n                            </goals>" +
		"\n                            <configuration>" +
		"\n                                <outputDirectory>target/classes</outputDirectory>" +
		"\n                                <useDefaultDelimiters>false</useDefaultDelimiters>" +
		"\n                                <delimiters>" +
		"\n                                    <delimiter>#</delimiter>" +
		"\n                                </delimiters>" +
		"\n                                <resources>" +
		"\n                                    <resource>" +
		"\n                                        <directory>src/main/resources/</directory>" +
		"\n                                        <filtering>true</filtering>" +
		"\n                                        <includes>" +
		"\n                                            <include>config/*.yml</include>" +
		"\n                                        </includes>" +
		"\n                                    </resource>" +
		"\n                                    <resource>" +
		"\n                                        <directory>src/main/resources/</directory>" +
		"\n                                        <filtering>false</filtering>" +
		"\n                                        <excludes>" +
		"\n                                            <exclude>config/*.yml</exclude>" +
		"\n                                        </excludes>" +
		"\n                                    </resource>" +
		"\n                                </resources>" +
		"\n                            </configuration>" +
		"\n                        </execution>" +
		"\n                        <execution>" +
		"\n                            <id>docker-resources</id>" +
		"\n                            <phase>verify</phase>" +
		"\n                            <goals>" +
		"\n                                <goal>copy-resources</goal>" +
		"\n                            </goals>" +
		"\n                            <configuration>" +
		"\n                                <outputDirectory>target/classes/static/</outputDirectory>" +
		"\n                                <resources>" +
		"\n                                    <resource>" +
		"\n                                        <directory>target/www</directory>" +
		"\n                                        <filtering>false</filtering>" +
		"\n                                    </resource>" +
		"\n                                </resources>" +
		"\n                            </configuration>" +
		"\n                        </execution>" +
		"\n                    </executions>" +
		"\n                </plugin>" +
		"\n                <plugin>" +
		"\n                    <groupId>org.apache.maven.plugins</groupId>" +
		"\n                    <artifactId>maven-surefire-plugin</artifactId>" +
		"\n                    <version>${maven-surefire-plugin.version}</version>" +
		"\n                    <configuration>" +
		"\n                        <!-- Force alphabetical order to have a reproducible build -->" +
		"\n                        <runOrder>alphabetical</runOrder>" +
		"\n                        <!-- Fixes a bug which will be solved in next version after 2.22.1 then this can be removed -->" +
		"\n                        <!-- see: https://issues.apache.org/jira/browse/SUREFIRE-1588 -->" +
		"\n                        <useSystemClassLoader>false</useSystemClassLoader>" +
		"\n                    </configuration>" +
		"\n                </plugin>" +
		"\n                <plugin>" +
		"\n                    <groupId>org.apache.maven.plugins</groupId>" +
		"\n                    <artifactId>maven-war-plugin</artifactId>" +
		"\n                    <version>${maven-war-plugin.version}</version>" +
		"\n                </plugin>" +
		"\n                <plugin>" +
		"\n                    <groupId>net.alchim31.maven</groupId>" +
		"\n                    <artifactId>scala-maven-plugin</artifactId>" +
		"\n                    <version>${scala-maven-plugin.version}</version>" +
		"\n                    <executions>" +
		"\n                        <execution>" +
		"\n                            <id>compile</id>" +
		"\n                            <phase>compile</phase>" +
		"\n                            <goals>" +
		"\n                                <goal>add-source</goal>" +
		"\n                                <goal>compile</goal>" +
		"\n                            </goals>" +
		"\n                        </execution>" +
		"\n                        <execution>" +
		"\n                            <id>test-compile</id>" +
		"\n                            <phase>test-compile</phase>" +
		"\n                            <goals>" +
		"\n                                <goal>add-source</goal>" +
		"\n                                <goal>testCompile</goal>" +
		"\n                            </goals>" +
		"\n                        </execution>" +
		"\n                    </executions>" +
		"\n                    <configuration>" +
		"\n                        <recompileMode>incremental</recompileMode>" +
		"\n                        <verbose>true</verbose>" +
		"\n                        <scalaVersion>${scala.version}</scalaVersion>" +
		"\n                    </configuration>" +
		"\n                </plugin>" +
		"\n                <plugin>" +
		"\n                    <groupId>org.sonarsource.scanner.maven</groupId>" +
		"\n                    <artifactId>sonar-maven-plugin</artifactId>" +
		"\n                    <version>${sonar-maven-plugin.version}</version>" +
		"\n                </plugin>" +
		"\n                <plugin>" +
		"\n                    <groupId>org.springframework.boot</groupId>" +
		"\n                    <artifactId>spring-boot-maven-plugin</artifactId>" +
		"\n                    <version>${spring-boot.version}</version>" +
		"\n                </plugin>" +
		"\n            </plugins>" +
		"\n        </pluginManagement>" +
		"\n    </build>" +
		"\n    <profiles>" +
		"\n        <profile>" +
		"\n            <id>no-liquibase</id>" +
		"\n            <properties>" +
		"\n                <profile.no-liquibase>,no-liquibase</profile.no-liquibase>" +
		"\n            </properties>" +
		"\n        </profile>" +
		"\n        <profile>" +
		"\n            <id>swagger</id>" +
		"\n            <properties>" +
		"\n                <profile.swagger>,swagger</profile.swagger>" +
		"\n            </properties>" +
		"\n        </profile>" +
		"\n        <profile>" +
		"\n            <id>tls</id>" +
		"\n            <properties>" +
		"\n                <profile.tls>,tls</profile.tls>" +
		"\n            </properties>" +
		"\n        </profile>" +
		"\n        <profile>" +
		"\n            <id>webpack</id>" +
		"\n            <activation>" +
		"\n                <file>" +
		"\n                    <missing>${basedir}/target/www/app/main.bundle.js</missing>" +
		"\n                </file>" +
		"\n            </activation>" +
		"\n            <dependencies>" +
		"\n                <dependency>" +
		"\n                    <groupId>org.springframework.boot</groupId>" +
		"\n                    <artifactId>spring-boot-starter-undertow</artifactId>" +
		"\n                </dependency>" +
		"\n                <dependency>" +
		"\n                    <groupId>org.springframework.boot</groupId>" +
		"\n                    <artifactId>spring-boot-devtools</artifactId>" +
		"\n                    <optional>true</optional>" +
		"\n                </dependency>" +
		"\n            </dependencies>" +
		"\n            <build>" +
		"\n                <plugins>" +
		"\n                    <plugin>" +
		"\n                        <groupId>com.github.eirslett</groupId>" +
		"\n                        <artifactId>frontend-maven-plugin</artifactId>" +
		"\n                        <executions>" +
		"\n                            <execution>" +
		"\n                                <id>install node and npm</id>" +
		"\n                                <goals>" +
		"\n                                    <goal>install-node-and-npm</goal>" +
		"\n                                </goals>" +
		"\n                                <configuration>" +
		"\n                                    <nodeVersion>${node.version}</nodeVersion>" +
		"\n                                    <npmVersion>${npm.version}</npmVersion>" +
		"\n                                </configuration>" +
		"\n                            </execution>" +
		"\n                            <execution>" +
		"\n                                <id>npm install</id>" +
		"\n                                <goals>" +
		"\n                                    <goal>npm</goal>" +
		"\n                                </goals>" +
		"\n                            </execution>" +
		"\n                            <execution>" +
		"\n                                <id>webpack build dev</id>" +
		"\n                                <goals>" +
		"\n                                    <goal>npm</goal>" +
		"\n                                </goals>" +
		"\n                                <phase>generate-resources</phase>" +
		"\n                                <configuration>" +
		"\n                                    <arguments>run webpack:build</arguments>" +
		"\n                                    <npmInheritsProxyConfigFromMaven>false</npmInheritsProxyConfigFromMaven>" +
		"\n                                </configuration>" +
		"\n                            </execution>" +
		"\n                        </executions>" +
		"\n                    </plugin>" +
		"\n                    <plugin>" +
		"\n                        <groupId>org.apache.maven.plugins</groupId>" +
		"\n                        <artifactId>maven-war-plugin</artifactId>" +
		"\n                        <configuration>" +
		"\n                            <failOnMissingWebXml>false</failOnMissingWebXml>" +
		"\n                            <warSourceDirectory>target/www/</warSourceDirectory>" +
		"\n                            <webResources>" +
		"\n                                <resource>" +
		"\n                                    <directory>src/main/webapp</directory>" +
		"\n                                    <includes>" +
		"\n                                        <include>WEB-INF/**</include>" +
		"\n                                    </includes>" +
		"\n                                </resource>" +
		"\n                            </webResources>" +
		"\n                        </configuration>" +
		"\n                    </plugin>" +
		"\n                </plugins>" +
		"\n            </build>" +
		"\n            <properties>" +
		"\n                <!-- default Spring profiles -->" +
		"\n                <spring.profiles.active>dev${profile.no-liquibase}</spring.profiles.active>" +
		"\n            </properties>" +
		"\n        </profile>" +
		"\n        <profile>" +
		"\n            <id>dev</id>" +
		"\n            <activation>" +
		"\n                <activeByDefault>true</activeByDefault>" +
		"\n            </activation>" +
		"\n            <dependencies>" +
		"\n                <dependency>" +
		"\n                    <groupId>org.springframework.boot</groupId>" +
		"\n                    <artifactId>spring-boot-starter-undertow</artifactId>" +
		"\n                </dependency>" +
		"\n                <dependency>" +
		"\n                    <groupId>org.springframework.boot</groupId>" +
		"\n                    <artifactId>spring-boot-devtools</artifactId>" +
		"\n                    <optional>true</optional>" +
		"\n                </dependency>" +
		"\n            </dependencies>" +
		"\n            <build>" +
		"\n                <plugins>" +
		"\n                    <plugin>" +
		"\n                        <groupId>org.apache.maven.plugins</groupId>" +
		"\n                        <artifactId>maven-war-plugin</artifactId>" +
		"\n                        <configuration>" +
		"\n                            <failOnMissingWebXml>false</failOnMissingWebXml>" +
		"\n                            <warSourceDirectory>target/www/</warSourceDirectory>" +
		"\n                            <webResources>" +
		"\n                                <resource>" +
		"\n                                    <directory>src/main/webapp</directory>" +
		"\n                                    <includes>" +
		"\n                                        <include>WEB-INF/**</include>" +
		"\n                                    </includes>" +
		"\n                                </resource>" +
		"\n                            </webResources>" +
		"\n                        </configuration>" +
		"\n                    </plugin>" +
		"\n                </plugins>" +
		"\n            </build>" +
		"\n            <properties>" +
		"\n                <!-- default Spring profiles -->" +
		"\n                <spring.profiles.active>dev${profile.tls}${profile.no-liquibase}</spring.profiles.active>" +
		"\n            </properties>" +
		"\n        </profile>" +
		"\n        <profile>" +
		"\n            <id>prod</id>" +
		"\n            <dependencies>" +
		"\n                <dependency>" +
		"\n                    <groupId>org.springframework.boot</groupId>" +
		"\n                    <artifactId>spring-boot-starter-undertow</artifactId>" +
		"\n                </dependency>" +
		"\n            </dependencies>" +
		"\n            <build>" +
		"\n                <plugins>" +
		"\n                    <plugin>" +
		"\n                        <artifactId>maven-clean-plugin</artifactId>" +
		"\n                        <configuration>" +
		"\n                            <filesets>" +
		"\n                                <fileset>" +
		"\n                                    <directory>target/www/</directory>" +
		"\n                                </fileset>" +
		"\n                            </filesets>" +
		"\n                        </configuration>" +
		"\n                    </plugin>" +
		"\n                    <plugin>" +
		"\n                        <groupId>org.apache.maven.plugins</groupId>" +
		"\n                        <artifactId>maven-war-plugin</artifactId>" +
		"\n                        <configuration>" +
		"\n                            <failOnMissingWebXml>false</failOnMissingWebXml>" +
		"\n                            <warSourceDirectory>target/www/</warSourceDirectory>" +
		"\n                            <webResources>" +
		"\n                                <resource>" +
		"\n                                    <directory>src/main/webapp</directory>" +
		"\n                                    <includes>" +
		"\n                                        <include>WEB-INF/**</include>" +
		"\n                                    </includes>" +
		"\n                                </resource>" +
		"\n                            </webResources>" +
		"\n                        </configuration>" +
		"\n                    </plugin>" +
		"\n                    <plugin>" +
		"\n                        <groupId>org.springframework.boot</groupId>" +
		"\n                        <artifactId>spring-boot-maven-plugin</artifactId>" +
		"\n                        <configuration>" +
		"\n                            <mainClass>${start-class}</mainClass>" +
		"\n                            <executable>true</executable>" +
		"\n                        </configuration>" +
		"\n                        <executions>" +
		"\n                            <execution>" +
		"\n                                <goals>" +
		"\n                                    <goal>build-info</goal>" +
		"\n                                </goals>" +
		"\n                            </execution>" +
		"\n                        </executions>" +
		"\n                    </plugin>" +
		"\n                    <plugin>" +
		"\n                        <groupId>com.github.eirslett</groupId>" +
		"\n                        <artifactId>frontend-maven-plugin</artifactId>" +
		"\n                        <executions>" +
		"\n                            <execution>" +
		"\n                                <id>install node and npm</id>" +
		"\n                                <goals>" +
		"\n                                    <goal>install-node-and-npm</goal>" +
		"\n                                </goals>" +
		"\n                                <configuration>" +
		"\n                                    <nodeVersion>${node.version}</nodeVersion>" +
		"\n                                    <npmVersion>${npm.version}</npmVersion>" +
		"\n                                </configuration>" +
		"\n                            </execution>" +
		"\n                            <execution>" +
		"\n                                <id>npm install</id>" +
		"\n                                <goals>" +
		"\n                                    <goal>npm</goal>" +
		"\n                                </goals>" +
		"\n                                <configuration>" +
		"\n                                    <arguments>install</arguments>" +
		"\n                                </configuration>" +
		"\n                            </execution>" +
		"\n                            <execution>" +
		"\n                                <id>webpack build test</id>" +
		"\n                                <goals>" +
		"\n                                    <goal>npm</goal>" +
		"\n                                </goals>" +
		"\n                                <phase>test</phase>" +
		"\n                                <configuration>" +
		"\n                                    <arguments>run webpack:test</arguments>" +
		"\n                                    <npmInheritsProxyConfigFromMaven>false</npmInheritsProxyConfigFromMaven>" +
		"\n                                </configuration>" +
		"\n                            </execution>" +
		"\n                            <execution>" +
		"\n                                <id>webpack build prod</id>" +
		"\n                                <goals>" +
		"\n                                    <goal>npm</goal>" +
		"\n                                </goals>" +
		"\n                                <phase>generate-resources</phase>" +
		"\n                                <configuration>" +
		"\n                                    <arguments>run webpack:prod</arguments>" +
		"\n                                    <npmInheritsProxyConfigFromMaven>false</npmInheritsProxyConfigFromMaven>" +
		"\n                                </configuration>" +
		"\n                            </execution>" +
		"\n                        </executions>" +
		"\n                    </plugin>" +
		"\n                    <plugin>" +
		"\n                        <groupId>pl.project13.maven</groupId>" +
		"\n                        <artifactId>git-commit-id-plugin</artifactId>" +
		"\n                    </plugin>" +
		"\n                </plugins>" +
		"\n            </build>" +
		"\n            <properties>" +
		"\n                <!-- default Spring profiles -->" +
		"\n                <spring.profiles.active>prod${profile.swagger}${profile.no-liquibase}</spring.profiles.active>" +
		"\n            </properties>" +
		"\n        </profile>" +
		"\n        <profile>" +
		"\n            <!--" +
		"\n                Profile for doing \"continuous compilation\" with the Scala Maven plugin." +
		"\n                It allows automatic compilation of Java classes as soon as they are saved." +
		"\n                To use it, run in 3 terminals:" +
		"\n                - './mvnw -Pcc scala:cc' for continuous compilation of your classes" +
		"\n                - './mvnw -Pcc -Pdev' for hot reload of Spring boot" +
		"\n                - 'npm start/yarn start' for hot reload of the HTML/JavaScript asset" +
		"\n                Everything should hot reload automatically!" +
		"\n            -->" +
		"\n            <id>cc</id>" +
		"\n            <dependencies>" +
		"\n                <dependency>" +
		"\n                    <groupId>org.springframework.boot</groupId>" +
		"\n                    <artifactId>spring-boot-starter-undertow</artifactId>" +
		"\n                </dependency>" +
		"\n                <dependency>" +
		"\n                    <groupId>org.springframework.boot</groupId>" +
		"\n                    <artifactId>spring-boot-devtools</artifactId>" +
		"\n                    <optional>true</optional>" +
		"\n                </dependency>" +
		"\n            </dependencies>" +
		"\n            <build>" +
		"\n                <plugins>" +
		"\n                    <plugin>" +
		"\n                        <groupId>org.apache.maven.plugins</groupId>" +
		"\n                        <artifactId>maven-war-plugin</artifactId>" +
		"\n                        <configuration>" +
		"\n                            <failOnMissingWebXml>false</failOnMissingWebXml>" +
		"\n                            <warSourceDirectory>src/main/webapp/</warSourceDirectory>" +
		"\n                        </configuration>" +
		"\n                    </plugin>" +
		"\n                    <plugin>" +
		"\n                        <groupId>org.springframework.boot</groupId>" +
		"\n                        <artifactId>spring-boot-maven-plugin</artifactId>" +
		"\n                        <configuration>" +
		"\n                            <mainClass>${start-class}</mainClass>" +
		"\n                            <executable>true</executable>" +
		"\n                            <fork>true</fork>" +
		"\n                            <addResources>true</addResources>" +
		"\n                            <!--" +
		"\n                            Enable the line below to have remote debugging of your application on port 5005" +
		"\n                            <jvmArguments>-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005</jvmArguments>" +
		"\n                            -->" +
		"\n                        </configuration>" +
		"\n                    </plugin>" +
		"\n                    <plugin>" +
		"\n                        <groupId>org.apache.maven.plugins</groupId>" +
		"\n                        <artifactId>maven-compiler-plugin</artifactId>" +
		"\n                        <executions>" +
		"\n                            <execution>" +
		"\n                                <id>default-compile</id>" +
		"\n                                <phase>none</phase>" +
		"\n                            </execution>" +
		"\n                            <execution>" +
		"\n                                <id>default-testCompile</id>" +
		"\n                                <phase>none</phase>" +
		"\n                            </execution>" +
		"\n                        </executions>" +
		"\n                    </plugin>" +
		"\n                    <plugin>" +
		"\n                        <groupId>net.alchim31.maven</groupId>" +
		"\n                        <artifactId>scala-maven-plugin</artifactId>" +
		"\n                    </plugin>" +
		"\n                </plugins>" +
		"\n            </build>" +
		"\n            <properties>" +
		"\n                <!-- default Spring profiles -->" +
		"\n                <spring.profiles.active>dev,swagger</spring.profiles.active>" +
		"\n            </properties>" +
		"\n        </profile>" +
		"\n        <profile>" +
		"\n            <!--" +
		"\n                Profile for applying IDE-specific configuration." +
		"\n                At the moment it configures MapStruct and Hibernate JPA Metamodel Generator, which you need when working" +
		"\n                with DTOs and entity filtering." +
		"\n            -->" +
		"\n            <id>IDE</id>" +
		"\n            <dependencies>" +
		"\n                <dependency>" +
		"\n                    <groupId>org.mapstruct</groupId>" +
		"\n                    <artifactId>mapstruct-processor</artifactId>" +
		"\n                </dependency>" +
		"\n                <dependency>" +
		"\n                    <groupId>org.hibernate</groupId>" +
		"\n                    <artifactId>hibernate-jpamodelgen</artifactId>" +
		"\n                </dependency>" +
		"\n            </dependencies>" +
		"\n        </profile>" +
		"\n        <!-- jh-needle-maven-add-profile -->" +
		"\n    </profiles>" +
		"\n</project>";
		return body;
	}

	public String getClassName(){
		return "pom";
	}

}
