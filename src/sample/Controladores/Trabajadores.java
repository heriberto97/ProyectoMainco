package sample.Controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javax.swing.text.TableView;
import java.util.ArrayList;
import java.util.List;

public class Trabajadores {
    @FXML
    TitledPane panel_trabajadores;
    @FXML
    TitledPane panel_faltas;
    @FXML
    TitledPane panel_prestamos;
    @FXML
    ListView<String> listview_trabajadores = new ListView<>();

    @FXML
    BorderPane paneCambio;

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



    }

    public void click_faltas(MouseEvent mouseEvent) {
        System.out.printf("se seleccionó faltas");

    }

}
