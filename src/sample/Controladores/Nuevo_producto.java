package sample.Controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javafx.util.StringConverter;
import sample.Conexion_bd.Conexion;
import sample.objetos.Compras.Proveedor;
import sample.objetos.Empresa;
import sample.objetos.Material;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class Nuevo_producto implements Initializable {

    Conexion c = new Conexion();
    @FXML
    ComboBox<Empresa> cb_empresas;

    @FXML
    ComboBox<Material> cb_materiales;
    // ComboBox<Empresa> cb_empresas;
    ObservableList <Empresa>  data;
    ObservableList <Material>  materiales;
    private int id;
    private String nombre;
    private int id_material;
    private String nombre_material;


    //CUANDO INICIA EL FORM PERRON
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        llenarcomboempresas();
        llenarcombomateriales();


    }

    //studentPicker.setItems(null);


    public void guardar() {

     int a =  cb_materiales.getSelectionModel().getSelectedItem().getId();
     System.out.println(a);

    }

    public void llenarcomboempresas() {
        data = FXCollections.observableArrayList();
        try {

            ResultSet rs = c.mostrarSql(c.comboempresas());
            while (rs.next()) {
                //get string from db,whichever way

                data.add(new Empresa(
                        rs.getInt("id"),
                        rs.getString("nombre")));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        Callback<ListView<Empresa>, ListCell<Empresa>> factory = lv -> new ListCell<Empresa>() {
            @Override
            protected void updateItem(Empresa item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNombre());
            }

        };

        cb_empresas.setCellFactory(factory);
        cb_empresas.setButtonCell(new ListCell<Empresa>() {
            @Override
            protected void updateItem(Empresa t, boolean bln) {
                super.updateItem(t, bln);
                if (t != null) {
                    setText(t.getNombre());

                    id= t.getId();
                    nombre = t.getNombre();

                } else {
                    setText(null);
                }
            }
        });

        cb_empresas.setItems(data);
        cb_empresas.setValue(data.get(0));
//        cb_empresas.setItems(null);
//        cb_empresas.setItems(data);
    }
    public void llenarcombomateriales()
    {
        materiales = FXCollections.observableArrayList();
        try {

            ResultSet rs = c.mostrarSql(c.combomateriales());
            while (rs.next()) {
                //get string from db,whichever way

                materiales.add(new Material(
                        rs.getInt("id"),
                        rs.getString("nombre")));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        Callback<ListView<Material>, ListCell<Material>> factory = lv -> new ListCell<Material>() {
            @Override
            protected void updateItem(Material item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNombre());
            }

        };

        cb_materiales.setCellFactory(factory);
        cb_materiales.setButtonCell(new ListCell<Material>() {
            @Override
            protected void updateItem(Material t, boolean bln) {
                super.updateItem(t, bln);
                if (t != null) {
                    setText(t.getNombre());

                    id_material= t.getId();
                    nombre_material = t.getNombre();

                } else {
                    setText(null);
                }
            }
        });

        cb_materiales.setItems(materiales);
        cb_materiales.setValue(materiales.get(0));
    }
}

