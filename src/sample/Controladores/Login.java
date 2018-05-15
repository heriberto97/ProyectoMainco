package sample.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Conexion_bd.Conexion;
import sample.objetos.Usuario;

import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @FXML
    Button entrar;
    //@FXML

    public void iniciar(javafx.event.ActionEvent event)
    {
        //Conexion c = new Conexion();
        //Usuario u = new Usuario("Ramirin","ramiro174","garcia","Galvan","1234","Usuario");
        //c.AltaUsuarios(u);
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/VentanaPrincipal.fxml"));
            Parent abrir =fxmlLoader.load();
            Stage s = new Stage();
            s.setMaximized(true);
            s.setTitle("Maquinados industriales Comarca");
            s.setScene(new Scene(abrir));
            s.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();

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
