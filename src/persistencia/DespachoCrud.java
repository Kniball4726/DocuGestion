package persistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import logica.Despacho;
import logica.Remito;
import utils.Utiles;

public class DespachoCrud {
    private final List<Despacho> despachos = new ArrayList<>();

    public Despacho crearDespacho(Remito remito, String operario, int bultos, String nota) {
        if (remito == null) {
            throw new IllegalArgumentException("El remito no puede ser nulo.");
        }
        if (remito.isPreparado()) {
            throw new IllegalStateException("El remito ya tiene un despacho asociado.");
        }

        Despacho despacho = new Despacho(remito, LocalDate.now(), operario, bultos, nota);
        despachos.add(despacho);
        return despacho;
    }

    public void crearDespacho() {
        int numeroRemito = Utiles.leerEntero("Número del remito: ");
        Remito remito = new Remito(numeroRemito);

        String operario = Utiles.leerString("Operario: ");
        int bultos = Utiles.leerEntero("Cantidad de bultos: ");
        String nota = Utiles.leerString("Nota: ");

        Despacho despacho = crearDespacho(remito, operario, bultos, nota);
        System.out.println("Despacho creado:");
        despacho.mostrarInformacion();
    }

    public List<Despacho> getDespachos() {
        return List.copyOf(despachos);
    }

    public void listarDespachos() {
        if (despachos.isEmpty()) {
            System.out.println("No hay despachos cargados.");
            return;
        }

        for (Despacho despacho : despachos) {
            despacho.mostrarInformacion();
            System.out.println();
        }
    }

    public Despacho buscarPorId(int idDespacho) {
        for (Despacho despacho : despachos) {
            if (despacho.getId_documento() == idDespacho) {
                return despacho;
            }
        }
        return null;
    }

    public Despacho buscarPorNumeroRemito(int numeroRemito) {
        for (Despacho despacho : despachos) {
            if (despacho.getRemito().getNum_remito() == numeroRemito) {
                return despacho;
            }
        }
        return null;
    }

    public void buscarDespacho() {
        int idDespacho = Utiles.leerEntero("Ingrese el ID del despacho a buscar: ");
        Despacho despacho = buscarPorId(idDespacho);

        if (despacho == null) {
            System.out.println("No se encontró un despacho con ese ID.");
            return;
        }

        despacho.mostrarInformacion();
    }

    public void actualizarDespacho() {
        int idDespacho = Utiles.leerEntero("Ingrese el ID del despacho a actualizar: ");
        Despacho despacho = buscarPorId(idDespacho);

        if (despacho == null) {
            System.out.println("No se encontró un despacho con ese ID.");
            return;
        }

        int opcion;
        do {
            System.out.println("1.- Modificar operario");
            System.out.println("2.- Modificar bultos");
            System.out.println("3.- Modificar nota");
            System.out.println("4.- Marcar como entregado");
            System.out.println("0.- Terminar");
            opcion = Utiles.leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> despacho.setOperario(Utiles.leerString("Nuevo operario: "));
                case 2 -> despacho.setBultos(Utiles.leerEntero("Nueva cantidad de bultos: "));
                case 3 -> despacho.setNota(Utiles.leerString("Nueva nota: "));
                case 4 -> {
                    despacho.marcarComoEntregado();
                    System.out.println("El despacho fue marcado como entregado.");
                }
                case 0 -> {
                }
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        System.out.println("Despacho actualizado:");
        despacho.mostrarInformacion();
    }

    public void actualizarDespacho(int idDespacho, String operario, int bultos, String nota) {
        Despacho despacho = buscarPorId(idDespacho);
        if (despacho == null) {
            throw new IllegalArgumentException("No existe un despacho con el ID indicado.");
        }

        despacho.setOperario(operario);
        despacho.setBultos(bultos);
        despacho.setNota(nota);
    }

        public void eliminarDespacho() {
        if (despachos.isEmpty()) {
            System.out.println("No hay despachos para eliminar.");
            return;
        }

        System.out.println("Despachos disponibles para eliminar:");
        listarDespachos();
        int idDespacho = Utiles.leerEntero("Ingrese el ID del despacho a eliminar: ");
        Despacho despacho = buscarPorId(idDespacho);

        if (despacho == null) {
            System.out.println("No se encontró un despacho con ese ID.");
            return;
        }

        despachos.remove(despacho);
        despacho.getRemito().desmarcarComoPreparado();
        System.out.println("Despacho eliminado.");
    }

    public void eliminarDespacho(Despacho despacho) {
        if (despacho == null) {
            throw new IllegalArgumentException("El despacho no puede ser nulo.");
        }
        despachos.remove(despacho);
        despacho.getRemito().desmarcarComoPreparado();
    }

}
