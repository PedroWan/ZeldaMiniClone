package zeldaminiclone;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{
	
	public static int WIDTH = 640, HEIGTH = 480;
	public static int SCALE = 3;
	public Player player; 
	
	public static World world;
	public List<Inimigo> inimigos = new ArrayList <Inimigo>();
	
	public Game () {
		this.addKeyListener(this);
		this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGTH*SCALE));
		new Spritesheet();
		player = new Player(32,32);
		world = new World();
		inimigos.add(new Inimigo(32,32));
		inimigos.add(new Inimigo(40,32));
		inimigos.add(new Inimigo(45,32));

	}
	
	public void tick() {
		player.tick();
		
		for(int i = 0; i < inimigos.size(); i++) {
			inimigos.get(i).tick();
		}
		
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(new Color(0,135,13));
		g.fillRect(0, 0, WIDTH, HEIGTH);
		
		player.render(g);
		for(int i = 0; i < inimigos.size(); i++) {
			inimigos.get(i).tick();
		}
		world.render(g);
		
		bs.show();
		
	}
	
	
	public static void main(String [] args) {
		Game game = new Game();
		JFrame frame = new JFrame();
		
		frame.add(game);
		frame.setTitle("Mini Zelda");
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		
		
		new Thread(game).start();
	}

	public void run() {
		
		while(true) {
			tick();
			render();
				try {
					Thread.sleep(1000/60);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
		
		
	}

	public void keyTyped(KeyEvent e) {		
			
	}

	public void keyPressed(KeyEvent e) {	
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
		player.rigth = true;
		
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
		player.left = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
		player.shoot = true;
		}
	
		if(e.getKeyCode() == KeyEvent.VK_UP) {
		player.up = true;
		
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
		player.down = true;
		}
		
}
	

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.rigth = false;
			
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = false;
			
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = false;
		}
		
	}

}
