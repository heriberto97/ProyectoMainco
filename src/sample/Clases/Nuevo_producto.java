package sample.Clases;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import sample.Conexion_bd.Conexion;
import sample.objetos.*;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;



public class Nuevo_producto implements Initializable {
    @FXML Button btn_asignar,btn_guardar_adicionales,btn_cancelar,btn_guardar,btn_salir;
    @FXML private  TableView<Esquema> tv_esquemas;
    @FXML private TableColumn<Esquema, String>columna_descripcion;
    private ObservableList<Esquema> lista_esquemas;
    Conexion c = new Conexion();
    @FXML
    ComboBox<Empresa> cb_empresas;
    @FXML
    TextField txt_numero;
    @FXML
    TextArea txt_descripcion;
    @FXML
    private javafx.scene.image.ImageView image_esquema;

    @FXML
    ComboBox<Material> cb_materiales;
    // ComboBox<Empresa> cb_empresas;
    ObservableList <Empresa>  data;
    ObservableList <Material>  materiales;

    @FXML Label lbl_materiales,lbl_peso,lbl_minutos_lado,lbl_tiempo,lbl_gramos;
    @FXML TextField txt_minutos,txt_gramos;
    private int id;
    private String nombre;
    private int id_material;
    private String nombre_material;
    String idi;
    //LLENAR LA TABLA DE ESQUEMAS
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
                                datitos.getString("descripcion")));

                    }
                }
                tv_esquemas.setItems(lista_esquemas);
                columna_descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
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
    //CUANDO INICIA EL FORM PERRON
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txt_gramos.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txt_gramos.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        txt_minutos.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txt_minutos.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        llenarcomboempresas();
        llenarcombomateriales();
        llenartabladeesquemas();



    }
    //METODO PARA GUARDAR UN PRODUCTO
    public void guardar() {


        try
        {

            if(txt_numero.getText().isEmpty()||txt_descripcion.getText().isEmpty())
            {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Maquinados industriales");
                alerta.setHeaderText(null);
                alerta.setContentText("¡Completa los campos!");
                alerta.showAndWait();
            }
            else
            {
                if(tv_esquemas.getSelectionModel().getSelectedItem()==null)
                {
                    idi = txt_numero.getText();
                    // Registrando de producto con campos obligatorios.-----------------------------------------------------------------------------------------------------
                    producto p = new producto( txt_numero.getText(),txt_descripcion.getText(),Integer.toString(cb_empresas.getSelectionModel().getSelectedItem().getId()));
                    c.AltaProductocamposobligatorios(p);
                    c.cerrarConexion();

                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Maquinados industriales");
                    alerta.setHeaderText("Exito");
                    alerta.setContentText("¡Producto creado correctamente!");
                    alerta.showAndWait();
                    btn_asignar.setVisible(true);
                    btn_asignar.setText("Asignar datos adicionales a: "+ idi);


                    txt_numero.setText("");
                    txt_descripcion.setText("");
                    tv_esquemas.getSelectionModel().clearSelection();
                    image_esquema.setImage(null);
                    System.out.println(idi);


                }
                else
                {
                    idi = txt_numero.getText();
                    producto p = new producto(txt_numero.getText(),txt_descripcion.getText(),Integer.toString(tv_esquemas.getSelectionModel().getSelectedItem().getId()),Integer.toString(cb_empresas.getSelectionModel().getSelectedItem().getId()));
                    c.AltaProductocamposobligatoriosyesquema(p);
                    c.cerrarConexion();
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Maquinados industriales");
                    alerta.setHeaderText("Exito");
                    alerta.setContentText("¡Producto creado correctamente!");
                    alerta.showAndWait();
                    btn_asignar.setVisible(true);
                    btn_asignar.setText("Asignar datos adicionales a: "+ idi);
                    txt_numero.setText("");
                    txt_descripcion.setText("");
                    tv_esquemas.getSelectionModel().clearSelection();
                    image_esquema.setImage(null);
                    System.out.println(idi);

                }




            }








        }

        catch(Exception e)
        {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Revisa tu conexion");
            alerta.setHeaderText("¡Error de servidor!");
            alerta.setContentText("Algo esta fallando");
            alerta.showAndWait();
        }






    }
    //LLENAR EL COMBO DE MATERIALES
    public void llenarcomboempresas() {
        data = FXCollections.observableArrayList();
        try {

            ResultSet rs = c.mostrarSql(c.comboempresas());
            while (rs.next()) {
                //get string from db,whichever way

                data.add(new Empresa(
                        rs.getInt("id"),
                        rs.getString("nombre")));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        Callback<ListView<Empresa>, ListCell<Empresa>> factory = lv -> new ListCell<Empresa>() {
            @Override
            protected void updateItem(Empresa item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNombre());
            }

        };

        cb_empresas.setCellFactory(factory);
        cb_empresas.setButtonCell(new ListCell<Empresa>() {
            @Override
            protected void updateItem(Empresa t, boolean bln) {
                super.updateItem(t, bln);
                if (t != null) {
                    setText(t.getNombre());

                    id= t.getId();
                    nombre = t.getNombre();

                } else {
                    setText(null);
                }
            }
        });

        cb_empresas.setItems(data);
        cb_empresas.setValue(data.get(0));

    }
    //llenar el combo de materiales
    public void llenarcombomateriales() {
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
    //click en el esquema
    public void click_esquema() {

        if(tv_esquemas.getSelectionModel().isEmpty())
        {

        }
        else
        {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText("¡Cuidado!");
            alert.setContentText("Estas seguro de seleccionar este esquema?");

            String ruta = tv_esquemas.getSelectionModel().getSelectedItem().getRuta();
            File file = new File(ruta);
            Image image = new Image(file.toURI().toString());
            image_esquema.setImage(image);


            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){

            }
            else
            {
                tv_esquemas.getSelectionModel().clearSelection();
                image_esquema.setImage(null);
            }


        }

    }
    //BOTON QUE MUESTRA LABELS.TEXTFIELD Y BOTONES PARA ASIGNAR DATOS
    public void asignar_datos() {

        System.out.println(idi);
        txt_numero.setDisable(true);
        txt_descripcion.setDisable(true);
        btn_guardar.setDisable(true);
        tv_esquemas.setDisable(true);
        cb_empresas.setDisable(true);





        btn_cancelar.setVisible(true);
        lbl_materiales.setVisible(true);
        lbl_peso.setVisible(true);
        lbl_minutos_lado.setVisible(true);
        lbl_tiempo.setVisible(true);
        lbl_gramos.setVisible(true);
        cb_materiales.setVisible(true);
        txt_minutos.setVisible(true);
        txt_gramos.setVisible(true);
        btn_guardar_adicionales.setVisible(true);


    }
    //BOTON CANCELAR DE LOS DATOS ADICIONALES
    public void cancelar() {
        lbl_materiales.setVisible(false);
        lbl_peso.setVisible(false);
        lbl_minutos_lado.setVisible(false);
        lbl_tiempo.setVisible(false);
        lbl_gramos.setVisible(false);
        cb_materiales.setVisible(false);
        txt_minutos.setVisible(false);
        txt_gramos.setVisible(false);
        btn_cancelar.setVisible(false);
        btn_guardar_adicionales.setVisible(false);
    }
    //BOTON PARA GUARDAR DATOS ADICIONALES
    public void guardar_datos_adi() {
        if(txt_gramos.getText().equals(""))
            {
                txt_gramos.setText(Integer.toString(0));
            }

            if(txt_minutos.getText().equals(""))
            {
                txt_minutos.setText(Integer.toString(0));
            }
            productos_materiales p = new productos_materiales();
                p.setProducto(idi);
                p.setMaterial(Integer.toString(cb_materiales.getSelectionModel().getSelectedItem().getId()));
                p.setTiempo_estimado(Integer.parseInt(txt_minutos.getText()));
                p.setPeso(Double.parseDouble(txt_gramos.getText()));
                c.Altaasignardatos(p);
                c.cerrarConexion();
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Maquinados industriales");
                alerta.setHeaderText("Exito");
                alerta.setContentText("¡Datos asignados correctamente!");
                alerta.showAndWait();
                cancelar();
                btn_asignar.setVisible(false);
                txt_minutos.setText("");
                txt_gramos.setText("");
                txt_numero.setDisable(false);
                txt_descripcion.setDisable(false);
                btn_guardar.setDisable(false);
                tv_esquemas.setDisable(false);
                cb_empresas.setDisable(false);
                Stage stage= (Stage) this.btn_guardar_adicionales.getScene().getWindow();
                stage.getOnCloseRequest().handle( new WindowEvent(
                        stage,
                        WindowEvent.WINDOW_CLOSE_REQUEST));
                stage.close();



    }
    public void salir(ActionEvent event) {
        inventario_productos.nuevo_producto= new Stage();
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage stage= (Stage) this.btn_salir.getScene().getWindow();
        stage.getOnCloseRequest().handle( new WindowEvent(
                stage,
                WindowEvent.WINDOW_CLOSE_REQUEST));
        stage.close();

     }

}

