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
     /*
    How to send data:
    Server.recievePiece Move takes 4 values values are -1 if they are not used
    value 0 = 1st card to be added
    value 1 = second Card to be added
    value 3 = player to be added to (0 for dealer, 1 for other player,2 for self)
    value 4 = thePot
    value 5 = stand (if !=-1, then myturn=false)
    */public static void sendStand()
    {
        if(connected)
        {
            serverOut.println(-1 + ":" + -1 + ":" + -1 +":"+-1 + ":" + 1);
            Network.myTurn=false;
        }
    }
    public static void sendHit(int val)
    {
		if (connected)
		{
//add or modify.                    
			//serverOut.println(-1 + ":" + val + ":" + 0);
                    serverOut.println(val + ":" + -1 + ":" + 2 +":"+-1+":"+-1 );
			//Network.myTurn = false;
		}        
    }
    public static void sendDealerHit(int val)
    {
		if (connected)
		{
//add or modify.                    
			//serverOut.println(-1 + ":" + val + ":" + 0);
                    serverOut.println(val + ":" + -1 + ":" + 0 +":"+-1+":"+-1 );
			Network.myTurn = false;
		}        
    }
    public static void sendBet(int val)
    {
		if (connected)
		{
//add or modify.                    
			//serverOut.println(-1 + ":" + val + ":" + 0);
                        serverOut.println(-1  + ":" + -1 + ":" + -1 + ":" + val+":"+-1);
			Network.myTurn = false;
		}        
    }
    public static void sendDeal(int val,int val2)
    {
		if (connected)
		{
//add or modify.                    
			serverOut.println(val + ":" + val2 + ":" + 2 + ":" + -1+":"+-1);
			Network.myTurn = false;
		}        
    }
    public static void sendDealerDeal(int val,int val2)
    {
		if (connected)
		{
//add or modify.                    
			serverOut.println(val + ":" + val2 + ":" + 0 + ":" + -1+":"+-1);
			Network.myTurn = false;
		}        
    }
    public static void sendMoneyEffect(int val,int val2)
    {
		if (connected)
		{
//add or modify.                    
			serverOut.println(-1 + ":" + -1 + ":" + 1 + ":" + -1+":"+-1+":"+val+":"+val2);
			//Network.myTurn = false;
		}        
    }
    public static void sendOtherPlayerDeal(int val,int val2)
    {
		if (connected)
		{
//add or modify.                    
			serverOut.println(val + ":" + val2 + ":" + 1 + ":" + -1+":"+-1);
			Network.myTurn = false;
		}        
    }
//    public static void sendPieceMove(ArrayList<Card> _hand )
//    {
//		if (connected)
//		{
////add or modify.                    
//			serverOut.println(_hand + ":" + -1);
//			Network.myTurn = false;
//		}            
//    }
//    public static void sendPieceMove(int val)
//    {
//		if (connected)
//		{
////add or modify.                    
//			serverOut.println(val + ":" + -1);
//			Network.myTurn = false;
//		}            
//    }
//    public static void sendPieceMove(int val,int val2)
//    {
//		if (connected)
//		{
////add or modify.                    
//			serverOut.println(val + ":" + val2);
//			Network.myTurn = false;
//		}            
//    }


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
                            /*
                            How to send data:
                            Server.recievePiece Move takes 4 values values are -1 if they are not used
                            value 0 = 1st card to be added
                            value 1 = second Card to be added
                            value 3 = player to be added to (0 for dealer, 1 for other player,2 for self)
                            value 4 = thePot
                            value 5 = stand (if recieve num !=-1, myturn=true)
                            */
//add or modify.                            
                            int ypost = Integer.parseInt(inputLine.split(":")[0]);
                            int xpost = Integer.parseInt(inputLine.split(":")[1]);
                            int zpost = Integer.parseInt(inputLine.split(":")[2]);
                            int wpost = Integer.parseInt(inputLine.split(":")[3]);
                            int vpost = Integer.parseInt(inputLine.split(":")[4]);
                            int upost = Integer.parseInt(inputLine.split(":")[5]);
                            int tpost = Integer.parseInt(inputLine.split(":")[6]);
                            
                            if(ypost!=-1)
                            {
                                if(xpost!=-1)
                                {
                                    if(zpost==0)
                                    {
                                        Network.dealer.hand.add(Card.cards[ypost]);
                                        Card.cards[ypost].setInPlay(true);
                                        Network.dealer.hand.add(Card.cards[xpost]);
                                        Card.cards[xpost].setInPlay(true);
                                    }
                                    if(zpost==1)
                                    {
                                        Network.goldfinger.hand.add(Card.cards[ypost]);
                                        Card.cards[ypost].setInPlay(true);
                                        Network.goldfinger.hand.add(Card.cards[xpost]);
                                        Card.cards[xpost].setInPlay(true);
                                        //Network.myTurn=true;
                                    }
                                    if(zpost==2)
                                    {
                                        Network.james.hand.add(Card.cards[ypost]);
                                        Card.cards[ypost].setInPlay(true);
                                        Network.james.hand.add(Card.cards[xpost]);
                                        Card.cards[xpost].setInPlay(true);
                                    }
                                    Network.beforeDeal=false;
                                    Network.betTime=true;
                                }
                                if(xpost==-1)
                                {
                                    if(zpost==0)
                                    {
                                        Network.dealer.hand.add(Card.cards[ypost]);
                                        Card.cards[ypost].setInPlay(true);
                                    }
                                    if(zpost==1)
                                    {
                                        Network.goldfinger.hand.add(Card.cards[ypost]);
                                        Card.cards[ypost].setInPlay(true);
                                    }
                                    if(zpost==2)
                                    {
                                        Network.james.hand.add(Card.cards[ypost]);
                                        Card.cards[ypost].setInPlay(true);
                                    }
                                    
                                }
                                                                                                       
                            }
                            if(ypost==-1)
                            {
                                if(wpost!=-1)
                                {
                                Network.thePot=wpost;
                                Network.myTurn = true;
                                }
                                if(wpost==-1)
                                {
                                    if(zpost==0)
                                    {
                                        Network.dealer.setStanding(true);
                                    }
                                    if(upost!=-1)
                                    {
                                        Network.james.setAmtMoney(upost);
                                        Network.goldfinger.setAmtMoney(tpost);
                                    }
                                    else
                                    Network.myTurn = true;
                                }
                            }
                            
                            
                            //Network.clientValue=xpost;
//                            if(xpost==-3)
//                            {
//                                Network.thePot=ypost;
//                            }
//                            else if(zpost==0)
//                            {
//                            Network.james.hand.add(Card.cards[xpost]);
//                            Card.cards[xpost].setInPlay(true);
//                            if(ypost!=-1)
//                            {
//                                Network.james.hand.add(Card.cards[ypost]);
//                            Card.cards[ypost].setInPlay(true);
//                            }
//                            }
//                            else if(zpost==-1)
//                            {
//                                Network.goldfinger.hand.add(Card.cards[xpost]);
//                                Card.cards[xpost].setInPlay(true);
//                                Network.goldfinger.hand.add(Card.cards[ypost]);
//                                Card.cards[ypost].setInPlay(true);
//                            }
//                            else if(zpost==-2)
//                            {
//                                Network.dealer.hand.add(Card.cards[xpost]);
//                                Card.cards[xpost].setInPlay(true);
//                                Network.dealer.hand.add(Card.cards[ypost]);
//                                Card.cards[ypost].setInPlay(true);
//                            }
                            
                            
                            //Network.myTurn = true;
 
                            
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
