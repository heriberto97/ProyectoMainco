package sample.Controladores.Compras;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sample.Conexion_bd.Conexion;
import sample.objetos.Compras.Proveedor;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Nuevo_Proveedor implements Initializable {
    @FXML private TextField txt_nombre_proveedor;
    @FXML private TextField txt_telefono;
    @FXML private TextField txt_correo;
    @FXML private TextField txt_rfc;
    @FXML private Button btn_cancelar_nuevo_proveedor;
    @FXML private TextArea txt_notas;
    @FXML private TextField txt_limite_credito;
    @FXML private TextField txt_dias_limite;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txt_limite_credito.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,11}([\\.]\\d{0,2})?")) {
                    txt_limite_credito.setText(oldValue);
                }
            }
        });
        txt_dias_limite.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txt_dias_limite.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        txt_telefono.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txt_telefono.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @FXML
    private void cancelar_nuevo_proveedor(Event event){
        // Reiniciamos la ventana que contiene nuevo_proveedor
        Proveedores.ventana_nuevo_proveedor = new Stage();
        Nueva_Compra.ventana_nuevo_proveedor = new Stage();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void registrar_proveedor(){
        Conexion c = new Conexion();
        Proveedor proveedor = new Proveedor();

        proveedor.setNombre(txt_nombre_proveedor.getText());
        proveedor.setDias_limite(Integer.parseInt(txt_dias_limite.getText()));
        proveedor.setTelefono(txt_telefono.getText());
        proveedor.setCorreo(txt_correo.getText());
        proveedor.setRfc(txt_rfc.getText());
        proveedor.setNotas(txt_notas.getText());
        proveedor.setCredito(Double.parseDouble(txt_limite_credito.getText()));

        c.registrar_proveedor(proveedor);

        ResultSet res = c.mostrarSql(c.ultimo_proveedor());
        try {
            while (res.next()) {
                for (int x = 0; x < 1; x++) {
                    proveedor.setId_proveedor(res.getInt("id_proveedor"));
                    System.out.println(res.getInt("id_proveedor"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        c.registrar_limite_proveedor(proveedor);

        c.cerrarConexion();

        Notifications noti = Notifications.create()
                .title("Proveedor Registrado!")
                .text("El proveedor "+ proveedor.getNombre() +" sido registrado con éxito")
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

    @FXML
    void limpiar(){
        txt_nombre_proveedor.setText("");
        txt_correo.setText("");
        txt_dias_limite.setText("");
        txt_limite_credito.setText("");
        txt_notas.setText("");
        txt_rfc.setText("");
        txt_correo.setText("");
    }
}
