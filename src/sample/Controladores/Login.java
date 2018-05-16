package sample.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Conexion_bd.Conexion;
import sample.objetos.Usuario;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @FXML
    Button entrar;
    @FXML
    TextField txt_usuario;
    @FXML
    TextField txt_contrasena;

    Conexion c = new Conexion();
    public Object datosusuario[] = new Object[7];
    int contador;
    public void iniciar(javafx.event.ActionEvent event) {
        String usuario = txt_usuario.getText().toString();
        String contrasena = txt_contrasena.getText().toString();
        try {
            ResultSet res = c.mostrarSql(c.datosusuario(usuario,contrasena));
        contador=0;
            while (res.next()) {
                for (int i = 0; i <= 6; i++) {
                    datosusuario[i] = res.getObject(i + 1);
                }
                contador++;
            }
                if(contador>0)
                {
                    entrar();
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                }

                else {
                    System.out.println("Ingresa correctamente tus datos");
                }

           // String nombre=datosusuario[2].toString();
            //System.out.println(nombre);

            res.close();



        }

        catch(SQLException e)
        {

            System.out.println(e);
        }

}







    public void entrar()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/VentanaPrincipal.fxml"));
            Parent abrir = fxmlLoader.load();
            Stage s = new Stage();
            s.setMaximized(true);
            s.setTitle("Maquinados industriales Comarca");
            s.setScene(new Scene(abrir));
            s.show();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public String dato(){

        return String.valueOf(datosusuario[1]);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}

