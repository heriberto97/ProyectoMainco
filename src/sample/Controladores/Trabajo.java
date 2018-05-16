package sample.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Trabajo {
    @FXML
    Button btn_nuevocliente;
    @FXML
    Button btn_ordendecompra;
    @FXML
    Button btn_esquema;
    @FXML
    Button btn_trabajadores;
    @FXML
    RadioButton rbtn_fabricacion;
    @FXML
    RadioButton rbtn_servicio;
    @FXML
    RadioButton rbtn_modificacion;
    @FXML
    ListView lv_clientes;
    @FXML
    TextArea txta_descripcion;



    @FXML void RadioBtn(){

        if(rbtn_fabricacion.isSelected())
        {
            rbtn_modificacion.setSelected(false);
            rbtn_servicio.setSelected(false);
        }
        if(rbtn_modificacion.isSelected())
        {
            rbtn_fabricacion.setSelected(false);
            rbtn_servicio.setSelected(false);
        }
        if(rbtn_servicio.isSelected())
        {
            rbtn_modificacion.setSelected(false);
            rbtn_fabricacion.setSelected(false);
        }
    }

    private Stage ventana_nuevo_cliente = new Stage();

    @FXML void nuevo_cliente(){

        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Nuevo_Cliente.fxml"));
            Parent abrir = fxmlLoader.load();
            if (ventana_nuevo_cliente.getScene() == null) {
                ventana_nuevo_cliente.initStyle(StageStyle.UNDECORATED);
                ventana_nuevo_cliente.setTitle("Maquinados industriales - Trabajos");
                ventana_nuevo_cliente.setScene(new Scene(abrir));
                ventana_nuevo_cliente.show();
                ventana_nuevo_cliente.setOnCloseRequest(e -> {
                    ventana_nuevo_cliente.setScene(null);
                });
            }
            else{
                ventana_nuevo_cliente.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
