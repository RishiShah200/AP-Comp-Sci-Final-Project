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

	public Monster(int x, int y, int width, int height,Color body, Color outline){

		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.body = body;
		this.outline = outline;
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
		public void move(int herox, int heroy, ArrayList<Wall> walls){
			boolean canMove = true;
			boolean hasMoved = false;
				//left
			if(heroy < getY()){
					for(Wall wall: walls){
						if(getRect(x,y-1).intersects(wall.getRect()))
							canMove = false;
					}
					if(canMove){
						y--;
						hasMoved = true;
					}
				}
			canMove = true;
				//up
			if(herox < getX() && !hasMoved){
					for(Wall wall: walls){
						if(getRect(x-1,y).intersects(wall.getRect())||(getX() == 0 && getY() == 2))
							canMove = false;
					}
					if(canMove){
						x--;
						hasMoved = true;
					}
				}
			canMove = true;
				//right
			if(heroy > getY() && !hasMoved){
					for(Wall wall: walls){
						if(getRect(x,y+1).intersects(wall.getRect()))
							canMove = false;
					}
					if(canMove){
						y++;
						hasMoved = true;
					}
				}
			canMove = true;
				//down
			if(herox > getX() && !hasMoved){
					for(Wall wall: walls){
						if(getRect(x+1,y).intersects(wall.getRect()))
							canMove = false;
					}
					if(canMove){
						x++;
						hasMoved = true;
				}
			canMove = true;
			}
			if(!hasMoved){
					//down
					for(Wall wall: walls){
						if(getRect(x+1,y).intersects(wall.getRect()))
							canMove = false;
						}
					if(canMove)
						x++;
					hasMoved = true;
			}
			if(!hasMoved){
				//left
					for(Wall wall1: walls){
						if(getRect(x,y-1).intersects(wall1.getRect()))
							canMove = false;
					}
					if(canMove)
						y--;
					hasMoved = true;
			}
			if(!hasMoved){
					//up
					for(Wall wall2: walls){
						if(getRect(x-1,y).intersects(wall2.getRect())||(getX() == 0 && getY() == 2))
							canMove = false;
					}
					if(canMove)
						x--;
					hasMoved = true;

			}
			if(!hasMoved){
					//right
					for(Wall wall3: walls){
						if(getRect(x,y+1).intersects(wall3.getRect()))
							canMove = false;
					}
					if(canMove)
						y++;
			}
	}

}
