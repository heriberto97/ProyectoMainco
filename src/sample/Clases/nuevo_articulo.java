package sample.Clases;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Conexion_bd.Conexion;
import sample.objetos.Inventario_oficina;

import java.net.URL;
import java.util.ResourceBundle;

public class nuevo_articulo implements Initializable {

    @FXML
    javafx.scene.control.TextArea txt_descripcion;

    @FXML
    javafx.scene.control.TextField  txt_cantidad;

    @FXML
    javafx.scene.control.Button btn_guardar;
    @FXML
    javafx.scene.control.Button btn_cancelar;
    Conexion c = new Conexion();

    public void guardar(javafx.event.ActionEvent event) {
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
                if(Integer.parseInt(txt_cantidad.getText())<1)
                {
                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setTitle("Alerta");
                    alerta.setHeaderText(null);
                    alerta.setContentText("¡No puedes registrar articulos con cantidad 0!");
                    alerta.showAndWait();
                }
                else
                {
                    Inventario_oficina articulo = new Inventario_oficina(Integer.parseInt(txt_cantidad.getText()),txt_descripcion.getText(),"En Existencias");
                    c.AltaArticulos(articulo);
                    c.cerrarConexion();
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Maquinados industriales");
                    alerta.setHeaderText("Exito");
                    alerta.setContentText("¡Articulo creado correctamente!");
                    alerta.showAndWait();
                    txt_descripcion.setText("");
                    txt_cantidad.setText("");
                    inventario_oficina.nuevo_articulo= new Stage();
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                    Stage stage= (Stage) this.btn_guardar.getScene().getWindow();
                    stage.getOnCloseRequest().handle( new WindowEvent(
                            stage,
                            WindowEvent.WINDOW_CLOSE_REQUEST));
                    stage.close();


                }


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

                //SOLO NUMEROS PARA ESTE TEXTFIELD
        txt_cantidad.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                   txt_cantidad.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }

    public void cancelar(javafx.event.ActionEvent event)
    {
        inventario_oficina.nuevo_articulo= new Stage();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
