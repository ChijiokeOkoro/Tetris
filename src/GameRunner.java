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
import javax.swing.Timer;

public class GameRunner extends JPanel implements KeyListener, ActionListener{
	
	private Queue<Pieces> pieceOrder = new LinkedList<>();
	private Pieces piece;
	private Timer timer;
	private int delay = 1, count = 0;
	
	public GameRunner() {
		for(int i = 0; i < 5; i++)
			pieceOrder.add(new Pieces( new Random().nextInt(6)) );
		
		addKeyListener(this);
		setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		
		timer = new Timer(delay, this);
		timer.start();
		
	}
	
	public void paint(Graphics g) {
		
		//background color
		g.setColor(Color.black);
		g.fillRect(1, 1, 800, 650);
		
		//tetris grid
		g.setColor(Color.gray);
		for( int i = 0; i < 21; i++) {
			g.drawLine(25, 50 + i*25, 275, 50 + i*25);
		}
		
		for( int i = 0; i < 11; i++) {
			g.drawLine( 25 + i*25, 50 , 25 + i*25, 550);
		}
		
		// draws the piece on the board
		if(piece != null)
			piece.drawShape(g);
		
		
		g.dispose();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		count++;
		
		if(piece == null || piece.dropped) {
			piece = pieceOrder.remove();
			pieceOrder.add(new Pieces( new Random().nextInt(7) ) );
		}
		
		if(count % 50 == 0 ) {
			switch(piece.shape){
				case 'O':
					if (piece.pt.y < 500) piece.pt.y += 25;
					break;
				case 'T':
					if ((piece.rotation % 4 == 0 && piece.pt.y < 525) ||
						(piece.rotation % 4 != 0 && piece.pt.y < 500)) piece.pt.y += 25;
					break;
				case 'Z':
					if ((piece.rotation % 2 == 0 && piece.pt.y < 500) ||
						(piece.rotation % 2 != 0 && piece.pt.y < 475)) piece.pt.y += 25;
					break;
				case 'S':
					if ((piece.rotation % 2 == 0 && piece.pt.y < 500) ||
						(piece.rotation % 2 != 0 && piece.pt.y < 475)) piece.pt.y += 25;
					break;
				case 'I':
					if((piece.rotation % 2 == 0 && piece.pt.y < 525) ||
					   (piece.rotation % 2 != 0 && piece.pt.y < 475)) piece.pt.y += 25;
					break;
					
			}
		}
		
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			switch(piece.piece){
				case 0: 
					if(piece.pt.x > 25) piece.pt.x -= 25;
					break;
				case 1:
					if(piece.rotation % 4 == 0 && piece.pt.x > 50) piece.pt.x -= 25;
					else if((piece.rotation % 4 == 1 || piece.rotation % 4 == -3) && piece.pt.x > 25) piece.pt.x -= 25;
					else if((piece.rotation % 4 == 2 || piece.rotation % 4 == -2) && piece.pt.x > 50) piece.pt.x -= 25;
					else if((piece.rotation % 4 == 3 || piece.rotation % 4 == -1) && piece.pt.x > 50) piece.pt.x -= 25;
					break;
				case 2: 
					if(piece.rotation % 2 == 0 && piece.pt.x > 50) piece.pt.x -= 25;
					else if(piece.rotation % 2 != 0 && piece.pt.x > 25) piece.pt.x -= 25;
					break;
				case 3: 
					if(piece.rotation % 2 == 0 && piece.pt.x > 50) piece.pt.x -= 25;
					else if(piece.rotation % 2 != 0 && piece.pt.x > 25) piece.pt.x -= 25;
					break;
				case 4: 
					break;
				case 5: 
					break;
				default:
					if(piece.rotation % 2 == 0 && piece.pt.x > 50) piece.pt.x -= 25;
					else if(piece.rotation % 2 != 0 && piece.pt.x > 25) piece.pt.x -= 25;
					break;
			}
			
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			switch(piece.piece){
				case 0: 
					if(piece.pt.x < 225) piece.pt.x += 25;
					break;
				case 1:
					if(piece.rotation % 4 == 0 && piece.pt.x < 225) piece.pt.x += 25;
					else if((piece.rotation % 4 == 1 || piece.rotation % 4 == -3) && piece.pt.x < 225) piece.pt.x += 25;
					else if((piece.rotation % 4 == 2 || piece.rotation % 4 == -2) && piece.pt.x < 225) piece.pt.x += 25;
					else if((piece.rotation % 4 == 3 || piece.rotation % 4 == -1) && piece.pt.x < 250) piece.pt.x += 25;
					break;
				case 2: 
					if(piece.rotation % 2 == 0 && piece.pt.x < 225) piece.pt.x += 25;
					else if(piece.rotation % 2 != 0 && piece.pt.x < 250) piece.pt.x += 25;
					break;
				case 3: 
					if(piece.rotation % 2 == 0 && piece.pt.x < 225) piece.pt.x += 25;
					else if(piece.rotation % 2 != 0 && piece.pt.x < 250) piece.pt.x += 25;
					break;
				case 4: 
				break;
				case 5: 
				break;
				default:
					if(piece.rotation % 2 == 0 && piece.pt.x < 200) piece.pt.x += 25;
					else if(piece.rotation % 2 != 0 && piece.pt.x < 250) piece.pt.x += 25;
					break;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_X) {
			piece.rotateX();
		}
		if(e.getKeyCode() == KeyEvent.VK_Z) {
			piece.rotateZ();			
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
