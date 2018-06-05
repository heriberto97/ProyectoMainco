package sample.objetos;

public class productos_materiales {

    String producto;
   String material;
    int tiempo_estimado;
    double peso;
    int realizaciones;

    public productos_materiales(String producto, String material, int tiempo_estimado, double peso) {
        this.producto = producto;
        this.material = material;
        this.tiempo_estimado = tiempo_estimado;
        this.peso = peso;
    }

    public productos_materiales() {
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getTiempo_estimado() {
        return tiempo_estimado;
    }

    public void setTiempo_estimado(int tiempo_estimado) {
        this.tiempo_estimado = tiempo_estimado;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getRealizaciones() {
        return realizaciones;
    }

    public void setRealizaciones(int realizaciones) {
        this.realizaciones = realizaciones;
    }
}
