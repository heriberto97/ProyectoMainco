package sample.objetos.Compras;

public class Compra {
    private String provedor, fecha_compra, fecha_limite, factura, cotizacion, orden_compra;;
    private double adeudo, cantidad_restante;

    public Compra(String provedor, double adeudo, String fecha_compra, String fecha_limite, String factura, String cotizacion, String orden_compra, double cantidad_restante) {
        this.provedor = provedor;
        this.fecha_compra = fecha_compra;
        this.fecha_limite = fecha_limite;
        this.factura = factura;
        this.cotizacion = cotizacion;
        this.orden_compra = orden_compra;
        this.adeudo = adeudo;
        this.cantidad_restante = cantidad_restante;
    }

    public String getProvedor() {
        return provedor;
    }
    public void setProvedor(String provedor) {
        this.provedor = provedor;
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

    public String getFactura() {
        return factura;
    }
    public void setFactura(String factura) {
        this.factura = factura;
    }

    public String getCotizacion() {
        return cotizacion;
    }
    public void setCotizacion(String cotizacion) {
        this.cotizacion = cotizacion;
    }

    public String getOrden_compra() {
        return orden_compra;
    }
    public void setOrden_compra(String orden_compra) {
        this.orden_compra = orden_compra;
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
