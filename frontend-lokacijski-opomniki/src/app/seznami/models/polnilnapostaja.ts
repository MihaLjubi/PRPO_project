export class Polnilnapostaja {
    id_polnilna_postaja: number;
    cena: number;
    lokacija: string;
    status: boolean; /** 0 je prosto, 1 je zasedeno */
    obratovanje_zacetek: Date;
    obratovanje_konec: Date;
}
