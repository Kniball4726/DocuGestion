package persistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import logica.Cliente;
import logica.Despacho;
import logica.Documento;
import logica.Producto;
import logica.Remito;
import utils.Utiles;

public class DespachoCrud {
    private final List<Despacho> despachos = new ArrayList<>();
    private final ClientesCrud clientesCrud = new ClientesCrud();

    public ClientesCrud getClientesCrud() {
        return clientesCrud;
    }

    public Despacho crearDespacho(Remito remito, String operario, int bultos, String nota) {
        return crearDespacho(remito, "", operario, bultos, nota);
    }

    public Despacho crearDespacho(Remito remito, String cliente, String operario, int bultos, String nota) {
        if (remito == null) {
            throw new IllegalArgumentException("El remito no puede ser nulo.");
        }
        if (remito.isPreparado()) {
            throw new IllegalStateException("El remito ya tiene un despacho asociado.");
        }

        Despacho despacho = new Despacho(remito, LocalDate.now(), cliente, operario, bultos, nota);
        despachos.add(despacho);
        return despacho;
    }

    public void crearDespacho() {
        Cliente cliente = clientesCrud.buscarOCrearClienteParaDespacho();
        int numeroRemito = Utiles.leerEntero("Número del remito (0 para autoincremental): ");
        Remito remito = new Remito(numeroRemito);

        System.out.println("\n--- Carga de productos en el Remito ---");
        String agregar;
        do {
            int cod = Utiles.leerEntero("Código del producto (0 para autoincremental): ");
            String nom = Utiles.leerString("Nombre del producto: ");
            int cant = Utiles.leerEntero("Cantidad (0 si no desea especificar ahora): ");

            // Demostración de sobrecarga de constructores de Producto
            Producto producto;
            if (cant > 0) {
                producto = new Producto(cod, nom, cant); // Constructor sobrecargado con 3 parámetros
            } else {
                producto = new Producto(cod, nom);       // Constructor sobrecargado con 2 parámetros
            }

            // Demostración de composición: Remito -> Detalle -> Producto
            remito.agregarProducto(producto);

            agregar = Utiles.leerString("¿Desea agregar otro producto al remito? (s/n): ");
        } while (agregar.trim().equalsIgnoreCase("s"));

        System.out.println("\n--- Datos del Despacho ---");
        String operario = Utiles.leerString("Operario: ");
        int bultos = Utiles.leerEntero("Cantidad de bultos: ");
        String nota = Utiles.leerString("Nota: ");

        Despacho despacho = crearDespacho(remito,
                cliente.getNombre() + " " + cliente.getApellido() + " (DNI: " + cliente.getDni() + ")",
                operario, bultos, nota);
        System.out.println("\nDespacho y remito creados con éxito:");
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

    // Demostración explícita de Polimorfismo con la clase abstracta Documento y la interfaz Imprimible
    public void listarHistorialDocumentos() {
        if (despachos.isEmpty()) {
            System.out.println("No hay documentos cargados.");
            return;
        }

        // Colección polimórfica que almacena diferentes subtipos de Documento
        List<Documento> documentos = new ArrayList<>();
        for (Despacho d : despachos) {
            documentos.add(d.getRemito()); // Subclase Remito
            documentos.add(d);             // Subclase Despacho
        }

        System.out.println("\n=== Historial General de Documentos (Polimorfismo) ===");
        for (Documento doc : documentos) {
            System.out.println("Resumen: " + doc.obtenerResumen()); // Invocación polimórfica de la interfaz Imprimible
            doc.mostrarInformacion();                              // Invocación polimórfica de método abstracto
            System.out.println("--------------------------------------------------");
        }
    }

    public Despacho buscarPorId(int idDespacho) {
        for (Despacho despacho : despachos) {
            if (despacho.getId_despacho() == idDespacho || despacho.getId_documento() == idDespacho) {
                return despacho;
            }
        }
        return null;
    }

    public Despacho buscarPorNumeroRemito(int numeroRemito) {
        for (Despacho despacho : despachos) {
            if (despacho.getRemito().getNum_remito() == numeroRemito || despacho.getRemito().getId_remito() == numeroRemito) {
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
