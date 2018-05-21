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
    //consultas
    public boolean AltaTrabjador(Trabajador t){
        String consulta="insert into trabajadores(nombre,apellido_paterno,apellido_materno,rfc,solicitud_empleo) values ('" + t.getNombre()+"','"+t.getApellido_paterno()+"','"+t.getApellido_materno()+"','"+t.getRfc()+"','"+t.getSolicitud_empleo()+"')";
        return consulta_insertar(consulta);
    }

    //METODOS PARA CONSULTAR

    public String verTrabajadores()  {

        String consulta="select * from trabajadores";
        return consulta;
    }

    public String verTrabajos()  {

        String consulta="select * from trabajos";
        return consulta;
    }

    public String datosusuario(String usuario,String contrasena){
        String sql= "Select * from usuarios where usuario='"+ usuario+"' and contrasena='"+contrasena+"';";
        return sql;
    }

    public String empresascombobox(){
        String sql= "select nombre from mainco.empresas;";
        return  sql;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - Métodos de Compras
    // - - - Muestra todas las compras
    public String mostrarcompras(){
        String sql = "Select  aoc.numero_orden_compra, \n" +
                "\t\t    ac.numero_cotizacion, \n" +
                "        af.numero_factura, \n" +
                "        p.nombre_proveedor, \n" +
                "        a.adeudo, \n" +
                "        a.fecha_compra, \n" +
                "        a.fecha_limite, \n" +
                "        a.cantidad_restante \n"+
                "\tfrom adeudos a \n" +
                "    left join adeudo_orden_compra aoc \n" +
                "\t\ton a.orden_compra = aoc.id \n" +
                "\tleft join adeudo_cotizacion ac \n" +
                "\t\ton a.cotizacion = ac.id \n" +
                "\tleft join adeudo_factura af \n" +
                "\t\ton a.factura = af.id \n" +
                "\tleft join proveedores p \n" +
                "\t\ton a.proveedor = p.id;";
        return sql;
    }
    // - - - Muestra todas las compras por pagar los siguientes 30 dias
    public String mostrar_compras_a_pagar(){
        String sql = "Select date_format(a.fecha_limite, '%Y/%m/%d/') as fecha_limite, p.nombre_proveedor, a.cantidad_restante from adeudos a \n" +
                " left join proveedores p \n" +
                "  on a.proveedor = p.id \n" +
                " where a.fecha_limite between current_date() and date_add(current_date(),interval 30 day);";
        return sql;
    }


    //METODOS PARA MODIFICAR


}
