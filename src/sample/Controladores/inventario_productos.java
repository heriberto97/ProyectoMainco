package sample.Controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Conexion_bd.Conexion;
import sample.objetos.Inventario_oficina;
import sample.objetos.producto;

import javax.swing.text.TabableView;
import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class inventario_productos implements Initializable {

    @FXML private  TableView<producto> tv_productos;
    @FXML private TableColumn<producto, String>columna_numero_producto;
    @FXML private   TableColumn<producto, String> columna_descripcion_producto;
    @FXML private   TableColumn<producto, String> columna_empresa;
    @FXML
    private ImageView imagen;
    private ObservableList<producto> lista_productos;
    private Conexion c = new Conexion();
    static Stage interactuar_producto = new Stage();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       llenartabla();



    }
    //METODO PARA LLENAR LA TABLA PRINCIPAL
    public void llenartabla() { lista_productos =  FXCollections.observableArrayList();
        try {

            ResultSet datitos = c.mostrarSql(c.tablaproductos());

            while (datitos.next()) {
                for (int z=0; z<1;z++)
                {
                    lista_productos.add(new producto(
                            datitos.getString("numero"),
                            datitos.getString("descripcion"),
                            datitos.getString("ruta"),
                            datitos.getString("empresa")));
                }
            }

            tv_productos.setItems(lista_productos);



            columna_numero_producto.setCellValueFactory(new PropertyValueFactory<>("numero_producto"));
            columna_descripcion_producto.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            columna_empresa.setCellValueFactory(new PropertyValueFactory<>("empresa"));


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
    //CLICK EN EL ARTICULO
    public void click_producto(MouseEvent ev) {

        if(ev.getClickCount()==2)
        {

            try
            {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/interactuar_producto_seleccionado.fxml"));
                Parent abrir = fxmlLoader.load();

                // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
                if (interactuar_producto.getScene() == null) {

                    interactuar_producto.setTitle("Producto seleccionado");
                    interactuar_producto.setScene(new Scene(abrir));
                    interactuar_producto.show();

                    // El evento vaciará la ventana antes de ser cerrada, así se podrá abrir nuevamente
                    interactuar_producto.setOnCloseRequest(e -> {

                        interactuar_producto.setScene(null);


                    });
                }
                else {
                    // Si la ventana tiene una escena, la trae al frente
                    interactuar_producto.requestFocus();
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }

        String ruta = tv_productos.getSelectionModel().getSelectedItem().getRuta_imagen();
        File file = new File(ruta);
        Image image = new Image(file.toURI().toString());
        imagen.setImage(image);

//





    }


}
