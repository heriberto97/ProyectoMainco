package sample.Conexion_bd;

import sample.objetos.Trabajador;
import sample.objetos.Usuario;

import java.sql.*;

public class Conexion {


    Connection conectar;

    //METODO PARA HACER LA CONEXION
    public Connection conecta() {
        String url = "jdbc:mysql://107.180.4.81:3306/mainco";
        String user = "maincoavengers";
        String pass = "123456789";
        try
        {
            conectar = DriverManager.getConnection(url, user,pass);
            System.out.println("Usted está conectado");
            return conectar;

        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("No logró conectar");
            conectar= null;
        }
        return null;
    }
//METODO PARA CERRAR LA CONEXION
    public void cerrarConexion(){

        try
        {
            conectar.close();
        }catch (SQLException e)
        {
            System.out.println(e);
        }
    }
//METODO GLOBAL PARA INSERTAR
    public boolean consulta_insertar(String consulta){
        try{
            String query1=consulta;
            PreparedStatement s = this.conecta().prepareStatement(query1);
            s.executeUpdate(query1);
            System.out.println("estamos insertando ...");
            s.close();
            return true;
        }
        catch(SQLException e){

            System.out.println(e);
            return false;
        }
    }

    //METODO  GLOBAL PARA MODIFICAR

    public int consulta_modificar(String consulta){
        try
        {
            String query1=consulta;
            PreparedStatement s = this.conecta().prepareStatement(query1);
            s.executeUpdate(query1);
            System.out.println("Modificando ...");
            s.close();
            return 1;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return 0;
        }
    }
//METODO GLOBAL PARA MOSTRAR CONSULTAS
    public ResultSet mostrarSql(String consulta){

        try
        {
            PreparedStatement us = this.conecta().prepareStatement(consulta);
            ResultSet res = us.executeQuery();
           // us.close();
            return res;
        }
        catch  (SQLException e)
        {
            System.out.println(e);
            return null;
        }

    }

    //METODOS PARA INSERTAR
    public boolean AltaUsuarios(Usuario u) {
       String query1 = "insert into usuarios (usuario,nombre,apellido_paterno,apellido_materno,contrasena,tipo) values ('"+u.getUsuario()+"','"+u.getNombre()+"','"+u.getApellido_p()+"','"+u.getApellido_m()+"','"+u.getContrasena()+"','"+u.getTipo_usuario()+"')";
        return consulta_insertar(query1);
    }

    public boolean AltaTrabjador(Trabajador t){
        String consulta="insert into trabajadores(nombre,apellido_paterno,apellido_materno,rfc,solicitud_empleo) values ('" + t.getNombre()+"','"+t.getApellido_paterno()+"','"+t.getApellido_materno()+"','"+t.getRfc()+"','"+t.getSolicitud_empleo()+"')";
        return consulta_insertar(consulta);
    }

    //METODOS PARA CONSULTAR

    public String verTrabajadores()  {

        String consulta="select * from trabajadores";
        return consulta;
    }

    public String verTrabajos(){
        String trabajos="select * from trabajos";
        return trabajos;
    }

    public String verTrabajosOrdenCompra(){
        String trabajos="select * from trabajos where orden_compra";
        return trabajos;
    }

    public String datosusuario(String usuario,String contrasena){
        String sql= "Select * from usuarios where usuario='"+ usuario+"' and contrasena='"+contrasena+"';";
        return sql;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - Métodos de Compras
    public String mostrarcompras(){
        String sql = "Select proveedores.nombre, adeudos.adeudo, adeudos.fecha_compra, adeudos.fecha_limite, adeudos.factura, adeudos.cotizacion, adeudos.orden_compra, adeudos.cantidad_restante from adeudos inner join proveedores on proveedores.id = adeudos.proveedor;";
        return sql;
    }

    //METODOS PARA MODIFICAR


}
