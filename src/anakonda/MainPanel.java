package anakonda;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;


public class MainPanel extends JFrame{
	public static final int rows=30;
	public static final int cols=30;
	public static final int Block_Size=15;
	private boolean gameOver = false;
	PaintThread paintThread = new PaintThread();
	private int score =0;
	private Font fontGameOver = new Font("微軟正黑體",Font.BOLD,50);

	Snake s= new Snake(this);
	Egg e =new Egg();
	Image offScreenImage=null;
	
	public void launch(){
		KeyMonitor l = new KeyMonitor();
		addKeyListener(l);
		this.setLocation(400, 200);
		this.setSize(cols*Block_Size, rows*Block_Size);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new Thread(paintThread).start();
		
	}

	public void stop(){
		gameOver= true;
	}

	@Override
	 public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.gray);
        g.fillRect(0, 0, cols*Block_Size , rows*Block_Size);
        g.setColor(Color.DARK_GRAY);
        for(int i=1;i<rows;i++) {
            g.drawLine(0, Block_Size*i, cols*Block_Size ,Block_Size*i);
        }
        for(int i=1;i<cols;i++) {
            g.drawLine(Block_Size*i, 0, Block_Size*i, rows*Block_Size);
        }
        g.setColor(Color.YELLOW);
        g.drawString("score :" +score, 10, 60);
        
        if(gameOver){
        	g.setFont(fontGameOver);
        	g.drawString("GameOver", 100, 250);
        	paintThread.gameOver();
        }
        g.setColor(c);
        
        e.draw(g);
        s.eat(e);
        s.draw(g);
	}

	
	//防止閃爍
	public void update(Graphics g) {
		if(offScreenImage==null){
			offScreenImage=this.createImage(cols*Block_Size , rows*Block_Size);
		}
		Graphics gOff=offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	private class PaintThread implements Runnable{
		private boolean running =true;
		public void run() {
			while(running){
				repaint();
				try{
					Thread.sleep(100);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
		public void gameOver(){
			running = false;
		}
	}
	
	private class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			s.keyPressed(e);
		}
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public static void main(String[] args) {
		new MainPanel().launch();
		
//		new UserInfo();
		
	}
	
}


