package sample.objetos;

public class Inventario_oficina {
    int id,cantidad;
    String descripcion,estado,ruta;



    public Inventario_oficina(int cantidad) {
        this.cantidad = cantidad;
    }



    public Inventario_oficina(int id, String descripcion, int cantidad,String ruta) {
        this.id = id;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.ruta=ruta;
    }

    public Inventario_oficina(int id, String descripcion, int cantidad, String estado,String ruta) {
        this.id = id;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.estado = estado;
        this.ruta = ruta;
    }

    public Inventario_oficina(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Inventario_oficina(int cantidad, String descripcion, String estado,String ruta) {
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.estado = estado;
        this.ruta = ruta;
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

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
