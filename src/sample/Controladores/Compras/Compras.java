package sample.Controladores.Compras;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sample.Conexion_bd.Conexion;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Compras implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        llenartablas();
    }

    private Conexion c = new Conexion();
    public Object compras[] = new Object[8];
    @FXML
    void llenartablas(){
        try {
            // mostrar los datos de las compras en la tabla principal
            ResultSet res = c.mostrarSql(c.mostrarcompras());

            while (res.next()) {
                for (int i = 0; i <= 7; i++) {
                    compras[i] = res.getObject(i + 1);
                }
            }
            String[] proveedores = new String[compras.length];
            proveedores[0] = compras[0].toString();
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }
}
