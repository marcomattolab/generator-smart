import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiService } from 'src/app/services/api/api.service';
import { createRequestOption } from 'src/app/shared';
import { Giustificativo } from './giustificativo.model';

@Injectable({ providedIn: 'root'})
export class GiustificativoService {
    private resourceUrl = ApiService.API_URL + '/giustificativos';

    constructor(protected http: HttpClient) { }

    create(giustificativo: Giustificativo): Observable<HttpResponse<Giustificativo>> {
        return this.http.post<Giustificativo>(this.resourceUrl, giustificativo, { observe: 'response'});
    }

    update(giustificativo: Giustificativo): Observable<HttpResponse<Giustificativo>> {
        return this.http.put(this.resourceUrl, giustificativo, { observe: 'response'});
    }

    find(id: number): Observable<HttpResponse<Giustificativo>> {
        return this.http.get(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    query(req?: any): Observable<HttpResponse<Giustificativo[]>> {
        const options = createRequestOption(req);
        return this.http.get<Giustificativo[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }
}
