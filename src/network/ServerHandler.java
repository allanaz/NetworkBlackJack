/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import blackjack.Card;
import java.util.ArrayList;

public class ServerHandler
{
    public static boolean connected = false;
    public static Point postPoints = null;
    public static Point initPoints = null;
    private static Socket client = null;
    private static PrintWriter serverOut = null;
    private static BufferedReader serverIn = null;

    public static void recieveConnect(int port) throws UnknownHostException, IOException, SocketTimeoutException
    {
        ServerSocket server = new ServerSocket(port);
        server.setSoTimeout(8000);
        client = server.accept();
        serverOut = new PrintWriter(client.getOutputStream(), true);
        serverIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
        connected = true;
        recievePieceMove();
    }

    public static void disconnect()
    {
        try
        {
            if (client != null)
                client.close();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        client = null;
        serverOut = null;
        serverIn = null;
        connected = false;
        Network.gameStarted = false;
        Network.reset();
    }
    public static void sendPieceMove(ArrayList<Card> _hand )
    {
		if (connected)
		{
//add or modify.                    
			serverOut.println(_hand + ":" + -1);
			Network.myTurn = false;
		}            
    }
    public static void sendPieceMove(int val)
    {
		if (connected)
		{
//add or modify.                    
			serverOut.println(val + ":" + -1);
			Network.myTurn = false;
		}            
    }
    public static void sendPieceMove(int val,int val2)
    {
		if (connected)
		{
//add or modify.                    
			serverOut.println(val + ":" + val2);
			Network.myTurn = false;
		}            
    }


    public static void sendDisconnect()
    {
        if (connected)
        {
            serverOut.println("esc");
        }
    }

    private static void recievePieceMove()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                String inputLine;

                try
                {
                    while ((inputLine = serverIn.readLine()) != null)
                    {
                        try
                        {
                            if (inputLine.equals("esc"))
                            {
                                disconnect();
                                return;
                            }
                            // row:col:initrow:initcol
//add or modify.                            
                            int ypost = Integer.parseInt(inputLine.split(":")[0]);
                            int xpost = Integer.parseInt(inputLine.split(":")[1]);
                            //Network.clientValue=xpost;
                            Network.james.hand.add(Card.cards[xpost]);
                            if(ypost!=-1)
                                Network.james.hand.add(Card.cards[ypost]);
 
                            Network.myTurn = true;
                        }
                        catch (NumberFormatException e)
                        {
                            e.printStackTrace();
                        }
                        catch (NullPointerException e)
                        {
                            disconnect();
                        }
                    }
                }
                catch (SocketException e)
                {
                    disconnect();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    public static boolean isConnected()
    {
        return connected;
    }
}
