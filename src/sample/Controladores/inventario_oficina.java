package sample.Controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Conexion_bd.Conexion;
import sample.objetos.Compras.Compra;
import sample.objetos.Inventario_oficina;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class inventario_oficina implements Initializable {


    @FXML private TableView<Inventario_oficina> tablita;
    @FXML private TableColumn<Inventario_oficina, Integer> columna_numero_articulo;
    @FXML private TableColumn<Inventario_oficina, String> columna_descripcion;
    @FXML private TableColumn<Inventario_oficina, Integer> columna_cantidad;
    @FXML private TableColumn<Inventario_oficina, String> columna_estado;
    @FXML
    ComboBox filtrar;
    private Conexion c = new Conexion();
    private ObservableList<Inventario_oficina> lista_articulos;

    public void llenarcombo()
    {
        ObservableList<String> items1 = FXCollections.observableArrayList();
        items1.addAll("Numero de articulo", "descripcion", "estado");
        filtrar.setItems(items1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        llenarcombo();
    lista_articulos =  FXCollections.observableArrayList();

    try {

        ResultSet datitos = c.mostrarSql(c.verarticulosoficina());

        while (datitos.next()) {
            for (int z=0; z<1;z++)
            {
                lista_articulos.add(new Inventario_oficina(
                        datitos.getInt("id"),
                        datitos.getString("descripcion"),
                        datitos.getInt("cantidad"),
                        datitos.getString("estado")));
            }
        }
        tablita.setItems(lista_articulos);


        columna_numero_articulo.setCellValueFactory(new PropertyValueFactory<>("id"));
        columna_descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        columna_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        columna_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        c.cerrarConexion();
    }
    catch (Exception e)
    {

    }

    }
}
