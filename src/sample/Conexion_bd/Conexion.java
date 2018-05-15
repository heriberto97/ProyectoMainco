package sample.Conexion_bd;

import java.sql.*;

public class Conexion {

    String url = "jdbc:mysql://170.180.4.81:3306/mainco";
    String user = "maincoavengers";
    String pass = "123456789";
    Connection conectar;

    //METODO PARA HACER LA CONEXION
    public Connection conecta() {
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
            //JOptionPane.showMessageDialog(null,e.getMessage());
            System.out.println(e.getMessage());
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
            return res;
        }
        catch  (SQLException e)
        {
            System.out.println(e);
            return null;
        }

    }

    //METODOS PARA INSERTAR
    //public boolean AltaUsuarios() {
      //  String query1 = "insert into choferes (nombre,apellido_paterno,apellido_materno,telefono,direccion,created_at) values ('"+c.getNombre()+"','"+c.getApellido_p()+"','"+c.getApellido_m()+"','"+c.getTelefono()+"','"+c.getDireccion()+"',now())";
        //return consulta_insertar(query1);
   // }

    //METODOS PARA CONSULTAR

    //METODOS PARA MODIFICAR


}
