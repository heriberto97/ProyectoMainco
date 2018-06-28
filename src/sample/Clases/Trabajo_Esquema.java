package sample.Clases;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Trabajo_Esquema {

    @FXML
    ImageView iv_esquema;
    @FXML
    Button btn_seleccionaresquema;
    @FXML
    Button btn_descargare;
    @FXML
    Button btn_removere;
    @FXML
    Button btn_listoe;
    @FXML
    Button btn_cancelare;

    static Stage seleccionar_esquema = new Stage();

    @FXML void seleccionar_esquema(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Trabajo_SeleccionarEsquema.fxml"));
            Parent abrir = fxmlLoader.load();

            // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
            if (seleccionar_esquema.getScene() == null) {
                seleccionar_esquema.setTitle("Esquemas");
                seleccionar_esquema.setScene(new Scene(abrir));
                seleccionar_esquema.show();



                // El evento vaciará la ventana antes de ser cerrada, así se podrá abrir nuevamente
                seleccionar_esquema.setOnCloseRequest(e -> {
                    seleccionar_esquema.setScene(null);
                });
            } else {
                // Si la ventana tiene una escena, la trae al frente
                seleccionar_esquema.requestFocus();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
