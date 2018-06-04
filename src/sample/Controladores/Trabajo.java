package sample.Controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Conexion_bd.Conexion;
import sample.Controladores.Compras.Compras;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Trabajo implements Initializable {

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

    Conexion c = new Conexion();

    private static Stage ventana_nuevo_cliente = new Stage();
    private static Stage ventana_trabajo = new Stage();
    private static Stage ventana_tipo_trabajo = new Stage();

    public ObservableList<sample.objetos.Trabajo> getTrabajos(){
        ObservableList<sample.objetos.Trabajo> trabajos= FXCollections.observableArrayList();

        try {
            ResultSet trabajosResult= c.mostrarSql(c.verClientes());
            while (trabajosResult.next()) {

                for (int i = 0; i <1; i++) {

                    sample.objetos.Trabajo trabajo= new sample.objetos.Trabajo(
                            Integer.parseInt(trabajosResult.getObject(1).toString())
                    );
                    trabajos.add(trabajo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trabajos;
    }

    public ObservableList<Integer> getIdsTrabajadores(){
        ObservableList<sample.objetos.Trabajo> trabajos2 = getTrabajos();
        ObservableList<Integer> ids = FXCollections.observableArrayList();
        for (int i = 0; i < trabajos2.size(); i++) {
            ids.add(trabajos2.get(i).getId());
        }
        return ids;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        lv_clientes.setItems(getIdsTrabajadores());

    }


    @FXML void llenarclientes() {


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
