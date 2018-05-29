package sample.Controladores;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class Trabajo_Trabajadores {


    @FXML
    ListView lv_empleadosasignados;
    @FXML
    ListView lv_empleados;
    @FXML
    Button btn_asignartrabajo;
    @FXML
    Button btn_trabajadoreslisto;

    @FXML
    public void Listo_Trabajadores(Event event){

        Tipo_Trabajo.ventana_trabajo = new Stage();
        ((Node)(event.getSource())).getScene().getWindow().hide();

    }
}
