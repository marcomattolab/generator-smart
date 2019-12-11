package it.eng.generate.template.fe.entities;

import java.util.Iterator;
import java.util.Set;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityService extends AbstractResourceTemplate {

	public TemplateEntityService(Table tabella) {
		super(tabella);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String nometabella = Utils.getClassNameLowerCase(tabella);
		String INometabella = Utils.getIName(tabella);
		String NometabellaService = Utils.getEntityName(tabella)+"Service";
		
		String body = 
		"import { Injectable } from '@angular/core';\r\n" +
		"import { HttpClient, HttpResponse, HttpParams } from '@angular/common/http';\r\n" +
		"import { Observable } from 'rxjs';\r\n" +
		"import * as moment from 'moment';\r\n" +
		"import { DATE_FORMAT } from 'app/shared/constants/input.constants';\r\n" +
		"import { map } from 'rxjs/operators';\r\n" +
		"import { SERVER_API_URL } from 'app/app.constants';\r\n" +
		"import { createRequestOption } from 'app/shared';\r\n" +
		"import { "+INometabella+" } from 'app/shared/model/"+nometabella+".model';\r\n\n" +
		"type EntityResponseType = HttpResponse<"+INometabella+">;\r\n" +
		"type EntityArrayResponseType = HttpResponse<"+INometabella+"[]>;\r\n\n" +
		"@Injectable({ providedIn: 'root' })\r\n" +
		"export class "+NometabellaService+" {\r\n\n" +
		"    public resourceUrl = SERVER_API_URL + 'api/"+nometabella+"s';\r\n" +
		"    constructor(private http: HttpClient) {}\r\n\n" +
		"    create("+nometabella+": "+INometabella+"): Observable<EntityResponseType> {\r\n" +
		"        const copy = this.convertDateFromClient("+nometabella+");\r\n" +
		"        return this.http\r\n" +
		"            .post<"+INometabella+">(this.resourceUrl, copy, { observe: 'response' })\r\n" +
		"            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));\r\n" +
		"    }\r\n\n" +
		"    update("+nometabella+": "+INometabella+"): Observable<EntityResponseType> {\r\n" +
		"        const copy = this.convertDateFromClient("+nometabella+");\r\n" +
		"        return this.http\r\n" +
		"            .put<"+INometabella+">(this.resourceUrl, copy, { observe: 'response' })\r\n" +
		"            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));\r\n" +
		"    }\r\n\n" +
		"    find(id: number): Observable<EntityResponseType> {\r\n" +
		"        return this.http\r\n" +
		"            .get<"+INometabella+">(`${this.resourceUrl}/${id}`, { observe: 'response' })\r\n" +
		"            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));\r\n" +
		"    }\r\n\n" +
		"    query(req?: any): Observable<EntityArrayResponseType> {\r\n" +
		"        const options = createRequestOption(req);\r\n" +
		"        return this.http\r\n" +
		"            .get<"+INometabella+"[]>(this.resourceUrl, { params: options, observe: 'response' })\r\n" +
		"            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));\r\n" +
		"    }\r\n\n" +
		"    delete(id: number): Observable<HttpResponse<any>> {\r\n" +
		"        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });\r\n" +
		"    }\r\n\n" +
		"    protected convertDateFromClient("+nometabella+": "+INometabella+"): "+INometabella+" {\r\n" +
		"        const copy: "+INometabella+" = Object.assign({}, "+nometabella+", {\r\n";
		
		//DONE MANAGE DATES
		Set set = tabella.getColumnNames();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
			String columnname = Utils.getFieldName(column);
			if(Utils.isDateField(column)) {
				/**
				 * LocalDate ==> format(DATE_FORMAT)
				 * Instant   ==> toJSON()
				 */
				String formatDate = Utils.isLocalDate(column) ? ".format(DATE_FORMAT)" : ".toJSON()";
				body += "            "+columnname+": "+nometabella+"."+columnname+" != null && "+nometabella+"."+columnname+".isValid() ? "+nometabella+"."+columnname + formatDate+" : null,\r\n";
			}
		}
		//MANAGE DATES

		body += 
		"        });\r\n" +
		"        return copy;\r\n" +
		"    }\r\n\n" +
		"    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {\r\n" +
		"        if (res.body) {\r\n";
		
		//DONE MANAGE DATES
		Set cset = tabella.getColumnNames();
		for (Iterator iter = cset.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
			String columnname = Utils.getFieldName(column);
			if(Utils.isDateField(column)) {
				body += 	"            res.body."+columnname+" = res.body."+columnname+" != null ? moment(res.body."+columnname+") : null;\r\n";
			}
		}
		//MANAGE DATES
		
		body +=
		"        }\r\n" +
		"        return res;\r\n" +
		"    }\r\n\n" +
		"    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {\r\n" +
		"        if (res.body) {\r\n" +
		"            res.body.forEach(("+nometabella+": "+INometabella+") => {\r\n";
		//DONE MANAGE DATES
		Set pset = tabella.getColumnNames();
		for (Iterator iter = pset.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Column column = tabella.getColumn(key);
			String columnname = Utils.getFieldName(column);
			if(Utils.isDateField(column)) {
				body += "                "+nometabella+"."+columnname+" = "+nometabella+"."+columnname+" != null ? moment("+nometabella+"."+columnname+") : null;\r\n";
			
			}
		}
		//MANAGE DATES
		body +=
		"            });\r\n" +
		"        }\r\n" +
		"        return res;\r\n" +
		"    }\r\n" +
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
		return "src/main/webapp/app/entities/"+Utils.getClassNameLowerCase(tabella);
	}

}
