package igu;
import utils.*;

public class MenuPrincipal {
    public static void MenuP(String rol){
        try {
            while (true) {
                System.out.println("Bienvenido al Menú Principal");
                
                if (rol.equals("admin")) {
                    
                    System.out.println("1.- Ver pedidos");
                    System.out.println("2.- Modificar pedido");
                    System.out.println("3.- Eliminar pedido");
                    System.out.println("4.- Gestionar usuarios");
                    System.out.println("0.- Salir");
                }
                else{
                    System.out.println("1.- Cargar pedido");
                    System.out.println("2.- Ver pedidos");
                    System.out.println("3.- Modificar pedido");
                    System.out.println("0.- Salir");
                }
                int seleccion = Utiles.leerEntero();

                switch (seleccion) {
                    case 1:
                        System.out.println("Cargando pedido...");
                        break;
                
                    default:
                        break;
                }


            }
        } catch (Exception e) {
        }
        
    }
}
