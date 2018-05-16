package sample.Controladores.Trabajador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class Trabajadores_Alta implements Initializable{
    @FXML
    TextField txt_direccionArchivo;

    public void guardarTrabajador(ActionEvent event) {
    }

    public void subirArchivo(ActionEvent event) {
        FileChooser fc  = new FileChooser();
        //FIltros
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files","*.pdf"));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Jpg Images","*.jpg","*.JPEG","*.JPG","*.jpeg","*.PNG","*.png"));

        File fileSelected = fc.showSaveDialog(null);

        if (fileSelected!= null){
            txt_direccionArchivo.setText(fileSelected.getPath());

        }

            else{
            System.out.println("no se seleccinoó");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
