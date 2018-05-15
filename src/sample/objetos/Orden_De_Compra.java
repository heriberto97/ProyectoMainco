package sample.objetos;

public class Orden_De_Compra {

    private int numero_orden_compra,representante;
    private String feche_inicio;

    public Orden_De_Compra(int numero_orden_compra, int representante, String feche_inicio) {
        this.numero_orden_compra = numero_orden_compra;
        this.representante = representante;
        this.feche_inicio = feche_inicio;
    }

    public int getNumero_orden_compra() {
        return numero_orden_compra;
    }

    public void setNumero_orden_compra(int numero_orden_compra) {
        this.numero_orden_compra = numero_orden_compra;
    }

    public int getRepresentante() {
        return representante;
    }

    public void setRepresentante(int representante) {
        this.representante = representante;
    }

    public String getFeche_inicio() {
        return feche_inicio;
    }

    public void setFeche_inicio(String feche_inicio) {
        this.feche_inicio = feche_inicio;
    }
}
