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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
    @FXML private TextArea txt_notas;
    @FXML private TextField txt_limite_credito;
    @FXML private TextField txt_dias_limite;
    @FXML private Button btn_cancelar_nuevo_proveedor;

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
    void registrar_proveedor(){
        if (       txt_nombre_proveedor.getText().isEmpty()
                || txt_limite_credito.getText().isEmpty()
                || txt_dias_limite.getText().isEmpty()
                || txt_rfc.getText().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Datos incompletos!");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor, completa los campos.");
            alerta.showAndWait();
        } else {
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

            Image img = new Image("/sample/img/check.png");
            Notifications noti = Notifications.create()
                    .title("Proveedor Registrado!")
                    .text("El proveedor " + proveedor.getNombre() + " sido registrado con éxito")
                    .graphic(new ImageView(img))
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
        txt_telefono.setText("");
    }

    @FXML
    void cancelar_nuevo_proveedor(){
        Stage stage= (Stage) this.btn_cancelar_nuevo_proveedor.getScene().getWindow();
        stage.getOnCloseRequest().handle( new WindowEvent(
                stage,
                WindowEvent.WINDOW_CLOSE_REQUEST));
        stage.close();
    }
}
