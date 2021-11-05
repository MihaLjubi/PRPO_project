INSERT INTO uporabnik (id_uporabnik, ime, priimek, uporabnisko_ime) VALUES (00001, 'John', 'Doe', 'nobody');
INSERT INTO polnilnapostaja (id_polnilna_postaja, cena, lokacija, obratovanje_konec, obratovanje_zacetek, status) VALUES (20001, 12, 'Ljubljana', '2020-8-7 12:15:0', '2020-8-7 9:45:0', 1);
INSERT INTO rezervacija (id_rezervacija, polnjenje_konec, polnjenje_zacetek, id_polnilna_postaja, id_uporabnik) VALUES (10001, '2020-8-7 12:0:0', '2020-8-7 10:0.0', 20001, 00001);

INSERT INTO uporabnik (id_uporabnik, ime, priimek, uporabnisko_ime) VALUES (00002, 'James', 'Bond', 'doubleO7');
INSERT INTO polnilnapostaja (id_polnilna_postaja, cena, lokacija, obratovanje_konec, obratovanje_zacetek, status) VALUES (20002, 10, 'Maribor', '2020-10-22 5:15:0', '2020-10-22 4:45:0', 0);
INSERT INTO rezervacija (id_rezervacija, polnjenje_konec, polnjenje_zacetek, id_polnilna_postaja, id_uporabnik) VALUES (10002, '2020-10-22 5:0:0', '2020-10-22 4:0.0', 20002, 00002);
