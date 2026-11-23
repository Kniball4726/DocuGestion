package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Remito extends Documento {
	private static int siguienteId = 1;

	private final int id_remito;
	private int num_remito;
	private LocalDate fecha_entrega;
	private ArrayList<Detalle> detalles = new ArrayList<>();
	private boolean preparado;

	public Remito() {
		super("Remito", LocalDate.now());
		this.id_remito = siguienteId++;
		this.num_remito = this.id_remito;
		this.fecha_entrega = getFecha_creacion().plusDays(1);
	}

	public Remito(int num_remito) {
		super("Remito", LocalDate.now());
		this.id_remito = siguienteId++;
		this.num_remito = (num_remito <= 0) ? this.id_remito : num_remito;
		this.fecha_entrega = getFecha_creacion().plusDays(1);
	}

	public int getId_remito() {
		return id_remito;
	}

	public void agregarProducto(Producto producto) {
		detalles.add(new Detalle(producto));
	}

	public List<Detalle> getDetalles() {
		return List.copyOf(detalles);
	}

	public void actualizarProducto(int indice, Producto producto) {
		detalles.set(indice, new Detalle(producto));
	}

	public void eliminarProducto(int indice) {
		detalles.remove(indice);
	}

	public int getNum_remito() {
		return num_remito;
	}

	public void setNum_remito(int num_remito) {
		this.num_remito = num_remito;
	}

	public LocalDate getFecha_entrega() {
		return fecha_entrega;
	}

	public boolean isPreparado() {
		return preparado;
	}

	public void marcarComoPreparado() {
		if (preparado) {
			throw new IllegalStateException("Ya se creó un despacho para este remito.");
		}
		this.preparado = true;
	}

	public void desmarcarComoPreparado() {
		this.preparado = false;
	}

	
	@Override
	public String toString() {
		return super.toString() + "\nID Remito: " + id_remito + "\nNum_remito: " + num_remito + "\nFecha_entrega: " + fecha_entrega + "\nProductos: " + detalles
				+ "\nPreparado: " + preparado;
	}

	@Override
	public void mostrarInformacion() {
		System.out.println("Número: " + num_remito);
		System.out.println("Fecha de creación: " + getFecha_creacion());
		System.out.println("Fecha de entrega: " + fecha_entrega);
		System.out.println("Preparado: " + (preparado ? "Sí" : "No"));
		System.out.println("Productos:");
		for (Detalle detalle : detalles) {
			System.out.println(detalle);
		}
	}

	@Override
	public String obtenerResumen() {
		return "Remito ID: " + id_remito + " (Nº " + num_remito + ") | Entrega: " + fecha_entrega + " | Ítems: " + detalles.size() + " | Preparado: " + (preparado ? "Sí" : "No");
	}
}

