
package blackjack;

import java.util.ArrayList;

public class Player {
    private String name;
    public ArrayList<Card> hand = new ArrayList<Card>();
    private int amtMoney;
//    public enum winState {UNDER,TWENTYONE,BUST}
//    private winState status;
    private boolean inGame;
    private boolean standing;
     
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
        _card.setInPlay(true);
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
<<<<<<< HEAD
            
             int randomCard=(int)(Math.random()*52);
             if(!Card.cards[randomCard].getInPlay())
=======
             int randomCard=(int)(Math.random()*Card.numCardsInDeck);
             if(Card.cards[randomCard].getInPlay()==false)
>>>>>>> refs/remotes/origin/master
             {
                System.out.println(Card.cards[randomCard]);
                //Card.cards[randomCard].setInPlay(true);
                addCard(Card.cards[randomCard]);
                
                hit=true;
                
                return randomCard;
             }
        }
         return 55;
        
    }
<<<<<<< HEAD

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int getPlayerCard()
=======
     public int getPlayerCard()
>>>>>>> refs/remotes/origin/master
    {
        boolean hit=false;
        while (hit ==false)
        {
             int randomCard=(int)(Math.random()*52);
             
             if(Card.cards[randomCard].getInPlay())
             {
<<<<<<< HEAD
                 
                 if(this.hand.contains(Card.cards[randomCard]))
                 {
                    System.out.println(Card.cards[randomCard]);
                    Card.cards[randomCard].setInPlay(false);
                    this.hand.remove(Card.cards[randomCard]);
=======
                 if(hand.contains(Card.cards[randomCard]))
                 {
                    System.out.println(Card.cards[randomCard]);
                    Card.cards[randomCard].setInPlay(false);
                    hand.remove(Card.cards[randomCard]);
>>>>>>> refs/remotes/origin/master
                    //addCard(Card.cards[randomCard]);

                    hit=true;

                    return randomCard;
                 }
             }
             
        }
        return 12;
    }
<<<<<<< HEAD
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
=======
    
>>>>>>> refs/remotes/origin/master
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

<<<<<<< HEAD
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

=======
>>>>>>> refs/remotes/origin/master
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


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setAmtMoney(int money)
    {
        amtMoney=money;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int getAmtMoney()
    {
        return amtMoney;
    }
<<<<<<< HEAD
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
=======
    public void setAmtMoney(int _money)
    {
        amtMoney=_money;
    }
>>>>>>> refs/remotes/origin/master
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
    public void setStanding(boolean _stand)
    {
        standing=_stand;
    }
    public boolean getStanding()
    {
        return standing;
    }
    
}
