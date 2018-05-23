package sample.Controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Conexion_bd.Conexion;
import sample.objetos.Compras.Compra;
import sample.objetos.Inventario_oficina;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class inventario_oficina implements Initializable {


    @FXML private TableView<Inventario_oficina> tv_articulos;
    @FXML private TableColumn<Inventario_oficina, Integer> columna_numero_articulo;
    @FXML private TableColumn<Inventario_oficina, String> columna_descripcion;
    @FXML private TableColumn<Inventario_oficina, Integer> columna_cantidad;
    @FXML private TableColumn<Inventario_oficina, String> columna_estado;
    @FXML
    ComboBox cb_filtrar;
    @FXML
    Button btn_nuevo_articulo;
    private Conexion c = new Conexion();
    private ObservableList<Inventario_oficina> lista_articulos;
    private Stage nuevo_articulo = new Stage();
    private Stage modificar_articulo = new Stage();
     //METODO PARA ABRIR FORKULARIO NUEVO ARTICULO
    public void abrir_form(javafx.event.ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Nuevo_articulo.fxml"));
            Parent abrir = fxmlLoader.load();

            // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
            if (nuevo_articulo.getScene() == null) {
               nuevo_articulo.setTitle("Nuevo articulo");
               nuevo_articulo.setScene(new Scene(abrir));
               nuevo_articulo.show();

                // El evento vaciará la ventana antes de ser cerrada, así se podrá abrir nuevamente
                nuevo_articulo.setOnCloseRequest(e -> {
                    nuevo_articulo.setScene(null);
                });
            }
            else {
                // Si la ventana tiene una escena, la trae al frente
               nuevo_articulo.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println("si jalo bien aca");
    }
    //MWTODO PARA ABRIR UN NUEVO FORMULARIO CON LA INFORMACION DE UN ARTICULO PARA MODIFICAR
    public void click_articulo(MouseEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/modificar_articulo.fxml"));
            Parent abrir = fxmlLoader.load();

            // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
            if (modificar_articulo.getScene() == null) {
                modificar_articulo.setTitle("Modificar articulo");
                modificar_articulo.setScene(new Scene(abrir));
                modificar_articulo.show();

                // El evento vaciará la ventana antes de ser cerrada, así se podrá abrir nuevamente
                modificar_articulo.setOnCloseRequest(e -> {
                    modificar_articulo.setScene(null);
                });
            }
            else {
                // Si la ventana tiene una escena, la trae al frente
                nuevo_articulo.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
     // String a =  tablita.getSelectionModel().getSelectedItem().getDescripcion();
      //System.out.println(a);
    }
    //METODO PARA LLENAR EL COMBOBOX DE OPCIONES
    public void llenarcombo()
    {
        ObservableList<String> items1 = FXCollections.observableArrayList();
        items1.addAll("Numero de articulo", "Descripcion", "Estado");
        cb_filtrar.setItems(items1);
    }
    //METODO AL INICIAR LA VENTANA
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
        tv_articulos.setItems(lista_articulos);


        columna_numero_articulo.setCellValueFactory(new PropertyValueFactory<>("id"));
        columna_descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        columna_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        columna_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        c.cerrarConexion();
    }
    catch (Exception e)
    {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Revisa tu conexion");
        alerta.setHeaderText("¡Error de servidor!");
        alerta.setContentText("Algo esta fallando");
        alerta.showAndWait();
    }

    }
}
