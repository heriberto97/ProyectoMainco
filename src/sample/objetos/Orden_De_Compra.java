package sample.objetos;

import java.sql.Date;

public class Orden_De_Compra {

    private String numero_orden_compra,esquema,cliente,notas,cotizacion,factura;
    private Date fecha;

    public Orden_De_Compra(){

    }

    public Orden_De_Compra(String numero_orden_compra, String esquema, String cliente, String notas, String cotizacion, String factura , Date fecha) {
        this.numero_orden_compra = numero_orden_compra;
        this.esquema = esquema;
        this.cliente = cliente;
        this.notas = notas;
        this.cotizacion = cotizacion;
        this.factura = factura;
        this.fecha=fecha;
    }

    public String getNumero_orden_compra() {
        return numero_orden_compra;
    }

    public void setNumero_orden_compra(String numero_orden_compra) {
        this.numero_orden_compra = numero_orden_compra;
    }

    public String getEsquema() {
        return esquema;
    }

    public void setEsquema(String esquema) {
        this.esquema = esquema;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(String cotizacion) {
        this.cotizacion = cotizacion;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
