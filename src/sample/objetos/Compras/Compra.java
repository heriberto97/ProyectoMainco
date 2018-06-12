package sample.objetos.Compras;

import java.sql.Date;

public class Compra {
    private int reg;
    private int id_proveedor;
    private String proveedor;
    private String cotizacion, factura, orden_compra;
    private Date fecha_compra, fecha_limite;
    private double adeudo, cantidad_restante;
    private String notas;
    private String estado;

    // Constructor para todas las compras
    public Compra(int reg, int id_proveedor, String proveedor, String cotizacion, String factura, String orden_compra, Date fecha_compra, Date fecha_limite, double adeudo, double cantidad_restante, String notas, String estado) {
        this.reg = reg;
        this.id_proveedor = id_proveedor;
        this.proveedor = proveedor;
        this.cotizacion = cotizacion;
        this.factura = factura;
        this.orden_compra = orden_compra;
        this.fecha_compra = fecha_compra;
        this.fecha_limite = fecha_limite;
        this.adeudo = adeudo;
        this.cantidad_restante = cantidad_restante;
        this.notas = notas;
        this.estado = estado;
    }
    // Constructor sin estado
    public Compra(int reg, int id_proveedor, String proveedor, String cotizacion, String factura, String orden_compra, Date fecha_compra, Date fecha_limite, double adeudo, double cantidad_restante, String notas) {
        this.reg = reg;
        this.id_proveedor = id_proveedor;
        this.proveedor = proveedor;
        this.cotizacion = cotizacion;
        this.factura = factura;
        this.orden_compra = orden_compra;
        this.fecha_compra = fecha_compra;
        this.fecha_limite = fecha_limite;
        this.adeudo = adeudo;
        this.cantidad_restante = cantidad_restante;
        this.notas = notas;
    }

    // Constructor para compras a pagar en los proximos 30 días
    public Compra(int reg, String proveedor, Date fecha_limite, double cantidad_restante) {
        this.reg = reg;
        this.proveedor = proveedor;
        this.fecha_limite = fecha_limite;
        this.cantidad_restante = cantidad_restante;
    }

    // Constructor vacío para registrar una nueva compra
    public Compra(){ }

    public int getReg() {
        return reg;
    }
    public void setReg(int reg) {
        this.reg = reg;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }
    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getProveedor() {
        return proveedor;
    }
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
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

    public String getOrden_compra() {
        return orden_compra;
    }
    public void setOrden_compra(String orden_compra) {
        this.orden_compra = orden_compra;
    }

    public Date getFecha_compra() {
        return fecha_compra;
    }
    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public Date getFecha_limite() {
        return fecha_limite;
    }
    public void setFecha_limite(Date fecha_limite) {
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

    public String getNotas() {
        return notas;
    }
    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
