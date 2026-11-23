package utils;
import java.util.Scanner;


public class Utiles {
    
    static Scanner leer = new Scanner(System.in);

    public static void limpiarConsola() {
        System.out.print("\u001B[2J\u001B[H");
        System.out.flush();
    }
    
    
    public static String leerString(){
        
        return leer.nextLine();
    }

    public static String leerString(String mensaje){
        System.out.print(mensaje);
        return leer.nextLine();
    }

    public static int leerEntero(){
        return Integer.parseInt(leer.nextLine());
    }

    public static int leerEntero(String mensaje){
        System.out.print(mensaje);
        return Integer.parseInt(leer.nextLine());
    }
    
    public static double leerDouble(){
        return Double.parseDouble(leer.nextLine());
    }

    public static double leerDouble(String mensaje){
        System.out.print(mensaje);
        return Double.parseDouble(leer.nextLine());
    }
    
    
}