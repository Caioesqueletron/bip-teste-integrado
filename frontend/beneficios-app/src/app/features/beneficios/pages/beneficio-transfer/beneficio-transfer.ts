import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { Beneficio } from "../../../../shared/models/beneficio.model";
import { TransferenciaApiService, Transferencia } from "../../../../core/services/transferencia-api";
import { BeneficioApi } from "../../../../core/services/beneficio-api";
import { Component } from "@angular/core";

@Component({
  selector: 'app-beneficio-transfer',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './beneficio-transfer.html'
})
export class BeneficioTransferComponent {

  beneficios: Beneficio[] = [];

  fromId?: number;
  toId?: number;
  amount?: number;

  constructor(private api: TransferenciaApiService, private apiBenefit: BeneficioApi) {}

  ngOnInit() {
    this.apiBenefit.list().subscribe(b => this.beneficios = b);
  }

  transfer() {
    if (!this.fromId || !this.toId || !this.amount) return;


    this.api.transfer({fromId: this.fromId,toId: this.toId, amount: this.amount})
      .subscribe({
        next: () => {
          alert('Transferência realizada com sucesso');
          this.apiBenefit.list().subscribe(b => this.beneficios = b);
        },
        error: err => alert(err.error?.message || 'Erro na transferência')
      });
  }
}
