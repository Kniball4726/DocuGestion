package logica;

import java.util.ArrayList;
import java.time.LocalDate;

public class Remito extends Documento {

    private int numero;
    private String cliente;
    private LocalDate fecha_entrega;
    private int bultos;
    private String operario;
    private ArrayList<DetalleRemito> detalles = new ArrayList<>();

    public Remito(int numero, String cliente, LocalDate fecha_entrega, int bultos, String operario) {
        super("Remito", LocalDate.now());
        this.numero = numero;
        this.cliente = cliente;
        this.fecha_entrega = fecha_entrega;
        this.bultos = bultos;
        this.operario = operario;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        detalles.add(new DetalleRemito(producto, cantidad));
    }

    public void mostrarProductos() {
        for (DetalleRemito detalle : detalles) {
            System.out.println(detalle);
        }
    }

    public int getNumero() {
        return numero;
    }

    public String getCliente() {
        return cliente;
    }

    

    @Override
    public void mostrarInformacion() {
        System.out.println("Número: " + numero);
        System.out.println("Cliente: " + cliente);
        System.out.println("Fecha de entrega: " + fecha_entrega);
        System.out.println("Bultos: " + bultos);
        System.out.println("Operario: " + operario);
        mostrarProductos();
    }

}