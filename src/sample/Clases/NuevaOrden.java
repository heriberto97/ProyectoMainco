package sample.Clases;

import javafx.scene.control.Alert;
import sample.objetos.Empresa;

public class NuevaOrden {



    public void guardar(){

        try {
            if (txt_nombre.getText().isEmpty()||txt_direccion.getText().isEmpty()||txt_telefono.getText().isEmpty()||txt_correo.getText().isEmpty())
            {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Maquinados industriales");
                alerta.setHeaderText(null);
                alerta.setContentText("¡Completa los campos!");
                alerta.showAndWait();
            }
            else {
                Empresa empresa = new Empresa(txt_nombre.getText(),txt_direccion.getText(),txt_telefono.getText(),txt_correo.getText());
                c.AltaEmpresa(empresa);
                c.cerrarConexion();
                txt_correo.clear();
                txt_telefono.clear();
                txt_direccion.clear();
                txt_nombre.clear();
            }
        }
        catch (Exception ex)
        {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Revisa tu conexion");
            alerta.setHeaderText("¡Error de servidor!");
            alerta.setContentText("Algo esta fallando");
            alerta.showAndWait();
        }

    }

}
