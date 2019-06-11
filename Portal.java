import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Rectangle;
import java.awt.Color;
import java.util.ArrayList;

public class Portal{

	private int x;
	private int y;
	private int width;
	private int height;
	private Color body, outline;
	public Portal(int x, int y, int width, int height, Color body, Color outline){

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

}

