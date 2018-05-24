package sample.Controladores.Compras;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Conexion_bd.Conexion;
import sample.objetos.Compras.Compra;
import sample.objetos.Compras.Proveedor;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Detalles_Proveedor implements Initializable {
    @FXML private TextField txt_nombre_proveedor;
    @FXML private TextField txt_dias_plazo;
    @FXML private TextField txt_limite_credito;
    @FXML private TextField txt_numero_telefono;
    @FXML private TextField txt_correo;
    @FXML private TextField txt_rfc;
    @FXML private TextArea txt_notas;
    @FXML private Label lbl_credito_actual;
    @FXML private Button btn_actualizar;
    @FXML private Button btn_cerrar;

    @FXML private TableView<Compra> tabla_compras_proveedor;
    @FXML private TableColumn<Compra, Date> tabla_compras_proveedor_columna_fecha_compra;
    @FXML private TableColumn<Compra, Double> tabla_compras_proveedor_columna_monto_compra;
    @FXML private TableColumn<Compra, String> tabla_compras_proveedor_columna_orden_compra;
    @FXML private TableColumn<Compra, String> tabla_compras_proveedor_columna_factura;
    @FXML private TableColumn<Compra, String> tabla_compras_proveedor_columna_cotizacion;


    static Proveedor proveedor = new Proveedor();
    public static void setProveedor(Proveedor proveedor) {
        Detalles_Proveedor.proveedor = proveedor;
    }

    Conexion c = new Conexion();
    ObservableList<Compra> lista_compras_proveedor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Seteamos los valores de cada uno de los textbox
        txt_nombre_proveedor.setText(proveedor.getNombre());
        txt_dias_plazo.setText(String.valueOf(proveedor.getDias_limite()));
        txt_limite_credito.setText(String.valueOf(proveedor.getCredito()));
        txt_numero_telefono.setText(proveedor.getTelefono());
        txt_correo.setText(proveedor.getCorreo());
        txt_rfc.setText(proveedor.getRfc());
        lbl_credito_actual.setText("$ " + proveedor.getCredito_disponible());

        // Inicializamos la lista de compras al proveedor seleccionado
        lista_compras_proveedor = FXCollections.observableArrayList();

        llenartabla();
    }

    @FXML
    void llenartabla(){
        try {
            // - - - - Todas las compras realizadas
            ResultSet compras_proveedor = c.mostrarSql(c.mostrar_compras_proveedor(proveedor.getId_proveedor()));
            while (compras_proveedor.next()){
                for(int x=0; x < 1;x++) {
                    lista_compras_proveedor.add(
                            new Compra(
                                    compras_proveedor.getInt("reg"),
                                    compras_proveedor.getString("nombre_proveedor"),
                                    compras_proveedor.getString("numero_cotizacion"),
                                    compras_proveedor.getString("numero_factura"),
                                    compras_proveedor.getString("numero_orden_compra"),
                                    compras_proveedor.getDate("fecha_compra"),
                                    compras_proveedor.getDate("fecha_limite"),
                                    compras_proveedor.getDouble("adeudo"),
                                    compras_proveedor.getDouble("cantidad_restante"))
                    );
                }
            }
            tabla_compras_proveedor.setItems(lista_compras_proveedor);
            tabla_compras_proveedor_columna_fecha_compra.setCellValueFactory(new PropertyValueFactory<>("fecha_compra"));
            tabla_compras_proveedor_columna_monto_compra.setCellValueFactory(new PropertyValueFactory<>("adeudo"));
            tabla_compras_proveedor_columna_orden_compra.setCellValueFactory(new PropertyValueFactory<>("orden_compra"));
            tabla_compras_proveedor_columna_cotizacion.setCellValueFactory(new PropertyValueFactory<>("cotizacion"));
            tabla_compras_proveedor_columna_factura.setCellValueFactory(new PropertyValueFactory<>("factura"));

            c.cerrarConexion();

            tabla_compras_proveedor.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    // Asigno la compra que vamos a mostrar en la siguiente ventana
                    Detalles_Compra.setCompra(tabla_compras_proveedor.getSelectionModel().getSelectedItem());
                    // Abrimos la ventana
                    iniciar_detalles_compra();
                }
            });
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }


    // - - - - - - - - - - - - - - - - - - - - - - - - - Abrir Ventanas
    static Stage ventana_detalles_compra = new Stage();
    @FXML
    void iniciar_detalles_compra(){
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../fxml/Compras/Detalles_Compra.fxml"));
            Parent abrir = fxmlLoader.load();

            // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
            if (ventana_detalles_compra.getScene() == null) {
                ventana_detalles_compra.setTitle("Detalles de Compra");
                ventana_detalles_compra.setScene(new Scene(abrir));

                ventana_detalles_compra.show();

                // El evento vaciará la ventana antes de ser cerrada, así se podrá abrir nuevamente
                ventana_detalles_compra.setOnCloseRequest(e -> {
                    ventana_detalles_compra.setScene(null);
                });
            }
            else {
                // Si la ventana tiene una escena, la trae al frente
                ventana_detalles_compra.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
