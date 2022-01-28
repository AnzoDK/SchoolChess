/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dk.anzodk.skakproject;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
/**
 *
 * @author anzo
 */
public class ChessPiece {
    public String name;
    public boolean isWhite;
    public ChessPos currPos;
    public ImageView pieceImageView = null;
    public ChessPiece(String _name, boolean _isWhite, ChessPos _currPos)
    {
        name = _name;
        isWhite = _isWhite;
        currPos = new ChessPos(_currPos);
    }
    public ChessPiece(String name)
    {
        this.name = name;
    }
    
    boolean ValidateMove(ChessPos from, ChessPos to, int MoveCount, boolean isWhite)
    {
        return false;
    }
    boolean ValidateMove(ChessPos to, int MoveCount)
    {
        return false;
    }
}
