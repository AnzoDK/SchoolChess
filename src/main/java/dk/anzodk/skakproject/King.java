/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dk.anzodk.skakproject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author anzo
 */
public class King extends ChessPiece{
    public King()
    {
        super("King");
    }
    public King(ChessPos startPos, boolean isWhite, AnchorPane gameBoard, double w, double h)
    {
        super("King", isWhite, startPos);
        if(isWhite)
        {
            pieceImageView.setImage(new Image("dk/anzodk/skakproject/pieces/white_king.png"));
        }
        else
        {
            pieceImageView.setImage(new Image("dk/anzodk/skakproject/pieces/black_king.png"));
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
            
            /*
            
            Thought Pattern
            
            	-1,1	0,1	1,1	
		
                -1,0	0,0	1,0
	
                -1,-1	0,-1	1,-1
            
            Where the king is 0,0
            
            
            */
            
            for(int x = -1; x < 2; x++)
            {
                for(int y = 1; y >= -1; y--)
                {
                    if(x == 0 && y == 0)
                    {
                        continue;
                    }
                    ChessPos tmp = new ChessPos((char)((int)currPos.row+x),currPos.column+y);
                    if((!ChessController.INSTANCE.ContainsKey(new ChessPos(tmp.row,tmp.column)) && tmp.column > 0 && tmp.column < 9 && (int)tmp.row >= (int)'a' && (int)tmp.row <= (int)'h') || ((( (ChessController.INSTANCE.GetPieceOnPos(tmp) == null ? ChessController.INSTANCE.isWhite : ChessController.INSTANCE.GetPieceOnPos(tmp).isWhite) != ChessController.INSTANCE.isWhite) && tmp.column > 0 && tmp.column < 9 && (int)tmp.row >= (int)'a' && (int)tmp.row <= (int)'h')) )
                    {
                        CreateMoveFor(tmp);
                    }
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
    public void OnDeath()
    {
        ConnectionManager.INSTANCE.Send("END "+(isWhite ? "White" : "Black" )+ " Lost");
    }
    
}
