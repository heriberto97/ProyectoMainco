package sample.Controladores;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Conexion_bd.Conexion;
import sample.objetos.Inventario_oficina;

import java.net.URL;
import java.util.ResourceBundle;

public class Modificar_articulo implements Initializable {
  @FXML
    TextField txt_numero_articulo;
    @FXML
    TextArea txt_descripcion;
    @FXML
    TextField txt_cantidad;
    @FXML
    Button btn_guardar;

    Conexion c = new Conexion();

   static Inventario_oficina obj = new Inventario_oficina();
   public static void setObj(Inventario_oficina obj) {
        Modificar_articulo.obj = obj;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       txt_numero_articulo.setText(Integer.toString(obj.getId()));
       txt_descripcion.setText(obj.getDescripcion());
       txt_cantidad.setText(Integer.toString(obj.getCantidad()));


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

    public void modificar(javafx.event.ActionEvent event)
    {

        try{

            if (txt_cantidad.getText().isEmpty()||txt_descripcion.getText().isEmpty())
            {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Maquinados industriales");
                alerta.setHeaderText(null);
                alerta.setContentText("¡Completa los campos!");
                alerta.showAndWait();
            }
            else
            {


                Inventario_oficina articuloseleccionado = new Inventario_oficina(obj.getId(),txt_descripcion.getText(),Integer.parseInt(txt_cantidad.getText()));

                c.modificarArticulo(articuloseleccionado);
                c.cerrarConexion();
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Maquinados industriales");
                alerta.setHeaderText("Exito");
                alerta.setContentText("¡Articulo modificado correctamente!");
                alerta.showAndWait();
                if(Integer.parseInt(txt_cantidad.getText())==0)
                {
                    c.modificarestadoArticulo();
                    c.cerrarConexion();

                }else if (Integer.parseInt(txt_cantidad.getText())>0)
                {
                    c.modificarestadoArticulo2();
                    c.cerrarConexion();

                }
                inventario_oficina.modificar_articulo= new Stage();
                ((Node)(event.getSource())).getScene().getWindow().hide();


            }

        }
        catch (Exception e)
        {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Revisa tu conexion");
            alerta.setHeaderText("¡Error de servidor!");
            alerta.setContentText("Algo esta fallando");
            alerta.showAndWait();
        }



    }

    public void cancelar(javafx.event.ActionEvent event)
    {
        inventario_oficina.modificar_articulo= new Stage();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }



}
