package persistencia;

import java.util.ArrayList;
import java.util.List;

import logica.Detalle;
import logica.Producto;
import logica.Remito;
import utils.Utiles;

public class RemitoCrud {
    private final List<Remito> remitos = new ArrayList<>();

    public void crearPedido() {
        int numero;
        do {
            numero = leerEntero("Indique el número del remito: ");
            if (buscarPorNumero(numero) != null) {
                System.out.println("Ya existe un remito con ese número.");
            }
        } while (buscarPorNumero(numero) != null);

        Remito remito = new Remito(numero);
        int cantidadProductos = leerNoNegativo("¿Cuántos productos desea cargar? ");

        for (int i = 0; i < cantidadProductos; i++) {
            System.out.println("Producto " + (i + 1) + ":");
            remito.agregarProducto(leerProducto());
        }

        remitos.add(remito);
        System.out.println("Pedido creado:");
        remito.mostrarInformacion();
    }

    public void listarPedidos() {
        if (remitos.isEmpty()) {
            System.out.println("No hay pedidos cargados.");
            return;
        }

        for (Remito remito : remitos) {
            remito.mostrarInformacion();
            System.out.println();
        }
    }

    public void buscarPedido() {
        int numero = leerEntero("Indique el número del remito: ");
        Remito remito = buscarPorNumero(numero);

        if (remito == null) {
            System.out.println("No se encontró un remito con ese número.");
            return;
        }

        remito.mostrarInformacion();
    }

    public void actualizarPedido() {
        int numero = leerEntero("Indique el número del remito a actualizar: ");
        Remito remito = buscarPorNumero(numero);

        if (remito == null) {
            System.out.println("No se encontró un remito con ese número.");
            return;
        }
        if (remito.isPreparado()) {
            System.out.println("No se puede modificar un remito que ya tiene un despacho.");
            return;
        }

        int opcion;
        do {
            System.out.println("1.- Agregar producto");
            System.out.println("2.- Modificar producto");
            System.out.println("3.- Quitar producto");
            System.out.println("0.- Terminar");
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    remito.agregarProducto(leerProducto());
                    break;
                case 2:
                    if (remito.getDetalles().isEmpty()) {
                        System.out.println("El remito no tiene productos.");
                        break;
                    }
                    mostrarProductos(remito);
                    int indiceModificar = leerIndiceProducto(remito);
                    remito.actualizarProducto(indiceModificar, leerProducto());
                    break;
                case 3:
                    if (remito.getDetalles().isEmpty()) {
                        System.out.println("El remito no tiene productos.");
                        break;
                    }
                    mostrarProductos(remito);
                    int indiceEliminar = leerIndiceProducto(remito);
                    remito.eliminarProducto(indiceEliminar);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        System.out.println("Pedido actualizado:");
        remito.mostrarInformacion();
    }

    public void eliminarPedido() {
        int numero = leerEntero("Indique el número del remito a eliminar: ");
        Remito remito = buscarPorNumero(numero);

        if (remito == null) {
            System.out.println("No se encontró un remito con ese número.");
            return;
        }
        if (remito.isPreparado()) {
            System.out.println("No se puede eliminar un remito que ya tiene un despacho.");
            return;
        }

        remitos.remove(remito);
        System.out.println("Pedido eliminado.");
    }

    private Remito buscarPorNumero(int numero) {
        for (Remito remito : remitos) {
            if (remito.getNum_remito() == numero) {
                return remito;
            }
        }
        return null;
    }

    private Producto leerProducto() {
        int codigo = leerNoNegativo("Código: ");
        String nombre = leerTexto("Nombre: ");
        int cantidad = leerNoNegativo("Cantidad: ");
        return new Producto(codigo, nombre, cantidad);
    }

    private void mostrarProductos(Remito remito) {
        List<Detalle> detalles = remito.getDetalles();
        for (int i = 0; i < detalles.size(); i++) {
            System.out.println((i + 1) + ")" + detalles.get(i));
        }
    }

    private int leerIndiceProducto(Remito remito) {
        int indice;
        do {
            indice = leerEntero("Indique el número del producto: ") - 1;
            if (indice < 0 || indice >= remito.getDetalles().size()) {
                System.out.println("No existe un producto con ese número.");
            }
        } while (indice < 0 || indice >= remito.getDetalles().size());
        return indice;
    }

    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(Utiles.leerString().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    private int leerNoNegativo(String mensaje) {
        int valor;
        do {
            valor = leerEntero(mensaje);
            if (valor < 0) {
                System.out.println("El valor no puede ser negativo.");
            }
        } while (valor < 0);
        return valor;
    }

    private String leerTexto(String mensaje) {
        String valor;
        do {
            System.out.print(mensaje);
            valor = Utiles.leerString().trim();
            if (valor.isEmpty()) {
                System.out.println("El texto no puede estar vacío.");
            }
        } while (valor.isEmpty());
        return valor;
    }
}
