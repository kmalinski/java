package admin;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleFloatProperty;

public class ProduktData {

        private final SimpleIntegerProperty ID;
        private final SimpleStringProperty nazwa;
        private final SimpleFloatProperty cena;
        private final SimpleStringProperty dostepny;
        private final SimpleStringProperty opis;
        private final SimpleIntegerProperty producent;

        public ProduktData (int id, String nazwa, Float cena,  String dostepny, String opis,  int producent){
                this.ID = new SimpleIntegerProperty(id);
                this.nazwa = new SimpleStringProperty(nazwa);
                this.cena = new SimpleFloatProperty(cena);
                this.dostepny = new SimpleStringProperty(dostepny);
                this.opis = new SimpleStringProperty(opis);
                this.producent = new SimpleIntegerProperty(producent);

        }

        public Integer getID() {
                return ID.get();
        }

        public SimpleIntegerProperty IDProperty() {
                return ID;
        }

        public void setID(int ID) {
                this.ID.set(ID);
        }

        public String getNazwa() {
                return nazwa.get();
        }

        public SimpleStringProperty nazwaProperty() {
                return nazwa;
        }

        public void setNazwa(String nazwa) {
                this.nazwa.set(nazwa);
        }

        public Float getCena() {
                return cena.get();
        }

        public SimpleFloatProperty cenaProperty() {
                return cena;
        }

        public void setCena(Float cena) {
                this.cena.set(cena);
        }

        public String getDostepny() {
                return dostepny.get();
        }

        public SimpleStringProperty dostepnyProperty() {
                return dostepny;
        }

        public void setDostepny(String dostepny) {
                this.dostepny.set(dostepny);
        }

        public String getOpis() {
                return opis.get();
        }

        public SimpleStringProperty opisProperty() {
                return opis;
        }

        public void setOpis(String opis) {
                this.opis.set(opis);
        }

        public int getProducent() {
                return producent.get();
        }

        public SimpleIntegerProperty producentProperty() {
                return producent;
        }

        public void setProducent(int producent) {
                this.producent.set(producent);
        }
}
