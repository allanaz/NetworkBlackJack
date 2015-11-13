package blackjack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Card {
    public static Card cards[]= new Card[58];
    
    private int value;
    public enum Suite {HEARTS,DIAMONDS,SPADES,CLUBS,SPECIAL}
    private Suite suite;
    private boolean inPlay;
    private boolean faceUp;
    private Player thePlayer;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int effect;
    
    Card(int _value, Suite _suite,int _effect)
    {
        value=_value;
        suite=_suite;
        inPlay=false;
        faceUp=false;
        thePlayer=null;
        effect=_effect;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean getFaceUp()
    {
        return faceUp;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void doEffect(Player _player, Player _player2)
    {
        for(Card temp: _player.hand)
        {
            if(temp!=null)
            {
                if(temp.getEffect()==0)
                {
                   _player.setAmtMoney(_player.getAmtMoney());
                   _player2.setAmtMoney(_player2.getAmtMoney());
                }
                else if(temp.getEffect()==1)
                {
                   _player.setAmtMoney(_player.getAmtMoney()-200);
                   _player2.setAmtMoney(_player2.getAmtMoney()-100);
                }
                else if(temp.getEffect()==2)
                {
                    _player.setAmtMoney(_player.getAmtMoney()+500);
                   _player2.setAmtMoney(_player2.getAmtMoney());
                }
                else if(temp.getEffect()==3)
                {
                    _player.setAmtMoney(_player.getAmtMoney()+50);
                   _player2.setAmtMoney(_player2.getAmtMoney()-100);
                }
                else if(temp.getEffect()==4)
                {
                    Card c;
//                    for(Card t: _player2.hand)
//                    {
//                        if(temp!=null)
//                        {
//                           
//                        }
//                    }
                    int pc;
                    pc=_player2.getPlayerCard();
                    _player.setPlayerCard(pc);
                }
                
            }
        }
    }
    public int getEffect()
    {
        return effect;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean addPlayer(Player _player)
    {
        if (!setPlayerOK(_player))
            return(false);
        if (!_player.setCardOK(this))
            return(false);
        _player.setCardDoIt(this);
        setPlayerDoIt(_player);
        return(true);
    }  
    public boolean setPlayerOK(Player _player)
    {
        if (_player == null)
            return(false);
        if (thePlayer != null)
            return(false);
        return(true);
    }
    public void setPlayerDoIt(Player _player)
    {
        thePlayer = _player;
    }
    
    public static boolean createDeck()
    {
        for(int i=0;i<5;i++)
        {
            for(int index=13*i;index<13*(i+1);index++)
            {
                if(i==0)
                cards[index] = new Card(index+1-13*i,Suite.HEARTS,0);
                else if(i==1)
                cards[index] = new Card(index+1-13,Suite.SPADES,0);
                else if(i==2)
                cards[index] = new Card(index+1-13*i,Suite.DIAMONDS,0);
                else if(i==3)
                cards[index] = new Card(index+1-13*i,Suite.CLUBS,4);
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    else if(i==4)
                    {
                        if(index<7)
                cards[index] = new Card(index+1-13*i,Suite.SPECIAL,4);//(int)(Math.random()*4+1));
                    }
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            }
        }
        
        return true;
        
    }
    ////create draw card method which takes value of boolean faceUp, then displays card either face up with value or face down without.
    
    public void drawCard(Graphics2D g,int xpos,int ypos,double xscale,double yscale,int value, Suite suite, boolean faceUp,boolean inPlay)
	{
    	g.translate(xpos,ypos);
    	g.scale( xscale , yscale );
    	if(inPlay==true)
        {
        if(faceUp)
        {
        if(suite == Suite.HEARTS || suite == Suite.DIAMONDS)
        {
            g.setColor(Color.red);
            g.fillRoundRect(0, 0, 15, 20,2,2);
            g.setColor(Color.black);
            
        }
        else if(suite == Suite.SPADES || suite == Suite.CLUBS)
        {
            g.setColor(Color.black);
            g.fillRoundRect(0, 0, 15, 20,2,2);
            g.setColor(Color.red);
           
            
        }
    	
       
        g.setColor(Color.white);
            if(suite == Suite.DIAMONDS)
            drawDiamond(g,7.5,6,45,.5,.5);
            else if(suite == Suite.HEARTS)
            drawHeart(g,7.5,11,180,.5,0.5);
            else if(suite == Suite.SPADES)
           drawSpade(g,7.5,8,0,.5,.5);
            else if(suite == Suite.CLUBS)
         drawClub(g,7.5,12,180,.5,.5);
            
            g.setFont(new Font("Impact",Font.BOLD,6));
            if(value==10)
            {
            g.drawString("" + value, 9, 5);
            g.drawString("" + value, 0, 20);
            }
            else if(value == 1)
            {
                g.drawString("A", 11, 6);
                g.drawString("A" , 1, 19);
            }
            else if(value == 11)
            {
                g.drawString("J", 12, 6);
                g.drawString("J" , 1, 19);
            }
            else if(value == 12)
            {
                g.drawString("Q", 11, 5);
                g.drawString("Q", 0, 19);
            }
            else if(value == 13)
            {
                g.drawString("K", 11, 5);
                g.drawString("K", 0, 19);
            }
            else if(value >=2 && value <=9)
            {
                g.drawString("" + value, 11, 5);
                g.drawString("" + value, 0, 19);
            }
        }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if(faceUp && suite == Suite.SPECIAL&&inPlay==true)
        {
            g.setColor(Color.white);
            g.fillRoundRect(0, 0, 15, 20,2,2);
            
            
            
            
            
            drawJester(g,7.5,10,180,.4,.4);
            
            g.setColor(Color.black);
            g.setFont(new Font("Impact",Font.BOLD,7));
            g.drawString("?", 11, 6);
            g.drawString("¿" , 0, 19);
        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            
        
        else
        {
            g.setColor(Color.red);
            g.fillRoundRect(0, 0, 15, 20,2,2);
        }
            
    	g.scale( 1.0/xscale,1.0/yscale );
    	g.translate(-xpos,-ypos);
	}
        ////////////////////////////////////////////////////////
    
//    //////////////////////////////////////////////////////////////
        public void drawDiamond(Graphics2D g,double xpos,int ypos,double rot,double xscale,double yscale)
	{
    	g.translate(xpos,ypos);
    	g.rotate(rot  * Math.PI/180.0);
    	g.scale( xscale , yscale );
    	
    	g.fillRect(0,0,10,10);
    	g.scale( 1.0/xscale,1.0/yscale );
    	g.rotate(-rot  * Math.PI/180.0);
    	g.translate(-xpos,-ypos);
	}
        ////////////////////////////////////////////////////////////////
        public void drawHeart(Graphics2D g,double xpos,int ypos,double rot,double xscale,double yscale)
	{
    	g.translate(xpos,ypos);
    	g.rotate(rot  * Math.PI/180.0);
    	g.scale( xscale , yscale );
    	
        int xval[] = {0,2,5,7,7,0,-7,-7,-5,-2,0};
        int yval[] = {4,8,8,6,4,-4,4,6,8,8,4};
        g.fillPolygon(xval,yval,xval.length);
    	
    	g.scale( 1.0/xscale,1.0/yscale );
    	g.rotate(-rot  * Math.PI/180.0);
    	g.translate(-xpos,-ypos);
	}
////////////////////////////////////////////////////////////////////////////
        public void drawSpade(Graphics2D g,double xpos,int ypos,double rot,double xscale,double yscale)
	{
    	g.translate(xpos,ypos);
    	g.rotate(rot  * Math.PI/180.0);
    	g.scale( xscale , yscale );
    	
        int xval[] = {0,2,2,1,1,4,7,9,9,0,-9,-9,-7,-4,-1,-1,-2,-2,0};
        int yval[] = {12,12,11,10,5,8,8,6,4,-4,4,6,8,8,5,10,11,12,12};
        g.fillPolygon(xval,yval,xval.length);
    	
    	g.scale( 1.0/xscale,1.0/yscale );
    	g.rotate(-rot  * Math.PI/180.0);
    	g.translate(-xpos,-ypos);
	}
        ///////////////////////////////////////////////////////////////////
        public void drawClub(Graphics2D g,double xpos,int ypos,double rot,double xscale,double yscale)
	{
    	g.translate(xpos,ypos);
    	g.rotate(rot  * Math.PI/180.0);
    	g.scale( xscale , yscale );
    	
        g.fillOval(-3,6,6,6);
        g.fillOval(-8,1,6,6);
        g.fillOval(2,1,6,6);
        int xval[] = {0,2,0,5,5,0,2,-2,0,-5,-5,0,-2,0};
        int yval[] = {-1,-2,4,2,6,4,9,9,4,6,2,4,-2,-1};
        g.fillPolygon(xval,yval,xval.length);
    	
    	g.scale( 1.0/xscale,1.0/yscale );
    	g.rotate(-rot  * Math.PI/180.0);
    	g.translate(-xpos,-ypos);
	}
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void drawJester(Graphics2D g,double xpos,int ypos,double rot,double xscale,double yscale)
	{
    	g.translate(xpos,ypos);
    	g.rotate(rot  * Math.PI/180.0);
    	g.scale( xscale , yscale );
    	
       
        
        
        g.setColor(Color.yellow);
        int xval[] = {0,-4,-6,-7,-2,2,7,6,4,0};
        int yval[] = {-9,-10,-9,-7,-6,-6,-7,-9,-10,-9};
        g.fillPolygon(xval,yval,xval.length);
    	
        
        g.setColor(Color.red);
        int xval2[] = {-7,-9,-9,-11,-14,-11,-7,-4,-2,-3,-7};
        int yval2[] = {-6,-4,0,3,4,5,4,2,-3,-5,-6};
        g.fillPolygon(xval2,yval2,xval2.length);

        g.setColor(Color.blue);
        int xval3[] = {0,-1,-2,-3,-1,2,5,6,4,3,4,3,0};
        int yval3[] = {-5,-4,-2,2,6,8,7,5,6,4,0,-4,-5};
        g.fillPolygon(xval3,yval3,xval3.length);
        
        Color darkGreen = new Color(70,115,55);
        g.setColor(darkGreen);
        int xval4[] = {8,4,4,5,8,10,12,13,11,10,10,9,8};
        int yval4[] = {-7,-5,-4,1,3,4,3,1,2,-1,-4,-6,-7};
        g.fillPolygon(xval4,yval4,xval4.length);
        
        
         g.setColor(Color.orange);
        g.fillOval(-16,2,3,3);
        g.fillOval(5,3,3,3);
        g.fillOval(12,-1,3,3);
        
        
    	g.scale( 1.0/xscale,1.0/yscale );
    	g.rotate(-rot  * Math.PI/180.0);
    	g.translate(-xpos,-ypos);
	}

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int getValue()
    {
        return value;
    }
    public Suite getSuite()
    {
        return suite;
    }
    public boolean getInPlay()
    {
        return inPlay;
    }
    public void setInPlay(boolean _inPlay)
    {
        inPlay=_inPlay;
    }
    public String toString()
    {
        return(value +""+ suite);
    }
}
