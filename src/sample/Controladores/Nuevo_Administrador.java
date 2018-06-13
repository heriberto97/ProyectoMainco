package sample.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Conexion_bd.Conexion;
import sample.objetos.Usuario;

public class Nuevo_Administrador {
    @FXML
    TextField txt_nombre,txt_ap,txt_am,txt_usuario;
    Conexion c = new Conexion();
    @FXML
    PasswordField txt_contrasena;


    public void guardar()
    {
        try {
            if(txt_nombre.getText().isEmpty()||txt_ap.getText().isEmpty() ||
               txt_am.getText().isEmpty()||
               txt_usuario.getText().isEmpty()||
               txt_contrasena.getText().isEmpty())
            {

                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("!Cuidado!");
                alerta.setHeaderText(null);
                alerta.setContentText("! Completa los campos !");
                alerta.showAndWait();
            }
            else
            {
                Usuario u = new Usuario(txt_usuario.getText(),txt_nombre.getText(),txt_ap.getText(),txt_am.getText(),txt_contrasena.getText(),"Administrador");
                c.AltaUsuarios(u);
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Maquinados industriales");
                alerta.setHeaderText("Exito");
                alerta.setContentText("¡Usuario creado correctamente!");
                alerta.showAndWait();
                txt_nombre.setText("");
                txt_contrasena.setText("");
                txt_ap.setText("");
                txt_am.setText("");
                txt_usuario.setText("");
                c.cerrarConexion();
            }


        }
        catch (Exception ex)
        {

        }


    }



}
