package sample.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class VentanaPrincipal {
    @FXML
    Button btn_abrir_inventario, btn_abrir_empleados, btn_abrir_trabajos, btn_abrir_compras;

    @FXML
    void iniciar_trabajos(javafx.event.ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Trabajos.fxml"));
            Parent abrir = fxmlLoader.load();
            Stage s = new Stage();
            s.setTitle("Maquinados industriales - Trabajos");
            s.setScene(new Scene(abrir));
            s.show();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    @FXML
    void iniciar_compras(javafx.event.ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Compras.fxml"));
            Parent abrir = fxmlLoader.load();
            Stage s = new Stage();
            s.setTitle("Maquinados industriales - Compras");
            s.setScene(new Scene(abrir));
            s.show();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    @FXML
    void iniciar_empleados(javafx.event.ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Trabajadores.fxml"));
            Parent abrir = fxmlLoader.load();
            Stage s = new Stage();
            s.setTitle("Maquinados industriales - Compras");
            s.setScene(new Scene(abrir));
            s.show();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    @FXML
    void iniciar_inventario(javafx.event.ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Inventario.fxml"));
            Parent abrir = fxmlLoader.load();
            Stage s = new Stage();
            s.setTitle("Maquinados industriales - Compras");
            s.setScene(new Scene(abrir));
            s.show();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
