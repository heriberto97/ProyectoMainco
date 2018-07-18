package sample.objetos;

public class Material {

    int id;
    String nombre;


    public Material(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Material (String nombre)
    {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
