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
        pieceImageView.setFitHeight(h);
        pieceImageView.setFitWidth(w);
        pieceImageView.setLayoutX((44+1) + (((int)currPos.row - ((int)'a')) * w));
        pieceImageView.setLayoutY((38+1) + ((currPos.column-1) * h));
        System.out.println("Placing Bishop at: X: " +  pieceImageView.getLayoutX() + " Y: " + pieceImageView.getLayoutY() +  " Colum: " + currPos.AsString());
        gameBoard.getChildren().add(pieceImageView);
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
            while(!(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row+c),currPos.column+(c*fieldMod)))) && (int)currPos.row+c <= (int)'h' && currPos.column+(c*fieldMod) > 0 && currPos.column+(c*fieldMod) < 9)
            {
                ImageView t = new ImageView();
                t.setImage(ChessController.INSTANCE.__Move_Image__);
                t.setFitHeight(GameController.SpaceHeight);
                t.setFitWidth(GameController.SpaceWidth);
                t.setLayoutX((44+1) + (((int)currPos.row - ((int)'a')+c) * GameController.SpaceWidth));
                t.setLayoutY((38+1) + ((currPos.column+(c*fieldMod)-1) * GameController.SpaceHeight));
                String id = "";
                id += (char)((int)currPos.row+c);
                id += currPos.column+(c*fieldMod);
                t.setId(id);
                GameController.GamePane.getChildren().add(t);
                currMoves.add(t);
                c++;
            }
            //X--
            c = 1;
            while(!(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row-c),currPos.column+(c*fieldMod)))) && (int)currPos.row-c >= (int)'a' && (int)currPos.column+(c*fieldMod) > 0 && (int)currPos.column+(c*fieldMod) < 9)
            {
                ImageView t = new ImageView();
                t.setImage(ChessController.INSTANCE.__Move_Image__);
                t.setFitHeight(GameController.SpaceHeight);
                t.setFitWidth(GameController.SpaceWidth);
                t.setLayoutX((44+1) + (((int)currPos.row - ((int)'a')-c) * GameController.SpaceWidth));
                t.setLayoutY((38+1) + ((currPos.column+(c*fieldMod)-1) * GameController.SpaceHeight));
                String id = "";
                id += (char)((int)currPos.row+c);
                id += currPos.column+(c*fieldMod);
                t.setId(id);
                GameController.GamePane.getChildren().add(t);
                currMoves.add(t);
                c++;
            }
            //Y--
            c = 1;
            while(!(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row-c),currPos.column-(c*fieldMod)))) && (int)currPos.column-(c*fieldMod) > 0 && (int)currPos.column-(c*fieldMod) < 9)
            {
                ImageView t = new ImageView();
                t.setImage(ChessController.INSTANCE.__Move_Image__);
                t.setFitHeight(GameController.SpaceHeight);
                t.setFitWidth(GameController.SpaceWidth);
                t.setLayoutX((44+1) + (((int)currPos.row - ((int)'a')-c) * GameController.SpaceWidth));
                t.setLayoutY((38+1) + ((currPos.column-(c*fieldMod)-1) * GameController.SpaceHeight));
                String id = "";
                id += (char)((int)currPos.row-c);
                id += currPos.column-(c*fieldMod);
                t.setId(id);
                GameController.GamePane.getChildren().add(t);
                currMoves.add(t);
                c++;
            }
            //Y++
            c = 1;
            while(!(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row+c),currPos.column-(c*fieldMod)))) && (int)currPos.column-c > 0 && (int)currPos.column-(c*fieldMod) <= 8 && (int)currPos.row-(c*fieldMod) <= (int)'h')
            {
                ImageView t = new ImageView();
                t.setImage(ChessController.INSTANCE.__Move_Image__);
                t.setFitHeight(GameController.SpaceHeight);
                t.setFitWidth(GameController.SpaceWidth);
                t.setLayoutX((44+1) + (((int)currPos.row - ((int)'a')+c) * GameController.SpaceWidth));
                t.setLayoutY((38+1) + ((currPos.column-(c*fieldMod)-1) * GameController.SpaceHeight));
                String id = "";
                id += (char)((int)currPos.row+c);
                id += currPos.column-(c*fieldMod);
                t.setId(id);
                GameController.GamePane.getChildren().add(t);
                currMoves.add(t);
                c++;
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
