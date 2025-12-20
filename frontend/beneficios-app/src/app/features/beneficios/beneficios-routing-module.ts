import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BeneficioList } from './pages/beneficio-list/beneficio-list';


const routes: Routes = [
  { path: '', component: BeneficioList },
  { path: 'beneficios', component: BeneficioList }

]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BeneficiosRoutingModule { }
