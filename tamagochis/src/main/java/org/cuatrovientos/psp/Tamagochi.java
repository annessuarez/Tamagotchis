package org.cuatrovientos.psp;

import java.util.Random;

public class Tamagochi implements Runnable{    
    Random rnd = new Random();

    private int id;
    private String cuidador;

    private long tiempoVida = 5*60*1000;
    private String actividad = "NORMAL";
    private boolean vivo = true;

    private int nivelSuciedad  = 0; //ANNE: en 10 muere
    private int tiempoEnsuciarse = 20 * 1000; //ANNE: 20 segundos se ensucia
    private int tiempoComer; //ANNE: milisegundos que tarda en comer
    

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

    public synchronized void limpiar(){
        if (vivo && actividad.equals("NORMAL")){
            try{
                actividad = "LIMPIANDO";
                log("Se esta limpiando");
                Thread.sleep(5000);
                nivelSuciedad = 0;
            }catch(InterruptedException e){
                return;
            }

            log("Ha terminado de limpiarse.");
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

    @Override
	public void run() {
        try{
            long contadorSegundos = 0;
            while (vivo){
                Thread.sleep(1000);
                contadorSegundos +=1000;
                tiempoVida -= 1000;
                if (contadorSegundos >= 20000){
                    contadorSegundos = 0;
                    nivelSuciedad +=1;
                    if (nivelSuciedad == 5){
                        log("AVISO: el tamagochi "+ id +" esta muy sucio");
                    }else if(nivelSuciedad >= 10){
                        morir("Ha muerto por suciedad.");
                    }

                }
                
                if (tiempoVida <= 0){
                    morir("Ha muerto de anciano.");
                }
            }

        }catch (InterruptedException e){
            System.out.println("Error en tamagochi");
        }
    }
    
}
