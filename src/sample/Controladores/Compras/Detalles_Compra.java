package sample.Controladores.Compras;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sample.Conexion_bd.Conexion;
import sample.objetos.Compras.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Detalles_Compra implements Initializable {
    @FXML private Label lbl_monto;
    @FXML private TextField txt_fecha_compra;
    @FXML private TextField txt_fecha_pago;
    @FXML private TextField txt_monto_pagar;
    @FXML private TextArea txt_notas;
    @FXML private Label lbl_cantidad_pagada;
    @FXML private Label lbl_proveedor;

    @FXML private TextField txt_factura;
    @FXML private TextField txt_esquema_factura;
    @FXML private Button btn_esquema_factura;

    @FXML private TextField txt_cotizacion;
    @FXML private TextField txt_esquema_cotizacion;
    @FXML private Button btn_esquema_cotizacion;

    @FXML private TextField txt_orden_compra;
    @FXML private TextField txt_esquema_orden_compra;
    @FXML private Button btn_esquema_orden_compra;


    static Compra compra = new Compra();
    public static void setCompra(Compra compra) {
        Detalles_Compra.compra = compra;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbl_monto.setText( String.valueOf(compra.getAdeudo()));

        txt_factura.setText(compra.getFactura());
        txt_cotizacion.setText(compra.getCotizacion());
        txt_orden_compra.setText(compra.getOrden_compra());

        lbl_proveedor.setText(compra.getProveedor());
        txt_fecha_compra.setText(String.valueOf(compra.getFecha_compra()));
        txt_fecha_pago.setText(String.valueOf(compra.getFecha_limite()));
        txt_monto_pagar.setText(String.valueOf(compra.getCantidad_restante()));
        txt_notas.setText(compra.getNotas());
        lbl_cantidad_pagada.setText(String.valueOf(compra.getAdeudo() - compra.getCantidad_restante()));

    }

    @FXML
    void realizar_pago(Event event){
        Conexion asd = new Conexion();
        asd.conecta();
        if(Double.parseDouble(txt_monto_pagar.getText()) <= compra.getCantidad_restante()){
            if(Double.parseDouble(txt_monto_pagar.getText()) == compra.getCantidad_restante()){
                asd.realizar_pago(compra.getReg());

                // Cerramos la ventana
                Compras.ventana_detalles_compra = new Stage();
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
            else{
                Double cantidad_restante = compra.getCantidad_restante() - Double.parseDouble(txt_monto_pagar.getText());
                asd.realizar_abono(compra.getReg(), cantidad_restante);

                // NOTIFICAR QUE SE REALIZÓ EL ABONO

                // Cerramos la ventana
                Compras.ventana_detalles_compra = new Stage();
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
        }else {
            // Alerta para decir que no se puede pagar más de lo que se debe
        }
        asd.cerrarConexion();
    }

    @FXML
    /*void actualizar_compra(){
        Conexion conexion = new Conexion();

        Compra compra_actualizar = new Compra();

        // Comienza el registro de una nueva compra
        compra_actualizar.setAdeudo(Double.parseDouble(txt_monto_compra.getText()));
        compra_actualizar.setCantidad_restante(Double.parseDouble(txt_monto_compra.getText()));

        //* Validación para los campos de Factura, Cotización & Orden de Compra
        if (txt_numero_factura.getText() != null && txt_numero_factura.getText().trim().isEmpty() == false){
            Factura factura = new Factura();
            factura.setNumero_factura(txt_numero_factura.getText());
            if (txt_esquema_factura.getText() != null && txt_esquema_factura.getText().trim().isEmpty() == false){
                factura.setEsquema_factura(txt_esquema_factura.getText());
            }
            // Registramos la factura
            conexion.registrar_factura(factura);

            // Obtenemos la ultma factura registrada y la asignamos a la compra
            ResultSet res = conexion.mostrarSql(conexion.ultima_factura());
            try {
                while (res.next()) {
                    for (int x = 0; x < 1; x++) {
                        compra.setFactura(res.getString("factura"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println( "Factura registrada");
        }
        if (txt_numero_cotizacion.getText() != null && txt_numero_cotizacion.getText().trim().isEmpty() == false){
            Cotizacion cotizacion = new Cotizacion();
            cotizacion.setNumero_cotizacion(txt_numero_cotizacion.getText());
            if (txt_esquema_cotizacion.getText() != null && txt_esquema_cotizacion.getText().trim().isEmpty() == false){
                cotizacion.setEsquema(txt_esquema_cotizacion.getText());
            }
            conexion.registrar_cotizacion(cotizacion);
            ResultSet res = conexion.mostrarSql(conexion.ultima_cotizacion());
            try {
                while (res.next()) {
                    for (int x = 0; x < 1; x++) {
                        compra.setCotizacion(res.getString("cotizacion"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Cotizacion registrada");
        }
        if (txt_numero_orden_compra.getText() != null && txt_numero_orden_compra.getText().trim().isEmpty() == false){
            Orden_compra orden_compra = new Orden_compra();
            orden_compra.setNumero_orden_compra(txt_numero_orden_compra.getText());
            if (txt_esquema_orden_compra.getText() != null && txt_esquema_orden_compra.getText().trim().isEmpty() == false){
                orden_compra.setEsquema_orden_compra(txt_esquema_orden_compra.getText());
            }
            conexion.registrar_orden_compra(orden_compra);
            ResultSet res = conexion.mostrarSql(conexion.ultima_orden_compra());
            try {
                while (res.next()) {
                    for (int x = 0; x < 1; x++) {
                        compra.setOrden_compra(res.getString("orden_compra"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Orden de Compra registrada");
        }
        //compra.setFecha_compra();
        //compra.setFecha_limite();

        compra_actualizar.setNotas(txt_notas.getText());

        conexion.actualizar_compra(compra_actualizar);
        conexion.cerrarConexion();

        Notifications noti = Notifications.create()
                .title("Compra Registrada!")
                .text("Ha sido registrada con éxito")
                .graphic(null)
                .hideAfter(Duration.seconds(4))
                .position(Pos.BOTTOM_LEFT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("hizo clic en la notificacion");
                    }
                });
        noti.show();

        limpiar();
    }
*/

    // - - - - - - - - - - - - - - - - - - - - - - - - - Abrir Ventanas
    static Stage ventana_detalles_proveedor = new Stage();
    @FXML
    void abrir_proveedor(){
        Conexion con = new Conexion();

        Proveedor proveedor = new Proveedor();
        try {
        ResultSet proveedores = con.mostrarSql(con.mostrar_proveedor(compra.getId_proveedor()));
            while (proveedores.next()) {

                // Estas propiedades se deben llamar igual que los campos de la consulta
                for (int x = 0; x < 1; x++) {
                    proveedor.setId_proveedor(proveedores.getInt("id"));
                    proveedor.setNombre(proveedores.getString("nombre_proveedor"));
                    proveedor.setCredito(proveedores.getDouble("credito"));
                    proveedor.setCredito_disponible(proveedores.getDouble("credito_disponible"));
                    proveedor.setDias_limite(proveedores.getInt("dias_limite"));
                    proveedor.setTelefono(proveedores.getString("telefono"));
                    proveedor.setCorreo(proveedores.getString("correo"));
                    proveedor.setRfc(proveedores.getString("rfc"));
                    proveedor.setNotas(proveedores.getString("notas"));
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        Detalles_Proveedor.setProveedor(proveedor);
        iniciar_detalles_proveedor();

        con.cerrarConexion();
    }

    @FXML
    void iniciar_detalles_proveedor(){
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../fxml/Compras/Detalles_Proveedor.fxml"));
            Parent abrir = fxmlLoader.load();

            // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
            if (ventana_detalles_proveedor.getScene() == null) {
                ventana_detalles_proveedor.setTitle("Detalles de Proveedor");
                ventana_detalles_proveedor.setScene(new Scene(abrir));

                ventana_detalles_proveedor.show();

                // El evento vaciará la ventana antes de ser cerrada, así se podrá abrir nuevamente
                ventana_detalles_proveedor.setOnCloseRequest(e -> {
                    ventana_detalles_proveedor.setScene(null);
                });
            }
            else {
                // Si la ventana tiene una escena, la trae al frente
                ventana_detalles_proveedor.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
