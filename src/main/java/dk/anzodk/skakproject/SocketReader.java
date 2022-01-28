/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dk.anzodk.skakproject;

import java.io.IOException;

/**
 *
 * @author anzo
 */
public class SocketReader implements Runnable{
    boolean stop = false;
    @Override
    public void run()
    {
        while(!stop)
        {
            String inLine;
            try{
                if((inLine = ConnectionManager.INSTANCE.reader.readLine()) != null)
                {
                    ConnectionManager.INSTANCE.CallbackCaller(inLine);
                }
            }
            catch(IOException e)
            {
                System.out.println("ReadThread fucked: " + e.getStackTrace());
                this.stop = true;
            }
        }
    }
}
