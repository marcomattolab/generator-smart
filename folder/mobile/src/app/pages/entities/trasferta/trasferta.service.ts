import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiService } from 'src/app/services/api/api.service';
import { createRequestOption } from 'src/app/shared';
import { Trasferta } from './trasferta.model';

@Injectable({ providedIn: 'root'})
export class TrasfertaService {
    private resourceUrl = ApiService.API_URL + '/trasfertas';

    constructor(protected http: HttpClient) { }

    create(trasferta: Trasferta): Observable<HttpResponse<Trasferta>> {
        return this.http.post<Trasferta>(this.resourceUrl, trasferta, { observe: 'response'});
    }

    update(trasferta: Trasferta): Observable<HttpResponse<Trasferta>> {
        return this.http.put(this.resourceUrl, trasferta, { observe: 'response'});
    }

    find(id: number): Observable<HttpResponse<Trasferta>> {
        return this.http.get(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    query(req?: any): Observable<HttpResponse<Trasferta[]>> {
        const options = createRequestOption(req);
        return this.http.get<Trasferta[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }
}
