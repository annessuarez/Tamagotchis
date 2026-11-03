package org.cuatrovientos.psp;

import java.sql.Time;
import java.util.Random;

public class Tamagochi {
    private int id;
    private boolean vivo = true;
    private Time tiempoVida = 5*60*1000;
    private int limpieza = 0; // en 10 muere
    private int suciedad = 20 * 1000; // 20 segundos se ensucia
    private Time hambre; //ANNE: milisegundos que tarda en comer
    private String cuidador;
    Random rnd = new Random();

    public Tamagochi (int id, String cuidador){
        this.id = id;
        this.cuidador = cuidador;
        this.hambre = 1000 + hambre.rnd.nextInt(9000);
    }

    public int getId() {
        return id;
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
