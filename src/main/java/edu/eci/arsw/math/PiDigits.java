package edu.eci.arsw.math;

///  <summary>
///  An implementation of the Bailey-Borwein-Plouffe formula for calculating hexadecimal
///  digits of pi.
///  https://en.wikipedia.org/wiki/Bailey%E2%80%93Borwein%E2%80%93Plouffe_formula
///  *** Translated from C# code: https://github.com/mmoroney/DigitsOfPi ***
///  </summary>
public class PiDigits {

    private static int DigitsPerSum = 8;
    private static double Epsilon = 1e-17;

    
    /**
     * Returns a range of hexadecimal digits of pi.
     * @param start The starting location of the range.
     * @param count The number of digits to return
     * @return An array containing the hexadecimal digits.
     */
    public static byte[] getDigits(int start, int count) {
        if (start < 0) {
            throw new RuntimeException("Invalid Interval");
        }

        if (count < 0) {
            throw new RuntimeException("Invalid Interval");
        }

        byte[] digits = new byte[count];
        double sum = 0;

        for (int i = 0; i < count; i++) {
            if (i % DigitsPerSum == 0) {
                sum = 4 * sum(1, start)
                        - 2 * sum(4, start)
                        - sum(5, start)
                        - sum(6, start);

                start += DigitsPerSum;
            }

            sum = 16 * (sum - Math.floor(sum));
            digits[i] = (byte) sum;
        }

        return digits;
    }

    /// <summary>
    /// Returns the sum of 16^(n - k)/(8 * k + m) from 0 to k.
    /// </summary>
    /// <param name="m"></param>
    /// <param name="n"></param>
    /// <returns></returns>
    private static double sum(int m, int n) {
        double sum = 0;
        int d = m;
        int power = n;

        while (true) {
            double term;

            if (power > 0) {
                term = (double) hexExponentModulo(power, d) / d;
            } else {
                term = Math.pow(16, power) / d;
                if (term < Epsilon) {
                    break;
                }
            }

            sum += term;
            power--;
            d += 8;
        }

        return sum;
    }

    /// <summary>
    /// Return 16^p mod m.
    /// </summary>
    /// <param name="p"></param>
    /// <param name="m"></param>
    /// <returns></returns>
    private static int hexExponentModulo(int p, int m) {
        int power = 1;
        while (power * 2 <= p) {
            power *= 2;
        }

        int result = 1;

        while (power > 0) {
            if (p >= power) {
                result *= 16;
                result %= m;
                p -= power;
            }

            power /= 2;

            if (power > 0) {
                result *= result;
                result %= m;
            }
        }

        return result;
    }

    /**
 * Retorna un rango de dígitos hexadecimales de pi, paralelizando el cálculo mediante múltiples hilos.
 * 
 * @param start La posición inicial del rango.
 * @param count El número de dígitos a retornar.
 * @param n El número de hilos a utilizar para la paralelización.
 * @return Un array que contiene los dígitos hexadecimales.
 * @throws InterruptedException Si alguno de los hilos es interrumpido durante la ejecución.
 * @throws IllegalArgumentException Si los parámetros son inválidos (start, count, o n son negativos).
 */
    public static byte[] getDigits(int start, int count, int n) throws InterruptedException {
        if (n <= 0) {
            throw new IllegalArgumentException("Invalid parameters");
        }
        if (start < 0) {
            throw new RuntimeException("Invalid Interval");
        }

        if (count < 0) {
            throw new RuntimeException("Invalid Interval");
        }

        byte[] result = new byte[count];
        Thread[] threads = new Thread[n];
        int size = count / n; // Calcula el tamaño de lote que cada hilo deberá calcular

        // Bucle para crear y ejecutar hilos
        for (int i = 0; i < n; i++) {
            int threadStart = start + i * size;
            int threadCount = (i == n - 1) ? (count - i * size) : size;

            PiDigitThread PiDigitThread = new PiDigitThread(threadStart, threadCount);
            threads[i] = PiDigitThread;
            PiDigitThread.start();
        }
        // Bucle para esperar a que todos los hilos terminen
        for (Thread thread : threads) {
            thread.join();
        }
        // Combina los resultados de los hilos
        int currentIndex = 0;
        for (int i = 0; i < n; i++) {
            PiDigitThread PiDigitThread = (PiDigitThread) threads[i];
            byte[] threadResult = PiDigitThread.getResult();
            System.arraycopy(threadResult, 0, result, currentIndex, threadResult.length);
            currentIndex += threadResult.length;
        }

        return result;
    }



}
