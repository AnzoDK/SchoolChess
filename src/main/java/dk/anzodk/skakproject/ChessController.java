/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dk.anzodk.skakproject;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Platform;
import javafx.scene.image.Image;
/**
 *
 * @author anzo
 */
public class ChessController {
    boolean isWhite;
    boolean isHost;
    public int moveCount = 0;
    public boolean yourTurn = false;
    public ChessPiece selectedPiece = null;
    
    
    public static ChessController INSTANCE;
    public Image __Move_Image__;
    public ArrayList<ChessPiece> WhitePieces = new ArrayList<ChessPiece>();
    public ArrayList<ChessPiece> BlackPieces = new ArrayList<ChessPiece>();
    public ArrayList<String> moveList = new ArrayList<String>();
    public HashMap<ChessPos, ChessPiece> ChessMap = new HashMap<ChessPos,ChessPiece>();
    public HashMap<String, ChessPos> ChessStringMap = new HashMap<String,ChessPos>();
    
    
    ChessController(boolean _isHost)
    {
        __Move_Image__ = new Image("dk/anzodk/skakproject/pieces/move_tile.png");
        isHost = _isHost;
        App._stage.setTitle("You Are Playing as BLACK");
        if(_isHost)
        {
            yourTurn = true;
            isWhite = true;
            App._stage.setTitle("You Are playing as WHITE");
        }
        for(int i = 0; i < 8; i++)
        {
            for(int u = 0; u < 8; u++)
            {
                String s = "";
                char r = (char)((int)'a'+i);
                s += r;
                s += u+1;
                ChessPos pos = new ChessPos(r,u);
                ChessStringMap.put(s, pos);
                ChessMap.put(pos, null);
            }
        }
    }
    public boolean ContainsKey(ChessPos pos)
    {
        System.out.println("Searching for Piece on pos: " + pos.AsString());
        boolean result = ( ChessMap.get(ChessStringMap.get(pos.AsString())) != null );
        
        System.out.println( (result ? "Found piece on pos: " + pos.AsString() : "Didn't find any piece on pos: " + pos.AsString()));
        
        return result;
    }
    
    public void AddPieceToPos(ChessPos pos, ChessPiece piece)
    {
        ChessMap.replace( ChessStringMap.get(pos.AsString()), piece );
    }
    
    public ChessPiece GetPieceOnPos(ChessPos pos)
    {
        return ChessMap.get(ChessStringMap.get(pos.AsString()));
    }
    
    public void ClearPieceOnPos(ChessPos pos)
    {
        ChessMap.replace( ChessStringMap.get(pos.AsString()), null );
    }
    
    public void RemovePieceFromPlay(ChessPiece p)
    {
        //GameController.GamePane.getChildren().remove(p.pieceImageView); //Wrong thread.....
        //p.pieceImageView.setLayoutX(999999999); // just move the piece out the fucking way, cuz threading issue /*This problem is now fixed :)*/
        p.isAlive = false;
        if(p.isWhite)
        {
            WhitePieces.remove(p);
        }
        else
        {
            BlackPieces.remove(p);
        }
        Platform.runLater(() -> {GameController.GamePane.getChildren().remove(p.pieceImageView);});
    }
    
    boolean ValidateMove(String pieceName, ChessPos from, ChessPos to)
    {
        try{
            return ChessPieceSystem.CreatePiece(pieceName).ValidateMove(from, to, moveCount, isWhite);
        }
        catch(NoSuchPieceException e)
        {
            System.out.println(e);
        }
        return false;
    }
    
    void onMove(String msg)
    {
        /*
        FORMAT:
        MOVE (from ex. a2) TO (example a3) TYPE (piecename ex. Pawn)
        */
        String msgCopy = new String(msg); //For safety
        ChessPos from = new ChessPos(msgCopy.charAt(5),Integer.parseInt(msgCopy.substring(6,7)));
        ChessPos to = new ChessPos(msgCopy.charAt(11),Integer.parseInt(msgCopy.substring(12,13)));
        String pieceName = msgCopy.substring(18);
        System.out.println("Moving Piece: " + pieceName + " from " + from.AsString() + " to " + to.AsString());
        
        if(ChessController.INSTANCE.ContainsKey(from))
        {
            GetPieceOnPos(from).MoveTo(to, true);
            ChessController.INSTANCE.yourTurn = true;
        }
        else
        {
            System.out.println("Cant find space: " + from.AsString() + " Excepting Desync.......");
        }
        
        
        /*if(isHost)
        {
            if(!ValidateMove(pieceName, from, to))
            {
                System.out.println("Client is cheating... Killing connection");
                ConnectionManager.INSTANCE.KillConnection();
            }
        }*/
        
        moveList.add(pieceName + "(" + (!ChessController.INSTANCE.isWhite ? "White" : "Black") + ") [" + from.AsString() + "] to [" + to.AsString() +"]");
        UpdateMoveList();
    }
    
    public void UpdateMoveList()
    {
        if(GameController.__moveListView != null)
        {
            Platform.runLater( () ->{ 
            
                GameController.__moveListView.getItems().clear();
                GameController.__moveListView.getItems().addAll(moveList);
            
            });
        }
    }
}
