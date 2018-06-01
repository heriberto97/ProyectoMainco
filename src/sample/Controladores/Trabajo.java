package sample.Controladores;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Controladores.Compras.Compras;

public class Trabajo {
    @FXML
    Button btn_nuevocliente;
    @FXML
    Button btn_ordendecompra;
    @FXML
    Button btn_esquema;
    @FXML
    Button btn_trabajadores;
    @FXML
    ListView lv_clientes;
    @FXML
    TextArea txta_descripcion;
    @FXML
    Button btn_guardartrabajo;



    private static Stage ventana_nuevo_cliente = new Stage();
    private static Stage ventana_trabajo = new Stage();
    private static Stage ventana_tipo_trabajo = new Stage();

    @FXML void abrir_ordencompra() {

    }

    @FXML void nuevo_cliente(){

        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Nuevo_Cliente.fxml"));
            Parent abrir = fxmlLoader.load();
            if (ventana_nuevo_cliente.getScene() == null) {
                ventana_nuevo_cliente.initStyle(StageStyle.UNDECORATED);
                ventana_nuevo_cliente.setTitle("Maquinados industriales - Trabajos");
                ventana_nuevo_cliente.setScene(new Scene(abrir));
                ventana_nuevo_cliente.show();
                ventana_nuevo_cliente.setOnCloseRequest(e -> {
                    ventana_nuevo_cliente.setScene(null);
                });
            }
            else{
                ventana_nuevo_cliente.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }


    @FXML
    void guardar_trabajo(Event event ) {


        //Tipo_Trabajo.ventana_trabajo = new Stage();
        //((Node)(event.getSource())).getScene().getWindow().hide();
    }
    @FXML void subir_ordencompra() {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Trabajos_OrdenCompra.fxml"));
            Parent abrir = fxmlLoader.load();
            if (ventana_nuevo_cliente.getScene() == null) {
                ventana_nuevo_cliente.initStyle(StageStyle.UNDECORATED);
                ventana_nuevo_cliente.setTitle("Maquinados industriales - OrdenCompra");
                ventana_nuevo_cliente.setScene(new Scene(abrir));
                ventana_nuevo_cliente.show();
                ventana_nuevo_cliente.setOnCloseRequest(e -> {
                    ventana_nuevo_cliente.setScene(null);
                });
            }
            else{
                ventana_nuevo_cliente.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    @FXML void seleccionar_esquema() {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Trabajo_Esquema.fxml"));
            Parent abrir = fxmlLoader.load();
            if (ventana_nuevo_cliente.getScene() == null) {
                ventana_nuevo_cliente.initStyle(StageStyle.UNDECORATED);
                ventana_nuevo_cliente.setTitle("Maquinados industriales - Esquemas");
                ventana_nuevo_cliente.setScene(new Scene(abrir));
                ventana_nuevo_cliente.show();
                ventana_nuevo_cliente.setOnCloseRequest(e -> {
                    ventana_nuevo_cliente.setScene(null);
                });
            }
            else{
                ventana_nuevo_cliente.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    @FXML void subir_cotizacion() {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Trabajo_Cotizacion.fxml"));
            Parent abrir = fxmlLoader.load();
            if (ventana_nuevo_cliente.getScene() == null) {
                ventana_nuevo_cliente.initStyle(StageStyle.UNDECORATED);
                ventana_nuevo_cliente.setTitle("Maquinados industriales - Cotizacion");
                ventana_nuevo_cliente.setScene(new Scene(abrir));
                ventana_nuevo_cliente.show();
                ventana_nuevo_cliente.setOnCloseRequest(e -> {
                    ventana_nuevo_cliente.setScene(null);
                });
            }
            else{
                ventana_nuevo_cliente.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
