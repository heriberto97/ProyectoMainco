package sample.Controladores.Trabajador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Conexion_bd.Conexion;
import sample.objetos.Traabajadores.Falta;
import sample.objetos.Trabajador;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Trabajador_faltas  implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Trabajador> list = FXCollections.observableList(getTrabajos());
        fecha_dehoy.setValue(LocalDate.now());
        fecha_dehoy.setEditable(false);


        txt_nombretrabajador.setEditable(false);

        lista_trabajadores.setItems(list);
        Callback<ListView<Trabajador>, ListCell<Trabajador>> factory = lv -> new ListCell<Trabajador>() {
            @Override
            protected void updateItem(Trabajador item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNombre());
            }

        };
        lista_trabajadores.setCellFactory(factory);
        lista_trabajadores.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                txt_nombretrabajador.setText(lista_trabajadores.getSelectionModel().getSelectedItem().getNombre());

            }
        });
    }
    Conexion conexion= new Conexion();

    public List<Trabajador> getTrabajos(){
        List<Trabajador> trabajadores= new ArrayList<>();
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

                    trabajadores.add(trabajador);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trabajadores;
    }

    @FXML TextField txt_nombretrabajador;
    @FXML Button btn_guardar;
    @FXML ListView<Trabajador> lista_trabajadores;
    @FXML DatePicker fecha_dehoy;
    @FXML RadioButton radio_falta,radio_retardo;
    public void guardar_Falta(ActionEvent event) {
        Trabajador t= lista_trabajadores.getSelectionModel().getSelectedItem() ;
        Falta f= new Falta(t.getId());

        if (radio_retardo.isSelected()){
            f.setTipo_falta("Retardo");
        }
        else if (radio_falta.isSelected()){
            f.setTipo_falta("Falta");
        }

        f.setFecha(fecha_dehoy.getValue().toString());

        conexion.Alta_falta(f);
        conexion.cerrarConexion();
    }
    public void cerrar_ventana(ActionEvent event) {
        Stage stage= (Stage) this.btn_guardar.getScene().getWindow();
        stage.close();
    }

    public void activado(ActionEvent event) {

        if (radio_retardo.isSelected()){
            radio_falta.setSelected(false);
        }

    }
    public void activado2(ActionEvent event){

        if (radio_falta.isSelected()){
            radio_retardo.setSelected(false);
        }
    }
}
