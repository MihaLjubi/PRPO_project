import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

import {AppRoutingModule} from './app-routing.module';

import {AppComponent} from './app.component';
import {SeznamiComponent} from './seznami/seznami.component';
import {PolnilnepostajeComponent} from './seznami/polnilnepostaje.component';
import {ArtikelDodajComponent} from './seznami/artikel-dodaj.component';
import {SeznamPodrobnostiComponent} from './seznami/seznam-podrobnosti.component';
import {SeznamiService} from './seznami/services/seznami.service';
import {PostajeService} from './seznami/services/seznamiPP.service';
import {PodrobnostiPostajeComponent} from './seznami/PodrobnostiPostaje.Component';


@NgModule({
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule,
        FormsModule
    ],
    declarations: [
        AppComponent,
        SeznamiComponent,
        SeznamPodrobnostiComponent,
        ArtikelDodajComponent,
        PolnilnepostajeComponent,
        PodrobnostiPostajeComponent
    ],
    providers: [PostajeService, SeznamiService],
    bootstrap: [AppComponent]
})
export class AppModule {
}

