package sample.Controladores.Compras;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Nuevo_Proveedor {

    @FXML
    private void cancelar_nuevo_proveedor(Event event){
        // Reiniciamos la ventana que contiene nuevo_proveedor
        Proveedores.ventana_nuevo_proveedor = new Stage();
        Nueva_Compra.ventana_nuevo_proveedor = new Stage();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
