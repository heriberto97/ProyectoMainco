package sample.objetos;

public class productos_materiales {
    String reg;
    String producto;
   String material;

    double peso;
    int realizaciones;



    public productos_materiales(String reg, String producto, String material, double peso) {
        this.reg = reg;
        this.producto = producto;
        this.material = material;

        this.peso = peso;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
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
