/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dk.anzodk.skakproject;

import java.io.IOException;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.event.EventType;

/**
 *
 * @author anzo
 */
public class ServerListener extends AnzoHorribleBaseCallback {
    
    @Override
    public void onClose(String msg)
    {
        ConnectionManager.INSTANCE.KillClient();
        //ConnectionManager.INSTANCE.socketReadThread.stop();
        //ChessLobbyController.DisplayError("Client has left the Game", "The Other Player Left the game!", Alert.AlertType.WARNING); //Wrong thread
        System.out.println("Client has left the Game");
    }
    @Override void onVictory(String msg)
    {
        System.out.println("You Won!");
        //GameController.GamePane.fireEvent(new EndGameEvent(EndGameEvent.GAME_WIN));
        //GameController.StringQueue.add("WIN");
        Platform.runLater(() ->{
            ChessLobbyController.DisplayError("You won!", "You Won The Game Of Chess!",Alert.AlertType.INFORMATION);
            ConnectionManager.INSTANCE.KillClient();
            ConnectionManager.INSTANCE.KillConnection();
            try{
            App.setRoot("ChessLobby");
            }
            catch(IOException e)
            {
                System.out.println(e);
            }
        });
        
    }
    
    @Override
    public void onDefeat(String msg)
    {
        System.out.println("You Lost!");
        //GameController.GamePane.fireEvent(new EndGameEvent(EndGameEvent.GAME_LOSS));
        //GameController.StringQueue.add("LOSS");
        Platform.runLater(() ->{
            ChessLobbyController.DisplayError("You Lost", "You Lost A Game Of Chess...");
            ConnectionManager.INSTANCE.KillClient();
            ConnectionManager.INSTANCE.KillConnection();
            try{
            App.setRoot("ChessLobby");
            }
            catch(IOException e)
            {
                System.out.println(e);
            }
        });
    }
    
}
