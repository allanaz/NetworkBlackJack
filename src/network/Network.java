/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import blackjack.Card;
import blackjack.Player;

public class Network extends JFrame implements Runnable
{
    public static final int XBORDER = 20;
    public static final int YBORDER = 20;
    public static final int YTITLE = 25;
    public static final int WINDOW_WIDTH = 1920;
    public static final int WINDOW_HEIGHT = 1045;
    final public static int NUM_ROWS = 8;
    final public static int NUM_COLUMNS = 8;
    public static boolean animateFirstTime = true;
    public static int xsize = -1;
    public static int ysize = -1;
    public static Image image;

    public static Graphics2D g;
    /////////////////////////////////////////////////////////
    static Player james=new Player("James Bond",200);
    static Player goldfinger = new Player("Goldfinger",500);
    boolean gWin=false;
    boolean jWin =false;
    boolean gBust=false;
    boolean jBust=false;
    boolean yes = true;
    /////////////////////////////////////////////////////////
    boolean showRules=true;
    Image table;
    /**
     * Variables to do with gameplay
     */
    public static boolean gameStarted = false;
    public static boolean myTurn;

    public static int serverValue = 0;
    public static int clientValue = 0;
    
    String ipAddress = new String();
    
    /**
     * Variables to do with main menu
     */
 //   public static MainMenu mainMenu;
 
    /**
     * Multiplayer variables
     */
    public static boolean isConnecting = false;
    public static boolean isClient;
    Thread relaxer;

    public Network()
    {
        addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                if (e.BUTTON1 == e.getButton())
                {

                }

                repaint();

            }
        });

        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent e)
            {
                if (e.BUTTON1 == e.getButton())
                {

                }

                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter()
        {
            public void mouseDragged(MouseEvent e)
            {
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter()
        {
            public void mouseMoved(MouseEvent e)
            {
                repaint();
            }
        });

        addKeyListener(new KeyAdapter()
        {

            public void keyPressed(KeyEvent e)
            {
//add or modify.
                /////////////////////////////////////////////////////////
                if (myTurn && gameStarted )
                {
				if (isClient)
                                {
                                        if(e.getKeyCode()==KeyEvent.VK_D)
                                            {
                                                if(!james.getInGame())
                                                {
                                                int hitCardIndex=0;
                                                int hitCardIndex2=0;
//                                                james.dealMeIn();
                                                hitCardIndex=james.hit();
                                                hitCardIndex2=james.hit();
                                                System.out.println("sending from client");
                                                ClientHandler.sendPieceMove(hitCardIndex,hitCardIndex2);
                                                james.setInGame(true);
                                                
                                                }
                                                
                                                
                                            }
                                            if(e.getKeyCode()==KeyEvent.VK_H)
                                            {
                                                int hitCardIndex=0;
                                                hitCardIndex=james.hit();
                                                System.out.println("sending from client");
                                                ClientHandler.sendPieceMove(hitCardIndex);
//                                                if(james.getHandValue()==21)
//                                                            jWin=true;
//                                                else if(james.getHandValue()>21)
//                                                            jBust=true;
                                            }
                                    
                                        
                                }
				else
                                {
                                    if(e.getKeyCode()==KeyEvent.VK_D)
                                            {
                                                if(!goldfinger.getInGame())
                                                {
                                                int hitCardIndex=0;
                                                int hitCardIndex2=0;
                                                //goldfinger.dealMeIn();
                                                hitCardIndex=goldfinger.hit();
                                                hitCardIndex2=goldfinger.hit();
                                                System.out.println("sending from client");
                                                ServerHandler.sendPieceMove(hitCardIndex,hitCardIndex2);
                                                goldfinger.setInGame(true);
                                                
                                                }
                                            }
                                            if(e.getKeyCode()==KeyEvent.VK_H)
                                            {
                                                int hitCardIndex=0;
                                                hitCardIndex=goldfinger.hit();
                                                System.out.println("sending from server");
                                   // serverValue++;
					ServerHandler.sendPieceMove(hitCardIndex);
//                                                if(goldfinger.getHandValue()==21)
//                                                    gWin=true;
//                                                else if(goldfinger.getHandValue()>21)
//                                                    gBust=true;
                                            }
                                    
                                        
                                }
                                
                }
                ///////////////////////////////////////////////////////
                else if (myTurn && gameStarted && e.getKeyCode() == KeyEvent.VK_2)
                {
			if (isClient)
                                {
                                    System.out.println("sending from client");
                                    clientValue+=2;
					ClientHandler.sendPieceMove(clientValue);
                                }
				else
                                {
                                    System.out.println("sending from server");
                                    serverValue+=2;
					ServerHandler.sendPieceMove(serverValue);
                                }	
			                    
                }
                
                else if (e.getKeyCode() == KeyEvent.VK_S)
                {
                    if (!isConnecting)
                    {
                        try
                        {
                    
                            isConnecting = true;
                            System.out.println("is connecting true");
                            ServerHandler.recieveConnect(5657);
                            System.out.println("after recieveConnect");
                            if (ServerHandler.connected)
                            {
                                isClient = false;
                                myTurn = false;
                                gameStarted = true;
                                isConnecting = false;
                            }
                        }
                        catch (IOException ex)
                        {
                            System.out.println("Cannot host server: " + ex.getMessage());
                            isConnecting = false;
                        }                        
                    }
                }
                else if (e.getKeyCode() == KeyEvent.VK_C)
                {
                    if (!isConnecting)
                    {
                    
                            try
                            {
                   
                                isConnecting = true;
                                ClientHandler.connect(ipAddress, 5657);
                                if (ClientHandler.connected)
                                {
                                    isClient = true;
                                    myTurn = true;
                                    gameStarted = true;
                                    isConnecting = false;
                                }
                            }
                            catch (IOException ex)
                            {
                                System.out.println("Cannot join server: " + ex.getMessage());
                                isConnecting = false;
                            }                    
                    }
                }                
                else
                {
                    if (!gameStarted)
                    {
                        if (e.getKeyCode() == KeyEvent.VK_0)
                        {
                            ipAddress += "0";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_1)
                        {
                            ipAddress += "1";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_2)
                        {
                            ipAddress += "2";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_3)
                        {
                            ipAddress += "3";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_4)
                        {
                            ipAddress += "4";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_5)
                        {
                            ipAddress += "5";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_6)
                        {
                            ipAddress += "6";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_7)
                        {
                            ipAddress += "7";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_8)
                        {
                            ipAddress += "8";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_9)
                        {
                            ipAddress += "9";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_PERIOD)
                        {
                            ipAddress += ".";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_Q)
                        {
                            ipAddress = ipAddress.substring(0,ipAddress.length()-1);
                        }
                        else if(e.VK_SPACE == e.getKeyCode()) {
                            	if(showRules==true)
                            	{
                                	showRules=false;
                            	}
                            	else
                            	{
                                	showRules=true;
                            	}
                        	}

                        else if(e.getKeyCode()==KeyEvent.VK_I)
                        {
                            try
                            {
                            ipAddress=InetAddress.getLocalHost().getHostAddress();
                            }
                            catch (UnknownHostException a)
                            {
                                a.printStackTrace();
                            }
                        }
                    }
                }
                /////////////////////////////////////////////////////////
//                if(e.getKeyCode()==KeyEvent.VK_D)
//                {
//                    if(!james.getInGame())
//                    {
//                    james.dealMeIn();
//                    james.setInGame(true);
//                    if(james.getHandValue()==21)
//                                jWin=true;
//                    }
//                    else if(james.getHandValue()>21)
//                                jBust=true;
//                }
//                if(e.getKeyCode()==KeyEvent.VK_H)
//                {
//                    james.hit();
//                    if(james.getHandValue()==21)
//                                jWin=true;
//                    else if(james.getHandValue()>21)
//                                jBust=true;
//                }
                /////////////////////////////////////////////////////////
                if(e.getKeyCode()==KeyEvent.VK_F)
                        {
                            if(!goldfinger.getInGame())
                            {
                            goldfinger.dealMeIn();
                            goldfinger.setInGame(true);
                            if(goldfinger.getHandValue()==21)
                                gWin=true;
                            else if(goldfinger.getHandValue()>21)
                                gBust=true;
                            }
                        }
                        if(e.getKeyCode()==KeyEvent.VK_J)
                        {
                            goldfinger.hit();
                            if(goldfinger.getHandValue()==21)
                                gWin=true;
                            else if(goldfinger.getHandValue()>21)
                                gBust=true;
                        }
                /////////////////////////////////////////////////////////
                
                
                
                if (gameStarted || isConnecting)
                {
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE && !isConnecting)
                    {
                        if (gameStarted)

                            if (isClient)
                            {
                                ClientHandler.sendDisconnect();
                                ClientHandler.disconnect();
                            }
                            else
                            {
                                ServerHandler.sendDisconnect();
                                ServerHandler.disconnect();
                            }
                        gameStarted = false;
                        reset();
                    }
                }

                repaint();
            }
        });
        init();
        start();
    }

    public static void main(String[] args)
    {
        Network frame = new Network();
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setTitle("Network");
        frame.setResizable(true);
    }

    /**
     * Resets all variables and restarts game
     */
    public static void reset()
    {
        Card.createDeck();

    }


    // ///////////////////////////////////////////////////////////////////////
    public static int getX(int x)
    {
        return (x + XBORDER);
    }

    public static int getY(int y)
    {
        return (y + YBORDER + YTITLE);
    }

    public static int getYNormal(int y)
    {
        return (-y + YBORDER + YTITLE + getHeight2());
    }

    public static int getWidth2()
    {
        return (xsize - getX(0) - XBORDER);
    }

    public static int getHeight2()
    {
        return (ysize - getY(0) - YBORDER);
    }

    // //////////////////////////////////////////////////////////////////////////
    public void init()
    {
        requestFocus();
    }

    // //////////////////////////////////////////////////////////////////////////
    public void destroy()
    {
    }

    /**
     * Paints the graphic
     */
    public void paint(Graphics gOld)
    {
        if (image == null || xsize != getSize().width || ysize != getSize().height)
        {
            xsize = getSize().width;
            ysize = getSize().height;
            image = createImage(xsize, ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        if (animateFirstTime)
        {
            gOld.drawImage(image, 0, 0, null);
            return;
        }

        int x[] = {getX(0), getX(getWidth2()), getX(getWidth2()), getX(0), getX(0)};
        int y[] = {getY(0), getY(0), getY(getHeight2()), getY(getHeight2()), getY(0)};
        int ydelta = getHeight2() / NUM_ROWS;
        int xdelta = getWidth2() / NUM_COLUMNS;
        // put all paint commands under this line

        
        
        // far outer border
        g.setColor(Color.black);
        g.fillRect(0, 0, xsize, ysize);
        // ----------------

        // background
      
       // g.setColor(Color.white);
        g.fillPolygon(x, y, 4);
        
        g.drawImage(table,getX(0),getY(0),getWidth2(),getHeight2(),this);
//add or modify.   
        /////////////////////////////////////////////////////////
        g.setColor(Color.red);
        g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
        g.drawString(james.getName(), 200, 250);
                        int index=0;

        for(Card temp: james.hand)
        {
            if(temp!=null)
            {
                        

          if(temp.getSuite()==Card.Suite.SPECIAL)
                g.drawString("?", 300+index, 350);
            else      
        temp.drawCard(g,300 + index,300,5,5,temp.getValue(),temp.getSuite(),isClient);

            index+=170;
            }
        }
        g.setColor(Color.red);

        if(jWin)
        {
            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
            g.drawString("Bond Wins", 600, 200);
        }
        if(jBust)
        {
            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
            g.drawString("Bond Bust", 600, 250);
        }
        /////////////////////////////////////////////////////////
        g.setColor(Color.red);
        g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
        g.drawString(goldfinger.getName(), 200, 400);
                        int index2=0;

        for(Card temp: goldfinger.hand)
        {
            if(temp!=null)
            {
                if(temp.getSuite()==Card.Suite.SPECIAL)
                g.drawString("?", 300+index, 350);
            else
           temp.drawCard(g,300 + index2,550,5,5,temp.getValue(),temp.getSuite(),!isClient);
            index2+=170;
            }
        }
        g.setColor(Color.red);
        if(gWin)
        {
            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
            g.drawString("Goldfinger Wins", 600, 220);
        }
        if(gBust)
        {
            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
            g.drawString("Goldfinger Bust", 600, 270);
        }
        /////////////////////////////////////////////////////////
        if (!gameStarted)
        {
            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
            g.setColor(Color.black);
            g.drawString("Not Connected",100,150);
            
        }
        else if (isClient)
        {
            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
            g.setColor(Color.black);
            g.drawString("The Client",100,150);
        }
        else
        {
            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
            g.setColor(Color.black);
            g.drawString("The Server",100,150);
        }            


        {
            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
            g.setColor(Color.black);
            g.drawString("Client value " + clientValue,100,200);
        }

        {
            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
            g.setColor(Color.black);
            g.drawString("Server value " + serverValue,100,300);
            
        }
        //shows rules
    	if(showRules==true)
    	{
        	g.setColor(Color.black);
       	g.fillRect(getX(getWidth2()/3-3),getY(0),getWidth2()/3,getHeight2()/2+5);
       	
       	g.setColor(Color.white);
    	g.setFont(new Font("Impact",Font.BOLD,50));
    	
    	g.drawString("Blackjack", getX(getWidth2()/3+170),getY(40));
    	
    	g.setFont(new Font("Impact",Font.BOLD,20));
     	
    	g.drawString("1.Each player will start out with $1000.", getX(getWidth2()/3),getY(65));
    	g.drawString("2.At the start of each round each player will put a $10 in the pot. ", getX(getWidth2()/3),getY(90));
    	g.drawString("4.At the start of each round each player is given 2 random cards.", getX(getWidth2()/3),getY(115));
    	g.drawString("5.The player will add the values of the cards. The value is the number", getX(getWidth2()/3),getY(140));
    	g.drawString("indicated on the card. The king, queen, jack cards are are all worth", getX(getWidth2()/3),getY(165));
    	g.drawString("10 points and the ace is worth 11 or 1 points.", getX(getWidth2()/3),getY(190));
    	g.drawString("6.To get the money you want to be as close to 21 without going over", getX(getWidth2()/3),getY(215));
    	g.drawString("7.Before getting more cards a player can choose to raise", getX(getWidth2()/3),getY(240));
    	g.drawString(" the amount each player has in the pot.", getX(getWidth2()/3),getY(265));
    	g.drawString("8.To stay in you have to raise your amount of money in the pot.", getX(getWidth2()/3),getY(290));
    	g.drawString("9.On your turn you can choose to get another card which will add the ", getX(getWidth2()/3),getY(315));
    	g.drawString("corresponding amount of points. You can keep adding cards until your ", getX(getWidth2()/3),getY(340));
    	g.drawString("turn is over 21 or you decide to stop.", getX(getWidth2()/3),getY(365));
    	g.drawString("10.There are special cards that you can do which will help you/harm ", getX(getWidth2()/3),getY(390));
    	g.drawString("the other player or will help the other player.", getX(getWidth2()/3),getY(415));
    	g.drawString("11.Who ever is the closest to 21 without going over wins all", getX(getWidth2()/3),getY(440));
    	g.drawString("the other players bets.", getX(getWidth2()/3),getY(465));
    	g.drawString("12.To win you have to have the most money out of all the players.", getX(getWidth2()/3),getY(490));
    	
    	}

            try
            {
                g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
                g.setColor(Color.black);
                g.drawString("Your IP address: " + InetAddress.getLocalHost().getHostAddress(), getX(10), getY(20));
                g.drawString("Enter IP address: " + ipAddress, getX(10), getY(60));
            }
            catch (UnknownHostException e)
            {
                e.printStackTrace();
            }
                    
        // put all paint commands above this line
        gOld.drawImage(image, 0, 0, null);
    }

    // //////////////////////////////////////////////////////////////////////////
    // needed for implement runnable
    public void run()
    {
        while (true)
        {
            update();
            repaint();
            double seconds = .1; // time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try
            {
                Thread.sleep(miliseconds);
            }
            catch (InterruptedException e)
            {
            }
        }
    }

    /**
     * Updates state of game
     */
    public void update()
    {

        if (animateFirstTime)
        {
            animateFirstTime = false;
            if (xsize != getSize().width || ysize != getSize().height)
            {
                xsize = getSize().width;
                ysize = getSize().height;
            }
            table = Toolkit.getDefaultToolkit().getImage("./table.PNG");

            reset();
        }
        if(james.getHandValue()==21)
                    jWin=true;
        else if(james.getHandValue()>21)
                    jBust=true;
        if(goldfinger.getHandValue()==21)
            gWin=true;
        else if(goldfinger.getHandValue()>21)
            gBust=true;
        
        
    }

    // //////////////////////////////////////////////////////////////////////////
    public void start()
    {
        if (relaxer == null)
        {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }

    // //////////////////////////////////////////////////////////////////////////
    public void stop()
    {
        if (relaxer.isAlive())
        {
            relaxer.stop();
        }
        relaxer = null;
    }
}
