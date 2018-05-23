package sample.Controladores.Trabajador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import sample.objetos.Trabajador;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import sample.Conexion_bd.*;

public class Trabajador_faltas  implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableList(getTrabajos());
        fecha_dehoy.setValue(LocalDate.now());
        fecha_dehoy.setEditable(false);
        check_falta.setSelected(false);
        check_falta.setDisable(true);
        btn_guardar.setDisable(true);

        txt_nombretrabajador.setEditable(false);

        lista_trabajadores.setItems(list);
        lista_trabajadores.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                txt_nombretrabajador.setText(lista_trabajadores.getSelectionModel().getSelectedItem());
                if (check_falta.isDisable()&&btn_guardar.isDisable()){
                    check_falta.setDisable(false);
                    btn_guardar.setDisable(false);
                }
            }
        });
    }
    Conexion conexion= new Conexion();

    public List<String> getTrabajos(){
        List<String> trabajadores= new ArrayList<>();
        try{
            ResultSet trabajadorresResult= conexion.mostrarSql(conexion.verTrabajadores());
            while (trabajadorresResult.next()) {

                for (int i = 0; i < 1; i++) {

                    Trabajador trabajador= new Trabajador(Integer.parseInt(trabajadorresResult.getObject(1).toString()), trabajadorresResult.getObject(2).toString(), trabajadorresResult.getObject(3).toString(), trabajadorresResult.getObject(4).toString(), trabajadorresResult.getObject(5).toString());
                    if (trabajadorresResult.getObject(6)!=null){
                        trabajador.setSolicitud_empleo(trabajadorresResult.getObject(6).toString());
                    }
                    else{
                        trabajador.setSolicitud_empleo("No tiene");
                    }

                    trabajadores.add(trabajador.nombreCompleto());

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trabajadores;
    }

    @FXML TextField txt_nombretrabajador;
    @FXML Button btn_guardar;
    @FXML ListView<String> lista_trabajadores;
    @FXML CheckBox check_falta;
    @FXML DatePicker fecha_dehoy;

    public void guardar_Falta(ActionEvent event) {

    }

    public void obtener_trabajador(MouseEvent mouseEvent) {

    }
}
