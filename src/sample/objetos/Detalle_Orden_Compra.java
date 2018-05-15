package sample.objetos;

public class Detalle_Orden_Compra {

    private int orden_compra,trabajo;
    private String descripcion;

    public Detalle_Orden_Compra(int orden_compra, int trabajo, String descripcion) {
        this.orden_compra = orden_compra;
        this.trabajo = trabajo;
        this.descripcion = descripcion;
    }

    public int getOrden_compra() {
        return orden_compra;
    }

    public void setOrden_compra(int orden_compra) {
        this.orden_compra = orden_compra;
    }

    public int getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(int trabajo) {
        this.trabajo = trabajo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
