package it.eng.generate.project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;

public class TemplatePackage {

	protected DataBase database;

	public TemplatePackage(DataBase database) {
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
		return "json";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://codebeautify.org/string-builder
		String body = 
		"{\r\n" +
		"  \"name\": \""+conf.getProjectName()+"\",\r\n" +
		"  \"version\": \"0.0.0\",\r\n" +
		"  \"description\": \"Description for "+conf.getProjectName()+"\",\r\n" +
		"  \"private\": true,\r\n" +
		"  \"license\": \"UNLICENSED\",\r\n" +
		"  \"cacheDirectories\": [\r\n" +
		"    \"node_modules\"\r\n" +
		"  ],\r\n" +
		"  \"dependencies\": {\r\n" +
		"    \"@angular/common\": \"7.0.0\",\r\n" +
		"    \"@angular/compiler\": \"7.0.0\",\r\n" +
		"    \"@angular/core\": \"7.0.0\",\r\n" +
		"    \"@angular/forms\": \"7.0.0\",\r\n" +
		"    \"@angular/platform-browser\": \"7.0.0\",\r\n" +
		"    \"@angular/platform-browser-dynamic\": \"7.0.0\",\r\n" +
		"    \"@angular/router\": \"7.0.0\",\r\n" +
		"    \"@fortawesome/angular-fontawesome\": \"0.3.0\",\r\n" +
		"    \"@fortawesome/fontawesome-svg-core\": \"1.2.8\",\r\n" +
		"    \"@fortawesome/free-solid-svg-icons\": \"5.5.0\",\r\n" +
		"    \"@ng-bootstrap/ng-bootstrap\": \"4.0.0\",\r\n" +
		"    \"bootstrap\": \"4.1.3\",\r\n" +
		"    \"core-js\": \"2.5.7\",\r\n" +
		"    \"moment\": \"2.22.2\",\r\n" +
		"    \"ng-diff-match-patch\": \"2.0.6\",\r\n" +
		"    \"ng-jhipster\": \"0.5.6\",\r\n" +
		"    \"ngx-cookie\": \"2.0.1\",\r\n" +
		"    \"ngx-infinite-scroll\": \"6.0.1\",\r\n" +
		"    \"ngx-webstorage\": \"2.0.1\",\r\n" +
		"    \"reflect-metadata\": \"0.1.12\",\r\n" +
		"    \"rxjs\": \"6.3.3\",\r\n" +
		"    \"swagger-ui\": \"2.2.10\",\r\n" +
		"    \"tslib\": \"1.9.3\",\r\n" +
		"    \"zone.js\": \"0.8.26\",\r\n" +
		"    \"@angular/animations\": \"7.0.0\",\r\n" +
		"    \"chart.js\": \"2.7.2\",\r\n" +
		"    \"primeng\": \"6.1.4\"\r\n" +
		"  },\r\n" +
		"  \"devDependencies\": {\r\n" +
		"    \"@angular/cli\": \"7.0.2\",\r\n" +
		"    \"@angular/compiler-cli\": \"7.0.0\",\r\n" +
		"    \"@ngtools/webpack\": \"7.0.2\",\r\n" +
		"    \"@types/chai\": \"4.1.4\",\r\n" +
		"    \"@types/chai-string\": \"1.4.1\",\r\n" +
		"    \"@types/jest\": \"23.3.5\",\r\n" +
		"    \"@types/mocha\": \"5.2.5\",\r\n" +
		"    \"@types/node\": \"9.4.7\",\r\n" +
		"    \"@types/selenium-webdriver\": \"3.0.8\",\r\n" +
		"    \"angular-router-loader\": \"0.8.5\",\r\n" +
		"    \"angular2-template-loader\": \"0.6.2\",\r\n" +
		"    \"autoprefixer\": \"9.2.0\",\r\n" +
		"    \"browser-sync\": \"2.26.3\",\r\n" +
		"    \"browser-sync-webpack-plugin\": \"2.2.2\",\r\n" +
		"    \"cache-loader\": \"1.2.2\",\r\n" +
		"    \"chai\": \"4.1.2\",\r\n" +
		"    \"chai-as-promised\": \"7.1.1\",\r\n" +
		"    \"chai-string\": \"1.5.0\",\r\n" +
		"    \"codelyzer\": \"4.5.0\",\r\n" +
		"    \"copy-webpack-plugin\": \"4.5.1\",\r\n" +
		"    \"css-loader\": \"0.28.10\",\r\n" +
		"    \"file-loader\": \"1.1.11\",\r\n" +
		"    \"fork-ts-checker-webpack-plugin\": \"0.4.10\",\r\n" +
		"    \"friendly-errors-webpack-plugin\": \"1.7.0\",\r\n" +
		"    \"generator-jhipster\": \"5.7.0\",\r\n" +
		"    \"html-loader\": \"0.5.5\",\r\n" +
		"    \"html-webpack-plugin\": \"3.2.0\",\r\n" +
		"    \"husky\": \"1.1.0\",\r\n" +
		"    \"jest\": \"23.6.0\",\r\n" +
		"    \"jest-junit\": \"5.1.0\",\r\n" +
		"    \"jest-preset-angular\": \"6.0.1\",\r\n" +
		"    \"jest-sonar-reporter\": \"2.0.0\",\r\n" +
		"    \"lint-staged\": \"7.3.0\",\r\n" +
		"    \"merge-jsons-webpack-plugin\": \"1.0.14\",\r\n" +
		"    \"mocha\": \"5.2.0\",\r\n" +
		"    \"mini-css-extract-plugin\": \"0.4.2\",\r\n" +
		"    \"moment-locales-webpack-plugin\": \"1.0.5\",\r\n" +
		"    \"optimize-css-assets-webpack-plugin\": \"5.0.1\",\r\n" +
		"    \"prettier\": \"1.14.3\",\r\n" +
		"    \"protractor\": \"5.4.0\",\r\n" +
		"    \"rimraf\": \"2.6.1\",\r\n" +
		"    \"simple-progress-webpack-plugin\": \"1.1.2\",\r\n" +
		"    \"style-loader\": \"0.20.3\",\r\n" +
		"    \"terser-webpack-plugin\": \"1.0.0\",\r\n" +
		"    \"thread-loader\": \"1.1.5\",\r\n" +
		"    \"to-string-loader\": \"1.1.5\",\r\n" +
		"    \"ts-node\": \"5.0.1\",\r\n" +
		"    \"ts-loader\": \"4.5.0\",\r\n" +
		"    \"tslint\": \"5.11.0\",\r\n" +
		"    \"tslint-config-prettier\": \"1.15.0\",\r\n" +
		"    \"tslint-loader\": \"3.6.0\",\r\n" +
		"    \"typescript\": \"3.1.3\",\r\n" +
		"    \"sass\": \"1.13.0\",\r\n" +
		"    \"sass-loader\": \"7.1.0\",\r\n" +
		"    \"postcss-loader\": \"2.1.6\",\r\n" +
		"    \"xml2js\": \"0.4.19\",\r\n" +
		"    \"webpack\": \"4.25.1\",\r\n" +
		"    \"webpack-cli\": \"3.1.2\",\r\n" +
		"    \"webpack-dev-server\": \"3.1.10\",\r\n" +
		"    \"webpack-merge\": \"4.1.4\",\r\n" +
		"    \"webpack-notifier\": \"1.7.0\",\r\n" +
		"    \"webpack-visualizer-plugin\": \"0.1.11\",\r\n" +
		"    \"workbox-webpack-plugin\": \"3.6.3\",\r\n" +
		"    \"write-file-webpack-plugin\": \"4.4.1\"\r\n" +
		"  },\r\n" +
		"  \"engines\": {\r\n" +
		"    \"node\": \">=8.9.0\"\r\n" +
		"  },\r\n" +
		"  \"lint-staged\": {\r\n" +
		"    \"src/**/*.{json,ts,css,scss}\": [\r\n" +
		"      \"prettier --write\",\r\n" +
		"      \"git add\"\r\n" +
		"    ]\r\n" +
		"  },\r\n" +
		"  \"scripts\": {\r\n" +
		"    \"prettier:format\": \"prettier --write \\\"src/**/*.{json,ts,css,scss}\\\"\",\r\n" +
		"    \"lint\": \"tslint --project tsconfig.json -e 'node_modules/**'\",\r\n" +
		"    \"lint:fix\": \"npm run lint -- --fix\",\r\n" +
		"    \"ngc\": \"ngc -p tsconfig-aot.json\",\r\n" +
		"    \"cleanup\": \"rimraf target/{aot,www}\",\r\n" +
		"    \"clean-www\": \"rimraf target//www/app/{src,target/}\",\r\n" +
		"    \"e2e\": \"protractor src/test/javascript/protractor.conf.js\",\r\n" +
		"    \"postinstall\": \"webdriver-manager update --gecko false\",\r\n" +
		"    \"start\": \"npm run webpack:dev\",\r\n" +
		"    \"start-tls\": \"npm run webpack:dev -- --env.tls\",\r\n" +
		"    \"serve\": \"npm run start\",\r\n" +
		"    \"build\": \"npm run webpack:prod\",\r\n" +
		"    \"test\": \"npm run lint && jest --coverage --logHeapUsage -w=2 --config src/test/javascript/jest.conf.js\",\r\n" +
		"    \"test:watch\": \"npm run test -- --watch\",\r\n" +
		"    \"webpack:dev\": \"npm run webpack-dev-server -- --config webpack/webpack.dev.js --inline --hot --port=9060 --watch-content-base --env.stats=minimal\",\r\n" +
		"    \"webpack:dev-verbose\": \"npm run webpack-dev-server -- --config webpack/webpack.dev.js --inline --hot --port=9060 --watch-content-base --profile --progress --env.stats=normal\",\r\n" +
		"    \"webpack:build:main\": \"npm run webpack -- --config webpack/webpack.dev.js --env.stats=minimal\",\r\n" +
		"    \"webpack:build\": \"npm run cleanup && npm run webpack:build:main\",\r\n" +
		"    \"webpack:prod:main\": \"npm run webpack -- --config webpack/webpack.prod.js --profile\",\r\n" +
		"    \"webpack:prod\": \"npm run cleanup && npm run webpack:prod:main && npm run clean-www\",\r\n" +
		"    \"webpack:test\": \"npm run test\",\r\n" +
		"    \"webpack-dev-server\": \"node --max_old_space_size=4096 node_modules/webpack-dev-server/bin/webpack-dev-server.js\",\r\n" +
		"    \"webpack\": \"node --max_old_space_size=4096 node_modules/webpack/bin/webpack.js\"\r\n" +
		"  },\r\n" +
		"  \"jestSonar\": {\r\n" +
		"    \"reportPath\": \"target/test-results/jest\",\r\n" +
		"    \"reportFile\": \"TESTS-results-sonar.xml\"\r\n" +
		"  }\r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "package";
	}

}
