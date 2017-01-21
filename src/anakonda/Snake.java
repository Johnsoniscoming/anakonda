package anakonda;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Snake {
	Node head =null;
	Node tail=null;
	int size=0;
	
	private Node n=new Node(10,10,Dirc.R);
	private MainPanel m;
	
	
	public Snake(MainPanel m){
		head=n;
		tail=n;
		size=1;
		this.m=m;
	}
	
	public void addToTail(){
		Node node=null;
		switch(tail.dirc){
		case L:
			node=new Node(tail.row,tail.col+1,tail.dirc);
			break;
		case U:
			node=new Node(tail.row+1,tail.col,tail.dirc);
			break;
		case R:
			node=new Node(tail.row,tail.col-1,tail.dirc);
			break;
		case D:
			node=new Node(tail.row-1,tail.col,tail.dirc);
			break;
		}
		tail.next=node;
		node.prev= tail;
		tail=node;
		size++;
	}
	public void addToHead(){
		Node node=null;
		switch(head.dirc){
		case L:
			node=new Node(head.row,head.col-1,head.dirc);
			break;
		case U:
			node=new Node(head.row-1,head.col,head.dirc);
			break;
		case R:
			node=new Node(head.row,head.col+1,head.dirc);
			break;
		case D:
			node=new Node(head.row+1,head.col,head.dirc);
			break;
		}
		node.next=head;
		head.prev=node;
		head=node;
		size++;
	}
	
	public void draw(Graphics g){
		if (size<=0) return;
		move();
		for(Node n =head;n!=null;n=n.next){
			n.draw(g);
		}
	}
	
	private void move() {
		addToHead();
		deleteFromTail();
		checkDead();
	}

	private void checkDead() {
		if(head.row<0 || head.row<3 || head.row>MainPanel.rows ||head.col>MainPanel.cols){
			m.stop();
		}
		for(Node n =head.next ; n!=null;n=n.next){
			if(head.row == n.row&&head.col==n.col){
				m.stop();
			}
		}
	}

	private void deleteFromTail() {
		if(size == 0) return;
		tail=tail.prev;
		tail.next=null;
	}

	private class Node{
		int w=MainPanel.Block_Size;
		int h=MainPanel.Block_Size;
		int row,col;
		Dirc dirc=Dirc.R;
		Node next=null;
		Node prev=null;
		
		Node(int row, int col,Dirc dirc) {
			this.row = row;
			this.col = col;
			this.dirc=dirc;
		}
		
		void draw(Graphics g){
			Color c =g.getColor();
			g.setColor(Color.black);
			g.fillRect(MainPanel.Block_Size*col, MainPanel.Block_Size*row, w, h);
			g.setColor(c);
		}
	}
	public void eat(Egg e){
		if(this.getRect().intersects(e.getRect())){
			e.reAppear();
			this.addToHead();
			m.setScore(m.getScore()+5);
		}
	}
	private Rectangle getRect(){
		return new Rectangle(MainPanel.Block_Size*head.col, MainPanel.Block_Size*head.row, head.w, head.h);
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key){
		case KeyEvent.VK_LEFT :
			if(head.dirc != Dirc.R)
				head.dirc=Dirc.L;
			break;
		case KeyEvent.VK_UP :
			if(head.dirc != Dirc.D)
				head.dirc=Dirc.U;
			break;
		case KeyEvent.VK_RIGHT :
			if(head.dirc != Dirc.L)
				head.dirc=Dirc.R;
			break;
		case KeyEvent.VK_DOWN :
			if(head.dirc != Dirc.U)
				head.dirc=Dirc.D;
			break;
		}
		
	}
	
}
