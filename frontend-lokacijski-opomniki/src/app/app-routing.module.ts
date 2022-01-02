import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {SeznamiComponent} from './seznami/seznami.component';
import {SeznamPodrobnostiComponent} from './seznami/seznam-podrobnosti.component';
import { ArtikelDodajComponent } from './seznami/artikel-dodaj.component';
import {PolnilnepostajeComponent} from './seznami/polnilnepostaje.component';
import {PodrobnostiPostajeComponent} from './seznami/PodrobnostiPostaje.Component';

const routes: Routes = [
    {path: '', redirectTo: '/pplist', pathMatch: 'full'},
    {path: 'pplist', component: PolnilnepostajeComponent},
    {path: 'pplist/:id', component: PodrobnostiPostajeComponent},
    {path: 'seznami', component: SeznamiComponent},
    {path: 'seznami/:id', component: SeznamPodrobnostiComponent},
    {path: 'seznami/:id/dodaj', component: ArtikelDodajComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
