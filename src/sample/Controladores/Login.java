package sample.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login implements Initializable {
    @FXML
    Button entrar;
    @FXML
    void iniciar(javafx.event.ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Trabajadores.fxml"));
            Parent abrir =fxmlLoader.load();
            Stage s = new Stage();
            s.setTitle("Trabajadores");
            s.setScene(new Scene(abrir));
            s.show();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
