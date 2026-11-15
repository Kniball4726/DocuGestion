package logica;

public class DetalleRemito {
    private final Producto producto;
    private final int cantidad;

    public DetalleRemito(Producto producto, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }

        this.producto = new Producto(producto.getCodigo(), producto.getNombre());
        this.cantidad = cantidad;
    }

    public DetalleRemito(Producto producto) {
        this(producto, 0);
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    @Override
    public String toString() {
        return "Producto: " + producto.getNombre()
                + ", Código: " + producto.getCodigo()
                + ", Cantidad enviada: " + cantidad;
    }
}
