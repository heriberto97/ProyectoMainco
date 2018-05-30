package sample.Controladores.Compras;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sample.Conexion_bd.Conexion;
import sample.objetos.Compras.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Nueva_Compra implements Initializable {
    @FXML private Label lbl_dias_pagar;
    @FXML private ComboBox<Proveedor> combo_proveedores;
    @FXML private Button btn_nuevo_proveedor;
    @FXML private TextField txt_monto_compra;
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

    @FXML private TextArea txt_notas;

    // Objetos de la clase
    private Conexion c = new Conexion();
    private ObservableList<Proveedor> lista_proveedores;
    private int proveedor_seleccionado;
    private int dias_limite;


    // - - - - - - - - - - Ejecutar al Iniciar la ventana
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lista_proveedores = FXCollections.observableArrayList();

        llenar_proveedores();
    }

    @FXML
    void llenar_proveedores(){
        try {
            ResultSet res = c.mostrarSql(c.mostrar_proveedores_limite());
            while (res.next()) {
                for (int x = 0; x < 1; x++) {
                    lista_proveedores.add(new Proveedor(
                            res.getInt("id"),
                            res.getString("nombre_proveedor"),
                            res.getInt("dias_limite")));
                }
            }

            // Este callback es para decir que es lo que vamos a mostrar en el combobox
            Callback<ListView<Proveedor>, ListCell<Proveedor>> factory = lv -> new ListCell<Proveedor>() {
                @Override
                protected void updateItem(Proveedor item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? "" : item.getNombre());
                }

            };

            combo_proveedores.setCellFactory(factory);
            combo_proveedores.setButtonCell(new ListCell<Proveedor>() {
                @Override
                protected void updateItem(Proveedor t, boolean bln) {
                    super.updateItem(t, bln);
                    if (t != null) {
                        setText(t.getNombre());
                        lbl_dias_pagar.setText(String.valueOf(t.getDias_limite()));
                        proveedor_seleccionado = t.getId_proveedor();
                        dias_limite = t.getDias_limite();

                    } else {
                        setText(null);
                    }
                }
            });

            combo_proveedores.setItems(lista_proveedores);
            combo_proveedores.setValue(lista_proveedores.get(0));

            c.cerrarConexion();
        }
        catch(SQLException e) {;
            System.out.println(e);
        }
    }

    @FXML
    void registrar_compra(){
        Conexion conexion = new Conexion();

        Compra compra = new Compra();

        // Comienza el registro de una nueva compra
        compra.setProveedor(String.valueOf(proveedor_seleccionado));
        compra.setAdeudo(Double.parseDouble(txt_monto_compra.getText()));
        compra.setCantidad_restante(Double.parseDouble(txt_monto_compra.getText()));

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
        compra.setNotas(txt_notas.getText());

        conexion.registrar_compra(compra, dias_limite);
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

    @FXML
    void limpiar(){
        txt_monto_compra.setText("");
        txt_numero_cotizacion.setText("");
        txt_esquema_cotizacion.setText("");
        txt_numero_factura.setText("");
        txt_esquema_factura.setText("");
        txt_numero_orden_compra.setText("");
        txt_esquema_orden_compra.setText("");
        txt_notas.setText("");
    }


    @FXML
    private void cancelar_nueva_compra(Event event){
        // Obtenemos la ventana
        Compras.ventana_nueva_compra = new Stage();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - Abrir Ventanas
    static Stage ventana_nuevo_proveedor = new Stage();
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
