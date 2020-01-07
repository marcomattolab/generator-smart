import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiService } from 'src/app/services/api/api.service';
import { createRequestOption } from 'src/app/shared';
import { Persona } from './persona.model';

@Injectable({ providedIn: 'root'})
export class PersonaService {
    private resourceUrl = ApiService.API_URL + '/personas';

    constructor(protected http: HttpClient) { }

    create(persona: Persona): Observable<HttpResponse<Persona>> {
        return this.http.post<Persona>(this.resourceUrl, persona, { observe: 'response'});
    }

    update(persona: Persona): Observable<HttpResponse<Persona>> {
        return this.http.put(this.resourceUrl, persona, { observe: 'response'});
    }

    find(id: number): Observable<HttpResponse<Persona>> {
        return this.http.get(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    query(req?: any): Observable<HttpResponse<Persona[]>> {
        const options = createRequestOption(req);
        return this.http.get<Persona[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }
}
