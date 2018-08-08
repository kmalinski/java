package admin;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class producentData {

    private final SimpleIntegerProperty ID;
    private final SimpleStringProperty nazwa;
    private final SimpleStringProperty imie;
    private final SimpleStringProperty nazwisko;
    private final SimpleStringProperty dodatkowe_informacje;

    public producentData(int id, String nazwa, String imie, String nazwisko, String dodatkowe_informacje){
        this.ID = new SimpleIntegerProperty(id);
        this.nazwa = new SimpleStringProperty(nazwa);
        this.imie = new SimpleStringProperty(imie);
        this.nazwisko = new SimpleStringProperty(nazwisko);
        this.dodatkowe_informacje = new SimpleStringProperty(dodatkowe_informacje);
    }

    public Integer getId() {
        return ID.get();
    }
    public SimpleIntegerProperty IDProperty() {
        return ID;
    }
    public void setID(int id) {
        this.ID.set(id);
    }

    public String getNazwa() {
        return this.nazwa.get();
    }
    public SimpleStringProperty nazwaProperty() {
        return nazwa;
    }
    public void setNazwa(String nazwa) {
        this.nazwa.set(nazwa);
    }

    public String getImie() {
        return this.imie.get();
    }
    public SimpleStringProperty imieProperty() {
        return imie;
    }
    public void setImie(String imie) {
        this.imie.set(imie);
    }

    public String getNazwisko() {
        return this.nazwisko.get();
    }
    public SimpleStringProperty nazwiskoProperty(){
        return nazwisko;
    }
    public void setNazwisko(String nazwisko) {
        this.nazwisko.set(nazwisko);
    }
    public String getDodatkowe_informacje() {
        return this.dodatkowe_informacje.get();
    }
    public SimpleStringProperty dodatkowe_informacjeProperty(){
        return dodatkowe_informacje;
    }
    public void setDodatkowe_informacje(String dodatkowe_informacje) {
        this.dodatkowe_informacje.set(dodatkowe_informacje);
    }
}
