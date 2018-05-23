package sample.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class inventario_productos implements Initializable {

    @FXML
    Button btn_nuevo_producto;
    private Stage nuevo_producto = new Stage();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void click_nuevo_producto(javafx.event.ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/nuevo_producto.fxml"));
            Parent abrir = fxmlLoader.load();

            // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
            if (nuevo_producto.getScene() == null) {
                nuevo_producto.setTitle("Nuevo producto");
                nuevo_producto.setScene(new Scene(abrir));
                nuevo_producto.show();

                // El evento vaciará la ventana antes de ser cerrada, así se podrá abrir nuevamente
                nuevo_producto.setOnCloseRequest(e -> {
                    nuevo_producto.setScene(null);
                });
            }
            else {
                // Si la ventana tiene una escena, la trae al frente
                nuevo_producto.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
