import javax.swing.JFrame;

public class Tetris_Main {
	public static void main(String[] args) {
		
		JFrame tetris = new JFrame();
		GameRunner game = new GameRunner();
		tetris.add(game);
		
		tetris.setBounds(1, 1, 800, 650);
		
		tetris.setResizable(false);
		tetris.setVisible(true);
		tetris.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
