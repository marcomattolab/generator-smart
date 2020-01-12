package it.eng.generate.template.ionic;

import java.util.Iterator;
import java.util.Set;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityServiceIonic extends AbstractResourceTemplate {

	public TemplateEntityServiceIonic(Table tabella) {
		super(tabella);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String nometabella = Utils.getClassNameLowerCase(tabella);
		String Nometabella = Utils.getEntityName(tabella);
		String body = 
		"import { Injectable } from '@angular/core';\r\n" +
		"import { HttpClient, HttpResponse } from '@angular/common/http';\r\n" +
		"import { Observable } from 'rxjs';\r\n" +
		"import { ApiService } from 'src/app/services/api/api.service';\r\n" +
		"import { createRequestOption } from 'src/app/shared';\r\n" +
		"import { "+Nometabella+" } from './"+nometabella+".model';\r\n\n" +
		"@Injectable({ providedIn: 'root'})\r\n" +
		"export class "+Nometabella+"Service {\r\n" +
		"    private resourceUrl = ApiService.API_URL + '/"+nometabella+"s';\r\n	\n" +
		"    constructor(protected http: HttpClient) { }\r\n\n" +
		"    create("+nometabella+": "+Nometabella+"): Observable<HttpResponse<"+Nometabella+">> {\r\n" +
		"        return this.http.post<"+Nometabella+">(this.resourceUrl, "+nometabella+", { observe: 'response'});\r\n" +
		"    }\r\n\n" +
		"    update("+nometabella+": "+Nometabella+"): Observable<HttpResponse<"+Nometabella+">> {\r\n" +
		"        return this.http.put(this.resourceUrl, "+nometabella+", { observe: 'response'});\r\n" +
		"    }\r\n\n" +
		"    find(id: number): Observable<HttpResponse<"+Nometabella+">> {\r\n" +
		"        return this.http.get(`${this.resourceUrl}/${id}`, { observe: 'response'});\r\n" +
		"    }\r\n\n" +
		"    query(req?: any): Observable<HttpResponse<"+Nometabella+"[]>> {\r\n" +
		"        const options = createRequestOption(req);\r\n" +
		"        return this.http.get<"+Nometabella+"[]>(this.resourceUrl, { params: options, observe: 'response' });\r\n" +
		"    }\r\n\n" +
		"    delete(id: number): Observable<HttpResponse<any>> {\r\n" +
		"        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});\r\n" +
		"    }\r\n\n" +
		"}\r\n";

		return body;
	}
	
	public String getClassName(){
		return Utils.getClassNameLowerCase(tabella)+".service";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "mobile/src/app/pages/entities/"+Utils.getClassNameLowerCase(tabella);
	}

}
