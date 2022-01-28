/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dk.anzodk.skakproject;

/**
 *
 * @author anzo
 */
public class ChessPieceSystem {
    public static ChessPiece CreatePiece(String pieceName) throws NoSuchPieceException
    {
        switch(pieceName)
        {
            case "Pawn":
                return new Pawn();
        }
        throw new NoSuchPieceException("No such ChessPiece: " + pieceName);
    }
}
