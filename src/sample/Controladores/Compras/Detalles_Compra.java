package sample.Controladores.Compras;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sample.Conexion_bd.Conexion;
import sample.objetos.Compras.Compra;

import java.net.URL;
import java.util.ResourceBundle;

public class Detalles_Compra implements Initializable {
    @FXML private TextField txt_monto;
    @FXML private TextField txt_fecha_compra;
    @FXML private TextField txt_fecha_pago;
    @FXML private TextField txt_moto_pagar;
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
        txt_monto.setText( String.valueOf(compra.getAdeudo()));

        txt_factura.setText(compra.getFactura());
        txt_cotizacion.setText(compra.getCotizacion());
        txt_orden_compra.setText(compra.getOrden_compra());

        lbl_proveedor.setText(compra.getProveedor());
        txt_fecha_compra.setText(String.valueOf(compra.getFecha_compra()));
        txt_fecha_pago.setText(String.valueOf(compra.getFecha_limite()));
        txt_moto_pagar.setText(String.valueOf(compra.getCantidad_restante()));
        txt_notas.setText(compra.getNotas());
        lbl_cantidad_pagada.setText(String.valueOf(compra.getAdeudo() - compra.getCantidad_restante()));

    }

    @FXML
    void realizar_pago(Event event){
        Conexion asd = new Conexion();
        asd.conecta();
        if(Double.parseDouble(txt_monto.getText()) <= compra.getCantidad_restante()){
            if(Double.parseDouble(txt_monto.getText()) == compra.getCantidad_restante()){
                asd.realizar_pago(compra.getReg(), compra.getAdeudo());

                // NOTIFICAR QUE SE REALIZÓ EL PAGO
                Notifications noti = Notifications.create()
                        .title("Pago Registrado!")
                        .text("Se ha pagado por completo la compra a "+ compra.getProveedor())
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

                // Cerramos la ventana
                Compras.ventana_detalles_compra = new Stage();
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
            else{
                Double cantidad_restante = Double.parseDouble(txt_monto.getText()) - compra.getCantidad_restante();
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
    void abrir_proveedor(){
        Conexion con = new Conexion();

        con.cerrarConexion();
    }
}
