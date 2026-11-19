package igu;
import logica.Usuarios;
import utils.Utiles;

public class Login {
    public static String loguear(){
        System.out.println("\n----Bienvenido al Sistema de Gestión de Usuarios----");
        System.out.println("\nIndique sus credenciales:");
        String usuario = Utiles.leerString("Usuario: ");
        String contrasena = Utiles.leerString("Contraseña: ");

        String rol = Usuarios.validarCredenciales(usuario, contrasena);
        if (rol == null) {
            System.out.println("Usuario o contraseña incorrectos.");
            return null;
        }
        System.out.println("Bienvenido al Sistema de Gestión de Usuarios. Su rol es: " + rol);
        return rol;
    }
}
