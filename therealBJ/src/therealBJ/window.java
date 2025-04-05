package therealBJ;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;


public class window extends JFrame 
    implements ActionListener, ComponentListener
{
    private GamePanel gamePanel;
    private Color defaultTableColour = new Color(80, 80, 80);
    
    
    final int W = 600;
    final int H = 500;

	public window()
    {
        super("Blackjack");
        
        addComponentListener(this);
        
        Dimension windowSize = new Dimension(W, H);
        setSize(windowSize);
        setLocationRelativeTo(null); 
        
        this.setBackground(defaultTableColour);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        JMenuBar menuBar = new JMenuBar();

        
       
        
        JMenu windowMenu = new JMenu("Game Window");
        JMenuItem windowTableColourMenu = new JMenuItem("Change Background Colour");
        windowMenu.add(windowTableColourMenu);
        menuBar.add(windowMenu);
        
      
        
        setJMenuBar(menuBar);
        

		windowTableColourMenu.addActionListener(this);

        		
        gamePanel = new GamePanel();
        gamePanel.setBackground(defaultTableColour);
		add(gamePanel);
        setVisible(true);
    }

	public void actionPerformed(ActionEvent evt)
    {
        String act = evt.getActionCommand();
        if (act.equals("Easy")) {
        	gamePanel.easy();
        }
        if (act.equals("Hard"))
        {
        	gamePanel.hard();
        }
        
        if (act.equals("Deal"))
        {
            gamePanel.deal();
        }
        else if (act.equals("Hit"))
        {
            gamePanel.hit();
        }
        if (act.equals("Stand"))
        {
            gamePanel.stand();
        }

        
		else if (act.equals("Change Table Colour"))
		{
		    Color tableColour = JColorChooser.showDialog(this, "Select Table Colour", defaultTableColour);
		    this.setBackground(tableColour);
		    gamePanel.setBackground(tableColour);
		    gamePanel.repaint();
		    this.repaint();
		}
		gamePanel.Update();
	}
	
	public void componentResized(ComponentEvent e)
	{
	    int currentWidth = getWidth();
	    int currentHeight = getHeight();
	    
	    boolean resize = false;
	    
	    if (currentWidth < W)
	    {
	        resize = true;
	        currentWidth = W;
	    }
	    
	    if (currentHeight < H)
	    {
	        resize = true;
	        currentHeight = H;
	    }
	    
	    if (resize)
	    {
	        setSize(currentWidth, currentHeight);
	    }
	}
	
	public void componentMoved(ComponentEvent e) { }
	public void componentShown(ComponentEvent e) { }
	public void componentHidden(ComponentEvent e) { }
}
