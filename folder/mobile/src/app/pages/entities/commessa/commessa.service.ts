import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiService } from 'src/app/services/api/api.service';
import { createRequestOption } from 'src/app/shared';
import { Commessa } from './commessa.model';

@Injectable({ providedIn: 'root'})
export class CommessaService {
    private resourceUrl = ApiService.API_URL + '/commessas';

    constructor(protected http: HttpClient) { }

    create(commessa: Commessa): Observable<HttpResponse<Commessa>> {
        return this.http.post<Commessa>(this.resourceUrl, commessa, { observe: 'response'});
    }

    update(commessa: Commessa): Observable<HttpResponse<Commessa>> {
        return this.http.put(this.resourceUrl, commessa, { observe: 'response'});
    }

    find(id: number): Observable<HttpResponse<Commessa>> {
        return this.http.get(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    query(req?: any): Observable<HttpResponse<Commessa[]>> {
        const options = createRequestOption(req);
        return this.http.get<Commessa[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }
}
