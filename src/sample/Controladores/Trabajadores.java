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
import javafx.scene.control.TableView;
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
    TableView<Trabajador> table_trabajador= new TableView<>();
    private Stage stage;
    ObservableList<String> list;

        public void click_trabajador(MouseEvent mouseEvent) {

            }

    public  ObservableList<Trabajador> getTrabajos(){
        ObservableList<Trabajador> trabajadores= FXCollections.observableArrayList();
        trabajadores.add(new Trabajador(1,"Antonio"));
        trabajadores.add(new Trabajador(2,"Juan"));
        trabajadores.add(new Trabajador(3,"Felipe"));
        trabajadores.add(new Trabajador(4,"Ricardo"));
        trabajadores.add(new Trabajador(5,"Luis"));
        trabajadores.add(new Trabajador(6,"Pedro"));
        return trabajadores;
    }

    public void click_faltas(MouseEvent mouseEvent) {
        System.out.printf("se seleccionó faltas");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> nombres= new ArrayList<>();
        nombres.add("juan");
        nombres.add("jose");
        nombres.add("pedro");
        nombres.add("antonio");

        System.out.println("se seleccionó trabajadores");
        list= FXCollections.observableList(nombres);
        listview_trabajadores.setItems(list);

        TableColumn firstNameCol = new TableColumn("Id");
        TableColumn lastNameCol = new TableColumn("Nombre");
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Trabajador,String>("Id")
        );
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Trabajador,String>("Nombre")
        );
        table_trabajador.getColumns().addAll(firstNameCol,lastNameCol);
        table_trabajador.setItems(getTrabajos());

    }

}
