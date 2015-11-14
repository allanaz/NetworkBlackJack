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
    public static final int WINDOW_WIDTH = 1920/2;
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
<<<<<<< HEAD
    boolean gWin=false;
    boolean jWin =false;
    boolean gBust=false;
    boolean jBust=false;
    boolean yes = true;
=======
    static Player dealer = new Player("Dealer Yee",1000);
    static boolean gWin=false;
    static boolean jWin =false;
    static boolean gBust=false;
    static boolean jBust=false;
    static boolean dWin=false;
    static boolean dBust=false;
    static boolean hitTime = false;
    static boolean betTime=false;
    static boolean beforeDeal= true;
    static boolean roundEnd= false;
    static boolean roundOver=false;
    public static int thePot=0;
    public static int myBet=0;
    
    boolean showRules=false;
    /////////////////////////////////////////////////////////
>>>>>>> refs/remotes/origin/master
    
    /////////////////////////////////////////////////////////
    boolean showRules=false;
    Image table;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
                
                if (myTurn && gameStarted )
                {
                if (e.BUTTON1 == e.getButton())
                {
                    //getX(getWidth2()-1005), getY(getHeight2()*15/16), getHeight2()/8, 100                    
                    int xpos = e.getX();
                    int ypos = e.getY();
                    if(xpos<getX(getWidth2()-5)&&xpos>getX(getWidth2()-105)
                            &&ypos<(getY(getHeight2()*13/16)+50)&&ypos>getY(getHeight2()*13/16))                        
                    {
                        System.out.println("hitBox");
                        if(isClient)
                        {
                            System.out.println("sending from client");
                            ClientHandler.sendStand();
                            james.setStanding(true);
                        }
                        else
                        {
                            System.out.println("sending from server");
                            ServerHandler.sendStand();
                            goldfinger.setStanding(true);
                        }
                              
                        
                    }
                    if(xpos<getX(getWidth2()-5)&&xpos>getX(getWidth2()-105)
                            &&ypos<(getY(getHeight2()*15/16)+50)&&ypos>getY(getHeight2()*15/16))                        
                    {
                        System.out.println("hitBox");
                        if(isClient&&hitTime)
                        {
                            int hitCardIndex=0;
                            hitCardIndex=james.hit();
                            System.out.println("sending from client");
                            ClientHandler.sendHit(hitCardIndex);
                        }
                        else if(hitTime)
                        {
                            int hitCardIndex=0;
                            hitCardIndex=goldfinger.hit();
                            System.out.println("sending from server");
                            ServerHandler.sendHit(hitCardIndex);
                        }
                              
                        
                    }
                    
                }
                }
                if(e.BUTTON3==e.getButton())
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
                                        if(e.getKeyCode()==KeyEvent.VK_D&&beforeDeal)
                                            {
                                                if(!james.getInGame())
                                                {
                                                int hitCardIndex=0;
                                                int hitCardIndex2=0;
//                                                james.dealMeIn();
                                                hitCardIndex=james.hit();
                                                hitCardIndex2=james.hit();
                                                System.out.println("sending from client");
                                                ClientHandler.sendDeal(hitCardIndex,hitCardIndex2);
                                                james.setInGame(true);
<<<<<<< HEAD

                                                //////////////////////////////////////////////////////////////////////////////////////////////////////////////// 

                                                for(Card temp: james.hand)

                                                        {
                                                            if(temp!=null)
                                                            {
                                                                
                                                               if(temp.getSuite()==Card.Suite.SPECIAL)
                                                                {
                                                                    temp.doEffect(james, goldfinger);
                                                                }
                                                            }
                                                        }

                                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

=======
                                                
                                                int dealerCardIndex=0;
                                                int dealerCardIndex2=0;
                                                dealerCardIndex=dealer.hit();
                                                dealerCardIndex2=dealer.hit();
                                                System.out.println("sending Dealer from client");
                                                ClientHandler.sendDealerDeal(dealerCardIndex,dealerCardIndex2);
                                                dealer.setInGame(true);
                                                
                                                int hitCardIndex3=0;
                                                int hitCardIndex4=0;
                                                hitCardIndex3=goldfinger.hit();
                                                hitCardIndex4=goldfinger.hit();
                                                System.out.println("sending from client");
                                                ClientHandler.sendOtherPlayerDeal(hitCardIndex3,hitCardIndex4);
                                                goldfinger.setInGame(true);
                                                
                                                beforeDeal=false;
                                                betTime=true;
>>>>>>> refs/remotes/origin/master
                                                }
                                                
                                                
                                            }
                                            if(e.getKeyCode()==KeyEvent.VK_H&&hitTime)
                                            {
                                                int hitCardIndex=0;
                                                hitCardIndex=james.hit();
                                                System.out.println("sending from client");
                                                ClientHandler.sendHit(hitCardIndex);
//                                                for(Card temp: james.hand)
//
//                                                        {
//                                                            if(temp!=null)
//                                                            {
//                                                               if(temp.getSuite()==Card.Suite.SPECIAL)
//                                                                {
//                                                                    temp.doEffect(james, goldfinger);
//                                                                }
//                                                            }
//                                                        }

//                                                if(james.getHandValue()==21)
//                                                            jWin=true;
//                                                else if(james.getHandValue()>21)
//                                                            jBust=true;
                                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                for(Card temp: james.hand)
                                                        {
                                                            if(temp!=null)
                                                            {
                                                               if(temp.getSuite()==Card.Suite.SPECIAL)
                                                                {
                                                                    temp.doEffect(james, goldfinger);
                                                                }
                                                            }
                                                        }
                                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                            }
                                            if(e.getKeyCode()==KeyEvent.VK_U&&hitTime)
                                            {
                                                for(Card temp :james.hand)
                                                {
                                                    if(temp.getSuite()==Card.Suite.SPECIAL)
                                                    {
                                                        temp.doEffect(james, goldfinger);
                                                    }
                                                }
                                                
                                            }
                                            if(e.getKeyCode()==KeyEvent.VK_B&&betTime)
                                            {
                                                thePot+=2;
                                                myBet+=2;
                                                System.out.println("sending from client");
                                                ClientHandler.sendBet(thePot);                                               
                                            }
                                    
                                        
                                }
				else
                                {
<<<<<<< HEAD
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
                                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                for(Card temp: goldfinger.hand)
                                                        {
                                                            if(temp!=null)
                                                            {
                                                               if(temp.getSuite()==Card.Suite.SPECIAL)
                                                                {
                                                                    temp.doEffect(goldfinger, james);
                                                                }
                                                            }
                                                        }
                                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                }
                                            }
                                            if(e.getKeyCode()==KeyEvent.VK_H)
=======
//                                    if(e.getKeyCode()==KeyEvent.VK_D)
//                                            {
//                                                if(!goldfinger.getInGame())
//                                                {
//                                                int hitCardIndex=0;
//                                                int hitCardIndex2=0;
//                                                //goldfinger.dealMeIn();
//                                                hitCardIndex=goldfinger.hit();
//                                                hitCardIndex2=goldfinger.hit();
//                                                System.out.println("sending from client");
//                                                ServerHandler.sendPieceMove(hitCardIndex,hitCardIndex2);
//                                                goldfinger.setInGame(true);
//                                                
//                                                }
//                                            }
                                            if(e.getKeyCode()==KeyEvent.VK_H&&hitTime)
>>>>>>> refs/remotes/origin/master
                                            {
                                                int hitCardIndex=0;
                                                hitCardIndex=goldfinger.hit();
                                                System.out.println("sending from server");
                                   // serverValue++;
					ServerHandler.sendHit(hitCardIndex);
//                                                if(goldfinger.getHandValue()==21)
//                                                    gWin=true;
//                                                else if(goldfinger.getHandValue()>21)
//                                                    gBust=true;
                                            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                        for(Card temp: goldfinger.hand)
                                                        {
                                                            if(temp!=null)
                                                            {
                                                               if(temp.getSuite()==Card.Suite.SPECIAL)
                                                                {
                                                                    temp.doEffect(goldfinger, james);
                                                                }
                                                            }
                                                        }
                                                        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                            }
                                            if(e.getKeyCode()==KeyEvent.VK_U&&hitTime)
                                            {
                                                for(Card temp :goldfinger.hand)
                                                {
                                                    if(temp.getSuite()==Card.Suite.SPECIAL)
                                                    {
                                                        temp.doEffect(goldfinger, james);
                                                    }
                                                }
                                                
                                            }
                                            if(e.getKeyCode()==KeyEvent.VK_B&&betTime)
                                            {
                                                thePot+=2;
                                                myBet+=2;
                                                System.out.println("sending from server");
                                                //ServerHandler.sendPieceMove(-3,thePot);
                                                ServerHandler.sendBet(thePot);
                                            }
                                    
                                        
                                }
                                
                }
                if(gameStarted&&roundOver)
                {
                    if(e.getKeyCode()==KeyEvent.VK_R)
                    {
                        newRound();
                    }
                }
                ///////////////////////////////////////////////////////
//                else if (myTurn && gameStarted && e.getKeyCode() == KeyEvent.VK_B)
//                {
//			if (isClient)
//                                {
//                                    System.out.println("sending from client");
//                                    thePot+=2;
//					ClientHandler.sendPieceMove(thePot,-3);
//                                }
//				else
//                                {
//                                    System.out.println("sending from server");
//                                    thePot+=2;
//					ServerHandler.sendPieceMove(-3,thePot);
//                                }	
//			                    
//                }
                
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
                        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
                        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
        for(Card temp:Card.cards)
        {
        System.out.println(temp);
                }
    }
    public static void newRound()
    {
        for(Card temp : Card.cards)
        {
            ///////////issue resetting the cards because one or more is null, and cannot be accessed////
            temp.setInPlay(false);
            temp.setPlayerNull();
            
        }
        james.hand.clear();
        goldfinger.hand.clear();
        dealer.hand.clear();
        james.setStanding(false);
        goldfinger.setStanding(false);
        dealer.setStanding(false);
        james.setInGame(false);
        goldfinger.setInGame(false);
        dealer.setInGame(false);
        if(isClient)
        {
            myTurn=true;
        }
        else
        {
            myTurn=false;
        }
        gWin=false;
        jWin =false;
        gBust=false;
        jBust=false;
        dWin=false;
        dBust=false;
        hitTime = false;
        betTime=false;
        beforeDeal= true;
        roundEnd= false;
        roundOver=false;
        thePot=0;
        myBet=0;
        

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
        g.setColor(Color.white);
        g.fillRect(0, 0, xsize, ysize);
        // ----------------

        // background
<<<<<<< HEAD
      
       // g.setColor(Color.white);
=======
        Color background = new Color(40,59,64);
        g.setColor(background);
>>>>>>> refs/remotes/origin/master
        g.fillPolygon(x, y, 4);
        
        g.drawImage(table,getX(0),getY(0),getWidth2(),getHeight2(),this);
//add or modify.   
        /////////////////////////////////////////////////////////
        if(gameStarted)
        {
            Color controlPanel= new Color(73,107,115);
        g.setColor(controlPanel);
        g.fillRect(getX(0), getY(getHeight2()*3/4), getWidth2(), getHeight2()/4);
        Color button = new Color(217,197,137);
        
        g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 28));
        
        g.setColor(button);
        g.fillRoundRect(getX(getWidth2()-105), getY(getHeight2()*13/16), 100, 50, 2, 2);
        g.fillRoundRect(getX(getWidth2()-105), getY(getHeight2()*15/16), 100, 50, 2, 2);
        g.setColor(Color.black);
        g.drawString("STAND", getX(getWidth2()-100), getY(getHeight2()*13/16+45));
        g.drawString("HIT", getX(getWidth2()-100), getY(getHeight2()*15/16+45));
        
        g.setColor(button);
        g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
//        g.drawString("isClient is "+isClient, getX(5), getY(100));
//        g.drawString("My Turn "+myTurn, getX(5), getY(120));
//        g.drawString("dealerstand is "+dealer.getStanding(), getX(5), getY(140));
//        g.drawString("roundEnd is "+roundEnd, getX(5), getY(160));
        if(isClient)
        {
        
        g.setColor(Color.red);
        
        g.fillOval(getX(5), getY(getHeight2()*7/8), getHeight2()/10, getHeight2()/10);
        
        
        //System.out.println(getY(getHeight2()*13/16));
        g.setColor(button);
        g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
        g.drawString(james.getName()+james.getAmtMoney(), getX(5), getY(getHeight2()*3/4+15));
        
        
        int index=0;
        for(Card temp: james.hand)
        {
            if(temp!=null)
            {
                        

                
        temp.drawCard(g,300 + index,getY(getHeight2()*13/16),8,8,temp.getValue(),temp.getSuite(),isClient);

            index+=65;
            }
        }        
        ////
        g.setColor(button);
        g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
<<<<<<< HEAD
        g.drawString(james.getName(), 200, 250);
                        int index=0;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
=======
        g.drawString(goldfinger.getName(), getX(5), 200);
                        int index2=0;

        for(Card temp: goldfinger.hand)
        {
            if(temp!=null)
            {
           temp.drawCard(g,getX(5) ,210+ index2,3,3,temp.getValue(),temp.getSuite(),roundOver);
            index2+=65;
            }
        }
        }
        else
        {
            g.setColor(button);
        g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
        g.drawString(james.getName(),getX(5),200);
        int index=0;
>>>>>>> refs/remotes/origin/master
        for(Card temp: james.hand)
        {
            if(temp!=null)
            {
                        


//                if(temp.getSuite()==Card.Suite.SPECIAL&&temp.getFaceUp()==true);
//                {
//                      temp.drawJester(g,300.0 + index,300,180,5.0,5.0);
//                      //drawJester(g,7.5,10,180,.4,.4);
//
//
//                }
                  
                
<<<<<<< HEAD
              temp.drawCard(g,300 + index,300,5,5,temp.getValue(),temp.getSuite(),isClient,temp.getInPlay());

                  index+=170;
            
        }
    }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        g.setColor(Color.red);
=======
        temp.drawCard(g,getX(5) ,210+ index,3,3,temp.getValue(),temp.getSuite(),roundOver);

            index+=65;
            }
        }        
        ////
        g.setColor(button);
        g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
        g.drawString(goldfinger.getName()+goldfinger.getAmtMoney(),getX(5), getY(getHeight2()*3/4+15));
        int index2=0;
        for(Card temp: goldfinger.hand)
        {
            if(temp!=null)
            {
           temp.drawCard(g,300 + index2,getY(getHeight2()*13/16),5,5,temp.getValue(),temp.getSuite(),!isClient);
            index2+=65;
            }
        }
        }
        ///////////Display Dealer////////////
        g.setColor(button);
        g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
        g.drawString(dealer.getName(),getX(getWidth2()-105),200);
        int index =0;
        for(Card temp: dealer.hand)
        {
            boolean faceUp=true;
            
            if(temp!=null)
            {
                        if(index==0&&!roundOver)
                            faceUp=false;
                        
>>>>>>> refs/remotes/origin/master

                
        temp.drawCard(g,getX(getWidth2()-50) ,210+ index,3,3,temp.getValue(),temp.getSuite(),faceUp);

            index+=65;
            }
        }
        g.setColor(button);
        g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
        g.drawString("The Pot: "+thePot,getX(getWidth2()-getWidth2()/2),200);
        ////////////////Table Directions/Title///////////////
            g.setColor(Color.white);
            Toolkit tk = Toolkit.getDefaultToolkit();
            int fontSize = (int)(tk.getScreenSize().getHeight()/1080*150);
            Font title = new Font("Bodoni MT",Font.BOLD,fontSize);
            g.setFont(title);
            FontMetrics fontMetrics = g.getFontMetrics(title);
            int xPos= (int) (getWidth2()/2-(fontMetrics.stringWidth("BLACKJACK")/2));
            g.drawString("BLACKJACK", getX(xPos), getY(getHeight2()/2));
            g.setColor(button);
            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
            if(beforeDeal)
            {
                if(isClient)
                    g.drawString("Hit the Deal Button to start the game", getX(xPos), getY(getHeight2()/2)+40);
                else
                    g.drawString("Wait for the other player to start the game",getX(xPos), getY(getHeight2()/2+40));              
            }
            else if(betTime)
            {
                if(isClient)
                {
                    if(myTurn)
                    g.drawString("Click a Poker Chip to Bet its value", getX(xPos), getY(getHeight2()/2)+40);
                    else
                    g.drawString("Other player is betting...", getX(xPos), getY(getHeight2()/2)+40);    
                }
                else
                {
                    if(!myTurn)
                        g.drawString("Other player is betting...", getX(xPos), getY(getHeight2()/2)+40);
                    
                    else
                        g.drawString("Click a Poker Chip to Bet its value", getX(xPos), getY(getHeight2()/2)+40);
                }
            }
            else if(hitTime)
            {
                if(isClient)
                {
                    if(!james.getStanding())
                        g.drawString("Hit until you want to stop. Then click the Stand Button", getX(xPos), getY(getHeight2()/2)+40);
                    if(james.getStanding())
                        g.drawString("Other player hitting...", getX(xPos), getY(getHeight2()/2)+40);
                }
                else
                {
                    if(!james.getStanding())
                        g.drawString("Other player hitting...", getX(xPos), getY(getHeight2()/2)+40);
                    else if(!goldfinger.getStanding())
                        g.drawString("Hit until you want to stop. Then click the Stand Button", getX(xPos), getY(getHeight2()/2)+40);
                    
                        
                }
            }
            else if(roundOver)
            {
                g.drawString("Hit the new Round Button to start next round", getX(xPos), getY(getHeight2()/2)+40);
            }
        //////////win/bust/////////
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
<<<<<<< HEAD
        /////////////////////////////////////////////////////////
        g.setColor(Color.red);
        g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
        g.drawString(goldfinger.getName(), 200, 400);
                        int index2=0;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        for(Card temp: goldfinger.hand)
        {
            if(temp!=null)
            {
//                if(temp.getSuite()==Card.Suite.SPECIAL)
//                g.drawString("?", 300+index, 350);
//            else
//                {
//                    g.fillRect(300 + index,300,5,5);
//                }
//               
           temp.drawCard(g,300 + index2,550,5,5,temp.getValue(),temp.getSuite(),!isClient,temp.getInPlay());
            index2+=170;
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
=======
        /////////////////////////////////////////////////////////        
>>>>>>> refs/remotes/origin/master
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
        g.setColor(Color.red);
        if(dWin)
        {
            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
            g.drawString("Dealer Wins", 600, 220);
        }
        if(dBust)
        {
            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
            g.drawString("Dealer Bust", 600, 270);
        }
        }
        /////////////////////////////////////////////////////////
        if (!gameStarted)
        {
            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 50));
            g.setColor(Color.white);
            g.drawString("Not Connected",getX(800),getY(600));
            
        }
        else if (isClient)
        {
            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 40));
            g.setColor(Color.white);
            g.drawString("The Client",getX(0),getY(40));
        }
        else
        {
            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 40));
            g.setColor(Color.white);
            g.drawString("The Server",getX(0),getY(40));
        }            


//        {
//            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
//            g.setColor(Color.black);
//            g.drawString("Client value " + clientValue,100,200);
//        }
//
//        {
//            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
//            g.setColor(Color.black);
//            g.drawString("Server value " + serverValue,100,300);
//            
//        }
        if(!gameStarted)
        {
            Toolkit tk = Toolkit.getDefaultToolkit();
            int fontSize = (int)(tk.getScreenSize().getHeight()/1080*200);
            Font title = new Font("Bodoni MT",Font.BOLD,fontSize);
            g.setFont(title);
            FontMetrics fontMetrics = g.getFontMetrics(title);
            int xPos= (int) (getWidth2()/2-(fontMetrics.stringWidth("BLACKJACK")/2));
            g.drawString("BLACKJACK", getX(xPos), getY(getHeight2()/2));
            
<<<<<<< HEAD
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //shows rules
    	if(showRules==true)
    	{
        	g.setColor(Color.black);
       	g.fillRect(getX(getWidth2()/3-3),getY(0),getWidth2()/3,getHeight2()/2+5+25);
       	
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
    	g.drawString("7.Before getting more cards a player can choose to raise/match the bet", getX(getWidth2()/3),getY(240));
    	g.drawString(" the amount each player has in the pot.", getX(getWidth2()/3),getY(265));
    	g.drawString("8.To stay in you have to raise/match your amount of money in the pot.", getX(getWidth2()/3),getY(290));
    	g.drawString("9.On your turn you can choose to get another card which will add the ", getX(getWidth2()/3),getY(315));
    	g.drawString("corresponding amount of points. You can keep adding cards until your ", getX(getWidth2()/3),getY(340));
    	g.drawString("turn is over 21 or you decide to stop.", getX(getWidth2()/3),getY(365));
    	g.drawString("10.There are special cards that you can do which will help you/harm ", getX(getWidth2()/3),getY(390));
    	g.drawString("the other player or will help the other player.", getX(getWidth2()/3),getY(415));
    	g.drawString("11.Who ever is the closest to 21 without going over wins all", getX(getWidth2()/3),getY(440));
    	g.drawString("the other players bets.", getX(getWidth2()/3),getY(465));
        g.drawString("12.If there is a tie each player gets their money back", getX(getWidth2()/3),getY(490));
    	g.drawString("13.To win you have to have the most money out of all the players.", getX(getWidth2()/3),getY(515));
    	
    	}
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

=======
>>>>>>> refs/remotes/origin/master
            try
            {
                g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
                g.setColor(Color.white);
                g.drawString("Your IP address: " + InetAddress.getLocalHost().getHostAddress(), getX(5), getY(getHeight2()-5));
                g.drawString("Enter IP address: " + ipAddress, getX(305), getY(getHeight2()-5));
            }
            catch (UnknownHostException e)
            {
                e.printStackTrace();
            }
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
        if(betTime&&thePot==2*myBet&&thePot>0)
        {
            betTime=false;
            hitTime=true;
        }
        if(isClient&&hitTime&&james.getStanding()==false)
        {
            if(james.getHandValue()>=21)
            {
                james.setStanding(true);
                System.out.println("sending from client");
                ClientHandler.sendStand();
            }
        }
        else if(!isClient&&hitTime&&goldfinger.getStanding()==false)
        {
           if(goldfinger.getHandValue()>=21)
            {
                goldfinger.setStanding(true);
                System.out.println("sending from server");
                ServerHandler.sendStand();
            } 
        }
        if(isClient&&myTurn&&james.getStanding()&&goldfinger.getStanding()&&!roundOver)
        {
            if(dealer.getHandValue()<17)
            {
                int hitCardIndex=0;
                hitCardIndex=dealer.hit();
                System.out.println("sending from client");
                ClientHandler.sendDealerHit(hitCardIndex);
                
            }
            else
            {
                dealer.setStanding(true);
                System.out.println("sending from client");
                ClientHandler.sendDealerStand();
            }
        }
        if(dealer.getStanding())
            roundEnd=true;
        
        if(roundEnd)
        {
        if(james.getHandValue()==21)
                    jWin=true;
        else if(james.getHandValue()>21)
                    jBust=true;
        if(goldfinger.getHandValue()==21)
            gWin=true;
        else if(goldfinger.getHandValue()>21)
            gBust=true;
        if(dealer.getHandValue()==21)
            dWin=true;
        else if(dealer.getHandValue()>21)
            dBust=true;
        else if(!jWin&&!gWin&&!dWin)
        {
            
            if(dealer.getHandValue()<21&&(dealer.getHandValue()>james.getHandValue()||jBust)&&(dealer.getHandValue()>goldfinger.getHandValue()||gBust))
                dWin=true;
            else if(james.getHandValue()<21&&(james.getHandValue()>dealer.getHandValue()||dBust)&&(james.getHandValue()>goldfinger.getHandValue()||gBust))
                jWin=true;
            else if(goldfinger.getHandValue()<21&&(goldfinger.getHandValue()>james.getHandValue()||jBust)&&(goldfinger.getHandValue()>dealer.getHandValue()||dBust))
                gWin=true;
        }
        
        if(jWin)
        {
            james.setAmtMoney(james.getAmtMoney()+thePot);
            thePot=0;
            roundEnd=false;
            roundOver=true;
        }
        else if(gWin)
        {
            goldfinger.setAmtMoney(goldfinger.getAmtMoney()+thePot);
            thePot=0;
            roundEnd=false;
            roundOver=true;
        }        
        else if(dWin)
        {
            roundEnd=false;
            roundOver=true;
            thePot=0;
        }
        else
        {
            thePot=0;
            roundEnd=false;
            roundOver=true;
        }
        
        
            
        
        
        
        
        }
        
        
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
