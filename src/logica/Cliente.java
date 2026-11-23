package logica;

public class Cliente extends Persona{
    private static int siguienteId = 1;

    protected final int id_cliente;

    public Cliente(String dni, String nombre, String apellido, String email, String telefono){
        super(dni, nombre, apellido, email, telefono);
        this.id_cliente = siguienteId++;
    }

    
    public int getId_cliente() {
        return id_cliente;
    }

    @Override 
    public String toString(){
        return "Cliente: " + id_cliente + ", " + super.toString();
    }

    @Override
    public void mostrarInformacion() {
        System.out.println(toString());
    }


}
