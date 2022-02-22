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
public class Bishop extends ChessPiece{
    public Bishop()
    {
        super("Bishop");
    }
    public Bishop(ChessPos startPos, boolean isWhite, AnchorPane gameBoard, double w, double h)
    {
        super("Bishop", isWhite, startPos);
        if(isWhite)
        {
            pieceImageView.setImage(new Image("dk/anzodk/skakproject/pieces/white_bishop.png"));
        }
        else
        {
            pieceImageView.setImage(new Image("dk/anzodk/skakproject/pieces/black_bishop.png"));
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
            //I hate myself, But I hate my code even more
            //X++
            while(!(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row+c),currPos.column+(c*fieldMod)))) && (int)currPos.row+c >= (int)'a' && (int)currPos.row+c <= (int)'h' && currPos.column+(c*fieldMod) > 0 && currPos.column+(c*fieldMod) < 9)
            {
                CreateMoveFor(new ChessPos((char)((int)currPos.row+c),currPos.column+(c*fieldMod)));
                c++;
            }
            if(( (ChessController.INSTANCE.GetPieceOnPos(new ChessPos((char)((int)currPos.row+c),currPos.column+(c*fieldMod))) == null ? ChessController.INSTANCE.isWhite : ChessController.INSTANCE.GetPieceOnPos(new ChessPos((char)((int)currPos.row+c),currPos.column+(c*fieldMod))).isWhite ) != ChessController.INSTANCE.isWhite) && (int)currPos.row+c >= (int)'a' && (int)currPos.row+c <= (int)'h' && currPos.column+(c*fieldMod) > 0 && currPos.column+(c*fieldMod) < 9)
            {
                CreateMoveFor(new ChessPos((char)((int)currPos.row+c),currPos.column+(c*fieldMod)));
            }
            //X--
            c = 1;
            while(!(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row-c),currPos.column+(c*fieldMod)))) && (int)currPos.row-c <= (int)'h' && (int)currPos.row-c >= (int)'a' && currPos.column+(c*fieldMod) > 0 && currPos.column+(c*fieldMod) < 9)
            {
                CreateMoveFor(new ChessPos((char)((int)currPos.row-c),currPos.column+(c*fieldMod)));
                c++;
            }
            if(( (ChessController.INSTANCE.GetPieceOnPos(new ChessPos((char)((int)currPos.row-c),currPos.column+(c*fieldMod))) == null ? ChessController.INSTANCE.isWhite : ChessController.INSTANCE.GetPieceOnPos(new ChessPos((char)((int)currPos.row-c),currPos.column+(c*fieldMod))).isWhite) != ChessController.INSTANCE.isWhite) && (int)currPos.row-c <= (int)'h' && (int)currPos.row-c >= (int)'a' && currPos.column+(c*fieldMod) > 0 && currPos.column+(c*fieldMod) < 9)
            {
                CreateMoveFor(new ChessPos((char)((int)currPos.row-c),currPos.column+(c*fieldMod)));
            }
            //Y--
            c = 1;
            while(!(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row-c),currPos.column-(c*fieldMod)))) && (int)currPos.row-c <= (int)'h' && (int)currPos.row-c >= (int)'a' && (int)currPos.column-(c*fieldMod) > 0 && (int)currPos.column-(c*fieldMod) < 9)
            {
                CreateMoveFor(new ChessPos((char)((int)currPos.row-c),currPos.column-(c*fieldMod)));
                c++;
            }
            if(( (ChessController.INSTANCE.GetPieceOnPos(new ChessPos((char)((int)currPos.row-c),currPos.column-(c*fieldMod))) == null ? ChessController.INSTANCE.isWhite : ChessController.INSTANCE.GetPieceOnPos(new ChessPos((char)((int)currPos.row-c),currPos.column-(c*fieldMod))).isWhite) != ChessController.INSTANCE.isWhite) && (int)currPos.row-c <= (int)'h' && (int)currPos.row-c >= (int)'a' && (int)currPos.column-(c*fieldMod) > 0 && (int)currPos.column-(c*fieldMod) < 9)
            {
                CreateMoveFor(new ChessPos((char)((int)currPos.row-c),currPos.column-(c*fieldMod)));
            }
            //Y++
            c = 1;
            while(!(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row+c),currPos.column-(c*fieldMod)))) && (int)currPos.column-(c*fieldMod) > 0 && (int)currPos.column-(c*fieldMod) < 9 && (int)currPos.row+c >= (int)'a' && (int)currPos.row+c <= (int)'h')
            {
                CreateMoveFor(new ChessPos((char)((int)currPos.row+c),currPos.column-(c*fieldMod)));
                c++;
            }
            if(( (ChessController.INSTANCE.GetPieceOnPos(new ChessPos((char)((int)currPos.row+c),currPos.column-(c*fieldMod))) == null ? ChessController.INSTANCE.isWhite : ChessController.INSTANCE.GetPieceOnPos(new ChessPos((char)((int)currPos.row+c),currPos.column-(c*fieldMod))).isWhite) != ChessController.INSTANCE.isWhite) && (int)currPos.column-(c*fieldMod) > 0 && (int)currPos.column-(c*fieldMod) < 9 && (int)currPos.row+c >= (int)'a' && (int)currPos.row+c <= (int)'h')
            {
                CreateMoveFor(new ChessPos((char)((int)currPos.row+c),currPos.column-(c*fieldMod)));
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
