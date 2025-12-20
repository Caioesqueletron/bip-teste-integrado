import { BeneficioCreateComponent } from './features/beneficios/pages/beneficio-create/beneficio-create';
import { BeneficioEditComponent } from './features/beneficios/pages/beneficio-edit/beneficio-edit';
import { BeneficioList } from './features/beneficios/pages/beneficio-list/beneficio-list';
import { Routes, provideRouter } from '@angular/router';

export const routes: Routes = [
    { path: '', redirectTo: 'beneficios', pathMatch: 'full' },
    { path: 'beneficios', component: BeneficioList },
    { path: 'beneficios/create', component: BeneficioCreateComponent },
    { path: 'beneficios/edit/:id', component: BeneficioEditComponent }
];

export const appConfig = {
  providers: [provideRouter(routes)]
};