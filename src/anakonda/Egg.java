package anakonda;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Egg {
	int row,col;
	int w = MainPanel.Block_Size;
	int h = MainPanel.Block_Size;
	public static Random r= new Random();
	private Color color =Color.GREEN;
	
	public Egg(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public Egg(){
		this(r.nextInt(MainPanel.rows-3)+3,r.nextInt(MainPanel.cols));
	}
	
	public void reAppear(){
		this.row=r.nextInt((MainPanel.rows-3)+3);
		this.col=r.nextInt(MainPanel.cols);
	}
	public Rectangle getRect(){
		return new Rectangle(MainPanel.Block_Size*col, MainPanel.Block_Size*row, w, h);
	}
	public void draw(Graphics g){
		Color c =g.getColor();
		g.setColor(color);
		g.fillOval(MainPanel.Block_Size*col, MainPanel.Block_Size*row, w, h);
		g.setColor(c);
		if(color ==Color.GREEN) color=Color.RED;
		else color=Color.GREEN;
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
}
