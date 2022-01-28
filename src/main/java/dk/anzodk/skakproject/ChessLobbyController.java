/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dk.anzodk.skakproject;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.TimeUnit;
import java.lang.InterruptedException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
/**
 *
 * @author anzo
 */



public class ChessLobbyController implements Initializable{
    
    public static int chessPort = 33233;
    
    @FXML
    public AnchorPane mainPane;
    
    @FXML
    public Label waitingLabel;
    
    @FXML
    public Button loginBtn;
    
    @FXML
    public Button hostMatchBtn;
    
    @FXML
    public TextField IpField;
    
    public static void DisplayError(String errTitle, String errMsg)
    {
        Alert err = new Alert(Alert.AlertType.ERROR, errMsg);
        err.setHeaderText(errTitle);
        err.showAndWait();
    }
    public static void DisplayError(String errTitle, String errMsg, Alert.AlertType type)
    {
        Alert err = new Alert(type, errMsg);
        err.setHeaderText(errTitle);
        err.showAndWait();
    }
    public static Alert DisplayAsyncError(String errTitle, String errMsg, Alert.AlertType type)
    {
        Alert err = new Alert(type, errMsg);
        err.setHeaderText(errTitle);
        //err.show();
        return err;
    }
    
    @FXML
    void HostMatch()
    {
        //Open Socket on port CHESS (33233)
        try{
            waitingLabel.setVisible(true);
            ServerSocket s = new ServerSocket(chessPort);
            //Alert a = DisplayAsyncError("Searching for player.","Player search will start - Please press 'OK'",Alert.AlertType.INFORMATION);
            //a.showAndWait();
            //Switch to waiting
            ConnectionManager.INSTANCE = new ConnectionManager(s);
            try{
                App.setRoot("Game");
            }
            catch(IOException e)
            {
                System.out.println("Failed to open Game.fxml : " + e.getStackTrace());
                e.printStackTrace();
                DisplayError("Missing FXML","Failed to open Game.fxml");
            }
            
        }
        catch(IOException e)
        {
           System.out.println("Failed to create socket : " + e);
           e.printStackTrace();
           DisplayError("Connection Failed","Failed To Create Socket....");
        }
        
        
    }
    
    @FXML
    void Login()
    {
        String ip = IpField.getText();
        if(ip.equals(""))
        {
            ip = "localhost";
        }
        Socket s;
        try{
            s = new Socket(ip,chessPort);
            ConnectionManager.INSTANCE = new ConnectionManager(s);
            try{
                App.setRoot("Game");
            }
            catch(IOException e)
            {
                System.out.println("Failed to open Game.fxml : " + e.getStackTrace());
                e.printStackTrace();
                DisplayError("Missing FXML","Failed to open Game.fxml");
                //ConnectionManager.INSTANCE.KillConnection();
            }
        }
        catch(IOException e)
        {
            System.out.println("Failed to open socket: " + e.getStackTrace());
            e.printStackTrace();
            DisplayError("Failed To Connecto To Host", "Could Not Connect to host: " + ip);
        }   
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        waitingLabel.setVisible(false);
    }
}
