package brick_bracker;

import java.awt.Color;
import java.awt.Graphics2D;
public class MapGenrator {

	public int map[][];
	public int brickwidht;
	public int brickheight;
	public MapGenrator(int row,int col) {
		map=new int[row][col];
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				map[i][j]=1;;
			}
		}
		brickwidht=540/col;
		brickheight=150/row;
	}
	
	public void draw(Graphics2D g) {
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++){
			if(map[i][j]>0) {	
				g.setColor(Color.blue);
				g.fillRect(j*brickwidht+80,i*brickheight+50,brickwidht,brickheight);
//				g.setStroke(new BasicStroke(3));
				g.setColor(Color.black);
				g.drawRect(j*brickwidht+80,i*brickheight+50,brickwidht,brickheight);
			}
			}
	}
	}
	public void setbrickvalue(int value,int row,int col) {
		map[row][col]=value;
	}
	}
