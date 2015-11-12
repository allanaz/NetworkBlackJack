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
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import blackjack.Card;
import java.util.ArrayList;



public class ClientHandler
{
	public static boolean connected = false;
	public static Point postPoints = null;
	public static Point initPoints = null;
	private static String hostIP = null;
	private static int hostPort = -1;
	private static Socket server = null;
	private static PrintWriter serverOut = null;
	private static BufferedReader serverIn = null;

	public static void connect(String ip, int port) throws UnknownHostException, IOException
	{
		hostIP = ip;
		hostPort = port;
		server = new Socket();
		server.connect(new InetSocketAddress(ip, port), 6000);
		serverOut = new PrintWriter(server.getOutputStream(), true);
		serverIn = new BufferedReader(new InputStreamReader(server.getInputStream()));
		connected = true;
		recievePieceMove();
	}

	public static void disconnect()
	{
		try
		{
			if (server != null)
				server.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hostIP = null;
		hostPort = -1;
		server = null;
		serverOut = null;
		serverIn = null;
		connected = false;
		Network.gameStarted = false;
		Network.reset();
	}
    public static void sendPieceMove(ArrayList<Card> _hand  )
    {
		if (connected)
		{
//add or modify.                    
			serverOut.println(-1 + ":" + _hand);
			Network.myTurn = false;
		}        
    }
    /*
    How to send data:
    Server.recievePiece Move takes 4 values values are -1 if they are not used
    value 0 = 1st card to be added
    value 1 = second Card to be added
    value 3 = player to be added to (0 for dealer, 1 for other player,2 for self)
    value 4 = thePot
    value 5 = stand (if !=-1, then myturn=false)
    */
    public static void sendStand()
    {
        if(connected)
        {
            serverOut.println(-1 + ":" + -1 + ":" + -1 +":"+-1 + ":" + 1);
        }
    }
    public static void sendHit(int val)
    {
		if (connected)
		{
//add or modify.                    
			//serverOut.println(-1 + ":" + val + ":" + 0);
                    serverOut.println(val + ":" + -1 + ":" + 2 +":"+-1+":"+-1 );
			Network.myTurn = false;
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
    public static void sendOtherPlayerDeal(int val,int val2)
    {
		if (connected)
		{
//add or modify.                    
			serverOut.println(val + ":" + val2 + ":" + 1 + ":" + -1+":"+-1);
			Network.myTurn = false;
		}        
    }
    public static void sendPieceMove(int val,int val2,int val3)
    {
		if (connected)
		{
//add or modify.                    
			serverOut.println(val2 + ":" + val + ":" + val3);
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
		new Thread(new Runnable() {

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
//add or modify.
							// row:col:initrow:initcol
							int ypost = Integer.parseInt(inputLine.split(":")[0]);
							int xpost = Integer.parseInt(inputLine.split(":")[1]);
                                                        int zpost = Integer.parseInt(inputLine.split(":")[2]);
                                                        int wpost = Integer.parseInt(inputLine.split(":")[3]);
                                                        int vpost = Integer.parseInt(inputLine.split(":")[4]);
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
                                                                    Network.james.hand.add(Card.cards[ypost]);
                                                                    Card.cards[ypost].setInPlay(true);
                                                                    Network.james.hand.add(Card.cards[xpost]);
                                                                    Card.cards[xpost].setInPlay(true);
                                                                }
                                                                if(zpost==2)
                                                                {
                                                                    Network.goldfinger.hand.add(Card.cards[ypost]);
                                                                    Card.cards[ypost].setInPlay(true);
                                                                    Network.goldfinger.hand.add(Card.cards[xpost]);
                                                                    Card.cards[xpost].setInPlay(true);
                                                                }

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
                                                                    Network.james.hand.add(Card.cards[ypost]);
                                                                    Card.cards[ypost].setInPlay(true);
                                                                }
                                                                if(zpost==2)
                                                                {
                                                                    Network.goldfinger.hand.add(Card.cards[ypost]);
                                                                    Card.cards[ypost].setInPlay(true);
                                                                }

                                                            }

                                                        }
                                                        if(ypost==-1)
                                                        {
                                                            if(vpost==-1)
                                                            {
                                                            Network.thePot=wpost;
                                                            //Network.myTurn = true;
                                                            }
                                                            if(vpost==1)
                                                            {
                                                                //Network.myTurn = true;
                                                            }
                                                            
                                                        }
                                                        
                                                        //Network.serverValue=ypost;
//                                                        if(ypost>=0)
//                                                        {
//                                                        Network.goldfinger.hand.add(Card.cards[ypost]);
//                                                        Card.cards[ypost].setInPlay(true);
//                                                        if(xpost!=-1)
//                                                        {
//                                                            Network.goldfinger.hand.add(Card.cards[xpost]);
//                                                        Card.cards[xpost].setInPlay(true);
//                                                        }
//                                                        }
//                                                        else if(ypost==-3)
//                                                        {
//                                                            Network.thePot=xpost;
//                                                        }

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
				catch (IOException e)
				{
					disconnect();
				}

			}
		}).start();
	}

	public static boolean isConnected()
	{
		return connected;
	}
}
