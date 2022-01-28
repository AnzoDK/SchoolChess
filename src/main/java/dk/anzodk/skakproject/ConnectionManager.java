/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dk.anzodk.skakproject;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
/**
 *
 * @author anzo
 */
public class ConnectionManager {
    public static ConnectionManager INSTANCE;
    public boolean isServer;
    public Socket clientSocket;
    public ServerSocket serverSocket;
    public BufferedReader reader;
    public PrintWriter writer;
    public int connectionTimeout;
    public Thread socketReadThread;
    public AnzoHorribleCallbackSystem callbackSystem = new AnzoHorribleCallbackSystem();
    ConnectionManager(Socket s, int connTimeOutSec)
    {
        connectionTimeout = connTimeOutSec;
        m_SetUp(s);
    }
    ConnectionManager(ServerSocket s, int connTimeOutSec)
    {
        connectionTimeout = connTimeOutSec;
        m_SetUp(s);
    }
    ConnectionManager(ServerSocket s)
    {
        connectionTimeout = 180;
        m_SetUp(s);
    }
    ConnectionManager(Socket s)
    {
        connectionTimeout = 180;
        m_SetUp(s);
    }
    /*@Override
    protected void finalize() throws Throwable
    {
        super.finalize();
        
        socketReadThread.stop();
        
    }*/
    private void m_SetUp(Socket s)
    {
        isServer = false;
        clientSocket = s;
        callbackSystem.RegisterListener(new ClientListener());
        try{
            writer = new PrintWriter(clientSocket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            //Start background ReadThread
            System.out.println("Starting SocketRead Thread");
            SocketReader sr = new SocketReader();
            socketReadThread = new Thread(sr);
            socketReadThread.start();
        }
        catch(IOException e)
        {
            System.out.println("Socket is not read/write-able...");
            if(socketReadThread != null)
            {
                socketReadThread.stop();
            }
        }
    }
    
    private void m_SetUp(ServerSocket s)
    {
        isServer = true;
        serverSocket = s;
        callbackSystem.RegisterListener(new ServerListener());
        try{
            clientSocket = serverSocket.accept();
            writer = new PrintWriter(clientSocket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            //Start background ReadThread
            System.out.println("Starting SocketRead Thread");
            SocketReader sr = new SocketReader();
            socketReadThread = new Thread(sr);
            socketReadThread.start();
            
        }
        catch(IOException e)
        {
            System.out.println("Socket is not read/write-able...");
            if(socketReadThread != null)
            {
                socketReadThread.stop();
            }
        }
    }
    void KillConnection()
    {
        try{
            if(isServer)
            {
                serverSocket.close();
                reader.close();
                writer.close();
                //clientSocket.close();
                return;
            }
            clientSocket.close();
            reader.close();
            writer.close();
            
        }
        catch(IOException e)
        {
            System.out.println("Failed To Close Socket: " + e.getMessage());
        }
    }
    public void KillClient()
    {
        if(isServer)
        {
            try{
                clientSocket.close();
            }
            catch(IOException e)
            {
                System.out.println("Failed To Close Socket: " + e.getMessage());
            }
        }
    }
    
    public void Send(String msg)
    {
        writer.println(msg);
        writer.flush();
    }
    
    public void CallbackCaller(String msg)
    {
        if(callbackSystem != null)
        {
            System.out.println((isServer ? "[Server] " : "[Client] ") + "Callback - New Message from SocketReadThread: " + msg);
            callbackSystem.HandleRaw(msg);
        }
    }
    
}
