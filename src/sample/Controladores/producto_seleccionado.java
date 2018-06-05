package sample.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import sample.objetos.Inventario_oficina;
import sample.objetos.producto;

import javax.swing.text.html.ImageView;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class producto_seleccionado implements Initializable {

    static producto obje = new producto();
    public static void setObj(producto obje) {
        producto_seleccionado.obje = obje;
    }

    @FXML
    TextField txt_numero,txt_ruta;
@FXML
    TextArea txt_descripcion;
@FXML
    private javafx.scene.image.ImageView image_esquema;
String ruta;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ruta =obje.getRuta_imagen() ;
        txt_numero.setText(obje.getNumero_producto());
        txt_descripcion.setText(obje.getDescripcion());
        txt_ruta.setText(ruta);


        File file = new File(ruta);
        Image image = new Image(file.toURI().toString());
        image_esquema.setImage(image);
    }
}
