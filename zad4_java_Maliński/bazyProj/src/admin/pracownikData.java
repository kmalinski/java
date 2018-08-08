package admin;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class pracownikData {

    private final SimpleIntegerProperty ID;
    private  final SimpleStringProperty imie;
    private final SimpleStringProperty nazwisko;
    private final SimpleStringProperty data_urodzenia;
    private final SimpleStringProperty pesel;

    public pracownikData(int id, String imie, String nazwisko, String data_urodzenia, String pesel) {
        this.ID = new SimpleIntegerProperty(id);
        this.imie = new SimpleStringProperty(imie);
        this.nazwisko = new SimpleStringProperty(nazwisko);
        this. data_urodzenia = new SimpleStringProperty(data_urodzenia);
        this.pesel = new SimpleStringProperty(pesel);
    }

    public Integer getId() {
        return this.ID.get();
    }
    public SimpleIntegerProperty idProperty() {
        return ID;
    }
    public void setID(int id){
        this.ID.set(id);
    }

    public String getImie(){
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

    public String getData_urodzenia() {
        return this.data_urodzenia.get();
    }

    public SimpleStringProperty data_urodzeniaProperty() {
        return data_urodzenia;
    }
    public void setData_urodzenia(String data_urodzenia){
        this.data_urodzenia.set(data_urodzenia);
    }

    public String getPesel(){
        return this.pesel.get();
    }

    public SimpleStringProperty peselProperty() {
        return pesel;
    }
    public void setPesel(String pesel) {
        this.pesel.set(pesel);
    }
}
