package persistencia;

import java.util.List;
import logica.Usuarios;
import utils.Utiles;

public class UsuariosCrud {

    public Usuarios crearUsuario(String dni, String nombre, String apellido, String email,
                                String telefono, String cargo, double salario,
                                String usuario, String contrasena) {
        if (dni == null || dni.isBlank() || nombre == null || nombre.isBlank() ||
                apellido == null || apellido.isBlank() || email == null || email.isBlank() ||
                telefono == null || telefono.isBlank() || cargo == null || cargo.isBlank() ||
                usuario == null || usuario.isBlank() || contrasena == null || contrasena.isBlank()) {
            throw new IllegalArgumentException("Todos los campos obligatorios deben completarse.");
        }

        if (buscarPorUsuario(usuario) != null) {
            throw new IllegalArgumentException("Ya existe un usuario con ese nombre.");
        }

        return new Usuarios(dni, nombre, apellido, email, telefono, cargo, salario, usuario, contrasena);
    }

    public void crearUsuarioDesdeConsola() {
        String dni = Utiles.leerString("DNI: ");
        String nombre = Utiles.leerString("Nombre: ");
        String apellido = Utiles.leerString("Apellido: ");
        String email = Utiles.leerString("Email: ");
        String telefono = Utiles.leerString("Teléfono: ");
        String cargo = Utiles.leerString("Cargo: ");
        double salario = Utiles.leerDouble("Salario: ");
        String usuario = Utiles.leerString("Usuario: ");
        String contrasena = Utiles.leerString("Contraseña: ");

        Usuarios creado = crearUsuario(dni, nombre, apellido, email, telefono, cargo, salario, usuario, contrasena);
        System.out.println("Usuario creado correctamente:");
        creado.mostrarInformacion();
    }

    public List<Usuarios> listarUsuarios() {
        List<Usuarios> usuarios = Usuarios.getUsuariosRegistrados();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return usuarios;
        }

        for (Usuarios usuario : usuarios) {
            usuario.mostrarInformacion();
            System.out.println();
        }
        return usuarios;
    }

    public Usuarios buscarPorId(int idUsuario) {
        for (Usuarios usuario : Usuarios.getUsuariosRegistrados()) {
            if (usuario.getId_usuario() == idUsuario || usuario.getId_trabajador() == idUsuario || usuario.getId_persona() == idUsuario) {
                return usuario;
            }
        }
        return null;
    }

    public Usuarios buscarPorUsuario(String usuario) {
        if (usuario == null || usuario.isBlank()) {
            return null;
        }

        for (Usuarios registrado : Usuarios.getUsuariosRegistrados()) {
            if (registrado.getUsuario().equalsIgnoreCase(usuario.trim())) {
                return registrado;
            }
        }
        return null;
    }

    public void buscarUsuario() {
        int id = Utiles.leerEntero("Ingrese el ID del usuario a buscar: ");
        Usuarios usuario = buscarPorId(id);
        if (usuario == null) {
            System.out.println("No existe un usuario con ese ID.");
            return;
        }
        usuario.mostrarInformacion();
    }

    public void actualizarUsuario() {
        int id = Utiles.leerEntero("Ingrese el ID del usuario a actualizar: ");
        Usuarios usuario = buscarPorId(id);
        if (usuario == null) {
            System.out.println("No existe un usuario con ese ID.");
            return;
        }

        int opcion;
        do {
            Utiles.limpiarConsola();
            System.out.println("1.- Modificar nombre");
            System.out.println("2.- Modificar apellido");
            System.out.println("3.- Modificar email");
            System.out.println("4.- Modificar teléfono");
            System.out.println("5.- Modificar cargo");
            System.out.println("6.- Modificar salario");
            System.out.println("7.- Modificar usuario");
            System.out.println("8.- Modificar contraseña");
            System.out.println("0.- Volver al menú principal");
            opcion = Utiles.leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> usuario.setNombre(Utiles.leerString("Nuevo nombre: "));
                case 2 -> usuario.setApellido(Utiles.leerString("Nuevo apellido: "));
                case 3 -> usuario.setEmail(Utiles.leerString("Nuevo email: "));
                case 4 -> usuario.setTelefono(Utiles.leerString("Nuevo teléfono: "));
                case 5 -> usuario.setCargo(Utiles.leerString("Nuevo cargo: "));
                case 6 -> usuario.setSalario(Utiles.leerDouble("Nuevo salario: "));
                case 7 -> {
                    String nuevoUsuario = Utiles.leerString("Nuevo nombre de usuario: ");
                    if (buscarPorUsuario(nuevoUsuario) != null && !nuevoUsuario.equalsIgnoreCase(usuario.getUsuario())) {
                        throw new IllegalArgumentException("Ya existe un usuario con ese nombre.");
                    }
                    usuario.setUsuario(nuevoUsuario);
                }
                case 8 -> usuario.setContrasena(Utiles.leerString("Nueva contraseña: "));
                case 0 -> {
                }
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        System.out.println("Usuario actualizado:");
        usuario.mostrarInformacion();
    }

    public void eliminarUsuario() {
        if (Usuarios.getUsuariosRegistrados().isEmpty()) {
            System.out.println("No hay usuarios para eliminar.");
            return;
        }

        System.out.println("Usuarios disponibles para eliminar:");
        listarUsuarios();
        int id = Utiles.leerEntero("Ingrese el ID del usuario a eliminar: ");
        Usuarios usuario = buscarPorId(id);
        if (usuario == null) {
            System.out.println("No existe un usuario con ese ID.");
            return;
        }

        if (usuario.getCargo().equalsIgnoreCase("admin") && Usuarios.getUsuariosRegistrados().stream().filter(u -> u.getCargo().equalsIgnoreCase("admin")).count() == 1) {
            System.out.println("No se puede eliminar el único administrador activo.");
            return;
        }

        Usuarios.getUsuariosRegistrados().remove(usuario);
        System.out.println("Usuario eliminado correctamente.");
    }

    public void eliminarUsuario(int idUsuario) {
        Usuarios usuario = buscarPorId(idUsuario);
        if (usuario == null) {
            throw new IllegalArgumentException("No existe un usuario con ese ID.");
        }
        if (usuario.getCargo().equalsIgnoreCase("admin") && Usuarios.getUsuariosRegistrados().stream().filter(u -> u.getCargo().equalsIgnoreCase("admin")).count() == 1) {
            throw new IllegalStateException("No se puede eliminar el único administrador activo.");
        }
        Usuarios.getUsuariosRegistrados().remove(usuario);
    }

    
}
