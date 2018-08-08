package admin;

import javafx.beans.property.SimpleIntegerProperty;

public class kategoria_produktuData {

    private final SimpleIntegerProperty id_produkt;
    private final SimpleIntegerProperty id_kategoria;

    public kategoria_produktuData (int idP, int idK) {
        this.id_produkt = new SimpleIntegerProperty(idP);
        this.id_kategoria = new SimpleIntegerProperty(idK);
    }

    public Integer getId_produkt() {
        return id_produkt.get();
    }

    public SimpleIntegerProperty id_produktProperty() {
        return id_produkt;
    }

    public void setId_produkt (int id_produkt) {
        this.id_produkt.set(id_produkt);
    }

    public Integer getId_kategoria() {
        return id_kategoria.get();
    }

    public SimpleIntegerProperty id_kategoriaProperty() {
        return id_kategoria;
    }

    public void setId_kategoria(int id_kategoria) {
        this.id_kategoria.set(id_kategoria);
    }
}
