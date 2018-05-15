package sample.objetos.Compras;

public class Compra {
    private int provedor, factura, cotizacion, orden_compra;
    private String fecha_compra, fecha_limite;
    private double adeudo, cantidad_restante;

    public Compra(int provedor, int factura, int cotizacion, int orden_compra, String fecha_compra, String fecha_limite, double adeudo) {
        this.provedor = provedor;
        this.factura = factura;
        this.cotizacion = cotizacion;
        this.orden_compra = orden_compra;
        this.fecha_compra = fecha_compra;
        this.fecha_limite = fecha_limite;
        this.adeudo = adeudo;
    }

    public int getProvedor() {
        return provedor;
    }
    public void setProvedor(int provedor) {
        this.provedor = provedor;
    }

    public int getFactura() {
        return factura;
    }
    public void setFactura(int factura) {
        this.factura = factura;
    }

    public int getCotizacion() {
        return cotizacion;
    }
    public void setCotizacion(int cotizacion) {
        this.cotizacion = cotizacion;
    }

    public int getOrden_compra() {
        return orden_compra;
    }
    public void setOrden_compra(int orden_compra) {
        this.orden_compra = orden_compra;
    }

    public String getFecha_compra() {
        return fecha_compra;
    }
    public void setFecha_compra(String fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public String getFecha_limite() {
        return fecha_limite;
    }
    public void setFecha_limite(String fecha_limite) {
        this.fecha_limite = fecha_limite;
    }

    public double getAdeudo() {
        return adeudo;
    }
    public void setAdeudo(double adeudo) {
        this.adeudo = adeudo;
    }

    public double getCantidad_restante() {
        return cantidad_restante;
    }
    public void setCantidad_restante(double cantidad_restante) {
        this.cantidad_restante = cantidad_restante;
    }
}
