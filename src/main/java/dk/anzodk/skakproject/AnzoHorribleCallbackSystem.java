/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dk.anzodk.skakproject;
import java.util.ArrayList;
/**
 *
 * @author anzo
 */
public class AnzoHorribleCallbackSystem {
    
    public ArrayList<AnzoHorribleBaseCallback> listeners = new ArrayList<AnzoHorribleBaseCallback>();
    
    public void HandleRaw(String rawString)
    {
        if(rawString.contains("MOVE"))
        {
            for(int i = 0; i < listeners.size(); i++)
            {
                listeners.get(i).onMove(rawString);
            }
        }
        else if(rawString.contains("CLOSE"))
        {
            for(int i = 0; i < listeners.size(); i++)
            {
                listeners.get(i).onClose(rawString);
            }
        }
        else if(rawString.contains("END"))
        {
            for(int i = 0; i < listeners.size(); i++)
            {
                if(rawString.contains("White") && ChessController.INSTANCE.isWhite)
                {
                    listeners.get(i).onDefeat(rawString);
                }
                else
                {
                    listeners.get(i).onVictory(rawString);
                }
            }
        }
    }
    public void RegisterListener(AnzoHorribleBaseCallback listener)
    {
        listeners.add(listener);
    }
}
