package igu;

import java.util.Locale;
import persistencia.DespachoCrud;
import persistencia.UsuariosCrud;
import utils.Utiles;

public class MenuPrincipal {
    
    public static void MenuP(String rol, DespachoCrud despachoCrud){
        if (rol == null || rol.isBlank()) {
            System.out.println("No se pudo determinar el rol del usuario.");
            return;
        }

        rol = rol.trim().toLowerCase(Locale.ROOT);
        if (!rol.equals("admin") && !rol.equals("vendedor")
                && !rol.equals("operario") && !rol.equals("logistica")) {
            System.out.println("El rol '" + rol + "' no tiene un menú disponible.");
            return;
        }

        UsuariosCrud usuariosCrud = new UsuariosCrud();
        boolean limpiarSiguienteMenu = true;

        while (true) {
            if (limpiarSiguienteMenu) {
                Utiles.limpiarConsola();
            }
            limpiarSiguienteMenu = true;
            System.out.println("\n----- Bienvenido al Menú Principal de " + rol + " -----");

            switch (rol) {
                case "admin" -> {
                    System.out.println("1.- Ver despachos");
                    System.out.println("2.- Eliminar despacho");
                    System.out.println("3.- Gestionar usuarios");
                    System.out.println("4.- Ver historial general de documentos (Polimorfismo)");
                    System.out.println("5.- Gestionar clientes");
                    System.out.println("0.- Salir");
                }
                case "vendedor", "logistica" -> {
                    System.out.println("1.- Ver despachos");
                    System.out.println("2.- Ver historial general de documentos (Polimorfismo)");
                    System.out.println("0.- Salir");
                }
                case "operario" -> {
                    System.out.println("1.- Cargar despacho");
                    System.out.println("2.- Ver despachos");
                    System.out.println("3.- Modificar despacho");
                    System.out.println("4.- Ver historial general de documentos (Polimorfismo)");
                    System.out.println("0.- Salir");
                }
                default -> throw new IllegalStateException("Rol no validado: " + rol);
            }

            int seleccion = leerEntero("Seleccione una opción: ");

            try {
                switch (seleccion) {
                    case 1 -> {
                        switch (rol) {
                            case "admin", "vendedor", "logistica" -> {
                                mostrarEncabezado("Consultar despachos", "Ver despachos");
                                despachoCrud.listarDespachos();
                                limpiarSiguienteMenu = false;
                            }
                            case "operario" -> {
                                mostrarEncabezado("Registrar despacho", "Cargar despacho");
                                despachoCrud.crearDespacho();
                            }
                            default -> throw new IllegalStateException("Rol no validado: " + rol);
                        }
                    }
                    case 2 -> {
                        switch (rol) {
                            case "admin" -> {
                                mostrarEncabezado("Eliminar despacho", "Eliminar despacho");
                                despachoCrud.eliminarDespacho();
                            }
                            case "operario" -> {
                                mostrarEncabezado("Consultar despachos", "Ver despachos");
                                despachoCrud.listarDespachos();
                                limpiarSiguienteMenu = false;
                            }
                            case "vendedor", "logistica" -> {
                                mostrarEncabezado("Consultar documentos", "Historial de documentos");
                                despachoCrud.listarHistorialDocumentos();
                                limpiarSiguienteMenu = false;
                            }
                            default -> System.out.println("Opción no disponible para este rol.");
                        }
                    }
                    case 3 -> {
                        switch (rol) {
                            case "admin" -> {
                                mostrarEncabezado("Administrar usuarios", "Gestionar usuarios");
                                MenuUsers.mostrarMenu(usuariosCrud);
                            }
                            case "operario" -> {
                                mostrarEncabezado("Modificar despacho", "Modificar despacho");
                                despachoCrud.actualizarDespacho();
                            }
                            default -> System.out.println("Opción no disponible para este rol.");
                        }
                    }
                    case 5 -> {
                        if (rol.equals("admin")) {
                            mostrarEncabezado("Administrar clientes", "Gestionar clientes");
                            MenuClientes.mostrarMenu(despachoCrud.getClientesCrud());
                        } else {
                            System.out.println("Opción no disponible para este rol.");
                        }
                    }
                    case 4 -> {
                        switch (rol) {
                            case "admin", "operario" -> {
                                mostrarEncabezado("Consultar documentos", "Historial de documentos");
                                despachoCrud.listarHistorialDocumentos();
                                limpiarSiguienteMenu = false;
                            }
                            default -> System.out.println("Opción no disponible para este rol.");
                        }
                    }
                    case 0 -> {
                        mostrarEncabezado("Cerrar sesión", "Salir");
                        return;
                    }
                    default -> System.out.println("Opción inválida. Seleccione una opción del menú.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("No se pudo completar la operación: " + e.getMessage());
            }
        }
    }

    private static void mostrarEncabezado(String funcion, String titulo) {
        System.out.println("\nFunción: " + funcion + "\n");
        System.out.println("---- " + titulo + " ----");
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                return Utiles.leerEntero(mensaje);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }
}
