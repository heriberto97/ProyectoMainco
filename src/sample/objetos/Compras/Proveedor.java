package sample.objetos.Compras;

public class Proveedor {
    private String nombre, telefono, correo, rfc;

    // Constructor con todas las propiedades
    public Proveedor(String nombre, String telefono, String correo, String rfc) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.rfc = rfc;
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

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRfc() {
        return rfc;
    }
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }
}
