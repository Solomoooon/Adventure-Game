package therealBJ.Cards;

public class PlayerCardHand extends CardHand
{
    public PlayerCardHand()
    {
        super();
    }
    

    public boolean add(Card card)
    {
        boolean cardAdded = false;
        
        if (!isBust() && !hasBlackjack())
        {
            cardAdded = super.add(card);
                        

            if (isBust())
            {
                for (Card eachCard : this)
                {
                    eachCard.getFace().changeAce(); 
                    
                    if (!isBust()) 
                    {
                        break; 
                    }
                }
            }
        }
        
        return (cardAdded) ? true : false;
    }
}