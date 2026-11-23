package igu;

import persistencia.ClientesCrud;
import utils.Utiles;

public class MenuClientes {

    public static void mostrarMenu(ClientesCrud clientesCrud) {
        int opcion;
        boolean limpiarSiguienteMenu = true;
        do {
            if (limpiarSiguienteMenu) {
                Utiles.limpiarConsola();
            }
            limpiarSiguienteMenu = true;
            System.out.println("\n--- Gestión de clientes ---");
            System.out.println("1.- Crear cliente");
            System.out.println("2.- Listar clientes");
            System.out.println("3.- Buscar cliente");
            System.out.println("4.- Actualizar cliente");
            System.out.println("5.- Eliminar cliente");
            System.out.println("0.- Volver");
            opcion = Utiles.leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> clientesCrud.crearClienteDesdeConsola();
                case 2 -> {
                    clientesCrud.listarClientes();
                    limpiarSiguienteMenu = false;
                }
                case 3 -> {
                    clientesCrud.buscarCliente();
                    limpiarSiguienteMenu = false;
                }
                case 4 -> clientesCrud.actualizarCliente();
                case 5 -> clientesCrud.eliminarCliente();
                case 0 -> {
                }
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }
}
