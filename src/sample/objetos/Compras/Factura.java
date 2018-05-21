package sample.objetos.Compras;

public class Factura {
    private int id_factura;
    private String numero_factura, esquema_factura;

    public Factura(int id_factura, String numero_factura, String esquema_factura) {
        this.id_factura = id_factura;
        this.numero_factura = numero_factura;
        this.esquema_factura = esquema_factura;
    }

    public Factura(int id_factura, String numero_factura) {
        this.id_factura = id_factura;
        this.numero_factura = numero_factura;
    }

    public int getId_factura() {
        return id_factura;
    }
    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
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
