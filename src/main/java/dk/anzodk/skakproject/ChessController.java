/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dk.anzodk.skakproject;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author anzo
 */
public class ChessController {
    boolean isWhite;
    boolean isHost;
    public int moveCount = 0;
    
    
    public static ChessController INSTANCE;
    public ArrayList<String> moveList = new ArrayList<String>();
    public HashMap<ChessPos, ChessPiece> ChessMap = new HashMap<ChessPos,ChessPiece>();
    
    
    ChessController(boolean _isHost)
    {
        isHost = _isHost;
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
        ChessPos from = new ChessPos(msgCopy.charAt(5),Integer.parseInt(msgCopy.substring(6,6)));
        ChessPos to = new ChessPos(msgCopy.charAt(11),Integer.parseInt(msgCopy.substring(12,12)));
        String pieceName = msgCopy.substring(18);
        System.out.println("Moving Piece: " + pieceName + " from " + from.AsString() + " to " + to.AsString());
        
        
        if(isHost)
        {
            if(!ValidateMove(pieceName, from, to))
            {
                System.out.println("Client is cheating... Killing connection");
                ConnectionManager.INSTANCE.KillConnection();
            }
        }
        moveList.add(pieceName + "(" + (isWhite ? "White" : "Black") + ")[" + from.AsString() + "] to [" + to.AsString() +"]");
    }
}
