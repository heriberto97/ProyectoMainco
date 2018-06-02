package sample.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class inventario {

    @FXML
    Button inv_oficina,inv_productos;

    private Stage articulos_oficina = new Stage();
    private Stage productos = new Stage();

    public void abriroficina(javafx.event.ActionEvent event)
    {

        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/inventario_oficina.fxml"));
            Parent abrir = fxmlLoader.load();

            // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
            if (articulos_oficina.getScene() == null) {
                articulos_oficina.setTitle("Articulos de oficina");
                articulos_oficina.setScene(new Scene(abrir));
                articulos_oficina.show();

                // El evento vaciará la ventana antes de ser cerrada, así se podrá abrir nuevamente
                articulos_oficina.setOnCloseRequest(e -> {
                    articulos_oficina.setScene(null);
                });
            }
            else {
                // Si la ventana tiene una escena, la trae al frente
                articulos_oficina.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void abrirproductos(javafx.event.ActionEvent event)
    {

        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/inventario_productos.fxml"));
            Parent abrir = fxmlLoader.load();

            // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
            if (productos.getScene() == null) {
                productos.setTitle("Inventario");
                productos.setScene(new Scene(abrir));
              //  productos.setMaximized(true);
                productos.show();

                // El evento vaciará la ventana antes de ser cerrada, así se podrá abrir nuevamente
                productos.setOnCloseRequest(e -> {
                    productos.setScene(null);
                });
            }
            else {
                // Si la ventana tiene una escena, la trae al frente
                productos.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }



}
