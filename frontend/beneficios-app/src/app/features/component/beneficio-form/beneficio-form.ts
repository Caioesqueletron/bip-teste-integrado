import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BeneficioApi } from '../../../core/services/beneficio-api';
import { Beneficio } from '../../../shared/models/beneficio.model';

@Component({
  selector: 'app-beneficio-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './beneficio-form.html'
})
export class BeneficioFormComponent implements OnInit {
  beneficio = signal<Beneficio>({ nome: '', descricao: '', valor: 0, ativo: true });
  isEdit = false;
  id?: number;

  constructor(
    private api: BeneficioApi,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    const paramId = this.route.snapshot.paramMap.get('id');
    if (paramId) {
      this.isEdit = true;
      this.id = +paramId;
      this.api.getById(this.id).subscribe(b => this.beneficio.set(b));
    }
  }

  submit() {
    if (this.isEdit && this.id) {
      this.api.update(this.id, this.beneficio()).subscribe(() => this.router.navigate(['/beneficios']));
    } else {
      this.api.create(this.beneficio()).subscribe(() => this.router.navigate(['/beneficios']));
    }
  }

  cancel() {
    this.router.navigate(['/beneficios']);
  }
}
