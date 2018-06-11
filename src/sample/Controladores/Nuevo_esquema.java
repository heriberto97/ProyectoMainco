package sample.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML
    ImageView image_esquema;
    Conexion c = new Conexion();

//METODO PARA SUBIR UN NUEVO ARCHIVO
    public void subir_file()
    {
        FileChooser fc  = new FileChooser();

        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files","*.pdf")
                , new FileChooser.ExtensionFilter("Jpg Images","*.jpg","*.JPEG","*.JPG","*.jpeg","*.PNG","*.png"));

        File fileSelected = fc.showOpenDialog(null);

        if (fileSelected!= null){
            txt_ruta.setText(fileSelected.getPath());
            File file = new File(txt_ruta.getText());
            javafx.scene.image.Image image = new Image(file.toURI().toString());
            image_esquema.setImage(image);
        }
        else{
            System.out.println("no se seleccinoó");
        }
    }
    //GUARDAR EL ESQUEMA
    public void guardar() {

        try{

            if(txt_descripcion.getText().isEmpty()||txt_ruta.getText().isEmpty())
            {

                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Maquinados industriales");
                alerta.setHeaderText(null);
                alerta.setContentText("¡Completa los campos!");
                alerta.showAndWait();
            }
            else
            {
                String c1=   txt_ruta.getText().replace( "\\","\\"+"\\");
                Esquema e = new Esquema(c1,txt_descripcion.getText());
                System.out.println(txt_ruta.getText());
                c.Altaesquema(e);
                c.cerrarConexion();
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Maquinados industriales");
                alerta.setHeaderText("Exito");
                alerta.setContentText("¡Esquema subido correctamente!");
                alerta.showAndWait();
                txt_descripcion.setText("");
                txt_ruta.setText("");
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
