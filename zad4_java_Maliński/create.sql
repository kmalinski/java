
CREATE TABLE kategoria (
  id_kategoria INTEGER NOT NULL,
  nazwa VARCHAR(25),
  opis VARCHAR(100),
  modyfikacja VARCHAR(20),
  data DATE
);
ALTER TABLE kategoria ADD CONSTRAINT kat_PK PRIMARY KEY ( id_kategoria ) ;
ALTER TABLE kategoria modify column id_kategoria INTEGER NOT NULL AUTO_INCREMENT;

CREATE TABLE producent (
  id_producent INTEGER NOT NULL,
  nazwa VARCHAR(25),
  imie VARCHAR(20),
  nazwisko VARCHAR(20),
  dodatkowe_informacje VARCHAR(100)
);
ALTER TABLE producent ADD CONSTRAINT prodc_PK PRIMARY KEY ( id_producent ) ;
ALTER TABLE producent modify column id_producent INTEGER NOT NULL AUTO_INCREMENT;

CREATE TABLE produkt (
  id_produkt INTEGER NOT NULL,
  nazwa VARCHAR(25),
  cena FLOAT,
  opis VARCHAR(100),
  producent INTEGER,
  dostepny VARCHAR(5)
);
ALTER TABLE produkt ADD CONSTRAINT p_PK PRIMARY KEY ( id_produkt ) ;
ALTER TABLE produkt modify column id_produkt INTEGER NOT NULL AUTO_INCREMENT;

CREATE TABLE pracownik (
  id_pracownik INTEGER NOT NULL,
  imie VARCHAR(25),
  nazwisko VARCHAR(20),
  data_urodzenia DATE,
  pesel VARCHAR(11)
);
ALTER TABLE pracownik ADD CONSTRAINT pracownik_PK PRIMARY KEY ( id_pracownik ) ;
ALTER TABLE pracownik modify column id_pracownik INTEGER NOT NULL AUTO_INCREMENT;

CREATE TABLE kategoria_produktu (
  produkt_id_produkt INTEGER NOT NULL,
  id_kategoria INTEGER NOT NULL
);
ALTER TABLE kategoria_produktu ADD CONSTRAINT katpp_PK PRIMARY KEY ( produkt_id_produkt, id_kategoria ) ;


CREATE TABLE kontakt (
  id_kontakt INTEGER NOT NULL,
  telefon VARCHAR(12),
  email VARCHAR(30),
  id_producent INTEGER,
  id_pracownik INTEGER
);
ALTER TABLE kontakt ADD CONSTRAINT kon_PK PRIMARY KEY ( id_kontakt ) ;
ALTER TABLE kontakt modify column id_kontakt INTEGER NOT NULL AUTO_INCREMENT;

CREATE TABLE konto (
  id_konto INTEGER NOT NULL,
  username VARCHAR(25),
  password VARCHAR(100)
);
ALTER TABLE konto ADD CONSTRAINT konto_PK PRIMARY KEY ( id_konto ) ;
ALTER TABLE konto modify column id_konto INTEGER NOT NULL AUTO_INCREMENT;
