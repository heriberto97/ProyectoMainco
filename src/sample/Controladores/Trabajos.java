package sample.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

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
