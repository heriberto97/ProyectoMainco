package sample.objetos;

public class Representante {

    private  String nombre,apellido_paterno,apellido_materno,telefono,correo;
    private int razon_social;

    public Representante(String nombre, String apellido_paterno, String apellido_materno, String telefono, String correo, int razon_social) {
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.telefono = telefono;
        this.correo = correo;
        this.razon_social = razon_social;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(int razon_social) {
        this.razon_social = razon_social;
    }
}
