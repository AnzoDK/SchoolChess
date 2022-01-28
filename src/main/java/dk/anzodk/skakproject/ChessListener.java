/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dk.anzodk.skakproject;

/**
 *
 * @author anzo
 */
public class ChessListener extends AnzoHorribleBaseCallback{
    @Override
    public void onMove(String msg)
    {
        ChessController.INSTANCE.onMove(msg);
    }
}
