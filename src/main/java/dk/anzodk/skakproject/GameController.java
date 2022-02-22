/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package dk.anzodk.skakproject;

import java.io.IOException;
import java.lang.Thread;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.ArrayList;


import javafx.scene.layout.AnchorPane;
import javafx.scene.canvas.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.*;
import javafx.scene.image.*;
import java.util.Map;
import javafx.scene.control.Alert;

import javafx.concurrent.Task;

/**
 * FXML Controller class
 *
 * @author anzo
 */
public class GameController implements Initializable {

    //public ChessController chessController;
    
    //public ArrayList<ChessPiece> WhitePieces = new ArrayList<ChessPiece>();
    //public ArrayList<ChessPiece> BlackPieces = new ArrayList<ChessPiece>();
    
    @FXML
    public Canvas board;
    
    @FXML
    public AnchorPane mainPane;
    
    @FXML
    public Button exitBtn;
    
    public GraphicsContext gc;
    
    public static AnchorPane GamePane = null;
    
    public static double SpaceWidth;
    public static double SpaceHeight;
    public static Task<Void> _task;
    public static Thread _thread;
    public static ArrayList<String> StringQueue = new ArrayList<>();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        gc = board.getGraphicsContext2D();
        double spaceW = (board.getWidth()-1)/8;
        double spaceH = (board.getHeight()-1)/8;
        
        GameController.SpaceWidth = spaceW;
        GameController.SpaceHeight = spaceH;
        GameController.GamePane = mainPane;
        
        GamePane.addEventHandler(EndGameEvent.GAME_WIN, event -> {
            
            ChessLobbyController.DisplayError("You WON!", "You WON!", Alert.AlertType.NONE);
            
        });
        GamePane.addEventHandler(EndGameEvent.GAME_LOSS, event -> {
            
            ChessLobbyController.DisplayError("You LOST!", "You LOST!", Alert.AlertType.NONE);
            
        });
        
        SetUpChess();
        DrawBoard(spaceW, spaceH);
        DrawPieces();
        
        /*_task = new Task<>()
        {
            @Override
            protected Void call() throws Exception
            {
                System.out.println("Task Start");
                while(true)
                {
                if(!GameController.StringQueue.isEmpty())
                {
                    if(GameController.StringQueue.get(0).equals("WIN"))
                    {
                        //GameController.GamePane.fireEvent(new EndGameEvent(EndGameEvent.GAME_WIN));
                        System.out.println("Task In Progress");
                        ChessLobbyController.DisplayError("You WON!", "You WON!", Alert.AlertType.NONE);
                    }
                    else if(GameController.StringQueue.get(0).equals("LOSS"))
                    {
                        //GameController.GamePane.fireEvent(new EndGameEvent(EndGameEvent.GAME_LOSS));
                        System.out.println("Task In Progress");
                        ChessLobbyController.DisplayError("You LOST!", "You LOST!", Alert.AlertType.NONE);                 
                    }
                    StringQueue.remove(GameController.StringQueue.get(0));
                }
                }
                //return null;
            }
            
            @Override
            protected void succeeded()
            {
                //GameController._task.run();
            }
            
            @Override
            protected void failed()
            {
                System.out.println("Task Died");
            }
        };
        _thread = new Thread(_task);
        _thread.setDaemon(false);
        _thread.start();*/
        
    }  
    
    public void Exit()
    {
        ConnectionManager.INSTANCE.Send("CLOSE");
        ConnectionManager.INSTANCE.KillConnection();
        try{
            App.setRoot("ChessLobby");
        }
        catch(IOException e)
        {
            System.out.println(e.getStackTrace());
            ChessLobbyController.DisplayError("Failed To Load Lobby", "Failed to load lobby...");
        }
    }
    
    void DrawBoard(double w, double h)
    {
        System.out.println("Drawing board...");
        gc.setLineWidth(2);
        gc.setStroke(Color.BLACK);
        for(int i = 0; i < 9; i++)
        {
            gc.moveTo(w*i, 0);
            gc.lineTo(w*i, board.getHeight());
            gc.stroke();  
        }
        for(int i = 0; i < 9; i++)
        {
            gc.moveTo(0, h*i);
            gc.lineTo(board.getWidth(),h*i);
            gc.stroke();
            
        }
        
        //Color The Spaces
        boolean Swap = false;
        for(int _y = 0; _y < 8; _y++)
        {
            for(int _x = 0; _x < 8; _x++)
            {
                if((Swap ? (_x%2 == 0) : !(_x%2==0)))
                {
                    gc.fillRect(w*_x, h*_y, w, h);
                }
            }
            Swap = !Swap;
        }
    }
    void DrawPieces()
    {
        for(int i = 0; i < 2; i++) // for both colors
        {
            if(i == 0)
            {
                for(int u = 0; u < 8; u++)
                {
                    //Drawing white pawns
                    Pawn p = new Pawn(new ChessPos((char)((int)'a'+u),2), true, mainPane, SpaceWidth, SpaceHeight);
                    ChessController.INSTANCE.WhitePieces.add(p);
                    ChessController.INSTANCE.AddPieceToPos(p.currPos, p);
                }
                Bishop bR = new Bishop(new ChessPos('f',1),true, mainPane, SpaceWidth, SpaceHeight);
                Bishop bL = new Bishop(new ChessPos('c',1),true, mainPane, SpaceWidth, SpaceHeight);
                Tower tR = new Tower(new ChessPos('h',1),true, mainPane, SpaceWidth, SpaceHeight);
                Tower tL = new Tower(new ChessPos('a',1),true, mainPane, SpaceWidth, SpaceHeight);
                Knight kL = new Knight(new ChessPos('b',1),true, mainPane, SpaceWidth, SpaceHeight);
                Knight kR = new Knight(new ChessPos('g',1),true, mainPane, SpaceWidth, SpaceHeight);
                Queen q = new Queen(new ChessPos('d',1),true, mainPane, SpaceWidth, SpaceHeight);
                King k = new King(new ChessPos('e',1),true,mainPane,SpaceWidth,SpaceHeight);
                
                
                ChessController.INSTANCE.WhitePieces.add(bR);
                ChessController.INSTANCE.WhitePieces.add(bL);
                ChessController.INSTANCE.AddPieceToPos(bR.currPos, bR);
                ChessController.INSTANCE.AddPieceToPos(bL.currPos, bL);
                ChessController.INSTANCE.WhitePieces.add(tR);
                ChessController.INSTANCE.WhitePieces.add(tL);
                ChessController.INSTANCE.AddPieceToPos(tR.currPos, tR);
                ChessController.INSTANCE.AddPieceToPos(tL.currPos, tL);
                ChessController.INSTANCE.WhitePieces.add(kR);
                ChessController.INSTANCE.WhitePieces.add(kL);
                ChessController.INSTANCE.AddPieceToPos(kR.currPos, kR);
                ChessController.INSTANCE.AddPieceToPos(kL.currPos, kL);
                ChessController.INSTANCE.WhitePieces.add(q);
                ChessController.INSTANCE.AddPieceToPos(q.currPos, q);
                ChessController.INSTANCE.WhitePieces.add(k);
                ChessController.INSTANCE.AddPieceToPos(k.currPos, k);                
            }
            else
            {
                //Drawing black pawns
                for(int u = 0; u < 8; u++)
                {
                    //Drawing black pawns
                    Pawn p = new Pawn(new ChessPos((char)((int)'a'+u),7), false, mainPane, SpaceWidth, SpaceHeight);
                    ChessController.INSTANCE.BlackPieces.add(p);
                    ChessController.INSTANCE.AddPieceToPos(p.currPos, p);
                }
                Bishop bR = new Bishop(new ChessPos('f',8),false, mainPane, SpaceWidth, SpaceHeight);
                Bishop bL = new Bishop(new ChessPos('c',8),false, mainPane, SpaceWidth, SpaceHeight);
                Tower tR = new Tower(new ChessPos('h',8),false, mainPane, SpaceWidth, SpaceHeight);
                Tower tL = new Tower(new ChessPos('a',8),false, mainPane, SpaceWidth, SpaceHeight);
                Knight kL = new Knight(new ChessPos('b',8),false, mainPane, SpaceWidth, SpaceHeight);
                Knight kR = new Knight(new ChessPos('g',8),false, mainPane, SpaceWidth, SpaceHeight);
                Queen q = new Queen(new ChessPos('d',8),false, mainPane, SpaceWidth, SpaceHeight);
                King k = new King(new ChessPos('e',8),false,mainPane,SpaceWidth,SpaceHeight);
                
                
                ChessController.INSTANCE.BlackPieces.add(bR);
                ChessController.INSTANCE.BlackPieces.add(bL);
                ChessController.INSTANCE.AddPieceToPos(bR.currPos, bR);
                ChessController.INSTANCE.AddPieceToPos(bL.currPos, bL);
                ChessController.INSTANCE.BlackPieces.add(tR);               
                ChessController.INSTANCE.BlackPieces.add(tL);
                ChessController.INSTANCE.AddPieceToPos(tR.currPos, tR);
                ChessController.INSTANCE.AddPieceToPos(tL.currPos, tL);
                ChessController.INSTANCE.BlackPieces.add(kR);               
                ChessController.INSTANCE.BlackPieces.add(kL);
                ChessController.INSTANCE.AddPieceToPos(kR.currPos, kR);
                ChessController.INSTANCE.AddPieceToPos(kL.currPos, kL);
                ChessController.INSTANCE.BlackPieces.add(q);                
                ChessController.INSTANCE.AddPieceToPos(q.currPos, q);
                ChessController.INSTANCE.BlackPieces.add(k);                
                ChessController.INSTANCE.AddPieceToPos(k.currPos, k);
                
            }
        }
    }
    void SetUpChess()
    {
        ChessController.INSTANCE = new ChessController(ConnectionManager.INSTANCE.isServer);
        ConnectionManager.INSTANCE.callbackSystem.RegisterListener(new ChessListener());
        
    }
    
}
