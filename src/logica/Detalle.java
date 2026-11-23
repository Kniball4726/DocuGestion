package logica;

public class Detalle {
    private static int siguienteId = 1;

    private final int id_detalle;
    private final Producto producto;

    public Detalle(Producto producto) {
        this.id_detalle = siguienteId++;
        this.producto = producto;
    }

    public int getId_detalle() {
        return id_detalle;
    }
    
    public Producto getProducto() {
        return producto;
    }

    @Override
    public String toString() {
        return    "\nProducto: " + producto.getNombre()
                + "\nID Producto: " + producto.getId_producto()
                + "\nCódigo: " + producto.getCodigo()
                + "\nCantidad enviada: " + producto.getCantidad();
    }
}
