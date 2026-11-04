package org.cuatrovientos.psp;

import java.util.ArrayList;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        final int NUMERO_TAMAGOCHIS = 3;

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

        for (int i = 1; i <= NUMERO_TAMAGOCHIS; i++){
            Tamagochi tamagochi = new Tamagochi(i, nombreCuidador);
            tamagochis.add(tamagochi);
            new Thread(tamagochi).start();
        }
        System.out.println("Tienes que cuidar a " + NUMERO_TAMAGOCHIS + " tamagochis.");
    }
}