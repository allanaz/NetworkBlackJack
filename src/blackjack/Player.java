
package blackjack;

import java.util.ArrayList;

public class Player {
    private String name;
    public ArrayList<Card> hand = new ArrayList<Card>();
    private int amtMoney;
    public enum winState {UNDER,TWENTYONE,BUST}
    private winState status;
    private boolean inGame;
     
    public Player(String _name,int _money)
    {
        name=_name;
        amtMoney=_money;
        inGame=false;
        
    }
    
    public boolean addCard(Card _card)
    {
        if (!setCardOK(_card))
            return(false);
        if (!_card.setPlayerOK(this))
            return(false);
        _card.setPlayerDoIt(this);
        setCardDoIt(_card);
        return(true);
    }  

    public boolean setCardOK(Card _card)
    {
        if (_card == null)
            return(false);
        if (hand.contains(_card))
            return(false);
        return(true);
    }
    public void setCardDoIt(Card _card)
    {
        hand.add(_card);
    }
    public void printCards()
    {
        System.out.println("====="+name+"'s Cards=======");

        for(Card temp: hand)
        {
            System.out.println(temp);
        }
    }
    public void dealMeIn()
    {
        int numPlayed=0;
         while(numPlayed<2)
         {
             int randomCard=(int)(Math.random()*52);
             if(!Card.cards[randomCard].getInPlay())
             {
                System.out.println(Card.cards[randomCard]);
                Card.cards[randomCard].setInPlay(true);
                addCard(Card.cards[randomCard]);
                numPlayed++;
             }
         }
        
    }
    public  int hit()
    {
        boolean hit=false;
        while (hit ==false)
        {
             int randomCard=(int)(Math.random()*52);
             if(!Card.cards[randomCard].getInPlay())
             {
                System.out.println(Card.cards[randomCard]);
                Card.cards[randomCard].setInPlay(true);
                addCard(Card.cards[randomCard]);
                
                hit=true;
                
                return randomCard;
             }
        }
         return 55;
        
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int getPlayerCard()
    {
        boolean hit=false;
        while (hit ==false)
        {
             int randomCard=(int)(Math.random()*52);
             
             if(Card.cards[randomCard].getInPlay())
             {
                 if(this.hand.contains(Card.cards[randomCard]))
                 {
                    System.out.println(Card.cards[randomCard]);
                    Card.cards[randomCard].setInPlay(false);
                    this.hand.remove(Card.cards[randomCard]);
                    //addCard(Card.cards[randomCard]);

                    hit=true;

                    return randomCard;
                 }
             }
             
        }
        return 12;
    }
    public void setPlayerCard(int c)
    {
        boolean hit=false;
        while (hit ==false)
        {
             //int randomCard=(int)(Math.random()*52);
             
             if(!Card.cards[c].getInPlay())
             {
                System.out.println(Card.cards[c]);
                Card.cards[c].setInPlay(true);
                addCard(Card.cards[c]);
                
                hit=true;
                
                
             }
             
        }
        
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int getHandValue()
    {
        int handValue=0;
        for(Card temp:hand)
        {
            if(temp.getValue()>9)
                handValue+=10;
            else if(temp.getValue()>1&&temp.getValue()<=9)
                handValue+=temp.getValue();
            else
            {
                if(handValue+11>21)
                    handValue+=1;
                else
                    handValue+=11;
            }
        }
        return handValue;
    }
    public String getName()
    {
        return name;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setAmtMoney(int money)
    {
        amtMoney=money;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int getAmtMoney()
    {
        return amtMoney;
    }
    public Card getCard(int index)
    {
        return hand.get(index);
    }
    public boolean getInGame()
    {
        return inGame;
    }
    public void setInGame(boolean _inGame)
    {
        inGame=_inGame;
    }
    
}
