package igu;

public class MenuPrincipal {
    public static void MenuP(String rol){
        try {
            while (true) {
                System.out.println("Bienvenido al Menú Principal");
                System.out.println("1.- Cargar pedido");
                System.out.println("2.- Ver pedidos");
                System.out.println("3.- Modificar pedido");
                System.out.println("4.- Eliminar pedido");
                if (rol.equals("admin")) {
                    System.out.println("5.- Gestionar usuarios");
                    System.out.println("0.- Salir");
                }
                else{
                    System.out.println("0.- Salir");
                }
                int seleccion = Integer.parseInt(System.console().readLine());

                switch (seleccion) {
                    case 1:
                        
                        break;
                
                    default:
                        break;
                }


            }
        } catch (Exception e) {
        }
        
    }
}
