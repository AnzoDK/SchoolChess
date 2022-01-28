/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dk.anzodk.skakproject;
import java.util.ArrayList;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
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
    private boolean showMoves = false;
    boolean hasMoved = false;
    ArrayList<ImageView> currMoves = new ArrayList<ImageView>();
    public ChessPiece(String _name, boolean _isWhite, ChessPos _currPos)
    {
        name = _name;
        isWhite = _isWhite;
        currPos = new ChessPos(_currPos);
        pieceImageView = new ImageView();
        pieceImageView.addEventHandler(MouseEvent.MOUSE_CLICKED, eh -> {
                //System.out.println("I feel the click ;)");
                if(ChessController.INSTANCE.yourTurn)
                {
                    System.out.println("I feel the click ;)");
                    ToggleShowMoves();
                }
        
        });
    }
    public ChessPiece(String name)
    {
        this.name = name;
        //pieceImageView = new ImageView();
    }
    
    void ToggleShowMoves()
    {
        return;
    }
    
    void MoveTo(ChessPos newPos, boolean local)
    {
        return;
    }
    void ReDraw()
    {
        pieceImageView.setLayoutX((44+1) + (((int)currPos.row - ((int)'a')) * GameController.SpaceWidth));
        pieceImageView.setLayoutY((38+1) + ((currPos.column-1) * GameController.SpaceHeight));
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
