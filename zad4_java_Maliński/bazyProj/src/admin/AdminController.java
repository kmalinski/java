package admin;

import dbConnection.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.event.ActionEvent;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminController implements Initializable{

    @FXML
    private TextField  idField, nazwaField, cenaField, opisField, prodField, dostField;
    @FXML
    private TextField idFieldKP, id2FieldKP;
    @FXML
    private TextField idFieldPr, nazwaFieldPr, imieFieldPr,nazwiskoFieldPr, dodInfFieldPr;
    @FXML
    private TextField idFieldPrac, imieFieldPrac, nazwiskoFieldPrac, dataFieldPrac, peselFieldPrac;
    @FXML
    private TextField idFieldK, telFieldK, emailFieldK, prodFieldK, pracFieldK;
    @FXML
    private DatePicker dataFieldKat;
    @FXML
    private TextField  idField1, nazwaFieldKat, opisFieldKat, modyfikacjaFieldKat;

    ObservableList gCombo = FXCollections.observableArrayList();
    @FXML
    private TableView<ProduktData> listOfProducts;

    @FXML
    private TableColumn<ProduktData, Integer> idColumn;
    @FXML
    private TableColumn<ProduktData, String> nazwaColumn;
    @FXML
    private TableColumn<ProduktData, Float> cenaColumn;
    @FXML
    private TableColumn<ProduktData, String> opisColumn;
    @FXML
    private TableColumn<ProduktData, Integer> prodColumn;
    @FXML
    private TableColumn<ProduktData, String> dostColumn;

    @FXML
    private TableView<kategoriaData> listOfKat;
    @FXML
    private TableColumn<kategoriaData, Integer> idColumnKat;
    @FXML
    private TableColumn<kategoriaData, String> nazwaColumnKat;
    @FXML
    private TableColumn<kategoriaData, String> opisColumnKat;
    @FXML
    private TableColumn<kategoriaData, String > modColumnKat;
    @FXML
    private TableColumn<kategoriaData, String> dataColumnKat;

    @FXML
    private TableView<producentData> listOfProducents;
    @FXML
    private TableColumn<producentData, Integer> idColumnPr;
    @FXML
    private TableColumn<producentData, String> nazwaColumnPr;
    @FXML
    private TableColumn<producentData, String> imieColumnPr;
    @FXML
    private TableColumn<producentData, String> nazwiskoColumnPr;
    @FXML
    private TableColumn<producentData, String> dodInfColumnPr;


    @FXML
    private TableView<kategoria_produktuData> listOfProdKat;
    @FXML
    private TableColumn<kategoria_produktuData, Integer> idColumnKP;
    @FXML
    private TableColumn<kategoria_produktuData, Integer> katColumnKP;
    @FXML
    private TableColumn<kategoria_produktuData, Integer> prodColumnKP;

    @FXML
    private TableView<pracownikData> listOfPrac;
    @FXML
    private TableColumn<pracownikData, Integer> idColumnPrac;
    @FXML
    private TableColumn<pracownikData, String> imieColumnPrac;
    @FXML
    private TableColumn<pracownikData, String> nazwiskoColumnPrac;
    @FXML
    private TableColumn<pracownikData, String> dataColumnPrac;
    @FXML
    private TableColumn<pracownikData, String> peselColumnPrac;

    @FXML
    private TableView<kontaktData> listOfKon;
    @FXML
    private TableColumn<kontaktData, Integer> idColumnK;
    @FXML
    private TableColumn<kontaktData, String>  telColumnK;
    @FXML
    private TableColumn<kontaktData, String> emailColumnK;
    @FXML
    private TableColumn<kontaktData, Integer> prodColumnK;
    @FXML
    private TableColumn<kontaktData, Integer> pracColumnK;

    private dbConnection db;
    @FXML
    private ObservableList<ProduktData> produktList;
    @FXML
    private ObservableList<kategoriaData> katList;
    @FXML
    private ObservableList<kategoria_produktuData> prodKatList;
    @FXML
    private ObservableList<producentData> producList;
    @FXML
    private ObservableList<pracownikData> pracList;
    @FXML
    private ObservableList<kontaktData> konList;
    @FXML
    Tab kat_tab;
    @FXML
    Tab produkty_tab;
    @FXML
    Tab prodKatTab;
    @FXML
    Tab producentTab;
    @FXML
    Tab pracownikTab;
    @FXML
    Tab kontaktTab;
    @FXML
    private Button listAllProduktBtn, listKatBtn, listAllProdKatBtn, listAllPrBtn, listAllPracBtn, listAllKonBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.db = new dbConnection();



    ObservableList<ProduktData> selectedRow = listOfProducts.getSelectionModel().getSelectedItems();
    selectedRow.addListener(new ListChangeListener<ProduktData>() {
        @Override
        public void onChanged(Change c) throws NullPointerException {

            try {
                ProduktData selectedR = listOfProducts.getSelectionModel().getSelectedItem();
                nazwaField.setText(selectedR.getNazwa());
                //cenaField.setText(selectedR.getCena());
                dostField.setText(selectedR.getDostepny());
                opisField.setText(selectedR.getOpis());
                //prodField.setValue(selectedR.getProducent());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            } catch (NullPointerException e) {
              idField.setText(null);
              nazwaField.setText(null);
              cenaField.setText(null);
              dostField.setText(null);
              prodField.setText(null);
              opisField.setText(null);
            }
        }
    });
    }
    private int idCounter = 1;
    @FXML
    public void loadProduktData(ActionEvent actionEvent) {
        String query = "SELECT * FROM produkt WHERE 1";
        try {
            Connection conn = dbConnection.getConnection();
            this.produktList = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery(query);

            while(rs.next()) {
                this.produktList.add(new ProduktData(
                                Integer.valueOf(idCounter++),
                                rs.getString(2),
                                rs.getFloat(3),
                                rs.getString(6),
                                rs.getString(4),
                                rs.getInt(5)

                        )
                );
            }
        } catch (SQLException er) {
            er.printStackTrace();
        }
        this.idColumn.setCellValueFactory(new PropertyValueFactory<ProduktData, Integer>("ID"));
        this.nazwaColumn.setCellValueFactory(new PropertyValueFactory<ProduktData, String>("nazwa"));
        this.cenaColumn.setCellValueFactory(new PropertyValueFactory<ProduktData, Float>("cena"));
        this.dostColumn.setCellValueFactory(new PropertyValueFactory<ProduktData, String>("dostepny"));
        this.opisColumn.setCellValueFactory(new PropertyValueFactory<ProduktData, String>("opis"));
        this.prodColumn.setCellValueFactory(new PropertyValueFactory<ProduktData, Integer>("producent"));

        this.listOfProducts.setItems(null);
        this.listOfProducts.setItems(this.produktList);
        idCounter = 1;
    }

    @FXML
    public void addProdukt(ActionEvent actionEvent) {
        String insertQuery = "insert into produkt(nazwa, cena, dostepny, opis, producent) " + "values(?, ?, ?, ?, ?)";
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stm  = conn.prepareStatement(insertQuery);

            stm.setString(1, String.valueOf(AdminController.this.nazwaField.getText()));
            stm.setFloat(2, Float.valueOf(AdminController.this.cenaField.getText()));
            stm.setString(3, String.valueOf(AdminController.this.dostField.getText()));
            stm.setString(4, String.valueOf(AdminController.this.opisField.getText()));

            PreparedStatement ps = conn.prepareStatement("SELECT id_producent FROM producent WHERE nazwa=?");
            ps.setString(1, AdminController.this.prodField.getText());
            ResultSet rs = ps.executeQuery();
            int id=0;
            while(rs.next()){
                id=rs.getInt(1);
            }
            stm.setInt(5, id);

            stm.execute();
            conn.close();


        }   catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        listAllProduktBtn.fire();
    }

    @FXML
    private void clearFields(ActionEvent event) {
        this.idField.setText("");
        this.nazwaField.setText("");
        this.cenaField.setText("");
        this.dostField.setText("");
        this.opisField.setText("");
        this.prodField.setText("");
    }

    @FXML
    public void deleteProdukt(ActionEvent event) {
        ProduktData getSelectedRow = listOfProducts.getSelectionModel().getSelectedItem();
        try {
            Connection conn = dbConnection.getConnection();

            if(!getSelectedRow.toString().equals("")) {
                String nazwa = getSelectedRow.getNazwa();
                int sID = 0;

                PreparedStatement ps = conn.prepareStatement("SELECT id_produkt FROM produkt where nazwa = ?");
                ps.setString(1, nazwa);
                ResultSet res = ps.executeQuery();

                while(res.next()) {
                    sID = res.getInt(1);
                }

                String deleteQuery = "delete from produkt where id_produkt=" + sID;
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Potwierdz usuwanie");
                alert.setHeaderText("Czy na pewno chcesz usunac ten produkt");
                alert.setContentText("" + getSelectedRow.getNazwa());

                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK) {
                    PreparedStatement stm = conn.prepareStatement(deleteQuery);
                    stm.execute();

                    listAllProduktBtn.fire();
                } else {

                }
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editProdukt(ActionEvent event) {
        String updateQuery = "update produkt set nazwa=?, cena=?, dostepny=?, opis=?, producent=? where id_produkt=?";
        ProduktData getSelectedRow = listOfProducts.getSelectionModel().getSelectedItem();

        try{
            Connection conn = dbConnection.getConnection();
            PreparedStatement stm = conn.prepareStatement(updateQuery);

            String nazwa = getSelectedRow.getNazwa();
            int sID = 0;

            PreparedStatement ps = conn.prepareStatement("SELECT id_produkt FROM produkt where nazwa = ?");
            ps.setString(1, nazwa);
            ResultSet res = ps.executeQuery();

            while(res.next()) {
                sID = res.getInt(1);
            }

            stm.setString(1, String.valueOf(AdminController.this.nazwaField.getText()));
            stm.setFloat(2, Float.valueOf(AdminController.this.cenaField.getText()));
            stm.setString(3, String.valueOf(AdminController.this.dostField.getText()));
            stm.setString(4, String.valueOf(AdminController.this.opisField.getText()));
            stm.setInt(5, Integer.valueOf(AdminController.this.prodField.getText()));
            stm.setInt(6, sID);

            stm.execute();

            conn.close();

        }   catch(SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        listAllProduktBtn.fire();
    }
    /**********************
     * KATEGORIE
     */

    @FXML
    public void loadKatData(ActionEvent actionEvent) {
        String query = "SELECT * FROM kategoria WHERE 1";
        try {
            Connection conn = dbConnection.getConnection();
            this.katList = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery(query);

            while(rs.next()) {
                this.katList.add(new kategoriaData(
                        Integer.valueOf(idCounter++),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                ));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        this.idColumnKat.setCellValueFactory(new PropertyValueFactory<kategoriaData, Integer>("ID"));
        this.nazwaColumnKat.setCellValueFactory(new PropertyValueFactory<kategoriaData, String>("nazwa"));
        this.opisColumnKat.setCellValueFactory(new PropertyValueFactory<kategoriaData, String>("opis"));
        this.modColumnKat.setCellValueFactory(new PropertyValueFactory<kategoriaData, String>("modyfikacja"));
        this.dataColumnKat.setCellValueFactory(new PropertyValueFactory<kategoriaData, String>("data"));

        this.listOfKat.setItems(null);
        this.listOfKat.setItems(this.katList);

        idCounter = 1;
    }

    @FXML
    public void clearKatField(ActionEvent event) {
        this.nazwaFieldKat.setText("");
        this.opisFieldKat.setText("");
        this.modyfikacjaFieldKat.setText("");
        // this.dataFieldKat.setText("");
    }

    @FXML
    public void deleteKategoria(ActionEvent event) {
        kategoriaData getSelectedRow = listOfKat.getSelectionModel().getSelectedItem();
        try {
            Connection conn = dbConnection.getConnection();

            if(!getSelectedRow.toString().equals("")) {
                String nazwa = getSelectedRow.getNazwa();
                int sID = 0;

                PreparedStatement ps = conn.prepareStatement("SELECT id_kategoria FROM kategoria where nazwa = ?");
                ps.setString(1, nazwa);
                ResultSet res = ps.executeQuery();

                while(res.next()) {
                    sID = res.getInt(1);
                }

                String deleteQuery = "delete from kategoria where id_kategoria=" + sID;
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Potwierdz usuwanie");
                alert.setHeaderText("Czy na pewno chcesz usunac ta kategorie");
                alert.setContentText("" + getSelectedRow.getNazwa());

                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK) {
                    PreparedStatement stm = conn.prepareStatement(deleteQuery);
                    stm.execute();

                    listKatBtn.fire();
                } else {

                }
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void addKategoria(ActionEvent actionEvent) {
        String insertQuery = "insert into kategoria(nazwa, opis, modyfikacja, data) " + "values(?, ?, ?, ?)";
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stm  = conn.prepareStatement(insertQuery);

            stm.setString(1, String.valueOf(AdminController.this.nazwaFieldKat.getText()));
            stm.setString(2, String.valueOf(AdminController.this.opisFieldKat.getText()));
            stm.setString(3, String.valueOf(AdminController.this.modyfikacjaFieldKat.getText()));

            String data = null;
            data = AdminController.this.dataFieldKat.getEditor().getText();
            // data.substring(0, 4);
            String y = data.substring(6, 10);
            String d = data.substring(1, 2);
            String m = data.substring(4, 5);
            stm.setString(4, y + "-" + m + "-" + d);

            stm.execute();
            conn.close();

        }   catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        listKatBtn.fire();
    }
    @FXML
    public void editKat(ActionEvent event) {
        String updateQuery = "update kategoria set nazwa=?, opis=?, modyfikacja=?, data=? where id_kategoria=?";
        kategoriaData getSelectedRow = listOfKat.getSelectionModel().getSelectedItem();

        try{
            Connection conn = dbConnection.getConnection();
            PreparedStatement stm = conn.prepareStatement(updateQuery);

            String nazwa = getSelectedRow.getNazwa();
            int sID = 0;

            PreparedStatement ps = conn.prepareStatement("SELECT id_kategoria FROM kategoria where nazwa = ?");
            ps.setString(1, nazwa);
            ResultSet res = ps.executeQuery();

            while(res.next()) {
                sID = res.getInt(1);
            }

            stm.setString(1, String.valueOf(AdminController.this.nazwaFieldKat.getText()));
            stm.setString(2, String.valueOf(AdminController.this.opisFieldKat.getText()));
            stm.setString(3, String.valueOf(AdminController.this.modyfikacjaFieldKat.getText()));
            stm.setInt(5, sID);

            String data = null;
            data = AdminController.this.dataFieldKat.getEditor().getText();
            String y = data.substring(6, 10);
            String d = data.substring(1, 2);
            String m = data.substring(4, 5);
            stm.setString(4, y + "-" + m + "-" + d);
            stm.execute();

            conn.close();

        }   catch(SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        listKatBtn.fire();
    }

    /**********************
     * KATEGORIE PRODUKTU
     */

    @FXML
    public void loadKatProdData(ActionEvent actionEvent) {
        String query = "SELECT * FROM kategoria_produktu WHERE 1";
        try {
            Connection conn = dbConnection.getConnection();
            this.prodKatList = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery(query);

            while(rs.next()) {
                this.prodKatList.add(new kategoria_produktuData(
                        rs.getInt(1),
                        rs.getInt(2)
                ));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        this.prodColumnKP.setCellValueFactory(new PropertyValueFactory<kategoria_produktuData, Integer>("id_produkt"));
        this.katColumnKP.setCellValueFactory(new PropertyValueFactory<kategoria_produktuData, Integer>("id_kategoria"));

        this.listOfProdKat.setItems(null);
        this.listOfProdKat.setItems(this.prodKatList);

        idCounter = 1;
    }

    @FXML
    public void clearKatProdField(ActionEvent event) {
        this.idFieldKP.setText("");
        this.id2FieldKP.setText("");
    }

    @FXML
    public void deleteKategoriaProduktu(ActionEvent event) {
        kategoria_produktuData getSelectedRow = listOfProdKat.getSelectionModel().getSelectedItem();
        try {
            Connection conn = dbConnection.getConnection();

            if(!getSelectedRow.toString().equals("")) {
                String deleteQuery = "delete from kategoria_produktu where id_kategoria=? and produkt_id_produkt=?";
                PreparedStatement stm = conn.prepareStatement(deleteQuery);
                stm.setInt(1, getSelectedRow.getId_kategoria());
               stm.setInt(2, getSelectedRow.getId_produkt());

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Potwierdz usuwanie");
                alert.setHeaderText("Czy na pewno chcesz usunac");
                //alert.setContentText("" + getSelectedRow.getNazwa());

                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK) {
                    //PreparedStatement stm = conn.prepareStatement(deleteQuery);
                    stm.execute();

                    listAllProdKatBtn.fire();
                } else {

                }
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void addKatProd(ActionEvent actionEvent) {
        String insertQuery = "insert into kategoria_produktu(produkt_id_produkt, id_kategoria) " + "values(?, ?)";
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stm  = conn.prepareStatement(insertQuery);

            PreparedStatement ps = conn.prepareStatement("SELECT id_kategoria FROM kategoria where nazwa=?");
            ps.setString(1, String.valueOf(AdminController.this.idFieldKP.getText()));
            ResultSet rs = ps.executeQuery();
            int id=0;
            while(rs.next()){
                id=rs.getInt(1);
            }

            stm.setInt(1, id);

            PreparedStatement pss = conn.prepareStatement("SELECT id_produkt FROM produkt where nazwa=?");
            pss.setString(1, String.valueOf(AdminController.this.id2FieldKP.getText()));
            ResultSet rss = pss.executeQuery();
            while(rss.next()) {
                id=rss.getInt(1);
            }
            stm.setInt(2, id);
            //stm.setString(3, String.valueOf(AdminController.this.modyfikacjaFieldKat.getText()));


            stm.execute();
            conn.close();

        }   catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        listAllProdKatBtn.fire();
    }
    @FXML
    public void ediProdtKat(ActionEvent event) {
        String updateQuery = "update kategoria_produktu set produkt_id_produkt=?, id_kategoria=? where produkt_id_produkt=? and id_kategoria=?";
        kategoria_produktuData getSelectedRow = listOfProdKat.getSelectionModel().getSelectedItem();

        try{
            Connection conn = dbConnection.getConnection();
            PreparedStatement stm = conn.prepareStatement(updateQuery);

            int idP = getSelectedRow.getId_produkt();
            int idK = getSelectedRow.getId_kategoria();


            stm.setInt(1, Integer.valueOf(AdminController.this.idFieldKP.getText()));
            stm.setInt(2, Integer.valueOf(AdminController.this.id2FieldKP.getText()));
            stm.setInt(3, idP);
            stm.setInt(4, idK);

            stm.execute();

            conn.close();

        }   catch(SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        listAllProdKatBtn.fire();
    }

    /***************
     *
     * PRODUCENT
     *****************/


    @FXML
    public void loadProducData(ActionEvent actionEvent) {
        String query = "SELECT * FROM producent WHERE 1";
        try {
            Connection conn = dbConnection.getConnection();
            this.producList = FXCollections.observableArrayList();
            idCounter = 1;

            ResultSet rs = conn.createStatement().executeQuery(query);

            while(rs.next()) {
                this.producList.add(new producentData(
                                Integer.valueOf(idCounter++),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5)
                                //rs.getInt(5)

                        )
                );
            }
        } catch (SQLException er) {
            er.printStackTrace();
        }
        this.idColumnPr.setCellValueFactory(new PropertyValueFactory<producentData, Integer>("ID"));
        this.nazwaColumnPr.setCellValueFactory(new PropertyValueFactory<producentData, String>("nazwa"));
        this.imieColumnPr.setCellValueFactory(new PropertyValueFactory<producentData, String>("imie"));
        this.nazwiskoColumnPr.setCellValueFactory(new PropertyValueFactory<producentData, String>("nazwisko"));
        this.dodInfColumnPr.setCellValueFactory(new PropertyValueFactory<producentData, String>("dodatkowe_informacje"));

        this.listOfProducents.setItems(null);
        this.listOfProducents.setItems(this.producList);
        idCounter = 1;
    }

    @FXML
    public void addProduc(ActionEvent actionEvent) {
        String insertQuery = "insert into producent(nazwa, imie, nazwisko, dodatkowe_informacje) " + "values(?, ?, ?, ?)";
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stm  = conn.prepareStatement(insertQuery);

            stm.setString(1, String.valueOf(AdminController.this.nazwaFieldPr.getText()));
            stm.setString(2, String.valueOf(AdminController.this.imieFieldPr.getText()));
            stm.setString(3, String.valueOf(AdminController.this.nazwiskoFieldPr.getText()));
            stm.setString(4, String.valueOf(AdminController.this.dodInfFieldPr.getText()));

            stm.execute();
            conn.close();


        }   catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        listAllPrBtn.fire();
    }

    @FXML
    private void clearProducFields(ActionEvent event) {
        this.idFieldPr.setText("");
        this.nazwaFieldPr.setText("");
        this.imieFieldPr.setText("");
        this.nazwiskoFieldPr.setText("");
        this.dodInfFieldPr.setText("");
    }

    @FXML
    public void deleteProduc(ActionEvent event) {
        producentData getSelectedRow = listOfProducents.getSelectionModel().getSelectedItem();
        try {
            Connection conn = dbConnection.getConnection();

            if(!getSelectedRow.toString().equals("")) {
                String nazwa = getSelectedRow.getNazwa();
                int sID = 0;

                PreparedStatement ps = conn.prepareStatement("SELECT id_producent FROM producent where nazwa = ?");
                ps.setString(1, nazwa);
                ResultSet res = ps.executeQuery();

                while(res.next()) {
                    sID = res.getInt(1);
                }

                String deleteQuery = "delete from producent where id_producent=" + sID;
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Potwierdz usuwanie");
                alert.setHeaderText("Czy na pewno chcesz usunac");
                //alert.setContentText("" + getSelectedRow.getNazwa());

                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK) {
                    PreparedStatement stm = conn.prepareStatement(deleteQuery);
                    stm.execute();

                    listAllPrBtn.fire();
                } else {

                }
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editProduc(ActionEvent event) {
        String updateQuery = "update producent set nazwa=?, imie=?, nazwisko=?, dodatkowe_informacje=? where id_producent=?";
        producentData getSelectedRow = listOfProducents.getSelectionModel().getSelectedItem();

        try{
            Connection conn = dbConnection.getConnection();
            PreparedStatement stm = conn.prepareStatement(updateQuery);
            String nazwa = getSelectedRow.getNazwa();
            int sID = 0;

            PreparedStatement ps = conn.prepareStatement("SELECT id_producent FROM producent where nazwa = ?");
            ps.setString(1, nazwa);
            ResultSet res = ps.executeQuery();

            while(res.next()) {
                sID = res.getInt(1);
            }

            stm.setString(1, String.valueOf(AdminController.this.nazwaFieldPr.getText()));
            stm.setString(2, String.valueOf(AdminController.this.imieFieldPr.getText()));
            stm.setString(3, String.valueOf(AdminController.this.nazwaFieldPr.getText()));
            stm.setString(4, String.valueOf(AdminController.this.dodInfFieldPr.getText()));
            stm.setInt(5, sID);

            stm.execute();

            conn.close();

        }   catch(SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        listAllPrBtn.fire();
    }

    /***************
     *
     * PRACOWNIK
     *****************/


    @FXML
    public void loadPracData(ActionEvent actionEvent) {
        String query = "SELECT * FROM pracownik WHERE 1";
        try {
            Connection conn = dbConnection.getConnection();
            this.pracList = FXCollections.observableArrayList();
            idCounter = 1;

            ResultSet rs = conn.createStatement().executeQuery(query);

            while(rs.next()) {
                this.pracList.add(new pracownikData(
                                Integer.valueOf(idCounter++),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5)
                                //rs.getInt(5)

                        )
                );
            }
        } catch (SQLException er) {
            er.printStackTrace();
        }
        this.idColumnPrac.setCellValueFactory(new PropertyValueFactory<pracownikData, Integer>("ID"));
        this.imieColumnPrac.setCellValueFactory(new PropertyValueFactory<pracownikData, String>("imie"));
        this.nazwiskoColumnPrac.setCellValueFactory(new PropertyValueFactory<pracownikData, String>("nazwisko"));
        this.dataColumnPrac.setCellValueFactory(new PropertyValueFactory<pracownikData, String>("data_urodzenia"));
        this.peselColumnPrac.setCellValueFactory(new PropertyValueFactory<pracownikData, String>("pesel"));

        this.listOfPrac.setItems(null);
        this.listOfPrac.setItems(this.pracList);
        idCounter = 1;
    }

    @FXML
    public void addPrac(ActionEvent actionEvent) {
        String insertQuery = "insert into pracownik(imie, nazwisko, data_urodzenia, pesel) " + "values(?, ?, ?, ?)";
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stm  = conn.prepareStatement(insertQuery);

            stm.setString(1, String.valueOf(AdminController.this.imieFieldPrac.getText()));
            stm.setString(2, String.valueOf(AdminController.this.nazwiskoFieldPrac.getText()));
            stm.setString(3, String.valueOf(AdminController.this.dataFieldPrac.getText()));
            stm.setString(4, String.valueOf(AdminController.this.peselFieldPrac.getText()));

            stm.execute();
            conn.close();


        }   catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        listAllPracBtn.fire();
    }

    @FXML
    private void clearPracFields(ActionEvent event) {
        this.idFieldPrac.setText("");
        this.imieFieldPrac.setText("");
        this.nazwiskoFieldPrac.setText("");
        this.dataFieldPrac.setText("");
        this.peselFieldPrac.setText("");
    }

    @FXML
    public void deletePrac(ActionEvent event) {
        pracownikData getSelectedRow = listOfPrac.getSelectionModel().getSelectedItem();
        try {
            Connection conn = dbConnection.getConnection();

            if(!getSelectedRow.toString().equals("")) {
                String pesel = getSelectedRow.getPesel();
                int sID = 0;

                PreparedStatement ps = conn.prepareStatement("SELECT id_pracownik FROM pracownik where pesel = ?");
                ps.setString(1, pesel);
                ResultSet res = ps.executeQuery();

                while(res.next()) {
                    sID = res.getInt(1);
                }
                //ystem.out.println(sID);

                String deleteQuery = "delete from pracownik where id_pracownik=" + sID;
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Potwierdz usuwanie");
                alert.setHeaderText("Czy na pewno chcesz usunac");
                //alert.setContentText("" + getSelectedRow.getNazwa());

                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK) {
                    PreparedStatement stm = conn.prepareStatement(deleteQuery);
                    stm.execute();

                    listAllPracBtn.fire();
                } else {

                }
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editPrac(ActionEvent event) {
        String updateQuery = "update pracownik set imie=?, nazwisko=?, data_urodzenia=?, pesel=? where id_pracownik=?";
        pracownikData getSelectedRow = listOfPrac.getSelectionModel().getSelectedItem();

        try{
            Connection conn = dbConnection.getConnection();
            PreparedStatement stm = conn.prepareStatement(updateQuery);
            String pesel = getSelectedRow.getPesel();
            int sID = 0;

            PreparedStatement ps = conn.prepareStatement("SELECT id_pracownik FROM pracownik where pesel = ?");
            ps.setString(1, pesel);
            ResultSet res = ps.executeQuery();

            while(res.next()) {
                sID = res.getInt(1);
            }

            stm.setString(1, String.valueOf(AdminController.this.imieFieldPrac.getText()));
            stm.setString(2, String.valueOf(AdminController.this.nazwiskoFieldPrac.getText()));
            stm.setString(3, String.valueOf(AdminController.this.dataFieldPrac.getText()));
            stm.setString(4, String.valueOf(AdminController.this.peselFieldPrac.getText()));
            stm.setInt(5, sID);

            stm.execute();

            conn.close();

        }   catch(SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        listAllPracBtn.fire();
    }

    /***************
     *
     * KONTAKT
     *****************/


    @FXML
    public void loadKonData(ActionEvent actionEvent) {
        String query = "SELECT * FROM kontakt WHERE 1";
        try {
            Connection conn = dbConnection.getConnection();
            this.konList = FXCollections.observableArrayList();
            idCounter = 1;

            ResultSet rs = conn.createStatement().executeQuery(query);

            while(rs.next()) {
                this.konList.add(new kontaktData(
                                Integer.valueOf(idCounter++),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getInt(4),
                                rs.getInt(5)
                                //rs.getInt(5)

                        )
                );
            }
        } catch (SQLException er) {
            er.printStackTrace();
        }
        this.idColumnK.setCellValueFactory(new PropertyValueFactory<kontaktData, Integer>("ID"));
        this.telColumnK.setCellValueFactory(new PropertyValueFactory<kontaktData, String>("telefon"));
        this.emailColumnK.setCellValueFactory(new PropertyValueFactory<kontaktData, String>("email"));
        this.prodColumnK.setCellValueFactory(new PropertyValueFactory<kontaktData, Integer>("id_producent"));
        this.pracColumnK.setCellValueFactory(new PropertyValueFactory<kontaktData, Integer>("id_pracownik"));

        this.listOfKon.setItems(null);
        this.listOfKon.setItems(this.konList);
        idCounter = 1;
    }

    @FXML
    public void addKon(ActionEvent actionEvent) {
        try {
            Connection conn = dbConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT id_producent FROM producent where nazwa=?");
            String insertQuery = "insert into kontakt(telefon, email, id_producent) " + "values(?, ?, ?)";

            PreparedStatement stm  = conn.prepareStatement(insertQuery);
            stm.setString(1, String.valueOf(AdminController.this.telFieldK.getText()));
            stm.setString(2, String.valueOf(AdminController.this.emailFieldK.getText()));

            ps.setString(1, String.valueOf(AdminController.this.prodFieldK.getText()));
            ResultSet rs = ps.executeQuery();
            int id=0;
            while(rs.next()){
                id=rs.getInt(1);
            }
            stm.setInt(3, id);
            stm.execute();

            conn.close();
        }   catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        listAllKonBtn.fire();
    }

    @FXML
    public void addKon2(ActionEvent actionEvent) {
        try {
            Connection conn = dbConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT id_pracownik FROM pracownik where nazwisko=?");
            String insertQuery = "insert into kontakt(telefon, email, id_pracownik) " + "values(?, ?, ?)";

            PreparedStatement stm  = conn.prepareStatement(insertQuery);
            stm.setString(1, String.valueOf(AdminController.this.telFieldK.getText()));
            stm.setString(2, String.valueOf(AdminController.this.emailFieldK.getText()));

            ps.setString(1, String.valueOf(AdminController.this.pracFieldK.getText()));
            ResultSet rs = ps.executeQuery();
            int id=0;
            while(rs.next()){
                id=rs.getInt(1);
            }
            stm.setInt(3, id);
            stm.execute();

            conn.close();
        }   catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        listAllKonBtn.fire();
    }
    @FXML
    private void clearKonFields(ActionEvent event) {
        this.idFieldK.setText("");
        this.telFieldK.setText("");
        this.emailFieldK.setText("");
        this.prodFieldK.setText("");
        this.pracFieldK.setText("");
    }

    @FXML
    public void deleteKon(ActionEvent event) {
        kontaktData getSelectedRow = listOfKon.getSelectionModel().getSelectedItem();
        try {
            Connection conn = dbConnection.getConnection();

            if(!getSelectedRow.toString().equals("")) {

                String deleteQuery = "delete from kontakt where id_pracownik=? and id_producent=?";
                PreparedStatement stm = conn.prepareStatement(deleteQuery);
                stm.setInt(1, getSelectedRow.getId_pracownik());
                stm.setInt(2, getSelectedRow.getId_producent());

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Potwierdz usuwanie");
                alert.setHeaderText("Czy na pewno chcesz usunac");
                //alert.setContentText("" + getSelectedRow.getNazwa());

                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK) {
                    //PreparedStatement stm = conn.prepareStatement(deleteQuery);
                    stm.execute();

                    listAllKonBtn.fire();
                } else {

                }
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editKon(ActionEvent event) {
        String updateQuery = "update kontakt set telefon=?, email=?, id_producent=?, id_pracownik=? where id_kontakt=?";
        kontaktData getSelectedRow = listOfKon.getSelectionModel().getSelectedItem();

        try{
            Connection conn = dbConnection.getConnection();
            PreparedStatement stm = conn.prepareStatement(updateQuery);
            String id1 = getSelectedRow.getTelefon();
            int sID = 0;

            PreparedStatement ps = conn.prepareStatement("SELECT id_kontakt FROM kontakt where telefon = ?");
            ps.setString(1, id1);
            ResultSet res = ps.executeQuery();

            while(res.next()) {
                sID = res.getInt(1);
            }

            stm.setString(1, String.valueOf(AdminController.this.telFieldK.getText()));
            stm.setString(2, String.valueOf(AdminController.this.emailFieldK.getText()));
            stm.setInt(3, Integer.valueOf(AdminController.this.prodFieldK.getText()));
            stm.setInt(4, Integer.valueOf(AdminController.this.pracFieldK.getText()));
            stm.setInt(5, sID);

            stm.execute();

            conn.close();

        }   catch(SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        listAllKonBtn.fire();
    }

}


