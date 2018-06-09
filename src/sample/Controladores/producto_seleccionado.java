package sample.Controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.PropertySheet;
import sample.Conexion_bd.Conexion;
import sample.objetos.Inventario_oficina;
import sample.objetos.Material;
import sample.objetos.producto;
import sample.objetos.productos_materiales;

import javax.swing.text.html.ImageView;
import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    @FXML Label label_material_actual;
    @FXML TextField txt_tiempo,txt_peso;
    ObservableList <Material>  materiales;
    int   id_material;
    String nombre_material;
    @FXML Button btn_guardar_descripcion;
    @FXML
    ComboBox<Material> cb_materiales;
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
        llenarcombomateriales();
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
    public void modificar_numero()
    {

    }
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
                            .hideAfter(Duration.seconds(5))
                            .position(Pos.BOTTOM_RIGHT)
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
        {txt_descripcion.setEditable(true);}



    }
    public void modificar_esquema()
    {

    }
    public void modificar_material()
    {

    }public void modificar_tiempo()
    {

    }
    public void modificar_peso()
    {

    }
    //click en la tabla de los materiales
    public void click_tabla()
    {
        if(tv_datosadicionales.getSelectionModel().isEmpty())
        {

        }
        else
        {
           String material = tv_datosadicionales.getSelectionModel().getSelectedItem().getMaterial();
           int tiempo = tv_datosadicionales.getSelectionModel().getSelectedItem().getTiempo_estimado();
           Double peso = tv_datosadicionales.getSelectionModel().getSelectedItem().getPeso();

           if(material==null)
           {
               label_material_actual.setText("Sin asignar");
               System.out.println("nulo");
           }
           else
           {
               label_material_actual.setText(material);
           }
           txt_tiempo.setText(Integer.toString(tiempo));
          txt_peso.setText(Double.toString(peso));
          tv_datosadicionales.getSelectionModel().clearSelection();





        }
    }
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

    public void btn_asignacion()
    {

    }


}
