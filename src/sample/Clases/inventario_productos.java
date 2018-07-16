package sample.Clases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sample.Conexion_bd.Conexion;
import sample.objetos.producto;

import java.awt.*;
import java.io.File;
import java.io.IOException;
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
    @FXML
    ComboBox cb_filtrar_productos;
    @FXML
    Button btn_actualizar_tabla;
    @FXML
    Button btn_esquema;
    private ObservableList<producto> lista_productos;
    private Conexion c = new Conexion();
    static Stage interactuar_producto = new Stage();
    static Stage nuevo_producto = new Stage();
    static Stage nuevo_esquema = new Stage();

    //MEOTODO PARA CUANDO INICIA LA VENTANA
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       llenarcombo();
       llenartabla();



    }

    //BOTON ACTUALIZAR LA TABLA
    public void actualizar()
    {
        llenartabla();
        imagen.setImage(null);
    }
    //ABRIR EL FORM PARA EL NUEVO ESQUEMA
    public void nuevo_esquema() {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("nuevo_esquema.fxml"));
            Parent abrir = fxmlLoader.load();

            // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
            if (nuevo_esquema.getScene() == null) {

                nuevo_esquema.setTitle("Nuevo producto");
                nuevo_esquema.setScene(new Scene(abrir));
                nuevo_esquema.show();

                // El evento vaciará la ventana antes de ser cerrada, así se podrá abrir nuevamente
                nuevo_esquema.setOnCloseRequest(e -> {

                    nuevo_esquema.setScene(null);


                });
            }
            else {
                // Si la ventana tiene una escena, la trae al frente
                nuevo_esquema.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    //METODO PARA LLENAR LA TABLA PRINCIPAL
    public void llenartabla() {



            lista_productos =  FXCollections.observableArrayList();
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

        if(tv_productos.getSelectionModel().isEmpty())
        {

        }
        else
        {
            if(ev.getClickCount()==2)
            {
                String numero = tv_productos.getSelectionModel().getSelectedItem().getNumero_producto();
                String descripcion = tv_productos.getSelectionModel().getSelectedItem().getDescripcion();
                String ruta = tv_productos.getSelectionModel().getSelectedItem().getRuta_imagen();
                String empresa = tv_productos.getSelectionModel().getSelectedItem().getEmpresa();
                producto p = new producto();
                p.setNumero_producto(numero);
                p.setDescripcion(descripcion);
                p.setRuta_imagen(ruta);
                p.setEmpresa(empresa);
                producto_seleccionado.setObj(p);


                try
                {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("interactuar_producto_seleccionado.fxml"));
                    Parent abrir = fxmlLoader.load();

                    // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
                    if (interactuar_producto.getScene() == null) {

                        interactuar_producto.setTitle("Producto seleccionado");
                        interactuar_producto.setScene(new Scene(abrir));
                        interactuar_producto.show();

                        // El evento vaciará la ventana antes de ser cerrada, así se podrá abrir nuevamente
                        interactuar_producto.setOnCloseRequest(e -> {
                            imagen.setImage(null);
                            llenartabla();
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

            if(tv_productos.getSelectionModel().getSelectedItem().getRuta_imagen()==null)
            {
                Image image = new Image("/sample/Clases/sin_asignar.jpg");
                imagen.setImage(image);
                //File file = new File("C:\\Users\\gwend\\IdeaProjects\\ProyectoMainco\\src\\sample\\Clases\\sin_asignar.jpg");

            }
            else
            {
                if(tv_productos.getSelectionModel().getSelectedItem().getRuta_imagen().contains(".pdf"))
                {
                    System.out.println("si es pdf");
                    Image image = new Image("/sample/Clases/pdf.png");
                    imagen.setImage(image);

                }

                else
                {
                    String ruta = tv_productos.getSelectionModel().getSelectedItem().getRuta_imagen();
                    File file = new File(ruta);
                    Image image = new Image(file.toURI().toString());
                    imagen.setImage(image);
                }

            }

        }



//





    }
    //ABRIR FORM PARA REGISTRAR
    public void nuevo_producto() {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("nuevo_producto.fxml"));
            Parent abrir = fxmlLoader.load();

            // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
            if (nuevo_producto.getScene() == null) {

                nuevo_producto.setTitle("Nuevo producto");
                nuevo_producto.setScene(new Scene(abrir));
                nuevo_producto.show();

                // El evento vaciará la ventana antes de ser cerrada, así se podrá abrir nuevamente
                nuevo_producto.setOnCloseRequest(e -> {
                        llenartabla();
                    nuevo_producto.setScene(null);


                });
            }
            else {
                // Si la ventana tiene una escena, la trae al frente

                nuevo_producto.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    //llena el combo de strings
    public void llenarcombo() {
        ObservableList<String> items1 = FXCollections.observableArrayList();
        items1.addAll("Numero de articulo", "Descripcion");
        cb_filtrar_productos.setItems(items1);
    }

    public void abrir_archivo()
    {
        if(imagen.getImage()==null)
        {
            System.out.println("si es nulo");
        }

        else
        {
            if(tv_productos.getSelectionModel().getSelectedItem().getRuta_imagen()==null)
            {
                Image img = new Image("/sample/Clases/alerta.png");
                Notifications noti = Notifications.create()
                        .title("Error en el archivo!")
                        .text("El archivo no existe")
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(4))
                        .position(Pos.BOTTOM_LEFT)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println("hizo clic en la notificacion");
                            }
                        });
                noti.show();
            }

            else
            {
                String ruta = tv_productos.getSelectionModel().getSelectedItem().getRuta_imagen();
                File pdfFile = new File(ruta.replace("\\","\\"+"\\"));
                if (pdfFile.exists()) {
                    if (Desktop.isDesktopSupported()) {
                        try {
                            Desktop.getDesktop().open(pdfFile);
                        } catch (IOException e) {

                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Awt Desktop no es soportado!");
                    }
                } else {
                    System.out.println("El archivo no existe!");
                    Image img = new Image("/sample/Clases/alerta.png");
                    Notifications noti = Notifications.create()
                            .title("Error en el archivo!")
                            .text("El archivo no existe o ha sido movido.")
                            .graphic(new ImageView(img))
                            .hideAfter(Duration.seconds(4))
                            .position(Pos.BOTTOM_LEFT)
                            .onAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    System.out.println("hizo clic en la notificacion");
                                }
                            });
                    noti.show();
                }
            }
        }

    }


}
