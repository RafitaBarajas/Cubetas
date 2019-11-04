package p5;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Servidor extends Thread{
    
    private int pto;
    private int[] array;
    
    public Servidor(int pto){
        this.pto = pto;
    }

    public void run() {
        try{
            ServerSocket s = new ServerSocket(pto);
            System.out.println("Servidor iniciado con puerto: " + pto);
            
            //while(true){
                Socket cl = s.accept();
                //System.out.println("Cliente conectado.");
                
                ObjectInputStream ois = new ObjectInputStream(cl.getInputStream());
                //ObjectOutputStream oos = new ObjectOutputStream(cl.getOutputStream());
                
                array = (int[]) ois.readObject();
                //System.out.println("Server > Arreglo recibido.");
                
                Arrays.sort(array);
                
                ObjectOutputStream oos = new ObjectOutputStream(cl.getOutputStream());
                
                oos.writeObject(array);
                oos.flush();
                //System.out.println("Server > Arreglo enviado.");
                
                ois.close();
                oos.close();
                
                cl.close();
            //}
                        
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
}
