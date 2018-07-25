package sample.Clases;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Inventario_Estadisticas implements Initializable {
    @FXML
    Button btn_estadisticas_productos,btn_estadisticas_oficina;
    static Stage articulos = new Stage();
    static Stage producto = new Stage();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void abrir_productos()
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("estadisticas_producto.fxml"));
            Parent abrir = fxmlLoader.load();

            // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
            if (producto.getScene() == null) {

                producto.setTitle("Estadisticas");
                producto.setScene(new Scene(abrir));
                producto.show();

                // El evento vaciará la ventana antes de ser cerrada, así se podrá abrir nuevamente
                producto.setOnCloseRequest(e -> {

                  producto.setScene(null);


                });
            }
            else {
                // Si la ventana tiene una escena, la trae al frente

               producto.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public  void abrir_articulos()
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("estadisticas_articulos.fxml"));
            Parent abrir = fxmlLoader.load();

            // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
            if (articulos.getScene() == null) {

                articulos.setTitle("Estadisticas");
                articulos.setScene(new Scene(abrir));
                articulos.show();

                // El evento vaciará la ventana antes de ser cerrada, así se podrá abrir nuevamente
                articulos.setOnCloseRequest(e -> {

                    articulos.setScene(null);


                });
            }
            else {
                // Si la ventana tiene una escena, la trae al frente

                articulos.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }


}
