package persistencia;

import java.util.ArrayList;
import java.util.List;
import logica.Cliente;
import utils.Utiles;

public class ClientesCrud {
    private final ArrayList<Cliente> clientes = new ArrayList<>();

    public Cliente crearCliente(String dni, String nombre, String apellido, String email, String telefono) {
        if (dni == null || dni.isBlank() || nombre == null || nombre.isBlank()
                || apellido == null || apellido.isBlank() || email == null || email.isBlank()
                || telefono == null || telefono.isBlank()) {
            throw new IllegalArgumentException("Todos los campos del cliente son obligatorios.");
        }
        if (buscarPorDni(dni) != null) {
            throw new IllegalArgumentException("Ya existe un cliente con ese DNI.");
        }

        Cliente cliente = new Cliente(dni.trim(), nombre.trim(), apellido.trim(), email.trim(), telefono.trim());
        clientes.add(cliente);
        return cliente;
    }

    public Cliente crearClienteDesdeConsola() {
        String dni = Utiles.leerString("DNI: ");
        String nombre = Utiles.leerString("Nombre: ");
        String apellido = Utiles.leerString("Apellido: ");
        String email = Utiles.leerString("Email: ");
        String telefono = Utiles.leerString("Teléfono: ");

        Cliente cliente = crearCliente(dni, nombre, apellido, email, telefono);
        System.out.println("Cliente creado correctamente:");
        cliente.mostrarInformacion();
        return cliente;
    }

    public Cliente buscarOCrearClienteParaDespacho() {
        String dni = Utiles.leerString("DNI del cliente: ");
        Cliente cliente = buscarPorDni(dni);
        if (cliente != null) {
            System.out.println("Cliente encontrado:");
            cliente.mostrarInformacion();
            return cliente;
        }

        System.out.println("No se encontró un cliente con ese DNI. Ingrese sus datos para registrarlo.");
        String nombre = Utiles.leerString("Nombre: ");
        String apellido = Utiles.leerString("Apellido: ");
        String email = Utiles.leerString("Email: ");
        String telefono = Utiles.leerString("Teléfono: ");
        cliente = crearCliente(dni, nombre, apellido, email, telefono);
        System.out.println("Cliente registrado. Continuando con la carga del despacho.");
        return cliente;
    }

    public List<Cliente> listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return List.copyOf(clientes);
        }

        for (Cliente cliente : clientes) {
            cliente.mostrarInformacion();
            System.out.println();
        }
        return List.copyOf(clientes);
    }

    public Cliente buscarPorId(int idCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getId_cliente() == idCliente || cliente.getId_persona() == idCliente) {
                return cliente;
            }
        }
        return null;
    }

    public Cliente buscarPorDni(String dni) {
        if (dni == null || dni.isBlank()) {
            return null;
        }
        for (Cliente cliente : clientes) {
            if (cliente.getDni().equalsIgnoreCase(dni.trim())) {
                return cliente;
            }
        }
        return null;
    }

    public void buscarCliente() {
        String dni = Utiles.leerString("Ingrese el DNI del cliente: ");
        Cliente cliente = buscarPorDni(dni);
        if (cliente == null) {
            System.out.println("No existe un cliente con ese DNI.");
            return;
        }
        cliente.mostrarInformacion();
    }

    public void actualizarCliente() {
        int id = Utiles.leerEntero("Ingrese el ID del cliente a actualizar: ");
        Cliente cliente = buscarPorId(id);
        if (cliente == null) {
            System.out.println("No existe un cliente con ese ID.");
            return;
        }

        int opcion;
        do {
            System.out.println("1.- Modificar DNI");
            System.out.println("2.- Modificar nombre");
            System.out.println("3.- Modificar apellido");
            System.out.println("4.- Modificar email");
            System.out.println("5.- Modificar teléfono");
            System.out.println("0.- Volver");
            opcion = Utiles.leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> {
                    String dni = Utiles.leerString("Nuevo DNI: ");
                    Cliente existente = buscarPorDni(dni);
                    if (existente != null && existente != cliente) {
                        throw new IllegalArgumentException("Ya existe un cliente con ese DNI.");
                    }
                    if (dni.isBlank()) {
                        throw new IllegalArgumentException("El DNI no puede estar vacío.");
                    }
                    cliente.setDni(dni.trim());
                }
                case 2 -> cliente.setNombre(leerCampoObligatorio("Nuevo nombre: "));
                case 3 -> cliente.setApellido(leerCampoObligatorio("Nuevo apellido: "));
                case 4 -> cliente.setEmail(leerCampoObligatorio("Nuevo email: "));
                case 5 -> cliente.setTelefono(leerCampoObligatorio("Nuevo teléfono: "));
                case 0 -> {
                }
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        System.out.println("Cliente actualizado:");
        cliente.mostrarInformacion();
    }

    public void eliminarCliente() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes para eliminar.");
            return;
        }
        System.out.println("Clientes disponibles para eliminar:");
        listarClientes();
        int id = Utiles.leerEntero("Ingrese el ID del cliente a eliminar: ");
        Cliente cliente = buscarPorId(id);
        if (cliente == null) {
            System.out.println("No existe un cliente con ese ID.");
            return;
        }
        clientes.remove(cliente);
        System.out.println("Cliente eliminado correctamente.");
    }

    private String leerCampoObligatorio(String mensaje) {
        String valor = Utiles.leerString(mensaje);
        if (valor.isBlank()) {
            throw new IllegalArgumentException("El campo no puede estar vacío.");
        }
        return valor.trim();
    }
}
