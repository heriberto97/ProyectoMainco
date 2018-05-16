package sample.Controladores.Trabajador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Conexion_bd.Conexion;
import sample.objetos.Trabajador;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class Trabajadores_Alta implements Initializable{
    @FXML
    TextField txt_direccionArchivo,
            txt_nombre,
            txt_appaterno,
            txt_apmaterno,
                   txt_rfc ;

    @FXML
    Button btn_guardar;

    public void guardarTrabajador(ActionEvent event) {
        Conexion conexion= new Conexion();
        if (      txt_nombre.getText().isEmpty()
                ||txt_apmaterno.getText().isEmpty()
                ||txt_appaterno.getText().isEmpty()
                ||txt_rfc.getText().isEmpty()){
            System.out.println("completa los campos");
        }
        else{

            if (txt_direccionArchivo.getText().isEmpty()){
                conexion.AltaTrabjador(new Trabajador(txt_nombre.getText(),
                        txt_appaterno.getText(),
                        txt_apmaterno.getText(),
                        txt_rfc.getText()));
            }
            else{
                conexion.AltaTrabjador(new Trabajador(txt_nombre.getText(),
                        txt_appaterno.getText(),
                        txt_apmaterno.getText(),
                        txt_rfc.getText(),
                        txt_direccionArchivo.getText()));
            }
            System.out.println(" agregado");
            limpiar();
            Stage stage= (Stage) btn_guardar.getScene().getWindow();
            stage.close();
        }


    }
    public void limpiar(){
        txt_direccionArchivo.setText("");
        txt_nombre.setText("");
        txt_appaterno.setText("");
        txt_apmaterno.setText("");
        txt_rfc.setText("");
    }
    public void subirArchivo(ActionEvent event) {
        FileChooser fc  = new FileChooser();
        //FIltros
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files","*.pdf")
                , new FileChooser.ExtensionFilter("Jpg Images","*.jpg","*.JPEG","*.JPG","*.jpeg","*.PNG","*.png"));

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

        txt_direccionArchivo.setEditable(false);
    }
}
