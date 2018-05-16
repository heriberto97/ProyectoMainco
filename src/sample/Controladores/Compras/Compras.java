package sample.Controladores.Compras;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sample.Conexion_bd.Conexion;
import sample.objetos.Compras.Compra;

import javax.swing.table.TableColumn;
import javax.swing.text.TableView;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Compras implements Initializable {
    // Tabla de todas las compras
    @FXML TableView tabla_compras;
    @FXML TableColumn tabla_compras_columna_orden_compra;
    @FXML TableColumn tabla_compras_columna_cotizacion;
    @FXML TableColumn tabla_compras_columna_factura;
    @FXML TableColumn tabla_compras_columna_proveedor;
    @FXML TableColumn tabla_compras_columna_monto;
    @FXML TableColumn tabla_compras_columna_fecha_compra;
    @FXML TableColumn tabla_compras_columna_fecha_pago;

    // Tabla de compras con documentos pendientes
    @FXML TableView tabla_compras_documentos_pendientes;
    @FXML TableColumn tabla_compras_documentos_pendientes_columna_orden_compra;
    @FXML TableColumn tabla_compras_documentos_pendientes_columna_cotizacion;
    @FXML TableColumn tabla_compras_documentos_pendientes_columna_factura;
    @FXML TableColumn tabla_compras_documentos_pendientes_columna_proveedor;
    @FXML TableColumn tabla_compras_documentos_pendientes_columna_monto;
    @FXML TableColumn tabla_compras_documentos_pendientes_columna_fecha_compra;
    @FXML TableColumn tabla_compras_documentos_pendientes_columna_fecha_pago;

    // Tabla de pagos de los prox 30 días
    @FXML TableView tabla_pagos_proximos_30_dias;
    @FXML TableColumn tabla_pagos_proximos_columna_fecha_pago;
    @FXML TableColumn tabla_pagos_proximos_columna_proveedor;
    @FXML TableColumn tabla_pagos_proximos_columna_monto;

    // - - - - - - - - - - Ejecutar al Iniciar la ventana
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        llenartablas();
    }

    private Conexion c = new Conexion();
    // en un objeto, se usan posiciones reales ej. si la tabla lleva 8 columnas, el objeto lleva 8 posiciones
    public Object compras[] = new Object[8];
    final ObservableList<Compra> datos = FXCollections.observableArrayList();

    @FXML
    void llenartablas(){
        try {
            // mostrar los datos de las compras en la tabla principal
            ResultSet res = c.mostrarSql(c.mostrarcompras());
            while (res.next()) {
                for (int i = 0; i <= 7; i++) {
                    // aqui llenamos el objeto renglón por renglón después de los titulos de las columnas, por eso el "i+1"
                    compras[i] = res.getObject(i + 1);
                    datos.add(new Compra(compras[1].toString(),Double.parseDouble(compras[2].toString()),compras[3].toString(),compras[4].toString(),compras[5].toString(),compras[6].toString(),compras[7].toString(),Double.parseDouble(compras[8].toString())));
                }
            }

        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }
}
