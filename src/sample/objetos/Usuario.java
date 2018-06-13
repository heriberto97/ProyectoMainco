package sample.objetos;

public class Usuario {
    int id;
    String nombre,usuario,apellido_p,apellido_m,contrasena,tipo_usuario;

    public Usuario(int id, String nombre, String usuario, String apellido_p, String apellido_m, String contrasena, String tipo_usuario) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.apellido_p = apellido_p;
        this.apellido_m = apellido_m;
        this.contrasena = contrasena;
        this.tipo_usuario = tipo_usuario;
    }

    public Usuario() {
    }

    public Usuario(String nombre, String usuario, String apellido_p, String apellido_m, String contrasena, String tipo_usuario) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.apellido_p = apellido_p;
        this.apellido_m = apellido_m;
        this.contrasena = contrasena;
        this.tipo_usuario = tipo_usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getApellido_p() {
        return apellido_p;
    }

    public void setApellido_p(String apellido_p) {
        this.apellido_p = apellido_p;
    }

    public String getApellido_m() {
        return apellido_m;
    }

    public void setApellido_m(String apellido_m) {
        this.apellido_m = apellido_m;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
}
