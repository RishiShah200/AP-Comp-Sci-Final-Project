import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.swing.JOptionPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.util.ArrayList;
public class Maze extends JPanel implements KeyListener,Runnable
{
	private JFrame frame;
	private Hero hero;
	private Thread thread;
	private boolean gameOn=true;
	private boolean right=false;
	private boolean up = false;
	private ArrayList<Wall> walls;
	private int dim = 20;
	private int dir = 0;
	private int frameX;
	private int frameY;
	private Key key;
	private Monster monster;
	private Portal portal;
	private Portal winGame;
	boolean gameEnd;
	boolean gameWin;
	boolean playerMove;
	boolean third;
	boolean keyCollected;
	String endFileName;
	boolean hopeTxtEndGame;
	//int musicCnt = 0;

	public Maze()
	{
		frame=new JFrame("Maze");
		frame.add(this);
		createMaze("outline.txt");
		hero = new Hero (0,4,dim,dim, Color.WHITE, Color.GREEN);
		monster = new Monster (2,60,dim,dim,Color.RED, Color.BLACK);
		portal = new Portal (12,17,dim,dim,Color.GREEN,Color.BLACK);
		winGame = new Portal (12,20,dim,dim,Color.RED,Color.YELLOW);
		key = new Key (22,3,dim,dim,Color.ORANGE, Color.WHITE);
		endFileName = "hope.txt";
		frameX = 400;
		frameY = 400;
		frame.addKeyListener(this);
		frame.setSize(1300,550);//change to frameX and frameY later
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		thread=new Thread(this);
		thread.start();
	}

	public void createMaze(String fileName)
	{

		walls = new ArrayList<Wall>();
		File name = new File(fileName);
		if(fileName.equals("hope.txt"))
			hopeTxtEndGame = true;
		try
		{

			BufferedReader input = new BufferedReader(new FileReader(name));	//reading in file information
			int row = 0;
			String text;
			while( (text=input.readLine())!= null)
			{
				for(int col = 0;col<text.length();col++){
					if(text.charAt(col) == 'X'){
						walls.add(new Wall (row,col,dim,dim));
					}
				}
				row++;
			}
		}
		catch (IOException io)
		{
			System.err.println("File does not exist");
		}
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		g2.setColor(Color.BLACK);
		g2.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g2.setColor(new Color(100,128,200));


		for(Wall wall: walls){
			g2.fill(wall.getRect());
		}
//creating the hero
		g2.setColor(hero.getBody());
		g2.fill(hero.getRect());
		g2.setColor(Color.GREEN);
		g2.setStroke(new BasicStroke(3));
		g2.draw(hero.getRect());
//creating the monster
		g2.setColor(monster.getBody());
		g2.fill(monster.getRect());
		g2.setColor(Color.YELLOW);
		g2.setStroke(new BasicStroke(3));
		g2.draw(monster.getRect());

//adding a portal to the 2nd  maze I created
		g2.setColor(portal.getBody());
		g2.fill(portal.getRect());
		g2.setColor(Color.ORANGE);
		g2.setStroke(new BasicStroke(3));
		g2.draw(portal.getRect());
//adding the winGame portal to the hope.txt maze
		g2.setColor(winGame.getBody());
		g2.fill(winGame.getRect());
		g2.setColor(Color.MAGENTA);
		g2.setStroke(new BasicStroke(3));
		g2.draw(winGame.getRect());

//game ending - lost
	if(gameEnd){
		g2.setColor(Color.BLACK);
		for(Wall wall: walls){
			g2.fill(wall.getRect());
		}
		frame.repaint();
		frame.removeAll();
		g2.setFont(new Font("Arial", Font.BOLD,200));
		g2.setColor(Color.RED);
		g2.drawString("Game Lost", 200,200);

	}
//game ending - win
		if(gameWin){
			g2.setColor(Color.BLACK);
			for(Wall wall: walls){
				g2.fill(wall.getRect());
			}
			frame.repaint();
			frame.removeAll();
			g2.setFont(new Font("Arial", Font.BOLD,200));
			g2.setColor(Color.RED);
			g2.drawString("Game Won.", 200,200);
		}

//creating the key you need for the portal to work
	g2.setColor(key.getBody());
	g2.fill(key.getRect());
	g2.setColor(Color.WHITE);
	g2.setStroke(new BasicStroke(3));
	g2.draw(key.getRect());

//if the key is collected, remove it
	if(keyCollected){
		g2.clearRect(key.getY()*key.getHeight(),key.getX()*key.getWidth(), key.getWidth(), key.getHeight());
		g2.setColor(Color.BLACK);
		g2.fillRect(key.getY()*key.getHeight(),key.getX()*key.getWidth(), key.getWidth(), key.getHeight());
		g2.setStroke(new BasicStroke(3));
		g2.draw(key.getRect());
	}
//display what all the components in the game are


	}
//creating the ledgend for the user to play




	public void run()
	{

//		playMusic("wiiMusic.wav");		//wii music hmmmmmm
		playMusic("finalDestination.wav");		//super smash bros music hmhmhmhmhmmh

		while(true)
		{
			if(gameOn)
			{
					//insert the music that i want played
				if(hero.getRect().intersects(key.getRect())){
					keyCollected = true;
				}

				if(hero.getX() == -1 && hero.getY() == 60){
					createMaze("hope.txt");
					hero.setX(33);
					winGame.setX(17);
					winGame.setY(35);
				}
				if(hero.getX() == 3 && hero.getY() == -1){
					createMaze("third.txt");		//this is the code for the third maze which I made
					hero.setX(3);
					hero.setY(63);
					monster.setX(15);
					monster.setY(50);
					portal.setX(3);
					portal.setY(37);
					third = true;
				}
				if(third && hero.getX() == 3 && hero.getY() == 64){
					createMaze("outline.txt");
					hero.setX(3);
					hero.setY(0);
				}
				System.out.println("Hero X" + hero.getX() + "Hero Y" + hero.getY());
				if(hero.getRect().intersects(portal.getRect())&& keyCollected){		//portal in outline which goes to hope.txt
					createMaze("hope.txt");
					hero.setX(30);
					hero.setY(55);
					monster.setX(20);
					monster.setY(60);
					portal.setX(300);
					portal.setY(300);
					winGame.setX(17);
					winGame.setY(35);
				}
	/*			System.out.println("This is the hero's Y location" + hero.getY()*hero.getHeight() + "Frame" + frameY);
				if(hero.getY()*hero.getHeight()>=frameY){
					frameY+=200;
					frame.reshape(400,400,600,600);		//fix algorithms for changing frame size
					frame.repaint();
				}
				if(hero.getY()*hero.getHeight()>=600){
					frameY+=200;
					frame.reshape(40,40,800,800);
					frame.repaint();
				}
				if(hero.getY()*hero.getHeight()>=800){
					frameY+=200;
					frame.reshape(40,40,1350,700);
					frame.repaint();
				}*/

			//	System.out.println("This is the player move" + playerMove);

			System.out.println("third  boolean value" + third);		//printing the boolean value for the third maze



					//move hero (if it can move)
					//pick stuff up if there is stuff to be picked up


				//move other stuff

				//check collisions if stuff is moving
				if(hero.getRect().intersects(monster.getRect())){
					gameEnd = true;
				}
				if(gameEnd){
					gameOn = false;
				//	playMusic("marioDie.wav");
				}
	//this is the line to win the game
				if(hero.getRect().intersects(winGame.getRect()) && hopeTxtEndGame){	//this is the line to win the game
					gameWin = true;
				//	playMusic("gameWinMusic.wav");
				}

			}
			try
			{
				thread.sleep(20);
			}catch(InterruptedException e)
			{
			}
			repaint();
		}
	}
	public void keyPressed(KeyEvent e)
	{
		playerMove = true;
		dir = e.getKeyCode();
		monster.move(hero.getX(), hero.getY(), walls);
		hero.move(dir,walls);

	}
	public void keyReleased(KeyEvent e)
	{
		playerMove = false;
		dir = 0;

	}
	public void keyTyped(KeyEvent e)
	{
	}

	public static void main(String[] args)
	{
		Maze app=new Maze();
	}

	public static void playMusic(String filepath) {
		InputStream music;
		//musicCnt++;
		try {
			music = new FileInputStream(new File(filepath));
			AudioStream audios = new AudioStream(music);
			AudioPlayer.player.start(audios);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error");
		}
    }

}