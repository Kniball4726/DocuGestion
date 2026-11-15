package logica;

import java.time.LocalDate;

public abstract class Documento {
    private static int siguienteId = 1;

    protected final int id_documento;
    protected String tipo;
    protected LocalDate fecha;

    public Documento(String tipo, LocalDate fecha) {
        this.id_documento = siguienteId++;
        this.tipo = tipo;
        this.fecha = fecha;
    }


    public int getId_documento() {
        return id_documento;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Override 
    public String toString(){
        return "\nID: " + id_documento + "\nTipo: " + tipo + "\nCreaciòn: " + fecha;
    }

    public abstract void mostrarInformacion();

}