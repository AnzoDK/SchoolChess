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
public class horse extends ChessPiece {
    public horse()
    {
        super("Horse");
        
    }
    public horse(ChessPos startPos, boolean isWhite, AnchorPane gameBoard, double w, double h)
    {
        super("Horse", isWhite, startPos);
        if(isWhite)
        {
            pieceImageView.setImage(new Image("dk/anzodk/skakproject/pieces/white_horse.png"));
        }
        else
        {
            pieceImageView.setImage(new Image("dk/anzodk/skakproject/pieces/black_horse.png"));
        }
        pieceImageView.setFitHeight(h);
        pieceImageView.setFitWidth(w);
        pieceImageView.setLayoutX((44+1) + (((int)currPos.row - ((int)'a')) * w));
        pieceImageView.setLayoutY((38+1) + ((currPos.column-1) * h));
        System.out.println("Placing horse at: X: " +  pieceImageView.getLayoutX() + " Y: " + pieceImageView.getLayoutY() +  " Colum: " + currPos.AsString());
        gameBoard.getChildren().add(pieceImageView);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
