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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sample.Conexion_bd.Conexion;
import sample.objetos.Trabajador;

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
    ListView<Trabajador> lv_verTrabajadores;

    @FXML
    PieChart Pc_faltasTotales;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Trabajador> list = FXCollections.observableList(getTrabajos());
        lv_verTrabajadores.setItems(list);
        Callback<ListView<Trabajador>, ListCell<Trabajador>> factory = lv -> new ListCell<Trabajador>() {
            @Override
            protected void updateItem(Trabajador item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNombre());
            }

        };
        lv_verTrabajadores.setCellFactory(factory);

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(getFaltas());
            Pc_faltasTotales.setData(pieChartData);
            Pc_faltasTotales.setTitle("faltas totales");

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


    Conexion conexion = new Conexion();
    public List<Trabajador> getTrabajos(){
        List<Trabajador> trabajadores= new ArrayList<>();
        try{
            ResultSet trabajadorresResult= conexion.mostrarSql(conexion.verTrabajadores());
            while (trabajadorresResult.next()) {

                for (int i = 0; i < 1; i++) {

                    Trabajador trabajador= new Trabajador(Integer.parseInt(trabajadorresResult.getObject(1).toString()), trabajadorresResult.getObject(2).toString(), trabajadorresResult.getObject(3).toString(), trabajadorresResult.getObject(4).toString(), trabajadorresResult.getObject(5).toString());
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

}
