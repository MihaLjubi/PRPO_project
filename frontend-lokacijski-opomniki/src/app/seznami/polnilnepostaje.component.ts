import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

import { SeznamPP } from './models/seznampp';
import { PostajeService } from './services/seznamiPP.service';
import {Polnilnapostaja} from './models/polnilnapostaja';

@Component({
    moduleId: module.id,
    selector: 'vsi-seznami',
    templateUrl: 'polnilnepostaje.component.html'
})
export class PolnilnepostajeComponent implements OnInit {
    polnilnePostaje: Polnilnapostaja[];
    polnilnaPostaja: Polnilnapostaja;

    constructor(public seznamiPPservice: PostajeService,
                public router: Router) {
    }

    ngOnInit(): void {
        this.getPP();
    }

    getPP(): void {
        this.seznamiPPservice
            .getPP()
            .subscribe(received => this.polnilnePostaje = received);
    }
    prikaziPodrobnosti(pp: Polnilnapostaja): void {
        this.polnilnaPostaja = pp;
        this.router.navigate(['/pplist', this.polnilnaPostaja.id_polnilna_postaja]);
        console.log(this.polnilnaPostaja.id_polnilna_postaja);
    }
    delete(seznam: Polnilnapostaja): void {
        this.seznamiPPservice
            .delete(0);
    }
}
