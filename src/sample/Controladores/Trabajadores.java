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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Conexion_bd.Conexion;
import sample.Controladores.Trabajador.Trabajador_faltas;
import sample.Controladores.Trabajador.Trabajadores_Alta;
import sample.objetos.Trabajador;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Trabajadores implements Initializable {
    @FXML Button btn_agregarTrabajador,
            btn_editarTrabajador,
            btn_verFaltas,
            btn_agregarFalta,
            btn_agregarPrestamo,
            btn_verPrestamo,
            btn_verVacaciones,
            btn_agregarVacaciones;

    @FXML ListView<String> listview_trabajadores = new ListView<>();

    @FXML TableView<Trabajador> table_trabajador= new TableView<>();

    @FXML Pane panel_Editar;

    @FXML TextField txt_nombre,txt_paterno,txt_materno,txt_rfc,txt_ruta;

    @FXML CheckBox check_activo;

    @FXML Button btn_editado,btn_agregarArchivo;

    private Stage stage;

    ObservableList<String> list;

    Conexion conexion= new Conexion();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        panel_Editar.toBack();
        panel_Editar.setVisible(false);
        txt_nombre.setEditable(false);
        txt_nombre.toBack();
        txt_paterno.setEditable(false);
        txt_paterno.toBack();
        txt_materno.setEditable(false);
        txt_materno.toBack();
        txt_rfc.setEditable(false);
        txt_rfc.toBack();
        txt_ruta.setEditable(false);
        txt_ruta.toBack();
        check_activo.setDisable(false);
        check_activo.toBack();
        btn_agregarArchivo.setDisable(false);
        btn_agregarArchivo.toBack();
        btn_editado.toBack();
        btn_editado.setDisable(false);

        List<String> nombres= new ArrayList<>();

        for (Trabajador t: getTrabajos()) {
            nombres.add(t.nombreCompleto());
        }


        System.out.println("se seleccionó trabajadores");
        list= FXCollections.observableList(nombres);
        listview_trabajadores.setItems(list);

        TableColumn firstNameCol = new TableColumn("Id");
        TableColumn lastNameCol = new TableColumn("Nombre");
        TableColumn tabla_ColumnaAp = new TableColumn("Apellido paterno");
        TableColumn tabla_ColumnaAm = new TableColumn("Apellido materno");
        TableColumn tabla_ColumnaRFC = new TableColumn("Rfc");
        TableColumn tabla_ColumnaDir = new TableColumn("Curriculum");
        TableColumn tabla_ColumnaEst = new TableColumn("Estado");

        tabla_ColumnaDir.maxWidthProperty().setValue(100);
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
        tabla_ColumnaEst.setCellValueFactory(
                new PropertyValueFactory<Trabajador,String>("Estado")
        );
        table_trabajador.getColumns().addAll(firstNameCol,lastNameCol,tabla_ColumnaAp,tabla_ColumnaAm,tabla_ColumnaRFC,tabla_ColumnaDir,tabla_ColumnaEst);
        table_trabajador.setItems(getTrabajos());
        table_trabajador.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
              Trabajador trabajador_seleccion=  table_trabajador.getSelectionModel().getSelectedItem();


              if (panel_Editar.isVisible()){

              }
              else {
                  if  (trabajador_seleccion!=null) {
                      panel_Editar.setVisible(true);
                      panel_Editar.toFront();
                      txt_nombre.setText(trabajador_seleccion.getNombre());
                      txt_nombre.setEditable(true);
                      txt_nombre.toFront();

                      txt_paterno.setText(trabajador_seleccion.getApellido_paterno());
                      txt_paterno.setEditable(true);
                      txt_paterno.toFront();

                      txt_materno.setText(trabajador_seleccion.getApellido_materno());
                      txt_materno.toFront();
                      txt_materno.setEditable(true);

                      txt_rfc.setText(trabajador_seleccion.getRfc());
                      txt_rfc.setEditable(true);
                      txt_rfc.toFront();

                      check_activo.toFront();
                      check_activo.setDisable(false);
                      txt_ruta.toFront();
                      btn_agregarArchivo.toFront();
                      btn_editado.toFront();
                  }

              }



            }
        });

    }

    public void click_trabajador(MouseEvent mouseEvent) {

        table_trabajador.setItems(getTrabajos());
        table_trabajador.refresh();
    }

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
                    trabajador.setEstado(trabajadorresResult.getObject(7).toString());
                    trabajadores.add(trabajador);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trabajadores;
    }

    public void mandar_prueba(ActionEvent event){
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Trabajador_faltas.fxml"));
            Parent abrir = fxmlLoader.load();
            Stage ventana_TrabajadorAlta= new Stage();

            if (ventana_TrabajadorAlta.getScene() == null) {
                ventana_TrabajadorAlta.setTitle("Asignar faltas");
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

                    table_trabajador.setItems(getTrabajos());
                    table_trabajador.refresh();


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

    public void editado(ActionEvent event) {


    }

    public void cerrarpanel(ActionEvent event) {

        panel_Editar.setVisible(false);
    }

    public void evento_btneditar(ActionEvent event) {


    }
}
