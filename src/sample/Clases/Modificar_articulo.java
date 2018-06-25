package sample.Clases;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Conexion_bd.Conexion;
import sample.objetos.Inventario_oficina;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Modificar_articulo implements Initializable {
  @FXML
    TextField txt_numero_articulo;
    @FXML
    TextArea txt_descripcion;
    @FXML
    TextField txt_cantidad,txt_ruta;
    @FXML
    ImageView image_esquema;
    @FXML
    Button btn_guardar,btn_seleccionar,btn_subir;

    Conexion c = new Conexion();

   static Inventario_oficina obj = new Inventario_oficina();
    static Stage inventario_oficinaa= new Stage();
   public static void setObj(Inventario_oficina obj) {
        Modificar_articulo.obj = obj;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

       txt_numero_articulo.setText(Integer.toString(obj.getId()));
       txt_descripcion.setText(obj.getDescripcion());
       txt_cantidad.setText(Integer.toString(obj.getCantidad()));
        if (obj.getRuta()==null)
        {
            File file = new File("C:\\Users\\gwend\\IdeaProjects\\ProyectoMainco\\src\\sample\\img\\sin_asignar.jpg");
            Image image = new Image(file.toURI().toString());
            image_esquema.setImage(image);
            txt_ruta.setText("C:\\Users\\gwend\\IdeaProjects\\ProyectoMainco\\src\\sample\\img\\sin_asignar.jpg");
        }
        else
        {
            if(obj.getRuta().contains(".pdf"))
            {
                System.out.println("si es pdf");
                File file = new File("C:\\Users\\gwend\\IdeaProjects\\ProyectoMainco\\src\\sample\\Clases\\pdf.png");
                Image image = new Image(file.toURI().toString());
                image_esquema.setImage(image);
            }
            else
            {
                File file = new File(obj.getRuta());
                javafx.scene.image.Image image = new Image(file.toURI().toString());
                image_esquema.setImage(image);
                txt_ruta.setText(obj.getRuta());
            }

        }





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

    public void modificar(javafx.event.ActionEvent event)
    {

        try{

            if (txt_cantidad.getText().isEmpty()||txt_descripcion.getText().isEmpty())
            {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Maquinados industriales");
                alerta.setHeaderText(null);
                alerta.setContentText("¡Completa los campos!");
                alerta.showAndWait();
            }
            else
            {




                Inventario_oficina articuloseleccionado = new Inventario_oficina(obj.getId(),txt_descripcion.getText(),Integer.parseInt(txt_cantidad.getText()),txt_ruta.getText().replace( "\\","\\"+"\\"));

                c.modificarArticulo(articuloseleccionado);
                c.cerrarConexion();
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Maquinados industriales");
                alerta.setHeaderText("Exito");
                alerta.setContentText("¡Articulo modificado correctamente!");
                alerta.showAndWait();
                if(Integer.parseInt(txt_cantidad.getText())==0)
                {
                    c.modificarestadoArticulo();
                    c.cerrarConexion();

                }else if (Integer.parseInt(txt_cantidad.getText())>0)
                {
                    c.modificarestadoArticulo2();
                    c.cerrarConexion();

                }

                inventario_oficina.modificar_articulo= new Stage();
                ((Node)(event.getSource())).getScene().getWindow().hide();

                Stage stage= (Stage) this.btn_guardar.getScene().getWindow();
                stage.getOnCloseRequest().handle( new WindowEvent(
                        stage,
                        WindowEvent.WINDOW_CLOSE_REQUEST));
                stage.close();

            }

        }
        catch (Exception e)
        {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Revisa tu conexion");
            alerta.setHeaderText("¡Error de servidor!");
            alerta.setContentText("Algo esta fallando");
            alerta.showAndWait();
        }



    }

    public void cancelar(javafx.event.ActionEvent event)
    {
        inventario_oficina.modificar_articulo= new Stage();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    public void guardar_imagen()
    {
        FileChooser fc  = new FileChooser();

        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files","*.pdf")
                , new FileChooser.ExtensionFilter("Jpg Images","*.jpg","*.JPEG","*.JPG","*.jpeg","*.PNG","*.png"));

        File fileSelected = fc.showOpenDialog(null);

        if (fileSelected!= null){
            txt_ruta.setText(fileSelected.getPath());
            File file = new File(txt_ruta.getText());
            javafx.scene.image.Image image = new Image(file.toURI().toString());
            image_esquema.setImage(image);
        }
        else{
            System.out.println("no se seleccinoó");
        }
    }




}
