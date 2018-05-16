package sample.Controladores;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Conexion_bd.Conexion;
import sample.objetos.Usuario;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Login implements Initializable  {
    @FXML
    Button entrar;
    @FXML
    TextField txt_usuario;
    @FXML
    TextField txt_contrasena;

    Conexion c = new Conexion();
    public Object datosusuario[] = new Object[7];
    int contador;




    public void iniciar( javafx.event.ActionEvent event) {
        String usuario = txt_usuario.getText().toString();
        String contrasena = txt_contrasena.getText().toString();
        try {

            if(txt_usuario.getText().isEmpty()||txt_contrasena.getText().isEmpty())
            {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Maquinados industriales");
                alerta.setHeaderText(null);
                alerta.setContentText("Hola, bienvenido "+datosusuario[2].toString());
                alerta.showAndWait();
            }
            else
            {
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
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Maquinados industriales");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Hola, bienvenido "+datosusuario[2].toString());
                    alerta.showAndWait();
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                    entrar();
                }

                else {
                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setTitle("Revisa tus datos");
                    alerta.setHeaderText("¡Error de indetificación!");
                    alerta.setContentText("Usuario o contraseña incorrectos");
                    alerta.showAndWait();
                    txt_usuario.setText("");
                    txt_contrasena.setText("");
                }
                res.close();
            }


            }

        catch(SQLException e)
        {

            System.out.println(e);
        }



            }

            public void enter(KeyEvent event)

            {
                switch (event.getCode())
                {

                    case ENTER: {

                        String usuario = txt_usuario.getText().toString();
                        String contrasena = txt_contrasena.getText().toString();
                        try {

                            if(txt_usuario.getText().isEmpty()||txt_contrasena.getText().isEmpty())
                            {
                                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                                alerta.setTitle("!Cuidado!");
                                alerta.setHeaderText(null);
                                alerta.setContentText("! Completa los campos !");
                                alerta.showAndWait();
                            }
                            else
                            {
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
                                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                                    alerta.setTitle("Maquinados industriales");
                                    alerta.setHeaderText(null);
                                    alerta.setContentText("Hola, bienvenido "+datosusuario[2].toString());
                                    alerta.showAndWait();
                                    ((Node)(event.getSource())).getScene().getWindow().hide();
                                    entrar();
                                }

                                else {
                                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                                    alerta.setTitle("Revisa tus datos");
                                    alerta.setHeaderText("¡Error de indetificación!");
                                    alerta.setContentText("Usuario o contraseña incorrectos");
                                    alerta.showAndWait();
                                    txt_usuario.setText("");
                                    txt_contrasena.setText("");
                                }
                                res.close();
                            }


                        }

                        catch(SQLException e)
                        {

                            System.out.println(e);
                        }

                    }break;
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

