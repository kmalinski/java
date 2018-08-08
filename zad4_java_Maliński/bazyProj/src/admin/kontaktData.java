package admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class kontaktData {

    private final SimpleIntegerProperty ID;
    private final SimpleStringProperty telefon;
    private final SimpleStringProperty email;
    private final SimpleIntegerProperty id_producent;
    private final SimpleIntegerProperty id_pracownik;

    public kontaktData(int id, String telefon, String email, int idProd, int idPrac) {
        this.ID = new SimpleIntegerProperty(id);
        this.telefon = new SimpleStringProperty(telefon);
        this.email = new SimpleStringProperty(email);
        this.id_producent = new SimpleIntegerProperty(idProd);
        this.id_pracownik = new SimpleIntegerProperty(idPrac);
    }

    public Integer getId() {
        return this.ID.get();
    }

    public SimpleIntegerProperty IDProperty() {
        return ID;
    }
    public void setID(int id){
        this.ID.set(id);
    }

    public String getTelefon() {
        return this.telefon.get();
    }

    public SimpleStringProperty telefonProperty() {
        return telefon;
    }
    public void setTelefon(String telefon) {
        this.telefon.set(telefon);
    }
    public String getEmail(){
        return this.email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }
    public void setEmail(String email){
        this.email.set(email);
    }

    public int getId_producent(){
        return this.id_producent.get();
    }

    public SimpleIntegerProperty id_producentProperty() {
        return id_producent;
    }
    public void setId_producent(int id) {
        this.id_producent.set(id);
    }
    public Integer getId_pracownik(){
        return this.id_pracownik.get();
    }

    public SimpleIntegerProperty id_pracownikProperty() {
        return id_pracownik;
    }
    public void setId_pracownik(int id){
        this.id_pracownik.set(id);
    }
}
