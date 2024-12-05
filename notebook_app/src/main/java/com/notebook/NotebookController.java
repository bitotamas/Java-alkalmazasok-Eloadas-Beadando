package com.notebook;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NotebookController implements Initializable {

    @FXML private Button databaseButton;
    @FXML private Button parhuzamosButton;
    @FXML private Button soapClientButton;
    @FXML private Button forexButton;
    @FXML private AnchorPane view;

    @FXML


    //Adatbázis (Db_view) betöltése a Main_view view Pane elemébe
    public void loadDatabase(javafx.event.ActionEvent actionEvent) throws IOException {
        AnchorPane selectedView = FXMLLoader.load(getClass().getResource("Db_view.fxml"));
        view.getChildren().setAll(selectedView);
    }
    //SOAP kliens (SOAP_view) betöltése a Main_view view Pane elemébe
    @FXML
    public void loadSoapClient(javafx.event.ActionEvent actionEvent) throws IOException {

    }
    //A szálkezeléses nézet (Parhuzamos_view) betöltése a Main_view view Pane elemébe
    @FXML
    public void loadParhuzamos(javafx.event.ActionEvent actionEvent) throws IOException {
        AnchorPane selectedView = FXMLLoader.load(getClass().getResource("Parhuzamos_view.fxml"));
        view.getChildren().setAll(selectedView);
    }

    @FXML
    public void loadForex(javafx.event.ActionEvent actionEvent) throws IOException {
        AnchorPane selectedView = FXMLLoader.load(getClass().getResource("Forex_view.fxml"));
        view.getChildren().setAll(selectedView);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}