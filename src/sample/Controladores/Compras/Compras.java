package sample.Controladores.Compras;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sample.Conexion_bd.Conexion;
import sample.objetos.Compras.*;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Compras implements Initializable {
    // Controladores de la interfaz
    @FXML private TableView<Compra> tabla_compras;
    @FXML private TableColumn<Compra, String> tabla_compras_columna_orden_compra;
    @FXML private TableColumn<Compra, String> tabla_compras_columna_cotizacion;
    @FXML private TableColumn<Compra, String> tabla_compras_columna_factura;
    @FXML private TableColumn<Compra, String> tabla_compras_columna_proveedor;
    @FXML private TableColumn<Compra, Double> tabla_compras_columna_monto;
    @FXML private TableColumn<Compra, Date> tabla_compras_columna_fecha_compra;
    @FXML private TableColumn<Compra, Date> tabla_compras_columna_fecha_pago;
    @FXML private TableColumn<Compra, Double> tabla_compras_columna_cantidad_restante;
    @FXML private TableColumn<Compra, String> tabla_compras_columna_estado;

    @FXML private TableView<Compra> tabla_pagos_proximos_30_dias;
    @FXML private TableColumn<Compra, Date> tabla_pagos_proximos_columna_fecha_pago;
    @FXML private TableColumn<Compra, String> tabla_pagos_proximos_columna_proveedor;
    @FXML private TableColumn<Compra, Double> tabla_pagos_proximos_columna_monto;

    @FXML private TableView<Compra> tabla_compras_documentos_pendientes;
    @FXML private TableColumn<Compra, String> tabla_compras_documentos_pendientes_columna_orden_compra;
    @FXML private TableColumn<Compra, String> tabla_compras_documentos_pendientes_columna_cotizacion;
    @FXML private TableColumn<Compra, String> tabla_compras_documentos_pendientes_columna_factura;
    @FXML private TableColumn<Compra, String> tabla_compras_documentos_pendientes_columna_proveedor;
    @FXML private TableColumn<Compra, Double> tabla_compras_documentos_pendientes_columna_monto;
    @FXML private TableColumn<Compra, Date> tabla_compras_documentos_pendientes_columna_fecha_compra;
    @FXML private TableColumn<Compra, Date> tabla_compras_documentos_pendientes_columna_fecha_pago;
    @FXML private TableColumn<Compra, Double> tabla_compras_documentos_pendientes_columna_cantidad_restante;
    @FXML private TableColumn<Compra, String> tabla_compras_documentos_pendientes_columna_estado;

    @FXML private TextField txt_busqueda_compras;
    @FXML private TextField txt_busqueda_compras_documentos_faltantes;


    // Objetos usados en la clase
    private Conexion c = new Conexion();
    private ObservableList<Compra> lista_compras;
    private ObservableList<Compra> lista_compras_pagos_proximos;
    private ObservableList<Compra> lista_compras_documentos_pendientes;

    // - - - - - - - - - - Ejecutar al Iniciar la ventana
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        llenartablas();
    }

    @FXML
    void actualizar(){
        tabla_compras.setItems(null);
        tabla_compras_documentos_pendientes.setItems(null);
        tabla_pagos_proximos_30_dias.setItems(null);
        llenartablas();
    }

    @FXML
    void llenartablas(){
        // Inicializamos las listas para ser usadas posteriormente
        lista_compras = FXCollections.observableArrayList();
        lista_compras_pagos_proximos = FXCollections.observableArrayList();
        lista_compras_documentos_pendientes = FXCollections.observableArrayList();
        try {
            // - - - - Todas las compras realizadas
            ResultSet completas = c.mostrarSql(c.mostrarcompras());
            while (completas.next()){

                // Estas propiedades se deben llamar igual que los campos de la consulta
                for (int x = 0; x < 1; x++){
                    lista_compras.add(new Compra(
                            completas.getInt("reg"),
                            completas.getInt("id_proveedor"),
                            completas.getString("nombre_proveedor"),
                            completas.getString("numero_cotizacion"),
                            completas.getString("numero_factura"),
                            completas.getString("numero_orden_compra"),
                            completas.getDate("fecha_compra"),
                            completas.getDate("fecha_limite"),
                            completas.getDouble("adeudo"),
                            completas.getDouble("cantidad_restante"),
                            completas.getString("notas"),
                            completas.getString("estado")));
                }
            }
            // Le asignamos a la tabla la lista contiene lo que va a mostrar | falta decirle a cada columna que dato mostrará
            tabla_compras.setItems(lista_compras);

            // Asignamos cada dato que mostrarán las columnas | Los nombres de las propiedades vienen del tipo de clase
            tabla_compras_columna_orden_compra.setCellValueFactory(new PropertyValueFactory<>("orden_compra"));
            tabla_compras_columna_cotizacion.setCellValueFactory(new PropertyValueFactory<>("cotizacion"));
            tabla_compras_columna_factura.setCellValueFactory(new PropertyValueFactory<>("factura"));
            tabla_compras_columna_proveedor.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
            tabla_compras_columna_monto.setCellValueFactory(new PropertyValueFactory<>("adeudo"));
            tabla_compras_columna_fecha_compra.setCellValueFactory(new PropertyValueFactory<>("fecha_compra"));
            tabla_compras_columna_fecha_pago.setCellValueFactory(new PropertyValueFactory<>("fecha_limite"));
            tabla_compras_columna_cantidad_restante.setCellValueFactory(new PropertyValueFactory<>("cantidad_restante"));
            tabla_compras_columna_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));



            // - - - - Todas las compras en los próximos 30 días
            ResultSet compras_pagar = c.mostrarSql(c.mostrar_compras_a_pagar());
            while (compras_pagar.next()){
                for (int x = 0; x < 1; x++){
                    lista_compras_pagos_proximos.add(
                            new Compra(
                                    compras_pagar.getInt("reg"),
                                    compras_pagar.getInt("id_proveedor"),
                                    compras_pagar.getString("nombre_proveedor"),
                                    compras_pagar.getString("numero_cotizacion"),
                                    compras_pagar.getString("numero_factura"),
                                    compras_pagar.getString("numero_orden_compra"),
                                    compras_pagar.getDate("fecha_compra"),
                                    compras_pagar.getDate("fecha_limite"),
                                    compras_pagar.getDouble("adeudo"),
                                    compras_pagar.getDouble("cantidad_restante"),
                                    compras_pagar.getString("notas"),
                                    compras_pagar.getString("estado"))
                    );
                }
            }
            tabla_pagos_proximos_30_dias.setItems(lista_compras_pagos_proximos);
            tabla_pagos_proximos_columna_fecha_pago.setCellValueFactory(new PropertyValueFactory<>("fecha_limite"));
            tabla_pagos_proximos_columna_proveedor.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
            tabla_pagos_proximos_columna_monto.setCellValueFactory(new PropertyValueFactory<>("cantidad_restante"));


            ResultSet compras_docum_faltantes = c.mostrarSql(c.mostrar_compras_docum_faltantes());
            while (compras_docum_faltantes.next()){
                for(int x=0; x < 1;x++) {
                    lista_compras_documentos_pendientes.add(
                            new Compra(
                                    compras_docum_faltantes.getInt("reg"),
                                    compras_docum_faltantes.getInt("id_proveedor"),
                                    compras_docum_faltantes.getString("nombre_proveedor"),
                                    compras_docum_faltantes.getString("numero_cotizacion"),
                                    compras_docum_faltantes.getString("numero_factura"),
                                    compras_docum_faltantes.getString("numero_orden_compra"),
                                    compras_docum_faltantes.getDate("fecha_compra"),
                                    compras_docum_faltantes.getDate("fecha_limite"),
                                    compras_docum_faltantes.getDouble("adeudo"),
                                    compras_docum_faltantes.getDouble("cantidad_restante"),
                                    compras_docum_faltantes.getString("notas"),
                                    compras_docum_faltantes.getString("estado"))
                    );
                }
            }
            tabla_compras_documentos_pendientes.setItems(lista_compras_documentos_pendientes);
            tabla_compras_documentos_pendientes_columna_orden_compra.setCellValueFactory(new PropertyValueFactory<>("orden_compra"));
            tabla_compras_documentos_pendientes_columna_cotizacion.setCellValueFactory(new PropertyValueFactory<>("cotizacion"));
            tabla_compras_documentos_pendientes_columna_factura.setCellValueFactory(new PropertyValueFactory<>("factura"));
            tabla_compras_documentos_pendientes_columna_proveedor.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
            tabla_compras_documentos_pendientes_columna_monto.setCellValueFactory(new PropertyValueFactory<>("adeudo"));
            tabla_compras_documentos_pendientes_columna_fecha_compra.setCellValueFactory(new PropertyValueFactory<>("fecha_compra"));
            tabla_compras_documentos_pendientes_columna_fecha_pago.setCellValueFactory(new PropertyValueFactory<>("fecha_limite"));
            tabla_compras_documentos_pendientes_columna_cantidad_restante.setCellValueFactory(new PropertyValueFactory<>("cantidad_restante"));
            tabla_compras_documentos_pendientes_columna_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));


            // Cerramos conexión porque ya no la vamos a usar
            c.cerrarConexion();


            tabla_compras.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (tabla_compras.getSelectionModel().isEmpty()){
                        System.out.println("clic vacío");
                    }else {
                        // Asigno la compra que vamos a mostrar en la siguiente ventana
                        Detalles_Compra.setCompra(tabla_compras.getSelectionModel().getSelectedItem());
                        // Abrimos la ventana
                        iniciar_detalles_compra();
                    }
                }
            });
            tabla_compras_documentos_pendientes.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (tabla_compras_documentos_pendientes.getSelectionModel().isEmpty()){
                        System.out.println("clic vacío");
                    }else {
                        // Asigno la compra que vamos a mostrar en la siguiente ventana
                        Detalles_Compra.setCompra(tabla_compras_documentos_pendientes.getSelectionModel().getSelectedItem());
                        // Abrimos la ventana
                        iniciar_detalles_compra();
                    }
                }
            });
            tabla_pagos_proximos_30_dias.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (tabla_pagos_proximos_30_dias.getSelectionModel().isEmpty()){
                        System.out.println("clic vacío");
                    }else {
                        // Asigno la compra que vamos a mostrar en la siguiente ventana
                        Detalles_Compra.setCompra(tabla_pagos_proximos_30_dias.getSelectionModel().getSelectedItem());
                        // Abrimos la ventana
                        iniciar_detalles_compra();
                    }
                }
            });
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }

    @FXML
    void buscar_compras() {
        //Factura
        ObservableList<Compra> lista_facturas = FXCollections.observableArrayList();
        try {
            Conexion c = new Conexion();
            // - - - - Todas las compras realizadas
            ResultSet completas = c.mostrarSql(c.buscar_compra_factura(txt_busqueda_compras.getText()));
            while (completas.next()) {

                // Estas propiedades se deben llamar igual que los campos de la consulta
                for (int x = 0; x < 1; x++) {
                    lista_facturas.add(new Compra(
                            completas.getInt("reg"),
                            completas.getInt("id_proveedor"),
                            completas.getString("nombre_proveedor"),
                            completas.getString("numero_cotizacion"),
                            completas.getString("numero_factura"),
                            completas.getString("numero_orden_compra"),
                            completas.getDate("fecha_compra"),
                            completas.getDate("fecha_limite"),
                            completas.getDouble("adeudo"),
                            completas.getDouble("cantidad_restante"),
                            completas.getString("notas"),
                            completas.getString("estado")));
                }
            }
            // Le asignamos a la tabla la lista contiene lo que va a mostrar | falta decirle a cada columna que dato mostrará
            tabla_compras.setItems(lista_facturas);

            // Asignamos cada dato que mostrarán las columnas | Los nombres de las propiedades vienen del tipo de clase
            tabla_compras_columna_orden_compra.setCellValueFactory(new PropertyValueFactory<>("orden_compra"));
            tabla_compras_columna_cotizacion.setCellValueFactory(new PropertyValueFactory<>("cotizacion"));
            tabla_compras_columna_factura.setCellValueFactory(new PropertyValueFactory<>("factura"));
            tabla_compras_columna_proveedor.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
            tabla_compras_columna_monto.setCellValueFactory(new PropertyValueFactory<>("adeudo"));
            tabla_compras_columna_fecha_compra.setCellValueFactory(new PropertyValueFactory<>("fecha_compra"));
            tabla_compras_columna_fecha_pago.setCellValueFactory(new PropertyValueFactory<>("fecha_limite"));
            tabla_compras_columna_cantidad_restante.setCellValueFactory(new PropertyValueFactory<>("cantidad_restante"));

            c.cerrarConexion();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @FXML
    void buscar_compras_documentos() {
        //Factura
        ObservableList<Compra> lista_facturas = FXCollections.observableArrayList();
        try {
            Conexion c = new Conexion();
            // - - - - Todas las compras realizadas
            ResultSet completas = c.mostrarSql(c.buscar_compra_pendientes_factura(txt_busqueda_compras_documentos_faltantes.getText()));
            while (completas.next()) {

                // Estas propiedades se deben llamar igual que los campos de la consulta
                for (int x = 0; x < 1; x++) {
                    lista_facturas.add(new Compra(
                            completas.getInt("reg"),
                            completas.getInt("id_proveedor"),
                            completas.getString("nombre_proveedor"),
                            completas.getString("numero_cotizacion"),
                            completas.getString("numero_factura"),
                            completas.getString("numero_orden_compra"),
                            completas.getDate("fecha_compra"),
                            completas.getDate("fecha_limite"),
                            completas.getDouble("adeudo"),
                            completas.getDouble("cantidad_restante"),
                            completas.getString("notas"),
                            completas.getString("estado")));
                }
            }
            tabla_compras_documentos_pendientes.setItems(lista_facturas);
            tabla_compras_documentos_pendientes_columna_orden_compra.setCellValueFactory(new PropertyValueFactory<>("orden_compra"));
            tabla_compras_documentos_pendientes_columna_cotizacion.setCellValueFactory(new PropertyValueFactory<>("cotizacion"));
            tabla_compras_documentos_pendientes_columna_factura.setCellValueFactory(new PropertyValueFactory<>("factura"));
            tabla_compras_documentos_pendientes_columna_proveedor.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
            tabla_compras_documentos_pendientes_columna_monto.setCellValueFactory(new PropertyValueFactory<>("adeudo"));
            tabla_compras_documentos_pendientes_columna_fecha_compra.setCellValueFactory(new PropertyValueFactory<>("fecha_compra"));
            tabla_compras_documentos_pendientes_columna_fecha_pago.setCellValueFactory(new PropertyValueFactory<>("fecha_limite"));
            tabla_compras_documentos_pendientes_columna_cantidad_restante.setCellValueFactory(new PropertyValueFactory<>("cantidad_restante"));

            c.cerrarConexion();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @FXML
    void enter_compras(KeyEvent event) {
        switch (event.getCode()) {
            case ENTER: {
                buscar_compras();
            }
        }
    }

    @FXML
    void enter_compras_documentos(KeyEvent event) {
        switch (event.getCode()) {
            case ENTER: {
                buscar_compras_documentos();
            }
        }
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - Abrir Ventanas
    static Stage ventana_nueva_compra = new Stage();
    static Stage ventana_proveedores = new Stage();
    static Stage ventana_detalles_compra = new Stage();
    @FXML
    void iniciar_nueva_compra(){
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../fxml/Compras/Nueva_Compra.fxml"));
            Parent abrir = fxmlLoader.load();

            // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
            if (ventana_nueva_compra.getScene() == null) {
                ventana_nueva_compra.setTitle("Registrar una compra");
                ventana_nueva_compra.setScene(new Scene(abrir));
                ventana_nueva_compra.getIcons().add(new Image("sample/img/iconos/icono_compras.png"));
                ventana_nueva_compra.show();

                // El evento vaciará la ventana antes de ser cerrada, así se podrá abrir nuevamente
                ventana_nueva_compra.setOnCloseRequest(e -> {
                    ventana_nueva_compra.setScene(null);
                    llenartablas();
                });
            }
            else {
                // Si la ventana tiene una escena, la trae al frente
                ventana_nueva_compra.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    @FXML
    void iniciar_proveedores(){
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../fxml/Compras/Proveedores.fxml"));
            Parent abrir = fxmlLoader.load();

            // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
            if (ventana_proveedores.getScene() == null) {
                ventana_proveedores.setTitle("Proveedores");
                ventana_proveedores.setScene(new Scene(abrir));
                ventana_proveedores.getIcons().add(new Image("sample/img/iconos/lista.png"));
                ventana_proveedores.show();

                // El evento vaciará la ventana antes de ser cerrada, así se podrá abrir nuevamente
                ventana_proveedores.setOnCloseRequest(e -> {
                    ventana_proveedores.setScene(null);
                });
            }
            else {
                // Si la ventana tiene una escena, la trae al frente
                ventana_proveedores.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

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
                    llenartablas();
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
