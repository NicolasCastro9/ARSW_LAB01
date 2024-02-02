/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.math;

import java.util.Arrays;

/**
 *
 * @author hcadavid
 */
public class Main {

    public static void main(String args[]) throws InterruptedException {
        // System.out.println(""+Runtime.getRuntime().availableProcessors());

        // System.out.println(bytesToHex(PiDigits.getDigits(0, 10)));
        // System.out.println(bytesToHex(PiDigits.getDigits(1, 100)));
        // System.out.println(bytesToHex(PiDigits.getDigits(1, 100000)));

        // System.out.println(bytesToHex(PiDigits.getDigits(0, 10,4)));
        // System.out.println(bytesToHex(PiDigits.getDigits(1, 100,4)));
        // System.out.println(bytesToHex(PiDigits.getDigits(1, 100000,4)));

        // 1 hilo
        // long start = System.currentTimeMillis();
        // System.out.println(bytesToHex(PiDigits.getDigits(0, 100000,1)));
        // long end = System.currentTimeMillis();
        // double time = (end - start);
        // System.out.println("Time: " + time);
        // System.out.println("Tiempo de ejecución (1 hilo): " + (end - start) + " milisegundos");


        // 8 hilos
        // long start = System.currentTimeMillis();
        // System.out.println(bytesToHex(PiDigits.getDigits(0, 100000,8)));
        // long end = System.currentTimeMillis();
        // double time = (end - start);
        // System.out.println("Time: " + time);
        // System.out.println("Tiempo de ejecución (8 hilos): " + (end - start) + " milisegundos");

        // 16 hilos
        // long start = System.currentTimeMillis();
        // System.out.println(bytesToHex(PiDigits.getDigits(0, 100000,16)));
        // long end = System.currentTimeMillis();
        // double time = (end - start);
        // System.out.println("Time: " + time);
        // System.out.println("Tiempo de ejecución (16 hilos): " + (end - start) + " milisegundos");


        // 200 hilos
        // long start = System.currentTimeMillis();
        // System.out.println(bytesToHex(PiDigits.getDigits(0, 100000,200)));
        // long end = System.currentTimeMillis();
        // double time = (end - start);
        // System.out.println("Time: " + time);
        // System.out.println("Tiempo de ejecución (200 hilos): " + (end - start) + " milisegundos");


        // 500 hilos
        long start = System.currentTimeMillis();
        System.out.println(bytesToHex(PiDigits.getDigits(0, 100000,500)));
        long end = System.currentTimeMillis();
        double time = (end - start);
        System.out.println("Time: " + time);
        System.out.println("Tiempo de ejecución (500 hilos): " + (end - start) + " milisegundos");
        
        
        
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<hexChars.length;i=i+2){
            //sb.append(hexChars[i]);
            sb.append(hexChars[i+1]);            
        }
        return sb.toString();
    }

}
