package therealBJ.Players;

import java.io.*;

import therealBJ.Cards.*;


public class Player extends BJplayer implements Serializable
{

    public transient PlayerCardHand hand = new PlayerCardHand();
    private int loses = 0;
    private int wins = 0;
    

    public Player(String name, int age, String gender)
    {
        super(name, age, gender);
    }
 

    
    public void loses()
    {
        this.loses++;
        Dealer.gameOver = true;
    }
    public void wins() {
    	this.wins++;
    	Dealer.gameOver = true;
    }
    public int getWins()
    {
        return this.wins;
      
    }
    public int getloses() {
    	return this.loses;
    }

    public PlayerCardHand getHand()
    {
        return this.hand;
    }
}