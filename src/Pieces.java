import java.awt.Color;
import java.awt.Graphics;

public class Pieces {

	public char shape;
	public int piece;
	public int position = 0;
	
	public Pieces(int piece) {
		this.piece = piece;
		
		switch(piece) {
			case 0: shape = 'O';
			break;
			case 1: shape = 'T';
			break;
			case 2: shape = 'Z';
			break;
			case 3: shape = 'S';
			break;
			case 4: shape = 'J';
			break;
			case 5: shape = 'L';
			break;
			default: shape = 'I';
			break;
		
		}
	}
	
	
	public void rotateLeft(){
		position++;
	}
	
	public void rotateRight(){
		position--;
	}

	
}
