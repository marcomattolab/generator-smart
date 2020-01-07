import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiService } from 'src/app/services/api/api.service';
import { createRequestOption } from 'src/app/shared';
import { Struttura } from './struttura.model';

@Injectable({ providedIn: 'root'})
export class StrutturaService {
    private resourceUrl = ApiService.API_URL + '/strutturas';

    constructor(protected http: HttpClient) { }

    create(struttura: Struttura): Observable<HttpResponse<Struttura>> {
        return this.http.post<Struttura>(this.resourceUrl, struttura, { observe: 'response'});
    }

    update(struttura: Struttura): Observable<HttpResponse<Struttura>> {
        return this.http.put(this.resourceUrl, struttura, { observe: 'response'});
    }

    find(id: number): Observable<HttpResponse<Struttura>> {
        return this.http.get(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    query(req?: any): Observable<HttpResponse<Struttura[]>> {
        const options = createRequestOption(req);
        return this.http.get<Struttura[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }
}
