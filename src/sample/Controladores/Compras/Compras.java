package sample.Controladores.Compras;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sample.Conexion_bd.Conexion;
import sample.objetos.Compras.Compra;

import javax.swing.table.TableColumn;
import javax.swing.text.TableView;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Compras implements Initializable {

    // - - - - - - - - - - Ejecutar al Iniciar la ventana
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        llenartablas();
    }

    private Conexion c = new Conexion();
    // en un objeto, se usan posiciones reales ej. si la tabla lleva 8 columnas, el objeto lleva 8 posiciones
    public Object compras[] = new Object[8];
    final ObservableList<Compra> lista_compras = FXCollections.observableArrayList();

    @FXML
    void llenartablas(){
        try {
            // mostrar los datos de las compras en la tabla principal
            ResultSet res = c.mostrarSql(c.mostrarcompras());
            while (res.next()) {
                for (int i = 0; i <= 7; i++) {
                    // aqui llenamos el objeto renglón por renglón después de los titulos de las columnas, por eso el "i+1"
                    compras[i] = res.getObject(i + 1);
                }
            }
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }
}
