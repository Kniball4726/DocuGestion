package igu;

import persistencia.RemitoCrud;
import utils.Utiles;


public class MenuPrincipal {
    
    public static void MenuP(String rol){
    
    	RemitoCrud remitoCrud = new RemitoCrud();
        
    	try {
            while (true) {
                System.out.println("Bienvenido al Menú Principal");
                
                if (rol.equals("admin")) {                    
                    System.out.println("1.- Ver pedidos");
                    System.out.println("2.- Ver despachos");
                    System.out.println("3.- Eliminar pedido");
                    System.out.println("4.- Eliminar despacho");
                    System.out.println("5.- Gestionar usuarios");
                    System.out.println("0.- Salir");
                }else if (rol.equals("vendedor")) {
                	System.out.println("1.- Cargar pedido");
                    System.out.println("2.- Ver pedidos");
                    System.out.println("3.- Modificar pedido");
                    System.out.println("0.- Salir");
                } else if (rol.equals("operario")) {
                	System.out.println("1.- Cargar despacho");
                    System.out.println("2.- Ver despachos");
                    System.out.println("3.- Modificar despacho");
                    System.out.println("0.- Salir");
				} else {
                    System.out.println("1- Ver pedidos");
                    System.out.println("2- Ver despachos");
                    System.out.println("0.- Salir");
				}
                int seleccion = Utiles.leerEntero();

                
                switch (seleccion) {
                    case 1:
                        if (rol.equals("admin") || rol.equals("logistica")) {
                            remitoCrud.listarPedidos();;    
                        } else if (rol.equals("vendedor")) {
                            remitoCrud.crearPedido();
                        } else if (rol.equals("operario")) {
                        	
                        } else {
                            System.out.println("Caso 1 otro rol");
                        }
                        break;
                    case 2:
                    	if (rol.equals("admin") || rol.equals("logistica")) {
                            System.out.println("Caso 2 admin");
                        } else if (rol.equals("vendedor")) {
                            System.out.println("Caso 2 vendedor");
                        } else if (rol.equals("operario")) {
                            System.out.println("Caso 2 operario");
                        } else {
                            System.out.println("Caso 2 otro rol");
                        }
                    	break;
                    case 3:
                    	if (rol.equals("admin")) {
                            System.out.println("Caso 3 admin");
                        } else if (rol.equals("vendedor")) {
                            System.out.println("Caso 3 vendedor");
                        } else if (rol.equals("operario")) {
                            System.out.println("Caso 3 operario");
                        } else {
                            System.out.println("Caso 3 otro rol");
                        }
                    	break;
                    case 4:
                    	if (rol.equals("admin")) {
                            System.out.println("Caso 4 admin");
                        } else if (rol.equals("vendedor")) {
                            System.out.println("Caso 4 vendedor");
                        } else if (rol.equals("operario")) {
                            System.out.println("Caso 4 operario");
                        } else {
                            System.out.println("Caso 4 otro rol");
                        }
                        break;
                    case 5:
                    	if (rol.equals("admin")) {
                            System.out.println("Caso 5 admin");
                        } else if (rol.equals("vendedor")) {
                            System.out.println("Caso 5 vendedor");
                        } else if (rol.equals("operario")) {
                            System.out.println("Caso 5 operario");
                        } else {
                            System.out.println("Caso 5 otro rol");
                        }
                        break;

                    case 0:
                    	System.exit(0);
                    	break;
                    default:
                    	System.out.println("Error: Debe introducir un número valido");
                    	MenuP(rol);
                        break;
                }


            }
        } catch (Exception e) {
        	System.out.println("Debe introducir un número y no una cadena de texto");
        }
        
    }
}
