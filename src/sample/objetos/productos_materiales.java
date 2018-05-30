package sample.objetos;

public class productos_materiales {

    int reg;
    int producto;
    int material;
    int tiempo_estimado;
    double peso;
    int realizaciones;

    public productos_materiales(int reg, int producto, int material, int tiempo_estimado, double peso, int realizaciones) {
        this.reg = reg;
        this.producto = producto;
        this.material = material;
        this.tiempo_estimado = tiempo_estimado;
        this.peso = peso;
        this.realizaciones = realizaciones;
    }

    public int getReg() {
        return reg;
    }

    public void setReg(int reg) {
        this.reg = reg;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
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
