package logica;

public abstract class Persona implements Imprimible {
    private static int siguienteId = 1;

    protected final int id_persona;
    private String dni;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;

    public Persona(String dni, String nombre, String apellido, String email, String telefono) {
        this.id_persona = siguienteId++;
        this.dni = dni; 
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    public int getId_persona() {
        return id_persona;
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

    @Override 
    public String toString(){
        return "\nDNI: " + dni +
                "\nNombre: " + nombre +
                "\nApellido: " + apellido +
                "\nEmail: " + email +
                "\nTelefono: " + telefono;
    }
    
    @Override
    public abstract void mostrarInformacion();

    @Override
    public String obtenerResumen() {
        return "Persona: " + nombre + " " + apellido + " (DNI: " + dni + ")";
    }
}
