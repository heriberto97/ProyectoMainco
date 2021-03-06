package sample.objetos;

public class Trabajador {
    Integer id;
    String  nombre,
            apellido_paterno,
            apellido_materno,
            rfc,
            solicitud_empleo,
            estado,
            fechaigreso,
            fotoperfil,
            puesto;
    Double ahorro;
    public Trabajador(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Trabajador(Integer id, String nombre, String apellido_paterno) {
        this.id = id;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
    }

    public String nombreCompleto(){
        String nombre= this.nombre+ " "+ this.apellido_paterno+" "+ this.apellido_materno;
        return nombre;
    }

    public Trabajador(Integer id, String nombre, String apellido_paterno, String apellido_materno, String rfc, String solicitud_empleo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.rfc = rfc;
        this.solicitud_empleo = solicitud_empleo;

    }

    public Trabajador(Integer id, String nombre, String apellido_paterno, String apellido_materno, String rfc, String solicitud_empleo, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.rfc = rfc;
        this.solicitud_empleo = solicitud_empleo;
        this.estado = estado;
    }


    public Trabajador(String nombre, String apellido_paterno, String apellido_materno, String rfc, String solicitud_empleo) {

        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.rfc = rfc;
        this.solicitud_empleo = solicitud_empleo;
    }

    public Trabajador( String nombre, String apellido_paterno, String apellido_materno, String rfc) {

        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.rfc = rfc;
    }

    public Trabajador(Integer id, String nombre, String apellido_paterno, String apellido_materno, String rfc) {
        this.id = id;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.rfc = rfc;
    }

    public Trabajador(Integer id, String nombre, String apellido_paterno, String apellido_materno, String rfc, String solicitud_empleo, String estado,  String fotoperfil,String fechaigreso, String puesto) {
        this.id = id;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.rfc = rfc;
        this.solicitud_empleo = solicitud_empleo;
        this.estado = estado;
        this.fechaigreso = fechaigreso;
        this.fotoperfil = fotoperfil;
        this.puesto = puesto;
    }


    public Trabajador(Integer id, String nombre, String apellido_paterno, String apellido_materno, String rfc, String solicitud_empleo, String estado, String fechaigreso, String puesto) {
        this.id = id;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.rfc = rfc;
        this.solicitud_empleo = solicitud_empleo;
        this.estado = estado;
        this.fechaigreso = fechaigreso;
        this.puesto = puesto;
    }

    public Trabajador(Integer id, String nombre, String apellido_paterno, String apellido_materno, String rfc, String solicitud_empleo, String estado, String fechaigreso, String fotoperfil, String puesto, Double ahorro) {
        this.id = id;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.rfc = rfc;
        this.solicitud_empleo = solicitud_empleo;
        this.estado = estado;
        this.fechaigreso = fechaigreso;
        this.fotoperfil = fotoperfil;
        this.puesto = puesto;
        this.ahorro = ahorro;
    }

    public Trabajador() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getSolicitud_empleo() {
        return solicitud_empleo;
    }

    public void setSolicitud_empleo(String solicitud_empleo) {
        this.solicitud_empleo = solicitud_empleo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaigreso() {
        return fechaigreso;
    }

    public void setFechaigreso(String fechaigreso) {
        this.fechaigreso = fechaigreso;
    }

    public String getFotoperfil() {
        return fotoperfil;
    }

    public void setFotoperfil(String fotoperfil) {
        this.fotoperfil = fotoperfil;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Double getAhorro() {
        return ahorro;
    }

    public void setAhorro(Double ahorro) {
        this.ahorro = ahorro;
    }
}
