package sample.Controladores.Compras;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

    @FXML private TableView<Compra> tabla_pagos_proximos_30_dias;
    @FXML private TableColumn<Compra, Date> tabla_pagos_proximos_columna_fecha_pago;
    @FXML private TableColumn<Compra, String> tabla_pagos_proximos_columna_proveedor;
    @FXML private TableColumn<Compra, Double> tabla_pagos_proximos_columna_monto;


    // Objetos usados en la clase
    private Conexion c = new Conexion();
    private ObservableList<Compra> lista_compras;
    private ObservableList<Compra> lista_compras_pagos_proximos;



    // - - - - - - - - - - Ejecutar al Iniciar la ventana
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Inicializamos las listas para ser usadas posteriormente
        lista_compras = FXCollections.observableArrayList();
        lista_compras_pagos_proximos = FXCollections.observableArrayList();

        llenartablas();
    }

    @FXML
    void llenartablas(){
        try {
            // - - - - Todas las compras realizadas
            ResultSet completas = c.mostrarSql(c.mostrarcompras());
            while (completas.next()){

                // Estas propiedades se deben llamar igual que los campos de la consulta
                lista_compras.add(new Compra(
                        completas.getString("nombre_proveedor"),
                        completas.getString("numero_cotizacion"),
                        completas.getString("numero_factura"),
                        completas.getString("numero_orden_compra"),
                        completas.getDate("fecha_compra"),
                        completas.getDate("fecha_limite"),
                        completas.getDouble("adeudo"),
                        completas.getDouble("cantidad_restante")));
            }
            // Le asignamos a la tabla la lista contiene lo que va a mostrar | falta decirle a cada columna que dato mostrará
            tabla_compras.setItems(lista_compras);

            // Asignamos cada dato que mostrarán las columnas |Todo - Los nombres de las propiedades vienen del tipo de clase
            tabla_compras_columna_orden_compra.setCellValueFactory(new PropertyValueFactory<>("orden_compra"));
            tabla_compras_columna_cotizacion.setCellValueFactory(new PropertyValueFactory<>("cotizacion"));
            tabla_compras_columna_factura.setCellValueFactory(new PropertyValueFactory<>("factura"));
            tabla_compras_columna_proveedor.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
            tabla_compras_columna_monto.setCellValueFactory(new PropertyValueFactory<>("adeudo"));
            tabla_compras_columna_fecha_compra.setCellValueFactory(new PropertyValueFactory<>("fecha_compra"));
            tabla_compras_columna_fecha_pago.setCellValueFactory(new PropertyValueFactory<>("fecha_limite"));
            tabla_compras_columna_cantidad_restante.setCellValueFactory(new PropertyValueFactory<>("cantidad_restante"));



            // - - - - Todas las compras en los próximos 30 días
            ResultSet compras_pagar = c.mostrarSql(c.mostrar_compras_a_pagar());
            while (compras_pagar.next()){
                lista_compras_pagos_proximos.add(
                        new Compra(
                                compras_pagar.getString("nombre_proveedor"),
                                compras_pagar.getDate("fecha_limite"),
                                compras_pagar.getDouble("cantidad_restante")));
            }
            tabla_pagos_proximos_30_dias.setItems(lista_compras_pagos_proximos);
            tabla_pagos_proximos_columna_fecha_pago.setCellValueFactory(new PropertyValueFactory<>("fecha_limite"));
            tabla_pagos_proximos_columna_proveedor.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
            tabla_pagos_proximos_columna_monto.setCellValueFactory(new PropertyValueFactory<>("cantidad_restante"));
        }
        catch(SQLException e) {;
            System.out.println(e);
        }
    }
}
