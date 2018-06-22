package sample.Clases;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import sample.objetos.articulos_empleados;
import sample.objetos.producto;

import javax.swing.text.TableView;
import java.net.URL;
import java.util.ResourceBundle;

public class Expedicion implements Initializable {

    @FXML private javafx.scene.control.TableView<articulos_empleados> tv_expedicion;
    @FXML private TableColumn<articulos_empleados, String> columna_nombre;
    @FXML private   TableColumn<articulos_empleados, String> columna_apellido;
    @FXML private   TableColumn<articulos_empleados, String> columna_articulo;
    @FXML private   TableColumn<articulos_empleados, Integer> columna_cantidad;
    @FXML private   TableColumn<articulos_empleados, String> columna_fecha;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
