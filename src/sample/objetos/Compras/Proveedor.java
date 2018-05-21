package sample.objetos.Compras;

public class Proveedor {
    private int id_proveedor;
    private String nombre, telefono, correo, rfc;

    // Constructor con todas las propiedades

    public Proveedor(int id, String nombre, String telefono, String correo, String rfc) {
        this.id_proveedor = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.rfc = rfc;
    }

    public Proveedor(int id_proveedor, String nombre) {
        this.id_proveedor = id_proveedor;
        this.nombre = nombre;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }
    public void setId_proveedor(int id) {
        this.id_proveedor = id;
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
