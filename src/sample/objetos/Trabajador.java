package sample.objetos;

public class Trabajador {
    Integer id;
    String  nombre,
            apellido_paterno,
            apellido_materno,
            rfc,
            solicitud_empleo;

    public Trabajador(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Trabajador(Integer id, String nombre, String apellido_paterno, String apellido_materno, String rfc, String solicitud_empleo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.rfc = rfc;
        this.solicitud_empleo = solicitud_empleo;
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
}
