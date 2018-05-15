package sample.objetos;

public class Esquema {

    private String ruta,descripcion,nombre,on_create,on_update;

    public Esquema(String ruta, String descripcion, String nombre, String on_create, String on_update) {
        this.ruta = ruta;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.on_create = on_create;
        this.on_update = on_update;

    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOn_create() {
        return on_create;
    }

    public void setOn_create(String on_create) {
        this.on_create = on_create;
    }

    public String getOn_update() {
        return on_update;
    }

    public void setOn_update(String on_update) {
        this.on_update = on_update;
    }
}
