
package p5;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente extends Thread{
    
    private int min, max;
    
    private int[] array;
    private int[] subarray;
    
    private int pto;
    private String host = "localhost";
    
    public Cliente(int min, int max, int[] array, int pto){
        this.min = min;
        this.max = max;
        
        this.array = array;
        
        this.pto = pto;
    }

    public void run() {
        llenarCubeta();
        
        if(subarray.length > 1){
            try {
                Socket s = new Socket(host, pto);
                //System.out.println("Conectado al servidor con puerto: " + pto);

                //ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

                oos.writeObject(subarray);
                oos.flush();
                //System.out.println("Arreglo enviado.");

                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

                subarray = (int[]) ois.readObject();
                //System.out.println("Arreglo recibido.");

                ois.close();
                oos.close();

                s.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private void llenarCubeta(){
        
        int index;
        int[] auxarray = new int[array.length];
        
        index = 0;
        for (int i = 0; i < array.length; i++) {
            if(array[i] >= min && array[i] <= max){
                auxarray[index] = array[i];
                index++;
            }
        }
        
        subarray = new int[index];
        
        System.arraycopy(auxarray, 0, subarray, 0, index);
    }

    public int[] getSubarray() {
        return subarray;
    }
    
}
