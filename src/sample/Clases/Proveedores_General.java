package sample.Clases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.Conexion_bd.Conexion;
import sample.objetos.Compras.Proveedor;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Proveedores_General implements Initializable {
    @FXML private TableView<Proveedor> tabla_proveedores;
    @FXML private TableColumn<Proveedor, String> tabla_proveedores_columna_nombre_proveedor;
    @FXML private TableColumn<Proveedor, Double> tabla_proveedores_columna_deuda_total;
    @FXML private TableColumn<Proveedor, String> tabla_proveedores_columna_pago_proximo;


    private Conexion c = new Conexion();
    private ObservableList<Proveedor> lista_proveedores;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        llenar_tabla();
    }

    @FXML
    void llenar_tabla() {
        lista_proveedores = FXCollections.observableArrayList();
        try {
            ResultSet proveedores = c.mostrarSql(c.mostrar_proveedores_general());
            while (proveedores.next()) {

                // Estas propiedades se deben llamar igual que los campos de la consulta
                for (int x = 0; x < 1; x ++) {
                    lista_proveedores.add(new Proveedor(
                            proveedores.getInt("id"),
                            proveedores.getString("nombre_proveedor"),
                            proveedores.getDouble("deuda"),
                            proveedores.getInt("dias")
                    ));
                    System.out.println(proveedores.getDouble("deuda"));
                }
            }
            // Le asignamos a la tabla la lista contiene lo que va a mostrar | falta decirle a cada columna que dato mostrará
            tabla_proveedores.setItems(lista_proveedores);

            // Asignamos cada dato que mostrarán las columnas | Los nombres de las propiedades vienen del tipo de clase
            tabla_proveedores_columna_nombre_proveedor.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            tabla_proveedores_columna_deuda_total.setCellValueFactory(new PropertyValueFactory<>("deuda"));
            tabla_proveedores_columna_pago_proximo.setCellValueFactory(new PropertyValueFactory<>("dias_limite"));

            tabla_proveedores.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (tabla_proveedores.getSelectionModel().isEmpty()){
                        System.out.println("clic vacío");
                    }else {
                        // algo
                    }
                }
            });



            c.cerrarConexion();
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }
}
