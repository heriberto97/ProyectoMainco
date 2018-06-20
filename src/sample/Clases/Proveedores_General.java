package sample.Clases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    @FXML private TableColumn<Proveedor, Double> tabla_proveedores_columna_limite_credito;
    @FXML private TableColumn<Proveedor, Double> tabla_proveedores_columna_credito_disponible;
    @FXML private TableColumn<Proveedor, String> tabla_proveedores_columna_telefono;
    @FXML private TableColumn<Proveedor, String> tabla_proveedores_columna_correo_electronico;
    @FXML private TableColumn<Proveedor, String> tabla_proveedores_columna_rfc;

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
            ResultSet proveedores = c.mostrarSql(c.mostrar_proveedores());
            while (proveedores.next()) {

                // Estas propiedades se deben llamar igual que los campos de la consulta
                for (int x = 0; x < 1; x ++) {
                    lista_proveedores.add(new Proveedor(
                            proveedores.getInt("id"),
                            proveedores.getString("nombre_proveedor"),
                            proveedores.getInt("dias_limite"),
                            proveedores.getDouble("credito"),
                            proveedores.getDouble("credito_disponible"),
                            proveedores.getString("telefono"),
                            proveedores.getString("correo"),
                            proveedores.getString("rfc"),
                            proveedores.getString("notas")
                    ));
                }
            }
            // Le asignamos a la tabla la lista contiene lo que va a mostrar | falta decirle a cada columna que dato mostrará
            tabla_proveedores.setItems(lista_proveedores);

            // Asignamos cada dato que mostrarán las columnas | Los nombres de las propiedades vienen del tipo de clase
            tabla_proveedores_columna_nombre_proveedor.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            tabla_proveedores_columna_limite_credito.setCellValueFactory(new PropertyValueFactory<>("credito"));
            tabla_proveedores_columna_credito_disponible.setCellValueFactory(new PropertyValueFactory<>("credito_disponible"));
            tabla_proveedores_columna_telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
            tabla_proveedores_columna_correo_electronico.setCellValueFactory(new PropertyValueFactory<>("correo"));
            tabla_proveedores_columna_rfc.setCellValueFactory(new PropertyValueFactory<>("rfc"));

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
