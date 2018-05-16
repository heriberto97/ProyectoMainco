package sample.Controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sample.Conexion_bd.Conexion;
import sample.objetos.*;
import sample.objetos.Trabajo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Trabajos {

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
    Conexion c = new Conexion();

    @FXML
    void agregar_trabajo() {
        try {
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
            } else {
                ventana_nuevo_trabajo.requestFocus();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public ObservableList<sample.objetos.Trabajo> getTrabajos() {
        ObservableList<Trabajo> trabajos = FXCollections.observableArrayList();
        try {
            ResultSet trabajosResult = c.mostrarSql(c.verTrabajos());
            while (trabajosResult.next()) {

                for (int i = 0; i < 10; i++) {
                    trabajos.add(new sample.objetos.Trabajo(
                            Integer.parseInt(trabajosResult.getObject(0).toString()),
                            Integer.parseInt(trabajosResult.getObject(1).toString()),
                            Integer.parseInt(trabajosResult.getObject(2).toString()),
                            Integer.parseInt(trabajosResult.getObject(4).toString()),
                            Integer.parseInt(trabajosResult.getObject(5).toString()),
                            Integer.parseInt(trabajosResult.getObject(9).toString()),
                            Integer.parseInt(trabajosResult.getObject(10).toString()),
                            Integer.parseInt(trabajosResult.getObject(11).toString()),
                            trabajosResult.getObject(3).toString(),
                            trabajosResult.getObject(6).toString(),
                            trabajosResult.getObject(7).toString(),
                            trabajosResult.getObject(8).toString()));
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trabajos;
    }
}
