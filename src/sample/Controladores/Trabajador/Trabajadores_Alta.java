package sample.Controladores.Trabajador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Conexion_bd.Conexion;
import sample.objetos.Trabajador;

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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            // alert.setHeaderText("Results:");
            alert.setContentText("Complete los campos");
            alert.showAndWait();
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
            Stage stage= (Stage) this.btn_guardar.getScene().getWindow();
        stage.getOnCloseRequest().handle( new WindowEvent(
                stage,
                WindowEvent.WINDOW_CLOSE_REQUEST));
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

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Todas las imágenes","*.*"));

        File fileSelected = fc.showSaveDialog(null);

        if (fileSelected!= null){
            txt_direccionArchivo.setText(fileSelected.getPath());
        }
        else{
            System.out.println("no se seleccinó");

        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txt_direccionArchivo.setEditable(false);
    }

    public void cerrar_ventana(ActionEvent event) {
        limpiar();
        Stage stage= (Stage) this.btn_guardar.getScene().getWindow();

        stage.close();
    }
}
