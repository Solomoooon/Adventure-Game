package therealBJ;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.*;
import therealBJ.Players.*;
import therealBJ.Cards.*;

public class GamePanel extends JPanel implements ActionListener
{
    private Dealer dealer;
    private Player player;
    
    private GameTable table;
    
    private JButton dealButton = new JButton("Deal");
    private JButton hitButton = new JButton("Hit");
    private JButton standButton = new JButton("Stand");
	private JButton easyButton = new JButton("Easy");
	private JButton hardButton = new JButton("Hard");

    private JLabel cardsLeft = new JLabel("Cards left...");
    private JLabel dealerSays = new JLabel("Dealer says...");
    private JLabel recordsSays = new JLabel("Records says...");
    private boolean easy = false;
    private boolean hard = false;
	private boolean playMore; 
    
    public GamePanel()
    {
        this.setLayout(new BorderLayout());
        dealer = new Dealer();
        player = new Player("William ", 18, "Male");
        table = new GameTable();
        add(table, BorderLayout.CENTER);
        
        dealButton.setEnabled(false);
        hitButton.setEnabled(false);
        standButton.setEnabled(false);
        

        JPanel recordsPanel = new JPanel();
        recordsPanel.add(recordsSays);
        
        JPanel dealerPanel = new JPanel();
        dealerPanel.add(dealerSays);
        
        JPanel optionsPanel = new JPanel();
        optionsPanel.add(easyButton);
        optionsPanel.add(hardButton);
        optionsPanel.add(dealButton);
        optionsPanel.add(hitButton);
       
        
        optionsPanel.add(standButton);
        optionsPanel.add(cardsLeft);
        
        JPanel bottomItems = new JPanel();
        bottomItems.setLayout(new GridLayout(0,1));
        bottomItems.add(dealerPanel);
        bottomItems.add(optionsPanel);
        bottomItems.add(recordsPanel);
        add(bottomItems, BorderLayout.SOUTH);
        
       JTextField records = new JTextField("Wins:" + player.getWins()+ "<->Losses:"+ player.getloses());
        
        dealerPanel.setOpaque(false);
        optionsPanel.setOpaque(false);
        recordsPanel.setOpaque(false);
        bottomItems.setOpaque(false);
        

        dealButton.addActionListener(this);
        hitButton.addActionListener(this);
		standButton.addActionListener(this);
		easyButton.addActionListener(this);
		hardButton.addActionListener(this);


		dealButton.setToolTipText("Deal a new game.");
		hitButton.setToolTipText("Request another card.");
		standButton.setToolTipText("Stand with your card-hand.");
		easyButton.setToolTipText("Easy Difficulty");
		hardButton.setToolTipText("Hard Difficulty");

		
		Update();
    }
    
    public void actionPerformed(ActionEvent evt)
    {
        String act = evt.getActionCommand();
        
        if (act.equals("Easy"))
        {
        	easy();
        	dealButton.setEnabled(true);
        	easyButton.setEnabled(false);
        	hardButton.setEnabled(false);
        	dealer.playAgain = true;
        	easy = false;
        	dealer.gameOver = false;
        }
        else if (act.equals("Hard"))
        {
        	hard();
        	dealButton.setEnabled(true);
        	easyButton.setEnabled(false);
        	hardButton.setEnabled(false);
        	dealer.playAgain = true;
        	hard = false;
        	dealer.gameOver = false;
        }
        else if (act.equals("Deal"))
        {
            deal();
            dealButton.setEnabled(false);
            hitButton.setEnabled(true);
            standButton.setEnabled(true);
        }
        else if (act.equals("Hit"))
        {
            hit();
        }

        else if (act.equals("Stand"))
        {
            stand();
            dealer.playAgain = true;
            hitButton.setEnabled(false);
            standButton.setEnabled(false);
            dealButton.setEnabled(false);
            easyButton.setEnabled(true);
        	hardButton.setEnabled(true);
        	easy = false;
        	hard = false;
 
        }
        else if (dealer.gameOver == true && easy == false && hard == false)
        {
        	 
        	// dealer.gameOver = true;
             hitButton.setEnabled(false);
             standButton.setEnabled(false);
             dealButton.setEnabled(false);
             easyButton.setEnabled(true);
         	 hardButton.setEnabled(true);
         	 easy = true;
         	 hard = true;
        }

        
        Update();
    }
    public void easy()
    {
    	dealer.easy(player);
    }
    public void hard()
    {
    	dealer.hard(player);
    }
    public void deal()
    {
        dealer.deal(player);
    }
    
    public void hit()
    {
        dealer.hit(player);
    }
    

    
    public void stand()
    {
        dealer.stand(player);
    }
    public static void reset()
    {

    }

    
   
    public void Update()
    {
        dealerSays.setText("<html><p align=\"center\"><font face=\"Serif\" color=\"white\" style=\"font-size: 20pt\">" + dealer.says() + "</font></p></html>");
        
        if (dealer.isGameOver())
        {
        	
        	recordsSays.setText("Wins:" + player.getWins()+ "<->Losses:"+ player.getloses());
        	dealButton.setEnabled(false);
            hitButton.setEnabled(false);
            standButton.setEnabled(false);
            easyButton.setEnabled(true);
        	hardButton.setEnabled(true);
        	dealer.DealerStands = 0;
        	dealer.playAgain = true;
        	easy = true;
        	hard = true;
        }
            
     
       else
        {
        	recordsSays.setText("Wins:" + player.getWins()+ "<->Losses:"+ player.getloses());
        } 
        

        table.update(dealer.getHand(), player.getHand(), (dealer.CardsFaceUp()) ? true : false);
		table.setNames(dealer.getName(), player.getName());
        table.repaint();
        
		cardsLeft.setText("Deck: " + dealer.cardsLeftInPack() + "/" + (dealer.CardPack * CardPack.CARDS_IN_PACK));
    }
}    
  