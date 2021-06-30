package com.webcamarada.entities;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.sun.prism.Graphics;
import com.webcamarada.main.Game;
import com.webcamarada.world.Camera;
import com.webcamarada.world.World;

public class Enemy extends Entity {
	
	private double speed = 1;

	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void tick() {
		//if(Game.rand.nextInt(100) < 30) {
			if((int)getX() < Game.player.getX() && World.isFree((int)(getX()+speed), (int)getY()) && !isColidding((int)(getX()+speed), (int)getY())) {
				setX(getX()+speed);
			}else if((int)getX() > Game.player.getX() && World.isFree((int)(getX()-speed), (int)getY()) && !isColidding((int)(getX()+speed), (int)getY())) {
				setX(getX()-speed);
			}
			if((int)getY() < Game.player.getY() && World.isFree((int)getX(), (int)(getY()+speed)) && !isColidding((int)getX(), (int)(getY()+speed))) {
				setY(getY()+speed);
			}else if((int)getY() > Game.player.getY() && World.isFree((int)getX(), (int)(getY()-speed)) && !isColidding((int)getX(), (int)(getY()+speed))) {
				setY(getY()-speed);
			}
		//}
	}
	
	public boolean isColidding(int xNext, int yNext) {
		Rectangle enemyCurrent = new Rectangle(xNext, yNext, World.TILE_SIZE, World.TILE_SIZE);
		for(int i=0; i < Game.enemies.size(); i++) {
			Enemy e = Game.enemies.get(i);
			if(e == this)
				continue;
			
			Rectangle targetEnemy = new Rectangle((int)e.getX(), (int)e.getY(), World.TILE_SIZE, World.TILE_SIZE);
			if(enemyCurrent.intersects(targetEnemy)) {
				return true;
			}
		}
		return false;
		
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.blue);
		g.fillRect((int)(getX() - Camera.x), (int)(getY() - Camera.y), World.TILE_SIZE, World.TILE_SIZE);
	}

}
