package logica;

import java.time.LocalDate;

public class Despacho extends Documento {
    private final Remito remito;
    private String operario;
    private int bultos;
    private String nota;
    private boolean entregado;

    public Despacho(Remito remito, LocalDate fecha_creacion, String operario, int bultos, String nota) {
        super("Despacho", fecha_creacion);
        if (remito == null) {
            throw new IllegalArgumentException("El remito no puede ser nulo.");
        }
        if (bultos < 0) {
            throw new IllegalArgumentException("La cantidad de bultos no puede ser negativa.");
        }

        this.remito = remito;
        this.operario = operario;
        this.bultos = bultos;
        this.nota = nota;
        remito.marcarComoPreparado();
    }

    public Remito getRemito() {
        return remito;
    }

    public String getOperario() {
        return operario;
    }

    public int getBultos() {
        return bultos;
    }

    public String getNota() {
        return nota;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void marcarComoEntregado() {
        this.entregado = true;
    }


    @Override
    public void mostrarInformacion() {
        System.out.println("Despacho ID: " + getId_documento());
        System.out.println("Fecha de creación: " + getFecha_creacion());
        System.out.println("Remito asociado:");
        remito.mostrarInformacion();
        System.out.println("Operario: " + operario);
        System.out.println("Bultos: " + bultos);
        System.out.println("Nota: " + nota);
        System.out.println("Entregado: " + (entregado ? "Sí" : "No"));
    }

}
