package sample.Controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.Conexion_bd.Conexion;
import sample.Controladores.Trabajador.Trabajadores_Alta;
import sample.objetos.Empresa;
import sample.objetos.Objeto_General;
import sample.objetos.Trabajador;
import sample.objetos.Trabajo;

import javax.swing.text.LabelView;
import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class Trabajos implements Initializable {


    @FXML ListView lv_varios,lv_trabajos;
    @FXML ComboBox cb_orden,cb_empresas;
    @FXML Button btn_nuevotrabajo;
    @FXML ToggleButton tb_urgente;
    @FXML TextField txt_filtro;
    @FXML Label lbl_ordencompra,lbl_cotizacion,lbl_factura;
    @FXML TableColumn tc_id,tc_producto,tc_notas,tc_pzsTotales,tc_pzsRestantes,tc_fechaInicio,tc_fechaFinal,tc_ordenCompra,tc_cotizacion,tc_factura;

    Conexion c= new Conexion();
    private Stage ventana_nuevo_trabajo = new Stage();

    @Override public void initialize(URL location, ResourceBundle resources) {
        inicio();
        lv_trabajos.setItems(getTrabajos());
        cb_empresas.setItems(getEmpresas());
    }

    private void inicio() {

    }

    public void opciones() {
        ObservableList<String> items1 = FXCollections.observableArrayList();
        items1.addAll("OrdenDeCompra", "Cotizacion", "Factura","Empresa");
        cb_orden.setItems(items1);
    }

    public  ObservableList<Trabajo> getTrabajos(){
        ObservableList<Trabajo> trabajos= FXCollections.observableArrayList();

        try {
            ResultSet trabajosResult= c.mostrarSql(c.verTrabajos());
            while (trabajosResult.next()) {

                for (int i = 0; i <1; i++) {

                    Trabajo trabajo= new Trabajo(
                            Integer.parseInt(trabajosResult.getObject(1).toString())
                    );
                    trabajos.add(trabajo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trabajos;
    }

    public ObservableList<Empresa> getEmpresas(){

        ObservableList<Empresa> empresas = FXCollections.observableArrayList();

        try {
            ResultSet empresasResult= c.mostrarSql(c.verTrabajos());
            while (empresasResult.next()) {

                for (int i = 0; i <1; i++) {

                    Empresa empresa= new Empresa();
                    empresa.setId(empresasResult.getInt(1));
                    empresa.setNombre(empresasResult.getString(2));
                    empresa.setTelefono(empresasResult.getString(3));
                    empresa.setDireccion(empresasResult.getString(4));
                    empresa.setCorreo(empresasResult.getString(5));
                    empresas.add(empresa);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empresas;

    }

    public  ObservableList<Objeto_General> getObjetoGeneral(){
        ObservableList<Objeto_General> objetos= FXCollections.observableArrayList();

        try {
            ResultSet trabajosResult= c.mostrarSql(c.verConsutaTrabajos());
            while (trabajosResult.next()) {

                for (int i = 0; i <1; i++) {

                    Objeto_General trabajo= new Objeto_General();
                    trabajo.setId(trabajosResult.getInt(1));
                    trabajo.setProducto(trabajosResult.getString(2));
                    trabajo.setNotas(trabajosResult.getString(3));
                    trabajo.setPzs_totales(trabajosResult.getInt(4));
                    trabajo.setPzs_restantes(trabajosResult.getInt(5));
                    trabajo.setFecha_inicio(trabajosResult.getString(6));
                    trabajo.setFecha_final(trabajosResult.getString(7));
                    trabajo.setNumero_orden(trabajosResult.getString(8));
                    trabajo.setNumero_cotizacion(trabajosResult.getString(9));
                    trabajo.setNumero_factura(trabajosResult.getString(10));
                    objetos.add(trabajo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return objetos;
    }

    public void agregar_trabajo(Event event){
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Tipo_Trabajo.fxml"));
            Parent abrir = fxmlLoader.load();
            if (ventana_nuevo_trabajo.getScene() == null) {
                ventana_nuevo_trabajo.setTitle("Maquinados industriales - Trabajos");
                //  ventana_nuevo_trabajo.initStyle(StageStyle.UNDECORATED);
                ventana_nuevo_trabajo.setScene(new Scene(abrir));
                ventana_nuevo_trabajo.show();


                ventana_nuevo_trabajo.setOnCloseRequest(e -> {
                    ventana_nuevo_trabajo.setScene(null);
                });
            }
            else{
                ventana_nuevo_trabajo.requestFocus();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }


    }

    public ObservableList<Integer> getIdsTrabajadores(){
        ObservableList<Trabajo> trabajos2 = getTrabajos();
        ObservableList<Integer> ids = FXCollections.observableArrayList();
        for (int i = 0; i < trabajos2.size(); i++) {
            ids.add(trabajos2.get(i).getId());
        }
        return ids;
    }

}
