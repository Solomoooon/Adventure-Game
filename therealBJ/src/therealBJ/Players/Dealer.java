package therealBJ.Players;

import therealBJ.GamePanel;
import therealBJ.Cards.*;


public class Dealer extends BJplayer
{

    private Deck deck;  
    public DealerCardHand hand = new DealerCardHand();
    private boolean firstDeal = true;
    public static int DealerStands;
    public static final int CardPack = 2;
    public static boolean gameOver = false;
    private boolean cardFaceUp = false;
    public static boolean playAgain = true;
    private String said = "Welcome to BlackJack!.";
    

    public Dealer()
    {
        super("Sam", 20, "male"); 
        deck = new Deck(CardPack);
    }
    
    public void say(String announcement)
    {
        said = announcement;
        System.out.println(said);
    }
    
    public String says()
    {
        return said;
    }
    
    public boolean isGameOver()
    {
        return gameOver;
    }
    
    public boolean CardsFaceUp()
    {
        return cardFaceUp;
    }
    

    public boolean deal(Player player)
    {
        boolean cardsDealt = false;
     //   playMore = true;
        
        if (playAgain == true)
        {   
            gameOver = false;
            cardFaceUp = false;

            
            player.hand = new PlayerCardHand();
            hand = new DealerCardHand();
            
            say("Initial deal made.");
            
            player.hand.add(deck.deal());
            this.hand.add(deck.deal());
            
            player.hand.add(deck.deal());
            this.hand.add(deck.deal());
            
            cardsDealt = true;
            firstDeal = false; 
            
            if (player.hand.hasBlackjack())
            {
                say("Blackjack!");
                go(player);
            }
            
        }
        else
        {
            say("gameOver!");
            gameOver = true;
            
        }
        
        return cardsDealt;
    }
    
    /**
     * Player requests another card.
     *
     * @param player The player requesting another card.
     */
    public void hit(Player player)
    {
        say(player.getName() + " hits.");
        player.hand.add(deck.deal());
        
        if (player.hand.isBust())
        {
            say(player.getName() + " busts. Loses");
            player.loses();
            gameOver = true;
        }
    }
    

    public void easy(Player player)
    {
    	DealerStands = 15;
    	say(player.getName() + "chooses easy difficulty.");
    	playAgain = false;
    }
    public void hard(Player player)
    {
    	DealerStands = 17;
    	say(player.getName() + "chooses hard difficulty");
    	playAgain = false;
    }
    public void stand(Player player)
    {
        say(player.getName() + " stands. " + this.getName() + " turn.");
        go(player);
        playAgain = true;
    }
    

    private void go(Player player)
    {
        cardFaceUp = true;
        
       while (gameOver == false)
       {
        if (!hand.hasBlackjack())
        {
            while (hand.getTotal() < DealerStands)
            {
                hand.add(deck.deal());
                say(this.getName() + " hits.");
            }
                      
        }
        if (hand.hasBlackjack() && player.hand.hasBlackjack())
        {
            say("Push");
            gameOver = true;
        }
        
        else if (player.hand.hasBlackjack() && !(hand.getTotal() ==21))
        {
        	say(player.getName() + " wins with Blackjack");
        	player.wins();
            gameOver = true;
        }
        else if (hand.hasBlackjack()&& !(player.hand.getTotal() ==21))
        {
        	say("Dealer has Blackjack. " + player.getName() + " loses ");
            player.loses();
            gameOver = true;
        }
        else if (player.hand.getTotal() == hand.getTotal())
        {
        	say("Push");
        	gameOver = true;
        }
        	
        else if (player.hand.getTotal() < hand.getTotal() && !hand.isBust()) {
        	say(player.getName() + " loses");
        	player.loses();
        	gameOver = true;
        }
        else if (player.hand.getTotal() > hand.getTotal()&&!player.hand.isBust())
        {
        	say(player.getName() + " wins ");
        	player.wins();
        	gameOver = true;
        }
        else if (hand.isBust())
        {
        	say("Dealer is bust. " + player.getName() + " wins");
        	player.wins();
        	gameOver = true;
        }
        else if (player.hand.isBust())
        {
        	say(player.getName() + " is bust. " + player.getName() + " loses");
        	player.loses();
        	gameOver = true;
        }
        else if (player.hand.isBust() && hand.isBust())
        {
        	say("Push");
        	gameOver = true;
        }
       
        	playAgain = true;
        }
        
       
    }
    public int cardsLeftInPack()
    {
        return deck.size();
    }
    
    public void newDeck()
    {
        deck = new Deck(CardPack);
    }
    
    
    public DealerCardHand getHand()
    {
        return hand;
    }
}