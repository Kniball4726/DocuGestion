package app;
import igu.Login;
import igu.MenuPrincipal;
public class Main {
    public static void main(String[] args) {
        try {
            while (true) {
                int intentos = 0;
                int maxIntentos = 3;
                String rol = null;

                while (rol == null && intentos < maxIntentos) {
                    rol = Login.loguear();
                    intentos++;

                    if (rol == null) {
                        System.out.println("Intento " + intentos + " de " + maxIntentos + " fallido.");
                        if (intentos < maxIntentos) {
                            int intentosRestantes = maxIntentos - intentos;
                            String mensaje = intentosRestantes == 1 ? "Le queda " : "Le quedan ";
                            String unidad = intentosRestantes == 1 ? " intento." : " intentos.";
                            System.out.println(mensaje + intentosRestantes + unidad);
                        } else {
                            System.out.println("Se agotaron los intentos. Aplicación cerrada.");
                        }
                    }
                }

                if (rol == null) {
                    return;
                }

                MenuPrincipal.MenuP(rol);
                System.out.println("Sesión cerrada. Volviendo al inicio de sesión.");
            }
        } catch (RuntimeException e) {
            String detalle = e.getMessage() == null ? "sin detalles adicionales" : e.getMessage();
            System.err.println("Error al iniciar la aplicación ("
                    + e.getClass().getSimpleName() + "): " + detalle);
        }
    }
}

