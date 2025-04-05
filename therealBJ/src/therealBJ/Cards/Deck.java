package therealBJ.Cards;

import java.util.*;


public class Deck extends Stack<Card>
{

    private int numofPacks;
    

    public Deck()
    {
        super();
        
        setnumofPacks(1);
        
        this.addAll(new CardPack());
        
        shuffle();
    }
    

    public Deck(int numofPacks)
    {
        super();
        
        setnumofPacks(numofPacks);
        
        for (int i = 0; i < numofPacks; i++)
        {
            this.addAll(new CardPack());
        }
        
        shuffle();
    }
    

    private void setnumofPacks(int number)
    {
        this.numofPacks = number;
    }
    

    public int getnumofPacks()
    {
        return this.numofPacks;
    }
    

    public void shuffle()
    {
        Collections.shuffle(this);
    }
    
  
    public Card deal()
    {
        if (this.empty())
        {
            for (int i = 0; i < numofPacks; i++)
            {
                this.addAll(new CardPack());
            }

            shuffle();
        }
        
        return this.pop();
    }
}