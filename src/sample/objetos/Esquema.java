package sample.objetos;

public class Esquema {

    int id;
    String ruta;
    String descripcion;
    String numero;

    public Esquema(String ruta, String descripcion) {
        this.ruta = ruta;
        this.descripcion = descripcion;
    }

    public Esquema(int id, String ruta, String descripcion) {
        this.id = id;
        this.ruta = ruta;
        this.descripcion = descripcion;
    }

    public Esquema(int id, String ruta, String descripcion, String numero) {
        this.id = id;
        this.ruta = ruta;
        this.descripcion = descripcion;
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
