import {HttpClient, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {environment} from 'src/environments/environment';
import {ApiService} from '../../../services/api/api.service';
import {Publisher} from './publisher.model';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PublisherService {
  private readonly PUBLISHER_URL = ApiService.API_URL + '/publishers/';

  constructor(public http: HttpClient) {
  }

  getAll() {
    return this.http.get<Publisher[]>(
      this.PUBLISHER_URL, {observe: 'response'}).pipe(map(res => res.body));
  }

  // get(params?: any, reqOpts?: any) {
  get(publisherId: string | number) {
    return this.http.get<Publisher>(this.PUBLISHER_URL + publisherId);
  }

  new(publisher: Publisher, reqOpts?: any) {
    return this.http.post<Publisher>(this.PUBLISHER_URL, publisher, reqOpts);
  }

  save(publisher: Publisher, reqOpts?: any) {
    return this.http.put(this.PUBLISHER_URL, publisher, reqOpts);
  }

  delete(publisherId: string | number) {
    return this.http.delete(this.PUBLISHER_URL + publisherId);
  }
}
