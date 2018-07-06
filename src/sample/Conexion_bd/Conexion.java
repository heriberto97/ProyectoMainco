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
        String url = "jdbc:mysql://192.168.1.73:3306/mainco";
       // String url = "jdbc:mysql://localhost:3306/mainco";

        //String user = "coreano";
        String user = "Mainco";
        //String user = "root";

        //String pass = "1234";
        String pass = "";
        try {
            conectar = DriverManager.getConnection(url, user, pass);
            System.out.println("Usted está conectado");
            return conectar;

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Revisa tu conexion");
            alerta.setHeaderText("¡Error de servidor!");
            alerta.setContentText("Algo esta fallando");
            alerta.showAndWait();
//            System.out.println(e.getMessage());
//            System.out.println("No logró conectar");

            conectar = null;
        }
        return null;
    }

    //METODO PARA CERRAR LA CONEXION
    public void cerrarConexion() {

        try {
            conectar.close();
            System.out.println("cerro conexion");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //METODO GLOBAL PARA INSERTAR
    public boolean consulta_insertar(String consulta) {
        try {
            String query1 = consulta;
            PreparedStatement s = this.conecta().prepareStatement(query1);
            s.executeUpdate(query1);
            System.out.println("estamos insertando ...");
            s.close();
            return true;
        } catch (SQLException e) {

            System.out.println(e);
            return false;
        }
    }

    //METODO  GLOBAL PARA MODIFICAR

    public int consulta_modificar(String consulta) {
        try {
            String query1 = consulta;
            PreparedStatement s = this.conecta().prepareStatement(query1);
            s.executeUpdate(query1);
            System.out.println("Modificando ...");
            s.close();
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    //METODO GLOBAL PARA MOSTRAR CONSULTAS
    public ResultSet mostrarSql(String consulta) {

        try {
            PreparedStatement us = this.conecta().prepareStatement(consulta);
            ResultSet res = us.executeQuery();
            // us.close();
            return res;
        } catch (SQLException e) {
           // System.out.println(e);
            return null;
        }

    }

    //METODOS PARA INSERTAR



    //

    public boolean PonerFalta(String nombre_completo) {
        String query = "";
        return consulta_insertar(query);
    }


    public String verClientes() {

        String consulta = "select * from representantes";
        return consulta;
    }

    public String InsertarTrabajos(String Nota, Integer PzTotales, Integer PzRestantes) {
        String consulta = "INSERT INTO `mainco`.`trabajos` (`notas`, `piezas_totales`, `piezas_restantes`) VALUES ('hola', '50', '50');";
        return consulta;
    }

    public String verTrabajos() {

        String consulta = "select * from trabajos";
        return consulta;
    }

    public String verTrabajosConOrdenCompra() {
        String sql = "SELECT * FROM mainco.trabajos where orden_compra is not null;";
        return sql;
    }


    public String empresascombobox() {
        String sql = "select nombre from mainco.empresas;";
        return sql;
    }

    //-----------------------------------------Metodos Trabajo--------------------------------------------------------
    public String veresquemas() {
        String sql = "Select * from esquemas;";
        return sql;
    }

    public String verEmpresas() {
        String sql = "Select * from empresas;";
        return sql;
    }

    public String verConsutaTrabajos(){
        String sql = "select trabajos.id_trabajo,productos.descripcion as producto,trabajos.notas,trabajos.piezas_totales,trabajos.piezas_restantes,trabajos.on_create as fecha_inicio,trabajos.fecha_final,trabajo_orden_compra.numero_orden_compra as numero_orden,trabajo_cotizacion.numero_cotizacion as numero_cotizacion,trabajo_factura.numero_factura \n" +
                "from trabajos \n" +
                "left join trabajo_orden_compra on trabajos.orden_compra = trabajo_orden_compra.id\n" +
                "left join trabajo_cotizacion on trabajos.cotizacion = trabajo_cotizacion.id\n" +
                "left join trabajo_factura on trabajos.factura = trabajo_factura.id\n" +
                "left join productos on trabajos.producto = productos.id_producto";
        return sql;
    }

    //----------------------------------------Metodos para inventario----------------------------------------------------------------------------------------



    public String ver_esquemas() {
        String sql = "select id, descripcion,ruta,numero from esquemas;";
        return sql;
    }

    public String id_delesquema(String des) {
        String sql = "select id,descripcion,ruta from esquemas where descripcion = '"+des+"';";
        return sql;
    }

    public String cantidad_articulo(int id) {
        String sql = "select cantidad from inventario_oficina where id = '"+id+"';";
        return sql;
    }




    public String tabla_producto_seleccionado(String id)
    {
        String sql = "select productos_materiales.reg as reg, productos.id_producto as numero ,productos_materiales.tiempo_estimado as tiempo,productos_materiales.peso,materiales.nombre as material from productos left join productos_materiales on productos.id_producto = productos_materiales.producto left join materiales on materiales.id = productos_materiales.material where id_producto = '"+id+"';";
        return sql;
    }

    public String combomateriales() {
        String sql = "select * from materiales;";
        return sql;
    }

    public String comboempresas() {
        String sql = "select * from empresas;";
        return sql;
    }
    public String combotrabajadores()
    {
        String sql = "select * from trabajadores;";
        return sql;
    }
    public String comboarticulos()
    {
        String sql = "select * from inventario_oficina;";
        return sql;
    }

    public String tablaproductos() {
        String sql = "select productos.id_producto as numero ,productos.descripcion as descripcion ,esquemas.ruta as ruta ,empresas.nombre as empresa from productos left join esquemas on productos.esquema = esquemas.id left join empresas on productos.empresa = empresas.id;";
        return sql;
    }

    public String datosusuario(String usuario, String contrasena) {
        String sql = "Select * from usuarios where usuario='" + usuario + "' and contrasena='" + contrasena + "';";
        return sql;
    }

    public String verarticulosoficina() {
        String sql = "Select * from inventario_oficina;";
        return sql;
    }

    public String datosalerta() {
        String sql = "select cantidad from inventario_oficina;";
        return sql;
    }

    public String buscar_numero_articulo(int numero_articulo) {
        String sql = "select * from inventario_oficina where id ='" + numero_articulo + "';";
        return sql;
    }

    public String buscar_descripcion_articulo(String descripcion) {
        String sql = "select * from inventario_oficina where descripcion like \"%" + descripcion + "%\";";
        return sql;
    }
    public String llenartablaexpedicion()
    {
        String sql = "Select reg as reg,\n" +
                "trabajadores.nombre as nombre\n" +
                ",trabajadores.apellido_paterno as apellido\n" +
                ",inventario_oficina.descripcion as descripcion\n" +
                ",articulos_empleados.cantidad as cantidad\n" +
                ",articulos_empleados.fecha_de_salida as fecha\n" +
                "from inventario_oficina inner join articulos_empleados on inventario_oficina.id=articulos_empleados.articulos\n" +
                "inner join trabajadores on trabajadores.id =articulos_empleados.trabajadores order by reg DESC;";
        return sql;
    }

    //metodos para insertar------------------------------------------------------------------------------------------------------------------------------------

    public boolean AltaArticulos(Inventario_oficina Articulo) {
        String query1 = "insert into inventario_oficina (cantidad,descripcion,estado,ruta) values ('" + Articulo.getCantidad() + "','" + Articulo.getDescripcion() + "','" + Articulo.getEstado() + "','"+Articulo.getRuta()+"');";
        return consulta_insertar(query1);
    }

    public boolean AltaArticulos2(Inventario_oficina Articulo) {
        String query1 = "insert into inventario_oficina (cantidad,descripcion,estado) values ('" + Articulo.getCantidad() + "','" + Articulo.getDescripcion() + "','" + Articulo.getEstado()+"');";
        return consulta_insertar(query1);
    }

    public boolean AltaProductocamposobligatorios(producto p) {
        String query1 = "insert into productos (id_producto,descripcion,empresa) values ('" + p.getNumero_producto() + "','" + p.getDescripcion() + "','" + p.getEmpresa() + "')";
        return consulta_insertar(query1);
    }

    public boolean AltaProductocamposobligatoriosyesquema(producto p) {
        String query1 = "insert into productos (id_producto,descripcion,esquema,empresa) values ('" + p.getNumero_producto() + "','" + p.getDescripcion() + "','" + p.getRuta_imagen() + "','" + p.getEmpresa() + "')";
        return consulta_insertar(query1);
    }

    public boolean Altaesquema(Esquema e) {
        String query1 = "insert into esquemas (ruta,descripcion,numero) values ('" + e.getRuta() + "','" + e.getDescripcion() + "','"+e.getNumero()+"');";
        return consulta_insertar(query1);
    }

    public boolean Altaasignardatos(productos_materiales p) {

            String sql = "insert into productos_materiales (producto ";
            if(p.getMaterial() != null) { sql =sql + ",material "; }
            if(p.getTiempo_estimado()!=0){sql=sql + ",tiempo_estimado ";}
            if(p.getPeso()!=0){sql = sql + ",peso";}
            sql=sql + ")values('"+p.getProducto()+"' ";
            if(p.getMaterial()!=null){sql=sql+ ",'"+p.getMaterial()+"'";}
            if(p.getTiempo_estimado()!=0){sql = sql + ",'"+p.getTiempo_estimado()+"'";}
            if(p.getPeso()!=0){sql = sql + ",'"+p.getPeso()+"'";}
            sql = sql + ");";
            return consulta_insertar(sql);

    }

    public boolean AltaUsuarios(Usuario u) {
        String query1 = "insert into usuarios (usuario,nombre,apellido_paterno,apellido_materno,contrasena,tipo) values ('" + u.getUsuario() + "','" + u.getNombre() + "','" + u.getApellido_p() + "','" + u.getApellido_m() + "','" + u.getContrasena() + "','" + u.getTipo_usuario() + "')";
        return consulta_insertar(query1);
    }

    public boolean Altaexpedicion(articulos_empleados e)
    {
        String query1 = "insert into articulos_empleados(articulos,trabajadores,fecha_de_salida,cantidad) \n" +
                "values ('"+e.getDescripcion_articulo()+"','"+e.getNombre_trabajador()+"',now(),'"+e.getCantidad()+"');";
        return consulta_insertar(query1);
    }

    //metodos para modificar-----------------------------------------------------------------------------------------------------------

    public int modificarArticulo(Inventario_oficina articulo){
        String sql="update inventario_oficina set descripcion='"+articulo.getDescripcion()+"', cantidad ='"+articulo.getCantidad()+"',ruta='"+articulo.getRuta()+"' where `id`='"+articulo.getId()+"';";
        int valor=consulta_modificar(sql);
        return valor;
    }

    public int modificarArticulo2(Inventario_oficina articulo){
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
    public int modificardescripcionprpoducto(producto p)
    {
        String sql = "update productos set descripcion ='"+p.getDescripcion()+"'where id_producto ='"+p.getNumero_producto()+"';";
        int valor=consulta_modificar(sql);
        return valor;
    }

    public int modificaresquema(int id_esquema, String id_producto)
    {
        String sql = "update productos set esquema = '"+id_esquema+"' where id_producto = '"+id_producto+"';";
        int valor=consulta_modificar(sql);
        return valor;
    }

    public int modificarmaterialproducto(String reg,int material)
    {
        String sql = "update productos_materiales set material = '"+material+"' where reg = '"+reg+"';";
        int valor=consulta_modificar(sql);
        return valor;
    }

    public int modificartiempoproducto(String reg,int tiempo)
    {
        String sql = "update productos_materiales set tiempo_estimado ='"+tiempo+"'where reg ='"+reg+"';";
        int valor=consulta_modificar(sql);
        return valor;
    }
    public int modificarpesoproducto(String reg,Double peso)
    {
        String sql = "update productos_materiales set peso ='"+peso+"'where reg ='"+reg+"';";
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

        sql = sql + "cantidad_restante, notas) values('" + Integer.parseInt(c.getProveedor()) +"', '" + c.getFecha_compra() +"', DATE_ADD('" + c.getFecha_compra() + "', INTERVAL " + dias_limite +" DAY), '" + c.getAdeudo() + "', ";

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
    public int realizar_pago(int registro, Double cantidad, String metodo_pago){
        String sql = "Insert into adeudo_pago (adeudo, pago, fecha, metodo_pago) values('" + registro + "', '" + cantidad + "', current_timestamp(), '" + metodo_pago + "');";
        return consulta_modificar(sql);
    }
    // - - - Realizar abono
    public int realizar_abono(int registro, Double cantidad, String metodo_pago){
        String sql = "Insert into adeudo_pago (adeudo, pago, fecha, metodo_pago) values('" + registro + "', '" + cantidad +"', current_timestamp(), '" + metodo_pago + "');";
        return consulta_modificar(sql);
    }
    // - - - Actualiza el pago de una compra
    public int actualizar_pago(int registro, Double cantidad){
        String sql = "UPDATE `adeudos` SET `cantidad_restante` = '" + cantidad +"' WHERE (`reg` = '" + registro + "');\n";
        return consulta_modificar(sql);
    }
    // - - - Eliminar pago
    public int eliminar_pago(Pago p){
        String sql = "Delete from adeudo_pago where reg = '" + p.getReg() + "';";
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
        String sql = "Select \ta.reg,\n" +
                "\t\taoc.numero_orden_compra,\n" +
                "\t\tac.numero_cotizacion, \n" +
                "\t\taf.numero_factura,\n" +
                "\t\tp.id as id_proveedor, \n" +
                "\t\tp.nombre_proveedor,\n" +
                "\t\ta.adeudo,\n" +
                "\t\tdate_format(a.fecha_compra, '%Y-%m-%d') as fecha_compra,\n" +
                "\t\tdate_format(a.fecha_limite, '%Y-%m-%d') as fecha_limite,\n" +
                "\t\ta.cantidad_restante,\n" +
                "        if(a.cantidad_restante > 0, \"POR PAGAR\", \"PAGADO\")as estado,\n" +
                "\t\ta.notas\n" +
                "\t\tfrom adeudos a\n" +
                "\tleft join adeudo_orden_compra aoc \n" +
                "\t\ton a.orden_compra = aoc.id\n" +
                "\tleft join adeudo_cotizacion ac\n" +
                "\t\ton a.cotizacion = ac.id\n" +
                "\tleft join adeudo_factura af\n" +
                "\t\ton a.factura = af.id\n" +
                "\tleft join proveedores p\n" +
                "\t\ton a.proveedor = p.id\n" +
                "\twhere cantidad_restante > 0;";
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
                "        date_format(a.fecha_compra, '%Y-%m-%d') as fecha_compra,\n" +
                "        date_format(a.fecha_limite, '%Y-%m-%d') as fecha_limite,\n" +
                "        a.cantidad_restante,\n" +
                "        if(a.cantidad_restante > 0, \"POR PAGAR\", \"PAGADO\")as estado,\n" +
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
        String sql = "Select  a.reg,\n" +
                "\t\taoc.numero_orden_compra,\n" +
                "\t\taf.numero_factura, \n" +
                "\t\tac.numero_cotizacion,\n" +
                "        p.id as id_proveedor,\n" +
                "        p.nombre_proveedor,\n" +
                "\t\ta.adeudo, \n" +
                "\t\tdate_format(a.fecha_compra, '%Y-%m-%d') as fecha_compra, \n" +
                "\t\tdate_format(a.fecha_limite, '%Y-%m-%d') as fecha_limite,\n" +
                "\t\ta.cantidad_restante,\n" +
                "        if(a.cantidad_restante > 0, \"POR PAGAR\", \"PAGADO\")as estado,\n" +
                "        a.notas\n" +
                " from adeudos a\n" +
                "\tleft join adeudo_orden_compra aoc \n" +
                "\t\ton a.orden_compra = aoc.id\n" +
                "\tleft join adeudo_cotizacion ac\n" +
                "\t\ton a.cotizacion = ac.id\n" +
                "\tleft join adeudo_factura af\n" +
                "\t\ton a.factura = af.id\n" +
                "\tleft join proveedores p\n" +
                "\t\ton a.proveedor = p.id \n" +
                "\twhere aoc.numero_orden_compra = \"\"\n" +
                "\tor    af.numero_factura = \"\" \n " +
                "\tor    ac.numero_cotizacion = \"\" " +
                "    order by fecha_limite asc;";
        return sql;
    }
    // - - - Muestra los pagos de X compra
    public String mostrar_pagos_compra(int id_compra){
        String sql = "Select reg, pago, date_format(fecha, '%Y-%m-%d') as fecha, metodo_pago from adeudo_pago where adeudo = '" + id_compra + "';";
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
                "       if(pl.credito - SUM(a.cantidad_restante) > 0,pl.credito - SUM(a.cantidad_restante), pl.credito) as credito_disponible,\n" +
                "       p.telefono,\n" +
                "       p.correo,\n" +
                "       p.rfc,\n" +
                "       p.notas\n" +
                " from proveedores p\n" +
                "\tleft join adeudos a\n" +
                "\t\ton p.id = a.proveedor\n" +
                "\tleft join proveedores_limite pl\n" +
                "\t\ton p.id = pl.proveedor\n" +
                "    where p.id = " + id + "";
        return sql;
    }
    // - - - Muestra todos los proveedores y los días que dan para pagar
    public String mostrar_proveedores_limite(){
        String sql = "Select p.id,\n" +
                "\t   p.nombre_proveedor,\n" +
                "\t   pl.dias as dias_limite,\n" +
                "       ifnull(pl.credito - SUM(a.cantidad_restante),pl.credito) as credito_disponible\n" +
                " from proveedores p\n" +
                "\tleft join adeudos a\n" +
                "\t\ton p.id = a.proveedor \n" +
                "\tleft join proveedores_limite pl\n" +
                "\t\ton pl.proveedor = p.id\n" +
                "\tgroup by p.id;";
        return sql;
    }
    // - - - Busca el proveedor que coincida
    public String buscar_proveedores(String nombre_proveedor){
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
                "\twhere p.nombre_proveedor like \"%" + nombre_proveedor + "%\"\n" +
                "\tgroup by p.id;";
        return sql;
    }
    // - - - Muestra las compras por pagar de X Proveedor
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
                "        date_format(a.fecha_limite, '%Y-%m-%d') as fecha_limite,\n" +
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
                "\twhere p.id = "+ id +
                "  and a.cantidad_restante > 0 ;\n";
        return sql;
    }
    // - - - Muestra las compras de X Proveedor
    public String mostrar_compras_proveedor_completas(int id){
        String sql = "Select  aoc.numero_orden_compra, \n" +
                "\t\tac.numero_cotizacion, \n" +
                "        af.numero_factura, \n" +
                "        p.id as id_proveedor, \n" +
                "        p.nombre_proveedor, \n" +
                "        a.notas, \n" +
                "        a.reg, \n" +
                "        a.adeudo,\n" +
                "        a.fecha_compra,\n" +
                "        date_format(a.fecha_limite, '%Y-%m-%d') as fecha_limite,\n" +
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
                "\twhere p.id = "+ id +";";
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
    // - - - Buscar todas las facturas que coincidan con la búsqueda
    public String buscar_compra_factura(String factura){
        String sql = "Select \ta.reg,\n" +
                "\t\taoc.numero_orden_compra,\n" +
                "\t\tac.numero_cotizacion, \n" +
                "\t\taf.numero_factura,\n" +
                "\t\tp.id as id_proveedor, \n" +
                "\t\tp.nombre_proveedor,\n" +
                "\t\ta.adeudo,\n" +
                "\t\tdate_format(a.fecha_compra, '%Y-%m-%d') as fecha_compra,\n" +
                "\t\tdate_format(a.fecha_limite, '%Y-%m-%d') as fecha_limite,\n" +
                "\t\ta.cantidad_restante,\n" +
                "        if(a.cantidad_restante > 0, \"POR PAGAR\", \"PAGADO\")as estado,\n" +
                "\t\ta.notas\n" +
                "\t\tfrom adeudos a\n" +
                "\tleft join adeudo_orden_compra aoc \n" +
                "\t\ton a.orden_compra = aoc.id\n" +
                "\tleft join adeudo_cotizacion ac\n" +
                "\t\ton a.cotizacion = ac.id\n" +
                "\tleft join adeudo_factura af\n" +
                "\t\ton a.factura = af.id\n" +
                "\tleft join proveedores p\n" +
                "\t\ton a.proveedor = p.id\n" +
                "\twhere af.numero_factura like \"%" + factura + "%\";";
        return sql;
    }
    // - - - Buscar todas las facturas que coincidan con la búsqueda
    public String buscar_compra_pendientes_factura(String factura){
        String sql = "Select \ta.reg,\n" +
                "\t\taoc.numero_orden_compra,\n" +
                "\t\tac.numero_cotizacion, \n" +
                "\t\taf.numero_factura,\n" +
                "\t\tp.id as id_proveedor, \n" +
                "\t\tp.nombre_proveedor,\n" +
                "\t\ta.adeudo,\n" +
                "\t\tdate_format(a.fecha_compra, '%Y-%m-%d') as fecha_compra,\n" +
                "\t\tdate_format(a.fecha_limite, '%Y-%m-%d') as fecha_limite,\n" +
                "\t\ta.cantidad_restante,\n" +
                "        if(a.cantidad_restante > 0, \"POR PAGAR\", \"PAGADO\")as estado,\n" +
                "\t\ta.notas\n" +
                "\t\tfrom adeudos a\n" +
                "\tleft join adeudo_orden_compra aoc \n" +
                "\t\ton a.orden_compra = aoc.id\n" +
                "\tleft join adeudo_cotizacion ac\n" +
                "\t\ton a.cotizacion = ac.id\n" +
                "\tleft join adeudo_factura af\n" +
                "\t\ton a.factura = af.id\n" +
                "\tleft join proveedores p\n" +
                "\t\ton a.proveedor = p.id\n" +
                "\twhere af.numero_factura like \"%" + factura + "%\";";
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
        String consulta="select count(reg), cast(fecha as date) from faltas where tipo_falta='Retardo' group by fecha;";
        return consulta;
    }

    public String verFaltasPorTrabajador(){
        String consulta="select trabajadores.nombre,trabajadores.apellido_paterno,count(faltas.trabajador)" +
                " from faltas inner join trabajadores on faltas.trabajador=trabajadores.id group by trabajador;";
        return consulta;
    }

    public String verFRMensuales(){
        String consulta="\n" +
                "select trabajadores.nombre,trabajadores.apellido_paterno, sum(if(tipo_falta='Falta',1,0)) as falta,\n" +
                "sum(if(tipo_falta='Retardo',1,0))as retardo,\n" +
                "faltas.trabajador\n" +
                "from faltas inner join trabajadores on faltas.trabajador=trabajadores.id where month(faltas.fecha)=month(now()) group by trabajador;";
        return consulta;
    }

    public String verFRPersonales(int id){
        String consulta="\n" +
                "select trabajadores.nombre, trabajadores.apellido_paterno,cast(faltas.fecha as date),sum(if(tipo_falta='Falta',1,0)) as Faltas,sum(if(tipo_falta='Retardo',1,0))as Retardos,weekofyear(fecha) as conteo from faltas inner join trabajadores on trabajadores.id=faltas.trabajador\n" +
                "where faltas.trabajador='"+id+"'  group by conteo;";
        return consulta;
    }

    public boolean altaPrestamo(int id,double cantidad){
        String sql = "Insert into prestamos(trabajador, cantidad,cantidad_restante, fecha_realizacion,estado) " +
                "values ('" + id + "', '" + cantidad + "', '" + cantidad +  "',now(),'Debe');\n";
        return consulta_insertar(sql);
    }

    public String verDeudor(int id){
        String consulta="select count(reg),sum(cantidad),sum(cantidad_restante) from prestamos where trabajador=' "+id +"' and estado='Debe' group by trabajador";
        return consulta;
    }

}
