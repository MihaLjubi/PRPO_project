INSERT INTO uporabnik (ime, priimek, uporabnisko_ime) VALUES ('John', 'Doe', 'nobody');
INSERT INTO uporabnik (ime, priimek, uporabnisko_ime) VALUES ('James', 'Bond', 'doubleO7');
INSERT INTO uporabnik (ime, priimek, uporabnisko_ime) VALUES ('Luka', 'Teden', 'lukec96');

INSERT INTO polnilnapostaja (cena, lokacija, obratovanje_konec, obratovanje_zacetek, status) VALUES (12, 'Ljubljana', '2020-8-7 12:15:0', '2020-8-7 9:45:0', 1);
INSERT INTO polnilnapostaja (cena, lokacija, obratovanje_konec, obratovanje_zacetek, status) VALUES (10, 'Maribor', '2020-10-22 5:15:0', '2020-10-22 4:45:0', 0);
INSERT INTO polnilnapostaja (cena, lokacija, obratovanje_konec, obratovanje_zacetek, status) VALUES (15, 'Koper', '2020-10-22 8:00:0', '2020-10-22 3:45:0', 0);

INSERT INTO rezervacija (polnjenje_konec, polnjenje_zacetek, id_polnilna_postaja, id_uporabnik) VALUES ('2020-10-22 5:0:0', '2020-10-22 4:0.0', 3, 1);
INSERT INTO rezervacija (polnjenje_konec, polnjenje_zacetek, id_polnilna_postaja, id_uporabnik) VALUES ('2020-8-7 12:0:0', '2020-8-7 10:0.0', 1, 2);
INSERT INTO rezervacija (polnjenje_konec, polnjenje_zacetek, id_polnilna_postaja, id_uporabnik) VALUES ('2020-10-22 7:0:0', '2020-10-22 6:0.0', 2, 2);