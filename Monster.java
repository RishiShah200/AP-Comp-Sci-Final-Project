import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Rectangle;
import java.awt.Color;
import java.util.ArrayList;

public class Monster{

	private int x;
	private int y;
	private int width;
	private int height;
	private Color body, outline;
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;

	public Monster(int x, int y, int width, int height,boolean up, boolean down, boolean left, boolean right,Color body, Color outline){

		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.body = body;
		this.outline = outline;
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}
	public void setX(int p){
			x = p;
	}
	public void setY(int p){
			y = p;
	}
	public int getHeight(){
		return height;
	}
	public boolean getUp(){
		return up;
	}
	public boolean getDown(){
			return down;
	}
	public boolean getLeft(){
			return left;
	}
	public boolean getRight(){
			return right;
	}
	public int getWidth(){
		return width;
	}
	public Color getBody(){
		return body;
	}

	public Color getOutline(){
			return outline;
	}
	public Rectangle getRect(int x, int y){
			return new Rectangle(y*getHeight(),x*getWidth(), getWidth(), getHeight());
	}
	public Rectangle getRect(){
			return new Rectangle( getY()*getHeight(),getX()*getWidth(), getWidth(), getHeight());
	}
	public void move(ArrayList<Wall> walls){

		boolean canMove = true;
		boolean up = true;
		boolean down = true;
		boolean left = true;
		boolean right = true;

			//left

			//up

					for(Wall wall : walls){
						if(getRect(x-1,y).intersects(wall.getRect()))
							up = false;
					}
					if(up)
						x--;



			//right

			for(Wall wall : walls){
				if(getRect(x,y+1).intersects(wall.getRect()))
					right = false;
			}
			if(right)
				y++;


			//left
			for(Wall wall : walls){
				if(getRect(x,y-1).intersects(wall.getRect()))
					left = false;
				}
			if(left && !right)
				y--;
			if(left && right){
				y++;
				left = false;
			}




			//down
			for(Wall wall : walls){
				if(getRect(x+1,y).intersects(wall.getRect()))
					down = false;
			}


		}

}
