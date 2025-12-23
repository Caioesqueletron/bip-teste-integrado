import { Component, OnInit, signal } from '@angular/core';
import { Beneficio } from '../../../../shared/models/beneficio.model';
import { BeneficioApi } from '../../../../core/services/beneficio-api';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-beneficio-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './beneficio-list.html'
})
export class BeneficioList implements OnInit {
  beneficios = signal<Beneficio[]>([]);

  constructor(
    private api: BeneficioApi,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.load();
  }

  load() {
    this.api.list().subscribe(data => this.beneficios.set(data));
  }

  toggleAtivo(b: Beneficio) {
    b.ativo = !b.ativo;
    if (b.id) this.api.update(b.id, b).subscribe(() => this.load());
  }

  delete(b: Beneficio) {
    if (!b.id) return;
    if (confirm(`Deseja realmente deletar o benefício "${b.nome}"?`)) {
      this.api.delete(b.id).subscribe(() => this.load());
    }
  }
}
