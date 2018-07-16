package sample.Clases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sample.Conexion_bd.Conexion;
import sample.objetos.Esquema;
import sample.objetos.producto;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;


public class Esquema_seleccionar implements Initializable {
    static producto obje = new producto();
    public static void setObj(producto obje) {
        Esquema_seleccionar.obje = obje;
    }
    @FXML
    Button btn_aceptar,btn_cancelar,btn_buscar;
    @FXML
    ComboBox cb_filtrar;

    Conexion c = new Conexion();
    @FXML
    private TableView<Esquema> tv_esquemas;
    @FXML private TableColumn<Esquema, String> columna_descripcion;
    @FXML private TableColumn<Esquema, String>columna_numero;
    private ObservableList<Esquema> lista_esquemas;
    @FXML
    private javafx.scene.image.ImageView image_esquema;
    @FXML
    TextField txt_buscar;
    public void llenarcombo() {
        ObservableList<String> items1 = FXCollections.observableArrayList();
        items1.addAll("Numero de esquema", "Descripcion");
        cb_filtrar.setItems(items1);
    }
    public void buscar()
    {
        switch (cb_filtrar.getSelectionModel().getSelectedIndex())
        {
            case 0: { buscar_num_esquema();
                System.out.println("numero");   }break;
            case 1: { buscar_descripcion_esquema();
                System.out.println("DESCRIPCION");  }break;

        }
    }

    public void actualizar()
    {
        image_esquema.setImage(null);
        llenartabladeesquemas();
    }

    public void enter(KeyEvent event) {
        switch (event.getCode())
        {

            case ENTER: {

                switch (cb_filtrar.getSelectionModel().getSelectedIndex())
                {
                    case 0: { buscar_num_esquema();  }break;
                    case 1: { buscar_descripcion_esquema(); }break;


                }



            }break;



        }









    }

    public void buscar_num_esquema()
    {
        lista_esquemas =  FXCollections.observableArrayList();
        String numero = txt_buscar.getText();
        try {
            ResultSet datitos = c.mostrarSql(c.buscar_numero_esquema(numero));
            while (datitos.next()) {
                for (int z=0; z<1;z++)
                {
                    lista_esquemas.add(new Esquema(
                            datitos.getInt("id"),
                            datitos.getString("ruta"),
                            datitos.getString("descripcion"),
                            datitos.getString("numero")
                    ));

                }
            }
            tv_esquemas.setItems(lista_esquemas);
            columna_descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            columna_numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
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

    public void buscar_descripcion_esquema()
    {
        lista_esquemas =  FXCollections.observableArrayList();
        String descripcion = txt_buscar.getText();
        try {
            ResultSet datitos = c.mostrarSql(c.buscar_descripcion_esquema(descripcion));
            while (datitos.next()) {
                for (int z=0; z<1;z++)
                {
                    lista_esquemas.add(new Esquema(
                            datitos.getInt("id"),
                            datitos.getString("ruta"),
                            datitos.getString("descripcion"),
                            datitos.getString("numero")
                    ));

                }
            }
            tv_esquemas.setItems(lista_esquemas);
            columna_descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            columna_numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
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

    public void click_esquema() {

        if(tv_esquemas.getSelectionModel().isEmpty())
        {

        }
        else
        {
            String ruta = tv_esquemas.getSelectionModel().getSelectedItem().getRuta();
            if(ruta.contains(".pdf"))
            {
                System.out.println("si es pdf");
                Image image = new Image("/sample/Clases/pdf.png");
                image_esquema.setImage(image);
            }

            else
            {
                File file = new File(ruta);
                Image image = new Image(file.toURI().toString());
                image_esquema.setImage(image);
            }
            Notifications noti = Notifications.create()
                    .title("Notificación!")
                    .text("¡Seleccionaste un archivo! Presiona aqui si deseas abrirlo")
                    .hideAfter(Duration.seconds(8))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("hizo clic en la notificacion");
                            if(tv_esquemas.getSelectionModel().getSelectedItem().getRuta()==null)
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
                                String ruta = tv_esquemas.getSelectionModel().getSelectedItem().getRuta();
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
                    });
            noti.show();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        llenarcombo();
        llenartabladeesquemas();
        String n = obje.getNumero_producto();
        System.out.println(n);
    }

    public void llenartabladeesquemas()
    {
        lista_esquemas =  FXCollections.observableArrayList();
        try {
            ResultSet datitos = c.mostrarSql(c.ver_esquemas());
            while (datitos.next()) {
                for (int z=0; z<1;z++)
                {
                    lista_esquemas.add(new Esquema(
                            datitos.getInt("id"),
                            datitos.getString("ruta"),
                            datitos.getString("descripcion"),
                            datitos.getString("numero")
                    ));

                }
            }
            tv_esquemas.setItems(lista_esquemas);
            columna_descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            columna_numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
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


        public void aceptar(Event event) {

        try {
            if(tv_esquemas.getSelectionModel().isEmpty())
            {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Maquinados industriales");
                alerta.setHeaderText(null);
                alerta.setContentText("¡Selecciona un archivo!");
                alerta.showAndWait();
            }
            else
            {
                int id_esquema= tv_esquemas.getSelectionModel().getSelectedItem().getId();
                String n = obje.getNumero_producto();
                c.modificaresquema(id_esquema,n);
                c.cerrarConexion();
                String r =   tv_esquemas.getSelectionModel().getSelectedItem().getRuta();
                producto pro = new producto();
                pro.setRuta_imagen(r);
                producto_seleccionado.setObj2(pro);
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Maquinados industriales");
                alerta.setHeaderText("Exito");
                alerta.setContentText("¡Archivo agregado correctamente!");
                alerta.showAndWait();
                producto_seleccionado.sel_esquema= new Stage();
                ((Node)(event.getSource())).getScene().getWindow().hide();
                Stage stage= (Stage) this.btn_aceptar.getScene().getWindow();
                stage.getOnCloseRequest().handle( new WindowEvent(
                        stage,
                        WindowEvent.WINDOW_CLOSE_REQUEST));
                stage.close();



            }


            } catch(Exception ec){
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Revisa tu conexion");
                alerta.setHeaderText("¡Error de servidor!");
                alerta.setContentText("Algo esta fallando");
                alerta.showAndWait();

            }
            }
            public void cancelar() {tv_esquemas.getSelectionModel().clearSelection();image_esquema.setImage(null);
            }



}
