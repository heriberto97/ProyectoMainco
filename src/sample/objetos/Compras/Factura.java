package sample.objetos.Compras;

public class Factura {
    private String numero_factura, esquema_factura;

    public Factura(String numero_factura, String esquema_factura) {
        this.numero_factura = numero_factura;
        this.esquema_factura = esquema_factura;
    }

    public String getNumero_factura() {
        return numero_factura;
    }

    public void setNumero_factura(String numero_factura) {
        this.numero_factura = numero_factura;
    }

    public String getEsquema_factura() {
        return esquema_factura;
    }

    public void setEsquema_factura(String esquema_factura) {
        this.esquema_factura = esquema_factura;
    }
}
