package sample.objetos.Traabajadores;

public class Falta {
    int reg,trabajador,conteoFaltas;
    String tipo_falta,fecha;

    public Falta(int trabajador, String tipo_falta, String fecha) {
        this.trabajador = trabajador;
        this.tipo_falta = tipo_falta;
        this.fecha = fecha;
    }

    public Falta(int reg, int trabajador, String tipo_falta, String fecha) {
        this.reg = reg;
        this.trabajador = trabajador;
        this.tipo_falta = tipo_falta;
        this.fecha = fecha;
    }

    public Falta() {
    }

    public Falta(int conteoFaltas, String tipo_falta) {
        this.conteoFaltas = conteoFaltas;
        this.tipo_falta = tipo_falta;
    }


    public Falta(int trabajador) {
        this.trabajador = trabajador;
    }

    public int getReg() {
        return reg;
    }

    public void setReg(int reg) {
        this.reg = reg;
    }

    public int getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(int trabajador) {
        this.trabajador = trabajador;
    }

    public String getTipo_falta() {
        return tipo_falta;
    }

    public void setTipo_falta(String tipo_falta) {
        this.tipo_falta = tipo_falta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getConteoFaltas() {
        return conteoFaltas;
    }

    public void setConteoFaltas(int conteoFaltas) {
        this.conteoFaltas = conteoFaltas;
    }

}
