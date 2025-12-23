import { Injectable } from '@angular/core';
import { Beneficio } from '../../shared/models/beneficio.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class BeneficioApi {
 delete(id: number) {
  return this.http.delete(`${this.apiUrl}/${id}`); // <-- retorna Observable
}
   private apiUrl = 'http://localhost:8080/api/v1/beneficios';

  constructor(private http: HttpClient) {}

  list() : Observable<Beneficio[]> {
    return this.http.get<Beneficio[]>(this.apiUrl);
  }

  create(beneficio: Beneficio) : Observable<Beneficio> {
    return this.http.post<Beneficio>(this.apiUrl, beneficio);
  }

  update(id: number, beneficio: Beneficio) : Observable<Beneficio> {
    return this.http.put<Beneficio>(`${this.apiUrl}/${id}`, beneficio);
  }

  getById(id: number) : Observable<Beneficio> {
    return this.http.get<Beneficio>(`${this.apiUrl}/${id}`);
  }
}
