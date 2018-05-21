package sample.objetos.Compras;

public class Cotizacion {
    private int id_cotizacion;
    private String numero_cotizacion, esquema;

    public Cotizacion(int id_cotizacion, String numero_cotizacion, String esquema) {
        this.id_cotizacion = id_cotizacion;
        this.numero_cotizacion = numero_cotizacion;
        this.esquema = esquema;
    }

    public Cotizacion(int id_cotizacion, String numero_cotizacion) {
        this.id_cotizacion = id_cotizacion;
        this.numero_cotizacion = numero_cotizacion;
    }

    public int getId_cotizacion() {
        return id_cotizacion;
    }
    public void setId_cotizacion(int id_cotizacion) {
        this.id_cotizacion = id_cotizacion;
    }

    public String getNumero_cotizacion() {
        return numero_cotizacion;
    }
    public void setNumero_cotizacion(String numero_cotizacion) {
        this.numero_cotizacion = numero_cotizacion;
    }

    public String getEsquema() {
        return esquema;
    }
    public void setEsquema(String esquema) {
        this.esquema = esquema;
    }
}
