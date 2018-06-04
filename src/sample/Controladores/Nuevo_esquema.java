package sample.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import sample.Conexion_bd.Conexion;
import sample.objetos.Esquema;
import sun.plugin.liveconnect.ReplaceMethod;

import java.awt.*;
import java.io.File;

public class Nuevo_esquema {

    @FXML
    javafx.scene.control.Button btn_guardar;
    @FXML
    Button btn_subir;
    @FXML
    javafx.scene.control.TextField txt_ruta;
    @FXML
    javafx.scene.control.TextArea txt_descripcion;
    Conexion c = new Conexion();

//METODO PARA SUBIR UN NUEVO ARCHIVO
    public void subir_file()
    {
        FileChooser fc  = new FileChooser();

        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files","*.pdf")
                , new FileChooser.ExtensionFilter("Jpg Images","*.jpg","*.JPEG","*.JPG","*.jpeg","*.PNG","*.png"));

        File fileSelected = fc.showSaveDialog(null);

        if (fileSelected!= null){
            txt_ruta.setText(fileSelected.getPath());
        }
        else{
            System.out.println("no se seleccinoó");
        }
    }
    //GUARDAR EL ESQUEMA
    public void guardar() {

        try{
            String c1=   txt_ruta.getText().replace( "\\","\\"+"\\");
            Esquema e = new Esquema(c1,txt_descripcion.getText());
            System.out.println(txt_ruta.getText());
            c.Altaesquema(e);
            c.cerrarConexion();
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
