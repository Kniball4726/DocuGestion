package logica;

public class Trabajador extends Persona {
    private static int siguienteId = 1;

    protected final int id_trabajador;
    protected String cargo;
    protected double salario;


    protected Trabajador(String dni, String nombre, String apellido, String email, String telefono, String cargo, double salario) {
        super(dni, nombre, apellido, email, telefono);
        this.id_trabajador = siguienteId++;
        this.cargo = cargo;
        this.salario = salario;
    }

    public int getId_trabajador() {
        return id_trabajador;
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

    @Override 
    public String toString(){
        return super.toString() +
                "\nID Trabajador: " + id_trabajador +
                "\nCargo: " + cargo +
                "\nSalario: " + salario;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println(this.toString());
    }

    @Override
    public String obtenerResumen() {
        return "Trabajador ID: " + id_trabajador + " | " + getNombre() + " " + getApellido() + " (Cargo: " + cargo + ")";
    }

}
