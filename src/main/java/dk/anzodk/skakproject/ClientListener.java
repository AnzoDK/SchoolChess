/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dk.anzodk.skakproject;

import javafx.scene.control.Alert;

/**
 *
 * @author anzo
 */
public class ClientListener extends AnzoHorribleBaseCallback{
    
    @Override void onClose(String msg)
    {
        //ConnectionManager.INSTANCE.socketReadThread.stop();
        //ChessLobbyController.DisplayError("Server has left the Game", "The Other Player Left the game!", Alert.AlertType.WARNING); //Wrong thread
        System.out.println("Sever has left the Game");
        ConnectionManager.INSTANCE.KillConnection();
    }
    @Override void onVictory(String msg)
    {
        System.out.println("You Won!");
        //GameController.GamePane.fireEvent(new EndGameEvent(EndGameEvent.GAME_WIN));
        GameController.StringQueue.add("WIN");
    }
    
    @Override
    public void onDefeat(String msg)
    {
        System.out.println("You Lost!");
        //GameController.GamePane.fireEvent(new EndGameEvent(EndGameEvent.GAME_LOSS));
        GameController.StringQueue.add("LOSS");
    }
}
