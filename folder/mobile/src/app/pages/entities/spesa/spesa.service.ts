import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiService } from 'src/app/services/api/api.service';
import { createRequestOption } from 'src/app/shared';
import { Spesa } from './spesa.model';

@Injectable({ providedIn: 'root'})
export class SpesaService {
    private resourceUrl = ApiService.API_URL + '/spesas';

    constructor(protected http: HttpClient) { }

    create(spesa: Spesa): Observable<HttpResponse<Spesa>> {
        return this.http.post<Spesa>(this.resourceUrl, spesa, { observe: 'response'});
    }

    update(spesa: Spesa): Observable<HttpResponse<Spesa>> {
        return this.http.put(this.resourceUrl, spesa, { observe: 'response'});
    }

    find(id: number): Observable<HttpResponse<Spesa>> {
        return this.http.get(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    query(req?: any): Observable<HttpResponse<Spesa[]>> {
        const options = createRequestOption(req);
        return this.http.get<Spesa[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }
}
