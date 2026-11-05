package org.cuatrovientos.psp;

import java.util.ArrayList;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        Scanner scan = new Scanner(System.in);
        String nombreCuidador = "";
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
                    for tamagochi in tamagochis{
                        tamagochis.getEstado();
                    }
                    break;
                
                case "2":
                    
                    break;

                case "3":
                    
                    break;
                
                case "4":
                    
                    break;

                case "5":
                    
                    break;

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
}