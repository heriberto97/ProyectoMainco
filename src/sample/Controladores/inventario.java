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

    public void abriroficina(javafx.event.ActionEvent event)
    {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/inventario_oficina.fxml"));
            Parent abrir = fxmlLoader.load();
            Stage s = new Stage();
            s.setMaximized(false);
            s.setTitle("Iventario oficina");
            s.setScene(new Scene(abrir));
            s.show();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void abrirproductos(javafx.event.ActionEvent event)
    {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/inventario_productos.fxml"));
            Parent abrir = fxmlLoader.load();
            Stage s = new Stage();
            s.setMaximized(false);
            s.setTitle("Iventario productos");
            s.setScene(new Scene(abrir));
            s.show();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }



}
