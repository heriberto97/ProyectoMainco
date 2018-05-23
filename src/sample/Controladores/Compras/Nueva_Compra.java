package sample.Controladores.Compras;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Conexion_bd.Conexion;
import sample.objetos.Compras.Compra;
import sample.objetos.Compras.Proveedor;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class Nueva_Compra implements Initializable {
    @FXML private DatePicker date_picker_fecha_compra;
    @FXML private ComboBox<String> combo_proveedores;
    @FXML private Button btn_nuevo_proveedor;
    @FXML private TextField txt_monto_compra;
    @FXML private DatePicker date_picker_dia_pago;
    @FXML private Button btn_registrar_compra;
    @FXML private Button btn_cancelar;

    @FXML private TextField txt_numero_cotizacion;
    @FXML private TextField txt_esquema_cotizacion;
    @FXML private Button btn_esquema_cotizacion;

    @FXML private TextField txt_numero_factura;
    @FXML private TextField txt_esquema_factura;
    @FXML private Button btn_esquema_factura;

    @FXML private TextField txt_numero_orden_compra;
    @FXML private TextField txt_esquema_orden_compra;
    @FXML private Button btn_esquema_orden_compra;

    // Objetos de la clase
    private Conexion c = new Conexion();
    private ObservableList<Proveedor> lista_proveedores;
    private ObservableList<String> lista_proveedores_nombres;


    // - - - - - - - - - - Ejecutar al Iniciar la ventana
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lista_proveedores = FXCollections.observableArrayList();
        lista_proveedores_nombres = FXCollections.observableArrayList();

        llenar_proveedores();
    }

    @FXML
    void llenar_proveedores(){
        try {
            ResultSet res = c.mostrarSql(c.mostrar_proveedores_limite());
            while (res.next()) {
                for (int x = 0; x < 1; x++) {
                    lista_proveedores.add(new Proveedor(
                            res.getString("nombre_proveedor"),
                            res.getInt("dias_limite")));
                    lista_proveedores_nombres.add(res.getString("nombre_proveedor"));
                }
            }
            combo_proveedores.setItems(lista_proveedores_nombres);

            c.cerrarConexion();
        }
        catch(SQLException e) {;
            System.out.println(e);
        }
    }

    @FXML
    void registrar_compra(){
        Conexion conexion = new Conexion();
        c.cerrarConexion();
    }


    @FXML
    private void cancelar_nueva_compra(Event event){
        // Obtenemos la ventana
        Compras.ventana_nueva_compra = new Stage();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - Abrir Ventanas
    private Stage ventana_nuevo_proveedor = new Stage();
    @FXML
    void iniciar_nuevo_proveedor(){
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../fxml/Compras/Nuevo_Proveedor.fxml"));
            Parent abrir = fxmlLoader.load();

            // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
            if (ventana_nuevo_proveedor.getScene() == null) {
                ventana_nuevo_proveedor.setTitle("Registrar una compra");
                ventana_nuevo_proveedor.setScene(new Scene(abrir));
                ventana_nuevo_proveedor.show();

                // El evento vaciará la ventana antes de ser cerrada, así se podrá abrir nuevamente
                ventana_nuevo_proveedor.setOnCloseRequest(e -> {
                    ventana_nuevo_proveedor.setScene(null);
                });
            }
            else {
                // Si la ventana tiene una escena, la trae al frente
                ventana_nuevo_proveedor.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
