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
 * @author gabriel
 */
public class Knight extends ChessPiece {
    
     private ChessPos TargetPos; 
    
    public Knight()
    {
        super("Knight");
        
    }
    public Knight(ChessPos startPos, boolean isWhite, AnchorPane gameBoard, double w, double h)
    {
        super("Knight", isWhite, startPos);
        if(isWhite)
        {
            pieceImageView.setImage(new Image("dk/anzodk/skakproject/pieces/white_knight.png"));
        }
        else
        {
            pieceImageView.setImage(new Image("dk/anzodk/skakproject/pieces/black_knight.png"));
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
            
            //I hate myself, But I hate my code even more
            //X++
            // THE OLD WAYS ARE DEAD
          /*
           if(!ChessController.INSTANCE.ContainsKey(new ChessPos((char)((int)currPos.row+c),currPos.column+(fieldMod*2))) && (int)currPos.row+c >= (int)'a' && (int)currPos.row+c <= (int)'h' && currPos.column+(fieldMod*2) > 0 && currPos.column+(fieldMod*2) < 9 )
                {
                    //prevent attacking allies
                     if(ChessController.INSTANCE.GetPieceOnPos(new ChessPos((char)((int)currPos.row+c),currPos.column+(fieldMod*2))).isWhite != ChessController.INSTANCE.isWhite)
                    {
                   
                    }
                       
            }
*/
            int fieldMod = 2;
            int c = 1;
         TargetPos=new ChessPos((char)((int)currPos.row+c),currPos.column+(fieldMod));
         if(TargetPos.row >= (int)'a' && TargetPos.row <= (int)'h' && TargetPos.column > 0 && TargetPos.column < 9)
         {                         
            if((!ChessController.INSTANCE.ContainsKey(TargetPos)))
                    {
                         //prevent attacking allies
                 ImageView t = new ImageView();
                t.setImage(ChessController.INSTANCE.__Move_Image__);
                t.setFitHeight(GameController.SpaceHeight);
                t.setFitWidth(GameController.SpaceWidth);
                t.setLayoutX((44+1) + (((int)TargetPos.row - ((int)'a')) * GameController.SpaceWidth));
                t.setLayoutY((38+1) + ((TargetPos.column-1) * GameController.SpaceHeight));
                String id = "";
                id += (char)((int)TargetPos.row);
                id += TargetPos.column;
                t.setId(id);
                GameController.GamePane.getChildren().add(t);
                currMoves.add(t);                                                 
                    }
            else if(ChessController.INSTANCE.GetPieceOnPos(TargetPos).isWhite != ChessController.INSTANCE.isWhite)
            {
                     ImageView t = new ImageView();
                t.setImage(ChessController.INSTANCE.__Move_Image__);
                t.setFitHeight(GameController.SpaceHeight);
                t.setFitWidth(GameController.SpaceWidth);
                t.setLayoutX((44+1) + (((int)TargetPos.row - ((int)'a')) * GameController.SpaceWidth));
                t.setLayoutY((38+1) + ((TargetPos.column-1) * GameController.SpaceHeight));
                String id = "";
                id += (char)((int)TargetPos.row);
                id += TargetPos.column;
                t.setId(id);
                GameController.GamePane.getChildren().add(t);
                currMoves.add(t);                                
            }            
         }
         
         fieldMod = 2;
         c = -1;
         TargetPos=new ChessPos((char)((int)currPos.row+c),currPos.column+(fieldMod));
   if(TargetPos.row >= (int)'a' && TargetPos.row <= (int)'h' && TargetPos.column > 0 && TargetPos.column < 9)
         {                         
            if((!ChessController.INSTANCE.ContainsKey(TargetPos)))
                    {
                         //prevent attacking allies
                 ImageView t = new ImageView();
                t.setImage(ChessController.INSTANCE.__Move_Image__);
                t.setFitHeight(GameController.SpaceHeight);
                t.setFitWidth(GameController.SpaceWidth);
                t.setLayoutX((44+1) + (((int)TargetPos.row - ((int)'a')) * GameController.SpaceWidth));
                t.setLayoutY((38+1) + ((TargetPos.column-1) * GameController.SpaceHeight));
                String id = "";
                id += (char)((int)TargetPos.row);
                id += TargetPos.column;
                t.setId(id);
                GameController.GamePane.getChildren().add(t);
                currMoves.add(t);                                                 
                    }
            else if(ChessController.INSTANCE.GetPieceOnPos(TargetPos).isWhite != ChessController.INSTANCE.isWhite)
            {
                     ImageView t = new ImageView();
                t.setImage(ChessController.INSTANCE.__Move_Image__);
                t.setFitHeight(GameController.SpaceHeight);
                t.setFitWidth(GameController.SpaceWidth);
                t.setLayoutX((44+1) + (((int)TargetPos.row - ((int)'a')) * GameController.SpaceWidth));
                t.setLayoutY((38+1) + ((TargetPos.column-1) * GameController.SpaceHeight));
                String id = "";
                id += (char)((int)TargetPos.row);
                id += TargetPos.column;
                t.setId(id);
                GameController.GamePane.getChildren().add(t);
                currMoves.add(t);                                
            }            
         }
         fieldMod = -2;
         c = 1;
         TargetPos=new ChessPos((char)((int)currPos.row+c),currPos.column+(fieldMod));
 if(TargetPos.row >= (int)'a' && TargetPos.row <= (int)'h' && TargetPos.column > 0 && TargetPos.column < 9)
         {                         
            if((!ChessController.INSTANCE.ContainsKey(TargetPos)))
                    {
                         //prevent attacking allies
                 ImageView t = new ImageView();
                t.setImage(ChessController.INSTANCE.__Move_Image__);
                t.setFitHeight(GameController.SpaceHeight);
                t.setFitWidth(GameController.SpaceWidth);
                t.setLayoutX((44+1) + (((int)TargetPos.row - ((int)'a')) * GameController.SpaceWidth));
                t.setLayoutY((38+1) + ((TargetPos.column-1) * GameController.SpaceHeight));
                String id = "";
                id += (char)((int)TargetPos.row);
                id += TargetPos.column;
                t.setId(id);
                GameController.GamePane.getChildren().add(t);
                currMoves.add(t);                                                 
                    }
            else if(ChessController.INSTANCE.GetPieceOnPos(TargetPos).isWhite != ChessController.INSTANCE.isWhite)
            {
                     ImageView t = new ImageView();
                t.setImage(ChessController.INSTANCE.__Move_Image__);
                t.setFitHeight(GameController.SpaceHeight);
                t.setFitWidth(GameController.SpaceWidth);
                t.setLayoutX((44+1) + (((int)TargetPos.row - ((int)'a')) * GameController.SpaceWidth));
                t.setLayoutY((38+1) + ((TargetPos.column-1) * GameController.SpaceHeight));
                String id = "";
                id += (char)((int)TargetPos.row);
                id += TargetPos.column;
                t.setId(id);
                GameController.GamePane.getChildren().add(t);
                currMoves.add(t);                                
            }            
         }
         fieldMod = -2;
         c = -1;
         TargetPos=new ChessPos((char)((int)currPos.row+c),currPos.column+(fieldMod));
 if(TargetPos.row >= (int)'a' && TargetPos.row <= (int)'h' && TargetPos.column > 0 && TargetPos.column < 9)
         {                         
            if((!ChessController.INSTANCE.ContainsKey(TargetPos)))
                    {
                         //prevent attacking allies
                 ImageView t = new ImageView();
                t.setImage(ChessController.INSTANCE.__Move_Image__);
                t.setFitHeight(GameController.SpaceHeight);
                t.setFitWidth(GameController.SpaceWidth);
                t.setLayoutX((44+1) + (((int)TargetPos.row - ((int)'a')) * GameController.SpaceWidth));
                t.setLayoutY((38+1) + ((TargetPos.column-1) * GameController.SpaceHeight));
                String id = "";
                id += (char)((int)TargetPos.row);
                id += TargetPos.column;
                t.setId(id);
                GameController.GamePane.getChildren().add(t);
                currMoves.add(t);                                                 
                    }
            else if(ChessController.INSTANCE.GetPieceOnPos(TargetPos).isWhite != ChessController.INSTANCE.isWhite)
            {
                     ImageView t = new ImageView();
                t.setImage(ChessController.INSTANCE.__Move_Image__);
                t.setFitHeight(GameController.SpaceHeight);
                t.setFitWidth(GameController.SpaceWidth);
                t.setLayoutX((44+1) + (((int)TargetPos.row - ((int)'a')) * GameController.SpaceWidth));
                t.setLayoutY((38+1) + ((TargetPos.column-1) * GameController.SpaceHeight));
                String id = "";
                id += (char)((int)TargetPos.row);
                id += TargetPos.column;
                t.setId(id);
                GameController.GamePane.getChildren().add(t);
                currMoves.add(t);                                
            }            
         }
         fieldMod = 1;
         c = 2;
         TargetPos=new ChessPos((char)((int)currPos.row+c),currPos.column+(fieldMod));
 if(TargetPos.row >= (int)'a' && TargetPos.row <= (int)'h' && TargetPos.column > 0 && TargetPos.column < 9)
         {                         
            if((!ChessController.INSTANCE.ContainsKey(TargetPos)))
                    {
                         //prevent attacking allies
                 ImageView t = new ImageView();
                t.setImage(ChessController.INSTANCE.__Move_Image__);
                t.setFitHeight(GameController.SpaceHeight);
                t.setFitWidth(GameController.SpaceWidth);
                t.setLayoutX((44+1) + (((int)TargetPos.row - ((int)'a')) * GameController.SpaceWidth));
                t.setLayoutY((38+1) + ((TargetPos.column-1) * GameController.SpaceHeight));
                String id = "";
                id += (char)((int)TargetPos.row);
                id += TargetPos.column;
                t.setId(id);
                GameController.GamePane.getChildren().add(t);
                currMoves.add(t);                                                 
                    }
            else if(ChessController.INSTANCE.GetPieceOnPos(TargetPos).isWhite != ChessController.INSTANCE.isWhite)
            {
                     ImageView t = new ImageView();
                t.setImage(ChessController.INSTANCE.__Move_Image__);
                t.setFitHeight(GameController.SpaceHeight);
                t.setFitWidth(GameController.SpaceWidth);
                t.setLayoutX((44+1) + (((int)TargetPos.row - ((int)'a')) * GameController.SpaceWidth));
                t.setLayoutY((38+1) + ((TargetPos.column-1) * GameController.SpaceHeight));
                String id = "";
                id += (char)((int)TargetPos.row);
                id += TargetPos.column;
                t.setId(id);
                GameController.GamePane.getChildren().add(t);
                currMoves.add(t);                                
            }            
         }
        fieldMod = -1;
         c = 2;
         TargetPos=new ChessPos((char)((int)currPos.row+c),currPos.column+(fieldMod));
        if(TargetPos.row >= (int)'a' && TargetPos.row <= (int)'h' && TargetPos.column > 0 && TargetPos.column < 9)
         {                         
            if((!ChessController.INSTANCE.ContainsKey(TargetPos)))
                    {
                         //prevent attacking allies
                 ImageView t = new ImageView();
                t.setImage(ChessController.INSTANCE.__Move_Image__);
                t.setFitHeight(GameController.SpaceHeight);
                t.setFitWidth(GameController.SpaceWidth);
                t.setLayoutX((44+1) + (((int)TargetPos.row - ((int)'a')) * GameController.SpaceWidth));
                t.setLayoutY((38+1) + ((TargetPos.column-1) * GameController.SpaceHeight));
                String id = "";
                id += (char)((int)TargetPos.row);
                id += TargetPos.column;
                t.setId(id);
                GameController.GamePane.getChildren().add(t);
                currMoves.add(t);                                                 
                    }
            else if(ChessController.INSTANCE.GetPieceOnPos(TargetPos).isWhite != ChessController.INSTANCE.isWhite)
            {
                     ImageView t = new ImageView();
                t.setImage(ChessController.INSTANCE.__Move_Image__);
                t.setFitHeight(GameController.SpaceHeight);
                t.setFitWidth(GameController.SpaceWidth);
                t.setLayoutX((44+1) + (((int)TargetPos.row - ((int)'a')) * GameController.SpaceWidth));
                t.setLayoutY((38+1) + ((TargetPos.column-1) * GameController.SpaceHeight));
                String id = "";
                id += (char)((int)TargetPos.row);
                id += TargetPos.column;
                t.setId(id);
                GameController.GamePane.getChildren().add(t);
                currMoves.add(t);                                
            }            
         }
           fieldMod = 1;
         c = -2;
         TargetPos=new ChessPos((char)((int)currPos.row+c),currPos.column+(fieldMod));
       if(TargetPos.row >= (int)'a' && TargetPos.row <= (int)'h' && TargetPos.column > 0 && TargetPos.column < 9)
         {                         
            if((!ChessController.INSTANCE.ContainsKey(TargetPos)))
                    {
                         //prevent attacking allies
                 ImageView t = new ImageView();
                t.setImage(ChessController.INSTANCE.__Move_Image__);
                t.setFitHeight(GameController.SpaceHeight);
                t.setFitWidth(GameController.SpaceWidth);
                t.setLayoutX((44+1) + (((int)TargetPos.row - ((int)'a')) * GameController.SpaceWidth));
                t.setLayoutY((38+1) + ((TargetPos.column-1) * GameController.SpaceHeight));
                String id = "";
                id += (char)((int)TargetPos.row);
                id += TargetPos.column;
                t.setId(id);
                GameController.GamePane.getChildren().add(t);
                currMoves.add(t);                                                 
                    }
            else if(ChessController.INSTANCE.GetPieceOnPos(TargetPos).isWhite != ChessController.INSTANCE.isWhite)
            {
                     ImageView t = new ImageView();
                t.setImage(ChessController.INSTANCE.__Move_Image__);
                t.setFitHeight(GameController.SpaceHeight);
                t.setFitWidth(GameController.SpaceWidth);
                t.setLayoutX((44+1) + (((int)TargetPos.row - ((int)'a')) * GameController.SpaceWidth));
                t.setLayoutY((38+1) + ((TargetPos.column-1) * GameController.SpaceHeight));
                String id = "";
                id += (char)((int)TargetPos.row);
                id += TargetPos.column;
                t.setId(id);
                GameController.GamePane.getChildren().add(t);
                currMoves.add(t);                                
            }            
         }
           fieldMod = -1;
         c = -2;
         TargetPos=new ChessPos((char)((int)currPos.row+c),currPos.column+(fieldMod));
          if(TargetPos.row >= (int)'a' && TargetPos.row <= (int)'h' && TargetPos.column > 0 && TargetPos.column < 9)
         {                         
            if((!ChessController.INSTANCE.ContainsKey(TargetPos)))
                    {
                         //prevent attacking allies
                 ImageView t = new ImageView();
                t.setImage(ChessController.INSTANCE.__Move_Image__);
                t.setFitHeight(GameController.SpaceHeight);
                t.setFitWidth(GameController.SpaceWidth);
                t.setLayoutX((44+1) + (((int)TargetPos.row - ((int)'a')) * GameController.SpaceWidth));
                t.setLayoutY((38+1) + ((TargetPos.column-1) * GameController.SpaceHeight));
                String id = "";
                id += (char)((int)TargetPos.row);
                id += TargetPos.column;
                t.setId(id);
                GameController.GamePane.getChildren().add(t);
                currMoves.add(t);                                                 
                    }
            else if(ChessController.INSTANCE.GetPieceOnPos(TargetPos).isWhite != ChessController.INSTANCE.isWhite)
            {
                     ImageView t = new ImageView();
                t.setImage(ChessController.INSTANCE.__Move_Image__);
                t.setFitHeight(GameController.SpaceHeight);
                t.setFitWidth(GameController.SpaceWidth);
                t.setLayoutX((44+1) + (((int)TargetPos.row - ((int)'a')) * GameController.SpaceWidth));
                t.setLayoutY((38+1) + ((TargetPos.column-1) * GameController.SpaceHeight));
                String id = "";
                id += (char)((int)TargetPos.row);
                id += TargetPos.column;
                t.setId(id);
                GameController.GamePane.getChildren().add(t);
                currMoves.add(t);                                
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
