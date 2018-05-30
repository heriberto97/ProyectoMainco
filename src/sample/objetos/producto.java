package sample.objetos;

public class producto {

    String numero_producto;
    String Descrpcion;
    String ruta_imagen;
    int empresa;

    public producto(String numero_producto, String descrpcion, String ruta_imagen, int empresa) {
        this.numero_producto = numero_producto;
        Descrpcion = descrpcion;
        this.ruta_imagen = ruta_imagen;
        this.empresa = empresa;
    }


    public String getNumero_producto() {
        return numero_producto;
    }

    public void setNumero_producto(String numero_producto) {
        this.numero_producto = numero_producto;
    }

    public String getDescrpcion() {
        return Descrpcion;
    }

    public void setDescrpcion(String descrpcion) {
        Descrpcion = descrpcion;
    }

    public String getRuta_imagen() {
        return ruta_imagen;
    }

    public void setRuta_imagen(String ruta_imagen) {
        this.ruta_imagen = ruta_imagen;
    }

    public int getEmpresa() {
        return empresa;
    }

    public void setEmpresa(int empresa) {
        this.empresa = empresa;
    }
}
