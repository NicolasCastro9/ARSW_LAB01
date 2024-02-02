package edu.eci.arsw.threads;

/**
 *
 * @author Nicolás Castro, Hann Jang, Jose Samuel, Juan Pablo
 */

/**
 * Clase que representa un hilo que cuenta desde un número A hasta un número B.
 */
public class CountThread extends Thread{

    private int numA;

    private int numB;

 
    /**
     * Constructor de la clase CountThread.
     *
     * @param numA El número inicial del contador.
     * @param numB El número final del contador.
     * @param name El nombre del hilo.
     */
    public CountThread(int numA, int numB, String name){
        // llamar al constructor de la superclase Thread
        super(name);
        this.numA = numA;
        this.numB = numB;

    }

   
    /**
     * Método principal que ejecuta el hilo. Cuenta desde numA hasta numB e imprime información en la consola.
     */
     public void run(){

        for (int i = numA; i < numB ; i++)
        System.out.println(i + " " + getName());
        System.out.println("Termina thread " + getName());

    }
}

