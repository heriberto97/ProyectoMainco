package sample.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.objetos.Inventario_oficina;
import sample.objetos.Usuario;

import java.net.URL;
import java.util.ResourceBundle;

public class VentanaPrincipal implements Initializable {
    @FXML
    Button btn_abrir_inventario, btn_abrir_empleados, btn_abrir_trabajos, btn_abrir_compras,btn_cerrar_sesion;


    // - - - - - - - - - - - - - - - - - - - - - - - - - Abrir Ventanas
    private Stage ventana_trabajos = new Stage();
    private Stage ventana_compras = new Stage();
    private Stage ventana_empleados = new Stage();
    private Stage ventana_inventario = new Stage();
    static Stage ventana = new Stage();

    static Usuario usuario = new Usuario();
    @FXML
    Label lbl_usuario;

    public static void setObj(Usuario obj) {
        VentanaPrincipal.usuario = obj;
    }



    @FXML
    void iniciar_trabajos(javafx.event.ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Trabajos.fxml"));
            Parent abrir = fxmlLoader.load();

            // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
            if (ventana_trabajos.getScene() == null) {
                ventana_trabajos.setTitle("Trabajos");
                ventana_trabajos.setScene(new Scene(abrir));
                ventana_trabajos.show();

                // El evento vaciará la ventana antes de ser cerrada, así se podrá abrir nuevamente
                ventana_trabajos.setOnCloseRequest(e -> {
                    ventana_trabajos.setScene(null);
                });
            }
            else {
                // Si la ventana tiene una escena, la trae al frente
                ventana_trabajos.requestFocus();
            }
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Compras/Compras.fxml"));
            Parent abrir = fxmlLoader.load();

            if (ventana_compras.getScene() == null) {
                ventana_compras.setTitle("Compras");
                ventana_compras.setScene(new Scene(abrir));
                ventana_compras.getIcons().add(new Image("sample/img/iconos/icono_compras.png"));
                ventana_compras.show();
                ventana_compras.setOnCloseRequest(e -> {
                    ventana_compras.setScene(null);
                });
            }
            else {
                ventana_compras.requestFocus();
            }
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

            if (ventana_empleados.getScene() == null) {
                ventana_empleados.setTitle("Empleados");
                ventana_empleados.setScene(new Scene(abrir));
                ventana_empleados.show();
                ventana_empleados.setOnCloseRequest(e -> {
                    ventana_empleados.setScene(null);
                });
            }
            else {
                ventana_empleados.requestFocus();
            }
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/inventario.fxml"));
            Parent abrir = fxmlLoader.load();

            if (ventana_inventario.getScene() == null) {
                ventana_inventario.setTitle("Inventario");
                ventana_inventario.setScene(new Scene(abrir));
                ventana_inventario.show();
                ventana_inventario.setOnCloseRequest(e -> {
                    ventana_inventario.setScene(null);
                });
            }
            else {
                ventana_inventario.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void cerrar_sesion(javafx.event.ActionEvent event)
    {
        Login.ventana=new Stage();
        ((Node)(event.getSource())).getScene().getWindow().hide();
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Login.fxml"));
            Parent abrir = fxmlLoader.load();

            // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
            if (ventana.getScene() == null) {

                ventana.setTitle("Maquinados industriales Comarca");
                ventana.initStyle(StageStyle.UNDECORATED);
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

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbl_usuario.setText(usuario.getNombre());
    }
}
