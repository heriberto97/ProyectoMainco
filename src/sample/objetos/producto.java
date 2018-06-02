package sample.objetos;

public class producto {

    String numero_producto;
    String descripcion;
    String ruta_imagen;
    String empresa;

    public producto(String numero_producto, String descripcion, String ruta_imagen, String empresa) {
        this.numero_producto = numero_producto;
       this.descripcion = descripcion;
        this.ruta_imagen = ruta_imagen;
        this.empresa = empresa;
    }

    public producto(String numero_producto, String descripcion, String empresa) {
        this.numero_producto = numero_producto;
        this.descripcion= descripcion;
        this.empresa = empresa;
    }

    public String getNumero_producto() {
        return numero_producto;
    }

    public void setNumero_producto(String numero_producto) {
        this.numero_producto = numero_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRuta_imagen() {
        return ruta_imagen;
    }

    public void setRuta_imagen(String ruta_imagen) {
        this.ruta_imagen = ruta_imagen;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
