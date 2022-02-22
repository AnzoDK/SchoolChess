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
public class Tower extends ChessPiece{
    Tower()
    {
        super("Tower");
    }
    Tower(ChessPos startPos, boolean isWhite, AnchorPane gameBoard, double w, double h)
    {
        super("Tower", isWhite, startPos);
        if(isWhite)
        {
            pieceImageView.setImage(new Image("dk/anzodk/skakproject/pieces/white_tower.png"));
        }
        else
        {
            pieceImageView.setImage(new Image("dk/anzodk/skakproject/pieces/black_tower.png"));
        }
        PlaceImage();
    }
    
    @Override
    public void ToggleShowMoves()
    {
        GameController.GamePane.getChildren().removeAll(currMoves);
        currMoves.clear();
        showMoves = !showMoves;
        if(showMoves)
        {
            int fieldMod = (isWhite ? 1 : -1);
            int c = 1;
            //Move in X positive
            while(!(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row+c),currPos.column))) && ((int)currPos.row+c) < 'h'+1)
            {
                CreateMoveFor(new ChessPos((char)((int)currPos.row+c),currPos.column));
                c++;
            }
            if(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row+c),currPos.column)) && ((int)currPos.row+c) < 'h'+1)
            {
                if(ChessController.INSTANCE.GetPieceOnPos(new ChessPos((char)((int)currPos.row+c),currPos.column)).isWhite != isWhite)
                {
                    CreateMoveFor(new ChessPos((char)((int)currPos.row+c),currPos.column));
                }
            }
            c = 1;
            //Move in X Negative
            while(!(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row-c),currPos.column))) && ((int)currPos.row-c) > (int)'a'-1)
            {
                CreateMoveFor(new ChessPos((char)((int)currPos.row-c),currPos.column));
                c++;
            }
            if(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row-c),currPos.column)) && ((int)currPos.row-c) > (int)'a'-1)
            {
                if(ChessController.INSTANCE.GetPieceOnPos(new ChessPos((char)((int)currPos.row-c),currPos.column)).isWhite != isWhite)
                {
                    CreateMoveFor(new ChessPos((char)((int)currPos.row-c),currPos.column));
                }
            }
            c = 1;
            //Move in Y Towards Center
            while(!(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row),currPos.column+(c*fieldMod)))) && (isWhite ? (currPos.column+(c*fieldMod) < 9) : (currPos.column+(c*fieldMod) > 0)))
            {
                CreateMoveFor(new ChessPos((char)((int)currPos.row),currPos.column+(c*fieldMod)));
                c++;
            }
            if(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row),currPos.column+(c*fieldMod))) && (isWhite ? (currPos.column+(c*fieldMod) < 9) : (currPos.column+(c*fieldMod) > 0)))
            {
                if(ChessController.INSTANCE.GetPieceOnPos(new ChessPos((char)((int)currPos.row),currPos.column+(c*fieldMod))).isWhite != isWhite)
                {
                    CreateMoveFor(new ChessPos((char)((int)currPos.row),currPos.column+(c*fieldMod)));
                }
            }
            c = 1;
            //Move in Y Away From Center
            fieldMod = fieldMod*-1;
            while(!(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row),currPos.column+(c*fieldMod)))) && (isWhite ? (currPos.column+(c*fieldMod) > 0) : (currPos.column+(c*fieldMod) < 9)))
            {
                CreateMoveFor(new ChessPos((char)((int)currPos.row),currPos.column+(c*fieldMod)));
                c++;
            }
            
            if(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row),currPos.column+(c*fieldMod))) && (isWhite ? (currPos.column+(c*fieldMod) > 0) : (currPos.column+(c*fieldMod) < 9)))
            {
                if(ChessController.INSTANCE.GetPieceOnPos(new ChessPos((char)((int)currPos.row),currPos.column+(c*fieldMod))).isWhite != isWhite)
                {
                    CreateMoveFor(new ChessPos((char)((int)currPos.row),currPos.column+(c*fieldMod)));
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
}
