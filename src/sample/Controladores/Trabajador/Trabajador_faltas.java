package sample.Controladores.Trabajador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.objetos.Trabajador;

import java.net.URL;
import java.util.ResourceBundle;

public class Trabajador_faltas  implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        lbl_prueba.setText(obtenido.getNombre());
    }

    @FXML
    Label lbl_prueba;

    Trabajador obtenido;
    public void guardarDatoTrabajador(Trabajador t){
    obtenido=t;


    }

    public void guardar_Falta(ActionEvent event) {

    }
}
