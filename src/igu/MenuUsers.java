package igu;

import persistencia.UsuariosCrud;
import utils.Utiles;

public class MenuUsers {

    public static void mostrarMenu(UsuariosCrud usuariosCrud) {
        int opcionUsuario;
        do {
            System.out.println("\n--- Gestión de usuarios ---");
            System.out.println("1.- Crear usuario");
            System.out.println("2.- Listar usuarios");
            System.out.println("3.- Buscar usuario");
            System.out.println("4.- Actualizar usuario");
            System.out.println("5.- Eliminar usuario");
            System.out.println("0.- Volver");
            opcionUsuario = Utiles.leerEntero("Seleccione una opción: ");

            switch (opcionUsuario) {
                case 1 -> {
                    mostrarEncabezado("Registrar usuario", "Crear usuario");
                    usuariosCrud.crearUsuarioDesdeConsola();
                }
                case 2 -> {
                    mostrarEncabezado("Consultar usuarios", "Listar usuarios");
                    usuariosCrud.listarUsuarios();
                }
                case 3 -> {
                    mostrarEncabezado("Buscar usuario", "Buscar usuario");
                    usuariosCrud.buscarUsuario();
                }
                case 4 -> {
                    mostrarEncabezado("Modificar usuario", "Actualizar usuario");
                    usuariosCrud.actualizarUsuario();
                }
                case 5 -> {
                    mostrarEncabezado("Eliminar usuario", "Eliminar usuario");
                    usuariosCrud.eliminarUsuario();
                }
                case 0 -> mostrarEncabezado("Volver al menú principal", "Volver");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcionUsuario != 0);
    }

    private static void mostrarEncabezado(String funcion, String titulo) {
        System.out.println("\nFunción: " + funcion + "\n");
        System.out.println("---- " + titulo + " ----");
    }
}
