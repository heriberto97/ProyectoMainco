package sample.Controladores.Trabajador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sample.Conexion_bd.Conexion;
import sample.objetos.Traabajadores.Falta;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Traabajadores_verFaltas implements Initializable {

    @FXML
    SplitPane panel_verFaltas;

    @FXML
    TableView<Falta> lv_verTrabajadores;

    @FXML
    PieChart Pc_faltasTotales,Pc_retardosTotales;


    ObservableList<Falta> list = FXCollections.observableList(getFaltasTrabajador());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lv_verTrabajadores.setItems(list);

        TableColumn nombre= new TableColumn("nombre");
        TableColumn faltas= new TableColumn("faltas mensuales");

        nombre.setCellValueFactory(
                new PropertyValueFactory<Falta,String>("nombre_completo")
        );
        faltas.setCellValueFactory(
                new PropertyValueFactory<Falta,String>("conteoFaltas")
        );
        lv_verTrabajadores.maxWidthProperty().setValue(200);
        lv_verTrabajadores.getColumns().addAll(nombre,faltas);
        lv_verTrabajadores.setItems(list);
        conexion.cerrarConexion();

        obtenerGraficoFaltas();
         obtenerGraficoRetartos();
        }

        public void obtenerGraficoFaltas(){

            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(getFaltas());
            Pc_faltasTotales.setData(pieChartData);
            Pc_faltasTotales.setTitle("Faltas del mes");

            final Label caption = new Label("");
            caption.setTextFill(Color.DARKORANGE);
            caption.setStyle("-fx-font: 24 arial;");

            for (final PieChart.Data data : Pc_faltasTotales.getData()) {
                data.getNode().setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent event) {
                        Notifications noti = Notifications.create()
                                .title("")
                                .text(data.getPieValue()+" ")
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
                });
            }

        }

    public void obtenerGraficoRetartos(){

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(getFaltas());
        Pc_retardosTotales.setData(pieChartData);
        Pc_retardosTotales.setTitle("Retardos del mes");

        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");

        for (final PieChart.Data data : Pc_retardosTotales.getData()) {
            data.getNode().setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent event) {
                    Notifications noti = Notifications.create()
                            .title("")
                            .text(data.getPieValue()+" ")
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
            });
        }

    }


    Conexion conexion = new Conexion();
    public List<Falta> getFaltasTrabajador(){
        List<Falta> faltas = new ArrayList<>();
        try{
            ResultSet trabajadorresResult= conexion.mostrarSql(conexion.verFaltasPorTrabajador());
            while (trabajadorresResult.next()) {

                for (int i = 0; i < 1; i++) {

                    String nombreCompleto=trabajadorresResult.getObject(0).toString()+" "+
                            trabajadorresResult.getObject(1).toString();
                    Falta nueva= new Falta();
                    nueva.setNombre_completo(nombreCompleto);
                    nueva.setConteoFaltas(Integer.parseInt(trabajadorresResult.getObject(2).toString()));
                    faltas.add(nueva);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return faltas;
    }


    public List<PieChart.Data> getFaltas(){
        List<PieChart.Data> faltas= new ArrayList<>();
        try{
            ResultSet res= conexion.mostrarSql(conexion.verFaltasTotales());
            while (res.next()) {
                for (int i = 0; i <1 ; i++) {
                    faltas.add(new PieChart.Data(res.getObject(2).toString(),
                            Double.parseDouble(res.getObject(1).toString())));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return faltas;
    }

    public List<PieChart.Data> getRetardos(){
        List<PieChart.Data> faltas= new ArrayList<>();
        try{
            ResultSet res= conexion.mostrarSql(conexion.verFaltasTotales());
            while (res.next()) {
                for (int i = 0; i <1 ; i++) {
                    faltas.add(new PieChart.Data(res.getObject(2).toString(),
                            Double.parseDouble(res.getObject(1).toString())));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return faltas;
    }
}
