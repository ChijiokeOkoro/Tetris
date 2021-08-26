import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.swing.JPanel;

public class GameRunner extends JPanel implements KeyListener, ActionListener{
	
	private Queue<Pieces> pieceOrder = new LinkedList<>();
	
	public GameRunner() {
		for(int i = 0; i < 5; i++)
			pieceOrder.add(new Pieces( new Random().nextInt(7) ) );
		
	}
	
	public void paint(Graphics g) {
		
		//background color
		g.setColor(Color.black);
		g.fillRect(1, 1, 800, 650);
		
		//tetris grid
		g.setColor(Color.gray);
		for( int i = 0; i < 20; i++) {
			g.drawLine(25, 50 + i*25, 275, 50 + i*25);
		}
		
		for( int i = 0; i < 11; i++) {
			g.drawLine( 25 + i*25, 50 , 25 + i*25, 525);
		}
		
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
