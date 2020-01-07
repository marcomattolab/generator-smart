import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiService } from 'src/app/services/api/api.service';
import { createRequestOption } from 'src/app/shared';
import { Luogo } from './luogo.model';

@Injectable({ providedIn: 'root'})
export class LuogoService {
    private resourceUrl = ApiService.API_URL + '/luogos';

    constructor(protected http: HttpClient) { }

    create(luogo: Luogo): Observable<HttpResponse<Luogo>> {
        return this.http.post<Luogo>(this.resourceUrl, luogo, { observe: 'response'});
    }

    update(luogo: Luogo): Observable<HttpResponse<Luogo>> {
        return this.http.put(this.resourceUrl, luogo, { observe: 'response'});
    }

    find(id: number): Observable<HttpResponse<Luogo>> {
        return this.http.get(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    query(req?: any): Observable<HttpResponse<Luogo[]>> {
        const options = createRequestOption(req);
        return this.http.get<Luogo[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }
}
