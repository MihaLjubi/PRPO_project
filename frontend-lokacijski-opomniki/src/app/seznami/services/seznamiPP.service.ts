import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import { SeznamPP } from '../models/seznampp';
import { Observable } from 'rxjs';

import { catchError } from 'rxjs/operators';
import { Polnilnapostaja } from '../models/polnilnapostaja';

@Injectable()
export class PostajeService {

    private headers = new HttpHeaders({'Content-Type': 'application/json'});
    private url = 'http://localhost:8080/v1/polnilnepostaje';

    constructor(private http: HttpClient) {
    }

    getPP(): Observable<Polnilnapostaja[]> {
        return this.http.get<Polnilnapostaja[]>(this.url)
            .pipe(catchError(this.handleError));
    }

    getPPbyId(id: number): Observable<Polnilnapostaja> {
        console.log('getPPbyId should trigger request');
        const url = `${this.url}/${id}`;
        return this.http.get<Polnilnapostaja>(url)
            .pipe(catchError(this.handleError));
    }

    delete(id: number): Observable<number> {
        const url = `${this.url}/${id}`;
        console.log(url);
        return this.http.delete<number>(url, {headers: this.headers})
            .pipe(catchError(this.handleError));
    }

    create(artikel: Polnilnapostaja): Observable<Polnilnapostaja> {
        return this.http.post<Polnilnapostaja>(this.url, JSON.stringify(artikel), {headers: this.headers})
            .pipe(catchError(this.handleError));
    }

    private handleError(error: any): Promise<any> {
        console.error('Prišlo je do napake', error);
        return Promise.reject(error.message || error);
    }
}

