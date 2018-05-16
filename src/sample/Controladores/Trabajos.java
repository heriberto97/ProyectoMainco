package sample.Controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.Conexion_bd.Conexion;
import sample.Controladores.Trabajador.Trabajadores_Alta;
import sample.objetos.Trabajador;
import javafx.scene.control.TableView;
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



    private Stage ventana_nuevo_trabajo = new Stage();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        opciones();

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

    @FXML void agregar_trabajo(){
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Trabajo.fxml"));
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
