package sample.Controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import sample.Conexion_bd.Conexion;
import sample.objetos.Inventario_oficina;
import sample.objetos.producto;
import sample.objetos.productos_materiales;

import javax.swing.text.html.ImageView;
import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class producto_seleccionado implements Initializable {

    static producto obje = new producto();
    public static void setObj(producto obje) {
        producto_seleccionado.obje = obje;
    }
    @FXML private TableView<productos_materiales> tv_datosadicionales;
    @FXML private TableColumn<productos_materiales, String> columna_numero;
    @FXML private   TableColumn<productos_materiales, Integer> columna_tiempo;
    @FXML private   TableColumn<productos_materiales,Double > columna_peso;
    @FXML private   TableColumn<productos_materiales, String> columna_material;
    private ObservableList<productos_materiales> lista_productos;
    Conexion c = new Conexion();

    @FXML
    TextField txt_numero,txt_ruta;
@FXML
    TextArea txt_descripcion;
@FXML
    private javafx.scene.image.ImageView image_esquema;
String ruta;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ruta =obje.getRuta_imagen() ;
        txt_numero.setText(obje.getNumero_producto());
        txt_descripcion.setText(obje.getDescripcion());
        txt_ruta.setText(ruta);
        llenartabla();

     if (ruta==null)
        {
            File file = new File("C:\\Users\\gwend\\Pictures\\Imagenes\\random.jpg");
            Image image = new Image(file.toURI().toString());
            image_esquema.setImage(image);
        }
        else
     {
         File file = new File(ruta);
         Image image = new Image(file.toURI().toString());
         image_esquema.setImage(image);
     }

    }
    public void llenartabla()
    {
             lista_productos= FXCollections.observableArrayList();
        try {

            ResultSet datitos = c.mostrarSql(c.tabla_producto_seleccionado(txt_numero.getText()));

            while (datitos.next()) {
                for (int z=0; z<1;z++)
                {
                    lista_productos.add(new productos_materiales(
                            datitos.getString("numero"),
                            datitos.getString("material"),
                            datitos.getInt("tiempo"),
                            datitos.getDouble("peso")));
                }
            }

            tv_datosadicionales.setItems(lista_productos);



            columna_numero.setCellValueFactory(new PropertyValueFactory<>("producto"));
            columna_material.setCellValueFactory(new PropertyValueFactory<>("material"));
           columna_tiempo.setCellValueFactory(new PropertyValueFactory<>("tiempo_estimado"));
            columna_peso.setCellValueFactory(new PropertyValueFactory<>("peso"));

            c.cerrarConexion();
        }
        catch (Exception e)
        {
            System.out.println(e);
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Revisa tu conexion");
            alerta.setHeaderText("¡Error de servidor!");
            alerta.setContentText("Algo esta fallando");
            alerta.showAndWait();
        }

    }
}
