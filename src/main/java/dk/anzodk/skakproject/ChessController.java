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
    public boolean yourTurn = false;
    
    
    public static ChessController INSTANCE;
    public ArrayList<String> moveList = new ArrayList<String>();
    public HashMap<ChessPos, ChessPiece> ChessMap = new HashMap<ChessPos,ChessPiece>();
    public HashMap<String, ChessPos> ChessStringMap = new HashMap<String,ChessPos>();
    
    
    ChessController(boolean _isHost)
    {
        isHost = _isHost;
        if(_isHost)
        {
            yourTurn = true;
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
        return ( ChessMap.get(ChessStringMap.get(pos.AsString())) != null );
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
        
        moveList.add(pieceName + "(" + (isWhite ? "White" : "Black") + ")[" + from.AsString() + "] to [" + to.AsString() +"]");
    }
}
