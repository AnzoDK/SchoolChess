/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dk.anzodk.skakproject;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
/**
 *
 * @author anzo
 */
public class Pawn extends ChessPiece{
    private boolean showMoves = false;
    public Pawn()
    {
        super("Pawn");
    }
    public Pawn(ChessPos startPos, boolean isWhite, AnchorPane gameBoard, double w, double h)
    {
        super("Pawn", isWhite, startPos);
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
    void ToggleShowMoves()
    {
        System.out.println("Showing dem moves !");
        GameController.GamePane.getChildren().removeAll(currMoves);
        currMoves.clear();
        showMoves = !showMoves;
        if(showMoves)
        {
            int fieldMod = (isWhite ? 1 : -1);
            if(!hasMoved)
            {
                if(!ChessController.INSTANCE.ContainsKey(new ChessPos(currPos.row,currPos.column+(fieldMod*2))))
                {
                    ImageView t = new ImageView();
                    t.setImage(new Image("dk/anzodk/skakproject/pieces/move_tile.png"));
                    t.setFitHeight(GameController.SpaceHeight);
                    t.setFitWidth(GameController.SpaceWidth);
                    t.setLayoutX((44+1) + (((int)currPos.row - ((int)'a')) * GameController.SpaceWidth));
                    t.setLayoutY((38+1) + ((currPos.column+(fieldMod*2)-1) * GameController.SpaceHeight));
                    String id = "";
                    id += currPos.row;
                    id += currPos.column+(fieldMod*2);
                    t.setId(id);
                    GameController.GamePane.getChildren().add(t);
                    currMoves.add(t);
                }
            }
            if(!ChessController.INSTANCE.ContainsKey(new ChessPos(currPos.row,currPos.column+fieldMod)))
            {
                    ImageView t = new ImageView();
                    t.setImage(new Image("dk/anzodk/skakproject/pieces/move_tile.png"));
                    t.setFitHeight(GameController.SpaceHeight);
                    t.setFitWidth(GameController.SpaceWidth);
                    t.setLayoutX((44+1) + (((int)currPos.row - ((int)'a')) * GameController.SpaceWidth));
                    t.setLayoutY((38+1) + ((currPos.column+fieldMod-1) * GameController.SpaceHeight));
                    String id = "";
                    id += currPos.row;
                    id += currPos.column+fieldMod;
                    t.setId(id);
                    GameController.GamePane.getChildren().add(t);
                    currMoves.add(t);
            }
            if(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row+1),currPos.column+fieldMod)) && currPos.row != 'h')
            {
                    ImageView t = new ImageView();
                    t.setImage(new Image("dk/anzodk/skakproject/pieces/move_tile.png"));
                    t.setFitHeight(GameController.SpaceHeight);
                    t.setFitWidth(GameController.SpaceWidth);
                    t.setLayoutX((44+1) + (((int)currPos.row - ((int)'a'+1)) * GameController.SpaceWidth));
                    t.setLayoutY((38+1) + ((currPos.column+fieldMod-1) * GameController.SpaceHeight));
                    String id = "";
                    id += (char)((int)currPos.row+1);
                    id += currPos.column+fieldMod;
                    t.setId(id);
                    GameController.GamePane.getChildren().add(t);
                    currMoves.add(t);                
            }
            if(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row-1),currPos.column+fieldMod)) && currPos.row != 'a')
            {
                    ImageView t = new ImageView();
                    t.setImage(new Image("dk/anzodk/skakproject/pieces/move_tile.png"));
                    t.setFitHeight(GameController.SpaceHeight);
                    t.setFitWidth(GameController.SpaceWidth);
                    t.setLayoutX((44+1) + (((int)currPos.row - ((int)'a'-1)) * GameController.SpaceWidth));
                    t.setLayoutY((38+1) + ((currPos.column+fieldMod-1) * GameController.SpaceHeight));
                    String id = "";
                    id += (char)((int)currPos.row-1);
                    id += currPos.column+fieldMod;
                    t.setId(id);
                    GameController.GamePane.getChildren().add(t);
                    currMoves.add(t);                
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
    void MoveTo(ChessPos newPos, boolean local)
    {
        if(!local)
        {
            ConnectionManager.INSTANCE.Send("MOVE " + currPos.AsString() + " TO " + newPos.AsString() + " TYPE " + name);
        }
        ChessController.INSTANCE.ClearPieceOnPos(newPos);
        currPos = new ChessPos(newPos);
        ChessController.INSTANCE.AddPieceToPos(newPos, this);
        ReDraw();
        if(!hasMoved)
        {
            hasMoved = true;
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
