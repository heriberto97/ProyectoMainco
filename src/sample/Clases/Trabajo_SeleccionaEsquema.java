package sample.Clases;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Conexion_bd.Conexion;
import sample.objetos.Esquema;
import sample.objetos.Inventario_oficina;

public class Trabajo_SeleccionaEsquema {

    @FXML
    private TableView<Esquema> tv_articulos;
    @FXML
    private TableColumn<Esquema, Integer> columna_numero_articulo;
    @FXML
    private Button btn_buscar;

    @FXML
    private Button btn_salir;

    @FXML
    private TextField tf_busqueda;

    @FXML
    private ComboBox<?> cb_filtrar;

    @FXML
    private Button btn_seleccionar;

    private Conexion c = new Conexion();
    private ObservableList<Inventario_oficina> lista_articulos;

   // @FXML
    //void traresquemas() {
      //  lista_articulos =  FXCollections.observableArrayList();

       // try {

        //    ResultSet datis = c.mostrarSql(c.veresquemas());

//            while (datis.next()) {
            //    for (int z=0; z<1;z++)
          //      {
          //          lista_articulos.add(new Inventario_oficina(
                           // datis.getInt("id")));
        //        }
  //          }
      //      tv_articulos.setItems(lista_articulos);


//            columna_numero_articulo.setCellValueFactory(new PropertyValueFactory<>("id"));


         //   c.cerrarConexion();
       // }
    //    catch (Exception e)
       // {
         //   Alert alerta = new Alert(Alert.AlertType.WARNING);
        //    alerta.setTitle("Revisa tu conexion");
        //    alerta.setHeaderText("¡Error de servidor!");
        //    alerta.setContentText("Algo esta fallando");
        //    alerta.showAndWait();
       // }
    //}

}
