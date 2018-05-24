package sample.Controladores.Compras;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import sample.objetos.Compras.Compra;

import java.net.URL;
import java.util.ResourceBundle;

public class Detalles_Compra implements Initializable {
    @FXML private TextField txt_monto;
    @FXML private TextField txt_orden_compra;
    @FXML private TextField txt_cotizacion;
    @FXML private TextField txt_factura;
    @FXML private TextField txt_proveedor;
    @FXML private TextField txt_fecha_compra;
    @FXML private TextField txt_fecha_pago;
    @FXML private TextField txt_moto_pagar;

    static Compra compra = new Compra();
    public static void setCompra(Compra compra) {
        Detalles_Compra.compra = compra;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txt_monto.setText( String.valueOf(compra.getAdeudo()));
        txt_orden_compra.setText(compra.getOrden_compra());
        txt_cotizacion.setText(compra.getCotizacion());
        txt_factura.setText(compra.getFactura());
        txt_proveedor.setText(compra.getProveedor());
        txt_fecha_compra.setText(String.valueOf(compra.getFecha_compra()));
        txt_fecha_pago.setText(String.valueOf(compra.getFecha_limite()));
        txt_moto_pagar.setText(String.valueOf(compra.getCantidad_restante()));
    }
}
