package logica;

public class Producto {
    private static int siguienteId = 1;

    private final int id_producto;
    private int codigo;
    private String nombre;
    private int cantidad;
    

    public Producto() {
        this.id_producto = siguienteId++;
        this.codigo = this.id_producto;
        this.cantidad = 0;
    }

    public Producto(String nombre) {
        this(0, nombre, 0);
    }

    public Producto(String nombre, int cantidad) {
        this(0, nombre, cantidad);
    }
    
    public Producto(int codigo, String nombre) {
        this(codigo, nombre, 0);
    }

    public Producto(int codigo, String nombre, int cantidad) {
        this.id_producto = siguienteId++;
        this.codigo = (codigo <= 0) ? this.id_producto : codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public int getId_producto() {
        return id_producto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    


    @Override 
    public String toString(){
        return "\nCódigo: " + codigo + "\nNombre: " + nombre + "\nCantidad: " + cantidad;
    }

    

}
