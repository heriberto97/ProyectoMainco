package sample.Clases;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Tipo_Trabajo {
    @FXML
    Button btn_servicio;
    @FXML
    Button btn_modificacion;
    @FXML
    Button btn_fabricacion;

    static Stage ventana_trabajo = new Stage();

    @FXML
    void iniciar_trabajo(javafx.event.ActionEvent event)
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Trabajo.fxml"));
            Parent abrir = fxmlLoader.load();

            // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
            if (ventana_trabajo.getScene() == null) {
                ventana_trabajo.setTitle("Trabajo");
                ventana_trabajo.setScene(new Scene(abrir));
                ventana_trabajo.show();



                // El evento vaciará la ventana antes de ser cerrada, así se podrá abrir nuevamente
                ventana_trabajo.setOnCloseRequest(e -> {
                    ventana_trabajo.setScene(null);
                });
            } else {
                // Si la ventana tiene una escena, la trae al frente
                ventana_trabajo.requestFocus();
            }
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
