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

    public void setOperario(String operario) {
        if (operario == null || operario.isBlank()) {
            throw new IllegalArgumentException("El operario no puede estar vacío.");
        }
        this.operario = operario;
    }

    public int getBultos() {
        return bultos;
    }

    public void setBultos(int bultos) {
        if (bultos < 0) {
            throw new IllegalArgumentException("La cantidad de bultos no puede ser negativa.");
        }
        this.bultos = bultos;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota == null ? "" : nota;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
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

    @Override
    public String obtenerResumen() {
        return "Despacho ID: " + getId_documento() + " | Remito Asociado Nº: " + remito.getNum_remito()
                + " | Operario: " + operario + " | Bultos: " + bultos + " | Entregado: " + (entregado ? "Sí" : "No");
    }

}
