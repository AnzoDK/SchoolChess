/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package dk.anzodk.skakproject;

import java.io.IOException;
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
        
        SetUpChess();
        DrawBoard(spaceW, spaceH);
        DrawPieces();
        
        
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
                horse hL = new horse(new ChessPos('b',1),true, mainPane, SpaceWidth, SpaceHeight);
                horse hR = new horse(new ChessPos('g',1),true, mainPane, SpaceWidth, SpaceHeight);
                 Queen q = new Queen(new ChessPos('d',1),true, mainPane, SpaceWidth, SpaceHeight);
                
                ChessController.INSTANCE.WhitePieces.add(bR);
                ChessController.INSTANCE.WhitePieces.add(bL);
                ChessController.INSTANCE.AddPieceToPos(bR.currPos, bR);
                ChessController.INSTANCE.AddPieceToPos(bL.currPos, bL);
                ChessController.INSTANCE.WhitePieces.add(tR);
                ChessController.INSTANCE.WhitePieces.add(tL);
                ChessController.INSTANCE.AddPieceToPos(tR.currPos, tR);
                ChessController.INSTANCE.AddPieceToPos(tL.currPos, tL);
                ChessController.INSTANCE.WhitePieces.add(hR);
                ChessController.INSTANCE.WhitePieces.add(hL);
                ChessController.INSTANCE.AddPieceToPos(hR.currPos, hR);
                ChessController.INSTANCE.AddPieceToPos(hL.currPos, hL);
                ChessController.INSTANCE.WhitePieces.add(q);
                 ChessController.INSTANCE.AddPieceToPos(q.currPos, q);
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
                 horse hL = new horse(new ChessPos('b',8),false, mainPane, SpaceWidth, SpaceHeight);
                horse hR = new horse(new ChessPos('g',8),false, mainPane, SpaceWidth, SpaceHeight);
                  Queen q = new Queen(new ChessPos('d',8),false, mainPane, SpaceWidth, SpaceHeight);
               
                ChessController.INSTANCE.BlackPieces.add(bR);
                ChessController.INSTANCE.BlackPieces.add(bL);
                ChessController.INSTANCE.AddPieceToPos(bR.currPos, bR);
                ChessController.INSTANCE.AddPieceToPos(bL.currPos, bL);
                ChessController.INSTANCE.BlackPieces.add(tR);               
                ChessController.INSTANCE.BlackPieces.add(tL);
                ChessController.INSTANCE.AddPieceToPos(tR.currPos, tR);
                ChessController.INSTANCE.AddPieceToPos(tL.currPos, tL);
                ChessController.INSTANCE.BlackPieces.add(hR);               
                ChessController.INSTANCE.BlackPieces.add(hL);
                ChessController.INSTANCE.AddPieceToPos(hR.currPos, hR);
                ChessController.INSTANCE.AddPieceToPos(hL.currPos, hL);
                 ChessController.INSTANCE.BlackPieces.add(q);               
                
                ChessController.INSTANCE.AddPieceToPos(q.currPos, q);
                
            }
        }
    }
    void SetUpChess()
    {
        ChessController.INSTANCE = new ChessController(ConnectionManager.INSTANCE.isServer);
        ConnectionManager.INSTANCE.callbackSystem.RegisterListener(new ChessListener());
        
    }
    
}
