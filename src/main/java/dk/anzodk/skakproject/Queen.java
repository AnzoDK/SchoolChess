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
/**
 *
 * @author gabri
 */
public class Queen extends ChessPiece{
 public Queen()
    {
        super("Queen");
    }
    public Queen(ChessPos startPos, boolean isWhite, AnchorPane gameBoard, double w, double h)
    {
        super("Queen", isWhite, startPos);
        if(isWhite)
        {
            pieceImageView.setImage(new Image("dk/anzodk/skakproject/pieces/white_Queen.png"));
        }
        else
        {
            pieceImageView.setImage(new Image("dk/anzodk/skakproject/pieces/black_Queen.png"));
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
            
            // TOWER CODE
            //Move in X positive
            while(!(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row+c),currPos.column))) && ((int)currPos.row+c) < 'h'+1)
            {
                ImageView t = new ImageView();
                t.setImage(ChessController.INSTANCE.__Move_Image__);
                t.setFitHeight(GameController.SpaceHeight);
                t.setFitWidth(GameController.SpaceWidth);
                t.setLayoutX((44+1) + (((int)currPos.row - ((int)'a')+c) * GameController.SpaceWidth));
                t.setLayoutY((38+1) + ((currPos.column-1) * GameController.SpaceHeight));
                String id = "";
                id += (char)((int)currPos.row+c);
                id += currPos.column;
                t.setId(id);
                GameController.GamePane.getChildren().add(t);
                currMoves.add(t);
                c++;
            }
            if(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row+c),currPos.column)) && ((int)currPos.row+c) < 'h'+1)
            {
                if(ChessController.INSTANCE.GetPieceOnPos(new ChessPos((char)((int)currPos.row+c),currPos.column)).isWhite != isWhite)
                {
                    ImageView t = new ImageView();
                    t.setImage(ChessController.INSTANCE.__Move_Image__);
                    t.setFitHeight(GameController.SpaceHeight);
                    t.setFitWidth(GameController.SpaceWidth);
                    t.setLayoutX((44+1) + (((int)currPos.row - ((int)'a')+c) * GameController.SpaceWidth));
                    t.setLayoutY((38+1) + ((currPos.column-1) * GameController.SpaceHeight));
                    String id = "";
                    id += (char)((int)currPos.row+c);
                    id += currPos.column;
                    t.setId(id);
                    GameController.GamePane.getChildren().add(t);
                    currMoves.add(t);
                }
            }
            c = 1;
            //Move in X Negative
            while(!(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row-c),currPos.column))) && ((int)currPos.row-c) > (int)'a'-1)
            {
                ImageView t = new ImageView();
                t.setImage(ChessController.INSTANCE.__Move_Image__);
                t.setFitHeight(GameController.SpaceHeight);
                t.setFitWidth(GameController.SpaceWidth);
                t.setLayoutX((44+1) + (((int)currPos.row - ((int)'a')-c) * GameController.SpaceWidth));
                t.setLayoutY((38+1) + ((currPos.column-1) * GameController.SpaceHeight));
                String id = "";
                id += (char)((int)currPos.row-c);
                id += currPos.column;
                t.setId(id);
                GameController.GamePane.getChildren().add(t);
                currMoves.add(t);
                c++;
            }
            if(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row-c),currPos.column)) && ((int)currPos.row-c) > (int)'a'-1)
            {
                if(ChessController.INSTANCE.GetPieceOnPos(new ChessPos((char)((int)currPos.row-c),currPos.column)).isWhite != isWhite)
                {
                    ImageView t = new ImageView();
                    t.setImage(ChessController.INSTANCE.__Move_Image__);
                    t.setFitHeight(GameController.SpaceHeight);
                    t.setFitWidth(GameController.SpaceWidth);
                    t.setLayoutX((44+1) + (((int)currPos.row - ((int)'a')-c) * GameController.SpaceWidth));
                    t.setLayoutY((38+1) + ((currPos.column-1) * GameController.SpaceHeight));
                    String id = "";
                    id += (char)((int)currPos.row-c);
                    id += currPos.column;
                    t.setId(id);
                    GameController.GamePane.getChildren().add(t);
                    currMoves.add(t);
                }
            }
            c = 1;
            //Move in Y Towards Center
            while(!(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row),currPos.column+(c*fieldMod)))) && (isWhite ? (currPos.column+(c*fieldMod) < 9) : (currPos.column+(c*fieldMod) > 0)))
            {
                ImageView t = new ImageView();
                t.setImage(ChessController.INSTANCE.__Move_Image__);
                t.setFitHeight(GameController.SpaceHeight);
                t.setFitWidth(GameController.SpaceWidth);
                t.setLayoutX((44+1) + (((int)currPos.row - ((int)'a')) * GameController.SpaceWidth));
                t.setLayoutY((38+1) + ((currPos.column+(c*fieldMod)-1) * GameController.SpaceHeight));
                String id = "";
                id += (char)currPos.row;
                id += currPos.column+(c*fieldMod);
                t.setId(id);
                GameController.GamePane.getChildren().add(t);
                currMoves.add(t);
                c++;
            }
            if(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row),currPos.column+(c*fieldMod))) && (isWhite ? (currPos.column+(c*fieldMod) < 9) : (currPos.column+(c*fieldMod) > 0)))
            {
                if(ChessController.INSTANCE.GetPieceOnPos(new ChessPos((char)((int)currPos.row),currPos.column+(c*fieldMod))).isWhite != isWhite)
                {
                    ImageView t = new ImageView();
                    t.setImage(ChessController.INSTANCE.__Move_Image__);
                    t.setFitHeight(GameController.SpaceHeight);
                    t.setFitWidth(GameController.SpaceWidth);
                    t.setLayoutX((44+1) + (((int)currPos.row - ((int)'a')) * GameController.SpaceWidth));
                    t.setLayoutY((38+1) + ((currPos.column+(c*fieldMod)-1) * GameController.SpaceHeight));
                    String id = "";
                    id += (char)((int)currPos.row);
                    id += currPos.column+(c*fieldMod);
                    t.setId(id);
                    GameController.GamePane.getChildren().add(t);
                    currMoves.add(t);
                }
            }
            c = 1;
            //Move in Y Away From Center
            fieldMod = fieldMod*-1;
            while(!(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row),currPos.column+(c*fieldMod)))) && (isWhite ? (currPos.column+(c*fieldMod) > 0) : (currPos.column+(c*fieldMod) < 9)))
            {
                ImageView t = new ImageView();
                t.setImage(ChessController.INSTANCE.__Move_Image__);
                t.setFitHeight(GameController.SpaceHeight);
                t.setFitWidth(GameController.SpaceWidth);
                t.setLayoutX((44+1) + (((int)currPos.row - ((int)'a')) * GameController.SpaceWidth));
                t.setLayoutY((38+1) + ((currPos.column+(c*fieldMod)-1) * GameController.SpaceHeight));
                String id = "";
                id += (char)currPos.row;
                id += currPos.column+(c*fieldMod);
                t.setId(id);
                GameController.GamePane.getChildren().add(t);
                currMoves.add(t);
                c++;
            }
            
            if(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row),currPos.column+(c*fieldMod))) && (isWhite ? (currPos.column+(c*fieldMod) > 0) : (currPos.column+(c*fieldMod) < 9)))
            {
                if(ChessController.INSTANCE.GetPieceOnPos(new ChessPos((char)((int)currPos.row),currPos.column+(c*fieldMod))).isWhite != isWhite)
                {
                    ImageView t = new ImageView();
                    t.setImage(ChessController.INSTANCE.__Move_Image__);
                    t.setFitHeight(GameController.SpaceHeight);
                    t.setFitWidth(GameController.SpaceWidth);
                    t.setLayoutX((44+1) + (((int)currPos.row - ((int)'a')) * GameController.SpaceWidth));
                    t.setLayoutY((38+1) + ((currPos.column+(c*fieldMod)-1) * GameController.SpaceHeight));
                    String id = "";
                    id += (char)((int)currPos.row);
                    id += currPos.column+(c*fieldMod);
                    t.setId(id);
                    GameController.GamePane.getChildren().add(t);
                    currMoves.add(t);
                }
            }
            
            /// BISHOP CODE
            
             //X++
            while(!(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row+c),currPos.column+(c*fieldMod)))) && (int)currPos.row+c >= (int)'a' && (int)currPos.row+c <= (int)'h' && currPos.column+(c*fieldMod) > 0 && currPos.column+(c*fieldMod) < 9)
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
            while(!(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row-c),currPos.column+(c*fieldMod)))) && (int)currPos.row-c <= (int)'h' && (int)currPos.row-c >= (int)'a' && currPos.column+(c*fieldMod) > 0 && currPos.column+(c*fieldMod) < 9)
            {
                ImageView t = new ImageView();
                t.setImage(ChessController.INSTANCE.__Move_Image__);
                t.setFitHeight(GameController.SpaceHeight);
                t.setFitWidth(GameController.SpaceWidth);
                t.setLayoutX((44+1) + (((int)currPos.row - ((int)'a')-c) * GameController.SpaceWidth));
                t.setLayoutY((38+1) + ((currPos.column+(c*fieldMod)-1) * GameController.SpaceHeight));
                String id = "";
                id += (char)((int)currPos.row-c);
                id += currPos.column+(c*fieldMod);
                t.setId(id);
                GameController.GamePane.getChildren().add(t);
                currMoves.add(t);
                c++;
            }
            //Y--
            c = 1;
            while(!(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row-c),currPos.column-(c*fieldMod)))) && (int)currPos.row-c <= (int)'h' && (int)currPos.row-c >= (int)'a' && (int)currPos.column-(c*fieldMod) > 0 && (int)currPos.column-(c*fieldMod) < 9)
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
            while(!(ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row+c),currPos.column-(c*fieldMod)))) && (int)currPos.column-(c*fieldMod) > 0 && (int)currPos.column-(c*fieldMod) < 9 && (int)currPos.row+c >= (int)'a' && (int)currPos.row+c <= (int)'h')
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
