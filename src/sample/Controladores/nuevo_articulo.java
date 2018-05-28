package sample.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import sample.Conexion_bd.Conexion;
import sample.Controladores.Compras.Compras;
import sample.objetos.Inventario_oficina;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class nuevo_articulo implements Initializable {

    @FXML
    javafx.scene.control.TextArea txt_descripcion;

    @FXML
    javafx.scene.control.TextField  txt_cantidad;

    @FXML
    javafx.scene.control.Button guardar;
    Conexion c = new Conexion();

    public void guardar(javafx.event.ActionEvent event)
    {
        try {
            if(txt_descripcion.getText().isEmpty()||txt_cantidad.getText().isEmpty())
            {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Maquinados industriales");
                alerta.setHeaderText(null);
                alerta.setContentText("¡Completa los campos!");
                alerta.showAndWait();
            }
            else
            {
                Inventario_oficina articulo = new Inventario_oficina(Integer.parseInt(txt_cantidad.getText()),txt_descripcion.getText(),"En Existencias");
                c.AltaArticulos(articulo);
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Maquinados industriales");
                alerta.setHeaderText("Exito");
                alerta.setContentText("¡Articulo creado correctamente!");
                alerta.showAndWait();
                txt_descripcion.setText("");
                txt_cantidad.setText("");
               // Compras.ventana_nueva_compra = new Stage();
               // ((Node)(event.getSource())).getScene().getWindow().hide();



                c.cerrarConexion();
            }

        }
        catch (Exception ex)
        {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Revisa tu conexion");
            alerta.setHeaderText("¡Error de servidor!");
            alerta.setContentText("Algo esta fallando");
            alerta.showAndWait();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
