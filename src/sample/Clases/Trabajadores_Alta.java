package sample.Clases;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
            txt_rfc,
            txt_puesto;

    @FXML
    Button  btn_guardar,
            btn_fotoperfil ;

   @FXML
   ImageView fotoperfil;
   @FXML
    DatePicker fecha_ingreso;

    public void guardarTrabajador(ActionEvent event) {
        Conexion conexion= new Conexion();
        if (      txt_nombre.getText().isEmpty()
                ||txt_apmaterno.getText().isEmpty()
                ||txt_appaterno.getText().isEmpty()
                ||txt_rfc.getText().isEmpty()
                ||txt_puesto.getText().isEmpty()
                ||fecha_ingreso.getValue().toString().isEmpty()){
            System.out.println("completa los campos");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            // alert.setHeaderText("Results:");
            alert.setContentText("Complete los campos");
            alert.showAndWait();
        }
        else{

            if (txt_direccionArchivo.getText().isEmpty()){
            /*    conexion.AltaTrabjador(new Trabajador(txt_nombre.getText(),
                        txt_appaterno.getText(),
                        txt_apmaterno.getText(),
                        txt_rfc.getText(),
                        txt_puesto.getText()

                ));*/
            Trabajador trabajador= new Trabajador();
            trabajador.setNombre(txt_nombre.getText());
            trabajador.setApellido_paterno(txt_appaterno.getText());
            trabajador.setApellido_materno(txt_apmaterno.getText());
            trabajador.setRfc(txt_rfc.getText());
            trabajador.setPuesto(txt_puesto.getText());
            trabajador.setFechaigreso(fecha_ingreso.getValue().toString());
            trabajador.setFotoperfil(foto);
            conexion.AltaTrabjador(trabajador);
            }
            else{
             /*   conexion.AltaTrabjador(new Trabajador(txt_nombre.getText(),
                        txt_appaterno.getText(),
                        txt_apmaterno.getText(),
                        txt_rfc.getText(),
                        txt_direccionArchivo.getText()));*/

                Trabajador trabajador= new Trabajador();
                trabajador.setNombre(txt_nombre.getText());
                trabajador.setApellido_paterno(txt_appaterno.getText());
                trabajador.setApellido_materno(txt_apmaterno.getText());
                trabajador.setRfc(txt_rfc.getText());
                trabajador.setPuesto(txt_puesto.getText());
                trabajador.setFechaigreso(fecha_ingreso.getValue().toString());
                trabajador.setFotoperfil(foto);
                trabajador.setSolicitud_empleo(txt_direccionArchivo.getText());
                conexion.AltaTrabjador(trabajador);
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
        stage.getOnCloseRequest().handle( new WindowEvent(
                stage,
                WindowEvent.WINDOW_CLOSE_REQUEST));
        stage.close();
    }
    String foto;
    Image imagen;
    public void subitfoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Foto de Trabajador");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            foto= file.toURI().toString();
            System.out.println(foto);
            System.out.println(file.getAbsolutePath());
            imagen = new Image(foto);
            fotoperfil.setImage(imagen);
        }
    }
}
