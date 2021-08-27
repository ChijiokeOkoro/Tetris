import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Pieces {

	public char shape;
	public int piece;
	public int rotation = 0;
	public Point pt;

	public boolean dropped = false;
	
	public Pieces(int piece) {
		this.piece = piece;
		
		switch(piece) {
			case 0: shape = 'O';
			pt = new Point(125, 25);
			break;
			case 1: shape = 'T';
			pt = new Point(125, 50);
			break;
			case 2: shape = 'Z';
			pt = new Point(125, 50);			
			break;
			case 3: shape = 'S';
			pt = new Point(125, 50);
			break;
			case 4: shape = 'J';
			pt = new Point(125, 50);
			break;
			case 5: shape = 'L';
			pt = new Point(125, 50);
			break;
			default: shape = 'I';
			pt = new Point(125, 25);
			break;
		}
	}
	
	public void drawShape(Graphics g) {
		switch(piece) {
			case 0:	g.setColor(Color.yellow);
					g.fillRect(pt.x, pt.y, 50, 50);
					break;
			case 1: g.setColor(Color.magenta);
					if(rotation % 4 == 0){
						if(pt.x-25 >= 0 && pt.y <= 525 && pt.x+25 <= 275) {
							g.fillRect(pt.x, pt.y, 25, 25);
							g.fillRect(pt.x-25, pt.y, 25, 25);
							g.fillRect(pt.x+25, pt.y, 25, 25);
							g.fillRect(pt.x, pt.y-25, 25, 25);	
						}
					}
					else if(rotation % 4 == 1 || rotation % 4 == -3) {
						if(pt.x >= 0 && pt.y+25 <= 550 && pt.x+25 <= 275) {
							g.fillRect(pt.x, pt.y, 25, 25);
							g.fillRect(pt.x, pt.y-25, 25, 25);
							g.fillRect(pt.x, pt.y+25, 25, 25);
							g.fillRect(pt.x+25, pt.y, 25, 25);				
						}
					}
					else if(rotation % 4 == 2 || rotation % 4 == -2) {
						if(pt.x-25 >= 0 && pt.y+25 <= 550 && pt.x+25 <= 275) {
							g.fillRect(pt.x, pt.y, 25, 25);
							g.fillRect(pt.x-25, pt.y, 25, 25);
							g.fillRect(pt.x+25, pt.y, 25, 25);
							g.fillRect(pt.x, pt.y+25, 25, 25);				
						}
					}
					else {
						if(pt.x-25 >= 0 && pt.y+25 <= 550 && pt.x <= 275) {
							g.fillRect(pt.x, pt.y, 25, 25);
							g.fillRect(pt.x, pt.y-25, 25, 25);
							g.fillRect(pt.x, pt.y+25, 25, 25);
							g.fillRect(pt.x-25, pt.y, 25, 25);		
						}
					}
					break;
			case 2: g.setColor(Color.red);
			 		if(rotation % 2 == 0) {
			 			g.fillRect(pt.x, pt.y, 25, 50);
			 			g.fillRect(pt.x+25, pt.y+25, 25, 50);
			 		}
			 		else { 
			 			g.fillRect(pt.x, pt.y, 50, 25);
			 			g.fillRect(pt.x+25, pt.y+25, 50, 25);
			 		}
			 		break;
			case 3: g.setColor(Color.green);
	 				if(rotation % 2 == 0) {
	 					g.fillRect(pt.x+25, pt.y, 50, 25);
	 					g.fillRect(pt.x, pt.y+25, 50, 25);
	 				}
	 				else {
	 					g.fillRect(pt.x, pt.y, 25, 50);
	 					g.fillRect(pt.x+25, pt.y+25, 25, 50);
	 				}
	 				break;
			case 4: g.setColor(Color.blue);
					if(rotation % 4 == 0){
						g.fillRect(pt.x, pt.y, 75, 25);
						g.fillRect(pt.x, pt.y-25, 25, 25);
					}
					else if(rotation % 4 == 1 || rotation % 4 == -3) {
						g.fillRect(pt.x, pt.y, 25, 75);
						g.fillRect(pt.x+25, pt.y, 25, 25);						
					}
					else if(rotation % 4 == 2 || rotation % 4 == -2) {
						g.fillRect(pt.x, pt.y, 75, 25);
						g.fillRect(pt.x+50, pt.y+25, 25, 25);				
					}
					else {
						g.fillRect(pt.x, pt.y, 25, 75);
						g.fillRect(pt.x-25, pt.y+50, 25, 25);					
					}
					break;
			case 5: g.setColor(Color.orange);
					if(rotation % 4 == 0){
						g.fillRect(pt.x, pt.y, 75, 25);
						g.fillRect(pt.x+50, pt.y-25, 25, 25);
					}
					else if(rotation % 4 == 1 || rotation % 4 == -3) {
						g.fillRect(pt.x, pt.y, 25, 75);
						g.fillRect(pt.x+25, pt.y+50, 25, 25);						
					}
					else if(rotation % 4 == 2 || rotation % 4 == -2) {
						g.fillRect(pt.x, pt.y, 75, 25);
						g.fillRect(pt.x, pt.y+25, 25, 25);				
					}
					else {
						g.fillRect(pt.x, pt.y, 25, 75);
						g.fillRect(pt.x-25, pt.y, 25, 25);					
					}
					break;
			default: g.setColor(Color.cyan);
					 if(rotation % 2 == 0) 
						 g.fillRect(pt.x, pt.y, 100, 25);
					 else 
						 g.fillRect(pt.x, pt.y, 25, 100);
					 break;
		}
	}
	
	public void rotateLeft(){
		switch(piece){
			case 0: 
				break;
			case 1:
				if( (rotation + 1) % 4 == 0 && pt.x-25 > 25 && pt.x+25 <= 250) rotation++;
				if( ((rotation + 1) % 4 == 1 || (rotation + 1) % 4 == -3) && pt.x > 25 && pt.x+25 <= 250) rotation++;	
				if( ((rotation + 1) % 4 == 2 || (rotation + 1) % 4 == -2) && pt.x-25 > 50 && pt.x+25 <= 250) rotation++;	
				if( ((rotation + 1) % 4 == 3 || (rotation + 1) % 4 == -1) && pt.x-25 > 50 && pt.x <= 250) rotation++;	
				break;
			case 2: 
			break;
			case 3: 
			break;
			case 4: 
			break;
			case 5: 
			break;
			default:
			break;
		}		
	}
	
	public void rotateRight(){
		switch(piece){
			case 0: 
				break;
			case 1:
				if( (rotation - 1) % 4 == 0 && pt.x-25 >= 25 && pt.x+25 <= 250) rotation--;
				if( ((rotation - 1) % 4 == 1 || (rotation - 1) % 4 == -3) && pt.x >= 25 && pt.x+25 <= 250) rotation--;	
				if( ((rotation - 1) % 4 == 2 || (rotation - 1) % 4 == -2) && pt.x-25 >= 25 && pt.x+25 <= 250) rotation--;	
				if( ((rotation - 1) % 4 == 3 || (rotation - 1) % 4 == -1) && pt.x-25 >= 25 && pt.x <= 250) rotation--;	
				break;
			case 2: 
			break;
			case 3: 
			break;
			case 4: 
			break;
			case 5: 
			break;
			default:
			break;
		}
	}

	
}
