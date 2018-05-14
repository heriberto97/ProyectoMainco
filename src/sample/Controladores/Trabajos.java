package sample.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    @FXML void agregar_trabajo(){

        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Trabajo.fxml"));
            Parent abrir = fxmlLoader.load();
            Stage s = new Stage();
            s.setTitle("Maquinados industriales - Trabajos");
            //s.initStyle(StageStyle.UNDECORATED);
            s.setScene(new Scene(abrir));
            s.show();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }

}
