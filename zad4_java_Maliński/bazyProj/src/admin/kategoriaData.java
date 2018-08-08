package admin;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class kategoriaData {

    private final SimpleIntegerProperty ID;
    private final SimpleStringProperty nazwa;
    private final SimpleStringProperty opis;
    private final SimpleStringProperty modyfikacja;
    private final SimpleStringProperty data;

    public kategoriaData (int id, String nazwa, String opis, String modyfikacja, String data) {
        this.ID = new SimpleIntegerProperty(id);
        this.nazwa = new SimpleStringProperty(nazwa);
        this.opis = new SimpleStringProperty(opis);
        this.modyfikacja = new SimpleStringProperty(modyfikacja);
        this.data = new SimpleStringProperty(data);
    }


    public Integer getID() {
        return ID.get();
    }

    public SimpleIntegerProperty IDProperty() {
        return ID;
    }

   public void setID(int id) {
        this.ID.set(id);
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
   public String getOpis() {
        return opis.get();
   }

    public SimpleStringProperty opisProperty() {
        return opis;
    }
    public void setOpis(String opis) {
        this.opis.set(opis);
    }
    public String getModyfikacja(){
        return modyfikacja.get();
    }

    public SimpleStringProperty modyfikacjaProperty() {
        return modyfikacja;
    }
    public void setModyfikacja(String modyfikacja) {
        this.modyfikacja.set(modyfikacja);
    }
    public String getData() {
        return data.get();
    }

    public SimpleStringProperty dataProperty() {
        return data;
    }
    public void setData(String data){
        this.data.set(data);
    }
}
