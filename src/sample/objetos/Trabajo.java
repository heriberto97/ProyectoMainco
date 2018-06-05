package sample.objetos;

public class Trabajo {

    private int id,tipo_trabajo,producto,piezas_totales,piezas_restantes,orden_compra,cotizacion,factura;
    private String notas,fecha_final,on_create,on_update;

    public Trabajo(int id, int tipo_trabajo, int producto, int piezas_totales, int piezas_restantes,int cotizacion,int factura,int orden_compra, String notas, String fecha_final, String on_create, String on_update) {
        this.id=id;
        this.tipo_trabajo = tipo_trabajo;
        this.producto = producto;
        this.piezas_totales = piezas_totales;
        this.piezas_restantes = piezas_restantes;
        this.orden_compra=orden_compra;
        this.notas = notas;
        this.fecha_final = fecha_final;
        this.cotizacion = cotizacion;
        this.factura = factura;
        this.on_create = on_create;
        this.on_update = on_update;
    }
    public Trabajo(int id){
        this.id=id;
    }


    public int getTipo_trabajo() {
        return tipo_trabajo;
    }

    public void setTipo_trabajo(int tipo_trabajo) {
        this.tipo_trabajo = tipo_trabajo;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public int getPiezas_totales() {
        return piezas_totales;
    }

    public void setPiezas_totales(int piezas_totales) {
        this.piezas_totales = piezas_totales;
    }

    public int getPiezas_restantes() {
        return piezas_restantes;
    }

    public void setPiezas_restantes(int piezas_restantes) {
        this.piezas_restantes = piezas_restantes;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(String fecha_final) {
        this.fecha_final = fecha_final;
    }

    public int getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(int cotizacion) {
        this.cotizacion = cotizacion;
    }

    public int getFactura() {
        return factura;
    }

    public void setFactura(int factura) {
        this.factura = factura;
    }

    public String getOn_create() {
        return on_create;
    }

    public void setOn_create(String on_create) {
        this.on_create = on_create;
    }

    public String getOn_update() {
        return on_update;
    }

    public void setOn_update(String on_update) {
        this.on_update = on_update;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrden_compra() {
        return orden_compra;
    }

    public void setOrden_compra(int orden_compra) {
        this.orden_compra = orden_compra;
    }

}
