package edu.eci.arsw.threads;

/**
 *
 * @author Nicolás Castro, Hann Jang, Jose Samuel, Juan Pablo
 */

/**
 * Clase principal que demuestra el uso de hilos para contar en paralelo.
 */
public class CountThreadsMain {


    /**
     * Método principal.
     *
     * @param args  argumentos de la línea de comandos.
     */
    public static void main(String args[]){

        Thread hilo1 = new Thread(new CountThread(0,100,"hilo1"));
        Thread hilo2 = new Thread(new CountThread(99,200,"hilo2"));
        Thread hilo3 = new Thread(new CountThread(199,300,"hilo3"));

 

        // hilo1.start();
        // hilo2.start();
        // hilo3.start();

 

        hilo1.run();
        hilo2.run();
        hilo3.run();
    }
    
}

