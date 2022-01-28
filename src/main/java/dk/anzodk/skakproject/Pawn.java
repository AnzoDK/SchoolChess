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
public class Pawn extends ChessPiece{
    public Pawn()
    {
        super("Pawn");
    }
    public Pawn(ChessPos startPos, boolean isWhite, AnchorPane gameBoard, double w, double h)
    {
        super("Pawn", isWhite, startPos);
        pieceImageView = new ImageView();
        if(isWhite)
        {
            pieceImageView.setImage(new Image("dk/anzodk/skakproject/pieces/white_pawn.png"));
        }
        else
        {
            pieceImageView.setImage(new Image("dk/anzodk/skakproject/pieces/black_pawn.png"));
        }
        pieceImageView.setFitHeight(h);
        pieceImageView.setFitWidth(w);
        pieceImageView.setLayoutX((44+1) + (((int)currPos.row - ((int)'a')) * w));
        pieceImageView.setLayoutY((38+1) + ((currPos.column-1) * h));
        System.out.println("Placing Pawn at: X: " +  pieceImageView.getLayoutX() + " Y: " + pieceImageView.getLayoutY() +  " Colum: " + currPos.AsString());
        gameBoard.getChildren().add(pieceImageView);
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
