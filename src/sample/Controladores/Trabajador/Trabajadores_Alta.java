package sample.Controladores.Trabajador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Conexion_bd.Conexion;
import sample.Controladores.Trabajadores;
import sample.objetos.Trabajador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


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
            Stage stage= (Stage) this.btn_guardar.getScene().getWindow();

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
            SaveFile(fileSelected.getName(),fileSelected);
        }
        else{
            System.out.println("no se seleccinó");
        }
    }
    private void SaveFile(String content, File file){
        try {
            FileWriter fileWriter = null;

            fileWriter = new FileWriter(file);
            fileWriter.write(content);

            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Trabajadores.class.getName()).log(Level.SEVERE, null, ex);
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
