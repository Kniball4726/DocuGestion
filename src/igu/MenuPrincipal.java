package igu;

import persistencia.DespachoCrud;
import persistencia.DocumentoCrud;
import persistencia.RemitoCrud;
import persistencia.UsuariosCrud;
import utils.Utiles;


public class MenuPrincipal {
    
    public static void MenuP(String rol){
    
    	RemitoCrud remitoCrud = new RemitoCrud();
    	DespachoCrud despachoCrud = new DespachoCrud();
    	DocumentoCrud documentoCrud = new DocumentoCrud(remitoCrud, despachoCrud);
    	UsuariosCrud usuariosCrud = new UsuariosCrud();
        
    	try {
            while (true) {
                System.out.println("Bienvenido al Menú Principal");
                
                if (rol.equals("admin")) {                    
                    System.out.println("1.- Ver pedidos");
                    System.out.println("2.- Ver despachos");
                    System.out.println("3.- Eliminar pedido");
                    System.out.println("4.- Eliminar despacho");
                    System.out.println("5.- Gestionar usuarios");
                    System.out.println("6.- Consultar todos los documentos");
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
                            remitoCrud.listarPedidos();
                        } else if (rol.equals("vendedor")) {
                            remitoCrud.crearPedido();
                        } else if (rol.equals("operario")) {
                            despachoCrud.crearDespacho();
                        } else {
                            remitoCrud.listarPedidos();
                        }
                        break;
                    case 2:
                    	if (rol.equals("admin") || rol.equals("logistica")) {
                            despachoCrud.listarDespachos();
                        } else if (rol.equals("vendedor")) {
                            remitoCrud.listarPedidos();
                        } else if (rol.equals("operario")) {
                            despachoCrud.listarDespachos();
                        } else {
                            despachoCrud.listarDespachos();
                        }
                    	break;
                    case 3:
                    	if (rol.equals("admin")) {
                            remitoCrud.eliminarPedido();
                        } else if (rol.equals("vendedor")) {
                            remitoCrud.actualizarPedido();
                        } else if (rol.equals("operario")) {
                            despachoCrud.actualizarDespacho();
                        } else {
                            System.out.println("Opción no disponible para este rol.");
                        }
                    	break;
                    case 4:
                    	if (rol.equals("admin")) {
                            despachoCrud.eliminarDespacho();
                        } else {
                            System.out.println("Opción no disponible para este rol.");
                        }
                        break;
                    case 5:
                    	if (rol.equals("admin")) {
                            int opcionUsuario;
                            do {
                                System.out.println("--- Gestión de usuarios ---");
                                System.out.println("1.- Crear usuario");
                                System.out.println("2.- Listar usuarios");
                                System.out.println("3.- Buscar usuario");
                                System.out.println("4.- Actualizar usuario");
                                System.out.println("5.- Eliminar usuario");
                                System.out.println("0.- Volver");
                                opcionUsuario = Utiles.leerEntero();

                                switch (opcionUsuario) {
                                    case 1:
                                        usuariosCrud.crearUsuarioDesdeConsola();
                                        break;
                                    case 2:
                                        usuariosCrud.listarUsuarios();
                                        break;
                                    case 3:
                                        usuariosCrud.buscarUsuario();
                                        break;
                                    case 4:
                                        usuariosCrud.actualizarUsuario();
                                        break;
                                    case 5:
                                        usuariosCrud.eliminarUsuario();
                                        break;
                                    case 0:
                                        break;
                                    default:
                                        System.out.println("Opción inválida.");
                                }
                            } while (opcionUsuario != 0);
                        } else {
                            System.out.println("Opción no disponible para este rol.");
                        }
                        break;
                    case 6:
                        if (rol.equals("admin")) {
                            documentoCrud.listarDocumentos();
                        } else {
                            System.out.println("Opción no disponible para este rol.");
                        }
                        break;

                    case 0:
                        return;
                    default:
                    	System.out.println("Opción inválida. Seleccione una opción del menú.");
                        break;
                }


            }
        } catch (Exception e) {
        	System.out.println("Debe introducir un número y no una cadena de texto");
        }
        
    }
}
