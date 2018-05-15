package sample.objetos.Compras;

public class Cotizacion {
    private String numero_cotizacion, esquema;

    public Cotizacion(String numero_cotizacion, String esquema) {
        this.numero_cotizacion = numero_cotizacion;
        this.esquema = esquema;
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
