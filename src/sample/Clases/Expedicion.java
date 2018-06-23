package sample.Clases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import sample.Conexion_bd.Conexion;
import sample.objetos.*;

import javax.swing.text.TableView;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Expedicion implements Initializable {
    String nombre,apellido;
    int id;

    @FXML private javafx.scene.control.TableView<articulos_empleados> tv_expedicion;
    @FXML private TableColumn<articulos_empleados, String> columna_nombre;
    @FXML private   TableColumn<articulos_empleados, String> columna_apellido;
    @FXML private   TableColumn<articulos_empleados, String> columna_articulo;
    @FXML private   TableColumn<articulos_empleados, Integer> columna_cantidad;
    @FXML private   TableColumn<articulos_empleados, String> columna_fecha;
    private ObservableList<articulos_empleados> lista_expedidos;
    ObservableList <Trabajador>  trabajadores;
    ObservableList <Inventario_oficina> articulos;
    @FXML
    ComboBox<Trabajador> cb_trabajadores;
    @FXML
    ComboBox<Inventario_oficina> cb_articulos;
    private Conexion c = new Conexion();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         llenartabla();
         llenarcombotrabajdores();

    }

    public void llenartabla() {

        lista_expedidos =  FXCollections.observableArrayList();
        try {

            ResultSet datitos = c.mostrarSql(c.llenartablaexpedicion());

            while (datitos.next()) {
                for (int z=0; z<1;z++)
                {
                    lista_expedidos.add(new articulos_empleados(
                            datitos.getInt("reg"),
                            datitos.getString("nombre"),
                            datitos.getString("apellido"),
                            datitos.getString("descripcion"),
                            datitos.getInt("cantidad"),
                            datitos.getString("fecha")

                    ));
                }
            }

            tv_expedicion.setItems(lista_expedidos);



            columna_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre_trabajador"));
            columna_apellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
            columna_articulo.setCellValueFactory(new PropertyValueFactory<>("descripcion_articulo"));
            columna_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            columna_fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));


            c.cerrarConexion();
        }
        catch (Exception e)
        {
            System.out.println(e);
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Revisa tu conexion");
            alerta.setHeaderText("¡Error de servidor!");
            alerta.setContentText("Algo esta fallando");
            alerta.showAndWait();
        }

    }
    public void llenarcombotrabajdores()
    {
        trabajadores = FXCollections.observableArrayList();
        try {

            ResultSet rs = c.mostrarSql(c.combotrabajadores());
            while (rs.next()) {
                //get string from db,whichever way

                trabajadores.add(new Trabajador(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido_paterno")));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        Callback<ListView<Trabajador>, ListCell<Trabajador>> factory = lv -> new ListCell<Trabajador>() {
            @Override
            protected void updateItem(Trabajador item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNombre()+" "+item.getApellido_paterno());
            }

        };

        cb_trabajadores.setCellFactory(factory);
        cb_trabajadores.setButtonCell(new ListCell<Trabajador>() {
            @Override
            protected void updateItem(Trabajador t, boolean bln) {
                super.updateItem(t, bln);
                if (t != null) {
                    setText(t.getNombre()+ " "+t.getApellido_paterno());

                    id= t.getId();
                    nombre = t.getNombre();
                    apellido = t.getApellido_paterno();

                } else {
                    setText(null);
                }
            }
        });

        cb_trabajadores.setItems(trabajadores);
        cb_trabajadores.setValue(trabajadores.get(0));
    }
}
