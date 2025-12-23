import { BeneficioList } from './features/beneficios/pages/beneficio-list/beneficio-list';
import { Routes, provideRouter } from '@angular/router';
import { BeneficioFormComponent } from './features/component/beneficio-form/beneficio-form';
import { BeneficioTransferComponent } from './features/beneficios/pages/beneficio-transfer/beneficio-transfer';

export const routes: Routes = [
    { path: '', redirectTo: 'beneficios', pathMatch: 'full' },
    { path: 'beneficios', component: BeneficioList },
    { path: 'beneficios/create', component: BeneficioFormComponent },
    { path: 'beneficios/edit/:id', component: BeneficioFormComponent },
    { path: 'transfer', component: BeneficioTransferComponent }

];

export const appConfig = {
  providers: [provideRouter(routes)]
};