/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dk.anzodk.skakproject;

/**
 *
 * @author anzo
 */
public class ChessPos {
    public char row;
    public int column;
    
    ChessPos(char _row, int _column)
    {
        row = _row;
        column = _column;
    }
    ChessPos(ChessPos toCopy) // Copy Constructor to take care of random refrence passes
    {
        row = toCopy.row;
        column = toCopy.column;
    }
    ChessPos(String fromString)
    {
        System.out.println("Trying to create ChessPos from String: " + fromString);
        row = fromString.charAt(0);
        column = Integer.parseInt(fromString.substring(1,2));
    }
    String AsString()
    {
        String s = "";
        s += row;
        s += column;
        return s;
    }
}
