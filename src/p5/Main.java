package p5;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    
    static int NUM = 4000;
    static int MAX = 1000;
    static int PTO = 1234;

    public static void main(String[] args) throws Exception{
        
        int cubetas;
        int combinaciones;
        int index;
        
        int[] array = new int[NUM];
        int[] orderedarray = new int[NUM];
        
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);
        
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        ArrayList<Servidor> servidores = new ArrayList<Servidor>();
        
        for (int i = 0; i < NUM; i++) {
            array[i] = rand.nextInt(MAX);
        }
        
        System.out.println("¿Cuántas cubetas quieres?");
        cubetas = scan.nextInt();
        
        for (int i = 0; i < cubetas; i++) {
            servidores.add(new Servidor(PTO + i));
            servidores.get(i).start();
        }
        
        combinaciones = MAX / cubetas;
        System.out.println("Número de combinaciones: " + combinaciones);
        
        for (int i = 0; i < cubetas; i++) {
            int min, max;
            
            min = i * combinaciones;
            if(i == cubetas - 1){
                max = MAX - 1;
            } else {
                max = min + combinaciones - 1;
            }
            
            System.out.println("Min " + i + ": " + min);
            System.out.println("Max " + i + ": " + max);
            
            clientes.add(new Cliente(min, max, array, PTO + i));
            clientes.get(i).start();
        }
        
        for (int i = 0; i < clientes.size(); i++) {
             clientes.get(i).join();
        }
                
        index = 0;
        for (int i = 0; i < clientes.size(); i++) {
            int[] clientarray;
            
            clientarray = clientes.get(i).getSubarray();
            
            for (int j = 0; j < clientarray.length; j++) {
                orderedarray[index] = clientarray[j];
                index++;
            }
        }
        
        for (int i = 0; i < NUM; i++) {
            System.out.println(array[i] + "\t" + orderedarray[i]);
        }
        
    }
    
}
