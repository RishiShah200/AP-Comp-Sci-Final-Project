import java.awt.Rectangle;
public class Wall{

	private int height;
	private int width;
	private int x;
	private int y;

	public Wall(int x, int y, int width, int height){
		this.height = height;
		this.width = width;
		this.x = x;
		this.y = y;
	}

	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}

	public Rectangle getRect(){
		return new Rectangle( getY()*getHeight(),getX()*getWidth(), getWidth(), getHeight());
	}

}