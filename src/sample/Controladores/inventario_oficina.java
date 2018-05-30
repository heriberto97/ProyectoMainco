package sample.Controladores;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sample.Conexion_bd.Conexion;
import sample.objetos.Compras.Compra;
import sample.objetos.Inventario_oficina;

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
    private  Conexion c = new Conexion();
    private  ObservableList<Inventario_oficina> lista_articulos;
    private ArrayList<Inventario_oficina> lista_cantidades;
    static Stage nuevo_articulo = new Stage();
   static Stage modificar_articulo = new Stage();

    public Object datosalerta[] = new Object[4];
     //METODO PARA ABRIR FORKULARIO NUEVO ARTICULO
    public void abrir_form(javafx.event.ActionEvent event)
    {


        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Nuevo_articulo.fxml"));
            Parent abrir = fxmlLoader.load();

            // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
            if (nuevo_articulo.getScene() == null) {
               nuevo_articulo.setTitle("Nuevo articulo");
               nuevo_articulo.setScene(new Scene(abrir));
               nuevo_articulo.show();

                // El evento vaciará la ventana antes de ser cerrada, así se podrá abrir nuevamente
                nuevo_articulo.setOnCloseRequest(e -> {
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
    }
    //MWTODO PARA ABRIR UN NUEVO FORMULARIO CON LA INFORMACION DE UN ARTICULO PARA MODIFICAR
    public void click_articulo(MouseEvent event)
    {
        int numero = tv_articulos.getSelectionModel().getSelectedItem().getId();
        String descripcion=  tv_articulos.getSelectionModel().getSelectedItem().getDescripcion();
        int cantidad = tv_articulos.getSelectionModel().getSelectedItem().getCantidad();
        String estado = tv_articulos.getSelectionModel().getSelectedItem().getEstado();

        Inventario_oficina articulo= new Inventario_oficina();
        articulo.setId(numero);
        articulo.setDescripcion(descripcion);
        articulo.setCantidad(cantidad);
        articulo.setEstado(estado);
        Modificar_articulo.setObj(articulo);


        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/modificar_articulo.fxml"));
            Parent abrir = fxmlLoader.load();

            // Verifica si la ventana tiene una escena, si no la tiene, le asigna una y la muestra
            if (modificar_articulo.getScene() == null) {

                modificar_articulo.setTitle("Modificar articulo");
                modificar_articulo.setScene(new Scene(abrir));
                modificar_articulo.show();

                // El evento vaciará la ventana antes de ser cerrada, así se podrá abrir nuevamente
                modificar_articulo.setOnCloseRequest(e -> {

                    modificar_articulo.setScene(null);


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


    }
    //METODO PARA LLENAR EL COMBOBOX DE OPCIONES
    public void llenarcombo()
    {
        ObservableList<String> items1 = FXCollections.observableArrayList();
        items1.addAll("Numero de articulo", "Descripcion", "Estado");
        cb_filtrar.setItems(items1);
    }
    //METODO AL INICIAR LA VENTANA
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        llenarcombo();
        llenartabla();
        for (int i =0;i<lista_cantidades.size();i++)
        {
            if ( lista_cantidades.get(i).getCantidad()<=5)
            {
                Notifications noti = Notifications.create()
                        .title("Alerta!")
                        .text("Articulos bajos en inventario")
                        .graphic(null)
                        .hideAfter(Duration.seconds(10))
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
                            datitos.getString("estado")));
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
//ACTUALIZAR LA TABLA PRINCIPAL
    public void actualiza()
    {
        llenartabla();
    }
}
