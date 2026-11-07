package org.cuatrovientos.psp;

import java.util.ArrayList;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        Scanner scan = new Scanner(System.in);
        String nombreCuidador = "";
        int respuestaID;

        do{
            System.out.println("Introduce tu nombre (seras el cuidador): ");
            nombreCuidador = scan.nextLine();
            if (nombreCuidador.isEmpty()){
                System.out.println("Error: escribe tu nombre.");
            }
        }while(nombreCuidador.isEmpty());
        
        ArrayList<Tamagochi> tamagochis = new ArrayList<>();

        for (int i = 1; i <= ConfigTamagochi.NUMERO_TAMAGOCHIS; i++){
            Tamagochi tamagochi = new Tamagochi(i, nombreCuidador);
            tamagochis.add(tamagochi);
            new Thread(tamagochi).start();
        }
        System.out.println("Tienes que cuidar a " + ConfigTamagochi.NUMERO_TAMAGOCHIS + " tamagochis.");

        boolean salir = false;
        while(!salir){
            System.out.println("\n--- MENÚ DEL CUIDADOR (" + nombreCuidador + ") ---");
            System.out.println("1. Ver estado de los Tamagotchis");
            System.out.println("2. Alimentar Tamagotchi");
            System.out.println("3. Jugar con Tamagotchi");
            System.out.println("4. Limpiar Tamagotchi");
            System.out.println("5. Matar Tamagotchi");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");

            String respuestaUsuario = scan.nextLine();
            switch (respuestaUsuario) {
                case "1":
                    System.out.println("1. Ver estado de los Tamagotchis");
                    for (Tamagochi tamagochi : tamagochis){
                        System.out.println(tamagochi.getEstado());
                    }
                    break;
                
                case "2":{
                    System.out.println("2. Alimentar Tamagotchi");
                    respuestaID = mostrarTamagochisDevuelveID(tamagochis, scan, "comer");

                    String respuestaComida = "";
                    do{
                        System.out.println("¿Qué quieres que coma el tamagochi? ");
                        respuestaComida = scan.nextLine();

                    } while (respuestaComida.isEmpty());
                        
                    //ANNE: se pone final porq las variables no cambien su valor
                    final Tamagochi tamagochiElegido = tamagochis.get(respuestaID-1);
                    final String comidaHilo = respuestaComida;
                    Thread thread = new Thread(new Runnable()  //ANNE: de esta forma se deja libre el menu y se pueden hacer otras cosas
                    {
                        @Override
                        public void run() {
                            tamagochiElegido.comer(comidaHilo);
                        }
                    });
                    thread.start();

                    break;
                }

                case "3":{
                    System.out.println("3. Jugar con Tamagotchi");
                    respuestaID = mostrarTamagochisDevuelveID(tamagochis, scan, "jugar");

                    final Tamagochi tamagochiElegido = tamagochis.get(respuestaID-1);
                    Thread thread = new Thread(new Runnable()  
                    {
                        @Override
                        public void run() {
                            tamagochiElegido.jugar(scan);
                        }
                    });
                    thread.start();

                    break;
                }
                case "4":{
                    System.out.println("4. Limpiar Tamagotchi");
                    respuestaID = mostrarTamagochisDevuelveID(tamagochis, scan, "limpiar");

                    final Tamagochi tamagochiElegido = tamagochis.get(respuestaID-1);
                    Thread thread = new Thread(new Runnable()
                    {
                        @Override
                        public void run() {
                            tamagochiElegido.limpiar();
                        }
                    });
                    thread.start();

                    break;
                }
                case "5":{
                    System.out.println("5. Matar Tamagotchi");
                    respuestaID = mostrarTamagochisDevuelveID(tamagochis, scan, "matar");

                    final Tamagochi tamagochiElegido = tamagochis.get(respuestaID-1);
                    Thread thread = new Thread(new Runnable()
                    {
                        @Override
                        public void run() {
                            tamagochiElegido.matar();
                        }
                    });
                    thread.start();
                    
                    break;
                }
                case "6":
                    System.out.println("Fin.");
                    salir = true;
                    break;
            
                default:
                    System.out.println(respuestaUsuario + ": Opción no válida.");
                    break;
            }
        }
    
    }

    private static int mostrarTamagochisDevuelveID(ArrayList<Tamagochi> tamagochis, Scanner scan, String accion){  
        System.out.println("IDs de los Tamagochis que tienes");
        for (Tamagochi tamagochi : tamagochis){
            System.out.println("Tamagochi: " + tamagochi.getId());
        }
        
        int respuestaID = 0;
        boolean esValido = false;

        do{
            System.out.println("Introduce el ID del tamagochi que quieres "+accion+" : ");
            try{
                respuestaID = Integer.parseInt(scan.nextLine());

                if (respuestaID >= 1 && respuestaID <= ConfigTamagochi.NUMERO_TAMAGOCHIS){
                    esValido = true;
                    

                }else{
                    System.out.println("Error: la respuesta "+ respuestaID +" no es válida.");
                }

            }catch (Exception e) {
                System.out.println("Error: debes introducir un número válido.");
            }                    

        }while (!esValido);
        
        return respuestaID;
    }
}