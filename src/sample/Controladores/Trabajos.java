package sample.Controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.Conexion_bd.Conexion;
import sample.Controladores.Trabajador.Trabajadores_Alta;
import sample.objetos.Trabajador;
import sample.objetos.Trabajo;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class Trabajos implements Initializable {

    @FXML
    ListView lv_trabajosincompletos;
    @FXML
    ListView lv_trabajoscompletos;
    @FXML
    ComboBox cb_ordentrabajos;
    @FXML
    ComboBox cb_empresas;
    @FXML
    CheckBox checkb_urgente;
    @FXML
    Button btn_nuevotrabajo;



    private Stage ventana_nuevo_trabajo = new Stage();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        opciones();
        lv_trabajoscompletos.setItems(getIdsTrabajadores());

    }

    Conexion c= new Conexion();
    public Object datoempresa[] = new Object[1];
    int Contador;

    @FXML void opciones()
    {
        ObservableList<String> items1 = FXCollections.observableArrayList();
        items1.addAll("OrdenDeCompra", "Cotizacion", "Factura");
        cb_ordentrabajos.setItems(items1);



        ObservableList<String> items2 = FXCollections.observableArrayList();
        //items2.add();


    }

    public  ObservableList<Trabajo> getTrabajos(){
        ObservableList<Trabajo> trabajos= FXCollections.observableArrayList();

        try {
            ResultSet trabajosResult= c.mostrarSql(c.verTrabajos());
            while (trabajosResult.next()) {

                for (int i = 0; i <1; i++) {

                    Trabajo trabajo= new Trabajo(
                            Integer.parseInt(trabajosResult.getObject(1).toString())
                    );
                    trabajos.add(trabajo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trabajos;
    }

    public ObservableList<Integer> getIdsTrabajadores(){
        ObservableList<Trabajo> trabajos2 = getTrabajos();
        ObservableList<Integer> ids = FXCollections.observableArrayList();
        for (int i = 0; i < trabajos2.size(); i++) {
            ids.add(trabajos2.get(i).getId());
        }
        return ids;
    }

    @FXML void agregar_trabajo(Event event){
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Tipo_Trabajo.fxml"));
            Parent abrir = fxmlLoader.load();
            if (ventana_nuevo_trabajo.getScene() == null) {
                ventana_nuevo_trabajo.setTitle("Maquinados industriales - Trabajos");
              //  ventana_nuevo_trabajo.initStyle(StageStyle.UNDECORATED);
                ventana_nuevo_trabajo.setScene(new Scene(abrir));
                ventana_nuevo_trabajo.show();


                ventana_nuevo_trabajo.setOnCloseRequest(e -> {
                    ventana_nuevo_trabajo.setScene(null);
                });
            }
            else{
                ventana_nuevo_trabajo.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }


    }


}
