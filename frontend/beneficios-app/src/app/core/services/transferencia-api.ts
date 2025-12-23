import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

export interface Transferencia {
  fromId: number;
  toId: number;
  amount: number;
}

@Injectable({ providedIn: 'root' })
export class TransferenciaApiService {
  private apiUrl = 'http://localhost:8080/api/v1/beneficios/transfer';

  constructor(private http: HttpClient) {}

  transfer(data: Transferencia) {
    return this.http.post(this.apiUrl, data);
  }
}