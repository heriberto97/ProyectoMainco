package sample.objetos.Compras;

import java.sql.Date;

public class Pago {
    private int reg;
    private Date fecha_pago;
    private Double pago;

    // Constructor para ver los pagos de una compra
    public Pago(int reg, Date fecha_pago, Double pago) {
        this.reg = reg;
        this.fecha_pago = fecha_pago;
        this.pago = pago;
    }

    public int getReg() {
        return reg;
    }
    public void setReg(int reg) {
        this.reg = reg;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }
    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public Double getPago() {
        return pago;
    }
    public void setPago(Double pago) {
        this.pago = pago;
    }
}
