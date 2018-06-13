package sample.Controladores.Compras;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sample.Conexion_bd.Conexion;
import sample.objetos.Compras.Compra;
import sample.objetos.Compras.Proveedor;

import java.awt.*;
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
    private ObservableList<Proveedor> proveedor_actualizado;

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
        txt_numero_telefono.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txt_numero_telefono.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        // Seteamos los valores de cada uno de los textbox
        txt_nombre_proveedor.setText(proveedor.getNombre());
        txt_dias_plazo.setText(String.valueOf(proveedor.getDias_limite()));
        txt_limite_credito.setText(String.valueOf(proveedor.getCredito()));
        txt_numero_telefono.setText(proveedor.getTelefono());
        txt_correo.setText(proveedor.getCorreo());
        txt_rfc.setText(proveedor.getRfc());
        lbl_credito_actual.setText("$ " + proveedor.getCredito_disponible());
        txt_notas.setText(proveedor.getNotas());

        llenartabla();
    }

    @FXML
    void actualizar_datos(){
        try {
            ObservableList<Proveedor> lista_proveedores = FXCollections.observableArrayList();
            ResultSet proveedores = c.mostrarSql(c.mostrar_proveedor(proveedor.getId_proveedor()));

            Proveedor p = new Proveedor();

            while (proveedores.next()) {

                // Estas propiedades se deben llamar igual que los campos de la consulta
                for (int x = 0; x < 1; x++) {
                    p = new Proveedor(
                            proveedores.getInt("id"),
                            proveedores.getString("nombre_proveedor"),
                            proveedores.getInt("dias_limite"),
                            proveedores.getDouble("credito"),
                            proveedores.getDouble("credito_disponible"),
                            proveedores.getString("telefono"),
                            proveedores.getString("correo"),
                            proveedores.getString("rfc"),
                            proveedores.getString("notas")
                    );
                }
            }

            // Seteamos los valores de cada uno de los textbox
            lbl_credito_actual.setText("");
            lbl_credito_actual.setText("$ " + p.getCredito_disponible());
        } catch(SQLException e) {
            System.out.println(e);
        }
    }

    @FXML
    void llenartabla(){
        // Inicializamos la lista de compras al proveedor seleccionado
        lista_compras_proveedor = FXCollections.observableArrayList();
        try {
            // - - - - Todas las compras realizadas
            ResultSet compras_proveedor = c.mostrarSql(c.mostrar_compras_proveedor(proveedor.getId_proveedor()));
            while (compras_proveedor.next()){
                for(int x=0; x < 1;x++) {
                    lista_compras_proveedor.add(
                            new Compra(
                                    compras_proveedor.getInt("reg"),
                                    compras_proveedor.getInt("id_proveedor"),
                                    compras_proveedor.getString("nombre_proveedor"),
                                    compras_proveedor.getString("numero_cotizacion"),
                                    compras_proveedor.getString("numero_factura"),
                                    compras_proveedor.getString("numero_orden_compra"),
                                    compras_proveedor.getDate("fecha_compra"),
                                    compras_proveedor.getDate("fecha_limite"),
                                    compras_proveedor.getDouble("adeudo"),
                                    compras_proveedor.getDouble("cantidad_restante"),
                                    compras_proveedor.getString("notas"))
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
                    if (tabla_compras_proveedor.getSelectionModel().isEmpty()){
                        System.out.println("clic vacío");
                    }else {
                        // Asigno la compra que vamos a mostrar en la siguiente ventana
                        Detalles_Compra.setCompra(tabla_compras_proveedor.getSelectionModel().getSelectedItem());
                        // Abrimos la ventana
                        iniciar_detalles_compra();
                    }
                }
            });
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }

    @FXML
    void actualizar_proveedor(){
        Conexion c = new Conexion();

        if (txt_nombre_proveedor.getText() != proveedor.getNombre()){
            proveedor.setNombre(txt_nombre_proveedor.getText());
        }
        if (txt_rfc.getText() != proveedor.getRfc()){
            proveedor.setRfc(txt_rfc.getText());
        }
        if (txt_numero_telefono.getText() != proveedor.getTelefono()){
            proveedor.setTelefono(txt_numero_telefono.getText());
        }
        if (txt_correo.getText() != proveedor.getCorreo()){
            proveedor.setCorreo(txt_correo.getText());
        }
        if (Double.parseDouble(txt_limite_credito.getText()) != proveedor.getCredito()){
            proveedor.setCredito(Double.parseDouble(txt_limite_credito.getText()));
        }
        if (Integer.parseInt(txt_dias_plazo.getText()) != proveedor.getDias_limite()){
            proveedor.setDias_limite(Integer.parseInt(txt_dias_plazo.getText()));
        }
        if (txt_notas.getText() != proveedor.getNotas()){
            proveedor.setNotas(txt_notas.getText());
        }

        c.actualizar_extras_proveedor(proveedor);
        c.actualizar_proveedor(proveedor);

        c.cerrarConexion();

        actualizar_datos();

        Image img = new Image("/sample/img/check.png");
        Notifications noti = Notifications.create()
                .title("Proveedor Actualizado!")
                .text("El proveedor se actualizó con éxito!")
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
                ventana_detalles_compra.getIcons().add(new Image("sample/img/iconos/icono_compras.png"));
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
