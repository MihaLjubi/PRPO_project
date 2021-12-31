import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {Location} from '@angular/common';

import { switchMap } from 'rxjs/operators';

import {PostajeService} from './services/seznamiPP.service';
import {Polnilnapostaja} from './models/polnilnapostaja';

@Component({
    moduleId: module.id,
    selector: 'seznam-podrobnosti',
    templateUrl: 'PodrobnostiPostaje.Component.html'
})
export class PodrobnostiPostajeComponent implements OnInit {
    podrobnosti: Polnilnapostaja;

    constructor(private postajeService: PostajeService,
                private route: ActivatedRoute,
                private location: Location,
                private router: Router) {
    }

    ngOnInit(): void {
        this.route.params.pipe(
            switchMap((params: Params) => this.postajeService.getPPbyId(+params['id'])))
            .subscribe(received => this.podrobnosti = received);
    }
    nazaj(): void {
        this.router.navigate(['pplist']);
    }
}
