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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sample.Conexion_bd.Conexion;
import sample.objetos.Compras.Pago;
import sample.objetos.Inventario_oficina;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class inventario_oficina implements Initializable {


    @FXML private  TableView<Inventario_oficina> tv_articulos;
    @FXML private  TableColumn<Inventario_oficina, Integer> columna_numero_articulo;
    @FXML private   TableColumn<Inventario_oficina, String> columna_descripcion;
    @FXML private   TableColumn<Inventario_oficina, Integer> columna_cantidad;
    @FXML private  TableColumn<Inventario_oficina, String> columna_estado;
    @FXML
    ComboBox cb_filtrar;
    @FXML
    Button btn_nuevo_articulo;
    @FXML
    Button btn_actualizar_tabla;
    @FXML
    Button btn_buscar;
    @FXML
    Button btn_nuevo_trabajador;
    @FXML
    TextField txt_busqueda;
    @FXML ImageView image_esquema;
    static Stage expedicion = new Stage();
    String variable= System.getProperty("user.home");

    private  Conexion c = new Conexion();
    private  ObservableList<Inventario_oficina> lista_articulos;
    private ArrayList<Inventario_oficina> lista_cantidades;
    static Stage nuevo_articulo = new Stage();
   static Stage modificar_articulo = new Stage();

    public void abrir_expedicion()
    {


        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("expedicion.fxml"));
            Parent abrir = fxmlLoader.load();

            // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
            if (expedicion.getScene() == null) {

                expedicion.setTitle("Articulos");
                expedicion.setScene(new Scene(abrir));
                expedicion.show();

                // El evento vaciará la ventana antes de ser cerrada, así se podrá abrir nuevamente
                expedicion.setOnCloseRequest(e -> {
                    llenartabla();
                    expedicion.setScene(null);


                });
            }
            else {
                // Si la ventana tiene una escena, la trae al frente
                expedicion.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void abrir_archivo()
    {

        if(image_esquema.getImage()==null)
        {
            System.out.println("si es nulo");
        }

        else
        {
            if(tv_articulos.getSelectionModel().getSelectedItem().getRuta()==null)
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
                String ruta = tv_articulos.getSelectionModel().getSelectedItem().getRuta();
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
    //METODO PARA ABRIR FORKULARIO NUEVO ARTICULO
    public void abrir_form(javafx.event.ActionEvent event) {




        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Nuevo_articulo.fxml"));
            Parent abrir = fxmlLoader.load();

            // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
            if (nuevo_articulo.getScene() == null) {
               nuevo_articulo.setTitle("Nuevo articulo");
               nuevo_articulo.setScene(new Scene(abrir));
               nuevo_articulo.show();

                // El evento vaciará la ventana antes de ser cerrada, así se podrá abrir nuevamente
                nuevo_articulo.setOnCloseRequest(e -> {
                    llenartabla();
                    nuevo_articulo.setScene(null);
                });

            }
            else {
                // Si la ventana tiene una escena, la trae al frente
               nuevo_articulo.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println("si jalo bien aca");
        alerta();
    }
    //MWTODO PARA ABRIR UN NUEVO FORMULARIO CON LA INFORMACION DE UN ARTICULO PARA MODIFICAR
    public void click_articulo(MouseEvent event) {

        if(tv_articulos.getSelectionModel().isEmpty())
        {

        }
        else
        {
            if(event.getClickCount()>1)
            {

                int numero = tv_articulos.getSelectionModel().getSelectedItem().getId();
                String descripcion=  tv_articulos.getSelectionModel().getSelectedItem().getDescripcion();
                int cantidad = tv_articulos.getSelectionModel().getSelectedItem().getCantidad();
                String estado = tv_articulos.getSelectionModel().getSelectedItem().getEstado();
                String ruta = tv_articulos.getSelectionModel().getSelectedItem().getRuta();
                tv_articulos.getSelectionModel().clearSelection();

                Inventario_oficina articulo= new Inventario_oficina();
                articulo.setId(numero);
                articulo.setDescripcion(descripcion);
                articulo.setCantidad(cantidad);
                articulo.setEstado(estado);
                articulo.setRuta(ruta);
                Modificar_articulo.setObj(articulo);


                try
                {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modificar_articulo.fxml"));
                    Parent abrir = fxmlLoader.load();

                    // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
                    if (modificar_articulo.getScene() == null) {

                        modificar_articulo.setTitle("Modificar articulo");
                        modificar_articulo.setScene(new Scene(abrir));
                        modificar_articulo.show();

                        // El evento vaciará la ventana antes de ser cerrada, así se podrá abrir nuevamente
                        modificar_articulo.setOnCloseRequest(e -> {
                            image_esquema.setImage(null);
                            llenartabla();
                            modificar_articulo.setScene(null);


                        });
                    }
                    else {
                        // Si la ventana tiene una escena, la trae al frente
                        modificar_articulo.requestFocus();
                    }
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                tv_articulos.getSelectionModel().clearSelection();

            }

            else if(event.getClickCount()==1)
            {
                alerta();
                if(tv_articulos.getSelectionModel().getSelectedItem().getRuta()==null)
                {

                    //File file = new File("C:\\Users\\gwend\\IdeaProjects\\ProyectoMainco\\src\\sample\\Clases\\sin_asignar.jpg");
                    Image image = new Image("/sample/Clases/sin_asignar.jpg");
                    image_esquema.setImage(image);


                }
                else
                {
                    if(tv_articulos.getSelectionModel().getSelectedItem().getRuta().contains(".pdf"))
                    {
                        System.out.println("si es pdf");
                       // File file = new File("C:\\Users\\gwend\\IdeaProjects\\ProyectoMainco\\src\\sample\\Clases\\pdf.png");
                        Image image = new Image("/sample/Clases/pdf.png");
                        image_esquema.setImage(image);

                    }
                    else
                    {
                        String ruta = tv_articulos.getSelectionModel().getSelectedItem().getRuta();

                        File file = new File(ruta.replace("\\","\\"+"\\"));
                        javafx.scene.image.Image image = new Image(file.toURI().toString());
                        image_esquema.setImage(image);
                        System.out.println(ruta);
                    }



                }


            }






        }


    }
    //METODO PARA LLENAR EL COMBOBOX DE OPCIONES
    public void llenarcombo() {
        ObservableList<String> items1 = FXCollections.observableArrayList();
        items1.addAll("Numero de articulo", "Descripcion");
        cb_filtrar.setItems(items1);
    }
    //METODO AL INICIAR LA VENTANA
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        llenarcombo();
        llenartabla();
        alerta();

    }
    //metodo donde leo la consulta
    public  void llenartabla()
    {
        lista_articulos =  FXCollections.observableArrayList();

        try {

            ResultSet datitos = c.mostrarSql(c.verarticulosoficina());

            while (datitos.next()) {
                for (int z=0; z<1;z++)
                {
                    lista_articulos.add(new Inventario_oficina(
                            datitos.getInt("id"),
                            datitos.getString("descripcion"),
                            datitos.getInt("cantidad"),
                            datitos.getString("estado"),
                            datitos.getString("ruta")));
                }
            }

            tv_articulos.setItems(lista_articulos);


            columna_numero_articulo.setCellValueFactory(new PropertyValueFactory<>("id"));
            columna_descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            columna_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            columna_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
            try {

                MenuItem opcion1 = new MenuItem("Eliminar articulo");
                opcion1.setOnAction((ActionEvent event) -> {
                    int id_art  = tv_articulos.getSelectionModel().getSelectedItem().getId();
                    c.eliminar_articulo(id_art);

                    Image img = new Image("/sample/Clases/check.png");
                    Notifications noti = Notifications.create()
                            .title("articulo eliminado!")
                            .text("El articulo se eliminó con éxito")
                            .graphic(new ImageView(img))
                            .hideAfter(Duration.seconds(5))
                            .position(Pos.BOTTOM_LEFT)
                            .onAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    System.out.println("hizo clic en la notificacion");
                                }
                            });
                    noti.show();
                    llenartabla();
                });
                ContextMenu menu = new ContextMenu();
                menu.getItems().add(opcion1);
                tv_articulos.setContextMenu(menu);
                c.cerrarConexion();
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
        catch (Exception e)
        {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Revisa tu conexion");
            alerta.setHeaderText("¡Error de servidor!");
            alerta.setContentText("Algo esta fallando");
            alerta.showAndWait();
        }
    }
//BOTON ACTUALIZAR LA TABLA PRINCIPAL
    public void actualiza() {

        llenartabla();
        image_esquema.setImage(null);
        alerta();
    }
//METODO PARA LA ALERTITA CHIDA
    public void alerta(){
        lista_cantidades = new ArrayList<>();
        try
        {
            ResultSet cantidades = c.mostrarSql(c.datosalerta());
            while(cantidades.next())
            {
                int cantidad = cantidades.getInt("cantidad");
                Inventario_oficina art = new Inventario_oficina(cantidad);
                lista_cantidades.add(art);
            }
//

        }
        catch(Exception ex)
        {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Revisa tu conexion");
            alerta.setHeaderText("¡Error de servidor!");
            alerta.setContentText("Algo esta fallando");
            alerta.showAndWait();
        }

        int bandera =0;
        for (int i =0;i<lista_cantidades.size();i++)
        {
            System.out.println(lista_cantidades.get(i).getCantidad());
            if ( lista_cantidades.get(i).getCantidad()<=5)
            {
                bandera = 1;
            }


        }
        if (bandera==1)
        {
            Image img = new Image("/sample/Clases/alerta.png");
            Notifications noti = Notifications.create()
                    .title("Alerta!")
                    .text("Articulos bajos en inventario")
                    .graphic(new ImageView(img))
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("hizo clic en la notificacion");
                        }
                    });
            noti.show();
        }



    }
    public void buscar()
    {
        switch (cb_filtrar.getSelectionModel().getSelectedIndex())
      {
          case 0: { buscar_articulo();   }break;
          case 1: { buscar_descripcion();  }break;


      }


    }
    public void buscar_articulo() {
        System.out.println("Numero");
        lista_articulos =  FXCollections.observableArrayList();
        int busqueda = Integer.parseInt(txt_busqueda.getText());
        try {

            ResultSet articulo_pornumero = c.mostrarSql(c.buscar_numero_articulo(busqueda));

            while (articulo_pornumero.next()) {
                for (int z=0; z<1;z++)
                {
                    lista_articulos.add(new Inventario_oficina(
                            articulo_pornumero.getInt("id"),
                            articulo_pornumero.getString("descripcion"),
                            articulo_pornumero.getInt("cantidad"),
                            articulo_pornumero.getString("estado"),
                            articulo_pornumero.getString("ruta")));
                }
            }

            tv_articulos.setItems(lista_articulos);


            columna_numero_articulo.setCellValueFactory(new PropertyValueFactory<>("id"));
            columna_descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            columna_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            columna_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));

            c.cerrarConexion();
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
    public  void buscar_descripcion()
    {
        String busqueda = txt_busqueda.getText();
        lista_articulos =  FXCollections.observableArrayList();

        try {

            ResultSet articulo_pornumero = c.mostrarSql(c.buscar_descripcion_articulo(busqueda));

            while (articulo_pornumero.next()) {
                for (int z=0; z<1;z++)
                {
                    lista_articulos.add(new Inventario_oficina(
                            articulo_pornumero.getInt("id"),
                            articulo_pornumero.getString("descripcion"),
                            articulo_pornumero.getInt("cantidad"),
                            articulo_pornumero.getString("estado"),
                            articulo_pornumero.getString("ruta")));
                }
            }

            tv_articulos.setItems(lista_articulos);


            columna_numero_articulo.setCellValueFactory(new PropertyValueFactory<>("id"));
            columna_descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            columna_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            columna_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));

            c.cerrarConexion();
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

    public void enter(KeyEvent event) {
        switch (event.getCode())
        {

            case ENTER: {

                switch (cb_filtrar.getSelectionModel().getSelectedIndex())
                {
                    case 0: { buscar_articulo();  }break;
                    case 1: { buscar_descripcion(); }break;


                }



            }break;



            }









    }

    public void abrir_trabajador()
    {

        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Trabajadores_Alta.fxml"));
            Parent abrir = fxmlLoader.load();
            Stage ventana_TrabajadorAlta= new Stage();

            if (ventana_TrabajadorAlta.getScene() == null) {
                ventana_TrabajadorAlta.setTitle("Alta de Trabajadores");
                ventana_TrabajadorAlta.setScene(new Scene(abrir));
                ventana_TrabajadorAlta.show();

                ventana_TrabajadorAlta.setOnCloseRequest(e -> {
                    ventana_TrabajadorAlta.close();

                });

            }
            else {
                ventana_TrabajadorAlta.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }
}



