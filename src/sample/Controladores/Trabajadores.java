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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sample.Conexion_bd.Conexion;
import sample.objetos.Trabajador;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Trabajadores implements Initializable {
    /*
    botones para
     */
    @FXML Button btn_agregarTrabajador,
            btn_editarTrabajador,
            btn_verFaltas,
            btn_agregarFalta,
            btn_agregarPrestamo,
            btn_verPrestamo,
            btn_verVacaciones,
            btn_agregarVacaciones,
            btn_busqueda;

    /*
    list view para la tabla
     */
    @FXML ListView<String> listview_trabajadores = new ListView<>();

    /*
    tabla de trabajadores
     */
    @FXML TableView<Trabajador> table_trabajador= new TableView<>();

    /*
    el panel de editar empleado
     */
    @FXML Pane panel_Editar;

    /*
    el panel
     */
    @FXML AnchorPane Ap_vista,Ap_lateral;

    /*
    text fields del panel editar
     */
    @FXML TextField txt_nombre,txt_paterno,txt_materno,txt_rfc,txt_ruta,txt_buscar;

    /*
     campos para editar
     */
    @FXML CheckBox check_activo;

    /*
    botones de editar empleado
     */
    @FXML Button btn_editado,btn_agregarArchivo;

    /*
    variables extras
     */
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

        ResultSet resultSet= conexion.mostrarSql(conexion.verTrabajadores());

        List<String> nombres= new ArrayList<>();

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
        table_trabajador.setItems(getTrabajos(resultSet));
        conexion.cerrarConexion();
        table_trabajador.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });




    }

    public void click_trabajador(MouseEvent mouseEvent) {
        ResultSet resultSet= conexion.mostrarSql(conexion.verTrabajadores());

        table_trabajador.setItems(getTrabajos(resultSet));
        table_trabajador.refresh();
        conexion.cerrarConexion();
    }

    /*
        metodo para sacar los trabajadores y meterlos al tableView
     */
    public  ObservableList<Trabajador> getTrabajos(ResultSet trabajadorresResult){
        ObservableList<Trabajador> trabajadores= FXCollections.observableArrayList();

        try {
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
                        trabajador.setSolicitud_empleo("null");
                    }
                    if (trabajadorresResult.getObject(7)!=null){
                        trabajador.setEstado(trabajadorresResult.getObject(7).toString());
                    }
                    else{
                        trabajador.setEstado("Inactivo");
                    }
                    trabajadores.add(trabajador);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trabajadores;
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
                    ResultSet resultSet= conexion.mostrarSql(conexion.verTrabajadores());

                    table_trabajador.setItems(getTrabajos(resultSet));
                    table_trabajador.refresh();

                    conexion.cerrarConexion();

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

        Trabajador t= new Trabajador(trabajador_seleccion.getId(),
                txt_nombre.getText(),
                txt_paterno.getText(),
                txt_materno.getText(),
                txt_rfc.getText(),
                txt_ruta.getText());

        if (check_activo.isSelected()){
            t.setEstado("Activo");
        }
        else{
            t.setEstado("Inactivo");
        }
        conexion.editarTrabajador(t);
        conexion.cerrarConexion();

        ResultSet resultSet= conexion.mostrarSql(conexion.verTrabajadores());
        table_trabajador.setItems(getTrabajos(resultSet));
        conexion.cerrarConexion();
        table_trabajador.refresh();
        cerrarpanel(event);

        /*
        notificaciones
         */
        Notifications noti = Notifications.create()
                .title("Empleado Editado!")
                .text("Se han cambiado los datos con éxito")
                .graphic(null)
                .hideAfter(Duration.seconds(4))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("hizo clic en la notificacion");
                    }
                });
        noti.show();


    }

    public void cerrarpanel(ActionEvent event) {

        panel_Editar.setVisible(false);
        Ap_vista.setDisable(false);
        Ap_lateral.setDisable(false);
        ResultSet resultSet= conexion.mostrarSql(conexion.verTrabajadores());
        table_trabajador.setItems(getTrabajos(resultSet));
        conexion.cerrarConexion();
        table_trabajador.refresh();

    }

    /*
    evento para abrir o editar un empleado
     */
    Trabajador trabajador_seleccion;
    public void evento_btneditar(ActionEvent event) {
        trabajador_seleccion=table_trabajador.getSelectionModel().getSelectedItem();


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

                if (trabajador_seleccion.getEstado().equals("Activo")){
                    check_activo.setSelected(true);
                }
                else if (trabajador_seleccion.getEstado().equals("Inactivo")){
                    check_activo.setSelected(false);
                }
                else if (trabajador_seleccion.getEstado().isEmpty()){
                    check_activo.setSelected(false);
                }
                check_activo.toFront();
                check_activo.setDisable(false);

                if (trabajador_seleccion.getRfc().toString().equals("null")){
                    btn_agregarArchivo.setText("Agregar");
                    txt_ruta.setText(" ");
                }
                else {
                    btn_agregarArchivo.setText("Editar ruta");
                    txt_ruta.setText(trabajador_seleccion.getSolicitud_empleo());

                }
                txt_ruta.toFront();


                btn_agregarArchivo.toFront();
                btn_editado.toFront();
                Ap_vista.setDisable(true);
                Ap_lateral.setDisable(true);
            }

        }
    }


    public void buscar_empleado(ActionEvent event) {
        encuentraEmpleado();
    }

    public void buscar_enter(KeyEvent keyEvent) {

        switch (keyEvent.getCode()){
            case ENTER:
                encuentraEmpleado();
                break;
        }
    }

    /*
    metodo para buscar un empleado por nombre
     */
    public void encuentraEmpleado(){


        if (txt_buscar.getText().isEmpty()){
            ResultSet resultSet= conexion.mostrarSql(conexion.verTrabajadores());
            table_trabajador.setItems(getTrabajos(resultSet));
            table_trabajador.refresh();
            conexion.cerrarConexion();

        }
        else {
            ResultSet resultSet= conexion.mostrarSql(conexion.buscarTrabajadores(txt_buscar.getText()));
            table_trabajador.setItems(getTrabajos(resultSet));
            table_trabajador.refresh();
            conexion.cerrarConexion();

        }

    }


    /*
     método para subir un archivo o para usar el filechooser
     */
    public void subirArchivo(ActionEvent event) {
        FileChooser fc  = new FileChooser();
        //FIltros
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files","*.pdf")
                , new FileChooser.ExtensionFilter("Jpg Images","*.jpg","*.JPEG","*.JPG","*.jpeg","*.PNG","*.png"));

        File fileSelected = fc.showSaveDialog(null);

        if (fileSelected!= null){
            txt_ruta.setText(fileSelected.getPath());
        }
        else{
            System.out.println("no se seleccinoó");
        }

    }

    public void subir_archivo(ActionEvent event) {
        subirArchivo(event);
    }

    public void agregar_Falta(ActionEvent event) {

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

    public void ver_faltas(ActionEvent event) {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Trabajador_verFaltas.fxml"));
            Parent abrir = fxmlLoader.load();
            Stage ventana_TrabajadorAlta= new Stage();

            if (ventana_TrabajadorAlta.getScene() == null) {
                ventana_TrabajadorAlta.setTitle("Ver Faltas de los Trabajadores" );
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

