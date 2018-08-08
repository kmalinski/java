INSERT INTO `kategoria` (`id_kategoria`, `nazwa`, `opis`) VALUES (NULL, 'owoce', 'kategoria owocow'), (NULL, 'warzywa', 'kategoria warzyw');

INSERT INTO `producent` (`id_producent`, `nazwa`, `imie`, `nazwisko`, `dodatkowe_informacje`) VALUES (NULL, 'pierwszy producent', 'Jan', 'Kowalski', 'dodatkowe informacje'), (NULL, 'drugi producent', 'Beata', 'Kowalska', 'nie');

INSERT INTO `produkt` (`id_produkt`, `nazwa`, `cena`, `opis`, `producent`, `dostepny`) VALUES (NULL, 'ziemniak', '2', 'warzywko', '1', 'tak'), (NULL, 'jablko', '3', 'owoc', '2', 'nie');

INSERT INTO `kategoria_produktu` (`produkt_id_produkt`, `id_kategoria`) VALUES ('1', '1'), ('2', '2');

INSERT INTO `pracownik` (`id_pracownik`, `imie`, `nazwisko`, `data_urodzenia`, `pesel`) VALUES (NULL, 'Janusz', 'Kowalski', '2018-05-06', '12345678900');

INSERT INTO `kontakt` (`id_kontakt`, `telefon`, `email`, `id_producent`, `id_pracownik`) VALUES (NULL, '123456789', 'jan@gmail.com', '1', NULL), (NULL, '23456789001', 'beata@gmail.com', '2', NULL);

INSERT INTO `kontakt` (`id_kontakt`, `telefon`, `email`, `id_producent`, `id_pracownik`) VALUES (NULL, '453214313', 'janusz@gmail.com', NULL, '1');

INSERT INTO `konto` (`username`, `password`) VALUES ('admin', '123');
