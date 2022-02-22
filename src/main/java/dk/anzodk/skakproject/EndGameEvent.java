/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dk.anzodk.skakproject;
import javafx.event.EventType;
import javafx.event.Event;
/**
 *
 * @author anzo
 */
public class EndGameEvent extends Event{
    
    public static final EventType<EndGameEvent> GAME_LOSS = new EventType(ANY,"GAME_LOSS");
    public static final EventType<EndGameEvent> GAME_WIN = new EventType(ANY,"GAME_WIN");
    
    public EndGameEvent(EventType<? extends Event> eventType)
    {
        super(eventType);
        
    }
    
}
