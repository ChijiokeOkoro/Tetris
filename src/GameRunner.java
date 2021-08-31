import java.awt.Color;
import java.awt.Font;
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
	
	private Pieces piece, nextPiece;
	private Timer timer;
	private int delay = 1, count = 0;
	private boolean[][] board = new boolean[20][10];
	
	public GameRunner() {
		piece = new Pieces( new Random().nextInt(7));
		nextPiece = new Pieces( (new Random().nextInt(7) + piece.piece) % 7);
		
		addKeyListener(this);
		setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		
		timer = new Timer(delay, this);
		timer.start();
		
	}

	private void updateBoard(Pieces piece) {
		
		if(piece != null && piece.pt != null && piece.pt2 != null 
				&& piece.pt3 != null && piece.pt4 != null && piece.dropped) {
			board[(piece.pt.y-25)/25 - 1][piece.pt.x/25 - 1] = true;
			board[(piece.pt2.y-25)/25 - 1][piece.pt2.x/25 - 1] = true;
			board[(piece.pt3.y-25)/25 - 1][piece.pt3.x/25 - 1] = true;
			board[(piece.pt4.y-25)/25 - 1][piece.pt4.x/25 - 1] = true;
		}
	}	
	
	private void paintBoard(Graphics g) {
		g.setColor(Color.white);
		
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 10; j++) {
				if(board[i][j]) {
					g.fillRect((j)*25+25 , (i+1)*25+25, 25, 25);
				}
			}
		}
	}

	public void paint(Graphics g) {
		
		//background color
		g.setColor(Color.black);
		g.fillRect(1, 1, 800, 650);
		
		//shows the dropped pieces
		clearLine();
		updateBoard(piece);
		paintBoard(g);
		
		//paints the queue
		g.setColor(Color.gray);
		g.setFont(new Font("arial", Font.PLAIN, 20));
		g.drawString("Next", 355, 35);
		g.drawRect( 325, 50, 100, 100);
		displayNext(g);
		
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

	public void displayNext(Graphics g){
		switch(nextPiece.piece){
			case 0:
				 g.setColor(Color.yellow);
				 g.fillRect(355, 80, 40, 40);
				 break;
			case 1:
				 g.setColor(Color.magenta);
				 g.fillRect(365, 80, 20, 20);
				 g.fillRect(345, 100, 60, 20);
				 break;
			case 2:
				 g.setColor(Color.red);
				 g.fillRect(345, 80, 40, 20);
				 g.fillRect(365, 100, 40, 20);
				 break;
			case 3:
				 g.setColor(Color.green);
				 g.fillRect(365, 80, 40, 20);
				 g.fillRect(345, 100, 40, 20);
				 break;
			case 4:
				 g.setColor(Color.blue);
				 g.fillRect(345, 80, 20, 20);
				 g.fillRect(345, 100, 60, 20);
				 break;
			case 5:
				 g.setColor(Color.orange);
				 g.fillRect(385, 80, 20, 20);
				 g.fillRect(345, 100, 60, 20);
				 break;
			default:
				 g.setColor(Color.cyan);
				 g.fillRect(335, 90, 80, 20);
				 break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		count++;
		
		if(piece.dropped) {
			piece = nextPiece;
			nextPiece = new Pieces((new Random().nextInt(7) + piece.piece) % 7);
		}
		
		if(count % 10 == 0 ) {
			switch(piece.shape){
				case 'O':
					if (piece.pt.y < 500 && validSpot(board, piece, 0)) piece.pt.y += 25;
					else piece.dropped = true;
					break;
				case 'T':
					if (((piece.rotation % 4 == 0 && piece.pt.y < 525) ||
						(piece.rotation % 4 != 0 && piece.pt.y < 500)) && validSpot(board, piece, 0)) piece.pt.y += 25;
					else piece.dropped = true;
					break;
				case 'Z':
					if (((piece.rotation % 2 == 0 && piece.pt.y < 500) ||
						(piece.rotation % 2 != 0 && piece.pt.y < 500)) && validSpot(board, piece, 0)) piece.pt.y += 25;
					else piece.dropped = true;
					break;
				case 'S':
					if (((piece.rotation % 2 == 0 && piece.pt.y < 500) ||
						(piece.rotation % 2 != 0 && piece.pt.y < 500)) && validSpot(board, piece, 0)) piece.pt.y += 25;
					else piece.dropped = true;
					break;
				case 'J':
					if (((piece.rotation % 4 == 0  && piece.pt.y < 525) ||
						(!(piece.rotation % 4 == 0) && piece.pt.y < 500)) && validSpot(board, piece, 0)) piece.pt.y += 25;
					else piece.dropped = true;
					break;
				case 'L':
					if (((piece.rotation % 4 == 0  && piece.pt.y < 525) ||
							(!(piece.rotation % 4 == 0) && piece.pt.y < 500)) && validSpot(board, piece, 0)) piece.pt.y += 25;
					else piece.dropped = true;
					break;
				case 'I':
					if(((piece.rotation % 2 == 0 && piece.pt.y < 525) ||
					   (piece.rotation % 2 != 0 && piece.pt.y < 475)) && validSpot(board, piece, 0)) piece.pt.y += 25;
					else piece.dropped = true;
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
		if (e.getKeyCode() == KeyEvent.VK_LEFT && validSpot(board, piece, -1)) {
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
					if((piece.rotation % 4 == 1 || piece.rotation % 4 == -3) && piece.pt.x > 25) piece.pt.x -= 25;
					else if(!(piece.rotation % 4 == 1 || piece.rotation % 4 == -3) && piece.pt.x > 50) piece.pt.x -= 25;
					break;
				case 5: 
					if((piece.rotation % 4 == 1 || piece.rotation % 4 == -3) && piece.pt.x > 25) piece.pt.x -= 25;
					else if(!(piece.rotation % 4 == 1 || piece.rotation % 4 == -3) && piece.pt.x > 50) piece.pt.x -= 25;
					break;
				default:
					if(piece.rotation % 2 == 0 && piece.pt.x > 50) piece.pt.x -= 25;
					else if(piece.rotation % 2 != 0 && piece.pt.x > 25) piece.pt.x -= 25;
					break;
			}
			
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT && validSpot(board, piece, 1)) {
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
					if(piece.pt.x < 225) piece.pt.x += 25;
					break;
				case 3: 
					if(piece.pt.x < 225) piece.pt.x += 25;
					break;
				case 4: 
					if((piece.rotation % 4 == 3 || piece.rotation % 4 == -1) && piece.pt.x < 250) piece.pt.x += 25;
					else if(!(piece.rotation % 4 == 3 || piece.rotation % 4 == -1) && piece.pt.x < 225) piece.pt.x += 25;
					break;
				case 5: 
					if((piece.rotation % 4 == 3 || piece.rotation % 4 == -1) && piece.pt.x < 250) piece.pt.x += 25;
					else if(!(piece.rotation % 4 == 3 || piece.rotation % 4 == -1) && piece.pt.x < 225) piece.pt.x += 25;
					break;
				default:
					if(piece.rotation % 2 == 0 && piece.pt.x < 200) piece.pt.x += 25;
					else if(piece.rotation % 2 != 0 && piece.pt.x < 250) piece.pt.x += 25;
					break;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_X ) {
			piece.rotateX();
		}
		if(e.getKeyCode() == KeyEvent.VK_Z ) {
			piece.rotateZ();			
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private boolean validSpot(boolean[][] board, Pieces piece, int dir) {
		
		switch(dir) {
			case 0: 
				if(	!board[(piece.pt.y-25)/25 ][piece.pt.x/25 - 1] &&
					!board[(piece.pt2.y-25)/25 ][piece.pt2.x/25 - 1] &&
					!board[(piece.pt3.y-25)/25 ][piece.pt3.x/25 - 1] &&
					!board[(piece.pt4.y-25)/25 ][piece.pt4.x/25 - 1]) {
					return true;
				}	
				break;
			case 1:
				if (piece.pt.x/25 < 10 && piece.pt2.x/25 < 10 
					&& piece.pt3.x/25 < 10 && piece.pt4.x/25 < 10) {
					if(	!board[(piece.pt.y-25)/25 ][piece.pt.x/25] &&
						!board[(piece.pt2.y-25)/25 ][piece.pt2.x/25] &&
						!board[(piece.pt3.y-25)/25 ][piece.pt3.x/25] &&
						!board[(piece.pt4.y-25)/25 ][piece.pt4.x/25]) {
						return true;
					}	
				}
				break;
			case -1: 
				if (piece.pt.x/25 - 2 >= 0 && piece.pt2.x/25 - 2 >= 0 
						&& piece.pt3.x/25 - 2 >= 0 && piece.pt4.x/25 - 2 >= 0) {
						if(	!board[(piece.pt.y-25)/25 ][piece.pt.x/25 - 2] &&
							!board[(piece.pt2.y-25)/25 ][piece.pt2.x/25 - 2] &&
							!board[(piece.pt3.y-25)/25 ][piece.pt3.x/25 - 2] &&
							!board[(piece.pt4.y-25)/25 ][piece.pt4.x/25 - 2]) {
							return true;
						}	
					}
				break;
		}
		
		return false;
	}
	
	private void clearLine() {
		int count = 0;
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 10; j++) {
				if(board[i][j]) count++;
			}
			if(count == 10) {
				for(int k = i; k > 0; k--) {
					for(int j = 0; j < 10; j++)
						board[k][j] = board[k-1][j];
				}
				
				for(int j = 0; j < 10; j++){
					board[0][j] = false;
				}
			}
			count = 0;
		}
	}

}
