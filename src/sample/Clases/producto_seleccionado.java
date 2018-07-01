package sample.Clases;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sample.Conexion_bd.Conexion;
import sample.objetos.*;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class producto_seleccionado implements Initializable {

    static producto obje = new producto();
    static producto obje2 = new producto();
    public static void setObj(producto obje) {
        producto_seleccionado.obje = obje;
    }

    public static void setObj2(producto obje2) {
        producto_seleccionado.obje2 = obje2;
    }
    @FXML private TableView<productos_materiales> tv_datosadicionales;
    @FXML private TableColumn<productos_materiales, String> columna_numero;
    @FXML private   TableColumn<productos_materiales, Integer> columna_tiempo;
    @FXML private   TableColumn<productos_materiales,Double > columna_peso;
    @FXML private   TableColumn<productos_materiales, String> columna_material;
    @FXML
    private Label label_material_actual_estatico;
    @FXML
    private Label label_material_disponilble;
    @FXML
    private Label label_tiempo_estimado;

    @FXML
    private Label label_peso;
    @FXML Label label_material_actual;
    @FXML TextField txt_tiempo,txt_peso;
    ObservableList <Material>  materiales;
    int   id_material;
    String nombre_material;
    String numero_id;
    @FXML Button btn_guardar_descripcion,btn_guardar_esquema,btn_guardar_file,btn_guardar_material,btn_guardar_tiempo,btn_guardar_peso,btn_guardar_asignacion;
    @FXML
    ComboBox<Material> cb_materiales,cb_materiales2;
    private ObservableList<productos_materiales> lista_productos;
    Conexion c = new Conexion();
    public Object esquemas_id[] = new Object[3];
    String reg;
    @FXML
    TextField txt_numero,txt_tiempo2,txt_peso2;
    @FXML
    TextArea txt_descripcion;
    @FXML
    private javafx.scene.image.ImageView image_esquema;
    String ruta;
    static Stage sel_esquema = new Stage();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label_material_actual_estatico.setVisible(false);
        label_material_disponilble.setVisible(false);
        cb_materiales.setVisible(false);
        btn_guardar_material.setVisible(false);
        label_tiempo_estimado.setVisible(false);
        txt_tiempo.setVisible(false);
        btn_guardar_tiempo.setVisible(false);
        label_peso.setVisible(false);
        txt_peso.setVisible(false);
        btn_guardar_peso.setVisible(false);
        noletrasenestosTextfield();
        llenarcombomateriales();
        llenarcombodemateriales2();
        ruta =obje.getRuta_imagen() ;
        txt_numero.setText(obje.getNumero_producto());
        txt_descripcion.setText(obje.getDescripcion());

        llenartabla();


     if (ruta==null)
        {
            File file = new File("C:\\Users\\gwend\\IdeaProjects\\ProyectoMainco\\src\\sample\\Clases\\sin_asignar.jpg");
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
    int variable =0;
    int variable1 =0;
    public void llenartabla() {
             lista_productos= FXCollections.observableArrayList();
        try {

            ResultSet datitos = c.mostrarSql(c.tabla_producto_seleccionado(txt_numero.getText()));

            while (datitos.next()) {
                for (int z=0; z<1;z++)
                {
                    lista_productos.add(new productos_materiales(
                            datitos.getString("reg"),
                            datitos.getString("numero"),
                            datitos.getString("material"),
                            datitos.getInt("tiempo"),
                            datitos.getDouble("peso")));
                }
                if(datitos.getInt("tiempo")==0) { variable=1; } else { variable=2; }
                if(datitos.getDouble("peso")==0) { variable1=1; } else { variable1=2; }

            }
            tv_datosadicionales.setItems(lista_productos);

            if(variable==1)
            {
                System.out.println("sin asignar tiempo");



            }
            else if (variable==2)
            {
                System.out.println("con tiempo");
            }

            if (variable1==1)
            {
                System.out.println("sin peso");
            }
            else if(variable1==2)
            {
                System.out.println("con peso");
            }
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
    //eventos de botones
    //boton modificar la descripcion
    public void modificar_descripcion() {
        if(txt_descripcion.isEditable())
        {
            try{
                if(txt_descripcion.getText().isEmpty())
                {
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Maquinados industriales");
                    alerta.setHeaderText(null);
                    alerta.setContentText("¡Completa los campos!");
                    alerta.showAndWait();
                }else
                {
                    String descripcion= txt_descripcion.getText();
                    producto p = new producto();
                    p.setNumero_producto(obje.getNumero_producto());
                    p.setDescripcion(descripcion);
                    c.modificardescripcionprpoducto(p);
                    c.cerrarConexion();
                    Notifications noti = Notifications.create()
                            .title("Notificación!")
                            .text("¡La descripción fue modificada correctamente!")
                            .hideAfter(Duration.seconds(3))
                            .position(Pos.TOP_CENTER)
                            .onAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    System.out.println("hizo clic en la notificacion");
                                }
                            });
                    noti.show();
                    txt_descripcion.setText(p.getDescripcion());
                    txt_descripcion.setEditable(false);}
                }


            catch(Exception ex)
            {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Revisa tu conexion");
                alerta.setHeaderText("¡Error de servidor!");
                alerta.setContentText("Algo esta fallando");
                alerta.showAndWait();
            }


        }
        else
        {txt_descripcion.setEditable(true);
            Notifications noti = Notifications.create()
                    .title("Notificación!")
                    .text("¡Ahora puedes modificar!")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.TOP_CENTER)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("hizo clic en la notificacion");
                        }
                    });
            noti.show();
        }



    }
    //metodos para cambiar un esquema.
    public void modificar_esquema() {
        image_esquema.setImage(null);
        producto p = new producto();
        p.setNumero_producto(txt_numero.getText());
        Esquema_seleccionar.setObj(p);

        try
        {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Esquemas_Seleccionar.fxml"));
            Parent abrir = fxmlLoader.load();

            // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
            if (sel_esquema.getScene() == null) {

                sel_esquema.setTitle("Articulos");
                sel_esquema.setScene(new Scene(abrir));
                sel_esquema.show();

                // El evento vaciará la ventana antes de ser cerrada, así se podrá abrir nuevamente
                sel_esquema.setOnCloseRequest(e -> {
                    File file = new File(obje2.getRuta_imagen());
                    Image image = new Image(file.toURI().toString());
                    image_esquema.setImage(image);
                    sel_esquema.setScene(null);


                });
            }
            else {
                // Si la ventana tiene una escena, la trae al frente
                sel_esquema.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    //---------------------------------------------------
    public void modificar_material() {

        try{

            reg = tv_datosadicionales.getSelectionModel().getSelectedItem().getReg();
            System.out.println(reg);
            int seleccion = cb_materiales.getSelectionModel().getSelectedItem().getId();
            c.modificarmaterialproducto(reg,seleccion);
            c.cerrarConexion();
            Notifications noti = Notifications.create()
                    .title("Notificación!")
                    .text("¡Material modificado correctamente!")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.TOP_CENTER)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("hizo clic en la notificacion");
                        }
                    });
            noti.show();
            llenartabla();
            label_material_actual.setText(cb_materiales.getSelectionModel().getSelectedItem().getNombre());
            tv_datosadicionales.getSelectionModel().clearSelection();

        }catch(Exception ex)
        {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Aviso");
            alerta.setHeaderText("¡Selecciona!");
            alerta.setContentText("Selecciona un elemento");
            alerta.showAndWait();
        }
        }
    public void modificar_tiempo() {
        if(txt_tiempo.isEditable())
        {
            try{
                if(txt_tiempo.getText().isEmpty())
                {
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Maquinados industriales");
                    alerta.setHeaderText(null);
                    alerta.setContentText("¡Completa los campos!");
                    alerta.showAndWait();
                }else
                {

                   reg = tv_datosadicionales.getSelectionModel().getSelectedItem().getReg();
                    c.modificartiempoproducto(reg,Integer.parseInt(txt_tiempo.getText()));
                    c.cerrarConexion();
                    Notifications noti = Notifications.create()
                            .title("Notificación!")
                            .text("¡ el tiempo fue modificado correctamente!")
                            .hideAfter(Duration.seconds(3))
                            .position(Pos.TOP_CENTER)
                            .onAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    System.out.println("hizo clic en la notificacion");
                                }
                            });
                    noti.show();
                    llenartabla();

                    txt_tiempo.setEditable(false);}
            }


            catch(Exception ex)
            {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Revisa tu conexion");
                alerta.setHeaderText("¡Error de servidor!");
                alerta.setContentText("Algo esta fallando");
                alerta.showAndWait();
            }


        }
        else
        {
            if(tv_datosadicionales.getSelectionModel().isEmpty())
            {
                Notifications noti = Notifications.create()
                        .title("Notificación!")
                        .text("¡Selecciona un elemento!")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.TOP_CENTER)
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
                txt_tiempo.setEditable(true);
                Notifications noti = Notifications.create()
                        .title("Notificación!")
                        .text("¡Ahora puedes modificar!")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.TOP_CENTER)
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
    public void modificar_peso() {
        if(txt_peso.isEditable())
        {
            try{
                if(txt_peso.getText().isEmpty())
                {
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Maquinados industriales");
                    alerta.setHeaderText(null);
                    alerta.setContentText("¡Completa los campos!");
                    alerta.showAndWait();
                }else
                {

                    reg = tv_datosadicionales.getSelectionModel().getSelectedItem().getReg();
                    c.modificarpesoproducto(reg,Double.parseDouble(txt_peso.getText()));
                    c.cerrarConexion();
                    Notifications noti = Notifications.create()
                            .title("Notificación!")
                            .text("¡el peso fue modificado correctamente!")
                            .hideAfter(Duration.seconds(3))
                            .position(Pos.TOP_CENTER)
                            .onAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    System.out.println("hizo clic en la notificacion");
                                }
                            });
                    noti.show();
                    llenartabla();
                    txt_peso.setEditable(false);}
            }


            catch(Exception ex)
            {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Revisa tu conexion");
                alerta.setHeaderText("¡Error de servidor!");
                alerta.setContentText("Algo esta fallando");
                alerta.showAndWait();
            }


        }
        else
        {
            if(tv_datosadicionales.getSelectionModel().isEmpty())
            {
                Notifications noti = Notifications.create()
                        .title("Notificación!")
                        .text("¡Selecciona un elemento!")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.TOP_CENTER)
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
                txt_peso.setEditable(true);
                Notifications noti = Notifications.create()
                        .title("Notificación!")
                        .text("¡Ahora puedes modificar!")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.TOP_CENTER)
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
    //click en la tabla de los materiales
    public void click_tabla() {
        if(tv_datosadicionales.getSelectionModel().isEmpty())
        {

        }
        else
        {

           String material = tv_datosadicionales.getSelectionModel().getSelectedItem().getMaterial();
           int tiempo = tv_datosadicionales.getSelectionModel().getSelectedItem().getTiempo_estimado();
           Double peso = tv_datosadicionales.getSelectionModel().getSelectedItem().getPeso();
            reg = tv_datosadicionales.getSelectionModel().getSelectedItem().getReg();

            if(reg==null)
            {
                Notifications noti = Notifications.create()
                        .title("Notificación!")
                        .text("¡No tiene datos adicionales, registralos en la parte inferior derecha!")
                        .hideAfter(Duration.seconds(6))
                        .position(Pos.TOP_CENTER)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println("hizo clic en la notificacion");
                            }
                        });
                noti.show();
            }

           if(material==null)
           {
              // label_material_actual.setText("Sin asignar");

           }
           else
           {
               label_material_actual.setText(material);
               label_material_actual_estatico.setVisible(true);
               label_material_disponilble.setVisible(true);
               cb_materiales.setVisible(true);
               btn_guardar_material.setVisible(true);
               label_tiempo_estimado.setVisible(true);
               txt_tiempo.setVisible(true);
               btn_guardar_tiempo.setVisible(true);
               label_peso.setVisible(true);
               txt_peso.setVisible(true);
               btn_guardar_peso.setVisible(true);
           }


           txt_tiempo.setText(Integer.toString(tiempo));
           txt_peso.setText(Double.toString(peso));







        }
    }
    //lena el combo de materiales
    public void llenarcombomateriales()
    {
        materiales = FXCollections.observableArrayList();
        try {

            ResultSet rs = c.mostrarSql(c.combomateriales());
            while (rs.next()) {
                //get string from db,whichever way

                materiales.add(new Material(
                        rs.getInt("id"),
                        rs.getString("nombre")));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        Callback<ListView<Material>, ListCell<Material>> factory = lv -> new ListCell<Material>() {
            @Override
            protected void updateItem(Material item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNombre());
            }

        };

        cb_materiales.setCellFactory(factory);
        cb_materiales.setButtonCell(new ListCell<Material>() {
            @Override
            protected void updateItem(Material t, boolean bln) {
                super.updateItem(t, bln);
                if (t != null) {
                    setText(t.getNombre());

                    id_material= t.getId();
                    nombre_material = t.getNombre();

                } else {
                    setText(null);
                }
            }
        });

        cb_materiales.setItems(materiales);
        cb_materiales.setValue(materiales.get(0));
    }
    public void guardar_asignacion()
    {

        try {
        if(txt_peso2.getText().equals(""))
        {
            txt_peso2.setText(Integer.toString(0));
        }

        if(txt_tiempo2.getText().equals(""))
        {
            txt_tiempo2.setText(Integer.toString(0));
        }
        productos_materiales p = new productos_materiales();
        p.setProducto(obje.getNumero_producto());
        p.setMaterial(Integer.toString(cb_materiales2.getSelectionModel().getSelectedItem().getId()));
        p.setTiempo_estimado(Integer.parseInt(txt_tiempo2.getText()));
        p.setPeso(Double.parseDouble(txt_peso2.getText()));
        c.Altaasignardatos(p);
        c.cerrarConexion();
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Maquinados industriales");
        alerta.setHeaderText("Exito");
        alerta.setContentText("¡Datos asignados correctamente!");
        alerta.showAndWait();
        txt_peso2.setText("");
        txt_tiempo2.setText("");
        llenartabla();
        }catch(Exception ex)
        {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Revisa tu conexion");
            alerta.setHeaderText("¡Error de servidor!");
            alerta.setContentText("Algo esta fallando");
            alerta.showAndWait();
        }
    }

    public void llenarcombodemateriales2()
    {
        materiales = FXCollections.observableArrayList();
        try {

            ResultSet rs = c.mostrarSql(c.combomateriales());
            while (rs.next()) {
                //get string from db,whichever way

                materiales.add(new Material(
                        rs.getInt("id"),
                        rs.getString("nombre")));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        Callback<ListView<Material>, ListCell<Material>> factory = lv -> new ListCell<Material>() {
            @Override
            protected void updateItem(Material item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNombre());
            }

        };

        cb_materiales2.setCellFactory(factory);
        cb_materiales2.setButtonCell(new ListCell<Material>() {
            @Override
            protected void updateItem(Material t, boolean bln) {
                super.updateItem(t, bln);
                if (t != null) {
                    setText(t.getNombre());

                    id_material= t.getId();
                    nombre_material = t.getNombre();

                } else {
                    setText(null);
                }
            }
        });

        cb_materiales2.setItems(materiales);
        cb_materiales2.setValue(materiales.get(0));
    }

    public void noletrasenestosTextfield()
    {
        //SOLO NUMEROS PARA ESTE TEXTFIELD
        txt_tiempo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txt_tiempo.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        txt_tiempo2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txt_tiempo2.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

//        txt_peso.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue,
//                                String newValue) {
//                if (!newValue.matches("\\d*")) {
//                    txt_peso.setText(newValue.replaceAll("[^\\d]", ""));
//                }
//            }
//        });

        txt_peso2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txt_peso2.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }


}
