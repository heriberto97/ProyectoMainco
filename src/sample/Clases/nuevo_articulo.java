package sample.Clases;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Conexion_bd.Conexion;
import sample.objetos.Inventario_oficina;

import java.io.File;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class nuevo_articulo implements Initializable {

    @FXML
    javafx.scene.control.TextArea txt_descripcion;

    @FXML
    javafx.scene.control.TextField  txt_cantidad,txt_ruta;

    @FXML
    javafx.scene.control.Button btn_guardar;
    @FXML
    javafx.scene.control.Button btn_cancelar;
    @FXML
    ImageView image_esquema;
    Conexion c = new Conexion();
    String rutaaa=null;

   String variable= System.getProperty("user.home");


    public void guardar(javafx.event.ActionEvent event) {
        try {
            if(txt_descripcion.getText().isEmpty()||txt_cantidad.getText().isEmpty())
            {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Maquinados industriales");
                alerta.setHeaderText(null);
                alerta.setContentText("¡Completa los campos!");
                alerta.showAndWait();
            }
            else
            {
                if(Integer.parseInt(txt_cantidad.getText())<1)
                {
                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setTitle("Alerta");
                    alerta.setHeaderText(null);
                    alerta.setContentText("¡No puedes registrar articulos con cantidad 0!");
                    alerta.showAndWait();
                }
                else
                {
                    if(txt_ruta.getText().isEmpty())
                    {
                       Inventario_oficina inv = new Inventario_oficina();
                       inv.setCantidad(Integer.parseInt(txt_cantidad.getText()));
                       inv.setDescripcion(txt_descripcion.getText());
                       inv.setEstado("En Existencias");
                       c.AltaArticulos2(inv);
                        c.cerrarConexion();
                        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                        alerta.setTitle("Maquinados industriales");
                        alerta.setHeaderText("Exito");
                        alerta.setContentText("¡Articulo creado correctamente!");
                        alerta.showAndWait();
                        txt_descripcion.setText("");
                        txt_cantidad.setText("");
                        inventario_oficina.nuevo_articulo= new Stage();
                        ((Node)(event.getSource())).getScene().getWindow().hide();
                        Stage stage= (Stage) this.btn_guardar.getScene().getWindow();
                        stage.getOnCloseRequest().handle( new WindowEvent(
                                stage,
                                WindowEvent.WINDOW_CLOSE_REQUEST));
                        stage.close();
                    }
                    else
                    {

                        Inventario_oficina articulo = new Inventario_oficina(Integer.parseInt(txt_cantidad.getText()),txt_descripcion.getText(),"En Existencias",txt_ruta.getText());
                        c.AltaArticulos(articulo);
                        c.cerrarConexion();
                        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                        alerta.setTitle("Maquinados industriales");
                        alerta.setHeaderText("Exito");
                        alerta.setContentText("¡Articulo creado correctamente!");
                        alerta.showAndWait();
                        txt_descripcion.setText("");
                        txt_cantidad.setText("");
                        inventario_oficina.nuevo_articulo= new Stage();
                        ((Node)(event.getSource())).getScene().getWindow().hide();
                        Stage stage= (Stage) this.btn_guardar.getScene().getWindow();
                        stage.getOnCloseRequest().handle( new WindowEvent(
                                stage,
                                WindowEvent.WINDOW_CLOSE_REQUEST));
                        stage.close();
                    }




                }


            }

        }
        catch (Exception ex)
        {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Revisa tu conexion");
            alerta.setHeaderText("¡Error de servidor!");
            alerta.setContentText("Algo esta fallando");
            alerta.showAndWait();
        }

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(variable);

                //SOLO NUMEROS PARA ESTE TEXTFIELD
        txt_cantidad.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                   txt_cantidad.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }

    public void cancelar(javafx.event.ActionEvent event)
    {
        inventario_oficina.nuevo_articulo= new Stage();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    public void subir()
    {
        FileChooser fc  = new FileChooser();

        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files","*.pdf")
                , new FileChooser.ExtensionFilter("Jpg Images","*.jpg","*.JPEG","*.JPG","*.jpeg","*.PNG","*.png"));

        File fileSelected = fc.showOpenDialog(null);

        if (fileSelected!= null){

            txt_ruta.setText(fileSelected.getName());
            System.out.println(fileSelected.getName());
            if(txt_ruta.getText().contains(".pdf"))
            {
                System.out.println("si es pdf");
               // File file = new File("C:\\Users\\gwend\\IdeaProjects\\ProyectoMainco\\src\\sample\\Clases\\pdf.png");
                Image image = new Image("/sample/Clases/pdf.png");
                image_esquema.setImage(image);
            }
            else
            {

                String a = variable+"\\Desktop\\carpeta compartida\\imagenes de inventario\\"+txt_ruta.getText();
                File file = new File(a.replace("\\","\\"+"\\"));
                javafx.scene.image.Image image = new Image(file.toURI().toString());
                image_esquema.setImage(image);
            }

        }
        else{
            System.out.println("no se seleccinoó");
        }
    }
}
