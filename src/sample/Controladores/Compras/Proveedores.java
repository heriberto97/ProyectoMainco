package sample.Controladores.Compras;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Conexion_bd.Conexion;
import sample.objetos.Compras.Proveedor;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Proveedores implements Initializable {
    @FXML private Button btn_nuevo_proveedor;

    @FXML private TableView<Proveedor> tabla_proveedores;
    @FXML private TableColumn<Proveedor, String> tabla_proveedores_columna_nombre_proveedor;
    @FXML private TableColumn<Proveedor, String> tabla_proveedores_columna_plazo_pagos;
    @FXML private TableColumn<Proveedor, Double> tabla_proveedores_columna_limite_credito;
    @FXML private TableColumn<Proveedor, Double> tabla_proveedores_columna_credito_disponible;
    @FXML private TableColumn<Proveedor, String> tabla_proveedores_columna_telefono;
    @FXML private TableColumn<Proveedor, String> tabla_proveedores_columna_correo_electronico;
    @FXML private TableColumn<Proveedor, String> tabla_proveedores_columna_rfc;
    @FXML private TextField txt_busqueda_proveedores;


    private Conexion c = new Conexion();
    private ObservableList<Proveedor> lista_proveedores;

    // - - - - - - - - - - Ejecutar al Iniciar la ventana
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lista_proveedores = FXCollections.observableArrayList();

        llenar_tabla();
    }

    @FXML
    void llenar_tabla(){
        try {
            ResultSet proveedores = c.mostrarSql(c.mostrar_proveedores());
            while (proveedores.next()) {

                // Estas propiedades se deben llamar igual que los campos de la consulta
                for (int x = 0; x < 1; x++) {
                    lista_proveedores.add(new Proveedor(
                            proveedores.getString("nombre_proveedor"),
                            proveedores.getInt("dias_limite"),
                            proveedores.getDouble("credito"),
                            proveedores.getDouble("credito_disponible"),
                            proveedores.getString("telefono"),
                            proveedores.getString("correo"),
                            proveedores.getString("rfc")
                    ));
                }
            }
            // Le asignamos a la tabla la lista contiene lo que va a mostrar | falta decirle a cada columna que dato mostrará
            tabla_proveedores.setItems(lista_proveedores);

            // Asignamos cada dato que mostrarán las columnas | Los nombres de las propiedades vienen del tipo de clase
            tabla_proveedores_columna_nombre_proveedor.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            tabla_proveedores_columna_plazo_pagos.setCellValueFactory(new PropertyValueFactory<>("dias_limite"));
            tabla_proveedores_columna_limite_credito.setCellValueFactory(new PropertyValueFactory<>("credito"));
            tabla_proveedores_columna_credito_disponible.setCellValueFactory(new PropertyValueFactory<>("credito_disponible"));
            tabla_proveedores_columna_telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
            tabla_proveedores_columna_correo_electronico.setCellValueFactory(new PropertyValueFactory<>("correo"));
            tabla_proveedores_columna_rfc.setCellValueFactory(new PropertyValueFactory<>("rfc"));
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - Abrir Ventanas
    private Stage ventana_nuevo_proveedor = new Stage();
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
