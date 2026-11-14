package logica;

public class Usuarios extends Trabajador {

    private static int siguienteId = 1;

    private final int id_usuario;
    private String usuario;
    private String contrasena;



    public Usuarios(String dni, String nombre, String apellido, String email, String telefono, String cargo, double salario, String usuario, String contrasena) {
        super(dni, nombre, apellido, email, telefono, cargo, salario);
        this.id_usuario = siguienteId++;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override 
    public String toString() {
        return super.toString() +
                "\nID Usuario: " + id_usuario +
                "\nUsuario: " + usuario;
    }

    @Override 
    public void mostrarInformacion() {
        System.out.println(this.toString());
    }

}
