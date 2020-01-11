import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiService } from 'src/app/services/api/api.service';
import { createRequestOption } from 'src/app/shared';
import { ConfigurationData } from './configuration-data.model';

@Injectable({ providedIn: 'root'})
export class ConfigurationDataService {
    private resourceUrl = ApiService.API_URL + '/configuration-data';

    constructor(protected http: HttpClient) { }

    create(configurationData: ConfigurationData): Observable<HttpResponse<ConfigurationData>> {
        return this.http.post<ConfigurationData>(this.resourceUrl, configurationData, { observe: 'response'});
    }

    update(configurationData: ConfigurationData): Observable<HttpResponse<ConfigurationData>> {
        return this.http.put(this.resourceUrl, configurationData, { observe: 'response'});
    }

    find(id: number): Observable<HttpResponse<ConfigurationData>> {
        return this.http.get(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    query(req?: any): Observable<HttpResponse<ConfigurationData[]>> {
        const options = createRequestOption(req);
        return this.http.get<ConfigurationData[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }
}
