package it.eng.generate.template.fe.admin;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateConfigurationService extends AbstractResourceTemplate {

	public TemplateConfigurationService(DataBase database) {
		super(database);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = "import { Injectable } from '@angular/core';\r\n" +
		"import { HttpClient, HttpResponse } from '@angular/common/http';\r\n" +
		"import { Observable } from 'rxjs';\r\n" +
		"import { map } from 'rxjs/operators';\r\n" +
		"import { SERVER_API_URL } from 'app/app.constants';\r\n" +
		"@Injectable({ providedIn: 'root' })\r\n" +
		"export class JhiConfigurationService {\r\n" +
		"    constructor(private http: HttpClient) {}\r\n" +
		"    get(): Observable<any> {\r\n" +
		"        return this.http.get(SERVER_API_URL + 'management/configprops', { observe: 'response' }).pipe(\r\n" +
		"            map((res: HttpResponse<any>) => {\r\n" +
		"                const properties: any[] = [];\r\n" +
		"                const propertiesObject = this.getConfigPropertiesObjects(res.body);\r\n" +
		"                for (const key in propertiesObject) {\r\n" +
		"                    if (propertiesObject.hasOwnProperty(key)) {\r\n" +
		"                        properties.push(propertiesObject[key]);\r\n" +
		"                    }\r\n" +
		"                }\r\n" +
		"                return properties.sort((propertyA, propertyB) => {\r\n" +
		"                    return propertyA.prefix === propertyB.prefix ? 0 : propertyA.prefix < propertyB.prefix ? -1 : 1;\r\n" +
		"                });\r\n" +
		"            })\r\n" +
		"        );\r\n" +
		"    }\r\n" +
		"    getConfigPropertiesObjects(res: Object) {\r\n" +
		"        // This code is for Spring Boot 2\r\n" +
		"        if (res['contexts'] !== undefined) {\r\n" +
		"            for (const key in res['contexts']) {\r\n" +
		"                // If the key is not bootstrap, it will be the ApplicationContext Id\r\n" +
		"                // For default app, it is baseName\r\n" +
		"                // For microservice, it is baseName-1\r\n" +
		"                if (!key.startsWith('bootstrap')) {\r\n" +
		"                    return res['contexts'][key]['beans'];\r\n" +
		"                }\r\n" +
		"            }\r\n" +
		"        }\r\n" +
		"        // by default, use the default ApplicationContext Id\r\n" +
		"        return res['contexts']['"+conf.getProjectName()+"']['beans'];\r\n" +
		"    }\r\n" +
		"    getEnv(): Observable<any> {\r\n" +
		"        return this.http.get(SERVER_API_URL + 'management/env', { observe: 'response' }).pipe(\r\n" +
		"            map((res: HttpResponse<any>) => {\r\n" +
		"                const properties: any = {};\r\n" +
		"                const propertySources = res.body['propertySources'];\r\n" +
		"                for (const propertyObject of propertySources) {\r\n" +
		"                    const name = propertyObject['name'];\r\n" +
		"                    const detailProperties = propertyObject['properties'];\r\n" +
		"                    const vals: any[] = [];\r\n" +
		"                    for (const keyDetail in detailProperties) {\r\n" +
		"                        if (detailProperties.hasOwnProperty(keyDetail)) {\r\n" +
		"                            vals.push({ key: keyDetail, val: detailProperties[keyDetail]['value'] });\r\n" +
		"                        }\r\n" +
		"                    }\r\n" +
		"                    properties[name] = vals;\r\n" +
		"                }\r\n" +
		"                return properties;\r\n" +
		"            })\r\n" +
		"        );\r\n" +
		"    }\r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName(){
		return "configuration.service";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "src/main/webapp/app/admin/configuration";
	}

}
