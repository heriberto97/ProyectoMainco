package sample.objetos.Compras;

public class Orden_compra {
    private int id_orden_compra;
    private String numero_orden_compra, esquema_orden_compra;

    // Constructor con todas las propiedades
    public Orden_compra(int id_orden_compra, String numero_orden_compra, String esquema_orden_compra) {
        this.id_orden_compra = id_orden_compra;
        this.numero_orden_compra = numero_orden_compra;
        this.esquema_orden_compra = esquema_orden_compra;
    }

    // Constructor para registrar una nueva orden de compra
    public Orden_compra(){ }

    public int getId_orden_compra() {
        return id_orden_compra;
    }
    public void setId_orden_compra(int id_orden_compra) {
        this.id_orden_compra = id_orden_compra;
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
