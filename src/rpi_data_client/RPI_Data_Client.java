/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpi_data_client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import rpio_data.RPI_Data;

/**
 *
 * @author Federico
 */
public class RPI_Data_Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        new RPI_Data_Client().runClient();
    }
    
    public void runClient() throws IOException, ClassNotFoundException, InterruptedException{
        
        while(true){
        Socket socket = new Socket("192.168.1.56",30005);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        
        
        RPI_Data data = (RPI_Data) ois.readObject();
        System.out.println("Name "+data.get_Rpi_name());
        System.out.println("Location "+data.get_Rpi_location());
        System.out.println("Name "+data.get_Rpi_address());
        System.out.println("Input port "+data.getInputs());
        System.out.println("Output port "+data.getOutputs());
        for(int i=0; i<8 ;i++){
            System.out.println("Channel "+i+" "+data.getAnalog(i));
        }
        
        ois.close();
        socket.close();
        
        Thread.sleep(1000);
        }
        
    
    }
    
}
