INSERT INTO uporabnik (ime, priimek, uporabnisko_ime) VALUES ('John', 'Doe', 'nobody');
INSERT INTO uporabnik (ime, priimek, uporabnisko_ime) VALUES ('James', 'Bond', 'doubleO7');
INSERT INTO uporabnik (ime, priimek, uporabnisko_ime) VALUES ('Luka', 'Teden', 'lukec96');
INSERT INTO uporabnik (ime, priimek, uporabnisko_ime) VALUES ('Alenka', 'Hrib', 'aHrib');
INSERT INTO uporabnik (ime, priimek, uporabnisko_ime) VALUES ('Bojan', 'Buhtel', 'BBB');
INSERT INTO uporabnik (ime, priimek, uporabnisko_ime) VALUES ('Karmen', 'Hren', 'karHre');

INSERT INTO polnilnapostaja (cena, lokacija, obratovanje_zacetek, obratovanje_konec, status) VALUES (12, 'Ljubljana', '9:45:0', '12:15:0', true);
INSERT INTO polnilnapostaja (cena, lokacija, obratovanje_zacetek, obratovanje_konec, status) VALUES (10, 'Maribor', '4:45:0', '5:15:0', true);
INSERT INTO polnilnapostaja (cena, lokacija, obratovanje_zacetek, obratovanje_konec, status) VALUES (15, 'Koper', '23:45:0', '8:00:0', false);
INSERT INTO polnilnapostaja (cena, lokacija, obratovanje_zacetek, obratovanje_konec, status) VALUES (9, 'Velenje', '3:45:0', '8:00:0', true);
INSERT INTO polnilnapostaja (cena, lokacija, obratovanje_zacetek, obratovanje_konec, status) VALUES (11, 'Novo mesto', '3:45:0', '8:00:0', true);
INSERT INTO polnilnapostaja (cena, lokacija, obratovanje_zacetek, obratovanje_konec, status) VALUES (7, 'Celje', '3:45:0', '8:00:0', true);
INSERT INTO polnilnapostaja (cena, lokacija, obratovanje_zacetek, obratovanje_konec, status) VALUES (12, 'Murska Sobota', '3:45:0', '8:00:0', false);

INSERT INTO rezervacija (polnjenje_konec, polnjenje_zacetek, id_polnilna_postaja, id_uporabnik) VALUES ('2020-10-22 5:0:0', '2020-10-22 4:0.0', 3, 1);
INSERT INTO rezervacija (polnjenje_konec, polnjenje_zacetek, id_polnilna_postaja, id_uporabnik) VALUES ('2020-8-7 12:0:0', '2020-8-7 10:0.0', 2, 2);
INSERT INTO rezervacija (polnjenje_konec, polnjenje_zacetek, id_polnilna_postaja, id_uporabnik) VALUES ('2020-10-22 7:0:0', '2020-10-22 6:0.0', 5, 2);
INSERT INTO rezervacija (polnjenje_konec, polnjenje_zacetek, id_polnilna_postaja, id_uporabnik) VALUES ('2020-10-22 5:0:0', '2020-10-22 4:0.0', 6, 5);
INSERT INTO rezervacija (polnjenje_konec, polnjenje_zacetek, id_polnilna_postaja, id_uporabnik) VALUES ('2020-10-22 5:0:0', '2020-10-22 4:0.0', 3, 4);
INSERT INTO rezervacija (polnjenje_konec, polnjenje_zacetek, id_polnilna_postaja, id_uporabnik) VALUES ('2020-10-22 5:0:0', '2020-10-22 4:0.0', 2, 4);
INSERT INTO rezervacija (polnjenje_konec, polnjenje_zacetek, id_polnilna_postaja, id_uporabnik) VALUES ('2020-10-22 5:0:0', '2020-10-22 4:0.0', 2, 2);

INSERT INTO racun (koncnacena, id_rezervacija) VALUES (20.5, 1);
INSERT INTO racun (koncnacena, id_rezervacija) VALUES (50, 3);
INSERT INTO racun (koncnacena, id_rezervacija) VALUES (103.72, 4);
INSERT INTO racun (koncnacena, id_rezervacija) VALUES (98.54, 6);
INSERT INTO racun (koncnacena, id_rezervacija) VALUES (23.56, 1);
INSERT INTO racun (koncnacena, id_rezervacija) VALUES (154, 4);
