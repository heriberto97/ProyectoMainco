package sample.Controladores.Compras;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import sample.objetos.Compras.Compra;

import java.net.URL;
import java.util.ResourceBundle;

public class Detalles_Compra_Pago implements Initializable {
    @FXML private TextField txt_monto;

    static Compra compra = new Compra();
    public static void setCompra(Compra compra) {
        Detalles_Compra.compra = compra;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txt_monto.setText(String.valueOf(compra.getCantidad_restante()));
    }
}
