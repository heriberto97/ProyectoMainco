package sample.Controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.text.TableView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Trabajadores implements Initializable {
    @FXML
    TitledPane panel_trabajadores;
    @FXML
    TitledPane panel_faltas;
    @FXML
    TitledPane panel_prestamos;
    @FXML
    ListView<String> listview_trabajadores = new ListView<>();

    @FXML
    Button btn_prueba;

    @FXML
    BorderPane paneCambio;

    private Stage stage;

    ObservableList<String> list;
    public void click_trabajador(MouseEvent mouseEvent) {
        List<String> nombres= new ArrayList<>();

        nombres.add("juan");
        nombres.add("jose");
        nombres.add("pedro");
        nombres.add("antonio");

        System.out.println("se seleccionó trabajadores");
            list= FXCollections.observableList(nombres);
        listview_trabajadores.setItems(list);

       // Trabajador trabajador= new Trabajador(this.paneCambio);
         //   paneCambio.setCenter(trabajador);
        try {
            setBtn_prueba();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void setBtn_prueba() throws IOException{
        stage = (Stage)  btn_prueba.getScene().getWindow();
        BorderPane root;
        root = (BorderPane) FXMLLoader.load(getClass().getResource("plantillas/Trabajador.fxml"));
        Scene scene = new Scene(root);
       // scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setScene(scene);
        System.out.println("Register.fxml opened");
    }

    public void click_faltas(MouseEvent mouseEvent) {
        System.out.printf("se seleccionó faltas");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
