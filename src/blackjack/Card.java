package blackjack;

public class Card {
    public static Card cards[]= new Card[52];
    
    private int value;
    public enum Suite {HEARTS,DIAMONDS,SPADES,CLUBS}
    private Suite suite;
    private boolean inPlay;
    private boolean faceUp;
    private Player thePlayer;
    
    Card(int _value, Suite _suite)
    {
        value=_value;
        suite=_suite;
        inPlay=false;
        faceUp=false;
        thePlayer=null;
    }
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
        for(int i=0;i<4;i++)
        {
            for(int index=13*i;index<13*(i+1);index++)
            {
                if(i==0)
                cards[index] = new Card(index+1-13*i,Suite.HEARTS);
                else if(i==1)
                cards[index] = new Card(index+1-13,Suite.SPADES);
                else if(i==2)
                cards[index] = new Card(index+1-13*i,Suite.DIAMONDS);
                else if(i==3)
                cards[index] = new Card(index+1-13*i,Suite.CLUBS);
                    
            }
        }
        
        return true;
        
    }
    ////create draw card method which takes value of boolean faceUp, then displays card either face up with value or face down without.
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
