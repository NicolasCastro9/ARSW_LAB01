package edu.eci.arsw.math;

public class PiDigitThread extends Thread {

    private int start; // Inicio del rango de dígitos a calcular
    private int count; // Número de dígitos a calcular
    private byte[] result; // Almacena los dígitos calculados

    /**
     * Constructor de la clase.
     *
     * @param start El inicio del rango de dígitos a calcular.
     * @param count El número de dígitos a calcular.
     */
    public PiDigitThread(int start, int count) {
        this.start = start;
        this.count = count;
    }

    /**
     * Obtiene los dígitos calculados por este hilo.
     *
     * @return Un array de bytes que representa los dígitos calculados.
     */
    public byte[] getResult() {
        return result;
    }

    /**
     * Método principal que representa el ciclo de vida del hilo.
     */
    @Override
    public void run() {
        result = PiDigits.getDigits(start, count);
    }
}
