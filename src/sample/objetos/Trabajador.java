package sample.objetos;

public class Trabajador {
    Integer id;
    String nombre, telefono, correo_electronico, rfc;

    public Trabajador(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Trabajador(int id, String nombre, String telefono, String correo_electronico, String rfc) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo_electronico = correo_electronico;
        this.rfc = rfc;
    }

    public Trabajador(String nombre) {
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }
}
