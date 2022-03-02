/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dk.anzodk.skakproject;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.lang.Math.*;
/**
 *
 * @author anzo
 */
public class Pawn extends ChessPiece{
    public Pawn()
    {
        super("Pawn");
    }
    public Pawn(ChessPos startPos, boolean isWhite, AnchorPane gameBoard, double w, double h)
    {
        super("Pawn", isWhite, startPos);
        if(isWhite)
        {
            pieceImageView.setImage(new Image("dk/anzodk/skakproject/pieces/maxresdefault.png"));
            
        }
        else
        {
            pieceImageView.setImage(new Image("dk/anzodk/skakproject/pieces/black_pawn.png"));
        }
        PlaceImage();
    }
    
    @Override
    void ToggleShowMoves()
    {
        System.out.println("Showing dem moves !");
        GameController.GamePane.getChildren().removeAll(currMoves);
        currMoves.clear();
        showMoves = !showMoves;
        if(showMoves)
        {
            int fieldMod = (isWhite ? 1 : -1);
            if(!ChessController.INSTANCE.ContainsKey(new ChessPos(currPos.row,currPos.column+fieldMod)))
            {
                    CreateMoveFor(new ChessPos(currPos.row,currPos.column+fieldMod));
                    if (!hasMoved) 
                    {
                        if (!ChessController.INSTANCE.ContainsKey(new ChessPos(currPos.row, currPos.column + (fieldMod * 2)))) {
                            CreateMoveFor(new ChessPos(currPos.row, currPos.column + (fieldMod * 2)));
                        }
                    }
            }
            if(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row+1),currPos.column+fieldMod)) && currPos.row != 'h')
            {
                    //Prevent possible attack on your own pawns
                    if(ChessController.INSTANCE.GetPieceOnPos(new ChessPos((char)((int)currPos.row+1),currPos.column+fieldMod)).isWhite != ChessController.INSTANCE.isWhite)
                    {
                        CreateMoveFor(new ChessPos((char)((int)currPos.row+1),currPos.column+fieldMod));
                    }
            }
            if(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row-1),currPos.column+fieldMod)) && currPos.row != 'a')
            {
                    //Prevent possible attack on your own pawns
                    if(ChessController.INSTANCE.GetPieceOnPos(new ChessPos((char)((int)currPos.row-1),currPos.column+fieldMod)).isWhite != ChessController.INSTANCE.isWhite)
                    {
                        CreateMoveFor(new ChessPos((char)((int)currPos.row-1),currPos.column+fieldMod));            
                    }
            }
            for(int i = 0; i < currMoves.size(); i++)
            {
                ImageView v = currMoves.get(i);
                v.addEventHandler(MouseEvent.MOUSE_CLICKED, eh ->{   
                    MoveTo(new ChessPos(v.getId()),false);
                    ToggleShowMoves();
                    ChessController.INSTANCE.yourTurn = false;
                });
            }
        }
    }
    
    
    @Override
    public boolean ValidateMove(ChessPos from, ChessPos to, int moveCount, boolean isWhite)
    {
        int fieldMod = (isWhite ? 1 : -1);
        if(from.row == to.row)
        {
            if(from.column+fieldMod == to.column)
            {
                return true;
            }
            else if(from.column+(fieldMod*2) == to.column)
            {
                if(moveCount == 1)
                {
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;

    }
    @Override
    public boolean ValidateMove(ChessPos to, int moveCount)
    {
        int fieldMod = (isWhite ? 1 : -1);
        if(currPos.row == to.row)
        {
            if(currPos.column+fieldMod == to.column)
            {
                return true;
            }
            else if(currPos.column+(fieldMod*2) == to.column)
            {
                if(moveCount == 1)
                {
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;

    }
}
