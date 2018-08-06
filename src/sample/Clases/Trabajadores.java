package sample.Clases;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Conexion_bd.Conexion;
import sample.objetos.Traabajadores.Nomina;
import sample.objetos.Trabajador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            btn_busqueda,
            btn_cambiarfoto,
            btn_guardarN,
            btn_cerrarN;

    /*
    tabla de trabajadores
     */
    @FXML TableView<Trabajador> table_trabajador= new TableView<>();
    /*
    Los paneles
     */
    @FXML AnchorPane panel_tabla,
            panel_Editar,
            Ap_lateral,
            panel_prestamo,
            panel_abono,
            panel_nomina;
    /*
    text fields del panel editar
     */
    @FXML TextField txt_nombre,
            txt_paterno,
            txt_materno,
            txt_rfc,
            txt_ruta,
            txt_buscar,
            txt_PNombre,
            txt_PApellidos,
            txt_Pcantidad,
            txt_ANombre,
            txt_AApellido,
            txt_AcantidadTotal,
            txt_AAbono,
            txt_ATotal,
            txt_numeroPrestamos,
            txt_foto,
            txt_puesto,
            txt_nombreN,
            txt_sueldoN,
            txt_prestamoN,
            txt_ahorroN,
            txt_funerarios,
            txt_infonavitN,
            txt_otroN,
            txt_totalN,
            txt_diasN;
    @FXML
    ImageView foto_perfil;
    /*
     campos para editar
     */
    @FXML CheckBox check_activo;
    /*
    botones de editar empleado
     */
    @FXML Button btn_editado,
            btn_agregarArchivo;

    /*
     botones para agregar abono
     */
    @FXML Button btn_AGuardar,
            btn_cerrarAbono,
            btn_otroAbono;
    /*
    variables extras
     */
    private Stage stage;

    ObservableList<String> list;

    Conexion conexion= new Conexion();

    Trabajador trabajador_seleccion;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        panel_Editar.toBack();
        panel_prestamo.toBack();
        panel_abono.toBack();
        panel_nomina.toBack();
        panel_tabla.toFront();

        txt_ruta.setEditable(false);


        ResultSet resultSet= conexion.mostrarSql(conexion.verTrabajadores());
        System.out.println("se seleccionó trabajadores");

        TableColumn firstNameCol = new TableColumn("Id");
        TableColumn lastNameCol = new TableColumn("Nombre");
        TableColumn tabla_ColumnaAp = new TableColumn("Apellido paterno");
        TableColumn tabla_ColumnaAm = new TableColumn("Apellido materno");
        TableColumn tabla_ColumnaRFC = new TableColumn("Rfc");
        TableColumn tabla_ColumnaDir = new TableColumn("Curriculum");
        TableColumn tabla_ColumnaEst = new TableColumn("Estado");
        TableColumn tabla_columnafoto= new TableColumn("foto");
        TableColumn tabla_columnafecha= new TableColumn("ingreso");
        TableColumn tabla_columnapuesto= new TableColumn("puesto");
        TableColumn tabla_columnaahorro= new TableColumn("ahorro");

        tabla_ColumnaDir.maxWidthProperty().setValue(300);
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
        tabla_columnafoto.setCellValueFactory(
                new PropertyValueFactory<Trabajador,String>("fotoperfil")
        );
        tabla_columnafecha.setCellValueFactory(
                new PropertyValueFactory<Trabajador,String>("fechaingreso")
        );
        tabla_columnapuesto.setCellValueFactory(
                new PropertyValueFactory<Trabajador,String>("puesto")
        );
        tabla_columnaahorro.setCellValueFactory(
                new PropertyValueFactory<Trabajador,String>("ahorro")
        );

        table_trabajador.getColumns().addAll(firstNameCol,
                lastNameCol,
                tabla_ColumnaAp,
                tabla_ColumnaAm,
                tabla_ColumnaRFC,
                tabla_ColumnaDir,
                tabla_ColumnaEst,
                tabla_columnafoto,
                tabla_columnafecha,
                tabla_columnapuesto,
                tabla_columnaahorro);
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
                    trabajador.setFotoperfil(trabajadorresResult.getString(8));
                    trabajador.setFechaigreso(trabajadorresResult.getString(9));
                    trabajador.setPuesto(trabajadorresResult.getString(10));
                    trabajador.setAhorro(trabajadorresResult.getDouble(11));
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Trabajadores_Alta.fxml"));
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

    /**
     * manda a actualizar un trabajador
     * @param event
     */
    public void editado(ActionEvent event) {

        Trabajador t= new Trabajador(trabajador_seleccion.getId(),
                txt_nombre.getText(),
                txt_paterno.getText(),
                txt_materno.getText(),
                txt_rfc.getText(),
                txt_ruta.getText().toString());

        if (check_activo.isSelected()){
            t.setEstado("Activo");
        }
        else{
            t.setEstado("Inactivo");
        }
        t.setFotoperfil(foto);
        t.setPuesto(txt_puesto.getText());
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
       /* Notifications noti = Notifications.create()
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
        noti.show();*/


    }

    /**
     * Regresa los paneles a la normalidad
     * @param event
     */
    public void cerrarpanel(ActionEvent event) {
        panel_prestamo.toBack();
        panel_Editar.toBack();
        panel_abono.toBack();
        panel_tabla.toFront();
        Ap_lateral.setDisable(false);
        ResultSet resultSet= conexion.mostrarSql(conexion.verTrabajadores());
        table_trabajador.setItems(getTrabajos(resultSet));
        conexion.cerrarConexion();
        table_trabajador.refresh();
        txt_Pcantidad.setText("");


    }

    /*
    evento para abrir o editar un empleado
     */
    public void evento_btneditar(ActionEvent event) {
        trabajador_seleccion=table_trabajador.getSelectionModel().getSelectedItem();

            if  (trabajador_seleccion!=null) {
                panel_Editar.toFront();
                Ap_lateral.setDisable(true);
                
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
                txt_foto.setText(trabajador_seleccion.getFotoperfil());
                imagen= new Image(trabajador_seleccion.getFotoperfil());
                foto_perfil.setImage(imagen);
                txt_puesto.setText(trabajador_seleccion.getPuesto());
                btn_agregarArchivo.toFront();
                btn_editado.toFront();


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

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Todas las imágenes","*.*"));

        File fileSelected = fc.showSaveDialog(stage);

        if (fileSelected!= null){
            txt_ruta.setText(fileSelected.getPath());
            SaveFile(fileSelected.getName(),fileSelected);
        }
        else{
            System.out.println("no se seleccinó");
        }
    }

    private void SaveFile(String content, File file){
        try {
            FileWriter fileWriter = null;

            fileWriter = new FileWriter(file);
            fileWriter.write(content);

            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Trabajadores.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void subir_archivo(ActionEvent event) {
        subirArchivo(event);
    }

    public void agregar_Falta(ActionEvent event) {

        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Trabajador_faltas.fxml"));
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Trabajador_verFaltas.fxml"));
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

    public void actualizar_tabla(KeyEvent keyEvent) {

        switch (keyEvent.getCode()){
            case ENTER:
                ResultSet resultSet= conexion.mostrarSql(conexion.verTrabajadores());
                table_trabajador.setItems(getTrabajos(resultSet));
                conexion.cerrarConexion();
                table_trabajador.refresh();

                break;
        }

    }

    Double valor_Total=0.0;
    public void ver_prestamo(ActionEvent event) throws SQLException {
        trabajador_seleccion=table_trabajador.getSelectionModel().getSelectedItem();

        if (trabajador_seleccion==null){

        }
        else{
            if ("Activo".equals(trabajador_seleccion.getEstado())|| "activo".equals(trabajador_seleccion.getEstado())) {
                ResultSet resultSet= conexion.mostrarSql(conexion.verDeudor(trabajador_seleccion.getId()));


                if (resultSet.first()){

                    Ap_lateral.setDisable(false);
                    panel_abono.toFront();
                    txt_ANombre.setText(trabajador_seleccion.getNombre());
                    txt_AApellido.setText(trabajador_seleccion.getApellido_paterno());
                    txt_numeroPrestamos.setText(resultSet.getString(1));
                    valor_Total=resultSet.getDouble(3);
                    txt_AcantidadTotal.setText(valor_Total.toString());


                }

                else {
                    Ap_lateral.setDisable(true);
                    txt_PNombre.setText(trabajador_seleccion.getNombre());
                    txt_PApellidos.setText(trabajador_seleccion.getApellido_paterno() + " " + trabajador_seleccion.getApellido_materno());
                    panel_prestamo.toFront();
                }

            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                // alert.setHeaderText("Results:");
                alert.setContentText("El Trabajador debe estar activo para el prestamo");
                alert.showAndWait();
            }
        }

    }


    public void guardar_prestamo(ActionEvent event) {
        conexion.altaPrestamo(trabajador_seleccion.getId(),Integer.parseInt(txt_Pcantidad.getText()));
    conexion.cerrarConexion();
    }

    public boolean checkNumeric(String value)    {
        String number=value.replaceAll("\\s+","");
        for(int j = 0 ; j<number.length();j++){
            if(!(((int)number.charAt(j)>=47 && (int)number.charAt(j)<=57)))
            {
                return false;
            }
        }
        return true;
    }

    public void mandar_prestamo(ActionEvent event) {
        Ap_lateral.setDisable(true);
        txt_PNombre.setText(trabajador_seleccion.getNombre());
        txt_PApellidos.setText(trabajador_seleccion.getApellido_paterno() + " " + trabajador_seleccion.getApellido_materno());
        panel_prestamo.toFront();
    }

    public void Hacer_abono(KeyEvent event) {
        switch (event.getCode()){
            case ENTER:
                double var1;
                double var;
                double totale;
                var1=Double.parseDouble(txt_AcantidadTotal.getText());
                var= Double.parseDouble(txt_AAbono.getText());
                totale=var1-var;
                txt_ATotal.setText(totale+" ");
                break;
        }

    }

    String foto;
    Image imagen;
    public void cambiar_foto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Foto de Trabajador");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            foto= file.toURI().toString();
            System.out.println(foto);
            System.out.println(file.getAbsolutePath());
            imagen = new Image(foto);
            txt_foto.setText(foto);
            foto_perfil.setImage(imagen);
        }

    }

    public void agregarNomina(ActionEvent event) {
        trabajador_seleccion=table_trabajador.getSelectionModel().getSelectedItem();

        if  (trabajador_seleccion!=null) {
            panel_nomina.toFront();
            txt_nombreN.setText(trabajador_seleccion.getNombre());

        }
    }
    int sueldo=0,
            dias=0,
            prestamo=0,
            ahorro=0,
            otros=0,
            funerarios=0,
            infonavit=0,
            total=0;

    public void guardar_nomina(ActionEvent event) {
        Nomina nomina= new Nomina();
        nomina.setId_trabajador(trabajador_seleccion.getId());
        nomina.setSueldo(sueldo);
        nomina.setPrestamo(prestamo);
        nomina.setAhorro(ahorro);
        nomina.setFunerarios(funerarios);
        nomina.setInfonavit(infonavit);
        nomina.setOtro(otros);
        nomina.setTotal(total);
        nomina.setFecha(LocalDate.now().toString());
        LocalDate date= LocalDate.now() ;
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
        nomina.setSemana(weekNumber);
        System.out.println(nomina.getFecha());
        System.out.println(weekNumber+"");

        conexion.Alta_nomina(nomina);
        int ahorrototal= (int) (trabajador_seleccion.getAhorro()+ahorro);
        Trabajador t = new Trabajador();
        t.setAhorro((double) ahorrototal);
        t.setId(trabajador_seleccion.getId());
        conexion.edita_trabajadorahorro(t);

        cerrarpanel(event);

    }
    public void cerrar_nomina(ActionEvent event) {
        cerrarpanel(event);
    }
    public void agregar_monto(KeyEvent event) {
        switch (event.getCode()){
            case ENTER:
                if (txt_diasN.getLength()==0){
                    dias=0;
                }
                else if (txt_sueldoN.getLength()==0){
                    sueldo=0;

                }
                else if (txt_prestamoN.getLength()==0){
                    prestamo=0;
                }
                else if (txt_ahorroN.getLength()==0){
                    ahorro=0;
                }
                else if (txt_funerarios.getLength()==0){
                    funerarios=0;
                }
                else if (txt_infonavitN.getLength()==0){
                    infonavit=0;
                }
                else if (txt_otroN.getLength()==0){
                    otros=0;
                }


                    sueldo=Integer.parseInt(txt_sueldoN.getText());
                    dias = Integer.parseInt(txt_diasN.getText());
                    prestamo=Integer.parseInt(txt_prestamoN.getText());
                    ahorro=Integer.parseInt(txt_ahorroN.getText());
                    funerarios=Integer.parseInt(txt_funerarios.getText());
                    infonavit=Integer.parseInt(txt_infonavitN.getText());
                    otros=Integer.parseInt(txt_otroN.getText());
                    System.out.println(dias + "");
                    total = sueldo * dias;
                    total = total - ahorro;
                    total = total - prestamo;
                    total = total - funerarios;
                    total = total - infonavit;
                    total = total - otros;
                    txt_totalN.setText(total + "");

                break;
        }
    }
    public void cantidad_numeros(KeyEvent event) {
        String character=event.getCharacter();


        if(!checkNumeric(character)){
            event.consume();
        }
        else{

        }


    }

    public void realizar_abono(ActionEvent event) {
    }
}

