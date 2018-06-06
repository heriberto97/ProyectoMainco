package sample.objetos.Compras;

public class Proveedor {
    private int id_proveedor;
    private String nombre;
    private int dias_limite;
    private Double credito, credito_disponible;
    private String telefono, correo, rfc, notas;

    // Constructor con todas las propiedades
    public Proveedor(int id_proveedor, String nombre, int dias_limite, Double credito, Double credito_disponible, String telefono, String correo, String rfc, String notas) {
        this.id_proveedor = id_proveedor;
        this.nombre = nombre;
        this.dias_limite = dias_limite;
        this.credito = credito;
        this.credito_disponible = credito_disponible;
        this.telefono = telefono;
        this.correo = correo;
        this.rfc = rfc;
        this.notas = notas;
    }

    // - - - - - Constructor para mostrar los proveedores en Compra Nueva
    public Proveedor(int id_proveedor, String nombre, int dias_limite, Double credito_disponible) {
        this.id_proveedor = id_proveedor;
        this.nombre = nombre;
        this.dias_limite = dias_limite;
        this.credito_disponible = credito_disponible;
    }

    // - - - - - Constructor para mostrar todos los proveedores
    public Proveedor(String nombre, int dias_limite, Double credito, Double credito_disponible, String telefono, String correo, String rfc) {
        this.nombre = nombre;
        this.dias_limite = dias_limite;
        this.credito = credito;
        this.credito_disponible = credito_disponible;
        this.telefono = telefono;
        this.correo = correo;
        this.rfc = rfc;
    }

    // Constructor vacío para registrar un nuevo Proveedor
    public Proveedor(){ }

    public int getId_proveedor() {
        return id_proveedor;
    }
    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDias_limite() {
        return dias_limite;
    }
    public void setDias_limite(int dias_limite) {
        this.dias_limite = dias_limite;
    }

    public Double getCredito() {
        return credito;
    }
    public void setCredito(Double credito) {
        this.credito = credito;
    }

    public Double getCredito_disponible() {
        return credito_disponible;
    }
    public void setCredito_disponible(Double credito_disponible) {
        this.credito_disponible = credito_disponible;
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

    public String getNotas() {
        return notas;
    }
    public void setNotas(String notas) {
        this.notas = notas;
    }
}
