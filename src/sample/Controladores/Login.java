package sample.Controladores;

import javafx.application.Platform;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    @FXML
    Label cerrar;

    Conexion c = new Conexion();
    public Object datosusuario[] = new Object[7];
    int contador;
    static Stage ventana = new Stage();
    String usuario_sis;
    String tipo;


    //EVENTO DEL BOTON ENTRAR-----ESTE COMPARA USUARIO Y PASS, Y DEJA ENTRAR O NO.---------------------------------------
    public void iniciar( javafx.event.ActionEvent event) {
        String usuario = txt_usuario.getText().toString();
        String contrasena = txt_contrasena.getText().toString();

        try {

            if(txt_usuario.getText().isEmpty()||txt_contrasena.getText().isEmpty())
            {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Maquinados industriales");
                alerta.setHeaderText(null);
                alerta.setContentText("¡Completa los campos!");
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
                    usuario_sis = datosusuario[2].toString();
                    tipo = datosusuario[6].toString();

                    if(datosusuario[6].toString().equals("Administrador"))
                    {

                        System.out.println(datosusuario[6].toString());
                    }
                    else if(datosusuario[6].toString().equals("Jefe"))
                    {

                        System.out.println(datosusuario[6].toString());
                    }
                    Usuario u = new Usuario();
                    u.setNombre(usuario_sis);
                    u.setTipo_usuario(tipo);
                    VentanaPrincipal.setObj(u);

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
                c.cerrarConexion();
        }



            }

        catch(SQLException e)
        {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Revisa tu conexion");
            alerta.setHeaderText("¡Error de servidor!");
            alerta.setContentText("Algo esta fallando");
            alerta.showAndWait();

        }



            }


    public void clickcerrar(MouseEvent event)
    {
        Platform.exit();

    }

    //EVENTO ENTER DEL BOTON ENTRAR------HACE LO MISMO QUE EL EVENTO ENTER PERO CON LA TECLA ENTER
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
                                    usuario_sis = datosusuario[2].toString();
                                    tipo = datosusuario[6].toString();
                                    if(datosusuario[6].toString().equals("Administrador"))
                                    {

                                        System.out.println(datosusuario[6].toString());
                                    }
                                    else if(datosusuario[6].toString().equals("Reportes"))
                                    {

                                        System.out.println(datosusuario[6].toString());
                                    }
                                    Usuario u = new Usuario();
                                    u.setNombre(usuario_sis);
                                    u.setTipo_usuario(tipo);
                                    VentanaPrincipal.setObj(u);
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

                                c.cerrarConexion();
                            }


                        }

                        catch(SQLException e)
                        {

                            System.out.println(e);
                        }

                    }break;
                }


            }
    //ESTE METODO LO UNICO QUE HACE ES ABRIR LA VENTANA PRINCIPA
    public void entrar()
    {
        VentanaPrincipal.ventana= new Stage();
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/VentanaPrincipal.fxml"));
            Parent abrir = fxmlLoader.load();

            // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
            if (ventana.getScene() == null) {

                ventana.setTitle("Maquinados industriales Comarca");
                ventana.getIcons().add(new Image("sample/img/iconos/icono_principal.png"));
                ventana.setMaximized(true);
                ventana.setScene(new Scene(abrir));
                ventana.show();

                // El evento vaciará la ventana antes de ser cerrada, así se podrá abrir nuevamente
                ventana.setOnCloseRequest(e -> {

                   ventana.setScene(null);


                });
            }
            else {
                // Si la ventana tiene una escena, la trae al frente
              ventana.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
//

    }
    //METODO DE CUANDO ABRE LOGIN
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

}

