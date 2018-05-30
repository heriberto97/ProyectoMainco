package sample.Conexion_bd;

import sample.objetos.Compras.Compra;
import sample.objetos.Compras.Cotizacion;
import sample.objetos.Compras.Factura;
import sample.objetos.Compras.Orden_compra;
import sample.objetos.Inventario_oficina;
import sample.objetos.Trabajador;
import sample.objetos.Usuario;

import java.sql.*;

public class Conexion {


    Connection conectar;

    //METODO PARA HACER LA CONEXION
    public Connection conecta() {
        //String url = "jdbc:mysql://192.168.1.65:3306/mainco";
        String url = "jdbc:mysql://localhost:3306/mainco";

        //String user = "Mainco";
        String user = "root";

        //String pass = "1234";
        String pass = "root";
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


    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - Métodos de Compras
    public boolean registrar_compra(Compra c){
        String sql = " Insert into adeudos (proveedor, fecha_compra, fecha_limite, adeudo, factura, cotizacion, orden_compra, cantidad_restante) values(";
        if (c.getProveedor() != null) { sql = sql + "'" + c.getProveedor() + "',"; }

        sql = sql + "'"+ c.getFecha_compra() +"'," + " '"+ c.getFecha_limite() +"'," + "'" + c.getAdeudo() + "',";

        if (c.getFactura() != null){ sql = sql + " '" + c.getFactura() + "',"; }
        if (c.getCotizacion() != null){ sql = sql + " '" + c.getCotizacion() + "',"; }
        if (c.getOrden_compra() != null){ sql = sql + " '" + c.getOrden_compra() + "',"; }

        sql = sql + " '" + c.getCantidad_restante() + "');";
        return consulta_insertar(sql);
    }
    public boolean registrar_cotizacion(Cotizacion c){
        String sql = "";
        return consulta_insertar(sql);
    }
    public boolean registrar_factura(Factura f){
        String sql = "";
        return consulta_insertar(sql);
    }
    public boolean registrar_orden_compra(Orden_compra oc){
        String sql = "";
        return consulta_insertar(sql);
    }


    //

    public boolean PonerFalta(String nombre_completo){
        String query= "";
        return consulta_insertar(query);
    }



    public String verTrabajos()  {

        String consulta="select * from trabajos";
        return consulta;
    }
    public String verTrabajosConOrdenCompra()
    {
        String sql ="SELECT * FROM mainco.trabajos where orden_compra is not null;";
        return sql;
    }

    public String datosusuario(String usuario,String contrasena){
        String sql= "Select * from usuarios where usuario='"+ usuario+"' and contrasena='"+contrasena+"';";
        return sql;
    }

    public String empresascombobox(){
        String sql= "select nombre from mainco.empresas;";
        return  sql;
    }

    //----------------------------------------Metodos para inventario----------------------------------------------------------------------------------------

    public String verarticulosoficina()
    {
        String sql = "Select * from inventario_oficina;";
        return sql;
    }

    public boolean AltaArticulos(Inventario_oficina Articulo) {
        String query1 = "insert into inventario_oficina (cantidad,descripcion,estado) values ('"+Articulo.getCantidad()+"','"+Articulo.getDescripcion()+"','"+Articulo.getEstado()+"')";
        return consulta_insertar(query1);
    }

    public int modificarArticulo(Inventario_oficina articulo){
        String sql="update inventario_oficina set descripcion='"+articulo.getDescripcion()+"', cantidad ='"+articulo.getCantidad()+"' where `id`='"+articulo.getId()+"';";
        int valor=consulta_modificar(sql);
        return valor;
    }

    public String datosalerta()
     {
         String sql = "select * from inventario_oficina;";
         return sql;
     }


    public int modificarestadoArticulo()
      {
               String sql = "update inventario_oficina set estado = 'Sin existencias' where cantidad = 0;";
               int valor=consulta_modificar(sql);
               return valor;
      }
      public int modificarestadoArticulo2()
      {
              String sql = "update inventario_oficina set estado = 'En existencias' where cantidad >0;";
              int valor=consulta_modificar(sql);
               return valor;
      }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - Métodos de Compras
    // - - - Muestra todas las compras
    public String mostrarcompras(){
        String sql = "Select a.reg,  " +
                "       aoc.numero_orden_compra, \n" +
                "\t\tac.numero_cotizacion, \n" +
                "        af.numero_factura, \n" +
                "        p.nombre_proveedor,\n" +
                "        a.adeudo,\n" +
                "        a.fecha_compra,\n" +
                "        date_format(a.fecha_limite, '%Y/%m/%d') as fecha_limite,\n" +
                "        a.cantidad_restante\n" +
                "\tfrom adeudos a \n" +
                "    left join adeudo_orden_compra aoc \n" +
                "\t\ton a.orden_compra = aoc.id\n" +
                "\tleft join adeudo_cotizacion ac\n" +
                "\t\ton a.cotizacion = ac.id\n" +
                "\tleft join adeudo_factura af\n" +
                "\t\ton a.factura = af.id\n" +
                "\tleft join proveedores p\n" +
                "\t\ton a.proveedor = p.id;";
        return sql;
    }
    // - - - Muestra todas las compras por pagar los siguientes 30 dias
    public String mostrar_compras_a_pagar(){
        String sql = "Select  a.reg," +
                "    date_format(a.fecha_limite, '%Y/%m/%d') as fecha_limite, " +
                "    p.nombre_proveedor, " +
                "    a.cantidad_restante " +
                " from adeudos a\n" +
                "\tleft join proveedores p\n" +
                "\t\ton a.proveedor = p.id\n" +
                "\twhere a.fecha_limite between current_date() and date_add(current_date(),interval 10 day) \n" +
                "    order by fecha_limite asc;";
        return sql;
    }
    // - - - Muestra todas las compras que tienen documentos pendientes
    public String mostrar_compras_docum_faltantes(){
        String sql = "Select a.reg, " +
                "    aoc.numero_orden_compra,\n" +
                "\t\taf.numero_factura, \n" +
                "\t\tac.numero_cotizacion,\n" +
                "\t\tp.nombre_proveedor,\n" +
                "\t\ta.adeudo, \n" +
                "\t\tdate_format(a.fecha_compra, '%Y/%m/%d') as fecha_compra, \n" +
                "\t\tdate_format(a.fecha_limite, '%Y/%m/%d') as fecha_limite,\n" +
                "\t\ta.cantidad_restante\n" +
                " from adeudos a\n" +
                "\tleft join adeudo_orden_compra aoc \n" +
                "\t\ton a.orden_compra = aoc.id\n" +
                "\tleft join adeudo_cotizacion ac\n" +
                "\t\ton a.cotizacion = ac.id\n" +
                "\tleft join adeudo_factura af\n" +
                "\t\ton a.factura = af.id\n" +
                "\tleft join proveedores p\n" +
                "\t\ton a.proveedor = p.id \n" +
                "\twhere a.orden_compra is null\n" +
                "\tor    a.factura is null \n" +
                "\tor    a.cotizacion is null;";
        return sql;
    }
    // - - - Muestra todos los proveedores
    public String mostrar_proveedores(){
        String sql = "Select p.id, " +
                "       p.nombre_proveedor,\n" +
                "       pl.dias as dias_limite,\n" +
                "       pl.credito,\n" +
                "       pl.credito - SUM(a.cantidad_restante) as credito_disponible,\n" +
                "       p.telefono,\n" +
                "       p.correo,\n" +
                "       p.rfc\n" +
                " from adeudos a\n" +
                "\tinner join proveedores p\n" +
                "\t\ton a.proveedor = p.id\n" +
                "\tinner join proveedores_limite pl\n" +
                "\t\ton pl.proveedor = p.id\n" +
                "\twhere a.cantidad_restante > 0\n" +
                "\tgroup by a.proveedor \n" +
                "\torder by fecha_compra asc;";
        return sql;
    };
    // - - - Muestra todos los proveedores y los días que dan para pagar
    public String mostrar_proveedores_limite(){
        String sql = "Select p.id, " +
                "     p.nombre_proveedor,\n" +
                "\t   pl.dias as dias_limite\n" +
                " from proveedores p\n" +
                "\tinner join proveedores_limite pl\n" +
                "\t\ton pl.proveedor = p.id;";
        return sql;
    };
    // - - - Muestra los datos de X Compra realizada
    public String mostrar_compras_proveedor(int id){
        String sql = "Select  aoc.numero_orden_compra, \n" +
                "\t\tac.numero_cotizacion, \n" +
                "        af.numero_factura, " +
                "        p.nombre_proveedor, " +
                "        a.reg, \n" +
                "        a.adeudo,\n" +
                "        a.fecha_compra,\n" +
                "        date_format(a.fecha_limite, '%Y/%m/%d') as fecha_limite,\n" +
                "        a.cantidad_restante\n" +
                "\tfrom adeudos a \n" +
                "    left join adeudo_orden_compra aoc \n" +
                "\t\ton a.orden_compra = aoc.id\n" +
                "\tleft join adeudo_cotizacion ac\n" +
                "\t\ton a.cotizacion = ac.id\n" +
                "\tleft join adeudo_factura af\n" +
                "\t\ton a.factura = af.id\n" +
                "\tleft join proveedores p\n" +
                "\t\ton a.proveedor = p.id\n" +
                "\twhere p.id = "+ id +"";
        return sql;
    }





    //metodos de Trabajadores
//consultas
    public boolean AltaTrabjador(Trabajador t){
        String consulta="insert into trabajadores(nombre,apellido_paterno,apellido_materno,rfc,solicitud_empleo,estado) values ('" + t.getNombre()+"','"+t.getApellido_paterno()+"','"+t.getApellido_materno()+"','"+t.getRfc()+"','"+t.getSolicitud_empleo()+"','activo')";
        return consulta_insertar(consulta);
    }

    //METODOS PARA CONSULTAR

    public String verTrabajadores()  {

        String consulta="select * from trabajadores where estado='Activo' or estado='Inactivo'";
        return consulta;
    }


    public String buscarTrabajadores(String text) {
        String consulta="select * from trabajadores where nombre like '%"+text+"%'";

                return consulta;

    }
}
