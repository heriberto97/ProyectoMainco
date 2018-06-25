package sample.Clases;

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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sample.Conexion_bd.Conexion;
import sample.objetos.Compras.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Detalles_Compra implements Initializable {
    @FXML private Label lbl_monto;
    @FXML private Label lbl_fecha_compra;
    @FXML private Label lbl_fecha_limite_pago;
    @FXML private TextField txt_monto_pagar;
    @FXML private TextArea txt_notas;
    @FXML private Label lbl_cantidad_pagada;
    @FXML private Label lbl_proveedor;
    @FXML private Button btn_registrar_pago;
    @FXML private Label lbl_por_pagar;
    @FXML private Label lbl_metodo;
    @FXML private ComboBox<String> combo_metodo_pago;

    @FXML private TextField txt_factura;
    @FXML private TextField txt_esquema_factura;
    @FXML private Button btn_esquema_factura;

    @FXML private TextField txt_cotizacion;
    @FXML private TextField txt_esquema_cotizacion;
    @FXML private Button btn_esquema_cotizacion;

    @FXML private TextField txt_orden_compra;
    @FXML private TextField txt_esquema_orden_compra;
    @FXML private Button btn_esquema_orden_compra;

    @FXML private TableView<Pago> tabla_abonos_realizados;
    @FXML private TableColumn<Pago, Date> tabla_abonos_realizados_columna_fecha_abono;
    @FXML private TableColumn<Pago, Double> tabla_abonos_realizados_columna_monto;
    @FXML private TableColumn<Pago, String> tabla_abonos_realizados_columna_metodo_pago;


    static Compra compra = new Compra();
    private Factura factura = new Factura();
    private Cotizacion cotizacion = new Cotizacion();
    private Orden_compra orden_compra = new Orden_compra();
    private ObservableList<Pago> lista_pagos;
    private ObservableList<String> lista_metodos_pago;

    public static void setCompra(Compra compra) {
        Detalles_Compra.compra = compra;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lista_metodos_pago = FXCollections.observableArrayList();
        lista_metodos_pago.addAll("Transferencia","Cheque");
        combo_metodo_pago.setItems(lista_metodos_pago);
        combo_metodo_pago.setValue(lista_metodos_pago.get(0));

        llenar_pagos();
        consultar_extras();

        txt_monto_pagar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,11}([\\.]\\d{0,2})?")) {
                    txt_monto_pagar.setText(oldValue);
                }
            }
        });

        lbl_monto.setText("$" + String.valueOf(compra.getAdeudo()));

        txt_factura.setText(compra.getFactura());
        txt_cotizacion.setText(compra.getCotizacion());
        txt_orden_compra.setText(compra.getOrden_compra());

        lbl_proveedor.setText(compra.getProveedor());
        lbl_fecha_compra.setText(String.valueOf(compra.getFecha_compra()));
        lbl_fecha_limite_pago.setText(String.valueOf(compra.getFecha_limite()));
        txt_notas.setText(compra.getNotas());
        lbl_cantidad_pagada.setText(String.valueOf(compra.getAdeudo() - compra.getCantidad_restante()));

        if (compra.getCantidad_restante() > 0) {
            txt_monto_pagar.setText(String.valueOf(compra.getCantidad_restante()));
        } else {
            txt_monto_pagar.setVisible(false);
            lbl_por_pagar.setVisible(false);
            btn_registrar_pago.setVisible(false);
            combo_metodo_pago.setVisible(false);
            lbl_metodo.setVisible(false);
        }
    }

    @FXML
    void llenar_pagos(){
        lista_pagos = FXCollections.observableArrayList();
        Conexion c = new Conexion();
        try {
            // - - - - Todas las compras realizadas
            ResultSet pagos = c.mostrarSql(c.mostrar_pagos_compra(compra.getReg()));
            while (pagos.next()){

                // Estas propiedades se deben llamar igual que los campos de la consulta
                for (int x = 0; x < 1; x++){
                    lista_pagos.add(new Pago(
                            pagos.getInt("reg"),
                            pagos.getDate("fecha"),
                            pagos.getDouble("pago"),
                            pagos.getString("metodo_pago")
                    ));
                }
            }
            // Le asignamos a la tabla la lista contiene lo que va a mostrar | falta decirle a cada columna que dato mostrará
            tabla_abonos_realizados.setItems(lista_pagos);

            // Asignamos cada dato que mostrarán las columnas | Los nombres de las propiedades vienen del tipo de clase
            tabla_abonos_realizados_columna_fecha_abono.setCellValueFactory(new PropertyValueFactory<>("fecha_pago"));
            tabla_abonos_realizados_columna_monto.setCellValueFactory(new PropertyValueFactory<>("pago"));
            tabla_abonos_realizados_columna_metodo_pago.setCellValueFactory(new PropertyValueFactory<>("metodo_pago"));

        }
        catch(SQLException e) {;
            System.out.println(e);
        }
    }

    @FXML
    void consultar_extras(){
        Conexion conexion = new Conexion();

        ResultSet factura_compra = conexion.mostrarSql(conexion.mostrar_datos_factura(compra.getFactura()));
        try {
            while (factura_compra.next()) {
                for (int x = 0; x < 1; x++) {
                    factura.setId_factura(factura_compra.getInt("id"));
                    factura.setEsquema_factura(factura_compra.getString("esquema_factura"));
                    txt_esquema_factura.setText(factura_compra.getString("esquema_factura"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet cotizacion_compra = conexion.mostrarSql(conexion.mostrar_datos_cotizacion(compra.getCotizacion()));
        try {
            while (cotizacion_compra.next()) {
                for (int x = 0; x < 1; x++) {
                    cotizacion.setId_cotizacion(cotizacion_compra.getInt("id"));
                    cotizacion.setEsquema(cotizacion_compra.getString("esquema_cotizacion"));
                    txt_esquema_cotizacion.setText(cotizacion_compra.getString("esquema_cotizacion"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet orden_compra_compra = conexion.mostrarSql(conexion.mostrar_datos_orden_compra(compra.getOrden_compra()));
        try {
            while (orden_compra_compra.next()) {
                for (int x = 0; x < 1; x++) {
                    orden_compra.setId_orden_compra(orden_compra_compra.getInt("id"));
                    orden_compra.setEsquema_orden_compra(orden_compra_compra.getString("esquema_orden_compra"));
                    txt_esquema_orden_compra.setText(orden_compra_compra.getString("esquema_orden_compra"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        conexion.cerrarConexion();
    }

    @FXML
    void realizar_pago(Event event){
        if(verificar_monto_compra()) {
            switch (combo_metodo_pago.getSelectionModel().getSelectedIndex()) {
                case 0: {
                    pagar();
                }
                break;
                case 1: {
                    pagar();
                }
                break;
            }
        }
    }
    @FXML
    void pagar(){
        Conexion asd = new Conexion();
        asd.conecta();
        String metodo_pago = combo_metodo_pago.getSelectionModel().getSelectedItem();

        Double cantidad_pago = Double.parseDouble(txt_monto_pagar.getText());

        if(Double.parseDouble(txt_monto_pagar.getText()) <= compra.getCantidad_restante()){
            if(Double.parseDouble(txt_monto_pagar.getText()) == compra.getCantidad_restante()){

                asd.realizar_pago(compra.getReg(), cantidad_pago, metodo_pago);
                asd.actualizar_pago(compra.getReg(), compra.getCantidad_restante() - cantidad_pago);

                // Cerramos la ventana
                Detalles_Proveedor.ventana_detalles_compra = new Stage();
                Compras.ventana_detalles_compra = new Stage();

                Stage stage = (Stage) this.btn_registrar_pago.getScene().getWindow();
                stage.getOnCloseRequest().handle( new WindowEvent(
                        stage,
                        WindowEvent.WINDOW_CLOSE_REQUEST));
                stage.close();
            }
            else{
                asd.realizar_abono(compra.getReg(), cantidad_pago, metodo_pago);
                asd.actualizar_pago(compra.getReg(), compra.getCantidad_restante() - cantidad_pago);

                // NOTIFICAR QUE SE REALIZÓ EL ABONO

                // Cerramos la ventana
                Detalles_Proveedor.ventana_detalles_compra = new Stage();
                Compras.ventana_detalles_compra = new Stage();

                Stage stage = (Stage) this.btn_registrar_pago.getScene().getWindow();
                stage.getOnCloseRequest().handle( new WindowEvent(
                        stage,
                        WindowEvent.WINDOW_CLOSE_REQUEST));
                stage.close();
            }
        }else {
            // Alerta para decir que no se puede pagar más de lo que se debe
        }
        asd.cerrarConexion();

        Image img = new Image("/sample/Clases/check.png");
        Notifications noti = Notifications.create()
                .title("Pago realizado!")
                .text("El pago se registró con éxito")
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
    @FXML
    boolean verificar_monto_compra(){
        try{
            if (Double.parseDouble(txt_monto_pagar.getText()) > 0){
                Double monto_compra = Double.parseDouble(txt_monto_pagar.getText());
                System.out.println(monto_compra + " es una cantidad válida!");
                return true;
            }
            else{
                System.out.println("no es un numero válido");

                Image img = new Image("/sample/Clases/alerta.png");
                Notifications noti = Notifications.create()
                        .title("Monto de pago inválido!")
                        .text("Por favor, revise los datos!")
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
                return false;
            }
        } catch (NumberFormatException e){
            System.out.println("no es un numero válido");
            Image img = new Image("/sample/Clases/alerta.png");
            Notifications noti = Notifications.create()
                    .title("Monto de pago inválido!")
                    .text("Por favor, revise los datos!")
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
            return false;
        }
    }


    @FXML
    void actualizar_compra(){
        Conexion c = new Conexion();
        Compra compra_actualizar = new Compra();
        compra_actualizar.setReg(compra.getReg());

        //* Validación para actualizar Factura, si no existe, se registra una nueva y se le asigna
            if (txt_factura.getText().trim() != compra.getFactura() || txt_esquema_factura.getText().trim() != factura.getEsquema_factura()){
                if (txt_factura.getText() != compra.getFactura()){
                    factura.setNumero_factura(txt_factura.getText());
                }
                if (txt_esquema_factura.getText() != factura.getEsquema_factura()) {
                    String ruta_esquema_factura =   txt_esquema_factura.getText().replace( "\\","\\"+"\\");
                    factura.setEsquema_factura(ruta_esquema_factura);
                }

                // Actualizamos la factura
                c.actualizar_factura(factura);
                System.out.println( "  - Factura actualizada");
            }

        //* Validación para actualizar Cotizacion, si no existe, se registra una nueva y se le asigna
        if (txt_cotizacion.getText().trim() != compra.getCotizacion() || txt_esquema_cotizacion.getText().trim() != cotizacion.getEsquema()){
                if (txt_cotizacion.getText().trim() != compra.getCotizacion()){
                    cotizacion.setNumero_cotizacion(txt_cotizacion.getText());
                }
                if (txt_esquema_cotizacion.getText().trim() != cotizacion.getEsquema()) {
                    String ruta_esquema_cotizacion =   txt_esquema_cotizacion.getText().replace( "\\","\\"+"\\");
                    cotizacion.setEsquema(ruta_esquema_cotizacion);
                }

                // Actualizamos la Cotización
                c.actualizar_cotizacion(cotizacion);
                System.out.println( "  - Cotización actualizada");
            }

        //* Validación para actualizar Orden de compra, si no existe, se registra una nueva y se le asigna
        if (txt_orden_compra.getText().trim() != compra.getOrden_compra() || txt_esquema_orden_compra.getText().trim() != orden_compra.getEsquema_orden_compra()){
                if (txt_orden_compra.getText().trim() != compra.getOrden_compra()){
                    orden_compra.setNumero_orden_compra(txt_orden_compra.getText());
                }
                if (txt_esquema_orden_compra.getText().trim() != orden_compra.getEsquema_orden_compra()) {
                    String ruta_esquema_orden_compra =   txt_esquema_orden_compra.getText().replace( "\\","\\"+"\\");
                    orden_compra.setEsquema_orden_compra(ruta_esquema_orden_compra);
                }

                // Actualizamos la factura
                c.actualizar_orden_compra(orden_compra);
                System.out.println( "  - Orden de Compra actualizada");
            }

        compra_actualizar.setNotas(txt_notas.getText());

        c.actualizar_compra(compra_actualizar);
        c.cerrarConexion();

        Image img = new Image("/sample/Clases/check.png");
        Notifications noti = Notifications.create()
                .title("Compra Actualizada!")
                .text("Ha sido actualizada con éxito")
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

    @FXML
    void abrir_factura() {
        File pdfFile = new File(txt_esquema_factura.getText());
        if (pdfFile.exists()) {
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(pdfFile);
                } catch (IOException e) {

                    e.printStackTrace();
                }
            } else {
                System.out.println("Awt Desktop no es soportado!");
            }
        } else {
            System.out.println("El archivo no existe!");
            Image img = new Image("/sample/Clases/alerta.png");
            Notifications noti = Notifications.create()
                    .title("Error en el archivo!")
                    .text("El archivo no existe o ha sido movido.")
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
    }
    @FXML
    void abrir_cotizacion() {
        File pdfFile = new File(txt_esquema_cotizacion.getText());
        if (pdfFile.exists()) {
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(pdfFile);
                } catch (IOException e) {

                    e.printStackTrace();
                }
            } else {
                System.out.println("Awt Desktop no es soportado!");
            }
        } else {
            System.out.println("El archivo no existe!");
            Image img = new Image("/sample/Clases/alerta.png");
            Notifications noti = Notifications.create()
                    .title("Error en el archivo!")
                    .text("El archivo no existe o ha sido movido.")
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
    }
    @FXML
    void abrir_orden_compra() {
        File pdfFile = new File(txt_esquema_orden_compra.getText());
        if (pdfFile.exists()) {
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(pdfFile);
                } catch (IOException e) {

                    e.printStackTrace();
                }
            } else {
                System.out.println("Awt Desktop no es soportado!");
            }
        } else {
            System.out.println("El archivo no existe!");
            Image img = new Image("/sample/Clases/alerta.png");
            Notifications noti = Notifications.create()
                    .title("Error en el archivo!")
                    .text("El archivo no existe o ha sido movido.")
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

    // - - - - - - - - - - - - - - - - - - - - - - - - - Abrir Ventanas
    static Stage ventana_detalles_proveedor = new Stage();
    @FXML
    void abrir_proveedor(){
        Conexion con = new Conexion();

        Proveedor proveedor = new Proveedor();
        try {
        ResultSet proveedores = con.mostrarSql(con.mostrar_proveedor(compra.getId_proveedor()));
            while (proveedores.next()) {

                // Estas propiedades se deben llamar igual que los campos de la consulta
                for (int x = 0; x < 1; x++) {
                    proveedor.setId_proveedor(proveedores.getInt("id"));
                    proveedor.setNombre(proveedores.getString("nombre_proveedor"));
                    proveedor.setCredito(proveedores.getDouble("credito"));
                    proveedor.setCredito_disponible(proveedores.getDouble("credito_disponible"));
                    proveedor.setDias_limite(proveedores.getInt("dias_limite"));
                    proveedor.setTelefono(proveedores.getString("telefono"));
                    proveedor.setCorreo(proveedores.getString("correo"));
                    proveedor.setRfc(proveedores.getString("rfc"));
                    proveedor.setNotas(proveedores.getString("notas"));
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        Detalles_Proveedor.setProveedor(proveedor);
        iniciar_detalles_proveedor();

        con.cerrarConexion();
    }

    @FXML
    void iniciar_detalles_proveedor(){
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Detalles_Proveedor.fxml"));
            Parent abrir = fxmlLoader.load();

            // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
            if (ventana_detalles_proveedor.getScene() == null) {
                ventana_detalles_proveedor.setTitle("Detalles de Proveedor");
                ventana_detalles_proveedor.setScene(new Scene(abrir));
                ventana_detalles_proveedor.getIcons().add(new Image("sample/img/iconos/proveedor_editar.png"));
                ventana_detalles_proveedor.show();

                // El evento vaciará la ventana antes de ser cerrada, así se podrá abrir nuevamente
                ventana_detalles_proveedor.setOnCloseRequest(e -> {
                    ventana_detalles_proveedor.setScene(null);
                });
            }
            else {
                // Si la ventana tiene una escena, la trae al frente
                ventana_detalles_proveedor.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}