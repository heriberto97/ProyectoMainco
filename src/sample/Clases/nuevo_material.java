package sample.Clases;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Conexion_bd.Conexion;
import sample.objetos.Material;

import java.net.URL;
import java.util.ResourceBundle;

public class nuevo_material implements Initializable {

    @FXML
    Button btn_guardar;
    @FXML
    TextField txt_material;
    Conexion c = new Conexion();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void  guardar()
    {
        try
        {
            if(txt_material.getText().isEmpty())
            {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Maquinados industriales");
                alerta.setHeaderText(null);
                alerta.setContentText("¡Completa los campos!");
                alerta.showAndWait();
            }
            else
            {
                Material m = new Material(txt_material.getText());
                c.AltaMaterial(m);
                txt_material.setText("");
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Maquinados industriales");
                alerta.setHeaderText("Exito");
                alerta.setContentText("¡Material creado correctamente!");
                alerta.showAndWait();
            }



        }
        catch(Exception ex)
        {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Revisa tu conexion");
            alerta.setHeaderText("¡Error de servidor!");
            alerta.setContentText("Algo esta fallando");
            alerta.showAndWait();

        }

    }
}
