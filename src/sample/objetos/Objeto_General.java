package sample.objetos;

public class Objeto_General {
    private int id,pzs_totales,pzs_restantes;
    private String producto,notas,fecha_inicio,fecha_final,numero_orden,numero_cotizacion,numero_factura;

    public Objeto_General() {
    }

    public Objeto_General(int id, int pzs_totales, int pzs_restantes, String producto, String notas, String fecha_inicio, String fecha_final, String numero_orden, String numero_cotizacion, String numero_factura) {
        this.id = id;
        this.pzs_totales = pzs_totales;
        this.pzs_restantes = pzs_restantes;
        this.producto = producto;
        this.notas = notas;
        this.fecha_inicio = fecha_inicio;
        this.fecha_final = fecha_final;
        this.numero_orden = numero_orden;
        this.numero_cotizacion = numero_cotizacion;
        this.numero_factura = numero_factura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPzs_totales() {
        return pzs_totales;
    }

    public void setPzs_totales(int pzs_totales) {
        this.pzs_totales = pzs_totales;
    }

    public int getPzs_restantes() {
        return pzs_restantes;
    }

    public void setPzs_restantes(int pzs_restantes) {
        this.pzs_restantes = pzs_restantes;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(String fecha_final) {
        this.fecha_final = fecha_final;
    }

    public String getNumero_orden() {
        return numero_orden;
    }

    public void setNumero_orden(String numero_orden) {
        this.numero_orden = numero_orden;
    }

    public String getNumero_cotizacion() {
        return numero_cotizacion;
    }

    public void setNumero_cotizacion(String numero_cotizacion) {
        this.numero_cotizacion = numero_cotizacion;
    }

    public String getNumero_factura() {
        return numero_factura;
    }

    public void setNumero_factura(String numero_factura) {
        this.numero_factura = numero_factura;
    }
}
