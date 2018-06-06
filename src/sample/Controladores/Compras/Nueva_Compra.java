package sample.Controladores.Compras;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sample.Conexion_bd.Conexion;
import sample.objetos.Compras.*;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
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
    private Double credito_disponible;
    private String nombre_p;

    // - - - - - - - - - - Ejecutar al Iniciar la ventana
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txt_monto_compra.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,10}([\\.]\\d{0,2})?")) {
                    txt_monto_compra.setText(oldValue);
                }
            }
        });

        llenar_proveedores();
    }

    @FXML
    void llenar_proveedores(){
        lista_proveedores = FXCollections.observableArrayList();
        try {
            ResultSet res = c.mostrarSql(c.mostrar_proveedores_limite());
            while (res.next()) {
                for (int x = 0; x < 1; x++) {
                    lista_proveedores.add(new Proveedor(
                            res.getInt("id"),
                            res.getString("nombre_proveedor"),
                            res.getInt("dias_limite"),
                            res.getDouble("credito_disponible")));
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
                        credito_disponible = t.getCredito_disponible();
                        nombre_p = t.getNombre();
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
        if (verificar_monto_compra()) {
            if (credito_disponible < Double.parseDouble(txt_monto_compra.getText())) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Crédito");
                alert.setHeaderText(null);
                alert.setContentText("El monto de la compra a " + nombre_p + " excede por $" + (Double.parseDouble(txt_monto_compra.getText()) - credito_disponible) + " pesos el crédito disponible.");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    registra();
                }
            } else{
                registra();
            }
        }
    }
    @FXML
    void registra(){
        Conexion conexion = new Conexion();

        Compra compra = new Compra();

        // Comienza el registro de una nueva compra
        compra.setProveedor(String.valueOf(proveedor_seleccionado));
        compra.setAdeudo(Double.parseDouble(txt_monto_compra.getText()));
        compra.setCantidad_restante(Double.parseDouble(txt_monto_compra.getText()));

        //* Validación para los campos de Factura, Cotización & Orden de Compra
        Factura factura = new Factura();
        factura.setNumero_factura(txt_numero_factura.getText());

        String ruta_esquema_factura =   txt_esquema_factura.getText().replace( "\\","\\"+"\\");
        factura.setEsquema_factura(ruta_esquema_factura);

        // Registramos la factura
        conexion.registrar_factura(factura);

        // Obtenemos la ultma factura registrada y la asignamos a la compra
        ResultSet fac = conexion.mostrarSql(conexion.ultima_factura());
        try {
            while (fac.next()) {
                for (int x = 0; x < 1; x++) {
                    compra.setFactura(fac.getString("factura"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Factura registrada");

        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setNumero_cotizacion(txt_numero_cotizacion.getText());

        String ruta_esquema_cotizacion =   txt_esquema_cotizacion.getText().replace( "\\","\\"+"\\");
        cotizacion.setEsquema(ruta_esquema_cotizacion);

        conexion.registrar_cotizacion(cotizacion);
        ResultSet cot = conexion.mostrarSql(conexion.ultima_cotizacion());
        try {
            while (cot.next()) {
                for (int x = 0; x < 1; x++) {
                    compra.setCotizacion(cot.getString("cotizacion"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Cotizacion registrada");


        Orden_compra orden_compra = new Orden_compra();
        orden_compra.setNumero_orden_compra(txt_numero_orden_compra.getText());

        String ruta_esquema_orden_compra =   txt_esquema_orden_compra.getText().replace( "\\","\\"+"\\");
        orden_compra.setEsquema_orden_compra(ruta_esquema_orden_compra);

        conexion.registrar_orden_compra(orden_compra);
        ResultSet oc = conexion.mostrarSql(conexion.ultima_orden_compra());
        try {
            while (oc.next()) {
                for (int x = 0; x < 1; x++) {
                    compra.setOrden_compra(oc.getString("orden_compra"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Orden de Compra registrada");


        compra.setNotas(txt_notas.getText());

        conexion.registrar_compra(compra, dias_limite);
        conexion.cerrarConexion();

        Image img = new Image("/sample/img/check.png");
        Notifications noti = Notifications.create()
                .title("Compra Registrada!")
                .text("Ha sido registrada con éxito")
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
    void cancelar_nueva_compra(){
        Stage stage= (Stage) this.btn_cancelar.getScene().getWindow();
        stage.getOnCloseRequest().handle( new WindowEvent(
                stage,
                WindowEvent.WINDOW_CLOSE_REQUEST));
        stage.close();
    }

    public void subirFactura(ActionEvent event) {
        FileChooser fc  = new FileChooser();

        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Archivos PDF","*.pdf")
                , new FileChooser.ExtensionFilter("Imágenes JPG, PNG","*.jpg","*.JPEG","*.JPG","*.jpeg","*.PNG","*.png"));

        File fileSelected = fc.showSaveDialog(null);

        if (fileSelected!= null){
            txt_esquema_factura.setText(fileSelected.getPath());
            //SaveFile(fileSelected.getName(),fileSelected);
        }
        else{
            System.out.println("no se seleccinó");
        }
    }
    public void subirCotizacion(ActionEvent event) {
        FileChooser fc  = new FileChooser();

        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Archivos PDF","*.pdf")
                , new FileChooser.ExtensionFilter("Imágenes JPG, PNG","*.jpg","*.JPEG","*.JPG","*.jpeg","*.PNG","*.png"));

        File fileSelected = fc.showSaveDialog(null);

        if (fileSelected!= null){
            txt_esquema_cotizacion.setText(fileSelected.getPath());
            //SaveFile(fileSelected.getName(),fileSelected);
        }
        else{
            System.out.println("no se seleccinó");
        }
    }
    public void subirOrdenCompra(ActionEvent event) {
        FileChooser fc  = new FileChooser();

        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Archivos PDF","*.pdf")
                , new FileChooser.ExtensionFilter("Imágenes JPG, PNG","*.jpg","*.JPEG","*.JPG","*.jpeg","*.PNG","*.png"));

        File fileSelected = fc.showSaveDialog(null);

        if (fileSelected!= null){
            txt_esquema_orden_compra.setText(fileSelected.getPath());
            //SaveFile(fileSelected.getName(),fileSelected);
        }
        else{
            System.out.println("no se seleccinó");
        }
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - Validaciones de los campos
    @FXML
    boolean verificar_monto_compra(){
        try{
            if (Double.parseDouble(txt_monto_compra.getText()) > 0){
                Double monto_compra = Double.parseDouble(txt_monto_compra.getText());
                System.out.println(monto_compra + " es una cantidad válida!");
                return true;
            }
            else{
                System.out.println("no es un numero válido");
                Alert alerta = new Alert(AlertType.WARNING);
                alerta.setTitle("Error!");
                alerta.setHeaderText("No se ha capturado el monto de la compra!");
                alerta.setContentText("Por favor, verifica los datos.");
                alerta.showAndWait();
                return false;
            }
        } catch (NumberFormatException e){
            System.out.println("no es un numero válido");
            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle("Error!");
            alerta.setHeaderText("La cantidad de la compra no es válida!");
            alerta.setContentText("Por favor, verifica los datos.");
            alerta.showAndWait();
            return false;
        }
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
                    llenar_proveedores();
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
