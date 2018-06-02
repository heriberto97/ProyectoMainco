package sample.objetos;

public class productos_materiales {

    String producto;
    int material;
    int tiempo_estimado;
    Double peso;
    int realizaciones;

    public productos_materiales(String producto, int material, int tiempo_estimado, Double peso) {
        this.producto = producto;
        this.material = material;
        this.tiempo_estimado = tiempo_estimado;
        this.peso = peso;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getMaterial() {
        return material;
    }

    public void setMaterial(int material) {
        this.material = material;
    }

    public int getTiempo_estimado() {
        return tiempo_estimado;
    }

    public void setTiempo_estimado(int tiempo_estimado) {
        this.tiempo_estimado = tiempo_estimado;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public int getRealizaciones() {
        return realizaciones;
    }

    public void setRealizaciones(int realizaciones) {
        this.realizaciones = realizaciones;
    }
}
