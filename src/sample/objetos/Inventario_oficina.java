package sample.objetos;

public class Inventario_oficina {
    int id,cantidad;
    String descripcion,estado;



    public Inventario_oficina(int cantidad) {
        this.cantidad = cantidad;
    }

    public Inventario_oficina(int id, String descripcion, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
    }

    public Inventario_oficina(int id, String descripcion, int cantidad, String estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.estado = estado;
    }



    public Inventario_oficina(int cantidad, String descripcion, String estado) {
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Inventario_oficina() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
