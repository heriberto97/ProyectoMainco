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

        String pass = "";
        //String pass = "root";
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
            String query1 = consulta;
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
         String sql = "select cantidad from inventario_oficina;";
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
    // - - - - - - - - - - - - - - - - - - - - - - - - - REGISTROS
    public boolean registrar_compra(Compra c){
        String sql = " Insert into adeudos (proveedor, fecha_compra, fecha_limite, adeudo, ";

        if (c.getFactura() != null){ sql = sql + "factura, "; }
        if (c.getCotizacion() != null){ sql = sql + "cotizacion, "; }
        if (c.getOrden_compra() != null){ sql = sql + "orden_compra, "; }

        sql = sql + "cantidad_restante, notas) values('" + Integer.parseInt(c.getProveedor()) +"', '2018-06-01', '2018-06-10', '" + c.getAdeudo() + "', ";

        if (c.getFactura() != null){ sql = sql + "'" + Integer.parseInt(c.getFactura()) + "', "; }
        if (c.getCotizacion() != null){ sql = sql + "'" + Integer.parseInt(c.getCotizacion()) + "', "; }
        if (c.getOrden_compra() != null){ sql = sql + "'" + Integer.parseInt(c.getOrden_compra()) + "', "; }

        sql = sql + "'" + c.getCantidad_restante() + "', '" + c.getNotas() +"');";
        return consulta_insertar(sql);
    }
    public boolean registrar_cotizacion(Cotizacion c){
        String sql = "Insert into `adeudo_cotizacion` (`numero_cotizacion`";
        if (c.getEsquema() != null){ sql = sql + ", `esquema_cotizacion`"; }
        sql = sql + ") VALUES ('" + c.getNumero_cotizacion() + "'";
        if (c.getEsquema() != null){ sql = sql + ", '" + c.getEsquema() +"'"; }
        sql = sql + ");";
        return consulta_insertar(sql);
    }
    public boolean registrar_factura(Factura f){
        String sql = "Insert into `adeudo_factura` (`numero_factura`";
        if (f.getEsquema_factura() != null){ sql = sql + ", `esquema_factura`"; }
        sql = sql + ") VALUES ('" + f.getNumero_factura() + "'";
        if (f.getEsquema_factura() != null){ sql = sql + ", '" + f.getEsquema_factura() +"'"; }
        sql = sql + ");";
        return consulta_insertar(sql);
    }
    public boolean registrar_orden_compra(Orden_compra oc){
        String sql = "Insert into `adeudo_orden_compra` (`numero_orden_compra`";
        if (oc.getEsquema_orden_compra() != null){ sql = sql + ", `esquema_orden_compra`"; }
        sql = sql + ") VALUES ('" + oc.getNumero_orden_compra() + "'";
        if (oc.getEsquema_orden_compra() != null){ sql = sql + ", '" + oc.getEsquema_orden_compra() +"'"; }
        sql = sql + ");";
        return consulta_insertar(sql);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - ACTUALIZACIONES
    // - - - Realizar pago
    public int realizar_pago(int registro){
        String sql = "UPDATE `adeudos` SET `cantidad_restante` = '0' WHERE (`reg` = '" + registro + "');\n";
        return consulta_modificar(sql);
    }
    // - - - Realizar abono
    public int realizar_abono(int registro, Double cantidad){
        String sql = "UPDATE `adeudos` SET `cantidad_restante` = '" + cantidad +"' WHERE (`reg` = '" + registro + "');\n";
        return consulta_modificar(sql);
    }
    public boolean actualizar_compra(Compra c, String nueva_factura, String nueva_cotizacion, String nueva_orden_compra){
        String sql = " Update adeudos  ";

        if (c.getFactura() != nueva_factura){ sql = sql + "set 'factura' = '" + Integer.parseInt(c.getProveedor()) + "'"; }
        if (c.getCotizacion() != nueva_cotizacion){ sql = sql + ", set 'cotizacion' = '" + Integer.parseInt(c.getCotizacion()) + "'"; }
        if (c.getOrden_compra() != nueva_orden_compra){ sql = sql + ", set 'orden_compra' = '" + Integer.parseInt(c.getOrden_compra()) + "'"; }

        sql = sql + "'" + c.getCantidad_restante() + "', '" + c.getNotas() +"');";
        return consulta_insertar(sql);
    }
    public boolean actualizar_cotizacion(Cotizacion c){
        String sql = "Insert into `adeudo_cotizacion` (`numero_cotizacion`";
        if (c.getEsquema() != null){ sql = sql + ", `esquema_cotizacion`"; }
        sql = sql + ") VALUES ('" + c.getNumero_cotizacion() + "'";
        if (c.getEsquema() != null){ sql = sql + ", '" + c.getEsquema() +"'"; }
        sql = sql + ");";
        return consulta_insertar(sql);
    }
    public boolean actualizar_factura(Factura f){
        String sql = "Insert into `adeudo_factura` (`numero_factura`";
        if (f.getEsquema_factura() != null){ sql = sql + ", `esquema_factura`"; }
        sql = sql + ") VALUES ('" + f.getNumero_factura() + "'";
        if (f.getEsquema_factura() != null){ sql = sql + ", '" + f.getEsquema_factura() +"'"; }
        sql = sql + ");";
        return consulta_insertar(sql);
    }
    public boolean actualizar_orden_compra(Orden_compra oc){
        String sql = "Insert into `adeudo_orden_compra` (`numero_orden_compra`";
        if (oc.getEsquema_orden_compra() != null){ sql = sql + ", `esquema_orden_compra`"; }
        sql = sql + ") VALUES ('" + oc.getNumero_orden_compra() + "'";
        if (oc.getEsquema_orden_compra() != null){ sql = sql + ", '" + oc.getEsquema_orden_compra() +"'"; }
        sql = sql + ");";
        return consulta_insertar(sql);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - CONSULTAS
    // - - - Muestra todas las compras
    public String mostrarcompras(){
        String sql = "Select a.reg,  " +
                "       aoc.numero_orden_compra, \n" +
                "\t\tac.numero_cotizacion, \n" +
                "        af.numero_factura, \n" +
                "        p.id as id_proveedor, \n" +
                "        p.nombre_proveedor,\n" +
                "        a.adeudo,\n" +
                "        a.fecha_compra,\n" +
                "        date_format(a.fecha_limite, '%Y/%m/%d') as fecha_limite,\n" +
                "        a.cantidad_restante,\n" +
                "        a.notas \n" +
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
    // - - - Muestra todas las compras por pagar los siguientes 10 dias
    public String mostrar_compras_a_pagar(){
        String sql = "Select  a.reg, \n" +
                "\t\taoc.numero_orden_compra, \n" +
                "\t\tac.numero_cotizacion, \n" +
                "        af.numero_factura, \n" +
                "        p.id as id_proveedor,\n" +
                "        p.nombre_proveedor,\n" +
                "        a.adeudo,\n" +
                "        date_format(a.fecha_compra, '%Y/%m/%d') as fecha_compra,\n" +
                "        date_format(a.fecha_limite, '%Y/%m/%d') as fecha_limite,\n" +
                "        a.cantidad_restante,\n" +
                "        a.notas \n" +
                "        from adeudos a\n" +
                "\tleft join adeudo_orden_compra aoc \n" +
                "\t\ton a.orden_compra = aoc.id\n" +
                "\tleft join adeudo_cotizacion ac\n" +
                "\t\ton a.cotizacion = ac.id\n" +
                "\tleft join adeudo_factura af\n" +
                "\t\ton a.factura = af.id\n" +
                "\tleft join proveedores p\n" +
                "\t\ton a.proveedor = p.id\n" +
                "\twhere a.fecha_limite between current_date() and date_add(current_date(),interval 10 day)\n" +
                "    and a.cantidad_restante > 0\n" +
                "    order by fecha_limite asc;";
        return sql;
    }
    // - - - Muestra todas las compras que tienen documentos pendientes
    public String mostrar_compras_docum_faltantes(){
        String sql = "Select a.reg, " +
                "    aoc.numero_orden_compra,\n" +
                "\t\taf.numero_factura, \n" +
                "\t\tac.numero_cotizacion,\n" +
                "    p.id as id_proveedor, \n" +
                "\t\tp.nombre_proveedor,\n" +
                "\t\ta.adeudo, \n" +
                "\t\tdate_format(a.fecha_compra, '%Y/%m/%d') as fecha_compra, \n" +
                "\t\tdate_format(a.fecha_limite, '%Y/%m/%d') as fecha_limite,\n" +
                "\t\ta.cantidad_restante, \n" +
                "\t\ta.notas " +
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
                "       p.rfc, \n" +
                "       p.notas \n" +
                " from adeudos a\n" +
                "\tinner join proveedores p\n" +
                "\t\ton a.proveedor = p.id\n" +
                "\tinner join proveedores_limite pl\n" +
                "\t\ton pl.proveedor = p.id\n" +
                "\twhere a.cantidad_restante > 0\n" +
                "\tgroup by a.proveedor \n" +
                "\torder by fecha_compra asc;";
        return sql;
    }
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
    // - - - Muestra los datos de X Proveedor
    public String mostrar_compras_proveedor(int id){
        String sql = "Select  aoc.numero_orden_compra, \n" +
                "\t\tac.numero_cotizacion, \n" +
                "        af.numero_factura, \n" +
                "        p.id as id_proveedor, \n" +
                "        p.nombre_proveedor, \n" +
                "        a.notas, \n" +
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
    // - - - Mostrar la última factura registrada
    public String ultima_factura(){
        String sql = "select id as factura from adeudo_factura\n" +
                "\torder by id desc\n" +
                "\tlimit 1;";
        return sql;
    }
    // - - - Mostrar la última cotizacion registrada
    public String ultima_cotizacion(){
        String sql = "select id as cotizacion from adeudo_cotizacion\n" +
                "\torder by id desc\n" +
                "\tlimit 1;";
        return sql;
    }
    // - - - Mostrar la última orden de compra registrada
    public String ultima_orden_compra(){
        String sql = "select id as orden_compra from adeudo_orden_compra\n" +
                "\torder by id desc\n" +
                "\tlimit 1;";
        return sql;
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - Fin de los Métodos de Compras





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
