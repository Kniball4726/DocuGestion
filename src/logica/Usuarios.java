package logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuarios extends Trabajador {

    private static int siguienteId = 1;
    private static final List<Usuarios> usuariosRegistrados = new ArrayList<>();

    static {
        new Usuarios("10000001", "Admin", "Sistema", "admin@example.com",
                "1111111111", "admin", 0, "admin", "admin123");
        new Usuarios("10000002", "Vendedor", "Demo", "vendedor@example.com",
                "2222222222", "vendedor", 0, "vendedor", "venta123");
    }

    private final int id_usuario;
    private String usuario;
    private String contrasena;

    public Usuarios(String dni, String nombre, String apellido, String email, String telefono, String cargo, double salario, String usuario, String contrasena) {
        super(dni, nombre, apellido, email, telefono, cargo, salario);
        this.id_usuario = siguienteId++;
        this.usuario = usuario;
        this.contrasena = contrasena;
        usuariosRegistrados.add(this);
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public static List<Usuarios> getUsuariosRegistrados() {
        return usuariosRegistrados;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
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

    public static String validarCredenciales(String usuario, String contrasena) {
        if (usuario == null || contrasena == null) {
            return null;
        }

        for (Usuarios registrado : usuariosRegistrados) {
            if (Objects.equals(registrado.usuario, usuario)
                    && Objects.equals(registrado.contrasena, contrasena)) {
                return registrado.cargo;
            }
        }
        return null;
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
