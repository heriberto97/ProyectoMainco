package sample.Conexion_bd;

import javafx.scene.control.Alert;
import sample.objetos.*;
import sample.objetos.Compras.*;
import sample.objetos.Traabajadores.Falta;

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
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Revisa tu conexion");
            alerta.setHeaderText("¡Error de servidor!");
            alerta.setContentText("Algo esta fallando");
            alerta.showAndWait();
//            System.out.println(e.getMessage());
//            System.out.println("No logró conectar");

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


    public String verClientes()  {

        String consulta="select * from representantes";
        return consulta;
    }

    public String InsertarTrabajos(String Nota, Integer PzTotales, Integer PzRestantes)
    {
        String consulta="INSERT INTO `mainco`.`trabajos` (`notas`, `piezas_totales`, `piezas_restantes`) VALUES ('hola', '50', '50');";
        return consulta;
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



    public String empresascombobox(){
        String sql= "select nombre from mainco.empresas;";
        return  sql;
    }

    //-----------------------------------------Metodos Trabajo--------------------------------------------------------
    public String veresquemas()
    {
        String sql = "Select * from esquemas;";
        return sql;
    }

    //----------------------------------------Metodos para inventario----------------------------------------------------------------------------------------

    //-----metodos para consultar
    public String ver_esquemas()
    {
        String sql = "select id, descripcion,ruta from esquemas;";
        return sql;
    }
    public String combomateriales()
    {
        String sql = "select * from materiales;";
        return sql;
    }
    public String comboempresas()
    {
        String sql = "select * from empresas;";
        return sql;
    }

    public String tablaproductos()
    {
        String sql = "select productos.id_producto as numero ,productos.descripcion as descripcion ,esquemas.ruta as ruta ,empresas.nombre as empresa from productos left join esquemas on productos.esquema = esquemas.id left join empresas on productos.empresa = empresas.id;";
        return sql;
    }

    public String datosusuario(String usuario,String contrasena){
        String sql= "Select * from usuarios where usuario='"+ usuario+"' and contrasena='"+contrasena+"';";
        return sql;
    }

    public String verarticulosoficina()
    {
        String sql = "Select * from inventario_oficina;";
        return sql;
    }

    public String datosalerta()
    {
        String sql = "select cantidad from inventario_oficina;";
        return sql;
    }

    public String buscar_numero_articulo(int numero_articulo)
    {
        String sql = "select * from inventario_oficina where id ='"+numero_articulo+"';";
        return sql;
    }

    public String buscar_descripcion_articulo(String descripcion)
    {
        String sql = "select * from inventario_oficina where descripcion ='"+descripcion+"';";
        return sql;
    }

    //metodos para insertar------------------------------------------------------------------------------------------------------------------------------------

    public boolean AltaArticulos(Inventario_oficina Articulo) {
        String query1 = "insert into inventario_oficina (cantidad,descripcion,estado) values ('"+Articulo.getCantidad()+"','"+Articulo.getDescripcion()+"','"+Articulo.getEstado()+"')";
        return consulta_insertar(query1);
    }

    public boolean AltaProductocamposobligatorios(producto p) {
        String query1 = "insert into productos (id_producto,descripcion,empresa) values ('"+p.getNumero_producto()+"','"+p.getDescripcion()+"','"+p.getEmpresa()+"')";
        return consulta_insertar(query1);
    }

    public boolean AltaProductocamposobligatoriosyesquema(producto p) {
        String query1 = "insert into productos (id_producto,descripcion,esquema,empresa) values ('"+p.getNumero_producto()+"','"+p.getDescripcion()+"','"+p.getRuta_imagen()+"','"+p.getEmpresa()+"')";
        return consulta_insertar(query1);
    }

    public boolean Altaesquema(Esquema e) {
        String query1 = "insert into esquemas (ruta,descripcion) values ('"+e.getRuta()+"','"+e.getDescripcion()+"')";
        return consulta_insertar(query1);
    }




    //metodos para modificar-----------------------------------------------------------------------------------------------------------

    public int modificarArticulo(Inventario_oficina articulo){
        String sql="update inventario_oficina set descripcion='"+articulo.getDescripcion()+"', cantidad ='"+articulo.getCantidad()+"' where `id`='"+articulo.getId()+"';";
        int valor=consulta_modificar(sql);
        return valor;
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
    public boolean registrar_compra(Compra c, int dias_limite){
        String sql = " Insert into adeudos (proveedor, fecha_compra, fecha_limite, adeudo, ";

        if (c.getFactura() != null){ sql = sql + "factura, "; }
        if (c.getCotizacion() != null){ sql = sql + "cotizacion, "; }
        if (c.getOrden_compra() != null){ sql = sql + "orden_compra, "; }

        sql = sql + "cantidad_restante, notas) values('" + Integer.parseInt(c.getProveedor()) +"', current_timestamp(), DATE_ADD(current_timestamp(), INTERVAL " + dias_limite +" DAY), '" + c.getAdeudo() + "', ";

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
    public boolean registrar_proveedor(Proveedor p){
        String sql = "Insert into proveedores(nombre_proveedor, telefono, correo, rfc, notas) " +
                "values ('" + p.getNombre() + "', '" + p.getTelefono() + "', '" + p.getCorreo() + "', '" + p.getRfc() + "', '" + p.getNotas() + "');\n";
        return consulta_insertar(sql);
    }
    public boolean registrar_limite_proveedor(Proveedor p){
        String sql = "Insert into proveedores_limite(proveedor, dias, credito)" +
                "values ('" + p.getId_proveedor() +"', '" + p.getDias_limite() + "', '" + p.getCredito() + "')";
        return consulta_insertar(sql);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - ACTUALIZACIONES
    // - - - Realizar pago
    public int realizar_pago(int registro, Double cantidad){
        String sql = "Insert into adeudo_pago (adeudo, pago, fecha) values('" + registro + "', '" + cantidad + "', current_timestamp());";
        return consulta_modificar(sql);
    }
    // - - - Realizar abono
    public int realizar_abono(int registro, Double cantidad){
        String sql = "Insert into adeudo_pago (adeudo, pago, fecha) values('" + registro + "', '" + cantidad +"', current_timestamp());\n";
        return consulta_modificar(sql);
    }
    // - - - Actualiza el pago de una compra
    public int actualizar_pago(int registro, Double cantidad){
        String sql = "UPDATE `adeudos` SET `cantidad_restante` = '" + cantidad +"' WHERE (`reg` = '" + registro + "');\n";
        return consulta_modificar(sql);
    }

    // - - - Actualiza una compra en específico
    public int actualizar_compra(Compra c){
        String sql = " Update adeudos set notas = '" + c.getNotas() +"' where reg = '" + c.getReg() + "';";
        return consulta_modificar(sql);
    }

    // - - - Actualiza un proveedor
    public int actualizar_proveedor(Proveedor p){
        String sql = " Update proveedores set nombre_proveedor = '" + p.getNombre() + "', telefono = '" + p.getTelefono() + "', correo = '" + p.getCorreo() +"', rfc = '" + p.getRfc() +"', notas = '" + p.getNotas() +"' where id = '" + p.getId_proveedor() + "';";
        return consulta_modificar(sql);
    }
    // - - - Actualiza los días de plazo y el crédito de un proveedor
    public int actualizar_extras_proveedor(Proveedor p){
        String sql = " Update proveedores_limite set dias = '" + p.getDias_limite() + "', credito = '" + p.getCredito() +"' where proveedor = '" + p.getId_proveedor() + "';";
        return consulta_modificar(sql);
    }

    // - - - Actualizar X factura
    public int actualizar_factura(Factura f){
        String sql = "Update adeudo_factura  set numero_factura = '" + f.getNumero_factura() + "', esquema_factura = '" + f.getEsquema_factura() +"' where id = '" + f.getId_factura() + "';";
        return consulta_modificar(sql);
    }
    // - - - Actualizar X cotizacion
    public int actualizar_cotizacion(Cotizacion c){
        String sql = "Update adeudo_cotizacion set numero_cotizacion = '" + c.getNumero_cotizacion() + "', esquema_cotizacion = '" + c.getEsquema() +"' where id = '" + c.getId_cotizacion() + "';";
        return consulta_modificar(sql);
    }
    // - - - Actualizar X orden de compra
    public boolean actualizar_orden_compra(Orden_compra oc){
        String sql = "Update adeudo_orden_compra set numero_orden_compra = '" + oc.getNumero_orden_compra() + "', esquema_orden_compra = '" + oc.getEsquema_orden_compra() +"' where id = '" + oc.getId_orden_compra() + "';";
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
    // - - - Muestra los pagos de X compra
    public String mostrar_pagos_compra(int id_compra){
        String sql = "Select reg, pago, date_format(fecha, '%Y-%m-%d') as fecha from adeudo_pago where adeudo = '" + id_compra + "';";
        return sql;
    }

    // - - - Muestra todos los proveedores
    public String mostrar_proveedores(){
        String sql = "Select p.id,\n" +
                "\t   p.nombre_proveedor,\n" +
                "\t   pl.dias as dias_limite,\n" +
                "       pl.credito,\n" +
                "       ifnull(pl.credito - SUM(a.cantidad_restante),pl.credito) as credito_disponible,\n" +
                "       p.telefono,\n" +
                "       p.correo,\n" +
                "       p.rfc,\n" +
                "       p.notas\n" +
                " from proveedores p\n" +
                "\tleft join adeudos a\n" +
                "\t\ton p.id = a.proveedor \n" +
                "\tleft join proveedores_limite pl\n" +
                "\t\ton pl.proveedor = p.id\n" +
                "\tgroup by p.id;";
        return sql;
    }
    // - - - Muestra un proveedor en específico
    public String mostrar_proveedor(int id){
        String sql = "Select p.id,\n" +
                "\t   p.nombre_proveedor,\n" +
                "\t   pl.dias as dias_limite,\n" +
                "       pl.credito,\n" +
                "       ifnull(pl.credito - SUM(a.cantidad_restante),pl.credito) as credito_disponible,\n" +
                "       p.telefono,\n" +
                "       p.correo,\n" +
                "       p.rfc,\n" +
                "       p.notas\n" +
                " from adeudos a\n" +
                "\tinner join proveedores p\n" +
                "\t\ton a.proveedor = p.id\n" +
                "\tinner join proveedores_limite pl\n" +
                "\t\ton pl.proveedor = p.id\n" +
                "    where p.id =" + id + ";";
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
    // - - - Muestra las compras de X Proveedor
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
    // - - - Muestra el último proveedor registrado
    public String ultimo_proveedor(){
        String sql = "Select id as id_proveedor from proveedores\n" +
                "\torder by id desc\n" +
                "\tlimit 1;";
        return sql;
    }

    // - - - Muestra la última factura registrada
    public String ultima_factura(){
        String sql = "select id as factura from adeudo_factura\n" +
                "\torder by id desc\n" +
                "\tlimit 1;";
        return sql;
    }
    // - - - Muestra los datos de X factura
    public String mostrar_datos_factura(String numero_factura){
        String sql = "Select id, numero_factura, esquema_factura from adeudo_factura where numero_factura = '" + numero_factura + "';";
        return sql;
    }

    // - - - Muestra la última cotizacion registrada
    public String ultima_cotizacion(){
        String sql = "select id as cotizacion from adeudo_cotizacion\n" +
                "\torder by id desc\n" +
                "\tlimit 1;";
        return sql;
    }
    // - - - Muestra los datos de X cotizacion
    public String mostrar_datos_cotizacion(String numero_cotizacion){
        String sql = "Select id, numero_cotizacion, esquema_cotizacion from adeudo_cotizacion where numero_cotizacion = '" + numero_cotizacion + "';";
        return sql;
    }

    // - - - Muestra la última orden de compra registrada
    public String ultima_orden_compra(){
        String sql = "select id as orden_compra from adeudo_orden_compra\n" +
                "\torder by id desc\n" +
                "\tlimit 1;";
        return sql;
    }
    // - - - Muestra los datos de X orden de compra
    public String mostrar_datos_orden_compra(String numero_orden_compra){
        String sql = "Select id, numero_orden_compra, esquema_orden_compra from adeudo_orden_compra where numero_orden_compra = '" + numero_orden_compra + "';";
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

    public int editarTrabajador(Trabajador t){
        String sql="update trabajadores set nombre='"+ t.getNombre()+
                "', apellido_paterno ='"+ t.getApellido_paterno()+
                "',apellido_materno='"+t.getApellido_materno()+
                "',rfc='"+t.getRfc()+
                "',solicitud_empleo='"+t.getSolicitud_empleo()+
                "',estado='"+t.getEstado()+
                "' where `id`='"+
                t.getId()+"';";
        int valor=consulta_modificar(sql);
        return valor;
    }

    public boolean Alta_falta(Falta falta){
        String sql = "Insert into faltas(trabajador, fecha, tipo_falta) " +
                "values ('" + falta.getTrabajador() + "', '" + falta.getFecha() + "', '" + falta.getTipo_falta() +  "');\n";
        return consulta_insertar(sql);
    }

    public String verFaltasTotales(){
        String consulta="select count(reg), cast(fecha as date) from faltas where tipo_falta='Falta' group by fecha;";
                return consulta;
    }

    public String verRetardosTotales(){
        String consulta="select count(reg), cast(fecha as date),weekday(fecha) from faltas where tipo_falta='Retardo' and month(now()) group by weekday(fecha);";
        return consulta;
    }


}
