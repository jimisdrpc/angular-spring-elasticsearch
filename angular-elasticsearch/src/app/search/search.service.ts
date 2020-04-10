import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';

@Injectable()
export class SearchService {
  baseUrl: string = 'http://localhost:8080/correntistas/search?palavras=';
  constructor(private http: HttpClient) { }
  search(queryString: string) {
    let _URL = this.baseUrl + queryString;
    return this.http.get(_URL);
  }
}