package sample.Controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.objetos.Inventario_oficina;

import java.net.URL;
import java.util.ResourceBundle;

public class Modificar_articulo implements Initializable {
  @FXML
    TextField txt_numero_articulo;
    @FXML
    TextArea txt_descripcion;
    @FXML
    TextField txt_cantidad;



   static Inventario_oficina obj = new Inventario_oficina();
   public static void setObj(Inventario_oficina obj) {
        Modificar_articulo.obj = obj;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       txt_numero_articulo.setText(Integer.toString(obj.getId()));
       txt_descripcion.setText(obj.getDescripcion());
       txt_cantidad.setText(Integer.toString(obj.getCantidad()));
    }
}
