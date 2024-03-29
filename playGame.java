package brick_bracker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class playGame extends JPanel implements KeyListener, ActionListener{
     
	 private boolean play=false;
	 private int score=0;
	 
	 private int totalbricks=21;
	
	 private Timer time;
	 private int delay=1;
	 
	 private int playerx=310;
	 
	 private int ballposx=120;
	 private int ballposy=350;
	 private int ballxdir=-1;
	 private int ballydir=-2;
	 
	 private MapGenrator map;
	 
	 public playGame() {
		 map = new MapGenrator(3,7);
		 addKeyListener(this);
		 setFocusable(true);
		 setFocusTraversalKeysEnabled(false);
		 time=new Timer(delay,this);
		 time.start();
		 
	 }
	 
	 public void paint(Graphics g) {
		 //Background
		 g.setColor(Color.black);
		 g.fillRect(1,1,692,592);
		 
		 //drawing map
		 map.draw((Graphics2D)g);
		 
		 //Border
		 g.setColor(Color.yellow);
		 g.fillRect(0,0,3,592);
		 g.fillRect(0,0,692,3);
		 g.fillRect(691,0,3,592);
		 
		 //score
		 g.setColor(Color.white);
		 g.setFont(new Font("serif",Font.BOLD,25));
		 g.drawString(""+score,590,30);
		 
		 
		 //paddle
		 g.setColor(Color.green);
		 g.fillRect(playerx,550,100,8);
		 
		 //ball
		 g.setColor(Color.yellow);
		 g.fillOval(ballposx,ballposy,20,15);
		 
		 if(totalbricks<=0) {
			 play=false;
			 ballxdir=0;
			 ballydir=0;
			 g.setColor(Color.RED);
		     g.setFont(new Font("serif",Font.BOLD,30));
			 g.drawString("YOu Won",260,300);
			 
			 g.setFont(new Font("serif",Font.BOLD,20));
			 g.drawString("Press Enter to Restart",230,350);
			 
		 }
		 
		 if(ballposy>570) {
			 play=true;
			 ballxdir=0;
			 ballydir=0;
			 g.setColor(Color.RED);
		     g.setFont(new Font("serif",Font.BOLD,30));
			 g.drawString("Game Over",190,300);
			 
			 g.setFont(new Font("serif",Font.BOLD,20));
			 g.drawString("Press Enter to Restart",230,350);
			 
		 }
		 
		 
		 g.dispose();
		 
		 
	 }
	  
	@Override
	public void actionPerformed(ActionEvent e) {
		
		time.start();
		if(play) {
			
		    if(new Rectangle(ballposx,ballposy,20,20).intersects(new Rectangle(playerx,550,100,8)))
		    {
		    	ballydir=-ballydir;
		    }
		    
		   A: for(int i=0; i<map.map.length;i++) {
		    	for(int j=0; j<map.map[0].length;j++)
		    	{
		    		if(map.map[i][j]>0) {
		    			int brickx=j*map.brickwidht+80;
		    			int bricky=i*map.brickheight+50;
		    			int brickwidth=map.brickwidht;
		    			int brickheigth=map.brickheight;
		    			
		    			Rectangle rect=new Rectangle(brickx,bricky,brickwidth,brickheigth);
		    			Rectangle ballrect=new Rectangle(ballposx,ballposy,20,20);
		    		    Rectangle brickrect=rect;
		    		    
		    		    if(ballrect.intersects(brickrect)) {
		    		    	
		    		    	map.setbrickvalue(0,i,j);
		    		    	totalbricks--;
		    		    	score+=5;
		    		    	
		    		    	if(ballposx+19<=brickrect.x || ballposx+1>=brickrect.x+ brickrect.width) {
		    		    		ballxdir=-ballxdir;
		    		    	}
		    		    	else {
		    		    		ballydir=-ballydir;
		    		    	}
		    		    	break A;
		    		    }
		    		    
		    		}
		    	}
		    }
		    
			
			ballposx += ballxdir;
			ballposy += ballydir;
			
			if(ballposx<0) {
				ballxdir = -ballxdir;	
			}
			if(ballposy <0) {
				ballydir = -ballydir;
			}
			if(ballposx > 670) {
				ballxdir= -ballxdir;
			}
		}
		
		
		
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
	
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			if(playerx>=600) {
				playerx=600;
			}else
			{
				moveRight();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			if(playerx<10) {
				playerx=10;
			}else
			{
				moveLeft();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			if(play) {
				play=true;
				ballposx=120;
				ballposy=350;
				ballxdir=-1;
				ballydir=-2;
				playerx=310;
				score=0;
				totalbricks=21;
				map=new MapGenrator(3,7);
				repaint();
			}
		}
	}
	public void moveRight() {
		play=true;
		playerx+=20;
	}
	public void moveLeft() {
		play=true;
		playerx-=20;
	}

	
  }