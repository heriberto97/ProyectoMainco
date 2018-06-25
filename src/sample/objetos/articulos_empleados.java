package sample.objetos;

public class articulos_empleados {

    int reg;
    String nombre_trabajador;
    String apellido;
    String descripcion_articulo;
    int cantidad;
    String fecha;


    public articulos_empleados() {
    }

    public articulos_empleados(int reg, String nombre_trabajador, String apellido, String descripcion_articulo, int cantidad, String fecha) {
        this.reg = reg;
        this.nombre_trabajador = nombre_trabajador;
        this.apellido = apellido;
        this.descripcion_articulo = descripcion_articulo;
        this.cantidad = cantidad;
        this.fecha = fecha;

    }



    public int getReg() {
        return reg;
    }

    public void setReg(int reg) {
        this.reg = reg;
    }

    public String getNombre_trabajador() {
        return nombre_trabajador;
    }

    public void setNombre_trabajador(String nombre_trabajador) {
        this.nombre_trabajador = nombre_trabajador;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDescripcion_articulo() {
        return descripcion_articulo;
    }

    public void setDescripcion_articulo(String descripcion_articulo) {
        this.descripcion_articulo = descripcion_articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
