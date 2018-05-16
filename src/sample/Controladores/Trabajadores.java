package sample.Controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Conexion_bd.Conexion;
import sample.Controladores.Trabajador.Trabajadores_Alta;
import sample.objetos.Trabajador;
import javafx.scene.control.TableView;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Trabajadores implements Initializable {
    @FXML
    TitledPane panel_trabajadores;
    @FXML
    TitledPane panel_faltas;
    @FXML
    TitledPane panel_prestamos;
    @FXML
    ListView<String> listview_trabajadores = new ListView<>();
    @FXML
    TableView<Trabajador> table_trabajador= new TableView<>();
    private Stage stage;
    ObservableList<String> list;

        public void click_trabajador(MouseEvent mouseEvent) {

            table_trabajador.setItems(getTrabajos());
            table_trabajador.refresh();
            }
    Conexion conexion= new Conexion();

    public  ObservableList<Trabajador> getTrabajos(){
        ObservableList<Trabajador> trabajadores= FXCollections.observableArrayList();

        try {
            ResultSet trabajadorresResult= conexion.mostrarSql(conexion.verTrabajadores());
            while (trabajadorresResult.next()) {

                for (int i = 0; i < 1; i++) {

                Trabajador trabajador= new Trabajador(
                        Integer.parseInt(trabajadorresResult.getObject(1).toString()),
                        trabajadorresResult.getObject(2).toString(),
                        trabajadorresResult.getObject(3).toString(),
                        trabajadorresResult.getObject(4).toString(),
                        trabajadorresResult.getObject(5).toString()
                );
                if (trabajadorresResult.getObject(6)!=null){
                    trabajador.setSolicitud_empleo(trabajadorresResult.getObject(6).toString());
                }

                else{
                    trabajador.setSolicitud_empleo("No tiene");
                }
                trabajadores.add(trabajador);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

       return trabajadores;
    }


    public void click_faltas(MouseEvent mouseEvent) {
        System.out.printf("se seleccionó faltas");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> nombres= new ArrayList<>();
        nombres.add("juan");
        nombres.add("jose");
        nombres.add("pedro");
        nombres.add("antonio");

        System.out.println("se seleccionó trabajadores");
        list= FXCollections.observableList(nombres);
        listview_trabajadores.setItems(list);

        TableColumn firstNameCol = new TableColumn("Id");
        TableColumn lastNameCol = new TableColumn("Nombre");
        TableColumn tabla_ColumnaAp = new TableColumn("Apellido paterno");
        TableColumn tabla_ColumnaAm = new TableColumn("Apellido materno");
        TableColumn tabla_ColumnaRFC = new TableColumn("Rfc");
        TableColumn tabla_ColumnaDir = new TableColumn("Curriculum");

        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Trabajador,Integer>("Id")
        );
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Trabajador,String>("Nombre")
        );
        tabla_ColumnaAp.setCellValueFactory(
                new PropertyValueFactory<Trabajador,String>("Apellido_paterno")
        );
        tabla_ColumnaAm.setCellValueFactory(
                new PropertyValueFactory<Trabajador,String>("Apellido_materno")
        );
        tabla_ColumnaRFC.setCellValueFactory(
                new PropertyValueFactory<Trabajador,String>("Rfc")
        );
        tabla_ColumnaDir.setCellValueFactory(
                new PropertyValueFactory<Trabajador,String>("Solicitud_empleo")
        );
        table_trabajador.getColumns().addAll(firstNameCol,lastNameCol,tabla_ColumnaAp,tabla_ColumnaAm,tabla_ColumnaRFC,tabla_ColumnaDir);
        table_trabajador.setItems(getTrabajos());

    }
    public void trabajador_ventana(ActionEvent event) {

        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Trabajadores_Alta.fxml"));
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
