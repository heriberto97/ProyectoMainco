package sample.objetos.Compras;

public class Orden_compra {
    private String numero_orden_compra, esquema_orden_compra;

    public Orden_compra(String numero_orden_compra, String esquema_orden_compra) {
        this.numero_orden_compra = numero_orden_compra;
        this.esquema_orden_compra = esquema_orden_compra;
    }

    public String getNumero_orden_compra() {
        return numero_orden_compra;
    }

    public void setNumero_orden_compra(String numero_orden_compra) {
        this.numero_orden_compra = numero_orden_compra;
    }

    public String getEsquema_orden_compra() {
        return esquema_orden_compra;
    }

    public void setEsquema_orden_compra(String esquema_orden_compra) {
        this.esquema_orden_compra = esquema_orden_compra;
    }
}
