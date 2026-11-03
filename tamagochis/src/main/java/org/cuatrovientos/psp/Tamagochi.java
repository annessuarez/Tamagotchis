package org.cuatrovientos.psp;

import java.util.Random;

public class Tamagochi {
    private int id;
    private long tiempoVida = 5*60*1000;
    private int limpieza = 0; //ANNE: en 10 muere
    private int suciedad = 20 * 1000; //ANNE: 20 segundos se ensucia
    private int tiempoComer; //ANNE: milisegundos que tarda en comer
    private String actividad = "NORMAL";
    private boolean vivo = true;
    private String cuidador;
    Random rnd = new Random();

    public Tamagochi (int id, String cuidador){
        this.id = id;
        this.cuidador = cuidador;
        this.tiempoComer = 1000 + rnd.nextInt(9000);
    }

    public int getId() {
        return id;
    }

    private void log(String mensaje){
        System.out.println("Tamagochi " + id + ": " + mensaje);
    }

    public synchronized void comer(String comida){
        if (vivo && actividad.equals("NORMAL")){
            try{
                actividad = "COMIENDO";
            log("Esta comiendo.");
                Thread.sleep(tiempoComer);
            }catch(InterruptedException e){
                return;
            }

            log("Ha terminado de comer.");
            actividad = "NORMAL";
        }
    } 


    /** Un único sitio donde se muere, para no duplicar mensajes. */
    private synchronized void morir(String motivo) {
        if (!vivo) {
            return; // ya estaba muerto
        }
        vivo = false;
        actividad = "MUERTO";
        log(motivo);
    }

    
}
