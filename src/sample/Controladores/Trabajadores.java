package sample.Controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.objetos.Trabajador;

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
    javafx.scene.control.TableView<Trabajador> table_trabajador;
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

        //columnas
        TableColumn<Trabajador,Integer> id_Columna =new TableColumn<>("id");
        id_Columna.setMinWidth(200);
        id_Columna.setCellValueFactory(new PropertyValueFactory<>("id"));
        //columnas
        TableColumn<Trabajador,String> nombre_Columna =new TableColumn<>("nombre");
        nombre_Columna.setMinWidth(200);
        nombre_Columna.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        table_trabajador= new javafx.scene.control.TableView<>();
        table_trabajador.setItems(getTrabajos());
        table_trabajador.getColumns().addAll(id_Columna,nombre_Columna);

    }

    public  ObservableList<Trabajador> getTrabajos(){
        ObservableList<Trabajador> trabajores= FXCollections.observableArrayList();
        trabajores.add(new Trabajador(1,"Antonio"));
        trabajores.add(new Trabajador(2,"Juan"));
        trabajores.add(new Trabajador(3,"Felipe"));
        trabajores.add(new Trabajador(4,"Ricardo"));
        trabajores.add(new Trabajador(5,"Luis"));
        trabajores.add(new Trabajador(6,"Pedro"));
        return trabajores;
    }

    public void click_faltas(MouseEvent mouseEvent) {
        System.out.printf("se seleccionó faltas");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
